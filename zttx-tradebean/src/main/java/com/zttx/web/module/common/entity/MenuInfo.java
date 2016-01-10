/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商、品牌商菜单信息 实体对象
 * <p>File：MenuInfo.java</p>
 * <p>Title: MenuInfo</p>
 * <p>Description:MenuInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class MenuInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**上层编号*/
    private java.lang.String  upMenuId;
    
    /**菜单名称*/
    @NotBlank(message = "菜单名称不能为空")
    private java.lang.String  menuName;
    
    /**菜单地址*/
    private java.lang.String  menuAddr;
    
    /**菜单编号*/
    private java.lang.Integer menuCode;
    
    /**权限编码*/
    private java.lang.String  authority;
    
    /**菜单级别*/
    private java.lang.Short   menuLevel;
    
    /**排序编号*/
    @NotNull(message = "排序编号不能为空")
    private java.lang.Integer menuOrder;
    
    /**菜单样式*/
    private java.lang.String  menuStyle;
    
    /**是否可以设置1：可以， 0不可以*/
    private java.lang.Boolean isSelect;
    
    /**是否是微店菜单0：否，1是*/
    private java.lang.Boolean shopMenu;
    
    /**菜单是否展开*/
    private java.lang.Boolean menuOpen;
    
    /**是否显示1：显示0：不显示*/
    @NotNull(message = "显示标识不能为空")
    private java.lang.Boolean showflag;
    
    /**创建时间
    */
    private java.lang.Long    createTime;
    
    /**最后修改时间*/
    private java.lang.Long    updateTime;
    
    /**菜单类型0菜单，1权限**/
    @NotNull(message = "菜单类型不能为空")
    private Boolean           menuType;
    
    /**是否顶部菜单1顶部，0不是顶部**/
    private Boolean           canTop;
    
    /**子菜单**/
    private List<MenuInfo>    children;
    
    /**是否叶子结点**/
    private Boolean           leaf;
    
    /**父菜单**/
    private MenuInfo          parent;
    
    public java.lang.String getUpMenuId()
    {
        return this.upMenuId;
    }
    
    public void setUpMenuId(java.lang.String upMenuId)
    {
        this.upMenuId = upMenuId;
    }
    
    public java.lang.String getMenuName()
    {
        return this.menuName;
    }
    
    public void setMenuName(java.lang.String menuName)
    {
        this.menuName = menuName;
    }
    
    public java.lang.String getMenuAddr()
    {
        return this.menuAddr;
    }
    
    public void setMenuAddr(java.lang.String menuAddr)
    {
        this.menuAddr = menuAddr;
    }
    
    public java.lang.Integer getMenuCode()
    {
        return this.menuCode;
    }
    
    public void setMenuCode(java.lang.Integer menuCode)
    {
        this.menuCode = menuCode;
    }
    
    public java.lang.String getAuthority()
    {
        return this.authority;
    }
    
    public void setAuthority(java.lang.String authority)
    {
        this.authority = authority;
    }
    
    public java.lang.Short getMenuLevel()
    {
        return this.menuLevel;
    }
    
    public void setMenuLevel(java.lang.Short menuLevel)
    {
        this.menuLevel = menuLevel;
    }
    
    public java.lang.Integer getMenuOrder()
    {
        return this.menuOrder;
    }
    
    public void setMenuOrder(java.lang.Integer menuOrder)
    {
        this.menuOrder = menuOrder;
    }
    
    public java.lang.String getMenuStyle()
    {
        return this.menuStyle;
    }
    
    public void setMenuStyle(java.lang.String menuStyle)
    {
        this.menuStyle = menuStyle;
    }
    
    public java.lang.Boolean getIsSelect()
    {
        return this.isSelect;
    }
    
    public void setIsSelect(java.lang.Boolean isSelect)
    {
        this.isSelect = isSelect;
    }
    
    public java.lang.Boolean getShopMenu()
    {
        return this.shopMenu;
    }
    
    public void setShopMenu(java.lang.Boolean shopMenu)
    {
        this.shopMenu = shopMenu;
    }
    
    public java.lang.Boolean getMenuOpen()
    {
        return this.menuOpen;
    }
    
    public void setMenuOpen(java.lang.Boolean menuOpen)
    {
        this.menuOpen = menuOpen;
    }
    
    public java.lang.Boolean getShowflag()
    {
        return this.showflag;
    }
    
    public void setShowflag(java.lang.Boolean showflag)
    {
        this.showflag = showflag;
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
    
    public List<MenuInfo> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<MenuInfo> children)
    {
        this.children = children;
    }
    
    public Boolean getLeaf()
    {
        return leaf;
    }
    
    public void setLeaf(Boolean leaf)
    {
        this.leaf = leaf;
    }
    
    public MenuInfo getParent()
    {
        return parent;
    }
    
    public void setParent(MenuInfo parent)
    {
        this.parent = parent;
    }
    
    public Boolean getMenuType()
    {
        return menuType;
    }
    
    public void setMenuType(Boolean menuType)
    {
        this.menuType = menuType;
    }
    
    public Boolean getCanTop()
    {
        return canTop;
    }
    
    public void setCanTop(Boolean canTop)
    {
        this.canTop = canTop;
    }
}
