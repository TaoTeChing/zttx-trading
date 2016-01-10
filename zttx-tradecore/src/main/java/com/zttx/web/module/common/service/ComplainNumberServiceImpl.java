/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ComplainNumber;
import com.zttx.web.module.common.mapper.ComplainNumberMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 投诉流水号 服务实现类
 * <p>File：ComplainNumber.java </p>
 * <p>Title: ComplainNumber </p>
 * <p>Description:ComplainNumber </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ComplainNumberServiceImpl extends GenericServiceApiImpl<ComplainNumber> implements ComplainNumberService
{
    private ComplainNumberMapper complainNumberMapper;
    
    @Autowired(required = true)
    public ComplainNumberServiceImpl(ComplainNumberMapper complainNumberMapper)
    {
        super(complainNumberMapper);
        this.complainNumberMapper = complainNumberMapper;
    }
    
    /**
     * 新增投诉流水号数据，返回当前流水号
     *
     * @return Long 当前流水号
     */
    @Override
    public Long getComplainId()
    {
        ComplainNumber complain = new ComplainNumber();
        complain.setCreateTime(CalendarUtils.getCurrentLong());
        complainNumberMapper.insert(complain);// complain会返回自增长主键
        return Long.valueOf(complain.getComplainId());
    }
}
