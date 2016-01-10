/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 地区编码与常规描述的转换表 实体对象
 * <p>File：RegionalCode.java</p>
 * <p>Title: RegionalCode</p>
 * <p>Description:RegionalCode</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RegionalCode extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**划分的所属区域包含的 逗号分隔 eg. 110000,122200*/
    @NotBlank(message = "所属区域编号不能为空")
    private java.lang.String  areaNos;
    
    /**区域描述*/
    @NotBlank(message = "区域描述不能为空")
    private java.lang.String  areaName;
    
    /**createTime*/
    private java.lang.Long    createTime;
    
    /**更新时间*/
    private java.lang.Long    updateTime;
    
    public java.lang.String getAreaNos()
    {
        return this.areaNos;
    }
    
    public void setAreaNos(java.lang.String areaNos)
    {
        this.areaNos = areaNos;
    }
    
    public java.lang.String getAreaName()
    {
        return this.areaName;
    }
    
    public void setAreaName(java.lang.String areaName)
    {
        this.areaName = areaName;
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
