/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商主营品类 实体对象
 * <p>File：BrandCatelog.java</p>
 * <p>Title: BrandCatelog</p>
 * <p>Description:BrandCatelog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandCatelog extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**品类编码*/
    private java.lang.Integer dealNo;
    
    /**品类名称*/
    private java.lang.String  dealName;
    
    /**建档时间*/
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
    
    public String getDealName()
    {
        return dealName;
    }
    
    public void setDealName(String dealName)
    {
        this.dealName = dealName;
    }
    
    public java.lang.Integer getDealNo()
    {
        return this.dealNo;
    }
    
    public void setDealNo(java.lang.Integer dealNo)
    {
        this.dealNo = dealNo;
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
