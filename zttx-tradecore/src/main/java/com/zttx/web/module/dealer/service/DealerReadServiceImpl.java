/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.dealer.entity.DealerRead;
import com.zttx.web.module.dealer.mapper.DealerReadMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商消息阅读 服务实现类
 * <p>File：DealerRead.java </p>
 * <p>Title: DealerRead </p>
 * <p>Description:DealerRead </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerReadServiceImpl extends GenericServiceApiImpl<DealerRead> implements DealerReadService
{
    private DealerReadMapper dealerReadMapper;
    
    @Autowired(required = true)
    public DealerReadServiceImpl(DealerReadMapper dealerReadMapper)
    {
        super(dealerReadMapper);
        this.dealerReadMapper = dealerReadMapper;
    }
    
    /**
     * 阅读所有消息
     *
     * @param dealerId 经销商ID
     */
    @Override
    public void saveRead(String dealerId)
    {
        List<String> msgIdList = dealerReadMapper.getUnReadMsgIdList(dealerId);
        List<DealerRead> list = this.createDealerReadList(dealerId, msgIdList);
        dealerReadMapper.insertBatch(list);
    }
    
    private List<DealerRead> createDealerReadList(String dealerId, List<String> msgIdList)
    {
        List<DealerRead> list = Lists.newArrayList();
        for (int i = 0; i < msgIdList.size(); i++)
        {
            list.add(this.createDealerRead(dealerId, msgIdList.get(i)));
        }
        return list;
    }
    
    /**
     * 阅读多条消息
     *
     * @param dealerId  经销商ID
     * @param msgIdList 消息ID列表
     */
    @Override
    public void saveRead(String dealerId, List<String> msgIdList)
    {
        msgIdList = this.getUnReadMsgIdList(dealerId, msgIdList);
        if (msgIdList.isEmpty()) { return; }
        List<DealerRead> list = this.createDealerReadList(dealerId, msgIdList);
        this.dealerReadMapper.insertBatch(list);
    }
    
    /**
     * 阅读一条消息
     *
     * @param dealerId 经销商ID
     * @param msgId    消息ID
     */
    @Override
    public void saveRead(String dealerId, String msgId)
    {
        msgId = getUnReadMsgIdList(dealerId, msgId);
        if (StringUtils.isBlank(msgId)) { return; }
        DealerRead dealerRead = createDealerRead(dealerId, msgId);
        dealerReadMapper.insertSelective(dealerRead);
    }
    
    /**
     * 判断与经销商相关的已读记录是否已存在
     *
     * @param dealerId 经销商ID
     * @param msgId    消息ID
     * @return Boolean true：存在， false：不存在
     * @author 章旭楠
     */
    @Override
    public Boolean isExistDealerRead(String dealerId, String msgId)
    {
        return this.dealerReadMapper.isExistDealerRead(dealerId, msgId).size() > 0;
    }
    
    private List<String> getUnReadMsgIdList(String dealerId, List<String> msgIdList)
    {
        List<String> newMsgIdList = Lists.newArrayList();
        for (int i = 0; i < msgIdList.size(); i++)
        {
            String msgId = msgIdList.get(i);
            if (!this.isExistDealerRead(dealerId, msgId))
            {
                newMsgIdList.add(msgId);
            }
        }
        return newMsgIdList;
    }
    
    private String getUnReadMsgIdList(String dealerId, String msgId)
    {
        if (!this.isExistDealerRead(dealerId, msgId)) { return msgId; }
        return null;
    }
    
    private DealerRead createDealerRead(String dealerId, String msgId)
    {
        DealerRead dealerRead = new DealerRead();
        dealerRead.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerRead.setDealerId(dealerId);
        dealerRead.setMsgId(msgId);
        dealerRead.setReadTime(CalendarUtils.getCurrentLong());
        dealerRead.setDelFlag(false);
        return dealerRead;
    }
}
