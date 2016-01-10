/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 手机验证码 实体对象
 * <p>File：TelCode.java</p>
 * <p>Title: TelCode</p>
 * <p>Description:TelCode</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class TelCode extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**手机号码*/
	private java.lang.String userMobile;
	/**验证码*/
	private java.lang.String verifyCode;
	/**客户类别*/
	private java.lang.Short cusType;
	/**验证码类型*/
	private java.lang.String verifyType;
	/**建档时间*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	/**有效时间*/
	private java.lang.Integer validTime;
	/**建档IP*/
	private java.lang.Integer createIp;
	/**使用状态*/
	private java.lang.Boolean useState;
	/**使用时间*/
	private java.lang.Long useTime;
	
	public java.lang.String getUserMobile()
	{
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String userMobile)
	{
		this.userMobile = userMobile;
	}
	
	public java.lang.String getVerifyCode()
	{
		return this.verifyCode;
	}
	
	public void setVerifyCode(java.lang.String verifyCode)
	{
		this.verifyCode = verifyCode;
	}
	
	public java.lang.Short getCusType()
	{
		return this.cusType;
	}
	
	public void setCusType(java.lang.Short cusType)
	{
		this.cusType = cusType;
	}
	
	public java.lang.String getVerifyType()
	{
		return this.verifyType;
	}
	
	public void setVerifyType(java.lang.String verifyType)
	{
		this.verifyType = verifyType;
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
	
	public java.lang.Integer getValidTime()
	{
		return this.validTime;
	}
	
	public void setValidTime(java.lang.Integer validTime)
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
	
	public java.lang.Boolean getUseState()
	{
		return this.useState;
	}
	
	public void setUseState(java.lang.Boolean useState)
	{
		this.useState = useState;
	}
	
	public java.lang.Long getUseTime()
	{
		return this.useTime;
	}
	
	public void setUseTime(java.lang.Long useTime)
	{
		this.useTime = useTime;
	}
	
}

