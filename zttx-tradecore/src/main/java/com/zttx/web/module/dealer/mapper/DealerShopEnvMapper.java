/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerShopEnv;

/**
 * 终端商店铺信息 持久层接口
 * <p>File：DealerShopEnvDao.java </p>
 * <p>Title: DealerShopEnvDao </p>
 * <p>Description:DealerShopEnvDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerShopEnvMapper extends GenericMapper<DealerShopEnv>
{
    /**
     * 条件查询
     * @param dealerShopEnv
     * @return
     */
    List<DealerShopEnv> selectDealerShopEnvsBy(DealerShopEnv dealerShopEnv);

    /**
     * 查找最新topn条记录
     * @param topn
     * @return
     */
    List<DealerShopEnv> selectTopNewestDealerShopEnvs(@Param("topn")int topn);
    
    /**
     * 根据区域分页获取
     * @param currAreaNo
     * @param pagination
     * @return
     */
    List<DealerShopEnv> selectExcludeDealerShopEnvsBy(@Param("currAreaNo") int currAreaNo, @Param("page") Pagination pagination);
}
