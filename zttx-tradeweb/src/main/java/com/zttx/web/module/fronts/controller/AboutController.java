package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>File：AboutController.java </p>
 * <p>Title: 关于控制器 </p>
 * <p>Description: AboutController </p>
 * <p>Copyright: Copyright (c) 2014 08/19/2015 09:34</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/about")
public class AboutController extends GenericController
{
    /**
     * 关于导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("")
    public String about() throws BusinessException
    {
        return "fronts/about/index";
    }
    
    /**
     * 模式与优势导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/superiority")
    public String superiority() throws BusinessException
    {
        return "fronts/about/superiority";
    }
    
    /**
    * 企业历程导航
    * @return
    * @throws BusinessException
    */
    @RequestMapping("/progress")
    public String progress() throws BusinessException
    {
        return "fronts/about/progress";
    }
    
    /**
    * 加入我们导航
    * @return
    * @throws BusinessException
    */
    @RequestMapping("/joinus")
    public String joinus() throws BusinessException
    {
        return "fronts/about/joinus";
    }
    
    /**
    * 联系我们导航
    * @return
    * @throws BusinessException
    */
    @RequestMapping("/contactus")
    public String contactus() throws BusinessException
    {
        return "fronts/about/contactus";
    }
}
