/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.UserInfo;

/**
 * 用户信息 持久层接口
 * <p>File：UserInfoDao.java </p>
 * <p>Title: UserInfoDao </p>
 * <p>Description:UserInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface UserInfoMapper extends GenericMapper<UserInfo>
{
    /**
     * 用户登陆验证
     * @param userMobile
     * @return
     */
    UserInfo loginVerifi(String userMobile);
    
    /**
     * 根据手机查找用户
     * @param userMobile
     * @return
     */
    UserInfo getByMobile(String userMobile);
    
    /**
     *更新手机验证过的状态
     * @param uuid
     * @param userMobile
     * @param state
     * @return
     */
    int updateVerifyMobile(@Param("refrenceId") String uuid, @Param("userMobile") String userMobile, @Param("state") Boolean state);
    
    /**
     * 更新用户的邮箱认证状态
     * @param uuid
     * @param userMail
     * @param state
     * @return
     * @throws BusinessException
     * @author 李星
     */
    Integer updateVerifyMail(@Param("refrenceId") String uuid, @Param("userMail") String userMail, @Param("state") Boolean state);
    /**
     * 根据子账号id获取父账号
     * @param childId
     * @return
     */
    UserInfo getParentUserByChildId(String childId);
    
    /**
     * 根据用户id 查询用户的具体信息
     * @return
     * @author 易永耀
     */
    UserInfo selectUserInfoMore(@Param("refrenceId") String refrenceId, @Param("userType") Short userType);
    
    /**
     * 搜索注册用户信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<UserInfo> findUserInfo(UserInfo filter);
}
