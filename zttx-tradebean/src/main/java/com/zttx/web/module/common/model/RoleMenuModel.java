/*
 * @(#)RoleMenuModel.java 2015-8-13 下午3:39:03
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.util.List;

import com.zttx.web.module.common.entity.RoleMenu;

/**
 * <p>File：RoleMenuModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-13 下午3:39:03</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class RoleMenuModel
{
    private List<RoleMenu> roleMenuList;

    public List<RoleMenu> getRoleMenuList()
    {
        return roleMenuList;
    }

    public void setRoleMenuList(List<RoleMenu> roleMenuList)
    {
        this.roleMenuList = roleMenuList;
    }
    
}
