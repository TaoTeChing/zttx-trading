/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandStore;

/**
 * 品牌商品牌仓库信息 服务接口
 * <p>File：BrandStoreService.java </p>
 * <p>Title: BrandStoreService </p>
 * <p>Description:BrandStoreService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandStoreService extends GenericServiceApi<BrandStore>{

    /**
     * 根据品牌编号获取品牌仓库
     *
     * @param brandsId
     * @return
     */
    List<BrandStore> listBrandStore(String brandsId);
    
    /**
     * 根据品牌商，品牌获取品牌仓库
     * @param brandsId
     * @param brandId
     * @return
     */
    List<BrandStore> listBrandStore(String brandsId, String brandId);
}
