/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌模特视频 实体对象
 * <p>File：BrandsVideo.java</p>
 * <p>Title: BrandsVideo</p>
 * <p>Description:BrandsVideo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandsVideo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**视频地址*/
	private java.lang.String videoName;
	/**视频位置
*/
	private java.lang.String position;
	/**视频宽*/
	private java.lang.Integer width;
	/**视频高*/
	private java.lang.Integer height;
	/**建档时间*/
	private java.lang.Long createTime;
	/**上传时间*/
	private java.lang.Long uploadTime;
	/**上传者IP*/
	private java.lang.Integer uploadIp;
	
	private String brandsName;
	
	public String getBrandsName() {
		return brandsName;
	}

	public void setBrandsName(String brandsName) {
		this.brandsName = brandsName;
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
	
	public java.lang.String getVideoName()
	{
		return this.videoName;
	}
	
	public void setVideoName(java.lang.String videoName)
	{
		this.videoName = videoName;
	}
	
	public java.lang.String getPosition()
	{
		return this.position;
	}
	
	public void setPosition(java.lang.String position)
	{
		this.position = position;
	}
	
	public java.lang.Integer getWidth()
	{
		return this.width;
	}
	
	public void setWidth(java.lang.Integer width)
	{
		this.width = width;
	}
	
	public java.lang.Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(java.lang.Integer height)
	{
		this.height = height;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
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

