/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.controller;

import com.zttx.web.module.fronts.entity.HelpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.HelpFilter;
import com.zttx.web.module.fronts.service.HelpInfoService;

/**
 * 帮助内容 控制器
 * <p>File：HelpInfoController.java </p>
 * <p>Title: HelpInfoController </p>
 * <p>Description:HelpInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
public class HelpInfoController extends GenericController
{
    @Autowired(required = true)
    private HelpInfoService helpInfoService;
    
    /**
     * 帮助导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/help")
    public String help() throws BusinessException
    {
        return "fronts/help/index";
    }
    
    /**
     * 帮助分类搜索导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/help/list")
    public ModelAndView helpList(HelpFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/help/list");
        mav.addObject("filter", filter);
        return mav;
    }
    
    /**
     * 查询帮助信息
     * @param id
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/help/info/{id}")
    public String detail(@PathVariable("id") String id, Model model) throws BusinessException
    {
        HelpInfo helpInfo = helpInfoService.selectByPrimaryKey(id);
        model.addAttribute("helpInfo", helpInfo);
        return "fronts/help/info";
    }
}
