/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.brand.entity.BrandPaylog;
import com.zttx.web.module.brand.entity.BrandPayword;
import com.zttx.web.module.brand.mapper.BrandPaylogMapper;
import com.zttx.web.module.brand.mapper.BrandPaywordMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncryptUtils;

/**
 * 品牌商支付密码 服务实现类
 * <p>File：BrandPayword.java </p>
 * <p>Title: BrandPayword </p>
 * <p>Description:BrandPayword </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandPaywordServiceImpl extends GenericServiceApiImpl<BrandPayword> implements BrandPaywordService
{
    // 创建
    public static final short  CREATION     = 1;
    
    // 修改
    public static final short  MODIFICATION = 2;
    
    @Autowired
    private BrandPaylogMapper  brandPaylogMapper;

    private BrandPaywordMapper brandPaywordMapper;
    
    @Autowired(required = true)
    public BrandPaywordServiceImpl(BrandPaywordMapper brandPaywordMapper)
    {
        super(brandPaywordMapper);
        this.brandPaywordMapper = brandPaywordMapper;
    }
    
    @Override
    public void insertCreatePayPassword(BrandPayword newPwd) throws BusinessException
    {
        encrypt(newPwd);
        newPwd.setCreateTime(CalendarUtils.getCurrentLong());
        BrandPaylog changeLog = new BrandPaylog();
        changeLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        changeLog.setBrandId(newPwd.getRefrenceId());
        changeLog.setCreateType(CREATION);
        changeLog.setCreateIp(newPwd.getCreateIp());
        changeLog.setCreateTime(CalendarUtils.getCurrentLong());
        changeLog.setOldSalt("");
        changeLog.setOldWord("");
        changeLog.setNewSalt(newPwd.getPaySalt());
        changeLog.setNewWord(newPwd.getPayWord());
        brandPaylogMapper.insert(changeLog);
        brandPaywordMapper.insert(newPwd);
    }
    
    private static void encrypt(BrandPayword pwd)
    {
        String salt = RandomStringUtils.randomAlphabetic(11);
        pwd.setPaySalt(salt);
        pwd.setPayWord(EncryptUtils.encrypt(pwd.getPayWord() + salt, EncryptUtils.SHA_256));
    }
}
