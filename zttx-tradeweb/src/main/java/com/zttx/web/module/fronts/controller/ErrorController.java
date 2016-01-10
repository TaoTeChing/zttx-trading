package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.core.GenericController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>File：ErrorController.java </p>
 * <p>Title: 申明所有异常编码返回的页面 </p>
 * <p>Description: ErrorController </p>
 * <p>Copyright: Copyright (c) 15/9/23</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends GenericController
{
    @RequestMapping("/403")
    public String e403(Model model)
    {
        return "error/403";
    }
    
    @RequestMapping("/404")
    public String e404(Model model)
    {
        return "error/404";
    }
    
    @RequestMapping("/500")
    public String e500(Model model)
    {
        return "error/500";
    }
    
    @RequestMapping("/502")
    public String e502(Model model)
    {
        return "error/502";
    }
    
    @RequestMapping("/503")
    public String e503(Model model)
    {
        return "error/503";
    }
}
