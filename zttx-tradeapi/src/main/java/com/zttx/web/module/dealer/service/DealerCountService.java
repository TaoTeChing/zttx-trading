/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerCount;

/**
 * 经销商计数信息 服务接口
 * <p>File：DealerCountService.java </p>
 * <p>Title: DealerCountService </p>
 * <p>Description:DealerCountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerCountService extends GenericServiceApi<DealerCount>
{
    /**
     * 根据dealerid 获取实体对象dealerCount 经销商信息计数表
     * @param dealerId
     * @return  {@link DealerCount}
     * @author 易永耀
     */
    DealerCount selectByDealerId(String dealerId);
    
    /**
     * 修改经销商计数信息
     * @param dealerId
     * @param countType
     * @return  {@link DealerCount}
     * @throws BusinessException
     */
    DealerCount modifyDealerCount(String dealerId, Integer[] countType) throws BusinessException;
    
    /**
     * 根据dealerId 对 factoryJoinCount,factoryHasPaid做++处理
     * @param dealerId
     * @param updateTime
     */
    void updateCount(String dealerId,Long updateTime);
}
