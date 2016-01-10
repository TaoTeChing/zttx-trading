/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerBank;
import com.zttx.web.module.dealer.mapper.DealerBankMapper;
import com.zttx.web.utils.EncryptUtils;

/**
 * 经销商银行卡信息 服务实现类
 * <p>File：DealerBank.java </p>
 * <p>Title: DealerBank </p>
 * <p>Description:DealerBank </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerBankServiceImpl extends GenericServiceApiImpl<DealerBank> implements DealerBankService
{
    private DealerBankMapper dealerBankMapper;
    
    @Autowired(required = true)
    public DealerBankServiceImpl(DealerBankMapper dealerBankMapper)
    {
        super(dealerBankMapper);
        this.dealerBankMapper = dealerBankMapper;
    }
    
    @Override
    public void updataCheckState(String refrenceId, String dealerId, Short checkState) throws BusinessException
    {
        DealerBank dealerBank = dealerBankMapper.selectByPrimaryKey(refrenceId);
        if (null == dealerBank) { throw new BusinessException(ClientConst.OBJECTEXIST); }
        if (DealerConstant.DealerBank.CHECK_STATE_FAILURE.equals(checkState) || DealerConstant.DealerBank.CHECK_STATE_SUCCESS.equals(checkState))
        {
            dealerBank.setCheckState(checkState);
            dealerBank.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerBankMapper.updateByPrimaryKey(dealerBank);
        }
        else
        {
            throw new BusinessException(ClientConst.FAILURE.getCode(), "未知类型");
        }
    }
    
    @Override
    public PaginateResult<DealerBank> searchByClient(DealerBank searchBean, Pagination page)
    {
        searchBean.setPage(page);
        searchBean.setBankNo(StringUtils.isBlank(searchBean.getBankNo()) ? null : EncryptUtils.desEncrypt(searchBean.getBankNo()));
        List<DealerBank> result = dealerBankMapper.findList(searchBean);
        for (DealerBank dealerBank : result)
        {
            dealerBank.setBankNo(EncryptUtils.desDecrypt(dealerBank.getBankNo()));
        }
        return new PaginateResult<>(searchBean.getPage(), result);
    }
}
