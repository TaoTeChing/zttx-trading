/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.entity.BrandBuyService;

/**
 * 品牌商购买的服务 服务接口
 * <p>File：BrandBuyServiceService.java </p>
 * <p>Title: BrandBuyServiceService </p>
 * <p>Description:BrandBuyServiceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandBuyServiceService extends GenericServiceApi<BrandBuyService>{

	/**
	 * 根据品牌商编号和服务编号查询记录
	 * @author 陈建平
	 * @param brandId
	 * @param serviceId
	 * @return
	 */
    BrandBuyService findByBrandIdAndServiceId(String brandId, String serviceId);
    
	/**
	 * 保存购买日志
	 * @author 陈建平
	 * @param brandBuySerLog
	 * @return
	 * @throws BusinessException
	 */
    BrandBuyService saveByClient(BrandBuySerLog brandBuySerLog) throws BusinessException;
}
