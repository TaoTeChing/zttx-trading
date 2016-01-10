/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.DealerDeposit;

/**
 * 押金支付记录 持久层接口
 * <p>File：DealerDepositDao.java </p>
 * <p>Title: DealerDepositDao </p>
 * <p>Description:DealerDepositDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerDepositMapper extends GenericMapper<DealerDeposit>
{

}
