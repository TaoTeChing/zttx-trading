/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商审核日志 实体对象
 * <p>File：DealerAudit.java</p>
 * <p>Title: DealerAudit</p>
 * <p>Description:DealerAudit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerAudit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**审核人员编号*/
	private java.lang.String userId;
	/**经销商主帐号编号*/
	private java.lang.String dealerId;
	/**经销商名称*/
	private java.lang.String dealerName;
	/**审核时间*/
	private java.lang.Long checkTime;
	/**审核状态（1：通过，2：不通过）*/
	private java.lang.Short checkState;
	/**审核不通过说明*/
	private java.lang.String checkMark;
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
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
	
	public java.lang.Long getCheckTime()
	{
		return this.checkTime;
	}
	
	public void setCheckTime(java.lang.Long checkTime)
	{
		this.checkTime = checkTime;
	}
	
	public java.lang.Short getCheckState()
	{
		return this.checkState;
	}
	
	public void setCheckState(java.lang.Short checkState)
	{
		this.checkState = checkState;
	}
	
	public java.lang.String getCheckMark()
	{
		return this.checkMark;
	}
	
	public void setCheckMark(java.lang.String checkMark)
	{
		this.checkMark = checkMark;
	}
	
}

