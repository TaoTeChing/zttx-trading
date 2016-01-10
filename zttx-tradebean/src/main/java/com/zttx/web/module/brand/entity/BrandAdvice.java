/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商优化建议 实体对象
 * <p>File：BrandAdvice.java</p>
 * <p>Title: BrandAdvice</p>
 * <p>Description:BrandAdvice</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandAdvice extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**建议内容*/
    @NotBlank(message = "建议内容不能为空")
    private java.lang.String  adviceText;
    
    /**建议时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getAdviceText()
    {
        return this.adviceText;
    }
    
    public void setAdviceText(java.lang.String adviceText)
    {
        this.adviceText = adviceText;
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
