/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.service.ProductPriceService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.entity.DealerShopers;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerShoperMapper;
import com.zttx.web.module.dealer.mapper.DealerShopersMapper;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商购物车 服务实现类
 * <p>File：DealerShoper.java </p>
 * <p>Title: DealerShoper </p>
 * <p>Description:DealerShoper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShoperServiceImpl extends GenericServiceApiImpl<DealerShoper> implements DealerShoperService
{
    private static final Logger         logger = LoggerFactory.getLogger(DealerShoperServiceImpl.class);
    
    private DealerShoperMapper          dealerShoperMapper;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private DealerShopersMapper         dealerShopersMapper;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private ProductPriceService         productPriceService;
    
    @Autowired(required = false)
    private DealerJoinMapper            dealerJoinMapper;
    
    @Autowired
    private DealerOrderService          dealerOrderService;
    
    @Autowired(required = false)
    private ProductSkuService           productSkuService;
    
    @Autowired
    private BrandInfoService            brandInfoService;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired(required = true)
    public DealerShoperServiceImpl(DealerShoperMapper dealerShoperMapper)
    {
        super(dealerShoperMapper);
        this.dealerShoperMapper = dealerShoperMapper;
    }
    
    @Override
    public Long getShoperCountByUserId(String userId)
    {
        return dealerShoperMapper.getShoperCountByUserId(userId);
    }
    
    /* =========================================批量/单一 新增车单 [@author易永耀] begin================================================ */
    @Override
    public void batchSaveShopers(List<String> productsId, List<Integer> productsType, String dealerId) throws BusinessException
    {
        if (ListUtils.isSizeEqual(productsId, productsType))
        {
            List<Map<String, Object>> mapList = Lists.newArrayList();
            for (int i = 0; i < productsId.size(); i++)
            {
                Map<String, Object> map = Maps.newHashMap();
                map.put("productId", productsId.get(i));
                map.put("productType", productsType.get(i));
                mapList.add(map);
            }
            this.batchSaveDealerShoperList(mapList, dealerId);
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
    }
    
    /**
     * 产品详情直接加车
     *
     * @param productId   产品id
     * @param productType 类型
     * @param dealerId    经销商id
     * @param skuIds      not null
     * @param amounts     not null
     * @throws BusinessException
     */
    @Override
    public String saveShopers(String productId, Short productType, String dealerId, List<String> skuIds, List<Integer> amounts) throws BusinessException
    {
        DealerShoper oldDealerShoper = dealerShoperMapper.selectDealerShoperBy(dealerId, productId);
        if (null == oldDealerShoper)
        {
            Map<String, Integer> skuAmountsMap = Maps.newHashMap();
            if (productType != DealerConstant.DealerShoper.PRODUCTTYPE_SAM) // 拿样产品不进行sku检测
            {
                skuAmountsMap = getSkuAmountsMap(skuIds, amounts);
            }
            ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
            if (null == productBaseInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(productId, false);
            if (null == productSkuList) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            DealerShoper dealerShoper = buildDealerShoper(dealerId, productType, productBaseInfo);
            List<DealerShopers> dealerShopersList = buildDealerShopersList(dealerShoper, skuAmountsMap, productSkuList);
            this.calTotalProductNum(dealerShoper, dealerShopersList);
            if (null != productType)
            {
                for (DealerShopers dealerShopers : dealerShopersList)
                {
                    dealerShopers.setProductType(productType);
                }
                dealerShoper.setProductType(productType);
            }
            dealerShoperMapper.insertSelective(dealerShoper);
            dealerShopersMapper.insertBatch(dealerShopersList);
            return dealerShoper.getRefrenceId();
        }
        else
        { // 购物车已经有的产品，会购买数量叠加
            Map<String, Integer> skuAmountsMap = getSkuAmountsMap(skuIds, amounts);
            List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(productId, false);
            List<DealerShopers> dealerShopersList = buildDealerShopersList(oldDealerShoper, skuAmountsMap, productSkuList);
            for (DealerShopers dealerShopers : dealerShopersList)
            {
                dealerShopers.setProductType(productType);
                dealerShopersMapper.updateByPrimaryKeySelective(dealerShopers);
            }
            this.calTotalProductNum(oldDealerShoper, dealerShopersList);
            oldDealerShoper.setProductType(productType);
            dealerShoperMapper.updateByPrimaryKeySelective(oldDealerShoper);
            return oldDealerShoper.getRefrenceId();
        }
    }
    
    @Override
    public void saveCountShopersToShoper(String dealerId, Map<String, DealerShoper> dealerShoperMap) throws BusinessException
    {
        for (String productId : dealerShoperMap.keySet())
        {
            DealerShoper dealerShoper = dealerShoperMap.get(productId);
            if (dealerShoper == null)
            {
                continue;
            }
            this.saveDealerShoper(productId, dealerShoper.getProductType(), dealerShoper.getDealerShopersList(), dealerId);
        }
        /*
         * for (DealerShoper dealerShoper : mapModel.values())
         * {
         * if (dealerShoper == null)
         * {
         * continue;
         * }
         * int purchaseNumSum = 0;
         * BigDecimal purchasePriceSum = BigDecimal.ZERO;
         * DealerShoper oldDealerShoper = dealerShoperMapper.selectDealerShoperBy(dealerShoper.getDealerId(), dealerShoper.getProductId());
         * if (oldDealerShoper == null)
         * {
         * if (dealerShoper.getDealerShopersList().size() > 0)
         * {
         * for (DealerShopers dealerShopers : dealerShoper.getDealerShopersList())
         * {
         * purchaseNumSum += dealerShopers.getPurchaseNum();
         * purchasePriceSum = purchasePriceSum.add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum())));
         * }
         * }
         * dealerShoper.setProductNum(purchaseNumSum);
         * dealerShoper.setProductPrice(purchasePriceSum);
         * dealerShoper.setDiscountPrice(dealerShoper.getProductPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getDiscount())));
         * dealerShoperMapper.insert(dealerShoper);
         * this.dealerShopersMapper.insertBatch(dealerShoper.getDealerShopersList());
         * }
         * }
         */
    }
    
    /**
     * 计算购买总量,折扣值计算
     *
     * @param dealerShopersList
     * @return
     */
    private void calTotalProductNum(DealerShoper dealerShoper, List<DealerShopers> dealerShopersList)
    {
        Integer totalProductNum = 0;
        BigDecimal totalProductPrice = BigDecimal.ZERO;
        if (!dealerShopersList.isEmpty())
        {
            for (DealerShopers dealerShopers : dealerShopersList)
            {
                totalProductNum += dealerShopers.getPurchaseNum();// 统计总和
                totalProductPrice = totalProductPrice.add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum())));
            }
        }
        Integer productNum = dealerShoper.getProductNum() != null ? dealerShoper.getProductNum() : 0;
        dealerShoper.setProductNum(productNum + totalProductNum);
        dealerShoper.setProductPrice(totalProductPrice);
        Map<String, Object> map = dealerJoinMapper.getValidMap(dealerShoper.getDealerId(), dealerShoper.getBrandsId(), dealerShoper.getProductId());
        if (MapUtils.getBoolean(map, "isDiscount", false)) // 产品支持折扣 才能有折扣值
        {
            dealerShoper.setDiscount(new BigDecimal(MapUtils.getString(map, "discount")));
        }
        else
        {
            dealerShoper.setDiscount(BigDecimal.ONE);
        }
        dealerShoper.setDiscountPrice(dealerShoper.getProductPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getDiscount())));
    }
    
    /**
     * 构建购物车sku
     *
     * @param dealerShoper   购物车
     * @param skuAmountsMap  数量信息
     * @param productSkuList skuList
     * @return {@List}
     * @throws BusinessException
     */
    private List<DealerShopers> buildDealerShopersList(DealerShoper dealerShoper, Map<String, Integer> skuAmountsMap, List<ProductSku> productSkuList)
            throws BusinessException
    {
        List<DealerShopers> dealerShopersList = Lists.newArrayList();
        for (ProductSku productSku : productSkuList)
        {
            // 购物车项已经存在就叠加已经存在的数量
            DealerShoper oldDealerShoper = dealerShoperMapper.selectByPrimaryKey(dealerShoper.getRefrenceId());
            DealerShopers dealerShopers = dealerShopersMapper.getByproductSkuIdAndDealerShopId(productSku.getRefrenceId(), dealerShoper.getRefrenceId());
            if (null != oldDealerShoper && null == dealerShopers) // 订单存在，订单项不存在，新增订单项，然后在更新订单项
            {
                dealerShopers = new DealerShopers();
                dealerShopers.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerShopers.setShoperId(dealerShoper.getRefrenceId());
                dealerShopers.setProductId(dealerShoper.getProductId());
                dealerShopers.setProductSkuId(productSku.getRefrenceId());
                dealerShopers.setProductSkuCode(productSku.getRefrenceId());
                dealerShopers.setProductSkuName(productSku.getProductSkuName());
                dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                dealerShopers.setProductStock(productSku.getQuantity());
                dealerShopers.setProductType(dealerShoper.getProductType());
                BigDecimal productSkuPrice = this.getProductSkuPriceByProductType(productSku.getRefrenceId(), dealerShoper.getProductType() + "");
                dealerShopers.setProductSkuPrice(productSkuPrice);
                Integer num = skuAmountsMap.get(productSku.getRefrenceId());// 从map取数量
                dealerShopers.setPurchaseNum(null != num ? num : 0);
                dealerShopers.setCreateTime(dealerShoper.getCreateTime());
                dealerShopers.setUpdateTime(dealerShoper.getUpdateTime());
                dealerShopers.setDelFlag(false);
                dealerShopersMapper.insert(dealerShopers);
            }
            if (null != dealerShopers)
            {
                Integer num = skuAmountsMap.get(productSku.getRefrenceId());
                dealerShopers.setPurchaseNum(dealerShopers.getPurchaseNum() + (null != num ? num : 0));
            }
            else
            {
                dealerShopers = new DealerShopers();
                dealerShopers.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerShopers.setShoperId(dealerShoper.getRefrenceId());
                dealerShopers.setProductId(dealerShoper.getProductId());
                dealerShopers.setProductSkuId(productSku.getRefrenceId());
                dealerShopers.setProductSkuCode(productSku.getRefrenceId());
                dealerShopers.setProductSkuName(productSku.getProductSkuName());
                dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                dealerShopers.setProductStock(productSku.getQuantity());
                dealerShopers.setProductType(dealerShoper.getProductType());
                BigDecimal productSkuPrice = this.getProductSkuPriceByProductType(productSku.getRefrenceId(), dealerShoper.getProductType() + "");
                dealerShopers.setProductSkuPrice(productSkuPrice);
                Integer num = skuAmountsMap.get(productSku.getRefrenceId());// 从map取数量
                dealerShopers.setPurchaseNum(null != num ? num : 0);
                dealerShopers.setCreateTime(dealerShoper.getCreateTime());
                dealerShopers.setUpdateTime(dealerShoper.getUpdateTime());
                dealerShoper.setDelFlag(false);
            }
            dealerShopersList.add(dealerShopers);
        }
        return dealerShopersList;
    }
    
    /**
     * 构建购物车
     *
     * @param dealerId
     * @param productType
     * @param productBaseInfo
     * @return
     * @author 章旭楠
     */
    private DealerShoper buildDealerShoper(String dealerId, Short productType, ProductBaseInfo productBaseInfo)
    {
        DealerShoper dealerShoper = new DealerShoper();
        dealerShoper.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerShoper.setDealerId(dealerId);
        dealerShoper.setBrandId(productBaseInfo.getBrandId());
        dealerShoper.setBrandsId(productBaseInfo.getBrandsId());
        dealerShoper.setProductId(productBaseInfo.getRefrenceId());
        dealerShoper.setProductType(productType);
        dealerShoper.setSourceId(CommonConstant.sourceType.ZTTX);
        dealerShoper.setDelFlag(false);
        dealerShoper.setProductNum(0);
        dealerShoper.setProductPrice(BigDecimal.ZERO);
        dealerShoper.setCreateTime(CalendarUtils.getCurrentTime());
        dealerShoper.setUpdateTime(CalendarUtils.getCurrentTime());
        dealerShoper.setDiscountPrice(BigDecimal.ZERO);
        return dealerShoper;
    }
    
    /**
     * 构建sku 数量信息
     *
     * @param skuIds  skuId
     * @param amounts 数量
     * @return {@link Map}
     * @author 章旭楠
     */
    private Map<String, Integer> getSkuAmountsMap(List<String> skuIds, List<Integer> amounts) throws BusinessException
    {
        Map<String, Integer> skuAmountsMap = null;
        if (null != skuIds && null != amounts)
        {
            skuAmountsMap = Maps.newHashMap();// 存放sku 对应数量
            if (ListUtils.isSizeEqual(skuIds, amounts))
            {
                for (int i = 0; i < skuIds.size(); i++)
                {
                    if (null == amounts.get(i) || amounts.get(i) == 0)
                    {
                        continue; // 排除购买数量是0或null的sku信息
                    }
                    skuAmountsMap.put(skuIds.get(i), amounts.get(i));
                }
            }
            if (skuAmountsMap.size() == 0) { throw new BusinessException(CommonConst.FAIL.getCode(), "请选择一款颜色尺码!"); }
        }
        return skuAmountsMap;
    }
    
    private Boolean batchSaveDealerShoperList(List<Map<String, Object>> mapList, String dealerId) throws BusinessException
    {
        if (ListUtils.isNotEmpty(mapList))
        {
            List<DealerShoper> dealerShoperList = new ArrayList<>();
            List<DealerShopers> dealerShopersList = new ArrayList<>();
            for (Map<String, Object> map : mapList)
            {
                if (map.get("productId") instanceof String && map.get("productType") instanceof Integer)
                {
                    String productId = (String) map.get("productId");
                    Integer productType = (Integer) map.get("productType");
                    this.buildShoperAndShopersParams(dealerShoperList, dealerShopersList, productId, productType.toString(), dealerId);
                }
            }
            if (ListUtils.isNotEmpty(dealerShoperList)/* && ListUtils.isNotEmpty(dealerShopersList)*/)
            {
                dealerShoperMapper.insertBatch(dealerShoperList);
               // dealerShopersMapper.insertBatch(dealerShopersList);
            }
            return true;
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
    }
    
    private void buildShoperAndShopersParams(List<DealerShoper> dealerShoperList, List<DealerShopers> dealerShopersList, String productId, String productType,
            String dealerId) throws BusinessException
    {
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        if (null != productBaseInfo && dealerId != null)
        {
            DealerShoper oldDealerShoper = dealerShoperMapper.selectDealerShoperBy(dealerId, productId);
            if (oldDealerShoper == null)
            {
                DealerShoper dealerShoper = new DealerShoper();
                dealerShoper.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerShoper.setDealerId(dealerId);
                dealerShoper.setBrandId(productBaseInfo.getBrandId());
                dealerShoper.setBrandsId(productBaseInfo.getBrandsId());
                dealerShoper.setProductId(productId);
                dealerShoper.setProductType(Short.valueOf(productType));
                dealerShoper.setSourceId(CommonConstant.sourceType.ZTTX);
                dealerShoper.setDelFlag(false);
                dealerShoper.setProductNum(0);
                dealerShoper.setProductPrice(BigDecimal.ZERO);
                dealerShoper.setCreateTime(CalendarUtils.getCurrentTime());
                dealerShoper.setUpdateTime(CalendarUtils.getCurrentTime());
                dealerShoper.setDiscountPrice(BigDecimal.ZERO);
                dealerShoperList.add(dealerShoper);
              /*  List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(productId, false);
                if (ListUtils.isNotEmpty(productSkuList))
                {
                    for (ProductSku productSku : productSkuList)
                    {
                        DealerShopers dealerShopers = new DealerShopers();
                        dealerShopers.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        dealerShopers.setShoperId(dealerShoper.getRefrenceId());
                        dealerShopers.setProductId(productId);
                        dealerShopers.setProductSkuId(productSku.getRefrenceId());
                        dealerShopers.setProductSkuCode(productSku.getRefrenceId());
                        dealerShopers.setProductSkuName(productSku.getProductSkuName());
                        dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                        dealerShopers.setProductSkuCode(productSku.getProductSkuNo());
                        dealerShopers.setProductStock(productSku.getQuantity());
                        dealerShopers.setProductType(dealerShoper.getProductType());
                        BigDecimal productSkuPrice = this.getProductSkuPriceByProductType(productSku.getRefrenceId(), productType);
                        dealerShopers.setProductSkuPrice(productSkuPrice);
                        dealerShopers.setPurchaseNum(0);
                        dealerShopers.setCreateTime(dealerShoper.getCreateTime());
                        dealerShopers.setUpdateTime(dealerShoper.getUpdateTime());
                        dealerShoper.setDelFlag(false);
                        dealerShopersList.add(dealerShopers);
                    }
                }*/
            }
        }
    }
    
    private BigDecimal getProductSkuPriceByProductType(String refrenceId, String productType) throws BusinessException
    {
        ProductSkuPrice productSkuPrice = productSkuInfoDubboConsumer.getPriceBySkuId(refrenceId);
        if (null == productSkuPrice) { throw new BusinessException("skuid:" + refrenceId + "对应的productSkuPrice不存在"); }
        // sku价格判断
        if (Integer.parseInt(productType) == BrandConstant.productPriceType.PRODUCT_DIR)
        {
            return productSkuPrice.getDirectPrice() != null ? productSkuPrice.getDirectPrice() : BigDecimal.ZERO;
        }
        else if (Integer.parseInt(productType) == BrandConstant.productPriceType.PRODUCT_CRE)
        {
            return productSkuPrice.getCreditPrice() != null ? productSkuPrice.getCreditPrice() : BigDecimal.ZERO;
        }
        else if (Integer.parseInt(productType) == BrandConstant.productPriceType.PRODUCT_SAM) { return productSkuPrice.getSamplePrice() != null ? productSkuPrice
                .getSamplePrice() : BigDecimal.ZERO; }
        return productSkuPrice.getSkuPrice() != null ? productSkuPrice.getSkuPrice() : BigDecimal.ZERO;
    }
    
    /* =========================================批量/单一 新增车单 end ================================================ */
    @Override
    public List<DealerShoper> selectDealerShoperBy(String dealerId, Boolean isHome) throws BusinessException
    {
        isHome = (null != isHome) && isHome;
        List<DealerShoper> dealershoperList = Lists.newArrayList();
        if (null != dealerId)
        {
            dealershoperList = dealerShoperMapper.selectDealerShoperByDealerId(dealerId, isHome);
        }
        for (DealerShoper dealerShoper : dealershoperList)
        {
            if (null == dealerShoper)
            {
                continue;
            }
            // 产品已经存在有效订单的，不具备拿样资格
            dealerShoper.setIsSample(dealerShoper.getIsSample() && !dealerOrderService.isHasTakeSample(dealerId, dealerShoper.getProductId()));
            // Boolean isPoint = MapUtils.getBoolean(validMap, "isPoint", Boolean.FALSE) && dealerShoper.getIsPoint();// 支持返点：产品支持返点&&加盟支持返点
            dealerShoper.setIsPoint(dealerJoinService.isSupportPoint(dealerId, dealerShoper.getProductId()));// 考虑到 validMap 中不一定 存在isPoint，改成直接查数据库
            this.getValidMapWithTypeAndPrice(dealerId, dealerShoper);
        }
        return dealershoperList;
    }
    
    /**
     * 由于是否返点可能会变更，而购物车的加车类型没有即时同步
     * 矫正加车类型：
     *
     * @param dealerShoper 购物车
     * @param type         经销商授权产品库中 产品与经销商之间的 状态
     *                     0; 产品失效
     *                     1; 产品下架
     *                     2; 产品无权购买
     *                     3; 产品现款购买权
     *                     4; 产品授信购买权
     * @param isPoint      当前是否支持返点
     */
    private void correctProductType(DealerShoper dealerShoper, Short type, Boolean isPoint)
    {
        DealerShoper dbDealerShoper = dealerShoperMapper.selectByPrimaryKey(dealerShoper.getRefrenceId());
        if (null == dbDealerShoper) { return; }
        Short productType = dbDealerShoper.getProductType();// （加车类型） 0; 现款 1; 授信 2; 拿样 3; 返点
        // 1:原本是支持返点，现在不支持返点:根据购买权限设置购物车类型
        if (!isPoint && DealerConstant.DealerShoper.PRODUCTTYPE_POINT == productType)
        {
            // 根据产品购买权 还原 购物车类型
            dealerShoper.setProductType(DealerConstant.productState.PRODUCT_CREDIT == type ? DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT
                    : DealerConstant.DealerShoper.PRODUCTTYPE_CASH);
            updateDealerShoperProductType(dbDealerShoper, dealerShoper.getProductType());
        }
        else if (isPoint && DealerConstant.DealerShoper.PRODUCTTYPE_POINT != productType)
        {// 2:原本不支持返点，现在支持
            dealerShoper.setProductType(DealerConstant.DealerShoper.PRODUCTTYPE_POINT);// 不影响原数据
            updateDealerShoperProductType(dbDealerShoper, dealerShoper.getProductType());
        }
    }
    
    private void updateDealerShoperProductType(DealerShoper dbDealerShoper, Short productType)
    {
        dbDealerShoper.setProductType(productType);
        dbDealerShoper.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerShoperMapper.updateByPrimaryKey(dbDealerShoper);
    }
    
    @Override
    public int batchRemoveShopers(List<String> dealerShopersId, String dealerId)
    {
        if (ListUtils.isNotEmpty(dealerShopersId) && null != dealerId)
        {
            int num = 0;
            for (String shoperId : dealerShopersId)
            {
                if (0 < dealerShoperMapper.deleteByPrimaryKey(shoperId))
                {
                    dealerShopersMapper.deleteBatch(shoperId);
                    num++;
                }
            }
            return num;
        }
        else
        {
            return dealerShoperMapper.deleteDealerShoperAndShopers(dealerId);
        }
    }
    
    @Override
    public DealerShoper updateProductTradeModel(String dealerId, String shoperId, Integer productType) throws BusinessException
    {
        if (null != shoperId && null != dealerId)
        {
            BigDecimal priceSum = BigDecimal.ZERO;
            DealerShoper dealerShoper = dealerShoperMapper.selectByPrimaryKey(shoperId);
            if (null != dealerShoper && dealerShoper.getProductType() != productType.shortValue())
            {
                List<DealerShopers> dealerShopersList = dealerShopersMapper.selectDealerShopersBy(shoperId, dealerShoper.getProductType());
                if (ListUtils.isNotEmpty(dealerShopersList))
                {
                    for (DealerShopers dealerShopers : dealerShopersList)
                    {
                        dealerShopers.setProductType(productType.shortValue());
                        dealerShopers.setProductSkuPrice(this.getProductSkuPriceByProductType(dealerShopers.getProductSkuId(), productType.toString()));
                        priceSum = priceSum.add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum().toString())));
                        dealerShopersMapper.updateByPrimaryKeySelective(dealerShopers);
                    }
                    if (productType.shortValue() == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT) // 控制折扣价
                    {
                        DealerJoin dealerJoint = dealerJoinMapper.findByDealerIdAndBrandsId(dealerId, dealerShoper.getBrandsId());
                        if (null != dealerJoint)
                        {
                            dealerShoper.setDiscountPrice(priceSum.multiply(BigDecimal.ONE.subtract(dealerJoint.getDiscount())));
                        }
                    }
                    else
                    {
                        dealerShoper.setDiscountPrice(BigDecimal.ZERO);
                    }
                    dealerShoper.setProductPrice(priceSum);
                    dealerShoper.setProductType(productType.shortValue());
                    dealerShoperMapper.updateByPrimaryKeySelective(dealerShoper);
                    dealerShoper.setDealerShopersList(dealerShopersList);
                    return dealerShoper;
                }
            }
        }
        return null;
    }
    
    @Override
    public void getValidMapWithTypeAndPrice(String dealerId, DealerShoper dealerShoper) throws BusinessException
    {
        Map<String, Object> validMap = dealerJoinMapper.getValidMap(dealerId, dealerShoper.getBrandsId(), dealerShoper.getProductId());
        if (validMap != null)
        {
            validMap.put("brandState", dealerShoper.getBrandState());
            validMap.put("productDelFlag", dealerShoper.getProductDelFlag());
            validMap.put("brandDelFlag", dealerShoper.getBrandDelFlag());
            validMap.put("isCredit", dealerShoper.getIsCredit());
            validMap.put("productState", dealerShoper.getProductStateSet());
            validMap.put("isPoint", dealerShoper.getIsPoint());// 是否支持返点：查询当前数据库 产品支持返点+返点加盟
            Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPrice(validMap);
            if (resultMap != null)
            {
                dealerShoper.setType(MapUtils.getShortValue(resultMap, "type"));
                correctProductType(dealerShoper, dealerShoper.getType(), dealerShoper.getIsPoint());// 需要矫正加车类型
                dealerShoper.setMessage(MapUtils.getString(resultMap, "message"));
                // 产品支持折扣 并且 不是返点类型 才能有折扣值
                dealerShoper.setDiscount(MapUtils.getBoolean(validMap, "isDiscount", false) && !dealerShoper.getIsPoint() ? new BigDecimal(MapUtils.getString(validMap,
                        "discount", "1")) : BigDecimal.ONE);
            }
        }
        else
        {
            validMap = Maps.newHashMap();
            validMap.put("brandsId", dealerShoper.getBrandsId());
            validMap.put("dealerId", dealerId);
            validMap.put("brandState", dealerShoper.getBrandState());
            validMap.put("brandDelFlag", dealerShoper.getBrandDelFlag());
            validMap.put("productState", dealerShoper.getProductStateSet());
            validMap.put("productDelFlag", dealerShoper.getProductDelFlag());
            Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPriceByAreaNo(validMap);
            if (null != resultMap)
            {
                dealerShoper.setType(MapUtils.getShortValue(resultMap, "type"));
                dealerShoper.setMessage(MapUtils.getString(resultMap, "message"));
            }
            dealerShoper.setDiscount(BigDecimal.ONE); // 区域授权折扣为1
        }
    }
    
    @Override
    public DealerShoper productToBalance(String productId, Short productType, List<String> skuIds, List<Integer> purchaseNums, String dealerId) throws BusinessException
    {
        validateParams(productId, productType, skuIds, purchaseNums, dealerId);
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        ProductExtInfo productExtInfo = (productBaseInfo == null ? null : productBaseInfo.getProductExtInfo());
        if (null == productBaseInfo || null == productExtInfo || null == productBaseInfo.getBrandId() || null == productBaseInfo.getBrandsId()) { throw new BusinessException(
                CommonConst.DATA_NOT_EXISTS.code, "查询异常：" + productId + "该产品不存在或品牌信息不存在"); }
        DealerShoper dealerShoper = new DealerShoper();
        dealerShoper.setIsPoint(dealerJoinService.isSupportPoint(dealerId, productId));
        if (dealerShoper.getIsPoint())// 支持返点
        {
            productType = DealerConstant.DealerShoper.PRODUCTTYPE_POINT;// 强制转返点类型
        }
        else if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_POINT) { throw new BusinessException(CommonConst.FAIL.code, "该产品已经不支持返点类型，结算失败"); }
        List<DealerShopers> dealerShopersList = Lists.newArrayList();
        this.buildDealerShoper(dealerShoper, dealerId, productType, productBaseInfo);
        this.setMoreInfo2DealerShoper(dealerShoper, productBaseInfo);
        this.getValidMapWithTypeAndPrice(dealerId, dealerShoper);
        this.buildDealerShopersList(dealerShopersList, dealerShoper, skuIds, purchaseNums);
        this.countDealerShoper(dealerShoper, dealerShopersList);
        if (dealerShoper.getProductNum() < productExtInfo.getStartNum()) { throw new BusinessException(DealerConst.ORDER_STARTNUM_ERROR); }// 起批量校验
        BigDecimal productPrice = BigDecimal.ZERO;
        for (DealerShopers dealerShopers : dealerShopersList)
        {
            DealerShopers currentDealerShopers = dealerShopersMapper.selectSkuPrice2DealerShopers(dealerShopers.getProductSkuId(), productType);
            if (currentDealerShopers == null) { throw new BusinessException(CommonConst.FAIL.code, "sku价格不存在"); }
            dealerShopers.setProductSkuPrice(currentDealerShopers.getProductSkuPrice());
            dealerShopers.setProductSkuName(currentDealerShopers.getProductSkuName());
            dealerShopers.setProductSkuCode(currentDealerShopers.getProductSkuCode());
            dealerShopers.setProductStock(currentDealerShopers.getProductStock());
            if (dealerShoper.getIsPoint())
            {
                productPrice = productPrice.add(dealerShopers.getProductSkuPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getPointPercent()))
                        .multiply(new BigDecimal(dealerShopers.getPurchaseNum())));// 返点成本价 = (1-返点比例)*返点价
            }
            else
            {
                productPrice = productPrice.add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum())));
            }
        }
        dealerShoper.setProductPrice(productPrice);
        dealerShoper.setDiscountPrice(dealerShoper.getProductPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getDiscount())));
        dealerShoper.setDealerShopersList(dealerShopersList);
        // 把产品按照页面显示的需要分类
        dealerShoper.setBrandsId(dealerShoper.getBrandsId() + "_" + dealerShoper.getProductType());
        return dealerShoper;
    }
    
    private void setMoreInfo2DealerShoper(DealerShoper dealerShoper, ProductBaseInfo productBaseInfo)
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerShoper.getBrandId());
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerShoper.getBrandsId());
        dealerShoper.setBrandName(brandInfo != null ? brandInfo.getComName() : "");
        dealerShoper.setProductNo(productBaseInfo.getProductNo());
        dealerShoper.setProductTitle(productBaseInfo.getProductTitle());
        dealerShoper.setProductImage(productBaseInfo.getProductImage());
        dealerShoper.setBrandsName(productBaseInfo.getBrandsName());
        dealerShoper.setPointPercent(productBaseInfo.getProductExtInfo().getPointPercent());
        dealerShoper.setIsSample(productBaseInfo.getProductExtInfo().getSample());
        dealerShoper.setIsCredit(productBaseInfo.getProductExtInfo().getCredit());
        dealerShoper.setProductStateSet(Short.valueOf(productBaseInfo.getStateSet()));
        dealerShoper.setBrandState(brandesInfo.getBrandState());
        dealerShoper.setBrandDelFlag(brandesInfo.getDelFlag());
        dealerShoper.setProductDelFlag(productBaseInfo.getDelFlag());
    }
    
    @Override
    public List<DealerShoper> getPurchaseDealerShoperList(String dealerId, String ... shopIds) throws BusinessException
    {
        if (null != dealerId && shopIds.length > 0)
        {
            List<DealerShoper> dealerShoperList = dealerShoperMapper.selectDealerShoperList(shopIds);
            if (ListUtils.isNotEmpty(dealerShoperList))
            {
                for (DealerShoper dealerShoper : dealerShoperList)
                {
                    if (null == dealerShoper)
                    {
                        continue;
                    }
                    Boolean isFristTakeSample = false;
                    ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(dealerShoper.getProductId());
                    ProductExtInfo productExtInfo = (productBaseInfo == null ? null : productBaseInfo.getProductExtInfo());
                    if (null != productExtInfo && productExtInfo.getSample() && dealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_SAM)
                    {
                        if (!dealerOrderService.isHasTakeSample(dealerId, productExtInfo.getRefrenceId()))
                        {
                            isFristTakeSample = true;
                        }
                    }
                    if (!isFristTakeSample) // 不是第一次拿样的产品都要 起批量校验
                    {
                        if (productExtInfo == null || dealerShoper.getProductNum() < productExtInfo.getStartNum()) { throw new BusinessException(
                                DealerConst.ORDER_STARTNUM_ERROR); }
                    }else{
                        if(dealerShoper.getProductNum().compareTo(1) != 0){throw new BusinessException("拿样产品有且只能有一件！");}
                    }
                    dealerShoper.setIsPoint(dealerJoinService.isSupportPoint(dealerId, dealerShoper.getProductId()));// 考虑到 validMap 中不一定 存在isPoint，改成直接查数据库
                    this.getValidMapWithTypeAndPrice(dealerId, dealerShoper);// 获取有效数据
                    // 把产品按照页面显示的需要分类
                    dealerShoper.setBrandsId(dealerShoper.getBrandsId() + "_" + dealerShoper.getProductType());
                }
            }
            return dealerShoperList;
        }
        return null;
    }
    
    @Override
    public void update(DealerShopers ds, String dealerId)
    {
        if (!StringUtils.isBlank(ds.getProductSkuId()))
        {
            DealerShoper shoper = dealerShoperMapper.selectDealerShoperBy(dealerId, ds.getProductId());
            DealerShopers dealerShopers = dealerShopersMapper.getByproductSkuIdAndDealerShopId(ds.getProductSkuId(), shoper.getRefrenceId());
            dealerShopers.setPurchaseNum(dealerShopers.getPurchaseNum() + ds.getPurchaseNum());
            dealerShopersMapper.updateByPrimaryKeySelective(dealerShopers);
            shoper.setProductNum(shoper.getProductNum() + ds.getPurchaseNum());
            shoper.setProductPrice(shoper.getProductPrice().add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(ds.getPurchaseNum()))));
            dealerShoperMapper.updateByPrimaryKeySelective(shoper);
        }
    }
    
    @Override
    public Boolean isExist(String productId, String dealerId)
    {
        DealerShoper entity = new DealerShoper();
        entity.setProductId(productId);
        entity.setDealerId(dealerId);
        List<DealerShoper> list = dealerShoperMapper.findList(entity);
        if (list != null && list.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public DealerShoper getDealerShoperBy(String dealerId, String productId)
    {
        DealerShoper entity = new DealerShoper();
        entity.setProductId(productId);
        entity.setDealerId(dealerId);
        List<DealerShoper> list = dealerShoperMapper.findList(entity);
        if (list != null && list.size() > 0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public String saveDealerShoper(String productId, Short productType, List<String> skuIds, List<Integer> purchaseNums, String dealerId) throws BusinessException
    {
        validateParams(productId, productType, skuIds, purchaseNums, dealerId);
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        if (null == productBaseInfo || null == productBaseInfo.getBrandId() || null == productBaseInfo.getBrandsId()) { throw new BusinessException(
                CommonConst.DATA_NOT_EXISTS.code, "查询异常：" + productId + "该产品不存在或品牌信息不存在"); }
        if (dealerJoinService.isSupportPoint(dealerId, productId))// 支持返点
        {
            productType = DealerConstant.DealerShoper.PRODUCTTYPE_POINT;// 强制转返点类型
        }
        else if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_POINT) { throw new BusinessException(CommonConst.FAIL.code, "该产品已经不支持返点类型，加车失败"); }
        DealerShoper oldDealerShoper = dealerShoperMapper.selectDealerShoperBy(dealerId, productId);
        DealerShoper dealerShoper = new DealerShoper();
        if (null != oldDealerShoper)
        {
            if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_SAM) { throw new BusinessException("已存在您的购物车中！"); }
            BeanUtils.copyProperties(oldDealerShoper, dealerShoper);
        }
        List<DealerShopers> dealerShopersList = Lists.newArrayList();
        // 购物车中不存在新增，存在累计进货量
        this.buildDealerShoper(dealerShoper, dealerId, productType, productBaseInfo);
        this.buildDealerShopersList(dealerShopersList, dealerShoper, skuIds, purchaseNums);
        this.countDealerShoper(dealerShoper, dealerShopersList);
        if (null == oldDealerShoper)
        {
            dealerShoperMapper.insert(dealerShoper);
        }
        else
        {
            dealerShoperMapper.updateByPrimaryKey(dealerShoper);
        }
        if (!dealerShopersList.isEmpty())
        {
            dealerShopersMapper.insertBatch(dealerShopersList);
        }
        return dealerShoper.getRefrenceId();
    }
    
    @Override
    public String saveDealerShoper(String productId, Short productType, List<DealerShopers> dealerShopersList, String dealerId) throws BusinessException
    {
        List<String> skuIds = Lists.newArrayList();
        List<Integer> purchaseNum = Lists.newArrayList();
        for (DealerShopers dealerShopers : dealerShopersList)
        {
            skuIds.add(dealerShopers.getProductSkuId());
            purchaseNum.add(dealerShopers.getPurchaseNum());
        }
        return this.saveDealerShoper(productId, productType, skuIds, purchaseNum, dealerId);
    }
    
    /**
     * 加车参数校验
     *
     * @param productId    产品id
     * @param productType  类型
     * @param skuIds       sku集合
     * @param purchaseNums 购买数量
     * @param dealerId     经销商id
     * @return true：校验通过  false ：校验失败
     */
    private void validateParams(String productId, Short productType, List<String> skuIds, List<Integer> purchaseNums, String dealerId) throws BusinessException
    {
        validateParams(productId, productType, dealerId);
        if (null == skuIds || skuIds.isEmpty()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "参数异常：skuId 空"); }
        if (null == purchaseNums || purchaseNums.isEmpty()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "参数异常：purchaseNums 空"); }
        if (!ListUtils.isSizeEqual(skuIds, purchaseNums)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "参数异常：skuId.size不等purchaseNums.size"); }
    }
    
    /**
     * 参数校验
     *
     * @param productId   产品id
     * @param productType 类型
     * @param dealerId    经销商id
     * @return true：校验通过  false ：校验失败
     * @throws BusinessException
     */
    private void validateParams(String productId, Short productType, String dealerId) throws BusinessException
    {
        if (StringUtils.isBlank(productId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "参数异常：productId 空"); }
        if (null == productType || productType < DealerConstant.DealerShoper.PRODUCTTYPE_MIN || productType > DealerConstant.DealerShoper.PRODUCTTYPE_MAX) { throw new BusinessException(
                CommonConst.PARAMS_VALID_ERR.code, "参数异常：productType 空或不在范围"); }
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "参数异常：dealerId 空"); }
    }
    
    private void countDealerShoper(DealerShoper dealerShoper, List<DealerShopers> dealerShopersList)
    {
        Map<String, Object> oldShopersSumMap = dealerShopersMapper.selectDealerShopersSumBy(dealerShoper.getRefrenceId());
        Integer newShopersPurchaseNumSum = 0;
        if (!dealerShopersList.isEmpty())
        {
            for (DealerShopers dealerShopers : dealerShopersList)
            {
                newShopersPurchaseNumSum = newShopersPurchaseNumSum + (dealerShopers.getPurchaseNum() == null ? 0 : dealerShopers.getPurchaseNum());
            }
        }
        dealerShoper.setProductNum(newShopersPurchaseNumSum + MapUtils.getIntValue(oldShopersSumMap, "purchaseNumSum", 0));
    }
    
    private void buildDealerShopersList(List<DealerShopers> dealerShopersList, DealerShoper dealerShoper, List<String> skuIds, List<Integer> purchaseNums)
            throws BusinessException
    {
        List<DealerShopers> oldDealerShopersList = dealerShopersMapper.selectDealerShopersBy(dealerShoper.getRefrenceId(), dealerShoper.getProductType());
        Map<String, Integer> skuAmountMap = Maps.newHashMap();
        if (dealerShoper.getProductType() != DealerConstant.DealerShoper.PRODUCTTYPE_SAM)
        {
            skuAmountMap = this.getSkuAmountsMap(skuIds, purchaseNums);
        }else{
            skuAmountMap.put(skuIds.get(0),1);   //拿样产品默认选择一个sku
        }
        if (null != oldDealerShopersList && !oldDealerShopersList.isEmpty())
        { // 修改累计
            for (DealerShopers dealerShopers : oldDealerShopersList)
            {
                Integer skuAmount = skuAmountMap.get(dealerShopers.getProductSkuId());
                if (null != skuAmount)
                {
                    dealerShopers.setPurchaseNum(dealerShopers.getPurchaseNum() + skuAmount);
                    skuAmountMap.remove(dealerShopers.getProductSkuId());
                }
                dealerShopers.setProductType(dealerShoper.getProductType());
                dealerShopers.setUpdateTime(CalendarUtils.getCurrentTime());
                // productSkuPrice 可以不更新，因为这个价格是实时取
                dealerShopersMapper.updateByPrimaryKeySelective(dealerShopers);
            }
        }
        if (MapUtils.isNotEmpty(skuAmountMap))
        { // 新增 补缺
            for (String skuId : skuAmountMap.keySet())
            {
                DealerShopers dealerShopers = new DealerShopers();
                dealerShopers.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerShopers.setShoperId(dealerShoper.getRefrenceId());
                dealerShopers.setProductId(dealerShoper.getProductId());
                dealerShopers.setProductType(dealerShoper.getProductType());
                dealerShopers.setProductSkuId(skuId);
                dealerShopers.setPurchaseNum(skuAmountMap.get(skuId));
                dealerShopers.setCreateTime(CalendarUtils.getCurrentTime());
                dealerShopers.setUpdateTime(dealerShopers.getCreateTime());
                dealerShopers.setDelFlag(false);
                dealerShopersList.add(dealerShopers);
            }
        }
    }
    
    private void buildDealerShoper(DealerShoper dealerShoper, String dealerId, Short productType, ProductBaseInfo productBaseInfo)
    {
        dealerShoper.setProductType(productType);
        dealerShoper.setUpdateTime(CalendarUtils.getCurrentTime());
        if (null != dealerShoper.getRefrenceId()) { return; }
        String productId = productBaseInfo.getRefrenceId();
        dealerShoper.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerShoper.setDealerId(dealerId);
        dealerShoper.setProductId(productId);
        dealerShoper.setProductType(productType);
        dealerShoper.setBrandId(productBaseInfo.getBrandId());
        dealerShoper.setBrandsId(productBaseInfo.getBrandsId());
        dealerShoper.setSourceId(CommonConstant.sourceType.ZTTX);
        dealerShoper.setDiscountPrice(BigDecimal.ZERO);
        dealerShoper.setProductPrice(BigDecimal.ZERO);
        dealerShoper.setProductNum(0);
        dealerShoper.setCreateTime(CalendarUtils.getCurrentTime());
        dealerShoper.setDelFlag(false);
    }
    
    @Override
    public List<DealerShopers> getMoreDealerShopers(String productId, Short productType, String dealerId, String shopId) throws BusinessException
    {
        validateParams(productId, productType, dealerId);
        if (StringUtils.isBlank(shopId)) { throw new BusinessException(CommonConst.PARAM_NULL.code, "参数异常：shopId 空"); }
        List<ProductSku> productSkuList = productSkuService.findByProductId(productId, false);
        List<DealerShopers> oldDealerShopersList = dealerShopersMapper.selectDealerShopersBy(shopId, productType);
        this.removeSameSku(productSkuList, oldDealerShopersList);
        oldDealerShopersList.clear();
        if (ListUtils.isNotEmpty(productSkuList))
        {
            for (ProductSku productSku : productSkuList)
            {
                DealerShopers dealerShopers = new DealerShopers();
                dealerShopers.setProductSkuName(productSku.getProductSkuName());
                dealerShopers.setRefrenceId(null);
                dealerShopers.setShoperId(shopId);
                dealerShopers.setProductId(productSku.getProductId());
                dealerShopers.setProductSkuId(productSku.getRefrenceId());
                dealerShopers.setPurchaseNum(0);
                dealerShopers.setProductStock(productSku.getQuantity());
                dealerShopers.setProductSkuPrice(productType == DealerConstant.DealerShoper.PRODUCTTYPE_POINT ? productSku.getProductSkuPrice().getPointPrice()
                        : productType == DealerConstant.DealerShoper.PRODUCTTYPE_CASH ? productSku.getProductSkuPrice().getDirectPrice()
                                : productType == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT ? productSku.getProductSkuPrice().getCreditPrice() : productSku
                                        .getProductSkuPrice().getSamplePrice());
                dealerShopers.setProductType(productType);
                oldDealerShopersList.add(dealerShopers);
            }
        }
        return oldDealerShopersList;
    }
    
    @Override
    public Map<String, Map<String, Object>> synShopers(List<JSONObject> dataList, String dealerId) throws BusinessException
    {
        if (!ListUtils.isNotEmpty(dataList)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        List<String> skuIds = getSkuIdList(dataList);
        List<ProductSku> productSkus = productSkuInfoDubboConsumer.findByProductSkuIds(skuIds);
        Set<String> productIds = getProductIdSet(productSkus);
        Map<String, Map<String, Object>> productStateMap = getProductStateMap(productIds, dealerId);// 根据产品id ，经销商 id 获取产品状态
        Map<String, ProductSku> productSkuMap = list2ProductSkuMap(productSkus);
        synShopers(productSkuMap, dataList, productStateMap);
        return productStateMap;
    }
    
    @Override
    public Boolean validateDealerShopProduct(String dealerId, String productId)
    {
        DealerShoper dealerShoper = this.dealerShoperMapper.selectDealerShoperBy(dealerId, productId);
        return !(dealerShoper.getBrandDelFlag() || dealerShoper.getProductDelFlag() || dealerShoper.getBrandState() != 2);
    }
    
    /**
     * 根据产品id ，经销商 id 获取产品状态
     *
     * @param productIds
     * @param dealerId
     * @return Map {key:productId  value：Map {key:isPoint ; key:isValid ; key:changedSkuList}}
     * @throws BusinessException
     */
    private Map<String, Map<String, Object>> getProductStateMap(Set<String> productIds, String dealerId) throws BusinessException
    {
        Map<String, Map<String, Object>> productStateMap = Maps.newHashMap();
        if (!productIds.isEmpty())
        {
            for (String pId : productIds)
            {
                Map<String, Object> productState = Maps.newHashMap();
                ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(pId);
                DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerId, productBaseInfo.getBrandsId());
                if (dealerJoinService.isSupportPoint(dealerJoin, productBaseInfo))
                {
                    productState.put("isPoint", Boolean.TRUE);
                    productState.put("pointPercent", productBaseInfo.getProductExtInfo().getPointPercent());
                }
                else
                {
                    productState.put("isPoint", Boolean.FALSE);
                    productState.put("pointPercent", "0");
                }
                productState.put("isValid", validateDealerShopProduct(dealerId, pId));
                productState.put("changedSkuList", Lists.newArrayList());
                productStateMap.put(pId, productState);
            }
        }
        return productStateMap;
    }
    
    /**
     * 对比数据,将有变动的结果放到 productState 中的changedSkuList
     *
     * @param productSku
     * @param productType
     * @param productSkuPrice
     * @param quantity
     * @param pointPercent
     * @param productStateMap
     */
    private void compareWith(ProductSku productSku, Short productType, BigDecimal productSkuPrice, Integer quantity, BigDecimal pointPercent,
            Map<String, Map<String, Object>> productStateMap)
    {
        if (null != productSku)
        {
            String productId = productSku.getProductId();
            Map<String, Object> productState = productStateMap.get(productId);
            if (MapUtils.isNotEmpty(productState) && MapUtils.getBoolean(productState, "isValid", Boolean.FALSE))
            {
                boolean isChange = false;
                Map<String, Object> changedMap = Maps.newHashMap();
                List changedSkuList = (List) productState.get("changedSkuList");
                Boolean isPoint = MapUtils.getBoolean(productState, "isPoint", Boolean.FALSE);
                BigDecimal nowPointPercent = new BigDecimal(MapUtils.getString(productState, "pointPercent"));
                if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_CASH)
                {
                    productState.put("typeChanged", isPoint);// 购物车是现款，当前支持反点； 类型改变，建议刷新购物车
                    isChange = isChange(productSku.getProductSkuPrice().getDirectPrice(), productSkuPrice, changedMap, isPoint);
                }
                else if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT)
                {
                    productState.put("typeChanged", isPoint);
                    isChange = isChange(productSku.getProductSkuPrice().getCreditPrice(), productSkuPrice, changedMap, isPoint);
                }
                else if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_SAM)
                {
                    productState.put("typeChanged", isPoint);
                    isChange = isChange(productSku.getProductSkuPrice().getSamplePrice(), productSkuPrice, changedMap, isPoint);
                }
                else if (productType == DealerConstant.DealerShoper.PRODUCTTYPE_POINT)
                {
                    productState.put("typeChanged", !isPoint);
                    isChange = isChange(productSku.getProductSkuPrice().getPointPrice(), productSkuPrice, nowPointPercent, pointPercent, changedMap);
                }
                boolean quantityChanged = isChange(productSku.getQuantity(), quantity, changedMap);
                if (isChange || quantityChanged)
                {
                    changedSkuList.add(changedMap);
                }
            }
        }
    }
    
    private boolean isChange(BigDecimal pointPrice, BigDecimal productSkuPrice, BigDecimal nowPointPercent, BigDecimal pointPercent, Map<String, Object> changedMap)
    {
        if (pointPrice.compareTo(productSkuPrice) != 0 || nowPointPercent.compareTo(pointPercent) != 0)
        {
            if (pointPrice.compareTo(productSkuPrice) != 0)
            {
                changedMap.put("productSkuPrice", pointPrice);
            }
            else
            {
                changedMap.put("pointPercent", nowPointPercent);
            }
            return true;
        }
        return false;
    }
    
    private boolean isChange(BigDecimal productSkuPrice0, BigDecimal productSkuPrice1, Map<String, Object> changedMap, Boolean isPoint)
    {
        if (productSkuPrice0.compareTo(productSkuPrice1) != 0)
        {
            changedMap.put("productSkuPrice", productSkuPrice0);
            return true;
        }
        return false;
    }
    
    private boolean isChange(Integer quantity, Integer quantity1, Map<String, Object> changedMap)
    {
        if (quantity - quantity1 < 0)
        {
            changedMap.put("quantity", quantity);
            return true;
        }
        return false;
    }
    
    private Set<String> getProductIdSet(List<ProductSku> productSkus)
    {
        Set<String> productIds = Sets.newHashSet();
        if (ListUtils.isNotEmpty(productSkus))
        {
            for (ProductSku productSku : productSkus)
            {
                productIds.add(productSku.getProductId());
            }
        }
        return productIds;
    }
    
    /**
     * 同步sku的私有方法
     *
     * @param productSkuMap   当前数据库sku数据
     * @param dataList        购物车中的sku列表（需要同步的）
     * @param productStateMap 产品状态map
     * @return List 包括 （无效的+有变动）
     * Map{
     * skuId：sku的Id
     * productSkuPrice：拿样价/现款价/授信价/返点价
     * quantity：库存
     * }
     */
    private void synShopers(Map<String, ProductSku> productSkuMap, List<JSONObject> dataList, Map<String, Map<String, Object>> productStateMap)
    {
        for (JSONObject data : dataList)
        {
            if (null == data || null == data.get("skuId"))
            {
                continue;
            }
            String skuId = data.get("skuId").toString();
            Short productType = Short.valueOf(data.get("productType").toString());
            BigDecimal productSkuPrice = new BigDecimal(data.get("productSkuPrice").toString());
            Integer quantity = data.getInteger("quantity");
            BigDecimal pointPercent = new BigDecimal(data.get("pointPercent").toString());
            ProductSku productSku = productSkuMap.get(skuId);
            compareWith(productSku, productType, productSkuPrice, quantity, pointPercent, productStateMap);
        }
    }
    
    private Map<String, ProductSku> list2ProductSkuMap(List<ProductSku> productSkus)
    {
        Map<String, ProductSku> resultMap = Maps.newHashMap();
        if (ListUtils.isNotEmpty(productSkus))
        {
            for (ProductSku productSku : productSkus)
            {
                if (null != productSku)
                {
                    resultMap.put(productSku.getRefrenceId(), productSku);
                }
            }
        }
        return resultMap;
    }
    
    private List<String> getSkuIdList(List<JSONObject> dataList)
    {
        List<String> skuIdList = Lists.newArrayList();
        for (JSONObject data : dataList)
        {
            if (null == data || null == data.get("skuId"))
            {
                continue;
            }
            skuIdList.add(data.get("skuId").toString());
        }
        return skuIdList;
    }
    
    private void removeSameSku(List<ProductSku> productSkuList, List<DealerShopers> oldDealerShopersList) throws BusinessException
    {
        if (!ListUtils.isNotEmpty(oldDealerShopersList) || !ListUtils.isNotEmpty(productSkuList) || productSkuList.size() < oldDealerShopersList.size()) { return; }
        Map<String, Object> oldDealerShopersMap = Maps.newConcurrentMap();
        for (DealerShopers dealerShopers : oldDealerShopersList)
        {
            oldDealerShopersMap.put(dealerShopers.getProductSkuId(), true);
        }
        Iterator iterator = productSkuList.iterator();
        while (iterator.hasNext())
        {
            ProductSku productSku = (ProductSku) iterator.next();
            if (MapUtils.getBoolean(oldDealerShopersMap, productSku.getRefrenceId(), false))
            {
                iterator.remove();
            }
        }
    }
}
