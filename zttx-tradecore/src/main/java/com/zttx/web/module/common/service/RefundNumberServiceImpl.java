/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.RefundNumber;
import com.zttx.web.module.common.mapper.RefundNumberMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 退款流水号 服务实现类
 * <p>File：RefundNumber.java </p>
 * <p>Title: RefundNumber </p>
 * <p>Description:RefundNumber </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RefundNumberServiceImpl extends GenericServiceApiImpl<RefundNumber> implements RefundNumberService
{

    private RefundNumberMapper refundNumberMapper;

    @Autowired(required = true)
    public RefundNumberServiceImpl(RefundNumberMapper refundNumberMapper)
    {
        super(refundNumberMapper);
        this.refundNumberMapper = refundNumberMapper;
    }
    
    @Override
    public Long execute()
    {
        Long currentTime = CalendarUtils.getCurrentLong();
        RefundNumber refund = new RefundNumber();
        refund.setCreateTime(currentTime);
        refundNumberMapper.insert(refund);
        return refund.getRefundId();
    }
}
