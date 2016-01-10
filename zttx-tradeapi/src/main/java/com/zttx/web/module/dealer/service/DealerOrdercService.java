/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrderc;
import com.zttx.web.module.dealer.model.ProductFilter;

import java.util.List;
import java.util.Map;

/**
 * 经销商产品进货计数 服务接口
 * <p>File：DealerOrdercService.java </p>
 * <p>Title: DealerOrdercService </p>
 * <p>Description:DealerOrdercService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerOrdercService extends GenericServiceApi<DealerOrderc>{


    /**
     * 分页查询 经销商的 我的常进款式
     * @param pagination
     * @param filter
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String,Object>> selectOrdercProductPage(Pagination pagination, ProductFilter filter) throws BusinessException;
    /**
     *  经销商的 我的常进款式 品牌类目
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectOrdercCata(String dealerId);

    /**
     *  经销商 我的常进款式 批量删除/单一删除
     * @param dealerOrdercsId
     * @return
     */
    int batchRemoveOrdercProduct(List<String> dealerOrdercsId);

    /**
     * 统计进货款数量
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    public Long countProCate(String dealerId, String brandId,String brandsId);

    /**
     * 统计经常进货款数量
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    public Long countFreProCate(String dealerId, String brandId, String brandsId);


    /**
     * 统计进货款产品id
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    public List<String> getProCateList(String dealerId, String brandId, String brandsId);

    /**
     * 统计经常进货款产品id
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    public List<String> getFreProCateList(String dealerId, String brandId, String brandsId);

    /**
     * 授权产品库
     * @param dealerId
     * @param pagination
     * @param filter
     * @return
     */
    public PaginateResult<Map<String, Object>> findOrdercProductsByDealerId(String dealerId, Pagination pagination, ProductFilter filter);
}
