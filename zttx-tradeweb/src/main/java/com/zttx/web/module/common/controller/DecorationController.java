/*
 * @(#)DecorationController.java 2015-8-28 下午2:21:30
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DecorateConfigConst;
import com.zttx.web.consts.DecorateHeaderEnumConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.exhibition.entity.*;
import com.zttx.web.module.exhibition.model.MenuJsonModel;
import com.zttx.web.module.exhibition.service.*;

/**
 * <p>File：DecorationController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-28 下午2:21:30</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.DECORATION)
public class DecorationController extends GenericBaseController
{
    
    @Autowired
    private DecorateHeaderService decorateHeaderService;
    
    @Autowired
    private DecorateGlobalService decorateGlobalService;
    
    @Autowired
    private DecorateMenuService   decorateMenuService;
    
    @Autowired
    private DecorateConfigService configService;
    
    @Autowired
    private DecorateService       decorateService;
    
    @Autowired
    private BrandMessageService   brandMessageService;

    @Autowired
    private BrandInfoService brandInfoService;
    
    /**
     * 页面管理
     * @return
     */
    @RequestMapping(value = "/managePage/{brandsId}")
    public String listMessages(HttpServletRequest request, @PathVariable("brandsId") String brandsId, Model model)
    {
        String brandId = this.getCurrentLoginBrandId();
        if (StringUtils.isNotBlank(brandId)){
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            model.addAttribute("brandUserm", brandInfo);
            DecorateGlobal dGlobal = decorateGlobalService.findLatestByBrandId(brandId, brandsId);
            model.addAttribute("dGlobal", dGlobal);// 全局属性
            Long count = brandMessageService.getBrandMessageCount(brandId);
            model.addAttribute("count", count);
        }
        return "/decoration/manage_page";
    }
    
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index/{brandsId}")
    public String index(HttpServletRequest request, @PathVariable("brandsId") String brandsId, Model model) throws InvocationTargetException, IllegalAccessException
    {
        String brandId = this.getCurrentLoginBrandId();
        if (StringUtils.isNotBlank(brandId)){
            setHeaderData(model, brandId, brandsId);
            Short tagId = DecorateConfigConst.TAGID_LEFT;// todo tagId 待确定
            List<DecorateConfigLog> configList = configService.findConfigLogs(brandId, brandsId, tagId, false);
            DecorateConfigLog brandInfoConfigLog = configService.findBrandConfigLog(brandId, brandsId);// 品牌介绍模块
            if (null == brandInfoConfigLog)
            {
                brandInfoConfigLog = configService.insertInitLog(brandId, brandsId);
                if (null == configList)
                {
                    configList = new ArrayList<DecorateConfigLog>();
                }
                configList.add(brandInfoConfigLog);
            }
            model.addAttribute("brandInfoConfigLog", brandInfoConfigLog);
            model.addAttribute("configList", configList);
        }
        model.addAttribute("brandId", brandId);
        model.addAttribute("brandesId",brandsId);
        return "decoration/index";
    }
    
    /**
     * 设置头部栏数据
     * @param model
     * @param brandId
     */
    private void setHeaderData(Model model, String brandId, String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        DecorateHeader header = decorateHeaderService.findLatestByBrandId(brandId, brandsId);
        model.addAttribute("dHeader", header);
        DecorateGlobal dGlobal = decorateGlobalService.findLatestByBrandId(brandId, brandsId);
        model.addAttribute("dGlobal", dGlobal);// 全局属性
        
        
        String headerText = null;
        if (header != null && StringUtils.isNotBlank(header.getHeaderText()))
        {
            headerText = HtmlUtils.htmlUnescape(header.getHeaderText());
        }
        model.addAttribute("headerText", headerText);// html反转义
        
        DecorateMenu menu = decorateMenuService.findLatestBrandMenus(brandId, brandsId);
        if (menu!=null){
            model.addAttribute("allMenus", menu.getMenus());
        }
    }
    
    /**
     * 获取heder数据
     * @param request
     * @param brandsId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/ajax_header/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object getHeaderData(HttpServletRequest request, @PathVariable("brandsId") String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        String brandId = this.getCurrentLoginBrandId();
        DecorateHeader header = decorateHeaderService.findLatestByBrandId(brandId, brandsId);
        return getJsonMessage(CommonConst.SUCCESS, header);
    }
    
    /**
     * 保存头部
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajax_saveHeader/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object saveHeader(HttpServletRequest request, @PathVariable("brandsId") String brandsId, DecorateHeaderLog header) throws InvocationTargetException,
            IllegalAccessException
    {
        String brandId = this.getCurrentLoginBrandId();
        JsonMessage json = null;
        try
        {
            if (StringUtils.isBlank(brandsId)) { throw new BusinessException(DecorateHeaderEnumConst.BRANDSID_NOT_EXIST); }
            decorateHeaderService.saveHeaderLog(header, brandId, brandsId, request);
            json = getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            json = getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    /**
     * 保存头部导航
     * @param request
     * @param header
     * @return
     */
    @RequestMapping(value = "/ajax_saveMenu/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object saveNav(HttpServletRequest request, @PathVariable("brandsId") String brandsId, DecorateHeaderLog header) throws InvocationTargetException,
            IllegalAccessException
    {
        String brandId = this.getCurrentLoginBrandId();
        JsonMessage json = null;
        try
        {
            if (StringUtils.isBlank(brandsId)) { throw new BusinessException(DecorateHeaderEnumConst.BRANDSID_NOT_EXIST); }
            List<MenuJsonModel> menus = header.getMenus();
            decorateMenuService.saveNavlog(brandId, brandsId, header, menus, request);
            throw new BusinessException(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            json = getJsonMessage(e.getErrorCode(), header.getMenus());
        }
        return json;
    }
    
    /**
     * 保存自定义模块
     * @param request
     * @param brandsId
     * @param log
     * @return
     */
    @RequestMapping(value = "/ajax_saveConfig/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object saveConfig(HttpServletRequest request, @PathVariable("brandsId") String brandsId, DecorateConfigLog log)
    {
        String brandId = this.getCurrentLoginBrandId();
        log.setBrandId(brandId);
        log.setBrandsId(brandsId);
        log = configService.saveConfigLog(log, log.getPreIds(), log.getNextIds());
        JsonMessage json = getJsonMessage(CommonConst.SUCCESS, log);
        return json;
    }
    
    /**
     * 自定义模块重新排序
     * @param request
     * @param brandsId
     * @param log
     * @return
     */
    @RequestMapping(value = "/ajax_configSort/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object configSort(HttpServletRequest request, @PathVariable("brandsId") String brandsId, DecorateConfigLog log)
    {
        String brandId = this.getCurrentLoginBrandId();
        List<String> preIds = log.getPreIds();
        configService.updateConfigLogShowOrder(brandId, brandsId, preIds);
        JsonMessage json = getJsonMessage(CommonConst.SUCCESS, log);
        return json;
    }
    
    /**
     * 删除自定义模块
     * @param request
     * @param brandsId
     * @param configId
     * @return
     */
    @RequestMapping(value = "/ajax_delConfig/{brandsId}/{configId}", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteConfig(HttpServletRequest request, @PathVariable("brandsId") String brandsId, @PathVariable String configId)
    {
        String brandId = this.getCurrentLoginBrandId();
        configService.deleteConfigLog(brandId, brandsId, configId);
        JsonMessage json = getJsonMessage(CommonConst.SUCCESS);
        return json;
    }
    
    /**
     * 重新显示品牌介绍模块
     * @param request
     * @param brandsId
     * @param configId
     * @return
     */
    @RequestMapping(value = "/ajax_showBrand/{brandsId}/{configId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBrandConfigState(HttpServletRequest request, @PathVariable("brandsId") String brandsId, @PathVariable String configId)
    {
        String brandId = this.getCurrentLoginBrandId();
        configService.updateBrandConfigLogState(brandId, brandsId, false);
        JsonMessage json = getJsonMessage(CommonConst.SUCCESS);
        return json;
    }
    
    /**
     * 全局属性
     * @param request
     * @param global
     * @return
     */
    @RequestMapping(value = "/ajax_saveGlobal/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object saveGlobal(HttpServletRequest request, @PathVariable("brandsId") String brandsId, DecorateGlobalLog global) throws InvocationTargetException,
            IllegalAccessException
    {
        DecorateGlobalLog dbGlobal = null;
        String brandId = this.getCurrentLoginBrandId();
        JsonMessage json = null;
        try
        {
            if (StringUtils.isBlank(brandsId)) { throw new BusinessException(DecorateHeaderEnumConst.BRANDSID_NOT_EXIST); }
            dbGlobal = decorateGlobalService.saveOrUpdateLog(brandId, brandsId, global, request);
            throw new BusinessException(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            json = getJsonMessage(e.getErrorCode(), dbGlobal);
        }
        return json;
    }
    
    /**
     * 风格模板
     * @param request
     * @param skinName
     * @return
     */
    @RequestMapping(value = "/ajax_saveTheme/{brandsId}", method = RequestMethod.POST)
    @ResponseBody
    public Object changeTheme(HttpServletRequest request, String skinName, @PathVariable("brandsId") String brandsId) throws InvocationTargetException,
            IllegalAccessException
    {
        if (StringUtils.isBlank(brandsId)) { return getJsonMessage(DecorateHeaderEnumConst.BRANDSID_NOT_EXIST); }
        String brandId = this.getCurrentLoginBrandId();
        decorateGlobalService.saveLogTheme(brandId, brandsId, skinName);
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     *立即发布
     * @param request
     * @return
     */
    @RequestMapping(value = "/release/{brandsId}", method = RequestMethod.POST)
    public String release(HttpServletRequest request, @PathVariable("brandsId") String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        String brandId = this.getCurrentLoginBrandId();
        decorateService.saveForRelease(brandId, brandsId);
        return "redirect:/market/brand/index/" + brandsId;// 跳转到品牌首页
    }
    
    /**
     * 品牌招募书
     * @return
     */
    @RequestMapping(value = "/recruit")
    public String showRecruit(HttpServletRequest request, Model model)
    {
        return null;
    }
    
    /**
     * 背景设置（下级页面）
     * @return
     */
    @RequestMapping(value = "background")
    public String setBackground()
    {
        return "decoration/set_background";
    }
    
    /**
     * 分类管理
     * @return
     */
    @RequestMapping(value = "manageSort")
    public String showEnterprise()
    {
        return "decoration/manage_sort";
    }
    
    /**
     * 商品分类管理
     * @return
     */
    @RequestMapping(value = "commodity")
    public String showDealernetwork()
    {
        return "decoration/commodity";
    }
    
    /**
     * 选择配套颜色
     * @return
     */
    @RequestMapping(value = "chooseColor")
    public String listNews()
    {
        return "decoration/choose_color";
    }
    
    /**
     * 样式管理
     * @return
     */
    @RequestMapping(value = "manageStyle")
    public String listMaterial()
    {
        return "decoration/manage_style";
    }
}
