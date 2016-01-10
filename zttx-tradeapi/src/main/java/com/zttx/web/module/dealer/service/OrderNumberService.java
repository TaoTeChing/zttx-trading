/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.OrderNumber;
/**
 * 订单流水号 服务接口
 * <p>File：OrderNumberService.java </p>
 * <p>Title: OrderNumberService </p>
 * <p>Description:OrderNumberService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface OrderNumberService extends GenericServiceApi<OrderNumber>{


    /**
     * 新增订单流水号数据，并返回当前流水号
     * @return Long 流水号
     */
    public Long execute();

}
