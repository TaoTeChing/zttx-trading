/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.UserInfo;

/**
 * 用户信息 服务接口
 * <p>File：UserInfoService.java </p>
 * <p>Title: UserInfoService </p>
 * <p>Description:UserInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserInfoService extends GenericServiceApi<UserInfo>
{
	/**
	 * 根据手机号注销用户
	 * @author 陈建平
	 * @param request
	 * @param userMobile
	 */
	void logoutByUserMobile(HttpServletRequest request,String userMobile);
	
    /**
     * 用户登陆验证
     * @param userMobile
     * @return
     */
    UserInfo loginVerifi(String userMobile);

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    UserInfo getByMobile(String mobile);
    
    /**
     * 更新手机验证过的状态
     * @param uuid
     * @param userMobile
     * @param state
     * @throws BusinessException
     */
    void updateVerifyMobile(String uuid, String userMobile, Boolean state) throws BusinessException;
    
    /**
     * 更新用户的邮箱认证状态
     * @param uuid
     * @param userMail
     * @param state
     * @throws BusinessException
     * @author 李星
     */
    void updateVerifyMail(String uuid, String userMail, Boolean state) throws BusinessException;
    
    /**
     * 根据用户ID获取支付用户ID
     * @param userId
     * @return
     */
    Long findPayUserId(String userId);
    
    /**
     * 根据交易平台用户ID查询对应支付平台用户ID（本地+远程），先找本地对应ID，找不到去支付系统查询并保存到本地
     * @param userId
     * @return
     * @throws BusinessException
     */
    Long executeFindPayUserId(String userId) throws BusinessException;
    /**
     * 根据子账号获取父账号
     * @param childId
     * @return
     */
    UserInfo getParentUserByChildId(String childId);

    /**
     * 根据用户id 查询用户的具体信息
     * @param refrenceId
     * @return
     * @author 易永耀
     */
    UserInfo selectUserInfoMore(String refrenceId,short userType);
    
    /**
     * 注册品牌商账户
     * @author 陈建平
     * @param userInfo
     * @param brandInfo
     * @param brandContact
     * @param dealNoList
     * @return
     * @throws BusinessException
     */
    String addBrandAccount(UserInfo userInfo, BrandInfo brandInfo, BrandContact brandContact, String[] dealNoList) throws BusinessException;
    
    /**
     * 过去注册用户信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<UserInfo> findUserInfo(UserInfo filter);

    /**
     * 发送短信通知
     *
     * @param uuid
     * @param content
     */
    public void sendSmsToDealerUser(String uuid, String content);
    
    /**
     * 依据用户中心用户信息，修改本地用户信息
     * @param centerUser 用户中心用户信息
     * @param localUserInfo 本地用户信息
     * @throws BusinessException
     */
    void updateLocalInfo(CenterUser centerUser, UserInfo localUserInfo) throws BusinessException;
    
    /**
     * 修改帐户注册信息
     * @author 陈建平
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    void updateUserInfo(UserInfo userInfo) throws BusinessException;

    /**
     * 验证登录密码是否匹配
     * @param dealerId 经销商ID
     * @param pwd      输入的登录密码
     * @return 如果匹配返回true
     * @author 徐志勇
     */
    boolean isUserpasswordMatch(String dealerId, String pwd);
}
