/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandRecruit;
/**
 * 品牌招募书 服务接口
 * <p>File：BrandRecruitService.java </p>
 * <p>Title: BrandRecruitService </p>
 * <p>Description:BrandRecruitService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandRecruitService extends GenericServiceApi<BrandRecruit>{
    /**
     * 根据 品牌商id和品牌id查询
     * @param brandId
     * @param brandesId
     * @return
     */
    BrandRecruit findByBrandIdAndBrandesid(String brandId,String brandesId);

    /**
     * 保存或更新品牌招募书, 调用前请判断该品牌商是否拥有该品牌
     * @author 陈建平
     * @param brandRecruit
     * @throws BusinessException
     */
    void saveOrUpdate(BrandRecruit brandRecruit) throws BusinessException;

}
