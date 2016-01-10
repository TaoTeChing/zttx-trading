/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateGlobalLog;
import com.zttx.web.module.exhibition.mapper.DecorateGlobalLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅装修全局配置历史记录 服务实现类
 * <p>File：DecorateGlobalLog.java </p>
 * <p>Title: DecorateGlobalLog </p>
 * <p>Description:DecorateGlobalLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateGlobalLogServiceImpl extends GenericServiceApiImpl<DecorateGlobalLog> implements DecorateGlobalLogService
{

    private DecorateGlobalLogMapper decorateGlobalLogMapper;

    @Autowired(required = true)
    public DecorateGlobalLogServiceImpl(DecorateGlobalLogMapper decorateGlobalLogMapper)
    {
        super(decorateGlobalLogMapper);
        this.decorateGlobalLogMapper = decorateGlobalLogMapper;
    }
}
