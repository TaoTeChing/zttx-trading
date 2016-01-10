/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.JoinInfo;

/**
 * 加盟入驻信息 服务接口
 * <p>File：JoinInfoService.java </p>
 * <p>Title: JoinInfoService </p>
 * <p>Description:JoinInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface JoinInfoService extends GenericServiceApi<JoinInfo>
{
    /**
     * 通用保存
     * @param joinInfo
     * @throws BusinessException
     */
    void save(JoinInfo joinInfo) throws BusinessException;
}
