/*
 * @(#)OrderPayService.java 2014-11-29 下午1:57:17
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.math.BigDecimal;
import java.util.List;

import com.zttx.pay.remoting.model.PayOrderDetails;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrder;

/**
 * <p>File：OrderPayService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-29 下午1:57:17</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public interface OrderPayService
{
    /**
     * 关闭未付款的支付订单
     *
     * @param orderId 订单ID
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeClosePay(String orderId) throws BusinessException;

    /**
     * 关闭未付款的支付订单
     * @param orderIdArr 订单ID
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeClosePay(String[] orderIdArr) throws BusinessException;
    
    /**
     * 是否存在付款记录
     * @param orderId
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    Boolean hasPaid(String orderId) throws BusinessException;
    
    /**
     * 关闭订单
     * @param orderIdArr
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeCloseOrder(String[] orderIdArr) throws BusinessException;
    
    /**
     * 关闭订单
     *
     * @param orderId
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeCloseOrder(String orderId) throws BusinessException;
    
    /**
     * 是否设置密码
     * @param userId
     * @return
     * @throws BusinessException
     */
    Boolean isPaymentPwdExists(String userId) throws BusinessException;
    
    /**
     * 验证支付密码
     *
     * @param userId
     * @param payPwd
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeValidPayPwd(String userId, String payPwd) throws BusinessException;
    
    /**
     * 退款
     * @param orderId
     * @param refundAmount
     * @param pointBalance
     * @author 张昌苗
     */
    void executeRefund(String orderId, BigDecimal refundAmount, BigDecimal pointBalance,String pwd) throws BusinessException;

    /**
     * 退款
     * @param orderId
     * @param refundAmount
     * @param pointBalance
     * @author 张昌苗
     */
    void executeRefund(String orderId, BigDecimal refundAmount, BigDecimal pointBalance) throws BusinessException;

    /**
     * 确认收货付款
     * @param orderId
     * @param payPwd
     * @throws BusinessException
     * @author 张昌苗
     */
    void executeConfirmPay(String orderId, String payPwd) throws BusinessException;

    /**
     * 计算订单因调价,关闭发货等因素造成的需退款给终端商的现金
     * @param orderId
     * @return
     */
    BigDecimal getNeedRefundCashWhenConfirmPay(String orderId);

    /**
     * 计算订单在没有退款单状态下需退款额度(关闭发货,调价造成的退款)
     * @param dealerOrder
     * @return
     */
    BigDecimal countRefundAmount(DealerOrder dealerOrder);

    /**
     * 计算授信订单已使用授信额度
     * @param dealerOrder
     * @return
     */
    BigDecimal countCretditAmountUsed(DealerOrder dealerOrder);
    
    /**
     * 获取订单在付款成功时的总货款
     * @param dealerOrder
     * @return
     */
    BigDecimal getOldProdcutPrice(DealerOrder dealerOrder);

    /**
     * 支付订单
     * @param userId
     * @param orderIdArr
     * @param payAmountArr
     * @param orderType
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public List<PayOrderDetails> synPay(String userId, String[] orderIdArr, BigDecimal[] payAmountArr, Short orderType) throws BusinessException;

}
