/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrderPay;

/**
 * 支付记录表 服务接口
 * <p>File：DealerOrderPayService.java </p>
 * <p>Title: DealerOrderPayService </p>
 * <p>Description:DealerOrderPayService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerOrderPayService extends GenericServiceApi<DealerOrderPay>{

    /**
     * 获取单个订单所有支付记录
     *
     * @param orderId
     * @return
     */
    List<DealerOrderPay> findByOrderId(String orderId);

    /**
     * 获取未付款记录
     * @param orderId
     * @return
     */
    List<DealerOrderPay> listUnpay(String orderId);

    /**
     * 获取已付款记录
     * @param orderId
     * @return
     */
    List<DealerOrderPay> listPaid(String orderId);

    void executePayClose(Long payId) throws BusinessException;

    /**
     * 根据订单id 获取经销商关于该订单的支付
     * @param orderId
     * @return
     * @author 易永耀
     */
    List<DealerOrderPay> selectDealerOrderPayList(String orderId);
    
    /**
     * 根据payId查找
     * @param payId
     * @return
     * @throws BusinessException
     */
    DealerOrderPay findByIdWithException(Long payId) throws BusinessException;
    
    /**
     * 支付成功
     * @param payId
     * @throws BusinessException
     * @author 张昌苗
     */
    DealerOrderPay executePaySuccess(Long payId) throws BusinessException;
    
    /**
     * 支付成功
     *
     * @param payId
     * @throws BusinessException
     * @author 张昌苗
     */
    DealerOrderPay executePaySuccess(Long payId, DealerOrder dealerOrder) throws BusinessException;

    /**
     * 查询并返回支付记录和订单对象
     * @param payId
     * @return
     * @author 张昌苗
     */
    DealerOrderPay findByIdWithOrder(Long payId) throws BusinessException;
    
    /**
     * 获取所有状态为未支付的订单支付记录
     * @return
     * @throws BusinessException
     */
    List<DealerOrderPay> findAllUnPay() throws BusinessException;


}
