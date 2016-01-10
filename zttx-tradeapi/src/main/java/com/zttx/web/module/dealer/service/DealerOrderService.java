/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.module.brand.model.SendGoodsModel;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.model.DealerOrderModel;

/**
 * 经销商订单信息 服务接口
 * <p>File：DealerOrderService.java </p>
 * <p>Title: DealerOrderService </p>
 * <p>Description:DealerOrderService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerOrderService extends GenericServiceApi<DealerOrder>
{
    /**
     * 品牌商获取订单信息
     * @param pagination
     * @param dealerOrder
     * @return
     */
    PaginateResult<Map<String, Object>> getDealerOrderList(Pagination pagination, DealerOrderModel dealerOrder);
    
    /**
     * 根据订单ID和品牌商ID查询对应的订单
     *
     * @param refrenceId
     * @param brandId
     */
    DealerOrderModel findEntityBy(String refrenceId, String brandId);
    
    /**
     * 根据订单号和品牌商编号查找
     *
     * @param orderId
     * @param brandId
     * @return
     */
    DealerOrderModel getByOrderIdAndBrandId(Long orderId, String brandId);
    
    /**
     * 根据产品id查找是否存在有效订单
     * @param productId
     * @return
     */
    Boolean hasValidOrderByProId(String productId);
    
    /**
     * 发货
     * @param sendModel
     * @param sendAll
     * @return
     * @throws BusinessException
     */
    DealerConst sendGoods(SendGoodsModel sendModel, Boolean sendAll) throws BusinessException;
    
    /**
     * 修改价格
     *
     * @param dealerOrder
     * @return
     * @throws BusinessException
     */
    DealerOrderModel updatePrice(DealerOrderModel dealerOrder) throws BusinessException;
    
    /**
     * 设置dealerOrder下一次操作时间
     * @param dealerOrder
     * @param dictCode
     * @param dictValueName
     */
    void setOutActTime(DealerOrder dealerOrder, String dictCode, String dictValueName);
    
    /**
     * 修改金额(品牌商使用)
     * @param discount
     * @param adjustPrice
     * @param adjustFreight
     * @param brandId
     * @param refrenceId
     * @param privilege_select
     * @throws BusinessException
     */
    void updatePrice(BigDecimal discount, BigDecimal adjustPrice, BigDecimal adjustFreight, String brandId, String refrenceId, Short privilege_select)
            throws BusinessException;
    
    /**
     * 修改运费(品牌商使用)
     *
     * @param adjustFreight
     * @param brandId
     * @param refrenceId
     * @param brandsId
     * @throws BusinessException
     * @author 鲍建明
     */
    DealerOrderModel updateFare(BigDecimal adjustFreight, String brandId, String refrenceId, String brandsId) throws BusinessException;
    
    /**
     * 品牌商更新订单备注
     * @param refrenceId
     * @param brandId
     * @param remark
     * @param levelMark
     * @throws BusinessException
     */
    void updateBrandRemark(String[] refrenceId, String brandId, String remark, Short levelMark) throws BusinessException;
    
    /**
     * 延迟收货期限
     * @param actTime
     * @param brandId
     * @param refrenceId
     * @throws BusinessException
     */
    void updateOutActTime(Long actTime, String brandId, String refrenceId) throws BusinessException;
    
    /**
     * 增加已扣点金额
     *
     * @param dealerOrder
     * @param pointBalance
     * @throws BusinessException
     * @author 张昌苗
     */
    void addPointBalance(DealerOrder dealerOrder, BigDecimal pointBalance) throws BusinessException;
    
    /**
     * 修改订单地址
     *
     * @param dealerOrder
     * @param brandId
     * @param orderId
     * @throws BusinessException
     * @author 张孟如
     */
    void updateAddr(DealerOrder dealerOrder, String brandId, Long orderId) throws BusinessException;
    
    /**
     * 新增订单
     * @param dealerId
     * @param addressId
     * @param dealerShoperIds
     * @param remarksMap
     * @param freightMap
     * @return
     * @author 易永耀
     */
    List<DealerOrder> addDealerOrder(String dealerId, String addressId, String[] dealerShoperIds, Map<String, String> remarksMap, Map<String, String> freightMap)
            throws BusinessException;
    
    /**
     * 抽象购物车直接生成订单
     * @param dealerId 经销商id
     * @param addressId 收货地址
     * @param dealerShoper 抽象购物车                
     * @param remarksMap 留言
     * @param freightMap 运费
     * @return 订单id
     * @throws BusinessException
     */
    String addDealerOrder(String dealerId, String addressId, DealerShoper dealerShoper, Map<String, String> remarksMap, Map<String, String> freightMap)
            throws BusinessException;
    
    /**
     * 是否 已经对该产品拿过样（该产品已经有有效订单 即为失去拿样资格）
     * @param dealerId
     * @param productId
     * @return
     * @author 易永耀
     */
    public Boolean isHasTakeSample(String dealerId, String productId);
    
    /**
     * 分页查询经销商订单
     * @param dealerOrder
     * @param pagination
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String, Object>> selectDealerOrderPage(DealerOrder dealerOrder, Pagination pagination) throws BusinessException;
    
    /**
     * 根据品牌商ID和经销商ID取报表信息
     *
     * @param brandId
     * @param dealerId
     * @return {@link java.util.Map<java.lang.String,java.lang.Object>}
     */
    Map<String, Object> getDealerReportInfo(String brandId, String dealerId) throws BusinessException;
    
    /**
     * 查询订单状态 （授信，现款，拿样）
     * @param dealerId
     * @return
     * @author 易永耀
     */
    Map<String, Object> selectDealerOrderType(String dealerId);
    
    /**
     * 根据 orderId 与经销商id 获取订单详情
     * @param orderId
     * @param dealerId
     * @return
     * @author 易永耀
     */
    DealerOrder getDealerOrder(String orderId, String dealerId) throws BusinessException;
    
    /**
     * 校验订单中经销商与品牌商，产品关系是否还有效
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    Map<String, Object> isValid(DealerOrder dealerOrder);
    
    /**
     * 批量免邮(品牌商动作)
     *
     * @param refrenceId
     * @param brandId
     * @throws BusinessException
     * @author 鲍建明
     */
    void updateAvoidMail(String[] refrenceId, String brandId) throws BusinessException;
    
    /**
     * 同步全部订单(用于boss)
     *
     * @param page
     * @return
     * @throws BusinessException
     * @author 李星
     */
    PaginateResult<DealerOrderModel> getDealerOrderList4Boss(DealerOrder dealerOrder, Pagination page);
    
    /**
     * 根据经销商id查询该经销商的所有进货数量和进货额
     * @param dealerId
     * @return
     */
    Map<String, Object> getCountAndAmountByDealerId(String dealerId);
    
    /**
     * 根据订单字段查询订单信息
     * @param dealerOrder
     * @return
     */
    List<Map<String, Object>> findByDealerOrder(DealerOrder dealerOrder);
    
    /**
     * 根据经销商id和时间查找订单
     * @param order
     * @return
     */
    List<Map<String, Object>> findbyDealerIdAndTimeRange(DealerOrder order);
    
    /**
     * 财务帐总帐（经销商，品牌商共用）
     * @param pagination
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    Map<String, Object> getDealerOrderReportMap(Pagination pagination, DealerOrder dealerOrder) throws BusinessException;
    
    /**
     * 财务帐明细（经销商，品牌商共用）
     * @param pagination
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    Map<String, Object> getDealerOrderReportDetailMap(Pagination pagination, DealerOrder dealerOrder) throws BusinessException;
    
    /**
     * 获取经销商品牌商 财务帐交易的类型（采购、退款。。。）
     * @param dealerId
     * @param brandId
     * @return
     * @author 易永耀
     */
    Map<String, Object> selectTradeTypeList(String dealerId, String brandId);
    
    /**
     * 根据状态统计订单
     * @param orderStatus
     * @param outActTime
     * @return
     * @author 李星
     */
    Long countByOrderStatus(Short orderStatus, Long outActTime);
    
    /**
     * 根据状态获取订单
     * @param orderStatus
     * @param outActTime
     * @param page
     * @return
     */
    PaginateResult<DealerOrder> getListByOrderStatus(Short orderStatus, Long outActTime, Pagination page);
    
    /**
     * 确认收货
     * @param refrenceId
     * @param dealerId
     * @param payword
     * @author 张昌苗
     */
    void confirmReceive(String refrenceId, String dealerId, String payword) throws BusinessException;
    
    /**
     * 铺货订单确认收货
     * @param refrenceId
     * @param dealerId
     * @author 张昌苗
     */
    void confirmReceive2(String refrenceId, String dealerId) throws BusinessException;
    
    /**
     * 调度:品牌商获取订单信息
     * @param dealerOrder
     * @return 李星
     */
    PaginateResult<Map<String, Object>> getTaskDealerOrderList(Pagination page, DealerOrder dealerOrder);
    
    /**
     * 订单自动退款，用于后台任务处理  终端商付清全部款项后，品牌商逾期未发货订单自动退款,
     *
     * @param refrenceId 订单编号
     * @throws BusinessException
     * @author 施建波
     */
    void confirmRefund(String refrenceId) throws BusinessException;
    
    /**
     * 更新状态
     * @param orderId
     * @param state
     */
    void updateComplaintState(String orderId, short state);
    
    /**
     * 返回品牌商下的订单信息
     * @param dealerOrder
     * @param page
     * @param type
     * @return
     * @author 李飞欧
     */
    PaginateResult<DealerOrderModel> getDealerOrders(DealerOrderModel dealerOrder, Pagination page, int type) throws BusinessException;
    
    /**
     * 订单统计
     * @param dealerOrder
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    Map<String, Object> findOrderCount(DealerOrderModel dealerOrder) throws BusinessException;
    
    /**
     * Client接口修改运费和优惠价
     * @param dealerOrder
     * @throws BusinessException
     * @author 李飞欧
     */
    void updateOrderCost(DealerOrder dealerOrder) throws BusinessException;
    
    /**
     * 查询订单
     *
     * @param userId
     * @param orderIdArr
     * @return
     * @author 张昌苗
     */
    List<DealerOrder> listWithException(String userId, String ... orderIdArr) throws BusinessException;
    
    /**
     * 是否退款状态
     * @param orderId
     * @param dealerId
     * @return
     */
    boolean isRefundStatus(Long orderId, String dealerId);
    
    /**
     * 更加订单号和经销商编号获取订单
     * @param orderId
     * @param dealerId
     * @return
     */
    DealerOrderModel getByOrderIdAndDealerId(Long orderId, String dealerId);
    
    /**
     * 查询订单列表
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @param page
     * @return
     */
    public PaginateResult<DealerOrderModel> getOrdList(String dealerId, String brandId, String brandsId, Pagination page);
    
    /**
     * 批量统计订单中销售数量
     *
     * @param list
     */
    public void countPrice(List<DealerOrderModel> list);
    
    /**
     * 统计订单中销售数量
     *
     * @param ord
     */
    public void countPrice(DealerOrderModel ord);
    
    void updateCostPriceByProductIdAndSkuList(List<ProductSku> productSkuList) throws BusinessException;
    
    /**
     * 查品牌与经销商的实时授信金额
     * @param dealerOrder
     * @return
     * @author 易永耀
     */
    Map<String, Object> selectCreditCurrent(DealerOrder dealerOrder) throws BusinessException;

    /**
     * 根据主键获取包含品牌名称，经销商名称等信息的订单Model
     * @param orderId
     * @return
     */
    DealerOrder findModelById(String orderId);
}
