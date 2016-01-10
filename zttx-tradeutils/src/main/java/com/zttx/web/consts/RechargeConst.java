/*
 * @(#)RechargeConst.java 2014-4-18 上午10:09:59
 * Copyright 2014 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：RechargeConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-18 上午10:09:59</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public enum RechargeConst implements EnumDescribable
{
    RECHARGE_STATE_NEW(0, "未成功"),
    RECHARGE_STATE_PAY(1, "已成功"),
    SUCCESS(2, "成功"),
    FAILURE(3, "失败");

    public static final String RECHARGE_TYPE_DEALER_RECHARGE_ONLY     = "1";
    
    public static final String RECHARGE_TYPE_DEALER_RECHARGE_AND_DEAL = "2";
    
    public static final String RECHARGE_TYPE_BRAND_RECHARGE_ONLY      = "1";
    
    public static final String RECHARGE_TYPE_BRAND_RECHARGE_AND_DEAL  = "2";
    
    private RechargeConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 根据状态码获取状态码描述
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (CommonConst c : CommonConst.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
            }
        }
        return result;
    }
    
    public Integer code;
    
    public String  message;
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getCode()
     */
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getMessage()
     */
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
