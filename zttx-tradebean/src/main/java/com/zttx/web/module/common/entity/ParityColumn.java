/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 比价栏目表 实体对象
 * <p>File：ParityColumn.java</p>
 * <p>Title: ParityColumn</p>
 * <p>Description:ParityColumn</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ParityColumn extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**比价栏目名称*/
	private java.lang.String name;
	/**是否必填：　0、否　1、是*/
	private java.lang.Short isRequired;
	/**排序号，数值越大越排在前面*/
	private java.lang.Integer sort;
	/**创建时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	/**样式*/
	private java.lang.String style;
	/**颜色*/
	private java.lang.String color;
	
	public java.lang.String getName()
	{
		return this.name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.Short getIsRequired()
	{
		return this.isRequired;
	}
	
	public void setIsRequired(java.lang.Short isRequired)
	{
		this.isRequired = isRequired;
	}
	
	public java.lang.Integer getSort()
	{
		return this.sort;
	}
	
	public void setSort(java.lang.Integer sort)
	{
		this.sort = sort;
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
	
	public java.lang.String getStyle()
	{
		return this.style;
	}
	
	public void setStyle(java.lang.String style)
	{
		this.style = style;
	}
	
	public java.lang.String getColor()
	{
		return this.color;
	}
	
	public void setColor(java.lang.String color)
	{
		this.color = color;
	}
	
}

