/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.OrderChangeLog;

/**
 * 订单金额修改记录 持久层接口
 * <p>File：OrderChangeLogDao.java </p>
 * <p>Title: OrderChangeLogDao </p>
 * <p>Description:OrderChangeLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface OrderChangeLogMapper extends GenericMapper<OrderChangeLog>
{

}
