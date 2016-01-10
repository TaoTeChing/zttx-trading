/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandNews;
import com.zttx.web.module.brand.model.BrandNewsModel;
/**
 * 品牌商新闻资讯 服务接口
 * <p>File：BrandNewsService.java </p>
 * <p>Title: BrandNewsService </p>
 * <p>Description:BrandNewsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandNewsService extends GenericServiceApi<BrandNews>{

    /**
     * 添加新闻点击次数
     * @param news
     */
    void addHitNum(String refrenceId);
    
    /**
     * 保存品牌资讯
     * @author 陈建平
     * @param brandNewsModel
     * @return
     */
    BrandNews insertBrandNews(BrandNewsModel brandNewsModel);
    
    /**
     * 获取品牌商资讯
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String,Object>> listBrandNews(BrandNews filter);
}
