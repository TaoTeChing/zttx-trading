/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.RulesInfo;
import com.zttx.web.module.fronts.model.RulesFilter;
import com.zttx.web.module.fronts.service.RulesInfoService;

/**
 * 规则内容信息 控制器
 * <p>File：RulesInfoController.java </p>
 * <p>Title: RulesInfoController </p>
 * <p>Description:RulesInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
public class RulesInfoController extends GenericController
{
    @Autowired(required = true)
    private RulesInfoService rulesInfoService;
    
    /**
     * 规则导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/rules")
    public String rules() throws BusinessException
    {
        return "fronts/rules/index";
    }
    
    /**
     * 规则分类搜索导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/rules/list")
    public ModelAndView rulesList(RulesFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/rules/list");
        mav.addObject("filter", filter);
        return mav;
    }
    
    /**
     * 查看详情
     * @param id
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/rules/info/{id}")
    public String detail(@PathVariable("id") String id, Model model) throws BusinessException
    {
        RulesInfo rulesInfo = rulesInfoService.selectByPrimaryKey(id);
        model.addAttribute("rulesInfo", rulesInfo);
        return "fronts/rules/detail";
    }
}
