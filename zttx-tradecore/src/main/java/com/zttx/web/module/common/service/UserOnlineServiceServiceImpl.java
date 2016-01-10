/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.common.model.UserOnlineServiceModel;
import com.zttx.web.module.common.entity.UserOnlineService;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;
import com.zttx.web.module.common.mapper.UserOnlineServiceDetailMapper;
import com.zttx.web.module.common.mapper.UserOnlineServiceMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 客服在线信息表 服务实现类
 * <p>File：UserOnlineService.java </p>
 * <p>Title: UserOnlineService </p>
 * <p>Description:UserOnlineService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserOnlineServiceServiceImpl extends GenericServiceApiImpl<UserOnlineService> implements UserOnlineServiceService
{
    @Autowired
    private UserOnlineServiceDetailMapper userOnlineServiceDetailMapper;

    private UserOnlineServiceMapper userOnlineServiceMapper;

    @Autowired(required = true)
    public UserOnlineServiceServiceImpl(UserOnlineServiceMapper userOnlineServiceMapper)
    {
        super(userOnlineServiceMapper);
        this.userOnlineServiceMapper = userOnlineServiceMapper;
    }
    
    @Override
    public UserOnlineService findUserOnlineService(String userId)
    {
        return userOnlineServiceMapper.findUserOnlineService(userId);
    }
    
    @Override
    public UserOnlineServiceModel fillUserOnlineServiceDetail(UserOnlineServiceModel userOnlineService)
    {
        if (null != userOnlineService)
        {
            List<UserOnlineServiceDetail> onlineDetailList = this.userOnlineServiceMapper.listUserOnlineServiceDetail(userOnlineService.getRefrenceId());
            userOnlineService.setOnlineDetailList(onlineDetailList);
        }
        return userOnlineService;
    }
    
    @Override
    public void save(UserOnlineServiceModel userOnlineService)
    {
        UserOnlineService old = userOnlineServiceMapper.findUserOnlineService(userOnlineService.getUserId());
        if (null != old)
        {
            List<UserOnlineServiceDetail> onlineDetailList = this.userOnlineServiceMapper.listUserOnlineServiceDetail(userOnlineService.getRefrenceId());
            for (UserOnlineServiceDetail detail : onlineDetailList)
            {
                userOnlineServiceDetailMapper.delete(detail.getRefrenceId());
            }
            userOnlineServiceMapper.delete(old.getRefrenceId());
        }
        userOnlineService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        userOnlineService.setOnlineBeginTime(CalendarUtils.getLongFromTime(userOnlineService.getTmpBeginTime()));
        userOnlineService.setOnlineEndTime(CalendarUtils.getLongFromTime(userOnlineService.getTmpEndTime()));
        userOnlineService.setCreateTime(CalendarUtils.getCurrentLong());
        userOnlineServiceMapper.insert(userOnlineService);
        if (ArrayUtils.isNotEmpty(userOnlineService.getQqs()) && ArrayUtils.isNotEmpty(userOnlineService.getNames()))
        {
            for (int i = 0; i < userOnlineService.getQqs().length; i++)
            {
                if (StringUtils.isBlank(userOnlineService.getQqs()[i]) || StringUtils.isBlank(userOnlineService.getNames()[i]))
                {
                    continue;
                }
                UserOnlineServiceDetail userOnlineServiceDetail = new UserOnlineServiceDetail();
                userOnlineServiceDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                userOnlineServiceDetail.setUserOnlineId(userOnlineService.getRefrenceId());
                userOnlineServiceDetail.setQq(userOnlineService.getQqs()[i]);
                userOnlineServiceDetail.setName(userOnlineService.getNames()[i]);
                // i+CalendarUtils.getCurrentLong() 是新增客服的顺序与取出的顺序一致
                userOnlineServiceDetail.setCreateTime(i + CalendarUtils.getCurrentLong());
                userOnlineServiceDetailMapper.insert(userOnlineServiceDetail);
            }
        }
    }
}
