/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 运费区域表 实体对象
 * <p>File：BrandFreightRegion.java</p>
 * <p>Title: BrandFreightRegion</p>
 * <p>Description:BrandFreightRegion</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandFreightRegion extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**运费模板ID*/
	private java.lang.String templateId;
	/**明细ID*/
	private java.lang.String detailId;
	/**收货地区编号*/
	private java.lang.Integer areaNo;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**areaName*/
	private java.lang.String areaName;
	
	public java.lang.String getTemplateId()
	{
		return this.templateId;
	}
	
	public void setTemplateId(java.lang.String templateId)
	{
		this.templateId = templateId;
	}
	
	public java.lang.String getDetailId()
	{
		return this.detailId;
	}
	
	public void setDetailId(java.lang.String detailId)
	{
		this.detailId = detailId;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
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
	
	public java.lang.String getAreaName()
	{
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName)
	{
		this.areaName = areaName;
	}
	
}

