/*
 * @(#)ExceConst.java 2014-2-27 下午4:42:19
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：ExceConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-27 下午4:42:19</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public enum ExceConst implements EnumDescribable
{
    SUCCESS(0, "成功"),
    ABCDEFF(2, "ddd"),
    FAILURE(1, "失败");
    private ExceConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public static String getMessage(Integer code)
    {
        String result = null;
        for (ExceConst c : ExceConst.values())
        {
            if (c.getCode() == code)
            {
                result = c.message;
            }
        }
        return result;
    }
    
    private Integer code;
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    private String message;
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public static void main(String[] args)
    {
        System.out.println(ExceConst.FAILURE.code);
        System.out.println(ExceConst.FAILURE.message);
        System.out.println(ExceConst.getMessage(0));
    }
}
