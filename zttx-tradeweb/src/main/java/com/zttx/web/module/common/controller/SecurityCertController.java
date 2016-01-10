/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.module.common.entity.SecurityCert;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.SecurityCertService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;

/**
 * 经销商/品牌商申请更改手机认证 控制器
 * <p>File：SecurityCertController.java </p>
 * <p>Title: SecurityCertController </p>
 * <p>Description:SecurityCertController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/securityCert")
public class SecurityCertController extends GenericController
{
    @Autowired(required = true)
    private SecurityCertService securityCertService;
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    @Autowired
    private UserInfoService     userInfoService;
    
    /**
     * 显示申诉页面  品牌商
     *
     * @return
     */
    @RequestMapping(value = ApplicationConst.BRAND + "/securityCert")
    public String brandIndex(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        SecurityCert securityCert = this.securityCertService.findSecurityCert(userPrincipal.getRefrenceId(), CommonConstant.SecurityCert.NONE_AUDIT);
        model.addAttribute("securityCert", securityCert);
        return "brand/account_complaint";
    }
    
    /**
     *  保存手机验证信息
     * @param request
     * @param securityCert
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = ApplicationConst.BRAND + "/securityCert/save", method = RequestMethod.POST)
    public JsonMessage brandSave(HttpServletRequest request, SecurityCert securityCert) throws BusinessException
    {
        if (!ValidateUtils.isMobileFormat(securityCert.getUserMobile(), true, 11)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        securityCert.setCreateIp(IPUtil.getIpAddr(request));
        securityCertService.save(securityCert, userPrincipal);
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 显示申诉页面  经销商
     * @return
     */
    @RequestMapping(value = ApplicationConst.DEALER + "/securityCert")
    public String dealerIndex(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        SecurityCert securityCert = this.securityCertService.findSecurityCert(userPrincipal.getRefrenceId(), CommonConstant.SecurityCert.NONE_AUDIT);
        model.addAttribute("securityCert", securityCert);
        return "dealer/account_complaint";
    }
    
    @ResponseBody
    @RequestMapping(value = ApplicationConst.DEALER + "/securityCert/save", method = RequestMethod.POST)
    public JsonMessage dealerSave(HttpServletRequest request, SecurityCert securityCert) throws BusinessException
    {
        if (!ValidateUtils.isMobileFormat(securityCert.getUserMobile(), true, 11)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        /**用户中心 用户是否存在*/
        Boolean isSameMobile = userCenterClient.checkUserName(securityCert.getUserMobile());
        if (isSameMobile) { return getJsonMessage(CommonConst.MOBILE_EXIST_TOLOGIN); }
        UserInfo _dealerUserm = userInfoService.getByMobile(securityCert.getUserMobile());
        if (_dealerUserm != null) { throw new BusinessException(DealerConst.USERM_HAS_EXIST); }
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        securityCert.setCreateIp(IPUtil.getIpAddr(request));
        securityCertService.save(securityCert, userPrincipal);
        return getJsonMessage(CommonConst.SUCCESS);
    }
}
