/*
 * @(#)CookieConst.java 2014-1-8 下午1:37:36
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：CookieConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:37:36</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class CookieConst
{
    // 私有构造器，防止类的实例化
    private CookieConst()
    {
        super();
    }
    
    public static final String ACCOUNT          = "account";
    
    // 经销商UUID的Cookie名称
    public static final String DEALER_UUID      = "dealerId";
    
    // 品牌商UUID的Cookie名称
    public static final String BRANDE_UUID      = "brandeId";
    
    // 代理商UUID的Cookie名称
    public static final String AGENT_UUID       = "agentId";
    
    // 品牌商菜单ID
    public static final String BRANDE_MENU_UUID = "brandeMneuId";
    
    public static final String PAYMENT          = "payment";
}
