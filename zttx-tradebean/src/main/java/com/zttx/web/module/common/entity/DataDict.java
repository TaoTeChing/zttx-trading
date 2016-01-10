/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 数据字典 实体对象
 * <p>File：DataDict.java</p>
 * <p>Title: DataDict</p>
 * <p>Description:DataDict</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DataDict extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**字典名称*/
    @NotBlank(message = "字典名称不能为空")
    private java.lang.String  dictName;
    
    /**字典编码*/
    @NotBlank(message = "字典编码不能为空")
    private java.lang.String  dictCode;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**dictDesc*/
    private java.lang.String  dictDesc;
    
    public java.lang.String getDictName()
    {
        return this.dictName;
    }
    
    public void setDictName(java.lang.String dictName)
    {
        this.dictName = dictName;
    }
    
    public java.lang.String getDictCode()
    {
        return this.dictCode;
    }
    
    public void setDictCode(java.lang.String dictCode)
    {
        this.dictCode = dictCode;
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
    
    public java.lang.String getDictDesc()
    {
        return this.dictDesc;
    }
    
    public void setDictDesc(java.lang.String dictDesc)
    {
        this.dictDesc = dictDesc;
    }
}
