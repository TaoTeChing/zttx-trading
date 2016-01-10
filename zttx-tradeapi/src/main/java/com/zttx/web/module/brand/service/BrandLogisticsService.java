/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandLogistics;

/**
 * 品牌商物流公司 服务接口
 * <p>File：BrandLogisticsService.java </p>
 * <p>Title: BrandLogisticsService </p>
 * <p>Description:BrandLogisticsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandLogisticsService extends GenericServiceApi<BrandLogistics>{

    /**
     * 根据品牌商ID查找
     *
     * @param brandId
     * @return
     */
    List<BrandLogistics> listByBrandId(String brandId);
    
    /**
     * 判断是否有重名的物流公司
     * @param logisName
     * @param brandId
     * @return
     */
    Boolean isExits(String brandId, String logisName);
}
