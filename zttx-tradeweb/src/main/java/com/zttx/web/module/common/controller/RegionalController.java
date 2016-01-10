/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zttx.sdk.core.GenericController;
import com.zttx.web.module.common.service.RegionalService;

import java.util.Map;

/**
 * 全国区域信息 控制器
 * <p>File：RegionalController.java </p>
 * <p>Title: RegionalController </p>
 * <p>Description:RegionalController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/regional")
public class RegionalController extends GenericController
{
    @Autowired(required = true)
    private RegionalService regionalService;


    /**
     * 获取脚本使用格式的全部地区信息
     * @return
     * @author 周光暖
     */
    @RequestMapping(value = "/searchAllArea")
    public String searchAll(String sale, String regProvince, String regCity, String regCounty, String regAreaNo, String style, String status, Boolean required, Model model)
    {
        Map<String, Object> regionalMap = regionalService.getRegionalMap();
        model.addAttribute("required", required);
        model.addAttribute("regionalMap", JSON.toJSONString(regionalMap));
        model.addAttribute("sale", sale);
        model.addAttribute("province", regProvince);
        model.addAttribute("city", regCity);
        model.addAttribute("county", regCounty);
        model.addAttribute("style", style);
        model.addAttribute("areaNo", regAreaNo);
        model.addAttribute("status", status);
        return "common/area";
    }
}
