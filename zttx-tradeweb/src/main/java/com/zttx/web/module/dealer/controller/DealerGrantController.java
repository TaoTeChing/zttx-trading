/*
 * @(#)DealerGrantController 2014/3/28 12:53
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.common.service.DataDictValueService;

/**
 * <p>File：DealerGrantController</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/3/28 12:53</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.DEALER)
public class DealerGrantController extends GenericController
{
    @Autowired
    private DataDictValueService dataDictValueService;
    
    /**
     * 我的授权产品库页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/shopper/grant", method = RequestMethod.GET)
    public String list(Model model) throws BusinessException
    {
        model.addAttribute("sortTypeList", dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT));
        return "redirect:/dealer/shopper/orderc";
    }
}
