/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 展厅自定义滚动图片内容 实体对象
 * <p>File：DecorateImage.java</p>
 * <p>Title: DecorateImage</p>
 * <p>Description:DecorateImage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateImage extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**滚动配置编号*/
	private java.lang.String rollId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**alt描述*/
	private java.lang.String altName;
	/**域名*/
	private java.lang.String domainName;
	/**图片地址*/
	private java.lang.String imageUrl;
	/**链接地址*/
	private java.lang.String hrefText;
	/**排序编号*/
	private java.lang.Integer showOrder;
	/**建档时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getRollId()
	{
		return this.rollId;
	}
	
	public void setRollId(java.lang.String rollId)
	{
		this.rollId = rollId;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getAltName()
	{
		return this.altName;
	}
	
	public void setAltName(java.lang.String altName)
	{
		this.altName = altName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getImageUrl()
	{
		return this.imageUrl;
	}
	
	public void setImageUrl(java.lang.String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public java.lang.String getHrefText()
	{
		return this.hrefText;
	}
	
	public void setHrefText(java.lang.String hrefText)
	{
		this.hrefText = hrefText;
	}
	
	public java.lang.Integer getShowOrder()
	{
		return this.showOrder;
	}
	
	public void setShowOrder(java.lang.Integer showOrder)
	{
		this.showOrder = showOrder;
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

