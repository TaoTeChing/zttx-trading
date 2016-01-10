/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.service.ComplainNumberService;
import com.zttx.web.module.dealer.entity.DealerComplaint;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.mapper.DealerComplaintMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商投诉信息 服务实现类
 * <p>File：DealerComplaint.java </p>
 * <p>Title: DealerComplaint </p>
 * <p>Description:DealerComplaint </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerComplaintServiceImpl extends GenericServiceApiImpl<DealerComplaint> implements DealerComplaintService
{
    @Autowired
    private DealerOrderService    dealerOrderService;
    
    @Autowired
    private DealerOrderMapper     dealerOrderMapper;
    
    @Autowired
    private ComplainNumberService complainNumberService;
    
    private DealerComplaintMapper dealerComplaintMapper;
    
    @Autowired(required = true)
    public DealerComplaintServiceImpl(DealerComplaintMapper dealerComplaintMapper)
    {
        super(dealerComplaintMapper);
        this.dealerComplaintMapper = dealerComplaintMapper;
    }
    
    @Override
    public DealerComplaint getDealerComplaint(String refrenceId)
    {
        if (refrenceId != null)
        {
            List<DealerComplaint> dealerComplaintList = dealerComplaintMapper.getDealerComplaint(refrenceId);
            if (!dealerComplaintList.isEmpty()) { return dealerComplaintList.get(0); }
        }
        return null;
    }
    
    @Override
    public PaginateResult<DealerComplaint> searchByClient(DealerComplaint dealerComplaint, Pagination page)
    {
        return new PaginateResult<>(page, dealerComplaintMapper.searchByClient(dealerComplaint, page));
    }
    
    @Override
    public void updateComState(DealerComplaint dealerComplaint) throws BusinessException
    {
        Short comState = dealerComplaint.getComState();
        String refrenceId = dealerComplaint.getRefrenceId();
        if (null == comState || StringUtils.isBlank(refrenceId))
        {
            throw new BusinessException(ClientConst.NULERROR);
        }
        else if (comState.equals(DealerConstant.DealerComplaint.COMSTATE_1))// 客户介入
        {
            DealerComplaint old = dealerComplaintMapper.selectByPrimaryKey(refrenceId);
            if (null == old || !old.getComState().equals(DealerConstant.DealerComplaint.COMSTATE_0)) { throw new BusinessException(ClientConst.PARAMERROR); }
            old.setComState(comState);
            old.setInterposeTime(CalendarUtils.getCurrentLong());
            dealerComplaintMapper.updateByPrimaryKey(old);
            // 维护投诉订单的状态
            dealerOrderService.updateComplaintState(old.getOrderId(), DealerConstant.DealerComplaint.COMSTATE_1);
        }
        else if (comState.equals(DealerConstant.DealerComplaint.COMSTATE_2))// 处理完成
        {
            DealerComplaint old = dealerComplaintMapper.selectByPrimaryKey(refrenceId);
            if (null == old || !old.getComState().equals(DealerConstant.DealerComplaint.COMSTATE_1)) { throw new BusinessException(ClientConst.PARAMERROR); }
            old.setComState(comState);
            old.setComplaintResult(dealerComplaint.getComplaintResult());
            dealerComplaintMapper.updateByPrimaryKey(old);
            // 维护投诉订单的状态
            dealerOrderService.updateComplaintState(old.getOrderId(), DealerConstant.DealerComplaint.COMSTATE_2);
        }
        else
        {
            throw new BusinessException(ClientConst.PARAMERROR);
        }
    }
    
    @Override
    public Short save(DealerComplaint dealerComplaint) throws BusinessException
    {
        DealerOrder dealerOrder = dealerOrderMapper.getDealerOrder(dealerComplaint.getOrderId(), dealerComplaint.getDealerId());
        if (null == dealerOrder) { throw new BusinessException(DealerConst.COMPLAINT_ORDER_ERROR); }
        short state = dealerOrder.getOrderStatus();
        Short ComplaintState = dealerOrder.getComplaintState();
        DateTime now = DateTime.now();
        Long updateTime = dealerOrder.getUpdateTime();
        DateTime lastUpdateTime = new DateTime(updateTime);
        if (now.minusDays(30).isBefore(lastUpdateTime))
        {
            if (dealerComplaintMapper.isExiect(dealerOrder.getRefrenceId())) { return ComplaintState; }
            if (DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED == state || DealerConstant.DealerOrder.ORDER_STATUS_CLOSED == state)
            {
                dealerComplaint.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerComplaint.setCreatetime(CalendarUtils.getCurrentLong());
                dealerComplaint.setComState(DealerConstant.DealerComplaint.COMSTATE_0);
                dealerComplaint.setBrandId(dealerOrder.getBrandId());
                // dealerComplaint.setBrandName(dealerOrder.getBrandName());
                dealerComplaint.setComplaintNumberId(com.zttx.web.utils.SerialnoUtils.buildComplainSN(complainNumberService.getComplainId()));
                // dealerComplaint.setDealerName(dealerOrder.getDealerName());
                dealerComplaint.setOrderId(dealerOrder.getRefrenceId());
                dealerComplaint.setOrderNumber(dealerOrder.getOrderId());
                dealerComplaint.setBrandsId(dealerOrder.getBrandsId());
                // dealerComplaint.setBrandsName(dealerOrder.getBrandsName());
                dealerComplaintMapper.insert(dealerComplaint);
                dealerOrderMapper.updateComplaintState(dealerOrder.getRefrenceId(), DealerConstant.DealerComplaint.COMSTATE_0);
                ComplaintState = DealerConstant.DealerOrder.SER_STATUS_WAIT;
                return ComplaintState;
            }
            else
            {
                throw new BusinessException(DealerConst.NOT_COMPLAINT);
            }
        }
        else
        {
            throw new BusinessException(DealerConst.ORDER_BEYOND_DATE);
        }
    }
    
    @Override
    public DealerComplaint loadDealerComplaint(String refrenceId, String dealerId)
    {
        return dealerComplaintMapper.loadDealerComplaint(refrenceId, dealerId);
    }
    
    @Override
    public void disApply(DealerComplaint dealerComplaint)
    {
        // 投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）
        dealerComplaint.setComState(DealerConstant.DealerComplaint.COMSTATE_3);
        dealerComplaintMapper.updateByPrimaryKey(dealerComplaint);
        // 徐志勇 增加订单状态修改
        dealerOrderMapper.updateComplaintState(dealerComplaint.getOrderId(), DealerConstant.DealerComplaint.COMSTATE_3);
    }
    
    @Override
    public PaginateResult<DealerComplaint> getList(DealerComplaint dealerComplaint, Pagination page)
    {
        if (StringUtils.isNotBlank(dealerComplaint.getStartTime()))
        {
            Long startTimeLong = CalendarUtils.getLongFromTime(dealerComplaint.getStartTime() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            dealerComplaint.setStartTime(String.valueOf(startTimeLong));
        }
        if (StringUtils.isNotBlank(dealerComplaint.getEndTime()))
        {
            Long endTimeLong = CalendarUtils.getLongFromTime(dealerComplaint.getEndTime() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            dealerComplaint.setEndTime(String.valueOf(endTimeLong));
        }
        List<DealerComplaint> list = null;
        try
        {
            list = dealerComplaintMapper.getList(dealerComplaint, page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new PaginateResult<>(page, list);
    }
}
