/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商购买的服务 实体对象
 * <p>File：BrandBuyService.java</p>
 * <p>Title: BrandBuyService</p>
 * <p>Description:BrandBuyService</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandBuyService extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**服务编号*/
	private java.lang.String serviceId;
	/**服务类别*/
	private java.lang.Short servicerCate;
	/**1：续期
            2：增加数量*/
	private java.lang.Short chargType;
	/**购买时间*/
	private java.lang.Long buyTime;
	/**开始时间*/
	private java.lang.Long beginTime;
	/**结束时间*/
	private java.lang.Long endTime;
	/**购买金额*/
	private java.math.BigDecimal buyMoney;
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
	
	public java.lang.String getBrandName()
	{
		return this.brandName;
	}
	
	public void setBrandName(java.lang.String brandName)
	{
		this.brandName = brandName;
	}
	
	public java.lang.String getServiceId()
	{
		return this.serviceId;
	}
	
	public void setServiceId(java.lang.String serviceId)
	{
		this.serviceId = serviceId;
	}
	
	public java.lang.Short getServicerCate()
	{
		return this.servicerCate;
	}
	
	public void setServicerCate(java.lang.Short servicerCate)
	{
		this.servicerCate = servicerCate;
	}
	
	public java.lang.Short getChargType()
	{
		return this.chargType;
	}
	
	public void setChargType(java.lang.Short chargType)
	{
		this.chargType = chargType;
	}
	
	public java.lang.Long getBuyTime()
	{
		return this.buyTime;
	}
	
	public void setBuyTime(java.lang.Long buyTime)
	{
		this.buyTime = buyTime;
	}
	
	public java.lang.Long getBeginTime()
	{
		return this.beginTime;
	}
	
	public void setBeginTime(java.lang.Long beginTime)
	{
		this.beginTime = beginTime;
	}
	
	public java.lang.Long getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(java.lang.Long endTime)
	{
		this.endTime = endTime;
	}
	
	public java.math.BigDecimal getBuyMoney()
	{
		return this.buyMoney;
	}
	
	public void setBuyMoney(java.math.BigDecimal buyMoney)
	{
		this.buyMoney = buyMoney;
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

