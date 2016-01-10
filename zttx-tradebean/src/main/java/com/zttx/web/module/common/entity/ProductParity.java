/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品比价表 实体对象
 * <p>File：ProductParity.java</p>
 * <p>Title: ProductParity</p>
 * <p>Description:ProductParity</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductParity extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**产品编号*/
	private java.lang.String productId;
	/**比价栏目编号*/
	private java.lang.String parityId;
	/**比价价格*/
	private java.math.BigDecimal price;
	/**链接地址*/
	private java.lang.String url;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**是否显示\n0：显示\n1：不显示*/
	private java.lang.Boolean isShow;
	/**关键字(不超过15个汉字)*/
	private java.lang.String keyWord;
	
	public java.lang.String getProductId()
	{
		return this.productId;
	}
	
	public void setProductId(java.lang.String productId)
	{
		this.productId = productId;
	}
	
	public java.lang.String getParityId()
	{
		return this.parityId;
	}
	
	public void setParityId(java.lang.String parityId)
	{
		this.parityId = parityId;
	}
	
	public java.math.BigDecimal getPrice()
	{
		return this.price;
	}
	
	public void setPrice(java.math.BigDecimal price)
	{
		this.price = price;
	}
	
	public java.lang.String getUrl()
	{
		return this.url;
	}
	
	public void setUrl(java.lang.String url)
	{
		this.url = url;
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
	
	public java.lang.Boolean getIsShow()
	{
		return this.isShow;
	}
	
	public void setIsShow(java.lang.Boolean isShow)
	{
		this.isShow = isShow;
	}
	
	public java.lang.String getKeyWord()
	{
		return this.keyWord;
	}
	
	public void setKeyWord(java.lang.String keyWord)
	{
		this.keyWord = keyWord;
	}
	
}

