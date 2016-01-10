/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.NetworkUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.brand.entity.BrandPhoto;
import com.zttx.web.module.brand.entity.BrandRoom;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandRoomMapper;
import com.zttx.web.module.brand.model.BrandRoomModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;

/**
 * 品牌商展厅信息 服务实现类
 * <p>File：BrandRoom.java </p>
 * <p>Title: BrandRoom </p>
 * <p>Description:BrandRoom </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandRoomServiceImpl extends GenericServiceApiImpl<BrandRoom> implements BrandRoomService
{
	@Autowired
	private BrandesInfoService brandesInfoService;
	
	@Autowired
	private BrandPhotoService  brandPhotoService;
	
    private BrandRoomMapper brandRoomMapper;

    @Autowired(required = true)
    public BrandRoomServiceImpl(BrandRoomMapper brandRoomMapper)
    {
        super(brandRoomMapper);
        this.brandRoomMapper = brandRoomMapper;
    }

    @Override
    public BrandRoom findByBrandId(String brandId)
    {
        List<BrandRoom> list= brandRoomMapper.findByBrandId(brandId);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void saveOrUpdate(HttpServletRequest request, BrandRoomModel brandRoom, String brandId) throws BusinessException
    {
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED, BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED};
        // 判断当前状态是不是通进行操作
        brandesInfoService.validatorState(brandId, brandRoom.getRefrenceId(), brandStates);
        BrandRoom oldBrandRoom = brandRoomMapper.selectByPrimaryKey(brandRoom.getRefrenceId());
        Long time = CalendarUtils.getCurrentLong();
        if (null == oldBrandRoom)
        {
            brandRoom.setCreateTime(time);
            brandRoom.setUpdateTime(time);
            brandRoom.setBrandId(brandId);
            brandRoom.setDomainName(NetworkUtils.getDoMainName());
            String logoImage = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandRoom.getLogoImage(), UploadAttCateConst.BRANDS_LOGO);
            brandRoom.setLogoImage(logoImage);
            insert(brandRoom);
        }
        else
        {
            if (!brandRoom.getLogoImage().equals(oldBrandRoom.getLogoImage()))
            {
            	String logoImage = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandRoom.getLogoImage(), UploadAttCateConst.BRANDS_LOGO);
                oldBrandRoom.setLogoImage(logoImage);
                brandRoom.setLogoImage(oldBrandRoom.getLogoImage());
                oldBrandRoom.setLogoPhoto(brandRoom.getLogoPhoto());
            }
            oldBrandRoom.setRoomName(brandRoom.getRoomName());
            oldBrandRoom.setBrandMark(brandRoom.getBrandMark());
            oldBrandRoom.setUserId(brandRoom.getUserId());
            oldBrandRoom.setUpdateTime(time);
            updateByPrimaryKey(oldBrandRoom);
        }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandRoom.getRefrenceId());
        if (null != brandesInfo)
        {
            moveImgFromTemp(brandRoom);
            updatePhoto(brandRoom, brandesInfo);
            if (StringUtils.isNotBlank(brandRoom.getMainProLogo()))
            {
                if (!brandRoom.getMainProLogo().equals(brandesInfo.getProLogo()))
                {
                    brandesInfo.setProLogo(brandRoom.getMainProLogo());
                }
            }
            else
            {
                brandesInfo.setProLogo(brandRoom.getPhotoImgPaths()[brandRoom.getPhotoImgPaths().length - 1]);
            }
            brandesInfo.setBrandSubMark(brandRoom.getBrandSubMark());
            // 2014-6-9 上午10:48:37 张昌苗
            // 曾经理说修改logo时，把brandesInfo也改了，只引用图片地址，并刷新solr
            brandesInfo.setBrandLogo(brandRoom.getLogoImage());
            brandesInfo.setRecommendImage(brandRoom.getRecommendImagePath());
            brandesInfoService.updateByPrimaryKey(brandesInfo);
            // 写入品牌形象照片
            List<BrandPhoto> brandPhotoList = parseBrandPhotoList(brandRoom, brandesInfo);
            if(null != brandPhotoList){
            	for(BrandPhoto brandPhoto :brandPhotoList){
            		brandPhotoService.insert(brandPhoto);
            	}
            }
            // 修改搜索引擎
            //BrandInfo brandInfo = brandInfoCache.getBrandInfoById(brandesInfo.getBrandId());
            //brandesInfoCache.updateSolrData(brandesInfo, brandInfo, true);
        }
    }
    
    /**
     * 修改品牌形象照片
     *
     * @param brandRoom
     * @param info
     */
    private void updatePhoto(BrandRoomModel brandRoom, BrandesInfo info)
    {
        List<BrandPhoto> brandPhotoList = brandPhotoService.findByBrandIdAndBrandsId(info.getBrandId(), info.getRefrenceId(), BrandConstant.DEL_STATE_NONE_DELETED);
        if (null != brandPhotoList && !brandPhotoList.isEmpty())
        {
            for (BrandPhoto photo : brandPhotoList)
            {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(brandRoom.getPhotoImgPaths(), photo.getImageName());
                if (size >= 0)
                {
                    brandRoom.getPhotoImgPaths()[size] = "";
                    // 获取数据库最先上传的图片
                    brandRoom.setMainProLogo(photo.getImageName());
                }
                else
                {
                    photo.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                    brandPhotoService.updateByPrimaryKey(photo);
                }
            }
        }
    }
    
    private void moveImgFromTemp(BrandRoomModel brandRoom) throws BusinessException
    {
        // 该方法只是实现本地文件移到的功能
        String resultPath = "";
        // 移动图片（品牌形象照片）
        for (int i = 0; i < brandRoom.getPhotoImgPaths().length; i++)
        {
            String imgPath = brandRoom.getPhotoImgPaths()[i];
            if (StringUtils.isNotBlank(imgPath))
            {
            	resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, imgPath, UploadAttCateConst.PRODUCT_GRAPH);
                brandRoom.getPhotoImgPaths()[i] = resultPath;
            }
        }
        // 移动推荐图片
        resultPath = brandRoom.getRecommendImagePath();
        if (StringUtils.isNotBlank(resultPath))
        {
        	resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandRoom.getRecommendImagePath(), UploadAttCateConst.BRANDS_LOGO);
            brandRoom.setRecommendImagePath(resultPath);
        }
    }
    
    // 转化成BrandPhoto对象
    private List<BrandPhoto> parseBrandPhotoList(BrandRoomModel brandRoom, BrandesInfo brandesInfo)
    {
        List<BrandPhoto> list = new ArrayList<BrandPhoto>();
        for (int i = 0; i < brandRoom.getPhotoImgPaths().length; i++)
        {
            String photoImgPath = brandRoom.getPhotoImgPaths()[i];
            if (StringUtils.isNotBlank(photoImgPath))
            {
                String photoImgName = brandRoom.getPhotoImgNames()[i];
                BrandPhoto brandPhoto = new BrandPhoto();
                brandPhoto.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandPhoto.setBrandId(brandesInfo.getBrandId());
                brandPhoto.setBrandesId(brandesInfo.getRefrenceId());
                brandPhoto.setDomainName(NetworkUtils.getDoMainName());
                brandPhoto.setPhotoName(photoImgName);
                brandPhoto.setImageName(photoImgPath);
                brandPhoto.setCreateTime(CalendarUtils.getCurrentLong());
                brandPhoto.setCreateIP(brandRoom.getIp());
                brandPhoto.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                list.add(brandPhoto);
            }
        }
        return list;
    }
}
