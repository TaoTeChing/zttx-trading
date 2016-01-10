/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandLicening;
/**
 * 品牌授权证书 服务接口
 * <p>File：BrandLiceningService.java </p>
 * <p>Title: BrandLiceningService </p>
 * <p>Description:BrandLiceningService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandLiceningService extends GenericServiceApi<BrandLicening>{
	
	/**
	 * 根据品牌商编号和品牌编号批量修改删除标志为删除状态
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 */
    void updateDelState(String brandId, String brandsId);
    
    /**
     * 根据品牌商编号、品牌编号、删除标志
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    List<BrandLicening> findByBrandsId(String brandId, String brandsId, Boolean delState);
}
