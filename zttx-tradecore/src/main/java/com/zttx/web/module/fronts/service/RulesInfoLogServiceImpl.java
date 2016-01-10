/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.fronts.entity.RulesInfoLog;
import com.zttx.web.module.fronts.mapper.RulesInfoLogMapper;

/**
 * 规则内容历史记录 服务实现类
 * <p>File：RulesInfoLog.java </p>
 * <p>Title: RulesInfoLog </p>
 * <p>Description:RulesInfoLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RulesInfoLogServiceImpl extends GenericServiceApiImpl<RulesInfoLog> implements RulesInfoLogService
{
    private RulesInfoLogMapper rulesInfoLogMapper;
    
    @Autowired(required = true)
    public RulesInfoLogServiceImpl(RulesInfoLogMapper rulesInfoLogMapper)
    {
        super(rulesInfoLogMapper);
        this.rulesInfoLogMapper = rulesInfoLogMapper;
    }
    
    @Override
    public void addRulesInfoLog(RulesInfoLog rulesInfoLog)
    {
        rulesInfoLogMapper.insert(rulesInfoLog);
    }
}
