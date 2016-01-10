/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.TradeDetails;

/**
 * 交易明细表 持久层接口
 * <p>File：TradeDetailsDao.java </p>
 * <p>Title: TradeDetailsDao </p>
 * <p>Description:TradeDetailsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface TradeDetailsMapper extends GenericMapper<TradeDetails>
{
    /**
     * 更新
     * @param orderId
     * @param tradeState
     * @return
     */
    Integer updateTradeDetails(@Param("orderId") String orderId, @Param("tradeState") Short tradeState);
    
    /**
     * 根据订单ID获取已支付的交易记录
     * @param orderId
     * @return
     */
    List<TradeDetails> listPaidByOrderId(@Param("orderId") String orderId);
    
    /**
     * 修改出款金额和支付状态
     * @param orderId
     * @param payState
     * @param balance
     * @return
     */
    Integer updatePayDetails(@Param("orderId") String orderId, @Param("payState") Short payState, @Param("balance") BigDecimal balance);
}
