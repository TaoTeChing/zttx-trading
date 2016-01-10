/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.utils.CalendarUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.exhibition.entity.DecorateGlobal;
import com.zttx.web.module.exhibition.entity.DecorateGlobalLog;
import com.zttx.web.module.exhibition.mapper.DecorateGlobalLogMapper;
import com.zttx.web.module.exhibition.mapper.DecorateGlobalMapper;

/**
 * 展厅装修全局配置 服务实现类
 * <p>File：DecorateGlobal.java </p>
 * <p>Title: DecorateGlobal </p>
 * <p>Description:DecorateGlobal </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateGlobalServiceImpl extends GenericServiceApiImpl<DecorateGlobal> implements DecorateGlobalService
{
    @Autowired
    private DecorateGlobalMapper decorateGlobalMapper;

    @Autowired
    private DecorateGlobalLogMapper decorateGlobalLogMapper;
    
    @Autowired(required = true)
    public DecorateGlobalServiceImpl(DecorateGlobalMapper decorateGlobalMapper)
    {
        super(decorateGlobalMapper);
        this.decorateGlobalMapper = decorateGlobalMapper;
    }

    @Override
    public DecorateGlobal findByBrandIdAndBrandsId(String brandId, String brandsId)
    {
         List<DecorateGlobal> list=decorateGlobalMapper.findByBrandIdAndBrandsId(brandId,brandsId);
         if(list!=null&&list.size()>0){
             return list.get(0);
         }
         return null;
    }

    @Override
    public DecorateGlobal findLatestByBrandId(
            String brandId, String brandsId)
    {
        DecorateGlobal global  = new DecorateGlobal();
        global.setBrandId(brandId);
        global.setBrandsId(brandsId);
        List<DecorateGlobal> globalList = decorateGlobalMapper.findList(global);
        if (CollectionUtils.isNotEmpty(globalList)){
            return globalList.get(0);
        }
        return null;
    }

    @Override
    public void saveLogTheme(String brandId, String brandsId, String skinName) throws IllegalAccessException, InvocationTargetException
    {
        List<DecorateGlobalLog> dbGlobalList = decorateGlobalLogMapper.findLogByBrandId(brandId, brandsId);
        DecorateGlobalLog dbGlobal = null;
        if (CollectionUtils.isEmpty(dbGlobalList))
        {
            dbGlobal = new DecorateGlobalLog();
            List<DecorateGlobal> globalList = decorateGlobalMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isEmpty(globalList))
            {
                dbGlobal.setBrandId(brandId);
                dbGlobal.setBrandsId(brandsId);
            }
            else
            {
                DecorateGlobal global = globalList.get(0);
                BeanUtils.copyProperties(dbGlobal, global);
                dbGlobal.setRefrenceId(null);
            }
        }else{
            dbGlobal = dbGlobalList.get(0);
        }
        dbGlobal.setSkinName(skinName);
        if (StringUtils.isBlank(dbGlobal.getRefrenceId()))
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            dbGlobal.setRefrenceId(SerialnoUtils.buildIdWorkderPrimaryKey());
            dbGlobal.setCreateTime(currentTime);
            dbGlobal.setUpdateTime(currentTime);
            dbGlobal.setDelFlag(false);
            decorateGlobalLogMapper.insert(dbGlobal);
        }
        else
        {
            dbGlobal.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateGlobalLogMapper.updateByPrimaryKey(dbGlobal);
        }
    }

    @Override
    public DecorateGlobalLog saveOrUpdateLog(String brandId, String brandsId,
            DecorateGlobalLog global, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException
    {
        DecorateGlobalLog dbGlobal = null;
        List<DecorateGlobalLog> dbGlobalList = decorateGlobalLogMapper.findLogByBrandId(brandId, brandsId);
        if (CollectionUtils.isEmpty(dbGlobalList))
        {
            dbGlobal = new DecorateGlobalLog();
            List<DecorateGlobal> globalList = decorateGlobalMapper.findByBrandIdAndBrandsId(brandId, brandsId);
            if (CollectionUtils.isEmpty(globalList))
            {
                dbGlobal.setBrandId(brandId);
                dbGlobal.setBrandsId(brandsId);
            }
            else
            {
                DecorateGlobal temp = globalList.get(0);
                BeanUtils.copyProperties(dbGlobal, temp);
                dbGlobal.setRefrenceId(null);
            }
        }else{
            dbGlobal = dbGlobalList.get(0);
        }

        dbGlobal.setFontCcolor(global.getFontCcolor());
        dbGlobal.setUrlFontColor(global.getUrlFontColor());
        dbGlobal.setUrlChangeColor(global.getUrlChangeColor());
        dbGlobal.setBackColor(global.getBackColor());
        dbGlobal.setShowBackColor(global.getShowBackColor());
        String backUrl = global.getBackUrl();
        if (StringUtils.isNotBlank(backUrl))
        {
//                backUrl = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.BRAND_IMG_PATH, backUrl, null);
                dbGlobal.setBackUrl(backUrl);
        }
        dbGlobal.setShowBackUrl(global.getShowBackUrl());
        dbGlobal.setBackRepeat(global.getBackRepeat());
        dbGlobal.setBackPosition(global.getBackPosition());
        if (StringUtils.isBlank(dbGlobal.getRefrenceId()))
        {
            Long currentTime = CalendarUtils.getCurrentLong();
            dbGlobal.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dbGlobal.setCreateTime(currentTime);
            dbGlobal.setUpdateTime(currentTime);
            dbGlobal.setDelFlag(false);
            decorateGlobalLogMapper.insert(dbGlobal);
        }
        else
        {
            global.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateGlobalLogMapper.updateByPrimaryKey(dbGlobal);
        }
        return dbGlobal;
    }
}
