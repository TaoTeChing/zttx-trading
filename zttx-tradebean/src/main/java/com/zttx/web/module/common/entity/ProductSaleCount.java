/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * ProductSaleCount 实体对象
 * <p>File：ProductSaleCount.java</p>
 * <p>Title: ProductSaleCount</p>
 * <p>Description:ProductSaleCount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductSaleCount extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**refrenceId*/
	private java.lang.String refrenceId;
	/**产品id*/
	private java.lang.String productId;
	/**brandId*/
	private java.lang.String brandId;
	/**brandsId*/
	private java.lang.String brandsId;
	/**每月销量*/
	private java.lang.Integer monthSale;
	/**统计月份*/
	private java.lang.Long monthTime;
	/**createTime*/
	private java.lang.Long createTime;
	
	public java.lang.String getRefrenceId()
	{
		return this.refrenceId;
	}
	
	public void setRefrenceId(java.lang.String refrenceId)
	{
		this.refrenceId = refrenceId;
	}
	
	public java.lang.String getProductId()
	{
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId)
	{
		this.productId = productId;
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
	
	public java.lang.Integer getMonthSale()
	{
		return this.monthSale;
	}
	
	public void setMonthSale(java.lang.Integer monthSale)
	{
		this.monthSale = monthSale;
	}
	
	public java.lang.Long getMonthTime()
	{
		return this.monthTime;
	}
	
	public void setMonthTime(java.lang.Long monthTime)
	{
		this.monthTime = monthTime;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
}

