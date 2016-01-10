/*
 * @(#)OrderPayCallbackServiceImpl.java 2014-11-28 下午1:32:26
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.pay.common.consts.PayOrderConst;
import com.zttx.pay.remoting.api.PayOrderApi;
import com.zttx.pay.remoting.exception.RemotingException;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.model.OrderPayModel;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.mapper.*;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.NumericUtils;
import com.zttx.web.utils.OrderUtils;
import com.zttx.web.utils.PayErrorConvertor;

/**
 * <p>File：OrderPayCallbackServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-28 下午1:32:26</p>
 * <p>Company: 8637.com</p>
 *
 * @author 张昌苗
 * @version 1.0
 */
@Service
public class OrderPayCallbackServiceImpl implements OrderPayCallbackService
{
    private final static Logger         logger = LoggerFactory.getLogger(OrderPayCallbackServiceImpl.class);
    
    @Autowired
    private DealerOrderPayService       dealerOrderPayService;
    
    @Autowired
    private DealerOrderService          dealerOrderService;
    
    @Autowired
    private PayOrderApi                 payOrderApi;
    
    @Autowired
    private DataDictValueService        dataDictValueService;
    
    @Autowired
    private DealerClearingService       dealerClearingService;
    
    @Autowired
    private DealerOrdersMapper          dealerOrdersMapper;
    
    @Autowired
    private DealerOrderActionMapper     dealerOrderActionMapper;
    
    @Autowired
    private OrderPayRecordMapper        orderPayRecordMapper;
    
    @Autowired
    private DealerCountService          dealerCountService;
    
    @Autowired
    private BrandCountService           brandCountService;
    
    @Autowired
    private DealerBuyServiceMapper      dealerBuyServiceMapper;
    
    @Autowired
    private WebServiceItemsMapper       webServiceItemsMapper;
    
    @Autowired
    private DealerBuySerLogService      dealerBuySerLogService;
    
    @Autowired
    private DealerBuySerLogMapper       dealerBuySerLogMapper;
    
    @Autowired
    private DealerInfoMapper            dealerInfoMapper;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private DealerDepositMapper         dealerDepositMapper;
    
    @Autowired
    private DealerCountMapper           dealerCountMapper;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Override
    public void executeDealWith(Long payId, Integer state, BigDecimal amount) throws BusinessException
    {
        try
        {
            if (PayOrderConst.STATE_UNPAY == state.intValue())
            {
                executeClosePay(payId);
            }
            else if (PayOrderConst.STATE_PAYED == state.intValue() || PayOrderConst.STATE_COMPLETED == state.intValue())
            {
                executePayComplete(payId, amount); // 付款完成回调
            }
            else
            {
                logger.info("订单{}状态{}不正确，不做处理", payId, state);
            }
        }
        catch (BusinessException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            logger.error("支付ID（{}）存在问题：", payId, e);
            throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
        }
    }
    
    private DealerOrderPay executeClosePay(Long payId) throws BusinessException
    {
        // 如果不存在该订单，去支付系统那边关闭订单
        DealerOrderPay dealerOrderPay = dealerOrderPayService.findByIdWithException(payId);
        if (null != dealerOrderPay)
        {
            return null;
        }
        try
        {
            logger.info("开始关闭支付订单：{}", payId);
            payOrderApi.closeOrder(payId, CommonConstant.OrderPay.PAY_MERCHANT_ID);
            logger.info("关闭支付订单成功：{}", payId);
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
        return dealerOrderPay;
    }
    
    private void executePayComplete(Long payId, BigDecimal amount) throws BusinessException
    {
        DealerOrderPay dealerOrderPay = dealerOrderPayService.findByIdWithException(payId);
        if (DealerConstant.DealerOrderPay.STATE_UNPAY != dealerOrderPay.getState().shortValue())
        {
            logger.info("支付ID（{}）的状态，已改变，不做处理", payId);
            return;
        }
        if (dealerOrderPay.getPayAmount().compareTo(amount) != 0)
        {
            logger.error("支付ID（{}）的金额不正确，不做处理", payId);
            return;
        }
        switch (dealerOrderPay.getOrderType())
        {
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_ORDER:
                dealWithDealerOrder2(dealerOrderPay); // 订单
                break;
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_SERVICE:
                dealWithDealerService(dealerOrderPay); // 经销商服务费
                break;
            case DealerConstant.DealerOrderPay.ORDER_TYPE_BRAND_SERVICE:
                dealWithBrandService(dealerOrderPay);
                break;
            case DealerConstant.DealerOrderPay.ORDER_TYPE_DEALER_DEPOSIT:
                dealWithDealerDeposit(dealerOrderPay); // 押金
                break;
            default:
                throw new BusinessException(CommonConst.FAIL.getCode(), "类型未支持");
        }
    }
    
    private DealerOrderPay dealWithDealerDeposit(DealerOrderPay dealerOrderPay) throws BusinessException
    {
        // todo 待验证 为什么用orderId
        /* DealerJoin dealerJoin = dealerJoinService.findWithException(dealerOrderPay.getOrderId()); */
        DealerJoin dealerJoin = dealerJoinService.selectByPrimaryKey(dealerOrderPay.getOrderId());
        // 是否为第一次付押金
        Boolean isFirstPaidDeposit = isFirstPaidDeposit(dealerJoin);
        // 修改押金
        dealerJoinService.savePaidDepositAmount(dealerJoin, dealerOrderPay.getPayAmount());
        // 新增押金缴纳记录
        saveDealerDeposit(dealerJoin, dealerOrderPay);
        if (isFirstPaidDeposit)
        {
            // 修改工厂店已缴押金数量
            dealerCountMapper.updateDealerCount(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_FACTORYHASPAID, dealerJoin.getDealerId(), 1);
        }
        // 修改支付状态
        return dealerOrderPayService.executePaySuccess(dealerOrderPay.getPayId());
    }
    
    public void saveDealerDeposit(DealerJoin dealerJoin, DealerOrderPay dealerOrderPay) throws BusinessException
    {
        DealerDeposit obj = new DealerDeposit();
        obj.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        obj.setDealerId(dealerJoin.getDealerId());
        obj.setBrandId(dealerJoin.getBrandId());
        obj.setPaidAmount(dealerOrderPay.getPayAmount());
        obj.setPaidTime(CalendarUtils.getCurrentLong());
        dealerDepositMapper.insert(obj);
    }
    
    private Boolean isFirstPaidDeposit(DealerJoin dealerJoin)
    {
        if (null == dealerJoin.getPaidAmount() || BigDecimal.ZERO.equals(dealerJoin.getPaidAmount())) { return true; }
        return false;
    }
    
    private DealerOrderPay dealWithBrandService(DealerOrderPay dealerOrderPay) throws BusinessException
    {
        // 品牌商目前没有购买服务功能
        return null;
    }
    
    // 同步结算平台订单状态
    private DealerOrderPay dealWithDealerOrder2(DealerOrderPay dealerOrderPay) throws BusinessException
    {
        DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(dealerOrderPay.getOrderId());
        if (null == dealerOrder) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 修改订单
        OrderPayModel orderPayModel = updateOrder(dealerOrder, dealerOrderPay);
        // 更新库存
        if (orderPayModel.getIsFirstPay())
        {
            updateStore(dealerOrder);
        }
        // 新增订单操作记录
        updateAction2(dealerOrder, dealerOrderPay, orderPayModel);
        // 更新统计数
        updateCount(dealerOrder);
        // 修改支付状态
        return dealerOrderPayService.executePaySuccess(dealerOrderPay.getPayId(), dealerOrder);
    }
    
    private void updateStore(DealerOrder dealerOrder) throws BusinessException
    {
        List<DealerOrders> dealerOrdersList = dealerOrdersMapper.selectDealerOrders(dealerOrder.getRefrenceId(), dealerOrder.getDealerId());
        updateBrandProductStoreByDealerOrder(dealerOrdersList);
    }
    
    /**
     * 根据 订单详情里各属性的购买量 更新 对应产品属性里的 库存量  并且根据实时库存情况修改 库存预警、库存缺货
     * @param dealerOrdersList
     */
    @SuppressWarnings("unchecked")
    public void updateBrandProductStoreByDealerOrder(List<DealerOrders> dealerOrdersList) throws BusinessException
    {
        /**调用商品中心接口**/
        // 改变sku库存同时,改变产品的库存
        for (DealerOrders dos : dealerOrdersList)
        {
            ProductSku productSku = productSkuInfoDubboConsumer.findProductSku(dos.getProductSkuId());
            Integer quantity = productSku.getQuantity() - dos.getQuantity();
            productSku.setQuantity(quantity);
            productSkuInfoDubboConsumer.updateSimple(productSku);
            ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(dos.getProductId());
            ProductExtInfo productExtInfo = productBaseInfo.getProductExtInfo();
            productExtInfo.setProductStore(productExtInfo.getProductStore() - dos.getQuantity());
            productInfoDubboConsumer.updateProductExtInfoSimple(productExtInfo);
        }
    }
    
    private OrderPayModel updateOrder(DealerOrder dealerOrder, DealerOrderPay dealerOrderPay) throws BusinessException
    {
        // 是否第一次付款
        Boolean isFirstPay = OrderUtils.isFirstPay(dealerOrder);
        BigDecimal curPayAmount = dealerOrderPay.getPayAmount(); // 本次支付的金额
        BigDecimal curFreightPaidAmount = BigDecimal.ZERO; // 本次支付的运费金额
        BigDecimal curGoodsPaidAmount = BigDecimal.ZERO; // 本次支付的货款金额
        // 当未付运费，运费已设置且运费的金额小于等于支付的金额时，优先支付运费，剩下的支付金额当货款支付
        // 支付运费后，修改订单运费支付状态
        if (!OrderUtils.isFreightPaid(dealerOrder) && null != dealerOrder.getAdjustFreight() && OrderUtils.getFreightAmount(dealerOrder).compareTo(curPayAmount) < 1)
        {
            curFreightPaidAmount = OrderUtils.getFreightAmount(dealerOrder);
            curGoodsPaidAmount = curPayAmount.subtract(curFreightPaidAmount);
            dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED);
        }
        else
        {
            curGoodsPaidAmount = curPayAmount;
        }
        // 修改已支付的货款金额
        BigDecimal oldGoodsPaidAmount = OrderUtils.getGoodsPaidAmount(dealerOrder); // 原已付货款金额
        BigDecimal newGoodsPaidAmount = oldGoodsPaidAmount.add(curGoodsPaidAmount); // 新已付货款金额
        dealerOrder.setPayment(newGoodsPaidAmount);
        // 当货款+优惠的金额小于等于已支付的金额时，修改货款支付状态
        BigDecimal goodsAmount = OrderUtils.getGoodsAmount(dealerOrder); // 订单货款金额
        /**修改订单支付状态 **/
        if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CASH) // 现款订单
        {
            if (goodsAmount.compareTo(newGoodsPaidAmount) < 1)
            {
                dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
            }
            else if (newGoodsPaidAmount.compareTo(BigDecimal.ZERO) > 0)
            {
                dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_PART);
            }
        }
        else
        // 授信订单
        {
            DealerJoin join = dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
            if (curGoodsPaidAmount.compareTo(BigDecimal.ZERO) == 0) // 当前实际付款为0，说明货款未超过剩余授信金额,使用授信金额付款
            {
                dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
                // 更新已用授信金额
                join.setCreditCurrent(join.getCreditCurrent().add(goodsAmount));
            }
            else
            {
                BigDecimal needPay = goodsAmount; // 本次订单需要支付的货款现款金额
                BigDecimal surplusCreditAmount = join.getCreditAmount().subtract(join.getCreditCurrent()); // 支付前剩余授信额度
                // 支付现款大于0且剩余授信额度大于0时说明有部分货款使用了授信
                if(surplusCreditAmount.compareTo(BigDecimal.ZERO) >= 0)
                {
                    needPay = goodsAmount.subtract(surplusCreditAmount);
                    // 支付现款大于0时说明授信金额全部用完,则设置当前已用授信额度为授信额度满额
                    join.setCreditCurrent(join.getCreditAmount());
                    dealerJoinService.updateByPrimaryKey(join);
                }
                // 设置订单支付状态
                if (curGoodsPaidAmount.compareTo(needPay) < 0) {
                    dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_PART);
                } else {
                    dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
                }
            }
        }
        // 修改订单状态，把等待付款的订单改为待发货状态
        if (DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY == dealerOrder.getOrderStatus().shortValue())
        {
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER);
        }
        // 更新订单的 下次执行时间
        Boolean isPaid = OrderUtils.isPaid(dealerOrder);
        if (isPaid && !dealerOrder.getIsAdvancePayment())
        {
            DataDictValue ddv = dataDictValueService.findDictValue("orderFixedDays", "orderNoShipRefund");
            if (StringUtils.isNotBlank(ddv.getDictValue()))
            {
                dealerOrder.setOutActTime(CalendarUtils.addDay(CalendarUtils.getCurrentLong(), Integer.parseInt(ddv.getDictValue())));
            }
        }
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        BigDecimal adjustPrice = dealerOrder.getAdjustPrice();
        adjustPrice = adjustPrice == null ? BigDecimal.ZERO : adjustPrice; // 现款订单可能有修改订单总价的优惠
        dealerOrder.setProductPriceWhenPay(dealerOrder.getProductPrice().add(adjustPrice));// 将付款时的订单总货款存起来
        dealerOrderService.updateByPrimaryKey(dealerOrder);
        if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType().shortValue())
        {
            dealerOrderService.addPointBalance(dealerOrder, dealerOrderPay.getPointBalance());
            dealerClearingService.fixDealerClearingNoPayedBy(dealerOrder.getDealerId(), dealerOrder.getBrandId());
        }
        OrderPayModel orderPayModel = new OrderPayModel();
        orderPayModel.setCurPayAmount(curPayAmount);
        orderPayModel.setIsFreightPaid(OrderUtils.isFreightPaid(dealerOrder));
        orderPayModel.setCurFreightPaidAmount(curFreightPaidAmount);
        orderPayModel.setIsGoodsPaid(OrderUtils.isGoodsPaid(dealerOrder));
        orderPayModel.setCurGoodsPaidAmount(curGoodsPaidAmount);
        orderPayModel.setIsFirstPay(isFirstPay);
        return orderPayModel;
    }
    
    //
    private void updateAction2(DealerOrder dealerOrder, DealerOrderPay dealerOrderPay, OrderPayModel orderPayModel)
    {
        DealerOrderAction dealerOrderAction = makeDealerOrderAction(dealerOrder, orderPayModel);
        dealerOrderActionMapper.insert(dealerOrderAction);
        OrderPayRecord orderPayRecord = makeOrderPayRecord(dealerOrder, dealerOrderPay, orderPayModel);
        orderPayRecordMapper.insert(orderPayRecord);
        // todo UserOperationLog
        /*
         * UserOperationLog userOperationLog = new UserOperationLog();
         * userOperationLog.setObjectId(dealerOrderAction.getOrderId());
         * userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
         * userOperationLog.setEvent(dealerOrderAction.getActContent());
         * userOperationLogService.addUserOperationLog(null, userOperationLog);
         */
    }
    
    private void updateCount(DealerOrder dealerOrder) throws BusinessException
    {
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT);
        dealerCountService.modifyDealerCount(dealerOrder.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT);
        brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
    }
    
    private DealerOrderPay dealWithDealerService(DealerOrderPay dealerOrderPay) throws BusinessException
    {
        // todo 待验证 为什么用orderId
        /* DealerBuySerLog dbsl = dealerBuySerLogService.findWithException(dealerOrderPay.getOrderId()); */
        DealerBuySerLog dbsl = dealerBuySerLogService.selectByPrimaryKey(dealerOrderPay.getOrderId());
        // 判断是否已经购买该服务
        DealerBuyService dbs = dealerBuyServiceMapper.findBy(dbsl.getDealerId(), dbsl.getServiceId());
        if (dbs != null)
        {
            // 更新
            if (DealerConstant.DealerBuySerLog.CHARGE_TYPE_RENEW == dbsl.getServicerCate().shortValue())
            {
                // 服务延期
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(dbs.getEndTime());
                cal.add(Calendar.YEAR, dbsl.getBuyNum());
                Long l = cal.getTimeInMillis();
                dbs.setEndTime(l);
            }
            else
            {
                // 增加数量
                /* dbs.setBuyMoney(NumericUtils.add(dbs.getBuyTime(), dbsl.getBuyMoney())); */
                BigDecimal buyTime = new BigDecimal(dbs.getBuyTime());
                dbs.setBuyMoney(dbsl.getBuyMoney().add(buyTime));
            }
        }
        else
        {
            // 新建
            dbs = makeDealerBuyService(dbsl.getDealerId(), dbsl.getServiceId(), dbsl.getServicerCate(), dbsl.getBuyNum(), dbsl.getBuyTime(), dbsl.getBuyMoney(),
                    dbsl.getChargType());
        }
        dbsl.setBuyState(Short.valueOf("2"));
        dealerBuySerLogMapper.updateByPrimaryKey(dbsl);
        if (dbs.getRefrenceId() != null && !"".equals(dbs.getRefrenceId()))
        {
            dealerBuyServiceMapper.updateByPrimaryKey(dbs);
        }
        else
        {
            dbs.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerBuyServiceMapper.insert(dbs);
        }
        WebServiceItems item = webServiceItemsMapper.selectByPrimaryKey(dbsl.getServiceId());
        if (item != null)
        {
            item.setBuyNum((item.getBuyNum() != null ? item.getBuyNum() : 0) + 1);
            webServiceItemsMapper.updateByPrimaryKey(item);
        }
        // dealerinfo 有效期
        if (PayConst.WEB_SERVICE_PLATEFORM_SERVICE_FEE_CODE.equals(dbsl.getServiceId()))
        {
            dealerInfoMapper.updateBeginTimeAndEndTime(dbsl.getDealerId(), dbs.getBeginTime(), dbs.getEndTime());
        }
        // 修改支付状态
        return dealerOrderPayService.executePaySuccess(dealerOrderPay.getPayId());
    }
    
    public DealerBuyService makeDealerBuyService(String dealerId, String serviceId, Short servicerCate, Integer buyNums, Long buyTime, BigDecimal buyMoney, Short chargType)
    {
        DealerBuyService dbs = new DealerBuyService();
        dbs.setBuyTime(buyTime);
        dbs.setBuyMoney(buyMoney);
        dbs.setBeginTime(CalendarUtils.getCurrentLong());
        dbs.setDealerId(dealerId);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, buyNums);
        dbs.setChargType(chargType);
        dbs.setEndTime(cal.getTimeInMillis());
        dbs.setServiceId(serviceId);
        dbs.setServicerCate(servicerCate);
        return dbs;
    }
    
    private DealerOrderAction makeDealerOrderAction(DealerOrder dealerOrder, OrderPayModel orderPayModel)
    {
        DealerOrderAction mas = new DealerOrderAction();
        mas.setActCode("orderpay");
        StringBuffer contentBuffer = new StringBuffer();
        /*
         * if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType().shortValue()) {
         * contentBuffer.append("支付：" + NumericUtils.roundCurrency(orderPayModel.getCurPayAmount()).toString() + "元");
         * } else {
         */
        if (orderPayModel.getCurGoodsPaidAmount().compareTo(BigDecimal.ZERO) > 0)
        {
            contentBuffer.append("支付" + DealerConstant.DealerOrderAction.FEE_NAME_GOODS + "："
                    + NumericUtils.roundCurrency(orderPayModel.getCurGoodsPaidAmount()).toString() + "元");
            if (orderPayModel.getIsGoodsPaid())
            {
                contentBuffer.append("," + DealerConstant.DealerOrderAction.FEE_NAME_GOODS + "已付清");
            }
        }
        if (orderPayModel.getCurFreightPaidAmount().compareTo(BigDecimal.ZERO) > 0)
        {
            contentBuffer.append(",");
            short frePayType = dealerOrder.getFrePayType(); // 1、快递 2、物流 3、快递到付 4、物流到付 60、包邮
            String frePayTypeName = null;
            switch (frePayType)
            {
                case 1:
                    frePayTypeName = DealerConstant.DealerOrderAction.FEE_NAME_EXPRESS;
                    break;
                case 2:
                    frePayTypeName = DealerConstant.DealerOrderAction.FEE_NAME_LOGISTICS;
                    break;
                case 3:
                    frePayTypeName = DealerConstant.DealerOrderAction.FEE_NAME_EXPRESS_TO_PAY;
                    break;
                case 4:
                    frePayTypeName = DealerConstant.DealerOrderAction.FEE_NAME_LOGISTICS_TO_PAY;
                    break;
                case 60:
                    frePayTypeName = DealerConstant.DealerOrderAction.FEE_NAME_FREE;
            }
            if (frePayType == 1 || frePayType == 2)
            {
                contentBuffer.append("支付" + frePayTypeName + "：" + NumericUtils.roundCurrency(orderPayModel.getCurFreightPaidAmount()).toString() + "元");
                if (orderPayModel.getIsFreightPaid())
                {
                    contentBuffer.append("," + DealerConstant.DealerOrderAction.FEE_NAME_FREIGHT + "已付清");
                }
            }
            if (frePayType == 3 || frePayType == 4 || frePayType == 60)
            {
                contentBuffer.append(frePayTypeName);
            }
        }
        // }
        mas.setActContent(contentBuffer.toString());
        mas.setActName("付款");
        mas.setCreateTime(CalendarUtils.getCurrentLong());
        mas.setOrderId(dealerOrder.getRefrenceId());
        mas.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        mas.setUserId(dealerOrder.getDealerId());
        mas.setUserName(dealerOrder.getDealerName());
        return mas;
    }
    
    private OrderPayRecord makeOrderPayRecord(DealerOrder dealerOrder, DealerOrderPay dealerOrderPay, OrderPayModel orderPayModel)
    {
        OrderPayRecord opr = new OrderPayRecord();
        opr.setCreateTime(CalendarUtils.getCurrentLong());
        opr.setDealerId(dealerOrder.getDealerId());
        opr.setDealerName(dealerOrder.getDealerName());
        opr.setOrderId(dealerOrder.getRefrenceId());
        opr.setPayAmount(orderPayModel.getCurPayAmount());
        opr.setRecharegeId(dealerOrderPay.getRefrenceId());
        opr.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        return opr;
    }
    
    public final static BusinessException parse(int cate, RemotingException e)
    {
        return new BusinessException(cate + e.getCode(), e.getMessage());
    }
}
