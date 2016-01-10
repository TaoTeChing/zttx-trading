/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商订单发货记录 实体对象
 * <p>File：OrderShipRecord.java</p>
 * <p>Title: OrderShipRecord</p>
 * <p>Description:OrderShipRecord</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class OrderShipRecord extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.String orderId;
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**发货数量*/
	private java.lang.Integer shipCount;
	/**物流公司名称*/
	private java.lang.String logisticName;
	/**单号*/
	private java.lang.String shipNumber;
	/**发货时间*/
	private java.lang.Long createTime;
	/**发货来源（0：交易平台，1：品牌ERP）*/
	private java.lang.Short sourceState;
	/**物流查询错误次数*/
	private java.lang.Integer errNumber;
	
	public java.lang.String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId)
	{
		this.orderId = orderId;
	}
	
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
	
	public java.lang.Integer getShipCount()
	{
		return this.shipCount;
	}
	
	public void setShipCount(java.lang.Integer shipCount)
	{
		this.shipCount = shipCount;
	}
	
	public java.lang.String getLogisticName()
	{
		return this.logisticName;
	}
	
	public void setLogisticName(java.lang.String logisticName)
	{
		this.logisticName = logisticName;
	}
	
	public java.lang.String getShipNumber()
	{
		return this.shipNumber;
	}
	
	public void setShipNumber(java.lang.String shipNumber)
	{
		this.shipNumber = shipNumber;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
	public java.lang.Short getSourceState()
	{
		return this.sourceState;
	}
	
	public void setSourceState(java.lang.Short sourceState)
	{
		this.sourceState = sourceState;
	}
	
	public java.lang.Integer getErrNumber()
	{
		return this.errNumber;
	}
	
	public void setErrNumber(java.lang.Integer errNumber)
	{
		this.errNumber = errNumber;
	}
	
}

