/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.math.BigDecimal;

import com.zttx.sdk.core.GenericEntity;

/**
 * 购物详细信息表 实体对象
 * <p>File：DealerShopers.java</p>
 * <p>Title: DealerShopers</p>
 * <p>Description:DealerShopers</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShopers extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**购物车编号*/
    private java.lang.String     shoperId;
    
    /**产品编号*/
    private java.lang.String     productId;
    
    /**产品SKU编号*/
    private java.lang.String     productSkuId;
    
    /**购买数量*/
    private java.lang.Integer    purchaseNum;
    
    /**创建时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    /**产品类型:0现款现货,1铺货*/
    private java.lang.Short      productType;
    
    /**产品sku的价格*/
    private java.math.BigDecimal productSkuPrice;
    
    /* ========================================= 临时字段 [@author易永耀] begin================================================ */
    /**SKU 编码*/
    private java.lang.String     productSkuCode;
    
    /**SKU名称*/
    private java.lang.String     productSkuName;
    
    /**产品库存*/
    private java.lang.Integer    productStock;
    
    private BigDecimal           directPrice;
    
    /* ========================================= 临时字段 end ================================================ */
    public java.lang.String getShoperId()
    {
        return this.shoperId;
    }
    
    public void setShoperId(java.lang.String shoperId)
    {
        this.shoperId = shoperId;
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
    
    public java.lang.String getProductSkuCode()
    {
        return this.productSkuCode;
    }
    
    public void setProductSkuCode(java.lang.String productSkuCode)
    {
        this.productSkuCode = productSkuCode;
    }
    
    public java.lang.String getProductSkuName()
    {
        return this.productSkuName;
    }
    
    public void setProductSkuName(java.lang.String productSkuName)
    {
        this.productSkuName = productSkuName;
    }
    
    public java.lang.Integer getProductStock()
    {
        return this.productStock;
    }
    
    public void setProductStock(java.lang.Integer productStock)
    {
        this.productStock = productStock;
    }
    
    public java.lang.Integer getPurchaseNum()
    {
        return this.purchaseNum;
    }
    
    public void setPurchaseNum(java.lang.Integer purchaseNum)
    {
        this.purchaseNum = purchaseNum;
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
    
    public java.lang.Short getProductType()
    {
        return this.productType;
    }
    
    public void setProductType(java.lang.Short productType)
    {
        this.productType = productType;
    }
    
    public java.math.BigDecimal getProductSkuPrice()
    {
        return this.productSkuPrice;
    }
    
    public void setProductSkuPrice(java.math.BigDecimal productSkuPrice)
    {
        this.productSkuPrice = productSkuPrice;
    }
    
    public BigDecimal getDirectPrice()
    {
        return directPrice;
    }
    
    public void setDirectPrice(BigDecimal directPrice)
    {
        this.directPrice = directPrice;
    }
}
