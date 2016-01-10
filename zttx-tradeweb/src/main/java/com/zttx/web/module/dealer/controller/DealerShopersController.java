/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.service.DealerShopersService;

/**
 * 购物详细信息表 控制器
 * <p>File：DealerShopersController.java </p>
 * <p>Title: DealerShopersController </p>
 * <p>Description:DealerShopersController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerShopers")
public class DealerShopersController extends GenericController
{
    @Autowired(required = true)
    private DealerShopersService dealerShopersService;
    
    /* ========================================= 购物车购买数量变动 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/batchUpdate")
    public JsonMessage batchUpdateShopersCart(@RequestBody List<JSONObject> jsonObjectList) throws BusinessException
    {
        try
        {
            dealerShopersService.batchUpdateShopers(jsonObjectList);
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), e.getLocalizedMessage());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    /* ========================================= 购物车购买数量变动 end ================================================ */
}
