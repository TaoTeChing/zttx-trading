package com.zttx.web.module.adverti.controller;

import com.zttx.sdk.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>File：AdvertiControooer.java </p>
 * <p>Title: AdvertiControooer </p>
 * <p>Description: AdvertiControooer </p>
 * <p>Copyright: Copyright (c) 15/11/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/adverti")
public class AdvertiControooer
{
    @RequestMapping("/jiaxing")
    public String jiaxing() throws BusinessException
    {
        return "adverti/jiaxing/index";
    }
}
