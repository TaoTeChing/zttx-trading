/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.OrderPayRecord;

/**
 * 经销商订单支付历史记录 服务接口
 * <p>File：OrderPayRecordService.java </p>
 * <p>Title: OrderPayRecordService </p>
 * <p>Description:OrderPayRecordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface OrderPayRecordService extends GenericServiceApi<OrderPayRecord>{

    /**
     * 根据订单ID和经销商ID查找订单支付记录
     *
     * @param orderId  订单ID
     * @param dealerId 经销商ID
     * @return List<OrderPayRecord>
     */
    List<OrderPayRecord> listPayRecordBy(String orderId,String dealerId);
}
