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
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandAdvice;
import com.zttx.web.module.brand.service.BrandAdviceService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 品牌商优化建议 控制器
 * <p>File：BrandAdviceController.java </p>
 * <p>Title: BrandAdviceController </p>
 * <p>Description:BrandAdviceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandAdvice")
public class BrandAdviceController extends GenericController
{
    @Autowired(required = true)
    private BrandAdviceService brandAdviceService;
    
    /**
     * 跳转品牌商建议页面
     * @return
     */
    @RequestMapping(value = "/index")
    @RequiresPermissions("brand:center")
    public String advice()
    {
        return "brand/center_advice";
    }
    
    /**
     * 品牌商 建议 保存
     * @param brandAdvice
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/submit")
    public JsonMessage submit(BrandAdvice brandAdvice) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        JsonMessage resultJson = new JsonMessage();
        Integer code = CommonConst.FAIL.code;
        if (beanValidator(resultJson, brandAdvice))
        {
            brandAdvice.setBrandId(brandId);
            brandAdviceService.save(brandAdvice);
            code = CommonConst.SUCCESS.code;
        }
        resultJson.setCode(code);
        return resultJson;
    }
}
