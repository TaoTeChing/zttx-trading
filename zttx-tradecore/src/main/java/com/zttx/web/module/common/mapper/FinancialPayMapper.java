/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.FinancialPay;

/**
 * 财务账支付记录表 持久层接口
 * <p>File：FinancialPayDao.java </p>
 * <p>Title: FinancialPayDao </p>
 * <p>Description:FinancialPayDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface FinancialPayMapper extends GenericMapper<FinancialPay>
{
    /**
     * 根据 payExtId 更新数据
     * @param financialPay
     */
    void updateFinancialPay(FinancialPay financialPay);
}
