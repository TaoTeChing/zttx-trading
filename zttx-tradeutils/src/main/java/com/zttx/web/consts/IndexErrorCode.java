/*
 * @(#)IndexErrorCode.java 2014-5-13 上午11:06:02
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：IndexErrorCode.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-13 上午11:06:02</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public enum IndexErrorCode implements EnumDescribable
{
    QUESTION_NULL(150001, "提交的问题内容不能为空"),
    MAIL_NULL(150002, "邮箱地址不能为空"),
    MAIL_FORMAT_ERROR(150003, "邮件格式错误"),
    OVERTOP_LENGTH(150004, "输入的字符过长，请重新输入"),
    MOBILE_ERROR(150005, "电话格式不正确");
    /**
     * @param code
     * @param message
     */
    private IndexErrorCode(Integer code, String message)
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
