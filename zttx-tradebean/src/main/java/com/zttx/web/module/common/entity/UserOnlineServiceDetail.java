/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 客服在线信息详情表 实体对象
 * <p>File：UserOnlineServiceDetail.java</p>
 * <p>Title: UserOnlineServiceDetail</p>
 * <p>Description:UserOnlineServiceDetail</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UserOnlineServiceDetail extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**在线服务编号*/
	private java.lang.String userOnlineId;
	/**QQ号*/
	private java.lang.String qq;
	/**客服名称*/
	private java.lang.String name;
	/**创建时间*/
	private java.lang.Long createTime;
	
	public java.lang.String getUserOnlineId()
	{
		return this.userOnlineId;
	}
	
	public void setUserOnlineId(java.lang.String userOnlineId)
	{
		this.userOnlineId = userOnlineId;
	}
	
	public java.lang.String getQq()
	{
		return this.qq;
	}
	
	public void setQq(java.lang.String qq)
	{
		this.qq = qq;
	}
	
	public java.lang.String getName()
	{
		return this.name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
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

