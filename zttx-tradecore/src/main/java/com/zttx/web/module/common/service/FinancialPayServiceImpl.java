/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.pay.remoting.model.TransferReturnObj;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.common.entity.FinancialPay;
import com.zttx.web.module.common.mapper.FinancialPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务账支付记录表 服务实现类
 * <p>File：FinancialPay.java </p>
 * <p>Title: FinancialPay </p>
 * <p>Description:FinancialPay </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class FinancialPayServiceImpl extends GenericServiceApiImpl<FinancialPay> implements FinancialPayService
{

    private FinancialPayMapper financialPayMapper;

    @Autowired(required = true)
    public FinancialPayServiceImpl(FinancialPayMapper financialPayMapper)
    {
        super(financialPayMapper);
        this.financialPayMapper = financialPayMapper;
    }

    @Override
    public void updateFinancialPay(List<TransferReturnObj> transferReturnObjList) throws BusinessException {
        if(null==transferReturnObjList||transferReturnObjList.isEmpty()){throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：transferReturnObjList 空");}
        for(int i=0,length=transferReturnObjList.size();i<length;i++)
        {
            TransferReturnObj transferReturnObj = transferReturnObjList.get(i);
            FinancialPay financialPay = new FinancialPay();
            financialPay.setPayExtId(transferReturnObj.getExId().toString());
            financialPay.setPayState(transferReturnObj.isSuccess()?CommonConstant.financial.PAY_SUCCESS:CommonConstant.financial.PAY_FAIL);
            financialPayMapper.updateFinancialPay(financialPay);
        }

    }
}
