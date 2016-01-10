/*
 * @(#)BrandVisitedController.java 2014-3-20 下午3:07:17
 * Copyright 2014 施建波, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.brand.model.BrandVisitedModel;
import com.zttx.web.module.brand.service.BrandVisitedService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * <p>File：BrandVisitedController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-20 下午3:07:17</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.DEALER)
public class BrandVisitedController extends GenericController
{
    @Autowired
    private UserInfoService      dealerUsermService;
    
    @Autowired
    private BrandVisitedService  brandVisitedService;
    
    @Autowired
    private DealerInfoService    dealerInfoService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    /**
     * 浏览过我的品牌商页面
     */
    @RequestMapping("/copartner/bevisited")
    public String visitList(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", dealerUsermService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.findById(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_bevisited";
    }
    
    /**
     * 获取浏览过我的品牌商的数据
     */
    @ResponseBody
    @RequestMapping("/copartner/bevisited/list")
    public Object listJson(Pagination pagination, BrandVisitedModel brandVisited) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandVisited.setDealerId(dealerId);
        PaginateResult<Map<String, Object>> paginateResult = brandVisitedService.getBrandVisitedPage(pagination, brandVisited);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 品牌申请操作页面
     * @param brandId
     * @return
     */
    @RequestMapping("/copartner/bevisited/applylist")
    @ResponseBody
    public Object applyList(String brandId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<Map<String, Object>> applyList = brandVisitedService.getBrandApplyList(dealerId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS, applyList);
    }
}
