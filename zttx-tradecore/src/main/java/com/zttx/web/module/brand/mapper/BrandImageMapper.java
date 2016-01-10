/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandImage;

/**
 * 品牌商图片管理 持久层接口
 * <p>File：BrandImageDao.java </p>
 * <p>Title: BrandImageDao </p>
 * <p>Description:BrandImageDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandImageMapper extends GenericMapper<BrandImage>
{
    List<BrandImage> findByBrandId(@Param("page") Pagination page, @Param("brandId") String brandId);
    
    /**
     * 多条件查询
     * @param image
     * @return
     */
    List<BrandImage> search(BrandImage image);

    /**
     * 统计该品牌商上传图片总大小
     * @param brandId 品牌商id
     * @param refrenceId 排除自身 允许空
     * @return
     */
    long getSumImageSize(@Param("brandId")String brandId,@Param("refrenceId")String refrenceId);
    
    List<BrandImage> findByImage(BrandImage image);
    
    /**
     * 更新图片类别
     * @param oldCateId
     * @param newCateId
     * @param brandId
     */
    void updateImageCate(@Param("oldCateId") String oldCateId, @Param("newCateId") String newCateId, @Param("brandId") String brandId);
    
    /**
     * 回收站还原
     * @param refrenceId
     */
    void revert(String refrenceId);
    
    /**
     * 清空回收站
     * @param brandId
     */
    void deleteRecycles(String brandId);
}
