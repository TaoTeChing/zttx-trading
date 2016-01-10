/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商展厅信息 实体对象
 * <p>File：BrandRoom.java</p>
 * <p>Title: BrandRoom</p>
 * <p>Description:BrandRoom</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandRoom extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**展厅名称*/
	private java.lang.String roomName;
	/**logo域名*/
	private java.lang.String domainName;
	/**logo原名称*/
	private java.lang.String logoPhoto;
	/**logo新名称*/
	private java.lang.String logoImage;
	/**品牌介绍*/
	private java.lang.String brandMark;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**编辑人员（当前登录的品牌商人员编号）*/
	private java.lang.String userId;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getRoomName()
	{
		return this.roomName;
	}
	
	public void setRoomName(java.lang.String roomName)
	{
		this.roomName = roomName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getLogoPhoto()
	{
		return this.logoPhoto;
	}
	
	public void setLogoPhoto(java.lang.String logoPhoto)
	{
		this.logoPhoto = logoPhoto;
	}
	
	public java.lang.String getLogoImage()
	{
		return this.logoImage;
	}
	
	public void setLogoImage(java.lang.String logoImage)
	{
		this.logoImage = logoImage;
	}
	
	public java.lang.String getBrandMark()
	{
		return this.brandMark;
	}
	
	public void setBrandMark(java.lang.String brandMark)
	{
		this.brandMark = brandMark;
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
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
}

