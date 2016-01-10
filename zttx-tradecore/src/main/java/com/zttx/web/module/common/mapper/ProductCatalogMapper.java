/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductCatalog;

/**
 * 产品分类信息 持久层接口
 * <p>File：ProductCatalogDao.java </p>
 * <p>Title: ProductCatalogDao </p>
 * <p>Description:ProductCatalogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductCatalogMapper extends GenericMapper<ProductCatalog>
{
    List<ProductCatalog> getCatalogList(@Param("brandId") String brandId, @Param("brandsId") String brandsId, @Param("cateLevel") Short cateLevel);
    
    void updateCatalogDelState(@Param("idArray") String[] idArray, @Param("delFlag") Boolean delFlag);

    /**
     * 查找指定 类目下的子类目
     * @param cataId
     * @return
     */
    List<ProductCatalog> findSubCates(String cataId);
}
