package com.zttx.web.module.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ForgetModel;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.JsonMessageUtil;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;

@Controller
public class UpdateLoginPasswordController extends GenericController
{
    @Autowired
    private UserCenterClient userCenterClient;
    
    @Autowired
    private UserInfoService  userInfoService;
    
    // 用户登录后修改密码
    @RequestMapping("/password/center/updateHasLogin")
    @ResponseBody
    public JsonMessage updatePasswordHasLogin(ForgetModel forgetModel, HttpServletRequest request, Model model) throws BusinessException
    {
        if (StringUtils.isBlank(forgetModel.getTelCode()) || StringUtils.isBlank(forgetModel.getNewPwd())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        // 修改参数规则校验
        List<Map<String, String>> validatorList = usermValidator(forgetModel);
        if (null != validatorList && !validatorList.isEmpty()) { throw new BusinessException(CommonConst.PASS_ERR); }
        UserInfo user = this.getUser(forgetModel.getUserMobile(), request);
        if (user != null)
        {
            String userId = user.getRefrenceId();
            String mobile = user.getUserMobile();
            String identifyingCode = forgetModel.getTelCode();
            String newPass = forgetModel.getNewPwd();
            userCenterClient.setpass(identifyingCode, userId, mobile, newPass, false);
            return JsonMessageUtil.getJsonMessage(CommonConst.SUCCESS);
        }
        return this.getJsonMessage(CommonConst.FAIL);
    }
    
    /**
    * 进入密码重置成功页面
    * @return
    */
    @RequestMapping(value = "/password/center/resetPassOK")
    public String resetPassOK()
    {
        return "common/resetPassOK";
    }
    
    /**
     * 重置密码不需要验证码
     * @param request
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/password/center/reset")
    public JsonMessage resetPassword(HttpServletRequest request) throws BusinessException
    {
        return null;
    }
    
    // 获取用户登录信息(登录/未登录) 不可以通过手机号来获取用户信息,登录后的用户名不一定等于手机号,所以必须通过request来获取用户
    public UserInfo getUser(String userMobile, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
        return brandUserm;
    }
    
    // 用户信息修改的基本参数格式验证
    public List<Map<String, String>> usermValidator(ForgetModel forgetModel)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = null;
        // password 验证, 密码校验,6-16位的 ,true代表不能为空,返回false代表不合格
        if (!ValidateUtils.isRange(forgetModel.getNewPwd(), 6, 16, true))
        {
            name = "newUserPwd";
            validatorList.add(this.getErrMsgMap(name, CommonConst.PASS_ERR.getMessage()));
        }
        return validatorList;
    }
}
