/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.Adjustments;
import com.zttx.web.module.common.mapper.AdjustmentsMapper;

/**
 * 调价详细信息表 服务实现类
 * <p>File：Adjustments.java </p>
 * <p>Title: Adjustments </p>
 * <p>Description:Adjustments </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AdjustmentsServiceImpl extends GenericServiceApiImpl<Adjustments> implements AdjustmentsService
{
    @Autowired
    private AdjustmentService brandAdjustmentService;
    
    private AdjustmentsMapper adjustmentsMapper;
    
    @Autowired(required = true)
    public AdjustmentsServiceImpl(AdjustmentsMapper adjustmentsMapper)
    {
        super(adjustmentsMapper);
        this.adjustmentsMapper = adjustmentsMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getBrandAdjustmentsList(Map<String, Object> sumMap, Adjustments filter, Pagination page)
    {
        if (brandAdjustmentService.isExitAdjustment(filter.getDealerId(), filter.getBrandId(), filter.getBrandAdjustId()))
        {
            List<Map<String, Object>> mapList = adjustmentsMapper.getBrandAdjustmentsList(page, filter);
            // 与分页相同条件下，查询符合条件的数据总和,总条数统计
            sumMap.putAll(adjustmentsMapper.getBrandAdjustmentsListSum(filter));
            return new PaginateResult<>(page, mapList);
        }
        return null;
    }
}
