/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandesWeightInfo;

/**
 * 品牌权重信息 服务接口
 * <p>File：BrandesWeightInfoService.java </p>
 * <p>Title: BrandesWeightInfoService </p>
 * <p>Description:BrandesWeightInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
public interface BrandesWeightInfoService extends GenericServiceApi<BrandesWeightInfo>
{
    /**
     * 保存
     * @param brandesWeightInfo 对象信息
     * @throws {@link BusinessException}
     */
    void save(BrandesWeightInfo brandesWeightInfo) throws BusinessException;
    
    /**
     * 分页查询
     * @param searchBean 查询条件
     * @param page 分页条件
     * @return
     */
    PaginateResult<BrandesWeightInfo> searchByClient(BrandesWeightInfo searchBean, Pagination page);
}
