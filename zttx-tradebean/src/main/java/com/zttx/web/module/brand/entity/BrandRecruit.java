/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌招募书 实体对象
 * <p>File：BrandRecruit.java</p>
 * <p>Title: BrandRecruit</p>
 * <p>Description:BrandRecruit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandRecruit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**招募书标题*/
	private java.lang.String recruitTitle;
	/**经营品牌*/
	private java.lang.Short dealBrand;
	/**开店经验*/
	private java.lang.Short dealExper;
	/**实体店*/
	private java.lang.Short dealShop;
	/**经营年限*/
	private java.lang.Short dealYear;
	/**招募书状态*/
	private java.lang.Short recruitState;
	/**招募内容*/
	private java.lang.String recruitText;
	/**建档时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**编辑人员*/
	private java.lang.String userId;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getRecruitTitle()
	{
		return this.recruitTitle;
	}
	
	public void setRecruitTitle(java.lang.String recruitTitle)
	{
		this.recruitTitle = recruitTitle;
	}
	
	public java.lang.Short getDealBrand()
	{
		return this.dealBrand;
	}
	
	public void setDealBrand(java.lang.Short dealBrand)
	{
		this.dealBrand = dealBrand;
	}
	
	public java.lang.Short getDealExper()
	{
		return this.dealExper;
	}
	
	public void setDealExper(java.lang.Short dealExper)
	{
		this.dealExper = dealExper;
	}
	
	public java.lang.Short getDealShop()
	{
		return this.dealShop;
	}
	
	public void setDealShop(java.lang.Short dealShop)
	{
		this.dealShop = dealShop;
	}
	
	public java.lang.Short getDealYear()
	{
		return this.dealYear;
	}
	
	public void setDealYear(java.lang.Short dealYear)
	{
		this.dealYear = dealYear;
	}
	
	public java.lang.Short getRecruitState()
	{
		return this.recruitState;
	}
	
	public void setRecruitState(java.lang.Short recruitState)
	{
		this.recruitState = recruitState;
	}
	
	public java.lang.String getRecruitText()
	{
		return this.recruitText;
	}
	
	public void setRecruitText(java.lang.String recruitText)
	{
		this.recruitText = recruitText;
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
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
}

