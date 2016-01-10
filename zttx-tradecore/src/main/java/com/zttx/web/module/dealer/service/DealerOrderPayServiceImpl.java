/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.service.BrandBuySerLogService;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrderPay;
import com.zttx.web.module.dealer.mapper.DealerOrderPayMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.OrderUtils;

/**
 * 支付记录表 服务实现类
 * <p>File：DealerOrderPay.java </p>
 * <p>Title: DealerOrderPay </p>
 * <p>Description:DealerOrderPay </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerOrderPayServiceImpl extends GenericServiceApiImpl<DealerOrderPay> implements DealerOrderPayService
{

    @Autowired
    private DealerOrderService     dealerOrderService;
    
    @Autowired
    private DealerBuySerLogService dealerBuySerLogService;
    
    @Autowired
    private BrandBuySerLogService  brandBuySerLogService;
    
    private DealerOrderPayMapper dealerOrderPayMapper;

    @Autowired(required = true)
    public DealerOrderPayServiceImpl(DealerOrderPayMapper dealerOrderPayMapper)
    {
        super(dealerOrderPayMapper);
        this.dealerOrderPayMapper = dealerOrderPayMapper;
    }
    
    @Override
    public List<DealerOrderPay> findByOrderId(String orderId)
    {
        return dealerOrderPayMapper.findByOrderId(orderId);
    }
    
    @Override
    public List<DealerOrderPay> listUnpay(String orderId)
    {
        return dealerOrderPayMapper.listUnpay(orderId);
    }
    
    @Override
    public List<DealerOrderPay> listPaid(String orderId)
    {
        if(null!=orderId)
        {
        return dealerOrderPayMapper.listPaid(orderId);
        }
        return null;
    }

    @Override
    public void executePayClose(Long payId) throws BusinessException {
        DealerOrderPay dealerOrderPay = findByIdWithException(payId);
        dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_CLOSE);
        dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderPayMapper.updateByPrimaryKey(dealerOrderPay);
    }

    @Override
    public List<DealerOrderPay> selectDealerOrderPayList(String orderId) {
        if(null != orderId)
        {
            dealerOrderPayMapper.findByOrderId(orderId);
        }
        return null;
    }

    @Override
    public DealerOrderPay findByIdWithException(Long payId) throws BusinessException
    {
        DealerOrderPay dealerOrderPay = dealerOrderPayMapper.findByPayId(payId);
        if (null == dealerOrderPay) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS.code, "支付记录不存在");
        }
        return dealerOrderPay;
    }
    
    @Override
    public DealerOrderPay executePaySuccess(Long payId) throws BusinessException
    {
        DealerOrderPay dealerOrderPay = findByIdWithException(payId);
        dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_PAID);
        dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderPayMapper.updateByPrimaryKey(dealerOrderPay);
        return dealerOrderPay;
    }
    
    @Override
    public DealerOrderPay executePaySuccess(Long payId, DealerOrder dealerOrder) throws BusinessException
    {
        DealerOrderPay dealerOrderPay = findByIdWithException(payId);
        BigDecimal totalAmount = OrderUtils.getTotalAmount(dealerOrder);
        BigDecimal paidAmount = OrderUtils.getPaidAmount(dealerOrder);
        BigDecimal unpayAmount = totalAmount.subtract(paidAmount);
        dealerOrderPay.setUnpayAmount(unpayAmount);
        dealerOrderPay.setPayTime(CalendarUtils.getCurrentLong());
        dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_PAID);
        dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderPayMapper.updateByPrimaryKey(dealerOrderPay);
        return dealerOrderPay;
    }
    
    @Override
    public DealerOrderPay findByIdWithOrder(Long payId) throws BusinessException
    {
        DealerOrderPay dealerOrderPay = findByIdWithException(payId);
        if (DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_ORDER == dealerOrderPay.getOrderType().shortValue())
        {
            DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(dealerOrderPay.getOrderId());
            if (null == dealerOrder) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS.getCode(), "订单不存在"); }
            dealerOrderPay.setDealerOrder(dealerOrder);
        }
        else if (DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_SERVICE == dealerOrderPay.getOrderType().shortValue())
        {
            DealerBuySerLog dealerBuySerLog = dealerBuySerLogService.selectByPrimaryKey(dealerOrderPay.getOrderId());
            if (null == dealerBuySerLog) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            dealerOrderPay.setDealerBuySerLog(dealerBuySerLog);
        }
        else if (DealerConstant.DealerOrderPay.ORDER_TYPE_BRAND_SERVICE == dealerOrderPay.getOrderType().shortValue())
        {
            BrandBuySerLog brandBuySerLog = brandBuySerLogService.selectByPrimaryKey(dealerOrderPay.getOrderId());
            if (null == brandBuySerLog) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            dealerOrderPay.setBrandBuySerLog(brandBuySerLog);
        }
        return dealerOrderPay;
    }
    
    @Override
    public List<DealerOrderPay> findAllUnPay() throws BusinessException
    {
        return dealerOrderPayMapper.findAllUnPay();
    }
}
