package com.zttx.web.consts;

/**
 * @author 易永耀
 */
public abstract class CrmConstant
{
    // crm免登陆到品牌商后台对相应的模块操作
    public abstract static class crmToBrandOptionModel
    {
        // 对 产品 的操作
        public static final short PRODUCT     = 1;
        
        // 对 产品线 的操作
        public static final short PRODUCTLINE = 2;
        
        // 对 交易管理 的操作
        public static final short ORDER       = 3;
        
        // 对 终端商管理 的操作
        public static final short DEALER      = 4;
    }
    
    public abstract static class CrmBrandOptionName
    {
        /** crm免登陆品牌商操作日志记录状态
         * 1)产品(ADD,DELETE,UPDATE,SOLD_OUT)
         * 2)产品线(ADD,DELETE,UPDATE)
         * 3)交易管理(UPDATE,CLOSE,AGREE,REFUSE,SHIPMENTS)
         * 4)终端商管理(REFUSE,REFUSE,INVITE,STOP)
         */
        // 增
        public static final short ADD       = 1;
        
        // 删
        public static final short DELETE    = 2;
        
        // 改
        public static final short UPDATE    = 3;
        
        // 下架产品
        public static final short SOLD_OUT  = 4;
        
        // 关闭交易
        public static final short CLOSE     = 5;
        
        // 同意退款申请,同意加盟
        public static final short AGREE     = 6;
        
        // 拒绝退款申请,拒绝加盟
        public static final short REFUSE    = 7;
        
        // 发货
        public static final short SHIPMENTS = 8;
        
        // 邀请
        public static final short INVITE    = 9;
        
        // 终止
        public static final short STOP      = 10;
    }
    
    // crm免登陆到经销商后台对相应的模块操作
    public abstract static class crmToDealerOptionModel
    {
        // 对 普通订单 的操作
        public static final short GENERAL_ORDER = 1;
        
        // 对 询价订单 的操作
        public static final short ENQUIRY_ORDER = 2;
        
        // 对 铺货订单(工厂店) 的操作
        public static final short FACTORY_ORDER = 3;
        
        // 对 加盟管理(品牌商) 的操作
        public static final short BRAND         = 4;
    }
    
    public abstract static class CrmDealerOptionName
    {
        /** crm免登陆经销商操作日志记录状态
         * 1)普通订单(ADD,CLOSE,REFUND,CONFIRM)
         * 2)询价订单(ADD,DELETE)
         * 3)铺货订单(工厂店)(ADD,DELETE,REFUND,CONFIRM)
         * 4)加盟管理(品牌商)(APPLY,AGREE,STOP)
         */
        // 增
        public static final short ADD     = 1;
        
        // 关闭订单
        public static final short CLOSE   = 2;
        
        // 退款
        public static final short REFUND  = 3;
        
        // 确认收货
        public static final short CONFIRM = 4;
        
        // 付款
        public static final short PAY     = 5;
        
        // 申请加盟
        public static final short APPLY   = 6;
        
        // 同意加盟
        public static final short AGREE   = 7;
        
        // 终止合作
        public static final short STOP    = 8;
    }
}