/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.entity.RoleMenu;

/**
 * 角色与菜单关联表 持久层接口
 * <p>File：RoleMenuDao.java </p>
 * <p>Title: RoleMenuDao </p>
 * <p>Description:RoleMenuDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RoleMenuMapper extends GenericMapper<RoleMenu>
{
    /**
     * 根据资源菜单主键删除权限
     * @param menuId
     */
    void deleteByMenuId(String menuId);
    
    /**
     *
     * @param roleMenu
     */
    void setRole(RoleMenu roleMenu);
    
    /**
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);
    
    /**
     *
     * @param roleMenuList
     * @param roleInfo
     */
    void setMenu(@Param("roleMenuList") List<RoleMenu> roleMenuList, @Param("roleInfo") RoleInfo roleInfo);
    
    /**
     * 根据角色id 获取菜单id集合
     * @param roleId 角色id
     * @return
     */
    List<String> findByRoleId(String roleId);
}
