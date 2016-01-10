/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 货运（物流）公司信息 实体对象
 * <p>File：FreightCompany.java</p>
 * <p>Title: FreightCompany</p>
 * <p>Description:FreightCompany</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class FreightCompany extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**物流公司名称*/
	private java.lang.String companyName;
	/**物流公司编码*/
	private java.lang.String freightCode;
	/**创建时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getCompanyName()
	{
		return this.companyName;
	}
	
	public void setCompanyName(java.lang.String companyName)
	{
		this.companyName = companyName;
	}
	
	public java.lang.String getFreightCode()
	{
		return this.freightCode;
	}
	
	public void setFreightCode(java.lang.String freightCode)
	{
		this.freightCode = freightCode;
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

