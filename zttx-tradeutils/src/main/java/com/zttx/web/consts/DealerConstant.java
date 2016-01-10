/*
 * @(#)DealerResult.java 2014-5-1 下午4:01:53
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：DealerResult.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-1 下午4:01:53</p>
 * <p>Company: 8637.com</p>
 * @author 吕海斌
 * @version 1.0
 */
public class DealerConstant
{
    public static final int SUCCESS = 100000;

    public class userType
    {
        public static final short DEALER_TYPE = 1; // 经销商用户类型 1
    }

    public class productState // 经销商授权产品库 中产品与经销商之间的 状态
    {
        public static final short PRODUCT_INVALID = 0; // 产品失效

        public static final short PRODUCT_DOWN    = 1; // 产品下架

        public static final short PRODUCT_NORIGHT = 2; // 产品无权购买(未合作)

        public static final short PRODUCT_CASH    = 3; // 产品现款购买权

        public static final short PRODUCT_CREDIT  = 4; // 产品授信购买权

        public static final short PRODUCT_POINT   = 5; // 产品返点购买权
    }

    public class DealerUserm
    {
        // userState 帐号状态
        public static final short USERSTATE_0 = 0; // 0:等待审核

        public static final short USERSTATE_1 = 1; // 1:审核通过

        public static final short USERSTATE_2 = 2; // 2:冻结

        public static final short USERSTATE_3 = 3; // 3:审核不通过
    }

    public class DealerApply
    {
        /**审批未处理*/
        public static final short UNTREATED            = 0;

        /**审批通过*/
        public static final short APPROVED             = 1;

        /**审批不通过*/
        public static final short REJECT               = 2;

        // DealerApply:auditState
        // 未审核
        public static final short AUDIT_STATE_UNVERFIY = 0;

        // 审核通过
        public static final short AUDIT_STATE_SUCCESS  = 1;

        // 审核失败
        public static final short AUDIT_STATE_FAILURE  = 2;

        // 申请处理的返回结果
        // 申请成功
        public final static int   APPLY_RESULT_SUCCESS = 0;

        // 正在审核
        public final static int   APPLY_RESULT_PROCESS = 1;

        // 已经通过
        public final static int   APPLY_RESULT_HADPASS = 2;
    }

    public class DealerJoin
    {
        public static final short COOPERATED               = 1; // 已合作状态

        public static final short TERMINATED               = 2; // 已终止状态

        public static final short INVITED                  = 3; // 已邀请状态

        // joinSource（加盟来源）
        public static final short JOIN_SOURCE_WEB_APPLY    = 1; // 平台线上申请加盟

        public static final short JOIN_SOURCE_WEB_INVITE   = 2; // 平台线上邀请加盟

        public static final short JOIN_SOURCE_OFFLINE      = 3; // 平台线下加盟

        public static final short JOIN_SOURCE_RELATIONSHIP = 4; // 关系户加盟

        // 1：正在合作，
        public final static short COOPERATION              = 1;

        // 2：品牌商中止合作
        public final static short STOP_COOPERATION         = 2;

        // 3: 经销商 中止合作
        public final static short STOP_COOPERATION_DEALER  = 3;

        // 5我撤消的申请
        public final static short DIS_APPLY                = 5;

        public static final short JOINFORM_CASH = 0;          //普通加盟
        public static final short JOINFORM_CREDIT = 1;        //授信加盟

    }

    public class DealerShoper
    {

        public final static short PRODUCTTYPE_MIN = DealerShoper.PRODUCTTYPE_CASH;

        public final static short PRODUCTTYPE_CASH   = 0; // 现款

        public final static short PRODUCTTYPE_CREDIT = 1; // 授信

        public final static short PRODUCTTYPE_SAM    = 2; // 拿样

        public final static short PRODUCTTYPE_POINT  = 3; // 返点

        public final static short PRODUCTTYPE_MAX = DealerShoper.PRODUCTTYPE_POINT;
    }

    public class DealerRefund
    {
        // refundState 退款状态
        public static final short WAIT_HANDLE                 = 1; // 申请退款等待处理

        public static final short WAIT_SHIP                   = 2; // 同意退货等待发货

        public static final short SHIPED                      = 3; // 退货已发货（经销商发货）

        public static final short NOT_REFUND                  = 4; // 拒绝退款（品牌商拒绝退款）

        public static final short NOT_RETURN                  = 5; // 拒绝退货（品牌商拒绝退货）

        public static final short CLOSS_REFUND                = 6; // 退款关闭（到期未处理自动关闭，或者客服关闭）

        public static final short CANCEL_REFUND               = 7; // 取消退款（经销商取消退款）

        public static final short AGREE_REFUND                = 9; // 同意退款（品牌商同意退款）

        public static final short AGREE_RETURN_AND_REFUND     = 10; // 同意退货(品牌商确认收货）

        public static final short PAYMENT_REACH               = 1; // 欠收抵应付

        public static final short PAYMENT_GENERAL             = 0; // 直接支付

        public static final short PAYMENT_REACH_AND_BALANCE   = 2; // 欠收抵应付和余额支付并存

        public static final short SerProStatus_involved       = 1; // 1：客服介入处理中

        public static final short SerProStatus_dispute        = 2; // 2：纠纷处理中，

        public static final short SerProStatus_finish         = 3; // 3：处理完毕

        public static final short SerProStatus_dispute_finish = 4; // 4:纠纷关闭

        public static final short SerProStatus_pending        = 5; // 5:等待客服介入中
    }

    public class DealerOrderAction
    {
        public static final String CLOSE                     = "closeOrder";

        public static final String CLOSE_NAME                = "关闭订单";

        public static final String CONFRIMGOODS              = "confrimgoods";

        public static final String CONFRIMGOODS_NAME         = "确认收货";

        public static final String APPLYREFUND               = "applyrefund";

        public static final String APPLYREFUND_NAME          = "申请退款";

        public static final String SHIPGOODS                 = "shipgoods";

        public static final String SHIPGOODS_NAME            = "发货";

        public static final String CLOSESENDGOODS            = "closesendgoods";

        public static final String CLOSESENDGOODS_NAME       = "关闭发货";

        public static final String ORDERPAY                  = "orderpay";

        public static final String ORDERPAY_NAME             = "付款";

        public static final String CREATEORDER               = "createorder";

        public static final String CREATEORDER_NAME          = "创建订单";

        public static final String CLOSE_TXT                 = "关闭原因：";

        public static final String AGREEREFUND               = "agreerefund";

        public static final String AGREEREFUND_NAME          = "同意退款";

        public static final String LOGIS_TIME_OUT            = "logisTimeout";

        public static final String LOGIS_TIME_OUT_NAME       = "终端商退货物流超时";

        // 支付相关
        public static final String FEE_NAME_GOODS            = "货款";

        public static final String FEE_NAME_FREIGHT          = "运费";            // 已被 详细分为下面的的几个 (快递 物流 快递到付 物流到付 包邮)

        public static final String FEE_NAME_EXPRESS          = "快递运费";

        public static final String FEE_NAME_LOGISTICS        = "物流运费";

        public static final String FEE_NAME_EXPRESS_TO_PAY   = "快递到付";

        public static final String FEE_NAME_LOGISTICS_TO_PAY = "物流到付";

        public static final String FEE_NAME_FREE             = "包邮";
    }

    public class DealerOrder
    {
        public static final double min                         = 0.01;

        public static final double max                         = 100000000;

        /* 订单状态 */
        public static final short  ORDER_STATUS_TO_PAY         = 1;        // 等待付款

        public static final short  ORDER_STATUS_TO_DELIVER     = 2;        // 等待发货

        public static final short  ORDER_STATUS_PART_DELIVERED = 3;        // 部分发货

        public static final short  ORDER_STATUS_TO_RECEIVE     = 4;        // 等待收货

        public static final short  ORDER_STATUS_COMPLETED      = 9;        // 交易完成

        public static final short  ORDER_STATUS_CLOSED         = 10;       // 交易关闭

        public static final short  ORDER_STATUS_REFUND         = 20;       // 退款中

        // /* serProStatus 纠纷处理状态 *1：客服介入处理中，2：纠纷处理中，3：处理完毕,4:纠纷关闭5:等待客服介入中
        public static final short  SER_STATUS_WAIT             = 1;        // 客服介入处理中

        public static final short  SER_STATUS_INVOLVED         = 2;        // 纠纷处理中

        /**
         * // 处理完毕
         */
        public static final short  SER_STATUS_PROCESSED        = 3;        // 处理完毕

        /**
         * // 纠纷关闭
         */
        public static final short  SER_STATUS_COMPLETED        = 4;        // 纠纷关闭

        /**
         * // 等待客服介入中
         */
        public static final short  SER_STATUS_CLOSED           = 5;        // 等待客服介入中

        /* 运费支付状态 */
        public static final short  FRE_PAY_STATE_UNPAY         = 0;        // 未支付运费

        public static final short  FRE_PAY_STATE_PAYED         = 1;        // 已支付运费

        /* 付款状态 */
        public static final short  PAY_STATE_UNPAY             = 0;        // 未支付

        public static final short  PAY_STATE_PART              = 1;        // 部分支付

        public static final short  PAY_STATE_ALL               = 2;        // 已支付

        public static final short  PRIVILEGE                   = 0;        // 优惠

        public static final short  RISEPRICE                   = 1;        // 加价


        public static final short  ORDER_TYPE_CASH             = 1;        // 现款订单

        public static final short  ORDER_TYPE_CREDIT           = 2;        // 授信订单

        public static final short  ORDER_TYPE_POINT            = 3;        // 返点订单

        public static final short  FREIGHT_PAY_YES = 1;                    //运费已付

        public static final short  FREIGHT_PAY_NO = 0 ;                    //运费未付

    }

    public class DealerComplaint
    {
        // comState投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）
        public static final short COMSTATE_0       = 0;  // 0：等待处理

        public static final short COMSTATE_1       = 1;  // 1：客服介入

        public static final short COMSTATE_2       = 2;  // 2：处理完成

        public static final short COMSTATE_3       = 3;  // 3：经销商撤消投诉

        // 组合查询时，当页面传入的数据格式错误时，SQL里传入一个永不会出现的值
        public static final long  FIXED_NUM        = -1l;

        // complaintCause投诉原因（1：未按约定时间发货，2：未按成交价格进行交易，3：承诺的没做到，4：违反交易流程）
        public static final short COMPLAINTCAUSE_1 = 1;

        public static final short COMPLAINTCAUSE_2 = 2;

        public static final short COMPLAINTCAUSE_3 = 3;

        public static final short COMPLAINTCAUSE_4 = 4;
    }

    public class DealerOrders
    {
        public static final short  ITEM_STATE_CLOSED = 0;  // 关闭状态

        public static final short  ITEM_STATE_NORMAL = 1;  // 正常状态

        public static final short  ITEM_STSTE_REFUND = 2;  // 退款状态

        public static final String VID               = "0"; // 默认产品属性
    }

    public class DealerInfo
    {
        public static final short CHECK_STATE_UNVERFIY = 0; // 0：未审核

        public static final short CHECK_STATE_SUCCESS  = 1; // 1：审核通过

        public static final short CHECK_STATE_FAILURE  = 2; // 2：审核不通过

        public static final short CHECK_STATE_PROCESS  = 3; // 3：处理中
    }

    public class DealerCount
    {
        public static final int DEALERCOUNT_COLUMN_JOINCOUNT         = 0; // 已经加盟的品牌数

        public static final int DEALERCOUNT_COLUMN_APPLYCOUNT        = 1; // 申请中的品牌数

        public static final int DEALERCOUNT_COLUMN_INVITECOUNT       = 2; // 邀请中的品牌数

        public static final int DEALERCOUNT_COLUMN_BALANCECOUNT      = 3; // 待付款订单数量

        public static final int DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT = 4; // 待收货订单数量

        public static final int DEALERCOUNT_COLUMN_REFUNDCOUNT       = 5; // 退款中的订单数

        public static final int DEALERCOUNT_COLUMN_SYSMESSAGECOUNT   = 6; // 未读系统消息

        public static final int DEALERCOUNT_COLUMN_WARNINGCOUNT      = 7; // 未读预警消息

        public static final int DEALERCOUNT_COLUMN_FACTORYJOINCOUNT  = 8;

        public static final int DEALERCOUNT_COLUMN_FACTORYHASPAID    = 9;

        public static final int DEALERCOUNT_COLUMN_FACTORYNONPAID    = 10;
    }

    public class TradeDetails
    {// 1等待付款 2 等待发货 3:部分发货 4 等待确认收货 9 交易成功 10 交易关闭
        public static final short TRADE_CLOSE = 10;
    }

    public class ProductViewLog
    {
        public static final short USER_CATE_DEALER = 1;

        public static final short USER_CATE_BRAND  = 2;
    }

    public static class ActionCode
    {
        // 1：访问首页
        // 2：查看招募书
        // 3：查看品牌视觉
        // 4：查看产品
        // 5：查看公司介绍
        // 6：查看经销商网络
        // 7：查看下载
        // 8：下载资料
        // 9：填写报名资料
        public static final int ACCESS_INDEX        = 1;

        public static final int VIEW_RECRUIT        = 2;

        public static final int VIEW_PHOTOS         = 3;

        public static final int VIEW_PRODUCTS       = 4;

        public static final int VIEW_INTRODUCE      = 5;

        public static final int VIEW_NETWORK        = 6;

        public static final int DOWNLOAD            = 7;

        public static final int VIEW_DOWNLOAD       = 8;

        public static final int SIGN_UP             = 9;

        public static final int VIEW_PRODUCT_DETAIL = 10;
    }
    
    public class DealerDrawing
    {
        public static final short DRAWSTATE_0 = 0; // 申请等待处理

        public static final short DRAWSTATE_1 = 1; // 提现成功

        public static final short DRAWSTATE_3 = 3; // 财务处理中

        public static final short DRAWSTATE_5 = 5; // 已提交到银行

        public static final short DRAWSTATE_7 = 7; // 提现失败（失败原因备注说明）

        public static final short DRAWSTATE_9 = 9; // 取消提现
    }

    public class DealerFrozen
    {
        public static final short FROZENSTATE_0 = 0; // 0：处理中

        public static final short FROZENSTATE_1 = 1; // 1：提现成功

        public static final short FROZENSTATE_2 = 2; // 2：提现失败

        public static final short FROZENSTATE_3 = 3; // 3：取消提现
    }

    public class DealerBuySerLog
    {
        public static final short BUY_STATE_CREATE  = 0; // 0：生成

        public static final short BUY_STATE_SUBMIT  = 1; // 1：提交支付

        public static final short BUY_STATE_SUCCESS = 2; // 2：支付成功

        public static final short BUY_STATE_FAILURE = 3; // 3：支付失败

        public static final short CHARGE_TYPE_RENEW = 1; // 1：续期

        public static final short CHARGE_TYPE_RAISE = 2; // 2：增加数量
    }

    // 品牌商/经销商促成拜访信息
    public class DealerGroomVisit
    {
        // 拜访类型
        public static final short VISIT_TYPE_1 = 1; // 电话

        public static final short VISIT_TYPE_2 = 2; // 　文字
    }

    // 银行卡表
    public abstract static class DealerBank
    {
        public static Short CHECK_STATE_UNVERFIY = 0; // 0：未审核

        public static Short CHECK_STATE_SUCCESS  = 1; // 1：审核通过

        public static Short CHECK_STATE_FAILURE  = 2; // 2：审核不通过
    }
    
    public static class DealerOrderPay
    {
        // 支付状态
        public final static short STATE_UNPAY               = 0; // 未付款

        public final static short STATE_PAID                = 1; // 已付款

        public final static short STATE_CLOSE               = 2; // 已关闭

        public final static short STATE_REFUND              = 3; // 已退款

        // 订单类型
        public final static short ORDER_TYPE_DEALER_ORDER   = 0; // 订单

        public final static short ORDER_TYPE_DEALER_SERVICE = 1; // 终端商购买服务

        public final static short ORDER_TYPE_BRAND_SERVICE  = 2; // 品牌商购买服务

        public final static short ORDER_TYPE_DEALER_DEPOSIT = 3; // 押金
    }
}
