/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.TreeSet;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.model.ZtreeTreeNode;

/**
 * 经销商、品牌商菜单信息 服务接口
 * <p>File：MenuInfoService.java </p>
 * <p>Title: MenuInfoService </p>
 * <p>Description:MenuInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface MenuInfoService extends GenericServiceApi<MenuInfo>
{
    List<MenuInfo> findMenuByRoleId(String roleId);
    
    List<MenuInfo> getMainMenuList(String userId);
    
    List<MenuInfo> getSideMenuList(String userId, String parentMenuId);
    
    List<MenuInfo> findByMenuInfo(MenuInfo menuInfo);
    
    MenuInfo selectByPrimaryKeyWithParent(String refrenceId);
    
    void enable(String refrenceId);
    
    /**
     * 根据角色获取菜单树
     * @param roleId 角色id
     * @param menuFilter
     * @return TreeSet
     * @author 章旭楠
     */
    TreeSet<ZtreeTreeNode> getTree(String roleId, MenuInfo menuFilter);
    
    /**
     * 提供给支撑 菜单管理查询
     * @param searchBean
     * @return
     */
    TreeSet<ZtreeTreeNode> getTree(MenuInfo searchBean);
    
    /**
     * 保存菜单
     * @param menuInfo
     */
    void saveByClient(MenuInfo menuInfo);
    
    /**
     * 删除菜单
     * 同时删除该菜单与角色绑定关系
     * @param refrenceId 菜单id
     */
    void deleteCascade(String refrenceId);
    
    /**
     * 判断是否存在子菜单
     * @param upMenuId
     * @return
     */
    Boolean hasChildMenu(String upMenuId);
    
    /**
     * 通过子菜单id 获取上级顶部菜单
     * @param subMenuId 子菜单id
     * @return 顶部菜单  如果不存在返回“”
     */
    String getTopMenuBySubMenuId(String subMenuId);
}
