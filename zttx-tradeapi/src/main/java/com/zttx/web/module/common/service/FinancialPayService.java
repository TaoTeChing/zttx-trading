/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.pay.remoting.model.TransferReturnObj;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.FinancialPay;

import java.util.List;

/**
 * 财务账支付记录表 服务接口
 * <p>File：FinancialPayService.java </p>
 * <p>Title: FinancialPayService </p>
 * <p>Description:FinancialPayService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface FinancialPayService extends GenericServiceApi<FinancialPay>{
    /**
     * 更新返点支付数据库
     * @author 易永耀
     * @param transferReturnObjList
     */
    void updateFinancialPay(List<TransferReturnObj> transferReturnObjList) throws BusinessException;
}
