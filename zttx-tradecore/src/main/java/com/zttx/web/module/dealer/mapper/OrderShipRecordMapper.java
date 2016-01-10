/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.OrderShipRecord;

import java.util.List;

/**
 * 经销商订单发货记录 持久层接口
 * <p>File：OrderShipRecordDao.java </p>
 * <p>Title: OrderShipRecordDao </p>
 * <p>Description:OrderShipRecordDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface OrderShipRecordMapper extends GenericMapper<OrderShipRecord>
{
    /**
     * 根据订单号 获取 经销商订单发货信息，获取最新的一条
     * @param orderId
     * @return
     */
    List<OrderShipRecord> getOrderShipRecord(String orderId);
}
