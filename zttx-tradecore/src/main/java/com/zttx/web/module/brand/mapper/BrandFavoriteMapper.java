/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandFavorite;
import com.zttx.web.module.brand.model.BrandFavoriteView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 品牌商收藏的经销商 持久层接口
 * <p>File：BrandFavoriteDao.java </p>
 * <p>Title: BrandFavoriteDao </p>
 * <p>Description:BrandFavoriteDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandFavoriteMapper extends GenericMapper<BrandFavorite>
{

    /**
     * 收藏的经销商列表
     *
     * @param pagination
     * @param  info
     * @return
     */
    public List<Map<String, Object>> search(@Param("page")Pagination pagination, @Param("info")BrandFavoriteView info);

    /**
     * 查询已收藏列表
     */
    List<BrandFavorite> selectBrandFavorite(@Param("brandId")String brandId,@Param("dealerId")String dealerId);

}
