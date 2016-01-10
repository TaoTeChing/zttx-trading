/*
 * @(#)DecorateMenuHelper.java 2015-8-18 下午7:52:31
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.consts.DecorateHeaderConst;
import com.zttx.web.consts.DecorateMenuConst;
import com.zttx.web.module.exhibition.entity.DecorateHeader;
import com.zttx.web.module.exhibition.entity.DecorateMenu;
import com.zttx.web.module.exhibition.entity.DecorateSysMenu;
import com.zttx.web.module.exhibition.model.MenuJsonModel;
import com.zttx.web.module.exhibition.service.DecorateSysMenuService;

/**
 * <p>File：DecorateMenuHelper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-18 下午7:52:31</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class DecorateMenuHelper
{
    private static DecorateSysMenuService sysMenuService=SpringUtils.getBean(DecorateSysMenuService.class);
    
    public static List<MenuJsonModel> mergeSysMenu(DecorateMenu menu, String brandId, String brandsId)
    {
        boolean isNew = false;
        List<MenuJsonModel> allMenus = null;
        if (menu != null)
        {
            String json = menu.getMenuValue();
            if (StringUtils.isNotBlank(json))
            {
                allMenus = JSON.parseArray(json, MenuJsonModel.class);
            }
        }
        else
        {
            isNew = true;
        }
        List<DecorateSysMenu> sysMenus = sysMenuService.selectAll();
        if (sysMenus != null && !sysMenus.isEmpty())
        {
            Map<String, Object> map = new HashMap<String, Object>();
            if (null == allMenus)
            {
                allMenus = new ArrayList<MenuJsonModel>();
            }
            for (MenuJsonModel model : allMenus)
            {
                String id = model.getId();
                if (StringUtils.isNotBlank(id))
                {
                    map.put(id, model);
                }
            }
            for (DecorateSysMenu sysMenu : sysMenus)
            {
                if (!map.containsKey(sysMenu.getRefrenceId()))
                {
                    MenuJsonModel model = new MenuJsonModel();
                    model.setId(sysMenu.getRefrenceId());
                    model.setN(sysMenu.getMenuName());
                    if (isNew)
                    {
                        model.setS(String.valueOf(DecorateMenuConst.MENU_SHOW));
                    }
                    else
                    {
                        model.setS(String.valueOf(DecorateMenuConst.MENU_HIDE));
                    }
                    if (StringUtils.isNotBlank(sysMenu.getMenuUrl()) && StringUtils.isNotBlank(brandsId) && StringUtils.isNotBlank(brandId))
                    {
                        String url = sysMenu.getMenuUrl().replace("{brandesId}", brandsId).replace("{brandId}", brandId);
                        model.setUrl(url);
                    }
                    allMenus.add(model);
                }
            }
        }
        return allMenus;
    }
    
    /**
     * 构建正式环境品牌展示、产品展示、企业展示等菜单项
     *
     * @param header
     * @param menu
     * @param brandsId
     * @return String
     */
    public static String getHeaderHtml(DecorateHeader header, DecorateMenu menu, String brandsId)
    {
        StringBuffer html = new StringBuffer("");
        html.append("<div class=\"header-nav\" id=\"header-nav\">");
        html.append("<div class=\"header-navcen\" id=\"header-navcen\">");
        html.append("<div class=\"header-cen\" id=\"headerhover\">");
        if (header != null)
        {
            Integer showCate = header.getShowCate();
            switch (showCate)
            {
                case DecorateHeaderConst.SHOWCATE_CUSTOM:
                    html.append("<div id=\"headnav-defualt\">");
                    html.append("<style id=\"custom_style\"> .header-nav { background: #fff;background-image: none;} .header-cen { background: #fff; height:100%;}</style>");
                    html.append("<div class=\"customContent\">");
                    html.append(header.getHeaderText()).append("</div></div>");
                    break;
                case DecorateHeaderConst.SHOWCATE_MARKET:
                    break;
                default:
                    html.append("<div id=\"headnav-defualt\"><div class=\"logo\" >");
                    html.append("<h2>").append(header.getComName());
                    html.append("</h2>").append("<h3>");
                    html.append(header.getMainDeal());
                    html.append("</h3></div></div>");
            }
        }
        String sld = "http://" + GlobalStaticTextUtils.getBrandsIdSubDomain(brandsId);
        html.append("</div>");
        // menu
        html.append("<div class=\"nav-cen\" style=\"height: 30px\" id=\"headerhover1\"><ul class=\"menu-list\" id=\"menu-list\">");
        html.append("<li class=\"menu\"><a class=\"menu-link\" href=\"" + sld + "$domain/index\" title=\"\" target=\"\"><span class=\"title\">品牌首页</span></a></li>");
        if (menu != null)
        {
            List<MenuJsonModel> list = menu.getMenus();
            if (list != null && !list.isEmpty())
            {
                for (MenuJsonModel model : list)
                {
                    if (DecorateMenuConst.MENU_SHOW == Short.valueOf(model.getS()))
                    {
                        String url = model.getUrl();
                        if ("213b20a7d74a11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/recruit";
                        }
                        else if ("38e5b1d4d74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/visual";
                        }
                        else if ("3c66d46fd74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/product";
                        }
                        else if ("3fc78b69d74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/company";
                        }
                        else if ("42849d3ad74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/network";
                        }
                        else if ("468e5684d74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/news";
                        }
                        else if ("4e70e89bd74b11e39f02c81f66446d84".equals(model.getId()))
                        {
                            url = "$domain/document";
                        }
                        String name = model.getN();
                        html.append("<li class=\"menu\"><a class=\"menu-link\" href=\"" + sld + url + "\" title=\"" + name + "\" ><span class=\"title\">" + name
                                + "</span></a></li>");
                    }
                }
            }
        }
        html.append("</ul>" +
                "</div>");
        html.append("</div></div>");
        return html.toString();
    }
    
}
