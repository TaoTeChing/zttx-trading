/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandFreightSettings;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.mapper.BrandFreightSettingsMapper;
import com.zttx.web.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运费设置表 服务实现类
 * <p>File：BrandFreightSettings.java </p>
 * <p>Title: BrandFreightSettings </p>
 * <p>Description:BrandFreightSettings </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandFreightSettingsServiceImpl extends GenericServiceApiImpl<BrandFreightSettings> implements BrandFreightSettingsService
{

    private BrandFreightSettingsMapper brandFreightSettingsMapper;

    @Autowired(required = true)
    public BrandFreightSettingsServiceImpl(BrandFreightSettingsMapper brandFreightSettingsMapper)
    {
        super(brandFreightSettingsMapper);
        this.brandFreightSettingsMapper = brandFreightSettingsMapper;
    }

    @Override
    public void removeByTemplateId(String templateId) {
        brandFreightSettingsMapper.removeByTemplateId(templateId);
    }

    @Override
    public BrandFreightSettings insertSettingForTempleat(BrandFreightTemplate template, Integer carryType){
        BrandFreightSettings settings = new BrandFreightSettings();
        settings.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        settings.setTemplateId(template.getRefrenceId());
        settings.setCarryType(carryType);
        settings.setCarryTypeName(BrandConstant.BrandFreight.CARRY_TYPE_NAME[carryType]);
        settings.setCreateTime(CalendarUtils.getCurrentLong());
        settings.setUpdateTime(CalendarUtils.getCurrentLong());
        settings.setDelFlag(false);
        brandFreightSettingsMapper.insert(settings);

        return settings;
    }

    @Override
    public List<BrandFreightSettings> listByTemplateId(String templateId) {
        if(null!=templateId)
        {
            return brandFreightSettingsMapper.listByTemplateId(templateId);
        }
        return null;
    }
}
