
/*
 * Copyright 2015 Playguy, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerOrders;

import java.util.Map;

/**
 * <p>File：DealerOrderItemsService.java</p>
 * <p>Title: DealerOrderItemsService</p>
 * <p>Description:DealerOrderItemsService</p>
 * <p>Copyright: Copyright (c) 2015年9月7日</p>
 * <p>Company: 8637.com</p>
 * @author txsb
 * @version 1.0
 */
public interface DealerOrderItemsService {

    /**
     * 获取品牌商所有订单项数据
     *
     * @param pagination
     * @param dealerOrders
     * @return
     */
    PaginateResult<Map<String, Object>> getDealerOrders(Pagination pagination, DealerOrders dealerOrders);

}
