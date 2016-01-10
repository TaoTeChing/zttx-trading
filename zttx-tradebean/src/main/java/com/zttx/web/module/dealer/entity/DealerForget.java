/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 取回密码日志 实体对象
 * <p>File：DealerForget.java</p>
 * <p>Title: DealerForget</p>
 * <p>Description:DealerForget</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerForget extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商编号*/
	private java.lang.String dealerId;
	/**原密码*/
	private java.lang.String oldPwd;
	/**新密码*/
	private java.lang.String newPwd;
	/**修改时间*/
	private java.lang.Long createTime;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**修改者IP*/
	private java.lang.Integer createIp;
	
	public java.lang.String getDealerId()
	{
		return this.dealerId;
	}
	
	public void setDealerId(java.lang.String dealerId)
	{
		this.dealerId = dealerId;
	}
	
	public java.lang.String getOldPwd()
	{
		return this.oldPwd;
	}
	
	public void setOldPwd(java.lang.String oldPwd)
	{
		this.oldPwd = oldPwd;
	}
	
	public java.lang.String getNewPwd()
	{
		return this.newPwd;
	}
	
	public void setNewPwd(java.lang.String newPwd)
	{
		this.newPwd = newPwd;
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
	
	public java.lang.Integer getCreateIp()
	{
		return this.createIp;
	}
	
	public void setCreateIp(java.lang.Integer createIp)
	{
		this.createIp = createIp;
	}
	
}

