package com.zttx.web.module.transfer.service;

import java.util.List;

import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.entity.DealerOrder;

/**
 * <p>File：ProductTransferService.java</p>
 * <p>Title: 订单信息迁移服务</p>
 * <p>
 * Description:数据迁移服务中提供的所有接口和实现方法其它模块不可引用，
 * 因为这些接口只提供一次性服务。
 * </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author 李星
 * @version 1.0
 */
public interface OrderTransferService
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
    List<DealerOrder> findFavourableOrderInfo(Pagination page);
    
    /**
     * 将优惠/加价分摊到SKU
     * @author 李星
     */
    void apportionDiscountToDealerOrder(DealerOrder dealerOrder);
    
    /**
     * 未完成工厂店订单数目
     * @return
     */
    Long findUnCompleteFactoryOrderInfoCount();
    
    /**
     * 未完成订单
     * @param page
     * @return
     */
    List<DealerOrder> findUnCompleteFactoryOrderInfo(Pagination page);
    
    /**
     * 处理未完成工厂店订单
     * @param dealerOrder
     */
    void processUnCompleteFactoryOrder(DealerOrder dealerOrder);
    
    /**
     * 等待确认收货的工厂店订单数目
     * @return
     */
    Long findWaitReceiveFactoryOrderInfoCount();
    
    /**
     * 获取等待确认收货的工厂店订单
     * @param page
     * @return
     */
    List<DealerOrder> findWaitReceiveFactoryOrderInfo(Pagination page);
    
    /**
     * 处理等待确认收货的工厂店订单
     * @param dealerOrder
     */
    /**
     * 处理等待确认收货的工厂店订单
     * @param dealerOrder
     * @param hasComfirm 产品是否已确定
     */
    void processWaitReceiveFactoryOrder(DealerOrder dealerOrder, boolean hasComfirm);
    
    /**
     * 根据订单number获取订单
     * @param orderId
     * @return
     */
    DealerOrder findByOrderId(Long orderId);
}
