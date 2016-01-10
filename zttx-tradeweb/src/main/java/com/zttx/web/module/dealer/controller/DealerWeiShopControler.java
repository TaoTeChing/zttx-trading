/*
 * @(#)DealerWeshopTrendsControler.java 2014-7-18 下午1:14:19
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.service.DealerWeiShopService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 微店管理 控制器
 * <p>File：DealerWeiShopControler.java </p>
 * <p>Title: DealerWeiShopControler </p>
 * <p>Description:DealerWeiShopControler </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("dealer/weishop")
public class DealerWeiShopControler extends GenericController
{
    @Autowired
    private DealerWeiShopService dealerWeiShopService;
    
    /**
     * 微店管理页面(如果开通过微店 跳转到:list 没开通过 跳转到: forceOpen)
     * @param forceOpen
     * @return
     * @throws BusinessException
     */
    @RequestMapping("")
    @RequiresPermissions(value = "dealer:center")
    public String index(@RequestParam(defaultValue = "false") boolean forceOpen) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        JsonMessage resultInfo = dealerWeiShopService.getDealerWeiShopsBy(dealerId);
        if (forceOpen || (resultInfo != null && resultInfo.getCode() == 121000 && resultInfo.getRows().size() == 0))
        {
            return "dealer/weishop_open";
        }
        else
        {
            return "dealer/weishop_list";
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/data")
    @RequiresPermissions(value = "dealer:center")
    public JsonMessage getData() throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        JsonMessage jsonMessage = dealerWeiShopService.getDealerWeiShopsBy(dealerId);
        return jsonMessage;
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/sendValidCode")
    public JsonMessage sendValidCode(String userMobile)
    {
        JsonMessage sendValidCode = dealerWeiShopService.sendWeiShopValidCode(userMobile, null);
        return sendValidCode;
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/openWeiShop")
    public JsonMessage openWeiShop(String userMobile, String validCode) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        boolean openWeiShop = dealerWeiShopService.openWeiShop(dealerId, userMobile, validCode);
        if (openWeiShop) { return super.getJsonMessage(CommonConst.SUCCESS); }
        return super.getJsonMessage(CommonConst.FAIL);
    }
}
