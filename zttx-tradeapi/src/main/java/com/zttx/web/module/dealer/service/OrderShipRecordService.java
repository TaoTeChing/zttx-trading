/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.OrderShipRecord;
/**
 * 经销商订单发货记录 服务接口
 * <p>File：OrderShipRecordService.java </p>
 * <p>Title: OrderShipRecordService </p>
 * <p>Description:OrderShipRecordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface OrderShipRecordService extends GenericServiceApi<OrderShipRecord>{
    /**
     * 根据订单号 获取 经销商订单发货信息
     * @param orderId
     * @return
     */
    OrderShipRecord getOrderShipRecord(String orderId);
}
