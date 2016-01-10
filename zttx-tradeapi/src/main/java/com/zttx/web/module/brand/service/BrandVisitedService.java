/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandVisited;
import com.zttx.web.module.brand.model.BrandVisitedModel;

import java.util.List;
import java.util.Map;

/**
 * 品牌商浏览记录 服务接口
 * <p>File：BrandVisitedService.java </p>
 * <p>Title: BrandVisitedService </p>
 * <p>Description:BrandVisitedService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandVisitedService extends GenericServiceApi<BrandVisited>{
    /**
     * 分页查询
     * @param pagination
     * @param brandVisitedModel
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String,Object>> getBrandVisitedPage(Pagination pagination, BrandVisitedModel brandVisitedModel);

    /**
     * 我浏览过的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public PaginateResult<Map<String, Object>> search(Pagination pagination, BrandVisited info);

    /*
     * 保存浏览次数，如果存在就覆盖否则新增
     */
    public void insert(String brandId, String dealerId) throws BusinessException;

    /**
     * 查询申请列表
     *
     * @param dealerId
     * @param brandId
     * @return
     */
    public List<Map<String, Object>> getBrandApplyList(String dealerId, String brandId);
}
