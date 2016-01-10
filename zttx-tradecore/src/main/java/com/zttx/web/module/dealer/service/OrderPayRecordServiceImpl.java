/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.OrderPayRecord;
import com.zttx.web.module.dealer.mapper.OrderPayRecordMapper;

/**
 * 经销商订单支付历史记录 服务实现类
 * <p>File：OrderPayRecord.java </p>
 * <p>Title: OrderPayRecord </p>
 * <p>Description:OrderPayRecord </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderPayRecordServiceImpl extends GenericServiceApiImpl<OrderPayRecord> implements OrderPayRecordService
{

    private OrderPayRecordMapper orderPayRecordMapper;

    @Autowired(required = true)
    public OrderPayRecordServiceImpl(OrderPayRecordMapper orderPayRecordMapper)
    {
        super(orderPayRecordMapper);
        this.orderPayRecordMapper = orderPayRecordMapper;
    }
    
    @Override
    public List<OrderPayRecord> listPayRecordBy(String orderId, String dealerId)
    {
        return orderPayRecordMapper.listPayRecordBy(orderId, dealerId);
    }
}
