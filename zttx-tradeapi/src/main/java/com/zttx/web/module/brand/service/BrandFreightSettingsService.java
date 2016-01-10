/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandFreightSettings;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;

import java.util.List;

/**
 * 运费设置表 服务接口
 * <p>File：BrandFreightSettingsService.java </p>
 * <p>Title: BrandFreightSettingsService </p>
 * <p>Description:BrandFreightSettingsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandFreightSettingsService extends GenericServiceApi<BrandFreightSettings>{

    public void removeByTemplateId(String templateId);

    public BrandFreightSettings insertSettingForTempleat(BrandFreightTemplate template, Integer carryType);

    /**
     * 根据模版id ,获取模版的详细信息
     * @param templateId
     * @return
     */
    List<BrandFreightSettings> listByTemplateId(String templateId);
}
