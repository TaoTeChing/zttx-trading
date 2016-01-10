/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.DataDictValue;

/**
 * 数据字典值 持久层接口
 * <p>File：DataDictValueDao.java </p>
 * <p>Title: DataDictValueDao </p>
 * <p>Description:DataDictValueDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DataDictValueMapper extends GenericMapper<DataDictValue>
{
    long countBySelective(DataDictValue dataDictValue);
    
    /**
     * 根据字典编码和字典值名称查询
     * @param dictCode
     * @param dictValueName
     * @return
     * @author 李星
     */
    DataDictValue findByDictCodeAndDictValueName(@Param("dictCode") String dictCode, @Param("dictValueName") String dictValueName);
}
