/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.entity.RoleMenu;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.common.mapper.RoleMenuMapper;

/**
 * 角色与菜单关联表 服务实现类
 * <p>File：RoleMenu.java </p>
 * <p>Title: RoleMenu </p>
 * <p>Description:RoleMenu </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RoleMenuServiceImpl extends GenericServiceApiImpl<RoleMenu> implements RoleMenuService
{
    private RoleMenuMapper roleMenuMapper;
    
    @Autowired
    private RoleInfoMapper roleInfoMapper;
    
    @Autowired(required = true)
    public RoleMenuServiceImpl(RoleMenuMapper roleMenuMapper)
    {
        super(roleMenuMapper);
        this.roleMenuMapper = roleMenuMapper;
    }
    
    @Override
    public void deleteByMenuId(String menuId)
    {
        roleMenuMapper.deleteByMenuId(menuId);
    }
    
    @Override
    public void setRole(RoleMenu roleMenu)
    {
        roleMenuMapper.setRole(roleMenu);
    }
    
    @Override
    public void deleteByRoleId(String roleId)
    {
        roleMenuMapper.deleteByRoleId(roleId);
    }
    
    @Override
    public void setMenu(List<RoleMenu> roleMenuList, RoleInfo roleInfo)
    {
        roleMenuMapper.setMenu(roleMenuList, roleInfo);
    }
    
    /**
     * 根据角色获取授权菜单
     *
     * @param roleId 角色id
     * @return List<String> 菜单主键集合
     * @author 章旭楠
     */
    @Override
    public List<String> findByRoleId(String roleId)
    {
        return roleMenuMapper.findByRoleId(roleId);
    }
    
    /**
     * 保存角色菜单
     *
     * @param roleMenuArr 菜单列表
     * @param roleId       角色id
     * @throws BusinessException
     * @author 章旭楠
     */
    @Override
    public void saveRoleMenu(String[] roleMenuArr, String roleId) throws BusinessException
    {
        RoleInfo roleInfo = roleInfoMapper.selectByPrimaryKey(roleId);
        if (roleInfo == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        this.deleteByRoleId(roleId);// 保存权限前 先删除该角色已有菜单权限
        List<RoleMenu> roleMenuList = Lists.newArrayList();
        if (!ArrayUtils.isEmpty(roleMenuArr))
        {
            for (String menuId : roleMenuArr)
            {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                roleMenu.setCreateTime(CalendarUtils.getCurrentLong());
                roleMenu.setUpdateTime(CalendarUtils.getCurrentLong());
                roleMenuList.add(roleMenu);
            }
            this.insertBatch(roleMenuList);
        }
    }
}
