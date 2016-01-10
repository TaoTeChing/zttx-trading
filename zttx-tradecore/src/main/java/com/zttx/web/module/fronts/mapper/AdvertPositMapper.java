/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.fronts.entity.AdvertPosit;

import java.util.List;

/**
 * 广告位置管理 持久层接口
 * <p>File：AdvertPositDao.java </p>
 * <p>Title: AdvertPositDao </p>
 * <p>Description:AdvertPositDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface AdvertPositMapper extends GenericMapper<AdvertPosit>
{

    /**
     * 根据广告位的KEY查询
     * @param key
     * @return
     */
    List<AdvertPosit> findAdvertPostByKey(String key);
}
