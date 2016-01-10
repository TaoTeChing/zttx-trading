/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商收藏标签 实体对象
 * <p>File：DealerFavtag.java</p>
 * <p>Title: DealerFavtag</p>
 * <p>Description:DealerFavtag</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerFavtag extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**标签名称*/
	private java.lang.String tagName;
	/**商品数量*/
	private java.lang.Integer productNum;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getTagName()
	{
		return this.tagName;
	}
	
	public void setTagName(java.lang.String tagName)
	{
		this.tagName = tagName;
	}
	
	public java.lang.Integer getProductNum()
	{
		return this.productNum;
	}
	
	public void setProductNum(java.lang.Integer productNum)
	{
		this.productNum = productNum;
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
	
}

