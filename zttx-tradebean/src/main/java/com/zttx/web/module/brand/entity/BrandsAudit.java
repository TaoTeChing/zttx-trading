/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌审核日志 实体对象
 * <p>File：BrandsAudit.java</p>
 * <p>Title: BrandsAudit</p>
 * <p>Description:BrandsAudit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandsAudit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**审核人员编号*/
	private java.lang.String userId;
	/**品牌编号*/
	private java.lang.String brandsId;
	/**品牌名称*/
	private java.lang.String brandsNames;
	/**品牌logo*/
	private java.lang.String logoName;
	/**品牌logo域名*/
	private java.lang.String domainName;
	/**审核时间*/
	private java.lang.Long checkTime;
	/**审核状态(1：审核通过，2：审核不通过)*/
	private java.lang.Short checkState;
	/**审核不通过原因*/
	private java.lang.String checkMark;
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.String getBrandsId()
	{
		return this.brandsId;
	}
	
	public void setBrandsId(java.lang.String brandsId)
	{
		this.brandsId = brandsId;
	}
	
	public java.lang.String getBrandsNames()
	{
		return this.brandsNames;
	}
	
	public void setBrandsNames(java.lang.String brandsNames)
	{
		this.brandsNames = brandsNames;
	}
	
	public java.lang.String getLogoName()
	{
		return this.logoName;
	}
	
	public void setLogoName(java.lang.String logoName)
	{
		this.logoName = logoName;
	}
	
	public java.lang.String getDomainName()
	{
		return this.domainName;
	}
	
	public void setDomainName(java.lang.String domainName)
	{
		this.domainName = domainName;
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

