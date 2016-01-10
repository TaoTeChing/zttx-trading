/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.consts.TelVerifyTypeConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.RegisterService;
import com.zttx.web.security.RedisSessionManager;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.IpUtilsHelper;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;

/**
 * <p>File：RegisterController.java</p>
 * <p>Title: 平台用户注册</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.COMMON)
public class RegisterController extends GenericController
{
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    @Autowired
    private RegisterService     registerService;
    
    @Autowired
    private RedisSessionManager redisSessionManager;
    
    /**
     * 跳转到注册页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/register")
    public String registerForward(Model model, HttpServletRequest request)
    {
        model.addAttribute("province", 0 == IpUtilsHelper.getAreaNo(request, 0) ? null : IpUtilsHelper.getAreaNo(request, 0));
        model.addAttribute("city", 0 == IpUtilsHelper.getAreaNo(request, 1) ? null : IpUtilsHelper.getAreaNo(request, 0));
        return brandGoToUrl(model, 1);
    }
    
    /**
     * 品牌商注册信息提交
     * @param brandUserm
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/register/brandRegister")
    @ResponseBody
    public JsonMessage ajaxBrandRegister(UserInfo brandUserm, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonConst.SUCCESS);
        if (beanValidator(jsonMessage, brandUserm))
        {
            try
            {
                Integer iRetCode = registerService.verifyAndCheck(brandUserm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM, brandUserm.getVerifyCode());
                if (ExceptionConst.SUCCESS.intValue() != iRetCode.intValue()) { throw new BusinessException(CommonConst.VALID_ERR); }
                String uuid = userCenterClient.register(brandUserm);
                brandUserm.setRefrenceId(uuid);
                String ipAddr = IPUtil.getOriginalIpAddr(request);
                int ipAddInt = IPUtil.getIpAddr(request);
                brandUserm.setUserName(brandUserm.getComName());
                // brandUserm.setUserState(UserAccountConst.USER_STATE_NEW);
                UserInfo userInfo = registerService.buildUserInfo(brandUserm, ipAddr, ipAddInt, "");
                registerService.save(userInfo);
                // 修改验证码状态
                registerService.modifyStateUsed(userInfo.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM);
                jsonMessage.setObject("common/login");
            }
            catch (BusinessException e)
            {
                logger.error(e.getMessage());
                jsonMessage.setCode(e.getErrorCode().getCode());
                jsonMessage.setMessage(e.getErrorCode().getMessage());
                jsonMessage.setObject("common/register");
            }
        }
        else
        {
            jsonMessage.setObject("common/register");
        }
        return jsonMessage;
    }
    
    /**
     * 经销商注册信息提交
     * @param dealerUserm
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/register/dealerRegister")
    @ResponseBody
    public JsonMessage ajaxDealerRegister(UserInfo dealerUserm, HttpServletRequest request)
    {
        JsonMessage jsonMessage = getJsonMessage(CommonConst.SUCCESS);
        if (beanValidator(jsonMessage, dealerUserm))
        {
            try
            {
                Integer iRetCode = registerService.verifyAndCheck(dealerUserm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM, dealerUserm.getVerifyCode());
                if (ExceptionConst.SUCCESS.intValue() != iRetCode.intValue()) { throw new BusinessException(CommonConst.VALID_ERR); }
                String uuid = userCenterClient.register(dealerUserm);
                dealerUserm.setRefrenceId(uuid);
                String areaNo = registerService.getAreaNo(dealerUserm.getProvince(), dealerUserm.getCity(), dealerUserm.getCounty());
                dealerUserm.setUserType((short) 1);
                // dealerUserm.setUserState(UserAccountConst.USER_STAT_OPEN);
                String ipAddr = IPUtil.getOriginalIpAddr(request);
                int ipAddInt = IPUtil.getIpAddr(request);
                UserInfo userInfo = registerService.buildUserInfo(dealerUserm, ipAddr, ipAddInt, areaNo);
                registerService.save(userInfo);
                // 修改验证码状态
                registerService.modifyStateUsed(userInfo.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM);
            }
            catch (BusinessException e)
            {
                logger.error(e.getMessage());
                jsonMessage.setCode(e.getErrorCode().getCode());
                jsonMessage.setMessage(e.getErrorCode().getMessage());
                jsonMessage.setObject("common/register");
            }
        }
        else
        {
            jsonMessage.setObject("common/register");
        }
        return jsonMessage;
    }
    
    // 品牌商用户注册参数检验
    private List<Map<String, String>> brandValidator(UserInfo brandUserm, String areaNo)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "userMobile";
        if (StringUtils.isBlank(brandUserm.getUserMobile()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("手机号码")));
        }
        else
        {
            if (!ValidateUtils.isMobileFormat(brandUserm.getUserMobile(), true, ValidateUtils.MOBILE_LENGTH))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.MOBILE_FORMAT_ERR.message));
            }
            if (this.registerService.isRegisted(brandUserm.getUserMobile()))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.MOBILE_EXIST_TOLOGIN.getMessage()));
            }
        }
        name = "verifyCode";
        if (StringUtils.isBlank(brandUserm.getVerifyCode()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("验证码")));
        }
        else
        {
            Integer iRetCode = registerService.verifyAndCheck(brandUserm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM, brandUserm.getVerifyCode());
            if (ExceptionConst.SUCCESS.intValue() != iRetCode.intValue())
            {
                validatorList.add(getErrMsgMap(name, CommonConst.VALID_ERR.getMessage()));
            }
        }
        name = "userPwd";
        if (StringUtils.isBlank(brandUserm.getUserPwd()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("密码")));
        }
        else
        {
            if (!ValidateUtils.isRange(brandUserm.getUserPwd(), 6, 16, true))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.PASS_ERR.getMessage()));
            }
        }
        name = "comName";
        if (StringUtils.isBlank(brandUserm.getComName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("公司名称")));
        }
        name = "dealBrands";
        if (StringUtils.isBlank(brandUserm.getDealBrands()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("持有品牌")));
        }
        return validatorList;
    }
    
    // 经绡商用户注册参数检验
    private List<Map<String, String>> dealerValidator(UserInfo dealerUserm, String areaNo, Boolean needTelCode)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "userMobile";
        if (StringUtils.isBlank(dealerUserm.getUserMobile()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("手机号码")));
        }
        else
        {
            if (!ValidateUtils.isMobileFormat(dealerUserm.getUserMobile(), true, ValidateUtils.MOBILE_LENGTH))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.MOBILE_FORMAT_ERR.message));
            }
            if (this.registerService.isRegisted(dealerUserm.getUserMobile()))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.MOBILE_EXIST_TOLOGIN.getMessage()));
            }
        }
        if (needTelCode)
        {
            name = "verifyCode";
            if (StringUtils.isBlank(dealerUserm.getVerifyCode()))
            {
                validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("验证码")));
            }
            else
            {
                Integer iRetCode = registerService.verifyAndCheck(dealerUserm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM, dealerUserm.getVerifyCode());
                if (ExceptionConst.SUCCESS.intValue() != iRetCode.intValue())
                {
                    validatorList.add(getErrMsgMap(name, CommonConst.VALID_ERR.getMessage()));
                }
            }
        }
        name = "userPwd";
        if (StringUtils.isBlank(dealerUserm.getUserPwd()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("密码")));
        }
        else
        {
            if (!ValidateUtils.isRange(dealerUserm.getUserPwd(), 6, 16, true))
            {
                validatorList.add(this.getErrMsgMap(name, CommonConst.PASS_ERR.getMessage()));
            }
        }
        name = "userName";
        if (StringUtils.isBlank(dealerUserm.getUserName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("姓名")));
        }
        name = "province";
        if (StringUtils.isBlank(areaNo))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("地区")));
        }
        return validatorList;
    }
    
    /**
     * 手机验证
     *
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/phoneVerify")
    public JsonMessage phoneVerify(UserInfo user, HttpServletRequest request)
    {
        JsonMessage jsonMsg = getJsonMessage(ExceptionConst.SUCCESS);
        try
        {
            // 判断验证码
            String sessionCaptcha = redisSessionManager.getString(request, RedisSessionManager.SessionKey.CAPTCHA);
            boolean valid = StringUtils.equalsIgnoreCase(user.getCaptcha(), sessionCaptcha);
            if (!valid) throw new BusinessException(CommonConst.VALID_ERR);
            registerService.phoneVerify(user);
            // 生成手机验证码
            registerService.createTelCode(user.getUserMobile(), IPUtil.getIpAddr(request));
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            jsonMsg.setCode(e.getErrorCode().getCode());
            jsonMsg.setMessage(e.getErrorCode().getMessage());
        }
        return jsonMsg;
    }
    
    private String brandGoToUrl(Model model, Integer type)
    {
        if (1 == type)
        {
            model.addAttribute("brandHide", "hide");
            model.addAttribute("formName", "brandForm");
            return "common/register";
        }
        else
        {
            // return "redirect:/brand/info";
            return "common/login";
        }
    }
    
    private String dealerGoToUrl(Model model, Integer type)
    {
        if (1 == type)
        {
            model.addAttribute("brandHide", "hide");
            model.addAttribute("formName", "dealerForm");
            return "common/register";
        }
        else
        {
            // return "redirect:/dealer/account/infor?reg=true";
            return "common/login";
        }
    }
}
