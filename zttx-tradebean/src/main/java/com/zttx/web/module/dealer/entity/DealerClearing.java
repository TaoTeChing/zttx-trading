/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

import java.math.BigDecimal;

/**
 * 工厂店品牌信息结算表 实体对象
 * <p>File：DealerClearing.java</p>
 * <p>Title: DealerClearing</p>
 * <p>Description:DealerClearing</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerClearing extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**经销商编号*/
	private java.lang.String dealerId;
    /*品牌编号*/
	private java.lang.String brandsId;
	/**结算状态(0:未结算,1:已结算)*/
	private java.lang.Boolean clearingStatus;
	/**结算时间*/
	private java.lang.Long clearingTime;
	/**销售金额	*/
	private java.math.BigDecimal salesAmount;
	/**结算金额*/
	private java.math.BigDecimal clearingAmount;
	/**结算-销量*/
	private java.lang.Long clearingVolume;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;

	private Boolean isClearingAmount;



	 /*========================================= 临时字段 [@author易永耀] begin================================================*/

	private String  startTime;

	private String  endTime;

	private BigDecimal hasClearingAmount; //已付金额

	   /*========================================= 临时字段 end ================================================*/

 /*========================================= 临时字段 get/set [@author易永耀] begin================================================*/

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getHasClearingAmount() {
		return hasClearingAmount;
	}

	public void setHasClearingAmount(BigDecimal hasClearingAmount) {
		this.hasClearingAmount = hasClearingAmount;
	}

	/*========================================= 临时字段 get/set end ================================================*/

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

	public String getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(String brandsId) {
		this.brandsId = brandsId;
	}

	public java.lang.Boolean getClearingStatus()
	{
		return this.clearingStatus;
	}
	
	public void setClearingStatus(java.lang.Boolean clearingStatus)
	{
		this.clearingStatus = clearingStatus;
	}
	
	public java.lang.Long getClearingTime()
	{
		return this.clearingTime;
	}
	
	public void setClearingTime(java.lang.Long clearingTime)
	{
		this.clearingTime = clearingTime;
	}
	
	public java.math.BigDecimal getSalesAmount()
	{
		return this.salesAmount;
	}
	
	public void setSalesAmount(java.math.BigDecimal salesAmount)
	{
		this.salesAmount = salesAmount;
	}
	
	public java.math.BigDecimal getClearingAmount()
	{
		return this.clearingAmount;
	}
	
	public void setClearingAmount(java.math.BigDecimal clearingAmount)
	{
		this.clearingAmount = clearingAmount;
	}
	
	public java.lang.Long getClearingVolume()
	{
		return this.clearingVolume;
	}
	
	public void setClearingVolume(java.lang.Long clearingVolume)
	{
		this.clearingVolume = clearingVolume;
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

	public Boolean getIsClearingAmount() {
		return isClearingAmount;
	}

	public void setIsClearingAmount(Boolean isClearingAmount) {
		this.isClearingAmount = isClearingAmount;
	}
}

