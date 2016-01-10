/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 页面模版定义 实体对象
 * <p>File：WebDefTemplate.java</p>
 * <p>Title: WebDefTemplate</p>
 * <p>Description:WebDefTemplate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class WebDefTemplate extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**模块名字*/
    @NotBlank(message = "模板名字不能为空")
    private java.lang.String  modelName;
    
    /**模块Key*/
    @NotBlank(message = "模块Key不能为空")
    private java.lang.String  modelKey;
    
    /**简介*/
    @NotBlank(message = "简介不能为空")
    private java.lang.String  subDesc;
    
    /**模块内容*/
    private java.lang.String  htmlText;
    
    /**创建时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    public java.lang.String getModelName()
    {
        return this.modelName;
    }
    
    public void setModelName(java.lang.String modelName)
    {
        this.modelName = modelName;
    }
    
    public java.lang.String getModelKey()
    {
        return this.modelKey;
    }
    
    public void setModelKey(java.lang.String modelKey)
    {
        this.modelKey = modelKey;
    }
    
    public java.lang.String getSubDesc()
    {
        return this.subDesc;
    }
    
    public void setSubDesc(java.lang.String subDesc)
    {
        this.subDesc = subDesc;
    }
    
    public java.lang.String getHtmlText()
    {
        return this.htmlText;
    }
    
    public void setHtmlText(java.lang.String htmlText)
    {
        this.htmlText = htmlText;
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
