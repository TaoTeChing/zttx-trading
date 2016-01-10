/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandDoccate;
import com.zttx.web.module.brand.model.BrandDoccateModel;
import com.zttx.web.module.brand.model.BrandJoinFilter;

/**
 * 品牌商文档类别信息 服务接口
 * <p>File：BrandDoccateService.java </p>
 * <p>Title: BrandDoccateService </p>
 * <p>Description:BrandDoccateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandDoccateService extends GenericServiceApi<BrandDoccate>{
	
	/**
     * 根据品牌编号获取所有资料分类
     * @param brandId       品牌商编号
     * @param brandsId      品牌编号
     * @return
     */
    List<BrandDoccate> getBrandDoccateList(String brandId, String brandsId);
    
    /**
     * 保存分类
     * @author 陈建平
     * @param brandDoccateModel
     * @throws BusinessException
     */
    void insertBrandDoccate(BrandDoccateModel brandDoccateModel) throws BusinessException;
    
    /**
     * 获取可以查看资料的终端商
     * @author 陈建平
     * @param filter
     * @param brandId
     * @return
     */
    List<Map<String, Object>> getDocJoinedDealers(BrandJoinFilter filter, String brandId);
}
