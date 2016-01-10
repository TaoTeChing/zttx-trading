/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品计数信息 实体对象
 * <p>File：ProductCount.java</p>
 * <p>Title: ProductCount</p>
 * <p>Description:ProductCount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductCount extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**brandId*/
    private java.lang.String  brandId;
    
    /**brandsId*/
    private java.lang.String  brandsId;
    
    /**产品被查看次数*/
    private java.lang.Integer viewNum;
    
    /**产品被收藏次数*/
    private java.lang.Integer collectNum;
    
    /**产品销售数量*/
    private java.lang.Integer saleNum;
    
    /**createTime*/
    private java.lang.Long    createTime;
    
    /**updateTime*/
    private java.lang.Long    updateTime;
    
    /**
     * 当前月销量
     */
    private Integer           monthSaleNum;
    
    public ProductCount()
    {
    }
    
    //
    public ProductCount(String refrenceId, String brandId, String brandsId, Integer viewNum, Integer collectNum, Integer saleNum, Long createTime, Integer monthSaleNum)
    {
        super();
        this.refrenceId = refrenceId;
        this.brandId = brandId;
        this.brandsId = brandsId;
        this.viewNum = viewNum;
        this.collectNum = collectNum;
        this.saleNum = saleNum;
        this.createTime = createTime;
        this.monthSaleNum = monthSaleNum;
    }
    
    public Integer getMonthSaleNum()
    {
        return monthSaleNum;
    }
    
    public void setMonthSaleNum(Integer monthSaleNum)
    {
        this.monthSaleNum = monthSaleNum;
    }
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandsId()
    {
        return this.brandsId;
    }
    
    public void setBrandsId(java.lang.String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public java.lang.Integer getViewNum()
    {
        return this.viewNum;
    }
    
    public void setViewNum(java.lang.Integer viewNum)
    {
        this.viewNum = viewNum;
    }
    
    public java.lang.Integer getCollectNum()
    {
        return this.collectNum;
    }
    
    public void setCollectNum(java.lang.Integer collectNum)
    {
        this.collectNum = collectNum;
    }
    
    public java.lang.Integer getSaleNum()
    {
        return this.saleNum;
    }
    
    public void setSaleNum(java.lang.Integer saleNum)
    {
        this.saleNum = saleNum;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
}
