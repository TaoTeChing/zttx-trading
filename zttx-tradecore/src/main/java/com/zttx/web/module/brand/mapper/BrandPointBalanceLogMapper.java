/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandPointBalanceLog;

/**
 * 扣点修改日志 持久层接口
 * <p>File：BrandPointBalanceLogDao.java </p>
 * <p>Title: BrandPointBalanceLogDao </p>
 * <p>Description:BrandPointBalanceLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandPointBalanceLogMapper extends GenericMapper<BrandPointBalanceLog>
{

}
