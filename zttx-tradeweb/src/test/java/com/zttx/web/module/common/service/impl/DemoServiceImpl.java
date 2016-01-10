/*
 * @(#)DemoServiceImpl.java 2015-8-10 上午9:27:31
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service.impl;

import com.zttx.web.module.common.mapper.DemoMapper;
import com.zttx.web.module.common.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.Demo;

/**
 * <p>File：DemoServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-10 上午9:27:31</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class DemoServiceImpl extends GenericServiceApiImpl<Demo> implements DemoService
{
    private DemoMapper demoMapper;

    @Autowired(required = true)
    public DemoServiceImpl(DemoMapper demoMapper)
    {
        super(demoMapper);
        this.demoMapper = demoMapper;
    }
}
