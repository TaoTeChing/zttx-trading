/*
 * @(#)CommonConstant 2014/5/6 10:44
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：CommonConstant</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/6 10:44</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public abstract class CommonConstant
{
    public static final String DOMAIN = "";

    //角色来源
    public static class  RoleType
    {
        public static  final String ROLE_DEALER_USER_CODE = "ROLE_DEALER_USER";  // 经销商角色代码
        public static  final String ROLE_BRAND_UNOPEN_USER_CODE = "ROLE_BRAND_UNOPEN"; // 品牌商未授权用户角色代码
        public static  final String ROLE_BRAND_USER_CODE = "ROLE_BRAND_USER";  //品牌商角色代码
    }
    
    //数据平台来源
    public static class  sourceType
    {
        public static  final String ZTTX = "0";   //交易平台
        public static  final String ERP_MD = "1";  //门店erp
        public static  final String ERP_PP = "3";  //品牌erp
        public static  final String APP = "4";     //约逛
    }
    // 帮助分类
    public static class HelpCate
    {
        public static final short SHOW_TYPE_LIST    = 1; // 列表显示
        
        public static final short SHOW_TYPE_ARTICLE = 2; // 文章显示
        
        public static final short VISIABLE          = 1; // 显示
        
        public static final short INVISIABLE        = 2; // 不显示
    }
    
    // 规则分类
    public static class RulesCate
    {
        public static final short CATE_TYPE_LIST    = 1; // 列表显示
        
        public static final short CATE_TYPE_ARTICLE = 2; // 文章显示
        
        public static final short VISIABLE          = 1; // 显示
        
        public static final short INVISIABLE        = 2; // 不显示
    }
    
    public static class Regional
    {
        public static final short PROVINCE = 1; // 省
        
        public static final short CITY     = 2; // 市
        
        public static final short COUNTY   = 3; // 区
    }
    
    public static class Message
    {
        // 系统发送人名称
        public static final String  SYS_SEND_NAME     = "交易平台";
        
        // 消息类型-系统消息
        public static final Short   MSG_CATE_SYS      = 1;
        
        // 消息类型-站内消息
        public static final Short   MSG_CATE_NET      = 2;
        
        // 消息类型-订单消息
        public static final Short   MSG_CATE_BIL      = 3;
        
        // 消息类型-留言消息
        public static final Short   MSG_CATE_LEA      = 4;
        
        // 拒绝回复（表示该消息是否不能回复）-可以回复(false为0，可以回复)
        public static final Boolean MSG_REPLY_YES     = false;
        
        // 拒绝回复（表示该消息是否不能回复）-不能回复(true为1，拒绝回复)
        public static final Boolean MSG_REPLY_NO      = true;
        
        // 消息查看方式-接收的消息
        public static final Short   MSG_LIST_RECEIVE  = 0;
        
        // 消息查看方式-发送的消息
        public static final Short   MSG_LIST_SEND     = 1;
        
        // 发送消息-不发送
        public static final Boolean SEND_NOTE_NO      = false;
        
        // 发送消息-发送
        public static final Boolean SEND_NOTE_YES     = true;
        
        // 消息标题的最大字数
        public static final int     MAX_NET_TITLE     = 128;
        
        // 消息内容的最大字数
        public static final int     MAX_NET_CONTENT   = 5120;
        
        // 短信内容的最大字数
        public static final int     MAX_MOB_CONTENT   = 128;
        
        // 消息状态-全部
        public static final Short   MSG_STATUS_ALL    = 0;
        
        // 消息状态-已读
        public static final Short   MSG_STATUS_READED = 1;
        
        // 消息状态-未读
        public static final Short   MSG_STATUS_UNREAD = 2;
    }
    
    public static class SecurityCert
    {
        // actState 操作结果
        public static final short NONE_AUDIT          = 0; // 未审核
        
        public static final short PASS_AUDIT          = 1; // 审核通过
        
        public static final short NO_PASS_AUDIT       = 2; // 审核不通过
        
        // applyType 申请类型
        public static final short APPLY_UPDATE_MOBILE = 1; // 更换手机号码
    }
    
    public static class WebServiceItems
    {
        public static final short  SERVICE_TYPE_BRAND      = 1;     // 品牌商
        
        public static final short  SERVICE_TYPE_DEALER     = 2;     // 经销商
        
        // public static final short SERVICE_CATE_SYSTEM = 1; // 系统
        // public static final short SERVICE_CATE_CUSTOM = 2; // 用户
        public static final String SERVICE_SYSTEM_PLATFORM = "S001"; // 平台服务
        
        public static final String SERVICE_SYSTEM_WESHOP   = "S004"; // 微店
        
        public static final String SERVICE_SYSTEM_STIALERP = "S005"; // 试用ERP
    }
    
    public static class FindAccountConstant
    {
        // 证件类型 1：身份证
        public static final short CART_ID     = 1;
        
        // 2：营业执照
        public static final short BUSINESS_ID = 2;
        
        // 3：其他
        public static final short OTHER_ID    = 3;
        
        // //审核状态 0：等待处理
        public static final short WAIT        = 0;
        
        // 1：处理完成
        public static final short FINISH      = 1;
        
        // 2：不处理/处理失败
        public static final short ERROR_FINIS = 2;
    }
    
    public static class DealerMenu
    {
        public static final short MENUTYPE_01 = 0; // 品牌商菜单
        
        public static final short MENUTYPE_02 = 1; // 经销商菜单
    }
    
    public static class QueryPrice
    {
        // 询价单状态
        public static final short  STATE_SUMBIT   = 0;        // 申请中
        
        public static final short  STATE_AGREE    = 1;        // 同意加盟
        
        public static final short  STATE_REFUSE   = 2;        // 拒绝加盟
        
        public static final short  STATE_GIVEUP   = 3;        // 已放弃
        
        public static final short  STATE_REMOVE   = 4;        // 已移除
        
        // 锁定状态
        public static final short  LOCKSTATE_NO   = 0;        // 未锁定
        
        public static final short  LOCKSTATE_YES  = 1;        // 锁定
        
        // 报价状态
        public static final short  QUERYSTATE_NO  = 0;        // 未报
        
        public static final short  QUERYSTATE_YES = 1;        // 已报
        
        // 询价单序列名称
        public static final String SEQUENCE_ID    = "QueryNo";
        
        public static final String SEQUENCE_SIZE  = "4";
    }
    
    public static class OrderPay
    {
        public final static Long   PAY_MERCHANT_ID   = 100000L;
        
        public final static String PAY_MERCHANT_NAME = "交易平台";
        
        public final static String SPLIT_PAY_ID      = ",";        // 支付订单ID分割标志
        
        public final static String PAY_LOCK_PRE_NAME = "PAY_LOCK_"; // 缓存锁前缀
    }
    
    public static class UserCenterClient
    {
        public final static String PLATFORM_TYPE_ZTTX   = "10001";                         // 交易平台编号
        
        public final static String PLATFORM_TYPE_APP    = "10002";                         // APP编号
        
        public final static Long   EXPIRATION_TIME_ZTTX = 100 * 365 * 12 * 60 * 60 * 1000L; // 交易平台过期时间
        
        public final static Long   EXPIRATION_TIME_APP  = 100 * 365 * 12 * 60 * 60 * 1000L; // APP过期时间
        
        public final static int    SUCCESS              = 121000;
        
        public final static int    SUCCESS_2            = 126000;
        
        public final static int    STATUS_YES           = 1;                               // 已启用
        
        public final static int    STATUS_NO            = 0;                               // 已停用
        
        public final static int    PARENTID             = 0;                               // 父ID
        
        public final static int    IS_SUB_ACCOUNT_YES   = 1;                               // 是否子账号
        
        public final static int    IS_SUB_ACCOUNT_NO    = 0;                               // 是否子账号
        
        public final static int    RESOURCE_TYPE_ZTTX   = 0;                               // 交易平台类型
        
        public final static int    LOGIN_ERROR_PASSWORD = 1260011;                         // 密码错误
    }
    
    public static class ErpClient
    {
        public final static int SUCCESS = 121000; // 成功代码
    }
    
    public static class FileClient
    {
        public final static int SUCCESS = 126000; // 成功代码
    }
    
    public static class Excel
    {
        // 名称中不能使用的符号和替换后的字符
        public final static String[] NAME_UNUSED = new String[]{"/", " "};
        
        public final static String[] NAME_USED   = new String[]{"\\1\\", "\\2\\"};
    }

    public static class financial
    {
        public final static short TYPE_POINT= 0;  //返点财务帐
        public final static short TYPE_FINANCIAL =1;   //财务帐

        public final static short PAY_NO = 0;  //未支付
        public final static short PAY_SUCCESS = 1;   // 支付成功
        public final static short PAY_FAIL =2;    //支付失败
    }
}
