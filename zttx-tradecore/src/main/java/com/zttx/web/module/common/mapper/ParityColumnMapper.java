/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ParityColumn;

/**
 * 比价栏目表 持久层接口
 * <p>File：ParityColumnDao.java </p>
 * <p>Title: ParityColumnDao </p>
 * <p>Description:ParityColumnDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ParityColumnMapper extends GenericMapper<ParityColumn>
{
    /**
     * 根据名称查找比价列
     * @param name
     * @return
     */
    ParityColumn findByNameAndProductId(@Param("name")String name,@Param("productId")String productId);
    /**
     * 根据产品id查找比价列
     * @param productId
     * @return
     */
    List<ParityColumn> selectAllByProductId(String productId);
}
