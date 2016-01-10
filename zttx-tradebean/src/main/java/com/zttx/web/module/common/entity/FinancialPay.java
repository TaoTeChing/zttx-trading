/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 财务账支付记录表 实体对象
 * <p>File：FinancialPay.java</p>
 * <p>Title: FinancialPay</p>
 * <p>Description:FinancialPay</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class FinancialPay extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**支付流水号*/
	private String payExtId;
	/**经销商id*/
	private String dealerId;
	/**品牌商id*/
	private String brandId;
	/**支付金额*/
	private java.math.BigDecimal payAmount;
	/**支付状态（0未支付，1支付成功，2支付失败）*/
	private Short payState;
	/**支付的期款开始时间*/
	private Long beginTime;
	/**支付的期款结束时间*/
	private Long endTime;
	/**支付的款项类型（0返点财务帐，1终端商财务帐）*/
	private Short amountType;
	
	public String getPayExtId()
	{
		return this.payExtId;
	}
	
	public void setPayExtId(String payExtId)
	{
		this.payExtId = payExtId;
	}
	
	public String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public String getBrandId()
	{
		return this.brandId;
	}
	
	public void setBrandId(String brandId)
	{
		this.brandId = brandId;
	}
	
	public java.math.BigDecimal getPayAmount()
	{
		return this.payAmount;
	}
	
	public void setPayAmount(java.math.BigDecimal payAmount)
	{
		this.payAmount = payAmount;
	}
	
	public Short getPayState()
	{
		return this.payState;
	}
	
	public void setPayState(Short payState)
	{
		this.payState = payState;
	}
	
	public Long getBeginTime()
	{
		return this.beginTime;
	}
	
	public void setBeginTime(Long beginTime)
	{
		this.beginTime = beginTime;
	}
	
	public Long getEndTime()
	{
		return this.endTime;
	}
	
	public void setEndTime(Long endTime)
	{
		this.endTime = endTime;
	}
	
	public Short getAmountType()
	{
		return this.amountType;
	}
	
	public void setAmountType(Short amountType)
	{
		this.amountType = amountType;
	}


}

