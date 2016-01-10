/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.DataDict;

/**
 * 数据字典 持久层接口
 * <p>File：DataDictDao.java </p>
 * <p>Title: DataDictDao </p>
 * <p>Description:DataDictDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DataDictMapper extends GenericMapper<DataDict>
{
    List<DataDict> searchByClient(DataDict searchBean);
    
    /**
     * 根据dictCode 统计
     * @param dictCode 代码
     * @param refrenceId 主键
     * @return 数量
     * @author 章旭楠
     */
    long countByDictCode(@Param("dictCode") String dictCode, @Param("refrenceId") String refrenceId);
    
    /**
     * 根据dictCode 查询
     * @param dictCode 代码
     * @return DataDict
     */
    DataDict findByDictCode(String dictCode);
}
