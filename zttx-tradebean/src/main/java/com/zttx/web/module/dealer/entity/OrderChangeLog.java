/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 订单金额修改记录 实体对象
 * <p>File：OrderChangeLog.java</p>
 * <p>Title: OrderChangeLog</p>
 * <p>Description:OrderChangeLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class OrderChangeLog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	private java.lang.Long orderId;
	/**帐户*/
	private java.lang.String account;
	/**操作内容*/
	private java.lang.String content;
	/**创建IP*/
	private java.lang.Integer createIp;
	/**创建时间*/
	private java.lang.Long createTime;
	
	public java.lang.Long getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(java.lang.Long orderId)
	{
		this.orderId = orderId;
	}
	
	public java.lang.String getAccount()
	{
		return this.account;
	}
	
	public void setAccount(java.lang.String account)
	{
		this.account = account;
	}
	
	public java.lang.String getContent()
	{
		return this.content;
	}
	
	public void setContent(java.lang.String content)
	{
		this.content = content;
	}
	
	public java.lang.Integer getCreateIp()
	{
		return this.createIp;
	}
	
	public void setCreateIp(java.lang.Integer createIp)
	{
		this.createIp = createIp;
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

