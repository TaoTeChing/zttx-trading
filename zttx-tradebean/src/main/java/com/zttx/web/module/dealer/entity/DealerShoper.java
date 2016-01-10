/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商购物车 实体对象
 * <p>File：DealerShoper.java</p>
 * <p>Title: DealerShoper</p>
 * <p>Description:DealerShoper</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShoper extends GenericEntity
{
    private static final long    serialVersionUID  = 1L;
    
    /**经销商编号*/
    private java.lang.String     dealerId;
    
    /**品牌商主帐号编号*/
    private java.lang.String     brandId;
    
    /**品牌编号*/
    private java.lang.String     brandsId;
    
    /**商品编号*/
    private java.lang.String     productId;
    
    /**数量*/
    private java.lang.Integer    productNum;
    
    /**单价*/
    private java.math.BigDecimal productPrice;
    
    /**建档时间*/
    private java.lang.Long       createTime;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    /**来源ID*/
    private java.lang.String     sourceId;
    
    /**产品类型 0现款现货 1铺货*/
    private java.lang.Short      productType;
    
    /**折扣价(授信车单)*/
    private java.math.BigDecimal discountPrice;
    
    /* ========================================= 临时字段 [@author易永耀] begin================================================ */
    private List<DealerShopers>  dealerShopersList = Lists.newArrayList();
    
    private String               brandName;
    
    private String               brandsName;
    
    private Short                brandState;
    
    private String               productImage;
    
    private String               productNo;
    
    private Short                productStateSet;
    
    private Boolean              productDelFlag;
    
    private Boolean              isSample;
    
    private Boolean              isCredit;
    
    private Boolean              isPoint           = Boolean.FALSE;
    
    // 返点比例
    private BigDecimal           pointPercent;
    
    private String               productTitle;
    
    private Integer              startNum;
    
    private Boolean              brandDelFlag;
    
    private short                type;
    
    private String               message;
    
    private BigDecimal           discount;
    
    private Boolean              isDiscount;                              // 产品支持折扣
    
    /* ========================================= 临时字段 end ================================================ */
    public Boolean getIsDiscount()
    {
        return isDiscount;
    }
    
    public void setIsDiscount(Boolean isDiscount)
    {
        this.isDiscount = isDiscount;
    }
    
    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
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
    
    public java.lang.String getProductId()
    {
        return this.productId;
    }
    
    public void setProductId(java.lang.String productId)
    {
        this.productId = productId;
    }
    
    public java.lang.Integer getProductNum()
    {
        return this.productNum;
    }
    
    public void setProductNum(java.lang.Integer productNum)
    {
        this.productNum = productNum;
    }
    
    public java.math.BigDecimal getProductPrice()
    {
        return this.productPrice;
    }
    
    public void setProductPrice(java.math.BigDecimal productPrice)
    {
        this.productPrice = productPrice;
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
    
    public java.lang.String getSourceId()
    {
        return this.sourceId;
    }
    
    public void setSourceId(java.lang.String sourceId)
    {
        this.sourceId = sourceId;
    }
    
    public java.lang.Short getProductType()
    {
        return this.productType;
    }
    
    public void setProductType(java.lang.Short productType)
    {
        this.productType = productType;
    }
    
    public List<DealerShopers> getDealerShopersList()
    {
        return dealerShopersList;
    }
    
    public void setDealerShopersList(List<DealerShopers> dealerShopersList)
    {
        this.dealerShopersList = dealerShopersList;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public Short getBrandState()
    {
        return brandState;
    }
    
    public void setBrandState(Short brandState)
    {
        this.brandState = brandState;
    }
    
    public String getProductImage()
    {
        return productImage;
    }
    
    public void setProductImage(String productImage)
    {
        this.productImage = productImage;
    }
    
    public String getProductNo()
    {
        return productNo;
    }
    
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    
    public Short getProductStateSet()
    {
        return productStateSet;
    }
    
    public void setProductStateSet(Short productStateSet)
    {
        this.productStateSet = productStateSet;
    }
    
    public Boolean getProductDelFlag()
    {
        return productDelFlag;
    }
    
    public void setProductDelFlag(Boolean productDelFlag)
    {
        this.productDelFlag = productDelFlag;
    }
    
    public Boolean getIsSample()
    {
        return isSample;
    }
    
    public void setIsSample(Boolean isSample)
    {
        this.isSample = isSample;
    }
    
    public Boolean getIsCredit()
    {
        return isCredit;
    }
    
    public void setIsCredit(Boolean isCredit)
    {
        this.isCredit = isCredit;
    }
    
    public Boolean getIsPoint()
    {
        return isPoint;
    }
    
    public void setIsPoint(Boolean isPoint)
    {
        this.isPoint = isPoint;
    }
    
    public BigDecimal getPointPercent()
    {
        return pointPercent;
    }
    
    public void setPointPercent(BigDecimal pointPercent)
    {
        this.pointPercent = pointPercent;
    }
    
    public String getProductTitle()
    {
        return productTitle;
    }
    
    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }
    
    public Integer getStartNum()
    {
        return startNum;
    }
    
    public void setStartNum(Integer startNum)
    {
        this.startNum = startNum;
    }
    
    public Boolean getBrandDelFlag()
    {
        return brandDelFlag;
    }
    
    public void setBrandDelFlag(Boolean brandDelFlag)
    {
        this.brandDelFlag = brandDelFlag;
    }
    
    public short getType()
    {
        return type;
    }
    
    public void setType(short type)
    {
        this.type = type;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public BigDecimal getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }
    
    public BigDecimal getDiscountPrice()
    {
        return discountPrice;
    }
    
    public void setDiscountPrice(BigDecimal discountPrice)
    {
        this.discountPrice = discountPrice;
    }
}
