/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandAlbum;
import com.zttx.web.module.brand.entity.BrandVisual;
import com.zttx.web.module.brand.mapper.BrandAlbumMapper;
import com.zttx.web.module.brand.mapper.BrandVisualMapper;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.NetworkUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 品牌视觉信息 服务实现类
 * <p>File：BrandVisual.java </p>
 * <p>Title: BrandVisual </p>
 * <p>Description:BrandVisual </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandVisualServiceImpl extends GenericServiceApiImpl<BrandVisual>
        implements BrandVisualService
{

    @Autowired
    private BrandAlbumMapper brandAlbumMapper;

    private BrandVisualMapper brandVisualMapper;

    @Autowired(required = true)
    public BrandVisualServiceImpl(BrandVisualMapper brandVisualMapper)
    {
        super(brandVisualMapper);
        this.brandVisualMapper = brandVisualMapper;
    }

    public void save(BrandVisual brandVisual, String[] images,String[] photoNames, HttpServletRequest request)
            throws BusinessException
    {
        Short[] brandStates = {
                BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED,
                BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED};
        // 判断当前状态是不是通进行操作
        BrandVisual visualParams = new BrandVisual();
        visualParams.setBrandId(brandVisual.getBrandId());
        visualParams.setBrandsId(brandVisual.getBrandsId());
        List<BrandVisual> brandVisualList = brandVisualMapper.findByBrandIdAndBrandesId(visualParams);

        if (null != brandVisualList && !brandVisualList.isEmpty())
        {
            BrandVisual oldBrandVisual = brandVisualList.get(0);
            oldBrandVisual.setVedioFile(brandVisual.getVedioFile());
            oldBrandVisual.setThreeFile(brandVisual.getThreeFile());
            oldBrandVisual.setBrandMark(brandVisual.getBrandMark());
            if (null == oldBrandVisual.getBrandMark())
            {
                oldBrandVisual.setBrandMark("");
            }
            oldBrandVisual.setCreateIp(brandVisual.getCreateIp());
            brandVisualMapper.updateByPrimaryKey(oldBrandVisual);
        }
        else
        {

            String imagePath = null;
            if (StringUtils.isNotBlank(brandVisual.getVedioFile()))
            {
                imagePath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_LIB, brandVisual.getVedioFile(), UploadAttCateConst.BRAND_VISUAL);
                brandVisual.setVedioFile(imagePath);
            }
            if (StringUtils.isNotBlank(brandVisual.getThreeFile()))
            {
                imagePath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_LIB, brandVisual.getThreeFile(), UploadAttCateConst.BRAND_VISUAL);
                brandVisual.setThreeFile(imagePath);
            }

            brandVisual.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandVisual.setDomainName(NetworkUtils.getDoMainName());
            brandVisual.setCreateTime(CalendarUtils.getCurrentLong());
            if (null == brandVisual.getBrandMark())
            {
                brandVisual.setBrandMark("");
            }
            brandVisualMapper.insert(brandVisual);
        }
        updateImg(request, brandVisual,images,photoNames);
    }

    // 更新相册
    private void updateImg(HttpServletRequest request,BrandVisual brandVisual,String[] images,String [] photoNames)
            throws BusinessException
    {
        BrandAlbum params  = new BrandAlbum();
        params.setBrandsId(brandVisual.getBrandsId());
        params.setBrandId(brandVisual.getBrandId());

        List<BrandAlbum> brandAlbumList = brandAlbumMapper.findList(params);
        List<String> delBrandAlbumList = Lists.newArrayList();
        if (null != brandAlbumList && !brandAlbumList.isEmpty())
        {
            for (BrandAlbum album : brandAlbumList)
            {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(images, album.getImageName());
                if (size >= 0)
                {
                    images[size] = "";
                }
                else
                {
                    delBrandAlbumList.add(album.getRefrenceId());
                    FileClientUtil.deleteFile(album.getImageName());
                }
            }
        }
        if (delBrandAlbumList.size()>0) {
            brandAlbumMapper.delBatch(delBrandAlbumList);
        }
        List<BrandAlbum> newBrandAlbumList  = insertBrandAlbumList(request, brandVisual, images, photoNames);
        if (newBrandAlbumList.size()>0) {
            for (BrandAlbum ba:newBrandAlbumList)
            brandAlbumMapper.insert(ba);
        }
    }


    private List<BrandAlbum> insertBrandAlbumList(HttpServletRequest request,BrandVisual brandVisual, String[] images,String[] photoNames) throws BusinessException
    {
        List<BrandAlbum> list = new ArrayList<BrandAlbum>();
        if (ArrayUtils.isNotEmpty(images))
        {
            for (int i = 0; i < images.length; i++)
            {
                String imageName = images[i];
                if (StringUtils.isNotBlank(imageName))
                {
                    String photoName = photoNames[i];
                    BrandAlbum brandAlbum = new BrandAlbum();
                    brandAlbum.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandAlbum.setBrandId(brandVisual.getBrandId());
                    brandAlbum.setBrandsId(brandVisual.getBrandsId());
                    brandAlbum.setDomainName(NetworkUtils.getDoMainName());

                    String result =  FileClientUtil.moveImgFromTemp(ImageConst.BRAND_LIB, imageName, UploadAttCateConst.BRAND_VISUAL);

                    brandAlbum.setImageName(result);
                    brandAlbum.setPhotoName(photoName);
                    brandAlbum.setCreateIp(brandVisual.getCreateIp());
                    brandAlbum.setCreateTime(CalendarUtils.getCurrentLong());
                    list.add(brandAlbum);
                }
            }
        }
        return list;
    }

    @Override
    public BrandVisual findByBrandsIdAndBrandId(String brandId, String brandsId)
    {
        BrandVisual params  = new BrandVisual();
        params.setBrandsId(brandsId);
        params.setBrandId(brandId);
        List<BrandVisual> visualList = brandVisualMapper.findByBrandIdAndBrandesId(params);
        if (CollectionUtils.isNotEmpty(visualList)){
            return visualList.get(0);
        }
        return null;
    }
}
