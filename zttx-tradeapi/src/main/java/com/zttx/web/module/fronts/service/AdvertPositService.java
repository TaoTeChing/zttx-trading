/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.AdvertPosit;

/**
 * 广告位置管理 服务接口
 * <p>File：AdvertPositService.java </p>
 * <p>Title: AdvertPositService </p>
 * <p>Description:AdvertPositService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface AdvertPositService extends GenericServiceApi<AdvertPosit>
{
    /**
     * 通用保存
     * @param advertPosit
     * @throws BusinessException
     */
    void save(AdvertPosit advertPosit) throws BusinessException;

    /**
     * 根据广告位的KEY查询
     * @param key
     * @return
     */
    AdvertPosit findAdvertPostByKey(String key);
}
