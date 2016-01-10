/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商收藏的品牌商 实体对象
 * <p>File：DealerFavbrand.java</p>
 * <p>Title: DealerFavbrand</p>
 * <p>Description:DealerFavbrand</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerFavbrand extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**收藏时间*/
	private java.lang.Long collectTime;
	
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
	
	public java.lang.Long getCollectTime()
	{
		return this.collectTime;
	}
	
	public void setCollectTime(java.lang.Long collectTime)
	{
		this.collectTime = collectTime;
	}
	
}

