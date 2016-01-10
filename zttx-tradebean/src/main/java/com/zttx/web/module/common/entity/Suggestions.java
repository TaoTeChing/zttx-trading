/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 意见和建议 实体对象
 * <p>File：Suggestions.java</p>
 * <p>Title: Suggestions</p>
 * <p>Description:Suggestions</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class Suggestions extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**电子邮件*/
    @Email(message = "电子邮件格式不正确")
    private java.lang.String  email;
    
    /**留言信息*/
    @NotBlank(message = "留言信息不可为空")
    private java.lang.String  message;
    
    /**创建时间*/
    private java.lang.Long    createTime;
    
    /**更新时间*/
    private java.lang.Long    updateTime;
    
    public java.lang.String getEmail()
    {
        return this.email;
    }
    
    public void setEmail(java.lang.String email)
    {
        this.email = email;
    }
    
    public java.lang.String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(java.lang.String message)
    {
        this.message = message;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
}
