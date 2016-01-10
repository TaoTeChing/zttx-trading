/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商审核日志 实体对象
 * <p>File：BrandAudit.java</p>
 * <p>Title: BrandAudit</p>
 * <p>Description:BrandAudit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandAudit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**品牌商编号*/
	private java.lang.String brandId;
	/**品牌商名称*/
	private java.lang.String brandName;
	/**审核人员编号*/
	private java.lang.String userId;
	/**审核时间*/
	private java.lang.Long checkTime;
	/**审核状态*/
	private java.lang.Short checkState;
	/**不通过原因*/
	private java.lang.String checkMark;
	
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
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
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

