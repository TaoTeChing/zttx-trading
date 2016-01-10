/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateMenuLog;
import com.zttx.web.module.exhibition.mapper.DecorateMenuLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅装修菜单历史记录 服务实现类
 * <p>File：DecorateMenuLog.java </p>
 * <p>Title: DecorateMenuLog </p>
 * <p>Description:DecorateMenuLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateMenuLogServiceImpl extends GenericServiceApiImpl<DecorateMenuLog> implements DecorateMenuLogService
{

    private DecorateMenuLogMapper decorateMenuLogMapper;

    @Autowired(required = true)
    public DecorateMenuLogServiceImpl(DecorateMenuLogMapper decorateMenuLogMapper)
    {
        super(decorateMenuLogMapper);
        this.decorateMenuLogMapper = decorateMenuLogMapper;
    }
}
