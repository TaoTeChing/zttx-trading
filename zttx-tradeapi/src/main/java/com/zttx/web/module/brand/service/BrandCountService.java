/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandCount;

/**
 * 品牌商计数信息 服务接口
 * <p>File：BrandCountService.java </p>
 * <p>Title: BrandCountService </p>
 * <p>Description:BrandCountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCountService extends GenericServiceApi<BrandCount>
{
    /**
     * 修改品牌统计数量
     * @param brandId
     * @param countType
     * @return
     */
    BrandCount modifyBrandCount(String brandId, Integer[] countType) throws BusinessException;

    /*
     * 获取剩余查看数量
     */
    public Integer getViewDealerTotal(String brandId) ;
}
