/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerClearing;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工厂店品牌信息结算表 持久层接口
 * <p>File：DealerClearingDao.java </p>
 * <p>Title: DealerClearingDao </p>
 * <p>Description:DealerClearingDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerClearingMapper extends GenericMapper<DealerClearing>
{
    /**
     * 取出当前品牌商与经销商未结算的总金额
     *
     * @param brandId
     * @param dealerId
     * @return {@link BigDecimal}
     */
    BigDecimal getUnClearAmount(@Param("brandId") String brandId, @Param("dealerId") String dealerId);

    /**
     * 获取 品牌商与经销商 当期应付款数据
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    List<DealerClearing> selectDealerClearingList(DealerClearing dealerClearing);
    
    /**
     * 结算表状态修改成已经结算
     * @param dealerClearing
     * @return
     */
    Integer updateDealerClearingBy(DealerClearing dealerClearing);
    
    /**
     * 得到 指定终端商与品牌商 当前未结算的成本总账
     * @param dealerClearing
     * @return
     * @author 吕海斌
     */
    List<DealerClearing> selectDealerClearingNoPayedByDealerIdAndBrandId(DealerClearing dealerClearing);

    /**
     * 按结算日期分组查询 当期应付款详情数据
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    List<DealerClearing> selectDealerClearingListGroupByClearingTime(DealerClearing dealerClearing);

    /**
     * 条件查询 dealerClering
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    DealerClearing selectDealerClearingSelective(DealerClearing dealerClearing);

    /**
     * 获取未结算 当期财务帐 根据dealerId brandId
     * @param dealerClearing
     * @return
     * @author 易永耀
     */
    Map<String,Object> selectDealerClearingNoPayed(DealerClearing dealerClearing);
    /**
     * 查询该经销商与品牌商之间支付的款项时间段 （如支付：4-10到10-10 共 10 笔当期款）
     * @author 易永耀
     * @param dealerClearing
     * @return
     *   startTime  4-10 最远的未支付时间
     *   endTime   10-10 最近的未支付的时间
     *   count      4    共多少笔支付款
     */
    Map<String,Object> selectDealerClearingPayTime(DealerClearing dealerClearing);
}
