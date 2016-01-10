/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商文档类别信息 实体对象
 * <p>File：BrandDoccate.java</p>
 * <p>Title: BrandDoccate</p>
 * <p>Description:BrandDoccate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandDoccate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**类别名称*/
	private java.lang.String cateName;
	/**建档时间*/
	private java.lang.Long createTime;
	/**排序字段*/
	private java.lang.Integer orderId;
	
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
	
	public java.lang.String getCateName()
	{
		return this.cateName;
	}
	
	public void setCateName(java.lang.String cateName)
	{
		this.cateName = cateName;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
	public java.lang.Integer getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.Integer orderId)
	{
		this.orderId = orderId;
	}
	
}

