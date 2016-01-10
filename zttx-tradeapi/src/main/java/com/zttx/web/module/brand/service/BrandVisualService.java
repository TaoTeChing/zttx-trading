/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandVisual;
/**
 * 品牌视觉信息 服务接口
 * <p>File：BrandVisualService.java </p>
 * <p>Title: BrandVisualService </p>
 * <p>Description:BrandVisualService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandVisualService extends GenericServiceApi<BrandVisual>{

    /**
     * 根据品牌商id和品牌id查询 
     * @param brandId
     * @param id
     * @return
     */
    BrandVisual findByBrandsIdAndBrandId(String brandId, String id);
    
   /**
    * 保存或更新 
    * @param brandVisual
    * @param request
    * @throws BusinessException
    */
    void save(BrandVisual brandVisual, String[] images,String[] photoNames,HttpServletRequest request) throws BusinessException;

}
