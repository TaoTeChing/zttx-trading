/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.entity.RoleMenu;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ZtreeTreeNode;
import com.zttx.web.module.common.service.MenuInfoService;
import com.zttx.web.module.common.service.RoleMenuService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerMessageService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * 经销商、品牌商菜单信息 控制器
 * <p>File：MenuInfoController.java </p>
 * <p>Title: MenuInfoController </p>
 * <p>Description:MenuInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/menuInfo")
public class MenuInfoController extends GenericController
{
    @Autowired(required = true)
    private MenuInfoService      menuInfoService;
    
    @Autowired
    private UserInfoService      userInfoService;
    
    @Autowired
    private BrandInfoService     brandInfoService;
    
    @Autowired
    private DealerInfoService    dealerInfoService;
    
    @Autowired
    private RoleMenuService      roleMenuService;
    
    @Autowired
    private BrandMessageService  brandMessageService;
    
    @Autowired
    private DealerMessageService dealerMessageService;
    
    @Autowired
    private BrandContactService  brandContactService;
    
    /**
     * 添加导航
     * @param menuInfo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonMessage add(MenuInfo menuInfo)
    {
        if (StringUtils.isEmpty(menuInfo.getMenuName())) { return this.getJsonMessage(CommonConst.FAIL); }
        MenuInfo parent = menuInfoService.selectByPrimaryKey(menuInfo.getUpMenuId());
        if (parent.getMenuLevel() == 4) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "只能添加四级菜单"); }
        menuInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        if (menuInfo.getMenuLevel() == 2)
        {
            menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + menuInfo.getRefrenceId());
            menuInfo.setCanTop(true);
        }
        else if (menuInfo.getMenuLevel() == 3)
        {
            menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + menuInfo.getUpMenuId());
            menuInfo.setCanTop(false);
        }
        else if (menuInfo.getMenuLevel() == 4)
        {
            menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + parent.getUpMenuId() + "&subMenuId=" + menuInfo.getRefrenceId());
            menuInfo.setCanTop(false);
        }
        menuInfo.setCreateTime(new Date().getTime());
        menuInfo.setUpdateTime(new Date().getTime());
        menuInfoService.insert(menuInfo);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 修复导航
     * @param menuInfo
     * @return
     */
    @RequestMapping("/modify")
    @ResponseBody
    public JsonMessage modify(MenuInfo menuInfo)
    {
        MenuInfo temp = menuInfoService.selectByPrimaryKey(menuInfo.getRefrenceId());
        if (menuInfo.getMenuLevel() == 2)
        {
            if (menuInfo.getMenuAddr().indexOf('?') < 0)
            {
                menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + temp.getRefrenceId());
            }
            menuInfo.setCanTop(true);
        }
        else if (menuInfo.getMenuLevel() == 3)
        {
            if (menuInfo.getMenuAddr().indexOf('?') < 0)
            {
                menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + temp.getUpMenuId());
            }
            menuInfo.setCanTop(false);
        }
        else if (menuInfo.getMenuLevel() == 4)
        {
            if (menuInfo.getMenuAddr().indexOf('?') < 0)
            {
                MenuInfo parent = menuInfoService.selectByPrimaryKey(temp.getUpMenuId());
                menuInfo.setMenuAddr(menuInfo.getMenuAddr() + "?menuId=" + parent.getUpMenuId() + "&subMenuId=" + temp.getRefrenceId());
            }
            menuInfo.setCanTop(false);
        }
        menuInfo.setUpdateTime(new Date().getTime());
        menuInfoService.updateByPrimaryKeySelective(menuInfo);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 
     * @param refrenceId
     * @return
     */
    @ResponseBody
    @RequestMapping("/modify/forward")
    public MenuInfo modifyForward(String refrenceId)
    {
        MenuInfo menuInfo = menuInfoService.selectByPrimaryKeyWithParent(refrenceId);
        return menuInfo;
    }
    
    /**
     * 菜单设置角色
     * @param roleMenu
     * @return
     */
    @RequestMapping("/setRole")
    @ResponseBody
    public JsonMessage setRole(RoleMenu roleMenu)
    {
        roleMenu.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        roleMenu.setCreateTime(new Date().getTime());
        roleMenu.setUpdateTime(new Date().getTime());
        roleMenu.setDelFlag(false);
        roleMenuService.deleteByMenuId(roleMenu.getMenuId());
        roleMenuService.setRole(roleMenu);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/remove")
    @ResponseBody
    public JsonMessage remove(MenuInfo menuInfo)
    {
        menuInfoService.delete(menuInfo.getRefrenceId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/enable")
    @ResponseBody
    public JsonMessage enable(MenuInfo menuInfo)
    {
        menuInfoService.enable(menuInfo.getRefrenceId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 列表查询导航
     * @return
     */
    @RequestMapping("/list")
    public String listMenuIfo()
    {
        return "common/list_menu";
    }
    
    /**
     * 列表查询 
     * @param pagination
     * @param menuInfo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/data")
    public JsonMessage data(Pagination pagination, MenuInfo menuInfo, HttpServletRequest request)
    {
        pagination.setStartIndex((pagination.getCurrentPage() - 1) * pagination.getPageSize());
        menuInfo.setPage(pagination);
        List<MenuInfo> menuInfoList = menuInfoService.findByMenuInfo(menuInfo);
        PaginateResult<MenuInfo> result = new PaginateResult<>(menuInfo.getPage(), menuInfoList);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 填充树形菜单数据 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/ztree/data")
    public List<ZtreeTreeNode> ztree(String id)
    {
        MenuInfo menuInfo = new MenuInfo();
        List<MenuInfo> menuList = menuInfoService.findByMenuInfo(menuInfo);
        List<ZtreeTreeNode> treeList = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++)
        {
            menuInfo = menuList.get(i);
            ZtreeTreeNode node = new ZtreeTreeNode();
            node.setChecked(false);
            node.setId(menuInfo.getRefrenceId());
            node.setpId(menuInfo.getUpMenuId());
            node.setName(menuInfo.getMenuName());
            node.setOpen(true);
            treeList.add(node);
        }
        return treeList;
    }
    
    /**
     * 加载顶部菜单
     * @param model
     * @param menuId
     * @return
     */
    @RequestMapping("/mainmenu")
    public String mainMenuManage(Model model, String menuId)
    {
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        model.addAttribute("mainMenuList", menuInfoService.getMainMenuList(user.getRefrenceId()));
        String topMenuId = menuInfoService.getTopMenuBySubMenuId(menuId);// 获取顶部选中菜单id
        model.addAttribute("openId", topMenuId);
        model.addAttribute("userm", user);
        if (user.getUserType().intValue() == 0)
        {
            //model.addAttribute("brandInfo", brandInfoService.selectByPrimaryKey(user.getRefrenceId()));
            model.addAttribute("headBrandMessageCount", brandMessageService.getBrandMessageCount(user.getRefrenceId()));
            return "brand/menu_head";
        }
        else
        {
            //model.addAttribute("dealerInfo", dealerInfoService.selectByPrimaryKey(user.getRefrenceId()));
            model.addAttribute("headDealerMessageCount", dealerMessageService.getDealerMessageCount(user.getRefrenceId()));
            return "dealer/agency_header";
        }
    }
    
    /**
     * 加载左侧菜单
     * @param model
     * @param menuId
     * @return
     */
    @RequestMapping("/sidemenu")
    public String sideMenuManage(Model model, String menuId)
    {
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        List<MenuInfo> menuTreeList = null;
        String topMenuId = menuInfoService.getTopMenuBySubMenuId(menuId);
        if (menuTreeList == null)
        {
            List<MenuInfo> mainMenuList = menuInfoService.getMainMenuList(user.getRefrenceId());
            if (topMenuId == null || topMenuId.trim().equals(""))
            {
                topMenuId = mainMenuList.get(0).getRefrenceId();
            }
            menuTreeList = menuInfoService.getSideMenuList(user.getRefrenceId(), topMenuId);
            setLeaf(menuTreeList);
            if (menuId != null)
            {
                setOpen(menuTreeList, null, menuId);
            }
        }
        UserInfo userInfo = userInfoService.selectByPrimaryKey(user.getRefrenceId());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("menuId", topMenuId);
        model.addAttribute("subMenuId", menuId);
        model.addAttribute("menuTreeList", menuTreeList);
        if (user.getUserType().intValue() == 0)
        {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(user.getRefrenceId());
            model.addAttribute("brandInfo", brandInfo);
            if(brandInfo!=null){
                model.addAttribute("brandContact", brandContactService.findByBrandId(brandInfo.getRefrenceId()));
            }
            return "brand/menu";
        }
        else
        {
            DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(user.getRefrenceId());
            model.addAttribute("dealerInfo", dealerInfo);
            return "dealer/agency_menu";
        }
    }
    
    private void setOpen(List<MenuInfo> menuTreeList, MenuInfo parent, String subMenuId)
    {
        if (menuTreeList == null || menuTreeList.size() == 0) { return; }
        for (int i = 0; i < menuTreeList.size(); i++)
        {
            MenuInfo menuInfo = menuTreeList.get(i);
            if (menuInfo.getRefrenceId().equals(subMenuId))
            {
                menuInfo.setMenuOpen(true);
                if (parent != null)
                {
                    parent.setMenuOpen(true);
                }
                break;
            }
            setOpen(menuInfo.getChildren(), menuInfo, subMenuId);
        }
    }
    
    private void setLeaf(List<MenuInfo> menuTreeList)
    {
        if (menuTreeList == null || menuTreeList.size() == 0) { return; }
        for (int i = 0; i < menuTreeList.size(); i++)
        {
            MenuInfo menuInfo = menuTreeList.get(i);
            if (menuInfo.getChildren() == null || menuInfo.getChildren().size() == 0)
            {
                menuInfo.setLeaf(true);
            }
            setLeaf(menuInfo.getChildren());
        }
    }
}
