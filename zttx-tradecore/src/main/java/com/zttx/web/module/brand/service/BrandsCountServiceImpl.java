/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandsCount;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.brand.mapper.BrandsCountMapper;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerCollectMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.search.solrj.BrandeSolrHandler;

/**
 * 品牌计数信息 服务实现类
 * <p>File：BrandsCount.java </p>
 * <p>Title: BrandsCount </p>
 * <p>Description:BrandsCount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandsCountServiceImpl extends GenericServiceApiImpl<BrandsCount> implements BrandsCountService
{
    private BrandsCountMapper        brandsCountMapper;
    
    @Autowired
    private BrandesInfoMapper        brandesInfoMapper;
    
    @Autowired
    private DealerJoinMapper         dealerJoinMapper;
    
    @Autowired
    private BrandInviteMapper        brandInviteMapper;
    
    @Autowired
    private DealerOrderMapper        dealerOrderMapper;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private DealerCollectMapper      dealerCollectMapper;
    
    @Autowired
    private BrandeSolrHandler        brandeSolrHandler;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired(required = true)
    public BrandsCountServiceImpl(BrandsCountMapper brandsCountMapper)
    {
        super(brandsCountMapper);
        this.brandsCountMapper = brandsCountMapper;
    }
    
    /**
     * 修改BrandsCount 统计数
     * @author 陈建平
     * @param brandsId
     * @param countTypeName
     * @param isUpdateSolr true:修改BrandsCount并修改品牌索引
     * @return
     * @throws BusinessException
     */
    public BrandsCount modifyBrandsCount(String brandsId, String[] countTypeName, boolean isUpdateSolr) throws BusinessException
    {
        BrandsCount brandsCount = null;
        if (isUpdateSolr)
        {
            brandsCount = modifyBrandsCount(brandsId, countTypeName);
            BrandesInfo brandesInfo = new BrandesInfo();
            brandesInfo.setRefrenceId(brandsId);
            brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
        }
        else
        {
            brandsCount = modifyBrandsCount(brandsId, countTypeName);
        }
        return brandsCount;
    }
    
    public BrandsCount modifyBrandsCount(String brandsId, String[] countTypeName) throws BusinessException
    {
        if (StringUtils.isBlank(brandsId)) throw new BusinessException("品牌编号不可为空！");
        BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandsId);
        if (null == brandesInfo) throw new BusinessException("品牌信息未找到！");
        BrandsCount brandsCount = brandsCountMapper.findByBrandIdAndBrandesId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
        Long count;
        Boolean isExist = true;
        if (null == brandsCount)
        {
            isExist = false;
            brandsCount = new BrandsCount();
            brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandsCount.setBrandId(brandesInfo.getBrandId());
            brandsCount.setBrandsId(brandesInfo.getRefrenceId());
            brandsCount.setCreateTime(CalendarUtils.getCurrentTime());
            brandsCount.setUpdateTime(CalendarUtils.getCurrentTime());
        }
        DealerJoin filter = new DealerJoin();
        filter.setBrandId(brandesInfo.getBrandId());
        filter.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
        { // 合作中的经销商
            count = dealerJoinMapper.getDealerJoinCount(filter);
            brandsCount.setJoinCount(null != count ? count.intValue() : 0);
        }
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setBrandId(brandesInfo.getBrandId());
        brandInvite.setBrandsId(brandesInfo.getRefrenceId());
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT))
        { // 申请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setApplyCount(null != count ? count.intValue() : 0);
        }
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT))
        { // 邀请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setInviteCount(null != count ? count.intValue() : 0);
        }
        DealerOrderModel dealerOrder = new DealerOrderModel();
        dealerOrder.setBrandId(brandesInfo.getBrandId());
        dealerOrder.setBrandsId(brandesInfo.getRefrenceId());
        dealerOrder.setOrderCountType(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_ORDERCOUNT))
        { // 发货订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandsCount.setOrderNum(null != count ? count.intValue() : 0);
        }
        ProductBaseInfo productInfo = new ProductBaseInfo();
        productInfo.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName)
                || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT))
        { // 产品数量
            count = productInfoDubboConsumer.getProductBaseInfoCount(productInfo);
            brandsCount.setProductCount(null != count ? count.intValue() : 0);
        }
        DealerCollect dealerCollect = new DealerCollect();
        dealerCollect.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM))
        { // 收藏数量
            count = dealerCollectMapper.getDealerCollectCount(dealerCollect);
            brandsCount.setFavNum(null != count ? count.intValue() : 0);
        }
        if (isExist)
        {
            brandsCount.setUpdateTime(CalendarUtils.getCurrentTime());
            brandsCountMapper.updateByPrimaryKey(brandsCount);
            if (ArrayUtils.isEmpty(countTypeName)
                    || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
            {
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
        }
        else
        {
            brandsCountMapper.insert(brandsCount);
            if (ArrayUtils.isEmpty(countTypeName)
                    || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
            {
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
        }
        return brandsCount;
    }
    
    @Override
    public BrandsCount findByBrandIdAndBrandsId(String brandId, String brandesId)
    {
        return brandsCountMapper.findByBrandIdAndBrandesId(brandId, brandesId);
    }
    
    @Override
    public List<String> getBrandsCountUpdatedIn(long time)
    {
        long now = CalendarUtils.getCurrentLong();
        return brandsCountMapper.getBrandsCountUpdatedBetween(now - time, now);
    }
}
