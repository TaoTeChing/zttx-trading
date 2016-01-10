/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.common.entity.EmailValidate;
import com.zttx.web.module.common.service.EmailValidateService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.IPUtil;

/**
 * 邮件验证 控制器
 * <p>File：EmailValidateController.java </p>
 * <p>Title: EmailValidateController </p>
 * <p>Description:EmailValidateController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/emailValidate")
public class EmailValidateController extends GenericController
{
    @Autowired(required = true)
    private EmailValidateService emailValidateService;

    @Autowired
    private UserInfoService      userInfoService;
    
    /**
     * 创建邮箱认证记录并发送邮件
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonMessage create(HttpServletRequest request, Short userCate, String emailAddr) throws BusinessException
    {
        EmailValidate emailValidate = new EmailValidate();
        emailValidate.setUserCate(userCate);
        if (UserAccountConst.BRAND == userCate)
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            emailValidate.setUserId(brandId);
        }
        else if (UserAccountConst.DEALER == userCate)
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            emailValidate.setUserId(dealerId);
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        emailValidate.setEmailAddr(emailAddr);
        emailValidate.setCreateIp(IPUtil.getIpAddr(request));
        emailValidateService.create(emailValidate);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 邮箱认证
     */
    @RequestMapping(value = "emailValidate/verify")
    public String verify(HttpServletRequest request, String refrenceId, String emailAddr) throws BusinessException
    {
        EmailValidate emailValidate = this.emailValidateService.verifyAndUpdate(refrenceId, emailAddr);
        /*
         * if (UserAccountConst.BRAND == emailValidate.getUserCate())
         * {
         */
        this.userInfoService.updateVerifyMail(emailValidate.getUserId(), emailValidate.getEmailAddr(), true);
        /*
         * }
         * else if (UserAccountConst.DEALER == emailValidate.getUserCate())
         * {
         * this.dealerUsermService.updateVerifyMail(emailValidate.getUserId(), emailValidate.getEmailAddr(), true);
         * }
         * else
         * {
         * throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
         * }
         */
        return "/common/verify_mailSuccess";
    }
}
