/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.adverti.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.adverti.entity.MeetingJoin;

/**
 * 交易会活动报名 服务接口
 * <p>File：MeetingJoinService.java </p>
 * <p>Title: MeetingJoinService </p>
 * <p>Description:MeetingJoinService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface MeetingJoinService extends GenericServiceApi<MeetingJoin> {
    /**
     * 保存活动报名
     *
     * @param entity
     */
    void save(MeetingJoin entity) throws BusinessException;
}
