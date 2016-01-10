/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.MessageHistory;
import com.zttx.web.module.common.mapper.MessageHistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 短信发送历史信息 服务实现类
 * <p>File：MessageHistory.java </p>
 * <p>Title: MessageHistory </p>
 * <p>Description:MessageHistory </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class MessageHistoryServiceImpl extends GenericServiceApiImpl<MessageHistory> implements MessageHistoryService
{

    private MessageHistoryMapper messageHistoryMapper;

    @Autowired(required = true)
    public MessageHistoryServiceImpl(MessageHistoryMapper messageHistoryMapper)
    {
        super(messageHistoryMapper);
        this.messageHistoryMapper = messageHistoryMapper;
    }
}
