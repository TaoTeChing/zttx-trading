/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerClearing;

import java.util.Map;

/**
 * 工厂店品牌信息结算表 服务接口
 * <p>File：DealerClearingService.java </p>
 * <p>Title: DealerClearingService </p>
 * <p>Description:DealerClearingService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerClearingService extends GenericServiceApi<DealerClearing>{
    /**
     * 分页查询 当期应付款数据
     * @param pagination
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    Map<String,Object> selectDealerClearing(Pagination pagination, DealerClearing dealerClearing) throws BusinessException;


    /**
     * 修正结算状态
     */
    Map<String, Object> fixDealerClearingNoPayedBy(String dealerId, String brandId);

    /**
     * 条件查询 dealerClering
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    DealerClearing selectDealerClearingSelective(DealerClearing dealerClearing);

    /**
     * 创建记录
     * @param dealerId
     * @param brandId
     * @param searchTime
     * @param map
     * @return
     * @author 张昌苗
     */
    public DealerClearing create(String dealerId, String brandId,String brandsId, Long searchTime,Map<String,Object> map) throws BusinessException;

    /**
     * 查询 当期未付的欠付款
     * @param dealerId
     * @param brandId
     * @return map：
     * @author 易永耀
     */
    Map<String,Object> selectDealerClearing(String dealerId, String brandId);

    Map<String,Object> findDealerClearingNoPayedBy(String dealerId, String brandId);

    /**
     * 查询该经销商与品牌商之间支付的款项时间段 （如支付：4-10到10-10 共 10 笔当期款）
     * @author 易永耀
     * @param dealerClearing
     * @return
     *   beginTime  4-10 最远的未支付时间
     *   endTime   10-10 最近的未支付的时间
     *   count      4    共多少笔支付款
     */
    Map<String,Object> selectDealerClearingPayTime(DealerClearing dealerClearing) throws BusinessException;
}
