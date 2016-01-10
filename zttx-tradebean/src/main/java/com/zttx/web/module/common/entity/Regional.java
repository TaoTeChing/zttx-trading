/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 全国区域信息 实体对象
 * <p>File：Regional.java</p>
 * <p>Title: Regional</p>
 * <p>Description:Regional</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class Regional extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**区域编码*/
	private java.lang.Integer areaNo;
	/**区域名称*/
	private java.lang.String areaName;
	/**上层编码*/
	private java.lang.String areaParent;
	/**所在层级*/
	private java.lang.Short areaLevel;
	/**区域排序*/
	private java.lang.Integer areaOrder;
	/**createTime*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getAreaName()
	{
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName)
	{
		this.areaName = areaName;
	}
	
	public java.lang.String getAreaParent()
	{
		return this.areaParent;
	}
	
	public void setAreaParent(java.lang.String areaParent)
	{
		this.areaParent = areaParent;
	}
	
	public java.lang.Short getAreaLevel()
	{
		return this.areaLevel;
	}
	
	public void setAreaLevel(java.lang.Short areaLevel)
	{
		this.areaLevel = areaLevel;
	}
	
	public java.lang.Integer getAreaOrder()
	{
		return this.areaOrder;
	}
	
	public void setAreaOrder(java.lang.Integer areaOrder)
	{
		this.areaOrder = areaOrder;
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

