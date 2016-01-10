/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefReply;
import com.zttx.web.module.dealer.mapper.DealerRefAttachtMapper;
import com.zttx.web.module.dealer.mapper.DealerRefReplyMapper;
import com.zttx.web.module.dealer.model.DealerRefReplyModel;

/**
 * 退款附件 服务实现类
 * <p>File：DealerRefAttacht.java </p>
 * <p>Title: DealerRefAttacht </p>
 * <p>Description:DealerRefAttacht </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerRefAttachtServiceImpl extends GenericServiceApiImpl<DealerRefAttacht> implements DealerRefAttachtService
{
    @Autowired
    private DealerRefReplyMapper   dealerRefReplyMapper;
    
    private DealerRefAttachtMapper dealerRefAttachtMapper;
    
    @Autowired(required = true)
    public DealerRefAttachtServiceImpl(DealerRefAttachtMapper dealerRefAttachtMapper)
    {
        super(dealerRefAttachtMapper);
        this.dealerRefAttachtMapper = dealerRefAttachtMapper;
    }
    
    @Override
    public List<DealerRefAttacht> listByRefundId(String refundId)
    {
        List<DealerRefReplyModel> list = dealerRefReplyMapper.listByRefundId(refundId);
        if (null != list && !list.isEmpty())
        {
            DealerRefReply dealerRefReply = list.get(list.size() - 1);
            return dealerRefAttachtMapper.listByRefundId(dealerRefReply.getRefundId());
        }
        return null;
    }
    
    @Override
    public List<DealerRefAttacht> newListBydomainNames(String[] attachtNames, String domainName)
    {
        List<DealerRefAttacht> dealerRefAttachts = Lists.newArrayList();
        if (null != attachtNames && attachtNames.length > 0)
        {
            for (String attachtName : attachtNames)
            {
                DealerRefAttacht dealerRefAttacht = new DealerRefAttacht();
                dealerRefAttacht.setAttachtName(attachtName);
                dealerRefAttacht.setDomainName(domainName);
                dealerRefAttachts.add(dealerRefAttacht);
            }
        }
        return dealerRefAttachts;
    }

    @Override
    public void insertList(List<DealerRefAttacht> dealerRefAttachts, String refundId, String userId, String userName, String replyId) {
        if (null != dealerRefAttachts && !dealerRefAttachts.isEmpty())
        {
            for (DealerRefAttacht dealerRefAttacht : dealerRefAttachts)
            {
                dealerRefAttacht.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                dealerRefAttacht.setCreateTime(CalendarUtils.getCurrentLong());
                dealerRefAttacht.setRefundId(refundId);
                dealerRefAttacht.setUserId(userId);
                dealerRefAttacht.setUserName(userName);
                dealerRefAttacht.setReplyId(replyId);
            }
            dealerRefAttachtMapper.insertBatch(dealerRefAttachts);
        }
    }
}
