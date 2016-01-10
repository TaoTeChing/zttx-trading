/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商浏览记录 实体对象
 * <p>File：DealerVisited.java</p>
 * <p>Title: DealerVisited</p>
 * <p>Description:DealerVisited</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerVisited extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**品牌商主帐号编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**logo所在的服务器域名*/
	private java.lang.String domainName;
	/**logo文件名称*/
	private java.lang.String logoName;
	/**地区编码*/
	private java.lang.Integer areaNo;
	/**品牌商省份名称*/
	private java.lang.String provinceName;
	/**品牌商城市名称*/
	private java.lang.String cityName;
	/**品牌商区域名称*/
	private java.lang.String areaName;
	/**浏览次数*/
	private java.lang.Integer viewNum;
	/**最后浏览时间*/
	private java.lang.Long viewTime;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
	}
	
	public java.lang.String getLogoName()
	{
		return this.logoName;
	}
	
	public void setLogoName(java.lang.String logoName)
	{
		this.logoName = logoName;
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

