/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 押金支付记录 实体对象
 * <p>File：DealerDeposit.java</p>
 * <p>Title: DealerDeposit</p>
 * <p>Description:DealerDeposit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerDeposit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商ID*/
	private java.lang.String dealerId;
	/**品牌商ID*/
	private java.lang.String brandId;
	/**当笔押金支付金额*/
	private java.math.BigDecimal paidAmount;
	/**支付时间*/
	private java.lang.Long paidTime;
	
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
	
	public java.math.BigDecimal getPaidAmount()
	{
		return this.paidAmount;
	}
	
	public void setPaidAmount(java.math.BigDecimal paidAmount)
	{
		this.paidAmount = paidAmount;
	}
	
	public java.lang.Long getPaidTime()
	{
		return this.paidTime;
	}
	
	public void setPaidTime(java.lang.Long paidTime)
	{
		this.paidTime = paidTime;
	}
	
}

