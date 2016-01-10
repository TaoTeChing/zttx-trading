/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;
import com.zttx.web.module.common.mapper.UserOnlineServiceDetailMapper;

/**
 * 客服在线信息详情表 服务实现类
 * <p>File：UserOnlineServiceDetail.java </p>
 * <p>Title: UserOnlineServiceDetail </p>
 * <p>Description:UserOnlineServiceDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserOnlineServiceDetailServiceImpl extends GenericServiceApiImpl<UserOnlineServiceDetail> implements UserOnlineServiceDetailService
{

    private UserOnlineServiceDetailMapper userOnlineServiceDetailMapper;

    @Autowired(required = true)
    public UserOnlineServiceDetailServiceImpl(UserOnlineServiceDetailMapper userOnlineServiceDetailMapper)
    {
        super(userOnlineServiceDetailMapper);
        this.userOnlineServiceDetailMapper = userOnlineServiceDetailMapper;
    }

    @Override
    public List<UserOnlineServiceDetail> getByOnlineService(String onlineRefrenceId)
    {
        return userOnlineServiceDetailMapper.getByOnlineService(onlineRefrenceId);
    }
}
