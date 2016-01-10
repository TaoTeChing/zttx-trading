/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import com.zttx.sdk.annotation.DataSource;
import com.zttx.sdk.db.DataSourceEnum;
import com.zttx.web.module.fronts.entity.Adverts;
import com.zttx.web.module.fronts.mapper.AdvertsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.fronts.entity.AdvertPosit;
import com.zttx.web.module.fronts.mapper.AdvertPositMapper;

import java.util.List;

/**
 * 广告位置管理 服务实现类
 * <p>File：AdvertPosit.java </p>
 * <p>Title: AdvertPosit </p>
 * <p>Description:AdvertPosit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AdvertPositServiceImpl extends GenericServiceApiImpl<AdvertPosit> implements AdvertPositService
{
    private AdvertPositMapper advertPositMapper;
    
    @Autowired
    private AdvertsService    advertsService;
    
    @Autowired(required = true)
    public AdvertPositServiceImpl(AdvertPositMapper advertPositMapper)
    {
        super(advertPositMapper);
        this.advertPositMapper = advertPositMapper;
    }
    
    /**
     * 通用保存
     *
     * @param advertPosit
     * @throws BusinessException
     */
    @Override
    @DataSource(DataSourceEnum.MASTER)
    public void save(AdvertPosit advertPosit) throws BusinessException
    {
        if (!StringUtils.isNotBlank(advertPosit.getRefrenceId())) {// to create
            advertPosit.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            advertPosit.setCreateTime(CalendarUtils.getCurrentLong());
            advertPosit.setUpdateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(advertPosit);
        } else {// to update
            advertPosit.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(advertPosit);
        }
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public AdvertPosit findAdvertPostByKey(String key)
    {
        AdvertPosit advertPosit = null;
        List<AdvertPosit> advertPositList = advertPositMapper.findAdvertPostByKey(key);
        if (null != advertPositList && advertPositList.size() > 0)
        {
            advertPosit = advertPositList.get(0);
            if (StringUtils.isNotBlank(advertPosit.getRefrenceId()))
            {
                advertPosit.setSubList(advertsService.searchAdverts(advertPosit.getRefrenceId()));
            }
        }
        return advertPosit;
    }
}
