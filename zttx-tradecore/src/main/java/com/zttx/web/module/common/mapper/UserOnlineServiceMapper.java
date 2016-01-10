/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.UserOnlineService;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;

import java.util.List;

/**
 * 客服在线信息表 持久层接口
 * <p>File：UserOnlineServiceDao.java </p>
 * <p>Title: UserOnlineServiceDao </p>
 * <p>Description:UserOnlineServiceDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface UserOnlineServiceMapper extends GenericMapper<UserOnlineService>
{
    /**
     * 根据用户编号获取客服在线信息
     * @param userId
     * @return
     */
    UserOnlineService findUserOnlineService(String userId);


    /**
     * 根据在线客服编号获取客服在线详情
     * @param userOnlineId
     * @return
     */
    List<UserOnlineServiceDetail> listUserOnlineServiceDetail(String userOnlineId);
}
