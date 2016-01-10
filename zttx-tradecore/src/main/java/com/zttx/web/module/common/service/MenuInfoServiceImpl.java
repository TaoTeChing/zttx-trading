/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.mapper.MenuInfoMapper;
import com.zttx.web.module.common.mapper.RoleMenuMapper;
import com.zttx.web.module.common.model.ZtreeTreeNode;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商、品牌商菜单信息 服务实现类
 * <p>File：MenuInfo.java </p>
 * <p>Title: MenuInfo </p>
 * <p>Description:MenuInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class MenuInfoServiceImpl extends GenericServiceApiImpl<MenuInfo> implements MenuInfoService
{
    private MenuInfoMapper menuInfoMapper;
    
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    
    @Autowired(required = true)
    public MenuInfoServiceImpl(MenuInfoMapper menuInfoMapper)
    {
        super(menuInfoMapper);
        this.menuInfoMapper = menuInfoMapper;
    }
    
    @Override
    public List<MenuInfo> findMenuByRoleId(String roleId)
    {
        return menuInfoMapper.findMenuByRoleId(roleId);
    }
    
    @Override
    public List<MenuInfo> getMainMenuList(String userId)
    {
        return menuInfoMapper.getMainMenuList(userId);
    }
    
    @Override
    public List<MenuInfo> getSideMenuList(String userId, String parentMenuId)
    {
        List<MenuInfo> sideList = menuInfoMapper.getSideMenuList(userId, parentMenuId);
        for (int i = 0; i < sideList.size(); i++)
        {
            MenuInfo menuInfo = sideList.get(i);
            List<MenuInfo> children = menuInfoMapper.findChildren(userId, menuInfo.getRefrenceId());
            menuInfo.setChildren(children);
        }
        return sideList;
    }
    
    @Override
    public List<MenuInfo> findByMenuInfo(MenuInfo menuInfo)
    {
        return menuInfoMapper.findByMenuInfo(menuInfo);
    }
    
    @Override
    public MenuInfo selectByPrimaryKeyWithParent(String refrenceId)
    {
        return menuInfoMapper.selectByPrimaryKeyWithParent(refrenceId);
    }
    
    @Override
    public void enable(String refrenceId)
    {
        menuInfoMapper.enable(refrenceId);
    }
    
    /**
     * 根据角色获取菜单树
     *
     * @param roleId
     * @param menuFilter
     * @return
     */
    @Override
    public TreeSet<ZtreeTreeNode> getTree(String roleId, MenuInfo menuFilter)
    {
        List<String> roleMenus = roleMenuMapper.findByRoleId(roleId);
        List<MenuInfo> menuList = this.findByMenuInfo(menuFilter);
        TreeSet<ZtreeTreeNode> menuTreeSet = Sets.newTreeSet(new Comparator<ZtreeTreeNode>()
        {
            @Override
            public int compare(ZtreeTreeNode o1, ZtreeTreeNode o2)
            {
                int result;
                result = o1.getOrderNo().compareTo(o2.getOrderNo());
                if (result == 0) result = o1.getId().compareTo(o2.getId());
                return result;
            }
        });
        for (MenuInfo menuInfo : menuList)
        {
            ZtreeTreeNode node = new ZtreeTreeNode();
            node.setId(menuInfo.getRefrenceId());
            node.setpId(menuInfo.getUpMenuId());
            node.setName(menuInfo.getMenuName());
            node.setChecked(roleMenus.contains(menuInfo.getRefrenceId()));
            node.setMenuLevel(menuInfo.getMenuLevel());
            node.setOpen((menuInfo.getMenuLevel() != null && menuInfo.getMenuLevel() < (short) 3));
            node.setOrderNo(null == menuInfo.getMenuOrder() ? 0 : menuInfo.getMenuOrder());
            menuTreeSet.add(node);
        }
        return menuTreeSet;
    }
    
    @Override
    public TreeSet<ZtreeTreeNode> getTree(MenuInfo searchBean)
    {
        Set<String> upMunuIdSet = getUpMenuIds(this.findList(searchBean));
        List<MenuInfo> menuList = Lists.newArrayList();
        if (upMunuIdSet.size() > 0)// upMunuIdSet not null
        {
            menuList = menuInfoMapper.findByIds(upMunuIdSet.toArray(new String[]{}));
        }
        TreeSet<ZtreeTreeNode> menuTreeSet = Sets.newTreeSet(new Comparator<ZtreeTreeNode>()
        {
            @Override
            public int compare(ZtreeTreeNode o1, ZtreeTreeNode o2)
            {
                int result;
                result = o1.getOrderNo().compareTo(o2.getOrderNo());
                if (result == 0) result = o1.getId().compareTo(o2.getId());
                return result;
            }
        });
        for (MenuInfo menuInfo : menuList)
        {
            ZtreeTreeNode node = new ZtreeTreeNode();
            node.setId(menuInfo.getRefrenceId());
            node.setpId(menuInfo.getUpMenuId());
            node.setName(menuInfo.getMenuName());
            node.setChecked(false);
            node.setMenuLevel(menuInfo.getMenuLevel());
            node.setOpen((menuInfo.getMenuLevel() != null && menuInfo.getMenuLevel() < (short) 3));
            node.setOrderNo(null == menuInfo.getMenuOrder() ? 0 : menuInfo.getMenuOrder());
            menuTreeSet.add(node);
        }
        return menuTreeSet;
    }
    
    /**
     * 获取上级所有菜单id
     * @param menuList
     * @return
     */
    private Set<String> getUpMenuIds(List<MenuInfo> menuList)
    {
        Set<String> upMunuIds = Sets.newHashSet();
        if (ListUtils.isNotEmpty(menuList))
        {
            Map<String, MenuInfo> allMenuMap = list2Map(this.menuInfoMapper.selectAll());
            for (MenuInfo menuInfo : menuList)
            {
                if (menuInfo == null)
                {
                    continue;
                }
                upMunuIds.addAll(getUpMenuIds(menuInfo, allMenuMap));
            }
        }
        return upMunuIds;
    }
    
    private Map<String, MenuInfo> list2Map(List<MenuInfo> menuList)
    {
        Map<String, MenuInfo> menuInfoHashMap = Maps.newHashMap();
        for (MenuInfo menuInfo : menuList)
        {
            menuInfoHashMap.put(menuInfo.getRefrenceId(), menuInfo);
        }
        return menuInfoHashMap;
    }
    
    /**
     * 根据菜单，获取所有上级菜单id集合（包含自身，不包含顶级-1）
     * @param menuInfo
     * @param allMenuMap 所有菜单map 集合，避免单个查询影响性能
     * @return
     */
    private Set<String> getUpMenuIds(MenuInfo menuInfo, Map allMenuMap)
    {
        Set<String> result = Sets.newHashSet();
        do
        {
            result.add(menuInfo.getRefrenceId());
            menuInfo = (MenuInfo) allMenuMap.get(menuInfo.getUpMenuId());
        }
        while (null != menuInfo);
        return result;
    }
    
    @Override
    public void saveByClient(MenuInfo menuInfo)
    {
        MenuInfo parent = this.menuInfoMapper.selectByPrimaryKey(menuInfo.getUpMenuId());
        if (parent == null)
        {
            menuInfo.setMenuLevel((short) 1);
            menuInfo.setUpMenuId("-1");
            menuInfo.setCanTop(false);
        }
        else
        {
            if (1 == parent.getMenuLevel())
            {
                menuInfo.setCanTop(true);// 2级菜单可置顶
            }
            else
            {
                menuInfo.setCanTop(false);
            }
            menuInfo.setMenuLevel((short) (parent.getMenuLevel() + 1));
        }
        if (menuInfo.getMenuType())
        {// 权限
            menuInfo.setShowflag(false);// 菜单不显示
        }
        if (StringUtils.isBlank(menuInfo.getRefrenceId()))
        {
            menuInfo.setCreateTime(CalendarUtils.getCurrentLong());
            menuInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            this.menuInfoMapper.insertSelective(menuInfo);
        }
        else
        {
            menuInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            this.menuInfoMapper.updateByPrimaryKeySelective(menuInfo);
        }
    }
    
    @Override
    public void deleteCascade(String refrenceId)
    {
        roleMenuMapper.deleteByMenuId(refrenceId);
        menuInfoMapper.delete(refrenceId);
    }
    
    @Override
    public Boolean hasChildMenu(String upMenuId)
    {
        return menuInfoMapper.countByUpMuneId(upMenuId) > 0;
    }
    
    @Override
    public String getTopMenuBySubMenuId(String subMenuId)
    {
        MenuInfo topMenu = selectByPrimaryKey(subMenuId);
        if (topMenu == null) { return ""; }
        if (topMenu.getCanTop())
        {
            return topMenu.getRefrenceId();
        }
        else
        {
            if (StringUtils.isBlank(topMenu.getUpMenuId()) || "-1".equals(topMenu.getUpMenuId())) { return topMenu.getRefrenceId(); }
            return getTopMenuBySubMenuId(topMenu.getUpMenuId());
        }
    }
}
