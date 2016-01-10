/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.exhibition.entity.DecorateConfigLog;

/**
 * 展厅自定义模块配置 持久层接口
 * <p>File：DecorateConfigLogDao.java </p>
 * <p>Title: DecorateConfigLogDao </p>
 * <p>Description:DecorateConfigLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateConfigLogMapper extends GenericMapper<DecorateConfigLog>
{
    /**
     * @param brandId
     * @param brandsId
     * @param tagId
     * @param delState
     * @return
     */
    List<DecorateConfigLog> findConfigLogs(@Param("brandId")String brandId, @Param("brandsId")String brandsId,
            @Param("tagId")Short tagId, @Param("delFlag")Boolean delFlag);
    
    
    List<DecorateConfigLog> findConfigLogsByConfigType(@Param("brandId")String brandId, @Param("brandsId")String brandsId,
            @Param("tagId")Short tagId, @Param("configType")Short configType);
    
}
