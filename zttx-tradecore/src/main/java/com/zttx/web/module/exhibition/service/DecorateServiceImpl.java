/*
 * @(#)DecorateServiceImpl.java 2015-8-25 下午1:20:06
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.DecorateConfigConst;
import com.zttx.web.consts.DecorateHeaderConst;
import com.zttx.web.consts.DecorateMenuConst;
import com.zttx.web.module.brand.entity.BrandRoom;
import com.zttx.web.module.brand.mapper.BrandRoomMapper;
import com.zttx.web.module.exhibition.entity.*;
import com.zttx.web.module.exhibition.mapper.*;
import com.zttx.web.module.exhibition.model.MenuJsonModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.GlobalStaticTextUtils;

/**
 * <p>File：DecorateServiceImpl.java</p>
 * <p>Title: 品牌装修 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-25 下午1:20:06</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
@Service
public class DecorateServiceImpl extends GenericServiceApiImpl<DecorateConfig> implements DecorateService
{
    @Autowired
    private DecorateConfigMapper decorateConfigMapper;
    
    @Autowired
    private DecorateConfigLogMapper decorateConfigLogMapper;
    
    @Autowired
    private BrandRoomMapper brandRoomMapper;

    @Autowired
    private DecorateHeaderMapper decorateHeaderMapper;

    @Autowired
    private DecorateHeaderLogMapper decorateHeaderLogMapper;

    @Autowired
    private DecorateMenuMapper decorateMenuMapper;

    @Autowired
    private DecorateMenuLogMapper decorateMenuLogMapper;

    @Autowired
    private DecorateGlobalMapper decorateGlobalMapper;

    @Autowired
    private DecorateGlobalLogMapper decorateGlobalLogMapper;

    @Autowired
    private DecorateSysMenuMapper decorateSysMenuMapper;

    @Override
    public String getConfigsHtml(String brandId, String brandsId, Short tagId)
    {
        List<DecorateConfig> configs = findConfigs(brandId, brandsId, tagId, false);
        return getConfigsHtml(configs);
    }
    
    @Override
    public String getConfigsHtmlView(String brandId, String brandsId, Short tagId) throws IllegalAccessException, InvocationTargetException
    {
        List<DecorateConfigLog> logs = decorateConfigLogMapper.findConfigLogs(brandId, brandsId, tagId, Boolean.FALSE);
        List<DecorateConfig> configList = null;
        if (logs != null && !logs.isEmpty())
        {
            configList = Lists.newArrayList();
            for (DecorateConfigLog log : logs)
            {
                DecorateConfig config = new DecorateConfig();
                BeanUtils.copyProperties(config, log);
                configList.add(config);
            }
        }
        return getConfigsHtml(configList);
    }
    
    @Override
    public List<DecorateConfig> findConfigs(String brandId, String brandsId, Short tagId, boolean delFlag)
    {
        DecorateConfig queryParams = new DecorateConfig();  
        queryParams.setBrandId(brandId);
        queryParams.setBrandsId(brandsId);
        queryParams.setTagId(tagId);
        queryParams.setDelFlag(delFlag);
        List<DecorateConfig> list = decorateConfigMapper.findList(queryParams);
        if (null == list || list.isEmpty())
        {
            list = Lists.newArrayList();
            list.add(getBrandsModel(brandId, brandsId));
        }
        for (DecorateConfig config : list)
        {
            String text = config.getShowText();
            if (StringUtils.isNotBlank(text))
            {
                config.setShowTextUnescape(HtmlUtils.htmlUnescape(text));
            }
        }
        return list;
    }

    @Override
    public String getConfigsHtml(List<DecorateConfig> configList)
    {
        StringBuffer html = new StringBuffer();
        if (configList != null)
        {
            for (DecorateConfig config : configList)
            {
                Boolean showTitle = config.getShowTitle();
                if (showTitle == null)
                {
                    showTitle = false;
                }
                html.append("<div>");
                if (showTitle)
                {
                    html.append("<h3 class=\"title\">" + config.getTitle() + "</h3>");
                }
                html.append("<div class=\"content\">");
                String content = config.getShowText();
                if (StringUtils.isNotBlank(content))
                {
                    html.append(HtmlUtils.htmlUnescape(content));
                }
                html.append("</div></div>");
            }
        }
        return html.toString();
    }
    
    
    /**
     * 获取品牌介绍自定义模块
     * @param brandId
     * @param brandsId
     * @return
     */
    private DecorateConfig getBrandsModel(String brandId, String brandsId)
    {
        DecorateConfig config = new DecorateConfig();
        config.setBrandsId(brandsId);
        config.setBrandId(brandId);
        config.setTitle("品牌介绍");
        config.setShowTitle(true);
        config.setConfigType(DecorateConfigConst.CONFIG_TYPE_FIXED);
        
        BrandRoom room = brandRoomMapper.selectByPrimaryKey(brandsId);
        if (room!=null){
            config.setShowText(room.getBrandMark());
        }
        return config;
    }

    @Override
    public void saveForRelease(String brandId, String brandsId)  throws InvocationTargetException, IllegalAccessException
    {
        Short tagId = DecorateConfigConst.TAGID_LEFT;
        saveConfigs(brandId, brandsId, tagId);
        saveHeader(brandId, brandsId);
        saveGlobal(brandId, brandsId);
        saveMenu(brandId, brandsId);
        //String headerHtml = getHeaderHtml(brandId, brandsId);
        //String configHtml = getConfigsHtml(brandId, brandsId, tagId);
    }


    private String getHeaderHtml(String brandId, String brandsId) {
        List<DecorateHeader> headerList = decorateHeaderMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        DecorateHeader header = null;
        if (CollectionUtils.isNotEmpty(headerList)) {
            header  = headerList.get(0);
        }
        DecorateMenu menu  = null;
        List<DecorateMenu> menuList = decorateMenuMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(menuList)) {
            menu = menuList.get(0);
        }
        // meger 系统默认导航菜单
        List<MenuJsonModel> allMenus = mergeSysMenu(menu, brandId, brandsId);
        if (null == menu)
        {
            menu = new DecorateMenu(brandId, brandsId, false, null);
        }

        menu.setMenus(allMenus);
        return getHeaderHtml(header, menu, brandsId);
        // return getHeaderHtmlByDev(header, menu, brandsId);
    }


    /**
     * 构建正式环境品牌展示、产品展示、企业展示等菜单项
     *
     * @param header
     * @param menu
     * @param brandsId
     * @return String
     */
    protected String getHeaderHtml(DecorateHeader header, DecorateMenu menu, String brandsId)
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
                // "<span class=\"address font f-r\">店铺地址：http://duocare.8637.com 复制</span>" +
                "</div>");
        html.append("</div></div>");
        return html.toString();
    }


    public List<MenuJsonModel> mergeSysMenu(DecorateMenu menu, String brandId, String brandsId)
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
        List<DecorateSysMenu> sysMenus = decorateSysMenuMapper.selectAll();
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

    private void saveConfigs(String brandId, String brandsId, Short tagId) throws InvocationTargetException, IllegalAccessException
    {

        decorateConfigMapper.delByBrandIdAndBrandesId(brandId, brandsId);

        DecorateConfigLog params = new DecorateConfigLog();
        params.setBrandId(brandId);
        params.setBrandsId(brandsId);
        params.setTagId(tagId);
        params.setDelFlag(false);
        List<DecorateConfigLog> logList = decorateConfigLogMapper.findList(params);

        if (logList != null && !logList.isEmpty())
        {
            for (DecorateConfigLog log : logList)
            {
                DecorateConfig config = new DecorateConfig();
                BeanUtils.copyProperties(config, log);
                config.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                decorateConfigMapper.insert(config);
            }
        }
    }


    private void saveHeader(String brandId, String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        List<DecorateHeaderLog> logList = decorateHeaderLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(logList))
        {
            DecorateHeaderLog log = logList.get(0);
            Long now = CalendarUtils.getCurrentLong();

            DecorateHeader header = null;
            List<DecorateHeader> headerList = decorateHeaderMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isNotEmpty(headerList)) {
                header = headerList.get(0);
            }

            String id = null;
            Long createTime = null;
            if (header != null)
            {
                id = header.getRefrenceId();
                createTime = header.getCreateTime();
            }
            else
            {
                header = new DecorateHeader();
            }

            BeanUtils.copyProperties(header, log);

            header.setRefrenceId(id);
            if (StringUtils.isNotBlank(id))
            {
                header.setCreateTime(createTime);
                header.setUpdateTime(now);
                decorateHeaderMapper.updateByPrimaryKey(header);
            }
            else
            {
                header.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                header.setUpdateTime(now);
                header.setCreateTime(now);
                decorateHeaderMapper.insert(header);
            }

            log.setDelFlag(false);
            log.setUpdateTime(now);
            decorateHeaderLogMapper.updateByPrimaryKey(log);
        }
    }


    /**
     * 保存全局属性，并更改临时记录状态
     *
     * @param brandId
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void saveGlobal(String brandId, String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        List<DecorateGlobalLog> logList = decorateGlobalLogMapper.findLogByBrandId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(logList))
        {
            DecorateGlobalLog log = logList.get(0);
            Long now = CalendarUtils.getCurrentLong();
            List<DecorateGlobal> globalList = decorateGlobalMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            DecorateGlobal global = null;
            if (CollectionUtils.isNotEmpty(globalList)) {
                global = globalList.get(0);
            }

            String id = null;
            Long createTime = null;
            if (global != null) {
                id = global.getRefrenceId();
                createTime = global.getCreateTime();
            } else {
                global = new DecorateGlobal();
            }

            BeanUtils.copyProperties(global, log);

            global.setRefrenceId(id);
            if (StringUtils.isNotBlank(id)) {
                global.setCreateTime(createTime);
                global.setUpdateTime(now);
                decorateGlobalMapper.updateByPrimaryKey(global);
            } else {
                global.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                global.setCreateTime(now);
                global.setUpdateTime(now);
                decorateGlobalMapper.insert(global);
            }

            log.setDelFlag(false);
            log.setUpdateTime(now);
            decorateGlobalLogMapper.updateByPrimaryKey(log);
        }
    }


    /**
     * 保存菜单，并更新菜单临时表状态
     *
     * @param brandId
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void saveMenu(String brandId, String brandsId) throws InvocationTargetException, IllegalAccessException {
        List<DecorateMenuLog> logList = decorateMenuLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(logList)) {
            DecorateMenuLog log = logList.get(0);
            Long now = CalendarUtils.getCurrentLong();

            List<DecorateMenu> menuList = decorateMenuMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            DecorateMenu menu = null;
            if (CollectionUtils.isNotEmpty(menuList)) {
                menu = menuList.get(0);
            }

            String id = null;
            Long createTime = null;
            if (menu != null) {
                id = menu.getRefrenceId();
                createTime = menu.getCreateTime();
            } else {
                menu = new DecorateMenu();
            }

            BeanUtils.copyProperties(menu, log);

            menu.setRefrenceId(id);
            if (StringUtils.isNotBlank(id)) {
                menu.setCreateTime(createTime);
                decorateMenuMapper.updateByPrimaryKey(menu);
            } else {
                menu.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                menu.setUpdateTime(now);
                menu.setCreateTime(now);
                decorateMenuMapper.insert(menu);
            }

            log.setUpdateTime(now);
            log.setDelFlag(false);
            decorateMenuLogMapper.updateByPrimaryKey(log);

        }

    }
}
