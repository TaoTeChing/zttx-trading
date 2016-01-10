/*
 * @(#)CenterUser.java 2014-12-9 上午11:57:09
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.io.Serializable;

/**
 * <p>File：CenterUser.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-9 上午11:57:09</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class CenterUser implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8415875785219985535L;
    
    private String            trueName;
    
    private String            userName;
    
    private String            email;
    
    private String            parentId;
    
    private String            mobile;
    
    private String            refrenceId;
    
    private String            status;                                  // 用户状态 1(启用)或 0(停用)
    
    private String            password;
    
    private Long              lastUpdateTime;
    
    public String getTrueName()
    {
        return trueName;
    }
    
    public void setTrueName(String trueName)
    {
        this.trueName = trueName;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getRefrenceId()
    {
        return refrenceId;
    }
    
    public void setRefrenceId(String refrenceId)
    {
        this.refrenceId = refrenceId;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getParentId()
    {
        return parentId;
    }
    
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public Long getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    
    public void setLastUpdateTime(Long lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
}
