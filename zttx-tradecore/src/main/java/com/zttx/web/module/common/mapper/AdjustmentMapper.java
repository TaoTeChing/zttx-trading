/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.Adjustment;

/**
 * 调价信息主表 持久层接口
 * <p>File：AdjustmentDao.java </p>
 * <p>Title: AdjustmentDao </p>
 * <p>Description:AdjustmentDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface AdjustmentMapper extends GenericMapper<Adjustment>
{
    /**
     * 统计数量
     * @param dealerId
     * @param brandId
     * @param brandAdjustId
     * @return
     */
    int countAdjustment(@Param("dealerId")String dealerId,@Param("brandId") String brandId,@Param("brandAdjustId") String brandAdjustId);
}
