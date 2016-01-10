/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.fronts.entity.Adverts;
import com.zttx.web.module.fronts.mapper.AdvertsMapper;

/**
 * 广告 服务实现类
 * <p>File：Adverts.java </p>
 * <p>Title: Adverts </p>
 * <p>Description:Adverts </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AdvertsServiceImpl extends GenericServiceApiImpl<Adverts> implements AdvertsService
{
    private AdvertsMapper advertsMapper;
    
    @Autowired(required = true)
    public AdvertsServiceImpl(AdvertsMapper advertsMapper)
    {
        super(advertsMapper);
        this.advertsMapper = advertsMapper;
    }
    
    /**
     * 分页查询广告信息
     *
     * @param advertsModel
     * @param pagination
     * @return
     */
    @Override
    public PaginateResult<Adverts> search(Adverts advertsModel, Pagination pagination)
    {
        advertsModel.setPage(pagination);
        advertsModel.setCurrentTime(CalendarUtils.getCurrentLong());
        List<Adverts> advertsList = advertsMapper.search(advertsModel);
        return new PaginateResult<>(pagination, advertsList);
    }
    
    @Override
    public List<Adverts> searchAdverts(String advertId)
    {
        return advertsMapper.searchAdverts(advertId, CalendarUtils.getCurrentTime());
    }
    
    /**
     * 通用保存
     *
     * @param adverts
     */
    @Override
    public void save(Adverts adverts)
    {
        if (StringUtils.isNotBlank(adverts.getRefrenceId()))
        // to update
        {
            adverts.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(adverts);
        }
        else
        // to create
        {
            adverts.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            adverts.setCreateTime(CalendarUtils.getCurrentLong());
            adverts.setUpdateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(adverts);
        }
    }
}
