/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateConfigLog;
import com.zttx.web.module.exhibition.mapper.DecorateConfigLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅自定义模块配置 服务实现类
 * <p>File：DecorateConfigLog.java </p>
 * <p>Title: DecorateConfigLog </p>
 * <p>Description:DecorateConfigLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateConfigLogServiceImpl extends GenericServiceApiImpl<DecorateConfigLog> implements DecorateConfigLogService
{

    private DecorateConfigLogMapper decorateConfigLogMapper;

    @Autowired(required = true)
    public DecorateConfigLogServiceImpl(DecorateConfigLogMapper decorateConfigLogMapper)
    {
        super(decorateConfigLogMapper);
        this.decorateConfigLogMapper = decorateConfigLogMapper;
    }
}
