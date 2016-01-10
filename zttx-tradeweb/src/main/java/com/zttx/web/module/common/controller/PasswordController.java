package com.zttx.web.module.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ForgetModel;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.JsonMessageUtil;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;

/**
 * 密码管理
 * Created by 李星 on 2015/8/14.
 * @version 2.0
 */
@Controller
@RequestMapping("/common")
public class PasswordController extends GenericController
{
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private BrandInfoService brandInfoService;
    
    @Autowired
    private UserCenterClient userCenterClient;
    /**
     * 品牌商后台我的账户密码管理
     *
     * @author 周光暖
     */
    @RequestMapping(value = "/brand/password")
    public String password(HttpServletRequest request, ModelMap map)
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        UserInfo userm = userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
        map.put("userm", userm);
        if (userm.getUserType().intValue() == 0)
        {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(userm.getRefrenceId());
            map.put("brandInfo", brandInfo);
        }
        return "brand/change_logword";
    }
    
    /**
     * 获取手机短信密码
     * (服务后台专用)
     *
     * @param request
     * @param userMobile
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/password/mobile/getCaptcha")
    public JsonMessage getMobielCaptcha(HttpServletRequest request, String userMobile) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        UserInfo user = userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
        if (user == null) return this.getJsonMessage(CommonConst.REG_USER_NOT_EXIST);
        String userId = user.getRefrenceId();
        String mobile = user.getUserMobile();
        try
        {
            userCenterClient.findpass(userId, mobile);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(CommonConst.FAIL, "用户中心访问异常:" + e.getLocalizedMessage());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 品牌商用户登录后修改密码
     * @param forgetModel
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     */
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
            try
            {
                userCenterClient.setpass(identifyingCode, userId, mobile, newPass, false);
            }
            catch (BusinessException e)
            {
                return this.getJsonMessage(CommonConst.FAIL, "用户中心访问异常:" + e.getLocalizedMessage());
            }
            return JsonMessageUtil.getJsonMessage(CommonConst.SUCCESS);
        }
        return this.getJsonMessage(CommonConst.FAIL);
    }

    /**
     * 获取用户登录信息(登录/未登录) 不可以通过手机号来获取用户信息,登录后的用户名不一定等于手机号,所以必须通过request来获取用户
     * @param userMobile
     * @param request
     * @return
     * @throws BusinessException
     */
    public UserInfo getUser(String userMobile, HttpServletRequest request) throws BusinessException {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        UserInfo brandUserm = null;
        String userId = userPrincipal.getRefrenceId();
        if (userId != null) {
            brandUserm = userInfoService.selectByPrimaryKey(userId);
        }
        if (userId == null && StringUtils.isNotBlank(userMobile)) {
            brandUserm = userInfoService.getByMobile(userMobile);
        }
        return brandUserm;
    }
    
    /**
     * 用户信息修改的基本参数格式验证
     * @param forgetModel
     * @return
     */
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
