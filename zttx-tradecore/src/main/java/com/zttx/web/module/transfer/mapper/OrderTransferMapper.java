package com.zttx.web.module.transfer.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrders;

/**
 * <p>File：ProductTransferMapper.java</p>
 * <p>Title: 订单信息迁移服务</p>
 * <p>Description:ProductTransferMapper </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author 李星
 * @version 1.0
 */
@MyBatisDao
public interface OrderTransferMapper
{
    /**
     * 查询有优惠的订单的数量
     * @return
     */
    Long findFavourableOrderInfoCount();
    
    /**
     * 查询有优惠的订单
     * @return
     */
    List<DealerOrder> findFavourableOrderInfo(@Param("page") Pagination page);
    
    /**
     * 根据订单ID和品牌商ID查找
     * @param orderId
     * @param brandId
     * @return
     */
    List<DealerOrders> findDealerOrdersByOrderIdAndBrandId(@Param("orderId") String orderId, @Param("brandId") String brandId);
    
    /**
     * 根据主键更新一条订单项优惠价
     * @return
     */
    int updateDealerOrdersAdjustPrice(@Param("refrenceId") String refrenceId, @Param("adjustPrice") BigDecimal adjustPrice);
    
    /**
     * 获取未完成工厂店订单数目
     * @return
     */
    Long findUnCompleteFactoryOrderInfoCount();
    
    /**
     * 未完成工厂店订单
     * @param page
     * @return
     */
    List<DealerOrder> findUnCompleteFactoryOrderInfo(Pagination page);
    
    /**
     * 等待确认收货的工厂店订单数目
     * @return
     */
    Long findWaitReceiveFactoryOrderInfoCount();
    
    /**
     * 等待确认收货的工厂店订单
     * @param page
     * @return
     */
    List<DealerOrder> findWaitReceiveFactoryOrderInfo(Pagination page);
    
    /**
     * 根据订单number获取订单
     * @param orderId
     * @return
     */
    DealerOrder findByOrderId(Long orderId);
    
    /**
     * 修改订单状态
     * @param orderStatus
     * @return
     */
    int updateorderStatus(@Param("orderId") Long orderId, @Param("orderStatus") Short orderStatus);
}
