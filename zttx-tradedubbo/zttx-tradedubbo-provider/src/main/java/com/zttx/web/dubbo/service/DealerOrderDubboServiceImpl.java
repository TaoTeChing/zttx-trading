package com.zttx.web.dubbo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.dealer.entity.DealerComplaint;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.entity.OrderShipRecord;
import com.zttx.web.module.dealer.mapper.OrderShipRecordMapper;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.model.DealerOrdersModel;
import com.zttx.web.module.dealer.service.DealerComplaintService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.module.dealer.service.DealerOrdersService;

/**
 * DealerOrderDubboServiceImpl
 *
 * @author 江枫林
 * @date 2015/9/9
 */
@Service
public class DealerOrderDubboServiceImpl implements DealerOrderDubboService
{
    private static int             complainDays = 30;
    
    @Autowired
    private DealerOrderService     dealerOrderService;
    
    @Autowired
    private OrderShipRecordMapper  orderShipRecordMapper;
    
    @Autowired
    private OrderPayService        orderPayService;
    
    @Autowired
    private DealerComplaintService dealerComplaintService;
    
    @Autowired
    private DealerOrdersService    dealerOrdersService;
    
    /**
     * 查询经营商订单信息分页显示
     *
     * @param pagination
     * @param dealerOrder
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> getDealerOrderList(Pagination pagination, DealerOrderModel dealerOrder)
    {
        return dealerOrderService.getDealerOrderList(pagination, dealerOrder);
    }
    
    @Override
    public List<Map<String, Object>> search(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        dealerOrder.setPage(pagination);
        List<Map<String, Object>> list = listByDealerId(dealerOrder);
        for (Map<String, Object> order : list)
        {
            order.remove("orderItem");
            order.remove("items");
            BigDecimal productPrice = (BigDecimal) order.get("productPrice"); // 价格已经打折后的价格
            BigDecimal adjustPrice = (BigDecimal) order.get("adjustPrice"); // 产品调价
            BigDecimal afterAdjustProductPrice = productPrice.add(adjustPrice);
            order.put("afterAdjustProductPrice", afterAdjustProductPrice);
            String id = (String) order.get("refrenceId");
            DealerOrders entity = new DealerOrders();
            entity.setOrderId(id);
            List<DealerOrders> ordersList = dealerOrdersService.findList(entity);
            if (null != ordersList && !ordersList.isEmpty())
            {
                for (DealerOrders dealerOrders : ordersList)
                {
                    if (null != dealerOrders.getAdjustPrice())
                    {
                        // 现款订单的 修改金额 调价
                        dealerOrders.setRealProductSkuPrice(dealerOrders.getAdjustPrice().divide(new BigDecimal(dealerOrders.getQuantity()),2));
                        dealerOrders.setRealProductSkuTotalPrice(dealerOrders.getAdjustPrice());
                    }
                    else
                    {
                        // 授信订单 产品价格调整
                        if (null == dealerOrders.getDiscount() || dealerOrders.getDiscount().compareTo(BigDecimal.ZERO) == 0) { throw new BusinessException("订单" + dealerOrders.getOrderId() + "折扣值异常"); }
                        if (null == dealerOrders.getOldPrice()) // 品牌商未对产品调价
                        {
                            dealerOrders.setRealProductSkuPrice(dealerOrders.getPrice().multiply(dealerOrders.getDiscount()));
                        }
                        else
                        { // 品牌商对产品调价
                            if (dealerOrders.getOldPrice().multiply(dealerOrders.getDiscount()).compareTo(dealerOrders.getPrice()) == 1) // 价格调价到比原有价格*折扣还小的值就不在折扣
                            {
                                dealerOrders.setRealProductSkuPrice(dealerOrders.getPrice());
                            }
                            else
                            {
                                dealerOrders.setRealProductSkuPrice(dealerOrders.getPrice().multiply(dealerOrders.getDiscount()));
                            }
                        }
                        dealerOrders.setRealProductSkuTotalPrice(dealerOrders.getTotalAmount());
                    }
                }
            }
            order.put("ordersList", ordersList);
        }
        return list;
    }
    
    public List<Map<String, Object>> listByDealerId(DealerOrder dealerOrder) throws BusinessException
    {
        List<Map<String, Object>> list = dealerOrderService.findByDealerOrder(dealerOrder);
        DateTime now = DateTime.now();
        for (Map<String, Object> order : list)
        {
            // 增加订单信息
            String orderId = (String) order.get("refrenceId");
            List<OrderShipRecord> recordlist = orderShipRecordMapper.getOrderShipRecord(orderId);
            if (null != recordlist && !recordlist.isEmpty())
            {
                OrderShipRecord orderShipRecord = recordlist.get(0);
                if (null != orderShipRecord)
                {
                    order.put("logisticName", orderShipRecord.getLogisticName());
                    order.put("shipNumber", orderShipRecord.getShipNumber());
                }
            }
            boolean isHasPaid = false;
            try
            {
                isHasPaid = orderPayService.hasPaid(orderId);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Integer payState = MapUtils.getInteger(order, "payState", 0);
            order.put("orderpaystatus", payState > 0 || isHasPaid);
            Short orderStatue = ((Integer) order.get("orderStatus")).shortValue();
            if (orderStatue.equals(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED) || orderStatue.equals(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED))
            {
                // 查询投诉的ID
                DealerComplaint dealerComplaint = dealerComplaintService.getDealerComplaint((String) order.get("refrenceId"));
                if (null != dealerComplaint)
                {
                    order.put("dealerComplaintId", dealerComplaint.getRefrenceId());
                }
                // 投诉期限判断
                Long updateTime = (Long) order.get("updateTime");
                DateTime lastUpdateTime = new DateTime(updateTime);
                if (order.get("complaintState") == null && now.minusDays(complainDays).isBefore(lastUpdateTime))
                {
                    order.put("complainable", true);
                }
                else
                {
                    order.put("complainable", false);
                }
            }
            List<Map<String, Object>> models = dealerOrdersService.getProductMap(dealerOrder.getDealerId(), orderId);
            order.put("items", models);
        }
        return list;
    }
    
    @Override
    public List<Map<String, Object>> findDealerOrderForDealerErp(DealerOrder dealerOrder) throws BusinessException
    {
        if (null == dealerOrder.getEndTimeLong()) { throw new BusinessException("结束时间不能为空"); }
        if (StringUtils.isBlank(dealerOrder.getDealerId())) { throw new BusinessException("经销商编号不能为空"); }
        List<Map<String, Object>> result = dealerOrderService.findbyDealerIdAndTimeRange(dealerOrder);
        return result;
    }
    
    /**
     * 品牌ERP订单发货询问接口
     *
     * @param map
     * @return
     * @throws BusinessException
     */
    @Override
    public JSONArray ordersAskBrandErp(Map<String, String> map) throws BusinessException
    {
        DealerOrdersModel dealerOrders = dealerOrdersService.updateOrderAsk(map);
        if (dealerOrders.isOrder_blank()) { return null; }
        return dealerOrders.getJsonArray();
    }
}
