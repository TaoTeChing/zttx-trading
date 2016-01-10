/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.common.entity.FindAccount;
import com.zttx.web.module.common.mapper.FindAccountMapper;

/**
 * 忘记登录账户 服务实现类
 * <p>File：FindAccount.java </p>
 * <p>Title: FindAccount </p>
 * <p>Description:FindAccount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class FindAccountServiceImpl extends GenericServiceApiImpl<FindAccount> implements FindAccountService
{
    private FindAccountMapper findAccountMapper;
    
    @Autowired(required = true)
    public FindAccountServiceImpl(FindAccountMapper findAccountMapper)
    {
        super(findAccountMapper);
        this.findAccountMapper = findAccountMapper;
    }
    
    @Override
    public void save(FindAccount findAccount) throws BusinessException
    {
        if (findAccount == null) { throw new BusinessException(CommonConst.PARAM_NULL); }
        findAccount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        findAccount.setCreateTime(CalendarUtils.getCurrentLong());
        findAccount.setCheckState(CommonConstant.FindAccountConstant.WAIT);
        findAccount.setCheckMark(null);
        findAccount.setUserId(null);
        findAccount.setCheckTime(null);
        findAccountMapper.insertSelective(findAccount);
    }
}
