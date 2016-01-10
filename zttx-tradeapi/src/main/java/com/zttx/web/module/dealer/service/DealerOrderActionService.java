/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrderAction;
import com.zttx.web.module.dealer.model.DealerOrderActionModel;

/**
 * 订单操作历史记录 服务接口
 * <p>File：DealerOrderActionService.java </p>
 * <p>Title: DealerOrderActionService </p>
 * <p>Description:DealerOrderActionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerOrderActionService extends GenericServiceApi<DealerOrderAction>{

    /**
     * 根据订单ID查找
     *
     * @param orderId
     * @return
     */
    List<DealerOrderAction> findByOrderId(String orderId);
    
    /**
     * 关闭订单
     * @param dealerOrderAction
     * @param operatorRole
     * @throws BusinessException
     */
    void saveCloseOrder(DealerOrderActionModel dealerOrderAction, String operatorRole) throws BusinessException;

    /**
     * 根据 订单Id， 经销Id 获取 经销商操作该订单的日志
     * @param dealerId
     * @param refrenceId
     * @return
     * @author 易永耀
     */

    List<DealerOrderAction> selectDealerOrderAction(String dealerId, String refrenceId);
    
    /**
     * 同步订单操作记录(boss)
     * @param dealerOrderAction
     * @param page
     * @return
     * @author 李星
     */
    PaginateResult<DealerOrderAction> getDealerOrderActionList4Boss(DealerOrderAction dealerOrderAction, Pagination page);
}
