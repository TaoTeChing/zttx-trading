/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.OrderChangeLog;
import com.zttx.web.module.dealer.mapper.OrderChangeLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单金额修改记录 服务实现类
 * <p>File：OrderChangeLog.java </p>
 * <p>Title: OrderChangeLog </p>
 * <p>Description:OrderChangeLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderChangeLogServiceImpl extends GenericServiceApiImpl<OrderChangeLog> implements OrderChangeLogService
{

    private OrderChangeLogMapper orderChangeLogMapper;

    @Autowired(required = true)
    public OrderChangeLogServiceImpl(OrderChangeLogMapper orderChangeLogMapper)
    {
        super(orderChangeLogMapper);
        this.orderChangeLogMapper = orderChangeLogMapper;
    }
}
