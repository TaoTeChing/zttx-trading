/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 运费设置表 实体对象
 * <p>File：BrandFreightSettings.java</p>
 * <p>Title: BrandFreightSettings</p>
 * <p>Description:BrandFreightSettings</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandFreightSettings extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**运费模板ID*/
	private java.lang.String templateId;
	/**运送方式（1：快递，2：物流，3：快递到付，4：物流到付）*/
	private java.lang.Integer carryType;
	/**运送方式名称*/
	private java.lang.String carryTypeName;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getTemplateId()
	{
		return this.templateId;
	}
	
	public void setTemplateId(java.lang.String templateId)
	{
		this.templateId = templateId;
	}
	
	public java.lang.Integer getCarryType()
	{
		return this.carryType;
	}
	
	public void setCarryType(java.lang.Integer carryType)
	{
		this.carryType = carryType;
	}
	
	public java.lang.String getCarryTypeName()
	{
		return this.carryTypeName;
	}
	
	public void setCarryTypeName(java.lang.String carryTypeName)
	{
		this.carryTypeName = carryTypeName;
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

