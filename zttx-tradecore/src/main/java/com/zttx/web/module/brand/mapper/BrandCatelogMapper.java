/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandCatelog;

import java.util.List;

/**
 * 品牌商主营品类 持久层接口
 * <p>File：BrandCatelogDao.java </p>
 * <p>Title: BrandCatelogDao </p>
 * <p>Description:BrandCatelogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandCatelogMapper extends GenericMapper<BrandCatelog>
{
    /**
     * 得到品牌商下的所有 BrandCatelog
     *
     * @param brandId
     * @return
     */
    List<BrandCatelog> selectBrandCatelogsByBrandId(String brandId);

    /**
     * 删除品牌商下的所有 BrandCatelog
     * @param brandId
     * @return
     */
    boolean deleteBrandCatelogsByBrandId(String brandId);

    /**
     * 通过品牌商ID来查询所有的品类编号
     * @param brandId
     * @return
     */
    List<Integer> fingBrandCateLogNo(String brandId);
}
