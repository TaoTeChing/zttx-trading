/*
 * @(#)TradeDetailsConst 2014/4/25 10:20
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：TradeDetailsConst</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/25 10:20</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class TradeDetailsConst
{
    /**
     * 订单支付
     */
    public static final short TRADE_TYPE_ORDER           = 1;
    
    /**
     * 短信支付
     */
    public static final short TRADE_TYPE_SMS             = 2;
    
    /**
     * 支付状态
    0：未支付
    1：部分支付
    2：全部支付
     */
    public static final Short PAY_STATE_OUTSTANDING      = 0; // 未付款
    
    public static final Short PAY_STATE_PART             = 1; // 部分支付
    
    public static final Short PAY_STATE_ALL              = 2; // 全部支付
    
    /**
     * 交易状态
            1等待付款
            2 等待发货
            3:部分发货
            4 等待确认收货
            9 交易成功
            10 交易关闭
     */
    public static final Short TRADE_STATE_TO_PAY         = 1; // 未付款等待付款
    
    public static final Short TRADE_STATE_TO_DELIVER     = 2; // 等待发货
    
    public static final Short TRADE_STATE_OUTSTANDING    = 3; // 部分发货
    
    public static final Short TRADE_STATE_PART_DELIVERED = 4; // 等待收货
    
    public static final Short TRADE_STATE_TO_COMPLETED   = 9; // 交易完成
    
    public static final Short TRADE_STATE_CLOSED         = 10; // 交易关闭
    
    public static final Short TRADE_STATE_REFUND         = 11; // 退款中
}
