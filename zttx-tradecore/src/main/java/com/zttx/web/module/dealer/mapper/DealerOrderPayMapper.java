/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrderPay;

/**
 * 支付记录表 持久层接口
 * <p>File：DealerOrderPayDao.java </p>
 * <p>Title: DealerOrderPayDao </p>
 * <p>Description:DealerOrderPayDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerOrderPayMapper extends GenericMapper<DealerOrderPay>
{
    /**
     * 获取单个订单所有支付记录
     * @param orderId
     * @return
     */
    List<DealerOrderPay> findByOrderId(String orderId);
    
    /**
     * 获取未付款记录
     * @param orderId
     * @return
     */
    List<DealerOrderPay> listUnpay(@Param("orderId") String orderId);
    
    /**
     * 获取已付款记录
     * @param orderId
     * @return
     */
    List<DealerOrderPay> listPaid(@Param("orderId") String orderId);
    
    /**
     * 根据支付ID获取支付记录
     * @param payId
     * @return
     */
    DealerOrderPay findByPayId(@Param("payId") Long payId);
    
    /**
     * 获取所有状态为未支付的订单支付记录
     *
     * @return
     * @throws BusinessException
     */
    List<DealerOrderPay> findAllUnPay();
}
