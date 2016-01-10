/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.brand.model.BrandCatelogModel;

/**
 * 品牌商主营品类 服务接口
 * <p>File：BrandCatelogService.java </p>
 * <p>Title: BrandCatelogService </p>
 * <p>Description:BrandCatelogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCatelogService extends GenericServiceApi<BrandCatelog>{

    /**
     * 通过BrandId获取品牌商主营品类
     * @param brandId
     * @return
     */
    List<BrandCatelogModel> getBrandCatelogByBrandId(String brandId);
    
    /**
     * 修改品牌商经营类目（会替换掉之前的所有类目）
     * @author 陈建平
     * @param brandId
     * @param dealDicIds
     */
    void updateBrandCatelogByClient(String brandId, String dealDicIds);
}
