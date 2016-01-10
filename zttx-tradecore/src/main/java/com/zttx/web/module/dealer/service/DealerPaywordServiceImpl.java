/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerPayword;
import com.zttx.web.module.dealer.mapper.DealerPaywordMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncryptUtils;

/**
 * 经销商支付密码 服务实现类
 * <p>File：DealerPayword.java </p>
 * <p>Title: DealerPayword </p>
 * <p>Description:DealerPayword </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerPaywordServiceImpl extends GenericServiceApiImpl<DealerPayword>implements DealerPaywordService
{
    private DealerPaywordMapper dealerPaywordMapper;
    
    @Autowired(required = true)
    public DealerPaywordServiceImpl(DealerPaywordMapper dealerPaywordMapper)
    {
        super(dealerPaywordMapper);
        this.dealerPaywordMapper = dealerPaywordMapper;
    }
    
    @Override
    public void updatePayPassword(DealerPayword newPwd)
    {
        encrypt(newPwd);
        String dealerId = newPwd.getRefrenceId();
        // 查找缓存
        DealerPayword oldPwd = dealerPaywordMapper.selectByPrimaryKey(dealerId);
        if (null == oldPwd)
        {
            create(newPwd);
        }
        else
        {
            update(oldPwd, newPwd);
        }
    }
    
    private static void encrypt(DealerPayword pwd)
    {
        String salt = RandomStringUtils.randomAlphabetic(11);
        pwd.setPaySalt(salt);
        pwd.setPayWord(EncryptUtils.encrypt(pwd.getPayWord() + salt, EncryptUtils.SHA_256));
    }
    
    /**
     * 初次建立支付密码
     * @param newPwd
     * @return
     * @author 夏铭
     */
    private DealerPayword create(DealerPayword newPwd)
    {
        // todo DealPaylog
        /*
         * DealPaylog changeLog = new DealPaylog();
         * changeLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
         * changeLog.setDealerId(newPwd.getDealerId());
         * changeLog.setCreateType(DealPaylog.CREATION);
         * changeLog.setCreateIp(newPwd.getCreateIp());
         * changeLog.setCreateTime(CalendarUtils.getCurrentLong());
         * changeLog.setOldSalt("");
         * changeLog.setOldWord("");
         * changeLog.setNewSalt(newPwd.getPaySalt());
         * changeLog.setNewWord(newPwd.getPayWord());
         * dealPayLogDao.insert(changeLog);
         */
        newPwd.setCreateTime(CalendarUtils.getCurrentLong());
        dealerPaywordMapper.insert(newPwd);
        return newPwd;
    }
    
    /**
     * 修改支付密码
     * @author 夏铭
     */
    private void update(DealerPayword oldPwd, DealerPayword newPwd)
    {
        // todo DealPaylog
        /*
         * DealPaylog changeLog = new DealPaylog();
         * changeLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
         * changeLog.setDealerId(newPwd.getDealerId());
         * changeLog.setCreateType(DealPaylog.MODIFICATION);
         * changeLog.setCreateIp(newPwd.getCreateIp());
         * changeLog.setCreateTime(CalendarUtils.getCurrentLong());
         * changeLog.setOldSalt(oldPwd.getPaySalt());
         * changeLog.setOldWord(oldPwd.getPayWord());
         * changeLog.setNewSalt(newPwd.getPaySalt());
         * changeLog.setNewWord(newPwd.getPayWord());
         * dealPayLogDao.insert(changeLog);
         */
        dealerPaywordMapper.updateByPrimaryKey(newPwd);
    }
}
