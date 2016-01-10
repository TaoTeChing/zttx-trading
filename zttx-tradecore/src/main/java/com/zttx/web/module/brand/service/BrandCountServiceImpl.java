/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.ProductConsts;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.mapper.*;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.mapper.DealerRefundMapper;
import com.zttx.web.module.dealer.model.DealerOrderModel;

/**
 * 品牌商计数信息 服务实现类
 * <p>File：BrandCount.java </p>
 * <p>Title: BrandCount </p>
 * <p>Description:BrandCount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCountServiceImpl extends GenericServiceApiImpl<BrandCount> implements BrandCountService {
    private BrandCountMapper brandCountMapper;

    @Autowired
    private BrandInfoMapper brandInfoMapper;

    @Autowired
    private DealerJoinMapper dealerJoinMapper;

    @Autowired
    private BrandInviteMapper brandInviteMapper;

    @Autowired
    private DealerOrderMapper dealerOrderMapper;

    @Autowired
    private DealerRefundMapper dealerRefundMapper;

    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;

    @Autowired
    private BrandViewContactMapper brandViewContactMapper;

    @Autowired
    private BrandesInfoMapper brandesInfoMapper;

    @Autowired(required = true)
    public BrandCountServiceImpl(BrandCountMapper brandCountMapper) {
        super(brandCountMapper);
        this.brandCountMapper = brandCountMapper;
    }

    @Override
    public BrandCount modifyBrandCount(String brandId, Integer[] countType) throws BusinessException {
        if (StringUtils.isBlank(brandId)) throw new BusinessException("品牌商编号不可为空！");
        BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(brandId);
        if (null == brandInfo) throw new BusinessException("品牌商信息未找到！");
        BrandCount brandCount = brandCountMapper.selectByPrimaryKey(brandInfo.getRefrenceId());
        Long count;
        Boolean isExist = true;
        if (null == brandCount) {
            isExist = false;
            brandCount = new BrandCount();
            brandCount.setRefrenceId(brandInfo.getRefrenceId());
            brandCount.setCreateTime(CalendarUtils.getCurrentTime());
            brandCount.setUpdateTime(CalendarUtils.getCurrentTime());
        }
        DealerJoin filter = new DealerJoin();
        filter.setBrandId(brandInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT)) {// 合作中的经销商
            count = dealerJoinMapper.getDealerJoinCount(filter);
            brandCount.setCooperCount(null != count ? count.intValue() : 0);
        }
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setBrandId(brandInfo.getRefrenceId());
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT)) { // 申请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandCount.setApplyCount(null != count ? count.intValue() : 0);
        }
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT)) { // 邀请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandCount.setInviteCount(null != count ? count.intValue() : 0);
        }
        DealerOrderModel dealerOrder = new DealerOrderModel();
        dealerOrder.setBrandId(brandInfo.getRefrenceId());
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT)) { // 等待付款订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandCount.setWaitPayCount(null != count ? count.intValue() : 0);
        }
        dealerOrder.setOrderStatus(null);
        dealerOrder.setIsAdvancePayment(true);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_PREORDERCOUNT)) { // 预订产品订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandCount.setPreOrderCount(null != count ? count.intValue() : 0);
        }
        dealerOrder.setIsAdvancePayment(null);
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT)) { // 待发货订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandCount.setWaitSendCount(null != count ? count.intValue() : 0);
        }
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT)) { // 已发货订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandCount.setWaitConfirmCount(null != count ? count.intValue() : 0);
        }
        DealerRefund dealerRefund = new DealerRefund();
        dealerRefund.setBrandId(brandInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT)) { // 退款订单数量
            count = dealerRefundMapper.getDealerRefundCount(dealerRefund);
            brandCount.setRefundCount(null != count ? count.intValue() : 0);
        }
        ProductBaseInfo productInfo = new ProductBaseInfo();
        productInfo.setProductExtInfo(new ProductExtInfo());
        productInfo.setBrandId(brandInfo.getRefrenceId());
        // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT)
                || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_WAITPUBLISHCOUNT)) {
            List<Map<String, Object>> list = productInfoDubboConsumer.getProductBaseInfoCountList(productInfo);
            Long[] countAry = {0L, 0L, 0L};
            if (CollectionUtils.isNotEmpty(list)) {
                for (Map<String, Object> item : list) {
                    Long proCount = Long.parseLong(item.get("count").toString());
                    Integer beginType = Integer.parseInt(item.get("stateSet").toString());
                    countAry[beginType - 1] = proCount;
                }
            }
            // 已铺货产品数量
            brandCount.setPublishedCount(countAry[0].intValue());
            // 未铺货产品数量
            brandCount.setWaitPublishCount(countAry[1].intValue() + countAry[2].intValue());
        }
        Integer storeWarnNum = productInfoDubboConsumer.getStoreWarnNum(brandInfo.getRefrenceId());
        productInfo.getProductExtInfo().setMinStore(storeWarnNum);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT)) { // 紧张库存产品数量
            count = productInfoDubboConsumer.getProductBaseInfoCount(productInfo);
            brandCount.setTightInventoryCount(null != count ? count.intValue() : 0);
        }
        productInfo.getProductExtInfo().setMinStore(0);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT)) { // 库存缺货产品数量
            count = productInfoDubboConsumer.getProductBaseInfoCount(productInfo);
            brandCount.setShortageCount(null != count ? count.intValue() : 0);
        }
        productInfo.setProductCate(ProductConsts.CATE_ORDER.getKey());
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT)) { // 预订铺货产品数量
            List<Map<String, Object>> list = productInfoDubboConsumer.getProductBaseInfoCountList(productInfo);
            Long[] countAry = {0L, 0L, 0L};
            if (CollectionUtils.isNotEmpty(list)) {
                for (Map<String, Object> item : list) {
                    Integer beginType = Integer.parseInt(item.get("stateSet").toString());
                    if (beginType == ProductConsts.BEGIN_TYPE_FIRST.getKey().intValue()) {
                        Long proCount = Long.parseLong(item.get("count").toString());
                        countAry[beginType - 1] = proCount;
                    }
                }
            }
            brandCount.setPrePublishedCount(countAry[0].intValue());
        }
        BrandViewContact brandViewContact = new BrandViewContact();
        brandViewContact.setBrandId(brandInfo.getRefrenceId());
        brandViewContact.setViewType(BrandConstant.BrandViewContact.VIEW_TYPE_INITIATIVE);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_VIEWDEALERCOUNT)) {// 查看经销商联系信息数量
            count = brandViewContactMapper.getBrandViewContactCount(brandViewContact);
            brandCount.setViewDealerCount(null != count ? count.intValue() : 0);
        }
        BrandesInfo brandesInfo = new BrandesInfo();
        brandesInfo.setBrandId(brandesInfo.getBrandId());
        brandesInfo.setBrandState(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, BrandConstant.BrandCountConst.BRANDCOUNT_BRANDSCOUNT)) { // 品牌数量
            count = brandesInfoMapper.getBrandsInfoCount(brandesInfo);
            brandCount.setBrandsCount(null != count ? count.intValue() : 0);
        }
        if (isExist) {
            brandCountMapper.updateByPrimaryKeySelective(brandCount);
        } else {
            brandCountMapper.insert(brandCount);
        }
        return brandCount;
    }


    /*
     * 获取剩余查看数量
     */
    @Override
    public Integer getViewDealerTotal(String brandId) {
        BrandCount brandCount = brandCountMapper.selectByPrimaryKey(brandId);
        Integer viewCount = BrandConstant.BrandCountConst.BRAND_VIEW_COUNT;
        if (null != brandCount.getViewDealerTotal() && 0 < brandCount.getViewDealerTotal()) {
            viewCount = brandCount.getViewDealerTotal();
        }
        return viewCount;
    }
}
