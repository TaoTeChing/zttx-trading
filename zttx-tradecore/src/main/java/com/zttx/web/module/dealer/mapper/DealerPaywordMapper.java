/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerPayword;

/**
 * 经销商支付密码 持久层接口
 * <p>File：DealerPaywordDao.java </p>
 * <p>Title: DealerPaywordDao </p>
 * <p>Description:DealerPaywordDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerPaywordMapper extends GenericMapper<DealerPayword>
{
}
