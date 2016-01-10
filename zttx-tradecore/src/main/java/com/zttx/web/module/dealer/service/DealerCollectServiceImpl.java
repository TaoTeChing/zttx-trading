/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductLine;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.mapper.*;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandsCountService;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.mapper.DealerCollectMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.model.DealerCollectModel;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.GlobalStaticTextUtils;

/**
 * 经销商品牌收藏夹 服务实现类
 * <p>File：DealerCollect.java </p>
 * <p>Title: DealerCollect </p>
 * <p>Description:DealerCollect </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerCollectServiceImpl extends GenericServiceApiImpl<DealerCollect> implements DealerCollectService
{
    private DealerCollectMapper      dealerCollectMapper;
    
    @Autowired
    private BrandesInfoMapper        brandesInfoMapper;
    
    @Autowired
    private BrandInfoMapper          brandInfoMapper;
    
    @Autowired
    private BrandsCountMapper        brandsCountMapper;
    
    @Autowired
    private DealerJoinMapper         dealerJoinMapper;
    
    @Autowired
    private BrandInviteMapper        brandInviteMapper;
    
    @Autowired
    private DealerOrderMapper        dealerOrderMapper;
    
    @Autowired
    private BrandInfoService         brandInfoService;
    
    @Autowired
    private BrandRecruitMapper       brandRecruitMapper;
    
    @Autowired
    private BrandsCountService       brandsCountService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired(required = true)
    public DealerCollectServiceImpl(DealerCollectMapper dealerCollectMapper)
    {
        super(dealerCollectMapper);
        this.dealerCollectMapper = dealerCollectMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getDealerCollectsBy(Pagination pagination, DealerCollectModel dealerCollectModel)
    {
        dealerCollectModel.setPage(pagination);
        List<Map<String, Object>> mapList = dealerCollectMapper.getDealerCollectsList(dealerCollectModel);
        if (null != mapList && !mapList.isEmpty())
        {
            List<BrandInvite> inviteList = null;
            for (Map<String, Object> item : mapList)
            {
                item.put("emploeeNumName", brandInfoService.getParamAryName(0, Integer.parseInt(item.get("emploeeNum").toString())));
                item.put("moneyNumName", brandInfoService.getParamAryName(1, Integer.parseInt(item.get("moneyNum").toString())));
                String brandsId = item.get("brandsId").toString();
                String domain = GlobalStaticTextUtils.getBrandsIdSubDomain(brandsId);
                item.put("domain", domain);
                inviteList = brandInviteMapper.getBrandInviteList(dealerCollectModel.getDealerId(), brandsId, null);
                if (null != inviteList && !inviteList.isEmpty())
                {
                    item.put("applyState", inviteList.get(0).getApplyState());
                }
                String brandesId = item.get("brandsId").toString();
                String brandId = item.get("brandId").toString();
                BrandRecruit _brandRecruit = brandRecruitMapper.findByBrandIdAndBrandesid(brandId, brandesId);
                if (_brandRecruit == null || _brandRecruit.getRecruitState() == null || _brandRecruit.getRecruitState().intValue() == 0)
                {
                    item.put("brandRecruit", false);
                }
                else
                {
                    item.put("brandRecruit", true);
                }
            }
        }
        PaginateResult<Map<String, Object>> mapPaginateResult = new PaginateResult<>(pagination, mapList);
        return mapPaginateResult;
    }
    
    @Override
    public Long findDealerCollect(String dealerId, String brandId, Boolean delState)
    {
        DealerCollect collect = new DealerCollect();
        collect.setDealerId(dealerId);
        collect.setBrandId(brandId);
        collect.setDelFlag(delState);
        return dealerCollectMapper.getDealerCollectCount(collect);
    }
    
    @Override
    public void saveCollect(String dealerId, String brandesId) throws BusinessException
    {
        DealerCollect old = this.dealerCollectMapper.findDealerCollect(dealerId, brandesId, BrandConstant.DEL_STATE_NONE_DELETED);
        if (null != old) { throw new BusinessException(CommonConst.DATA_EXISTS); }
        BrandesInfo brandesInfo = this.brandesInfoMapper.selectByPrimaryKey(brandesId);
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BrandInfo brandInfo = this.brandInfoMapper.selectByPrimaryKey(brandesInfo.getBrandId());
        if (null == brandInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        DealerCollect dealerCollect = new DealerCollect();
        dealerCollect.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerCollect.setDealerId(dealerId);
        dealerCollect.setBrandId(brandInfo.getRefrenceId());
        dealerCollect.setBrandsId(brandesInfo.getRefrenceId());
        dealerCollect.setAreaNo(brandInfo.getAreaNo());
        dealerCollect.setAreaName(brandInfo.getAreaName());
        dealerCollect.setCollectTime(CalendarUtils.getCurrentLong());
        dealerCollect.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        this.dealerCollectMapper.insert(dealerCollect);
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM);
        BrandsCount temp = modifyBrandsCountCache(brandesId, countTypeNameList.toArray(new String[]{}));
        if (null == temp) { throw new BusinessException(DealerConst.BRANDS_FAVORITE_ERROR); }
    }
    
    /**
     * @param brandsId
     * @param countTypeName
     * @return
     */
    private BrandsCount modifyBrandsCountCache(String brandsId, String[] countTypeName)
    {
        if (StringUtils.isBlank(brandsId)) { return null; }
        BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandsId);
        if (null == brandesInfo) { return null; }
        BrandsCount brandsCount = brandsCountMapper.findByBrandIdAndBrandesId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
        Boolean isExist = true;
        if (null == brandsCount)
        {
            isExist = false;
            return null;
        }
        DealerJoin filter = new DealerJoin();
        filter.setBrandId(brandesInfo.getBrandId());
        filter.setBrandsId(brandesInfo.getRefrenceId());
        Long count = 0L;
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
        {
            // 合作中的经销商
            count = dealerJoinMapper.getDealerJoinCount(filter);
            brandsCount.setJoinCount(count.intValue());
        }
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setBrandId(brandesInfo.getBrandId());
        brandInvite.setBrandsId(brandesInfo.getRefrenceId());
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT))
        {
            // 申请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setApplyCount(count.intValue());
        }
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT))
        {
            // 邀请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setInviteCount(count.intValue());
        }
        DealerOrder dealerOrder = new DealerOrder();
        dealerOrder.setBrandId(brandesInfo.getBrandId());
        dealerOrder.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_ORDERCOUNT))
        {
            DealerOrderModel model = new DealerOrderModel();
            model.setBrandId(dealerOrder.getBrandId());
            model.setBrandsId(dealerOrder.getBrandsId());
            model.setOrderCountType(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
            // 发货订单数量
            count = dealerOrderMapper.getDealerOrderCount(model);
            brandsCount.setOrderNum(count.intValue());
        }
        ProductLine productLine = new ProductLine();
        productLine.setBrandId(brandesInfo.getBrandId());
        productLine.setBrandsId(brandesInfo.getRefrenceId());
        ProductBaseInfo productInfo = new ProductBaseInfo();
        productInfo.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName)
                || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT))
        {
            // 产品数量
            count = productInfoDubboConsumer.getProductBaseInfoCount(productInfo);
            brandsCount.setProductCount(count.intValue());
        }
        DealerCollect dealerCollect = new DealerCollect();
        dealerCollect.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM))
        {
            // 收藏数量
            count = dealerCollectMapper.getDealerCollectCount(dealerCollect);
            brandsCount.setFavNum(count.intValue());
        }
        if (isExist)
        {
            brandsCount.setUpdateTime(CalendarUtils.getCurrentLong());
            brandsCountMapper.updateByPrimaryKey(brandsCount);
            return brandsCount;
        }
        else
        {
            brandsCountMapper.insert(brandsCount);
            return brandsCount;
        }
    }
    
    /**
     * 批量取消收藏
     *
     * @param refrenceIds
     * @param dealerId
     * @throws BusinessException
     */
    @Override
    public void executeUnCollect(String[] refrenceIds, String dealerId) throws BusinessException
    {
        if (ArrayUtils.isEmpty(refrenceIds) || StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        for (String refrenceId : refrenceIds)
        {
            DealerCollect filter = new DealerCollect();
            filter.setRefrenceId(refrenceId);
            filter.setDealerId(dealerId);
            filter.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            try
            {
                DealerCollect dealerCollect = this.dealerCollectMapper.findDealerCollectByFilter(filter);
                this.executeUnCollect(dealerCollect);
            }
            catch (Throwable ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * 取消收藏
     *
     * @param dealerCollect
     * @throws BusinessException
     */
    @Override
    public void executeUnCollect(DealerCollect dealerCollect) throws BusinessException
    {
        if (null == dealerCollect) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        dealerCollect.setDelFlag(BrandConstant.DEL_STATE_DELETED);
        this.dealerCollectMapper.updateByPrimaryKey(dealerCollect);
        // 品牌计数器
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM);
        brandsCountService.modifyBrandsCount(dealerCollect.getBrandsId(), countTypeNameList.toArray(new String[]{}));
    }
}
