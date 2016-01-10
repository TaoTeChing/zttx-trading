/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightRegion;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;

import java.util.List;

/**
 * 运费区域表 服务接口
 * <p>File：BrandFreightRegionService.java </p>
 * <p>Title: BrandFreightRegionService </p>
 * <p>Description:BrandFreightRegionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandFreightRegionService extends GenericServiceApi<BrandFreightRegion>{


    public void removeByTemplateId(String templeId) throws BusinessException;

    public void saveRegionForTempleatDefault(BrandFreightTemplate template, BrandFreightDetail brandFreightDetail)  throws BusinessException;

    public void saveRegionForTempleatNotDefault(BrandFreightTemplate template,List<BrandFreightDetail> detailList)  throws BusinessException;
}
