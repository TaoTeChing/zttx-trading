/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.exhibition.entity.DecorateMenuLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 展厅装修菜单历史记录 持久层接口
 * <p>File：DecorateMenuLogDao.java </p>
 * <p>Title: DecorateMenuLogDao </p>
 * <p>Description:DecorateMenuLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateMenuLogMapper extends GenericMapper<DecorateMenuLog>
{

    List<DecorateMenuLog> findByBrandIdAndBrandsId(@Param("brandId")String brandId, @Param("brandsId")String brandsId);
    
}
