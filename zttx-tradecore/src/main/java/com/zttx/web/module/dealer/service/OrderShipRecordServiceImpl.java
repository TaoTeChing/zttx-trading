/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.OrderShipRecord;
import com.zttx.web.module.dealer.mapper.OrderShipRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 经销商订单发货记录 服务实现类
 * <p>File：OrderShipRecord.java </p>
 * <p>Title: OrderShipRecord </p>
 * <p>Description:OrderShipRecord </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderShipRecordServiceImpl extends GenericServiceApiImpl<OrderShipRecord> implements OrderShipRecordService
{

    private OrderShipRecordMapper orderShipRecordMapper;

    @Autowired(required = true)
    public OrderShipRecordServiceImpl(OrderShipRecordMapper orderShipRecordMapper)
    {
        super(orderShipRecordMapper);
        this.orderShipRecordMapper = orderShipRecordMapper;
    }

    @Override
    public OrderShipRecord getOrderShipRecord(String orderId) {
        if(null!=orderId)
        {
            List<OrderShipRecord> orderShipRecordList = orderShipRecordMapper.getOrderShipRecord(orderId);
            if(!orderShipRecordList.isEmpty()){return orderShipRecordList.get(0);}
        }
        return null;
    }
}
