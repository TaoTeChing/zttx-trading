/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandImage;
import com.zttx.web.module.brand.entity.BrandImgcate;
import com.zttx.web.module.brand.mapper.BrandImageMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;

/**
 * 品牌商图片管理 服务实现类
 * <p>File：BrandImage.java </p>
 * <p>Title: BrandImage </p>
 * <p>Description:BrandImage </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandImageServiceImpl extends GenericServiceApiImpl<BrandImage> implements BrandImageService
{
    private BrandImageMapper    brandImageMapper;
    
    @Autowired
    private BrandImgcateService brandImgcateService;
    
    @Autowired(required = true)
    public BrandImageServiceImpl(BrandImageMapper brandImageMapper)
    {
        super(brandImageMapper);
        this.brandImageMapper = brandImageMapper;
    }
    
    @Override
    public PaginateResult<BrandImage> findByBrandId(Pagination page, String brandId)
    {
        List<BrandImage> imageList = brandImageMapper.findByBrandId(page, brandId);
        PaginateResult<BrandImage> result = new PaginateResult<>(page, imageList);
        return result;
    }
    
    /**
     * 查询图库并分页
     *
     * @param page    Pagination
     * @param image   BrandImage
     * @param deleted 是否删除
     * @return PaginateResult<BrandImage>
     */
    @Override
    public PaginateResult<BrandImage> search(Pagination page, BrandImage image, Boolean deleted)
    {
        image.setPage(page);
        image.setDelFlag(deleted);
        List<BrandImage> brandImages = brandImageMapper.search(image);
        return new PaginateResult<>(image.getPage(), brandImages);
    }
    
    @Override
    public long getFileSum(String brandId, String refrenceId)
    {
        return brandImageMapper.getSumImageSize(brandId, refrenceId);
    }
    
    /**
     * 根据ID 和品牌商ID 加载
     *
     * @param uuid
     * @param brandId 品牌商ID
     * @return
     */
    @Override
    public BrandImage load(String uuid, String brandId)
    {
        return brandImageMapper.selectByPrimaryKey(uuid);
    }
    
    /**
     * 清空回收站 物理删除
     *
     * @param brandId
     * @return BrandConst
     * @author 章旭楠
     */
    @Override
    public BrandConst deleteRecycles(String brandId)
    {
        brandImageMapper.deleteRecycles(brandId);
        return BrandConst.SUCCESS;
    }
    
    private String getCateIdBy(String brandId, String cateId)
    {
        BrandImgcate cate = brandImgcateService.selectByPrimaryKey(cateId);
        if (cate == null) cate = brandImgcateService.findDefaultImgCate(brandId);
        // 分类ID 为空 为当前品牌商 增加一个默认图库分类
        if (null == cate) cate = brandImgcateService.createDefaultImgCate(brandId);
        return cate == null ? cateId : cate.getRefrenceId();
    }
    
    /**
     * 计算文件占百分比
     * @param size 文件大小
     * @param total 总容量
     * @return 百分百
     * @author 罗盛平
     */
    @Override
    public String getPercent(long size, long total)
    {
        DecimalFormat df = new DecimalFormat("0.0%");
        float per = ((float) size) / total;
        return df.format(per);
    }
    
    @Override
    public String formatSize(long fileByteSize, String unit)
    {
        double size = (double) fileByteSize;
        long kb = 1024;
        long mb = (kb * 1024);
        long gb = (mb * 1024);
        if (size < kb)
        {
            return String.format("%.0f B", size);
        }
        else if ("KB".equals(unit) || size < mb)
        {
            return String.format("%.2f KB", size / kb);// 保留两位小数
        }
        else if ("MB".equals(unit) || size < gb)
        {
            return String.format("%.2f MB", size / mb);
        }
        else
        {
            return String.format("%.2f GB", size / gb);
        }
    }
    
    @Override
    public PaginateResult<BrandImage> findByImage(BrandImage image)
    {
        List<BrandImage> imageList = brandImageMapper.findByImage(image);
        PaginateResult<BrandImage> result = new PaginateResult<BrandImage>(image.getPage(), imageList);
        return result;
    }
    
    /**
     * 改变分类
     *
     * @param oldCateId
     * @param newCateId
     * @param brandId
     */
    @Override
    public void updateImageCate(String oldCateId, String newCateId, String brandId)
    {
        this.brandImageMapper.updateImageCate(oldCateId, newCateId, brandId);
    }
    
    /**
     * 改变分类
     *
     * @param refrenceIds
     * @param newCateId
     * @param brandId
     */
    @Override
    public void updateImageCate(String[] refrenceIds, String newCateId, String brandId) throws BusinessException
    {
        BrandImgcate cate = this.brandImgcateService.selectByPrimaryKey(newCateId);
        if (null == cate) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        for (String refrenceId : refrenceIds)
        {
            BrandImage brandImage = this.brandImageMapper.selectByPrimaryKey(refrenceId);
            if (null == brandImage || cate.getRefrenceId().equals(brandImage.getCateId()))
            {
                continue;
            }
            brandImage.setCateId(newCateId);
            this.brandImageMapper.updateByPrimaryKeySelective(brandImage);
        }
    }
    
    /**
     * 回收站还原
     *
     * @param refrenceId 主键
     */
    @Override
    public void revert(String refrenceId)
    {
        brandImageMapper.revert(refrenceId);
    }
    
    @Override
    public JsonMessage uploadFile(MultipartFile file) throws Exception
    {
        Map<String, Object> params = Maps.newHashMap();
        params.put("_savePath", ImageConst.BRAND_LIB);
        params.put("_fileSize", FileSizeEmun.F3M.getCode().toString());
        return FileClientUtil.getJsonMessage(params, "/client/upload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
    }
    
    @Override
    public void checkFileSize(String brandId, String imgId, long fileSize) throws BusinessException
    {
        Long currentSize = this.getFileSum(brandId, imgId) * 1024;
        Long totalSize = ApplicationConst.BRAND_PIC_TOTAL_SIZE;
        if ((currentSize + fileSize) > totalSize) { throw new BusinessException(CommonConst.FILESIZE_IS_TOO_LARGE); }
    }
    
    @Override
    public void parseImage(BrandImage image, Map resultMap) throws BusinessException
    {
        image.setUpdateTime(CalendarUtils.getCurrentLong());
        image.setPhotoName(MapUtils.getString(resultMap, "fileName", ""));
        image.setImageName(MapUtils.getString(resultMap, "urlPath", ""));
        image.setImageSize(MapUtils.getInteger(resultMap, "sizeKb", 0));
    }
    
    @Override
    public void parseImage(BrandImage image, String cateId, String brandId, Integer ipAddr, Map resultMap) throws BusinessException
    {
        image.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        image.setBrandId(brandId);
        image.setCateId(getCateIdBy(brandId, cateId));// cateId 有可能需要获取默认类型
        image.setDomainName(NetworkUtils.getDoMainName());
        image.setCreateTime(CalendarUtils.getCurrentLong());
        image.setCreateIp(ipAddr);
        parseImage(image, resultMap);
    }
}
