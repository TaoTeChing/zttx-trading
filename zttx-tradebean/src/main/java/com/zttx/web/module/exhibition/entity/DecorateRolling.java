/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 展厅图片自定义滚动表 实体对象
 * <p>File：DecorateRolling.java</p>
 * <p>Title: DecorateRolling</p>
 * <p>Description:DecorateRolling</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateRolling extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**配置编号*/
	private java.lang.String configId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**最多显示数*/
	private java.lang.Short maxCount;
	/**图片宽度*/
	private java.lang.Integer width;
	/**图片高度*/
	private java.lang.Integer height;
	/**滚动方式*/
	private java.lang.String rollMode;
	/**是否自动滚动*/
	private java.lang.Boolean isAuto;
	/**建档时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getConfigId()
	{
		return this.configId;
	}
	
	public void setConfigId(java.lang.String configId)
	{
		this.configId = configId;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.Short getMaxCount()
	{
		return this.maxCount;
	}
	
	public void setMaxCount(java.lang.Short maxCount)
	{
		this.maxCount = maxCount;
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
	
	public java.lang.String getRollMode()
	{
		return this.rollMode;
	}
	
	public void setRollMode(java.lang.String rollMode)
	{
		this.rollMode = rollMode;
	}
	
	public java.lang.Boolean getIsAuto()
	{
		return this.isAuto;
	}
	
	public void setIsAuto(java.lang.Boolean isAuto)
	{
		this.isAuto = isAuto;
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

