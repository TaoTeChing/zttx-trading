/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.OrderPayRecord;

/**
 * 经销商订单支付历史记录 持久层接口
 * <p>File：OrderPayRecordDao.java </p>
 * <p>Title: OrderPayRecordDao </p>
 * <p>Description:OrderPayRecordDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface OrderPayRecordMapper extends GenericMapper<OrderPayRecord>
{
    /**
     * 根据订单ID和经销商ID查找订单支付记录
     *
     * @param orderId  订单ID
     * @param dealerId 经销商ID
     * @return List<OrderPayRecord>
     */
    List<OrderPayRecord> listPayRecordBy(@Param("orderId") String orderId, @Param("dealerId") String dealerId);
}
