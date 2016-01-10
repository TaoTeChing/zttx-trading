/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌预订模板 实体对象
 * <p>File：BrandTemplate.java</p>
 * <p>Title: BrandTemplate</p>
 * <p>Description:BrandTemplate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandTemplate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**模板名称*/
	private java.lang.String templateName;
	/**订货时间*/
	private java.lang.Long orderStart;
	/**订货结束时间*/
	private java.lang.Long orderEnd;
	/**起批量*/
	private java.lang.Integer startNum;
	/**订货量*/
	private java.lang.Integer orderNum;
	/**出货开始时间*/
	private java.lang.Long outStart;
	/**出货结束时间*/
	private java.lang.Long outEnd;
	/**订单选择*/
	private java.lang.Short orderSelect;
	/**订单金额*/
	private java.lang.Short orderMoney;
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
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getTemplateName()
	{
		return this.templateName;
	}
	
	public void setTemplateName(java.lang.String templateName)
	{
		this.templateName = templateName;
	}
	
	public java.lang.Long getOrderStart()
	{
		return this.orderStart;
	}
	
	public void setOrderStart(java.lang.Long orderStart)
	{
		this.orderStart = orderStart;
	}
	
	public java.lang.Long getOrderEnd()
	{
		return this.orderEnd;
	}
	
	public void setOrderEnd(java.lang.Long orderEnd)
	{
		this.orderEnd = orderEnd;
	}
	
	public java.lang.Integer getStartNum()
	{
		return this.startNum;
	}
	
	public void setStartNum(java.lang.Integer startNum)
	{
		this.startNum = startNum;
	}
	
	public java.lang.Integer getOrderNum()
	{
		return this.orderNum;
	}
	
	public void setOrderNum(java.lang.Integer orderNum)
	{
		this.orderNum = orderNum;
	}
	
	public java.lang.Long getOutStart()
	{
		return this.outStart;
	}
	
	public void setOutStart(java.lang.Long outStart)
	{
		this.outStart = outStart;
	}
	
	public java.lang.Long getOutEnd()
	{
		return this.outEnd;
	}
	
	public void setOutEnd(java.lang.Long outEnd)
	{
		this.outEnd = outEnd;
	}
	
	public java.lang.Short getOrderSelect()
	{
		return this.orderSelect;
	}
	
	public void setOrderSelect(java.lang.Short orderSelect)
	{
		this.orderSelect = orderSelect;
	}
	
	public java.lang.Short getOrderMoney()
	{
		return this.orderMoney;
	}
	
	public void setOrderMoney(java.lang.Short orderMoney)
	{
		this.orderMoney = orderMoney;
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

