/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商经销网络图片信息 实体对象
 * <p>File：BrandNetimg.java</p>
 * <p>Title: BrandNetimg</p>
 * <p>Description:BrandNetimg</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandNetimg extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商网络编号*/
	private java.lang.String networkId;
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**所在域名*/
	private java.lang.String domainName;
	/**图片原名称*/
	private java.lang.String photoName;
	/**图片新名称*/
	private java.lang.String imageName;
	/**是否主图（0：非主图，1：主图）*/
	private java.lang.Boolean mainFlag;
	/**上传时间*/
	private java.lang.Long uploadTime;
	/**上传者IP*/
	private java.lang.Integer uploadIp;
	
	public java.lang.String getNetworkId()
	{
		return this.networkId;
	}
	
	public void setNetworkId(java.lang.String networkId)
	{
		this.networkId = networkId;
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
	
	public java.lang.Boolean getMainFlag()
	{
		return this.mainFlag;
	}
	
	public void setMainFlag(java.lang.Boolean mainFlag)
	{
		this.mainFlag = mainFlag;
	}
	
	public java.lang.Long getUploadTime()
	{
		return this.uploadTime;
	}
	
	public void setUploadTime(java.lang.Long uploadTime)
	{
		this.uploadTime = uploadTime;
	}
	
	public java.lang.Integer getUploadIp()
	{
		return this.uploadIp;
	}
	
	public void setUploadIp(java.lang.Integer uploadIp)
	{
		this.uploadIp = uploadIp;
	}
	
}

