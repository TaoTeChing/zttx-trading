/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.LogisticsInfo;
import com.zttx.web.module.common.mapper.LogisticsInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 物流详细信息 服务实现类
 * <p>File：LogisticsInfo.java </p>
 * <p>Title: LogisticsInfo </p>
 * <p>Description:LogisticsInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class LogisticsInfoServiceImpl extends GenericServiceApiImpl<LogisticsInfo> implements LogisticsInfoService
{

    private LogisticsInfoMapper logisticsInfoMapper;

    @Autowired(required = true)
    public LogisticsInfoServiceImpl(LogisticsInfoMapper logisticsInfoMapper)
    {
        super(logisticsInfoMapper);
        this.logisticsInfoMapper = logisticsInfoMapper;
    }
}
