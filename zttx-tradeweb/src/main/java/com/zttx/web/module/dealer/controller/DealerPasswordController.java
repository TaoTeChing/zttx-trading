/*
 * @(#)DealerPaywordController.java 2014年3月6日 下午1:20:45
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ChangePayword;
import com.zttx.web.module.common.model.ForgetModel;
import com.zttx.web.module.common.service.TelCodeService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerPayword;
import com.zttx.web.module.dealer.service.DealerPaywordService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncryptUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 经销商支付密码控制器
 * @author 李星
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.DEALER)
public class DealerPasswordController extends GenericController
{
    @Autowired
    UserInfoService              userInfoService;
    
    @Autowired
    private DealerPaywordService dealerPaywordService;
    
    @Autowired
    private TelCodeService       telCodeService;
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/payword", method = RequestMethod.GET)
    public String execute(Model model)
    {
        UserInfo userm = getLoginUser();
        DealerPayword payword = dealerPaywordService.selectByPrimaryKey(userm.getRefrenceId());
        model.addAttribute("userm", userm);
        model.addAttribute("payword", payword);
        return "dealer/account_password_payword";
    }
    
    private UserInfo getLoginUser()
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        return userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
    }
    
    /**
     * 验证登录密码是否匹配
     * @param dealerId 经销商ID
     * @param pwd      输入的登录密码
     * @return 如果匹配返回true
     * @author 徐志勇
     */
    private boolean isUserpasswordMatch(String dealerId, String pwd)
    {
        UserInfo userPassword = userInfoService.selectByPrimaryKey(dealerId);
        return EncryptUtils.encrypt(pwd + userPassword.getUserSalt(), ApplicationConst.ENCRYPT).equals(userPassword.getUserPwd());
    }
    
    /**
     * 登录密码
     * 
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/password")
    public String password(ModelMap map)
    {
        // 准备回显数据
        UserInfo duserm = getLoginUser();
        map.put("userMobile", duserm.getUserMobile());
        map.put("userType", UserAccountConst.DEALER);
        return "dealer/account_password_logword";
    }
    
    /**
     * 密码修改成功页面
     * 
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/password/ok")
    public String passwordOk()
    {
        return "dealer/account_password_OK"; // TODO N 页面暂时模拟
    }
    
    // 内部方法
    // ==========================================================================================
    private List<String> verifyAccountPassword(ForgetModel forgetModel)
    {
        List<String> list = Lists.newArrayList();
        if (ValidateUtils.isNull(forgetModel.getTelCode()))
        {
            list.add("验证码不能为空");
        }
        else if (!ValidateUtils.isInt(forgetModel.getTelCode()))
        {
            list.add("验证码格式不正确");
        }
        if (ValidateUtils.isNull(forgetModel.getNewPwd()))
        {
            list.add("密码不能为空");
        }
        int iLen = ValidateUtils.length(forgetModel.getNewPwd());
        if (iLen < 6 || iLen > 20)
        {
            list.add("密码的长度必须为6-20之间");
        }
        return list;
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payword/update", method = RequestMethod.POST)
    public JsonMessage changeDealerPayword(ChangePayword payword, HttpServletRequest request) throws BusinessException
    {
        if (!payword.getCfmPassword().equals(payword.getNewPassowrd())) { throw new BusinessException(DealerConst.PWD_NOT_MATCH); }
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo userm = userInfoService.selectByPrimaryKey(dealerId);
        // 增加登录密码和支付密码一致时，提示错误
        if (null != userm && userInfoService.isUserpasswordMatch(userm.getRefrenceId(), payword.getNewPassowrd())) { throw new BusinessException(
                DealerConst.NOT_USERPASSWORD_PAYWORD); }
        DealerPayword newPwd = new DealerPayword();
        newPwd.setRefrenceId(userm.getRefrenceId());
        newPwd.setCreateTime(CalendarUtils.getCurrentLong());
        newPwd.setDealerTel(userm.getUserMobile());
        newPwd.setCreateIp(IPUtil.getIpAddr(request));
        newPwd.setPayWord(payword.getNewPassowrd());
        if (ValidateUtils.isNumber(newPwd.getPayWord())) { return getJsonMessage(DealerConst.FAILURE.code, "密码不能全数字"); }
        int iLen = ValidateUtils.length(newPwd.getPayWord());
        if (iLen < 8 || iLen > 20) { return getJsonMessage(DealerConst.FAILURE.code, "密码的长度必须为8-20之间"); }
        // 增加首次设置支付密码时，不用短信校验 徐志勇
        DealerPayword dealerPayword = dealerPaywordService.selectByPrimaryKey(userm.getRefrenceId());
        if (null == dealerPayword)
        {
            dealerPaywordService.updatePayPassword(newPwd);
        }
        else
        {
            Integer verifyResult = telCodeService.verifyAndCheck(userm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM, payword.getTelCode());
            // 验证码校验
            if (ExceptionConst.SUCCESS.intValue() != verifyResult.intValue()) { throw new BusinessException(DealerConst.VERIFY_FAIL); }
            dealerPaywordService.updatePayPassword(newPwd);
            telCodeService.modifyStateUsed(userm.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM);
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
