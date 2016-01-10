/*
 * @(#)ChangePayword.java 2014年3月6日 下午2:29:36
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

/**
 * 经销商支付密码提交表单对象
 * 
 * <p>File：ChangePayword.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014年3月6日 下午2:29:36</p>
 * <p>Company: 8637.com</p>
 * @author 夏铭
 * @version 1.0
 */
public class ChangePayword
{
    private String newPassowrd;
    
    private String cfmPassword;
    
    private String telCode;
    
    public String getNewPassowrd()
    {
        return newPassowrd;
    }
    
    public void setNewPassowrd(String newPassowrd)
    {
        this.newPassowrd = newPassowrd;
    }
    
    public String getCfmPassword()
    {
        return cfmPassword;
    }
    
    public void setCfmPassword(String cfmPassword)
    {
        this.cfmPassword = cfmPassword;
    }
    
    public String getTelCode()
    {
        return telCode;
    }
    
    public void setTelCode(String telCode)
    {
        this.telCode = telCode;
    }
}
