/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.RefundNumber;

/**
 * 退款流水号 持久层接口
 * <p>File：RefundNumberDao.java </p>
 * <p>Title: RefundNumberDao </p>
 * <p>Description:RefundNumberDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RefundNumberMapper extends GenericMapper<RefundNumber>
{

}
