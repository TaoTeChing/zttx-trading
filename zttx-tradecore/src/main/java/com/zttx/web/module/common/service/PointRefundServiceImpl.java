/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.PointRefund;
import com.zttx.web.module.common.mapper.PointRefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品返点退货记录表 服务实现类
 * <p>File：PointRefund.java </p>
 * <p>Title: PointRefund </p>
 * <p>Description:PointRefund </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class PointRefundServiceImpl extends GenericServiceApiImpl<PointRefund> implements PointRefundService
{

    private PointRefundMapper pointRefundMapper;

    @Autowired(required = true)
    public PointRefundServiceImpl(PointRefundMapper pointRefundMapper)
    {
        super(pointRefundMapper);
        this.pointRefundMapper = pointRefundMapper;
    }
    
}
