/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.model.DealerOrderModel;

/**
 * 经销商订单信息 持久层接口
 * <p>File：DealerOrderDao.java </p>
 * <p>Title: DealerOrderDao </p>
 * <p>Description:DealerOrderDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerOrderMapper extends GenericMapper<DealerOrder>
{
    /**
     * 统计数量,for BrandCount
     * @param dealerOrder
     * @return
     */
    Long getDealerOrderCount(DealerOrderModel dealerOrder);

    /**
     * 统计数量,for BrandCount
     * @param dealerOrder
     * @return
     */
    Long getSumOrderMoney(DealerOrderModel dealerOrder);
    
    /**
     * 品牌商获取订单信息
     * @param dealerOrder
     * @return
     * @author 李星
     */
    List<Map<String, Object>> getDealerOrderList(DealerOrderModel dealerOrder);

    /**
     * 根据订单编号和品牌商或经绡商编号查询实体
     * @param dealerOrder
     * @return
     */
    List<DealerOrder> getDealerOrderEntityList(DealerOrder dealerOrder);
    
    /**
     * 获取未摊完成本的授信订单
     * @param brandId
     * @param dealerId
     * @return
     */
    List<DealerOrderModel> listFactoryStoreUnpay(@Param("brandId")String brandId,@Param("dealerId") String dealerId);
    
    /**
     * 查询对应的订单
     *
     * @param refrenceId
     * @param brandId
     */
    DealerOrderModel findEntityBy(@Param("refrenceId") String refrenceId, @Param("brandId") String brandId);
    
    /**
     * 根据订单号和品牌商编号查找
     * @param orderId
     * @param brandId
     * @return
     */
    DealerOrderModel getByOrderIdAndBrandId(@Param("orderId") Long orderId, @Param("brandId") String brandId);
    /**
     * 根据产品id查找存在有效订单
     * @param productId
     * @return
     */
    Integer hasValidOrderByProId(String productId);
    
    /**
     * 根据条件统计数量,for dealerCount
     * @param dealerOrder
     * @return
     */
    Long selectDealerOrderCount(DealerOrder dealerOrder);
    
    /**
     * 更新优惠的价格 和 调整后的运费 (品牌商使用)
     * @param adjustPrice
     * @param adjustFreight
     * @param refrenceId
     */
    Integer updatePrice(@Param("adjustPrice") BigDecimal adjustPrice, @Param("adjustFreight") BigDecimal adjustFreight, @Param("refrenceId") String refrenceId);
    
    /**
     * 修改订单更新时间
     * @param refrenceId
     * @return
     */
    Integer updateOrderTime(@Param("refrenceId") String refrenceId, @Param("now") Long now);
    
    /**
     * 修改备注
     * @param refrenceId
     * @param brandRemark
     * @param brandId
     * @param levelMark
     * @return
     */
    Integer updateBrandRemark(@Param("refrenceId") String refrenceId, @Param("brandRemark") String brandRemark, @Param("brandId") String brandId,
            @Param("levelMark") Short levelMark);
    
    /**
     * 延迟收货期限
     * @param outActTime
     * @param refrenceId
     */
    Integer updateOutActTime(@Param("outActTime") Long outActTime, @Param("refrenceId") String refrenceId);
    
    /**
     * 修改退款状态
     * @param refrenceId
     * @param refundStatus
     * @return
     */
    Integer updateRefundStatus(@Param("refrenceId") String refrenceId, @Param("refundStatus") Short refundStatus);
    
    /**
     * 修改订单状态
     * @param refrenceId
     * @param orderStatus
     * @return
     */
    Integer updateOrderStatus(@Param("refrenceId") String refrenceId, @Param("orderStatus") Short orderStatus);
    /**
     * 是否 已经对该产品拿过样（该产品已经有有效订单 即为失去拿样资格）
     * @param dealerId
     * @param productId
     * @return
     * @author 易永耀
     */
    int countDealerOrder(String dealerId, String productId);

    /**
     * 根据实体查询相关订单
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectDealerOrderList(DealerOrder dealerOrder);
    /**
     * 根据条件查询经销商订单
     * @param order
     * @return
     */
    List<Map<String,Object>> findByDealerOrder(DealerOrder order);
    /**
     * 查询订单状态 （授信，现款，拿样）
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectDealerorderType(String dealerId);
    
    /**
     * 获取订单信息(boss同步订单)
     * @param dealerOrder
     * @return
     */
    List<DealerOrder> getDealerOrderList4Boss(DealerOrder dealerOrder);
    
    /**
     * 根据经销商id查询该经销商的所有进货数量和进货额
     * @param dealerId
     * @return
     */
    Map<String, Object> getCountAndAmountByDealerId(String dealerId);
    /**
     * 根据经销商id和时间查询订单
     * @param order
     * @return
     */
    List<Map<String,Object>> findbyDealerIdAndTimeRange(DealerOrder order);
    /**
     * 分页查询经销商、品牌商交易记录
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> getDealerOrderReportDetailList(DealerOrder dealerOrder);
    
    /**
     * 根据状态统计订单
     * @param orderStatus
     * @param outActTime
     * @return
     * @author 李星
     */
    Long countByOrderStatus(@Param("orderStatus") Short orderStatus, @Param("outActTime") Long outActTime);
    
    /**
     * 统计调度任务订单
     * @param dealerOrder
     * @return
     * @author 李星
     */
    Long getTaskDealerOrderCount(DealerOrder dealerOrder);
    
    /**
     * 调度:品牌商获取订单信息
     * @param dealerOrder
     * @return 李星
     */
    List<Map<String, Object>> getTaskDealerOrderList(DealerOrder dealerOrder);
    
    /**
     * 根据主键和经销商编号获取订单
     * @param refrenceId
     * @param dealerId
     * @return
     */
    DealerOrderModel getDealerOrder(@Param("refrenceId") String refrenceId, @Param("dealerId") String dealerId);
    
    /**
     * 根据状态获取订单
     * @param orderStatus
     * @param outActTime
     * @return
     */
    List<DealerOrder> getListByOrderStatus(@Param("orderStatus") Short orderStatus, @Param("outActTime") Long outActTime, @Param("page") Pagination page);

    /**
     * 获取与该经销商或品牌商有订单的所有品牌商或经销商的信息
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectOrderUser(DealerOrder dealerOrder);
    
    /**
     * 获取未结算成本 的所有订单
     * @param dealerId
     * @param brandId
     * @return
     */
    List<DealerOrder> selectNoneCostSettlementOrderBy(@Param("dealerId") String dealerId, @Param("brandId") String brandId);
    
    /**
     * 返回品牌商下的订单信息
     * @param dealerOrder
     * @param page
     * @param type
     * @return
     * @author 李飞欧
     */
    List<DealerOrder> getDealerOrders(@Param("dealerOrder") DealerOrderModel dealerOrder, @Param("page") Pagination page, @Param("type") int type);
    
    /**
     * 根据条件查询订单
     * @param dealerOrder
     * @return
     */
    List<DealerOrder> listDealerOrders(@Param("dealerOrder") DealerOrderModel dealerOrder);
    
    /**
     * 根据订单编号和经销商ID查新订单是否处在退款状态
     * @param orderId
     * @param dealerId
     * @return
     */
    Boolean isRefundStatus(@Param("orderId") Long orderId, @Param("dealerId") String dealerId);
    
    /**
     * 根据订单编号和经销商ID获取订单信息
     * @param orderId  订单编号
     * @param dealerId 经销商编号
     * @return 如果存在返回相应订单信息，不存在返回 null
     * @author 夏铭
     */
    DealerOrderModel getByOrderIdAndDealerId(@Param("orderId") Long orderId, @Param("dealerId") String dealerId);
    
    /**
     * 修改经绡商统计数量
     * @param columnType
     * @param dealerId
     * @param count
     * @return
     */
    Integer updateDealerCount(@Param("columnType") Integer columnType, @Param("dealerId") String dealerId, @Param("count") Integer count);
    
    /**
     * 修改订单投诉状态
     * @param refrenceId
     * @param status
     */
    Integer updateComplaintState(@Param("refrenceId")String refrenceId, @Param("status")Short status);

    /**
     * 查询订单列表
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @param page
     * @return
     */
    List<DealerOrderModel> getOrdList(@Param("dealerId") String dealerId,@Param("brandId") String  brandId,@Param("brandsId") String  brandsId,@Param("page")Pagination  page);
    /**
     * 根据 dealerId brandId获取两之间的合作品牌
     * @author 易永耀
     * @param dealerId
     * @param brandId
     * @return
     *  map{brandesName:品牌名称}
     */
    List<Map<String,Object>> findByDealerIdAndBrandId(@Param("dealerId") String dealerId, @Param("brandId") String brandId);
    
    /**
     * 根据主键获取包含品牌名称，经销商名称等信息的订单Model
     * @param orderId
     * @return
     */
    DealerOrderModel findModelById(@Param("orderId") String orderId);
}
