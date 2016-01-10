/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.module.brand.entity.BrandImage;

/**
 * 品牌商图片管理 服务接口
 * <p>File：BrandImageService.java </p>
 * <p>Title: BrandImageService </p>
 * <p>Description:BrandImageService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandImageService extends GenericServiceApi<BrandImage>
{
    PaginateResult<BrandImage> findByBrandId(Pagination page, String brandId);
    
    /**
     * 查询图库并分页
     * @param page Pagination
     * @param image BrandImage
     * @param deleted 是否删除
     * @return PaginateResult<BrandImage>
     */
    PaginateResult<BrandImage> search(Pagination page, BrandImage image, Boolean deleted);
    
    /**
     * 获取图片总大小
     * @param brandId 品牌商id
     * @param refrenceId 图片id
     * @return 总大小
     */
    long getFileSum(String brandId, String refrenceId);
    
    /**
     * 根据ID 和品牌商ID 加载
     * @param uuid
     * @param brandId 品牌商ID
     * @return
     */
    BrandImage load(String uuid, String brandId);
    
    String formatSize(long currentSize, String unit);
    
    String getPercent(long currentSize, long totalSize);
    
    /**
     * 清空回收站 物理删除
     * @param brandId
     * @return BrandConst
     * @author 章旭楠
     */
    BrandConst deleteRecycles(String brandId);
    
    /**
     * 根据图片名称，分类，所属品牌商查询图片
     * @param image
     * @return
     */
    PaginateResult<BrandImage> findByImage(BrandImage image);
    
    /**
     * 改变分类
     * @param oldCateId
     * @param newCateId
     * @param brandId
     * @throws BusinessException
     */
    void updateImageCate(String oldCateId, String newCateId, String brandId);
    
    /**
     * 改变分类
     * @param refrenceIds
     * @param newCateId
     * @param brandId
     */
    void updateImageCate(String[] refrenceIds, String newCateId, String brandId) throws BusinessException;
    
    /**
     * 回收站还原
     * @param refrenceId
     */
    void revert(String refrenceId);
    
    /**
     * 图片上传
     * @param file 图片
     * @return JsonMessage
     * @throws Exception
     */
    JsonMessage uploadFile(MultipartFile file) throws Exception;
    
    /**
     * 检查是否到达文件上传上限
     * @param brandId
     * @param imgId 排除 允许null
     * @param fileSize
     * @throws BusinessException
     */
    void checkFileSize(String brandId, String imgId, long fileSize) throws BusinessException;
    
    /**
     * 将文件服务器返回的结果，转成图片对象
     * @param image
     * @param resultMap
     * @throws BusinessException
     */
    void parseImage(BrandImage image, Map resultMap) throws BusinessException;
    
    /**
     * 将文件服务器返回的结果，转成图片对象
     * @param image
     * @param cateId
     * @param brandId
     * @param ipAddr
     * @param resultMap
     * @throws BusinessException
     */
    void parseImage(BrandImage image, String cateId, String brandId, Integer ipAddr, Map resultMap) throws BusinessException;
}
