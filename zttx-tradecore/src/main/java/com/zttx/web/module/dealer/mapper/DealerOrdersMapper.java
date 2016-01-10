/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerOrders;

/**
 * 经销商订单项信息 持久层接口
 * <p>File：DealerOrdersDao.java </p>
 * <p>Title: DealerOrdersDao </p>
 * <p>Description:DealerOrdersDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerOrdersMapper extends GenericMapper<DealerOrders> {

    /**
     * 根据订单流水号跟品牌商获取订单详细信息列表 根据产品编号分组
     *
     * @param orderId
     * @param brandId
     * @return
     */
    List<DealerOrders> findDifferentByBrandId(@Param("orderId") String orderId, @Param("brandId") String brandId);

    /**
     * 根据订单ID和品牌商ID查找
     *
     * @param orderId
     * @param brandId
     * @return
     */
    List<DealerOrders> findByOrderIdAndBrandId(@Param("orderId") String orderId, @Param("brandId") String brandId);

    /**
     * 根据订单号获取订单项信息
     *
     * @param orderId
     * @return
     */
    List<Map<String, Object>> getDealerOrdersList(@Param("orderId") String orderId);

    /**
     * 获取经销商的某个订单中的前10个产品的主图和其他相关信息
     *
     * @param dealerOrders
     * @return * @author易永耀
     */
    List<Map<String, Object>> getProductMap(DealerOrders dealerOrders);

    /**
     * 查询 dealerId订单为orderId 下的订单详情
     *
     * @param orderId
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<DealerOrders> selectDealerOrders(String orderId, String dealerId);

    /**
     * 根据订单Id获取订单项信息
     *
     * @param orderId
     * @return
     * @author 李星
     */
    List<DealerOrders> getDealerOrdersByOrderId(@Param("orderId") String orderId);

    /**
     * 统计产品销售量
     *
     * @param productId
     * @return
     */
    Integer countSaleNumByProductId(String productId);


    /**
     * 查询品牌商下所有订单项
     *
     * @param pagination
     * @param dealerOrders
     * @return
     */
    List<Map<String, Object>> getDealerOrdersByUpdateTime(@Param("page") Pagination pagination, @Param("dealerOrders") DealerOrders dealerOrders);

    /**
     * 查询品牌商下所有订单项
     *
     * @param productSkuId
     * @param orderid
     * @return
     */
    DealerOrders getDealerOrdersByProductSkuId(@Param("orderid") String orderid, @Param("productSkuId") String productSkuId);


    /**
     * 统计月销售数量
     *
     * @param productId
     * @return
     */
    List<Map> countMonthSaleNumByProductId(String productId);

    /**
     * 获取本月月销售量
     *
     * @param productId
     * @param currentMonth
     * @param nextMonth
     * @return
     */
    Integer countSaleNumByProductIdAndMonth(@Param("productId") String productId, @Param("currentMonth") Long currentMonth, @Param("nextMonth") Long nextMonth);
    
    /**
     * 获取产品productId 下订单为orderId的订单详细信息，和订单状态：发货还是已发货等
     * @param dealerId
     * @param orderId
     * @param productId
     * @return
     */
    List<Map<String, Object>> getDealerSkuMap(@Param("dealerId") String dealerId, @Param("orderId") String orderId, @Param("productId") String productId);
    
    /**
     * 取得未发货的订单项数据
     * @param productSkuId
     * @return
     */
    List<DealerOrders> getNoSendGoodsOrders(@Param("productSkuId") String productSkuId);
}
