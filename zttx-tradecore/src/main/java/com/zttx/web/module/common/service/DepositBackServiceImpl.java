/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.DepositBack;
import com.zttx.web.module.common.mapper.DepositBackMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转账记录表 服务实现类
 * <p>File：DepositBack.java </p>
 * <p>Title: DepositBack </p>
 * <p>Description:DepositBack </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DepositBackServiceImpl extends GenericServiceApiImpl<DepositBack> implements DepositBackService
{

    private DepositBackMapper depositBackMapper;

    @Autowired(required = true)
    public DepositBackServiceImpl(DepositBackMapper depositBackMapper)
    {
        super(depositBackMapper);
        this.depositBackMapper = depositBackMapper;
    }
}
