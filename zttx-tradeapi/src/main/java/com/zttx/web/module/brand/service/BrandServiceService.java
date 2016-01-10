/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandService;
import com.zttx.web.module.common.model.Customer;
/**
 * 品牌商客服信息 服务接口
 * <p>File：BrandServiceService.java </p>
 * <p>Title: BrandServiceService </p>
 * <p>Description:BrandServiceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandServiceService extends GenericServiceApi<BrandService>{

	/**
	 * 根据品牌商编号获取品牌客服信息
	 * @author 陈建平
	 * @param brandId
	 * @return
	 */
    BrandService findBrandService(String brandId);
    
    /**
     * 保存客户信息
     * @author 陈建平
     * @param customer
     * @throws BusinessException
     */
    BrandService saveBrandService(BrandService brandService) throws BusinessException;
    
    /**
     * 根据客服ID修改所有该客服信息
     * @author 陈建平
     * @param brandService
     */
    void updateBrandServiceByUserId(BrandService brandService);
    
	/**
     * 保存客户信息
     * @author 陈建平
     * @param customer
     * @throws BusinessException
     */
    void save(Customer customer) throws BusinessException;
}
