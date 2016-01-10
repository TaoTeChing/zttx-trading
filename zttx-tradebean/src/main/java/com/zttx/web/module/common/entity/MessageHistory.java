/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 短信发送历史信息 实体对象
 * <p>File：MessageHistory.java</p>
 * <p>Title: MessageHistory</p>
 * <p>Description:MessageHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class MessageHistory extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**手机号码*/
	private java.lang.String userMobile;
	/**短信内容*/
	private java.lang.String message;
	/**发送时间*/
	private java.lang.Long createTime;
	
	public java.lang.String getUserMobile()
	{
		return this.userMobile;
	}
	
	public void setUserMobile(java.lang.String userMobile)
	{
		this.userMobile = userMobile;
	}
	
	public java.lang.String getMessage()
	{
		return this.message;
	}
	
	public void setMessage(java.lang.String message)
	{
		this.message = message;
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

