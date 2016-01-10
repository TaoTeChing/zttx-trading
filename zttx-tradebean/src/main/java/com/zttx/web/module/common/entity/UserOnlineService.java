/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * 客服在线信息表 实体对象
 * <p>File：UserOnlineService.java</p>
 * <p>Title: UserOnlineService</p>
 * <p>Description:UserOnlineService</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UserOnlineService extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**用户类型*/
	private java.lang.Short userType;
	/**在线时间段（0.周一到周五，1.周一到周六，2.周一到周日）*/
	private java.lang.Short onlineDateType;
	/**开始时间*/
	private java.lang.Long onlineBeginTime;
	/**结束时间*/
	private java.lang.Long onlineEndTime;
	/**创建时间*/
	private java.lang.Long createTime;
	/**用户ID*/
	private java.lang.String userId;
	/**是否显示 0、不显示　1、显示*/
	private java.lang.Integer showed;
	//详情
	private List<UserOnlineServiceDetail> detailList;
	
	
	
    public List<UserOnlineServiceDetail> getDetailList()
    {
        return detailList;
    }

    public void setDetailList(List<UserOnlineServiceDetail> detailList)
    {
        this.detailList = detailList;
    }

    public java.lang.Short getUserType()
	{
		return this.userType;
	}
	
	public void setUserType(java.lang.Short userType)
	{
		this.userType = userType;
	}
	
	public java.lang.Short getOnlineDateType()
	{
		return this.onlineDateType;
	}
	
	public void setOnlineDateType(java.lang.Short onlineDateType)
	{
		this.onlineDateType = onlineDateType;
	}
	
	public java.lang.Long getOnlineBeginTime()
	{
		return this.onlineBeginTime;
	}
	
	public void setOnlineBeginTime(java.lang.Long onlineBeginTime)
	{
		this.onlineBeginTime = onlineBeginTime;
	}
	
	public java.lang.Long getOnlineEndTime()
	{
		return this.onlineEndTime;
	}
	
	public void setOnlineEndTime(java.lang.Long onlineEndTime)
	{
		this.onlineEndTime = onlineEndTime;
	}
	
	public java.lang.Long getCreateTime()
	{
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.Long createTime)
	{
		this.createTime = createTime;
	}
	
	public java.lang.String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId)
	{
		this.userId = userId;
	}
	
	public java.lang.Integer getShowed()
	{
		return this.showed;
	}
	
	public void setShowed(java.lang.Integer showed)
	{
		this.showed = showed;
	}
	
}

