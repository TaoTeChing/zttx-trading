/*
 * @(#)LoginController.java 2015-8-10 下午12:13:09
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.sdk.utils.JsonMessageUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CookieConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.LoginModel;
import com.zttx.web.security.RedisSessionManager;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.UsernamePasswordToken;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CookieUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.UserCenterClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <p>File：LoginController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-10 下午12:13:09</p>
 * <p>Company: 8637.com</p>
 *
 * @author 黄小平
 * @version 1.0
 */
@RequestMapping("/common")
@Controller
public class LoginController extends GenericController
{
    private static Logger       logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    @Autowired
    private RedisSessionManager redisSessionManager;
    
    /**
     * 登陆页面导航
     * @param redirect
     * @param service
     * @param model
     * @return {@link String}
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/login")
    public String loginForward(String redirect, String service, Model model) throws UnsupportedEncodingException
    {
        if (StringUtils.isNotBlank(redirect))
        {// 判断是否有跳转的地址
            if ("payment".equals(service))
            { // 是否是结算平台登录请求
                return processPaySystemLogin(redirect);
            }
            else
            {
                UserPrincipal onLine = OnLineUserUtils.getPrincipal();
                if (null != onLine)
                {// 如果当前是已登陆用户，就直接跳到用户后台
                    if (UserAccountConst.USERINFO_TYPE_BRAND.equals(onLine.getUserType()))
                    {
                        return "redirect:/brand/center";
                    }
                    else
                    {
                        return "redirect:/dealer/center";
                    }
                }
            }
            model.addAttribute("redirect", URLDecoder.decode(redirect, CharsetConst.CHARSET_UT));
        }
        return "common/login";
    }
    
    /**
     * 处理结算平台登录
     * @return
     * @author 李星
     */
    private String processPaySystemLogin(String redirect) throws UnsupportedEncodingException
    {
        String userId = null;
        if (null != OnLineUserUtils.getPrincipal())
        {
            userId = OnLineUserUtils.getPrincipal().getRefrenceId();
        }
        if (StringUtils.isNotBlank(userId))
        {
            // 在缓存中保存登录用户ID,key设置如下
            String token = com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey();
            String key = "cas_token_" + token;
            JedisUtils.set(key, userId, 5 * 60);
            redirect = URLDecoder.decode(redirect, CharsetConst.CHARSET_UT);
            int index = StringUtils.indexOf(redirect, "?");
            // 在重定向 URL 中追加 token
            if (index < 0)
            {
                redirect = redirect + "?token=" + token;
            }
            else
            {
                if (index == redirect.length() - 1)
                {
                    redirect = redirect + "token=" + token;
                }
                else
                {
                    redirect = redirect + "&token=" + token;
                }
            }
            return "redirect:" + redirect;
        }
        return "common/login";
    }
    
    /**
     * 补充用户信息
     * @param key 用户中心 用户id
     * @param value 用户中心 用户密码
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/login/completion")
    public String completion(String key, String value, Model model) throws BusinessException
    {
        CenterUser centerUser = userCenterClient.getUser(key);
        if (null == centerUser) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        model.addAttribute("key", key);
        centerUser.setPassword(StringUtils.isEmpty(value) ? "123456" : value);// 默认密码123456
        model.addAttribute("centerUser", centerUser);
        return "common/centerUser_completion";
    }
    
    /**
     * 登陆请求处理
     *
     * @param userInfo
     * @param request
     * @param captcha
     * @return
     */
    @RequestMapping("/login/submitlogin")
    @ResponseBody
    public JsonMessage login(UserInfo userInfo, HttpServletRequest request, String captcha)
    {
        JsonMessage msg;
        UserPrincipal onLine = OnLineUserUtils.getPrincipal();
        if (null != onLine)
        {// 如果当前是已登陆用户，就直接跳到用户后台
            msg = JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
            msg.setObject(loginProcess(onLine));
            return msg;
        }
        if (userInfo.getUserMobile() == null || userInfo.getUserMobile().trim().equals(""))
        {
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("用户名不能为空");
            return msg;
        }
        if (userInfo.getUserPwd() == null || userInfo.getUserPwd().trim().equals(""))
        {
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("密码不能为空");
            return msg;
        }
        if (null != captcha)
        {
            // 判断验证码
            String sessionCaptcha = redisSessionManager.getString(request, RedisSessionManager.SessionKey.CAPTCHA);
            boolean valid = StringUtils.equalsIgnoreCase(captcha, sessionCaptcha);
            if (!valid)
            {
                msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
                msg.setMessage("验证码错误");
                return msg;
            }
        }
        AuthenticationToken token = new UsernamePasswordToken(userInfo.getUserMobile(), userInfo.getUserPwd().toCharArray(), userInfo.getRememberMe(),
                IPUtil.getOriginalIpAddr(request), captcha);
        Subject currentUser = SecurityUtils.getSubject();
        try
        {
            currentUser.login(token);
        }
        catch (UnknownAccountException uae)
        {
            logger.error("UnknownAccountException", uae.getLocalizedMessage());
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("用户名不存在或密码错误");
            return msg;
        }
        catch (IncorrectCredentialsException ice)
        {
            logger.error("IncorrectCredentialsException", ice.getLocalizedMessage());
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("密码错误");
            return msg;
        }
        catch (LockedAccountException lae)
        {
            logger.error("LockedAccountException", lae.getLocalizedMessage());
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("账户被锁");
            return msg;
        }
        catch (AuthenticationException ae)
        {
            logger.error("AuthenticationException", ae.getLocalizedMessage());
            msg = JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
            msg.setMessage("未知错误");
            return msg;
        }
        msg = JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
        msg.setObject(loginProcess(OnLineUserUtils.getPrincipal()));
        return msg;
    }
    
    /**
     * 处理登陆成功好的转向
     * @param userInfo
     * @return
     */
    private LoginModel loginProcess(UserPrincipal userInfo)
    {
        LoginModel login = new LoginModel();
        if (UserAccountConst.USERINFO_TYPE_BRAND.equals(userInfo.getUserType()))
        {
            login.setRedirect("/brand/center");
        }
        else if (UserAccountConst.USERINFO_TYPE_DEALER.equals(userInfo.getUserType()))
        {
            login.setRedirect("/dealer/center");
        }
        return login;
    }
    
    /**
     * 用户退出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        // 清理自定义会话
        RedisSessionManager.clear(request, response);
        // 清理结算平台 cookie;
        CookieUtils.remove(request, response, CookieConst.PAYMENT);
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) currentUser.logout();
        return "redirect:/common/login";
    }
    
    /**
     * 判断用户是否登录，
     * 当 type == 0 时，只判断品牌商是否登录，如果 品牌商已经登录, object == 0
     * 当 type == 1 时，只判断经销商是否登录，如果 经销商已经登录， object == 1
     * 其他情况， 先判断 经销商是否登录， 如果经销商未登录， 再判断品牌商登录
     *
     * @return
     */
    @RequestMapping(value = "/islogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage isLogin()
    {
        JsonMessage jsonMessage = null;
        UserPrincipal userInfo = OnLineUserUtils.getPrincipal();
        if (userInfo != null)
        {
            jsonMessage = new JsonMessage(CommonConst.SUCCESS);
            jsonMessage.setObject(userInfo.getUserType());
        }
        return jsonMessage;
    }
}
