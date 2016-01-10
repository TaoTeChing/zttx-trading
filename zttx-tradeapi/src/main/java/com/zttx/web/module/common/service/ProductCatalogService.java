/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.module.common.model.ProductCatalogModel;

/**
 * 产品分类信息 服务接口
 * <p>File：ProductCatalogService.java </p>
 * <p>Title: ProductCatalogService </p>
 * <p>Description:ProductCatalogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductCatalogService extends
        GenericServiceApi<ProductCatalog>
{
    List<MenuTree> getCatalogTreeList(String brandId, String brandsId);

    /**
     * 根据品牌商id， 品牌id ，分类级别 获取分类
     * @param uuid
     * @param brandsId
     * @param catelogLevel
     * @return
     */
    List<ProductCatalog> getCatalogList(String uuid, String brandsId, Short catelogLevel);
    
    
    /**
     * 根据品牌商id， 品牌id 获取分类
     * @param uuid
     * @param brandsId
     * @return
     */
    List<ProductCatalog> getCatalogList(String uuid, String brandsId);

    void insertCatalog(ProductCatalogModel productCatalog,
            HttpServletRequest request) throws BusinessException;

    /**
     * 查找指定 类目下的子类目
     * @param cataId
     * @return
     */
    List<ProductCatalog> findSubCates(String cataId);

    /**
     * 根据分类ID取所有上级类目
     * @param cataId
     * @return
     */
    List<ProductCatalog> findParentCates(String cataId);
}
