/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerFeed;

/**
 * 经销商反馈信息 持久层接口
 * <p>File：DealerFeedDao.java </p>
 * <p>Title: DealerFeedDao </p>
 * <p>Description:DealerFeedDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerFeedMapper extends GenericMapper<DealerFeed>
{
    /**
     * 根据品牌商ID和品牌ID获取反馈信息
     * @param pagination
     * @param brandId
     * @param brandsId
     * @return
     */
    List<DealerFeed> findByBrandIdAndBrandsId(@Param("page") Pagination pagination, @Param("brandId") String brandId, @Param("brandsId") String brandsId);
}
