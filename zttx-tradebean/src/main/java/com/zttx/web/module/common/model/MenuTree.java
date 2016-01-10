/*
 * @(#)MenuTree.java 2014-3-5 下午4:35:32
 * Copyright 2014 施建波, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.zttx.web.module.brand.entity.BrandImgcate;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.entity.ProductCatalog;

/**
 * <p>File：MenuTree.java</p>
 * <p>Title:　菜单树</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-5 下午4:35:32</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
@Component
public class MenuTree
{
    private String  id;
    
    // 树节点
    private String  name;
    
    private String  parentId;
    
    // 节点URL
    private String  url;
    
    // 是否叶子节点
    private Boolean leaf         = true;
    
    // 节点排序
    // private Integer sort;
    // 节点级别
    private Integer level;
    
    // 节点编号
    private Integer code;
    
    // 菜单样式
    private String  style;
    
    // 是否打开状态
    private Boolean isOpen;
    
    // 图片
    private String  img;
    
    // 图片域名
    private String  domainName;
    
    // 是否在左侧的主菜单中显示
    private Boolean isShowInLeft = true;
    
    // 是否显示
    private Boolean isShow       = true;
    
    // 是否在权限设置中显示
    private Boolean isSelect;
    
    public Boolean getIsShow()
    {
        return isShow;
    }
    
    public void setIsShow(Boolean isShow)
    {
        this.isShow = isShow;
    }
    
    public String getDomainName()
    {
        return domainName;
    }
    
    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }
    
    public String getImg()
    {
        return img;
    }
    
    public void setImg(String img)
    {
        this.img = img;
    }
    
    public String getStyle()
    {
        return style;
    }
    
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    public Boolean getIsOpen()
    {
        return isOpen;
    }
    
    public void setIsOpen(Boolean isOpen)
    {
        this.isOpen = isOpen;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public Integer getLevel()
    {
        return level;
    }
    
    public void setLevel(Integer level)
    {
        this.level = level;
    }
    
    /*
     * public void setSort(Integer sort) { this.sort = sort; }
     */
    public Boolean getIsShowInLeft()
    {
        return isShowInLeft;
    }
    
    public void setIsShowInLeft(Boolean isShowInLeft)
    {
        this.isShowInLeft = isShowInLeft;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getParentId()
    {
        return parentId;
    }
    
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public Boolean getLeaf()
    {
        return leaf;
    }
    
    public void setLeaf(Boolean leaf)
    {
        this.leaf = leaf;
    }
    
    public Boolean getIsSelect()
    {
        return isSelect;
    }
    
    public void setIsSelect(Boolean isSelect)
    {
        this.isSelect = isSelect;
    }
    
    // 节点树
    private List<MenuTree> children = new ArrayList<MenuTree>();
    
    // 菜单树
    public static List<MenuTree> getMenuTree(List<MenuInfo> menuList)
    {
        Map<String, MenuTree> nodeMap = Maps.newLinkedHashMap();
        MenuTree root = new MenuTree();
        // 把所有菜单所入MAP中，以菜单编号为key
        if (null != menuList && !menuList.isEmpty()) for (MenuInfo dealerMenu : menuList)
        {
            MenuTree node = new MenuTree();
            node.setIsShow(dealerMenu.getShowflag());
            node.setUrl(dealerMenu.getMenuAddr());
            node.setId(dealerMenu.getRefrenceId());
            node.setName(dealerMenu.getMenuName());
            node.setParentId(dealerMenu.getUpMenuId());
            // node.sort = dealerMenu.getMenuOrder() == null ? 0 : dealerMenu.getMenuOrder();
            node.setLevel((int) dealerMenu.getMenuLevel());
            node.setCode(dealerMenu.getMenuCode());
            node.setStyle(dealerMenu.getMenuStyle());
            node.setIsOpen(dealerMenu.getMenuOpen());
            node.setIsSelect(dealerMenu.getIsSelect());
            nodeMap.put(node.id, node);
        }
        return getMenuChild(root, nodeMap);
    }
    
    // 获取品牌商图片分类菜单树
    public static List<MenuTree> getBrandImgcateTree(List<BrandImgcate> imgcateList)
    {
        Map<String, MenuTree> nodeMap = Maps.newLinkedHashMap();
        MenuTree root = new MenuTree();
        for (BrandImgcate brandImgcate : imgcateList)
        {
            MenuTree node = new MenuTree();
            node.setId(brandImgcate.getRefrenceId());
            node.setName(brandImgcate.getCateName());
            node.setParentId(brandImgcate.getParentId());
            nodeMap.put(node.id, node);
        }
        return getMenuChild(root, nodeMap);
    }
    
    // 品牌商分类树
    public static List<MenuTree> getCatalogTree(List<ProductCatalog> catalogList)
    {
        Map<String, MenuTree> nodeMap = Maps.newLinkedHashMap();
        MenuTree root = new MenuTree();
        for (ProductCatalog productCatalog : catalogList)
        {
            MenuTree node = new MenuTree();
            node.setId(productCatalog.getRefrenceId());
            node.setName(productCatalog.getCateName());
            node.setParentId(productCatalog.getParentId());
            node.setLevel((int) productCatalog.getCateLevel());
            node.setImg(productCatalog.getCateIcon());
            node.setDomainName(productCatalog.getDomainName());
            nodeMap.put(node.id, node);
        }
        return getMenuChild(root, nodeMap);
    }
    
    public static List<MenuTree> getMenuChild(MenuTree root, Map<String, MenuTree> nodeMap)
    {
        // 根据菜单的父编号，把子菜单放到对应的父菜单中
        for (Entry<String, MenuTree> entry : nodeMap.entrySet())
        {
            MenuTree node = entry.getValue();
            if (null != nodeMap.get(node.parentId))
            {
                if (node.isShow) nodeMap.get(node.parentId).leaf = false;
                nodeMap.get(node.parentId).addChild(node);
            }
            else root.addChild(node);
        }
        return root.getChildren();
    }
    
    public static String setMenuIdMap(Map<String, MenuTree> menuIdMap, List<MenuTree> menuTreeList, String forwardUrl)
    {
        String id = "";
        if (null != menuTreeList && !menuTreeList.isEmpty())
            for (MenuTree item : menuTreeList)
            {
                menuIdMap.put(item.getId(), item);
                if (StringUtils.isNotBlank(forwardUrl) && StringUtils.isNotBlank(item.getUrl()))
                {
                    if (forwardUrl.equals(item.getUrl()))
                    {
                        if (item.getLevel() == 1)
                        {
                            id = item.getId();
                        }else if (item.isShow){
                            id = item.getId();
                        } else{
                           id = item.getParentId();
                        }
                        break;
                    }
                }
                String tempId = setMenuIdMap(menuIdMap, item.getChildren(), forwardUrl);
                if (StringUtils.isBlank(id) && StringUtils.isNotBlank(tempId)) id = tempId;
            }
        return id;
    }
    
    public List<MenuTree> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<MenuTree> children)
    {
        this.children = children;
    }
    
    /**
     * 增加树节点
     * @param tree
     */
    public void addChild(MenuTree tree)
    {
        children.add(tree);
    }
    
    @Override
    public String toString()
    {
        return "MenuTree [id=" + id + ", name=" + name + ", parentId=" + parentId + ", url=" + url + ", leaf=" + leaf + ", level=" + level + ", code=" + code + ", style="
                + style + ", isOpen=" + isOpen + ", img=" + img + ", isShow=" + isShow + ", isShowInLeft=" + isShowInLeft + ", children=" + children + "]";
    }
}
