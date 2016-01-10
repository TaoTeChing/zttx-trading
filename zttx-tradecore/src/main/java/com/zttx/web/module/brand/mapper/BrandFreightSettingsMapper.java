/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandFreightSettings;

import java.util.List;

/**
 * 运费设置表 持久层接口
 * <p>File：BrandFreightSettingsDao.java </p>
 * <p>Title: BrandFreightSettingsDao </p>
 * <p>Description:BrandFreightSettingsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@MyBatisDao
public interface BrandFreightSettingsMapper extends GenericMapper<BrandFreightSettings>
{

    public List<BrandFreightSettings> listByTemplateId(String templateId);

    public void removeByTemplateId(String templateId);

    public List<BrandFreightSettings> getCarryType(String templateId);

}
