/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.service.DealerFavoriteService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商产品收藏 控制器
 * <p>File：DealerFavoriteController.java </p>
 * <p>Title: DealerFavoriteController </p>
 * <p>Description:DealerFavoriteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerFavorite")
public class DealerFavoriteController extends GenericController
{
    private final static Logger   logger = LoggerFactory.getLogger(DealerFavoriteController.class);
    
    @Autowired(required = true)
    private DealerFavoriteService dealerFavoriteService;
    
    @Autowired
    private DataDictValueService  dataDictValueService;
    
    /* ========================================= 经销商 产品收藏夹 [@author易永耀] begin================================================ */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/productFavorite")
    public String favoriteIndex(String brandsId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<Map<String, Object>> mapList = dealerFavoriteService.selectFavoriteCata(dealerId); // 品牌类目 分类
        List<DataDictValue> dataDictValueList = dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT); // 数据字典 经销商搜索排序
        model.addAttribute("brandsCataList", mapList);
        model.addAttribute("sortTypeList", dataDictValueList);
        model.addAttribute("brandsId", brandsId);
        return "dealer/favorite_index";
    }
    
    /**
     * 产品收藏夹 data
     * @param pagination
     * @param filter
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/productFavorite/data", method = RequestMethod.POST)
    public JsonMessage favoriteData(Pagination pagination, ProductFilter filter)
    {
        try
        {
            pagination.setPageSize(20);
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            filter.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> result = dealerFavoriteService.selectProductFavoritePage(pagination, filter);
            return this.getJsonMessage(ExceptionConst.SUCCESS, result);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /** 添加产品收藏  @author 施建波*/
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public JsonMessage addFavoriteAction(DealerFavorite dealerFavorite)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            dealerFavorite.setDealerId(dealerId);
            dealerFavoriteService.addDealerFavorite(dealerFavorite);
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /** 删除产品收藏 @author 施建波*/
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/removeFavorite", method = RequestMethod.POST)
    public JsonMessage removeFavoriteAction(DealerFavorite dealerFavorite)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            dealerFavorite.setDealerId(dealerId);
            dealerFavoriteService.removeDealerFavorite(dealerFavorite);
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    /* ========================================= 经销商 产品收藏夹 end ================================================ */
}
