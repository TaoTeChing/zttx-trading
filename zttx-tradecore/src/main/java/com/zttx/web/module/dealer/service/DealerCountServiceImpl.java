/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.entity.BrandsCount;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.mapper.*;

/**
 * 经销商计数信息 服务实现类
 * <p>File：DealerCount.java </p>
 * <p>Title: DealerCount </p>
 * <p>Description:DealerCount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerCountServiceImpl extends GenericServiceApiImpl<DealerCount> implements DealerCountService
{
    @Autowired
    private DealerJoinMapper    dealerJoinMapper;
    
    @Autowired
    private BrandInviteMapper   brandInviteMapper;
    
    @Autowired
    private DealerInfoMapper    dealerInfoMapper;
    
    @Autowired
    private DealerOrderMapper   dealerOrderMapper;
    
    private DealerCountMapper   dealerCountMapper;
    
    @Autowired
    private DealerRefundMapper  dealerRefundMapper;
    
    @Autowired
    private DealerMessageMapper dealerMessageMapper;
    
    @Autowired(required = true)
    public DealerCountServiceImpl(DealerCountMapper dealerCountMapper)
    {
        super(dealerCountMapper);
        this.dealerCountMapper = dealerCountMapper;
    }
    
    @Override
    public DealerCount selectByDealerId(String dealerId)
    {
        if (null != dealerId) { return dealerCountMapper.selectByDealerId(dealerId); }
        return null;
    }
    
    @Override
    public DealerCount modifyDealerCount(String dealerId, Integer[] countType) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId)) { return null; }
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerId);
        if (null == dealerInfo) { return null; }
        DealerCount dealerCount = dealerCountMapper.selectByDealerId(dealerInfo.getRefrenceId());
        Boolean isExist = true;
        if (null == dealerCount)
        {
            isExist = false;
            dealerCount = new DealerCount();
            dealerCount.setDealerId(dealerId);
            dealerCount.setCreatetime(CalendarUtils.getCurrentLong());
            dealerCount.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerCount.setDelFlag(false);
        }
        DealerJoin filter = new DealerJoin();
        filter.setDealerId(dealerInfo.getRefrenceId());
        Long count = 0L;
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT))
        {
            // 已经加盟的品牌数
            count = dealerJoinMapper.getDealerJoinCount(filter);
            dealerCount.setJoinCount(count.intValue());
        }
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setDealerId(dealerInfo.getRefrenceId());
        brandInvite.setApplyState((short) BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT))
        {
            // 申请中的品牌数
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            dealerCount.setApplyCount(count.intValue());
        }
        brandInvite.setApplyState((short) BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT))
        {
            // 邀请中的品牌数
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            dealerCount.setInviteCount(count.intValue());
        }
        DealerOrder dealerOrder = new DealerOrder();
        dealerOrder.setDealerId(dealerInfo.getRefrenceId());
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT))
        {
            // 待付款订单数量
            count = dealerOrderMapper.selectDealerOrderCount(dealerOrder);
            dealerCount.setBalanceCount(count.intValue());
        }
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT))
        {
            // 待收货订单数量
            count = dealerOrderMapper.selectDealerOrderCount(dealerOrder);
            dealerCount.setWaitConfirmCount(count.intValue());
        }
        DealerRefund dealerRefund = new DealerRefund();
        dealerRefund.setDealerId(dealerInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT))
        {
            // 退款中的订单数
            count = dealerRefundMapper.getDealerRefundCount(dealerRefund);
            dealerCount.setRefundCount(count.intValue());
        }
        if (ArrayUtils.isEmpty(countType) || ArrayUtils.contains(countType, DealerConstant.DealerCount.DEALERCOUNT_COLUMN_SYSMESSAGECOUNT))
        {
            // 未读系统消息
            count = dealerMessageMapper.countDealerUnReadMessages(dealerInfo.getRefrenceId());
            dealerCount.setSysMessageCount(count.intValue());
        }
        if (isExist){
        	try {
                dealerCountMapper.updateByPrimaryKey(dealerCount);
            }catch (Throwable ex){
                ex.printStackTrace();
            }
        }else{
        	dealerCountMapper.insert(dealerCount);
        }
        return dealerCount;
    }
    
    /**
     * 根据dealerId 对 factoryJoinCount,factoryHasPaid做++处理
     * @param dealerId
     * @param updateTime
     */
    @Override
    public void updateCount(String dealerId,Long updateTime){
    	dealerCountMapper.updateCount(dealerId,updateTime);
    }
}
