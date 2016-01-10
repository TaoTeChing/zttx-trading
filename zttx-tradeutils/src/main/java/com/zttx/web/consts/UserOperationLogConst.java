package com.zttx.web.consts;

/**
 * 用户操作常量
 * <p>File：UserOperationLogConst.java</p>
 * <p>Title: UserOperationLogConst</p>
 * <p>Description:UserOperationLogConst</p>
 * <p>Copyright: Copyright (c) Jul 15, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class UserOperationLogConst {
    public static final String TYPE_USERNAME_CRM = "支撑";
    
    public static final String TYPE_USERNAME_TASK = "自动任务";
    
	/** 操作押金*/
    public static final int TYPE_DEALERJOIN_DEPOSITTOTALAMOUNT = 0;
    
    /** 操作订单金额  货款金额，优惠金额，运费，折扣或者抵扣金额*/
    public static final int TYPE_DEALERORDER_PRICE = 1;
    
    /** 操作退款*/
    public static final int TYPE_DEALERREFUND = 2;
    
    /** 操作财务账*/
    public static final int TYPE_TRANSFER = 3;
    
    /** 操作产品*/
    public static final int TYPE_PRODUCTBASEINFO = 4;
    
    /** 操作活动产品*/
    public static final int TYPE_BRANDACTIVITYPRODUCT = 5;
}
