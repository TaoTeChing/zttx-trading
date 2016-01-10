/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.dealer.entity.DealerAddr;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.WebServiceCom;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 网站服务项目 控制器
 * <p>File：WebServiceItemsController.java </p>
 * <p>Title: WebServiceItemsController </p>
 * <p>Description:WebServiceItemsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/webServiceItems")
public class WebServiceItemsController extends GenericController
{
    @Autowired(required = true)
    private WebServiceItemsService  webServiceItemsService;
    
    @Autowired
    private DealerBuySerLogService  dealerBuySerLogService;
    
    @Autowired
    private DealerBuyServiceService dealerBuyServiceService;
    
    @Autowired
    private DealerAddrService       dealerAddrService;
    
    @Autowired
    private WebServiceComService    webServiceComService;
    
    @Autowired
    private RegionalService         regionalService;
    
    /** 服务管理 首页*/
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/index")
    public String defaultIndex()
    {
        return "dealer/service_index";
    }
    
    /** 服务管理 数据 */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/list")
    public JsonMessage list(WebServiceItems webServiceItems, Pagination pagination) throws BusinessException
    {
        webServiceItems.setServiceType(CommonConstant.WebServiceItems.SERVICE_TYPE_DEALER);
        webServiceItems.setPage(pagination);
        List<WebServiceItems> webServiceItemsList = webServiceItemsService.search(webServiceItems);
        return this.getJsonMessage(CommonConst.SUCCESS, new PaginateResult<>(pagination, webServiceItemsList));
    }
    
    /** 服务管理 详情*/
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/details")
    public String showDetails(String refrenceId, Model model, HttpServletRequest request) throws BusinessException
    {
        WebServiceItems _webServiceItems = webServiceItemsService.selectByPrimaryKey(refrenceId);
        if (_webServiceItems == null) { throw new BusinessException("服务项不存在!"); }
        _webServiceItems.setViewNum(_webServiceItems.getViewNum() == null ? 1 : _webServiceItems.getViewNum() + 1);
        webServiceItemsService.updateByPrimaryKeySelective(_webServiceItems);// 修改浏览次数
        WebServiceCom _webServiceCom = webServiceComService.selectByPrimaryKey(_webServiceItems.getComId());
        model.addAttribute("webServiceItems", _webServiceItems);
        model.addAttribute("webServiceCom", _webServiceCom);
        // 硬件设备采购服务
        if ("S006".equals(refrenceId))
        {
            // 当前物理地址
            Regional regional = regionalService.getRegionalByRequest(request);
            if (regional != null)
            {
                model.addAttribute("province", regional.getAreaNo() / 10000 * 10000);
                model.addAttribute("city", regional.getAreaNo() / 100 * 100);
                model.addAttribute("country", regional.getAreaNo());
            }
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            List<DealerAddr> dealerAddrs = dealerAddrService.getAllDealerAddrsList(dealerId);
            model.addAttribute("dealerAddrs", dealerAddrs);
            model.addAttribute("defaultAddr", dealerAddrService.getDefaultDealerAddrBy(dealerId));
        }
        return "dealer/service_details";
    }
    
    /** 增加服务购买记录 */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public JsonMessage buy(DealerBuySerLog dealerBuySerLog) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerBuySerLog.setDealerId(dealerId);
        dealerBuySerLogService.addDealerBuySerLog(dealerBuySerLog);
        return this.getJsonMessage(CommonConst.SUCCESS, dealerBuySerLog.getRefrenceId());
    }
    
    /** 添加 ERP 试用服务*/
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/trail", method = RequestMethod.POST)
    public JsonMessage trail() throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerBuyServiceService.addTrialErpDealerBuyService(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
