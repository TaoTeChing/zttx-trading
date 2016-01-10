/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.util.Date;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品调价明细表 实体对象
 * <p>File：ProductAdjustDetail.java</p>
 * <p>Title: ProductAdjustDetail</p>
 * <p>Description:ProductAdjustDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductAdjustDetail extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private String brandId;
	/**品牌编号*/
	private String brandsId;
	/**产品编号*/
	private String productId;
	/**SKU编号*/
	private String proSkuId;
	/**调整前价格*/
	private Long beforePrice;
	/**调整前比例*/
	private Double beforePercent;
	/**调整后价格*/
	private Long afterPrice;
	/**调整后比例*/
	private Double afterPercent;
	/**调整时间*/
	private Long createTime;
	//生效时间
	private Long effTime;
	
	//----以下为查询条件-----------------------------//
	private String createTimeStart;
	private String createTimeEnd;
	private String effectiveTimeStart;
	private String effectiveTimeEnd;
	private Long createTimeStartLong;
    private Long createTimeEndLong;
    private Long effectiveTimeStartLong;
    private Long effectiveTimeEndLong;
	private String productNo;
	private String productName;
	private String color;
	private String size;

	
	
	public Long getEffTime()
    {
        return effTime;
    }

    public void setEffTime(Long effTime)
    {
        this.effTime = effTime;
    }

    public Long getCreateTimeStartLong()
    {
        return createTimeStartLong;
    }

    public void setCreateTimeStartLong(Long createTimeStartLong)
    {
        this.createTimeStartLong = createTimeStartLong;
    }

    public Long getCreateTimeEndLong()
    {
        return createTimeEndLong;
    }

    public void setCreateTimeEndLong(Long createTimeEndLong)
    {
        this.createTimeEndLong = createTimeEndLong;
    }

    public Long getEffectiveTimeStartLong()
    {
        return effectiveTimeStartLong;
    }

    public void setEffectiveTimeStartLong(Long effectiveTimeStartLong)
    {
        this.effectiveTimeStartLong = effectiveTimeStartLong;
    }

    public Long getEffectiveTimeEndLong()
    {
        return effectiveTimeEndLong;
    }

    public void setEffectiveTimeEndLong(Long effectiveTimeEndLong)
    {
        this.effectiveTimeEndLong = effectiveTimeEndLong;
    }

    
    public String getCreateTimeStart()
    {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart)
    {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd()
    {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd)
    {
        this.createTimeEnd = createTimeEnd;
    }

    public String getEffectiveTimeStart()
    {
        return effectiveTimeStart;
    }

    public void setEffectiveTimeStart(String effectiveTimeStart)
    {
        this.effectiveTimeStart = effectiveTimeStart;
    }

    public String getEffectiveTimeEnd()
    {
        return effectiveTimeEnd;
    }

    public void setEffectiveTimeEnd(String effectiveTimeEnd)
    {
        this.effectiveTimeEnd = effectiveTimeEnd;
    }

    public String getProductNo()
    {
        return productNo;
    }

    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getBrandId()
	{
		return this.brandId;
	}

	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}

	public String getBrandsId()
	{
		return this.brandsId;
	}

	public void setBrandsId(String brandsId)
	{
		this.brandsId = brandsId;
	}

	public String getProductId()
	{
		return this.productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getProSkuId()
	{
		return this.proSkuId;
	}

	public void setProSkuId(String proSkuId)
	{
		this.proSkuId = proSkuId;
	}

	public Long getBeforePrice()
	{
		return this.beforePrice;
	}

	public void setBeforePrice(Long beforePrice)
	{
		this.beforePrice = beforePrice;
	}

	public Double getBeforePercent()
	{
		return this.beforePercent;
	}

	public void setBeforePercent(Double beforePercent)
	{
		this.beforePercent = beforePercent;
	}

	public Long getAfterPrice()
	{
		return this.afterPrice;
	}

	public void setAfterPrice(Long afterPrice)
	{
		this.afterPrice = afterPrice;
	}

	public Double getAfterPercent()
	{
		return this.afterPercent;
	}

	public void setAfterPercent(Double afterPercent)
	{
		this.afterPercent = afterPercent;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}
	
}

