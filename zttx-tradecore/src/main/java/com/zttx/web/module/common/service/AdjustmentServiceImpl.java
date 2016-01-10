/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.Adjustment;
import com.zttx.web.module.common.mapper.AdjustmentMapper;

/**
 * 调价信息主表 服务实现类
 * <p>File：Adjustment.java </p>
 * <p>Title: Adjustment </p>
 * <p>Description:Adjustment </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AdjustmentServiceImpl extends GenericServiceApiImpl<Adjustment> implements AdjustmentService
{
    private AdjustmentMapper adjustmentMapper;
    
    @Autowired(required = true)
    public AdjustmentServiceImpl(AdjustmentMapper adjustmentMapper)
    {
        super(adjustmentMapper);
        this.adjustmentMapper = adjustmentMapper;
    }
    
    @Override
    public Boolean isExitAdjustment(String dealerId, String brandId, String brandAdjustId)
    {
        return adjustmentMapper.countAdjustment(dealerId, brandId, brandAdjustId) > 0;
    }
}
