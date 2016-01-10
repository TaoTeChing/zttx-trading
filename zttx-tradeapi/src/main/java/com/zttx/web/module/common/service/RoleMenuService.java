/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.entity.RoleMenu;

/**
 * 角色与菜单关联表 服务接口
 * <p>File：RoleMenuService.java </p>
 * <p>Title: RoleMenuService </p>
 * <p>Description:RoleMenuService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RoleMenuService extends GenericServiceApi<RoleMenu>
{
    void deleteByMenuId(String menuId);
    
    void setRole(RoleMenu roleMenu);
    
    void deleteByRoleId(String roleId);
    
    void setMenu(List<RoleMenu> roleMenuList, RoleInfo roleInfo);
    
    /**
     * 保存角色菜单
     * @param roleMenuList 菜单列表
     * @param roleId 角色id
     * @throws BusinessException
     * @author 章旭楠
     */
    void saveRoleMenu(String[] roleMenuList, String roleId) throws BusinessException;
    
    /**
     * 根据角色获取授权菜单
     * @param roleId 角色id
     * @return List
     * @author 章旭楠
     */
    List<String> findByRoleId(String roleId);
}
