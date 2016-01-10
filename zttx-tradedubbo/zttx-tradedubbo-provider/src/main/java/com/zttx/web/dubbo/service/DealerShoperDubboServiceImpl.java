/*
 * @(#)DealerShopersDubboServiceImpl.java 2015-9-2 上午10:13:58
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.entity.DealerShopers;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerShoperService;
import com.zttx.web.module.dealer.service.DealerShopersService;

/**
 * <p>File：DealerShopersDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:13:58</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class DealerShoperDubboServiceImpl implements DealerShoperDubboService
{
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @Autowired
    private DealerShoperService         dealerShoperService;
    
    @Autowired
    private DealerShopersService        dealerShopersService;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Override
    public JsonMessage addProductIntoShopper(String dealerId, List<DealerShopers> shopers)
    {
        // 异常配置
        List<Map> errorlist = new ArrayList<Map>();
        Map<String, Object> _map = new HashMap<String, Object>();
        String productId = null;
        String productSkuId = null;
        String productSkuName = "";
        Map<String, Map<String, Object>> resultMap = Maps.newHashMap();
        try
        {
            for (DealerShopers dealerShopers : shopers)
            {
                productId = dealerShopers.getProductId();
                productSkuId = dealerShopers.getProductSkuId();
                if (StringUtils.isEmpty(dealerId) || StringUtils.isEmpty(productId) || StringUtils.isEmpty(productSkuId)) { throw new BusinessException(
                        CommonConst.SHOPPER_PARAMS_ERROR); }
                if (!dealerInfoService.isAuthorized(dealerId)) { throw new BusinessException(DealerConst.NOT_PAY_FOR_SERVICE); }
                ProductSku productSku = productSkuInfoDubboConsumer.findProductSku(productSkuId);
                if (productSku == null || productSku.getDelFlag()) { throw new BusinessException(DealerConst.PRODUCTSKU_NOT_EXIST); }
                productSkuName = productSku.getProductSkuName();
                dealerShopers = this.addProductTypeAndPrice(dealerId, dealerShopers);
                if (null == resultMap.get(productId))
                {
                    Map<String, Object> tempMap = Maps.newHashMap();
                    DealerShoper dealerShoper = dealerShoperService.getDealerShoperBy(dealerId, productId);
                    List<DealerShopers> tempList = Lists.newArrayList();
                    if (null == dealerShoper)
                    {
                        tempMap.put("productType", dealerShopers.getProductType());
                        tempMap.put("isExist", false);
                    }
                    else
                    {
                        // 订单类型不能为拿样
                        if (dealerShoper.getProductType().equals(DealerConstant.DealerShoper.PRODUCTTYPE_SAM)) { throw new BusinessException(DealerConst.ORDER_STATE_ERROR); }
                        tempMap.put("productType", dealerShoper.getProductType());
                        tempMap.put("dealerShoper", dealerShoper);
                        tempMap.put("isExist", true);
                    }
                    tempList.add(dealerShopers);
                    tempMap.put("dealerShopersList", tempList);
                    resultMap.put(productId, tempMap);
                }
                else
                {
                    Map<String, Object> resultTempMap = resultMap.get(productId);
                    List<DealerShopers> tempDealerShopersList = (List<DealerShopers>) MapUtils.getObject(resultTempMap, "dealerShopersList");
                    tempDealerShopersList.add(dealerShopers);
                }
            }
            this.updateDealerShoper(dealerId, resultMap);
        }
        catch (Exception e)
        {
            _map.put("productSkuId", productSkuId);
            _map.put("productSkuName", productSkuName);
            _map.put("msg", e.getMessage());
            errorlist.add(_map);
        }
        if (errorlist.size() == 0)
        {
            JsonMessage jsonMessage = new JsonMessage();
            jsonMessage.setCode(CommonConst.SUCCESS.getCode());
            jsonMessage.setMessage(CommonConst.SUCCESS.getMessage());
            return jsonMessage;
        }
        else
        {
            JsonMessage jsonMessage = new JsonMessage();
            jsonMessage.setCode(CommonConst.FAIL.getCode());
            jsonMessage.setMessage(CommonConst.FAIL.getMessage());
            jsonMessage.setRows(errorlist);
            return jsonMessage;
        }
    }
    
    private DealerShopers addProductTypeAndPrice(String dealerId, DealerShopers dealerShopers) throws BusinessException
    {
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(dealerShopers.getProductId());
        ProductSku productSku = productSkuInfoDubboConsumer.findProductSku(dealerShopers.getProductSkuId());
        if (null == productBaseInfo) { throw new BusinessException("产品失效:" + dealerShopers.getProductId()); }
        DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerId, productBaseInfo.getBrandsId());
        if (dealerJoin == null) { throw new BusinessException("没有加盟关系(品牌id:" + productBaseInfo.getBrandsId() + ")"); }
        if (dealerJoin.getJoinForm().equals(DealerConstant.DealerJoin.JOINFORM_CREDIT) && dealerJoin.getIsPaid()) // 现款加盟
        {
            dealerShopers.setProductType(DealerConstant.DealerShoper.PRODUCTTYPE_CASH);
            dealerShopers.setProductSkuPrice(productSku.getProductSkuPrice().getCreditPrice());
        }
        else
        {
            dealerShopers.setProductType(DealerConstant.DealerShoper.PRODUCTTYPE_CASH);
            dealerShopers.setProductSkuPrice(productSku.getProductSkuPrice().getDirectPrice());
        }
        return dealerShopers;
    }
    
    private void updateDealerShoper(String dealerId, Map<String, Map<String, Object>> resultMap) throws BusinessException
    {
        /**
         *   购物单分类后的加车动作
         *
         *   购物车不存在的 重新加成
         *
         *   购物车存在   购买数量叠加
         *
         */
        for (Map.Entry<String, Map<String, Object>> mapEntry : resultMap.entrySet())
        {
            String productId = mapEntry.getKey();
            Map<String, Object> map = mapEntry.getValue();
            Boolean isExist = MapUtils.getBoolean(map, "isExist", false);
            List<DealerShopers> dealerShopersList = (List<DealerShopers>) MapUtils.getObject(map, "dealerShopersList");
            if (!isExist) // 不存在订单新增订单
            {
                Short productType = MapUtils.getShort(map, "productType");
                List<String> skuIds = new ArrayList<>();
                List<Integer> amountList = new ArrayList<>();
                for (DealerShopers dealerShopers : dealerShopersList)
                {
                    skuIds.add(dealerShopers.getProductSkuId());
                    amountList.add(dealerShopers.getPurchaseNum());
                }
                dealerShoperService.saveShopers(productId, productType, dealerId, skuIds, amountList);
            }
            else
            {
                DealerShoper dealerShoper = (DealerShoper) MapUtils.getObject(map, "dealerShoper");
                for (DealerShopers dealerShopers : dealerShopersList)
                {
                    DealerShopers oldDealerShopers = dealerShopersService.selectDealerShopersBy(dealerShoper.getRefrenceId(), dealerShopers.getProductSkuId());
                    if (oldDealerShopers != null)
                    {
                        oldDealerShopers.setPurchaseNum(oldDealerShopers.getPurchaseNum() + dealerShopers.getPurchaseNum());
                        dealerShopersService.updateByPrimaryKeySelective(oldDealerShopers);
                        dealerShoper.setProductNum(dealerShoper.getProductNum() + dealerShopers.getPurchaseNum());
                        dealerShoper.setProductPrice(oldDealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum()))
                                .add(dealerShoper.getProductPrice()));
                    }
                    else
                    {
                        throw new BusinessException("dealerShopers 中不存在该sku");
                    }
                }
                dealerShoperService.updateByPrimaryKeySelective(dealerShoper);
            }
        }
    }
}
