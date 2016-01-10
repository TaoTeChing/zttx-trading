/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerOrderAction;

/**
 * 订单操作历史记录 持久层接口
 * <p>File：DealerOrderActionDao.java </p>
 * <p>Title: DealerOrderActionDao </p>
 * <p>Description:DealerOrderActionDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerOrderActionMapper extends GenericMapper<DealerOrderAction>
{

    /**
     * 根据订单ID查找
     * @param orderId
     * @return
     */
    List<DealerOrderAction> findByOrderId(@Param("orderId") String orderId);
    
    /**
     * 通过用户ID和订单ID来查询是否存在
     * @param userId
     * @param orderId
     * @param actCode
     * @return
     */
    Boolean isExist(@Param("userId") String userId, @Param("orderId") String orderId, @Param("actCode") String actCode);
    /**
     * 根据 订单Id， 经销Id 获取 经销商操作该订单的日志
     * @param dealerId
     * @param orderId
     * @return
     * @author 易永耀
     */

    List<DealerOrderAction> selectDealerOrderAction(String dealerId, String orderId);
    
    /**
     * 同步订单操作记录(boss)
     * @param dealerOrderAction
     * @return
     */
    List<DealerOrderAction> getDealerOrderActionList4Boss(DealerOrderAction dealerOrderAction);
}
