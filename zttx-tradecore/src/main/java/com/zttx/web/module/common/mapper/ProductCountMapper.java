/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductCount;

/**
 * 产品计数信息 持久层接口
 * <p>File：ProductCountDao.java </p>
 * <p>Title: ProductCountDao </p>
 * <p>Description:ProductCountDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductCountMapper extends GenericMapper<ProductCount>
{
    /**
     * 最前一个小时类变更过的所有产品统计信息
     *
     * @param past
     * @param now
     * @return {@link List}
     */
    List<String> getProductCountMaps(@Param("past") Long past, @Param("now") Long now);
}
