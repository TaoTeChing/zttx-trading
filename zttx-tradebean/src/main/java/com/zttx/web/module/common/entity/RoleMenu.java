/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;


import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * 角色与菜单关联表 实体对象
 * <p>File：RoleMenu.java</p>
 * <p>Title: RoleMenu</p>
 * <p>Description:RoleMenu</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RoleMenu extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**角色编码*/
	private java.lang.String roleId;
	/**菜单编码*/
	private java.lang.String menuId;
	/**创建时间*/
	private java.lang.Long createTime;
	/**更新时间*/
	private java.lang.Long updateTime;
	
	public java.lang.String getRoleId()
	{
		return this.roleId;
	}
	
	public void setRoleId(java.lang.String roleId)
	{
		this.roleId = roleId;
	}
	
	public java.lang.String getMenuId()
	{
		return this.menuId;
	}
	
	public void setMenuId(java.lang.String menuId)
	{
		this.menuId = menuId;
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

