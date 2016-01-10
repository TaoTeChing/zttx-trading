/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandNetwork;
/**
 * 品牌经销网络 服务接口
 * <p>File：BrandNetworkService.java </p>
 * <p>Title: BrandNetworkService </p>
 * <p>Description:BrandNetworkService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandNetworkService extends GenericServiceApi<BrandNetwork>{

    /**
     * 根据 品牌id查询 
     * @param params
     * @return
     */
    List<BrandNetwork> selectNetworkAndImgByBrandesId(String brandesId);

    /**
     * @param network
     * @param request
     */
    void save(BrandNetwork network, HttpServletRequest request) throws BusinessException;

    /**
     * @param page
     * @param brandNetwork
     * @return
     */
    PaginateResult<Map<String, Object>> getNetwortNotDealerList(
            Pagination page, BrandNetwork brandNetwork);

    /**
     * 根据主键将 brandNetwork修改为 指定 显示状态 showflag
     * @param brandNetwork
     */
    void updateShowFlag(String refrenceId,Boolean showFlag);

    /**
     * @param brandId
     * @param brandsId
     * @param dealerId
     * @param levelId
     */
    void updateNetworkLevel(String brandId, String brandsId,  String dealerId,  String levelId);

    /**
     * 查询销售网信息
     *
     * @param brandId
     * @param brandsId
     * @param dealerId
     * @param showFlag
     * @return
     */
    public BrandNetwork getBrandNetwork(String brandId, String brandsId, String dealerId, Boolean showFlag);


    /**
     * 保存经销网络
     *
     * @param request
     * @param brandNetwork
     * @throws BusinessException
     */
    public void insertBrandNetwork(HttpServletRequest request, BrandNetwork brandNetwork) throws BusinessException;
}
