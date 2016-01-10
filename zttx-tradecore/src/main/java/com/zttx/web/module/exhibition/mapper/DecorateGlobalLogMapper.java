/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.exhibition.entity.DecorateGlobalLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 展厅装修全局配置历史记录 持久层接口
 * <p>File：DecorateGlobalLogDao.java </p>
 * <p>Title: DecorateGlobalLogDao </p>
 * <p>Description:DecorateGlobalLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateGlobalLogMapper extends GenericMapper<DecorateGlobalLog>
{

    /**
     * 根据品牌商id 品牌id查找
     * @param brandId
     * @param brandsId
     * @return
     */
    List<DecorateGlobalLog> findLogByBrandId(@Param("brandId")String brandId, @Param("brandsId")String brandsId);

}
