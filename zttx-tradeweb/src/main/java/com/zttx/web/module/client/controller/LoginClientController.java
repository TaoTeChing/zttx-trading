package com.zttx.web.module.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zttx.web.consts.CookieConst;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.RedisSessionManager;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CookieUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CacheConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.UsernamePasswordToken;
import com.zttx.web.utils.AesUtil;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：LoginClientController.java </p>
 * <p>Title: 用户登陆RPC控制器 </p>
 * <p>Description: LoginClientController </p>
 * <p>Copyright: Copyright (c) 15/9/7</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
public class LoginClientController extends GenericController
{
    public static final Logger logger = LoggerFactory.getLogger(LoginClientController.class);
    
    @Autowired
    private UserInfoService    userInfoService;
    
    /**
     * 预登陆接口地址
     * <p>
     * 支撑系统登陆前先通过此接口生成对应的会话ID，然后在具体的登陆接口中取此会话编号
     * </p>
     *
     * @param param
     * @return {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/client/prelogin", method = RequestMethod.POST)
    public JsonMessage preLogin(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String id = MapUtils.getString(map, "id");
        if (StringUtils.isNotBlank(id))
        {
            String random = SerialnoUtils.buildPrimaryKey();
            JedisUtils.hset(CacheConst.crmSessionKeyPrefix, id, random);
            return this.getJsonMessage(CommonConst.SUCCESS, random);
        }
        return this.getJsonMessage(ClientConst.NULERROR);
    }
    
    /**
     * CRM免登陆
     *
     * @param request
     * @param response
     * @param code
     * @param userId
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/common/crmLogin", method = RequestMethod.POST)
    public String crmLogin(HttpServletRequest request, HttpServletResponse response, String code, String userId, String userName) throws Exception
    {
        code = AesUtil.aesDecrypt(code);
        String[] split = AesUtil.separateCode(code);
        String id = split[0];
        String random = split[1];
        String cachedRandom = JedisUtils.hget(CacheConst.crmSessionKeyPrefix, id, true);
        if (null != cachedRandom && null != random && random.equals(cachedRandom))
        {
            // 清理自定义会话
            RedisSessionManager.clear(request, response);
            // 清理结算平台 cookie;
            CookieUtils.remove(request, response, CookieConst.PAYMENT);
            Subject currentUser = SecurityUtils.getSubject();
            if (null != currentUser) currentUser.logout();
            UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
            if (null != userInfo)
            {
                AuthenticationToken token = new UsernamePasswordToken(userInfo.getUserMobile(), userInfo.getUserPwd().toCharArray(), IPUtil.getOriginalIpAddr(request),
                        true, userId, userName);
                currentUser = SecurityUtils.getSubject();
                try
                {
                    currentUser.login(token);
                    if (UserAccountConst.USERINFO_TYPE_BRAND.equals(userInfo.getUserType()))
                    {
                        return "redirect:/brand/center";
                    }
                    else
                    {
                        return "redirect:/dealer/center";
                    }
                }
                catch (Exception e)
                {
                    return "redirect:/common/login";
                }
            }
        }
        return "redirect:/common/login";
    }
}
