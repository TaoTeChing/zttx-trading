/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.MessageHistory;

/**
 * 短信发送历史信息 持久层接口
 * <p>File：MessageHistoryDao.java </p>
 * <p>Title: MessageHistoryDao </p>
 * <p>Description:MessageHistoryDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface MessageHistoryMapper extends GenericMapper<MessageHistory>
{

}
