/*
 * @(#)ZttxConst.java 2014-11-29 下午3:39:28
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.PropertiesLoader;

/**
 * <p>File：ZttxConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-29 下午3:39:28</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class ZttxConst
{
    public final static PropertiesLoader zttxPropertiesLoader    = new PropertiesLoader("classpath:zttx.properties");
    
    // 交易平台
    public final static String           ZTTX_WEBURL             = zttxPropertiesLoader.getProperty("zttx.weburl");
    
    // 支付平台
    public final static String           PAYAPI_WEBURL           = zttxPropertiesLoader.getProperty("zttx.payapi.weburl");
    
    // 充值
    public final static String           PAYAPI_WEBURL_RECHARGE  = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.recharge");
    
    // 查询余额
    public final static String           PAYAPI_WEBURL_PORTAL    = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.portal");
    
    // 提现
    public final static String           PAYAPI_WEBURL_WITHDRAW  = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.withdraw");
    
    // 修改支付密码
    public final static String           PAYAPI_WEBURL_RESETPWD  = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.resetpwd");
    
    // 退款信息
    public final static String           PAYAPI_WEBURL_REFUND    = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.refund");
    
    // 消费记录
    public final static String           PAYAPI_WEBURL_TRADE     = PAYAPI_WEBURL + zttxPropertiesLoader.getProperty("zttx.payapi.weburl.trade");
    
    // 图片域名
    public final static String           IMAGE_DOMAIN            = zttxPropertiesLoader.getProperty("zttx.image.domain");
    
    // 支撑系统
    public final static String           CRMAPI_USERKEY          = zttxPropertiesLoader.getProperty("zttx.crmapi.userkey");
    
    public final static String           CRMAPI_WEBURL           = zttxPropertiesLoader.getProperty("zttx.crmapi.weburl");
    
    // ID应用
    public final static String           IDAPI_UUID_WEBURL       = zttxPropertiesLoader.getProperty("zttx.idapi.uuid.weburl");
    
    public final static String           IDAPI_IDWORKER_WEBURL   = zttxPropertiesLoader.getProperty("zttx.idapi.idworker.weburl");
    
    public final static String           IDAPI_SEQUENCEID_WEBURL = zttxPropertiesLoader.getProperty("zttx.idapi.sequenceid.weburl");
    
    public final static String           IDAPI_USERKEY           = zttxPropertiesLoader.getProperty("zttx.idapi.userkey");
    
    public final static String           IDAPI_PLATFORMCODE      = zttxPropertiesLoader.getProperty("zttx.idapi.platformcode");
    
    // 用户中心
    public final static String           USERAPI_USERKEY         = zttxPropertiesLoader.getProperty("zttx.userapi.userkey");
    
    public final static String           USERAPI_WEBURL          = zttxPropertiesLoader.getProperty("zttx.userapi.weburl");
    
    // ERP
    public final static String           ERPAPI_USERKEY          = zttxPropertiesLoader.getProperty("zttx.erpapi.userkey");
    
    public final static String           ERPAPI_WEBURL           = zttxPropertiesLoader.getProperty("zttx.erpapi.weburl");
    // BI URL
    public static final String           BIAPI_WEBURL            = zttxPropertiesLoader.getProperty("zttx.biapi.weburl");
    
    // 文件服务器
    public final static String           FILEAPI_USERKEY         = zttxPropertiesLoader.getProperty("zttx.fileapi.userkey");
    
    public final static String           FILEAPI_WEBURL          = zttxPropertiesLoader.getProperty("zttx.fileapi.weburl");
    
    public static final String           INDEX                   = "index";
    
    public static final String           RECRUIT                 = "recruit";                                                                      // 招募书
    
    public static final String           VISUAL                  = "visual";                                                                       // 品牌视觉
    
    public static final String           PRODUCT                 = "product";                                                                      // 产品展示
    
    public static final String           COMPANY                 = "company";                                                                      // 企业展示
    
    public static final String           NETWORK                 = "network";                                                                      // 经销网络
    
    public static final String           NEWS                    = "news";                                                                         // 品牌新闻
    
    public static final String           NEWSINFO                = "newsinfo";                                                                     // 品牌新闻
    
    public static final String           DOCUMENT                = "document";                                                                     // 资料下载
    
    public static final String           DEAL                    = "deal";                                                                         // 交易会
    
    public static final String           PRODUCT_PHOTOS          = "product_photos";                                                               // 产品相册
    
    // 文件服务器参数：加密标准
    public static final String           FILE_PARAM_USER_DES     = "userDes";
    
    // 文件服务器参数：身份标示
    public static final String           FILE_PARAM_USR_KEY      = "userKey";
    
    // 文件服务器参数：消息长度
    public static final String           FILE_PARAM_DATA_LEN     = "dataLen";
    
    // 文件服务器参数：消息体内容
    public static final String           FILE_PARAM_DATA         = "data";
    
    // 文件服务器参数：消息体内容
    public static final String           FILE_PARAM_HTML         = "html";
    
    // 文件服务器参数成功状态代码
    public static final Integer          SUCCESS                 = 126000;
    
    public static final String           CODE                    = "code";                                                                          // 状态代码
    
    public static final String           MESSAGE                 = "message";                                                                       // 状态消息：如果错误，这里面存储错误原因。
}
