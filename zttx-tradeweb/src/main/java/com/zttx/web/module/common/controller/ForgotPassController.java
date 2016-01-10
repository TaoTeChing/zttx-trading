package com.zttx.web.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.module.common.entity.FindAccount;
import com.zttx.web.module.common.entity.TelCode;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ForgetModel;
import com.zttx.web.module.common.service.FindAccountService;
import com.zttx.web.module.common.service.TelCodeService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.RedisSessionManager;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.*;

/**
 * <p>File：ForgotPassController.java </p>
 * <p>Title: ForgotPassController </p>
 * <p>Description: 忘记密码管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/20</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.COMMON + "/forgotpass")
public class ForgotPassController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(ForgotPassController.class);
    
    @Autowired
    private RedisSessionManager redisSessionManager;
    
    @Autowired
    private UserInfoService     userInfoService;
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    @Autowired
    private FindAccountService  findAccountService;
    
    @Autowired
    private TelCodeService      telCodeService;
    
    /**
     * 进入忘记密码页面
     * @return
     */
    @RequestMapping("/index")
    public String forgotPassJsp()
    {
        return "common/forgotPass";
    }
    
    /**
     * 找回用户名页面
     * @return
     */
    @RequestMapping("/findAccount")
    public String findAccount()
    {
        return "common/findAccount";
    }
    
    /**
     * 获取手机短信密码
     *
     * @param request
     * @param userMobile
     * @param captcha
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/getCaptcha")
    public JsonMessage getCaptcha(HttpServletRequest request, String userMobile, String captcha)
    {
        try
        {
            // 判断验证码
            String sessionCaptcha = redisSessionManager.getString(request, RedisSessionManager.SessionKey.CAPTCHA);
            boolean valid = StringUtils.equalsIgnoreCase(captcha, sessionCaptcha);
            if (!valid) { throw new BusinessException(CommonConst.VALID_ERR); }
            UserInfo user = this.getUser(userMobile);
            if (user == null) return this.getJsonMessage(CommonConst.REG_USER_NOT_EXIST);
            String userId = user.getRefrenceId();
            String mobile = user.getUserMobile();
            userCenterClient.findpass(userId, mobile);
        }
        catch (BusinessException e)
        {
            logger.error("获取手机短信验证码异常:" + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 修改密码
     * @param forgetModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public JsonMessage updatePassword(ForgetModel forgetModel, Model model)
    {
        try
        {
            if (StringUtils.isBlank(forgetModel.getTelCode()) || StringUtils.isBlank(forgetModel.getNewPwd())) { throw new BusinessException(CommonConst.PARAM_NULL); }
            UserInfo user = this.getUser(forgetModel.getUserMobile());
            if (user == null) { throw new BusinessException(CommonConst.REG_USER_NOT_EXIST); }
            if (beanValidator(model, forgetModel))
            {
                String userId = user.getRefrenceId();
                String mobile = user.getUserMobile();
                String identifyingCode = forgetModel.getTelCode();
                String newPass = forgetModel.getNewPwd();
                userCenterClient.setpass(identifyingCode, userId, mobile, newPass, false);
            }
        }
        catch (BusinessException e)
        {
            logger.error("修改密码异常:" + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 进入密码重置成功页面
     *
     * @return
     */
    @RequestMapping(value = "/resetPassOK")
    public String resetPassOK()
    {
        return "common/resetPassOK";
    }
    
    /**
     * 保存找回用户名
     * @param findAccount
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/findAccountSave", method = RequestMethod.POST)
    public String save(FindAccount findAccount, Model model) throws BusinessException
    {
        // 账户信息检验
        if (beanValidator(model, findAccount))
        {
            if (findAccount.getCertType() == CommonConstant.FindAccountConstant.CART_ID)
            {
                if (!ValidateUtils.matchRegexp(findAccount.getCertNo(), ValidateUtils.REGULAR_ID_CARD))
                {
                    model.addAttribute("message", "身份证格式不正确");
                    return findAccount();
                }
            }
        }
        else
        {
            return findAccount();
        }
        // 保存信息
        if (StringUtils.isNotBlank(findAccount.getCertPhoto())
                && (findAccount.getCertPhoto().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || findAccount.getCertPhoto().startsWith(ImageConst.TEMP)))
        {
            String path = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, findAccount.getCertPhoto(), null);
            findAccount.setCertPhoto(path);
        }
        findAccountService.save(findAccount);
        return "common/findAccount_success";
    }
    
    /**
     * 获取用户登录信息(登录/未登录)
     * @param userMobile
     * @return
     * @throws BusinessException
     */
    private UserInfo getUser(String userMobile) throws BusinessException
    {
        UserInfo brandUserm = null;
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        String userId = userPrincipal == null ? null : userPrincipal.getRefrenceId();
        if (userId != null)
        {
            brandUserm = userInfoService.selectByPrimaryKey(userId);
        }
        if (userId == null && StringUtils.isNotBlank(userMobile))
        {
            brandUserm = userInfoService.getByMobile(userMobile);
        }
        return brandUserm;
    }
    
    @ResponseBody
    @RequestMapping(value = "/sendvcode")
    public JsonMessage createMobileCode(String userMobile, Boolean isNew, HttpServletRequest request) throws BusinessException
    {
        int waitTime = 0;
        if (null != isNew && isNew)
        {
            Boolean isSameMobile = userCenterClient.checkUserName(userMobile);
            if (isSameMobile) { return getJsonMessage(CommonConst.MOBILE_EXIST_TOLOGIN); }
        }
        TelCode telCode = new TelCode();
        telCode.setUserMobile(userMobile);
        telCode.setVerifyType("001");
        telCode.setCreateIp(IPUtil.getIpAddr(request));
        telCode.setCusType(ClientConst.CUS_TYPE_TRADE);
        Integer resultState = telCodeService.create(telCode);
        TelCode _telCode = telCodeService.search(userMobile, "001", false);
        if (resultState.equals(ExceptionConst.EXISTES))
        {
            waitTime = (int) ((_telCode.getValidTime() - CalendarUtils.getCurrentLong() + _telCode.getCreateTime()) / 1000);
            return getJsonMessage(ExceptionConst.EXISTES, waitTime);
        }
        if (!resultState.equals(ExceptionConst.SUCCESS))
        {
            waitTime = (int) ((_telCode.getValidTime() - CalendarUtils.getCurrentLong() + _telCode.getCreateTime()) / 1000);
            return getJsonMessage(DealerConst.CAPTCHA_SEND_FAIL, waitTime);
        }
        waitTime = _telCode.getValidTime() / 1000;
        return getJsonMessage(CommonConst.SUCCESS, waitTime);
    }
    
    /**
     * 发送手机验证码
     *
     * @param
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendDealerCode")
    public JsonMessage sendDealerCode(HttpServletRequest request) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        // TODO N 模拟发送手机验证码
        String verifyType = "001";// TODO N 临时模拟 验证码类型
        TelCode telCode = new TelCode();
        telCode.setUserMobile(dealerUserm.getUserMobile());
        telCode.setVerifyType(verifyType);
        telCode.setCreateIp(IPUtil.getIpAddr(request));
        telCode.setCusType(ClientConst.CUS_TYPE_TRADE);
        Integer resultState = telCodeService.create(telCode);
        int waitTime;
        // 发送验证码成功
        if (resultState.equals(ExceptionConst.SUCCESS))
        {
            // waitTime = tc.getValidTime() / 1000;
            waitTime = 60;
            return getJsonMessage(DealerConst.CAPTCHA_SEND_SUCCESS, waitTime);
        }
        // 验证码已经发送
        else if (resultState.equals(ExceptionConst.EXISTES))
        {
            /*
             * waitTime = (int) ((tc.getValidTime()
             * - CalendarUtils.getCurrentLong() + tc.getCreateTime()) / 1000);
             */
            waitTime = 60; // 原是5分钟才发送一次,现在是60秒即可发送一次,不是验证码的有效时间
            return getJsonMessage(DealerConst.CAPTCHA_EXIST, waitTime);
        }
        // 验证码发送失败
        else
        {
            return getJsonMessage(DealerConst.CAPTCHA_SEND_FAIL);
        }
    }
}
