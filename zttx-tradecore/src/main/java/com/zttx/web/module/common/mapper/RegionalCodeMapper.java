/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.RegionalCode;

/**
 * 地区编码与常规描述的转换表 持久层接口
 * <p>File：RegionalCodeDao.java </p>
 * <p>Title: RegionalCodeDao </p>
 * <p>Description:RegionalCodeDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RegionalCodeMapper extends GenericMapper<RegionalCode>
{
    /**
     * 根据编号 、 名称统计数量
     * @param refrenceId
     * @param areaNos
     * @param areaName
     * @return
     */
    int countBy(@Param("refrenceId") String refrenceId, @Param("areaNos") String areaNos, @Param("areaName") String areaName);
    
    /**
     * 根据主键或者区域编号或者区域名称返回区域对象
     *
     * @param refrenceId
     * @param areaNos
     * @param areaName
     * @return
     */
    RegionalCode selectRegionalCode(@Param("refrenceId") String refrenceId, @Param("areaNos") String areaNos, @Param("areaName") String areaName);
}
