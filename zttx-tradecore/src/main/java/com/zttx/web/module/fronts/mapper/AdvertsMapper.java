/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.fronts.entity.Adverts;

/**
 * 广告 持久层接口
 * <p>File：AdvertsDao.java </p>
 * <p>Title: AdvertsDao </p>
 * <p>Description:AdvertsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface AdvertsMapper extends GenericMapper<Adverts>
{
    /**
     * 广告信息查询（分页）
     * @param advertsModel
     * @return
     */
    List<Adverts> search(Adverts advertsModel);
    
    /**
     * 根据广告位编号取有效广告
     * @param advertId
     * @param currentTime 允许null
     * @return
     */
    List<Adverts> searchAdverts(@Param("advertId") String advertId, @Param("currentTime") Long currentTime);
}
