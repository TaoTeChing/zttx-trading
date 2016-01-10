/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandCrm;
import com.zttx.web.module.brand.model.BrandCrmModel;

/**
 * 品牌商更新信息表CRM 服务接口
 * <p>File：BrandCrmService.java </p>
 * <p>Title: BrandCrmService </p>
 * <p>Description:BrandCrmService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCrmService extends GenericServiceApi<BrandCrm>{

    BrandCrm save(String s, short brandContact);
    
    /**
     * 通过接口保存品牌商更新信息
     * @author 陈建平
     * @param brandCrmModel
     * @throws BusinessException
     */
    void insertByClient(BrandCrmModel brandCrmModel) throws BusinessException;
    
    /**
     * 通过接口获取品牌商更新信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<BrandCrm> selectBrandCrmByClient(BrandCrm filter);
}
