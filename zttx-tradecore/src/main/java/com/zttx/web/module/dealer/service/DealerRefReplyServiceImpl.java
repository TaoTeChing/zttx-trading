/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.List;

import com.zttx.web.module.dealer.model.DealerRefReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefReply;
import com.zttx.web.module.dealer.mapper.DealerRefAttachtMapper;
import com.zttx.web.module.dealer.mapper.DealerRefReplyMapper;

/**
 * 退款留言 服务实现类
 * <p>File：DealerRefReply.java </p>
 * <p>Title: DealerRefReply </p>
 * <p>Description:DealerRefReply </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerRefReplyServiceImpl extends GenericServiceApiImpl<DealerRefReply> implements DealerRefReplyService
{
    @Autowired
    private DealerRefAttachtMapper dealerRefAttachtMapper;

    private DealerRefReplyMapper dealerRefReplyMapper;

    @Autowired(required = true)
    public DealerRefReplyServiceImpl(DealerRefReplyMapper dealerRefReplyMapper)
    {
        super(dealerRefReplyMapper);
        this.dealerRefReplyMapper = dealerRefReplyMapper;
    }
    
    @Override
    public List<DealerRefReplyModel> listByRefundId(String refundId)
    {
        List<DealerRefReplyModel> list = dealerRefReplyMapper.listByRefundId(refundId);
        if (null != list && !list.isEmpty())
        {
            for (DealerRefReplyModel dealerRefReply : list)
            {
                List<DealerRefAttacht> draList = dealerRefAttachtMapper.listByRefundIdAndReplyId(refundId, dealerRefReply.getRefrenceId());
                dealerRefReply.setDraList(draList);
            }
        }
        return list;
    }
}
