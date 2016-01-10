/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandRead;

/**
 * 品牌商消息阅读 持久层接口
 * <p>File：BrandReadDao.java </p>
 * <p>Title: BrandReadDao </p>
 * <p>Description:BrandReadDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandReadMapper extends GenericMapper<BrandRead>
{
    /**
     * @param msgIdList
     * @return
     */
    List<BrandRead> getBrandReadList(@Param("msgIdList") List<String> msgIdList);

    /**
     * 逻辑删除
     * @param record
     */
    void deleteSelective(BrandRead record);

    /**
     * 带条件物理删除
     * @param record
     */
    void deleteRealSelective(BrandRead record);

    /**
     * 判断与品牌商相关的已读记录是否已存在
     * @param brandId
     * @param msgId
     * @return
     */
    List selectBrandRead(@Param("brandId")String brandId,@Param("msgId") String msgId);
}
