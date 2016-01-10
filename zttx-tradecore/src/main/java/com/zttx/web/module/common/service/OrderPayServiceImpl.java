/*
 * @(#)OrderPayServiceImpl.java 2014-11-29 下午1:57:38
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.pay.common.consts.PayOrderConst;
import com.zttx.pay.remoting.api.PayOrderApi;
import com.zttx.pay.remoting.exception.RemotingException;
import com.zttx.pay.remoting.model.*;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.RedissonUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandBuySerLogService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandPointBalanceService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.utils.OrderUtils;
import com.zttx.web.utils.PayErrorConvertor;
import com.zttx.web.utils.RPCUtils;

/**
 * <p>File：OrderPayServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-29 下午1:57:38</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
@Service
public class OrderPayServiceImpl implements OrderPayService
{
    private final static Logger      logger               = LoggerFactory.getLogger(OrderPayServiceImpl.class);
    
    public final static Integer      CATE_USER_ERROR      = 201000;
    
    public final static Integer      CATE_PAY_ORDER_ERROR = 202000;
    
    @Autowired
    private DealerOrderService       dealerOrderService;
    
    @Autowired
    private DealerOrderPayService    dealerOrderPayService;
    
    @Autowired
    private PayOrderApi              payOrderApi;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private BrandPointBalanceService brandPointBalanceService;
    
    @Autowired
    private DealerJoinService        dealerJoinService;
    
    @Autowired
    private BrandBuySerLogService    brandBuySerLogService;
    
    @Autowired
    private DealerBuySerLogService   dealerBuySerLogService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private BrandInfoService         brandInfoService;
    
    @Autowired
    private DealerInfoService        dealerInfoService;
    
    @Autowired
    private WebServiceItemsService   webServiceItemsService;
    
    @Autowired
    private DealerOrdersMapper       dealerOrdersMapper;
    
    @Override
    public void executeCloseOrder(String orderId) throws BusinessException
    {
        executeCloseOrder(new String[]{orderId});
    }
    
    @Override
    public void executeCloseOrder(String[] orderIdArr) throws BusinessException
    {
        /*
         * for (String orderId : orderIdArr)
         * {
         * if (hasPaid(orderId)) { throw new BusinessException(CommonConst.ORDER_PAY_CLOSE_ORDER); }
         * }
         */
        executeClosePay(orderIdArr);
    }
    
    @Override
    public void executeClosePay(String orderId) throws BusinessException
    {
        executeClosePay(new String[]{orderId});
    }
    
    @Override
    public void executeClosePay(String[] orderIdArr) throws BusinessException
    {
        List<DealerOrderPay> dataList = Lists.newArrayList();
        List<Long> payIdList = Lists.newArrayList();
        for (String orderId : orderIdArr)
        {
            List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(orderId);
            for (DealerOrderPay unpay : unpayList)
            {
                payIdList.add(unpay.getPayId());
                dataList.add(unpay);
            }
        }
        if (CollectionUtils.isEmpty(payIdList)) { return; }
        try
        {
            payOrderApi.closeOrders(payIdList, CommonConstant.OrderPay.PAY_MERCHANT_ID);
        }
        catch (RemotingException e)
        {
            throw parse(CATE_PAY_ORDER_ERROR, e);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
        for (DealerOrderPay dealerOrderPay : dataList)
        {
            dealerOrderPayService.executePayClose(dealerOrderPay.getPayId());
        }
    }
    
    @Override
    public Boolean hasPaid(String orderId) throws BusinessException
    {
        DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(orderId);
        List<DealerOrderPay> paidList = dealerOrderPayService.listPaid(dealerOrder.getRefrenceId());
        if (CollectionUtils.isNotEmpty(paidList)) { return true; }
        List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(dealerOrder.getRefrenceId());
        if (CollectionUtils.isEmpty(unpayList)) { return false; }
        for (DealerOrderPay dealerOrderPay : unpayList)
        {
            PayOrderDetails payOrderDetails;
            try
            {
                payOrderDetails = payOrderApi.queryOrder(dealerOrderPay.getPayId(), CommonConstant.OrderPay.PAY_MERCHANT_ID);
            }
            catch (RemotingException e)
            {
                throw parse(CATE_PAY_ORDER_ERROR, e);
            }
            catch (Exception e)
            {
                logger.error("调用支付系统接口失败：", e);
                throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
            }
            if (null == payOrderDetails) { throw new BusinessException(CommonConst.ORDER_PAY_NULL); }
            if (PayOrderConst.STATE_PAYED == payOrderDetails.getTranState().intValue() || PayOrderConst.STATE_COMPLETED == payOrderDetails.getTranState().intValue()) { return true; }
        }
        return false;
    }
    
    /**
     * 处理异常
     * @param cate
     * @param e
     * @return
     */
    private final static BusinessException parse(int cate, RemotingException e)
    {
        return new BusinessException(cate + e.getCode(), e.getMessage());
    }
    
    @Override
    public Boolean isPaymentPwdExists(String userId) throws BusinessException
    {
        Long payUserId = userInfoService.executeFindPayUserId(userId);
        try
        {
            return payOrderApi.isPaymentPwdExists(payUserId);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
    }
    
    @Override
    public void executeValidPayPwd(String userId, String payPwd) throws BusinessException
    {
        Long payUserId = userInfoService.executeFindPayUserId(userId);
        try
        {
            payOrderApi.validatePaymentPwd(payUserId, payPwd);
        }
        catch (RemotingException e)
        {
            throw parse(CATE_USER_ERROR, e);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
    }
    
    @Override
    public void executeRefund(String orderId, BigDecimal refundAmount, BigDecimal pointBalance) throws BusinessException
    {
        this.executeRefund(orderId, refundAmount, pointBalance, null);
    }
    
    @Override
    public void executeRefund(String orderId, BigDecimal refundAmount, BigDecimal pointBalance, String pwd) throws BusinessException
    {
        if (null == refundAmount || refundAmount.compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "退款金额不正确"); }
        pointBalance = null == pointBalance ? BigDecimal.ZERO : pointBalance;
        if (pointBalance.compareTo(BigDecimal.ZERO) < 0) { throw new BusinessException(CommonConst.ORDER_PAY_POINT_BALANCE); }
        DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(orderId);
        if (dealerOrder.getDealerOrderType().shortValue() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
        {
            this.executeRefund4Credit(dealerOrder, refundAmount, pwd);
        }
        else
        {
            if (null == dealerOrder) throw new BusinessException(CommonConst.DATA_NOT_EXISTS.getCode(), "订单不存在");
            Long userId = userInfoService.executeFindPayUserId(dealerOrder.getDealerId());
            List<DealerOrderPay> paidList = dealerOrderPayService.listPaid(dealerOrder.getRefrenceId());
            if (CollectionUtils.isEmpty(paidList)) { throw new BusinessException(CommonConst.ORDER_PAY_PAID_NULL); }
            BigDecimal refundAmountNoSendsGoods = countRefundAmount(dealerOrder); // 如果有关闭发货
            refundAmount = refundAmount.add(refundAmountNoSendsGoods);
            List<RefundObj> refundObjList = countRefund(paidList, refundAmount, pointBalance);
            List<ConfirmPay> confirmPayList = countConfirm(refundObjList);
            ComplicatedPayDetails complicatedPayDetails;
            List<RefundDetails> refundDetailsList;
            try
            {
                complicatedPayDetails = payOrderApi.batchConfirmAndRefund(confirmPayList, refundObjList, userId, CommonConstant.OrderPay.PAY_MERCHANT_ID);
            }
            catch (RemotingException e)
            {
                throw parse(CATE_PAY_ORDER_ERROR, e);
            }
            catch (Exception e)
            {
                logger.error("调用支付系统接口失败：", e);
                throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
            }
            if (null == complicatedPayDetails) { throw new BusinessException(CommonConst.ORDER_PAY_REFUND_NULL); }
            refundDetailsList = complicatedPayDetails.getRefundDetailsList();
            if (CollectionUtils.isEmpty(refundDetailsList)) { throw new BusinessException(CommonConst.ORDER_PAY_REFUND_NULL); }
            if (refundDetailsList.size() != refundObjList.size()) { throw new BusinessException(CommonConst.FAIL.getCode(), "退款订单生成的数量与订单数量不相等"); }
            for (int i = 0; i < refundDetailsList.size(); i++)
            {
                // 新增支付记录
                DealerOrderPay dealerOrderPay = new DealerOrderPay();
                dealerOrderPay.setOrderId(orderId);
                dealerOrderPay.setOrderType(DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_ORDER);
                dealerOrderPay.setPayId(refundDetailsList.get(i).getId());
                dealerOrderPay.setPayAmount(refundDetailsList.get(i).getAmount().multiply(new BigDecimal(-1)));
                dealerOrderPay.setPointBalance(BigDecimal.ZERO);
                dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_REFUND);
                dealerOrderPay.setCreateTime(CalendarUtils.getCurrentTime());
                dealerOrderPay.setDelFlag(Boolean.FALSE);
                dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerOrderPayService.insert(dealerOrderPay);
            }
            // 增加已扣点金额
            dealerOrderService.addPointBalance(dealerOrder, pointBalance);
        }
    }

    private void executeRefund4Credit(DealerOrder dealerOrder, BigDecimal refundAmount, String pwd) throws BusinessException
    {
        if (null == dealerOrder) throw new BusinessException(CommonConst.DATA_NOT_EXISTS.getCode(), "订单不存在");
        Long userId = userInfoService.executeFindPayUserId(dealerOrder.getDealerId());
        List<DealerOrderPay> paidList = dealerOrderPayService.listPaid(dealerOrder.getRefrenceId());
        if(null == paidList) paidList = Lists.newArrayList();
        /*if (CollectionUtils.isEmpty(paidList)) {
            throw new BusinessException(CommonConst.ORDER_PAY_PAID_NULL);
        }*/
        BigDecimal pointBalance = brandPointBalanceService.getPointBalance(dealerOrder, null);
        List<RefundDetails> refundDetailsList = Lists.newArrayList();
        List<ConfirmPay> confirmPayList = countConfirm(paidList, pointBalance);
        try {
            BigDecimal refundAmountNoApply = countRefundAmount(dealerOrder); // 非申请退款需退款(有关闭发货,授信调价等因素的需退货款)
            refundAmount = refundAmount.add(refundAmountNoApply);
            if (refundAmount.compareTo(BigDecimal.ZERO) > 0)
            {
                BigDecimal creditAmountUsed = countCretditAmountUsed(dealerOrder); //订单已用授信额度
                if (refundAmount.compareTo(creditAmountUsed) > 0) // 退款超过授信额度,需退部分余额
                {
                    BigDecimal refundMoney = refundAmount.subtract(creditAmountUsed);
                    BigDecimal payment = dealerOrder.getPayment();
                    if(null != dealerOrder.getAdjustFreight() && dealerOrder.getFrePayState().shortValue() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED)
                    {
                        payment = payment.add(dealerOrder.getAdjustFreight());
                    }
                    if (refundMoney.compareTo(payment) > 0)
                    {
                        refundMoney = payment;
                    }
                    List<RefundObj> refundObjList = countRefund(paidList, refundMoney, pointBalance);
                    confirmPayList = countConfirm(refundObjList);
                    //确认收货并退款
                    ComplicatedPayDetails cpds = payOrderApi.batchConfirmAndRefund(confirmPayList, refundObjList, userId, CommonConstant.OrderPay.PAY_MERCHANT_ID);
                    refundDetailsList = cpds.getRefundDetailsList();
                    // 退还全部订单授信额度
                    updateOrderCreditAmount(dealerOrder, creditAmountUsed);
                }
                else
                {
                    //确认收货
                    doCommonRefundAmount(pwd, userId, confirmPayList, dealerOrder);
                    // 退还部分订单授信额度
                    updateOrderCreditAmount(dealerOrder, refundAmount);
                }
            }
            else{
                //确认收货
                doCommonRefundAmount(pwd, userId, confirmPayList, dealerOrder);
            }
        } catch (RemotingException e) {
            throw parse(CATE_PAY_ORDER_ERROR, e);
        } catch (Exception e) {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
        for (int i = 0; i < refundDetailsList.size(); i++) {
            // 新增支付记录
            DealerOrderPay dealerOrderPay = new DealerOrderPay();
            dealerOrderPay.setOrderId(dealerOrder.getRefrenceId());
            dealerOrderPay.setOrderType(DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_ORDER);
            dealerOrderPay.setPayId(refundDetailsList.get(i).getId());
            dealerOrderPay.setPayAmount(refundDetailsList.get(i).getAmount().multiply(new BigDecimal(-1)));
            dealerOrderPay.setPointBalance(BigDecimal.ZERO);
            dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_REFUND);
            dealerOrderPay.setCreateTime(CalendarUtils.getCurrentTime());
            dealerOrderPay.setDelFlag(Boolean.FALSE);
            dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderPayService.insert(dealerOrderPay);
        }
        // 增加已扣点金额
        dealerOrderService.addPointBalance(dealerOrder, pointBalance);
    }
    
    /**
     * 计算退款金额（退款和佣金分别从各笔支付记录扣除）
     *
     * @param paidList        支付记录
     * @param curRefundAmount 需要退款的金额
     * @param curPointBalance 需要扣除的佣金
     * @return
     * @author 张昌苗
     */
    private List<RefundObj> countRefund(List<DealerOrderPay> paidList, BigDecimal curRefundAmount, BigDecimal curPointBalance) throws BusinessException
    {
        List<RefundObj> refundObjList = Lists.newArrayList();
        for (int i = 0; i < paidList.size(); i++)
        {
            DealerOrderPay paid = paidList.get(i);
            RefundObj obj = new RefundObj();
            obj.setId(paid.getPayId());
            if (curRefundAmount.compareTo(paid.getPayAmount()) < 1)
            {
                obj.setRefundAmount(curRefundAmount);
                obj.setPayAmount(paid.getPayAmount().subtract(curRefundAmount));
                curRefundAmount = BigDecimal.ZERO;
            }
            else
            {
                obj.setRefundAmount(paid.getPayAmount());
                obj.setPayAmount(BigDecimal.ZERO);
                curRefundAmount = curRefundAmount.subtract(paid.getPayAmount());
            }
            if (curPointBalance.compareTo(obj.getPayAmount()) < 1)
            {
                obj.setCommission(curPointBalance);
                curPointBalance = BigDecimal.ZERO;
            }
            else
            {
                obj.setCommission(obj.getPayAmount());
                curPointBalance = curPointBalance.subtract(obj.getPayAmount());
            }
            refundObjList.add(obj);
        }
        if (curRefundAmount.compareTo(BigDecimal.ZERO) > 0) { throw new BusinessException(CommonConst.FAIL.getCode(), "退款的金额超出可退金额"); }
        if (curPointBalance.compareTo(BigDecimal.ZERO) > 0) { throw new BusinessException(CommonConst.FAIL.getCode(), "扣除的佣金超出可扣佣金"); }
        return refundObjList;
    }
    
    private List<ConfirmPay> countConfirm(List<RefundObj> refundObjList) throws BusinessException
    {
        List<ConfirmPay> confirmPayList = Lists.newArrayList();
        List<RefundObj> tempRefundObjList = Lists.newArrayList();
        for (RefundObj refundObj : refundObjList)
        {
            if (refundObj.getRefundAmount().compareTo(BigDecimal.ZERO) == 0)
            {
                ConfirmPay confirmPay = new ConfirmPay();
                confirmPay.setId(refundObj.getId());
                confirmPay.setCommission(refundObj.getCommission());
                confirmPayList.add(confirmPay);
            }
            else
            {
                tempRefundObjList.add(refundObj);
            }
        }
        refundObjList.clear();
        refundObjList.addAll(tempRefundObjList);
        return confirmPayList;
    }
    
    @Override
    public void executeConfirmPay(String orderId, String payPwd) throws BusinessException
    {
        DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(orderId);
        Long userId = userInfoService.executeFindPayUserId(dealerOrder.getDealerId());
        BigDecimal pointBalance = brandPointBalanceService.getPointBalance(dealerOrder, null);
        if (null == pointBalance || pointBalance.compareTo(BigDecimal.ZERO) < 0) { throw new BusinessException(CommonConst.ORDER_PAY_POINT_BALANCE); }
        List<DealerOrderPay> paidList = dealerOrderPayService.listPaid(dealerOrder.getRefrenceId());
        List<ConfirmPay> confirmPayList = countConfirm(paidList, pointBalance);
        List<PayOrderDetails> payOrderDetailsList = Lists.newArrayList();
        /*boolean refundFlag = false;*/
        List<RefundDetails> refundDetailsList = Lists.newArrayList();
        try
        {
            BigDecimal refundAmount = countRefundAmount(dealerOrder);
            if (refundAmount.compareTo(BigDecimal.ZERO) > 0) // 有需要退款,比如关闭发货,授信调价
            {
                if (dealerOrder.getDealerOrderType().shortValue() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
                {
                    BigDecimal creditAmountUsed = countCretditAmountUsed(dealerOrder);
                    if (refundAmount.compareTo(creditAmountUsed) > 0) // 退款超过授信额度,需退部分现金余额
                    {
                        BigDecimal refundMoney = refundAmount.subtract(creditAmountUsed); // 需退的现金
                        if (refundMoney.compareTo(dealerOrder.getPayment()) > 0)
                        {
                            refundMoney = dealerOrder.getPayment();
                        }
                        if (refundMoney.compareTo(BigDecimal.ZERO) > 0 && refundMoney.compareTo(dealerOrder.getPayment()) <= 0)
                        {
                            /*refundFlag = true;*/
                            List<RefundObj> refundObjList = countRefund(paidList, refundMoney, pointBalance);
                            confirmPayList = countConfirm(refundObjList);
                            ComplicatedPayDetails cpds = payOrderApi.batchConfirmAndRefund(confirmPayList, refundObjList, userId, CommonConstant.OrderPay.PAY_MERCHANT_ID);
                            refundDetailsList = cpds.getRefundDetailsList();
                        }
                        else
                        {
                            payOrderDetailsList = doCommonRefundAmount(payPwd, userId, confirmPayList, dealerOrder);
                        }
                        // 退还全部订单授信额度
                        updateOrderCreditAmount(dealerOrder,creditAmountUsed);
                    }
                    else
                    {
                        /*refundFlag = true;*/
                        payOrderDetailsList = doCommonRefundAmount(payPwd, userId, confirmPayList, dealerOrder);
                        // 退还授信订单授信额度
                        updateOrderCreditAmount(dealerOrder, refundAmount);
                    }
                }
                else // 现款订单
                {
                    if (refundAmount.compareTo(dealerOrder.getPayment()) > 0)
                    {
                        refundAmount = dealerOrder.getPayment();
                    }
                    if (refundAmount.compareTo(BigDecimal.ZERO) > 0 && refundAmount.compareTo(dealerOrder.getPayment()) <= 0)
                    {
                        /*refundFlag = true;*/
                        List<RefundObj> refundObjList = countRefund(paidList, refundAmount, pointBalance);
                        confirmPayList = countConfirm(refundObjList);
                        ComplicatedPayDetails cpds = payOrderApi.batchConfirmAndRefund(confirmPayList, refundObjList, userId, CommonConstant.OrderPay.PAY_MERCHANT_ID);
                        refundDetailsList = cpds.getRefundDetailsList();
                    }
                }
            }
            else // 无退款
            {
                payOrderDetailsList = doCommonRefundAmount(payPwd, userId, confirmPayList, dealerOrder);
            }
        }
        catch (RemotingException e)
        {
            throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
        /*if (!refundFlag)
        {
            if (CollectionUtils.isEmpty(payOrderDetailsList)) { throw new BusinessException(CommonConst.ORDER_PAY_REFUND_NULL); }
            if (confirmPayList.size() != payOrderDetailsList.size()) { throw new BusinessException(CommonConst.FAIL.getCode(), "付款订单生成的数量与订单数量不相等"); }
        }
        else
        {
            if (CollectionUtils.isEmpty(refundDetailsList)) { throw new BusinessException(CommonConst.ORDER_PAY_REFUND_NULL); }
        }*/
        // 增加已扣点金额
        dealerOrderService.addPointBalance(dealerOrder, pointBalance);
    }

    @Override
    public BigDecimal getNeedRefundCashWhenConfirmPay(String orderId)
    {
        BigDecimal needRefundCash = BigDecimal.ZERO;
        DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(orderId);
        if(dealerOrder.getOrderStatus().shortValue() == DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE)
        {
            if(null != dealerOrder.getNoSendGoodsAmount())
            {
                needRefundCash = dealerOrder.getNoSendGoodsAmount();
            }
        }
        else if(dealerOrder.getOrderStatus().shortValue() == DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED)
        {
            BigDecimal refundAmount = countRefundAmount(dealerOrder);
            if (refundAmount.compareTo(BigDecimal.ZERO) > 0) // 有需要退款,比如关闭发货,授信调价
            {
                if (dealerOrder.getDealerOrderType().shortValue() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT) {
                    BigDecimal creditAmountUsed = countCretditAmountUsed(dealerOrder);
                    if (refundAmount.compareTo(creditAmountUsed) > 0) // 退款超过授信额度,需退部分现金余额
                    {
                        needRefundCash = refundAmount.subtract(creditAmountUsed); // 需退的现金
                        if (needRefundCash.compareTo(dealerOrder.getPayment()) > 0) {
                            needRefundCash = dealerOrder.getPayment();
                        }
                    }
                }
                else // 现款订单
                {
                    if (refundAmount.compareTo(dealerOrder.getPayment()) > 0)
                    {
                        refundAmount = dealerOrder.getPayment();
                    }
                    needRefundCash = refundAmount;
                }
            }
        }
        return needRefundCash;
    }
    
    // 常规确认支付
    private List<PayOrderDetails> doCommonRefundAmount(String payPwd, Long userId, List<ConfirmPay> confirmPayList, DealerOrder dealerOrder) throws RemotingException,
            BusinessException
    {
        if (null == payPwd)
        {
            return payOrderApi.batchAutoConfirmPay(confirmPayList, CommonConstant.OrderPay.PAY_MERCHANT_ID);
        }
        else
        {
            payOrderApi.validatePaymentPwd(userId, payPwd);
            return payOrderApi.batchConfirmPay(confirmPayList, userId, CommonConstant.OrderPay.PAY_MERCHANT_ID, payPwd);
        }
    }
    
    // 计算确认收货时需退款的金额,考虑了存在关闭发货，授信调价等因素
    @Override
    public BigDecimal countRefundAmount(DealerOrder dealerOrder)
    {
        BigDecimal refundAmount = BigDecimal.ZERO;
        if (dealerOrder.getDealerOrderType().shortValue() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
        {
            BigDecimal oldProductPrice = getOldProdcutPrice(dealerOrder);
            refundAmount = oldProductPrice.subtract(dealerOrder.getProductPrice());
        }
        else // 现款订单
        {
            if (null != dealerOrder.getNoSendGoodsAmount())
            {
                if (dealerOrder.getNoSendGoodsAmount().compareTo(dealerOrder.getPayment()) <= 0) refundAmount = dealerOrder.getNoSendGoodsAmount();
                else refundAmount = dealerOrder.getPayment();
            }
        }
        return refundAmount;
    }

    /**
     * 计算关闭发货的授信订单新的授信额度
     （1）若关闭发货金额<=订单内使用的授信额度，那么订单内使用的新授信额度=订单内使用的原授信额度-关闭发货金额；
     （2）若关闭发货金额>订单内使用的授信额度，那么订单内使用的新授信额度=0；
     * @param dealerOrder
     * @return
     */
    private BigDecimal countNewCreditAmount(DealerOrder dealerOrder)
    {
        BigDecimal newCreditAmount = BigDecimal.ZERO;
        BigDecimal creditAmountUsed = countCretditAmountUsed(dealerOrder);
        if (dealerOrder.getNoSendGoodsAmount().compareTo(creditAmountUsed) <= 0)
        {
            newCreditAmount = creditAmountUsed.subtract(dealerOrder.getNoSendGoodsAmount());
        }
        return newCreditAmount;
    }

    // 计算授信订单已使用授信额度
    @Override
    public BigDecimal countCretditAmountUsed(DealerOrder dealerOrder)
    {
        BigDecimal productPriceOld = getOldProdcutPrice(dealerOrder);
        BigDecimal creditAmountUsed = productPriceOld.subtract(dealerOrder.getPayment());
        return creditAmountUsed;
    }

    //获取订单在付款成功时的总货款
    public BigDecimal getOldProdcutPrice(DealerOrder dealerOrder)
    {
        BigDecimal productPriceOld = BigDecimal.ZERO;
        if(null != dealerOrder)
        {
            // 付款成功时保存的总货款
            if (null != dealerOrder.getProductPriceWhenPay() && dealerOrder.getProductPriceWhenPay().compareTo(BigDecimal.ZERO) > 0) {
                productPriceOld = dealerOrder.getProductPriceWhenPay();
            } else {
                List<DealerOrders> dealerOrdersList = dealerOrdersMapper.selectDealerOrders(dealerOrder.getRefrenceId(), dealerOrder.getDealerId());
                for (DealerOrders dealerOrders : dealerOrdersList) {
                    if (null != dealerOrders.getOldPrice()) // 订单项sku有调过价
                    {
                        productPriceOld = productPriceOld.add(dealerOrders.getOldPrice().multiply(new BigDecimal(dealerOrders.getQuantity())));
                    } else {
                        productPriceOld = productPriceOld.add(dealerOrders.getTotalAmount());
                    }
                }
            }
        }
        return productPriceOld;
    }
    
    // 修改授信订单授信额度
    private void updateOrderCreditAmount(DealerOrder dealerOrder,BigDecimal refundCreditMoney)
    {
        DealerJoin join = dealerJoinService.findByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
        if (null != join)
        {
            BigDecimal creditCurrent = join.getCreditCurrent();
            join.setCreditCurrent(creditCurrent.subtract(refundCreditMoney));
            dealerJoinService.updateByPrimaryKey(join);
        }
    }
    
    private List<ConfirmPay> countConfirm(List<DealerOrderPay> paidList, BigDecimal curPointBalance) throws BusinessException
    {
        List<ConfirmPay> confirmPayList = Lists.newArrayList();
        for (int i = 0; i < paidList.size(); i++)
        {
            DealerOrderPay paid = paidList.get(i);
            ConfirmPay obj = new ConfirmPay();
            obj.setId(paid.getPayId());
            if (curPointBalance.compareTo(paid.getPayAmount()) < 1)
            {
                obj.setCommission(curPointBalance);
                curPointBalance = BigDecimal.ZERO;
            }
            else
            {
                obj.setCommission(paid.getPayAmount());
                curPointBalance = curPointBalance.subtract(paid.getPayAmount());
            }
            confirmPayList.add(obj);
        }
        if (curPointBalance.compareTo(BigDecimal.ZERO) > 0) { throw new BusinessException(CommonConst.FAIL.getCode(), "扣除的佣金超出可扣佣金"); }
        return confirmPayList;
    }
    
    public List<PayOrderDetails> synPay(String userId, String[] orderIdArr, BigDecimal[] payAmountArr, Short orderType) throws BusinessException
    {
        if (ArrayUtils.isEmpty(orderIdArr)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(orderIdArr.hashCode()));
        List<PayOrderDetails> detailsList = null;
        try
        {
            lock.lock();
            detailsList = pay(userId, orderIdArr, payAmountArr, orderType);
        }
        catch (BusinessException e)
        {
            throw new BusinessException("支付订单失败！"+e.getMessage());
        }
        catch (Exception e)
        {
            throw new BusinessException("支付订单失败！");
        }
        finally
        {
            lock.unlock();
        }
        return detailsList;
    }
    
    private List<PayOrderDetails> pay(String userId, String[] orderIdArr, BigDecimal[] payAmountArr, Short orderType) throws BusinessException
    {
        orderType = null == orderType ? (short) 0 : orderType;
        List<PayOrderDetails> payOrderDetailsList;
        switch (orderType)
        {
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_ORDER:
                payOrderDetailsList = executePayDealerOrder(userId, orderIdArr, payAmountArr);
                break;
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_SERVICE:
                payOrderDetailsList = executePayDealerService(userId, orderIdArr);
                break;
            case DealerConstant.DealerOrderPay.ORDER_TYPE_BRAND_SERVICE:
                payOrderDetailsList = executePayBrandService(userId, orderIdArr);
                break;
            // 支付押金
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_DEPOSIT:
                payOrderDetailsList = executePayDealerDeposit(userId, orderIdArr);
                break;
            default:
                throw new BusinessException(CommonConst.FAIL.getCode(), "类型未支持");
        }
        return payOrderDetailsList;
    }
    
    public List<PayOrderDetails> executePayDealerDeposit(String userId, String[] orderIdArr) throws BusinessException
    {
        List<DealerJoin> dataList = dealerJoinService.listWithException(userId, orderIdArr);
        for (DealerJoin dealerJoin : dataList)
        {
            if (dealerJoin.getPaidAmount() != null && dealerJoin.getDepositTotalAmount() != null)
            {
                // 押金额度-已付押金如果大于等于0，说明已经缴过押金
                if (dealerJoin.getDepositTotalAmount().subtract(dealerJoin.getPaidAmount()).compareTo(BigDecimal.ZERO) <= 0) { throw new BusinessException(
                        CommonConst.FAIL.code, "已付过押金，无需再付"); }
            }
        }
        List<String> oldOrderIdList = Lists.newArrayList();
        List<String> newOrderIdList = Lists.newArrayList();
        List<PayOrder> newPayOrderList = Lists.newArrayList();
        for (int i = 0; i < dataList.size(); i++)
        {
            if (isNeedGenerate(dataList.get(i)))
            {
                PayOrder payOrder = parsePayOrderTransfer(dataList.get(i));
                newPayOrderList.add(payOrder);
                newOrderIdList.add(dataList.get(i).getRefrenceId());
            }
            else
            {
                oldOrderIdList.add(dataList.get(i).getRefrenceId());
            }
        }
        List<PayOrderDetails> oldPayOrderDetailList = selectPayOrder(oldOrderIdList);
        List<PayOrderDetails> newPayOrderDetailList = generatePayOrder(newOrderIdList, null, newPayOrderList, (short) 3);
        oldPayOrderDetailList.addAll(newPayOrderDetailList);
        return oldPayOrderDetailList;
    }
    
    public List<PayOrderDetails> executePayBrandService(String userId, String[] orderIdArr) throws BusinessException
    {
        List<BrandBuySerLog> dataList = brandBuySerLogService.listWithException(userId, orderIdArr);
        List<String> oldOrderIdList = Lists.newArrayList();
        List<String> newOrderIdList = Lists.newArrayList();
        List<PayOrder> newPayOrderList = Lists.newArrayList();
        for (int i = 0; i < dataList.size(); i++)
        {
            if (isNeedGenerate(dataList.get(i)))
            {
                PayOrder payOrder = parsePayOrder(dataList.get(i));
                newPayOrderList.add(payOrder);
                newOrderIdList.add(dataList.get(i).getRefrenceId());
            }
            else
            {
                oldOrderIdList.add(dataList.get(i).getRefrenceId());
            }
        }
        List<PayOrderDetails> oldPayOrderDetailList = selectPayOrder(oldOrderIdList);
        List<PayOrderDetails> newPayOrderDetailList = generatePayOrder(newOrderIdList, null, newPayOrderList, (short) 2);
        oldPayOrderDetailList.addAll(newPayOrderDetailList);
        return oldPayOrderDetailList;
    }
    
    public List<PayOrderDetails> executePayDealerOrder(String userId, String[] orderIdArr, BigDecimal[] payAmountArr) throws BusinessException
    {
        payAmountArr = null == payAmountArr ? new BigDecimal[orderIdArr.length] : payAmountArr;
        if (orderIdArr.length != payAmountArr.length) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        List<DealerOrder> dataList = dealerOrderService.listWithException(userId, orderIdArr);
        for (DealerOrder dealerOrder : dataList)
        {
            if (dealerOrder.getOrderStatus().shortValue() == DealerConstant.DealerOrder.ORDER_STATUS_CLOSED) { throw new BusinessException(CommonConst.FAIL.getCode(),
                    "订单已关闭，不能支付"); }
        }
        List<String> oldOrderIdList = Lists.newArrayList();
        List<String> newOrderIdList = Lists.newArrayList();
        List<Long> newOrderNoList = Lists.newArrayList();
        List<PayOrder> newPayOrderList = Lists.newArrayList();
        for (int i = 0; i < dataList.size(); i++)
        {
            if (isNeedGenerate(dataList.get(i)))
            {
                PayOrder payOrder = parsePayOrder(dataList.get(i), payAmountArr[i]);
                if (null != payOrder)
                {
                    newPayOrderList.add(payOrder);
                    newOrderIdList.add(dataList.get(i).getRefrenceId());
                    newOrderNoList.add(dataList.get(i).getOrderId());
                }
            }
            else
            {
                oldOrderIdList.add(dataList.get(i).getRefrenceId());
            }
        }
        if (CollectionUtils.isEmpty(newOrderIdList) && CollectionUtils.isEmpty(oldOrderIdList)) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额不能小于等于0"); }
        List<PayOrderDetails> oldPayOrderDetailList = selectPayOrder(oldOrderIdList);
        List<PayOrderDetails> newPayOrderDetailList = generatePayOrder(newOrderIdList, newOrderNoList, newPayOrderList, (short) 0);
        oldPayOrderDetailList.addAll(newPayOrderDetailList);
        return oldPayOrderDetailList;
    }
    
    public List<PayOrderDetails> executePayDealerService(String userId, String[] orderIdArr) throws BusinessException
    {
        List<DealerBuySerLog> dataList = dealerBuySerLogService.listWithException(userId, orderIdArr);
        List<String> oldOrderIdList = Lists.newArrayList();
        List<String> newOrderIdList = Lists.newArrayList();
        List<PayOrder> newPayOrderList = Lists.newArrayList();
        for (int i = 0; i < dataList.size(); i++)
        {
            if (isNeedGenerate(dataList.get(i)))
            {
                PayOrder payOrder = parsePayOrder(dataList.get(i));
                newPayOrderList.add(payOrder);
                newOrderIdList.add(dataList.get(i).getRefrenceId());
            }
            else
            {
                oldOrderIdList.add(dataList.get(i).getRefrenceId());
            }
        }
        List<PayOrderDetails> oldPayOrderDetailList = selectPayOrder(oldOrderIdList);
        List<PayOrderDetails> newPayOrderDetailList = generatePayOrder(newOrderIdList, null, newPayOrderList, (short) 1);
        oldPayOrderDetailList.addAll(newPayOrderDetailList);
        return oldPayOrderDetailList;
    }
    
    /**
     * 生成新的支付订单
     * @author 张昌苗
     */
    private List<PayOrderDetails> generatePayOrder(List<String> orderIdList, List<Long> orderNoList, List<PayOrder> payOrderList, Short orderType) throws BusinessException
    {
        if (CollectionUtils.isEmpty(orderIdList)) { return Lists.newArrayList(); }
        List<PayOrderDetails> payOrderDetailList;
        try
        {
            payOrderDetailList = payOrderApi.submitOrders(payOrderList, CommonConstant.OrderPay.PAY_MERCHANT_ID);
        }
        catch (RemotingException e)
        {
            throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
        if (CollectionUtils.isEmpty(payOrderDetailList)) { throw new BusinessException(CommonConst.ORDER_PAY_NULL); }
        if (orderIdList.size() != payOrderDetailList.size()) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付订单生成的数量与订单数量不相等"); }
        for (int i = 0; i < orderIdList.size(); i++)
        {
            DealerOrderPay dealerOrderPay = new DealerOrderPay();
            dealerOrderPay.setOrderId(orderIdList.get(i));
            dealerOrderPay.setOrderNo(CollectionUtils.isEmpty(orderNoList) ? null : orderNoList.get(i));
            dealerOrderPay.setOrderType(orderType);
            dealerOrderPay.setPayId(payOrderDetailList.get(i).getId());
            dealerOrderPay.setPayAmount(payOrderDetailList.get(i).getAmount());
            dealerOrderPay.setPointBalance(payOrderDetailList.get(i).getCommission());
            dealerOrderPay.setState(DealerConstant.DealerOrderPay.STATE_UNPAY);
            dealerOrderPay.setCreateTime(CalendarUtils.getCurrentTime());
            dealerOrderPay.setDelFlag(Boolean.FALSE);
            dealerOrderPay.setUpdateTime(CalendarUtils.getCurrentLong());
            // 新增支付记录
            dealerOrderPayService.insert(dealerOrderPay);
        }
        return payOrderDetailList;
    }
    
    /**
     * 生成支付订单对象
     * @author 张昌苗
     */
    private PayOrder parsePayOrder(DealerOrder dealerOrder, BigDecimal payAmount) throws BusinessException
    {
        if (DealerConstant.DealerOrder.ORDER_TYPE_CASH == dealerOrder.getDealerOrderType().intValue())
        {
            payAmount = OrderUtils.getUnpayAmount(dealerOrder);
        }
        else
        {
            payAmount = null == payAmount ? BigDecimal.ZERO : payAmount;
            if (payAmount.compareTo(new BigDecimal(-1)) == 0) { return null; }
            if (!OrderUtils.isFreightPaid(dealerOrder) && payAmount.compareTo(OrderUtils.getFreightAmount(dealerOrder)) < 0) { throw new BusinessException(
                    CommonConst.FAIL.getCode(), "支付金额不能小于运费的金额"); }
        }
        if (payAmount.compareTo(BigDecimal.ZERO) < 1) { return null; }
        Integer orderType = OrderUtils.parsePayType(dealerOrder.getDealerOrderType().intValue());
        BigDecimal pointBalance = BigDecimal.ZERO;
        if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType().intValue())
        {
            pointBalance = brandPointBalanceService.getPointBalance(dealerOrder, payAmount);
        }
        PayOrder payOrder = new PayOrder();
        payOrder.setTitle(MessageFormat.format("订单（{0}）", dealerOrder.getOrderId().toString()));
        payOrder.setOrderId(RPCUtils.getIdWorker());
        payOrder.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setAmount(payAmount);
        payOrder.setCommission(pointBalance);
        payOrder.setPayerId(userInfoService.executeFindPayUserId(dealerOrder.getDealerId()));
        payOrder.setPayerName(dealerOrder.getDealerName());
        /*
         * if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType())
         * {
         * payOrder.setPayeeId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
         * payOrder.setPayeeName(CommonConstant.OrderPay.PAY_MERCHANT_NAME);
         * }
         * else
         * {
         */
        payOrder.setPayeeId(userInfoService.executeFindPayUserId(dealerOrder.getBrandId()));
        payOrder.setPayeeName(dealerOrder.getBrandName());
        /* } */
        payOrder.setOrderType(orderType);
        payOrder.setGoodsType(PayOrderConst.GOODS_PRODUCTS);
        payOrder.setReturnUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/view");
        payOrder.setNotifyUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/back");
        return payOrder;
    }
    
    /**
     * 生成支付订单对象
     * @author 张昌苗
     */
    private PayOrder parsePayOrder(DealerBuySerLog dealerBuySerLog) throws BusinessException
    {
        BigDecimal payAmount = dealerBuySerLog.getBuyMoney();
        if (payAmount.compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额不能小于等于0"); }
        WebServiceItems webServiceItems = webServiceItemsService.selectByPrimaryKey(dealerBuySerLog.getServiceId());
        if (null == webServiceItems) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        PayOrder payOrder = new PayOrder();
        payOrder.setTitle(MessageFormat.format("购买({0})", webServiceItems.getServiceName()));
        payOrder.setOrderId(RPCUtils.getIdWorker());
        payOrder.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setAmount(payAmount);
        payOrder.setPayerId(userInfoService.executeFindPayUserId(dealerBuySerLog.getDealerId()));
        payOrder.setPayerName(dealerBuySerLog.getDealerName());
        payOrder.setPayeeId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setPayeeName(CommonConstant.OrderPay.PAY_MERCHANT_NAME);
        payOrder.setOrderType(PayOrderConst.ORDER_TYPE_DIRECT_PAY);
        payOrder.setGoodsType(PayOrderConst.GOODS_SERVICE);
        payOrder.setReturnUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/view");
        payOrder.setNotifyUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/back");
        return payOrder;
    }
    
    /**
     * 生成支付订单对象
     * @author 张昌苗
     */
    private PayOrder parsePayOrder(BrandBuySerLog brandBuySerLog) throws BusinessException
    {
        BigDecimal payAmount = brandBuySerLog.getBuyMoney();
        if (payAmount.compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额不能小于等于0"); }
        WebServiceItems webServiceItems = webServiceItemsService.selectByPrimaryKey(brandBuySerLog.getServiceId());
        if (null == webServiceItems) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        PayOrder payOrder = new PayOrder();
        payOrder.setTitle(MessageFormat.format("购买({0})", webServiceItems.getServiceName()));
        payOrder.setOrderId(RPCUtils.getIdWorker());
        payOrder.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setAmount(payAmount);
        payOrder.setPayerId(userInfoService.executeFindPayUserId(brandBuySerLog.getBrandId()));
        payOrder.setPayerName(brandBuySerLog.getBrandName());
        payOrder.setPayeeId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setPayeeName(CommonConstant.OrderPay.PAY_MERCHANT_NAME);
        payOrder.setOrderType(PayOrderConst.ORDER_TYPE_DIRECT_PAY);
        payOrder.setGoodsType(PayOrderConst.GOODS_SERVICE);
        payOrder.setReturnUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/view");
        payOrder.setNotifyUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/back");
        return payOrder;
    }
    
    /**
     * 生成支付订单对象
     * @author 张昌苗
     */
    private PayOrder parsePayOrder(DealerJoin dealerJoin) throws BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BigDecimal payAmount = dealerJoinService.getUnpayDepositAmount(dealerJoin);
        if (payAmount.compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额不能小于等于0"); }
        PayOrder payOrder = new PayOrder();
        payOrder.setTitle(MessageFormat.format("押金({0})", dealerJoin.getBrandsName()));
        payOrder.setOrderId(RPCUtils.getIdWorker());
        payOrder.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setAmount(payAmount);
        payOrder.setPayerId(userInfoService.executeFindPayUserId(dealerJoin.getDealerId()));
        payOrder.setPayerName(dealerJoin.getDealerName());
        // payOrder.setPayeeId(brandUsermService.executeFindPayUserId(dealerJoin.getBrandId()));
        // payOrder.setPayeeName(dealerJoin.getBrandName());
        payOrder.setPayeeId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setPayeeName(CommonConstant.OrderPay.PAY_MERCHANT_NAME);
        payOrder.setOrderType(PayOrderConst.ORDER_TYPE_DIRECT_PAY);
        payOrder.setGoodsType(PayOrderConst.GOODS_SERVICE);
        payOrder.setReturnUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/view");
        payOrder.setNotifyUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/back");
        return payOrder;
    }
    
    /**
     * 生成支付订单对象（转账，支付押金使用）
     * @author
     */
    private PayOrder parsePayOrderTransfer(DealerJoin dealerJoin) throws BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerJoin.getBrandId());
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerJoin.getDealerId());
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BigDecimal payAmount = dealerJoinService.getUnpayDepositAmount(dealerJoin);
        if (payAmount.compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额不能小于等于0"); }
        PayOrder payOrder = new PayOrder();
        payOrder.setTitle(MessageFormat.format("押金({0})", brandesInfo.getBrandsName()));
        payOrder.setOrderId(RPCUtils.getIdWorker());
        payOrder.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        payOrder.setAmount(payAmount);
        payOrder.setPayerId(userInfoService.executeFindPayUserId(dealerJoin.getDealerId()));
        payOrder.setPayerName(dealerInfo.getDealerName());
        payOrder.setPayeeId(userInfoService.executeFindPayUserId(dealerJoin.getBrandId()));
        payOrder.setPayeeName(brandInfo.getComName());
        payOrder.setOrderType(PayOrderConst.ORDER_TYPE_DIRECT_PAY_FREEZE);
        payOrder.setGoodsType(PayOrderConst.GOODS_SERVICE);
        payOrder.setReturnUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/view");
        payOrder.setNotifyUrl(ZttxConst.ZTTX_WEBURL + "/common/orderPayCallback/back");
        return payOrder;
    }
    
    /**
     * 是否需要生成新的支付订单
     * @author 张昌苗
     */
    private Boolean isNeedGenerate(DealerOrder dealerOrder)
    {
        if (DealerConstant.DealerOrder.ORDER_TYPE_CASH == dealerOrder.getDealerOrderType().intValue())
        {
            List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(dealerOrder.getRefrenceId());
            return CollectionUtils.isEmpty(unpayList);
        }
        return true;
    }
    
    /**
     * 是否需要生成新的支付订单
     * @author 张昌苗
     */
    private Boolean isNeedGenerate(DealerBuySerLog dealerBuySerLog)
    {
        List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(dealerBuySerLog.getRefrenceId());
        return CollectionUtils.isEmpty(unpayList);
    }
    
    /**
     * 是否需要生成新的支付订单
     * @author 张昌苗
     */
    private Boolean isNeedGenerate(BrandBuySerLog brandBuySerLog)
    {
        List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(brandBuySerLog.getRefrenceId());
        return CollectionUtils.isEmpty(unpayList);
    }
    
    /**
     * 是否需要生成新的支付订单
     * @author 张昌苗
     */
    private Boolean isNeedGenerate(DealerJoin dealerJoin)
    {
        List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(dealerJoin.getRefrenceId());
        return CollectionUtils.isEmpty(unpayList);
    }
    
    /**
     * 获取老的支付订单
     * @author 张昌苗
     */
    private List<PayOrderDetails> selectPayOrder(List<String> orderIdList) throws BusinessException
    {
        if (CollectionUtils.isEmpty(orderIdList)) { return Lists.newArrayList(); }
        List<PayOrderDetails> payOrderDetailsList = Lists.newArrayList();
        for (String orderId : orderIdList)
        {
            List<DealerOrderPay> unpayList = dealerOrderPayService.listUnpay(orderId);
            for (DealerOrderPay dealerOrderPay : unpayList)
            {
                PayOrderDetails payOrderDetails;
                try
                {
                    payOrderDetails = payOrderApi.queryOrder(dealerOrderPay.getPayId(), CommonConstant.OrderPay.PAY_MERCHANT_ID);
                }
                catch (RemotingException e)
                {
                    throw PayErrorConvertor.parse(PayErrorConvertor.CATE_PAY_ORDER_ERROR, e);
                }
                catch (Exception e)
                {
                    logger.error("调用支付系统接口失败：", e);
                    throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
                }
                if (null == payOrderDetails) { throw new BusinessException(CommonConst.ORDER_PAY_NULL); }
                payOrderDetailsList.add(payOrderDetails);
            }
        }
        return payOrderDetailsList;
    }
}
