/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.fronts.entity.JoinInfo;
import com.zttx.web.module.fronts.mapper.JoinInfoMapper;

/**
 * 加盟入驻信息 服务实现类
 * <p>File：JoinInfo.java </p>
 * <p>Title: JoinInfo </p>
 * <p>Description:JoinInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class JoinInfoServiceImpl extends GenericServiceApiImpl<JoinInfo> implements JoinInfoService
{
    private JoinInfoMapper joinInfoMapper;
    
    @Autowired(required = true)
    public JoinInfoServiceImpl(JoinInfoMapper joinInfoMapper)
    {
        super(joinInfoMapper);
        this.joinInfoMapper = joinInfoMapper;
    }
    
    @Override
    public void save(JoinInfo joinInfo) throws BusinessException
    {
        if (StringUtils.isNotBlank(joinInfo.getRefrenceId()))
        {
            JoinInfo dbJoinInfo = joinInfoMapper.selectByPrimaryKey(joinInfo.getRefrenceId());
            if (dbJoinInfo == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            joinInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            joinInfo.setCreateIp(dbJoinInfo.getCreateIp());
            joinInfoMapper.updateByPrimaryKeySelective(joinInfo);
        }
        else
        {
            joinInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            joinInfo.setCreateTime(CalendarUtils.getCurrentLong());
            joinInfoMapper.insertSelective(joinInfo);
        }
    }
}
