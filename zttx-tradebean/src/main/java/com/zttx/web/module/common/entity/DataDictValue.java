/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 数据字典值 实体对象
 * <p>File：DataDictValue.java</p>
 * <p>Title: DataDictValue</p>
 * <p>Description:DataDictValue</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DataDictValue extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**字典编号*/
    @NotBlank(message = "字典编号不能为空")
    private java.lang.String  dictid;
    
    /**字典编码*/
    private java.lang.String  dictCode;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**字典值名称*/
    @NotBlank(message = "字典值名称不能为空")
    @Length(max = 32, message = "字典值名称长度不能超过32位")
    private java.lang.String  dictValueName;
    
    /**字典值*/
    @NotBlank(message = "字典值不能为空")
    @Length(max = 32, message = "字典值长度不能超过32位")
    private java.lang.String  dictValue;
    
    /**排序字段*/
    @NotNull(message = "排序字段不能为空")
    private java.lang.Integer dictOrder;
    
    /**描述*/
    private java.lang.String  remark;
    
    public java.lang.String getDictid()
    {
        return this.dictid;
    }
    
    public void setDictid(java.lang.String dictid)
    {
        this.dictid = dictid;
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
    
    public java.lang.String getDictValueName()
    {
        return this.dictValueName;
    }
    
    public void setDictValueName(java.lang.String dictValueName)
    {
        this.dictValueName = dictValueName;
    }
    
    public java.lang.String getDictValue()
    {
        return this.dictValue;
    }
    
    public void setDictValue(java.lang.String dictValue)
    {
        this.dictValue = dictValue;
    }
    
    public java.lang.Integer getDictOrder()
    {
        return this.dictOrder;
    }
    
    public void setDictOrder(java.lang.Integer dictOrder)
    {
        this.dictOrder = dictOrder;
    }
    
    public java.lang.String getRemark()
    {
        return this.remark;
    }
    
    public void setRemark(java.lang.String remark)
    {
        this.remark = remark;
    }
}
