/*
 * @(#)PayConst.java 2014-4-15 下午6:05:28
 * Copyright 2014 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：PayConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-15 下午6:05:28</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public enum PayConst implements EnumDescribable
{
    SUCCESS(1, "交易成功"),
    FAILURE(0, "交易失败"),
    NO_AVAILABLE_PAYPROVIDER(202, "没有可用的支付平台配置"),
    INVALID_PARAMETER_ORDER_NO(203, "订单号参数异常"),
    INVALID_PARAMETER_REQ_TYPE(204, "交易请求类型参数异常"),
    INVALID_PARAMETER_CHECK_VALUE(205, "交易数据验证值参数异常"),
    INVALID_PARAMETER_DEALER_ID(206, "交易数据dealerId参数异常"),
    INVALID_PARAMETER_BRAND_ID(207, "交易数据brandId参数异常"),
    INVALID_PARAMETER_DATETIME(208, "交易时间值参数异常"),
    INVALID_PARAMETER_BANKID(209, "银行代码参数异常"),
    INVALID_PARAMETER_AMOUNT(210, "金额参数异常"),
    INVALID_PARAMETER_RECHARGE_ID(211, "rechargeId参数异常"),
    ORDER_NO_EXISTS(212, "订单不存在"),
    INVALID_ORDER_STATE(213, "订单不存在"),
    PAYPROVIDER_NOT_AVAILABLE(214, "指定的网关不可用"),
    VALID_PARAMETER(215, "提交的参数正确"),
    INVALID_PARAMETER_BRANDID_DEALERID(216, "brandId和dealerId参数不能同时传入"),
    INVALID_PARAMETER_REQUEST_URL(216, "后台回调地址参数异常"),
    INVALID_PARAMETER_NOTICE_URL(217, "前台回调地址参数异常"),
    INVALID_PARAMETER_PARAMET(218, "调用通知参数异常"),
    PAY_BIZ_ERROR_PASSWORD(219, "支付密码不正确"),
    INVALID_PAY_PASSWORD(220, "支付密码不正确"),
    INVALID_PARAMETER_BALANCE_AMOUNT(221, "余额支付金额参数不正确");
    // 业务模块发起交易请求 参数 key
    public static final String PARAMS_PAY_REQ_TYPE                          = "payReqType";
    
    public static final String PARAMS_BANKID                                = "bankId";
    
    public static final String PARAMS_AMOUNT                                = "amount";
    
    public static final String PARAMS_BALANCE_AMOUNT                        = "balanceAmount";
    
    public static final String PARAMS_DATE                                  = "date";
    
    public static final String PARAMS_REFERENCE_ID                          = "refrenceId";
    
    public static final String PARAMS_CHECKVALUE                            = "cv";
    
    public static final String PARAMS_ORDER_NO                              = "orderNo";
    
    public static final String PARAMS_DEALER_ID                             = "dealerId";
    
    public static final String PARAMS_BRAND_ID                              = "brandId";
    
    public static final String PARAMS_RECHARGE_ID                           = "rechargeId";                                                                        // 充值单号
    
    public static final String PARAMS_REQUEST_URL                           = "requestUrl";                                                                        // 回调客户端的后台回调地址
    
    public static final String PARAMS_NOTICE_URL                            = "noticeUrl";                                                                         // 回调客户端的前台回调地址
    
    public static final String PARAMS_PARAMET                               = "paramet";                                                                           // 通知回调参数
    
    // 支付请求业务类型
    public static final String PAY_REQ_TYPE_DEALER_RECHARGE                 = "1";                                                                                 // 经销商充值
    
    public static final String PAY_REQ_TYPE_DEALER_RECHARGE_AND_PAY_BILL    = "2";                                                                                 // 经销商充值
                                                                                                                                                                    // 并支付订单
    
    public static final String PAY_REQ_TYPE_DEALER_RECHARGE_AND_PAY_FREIGHT = "3";                                                                                 // 经销商充值
                                                                                                                                                                    // 并支付订单运费
    
    public static final String PAY_REQ_TYPE_DEALER_BALANCE_PAY_FREIGHT      = "4";                                                                                 // 经销商余额
                                                                                                                                                                    // 支付订单运费
    
    public static final String PAY_REQ_TYPE_DEALER_BALANCE_PAY_BILL         = "5";                                                                                 // 经销商余额
                                                                                                                                                                    // 支付订单
    
    public static final String PAY_REQ_TYPE_BRAND_RECHARGE                  = "6";                                                                                 // 品牌商充值
    
    public static final String PAY_REQ_TYPE_BRAND_RECHARGE_AND_PAY_BILL     = "7";                                                                                 // 品牌商充值并支付
    
    public static final String PAY_REQ_TYPE_BRAND_BALANCE_PAY_BILL          = "8";                                                                                 // 品牌商
                                                                                                                                                                    // 余额
                                                                                                                                                                    // 支付订单
    
    public static final String PAY_REQ_TYPE_DEALER_REMIT                    = "9";                                                                                 // 向
                                                                                                                                                                    // 经销商账户汇款
    
    public static final String PAY_REQ_TYPE_BRAND_REMIT                     = "10";                                                                                // 向
                                                                                                                                                                    // 品牌商账户汇款
    
    public static final String PAY_REQ_TYPE_DEALER_REFUND                   = "11";                                                                                // 向
                                                                                                                                                                    // 经销商账户
                                                                                                                                                                    // 退款
    
    public static final String PAY_REQ_TYPE_BRAND_REFUND                    = "12";                                                                                // 向
                                                                                                                                                                    // 品牌商账户
                                                                                                                                                                    // 退款
    
    public static final String PAY_REQ_TYPE_DEALER_BALANCE_BUY_SERVICE      = "13";                                                                                // 经销商余额购买站内服务
    
    public static final String PAY_REQ_TYPE_DEALER_RECHARGE_BUY_SERVICE     = "15";                                                                                // 经销商充值购买站内服务
    
    public static final String PAY_REQ_TYPE_BRAND_BALANCE_BUY_SERVICE       = "14";                                                                                // 品牌商余额购买站内服务
    
    public static final String PAY_REQ_TYPE_BRAND_RECHARGE_BUY_SERVICE      = "16";                                                                                // 品牌商充值购买站内服务
    
    /**
     * TRADE_TYPE 
     *  1:充值
        2：支付
        3：收入
        4：退款
        5：提现
     **/
    public static final Short  TRADE_TYPE_RECHARGE_BANK                     = (short) 10;                                                                          // 10:银行充值
    
    public static final Short  TRADE_TYPE_RECHARGE_MANUAL                   = (short) 11;                                                                          // 11:手工充值
    
    public static final Short  TRADE_TYPE_PAY                               = 2;                                                                                   // 2：支付
    
    public static final Short  TRADE_TYPE_INCOME                            = 3;                                                                                   // 3：收入
    
    public static final Short  TRADE_TYPE_REFUND                            = 4;                                                                                   // 4：退款
    
    public static final Short  TRADE_TYPE_WITHDRAWAL                        = 5;                                                                                   // 5：提现
    
    public static final String TRADE_TYPE_NAME[][]                          = {{"10", "网银充值"}, {"11", "手工充值"}, {"2", "支付"}, {"3", "收入"}, {"4", "退款"}, {"5", "提现"}}; // 1:充值
    
    public static final String getTradeNameByTradeType(String tradeType)
    {
        for (String[] strs : TRADE_TYPE_NAME)
        {
            if (strs[0].equals(tradeType)) { return strs[1]; }
        }
        return null;
    }
    
    // recharge表内 rechargestate字段
    public static final Boolean RECHARGE_STATE_NEW                             = Boolean.FALSE; // 未成功
    
    public static final Boolean RECHARGE_STATE_PAYED                           = Boolean.TRUE; // 已成功
    
    // recharge表内 rechargetype字段
    public static final Short   RECHARGE_TYPE_RECHARGE                         = 1;
    
    public static final Short   RECHARGE_TYPE_RECHARGE_AND_PAY_BILL            = 2;
    
    public static final String  WEB_SERVICE_PLATEFORM_SERVICE_FEE_CODE         = "S001";
    
    public static final String  WEB_SERVICE_EQUIPMENT_DEPOSIT_FEE_CODE         = "S002";
    
    public static final String  WEB_SERVICE_SOFTWARE_HARDWARE_SERVICE_FEE_CODE = "S003";
    
    // 支付相关
    private PayConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public static String getMessage(Integer code)
    {
        String result = null;
        for (ClientConst c : ClientConst.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
            }
        }
        return result;
    }
    
    public Integer code;
    
    public String  message;
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getCode()
     */
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getMessage()
     */
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    /**
     * 根据交易类型查询 对应的 经销商或品牌商 金额日志 交易类型 值 tradetype
     * 因为 退款和提现未在 交易平台进行，因此不包括在此方法之类
     * @param payReqType
     * @return
     */
    public static Short getBalanceLogTradeTypeByPayReqType(String payReqType)
    {
        Short tradetype = null;
        if (payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_RECHARGE) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_RECHARGE))
        {
            tradetype = PayConst.TRADE_TYPE_RECHARGE_BANK;
        }
        else if (payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_BALANCE_PAY_BILL) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_RECHARGE_AND_PAY_BILL)
                || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_BALANCE_PAY_BILL) || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_RECHARGE_AND_PAY_BILL)
                || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_BALANCE_PAY_FREIGHT) || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_RECHARGE_AND_PAY_FREIGHT)
                || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_BALANCE_BUY_SERVICE) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_BALANCE_BUY_SERVICE)
                || payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_RECHARGE_BUY_SERVICE) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_RECHARGE_BUY_SERVICE))
        {
            tradetype = PayConst.TRADE_TYPE_PAY;
        }
        else if (payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_REMIT) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_REMIT))
        {
            tradetype = PayConst.TRADE_TYPE_INCOME;
        }
        else if (payReqType.equals(PayConst.PAY_REQ_TYPE_DEALER_REFUND) || payReqType.equals(PayConst.PAY_REQ_TYPE_BRAND_REFUND))
        {
            tradetype = PayConst.TRADE_TYPE_REFUND;
        }
        else
        {
            throw new IllegalArgumentException("payReqType 参数不在可用范围之内");
        }
        return tradetype;
    }
}
