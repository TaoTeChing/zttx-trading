/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.DecorateConfigConst;
import com.zttx.web.module.brand.entity.BrandRoom;
import com.zttx.web.module.brand.mapper.BrandRoomMapper;
import com.zttx.web.module.exhibition.entity.DecorateConfig;
import com.zttx.web.module.exhibition.entity.DecorateConfigLog;
import com.zttx.web.module.exhibition.mapper.DecorateConfigLogMapper;
import com.zttx.web.module.exhibition.mapper.DecorateConfigMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 展厅自定义模块配置 服务实现类
 * <p>File：DecorateConfig.java </p>
 * <p>Title: DecorateConfig </p>
 * <p>Description:DecorateConfig </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DecorateConfigServiceImpl extends GenericServiceApiImpl<DecorateConfig> implements DecorateConfigService
{
    @Autowired
    private DecorateConfigLogMapper decorateConfigLogMapper;

    @Autowired
    private DecorateConfigMapper decorateConfigMapper;

    @Autowired(required = true)
    public DecorateConfigServiceImpl(DecorateConfigMapper decorateConfigMapper)
    {
        super(decorateConfigMapper);
        this.decorateConfigMapper = decorateConfigMapper;
    }

    @Autowired
    private BrandRoomMapper brandRoomMapper; 
    
    @Override
    public List<DecorateConfigLog> findConfigLogs(String brandId,
            String brandsId, Short tagId, Boolean delState)
    {
        List<DecorateConfigLog> list = decorateConfigLogMapper.findConfigLogs(brandId, brandsId, tagId, delState);
        if (list != null && !list.isEmpty())
        {
            for (DecorateConfigLog config : list)
            {
                String text = config.getShowText();
                if (StringUtils.isNotBlank(text))
                {
                    config.setShowTextUnescape(HtmlUtils.htmlUnescape(text));
                }
            }
        }
        return list;
    }

    @Override
    public DecorateConfigLog findBrandConfigLog(String brandId, String brandsId)
    {
        Short tagId = DecorateConfigConst.TAGID_LEFT;
        Short configType = DecorateConfigConst.CONFIG_TYPE_FIXED;
        List<DecorateConfigLog> list = decorateConfigLogMapper.findConfigLogsByConfigType(brandId, brandsId, tagId, configType);
        DecorateConfigLog log = null;
        if (list != null && !list.isEmpty())
        {
            log = list.get(0);
            log.setShowTextUnescape(HtmlUtils.htmlUnescape(log.getShowText()));
        }
        return log;
    }

    @Override
    public DecorateConfigLog insertInitLog(String brandId, String brandsId)
    {
        DecorateConfigLog log = new DecorateConfigLog();
        
        BrandRoom room = brandRoomMapper.selectByPrimaryKey(brandsId);
        String brandMark = null;
        if (room!=null){
            brandMark = room.getBrandMark();
            log.setShowText(brandMark);
        }
        log.setCreateTime(CalendarUtils.getCurrentLong());
        log.setBrandId(brandId);
        log.setBrandsId(brandsId);
        log.setTitle("品牌介绍");
        log.setShowType(DecorateConfigConst.SHOW_TYPE_HTML);
        log.setShowTitle(true);
        log.setShowFlag(true);
        log.setShowOrder((short) 1);
        log.setConfigType(DecorateConfigConst.CONFIG_TYPE_FIXED);
        log.setTagId(DecorateConfigConst.TAGID_LEFT);
        log.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        log.setCreateTime(CalendarUtils.getCurrentLong());
        log.setUpdateTime(CalendarUtils.getCurrentLong());
        decorateConfigLogMapper.insert(log);
        log.setShowTextUnescape(HtmlUtils.htmlUnescape(brandMark));
        return log;
    }

    @Override
    public void updateBrandConfigLogState(String brandId, String brandsId,
            Boolean delState)
    {
        DecorateConfigLog log = findBrandConfigLog(brandId, brandsId);
        if (log != null)
        {
            log.setDelFlag(delState);
            decorateConfigLogMapper.updateByPrimaryKeySelective(log);
        }
    }

    @Override
    public void deleteConfigLog(String brandId, String brandsId, String configLogId)
    {
        DecorateConfigLog log = decorateConfigLogMapper.selectByPrimaryKey(configLogId);
        
        if (log != null && brandId.equals(log.getBrandId()) && brandsId.equals(log.getBrandsId()))
        {
            switch (log.getConfigType())
            {
                case DecorateConfigConst.CONFIG_TYPE_FIXED:
                    log.setDelFlag(Boolean.TRUE);
                    decorateConfigLogMapper.updateByPrimaryKeySelective(log);
                    break;
                case DecorateConfigConst.CONFIG_TYPE_UNFIXED:
                    decorateConfigLogMapper.delete(configLogId);
            }
        }
    }

    @Override
    public void updateConfigLogShowOrder(String brandId, String brandsId,
            List<String> preIds)
    {
        if (preIds != null)
        {
            short order = 1;
            for (String id : preIds)
            {
                DecorateConfigLog log = new  DecorateConfigLog();
                log.setRefrenceId(id);
                log.setShowOrder(order);
                decorateConfigLogMapper.updateByPrimaryKeySelective(log);
                order++;
            }
        }
    }

    @Override
    public DecorateConfigLog saveConfigLog(DecorateConfigLog log,
            List<String> preIds, List<String> nextIds)
    {
        String logId = log.getRefrenceId();
        DecorateConfigLog configLog = null;
        if (StringUtils.isNotBlank(logId))
        {
            configLog = decorateConfigLogMapper.selectByPrimaryKey(logId);
        }
        if (null == configLog)
        {
            configLog = new DecorateConfigLog();
            configLog.setBrandId(log.getBrandId());
            configLog.setBrandsId(log.getBrandsId());
            configLog.setShowType(DecorateConfigConst.SHOW_TYPE_HTML);
            configLog.setShowTitle(true);
            configLog.setShowFlag(true);
            configLog.setConfigType(DecorateConfigConst.CONFIG_TYPE_UNFIXED);
            configLog.setTagId(DecorateConfigConst.TAGID_LEFT);
            configLog.setCreateTime(CalendarUtils.getCurrentLong());
            configLog.setUpdateTime(CalendarUtils.getCurrentLong());
        }
        Boolean showTitle = log.getShowTitle();
        if (null == showTitle)
        {
            showTitle = false;
        }
        configLog.setShowOrder(log.getShowOrder());
        configLog.setTitle(log.getTitle());
        configLog.setShowTitle(showTitle);
        configLog.setShowText(log.getShowText());
        
        if (StringUtils.isNotBlank(configLog.getRefrenceId()))
        {
            configLog.setUpdateTime(CalendarUtils.getCurrentLong());
            decorateConfigLogMapper.updateByPrimaryKey(configLog);
        }
        else
        {
            configLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            decorateConfigLogMapper.insert(configLog);
        }
        
        List<String> idList = new ArrayList<String>();
        if (preIds != null)
        {
            idList.addAll(preIds);
        }
        idList.add(configLog.getRefrenceId());
        if (nextIds != null)
        {
            idList.addAll(nextIds);
        }
        updateConfigLogShowOrder(configLog.getBrandId(), configLog.getBrandsId(), idList);
        return configLog;
    }
}
