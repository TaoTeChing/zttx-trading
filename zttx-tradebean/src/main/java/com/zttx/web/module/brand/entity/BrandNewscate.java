/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌资讯类别 实体对象
 * <p>File：BrandNewscate.java</p>
 * <p>Title: BrandNewscate</p>
 * <p>Description:BrandNewscate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandNewscate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**类别名称*/
	private java.lang.String cateName;
	/**类别说明*/
	private java.lang.String cateMark;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getCateName()
	{
		return this.cateName;
	}
	
	public void setCateName(java.lang.String cateName)
	{
		this.cateName = cateName;
	}
	
	public java.lang.String getCateMark()
	{
		return this.cateMark;
	}
	
	public void setCateMark(java.lang.String cateMark)
	{
		this.cateMark = cateMark;
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

