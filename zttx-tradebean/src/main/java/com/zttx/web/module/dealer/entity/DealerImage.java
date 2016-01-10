/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商店铺招牌 实体对象
 * <p>File：DealerImage.java</p>
 * <p>Title: DealerImage</p>
 * <p>Description:DealerImage</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerImage extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**图片原名称*/
	private java.lang.String photoName;
	/**图片新名称*/
	private java.lang.String imageName;
	/**上传时间*/
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
	
}

