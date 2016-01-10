/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.exhibition.entity.DecorateHeaderLog;

/**
 * 展厅头部装修 持久层接口
 * <p>File：DecorateHeaderLogDao.java </p>
 * <p>Title: DecorateHeaderLogDao </p>
 * <p>Description:DecorateHeaderLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateHeaderLogMapper extends GenericMapper<DecorateHeaderLog>
{

    /**
     * 根据品牌id 品牌商id查询
     * @param brandId
     * @param brandsId
     * @return
     */
    List<DecorateHeaderLog> findByBrandIdAndBrandsId(@Param("brandId")String brandId, @Param("brandsId")String brandsId);

}
