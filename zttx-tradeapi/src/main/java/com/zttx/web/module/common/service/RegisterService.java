/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.UserInfo;

/**
 * Created by txsb on 2015/8/15.
 */
public interface RegisterService
{
    /**
     * 获取区域可能用节点
     *
     * @param province 省
     * @param city     市
     * @param county   区
     * @return
     * @author 施建波
     */
    String getAreaNo(String province, String city, String county);
    
    /**
     * @param userm
     * @param ipAddr
     * @param ipAddInt
     * @param areaNo
     * @return
     */
    UserInfo buildUserInfo(UserInfo userm, String ipAddr, int ipAddInt, String areaNo);
    
    /**
     * 保存注册用户
     * @param userInfo
     * @throws BusinessException
     */
    void save(UserInfo userInfo) throws BusinessException;
    
    /**
     * 验证手机号
     *
     * @param user
     * @throws BusinessException
     */
    void phoneVerify(UserInfo user) throws BusinessException;
    
    /**
     * 生成验证码
     *
     * @param userMobile
     * @param ipAddr
     * @throws BusinessException
     */
    void createTelCode(String userMobile, int ipAddr) throws BusinessException;
    
    void modifyStateUsed(String userMobile, String dealPlatform);
    
    boolean isRegisted(String userMobile);
    
    Integer verifyAndCheck(String userMobile, String dealPlatform, String verifyCode);
    
    void tryLogin(UserInfo userInfo);
}
