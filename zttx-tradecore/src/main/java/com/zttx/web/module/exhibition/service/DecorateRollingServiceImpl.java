/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateRolling;
import com.zttx.web.module.exhibition.mapper.DecorateRollingMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 展厅图片自定义滚动表 服务实现类
 * <p>File：DecorateRolling.java </p>
 * <p>Title: DecorateRolling </p>
 * <p>Description:DecorateRolling </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateRollingServiceImpl extends GenericServiceApiImpl<DecorateRolling> implements DecorateRollingService
{

    private DecorateRollingMapper decorateRollingMapper;

    @Autowired(required = true)
    public DecorateRollingServiceImpl(DecorateRollingMapper decorateRollingMapper)
    {
        super(decorateRollingMapper);
        this.decorateRollingMapper = decorateRollingMapper;
    }
}
