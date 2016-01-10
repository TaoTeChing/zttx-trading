/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerShopEnv;

/**
 * 终端商店铺信息 服务接口
 * <p>File：DealerShopEnvService.java </p>
 * <p>Title: DealerShopEnvService </p>
 * <p>Description:DealerShopEnvService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerShopEnvService extends GenericServiceApi<DealerShopEnv>
{
    /**
     * 保存店铺周边
     * @param dealerShopEnv
     */
    void save(DealerShopEnv dealerShopEnv);
    
    /**
     * 根据dealerId 获取终端商店铺信息
     * @author 陈建平
     * @param dealerId
     * @return
     */
    DealerShopEnv getDealerShopEnv(String dealerId);
    
    /**
     * 修改店铺周边是否显示
     * @param refrenceId 主键
     * @param isShow   true: 显示  false:不显示
     */
    void updateDealerShopEnvShowed(String refrenceId, boolean isShow) throws BusinessException;
    
    /**
     * 分页条件查询
     * @param dealerShopEnv
     * @param pagination
     * @return
     */
    PaginateResult<DealerShopEnv> getDealerShopEnvsBy(DealerShopEnv dealerShopEnv, Pagination pagination);
    
    /**
     * 查找最新的N条店铺信息
     * @return
     */
    List<DealerShopEnv> getTopNewestDealerShopEnvs(int topn);
    
    /**
     * 分页地区查询
     * @param pagination
     * @return
     */
    PaginateResult<DealerShopEnv> getExcludeDealerShopEnvsBy(int currAreaNo, Pagination pagination);
    
    /**
     * 修改店铺周边信息记录
     *
     * @return
     */
    boolean modDealerShopEnvViewCount(String refrenceId, int incCount) throws Exception;
}
