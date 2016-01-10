/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 展厅装修系统菜单 实体对象
 * <p>File：DecorateSysMenu.java</p>
 * <p>Title: DecorateSysMenu</p>
 * <p>Description:DecorateSysMenu</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DecorateSysMenu extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**菜单名称*/
	private java.lang.String menuName;
	/**菜单链接*/
	private java.lang.String menuUrl;
	/**建档时间*/
	private java.lang.Long createTime;
	/**最后修改时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getMenuName()
	{
		return this.menuName;
	}
	
	public void setMenuName(java.lang.String menuName)
	{
		this.menuName = menuName;
	}
	
	public java.lang.String getMenuUrl()
	{
		return this.menuUrl;
	}
	
	public void setMenuUrl(java.lang.String menuUrl)
	{
		this.menuUrl = menuUrl;
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

