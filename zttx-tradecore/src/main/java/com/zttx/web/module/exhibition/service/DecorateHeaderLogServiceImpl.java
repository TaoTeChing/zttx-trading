/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateHeaderLog;
import com.zttx.web.module.exhibition.mapper.DecorateHeaderLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅头部装修 服务实现类
 * <p>File：DecorateHeaderLog.java </p>
 * <p>Title: DecorateHeaderLog </p>
 * <p>Description:DecorateHeaderLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateHeaderLogServiceImpl extends GenericServiceApiImpl<DecorateHeaderLog> implements DecorateHeaderLogService
{

    private DecorateHeaderLogMapper decorateHeaderLogMapper;

    @Autowired(required = true)
    public DecorateHeaderLogServiceImpl(DecorateHeaderLogMapper decorateHeaderLogMapper)
    {
        super(decorateHeaderLogMapper);
        this.decorateHeaderLogMapper = decorateHeaderLogMapper;
    }
}
