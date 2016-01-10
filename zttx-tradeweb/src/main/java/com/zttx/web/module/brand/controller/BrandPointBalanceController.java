/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.brand.service.BrandPointBalanceService;

/**
 * 扣点表 控制器
 * <p>File：BrandPointBalanceController.java </p>
 * <p>Title: BrandPointBalanceController </p>
 * <p>Description:BrandPointBalanceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandPointBalance")
public class BrandPointBalanceController extends GenericController
{
    @Autowired(required = true)
    private BrandPointBalanceService brandPointBalanceService;

    /**
     * 初始化扣点值
     * @author 陈建平
     * @param joinForm
     * @param point
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/init")
    public JsonMessage init(Short joinForm, Double point) throws BusinessException
    {
    	brandPointBalanceService.saveInitBrandPointBalance(joinForm, point);
    	return getJsonMessage(ExceptionConst.SUCCESS, "操作成功");
    }
}
