/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandImgcate;
import com.zttx.web.module.common.model.MenuTree;

/**
 * 品牌商图片分类信息 服务接口
 * <p>File：BrandImgcateService.java </p>
 * <p>Title: BrandImgcateService </p>
 * <p>Description:BrandImgcateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandImgcateService extends GenericServiceApi<BrandImgcate>
{
    List<MenuTree> getCateMenuTree(String brandId);
    
    /**
     * 批量添加或者更新图库分类
     * @param cateNames
     * @param uuids
     * @param brandId
     * @param levels
     * @throws BusinessException
     */
    void saveOrUpdate(String[] cateNames, String[] uuids, String brandId, String[] levels) throws BusinessException;
    
    /**
     * 是否存在同名分类
     * @param brandId 品牌商ID
     * @param cateName 分类名称
     * @param cateId 分类ID(null：全部，不空：除此之外)
     * @return Boolean false：不存在，true：存在
     * @author 章旭楠
     */
    Boolean isExistName(String brandId, String cateName, String cateId);
    
    /**
     * 批量删除图库分类
     * @param ids 要删除的ID 数组
     * @param brandId 品牌商ID
     * @author 章旭楠
     */
    void deleteBatch(String[] ids, String brandId) throws BusinessException;
    
    /**
     * 删除分类的同时删除其子集(逻辑删除)
     * @param cateId
     * @param brandId
     */
    void deleteCascade(String cateId, String brandId);
    
    /**
     * 创建默认分类
     * @param brandId
     * @return not null
     */
    BrandImgcate createDefaultImgCate(String brandId);
    
    /**
     * 查找默认分类
     * @param brandId
     * @return BrandImgcate
     */
    BrandImgcate findDefaultImgCate(String brandId);
}
