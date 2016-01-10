/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商收藏的品牌 实体对象
 * <p>File：DealerFavbrands.java</p>
 * <p>Title: DealerFavbrands</p>
 * <p>Description:DealerFavbrands</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerFavbrands extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌编号*/
	private java.lang.String brandsId;
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
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
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

