/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandRoom;
import com.zttx.web.module.brand.model.BrandRoomModel;
/**
 * 品牌商展厅信息 服务接口
 * <p>File：BrandRoomService.java </p>
 * <p>Title: BrandRoomService </p>
 * <p>Description:BrandRoomService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandRoomService extends GenericServiceApi<BrandRoom>{
    
    /**
     * 根据品牌商id查询 BrandRoom
     * @param brandId
     * @return
     */
    BrandRoom findByBrandId(String brandId);

    /**
     * 保存或更新  
     * @param request
     * @param brandRoom
     * @param brandId
     */
    void saveOrUpdate(HttpServletRequest request, BrandRoomModel brandRoom, String brandId) throws BusinessException;
    
}
