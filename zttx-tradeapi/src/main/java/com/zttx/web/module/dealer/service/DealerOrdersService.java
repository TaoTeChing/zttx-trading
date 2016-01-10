/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.model.DealerOrdersModel;
import com.zttx.web.module.dealer.model.OrderModel;

/**
 * 经销商订单项信息 服务接口
 * <p>File：DealerOrdersService.java </p>
 * <p>Title: DealerOrdersService </p>
 * <p>Description:DealerOrdersService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerOrdersService extends GenericServiceApi<DealerOrders>{

    /**
     * 根据订单ID和品牌商ID查找
     * @param orderId
     * @param brandId
     * @return
     */
    List<DealerOrders> findByOrderIdAndBrandId(String orderId, String brandId);
    
    /**
     * 转换订单项对象到订单模型对象
     *
     * @param datas
     * @return
     */
    List<OrderModel> convertDealerOrdersToOrderModel(List<DealerOrders> datas);
    /**
     * 获取经销商的某个订单中的前10个产品的主图和其他相关信息
     * @param dealerId
     * @param orderId
     * @return
     * * @author易永耀
     */
    List<Map<String,Object>> getProductMap(String dealerId, String orderId);

    /**
     * 获取经销商订单下 订单详情
     * @param orderId
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<DealerOrders> selectDealerOrders(String orderId, String dealerId);


    /**
     * 品牌ERP发货询问接口
     *
     * @param map
     * @return
     * @throws BusinessException
     */
    public DealerOrdersModel updateOrderAsk(Map<String, String> map) throws BusinessException;
    
    /**
     * 根据订单号获取订单项信息
     
     * @param dealerorders
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    List<DealerOrders> getDealerOrders(DealerOrders dealerorders) throws BusinessException;
    
    /**
     * h获取产品productId 下订单为orderId的订单详细信息，和订单状态：发货还是已发货等
     * @param dealerId
     * @param orderId
     * @param productId
     * @return
     */
    List<Map<String, Object>> getDealerSkuMap(String dealerId, String orderId, String productId);
}
