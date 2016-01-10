/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.common.service.EmailValidateService;
import com.zttx.web.module.common.service.TelCodeService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.service.DealerClassService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商基础信息 控制器
 * <p>File：DealerInfoController.java </p>
 * <p>Title: DealerInfoController </p>
 * <p>Description:DealerInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerInfo")
public class DealerInfoController extends GenericController
{
    // CODE 验证类型
    private static String  verifyType = "001";
    
    @Autowired
    DealerInfoService      dealerInfoService;
    
    @Autowired
    DealerClassService     dealerClassService;
    
    @Autowired
    TelCodeService         telCodeService;
    
    @Autowired
    EmailValidateService   emailValidateService;
    
    @Autowired
    UserInfoService        userInfoService;
    
    @Autowired
    private DealDicService dealDicService;
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/myAccount")
    public String myAccount()
    {
        return "dealer/account_information_normal";
    }
    
    /**
     * 经销商基本信息页 [注册成功页/正常信息页]
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/infor", method = RequestMethod.GET)
    public String infor(boolean reg, Model model) throws BusinessException
    {
        String parentRefrenceId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerInfo dealerInfo = dealerInfoService.findById(parentRefrenceId);
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(parentRefrenceId);
        model.addAttribute("dealerUserm", dealerUserm);
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("dealNos", JSONArray.toJSON(dealerClassService.findByDealerId(parentRefrenceId)));
        model.addAttribute("dealList", dealDicService.getDealDicJson());
        return reg ? "dealer/account_information_register" : "dealer/account_information_normal";
    }
    
    /**
     * 安全验证页面
     *
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     * @author 徐志勇
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/security")
    public String verifPage(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        model.addAttribute("dealerUserm", dealerUserm);
        return "dealer/account_security";
    }
    
    /**
     * 手机认证
     *
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/info/mobile")
    @ResponseBody
    public JsonMessage verifyMobile(String oldUserMobile, String oldVerifyCode, String newUserMobile, String newVerifyCode)
    {
        Integer state = telCodeService.verifyAndUpdate(oldUserMobile, verifyType, oldVerifyCode);
        if (StringUtils.isNotBlank(newUserMobile) && StringUtils.isNotBlank(newVerifyCode) && state == 0)
        {
            // 检查手机是否有相同的存在，包括品牌商和经销商的
            UserInfo userInfo = userInfoService.getByMobile(newUserMobile);
            if (null != userInfo) return super.getJsonMessage(CommonConst.MOBILE_EXIST);
            state = telCodeService.verifyAndUpdate(newUserMobile, verifyType, newVerifyCode);
        }
        if (state == 0) // 成功
        {
            try
            {
                String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
                userInfoService.updateVerifyMobile(dealerId, newUserMobile, true);
                return super.getJsonMessage(CommonConst.SUCCESS);
            }
            catch (BusinessException e)
            {
                return super.getJsonMessage(e.getErrorCode());
            }
        }
        else if (state == 9)
        { // 超时
            return super.getJsonMessage(CommonConst.VERIFY_CODE_OUT);
        }
        else if (state == 8)
        {
            return super.getJsonMessage(CommonConst.DATA_NOT_EXISTS);
        }
        else
        {
            return super.getJsonMessage(CommonConst.FAIL);
        }
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/info/check", method = RequestMethod.POST)
    public JsonMessage checkDealerInfo(DealerInfoModel dealerInfo, HttpServletRequest request) throws BusinessException
    {
        if (dealerInfo.getEmpNum() == null)
        {
            dealerInfo.setEmpNum(0);
        }
        dealerInfoService.modDealerInfoMore(dealerInfo, request);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 配置 是否需要 短信收货页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/config")
    public String configDealerInfo(HttpServletRequest request, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        DealerInfo dealerInfo = dealerInfoService.findById(dealerId);
        model.addAttribute("dealerUserm", dealerUserm);
        model.addAttribute("dealerInfo", dealerInfo);
        return "dealer/account_config";
    }
    
    /**
     * 配置 是否需要 短信收货验证
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/rcvsms")
    public JsonMessage modifyDealerInfoRcvSms(HttpServletRequest request, boolean rcvSmsVerify, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerInfoService.modifyDealerInfoRcvSmsVerify(dealerId, rcvSmsVerify);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
