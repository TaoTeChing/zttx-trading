/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商收藏的经销商 实体对象
 * <p>File：BrandFavorite.java</p>
 * <p>Title: BrandFavorite</p>
 * <p>Description:BrandFavorite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandFavorite extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**收藏时间*/
	private java.lang.Long collectTime;
	
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
	
	public java.lang.Long getCollectTime()
	{
		return this.collectTime;
	}
	
	public void setCollectTime(java.lang.Long collectTime)
	{
		this.collectTime = collectTime;
	}
	
}

