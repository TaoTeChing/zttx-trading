/*
 * @(#)DecorateMenuConst.java 14-4-21 上午10:23
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：DecorateMenuConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-21 上午10:23</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public enum DecorateMenuConst implements EnumDescribable
{
    // 状态码范围：116000到120000
    MENU_NOT_EXIST(116000, "该菜单不存在"), MENU_NAME_NULL(116001, "菜单链接名称不能为空"), MENU_URL_NULL(116002, "菜单链接地址不能为空");
    public static final short MENU_SHOW = 1; // 菜单显示
    
    public static final short MENU_HIDE = 0; // 菜单隐藏
    
    private DecorateMenuConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public Integer code;
    
    public String  message;
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
}
