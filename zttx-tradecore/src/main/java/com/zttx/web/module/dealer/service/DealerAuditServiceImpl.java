/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.dealer.entity.DealerAudit;
import com.zttx.web.module.dealer.mapper.DealerAuditMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商审核日志 服务实现类
 * <p>File：DealerAudit.java </p>
 * <p>Title: DealerAudit </p>
 * <p>Description:DealerAudit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerAuditServiceImpl extends GenericServiceApiImpl<DealerAudit> implements DealerAuditService
{
    private DealerAuditMapper dealerAuditMapper;
    
    @Autowired(required = true)
    public DealerAuditServiceImpl(DealerAuditMapper dealerAuditMapper)
    {
        super(dealerAuditMapper);
        this.dealerAuditMapper = dealerAuditMapper;
    }
    
    @Override
    public void save(DealerAudit dealerAudit)
    {
        if (StringUtils.isBlank(dealerAudit.getRefrenceId()))
        {
            dealerAudit.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerAudit.setCheckTime(CalendarUtils.getCurrentLong());
            dealerAuditMapper.insertSelective(dealerAudit);
        }
        else
        {
            dealerAuditMapper.updateByPrimaryKeySelective(dealerAudit);
        }
    }
}
