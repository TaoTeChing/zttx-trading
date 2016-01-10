/*
 * @(#)UserAccountConst.java 2014年2月28日 下午2:29:45
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * 与账号相关的一些常量
 * 
 * <p>File：UserAccountConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014年2月28日 下午2:29:45</p>
 * <p>Company: 8637.com</p>
 * @author 夏铭
 * @version 1.0
 */
public class UserAccountConst
{
    // 子帐号
    public static final short ACCOUNT_TYPE_SUB     = 0;
    
    // 主帐号
    public static final short ACCOUNT_TYPE_MASTER  = 1;
    
    // 帐号冻结
    public static final short FROZEN               = 2;
    
    // 新注册
    public static final short USER_STATE_NEW       = 0;
    
    // 审核通过
    public static final short USER_STAT_OPEN       = 1;
    
    // 审核不通过
    public static final Short USER_STAT_CLOSE      = 3;
    
    // 每个主帐号能创建的子帐户最大数
    public static final short SUM_USER_COUNT       = 20;
    
    // 会员类型 0、品牌商 1、经销商 2、系统平台
    public static final int   BRAND                = 0;
    
    public static final int   DEALER               = 1;
    
    public static final int   SYSTEM               = 2;
    
    /**用户是品牌商用户**/
    public static final Short   USERINFO_TYPE_BRAND  = 0;
    
    /**用户是终端商用户**/
    public static final Short   USERINFO_TYPE_DEALER = 1;
    /**信息维护员**/
    public static final Short   USERINFO_TYPE__SERVICE=2;
    /**平台维护员**/
    public static final Short   USERINFO_TYPE_SYSTEM=3;
}
