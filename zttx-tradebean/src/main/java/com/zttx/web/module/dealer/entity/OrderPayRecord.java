/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商订单支付历史记录 实体对象
 * <p>File：OrderPayRecord.java</p>
 * <p>Title: OrderPayRecord</p>
 * <p>Description:OrderPayRecord</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class OrderPayRecord extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.String orderId;
	/**经销商编号（支付人编号）*/
	private java.lang.String dealerId;
	/**经销商名称*/
	private java.lang.String dealerName;
	/**支付编号（充值编号）*/
	private java.lang.String recharegeId;
	/**支付金额*/
	private java.math.BigDecimal payAmount;
	/**创建时间
*/
	private java.lang.Long createTime;
	
	public java.lang.String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.String orderId)
	{
		this.orderId = orderId;
	}
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getDealerName()
	{
		return this.dealerName;
	}
	
	public void setDealerName(java.lang.String dealerName)
	{
		this.dealerName = dealerName;
	}
	
	public java.lang.String getRecharegeId()
	{
		return this.recharegeId;
	}
	
	public void setRecharegeId(java.lang.String recharegeId)
	{
		this.recharegeId = recharegeId;
	}
	
	public java.math.BigDecimal getPayAmount()
	{
		return this.payAmount;
	}
	
	public void setPayAmount(java.math.BigDecimal payAmount)
	{
		this.payAmount = payAmount;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
}

