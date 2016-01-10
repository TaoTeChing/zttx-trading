/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ParityColumn;
import com.zttx.web.module.common.mapper.ParityColumnMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 比价栏目表 服务实现类
 * <p>File：ParityColumn.java </p>
 * <p>Title: ParityColumn </p>
 * <p>Description:ParityColumn </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ParityColumnServiceImpl extends GenericServiceApiImpl<ParityColumn> implements ParityColumnService
{

    private ParityColumnMapper parityColumnMapper;

    @Autowired(required = true)
    public ParityColumnServiceImpl(ParityColumnMapper parityColumnMapper)
    {
        super(parityColumnMapper);
        this.parityColumnMapper = parityColumnMapper;
    }

    @Override
    public List<ParityColumn> getParityColumnList()
    {
        
        return parityColumnMapper.selectAll();
    }
}
