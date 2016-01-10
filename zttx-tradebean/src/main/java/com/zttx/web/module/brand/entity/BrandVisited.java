/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商浏览记录 实体对象
 * <p>File：BrandVisited.java</p>
 * <p>Title: BrandVisited</p>
 * <p>Description:BrandVisited</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandVisited extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**经销商名称*/
	private java.lang.String dealerName;
	/**地区编码*/
	private java.lang.Integer areaNo;
	/**经销商所在省份名称*/
	private java.lang.String provinceName;
	/**经销商所在城市名称*/
	private java.lang.String cityName;
	/**经销商所在区域名称*/
	private java.lang.String areaName;
	/**查看次数*/
	private java.lang.Integer viewNum;
	/**最后查看时间*/
	private java.lang.Long viewTime;
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getBrandName()
	{
		return this.brandName;
	}
	
	public void setBrandName(java.lang.String brandName)
	{
		this.brandName = brandName;
	}
	
	public java.lang.String getDealerName()
	{
		return this.dealerName;
	}
	
	public void setDealerName(java.lang.String dealerName)
	{
		this.dealerName = dealerName;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.String getProvinceName()
	{
		return this.provinceName;
	}
	
	public void setProvinceName(java.lang.String provinceName)
	{
		this.provinceName = provinceName;
	}
	
	public java.lang.String getCityName()
	{
		return this.cityName;
	}
	
	public void setCityName(java.lang.String cityName)
	{
		this.cityName = cityName;
	}
	
	public java.lang.String getAreaName()
	{
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String areaName)
	{
		this.areaName = areaName;
	}
	
	public java.lang.Integer getViewNum()
	{
		return this.viewNum;
	}
	
	public void setViewNum(java.lang.Integer viewNum)
	{
		this.viewNum = viewNum;
	}
	
	public java.lang.Long getViewTime()
	{
		return this.viewTime;
	}
	
	public void setViewTime(java.lang.Long viewTime)
	{
		this.viewTime = viewTime;
	}
	
}

