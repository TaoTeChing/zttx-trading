/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商产品收藏 实体对象
 * <p>File：DealerFavorite.java</p>
 * <p>Title: DealerFavorite</p>
 * <p>Description:DealerFavorite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerFavorite extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**产品编号*/
	private java.lang.String productId;
	/**收藏标签编号*/
	private java.lang.String tagId;
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
	
	public java.lang.String getTagId()
	{
		return this.tagId;
	}
	
	public void setTagId(java.lang.String tagId)
	{
		this.tagId = tagId;
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

