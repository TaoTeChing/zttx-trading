/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandRoom;

/**
 * 品牌商展厅信息 持久层接口
 * <p>File：BrandRoomDao.java </p>
 * <p>Title: BrandRoomDao </p>
 * <p>Description:BrandRoomDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandRoomMapper extends GenericMapper<BrandRoom>
{
    /**
     * 根据品牌获取展厅
     */
    List<BrandRoom> findByBrandId(String brandId);
}
