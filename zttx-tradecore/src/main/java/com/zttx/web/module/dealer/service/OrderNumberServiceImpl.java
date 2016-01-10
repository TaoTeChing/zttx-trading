/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.OrderNumber;
import com.zttx.web.module.dealer.mapper.OrderNumberMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单流水号 服务实现类
 * <p>File：OrderNumber.java </p>
 * <p>Title: OrderNumber </p>
 * <p>Description:OrderNumber </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderNumberServiceImpl extends GenericServiceApiImpl<OrderNumber> implements OrderNumberService
{

    private OrderNumberMapper orderNumberMapper;

    @Autowired(required = true)
    public OrderNumberServiceImpl(OrderNumberMapper orderNumberMapper)
    {
        super(orderNumberMapper);
        this.orderNumberMapper = orderNumberMapper;
    }

    @Override
    public Long execute()
    {
        Long currentTime = CalendarUtils.getCurrentLong();
        OrderNumber order = new OrderNumber();
        order.setCreateTime(currentTime);
        orderNumberMapper.insert(order);
        return Long.valueOf(order.getRefrenceId());
    }

}
