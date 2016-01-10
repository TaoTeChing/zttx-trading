/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 邮件验证 实体对象
 * <p>File：EmailValidate.java</p>
 * <p>Title: EmailValidate</p>
 * <p>Description:EmailValidate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class EmailValidate extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商/品牌商主账户编号*/
	private java.lang.String userId;
	/**用户类型
            1：经销商
            2：品牌商*/
	private java.lang.Short userCate;
	/**emailAddr*/
	private java.lang.String emailAddr;
	/**有效时间*/
	private java.lang.Long validTime;
	/**建档IP*/
	private java.lang.Integer createIp;
	/**使用时间*/
	private java.lang.Long useTime;
	/**建档时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.Short getUserCate()
	{
		return this.userCate;
	}
	
	public void setUserCate(java.lang.Short userCate)
	{
		this.userCate = userCate;
	}
	
	public java.lang.String getEmailAddr()
	{
		return this.emailAddr;
	}
	
	public void setEmailAddr(java.lang.String emailAddr)
	{
		this.emailAddr = emailAddr;
	}
	
	public java.lang.Long getValidTime()
	{
		return this.validTime;
	}
	
	public void setValidTime(java.lang.Long validTime)
	{
		this.validTime = validTime;
	}
	
	public java.lang.Integer getCreateIp()
	{
		return this.createIp;
	}
	
	public void setCreateIp(java.lang.Integer createIp)
	{
		this.createIp = createIp;
	}
	
	public java.lang.Long getUseTime()
	{
		return this.useTime;
	}
	
	public void setUseTime(java.lang.Long useTime)
	{
		this.useTime = useTime;
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

