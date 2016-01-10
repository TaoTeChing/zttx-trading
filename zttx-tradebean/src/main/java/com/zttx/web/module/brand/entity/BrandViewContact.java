/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商查看经销商联系信息 实体对象
 * <p>File：BrandViewContact.java</p>
 * <p>Title: BrandViewContact</p>
 * <p>Description:BrandViewContact</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandViewContact extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**经销商编号*/
	private java.lang.String dealerId;
	/**查看时间*/
	private java.lang.Long viewTime;
	/**查看类型（1：主动浏览，2：经销商申请，3：天下商邦人员撮合）*/
	private java.lang.Short viewType;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.Long getViewTime()
	{
		return this.viewTime;
	}
	
	public void setViewTime(java.lang.Long viewTime)
	{
		this.viewTime = viewTime;
	}
	
	public java.lang.Short getViewType()
	{
		return this.viewType;
	}
	
	public void setViewType(java.lang.Short viewType)
	{
		this.viewType = viewType;
	}
	
}

