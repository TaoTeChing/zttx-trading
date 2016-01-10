/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandTemplate;
/**
 * 品牌预订模板 服务接口
 * <p>File：BrandTemplateService.java </p>
 * <p>Title: BrandTemplateService </p>
 * <p>Description:BrandTemplateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandTemplateService extends GenericServiceApi<BrandTemplate>{
    List<BrandTemplate> listBrandTemplate(String brandId,String brandsId);
}
