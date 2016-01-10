/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandNews;

/**
 * 品牌商新闻资讯 持久层接口
 * <p>File：BrandNewsDao.java </p>
 * <p>Title: BrandNewsDao </p>
 * <p>Description:BrandNewsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandNewsMapper extends GenericMapper<BrandNews>
{

    /**
     * 新闻点击数目自增1
     * @param refrenceId
     */
    void addHitNum(String refrenceId);

    /**
     * 获取品牌商资讯
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String,Object>> listBrandNews(BrandNews filter);
}
