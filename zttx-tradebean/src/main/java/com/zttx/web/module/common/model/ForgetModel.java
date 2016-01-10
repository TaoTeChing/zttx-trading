/*
 * @(#)ForgetModel.java 2014-3-3 下午6:20:59
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.web.module.common.entity.UserInfo;

/**
 * <p>
 * File：ForgetModel.java
 * </p>
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014 2014-3-3 下午6:20:59
 * </p>
 * <p>
 * Company: 8637.com
 * </p>
 * 
 * @author 周光暖
 * @version 1.0
 */
public class ForgetModel implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3136818877094157638L;
    
    @NotBlank(message = "验证码不能为空")
    private String            telCode;
    
    private String            userMobile;
    
    private Integer           userType;
    
    private String            verifyCode;
    
    @NotBlank(message = "新密码不能为空")
    private String            newPwd;
    
    private String            oldPwd;
    
    private long              createTime;                              //
    
    private Integer           createIp;                                //
    
    // 对应 brandId、dealerId
    private String            usermId;
    
    public ForgetModel()
    {
    }
    
    public ForgetModel(String newPwd, UserInfo brandUserm)
    {
        this.newPwd = newPwd;
        this.createTime = System.currentTimeMillis();
        this.usermId = brandUserm.getRefrenceId();
        this.oldPwd = brandUserm.getUserPwd();
    }
    
    public String getNewPwd()
    {
        return newPwd;
    }
    
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    
    public long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(long createTime)
    {
        this.createTime = createTime;
    }
    
    public Integer getCreateIp()
    {
        return createIp;
    }
    
    public void setCreateIp(Integer createIp)
    {
        this.createIp = createIp;
    }
    
    public String getUsermId()
    {
        return usermId;
    }
    
    public void setUsermId(String usermId)
    {
        this.usermId = usermId;
    }
    
    public String getOldPwd()
    {
        return oldPwd;
    }
    
    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }
    
    public String getTelCode()
    {
        return telCode;
    }
    
    public void setTelCode(String telCode)
    {
        this.telCode = telCode;
    }
    
    public String getUserMobile()
    {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public Integer getUserType()
    {
        return userType;
    }
    
    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
    
    public String getVerifyCode()
    {
        return verifyCode;
    }
    
    public void setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
    }
}
