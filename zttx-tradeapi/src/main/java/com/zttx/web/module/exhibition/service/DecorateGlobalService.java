/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.exhibition.entity.DecorateGlobal;
import com.zttx.web.module.exhibition.entity.DecorateGlobalLog;
/**
 * 展厅装修全局配置 服务接口
 * <p>File：DecorateGlobalService.java </p>
 * <p>Title: DecorateGlobalService </p>
 * <p>Description:DecorateGlobalService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DecorateGlobalService extends GenericServiceApi<DecorateGlobal>{
    DecorateGlobal findByBrandIdAndBrandsId(String brandId,String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    DecorateGlobal findLatestByBrandId(
            String brandId, String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @param skinName
     */
    void saveLogTheme(String brandId, String brandsId, String skinName) throws IllegalAccessException, InvocationTargetException;

    /**
     * @param brandId
     * @param brandsId
     * @param global
     * @param request
     * @return
     */
    DecorateGlobalLog saveOrUpdateLog(String brandId, String brandsId,
            DecorateGlobalLog global, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException;
}
