/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.exhibition.entity.DecorateHeader;
import com.zttx.web.module.exhibition.entity.DecorateHeaderLog;

import java.lang.reflect.InvocationTargetException;

/**
 * 展厅头部装修 服务接口
 * <p>File：DecorateHeaderService.java </p>
 * <p>Title: DecorateHeaderService </p>
 * <p>Description:DecorateHeaderService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DecorateHeaderService extends GenericServiceApi<DecorateHeader>{
    DecorateHeader findByBrandIdAndBrandsId(String brandId,String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    DecorateHeader findLatestByBrandId(String brandId, String brandsId);

    /**
     * @param header
     * @param brandId
     * @param brandsId
     * @param request
     */
    void saveHeaderLog(DecorateHeaderLog header, String brandId,
            String brandsId, HttpServletRequest request) throws BusinessException,
            InvocationTargetException, IllegalAccessException;

}
