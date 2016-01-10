/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateHeader;
import com.zttx.web.module.exhibition.entity.DecorateHeaderLog;
import com.zttx.web.module.exhibition.mapper.DecorateHeaderLogMapper;
import com.zttx.web.module.exhibition.mapper.DecorateHeaderMapper;

/**
 * 展厅头部装修 服务实现类
 * <p>File：DecorateHeader.java </p>
 * <p>Title: DecorateHeader </p>
 * <p>Description:DecorateHeader </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateHeaderServiceImpl extends GenericServiceApiImpl<DecorateHeader> implements DecorateHeaderService
{
    @Autowired
    private DecorateHeaderMapper decorateHeaderMapper;
    
    @Autowired
    private DecorateHeaderLogMapper decorateHeaderLogMapper;

    @Autowired(required = true)
    public DecorateHeaderServiceImpl(DecorateHeaderMapper decorateHeaderMapper)
    {
        super(decorateHeaderMapper);
        this.decorateHeaderMapper = decorateHeaderMapper;
    }

    private static final Logger logger = Logger.getLogger(DecorateHeaderServiceImpl.class);

    @Override
    public DecorateHeader findByBrandIdAndBrandsId(String brandId, String brandsId)
    {
         List<DecorateHeader> list=decorateHeaderMapper.findByBrandIdAndBrandsId(brandId,brandsId);
         if(list!=null&&list.size()>0){
             return list.get(0);
         }
         return null;
    }

    @Override
    public DecorateHeader findLatestByBrandId(String brandId, String brandsId)
    {
        DecorateHeader header = null;
        try
        {
            List<DecorateHeaderLog> logList = decorateHeaderLogMapper
                    .findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isNotEmpty(logList))
            {
                DecorateHeaderLog log = logList.get(0);
                header = new DecorateHeader();
                BeanUtils.copyProperties(header, log);
            }
            else
            {
                List<DecorateHeader> headerList = decorateHeaderMapper
                        .findByBrandIdAndBrandsId(brandId, brandsId);
                if (CollectionUtils.isNotEmpty(headerList))
                {
                    header = headerList.get(0);
                }
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return header;
    }

    @Override
    public void saveHeaderLog(DecorateHeaderLog decorateHeader, String brandId,
            String brandsId, HttpServletRequest request) throws BusinessException,
            InvocationTargetException, IllegalAccessException
    {
        Short showCate = decorateHeader.getShowCate();
        if (null == showCate) { throw new BusinessException(DecorateHeaderEnumConst.SHOW_CATE_CANNOT_NULL); }
        switch (showCate)
        {
            case DecorateHeaderConst.SHOWCATE_DEFAULT:
                saveOrUpdateDefualtHeaderLog(decorateHeader, brandId, brandsId, request);
                break;
            case DecorateHeaderConst.SHOWCATE_CUSTOM:
                saveOrUpdateCustomHeaderLog(decorateHeader.getHeaderText(), brandId, brandsId);
                break;
            case DecorateHeaderConst.SHOWCATE_MARKET:
                updateMarketHeader(decorateHeader);
                break;
            default:
                throw new BusinessException(CommonConst.FAIL);
        }
    }

    /**
     * 更新默认格式的头部信息
     * @param newHeader
     * @param brandId
     * @author 吴万杰
     */
    private void saveOrUpdateDefualtHeaderLog(DecorateHeaderLog newHeader, String brandId, String brandsId, HttpServletRequest request) throws BusinessException,
            InvocationTargetException, IllegalAccessException
    {
        DecorateHeaderLog  dbHeader = null;
        List<DecorateHeaderLog> dbHeaderList = decorateHeaderLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(dbHeaderList)){
            dbHeader = dbHeaderList.get(0);
        }

        String[] newUrls = new String[]{newHeader.getLogoName(), newHeader.getOutBackUrl(), newHeader.getInBackUrl()};
        if (dbHeader == null)
        {
            dbHeader = new DecorateHeaderLog();

            DecorateHeader header =null;
            List<DecorateHeader> headerList = decorateHeaderMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isNotEmpty(headerList)){
                header = headerList.get(0);
            }

            if (header == null)
            {
                dbHeader.setBrandId(brandId);
                dbHeader.setBrandsId(brandsId);
            }
            else
            {
                BeanUtils.copyProperties(dbHeader, header);
                dbHeader.setRefrenceId(null);
            }
        }
        try
        {
            for (int i = 0, len = newUrls.length; i < len; i++)
            {
                if (StringUtils.isNotBlank(newUrls[i]))
                {
//                    String tempUrl = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.BRAND_IMG_PATH, newUrls[i], null);
                    String tempUrl = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, newUrls[i], UploadAttCateConst.BRANDS_LOGO);
                    newUrls[i] = tempUrl;
                }
            }
        }
        catch (FileOperateException e)
        {
            logger.error("FileOperateException:编辑店铺头部保存图片失败");
            throw new BusinessException(CommonConst.IMG_SAVE_FAULT);
        }
        Boolean showName = newHeader.getShowName();
        Boolean showLogo = newHeader.getShowLogo();
        if (null == showName)
        {
            showName = false;
        }
        if (null == showLogo)
        {
            showLogo = false;
        }
        String conName = newHeader.getComName();
        if (StringUtils.isBlank(conName)) { throw new BusinessException(DecorateHeaderEnumConst.COMPANY_NAME_NOT_EXIST); }
        dbHeader.setShowCate((short)DecorateHeaderConst.SHOWCATE_DEFAULT);
        dbHeader.setComName(conName);
        dbHeader.setShowName(showName);
        dbHeader.setLogoName(newUrls[0]);
        dbHeader.setShowLogo(showLogo);
        dbHeader.setMainDeal(newHeader.getMainDeal());
        dbHeader.setNameSize(newHeader.getNameSize());
        dbHeader.setNameColor(newHeader.getNameColor());
        dbHeader.setOutBackUrl(newUrls[1]);
        dbHeader.setInBackUrl(newUrls[2]);
        dbHeader.setUpdateTime(CalendarUtils.getCurrentLong());
        dbHeader.setDomainName(NetworkUtils.getDoMainName());

        if (StringUtils.isNotBlank(dbHeader.getRefrenceId()))
        {
            dbHeader.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateHeaderLogMapper.updateByPrimaryKey(dbHeader);
        }
        else
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            dbHeader.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dbHeader.setUpdateTime(currentTime);
            dbHeader.setCreateTime(currentTime);
            dbHeader.setDelFlag(false);
            decorateHeaderLogMapper.insert(dbHeader);
        }

    }

    /**
     * 更新自定义的头部信息
     * @param headerText
     * @param brandId
     * @author 吴万杰
     */
    private void saveOrUpdateCustomHeaderLog(String headerText, String brandId, String brandsId) throws InvocationTargetException, IllegalAccessException
    {
        DecorateHeaderLog  dbHeader = null;
        List<DecorateHeaderLog> dbHeaderList = decorateHeaderLogMapper.findByBrandIdAndBrandsId(brandId, brandsId);
        if (CollectionUtils.isNotEmpty(dbHeaderList)){
            dbHeader = dbHeaderList.get(0);
        }
        if (dbHeader == null)
        {
            dbHeader = new DecorateHeaderLog();
            DecorateHeader header =null;
            List<DecorateHeader> headerList = decorateHeaderMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isNotEmpty(headerList)){
                header = headerList.get(0);
            }
            if (header == null)
            {
                dbHeader.setBrandId(brandId);
                dbHeader.setBrandsId(brandsId);
                dbHeader.setShowLogo(false);
                dbHeader.setShowName(false);
                dbHeader.setComName("");
            }
            else
            {
                BeanUtils.copyProperties(dbHeader, header);
                dbHeader.setRefrenceId(null);
            }
        }
        dbHeader.setShowCate((short)DecorateHeaderConst.SHOWCATE_CUSTOM);
        dbHeader.setHeaderText(headerText);
        dbHeader.setUpdateTime(CalendarUtils.getCurrentLong());
        if (StringUtils.isNotBlank(dbHeader.getRefrenceId()))
        {
            dbHeader.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateHeaderLogMapper.updateByPrimaryKey(dbHeader);
        }
        else
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            dbHeader.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dbHeader.setUpdateTime(currentTime);
            dbHeader.setCreateTime(currentTime);
            dbHeader.setDelFlag(false);
            decorateHeaderLogMapper.insert(dbHeader);
        }
    }

    /**
     * 更新招牌超市类型的头部信息
     * @param header
     * @author 吴万杰
     */
    private void updateMarketHeader(DecorateHeaderLog header)
    {
        // todo 预留
    }
}
