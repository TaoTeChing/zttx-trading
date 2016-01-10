/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.model.DealerCollectModel;

import java.util.Map;

/**
 * 经销商品牌收藏夹 服务接口
 * <p>File：DealerCollectService.java </p>
 * <p>Title: DealerCollectService </p>
 * <p>Description:DealerCollectService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerCollectService extends GenericServiceApi<DealerCollect>{
    /**
     * 分页获取经销商的品牌收藏夹信息
     * @param pagination
     * @param dealerCollectModel
     * @return
     */
    PaginateResult<Map<String,Object>> getDealerCollectsBy(Pagination pagination, DealerCollectModel dealerCollectModel);
    /**
     * 根据经销商id，品牌id销商的品牌收藏夹信息获取获取
     * @param dealerId
     * @param brandId
     * @param delState
     * @return
     */
    Long findDealerCollect(String dealerId, String brandId, Boolean delState);
    
    /**
     * 保存终端商对 品牌的收藏
     * @param dealerId
     * @param brandesId
     */
    void saveCollect(String dealerId, String brandesId)  throws  BusinessException;

    /**
     * 批量取消收藏
     *
     * @param refrenceIds
     * @param dealerId
     * @throws BusinessException
     */
    public void executeUnCollect(String[] refrenceIds, String dealerId) throws BusinessException;

    /**
     * 取消收藏
     * @param dealerCollect
     * @throws BusinessException
     */
    public void executeUnCollect(DealerCollect dealerCollect) throws BusinessException;
}
