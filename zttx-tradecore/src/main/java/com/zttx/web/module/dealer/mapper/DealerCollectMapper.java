/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.model.DealerCollectModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经销商品牌收藏夹 持久层接口
 * <p>File：DealerCollectDao.java </p>
 * <p>Title: DealerCollectDao </p>
 * <p>Description:DealerCollectDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerCollectMapper extends GenericMapper<DealerCollect>
{
    /**
     * 统计数量
     * @param filter
     * @return
     */
    Long getDealerCollectCount(DealerCollect filter);
    /**
     * 经销商 加盟管理  品牌收藏夹
     * @param dealerCollectModel
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> getDealerCollectsList(DealerCollectModel dealerCollectModel);
    
    
    /**
     * 根据 终端商ID,品牌id，删除状态查询 
     * @param dealerId
     * @param brandesId
     * @param delFlag
     * @return
     */
    DealerCollect findDealerCollect(@Param("dealerId")String dealerId, @Param("brandsId")String brandesId,
            @Param("delFlag")Boolean delFlag);

    /**
     * 取消收藏
     */
    DealerCollect findDealerCollectByFilter(@Param("filter")DealerCollect filter);
}
