package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.security.OnLineUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>File：CommonController.java </p>
 * <p>Title: 公共的控制器 </p>
 * <p>Description: CommonController </p>
 * <p>Copyright: Copyright (c) 2014 08/19/2015 10:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common")
public class CommonController extends GenericController
{
    /**
     * 页面头部
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/top")
    public String top(Model model) throws BusinessException
    {
        model.addAttribute("userPrincipal", OnLineUserUtils.getPrincipal());
        return "include/top";
    }
    
    /**
     * 页面导航
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/nav")
    public String nav(Model model) throws BusinessException
    {
        return "include/nav";
    }
}
