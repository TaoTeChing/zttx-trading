/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌形象照片 实体对象
 * <p>File：BrandPhoto.java</p>
 * <p>Title: BrandPhoto</p>
 * <p>Description:BrandPhoto</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandPhoto extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandesId;
	/**domainName*/
	private java.lang.String domainName;
	/**照片原名称*/
	private java.lang.String photoName;
	/**照片新名称*/
	private java.lang.String imageName;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**建档IP*/
	private java.lang.Integer createIP;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getBrandesId()
	{
		return this.brandesId;
	}
	
	public void setBrandesId(java.lang.String brandesId)
	{
		this.brandesId = brandesId;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getPhotoName()
	{
		return this.photoName;
	}
	
	public void setPhotoName(java.lang.String photoName)
	{
		this.photoName = photoName;
	}
	
	public java.lang.String getImageName()
	{
		return this.imageName;
	}
	
	public void setImageName(java.lang.String imageName)
	{
		this.imageName = imageName;
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
	
	public java.lang.Integer getCreateIP()
	{
		return this.createIP;
	}
	
	public void setCreateIP(java.lang.Integer createIP)
	{
		this.createIP = createIP;
	}
	
}

