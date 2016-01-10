/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 调价详细信息表 实体对象
 * <p>File：Adjustments.java</p>
 * <p>Title: Adjustments</p>
 * <p>Description:Adjustments</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class Adjustments extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**品牌商调价Id*/
    private java.lang.String     brandAdjustId;
    
    /**产品Id*/
    private java.lang.String     productId;
    
    /**调价产品的skuId*/
    private java.lang.String     productSkuId;
    
    /**原工厂店sku直供价*/
    private java.math.BigDecimal oldFacSkuDirPrice;
    
    /**现工厂店直sku供价*/
    private java.math.BigDecimal nowFacSkuDirPrice;
    
    /**sku现有库存量*/
    private java.lang.Integer    quantity;
    
    /**sku总 优惠/加价金额(+为加价,-为优惠: (现价-原价)*库存量)*/
    private java.math.BigDecimal totalAdjustPrice;
    
    /**智慧门店调价时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    /**查询条件*/
    private java.lang.String     brandId;
    private java.lang.String     dealerId;
    private java.lang.String     brandesName;
    private java.lang.String     productTitle;
    private java.lang.String     productNo;
    /**查询条件*/

    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getDealerId()
    {
        return dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public String getBrandesName()
    {
        return brandesName;
    }
    
    public void setBrandesName(String brandesName)
    {
        this.brandesName = brandesName;
    }
    
    public String getProductTitle()
    {
        return productTitle;
    }
    
    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }
    
    public String getProductNo()
    {
        return productNo;
    }
    
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    
    public java.lang.String getBrandAdjustId()
    {
        return this.brandAdjustId;
    }
    
    public void setBrandAdjustId(java.lang.String brandAdjustId)
    {
        this.brandAdjustId = brandAdjustId;
    }
    
    public java.lang.String getProductId()
    {
        return this.productId;
    }
    
    public void setProductId(java.lang.String productId)
    {
        this.productId = productId;
    }
    
    public java.lang.String getProductSkuId()
    {
        return this.productSkuId;
    }
    
    public void setProductSkuId(java.lang.String productSkuId)
    {
        this.productSkuId = productSkuId;
    }
    
    public java.math.BigDecimal getOldFacSkuDirPrice()
    {
        return this.oldFacSkuDirPrice;
    }
    
    public void setOldFacSkuDirPrice(java.math.BigDecimal oldFacSkuDirPrice)
    {
        this.oldFacSkuDirPrice = oldFacSkuDirPrice;
    }
    
    public java.math.BigDecimal getNowFacSkuDirPrice()
    {
        return this.nowFacSkuDirPrice;
    }
    
    public void setNowFacSkuDirPrice(java.math.BigDecimal nowFacSkuDirPrice)
    {
        this.nowFacSkuDirPrice = nowFacSkuDirPrice;
    }
    
    public java.lang.Integer getQuantity()
    {
        return this.quantity;
    }
    
    public void setQuantity(java.lang.Integer quantity)
    {
        this.quantity = quantity;
    }
    
    public java.math.BigDecimal getTotalAdjustPrice()
    {
        return this.totalAdjustPrice;
    }
    
    public void setTotalAdjustPrice(java.math.BigDecimal totalAdjustPrice)
    {
        this.totalAdjustPrice = totalAdjustPrice;
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
