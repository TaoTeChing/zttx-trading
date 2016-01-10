/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商物流公司 实体对象
 * <p>File：BrandLogistics.java</p>
 * <p>Title: BrandLogistics</p>
 * <p>Description:BrandLogistics</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandLogistics extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商主账户编号*/
	private java.lang.String brandId;
	/**物流公司名称*/
	private java.lang.String logisticName;
	/**logisticType*/
	private java.lang.Integer logisticType;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getLogisticName()
	{
		return this.logisticName;
	}
	
	public void setLogisticName(java.lang.String logisticName)
	{
		this.logisticName = logisticName;
	}
	
	public java.lang.Integer getLogisticType()
	{
		return this.logisticType;
	}
	
	public void setLogisticType(java.lang.Integer logisticType)
	{
		this.logisticType = logisticType;
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

