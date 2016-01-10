/*
 * @(#)OrderUtils.java 2014-11-29 下午2:22:04
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.zttx.pay.common.consts.PayOrderConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerOrder;

/**
 * <p>File：OrderUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-29 下午2:22:04</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class OrderUtils
{
    /**
     * 获取货款金额（含优惠金额）
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getGoodsAmount(DealerOrder dealerOrder)
    {
        BigDecimal goodsAmount = dealerOrder.getProductPrice();
        goodsAmount = goodsAmount.add(null == dealerOrder.getAdjustPrice() ? BigDecimal.ZERO : dealerOrder.getAdjustPrice());
        return goodsAmount;
    }
    
    /**
     * 获取运费金额（未设置运费则为0）
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getFreightAmount(DealerOrder dealerOrder)
    {
        BigDecimal freightAmount = null == dealerOrder.getAdjustFreight() ? BigDecimal.ZERO : dealerOrder.getAdjustFreight();
        return freightAmount;
    }
    
    /**
     * 获取已支付的货款金额
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getGoodsPaidAmount(DealerOrder dealerOrder)
    {
        BigDecimal goodsPaidAmount = null == dealerOrder.getPayment() ? BigDecimal.ZERO : dealerOrder.getPayment();
        return goodsPaidAmount;
    }
    
    /**
     * 获取已支付的运费金额
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getFreightPaidAmount(DealerOrder dealerOrder)
    {
        return isFreightPaid(dealerOrder) ? getFreightAmount(dealerOrder) : BigDecimal.ZERO;
    }
    
    /**
     * 获取订单的总金额（货款+优惠+运费）
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getTotalAmount(DealerOrder dealerOrder)
    {
        BigDecimal goodsAmount = getGoodsAmount(dealerOrder);
        BigDecimal freightAmount = getFreightAmount(dealerOrder);
        return goodsAmount.add(freightAmount);
    }
    
    public final static BigDecimal getPaidAmount(DealerOrder dealerOrder)
    {
        BigDecimal goodsPaidAmount = getGoodsPaidAmount(dealerOrder);
        BigDecimal freightPaidAmount = getFreightPaidAmount(dealerOrder);
        return goodsPaidAmount.add(freightPaidAmount);
    }
    
    /**
     * 获取未付的金额（包含未付的货款和未付的运费）
     * @param dealerOrder
     * @return
     * @author 张昌苗
     */
    public final static BigDecimal getUnpayAmount(DealerOrder dealerOrder)
    {
        BigDecimal totalAmount = getTotalAmount(dealerOrder);
        BigDecimal paidAmount = getPaidAmount(dealerOrder);
        BigDecimal unpayAmount = totalAmount.subtract(paidAmount);
        return unpayAmount.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : unpayAmount;
    }
    
    /**
     * 是否第一次付款,货款和运费都是未支付状态则为第一次付款
     * @param dealerOrder
     * @return true:是,false:否
     * @author 张昌苗
     */
    public final static Boolean isFirstPay(DealerOrder dealerOrder)
    {
        // if(DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY != dealerOrder.getFrePayState().shortValue())
        // {
        // return false;
        // }
        if (DealerConstant.DealerOrder.PAY_STATE_UNPAY != dealerOrder.getPayState().shortValue()) { return false; }
        return true;
    }
    
    /**
     * 订单是否已付清
     * @param dealerOrder
     * @return true:是,false:否
     * @author 张昌苗
     */
    public final static Boolean isPaid(DealerOrder dealerOrder)
    {
        if (DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED != dealerOrder.getFrePayState().shortValue()) { return false; }
        if (DealerConstant.DealerOrder.PAY_STATE_ALL != dealerOrder.getPayState().shortValue()) { return false; }
        return true;
    }
    
    /**
     * 运费是否已付清
     * @param dealerOrder
     * @return true:是,false:否
     * @author 张昌苗
     */
    public final static Boolean isFreightPaid(DealerOrder dealerOrder)
    {
        if (DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED != dealerOrder.getFrePayState().shortValue()) { return false; }
        return true;
    }
    
    /**
     * 货款是否已付清
     * @param dealerOrder
     * @return true:是,false:否
     * @author 张昌苗
     */
    public final static Boolean isGoodsPaid(DealerOrder dealerOrder)
    {
        if (DealerConstant.DealerOrder.PAY_STATE_ALL != dealerOrder.getPayState().shortValue()) { return false; }
        return true;
    }
    
    /**
     * 把订单类型转化成支付平台的支付类型
     * @param orderType
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public final static Integer parsePayType(Integer orderType) throws BusinessException
    {
        /*switch (orderType.intValue())
        {
            case DealerConstant.DealerOrder.ORDER_TYPE_CASH:
                return PayOrderConst.ORDER_TYPE_GUARANTEE;
            case DealerConstant.DealerOrder.ORDER_TYPE_CREDIT:
                return PayOrderConst.ORDER_TYPE_DIRECT_PAY;
        }
        throw new BusinessException(CommonConst.PARAMS_VALID_ERR);*/

        //交易平台2.0版业务需求:现款和授信都是担保交易
        return PayOrderConst.ORDER_TYPE_GUARANTEE;
    }
    
    /**
     * 获取订单ID
     * @param dealerOrderList
     * @return
     * @author 张昌苗
     */
    public final static String[] getOrderIdArr(List<DealerOrder> dealerOrderList)
    {
        if (CollectionUtils.isEmpty(dealerOrderList)) { return null; }
        String[] orderIds = new String[dealerOrderList.size()];
        for (int i = 0; i < orderIds.length; i++)
        {
            orderIds[i] = dealerOrderList.get(i).getRefrenceId();
        }
        return orderIds;
    }
}
