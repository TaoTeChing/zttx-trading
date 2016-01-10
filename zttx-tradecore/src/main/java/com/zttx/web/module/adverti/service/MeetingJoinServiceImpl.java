/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.adverti.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.adverti.entity.MeetingJoin;
import com.zttx.web.module.adverti.mapper.MeetingJoinMapper;

/**
 * 交易会活动报名 服务实现类
 * <p>File：MeetingJoin.java </p>
 * <p>Title: MeetingJoin </p>
 * <p>Description:MeetingJoin </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class MeetingJoinServiceImpl extends GenericServiceApiImpl<MeetingJoin> implements MeetingJoinService
{
    private MeetingJoinMapper meetingJoinMapper;
    
    @Autowired(required = true)
    public MeetingJoinServiceImpl(MeetingJoinMapper meetingJoinMapper)
    {
        super(meetingJoinMapper);
        this.meetingJoinMapper = meetingJoinMapper;
    }
    
    @Override
    public void save(MeetingJoin entity) throws BusinessException
    {
        if (StringUtils.isNotBlank(entity.getRefrenceId()))
        {
            meetingJoinMapper.updateByPrimaryKeySelective(entity);
        }
        else
        {
            entity.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            entity.setCreateTime(CalendarUtils.getCurrentTime());
            meetingJoinMapper.insert(entity);
        }
    }
}
