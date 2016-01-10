/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商产品进货计数 实体对象
 * <p>File：DealerOrderc.java</p>
 * <p>Title: DealerOrderc</p>
 * <p>Description:DealerOrderc</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerOrderc extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**经销商编号*/
    private java.lang.String  dealerId;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**品牌编号*/
    private java.lang.String  brandsId;
    
    /**商品编号*/
    private java.lang.String  productId;
    
    /**下单次数*/
    private java.lang.Integer subTime;
    
    /**下单数量*/
    private java.lang.Integer subNum;
    
    /**进货次数*/
    private java.lang.Integer joinNum;
    
    /**进货数量*/
    private java.lang.Integer orderNum;
    
    /**最后进货时间*/
    private java.lang.Long    orderTime;
    
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
    
    public java.lang.Integer getSubTime()
    {
        return this.subTime;
    }
    
    public void setSubTime(java.lang.Integer subTime)
    {
        this.subTime = subTime;
    }
    
    public java.lang.Integer getSubNum()
    {
        return this.subNum;
    }
    
    public void setSubNum(java.lang.Integer subNum)
    {
        this.subNum = subNum;
    }
    
    public java.lang.Integer getJoinNum()
    {
        return this.joinNum;
    }
    
    public void setJoinNum(java.lang.Integer joinNum)
    {
        this.joinNum = joinNum;
    }
    
    public java.lang.Integer getOrderNum()
    {
        return this.orderNum;
    }
    
    public void setOrderNum(java.lang.Integer orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public java.lang.Long getOrderTime()
    {
        return this.orderTime;
    }
    
    public void setOrderTime(java.lang.Long orderTime)
    {
        this.orderTime = orderTime;
    }
}
