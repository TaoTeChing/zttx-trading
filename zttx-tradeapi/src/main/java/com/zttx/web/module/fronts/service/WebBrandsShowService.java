/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.fronts.entity.WebBrandsShow;

/**
 * 首页感兴趣品牌展示 服务接口
 * <p>File：WebBrandsShowService.java </p>
 * <p>Title: WebBrandsShowService </p>
 * <p>Description:WebBrandsShowService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface WebBrandsShowService extends GenericServiceApi<WebBrandsShow>
{
    /**
     * 保存（接口调用）
     * @param webBrandsShow
     * @throws BusinessException
     */
    void saveByClient(WebBrandsShow webBrandsShow) throws BusinessException;
    
    /**
     * 分页查询（接口调用）
     * @param searchBean
     * @param pagination
     * @return
     */
    PaginateResult<WebBrandsShow> searchByClient(WebBrandsShow searchBean, Pagination pagination);
    
    /**
     * 是否存在相同
     * @param refrenceId
     * @param brandsId
     * @param showType
     * @return
     */
    Boolean isBrandsIdExist(String refrenceId, String brandsId, Short showType);
    
    /**
     * 首页查询品牌
     * @param pagination 分页
     * @param showType 1:首页感兴趣的品牌  2:入驻加盟中品牌推荐 3：新闻资讯中知名品牌
     * @return List
     */
    List<BrandsInfoModel> indexList(Pagination pagination, Short showType);
}
