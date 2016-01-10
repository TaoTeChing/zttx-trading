/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerCount;

/**
 * 经销商计数信息 持久层接口
 * <p>File：DealerCountDao.java </p>
 * <p>Title: DealerCountDao </p>
 * <p>Description:DealerCountDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerCountMapper extends GenericMapper<DealerCount>
{

    /**
     * 根据dealerid 获取实体对象dealerCount 经销商信息计数表
     * @param dealerId
     * @return
     * @author 易永耀
     */
    DealerCount selectByDealerId(String dealerId);
    
    /**
     * 修改经绡商统计数量
     * @param columnType
     * @param dealerId
     * @param count
     * @return
     */
    Integer updateDealerCount(@Param("columnType") Integer columnType, @Param("dealerId") String dealerId, @Param("count") Integer count);
    
    /**
     * 根据dealerId 对 factoryJoinCount,factoryHasPaid做++处理
     * @param dealerId
     * @param updateTime
     */
    void updateCount(@Param("dealerId")String dealerId,@Param("updateTime")Long updateTime);
}
