/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandBuySerLog;

import java.util.List;

/**
 * 品牌商购买的服务记录 服务接口
 * <p>File：BrandBuySerLogService.java </p>
 * <p>Title: BrandBuySerLogService </p>
 * <p>Description:BrandBuySerLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandBuySerLogService extends GenericServiceApi<BrandBuySerLog>{

    /**
     * 查询
     * @param brandId
     * @param idArr
     * @return
     * @author 张昌苗
     */
    public List<BrandBuySerLog> listWithException(String brandId, String[] idArr) throws BusinessException;
}
