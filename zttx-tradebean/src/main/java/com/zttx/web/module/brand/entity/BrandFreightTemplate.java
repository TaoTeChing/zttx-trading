/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

import java.util.List;
import java.util.Map;

/**
 * 运费模板表 实体对象
 * <p>File：BrandFreightTemplate.java</p>
 * <p>Title: BrandFreightTemplate</p>
 * <p>Description:BrandFreightTemplate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandFreightTemplate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商ID（推荐模板值为null）*/
	private java.lang.String brandId;
	/**模板名称*/
	private java.lang.String name;
	/**发货地区编号*/
	private java.lang.Integer areaNo;
	/**是否默认（0：否，1：是）*/
	private java.lang.Short isDefault;
	/**是否为推荐模板（0：否，1：是）*/
	private java.lang.Short isRecommend;
	/**创建时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;


	/****** 非数据库字段    *****/
	private String expressCollect;
	private String logisticsCollect;
	private String fullAreaName;
	List<Map<String, Object>> listMapDetail;


	public java.lang.String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(java.lang.String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.lang.String getName()
	{
		return this.name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	public java.lang.Integer getAreaNo()
	{
		return this.areaNo;
	}
	
	public void setAreaNo(java.lang.Integer areaNo)
	{
		this.areaNo = areaNo;
	}
	
	public java.lang.Short getIsDefault()
	{
		return this.isDefault;
	}
	
	public void setIsDefault(java.lang.Short isDefault)
	{
		this.isDefault = isDefault;
	}
	
	public java.lang.Short getIsRecommend()
	{
		return this.isRecommend;
	}
	
	public void setIsRecommend(java.lang.Short isRecommend)
	{
		this.isRecommend = isRecommend;
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

	public String getFullAreaName() {
		return fullAreaName;
	}

	public void setFullAreaName(String fullAreaName) {
		this.fullAreaName = fullAreaName;
	}

	public List<Map<String, Object>> getListMapDetail() {
		return listMapDetail;
	}

	public void setListMapDetail(List<Map<String, Object>> listMapDetail) {
		this.listMapDetail = listMapDetail;
	}

	public String getExpressCollect() {
		return expressCollect;
	}

	public void setExpressCollect(String expressCollect) {
		this.expressCollect = expressCollect;
	}

	public String getLogisticsCollect() {
		return logisticsCollect;
	}

	public void setLogisticsCollect(String logisticsCollect) {
		this.logisticsCollect = logisticsCollect;
	}
}

