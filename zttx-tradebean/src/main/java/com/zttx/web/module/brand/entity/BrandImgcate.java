/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商图片分类信息 实体对象
 * <p>File：BrandImgcate.java</p>
 * <p>Title: BrandImgcate</p>
 * <p>Description:BrandImgcate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandImgcate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**类别名称*/
	private java.lang.String cateName;
	/**上层编号*/
	private java.lang.String parentId;
	/**排序编号*/
	private java.lang.Integer cateOrder;
	/**是否默认分类（0：否，1：是）*/
	private java.lang.Boolean cateDefault;
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
	
	public java.lang.String getCateName()
	{
		return this.cateName;
	}
	
	public void setCateName(java.lang.String cateName)
	{
		this.cateName = cateName;
	}
	
	public java.lang.String getParentId()
	{
		return this.parentId;
	}
	
	public void setParentId(java.lang.String parentId)
	{
		this.parentId = parentId;
	}
	
	public java.lang.Integer getCateOrder()
	{
		return this.cateOrder;
	}
	
	public void setCateOrder(java.lang.Integer cateOrder)
	{
		this.cateOrder = cateOrder;
	}
	
	public java.lang.Boolean getCateDefault()
	{
		return this.cateDefault;
	}
	
	public void setCateDefault(java.lang.Boolean cateDefault)
	{
		this.cateDefault = cateDefault;
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

