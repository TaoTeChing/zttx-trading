/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 产品修改 实体对象
 * <p>File：ProductEdit.java</p>
 * <p>Title: ProductEdit</p>
 * <p>Description:ProductEdit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ProductEdit extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**审核状态（0：待审，1：已审）*/
	private java.lang.Short state;
	/**修改时间*/
	private java.lang.Long updateTime;
	/**建档时间*/
	private java.lang.Long createTime;
	
	public java.lang.Short getState()
	{
		return this.state;
	}
	
	public void setState(java.lang.Short state)
	{
		this.state = state;
	}
	
	public java.lang.Long getUpdateTime()
	{
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.Long updateTime)
	{
		this.updateTime = updateTime;
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

