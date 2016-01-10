/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandImgcate;

/**
 * 品牌商图片分类信息 持久层接口
 * <p>File：BrandImgcateDao.java </p>
 * <p>Title: BrandImgcateDao </p>
 * <p>Description:BrandImgcateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandImgcateMapper extends GenericMapper<BrandImgcate>
{
    List<BrandImgcate> findByBrandId(String brandId);
    
    /**
     * 是否存在同名分类
     * @param brandId 品牌商ID
     * @param cateName 分类名称
     * @param cateId 分类ID(null：全部，不空：除此之外)
     * @return List size>0:存在
     * @author 章旭楠
     */
    List isExistName(@Param("brandId") String brandId, @Param("cateName") String cateName, @Param("cateId") String cateId);

    /**
     * 查找默认分类
     * @param brandId
     * @return BrandImgcate
     */
    BrandImgcate findDefaultImgCate(String brandId);

    /**
     * 删除分类的同时删除其子集(逻辑删除)
     * @param cateId
     * @param brandId
     */
    void deleteCascade(@Param("cateId")String cateId,@Param("brandId") String brandId);
}
