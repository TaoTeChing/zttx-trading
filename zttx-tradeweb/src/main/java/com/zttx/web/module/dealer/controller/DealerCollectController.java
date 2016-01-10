/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.model.DealerCollectModel;
import com.zttx.web.module.dealer.service.DealerCollectService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商品牌收藏夹 控制器
 * <p>File：DealerCollectController.java </p>
 * <p>Title: DealerCollectController </p>
 * <p>Description:DealerCollectController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerCollect")
public class DealerCollectController extends GenericController
{

    @Autowired
    private DealerCollectService dealerCollectService;

    @Autowired
    private DealerInfoService dealerInfoService;

    @Autowired
    private DataDictValueService dataDictValueService;

    @Autowired
    private UserInfoService dealerUsermService;

    /**
     * 品牌收藏夹页
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copartner/favorite")
    public String brandFavorites(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", dealerUsermService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.findById(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "/dealer/copartner_favorite";
    }

    /**
     * 收藏的品牌列表数据
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copartner/favorite/list")
    public Object listJson(Pagination pagination, DealerCollectModel dealerCollect) throws BusinessException
    {
        pagination.setPageSize(10);
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerCollect.setDealerId(dealerId);
        PaginateResult<Map<String, Object>> page = dealerCollectService.getDealerCollectsBy(pagination, dealerCollect);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }

    /**
     * 删除品牌收藏
     * @param refrenceIds
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/collect/delete")
    public JsonMessage delete(String[] refrenceIds) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        this.dealerCollectService.executeUnCollect(refrenceIds, dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
