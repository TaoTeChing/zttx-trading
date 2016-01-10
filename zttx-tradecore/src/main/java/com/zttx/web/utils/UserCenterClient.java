/*
 * @(#)UserCenterClient.java 2014-12-9 上午10:33:01
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.bean.WeshopAPIConfig;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>File：UserCenterClient.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-9 上午10:33:01</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Component
public class UserCenterClient {
    // private static final Logger logger = LoggerFactory.getLogger(UserCenterClient.class);

    /**
     * 5.2用户信息注册  终端商
     *
     * @param user
     * @return
     * @throws BusinessException
     */
    public String register(UserInfo user) throws BusinessException {
        Map<String, Object> params = Maps.newHashMap();
        params.put("trueName", user.getUserName());
        params.put("mobile", user.getUserMobile());
        params.put("userPass", this.parsePassword(user.getUserPwd()));
        params.put("status", CommonConstant.UserCenterClient.STATUS_YES);
        params.put("parentId", CommonConstant.UserCenterClient.PARENTID);
        params.put("resourceType", CommonConstant.UserCenterClient.RESOURCE_TYPE_ZTTX);
        params.put("userAuthority", this.getUserAuthority());
        params.put("accountType", 0);
        return this.register(params);
    }


    public String registerOrUpdate(UserInfo user) throws BusinessException {
        String userId = null;
        try {
            return register(user);
        } catch (BusinessException e) {
            if (126030 != e.getCode().intValue()) throw e;
            userId = (String) e.getObject();
            if (StringUtils.isBlank(userId)) throw new BusinessException(CommonConst.PARAM_NULL);
        }
        // 修改用户中心的密码
        setpass(null, userId, user.getUserMobile(), user.getUserPwd(), true);
        // 修改用户中心的名称
        CenterUser centerUser = getUser(userId);
        centerUser.setTrueName(user.getUserName());
        centerUser.setEmail(user.getUserMail());
        update(centerUser);
        return userId;
    }


    public String getUserAuthority() {
        // 交易平台初始化
        Map<String, Object> mapZttx = Maps.newHashMap();
        mapZttx.put("PlatFormType", CommonConstant.UserCenterClient.PLATFORM_TYPE_ZTTX);
        mapZttx.put("ExpirationTime", CalendarUtils.getCurrentLong() + CommonConstant.UserCenterClient.EXPIRATION_TIME_ZTTX);
        mapZttx.put("Status", CommonConstant.UserCenterClient.STATUS_YES);
        // app初始化
        Map<String, Object> mapApp = Maps.newHashMap();
        mapApp.put("PlatFormType", CommonConstant.UserCenterClient.PLATFORM_TYPE_APP);
        mapApp.put("ExpirationTime", CalendarUtils.getCurrentLong() + CommonConstant.UserCenterClient.EXPIRATION_TIME_APP);
        mapApp.put("Status", CommonConstant.UserCenterClient.STATUS_YES);
        List<Map<String, Object>> list = Lists.newArrayList();
        list.add(mapZttx);
        list.add(mapApp);
        return JSON.toJSONString(list);
    }

    /**
     * 5.2用户信息注册
     *
     * @param params
     * @return
     * @throws BusinessException
     */
    public String register(Map<String, Object> params) throws BusinessException {
        JsonMessage jsonMessage = this.getJsonMessage(params, "/UserInfoController/register");
        String userCenterId = (String) jsonMessage.getObject();
        if (StringUtils.isBlank(userCenterId)) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        return userCenterId;
    }

    /**
     * 5.3回滚删除已注册用户接口
     *
     * @param userId
     */
    public void delete(String userId) throws BusinessException {
        if (StringUtils.isBlank(userId)) throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        this.getJsonMessage(params, "/UserInfoController/delete");
    }

    /**
     * 5.4用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    public CenterUser login(String userName, String password) throws BusinessException {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> user = Maps.newHashMap();
        user.put("userName", userName);
        user.put("userPass", this.parsePassword(password));
        user.put("platFormType", CommonConstant.UserCenterClient.PLATFORM_TYPE_ZTTX);
        JsonMessage jsonMessage = this.getJsonMessage(user, "/UserInfoController/login");
        Map<String, Object> map = (Map<String, Object>) jsonMessage.getObject();
        String userCenterId = (String) map.get("refrenceId");
        if (StringUtils.isBlank(userCenterId)) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        CenterUser centerUser = this.getUser(userCenterId);
        return centerUser;
    }

    /**
     * 5.5获取用户基本信息
     *
     * @param userId
     * @return
     */
    public CenterUser getUser(String userId) throws BusinessException {
        if (StringUtils.isBlank(userId)) throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userid", userId);
        params.put("issubaccount", CommonConstant.UserCenterClient.IS_SUB_ACCOUNT_NO);
        JsonMessage jsonMessage = this.getJsonMessage(params, "/UserInfoController/getUser");
        if (null == jsonMessage.getObject()) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        CenterUser centerUser = JSON.parseObject(JSON.toJSONString(jsonMessage.getObject()), CenterUser.class);
        return centerUser;
    }

    /**
     * 5.5.1.1 修改用户信息
     *
     * @param centerUser
     * @throws BusinessException
     */
    public void update(CenterUser centerUser) throws BusinessException {
        if (null == centerUser) throw new BusinessException(CommonConst.PARAM_NULL);
        // if(StringUtils.isBlank(centerUser.getRefrenceId()) || StringUtils.isBlank(centerUser.getStatus())
        // || StringUtils.isBlank(centerUser.getMobile()) || StringUtils.isBlank(centerUser.getUserName())
        // || StringUtils.isBlank(centerUser.getTrueName()) || StringUtils.isBlank(centerUser.getParentId()))
        // {
        // throw new BusinessException(CommonConst.PARAM_NULL);
        // }
        String userId = centerUser.getRefrenceId();
        String userName = centerUser.getUserName();
        String trueName = centerUser.getTrueName();
        String email = centerUser.getEmail();
        String parentId = centerUser.getParentId();
        String status = centerUser.getStatus();
        String mobile = centerUser.getMobile();
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("userName", userName);
        params.put("trueName", trueName);
        params.put("email", email);
        params.put("parentId", parentId);
        params.put("status", status);
        params.put("mobile", mobile);
        this.getJsonMessage(params, "/UserInfoController/update");
    }

    /**
     * 5.5.2修改手机号
     *
     * @param userId
     * @param mobile
     * @throws BusinessException
     */
    public void changeMobile(String userId, String mobile) throws BusinessException {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(mobile))
            throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("mobile", mobile);
        params.put("parentId", userId);
        params.put("isSyn", true);
        this.getJsonMessage(params, "/UserInfoController/changeMobile");
    }

    /**
     * 5.5.3.1找回密码信息   ,发送验证码
     *
     * @param userId
     * @param mobile
     * @throws BusinessException
     */
    public void findpass(String userId, String mobile) throws BusinessException {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(mobile))
            throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("mobile", mobile);
        this.getJsonMessage(params, "/UserInfoController/findpass");
    }

    /**
     * 5.5.3.2 设置密码信息
     *
     * @param identifyingCode
     * @param userId
     * @param mobile
     * @param newPass
     */
    public void setpass(String identifyingCode, String userId, String mobile, String newPass, boolean isDeal) throws BusinessException {
        if (StringUtils.isBlank(identifyingCode) && !isDeal || StringUtils.isBlank(userId) || StringUtils.isBlank(mobile) || StringUtils.isBlank(newPass))
            throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("identifyingCode", identifyingCode);
        params.put("userId", userId);
        params.put("mobile", mobile);
        params.put("newPass", this.parsePassword(newPass));
        params.put("isDeal", isDeal);
        this.getJsonMessage(params, "/UserInfoController/setpass");
    }

    /**
     * 5.5.4.1用户平台授权
     *
     * @param userId
     * @param expirationTime
     * @param status
     * @throws BusinessException
     */
    public void setPlatFormAuthority(String userId, String expirationTime, String status) throws BusinessException {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(expirationTime) || StringUtils.isBlank(status))
            throw new BusinessException(CommonConst.PARAM_NULL);
        if (status.equals(String.valueOf(CommonConstant.UserCenterClient.STATUS_YES)))
            throw new BusinessException(CommonConst.NOT_PLATFORM_ACCESS);
        Map<String, Object> params = Maps.newHashMap();
        params.put("platFormType", CommonConstant.UserCenterClient.PLATFORM_TYPE_ZTTX);
        params.put("userId", userId);
        params.put("expirationTime", expirationTime);
        params.put("status", status);
        this.getJsonMessage(params, "/UserInfoController/setPlatFormAuthority");
    }

    /**
     * 5.6.1.1获取用户信息
     *
     * @return
     */
    public PaginateResult<Map<String, Object>> userlist() {
        return null;
    }

    /**
     * 5.6.2.1启用或停用用户
     *
     * @param userId
     * @throws BusinessException
     */
    public void userstatus(String userId, String status) throws BusinessException {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(status))
            throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("platFormType", CommonConstant.UserCenterClient.PLATFORM_TYPE_ZTTX);
        params.put("userId", userId);
        params.put("status", status);
        this.getJsonMessage(params, "/UserInfoController/userstatus");
    }

    /**
     * 5.6.2.2更改用户到期时间
     *
     * @param userId
     * @param platFormType
     * @param expirationTime
     * @throws BusinessException
     */
    public void changeExpirationTime(String userId, String platFormType, String expirationTime) throws BusinessException {
        if (StringUtils.isBlank(userId)) throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("platFormType", platFormType); // CommonConstant.UserCenterClient.PLATFORM_TYPE_ZTTX
        params.put("userID", userId);
        params.put("expirationTime", expirationTime);
        this.getJsonMessage(params, "/UserInfoController/changeExpirationTime");
    }

    /**
     * 5.6.2.3 重置用户密码
     *
     * @param userId
     * @throws BusinessException
     */
    public CenterUser resetPass(String userId) throws BusinessException {
        if (StringUtils.isBlank(userId)) throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        JsonMessage jsonMessage = this.getJsonMessage(params, "/UserInfoController/resetPass");
        CenterUser centerUser = JSON.parseObject(JSON.toJSONString(jsonMessage.getObject()), CenterUser.class);
        return centerUser;
    }

    /**
     * 5.5 验证主账号信息是否存在
     *
     * @param userName 用户账号
     * @throws BusinessException
     */
    public Boolean checkUserName(String userName) throws BusinessException {
        if (StringUtils.isBlank(userName)) throw new BusinessException(CommonConst.PARAM_NULL);
        Map<String, Object> params = Maps.newHashMap();
        params.put("userName", userName);
        try {
            this.getJsonMessage(params, "/UserInfoController/checkUserName");
            return false;
        } catch (BusinessException e) {
            if (126014 == e.getCode()) return true;
            throw e;
        }
    }

    private JsonMessage getJsonMessage(Map<String, Object> params, String uri) throws BusinessException {
        Map<String, String> _params = WeshopAPIConfig.transMapToEncryMap(params, ZttxConst.USERAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.USERAPI_WEBURL + uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JsonMessage resJson;
        try {
            resJson = JSON.parseObject(result, JsonMessage.class);
        } catch (JSONException e) {
            resJson = null;
        }
        if (null == resJson) throw new BusinessException(CommonConst.USER_CENTER_INTERFACE_FAIL);
        if (resJson.getCode() == CommonConstant.UserCenterClient.SUCCESS || resJson.getCode() == CommonConstant.UserCenterClient.SUCCESS_2)
            return resJson;
        throw new BusinessException(resJson.getCode(), resJson.getMessage(), resJson.getObject());
    }

    private String parsePassword(String password) {
        return EncryptUtils.getEncryString(password);
    }
}
