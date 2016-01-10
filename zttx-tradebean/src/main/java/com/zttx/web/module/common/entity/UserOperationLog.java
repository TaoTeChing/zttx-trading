/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 用户操作日志 实体对象
 * <p>File：UserOperationLog.java</p>
 * <p>Title: UserOperationLog</p>
 * <p>Description:UserOperationLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UserOperationLog extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**操作对象ID*/
	private java.lang.String objectId;
	/**操作人id*/
	private java.lang.String userId;
	/**操作人*/
	private java.lang.String userName;
	/**操作类型   0：操作品牌商押金   1：操作订单金额或者运费   2：操作退款  3：操作财务帐  4：操作产品  5：操作活动产品*/
	private java.lang.Integer type;
	/**操作事件*/
	private java.lang.String event;
	/**事件操作时间*/
	private java.lang.Long eventTime;
	/**登录IP*/
	private java.lang.Short loginType;
	/**登录IP*/
	private java.lang.Integer loginIP;
	
	public java.lang.String getObjectId()
	{
		return this.objectId;
	}
	
	public void setObjectId(java.lang.String objectId)
	{
		this.objectId = objectId;
	}
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName)
	{
		this.userName = userName;
	}
	
	public java.lang.Integer getType()
	{
		return this.type;
	}
	
	public void setType(java.lang.Integer type)
	{
		this.type = type;
	}
	
	public java.lang.String getEvent()
	{
		return this.event;
	}
	
	public void setEvent(java.lang.String event)
	{
		this.event = event;
	}
	
	public java.lang.Long getEventTime()
	{
		return this.eventTime;
	}
	
	public void setEventTime(java.lang.Long eventTime)
	{
		this.eventTime = eventTime;
	}
	
	public java.lang.Short getLoginType()
	{
		return this.loginType;
	}
	
	public void setLoginType(java.lang.Short loginType)
	{
		this.loginType = loginType;
	}
	
	public java.lang.Integer getLoginIP()
	{
		return this.loginIP;
	}
	
	public void setLoginIP(java.lang.Integer loginIP)
	{
		this.loginIP = loginIP;
	}
	
}

