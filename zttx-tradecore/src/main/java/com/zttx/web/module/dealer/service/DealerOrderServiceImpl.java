/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandPointBalance;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.mapper.BrandInfoMapper;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.brand.model.BrandFreightQueryModel;
import com.zttx.web.module.brand.model.BrandFreightResultModel;
import com.zttx.web.module.brand.model.SendGoodsAttributeModel;
import com.zttx.web.module.brand.model.SendGoodsModel;
import com.zttx.web.module.brand.service.*;
import com.zttx.web.module.common.entity.*;
import com.zttx.web.module.common.mapper.DataDictValueMapper;
import com.zttx.web.module.common.mapper.ProductCountMapper;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.common.service.StockAdjustDetailService;
import com.zttx.web.module.common.service.UserOperationLogService;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.mapper.*;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EntityUtils;
import com.zttx.web.utils.ListUtils;
import com.zttx.web.utils.NumericUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经销商订单信息 服务实现类
 * <p>File：DealerOrder.java </p>
 * <p>Title: DealerOrder </p>
 * <p>Description:DealerOrder </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerOrderServiceImpl extends GenericServiceApiImpl<DealerOrder> implements DealerOrderService
{
    private static int                  complainDays = 30;          // 投诉期限30天
    
    @Autowired
    private DealerOrdersMapper          dealerOrdersMapper;
    
    @Autowired
    private OrderShipRecordMapper       orderShipRecordMapper;
    
    @Autowired
    private DataDictValueMapper         dataDictValueMapper;
    
    @Autowired
    private BrandCountService           brandCountService;
    
    @Autowired
    private DealerCountService          dealerCountService;
    
    @Autowired
    private OrderNumberService          orderNumberService;
    
    @Autowired
    private BrandsCountService          brandsCountService;
    
    @Autowired
    private DealerOrderActionMapper     dealerOrderActionMapper;
    
    @Autowired
    private TradeDetailsMapper          tradeDetailsMapper;
    
    @Autowired
    private DealerShoperService         dealerShoperService;
    
    @Autowired
    private DealerShopersService        dealerShopersService;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private BrandFreightTemplateService brandFreightTemplateService;
    
    @Autowired
    private DealerAddrService           dealerAddrService;
    
    @Autowired
    private OrderShipRecordService      orderShipRecordService;
    
    @Autowired
    private OrderPayService             orderPayService;
    
    @Autowired
    private DealerComplaintService      dealerComplaintService;
    
    @Autowired
    private DealerOrdersService         dealerOrdersService;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private BrandInfoMapper             brandInfoMapper;
    
    @Autowired
    private DealerInfoMapper            dealerInfoMapper;
    
    @Autowired
    private ProductCountMapper          productCountMapper;
    
    @Autowired
    private DealerRefundMapper          dealerRefundMapper;
    
    @Autowired
    private DealerClearingService       dealerClearingService;
    
    @Autowired
    private HttpServletRequest          request;
    
    @Autowired
    private UserOperationLogService     userOperationLogService;
    
    @Autowired
    private UserInfoMapper              userInfoMapper;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private BrandesInfoMapper           brandesInfoMapper;
    
    @Autowired
    private DealerOrdercMapper          dealerOrdercMapper;
    
    @Autowired
    private BrandesInfoRegionalService  brandesInfoRegionalService;
    
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @Autowired
    private BrandPointBalanceService    brandPointBalanceService;

    @Autowired
    private StockAdjustDetailService stockAdjustDetailService;

    private DealerOrderMapper           dealerOrderMapper;

    @Autowired(required = true)
    public DealerOrderServiceImpl(DealerOrderMapper dealerOrderMapper)
    {
        super(dealerOrderMapper);
        this.dealerOrderMapper = dealerOrderMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getDealerOrderList(Pagination pagination, DealerOrderModel dealerOrder)
    {
        // 处理时间
        processTime(dealerOrder);
        // 设置分页
        dealerOrder.setPage(pagination);
        List<Map<String, Object>> list = dealerOrderMapper.getDealerOrderList(dealerOrder);
        PaginateResult<Map<String, Object>> page = new PaginateResult(pagination, list);
        if (null != list && !list.isEmpty())
        {
            for (Map<String, Object> item : list)
            {
                Long orderNumber = Long.parseLong(item.get("orderId").toString());
                DealerRefund refund = dealerRefundMapper.getEntityByOrderNumber(orderNumber);
                if (null != refund) item.put("refundId", refund.getRefundId());
                String orderId = String.valueOf(item.get("refrenceId"));
                String brandId = String.valueOf(item.get("brandId"));
                List<DealerOrders> dealerOrdersData = dealerOrdersMapper.findDifferentByBrandId(orderId, brandId);
                item.put("items", dealerOrdersData);
                Object obj = item.get("outActTime");
                if (null != obj)
                {
                    Long time = CalendarUtils.addHour(Long.parseLong(obj.toString()), 1);
                    item.put("outActTime", CalendarUtils.getStringTime(time, "yyyy-MM-dd HH"));
                }
            }
        }
        return page;
    }
    
    // 处理时间
    private void processTime(DealerOrderModel dealerOrder)
    {
        // 结束时间加上1天,并转换成时间戳
        String endTime = dealerOrder.getEndTimeStr();
        if (StringUtils.isNotBlank(endTime))
        {
            Long endTimeStamp = CalendarUtils.addDay(CalendarUtils.getLongFromTime(endTime), 1);
            dealerOrder.setEndTimeStr(endTimeStamp.toString());
        }
        // 开始时间
        String startTime = dealerOrder.getStartTimeStr();
        if (StringUtils.isNotBlank(startTime))
        {
            Long startTimeStamp = CalendarUtils.getLongFromTime(startTime);
            dealerOrder.setStartTimeStr(startTimeStamp.toString());
        }
    }
    
    @Override
    public DealerOrderModel findEntityBy(String refrenceId, String brandId)
    {
        return dealerOrderMapper.findEntityBy(refrenceId, brandId);
    }
    
    @Override
    public DealerOrderModel getByOrderIdAndBrandId(Long orderId, String brandId)
    {
        return dealerOrderMapper.getByOrderIdAndBrandId(orderId, brandId);
    }
    
    @Override
    public Boolean hasValidOrderByProId(String productId)
    {
        Integer count = dealerOrderMapper.hasValidOrderByProId(productId);
        if (count > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public DealerConst sendGoods(SendGoodsModel sendModel, Boolean sendAll) throws BusinessException
    {
        DealerConst error = DealerConst.SUCCESS;
        Long orderId = sendModel.getOrderId();
        String brandId = sendModel.getBrandId();
        DealerOrderModel order = this.getByOrderIdAndBrandId(orderId, brandId);
        if (null != order)
        {
            if (null != sendAll)
            {
                List<SendGoodsAttributeModel> sendListAtt = sendModel.getSendAtts();
                List<SendGoodsAttributeModel> listAtt = Lists.newArrayList();
                if (sendAll)
                {
                    listAtt = sendListAtt;
                }
                else
                {
                    if (!CollectionUtils.isEmpty(sendListAtt))
                    {
                        for (SendGoodsAttributeModel model : sendListAtt)
                        {
                            if (model.getIsSelect())
                            {
                                listAtt.add(model);
                            }
                        }
                    }
                }
                Integer totalNum = this._getTotalCount(listAtt);// 剩余发货总数
                if (sendAll)
                {// 全部发货
                    error = this.sendAll(brandId, order);
                }
                else
                // 部分发货
                {
                    error = this.sendPart(brandId, order, listAtt);
                }
                order.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerOrderMapper.updateByPrimaryKey(order);
                this.addShipRecord(sendModel, totalNum);// 新增发货记录
                // 新增订单操作记录
                DealerOrderAction dealerOrderAction = new DealerOrderAction();
                dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.SHIPGOODS);
                dealerOrderAction.setActName(DealerConstant.DealerOrderAction.SHIPGOODS_NAME);
                String content = "已发【" + totalNum + "】件  物流公司：" + sendModel.getLogisticName() + "  物流单号：" + sendModel.getShipNumber();
                dealerOrderAction.setActContent(content);
                dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
                dealerOrderAction.setOrderId(order.getRefrenceId());
                dealerOrderAction.setUserId(order.getBrandId());
                BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(order.getBrandId());
                dealerOrderAction.setUserName(brandInfo.getComName());
                dealerOrderActionMapper.insert(dealerOrderAction);
                // 用户操作日志
                UserOperationLog userOperationLog = new UserOperationLog();
                userOperationLog.setObjectId(dealerOrderAction.getOrderId());
                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                userOperationLog.setEvent(dealerOrderAction.getActContent());
                userOperationLogService.addUserOperationLog(request, userOperationLog);
                // crm免登陆品牌商后台操作--发货
                /*
                 * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
                 * if (StringUtils.isNotBlank(crm_userId))
                 * {
                 * BrandCRMLog brandCRMLog = new BrandCRMLog();
                 * brandCRMLog.setOperatorId(crm_userId);
                 * brandCRMLog.setBrandesId(order.getBrandsId());
                 * brandCRMLog.setBrandesName(order.getBrandsName());
                 * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
                 * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.SHIPMENTS); // 发货
                 * brandCRMLog.setOperationDetails("发货:'订单" + orderId + "','发货" + order.getShipCount() + "件';");
                 * brandCRMLogService.addCRMLog(request, brandCRMLog);
                 * }
                 */
            }
        }
        return error;
    }
    
    /**
     * 全部发货
     *
     * @param brandId 品牌商ID
     * @param order   DealerOrder 订单
     * @return DealerConst
     * @author 罗盛平
     */
    @SuppressWarnings("unchecked")
    private DealerConst sendAll(String brandId, DealerOrder order) throws BusinessException
    {
        DealerConst error = DealerConst.SUCCESS;
        DealerOrder dealerOrder = findEntityBy(order.getRefrenceId(), brandId);
        if (null != dealerOrder)
        {
            order.setShipCount(order.getProductCount());// 设置发货数量等于购买数量
            if (null != dealerOrder.getDealerOrderType() && dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_POINT)
            {
                order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);// 返点订单 全部发货完 直接交易成功
            }
            else
            {
                order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);// 设置订单状态位
            }
            List<DealerOrders> orderses = dealerOrdersMapper.findByOrderIdAndBrandId(order.getRefrenceId(), brandId);
            Long sendTime = CalendarUtils.getCurrentLong();
            for (DealerOrders orders : orderses)
            { // 将订单项里的发货数量全设置为购买数量
                orders.setUpdateTime(sendTime);
                orders.setOrderNo(order.getOrderId());    // 用于 返点财务帐统计
                orders.setSendNumEvery(orders.getQuantity()-orders.getShipCount()); // 用于 返点财务帐统计
                orders.setShipCount(orders.getQuantity());
                dealerOrdersMapper.updateByPrimaryKey(orders);
            }
            if (null == dealerOrder.getDealerOrderType() || dealerOrder.getDealerOrderType() != DealerConstant.DealerOrder.ORDER_TYPE_POINT)
            {
                // 设置下一个操作时间
                setOutActTime(order, null, null);
                /**交易明细**/
                tradeDetailsMapper.updateTradeDetails(order.getRefrenceId(), DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
                // 等待收货
                this._orderCount(order.getDealerId(), brandId, order.getBrandsId(), order);
            }
            if(DealerConstant.DealerOrder.ORDER_TYPE_POINT == dealerOrder.getDealerOrderType())
            {
                try {
                    //返点订单发货后，统计发货量到 返点财务账
                    stockAdjustDetailService.addStockAdjustDetail(StockAdjustDetail.TYPE_SEND, orderses);
                }catch (Exception e){
                    throw new BusinessException( CommonConst.FAIL.getCode(),"返点财务帐统计订单发货时异常："+e.getLocalizedMessage());
                }

            }
        }
        return error;
    }
    
    /**
     * 部分发货
     *
     * @param brandId 品牌商ID
     * @param order   DealerOrder 订单
     * @param listAtt List<SendGoodsAttributeModel>
     * @return DealerConst
     * @author 罗盛平
     */
    private DealerConst sendPart(String brandId, DealerOrder order, List<SendGoodsAttributeModel> listAtt) throws BusinessException
    {
        DealerConst error = DealerConst.SUCCESS;
        if (null != listAtt && !listAtt.isEmpty())
        {
            Integer newAddCount = 0;// 本次操作新增数量
            Map<String, Integer> mapAttr = Maps.newHashMap();
            for (SendGoodsAttributeModel sendGoodsAttributeModel : listAtt)
            {
                mapAttr.put(sendGoodsAttributeModel.getOrderItemId(), sendGoodsAttributeModel.getSendNum());
            }
            List<DealerOrders> orderses = dealerOrdersMapper.findByOrderIdAndBrandId(order.getRefrenceId(), brandId);
            List<DealerOrders> pointFinancial = Lists.newArrayList();   //用于返点财务帐统计 参数合成
            Long sendTime = CalendarUtils.getCurrentLong();
            for (DealerOrders orders : orderses)
            {// 设置部分发货的数量
                Integer shipCount = mapAttr.get(orders.getRefrenceId());
                if (shipCount != null)
                {
                    orders.setSendNumEvery(shipCount);  //用于 返点财务帐发货统计 stockAdjustDetail
                    newAddCount += shipCount;
                    if (null != orders.getShipCount())
                    {
                        orders.setShipCount(shipCount + orders.getShipCount());
                    }
                    else
                    {
                        orders.setShipCount(shipCount);
                    }
                    orders.setUpdateTime(sendTime);
                    dealerOrdersMapper.updateByPrimaryKey(orders);
                }

                //用于返点财务帐统计 参数合成
                if(DealerConstant.DealerOrder.ORDER_TYPE_POINT==order.getDealerOrderType() && null != shipCount && shipCount > 0)
                {
                    orders.setSendNumEvery(shipCount);
                    orders.setUpdateTime(sendTime);
                    orders.setOrderNo(order.getOrderId());
                    pointFinancial.add(orders);
                }
            }
            mapAttr.clear();
            order.setShipCount(order.getShipCount() + newAddCount);// 更新订单发货数量
            // 订单发货数量>=购买数量 则修改订单状态为 等待收货
            if (order.getShipCount().intValue() >= order.getProductCount().intValue())
            {
                if (null != order.getDealerOrderType() && order.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_POINT)
                {
                    order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);// 返点订单 全部发货完 直接交易成功
                }
                else
                {
                    order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);// 设置订单状态位
                    // 设置下一个操作时间
                    setOutActTime(order, null, null);
                    // 等待收货
                    this._orderCount(order.getDealerId(), brandId, order.getBrandsId(), order);
                    /**交易明细**/
                    tradeDetailsMapper.updateTradeDetails(order.getRefrenceId(), DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
                }
            }
            else
            {
                order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED);// 设置订单状态位
                // 部分发货
                /**交易明细**/
                tradeDetailsMapper.updateTradeDetails(order.getRefrenceId(), DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED);
            }
            if(DealerConstant.DealerOrder.ORDER_TYPE_POINT==order.getDealerOrderType())
            {
                try {
                    //返点订单，部分发货统计到 stockAdjustDetail 中 用于返点财务帐统计
                    stockAdjustDetailService.addStockAdjustDetail(StockAdjustDetail.TYPE_SEND,pointFinancial);
                }catch(Exception e){
                      throw new BusinessException( CommonConst.FAIL.getCode(),"返点财务帐统计订单发货时异常："+e.getLocalizedMessage());
                }
            }
        }
        return error;
    }
    
    @Override
    public void setOutActTime(DealerOrder order, String dictCode, String dictValueName)
    {
        if (StringUtils.isBlank(dictCode))
        {
            dictCode = "orderFixedDays";
        }
        if (StringUtils.isBlank(dictValueName))
        {
            dictValueName = "dealerOrderFixedDays";
        }
        DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName(dictCode, dictValueName);
        if (null != dataDictValue)
        {
            String dictValue = dataDictValue.getDictValue();
            Long time = CalendarUtils.getCurrentLong();
            if (StringUtils.isNotBlank(dictValue))
            {
                order.setOutActTime(CalendarUtils.addDay(time, Integer.parseInt(dictValue)));
            }
        }
    }
    
    /**
     * 订单计数
     *
     * @param dealerId 经销商ID
     * @param brandId  品牌商ID
     * @throws BusinessException
     * @author 罗盛平
     */
    private void _orderCount(String dealerId, String brandId, String brandsId, DealerOrder order) throws BusinessException
    {
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT);
        brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
        if (null == order.getRefundStatus() || DealerConstant.DealerRefund.CANCEL_REFUND == order.getRefundStatus().shortValue()
                || DealerConstant.DealerRefund.CLOSS_REFUND == order.getRefundStatus().shortValue())
        {
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
        }
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_ORDERCOUNT);
        brandsCountService.modifyBrandsCount(brandsId, countTypeNameList.toArray(new String[]{}));
    }
    
    /**
     * 获取模型发货总量
     *
     * @param listAtt List<SendGoodsAttributeModel>
     * @return Integer
     * @author 罗盛平
     */
    private Integer _getTotalCount(List<SendGoodsAttributeModel> listAtt)
    {
        Integer totalNum = 0;
        if (null != listAtt && !listAtt.isEmpty())
        {
            for (SendGoodsAttributeModel model : listAtt)
            {
                if (null != model.getSendNum() && model.getSendNum() > 0)
                {
                    totalNum += model.getSendNum();
                }
            }
        }
        return totalNum;
    }
    
    /**
     * 新增发货记录
     *
     * @param sendModel SendGoodsModel
     * @param totalSend Integer
     * @author 罗盛平
     */
    private void addShipRecord(SendGoodsModel sendModel, Integer totalSend)
    {
        OrderShipRecord record = new OrderShipRecord();
        String logisName = sendModel.getLogisticName();
        String logisNumber = sendModel.getShipNumber();
        String orderId = sendModel.getOrderRefrenceId();
        String brandId = sendModel.getBrandId();
        String brandName = sendModel.getBrandName();
        record.setBrandId(brandId);
        record.setBrandName(brandName);
        record.setCreateTime(CalendarUtils.getCurrentLong());
        record.setLogisticName(logisName);
        record.setOrderId(orderId);
        record.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        record.setShipCount(totalSend);
        record.setShipNumber(logisNumber);
        record.setSourceState(BrandConstant.OrderShipRecord.SOURCESTATE_ZTTX); // 发货来源
        orderShipRecordMapper.insert(record);// 新增发货记录
    }
    
    @Override
    public void updatePrice(BigDecimal discount, BigDecimal adjustPrice, BigDecimal adjustFreight, String brandId, String refrenceId, Short privilege_select)
            throws BusinessException
    {
        vilidBigDecimal(new BigDecimal[]{discount, adjustPrice, adjustFreight});
        // 优惠
        if (DealerConstant.DealerOrder.PRIVILEGE == privilege_select)
        {
            adjustPrice = adjustPrice.abs().multiply(new BigDecimal(-1));
        }
        else if (DealerConstant.DealerOrder.RISEPRICE == privilege_select) // 加价
        {
            adjustPrice = adjustPrice.abs();
        }
        else
        {
            throw new BusinessException(DealerConst.PRIVILEGE_SELECT_ERROR);
        }
        DealerOrder dealerOrder = dealerOrderMapper.findEntityBy(refrenceId, brandId);
        if (null == dealerOrder || null == dealerOrder.getOrderStatus()) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        vilidOrderState(dealerOrder.getOrderStatus(), DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
        BigDecimal productPrice = dealerOrder.getProductPrice();
        if (discount != null) // 打折
        {
            if (discount.compareTo(new BigDecimal(10)) == 0 || discount.compareTo(new BigDecimal(10)) == 1) { throw new BusinessException(DealerConst.DISCOUNT_ERROR); }
            adjustPrice = productPrice.multiply(discount).divide(new BigDecimal(10)).subtract(productPrice);
            validCountBigDe(productPrice, adjustPrice, adjustFreight.abs());
            dealerOrderMapper.updatePrice(NumericUtils.round(adjustPrice, 2), NumericUtils.round(adjustFreight, 2).abs(), refrenceId);
        }
        else if (null != adjustPrice) // 优惠价
        {
            validCountBigDe(productPrice, adjustPrice, adjustFreight.abs());
            dealerOrderMapper.updatePrice(NumericUtils.round(adjustPrice, 2), NumericUtils.round(adjustFreight, 2).abs(), refrenceId);
        }
        else
        // 原价
        {
            validCountBigDe(productPrice, new BigDecimal(0), adjustFreight.abs());
            dealerOrderMapper.updatePrice(new BigDecimal(0), NumericUtils.round(adjustFreight, 2).abs(), refrenceId);
        }
        // 修改订单更新时间
        dealerOrderMapper.updateOrderTime(refrenceId, CalendarUtils.getCurrentLong());
    }
    
    // 判断运费和优惠是否为负数
    // 判断是否超出范围
    private void vilidBigDecimal(BigDecimal[] value) throws BusinessException
    {
        for (BigDecimal bigDecimal : value)
        {
            if (bigDecimal == null)
            {
                continue;
            }
            if (bigDecimal.signum() == -1) { throw new BusinessException(DealerConst.PRICE_ERROR); }
            if (bigDecimal.compareTo(new BigDecimal(1000000000)) != -1) { throw new BusinessException(DealerConst.OVERTOP_MONEY); }
        }
    }
    
    // 校验状态
    private void vilidOrderState(short valiState, short ... state) throws BusinessException
    {
        boolean flag = false;
        for (short s : state)
        {
            if (s == valiState)
            {
                flag = true;
                break;
            }
        }
        if (!flag) { throw new BusinessException(DealerConst.ORDER_STATE_ERROR); }
    }
    
    // 计算是否超出预算
    private void validCountBigDe(BigDecimal productPrice, BigDecimal adjustPrice, BigDecimal adjustFreight) throws BusinessException
    {
        BigDecimal count = productPrice.add(adjustPrice).add(adjustFreight.abs());
        if (count.signum() == -1) { throw new BusinessException(DealerConst.OVERTOP_BUDGET); }
    }
    
    @Override
    public DealerOrderModel updatePrice(DealerOrderModel dealerOrder) throws BusinessException
    {
        /* String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID); */
        vilidBigDecimal(new BigDecimal[]{dealerOrder.getAdjustPrice(), dealerOrder.getAdjustFreight()});
        List<DealerOrder> dealerOrderList = dealerOrderMapper.getDealerOrderEntityList(dealerOrder);
        if (CollectionUtils.isEmpty(dealerOrderList)) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        DealerOrder oldDealerOrder = dealerOrderList.get(0);
        vilidOrderState(oldDealerOrder.getOrderStatus(), DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
        if (null == dealerOrder.getAdjustPrice())
        {
            if (null != oldDealerOrder.getAdjustPrice())
            {
                UserOperationLog userOperationLog = new UserOperationLog();
                userOperationLog.setObjectId(oldDealerOrder.getRefrenceId());
                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                userOperationLog.setEvent("【修改订单优惠价格：修改前：" + oldDealerOrder.getAdjustPrice() + " 修改后：0.00，订单号：" + oldDealerOrder.getOrderId() + "】");
                userOperationLogService.addUserOperationLog(request, userOperationLog);
            }
            oldDealerOrder.setAdjustPrice(new BigDecimal(0));
        }
        else
        {
            BigDecimal tempAdjustPrice = dealerOrder.getAdjustPrice().abs();
            if (DealerConstant.DealerOrder.PRIVILEGE == dealerOrder.getPrivilege_select().shortValue())
            {
                if (tempAdjustPrice.compareTo(oldDealerOrder.getProductPrice()) < 1)
                {
                    tempAdjustPrice = tempAdjustPrice.multiply(new BigDecimal(-1));
                }
                else
                {
                    throw new BusinessException(DealerConst.OVERTOP_BUDGET);
                }
            }
            if (null == oldDealerOrder.getAdjustPrice() || oldDealerOrder.getAdjustPrice().compareTo(NumericUtils.round(tempAdjustPrice, 2)) != 0)
            {
                UserOperationLog userOperationLog = new UserOperationLog();
                userOperationLog.setObjectId(oldDealerOrder.getRefrenceId());
                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                userOperationLog.setEvent("【修改订单优惠价格：修改前：" + (oldDealerOrder.getAdjustPrice() == null ? "" : oldDealerOrder.getAdjustPrice()) + " 修改后："
                        + NumericUtils.round(tempAdjustPrice, 2) + "，订单号：" + oldDealerOrder.getOrderId() + "】");
                userOperationLogService.addUserOperationLog(request, userOperationLog);
            }
            oldDealerOrder.setAdjustPrice(NumericUtils.round(tempAdjustPrice, 2));
        }
        if (null == dealerOrder.getAdjustFreight())
        {
            if (null != oldDealerOrder.getAdjustFreight())
            {
                UserOperationLog userOperationLog = new UserOperationLog();
                userOperationLog.setObjectId(oldDealerOrder.getRefrenceId());
                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                userOperationLog.setEvent("【修改订单运费：修改前：" + oldDealerOrder.getAdjustFreight() + " 修改后：0.00，订单号：" + oldDealerOrder.getOrderId() + "】");
                userOperationLogService.addUserOperationLog(request, userOperationLog);
            }
            oldDealerOrder.setAdjustFreight(null);
        }
        else
        {
            if (null == oldDealerOrder.getAdjustFreight() || oldDealerOrder.getAdjustFreight().compareTo(dealerOrder.getAdjustFreight()) != 0)
            {
                UserOperationLog userOperationLog = new UserOperationLog();
                userOperationLog.setObjectId(oldDealerOrder.getRefrenceId());
                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                userOperationLog.setEvent("【修改订单运费：修改前：" + (oldDealerOrder.getAdjustFreight() == null ? "" : oldDealerOrder.getAdjustFreight()) + " 修改后："
                        + new java.text.DecimalFormat("0.00").format(dealerOrder.getAdjustFreight()) + "，订单号：" + oldDealerOrder.getOrderId() + "】");
                userOperationLogService.addUserOperationLog(request, userOperationLog);
            }
            oldDealerOrder.setAdjustFreight(NumericUtils.round(dealerOrder.getAdjustFreight().abs(), 2));
        }
        BigDecimal tradeFreight = new BigDecimal("0");
        if (null != oldDealerOrder.getAdjustFreight())
        {
            tradeFreight = oldDealerOrder.getAdjustFreight();
        }
        BigDecimal balance = oldDealerOrder.getProductPrice().add(oldDealerOrder.getAdjustPrice()).add(tradeFreight);
        balance = balance.abs().setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal oldBalance = new BigDecimal("0");
        List<TradeDetails> detailsList = tradeDetailsMapper.listPaidByOrderId(oldDealerOrder.getRefrenceId());
        if (detailsList != null && detailsList.size() > 0)
        {
            for (TradeDetails tradeDetail : detailsList)
            {
                oldBalance = oldBalance.add(tradeDetail.getBalance());
            }
        }
        tradeDetailsMapper.updatePayDetails(oldDealerOrder.getRefrenceId(), null, balance);
        oldDealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderMapper.updateByPrimaryKey(oldDealerOrder);
        try
        {
            apportionDiscountToSku(oldDealerOrder, dealerOrder.getPrivilege_select());
        }
        catch (Exception e)
        {
            throw new BusinessException("分摊优惠(加价)出错:" + e.getLocalizedMessage());
        }
        DealerOrderModel newDealerOrder = EntityUtils.buildModelByEntity(DealerOrderModel.class, oldDealerOrder);
        newDealerOrder.setNewBalance(balance);
        newDealerOrder.setOldBalance(oldBalance);
        // crm免登陆品牌商后台操作-交易管理 修改订单金额
        /*
         * if (StringUtils.isNotBlank(crm_userId)) {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.UPDATE); // 修改交易订单金额
         * brandCRMLog.setOperationDetails("修改订单金额：'订单:" + newDealerOrder.getOrderId() + "','原订单金额" + newDealerOrder.getOldBalance() + "元'，现订单金额"
         * + newDealerOrder.getNewBalance() + "元';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
        return newDealerOrder;
    }
    
    /**
     * 将优惠/加价分摊到SKU
     *
     * @param dealerOrder
     * @param type        优惠:0/加价:1
     * @author 李星
     */
    private void apportionDiscountToSku(DealerOrder dealerOrder, Short type)
    {
        List<DealerOrders> dealerOrdersList = dealerOrdersMapper.findByOrderIdAndBrandId(dealerOrder.getRefrenceId(), dealerOrder.getBrandId());
        // 当订单有优惠(加价)
        if (null != dealerOrder.getAdjustPrice() && dealerOrder.getAdjustPrice().abs().compareTo(new BigDecimal(0)) >= 0)
        {
            BigDecimal totalSkuMoney = getTotalSkuMoney(dealerOrdersList);
            int c = 0;
            BigDecimal adjustMoney = dealerOrder.getAdjustPrice().abs(); // 订单优惠(加价)的数值
            BigDecimal tempAdjustMoney = adjustMoney;
            for (DealerOrders dealerOrders : dealerOrdersList)
            {
                BigDecimal skuMoney = getSkuMoney(dealerOrders);
                BigDecimal discount = skuMoney.subtract(tempAdjustMoney);
                if (c < dealerOrdersList.size() - 1)
                {
                    // 分摊比例,保留2位小数,四舍五入
                    /*
                     * BigDecimal ratio = skuMoney.divide(totalSkuMoney, 2, BigDecimal.ROUND_HALF_EVEN);
                     * BigDecimal willDiscount = adjustMoney.multiply(ratio);
                     */
                    BigDecimal willDiscount = adjustMoney.multiply(skuMoney).divide(totalSkuMoney, 2, BigDecimal.ROUND_HALF_EVEN);
                    // 优惠
                    if (type == DealerConstant.DealerOrder.PRIVILEGE) discount = skuMoney.subtract(willDiscount.abs());
                    // 加价
                    else discount = skuMoney.add(willDiscount.abs());
                    // 逐步减去已经分摊的;最后一个SKU的优惠价格不采用比例换算,采用总优惠价格-前面已优惠价格计算
                    tempAdjustMoney = tempAdjustMoney.subtract(willDiscount.abs());
                }
                // 保存优惠/加价后的SKU价格
                dealerOrders.setAdjustPrice(discount);
                dealerOrdersMapper.updateByPrimaryKey(dealerOrders);
                c++;
            }
        }
    }
    
    /**
     * 获得订单所有SKU金额汇总值
     *
     * @param dealerOrdersList
     * @return
     * @author 李星
     */
    private BigDecimal getTotalSkuMoney(List<DealerOrders> dealerOrdersList)
    {
        BigDecimal totalSkuMoney = new BigDecimal(0);
        for (DealerOrders dealerOrders : dealerOrdersList)
        {
            totalSkuMoney = totalSkuMoney.add(getSkuMoney(dealerOrders));
        }
        return totalSkuMoney;
    }
    
    /**
     * 获取SKU金额
     *
     * @param dealerOrders
     * @return
     * @author 李星
     */
    private BigDecimal getSkuMoney(DealerOrders dealerOrders)
    {
        BigDecimal quantity = new BigDecimal(dealerOrders.getQuantity());
        BigDecimal skuMoney = dealerOrders.getPrice().multiply(quantity);
        // 有折扣
        if (dealerOrders.getDiscount() != null && dealerOrders.getDiscount().compareTo(BigDecimal.ZERO) != 0)
        {
            skuMoney = dealerOrders.getPrice().multiply(quantity).multiply(dealerOrders.getDiscount());
        }
        return skuMoney;
    }
    
    @Override
    public DealerOrderModel updateFare(BigDecimal adjustFreight, String brandId, String refrenceId, String brandsId) throws BusinessException
    {
        DealerOrderModel dealerOrder = dealerOrderMapper.findEntityBy(refrenceId, brandId);
        dealerOrder.setTmpAdjust(dealerOrder.getAdjustFreight());// 将原有的运费加入到临时对象中
        vilidOrderState(dealerOrder.getFrePayState(), DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY);
        vilidOrderState(dealerOrder.getOrderStatus(), DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY, DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER,
                DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED, DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        BigDecimal setFreight = null;
        if (null != adjustFreight)
        {
            vilidBigDecimal(new BigDecimal[]{adjustFreight});
            setFreight = NumericUtils.round(adjustFreight, 2).abs();
            dealerOrder.setAdjustFreight(setFreight);
            if (DealerConstant.DealerOrder.PAY_STATE_ALL == dealerOrder.getPayState().shortValue() && adjustFreight.compareTo(BigDecimal.ZERO) == 0)
            {
                dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED);
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT);
                dealerCountService.modifyDealerCount(dealerOrder.getDealerId(), countTypeList.toArray(new Integer[]{}));
                countTypeList.clear();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT);
                brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
                // 货款已支付完成，并且是运费为0时修改下一次操作时间
                if (null == dealerOrder.getIsAdvancePayment() || !dealerOrder.getIsAdvancePayment())
                {
                    setOutActTime(dealerOrder, null, "orderNoShipRefund");
                }
            }
        }
        else
        {
            dealerOrder.setAdjustFreight(null);
        }
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        BigDecimal tradeFreight = new BigDecimal("0");
        if (null != dealerOrder.getAdjustFreight())
        {
            tradeFreight = dealerOrder.getAdjustFreight();
        }
        BigDecimal balance = dealerOrder.getProductPrice().add(dealerOrder.getAdjustPrice()).add(tradeFreight);
        balance = balance.abs().setScale(2, BigDecimal.ROUND_HALF_UP);
        Short payState = TradeDetailsConst.PAY_STATE_PART;
        if (DealerConstant.DealerOrder.PAY_STATE_ALL == dealerOrder.getPayState().shortValue()
                && dealerOrder.getFrePayState() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED && dealerOrder.getPayment().compareTo(balance) == 0)
        {
            payState = TradeDetailsConst.PAY_STATE_ALL;
        }
        tradeDetailsMapper.updatePayDetails(refrenceId, payState, balance);
        // todo 日志：crm免登陆到品牌商后台操作--修改订单运费
        /*
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBrandesId(brandsId);
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.UPDATE); // 修改运费
         * brandCRMLog.setOperationDetails("修改订单运费：'订单:" + dealerOrder.getOrderId() + "','原运费" + dealerOrder.getTmpAdjust() + "元'，'修改后的运费"
         * + dealerOrder.getAdjustFreight() + "元';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
        return dealerOrder;
    }
    
    @Override
    public void updateBrandRemark(String[] refrenceId, String brandId, String remark, Short levelMark) throws BusinessException
    {
        for (String id : refrenceId)
        {
            dealerOrderMapper.updateBrandRemark(id, remark, brandId, levelMark);
            dealerOrderMapper.updateOrderTime(id, CalendarUtils.getCurrentLong());
        }
    }
    
    @Override
    public void updateOutActTime(Long outActTimeDelay, String brandId, String refrenceId) throws BusinessException
    {
        DealerOrder dealerOrder = dealerOrderMapper.findEntityBy(refrenceId, brandId);
        if (null == dealerOrder) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        vilidOrderState(dealerOrder.getOrderStatus(), DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED, DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        Long outActTime = dealerOrder.getOutActTime() == null ? 0L : dealerOrder.getOutActTime();
        outActTime = CalendarUtils.addDay(outActTime, outActTimeDelay.intValue());
        dealerOrderMapper.updateOutActTime(outActTime, refrenceId);
    }
    
    @Override
    public void addPointBalance(DealerOrder dealerOrder, BigDecimal pointBalance) throws BusinessException
    {
        if (null == dealerOrder) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (null == pointBalance || pointBalance.compareTo(BigDecimal.ZERO) < 0) { return; }
        BigDecimal pointBalanceAmount = dealerOrder.getPointBalanceAmount();
        pointBalanceAmount = null == pointBalanceAmount ? BigDecimal.ZERO : pointBalanceAmount;
        dealerOrder.setPointBalanceAmount(pointBalanceAmount.add(pointBalance));
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderMapper.updateByPrimaryKey(dealerOrder);
    }
    
    @Override
    public void updateAddr(DealerOrder dealerOrder, String brandId, Long orderId) throws BusinessException
    {
        DealerOrder oldoOrder = dealerOrderMapper.getByOrderIdAndBrandId(orderId, brandId);
        if (oldoOrder.getFrePayState().shortValue() == DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY
                && oldoOrder.getOrderStatus().shortValue() <= DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER)
        {
            oldoOrder.setDealerAddrProvince(dealerOrder.getDealerAddrProvince());
            oldoOrder.setDealerAddrCity(dealerOrder.getDealerAddrCity());
            oldoOrder.setDealerAddrArea(dealerOrder.getDealerAddrArea());
            oldoOrder.setDealerAddress(dealerOrder.getDealerAddress());
            oldoOrder.setShipName(dealerOrder.getShipName());
            oldoOrder.setDealerMobile(dealerOrder.getDealerMobile());
            oldoOrder.setDealerTel(dealerOrder.getDealerTel());
            oldoOrder.setAreaNo(dealerOrder.getAreaNo());
            oldoOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(oldoOrder);
        }
    }
    
    /**
     * 生成交易明细记录
     *
     * @param dealerOrder
     * @return
     * @author 夏铭
     */
    private TradeDetails generateTradeDetails(DealerOrder dealerOrder)
    {
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        BigDecimal adjustPrice = dealerOrder.getAdjustPrice() == null ? new BigDecimal(0) : dealerOrder.getAdjustPrice();
        BigDecimal adjustFreight = dealerOrder.getAdjustFreight() == null ? new BigDecimal(0) : dealerOrder.getAdjustFreight();
        tradeDetails.setBalance(NumericUtils.roundCurrency(dealerOrder.getProductPrice().add(adjustPrice).add(adjustFreight)));
        tradeDetails.setCreateTime(dealerOrder.getCreateTime());
        tradeDetails.setUpdatetime(dealerOrder.getUpdateTime());
        tradeDetails.setOrderId(dealerOrder.getRefrenceId());
        tradeDetails.setTitle("进货（订单号:" + dealerOrder.getOrderId() + "）");
        tradeDetails.setSerialNumber(String.valueOf(dealerOrder.getOrderId()));
        tradeDetails.setSendUserId(dealerOrder.getDealerId());
        tradeDetails.setRecUserId(dealerOrder.getBrandId());
        tradeDetails.setTradeState(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
        tradeDetails.setTradeType(TradeDetailsConst.TRADE_TYPE_ORDER);
        return tradeDetails;
    }
    
    @Override
    public List<DealerOrder> addDealerOrder(String dealerId, String addressId, String[] dealerShoperIds, Map<String, String> remarksMap, Map<String, String> freightMap)
            throws BusinessException
    {
        List<DealerShoper> dealerShoperList = dealerShoperService.getPurchaseDealerShoperList(dealerId, dealerShoperIds);
        DealerAddr dealerAddr = dealerAddrService.selectByPrimaryKey(addressId);
        if (dealerAddr == null) { throw new BusinessException(CommonConst.ADDRESS_NOT_EXIST); }
        List<DealerOrder> dealerOrderList = Lists.newArrayList(); // 最终要保存的订单列表
        List<DealerOrders> dealerOrdersList = Lists.newArrayList(); // 最终要保存的订单明细列表
        // List<TradeDetails> tradeDetailsList = Lists.newArrayList(); // 最终要保存的交易明细列表
        Map<String, List<DealerOrders>> ordersList = this.shopperTransOrder(dealerShoperList); // 按品牌(拿样、普通、授信) 分类
        for (String _brandsId : ordersList.keySet())
        {
            String[] brandsIdArr = _brandsId.split("_");
            String brandsId = brandsIdArr[0];
            String productType = brandsIdArr[brandsIdArr.length - 1];
            List<DealerOrders> _dealerordersList = ordersList.get(_brandsId);
            DealerOrder dealerOrder = createDealerOrder(dealerId, brandsId, productType, MapUtils.getString(freightMap, _brandsId, ""),
                    MapUtils.getString(remarksMap, _brandsId, ""), _dealerordersList, dealerAddr);
            if (null == dealerOrder)
            {
                continue;
            }
            // TradeDetails tradeDetails = generateTradeDetails(dealerOrder);
            // tradeDetailsList.add(tradeDetails);
            dealerOrderList.add(dealerOrder);
            dealerOrdersList.addAll(_dealerordersList);
        }
        this.saveOrder(dealerOrderList, dealerOrdersList);
        // 授信订单 更新 实时授信额度
        /* updateCreditCurrent(dealerOrderList); */
        // 同步dealerOrderc的相关数据操作
        addDealerOrderc(dealerOrdersList, dealerId);
        // 移除进货单列表 吕海斌
        removeDealerShopper(Lists.newArrayList(dealerShoperIds), dealerId);
        // 为每一个订单 增加操作记录
        addDealerOrderAction(dealerOrderList, dealerId);
        return dealerOrderList;
    }
    
    @Override
    public String addDealerOrder(String dealerId, String addressId, DealerShoper dealerShoper, Map<String, String> remarksMap, Map<String, String> freightMap)
            throws BusinessException
    {
        DealerAddr dealerAddr = dealerAddrService.selectByPrimaryKey(addressId);
        if (dealerAddr == null) { throw new BusinessException(CommonConst.ADDRESS_NOT_EXIST); }
        List<DealerOrders> dealerOrdersList = this.shopperTransOrder(dealerShoper);
        String _brandsId = dealerShoper.getBrandsId();
        String[] brandsIdArr = _brandsId.split("_");
        String brandsId = brandsIdArr[0];
        String productType = brandsIdArr[brandsIdArr.length - 1];
        DealerOrder dealerOrder = createDealerOrder(dealerId, brandsId, productType, MapUtils.getString(freightMap, _brandsId, ""),
                MapUtils.getString(remarksMap, _brandsId, ""), dealerOrdersList, dealerAddr);
        if (null == dealerOrder) { throw new BusinessException(CommonConst.FAIL.code, "生成订单失败"); }
        this.saveOrder(Lists.newArrayList(dealerOrder), dealerOrdersList);
        // 同步dealerOrderc的相关数据操作
        addDealerOrderc(dealerOrdersList, dealerId);
        // 为每一个订单 增加操作记录
        addDealerOrderAction(dealerOrder, dealerId);
        return dealerOrder.getRefrenceId();
    }
    
    /**
     * 订单按照品牌为单位生成（品牌中分为 拿样，普通，授信，返点）
     * @param dealerId
     * @param brandsId
     * @param productType
     * @param freightType
     * @param remark
     * @param dealerOrdersList
     * @param dealerAddr
     * @return
     * @throws BusinessException
     */
    private DealerOrder createDealerOrder(String dealerId, String brandsId, String productType, String freightType, String remark, List<DealerOrders> dealerOrdersList,
            DealerAddr dealerAddr) throws BusinessException
    {
        DealerOrder dealerOrder = null;
        boolean isPoint = false;
        if (null != dealerOrdersList && !dealerOrdersList.isEmpty())
        {
            dealerOrder = new DealerOrder();
            dealerOrder.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerOrder.setOrderId(com.zttx.web.utils.SerialnoUtils.buildOrderSN(orderNumberService.execute()));
            dealerOrder.setDealerId(dealerId);
            dealerOrder.setBrandId(dealerOrdersList.get(0).getBrandId());
            dealerOrder.setBrandsId(brandsId);
            if (Short.valueOf(productType) == DealerConstant.DealerShoper.PRODUCTTYPE_SAM) // 拿样
            {
                dealerOrder.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                dealerOrder.setIsSampleOrder(true);
            }
            else if (Short.valueOf(productType) == DealerConstant.DealerShoper.PRODUCTTYPE_CASH)
            { // 现款
                dealerOrder.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                dealerOrder.setIsSampleOrder(false);
            }
            else if (Short.valueOf(productType) == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT) // 授信
            {
                dealerOrder.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_CREDIT);
                dealerOrder.setIsSampleOrder(false);
            }
            else if (Short.valueOf(productType) == DealerConstant.DealerShoper.PRODUCTTYPE_POINT)
            {
                dealerOrder.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_POINT);
                dealerOrder.setIsSampleOrder(false);
                isPoint = true;// 返点订单
            }
            BigDecimal totalPrice = BigDecimal.ZERO;
            Integer totalNum = 0;
            BigDecimal totalWeight = BigDecimal.ZERO;
            BigDecimal minPoint = BigDecimal.ZERO;
            BrandFreightQueryModel brandFreightQueryModel = new BrandFreightQueryModel();
            for (DealerOrders dealerOrders : dealerOrdersList)
            {
                dealerOrders.setOrderId(dealerOrder.getRefrenceId());
                dealerOrders.setBrandsId(dealerOrder.getBrandsId());
                totalPrice = totalPrice.add(dealerOrders.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP)); // 4舍5入两位小数
                totalNum += dealerOrders.getQuantity();
                // 必须放在dealerOrders 中循环取出重量和， 因为一个订单中会含有多个产品，一个产品含有多个sku
                ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(dealerOrders.getProductId());
                if (null == productBaseInfo || null == productBaseInfo.getProductExtInfo()) { throw new BusinessException("产品：" + dealerOrders.getProductId() + "不存在"); }
                double weight = (productBaseInfo.getProductExtInfo().getProductWeight() == null ? 0 : productBaseInfo.getProductExtInfo().getProductWeight());
                totalWeight = totalWeight.add(new BigDecimal(weight).multiply(new BigDecimal(dealerOrders.getQuantity())));
                if (minPoint.compareTo(BigDecimal.ZERO) == 0)
                {
                    minPoint = dealerOrders.getPoint();
                }
                else
                {
                    minPoint = BigDecimal.valueOf(Math.min(dealerOrders.getPoint().doubleValue(), minPoint.doubleValue()));
                }
                brandFreightQueryModel.addProductParam(dealerOrders.getProductId(), dealerOrders.getQuantity());
                if (dealerOrder.getDealerOrderType() == DealerConstant.DealerShoper.PRODUCTTYPE_SAM)
                {
                    // 拿样日志 ，个人觉得多余
                }
            }
            dealerOrder.setProductPrice(totalPrice);
            dealerOrder.setProductCount(totalNum);
            dealerOrder.setMinPoint(minPoint);
            dealerOrder.setProductWeight(totalWeight);
            dealerOrder.setShipCount(0);
            dealerOrder.setReceiveCount(0);
            dealerOrder.setAdjustPrice(BigDecimal.ZERO);
            dealerOrder.setFreight(BigDecimal.ZERO);
            dealerOrder.setSourceId("0");
            if (totalNum > 0)
            {
                brandFreightQueryModel.setAreaNo(dealerAddr.getDealerAddr());
                if (isPoint)// 返点 品牌商承担运费
                {
                    dealerOrder.setAdjustFreight(null);
                    dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED);
                    dealerOrder.setFrePayType(BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT.shortValue());
                }
                else
                {
                    BrandFreightResultModel _resultModel = brandFreightTemplateService.getFreightAmount(brandFreightQueryModel, Integer.parseInt(freightType));
                    dealerOrder.setAdjustFreight(_resultModel.getFreightAmount());
                    dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY);
                    dealerOrder.setFrePayType(_resultModel.getCarryType().shortValue());
                }
                dealerOrder.setPayment(BigDecimal.ZERO);
                dealerOrder.setShipName(dealerAddr.getDealerName());
                dealerOrder.setAreaNo(dealerAddr.getDealerAddr());
                dealerOrder.setDealerAddrProvince(dealerAddr.getProvinceName());
                dealerOrder.setDealerAddrCity(dealerAddr.getCityName());
                dealerOrder.setDealerAddrArea(dealerAddr.getAreaName());
                dealerOrder.setDealerAddress(dealerAddr.getDealerAddress());
                dealerOrder.setPostCode(dealerAddr.getPostCode());
                dealerOrder.setDealerMobile(dealerAddr.getDealerMobile());
                dealerOrder.setDealerTel(dealerAddr.getDealerTel());
                dealerOrder.setCreateTime(CalendarUtils.getCurrentLong());
                dealerOrder.setUpdateTime(dealerOrder.getCreateTime());
                // 2015/11/24 返点类型订单 直接是等待发货状态
                dealerOrder.setOrderStatus(isPoint ? DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER : DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY);
                // 2015/11/24 返点类型订单 直接是已支付状态
                dealerOrder.setPayState(isPoint ? DealerConstant.DealerOrder.PAY_STATE_ALL : DealerConstant.DealerOrder.PAY_STATE_UNPAY);
                dealerOrder.setRemark(remark);
                dealerOrder.setIsAdvancePayment(false);
            }
        }
        return dealerOrder;
    }
    
    @Override
    public Map<String, Object> selectCreditCurrent(DealerOrder dealerOrder) throws BusinessException
    {
        BigDecimal creditCurrent = BigDecimal.ZERO;
        BigDecimal payCash = BigDecimal.ZERO;
        Boolean isPaid = false;
        if (null != dealerOrder && dealerOrder.getDealerOrderType().equals(DealerConstant.DealerOrder.ORDER_TYPE_CREDIT))
        {
            DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
            if (null == dealerJoin || !dealerJoin.getJoinForm().equals(DealerConstant.DealerJoin.JOINFORM_CREDIT) || !dealerJoin.getIsPaid()
                    || dealerJoin.getPaidAmount().compareTo(dealerJoin.getDepositTotalAmount()) < 0) { throw new BusinessException(CommonConst.FAIL_JOIN_CREDIT); }
            creditCurrent = dealerJoin.getCreditAmount().subtract(dealerJoin.getCreditCurrent());
            if (creditCurrent.compareTo(dealerOrder.getProductPrice()) != 1 && creditCurrent.compareTo(BigDecimal.ZERO) != -1) // 剩余可用授信额度不大于订单支付款
            {
                isPaid = true;
                payCash = dealerOrder.getProductPrice().subtract(creditCurrent);
            }
            if (creditCurrent.compareTo(BigDecimal.ZERO) == -1)
            {
                payCash = dealerOrder.getProductPrice();
            }
            if (dealerOrder.getFrePayState() == DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY)
            {
                payCash = payCash.add(dealerOrder.getAdjustFreight());
            }
        }
        Map<String, Object> creditMap = Maps.newHashMap();
        creditMap.put("creditCurrent", creditCurrent); // 实时授信额度
        creditMap.put("payCash", payCash); // 授信额度不足，补交现金额度
        creditMap.put("isPaid", isPaid); // 是否是授信额度不足
        return creditMap;
    }
    
    /*
     * 更新授信金额
     */
    private void updateCreditCurrent(List<DealerOrder> dealerOrderList) throws BusinessException
    {
        if (!dealerOrderList.isEmpty())
        {
            for (DealerOrder dealerOrder : dealerOrderList)
            {
                if (dealerOrder.getDealerOrderType().equals(DealerConstant.DealerOrder.ORDER_TYPE_CREDIT))
                {
                    DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
                    if (null == dealerJoin || !dealerJoin.getJoinForm().equals(DealerConstant.DealerJoin.JOINFORM_CREDIT)) { throw new BusinessException(
                            CommonConst.FAIL_JOIN_CREDIT); }
                    BigDecimal creditCurrent = dealerJoin.getCreditCurrent().subtract(dealerOrder.getProductPrice());
                    if (creditCurrent.compareTo(BigDecimal.ZERO) == -1) { throw new BusinessException(CommonConst.FAIL_CREDIT_CURRENT); }
                    dealerJoin.setCreditCurrent(creditCurrent);
                    dealerJoinService.updateByPrimaryKeySelective(dealerJoin);
                }
            }
        }
    }
    
    /**
     * 移除进货单记录
     */
    private void removeDealerShopper(List<String> idList, String dealerId)
    {
        try
        {
            dealerShoperService.batchRemoveShopers(idList, dealerId);
        }
        catch (Exception e)
        {
            throw new RuntimeException("系统异常" + e);
        }
    }
    
    /**
     * 添加常进款式
     */
    private void addDealerOrderc(List<DealerOrders> dealerOrdersList, String dealerId)
    {
        for (DealerOrders dealerOrders : dealerOrdersList)
        {
            DealerOrderc dealerOrderc = new DealerOrderc();
            dealerOrderc.setDealerId(dealerId);
            dealerOrderc.setBrandId(dealerOrders.getBrandId());
            dealerOrderc.setBrandsId(dealerOrders.getBrandsId());
            dealerOrderc.setProductId(dealerOrders.getProductId());
            dealerOrderc = dealerOrdercMapper.selectByIds(dealerOrderc);
            // 不存在时
            if (dealerOrderc == null)
            {
                dealerOrderc = new DealerOrderc();
                dealerOrderc.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerOrderc.setBrandId(dealerOrders.getBrandId());
                dealerOrderc.setDealerId(dealerOrders.getDealerId());
                dealerOrderc.setProductId(dealerOrders.getProductId());
                dealerOrderc.setBrandsId(dealerOrders.getBrandsId());
                dealerOrderc.setOrderNum(dealerOrders.getQuantity());
                dealerOrderc.setOrderTime(CalendarUtils.getCurrentLong());
                dealerOrderc.setJoinNum(1);
                dealerOrderc.setSubNum(dealerOrders.getQuantity());
                dealerOrderc.setSubTime(1);
                dealerOrdercMapper.insert(dealerOrderc);
            }
            else
            {
                dealerOrderc.setJoinNum(dealerOrderc.getJoinNum() + 1);
                dealerOrderc.setSubNum(dealerOrderc.getSubNum() + dealerOrders.getQuantity());
                dealerOrderc.setSubTime(dealerOrderc.getSubTime() + 1);
                dealerOrderc.setOrderNum(dealerOrderc.getOrderNum() + dealerOrders.getQuantity());
                dealerOrderc.setOrderTime(CalendarUtils.getCurrentLong());
                dealerOrdercMapper.updateByPrimaryKeySelective(dealerOrderc);
            }
        }
    }
    
    /**
     * 订单增加操作记录
     */
    private void addDealerOrderAction(DealerOrder dealerOrder, String dealerId) throws BusinessException
    {
        // 结算订单 统计+1
        // dealerCountCache.updateDealerCount(3, dealerId, 1);
        List<Integer> countTypeList = Lists.newArrayList();
        if (DealerConstant.DealerOrder.ORDER_TYPE_POINT != dealerOrder.getDealerOrderType())// 返点订单直接支付完成 待发货
        {
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT);
        }
        dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
        // 等待付款 统计+1
        // brandCountCache.updateBrandCount(3, dealerOrder.getBrandId(), 1);
        countTypeList.clear();
        // 返点订单直接支付完成 待发货
        countTypeList.add(DealerConstant.DealerOrder.ORDER_TYPE_POINT == dealerOrder.getDealerOrderType() ? BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT
                : BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT);
        brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // 预付订单 统计+1
        if (dealerOrder.getIsAdvancePayment())
        {
            // brandCountCache.updateBrandCount(4, dealerOrder.getBrandId(),
            // 1);
            countTypeList.clear();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREORDERCOUNT);
            brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        }
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode("createorder");
        dealerOrderAction.setActName("创建订单");
        dealerOrderAction.setActContent("订单号:" + dealerOrder.getOrderId());
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(dealerOrder.getRefrenceId());
        dealerOrderAction.setUserId(dealerId);
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerId);
        dealerOrderAction.setUserName(dealerInfo.getDealerName());
        dealerOrderActionMapper.insert(dealerOrderAction);
    }
    
    /**
     * 订单增加操作记录
     */
    private void addDealerOrderAction(List<DealerOrder> dealerOrderList, String dealerId) throws BusinessException
    {
        if (!ListUtils.isNotEmpty(dealerOrderList)) { return; }
        for (DealerOrder dealerOrder : dealerOrderList)
        {
            addDealerOrderAction(dealerOrder, dealerId);
        }
    }
    
    private void saveOrder(List<DealerOrder> orderList, List<DealerOrders> dealerOrdersList)
    {
        dealerOrderMapper.insertBatch(orderList);
        dealerOrdersMapper.insertBatch(dealerOrdersList);
    }
    
    private Map<String, List<DealerOrders>> shopperTransOrder(List<DealerShoper> dealerShoperList) throws BusinessException
    {
        Map<String, List<DealerOrders>> resultMap = new HashMap<>();
        for (DealerShoper dealerShoper : dealerShoperList)
        {
            String brandsId = dealerShoper.getBrandsId();
            // 获取扣点
            short joinForm = 1;
            if (dealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT)
            {
                joinForm = 2;
            }
            BrandPointBalance _brandPointBalance = brandPointBalanceService.findPointBalance(dealerShoper.getBrandsId().substring(0, 32), joinForm);
            if (null == _brandPointBalance || _brandPointBalance.getPoint().compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.ORDER_PAY_POINT); }
            List<DealerShopers> dealerShopersList = dealerShopersService.selectDealerShopersBy(dealerShoper.getRefrenceId(), dealerShoper.getProductType());
            // this.countDealerOrdersToDealerOrder(dealerShopersList);
            if (ListUtils.isNotEmpty(dealerShopersList))
            {
                List<DealerOrders> dealerOrdersList = resultMap.get(brandsId);
                if (null == dealerOrdersList)
                {
                    dealerOrdersList = new ArrayList<>();
                    resultMap.put(brandsId, dealerOrdersList);
                }
                for (DealerShopers dealerShopers : dealerShopersList)
                {
                    DealerOrders dealerOrders = createDealerOrders(dealerShoper, _brandPointBalance, dealerShopers);
                    dealerOrdersList.add(dealerOrders);
                }
            }
        }
        return resultMap;
    }
    
    private List<DealerOrders> shopperTransOrder(DealerShoper dealerShoper) throws BusinessException
    {
        List<DealerOrders> dealerOrdersList = Lists.newArrayList();
        // 获取扣点
        short joinForm = 1;
        if (dealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT)
        {
            joinForm = 2;
        }
        BrandPointBalance _brandPointBalance = brandPointBalanceService.findPointBalance(dealerShoper.getBrandsId().substring(0, 32), joinForm);
        if (null == _brandPointBalance || _brandPointBalance.getPoint().compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.ORDER_PAY_POINT); }
        List<DealerShopers> dealerShopersList = dealerShoper.getDealerShopersList();
        if (ListUtils.isNotEmpty(dealerShopersList))
        {
            for (DealerShopers dealerShopers : dealerShopersList)
            {
                DealerOrders dealerOrders = createDealerOrders(dealerShoper, _brandPointBalance, dealerShopers);
                dealerOrdersList.add(dealerOrders);
            }
        }
        return dealerOrdersList;
    }
    
    private DealerOrders createDealerOrders(DealerShoper dealerShoper, BrandPointBalance brandPointBalance, DealerShopers dealerShopers)
    {
        DealerOrders dealerOrders = new DealerOrders();
        dealerOrders.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrders.setBrandId(dealerShoper.getBrandId());
        dealerOrders.setDealerId(dealerShoper.getDealerId());
        dealerOrders.setProductId(dealerShoper.getProductId());
        dealerOrders.setProductTitle(dealerShoper.getProductTitle());
        dealerOrders.setProductNo(dealerShoper.getProductNo());
        dealerOrders.setProductImage(dealerShoper.getProductImage());
        dealerOrders.setPrice(dealerShopers.getProductSkuPrice());
        dealerOrders.setQuantity(dealerShopers.getPurchaseNum());
        dealerOrders.setDiscount(dealerShoper.getDiscount());
        if (dealerShoper.getIsPoint())
        {
            dealerOrders.setPointPercent(dealerShoper.getPointPercent());
            dealerOrders.setTotalAmount(dealerOrders.getPrice().multiply(BigDecimal.ONE.subtract(dealerOrders.getPointPercent()))
                    .multiply(new BigDecimal(dealerOrders.getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP)); // 4舍5入
        }
        else
        {
            dealerOrders.setTotalAmount(dealerOrders.getPrice().multiply(dealerOrders.getDiscount()).multiply(new BigDecimal(dealerOrders.getQuantity()))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)); // 4舍5入
        }
        dealerOrders.setDiscountPrice(dealerOrders.getPrice().multiply(BigDecimal.ONE.subtract(dealerOrders.getDiscount()))
                .multiply(new BigDecimal(dealerOrders.getQuantity())));
        dealerOrders.setShipCount(0);// 与订单发货数量保持一致 默认0
        dealerOrders.setProductSkuId(dealerShopers.getProductSkuId());
        dealerOrders.setProductSkuCode(dealerShopers.getProductSkuCode());
        dealerOrders.setProductSkuName(dealerShopers.getProductSkuName());
        dealerOrders.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrders.setUpdateTime(dealerOrders.getCreateTime());
        if (brandPointBalance.getPoint() != null)
        {
            dealerOrders.setPoint(brandPointBalance.getPoint());
            dealerOrders.setPointAmount(dealerOrders.getTotalAmount().multiply(dealerOrders.getPoint()));
        }
        dealerOrders.setItemState(DealerConstant.DealerOrders.ITEM_STATE_NORMAL);
        return dealerOrders;
    }
    
    @Override
    public Boolean isHasTakeSample(String dealerId, String productId)
    {
        if (null != dealerId && productId != null)
        {
            int count = dealerOrderMapper.countDealerOrder(dealerId, productId);
            return count > 0 ? true : false;
        }
        return true;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> selectDealerOrderPage(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        if (null != dealerOrder && null != dealerOrder.getDealerId())
        {
            dealerOrder.setPage(pagination);
            dealerOrder.setStartTimeLong(CalendarUtils.getLongFromTime(dealerOrder.getStartTime()));
            if (null != dealerOrder.getEndTime())
            {
                dealerOrder.setEndTimeLong(CalendarUtils.addDay(dealerOrder.getEndTime(), 1));
            }
            List<Map<String, Object>> dealerOrderList = dealerOrderMapper.selectDealerOrderList(dealerOrder);
            for (Map<String, Object> map : dealerOrderList)
            {
                // 增加订单信息
                String orderId = (String) map.get("refrenceId");
                OrderShipRecord orderShipRecord = orderShipRecordService.getOrderShipRecord(orderId);
                if (null != orderShipRecord)
                {
                    map.put("logisticName", orderShipRecord.getLogisticName());
                    map.put("shipNumber", orderShipRecord.getShipNumber());
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
                Integer payState = MapUtils.getInteger(map, "payState", 0);
                map.put("orderpaystatus", payState > 0 || isHasPaid);
                Short orderStatue = Short.valueOf(String.valueOf(map.get("orderStatus")));
                if (orderStatue.equals(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED) || orderStatue.equals(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED))
                {
                    // 查询投诉的ID
                    DealerComplaint dealerComplaint = dealerComplaintService.getDealerComplaint((String) map.get("refrenceId"));
                    if (null != dealerComplaint)
                    {
                        map.put("dealerComplaintId", dealerComplaint.getRefrenceId());
                    }
                    // 投诉期限判断
                    Long updateTime = (Long) map.get("updateTime");
                    DateTime lastUpdateTime = new DateTime(updateTime);
                    if (map.get("complaintState") == null && DateTime.now().minusDays(complainDays).isBefore(lastUpdateTime))
                    {
                        map.put("complainable", true);
                    }
                    else
                    {
                        map.put("complainable", false);
                    }
                }
                List<Map<String, Object>> models = dealerOrdersService.getProductMap(dealerOrder.getDealerId(), orderId);
                map.put("items", models);
            }
            PaginateResult paginateResult = new PaginateResult(pagination, dealerOrderList);
            return paginateResult;
        }
        return null;
    }
    
    @Override
    public Map<String, Object> getDealerReportInfo(String brandId, String dealerId) throws BusinessException
    {
        Map<String, Object> mapResult = Maps.newHashMap();
        BigDecimal totalProductAmount = BigDecimal.ZERO; // 应付款，包含运费
        BigDecimal totalHasRefundAmount = BigDecimal.ZERO; // 已完成的退款金额
        BigDecimal totalRefundIngAmount = BigDecimal.ZERO; // 进行中的退款金额
        BigDecimal totalClearingAmount = BigDecimal.ZERO; // 当期欠付款总款(未付)
        if (StringUtils.isNotBlank(brandId) && StringUtils.isNotBlank(dealerId))
        {
            DealerOrder dealerOrder = new DealerOrder();
            dealerOrder.setDealerId(dealerId);
            dealerOrder.setBrandId(brandId);
            List<Map<String, Object>> mapList = dealerOrderMapper.getDealerOrderReportDetailList(dealerOrder);
            if (null != mapList && !mapList.isEmpty())
            {
                for (Map<String, Object> map : mapList)
                {
                    String type = MapUtils.getString(map, "type");
                    if (type.equals("DealerOrder"))
                    {
                        BigDecimal productPrice = (BigDecimal) map.get("productPrice");
                        BigDecimal adjustFreight = (BigDecimal) map.get("adjustFreight");
                        BigDecimal adjustPrice = (BigDecimal) map.get("adjustPrice");
                        if (null != adjustFreight && adjustFreight.compareTo(BigDecimal.ZERO) == 1)
                        {
                            productPrice = productPrice.add(adjustFreight);
                        }
                        if (null != adjustPrice && adjustPrice.compareTo(BigDecimal.ZERO) != 0)
                        {
                            productPrice = productPrice.add(adjustPrice);
                        }
                        totalProductAmount = totalProductAmount.add(productPrice);
                    }
                    else if (type.equals("DealerRefund"))
                    {
                        String refundState = map.get("refundState").toString(); // 退货款状态
                        BigDecimal refundPrice = getPrice(map.get("refundAmount")); // 退货款
                        if ("9,10".contains(refundState))
                        {
                            totalHasRefundAmount = totalHasRefundAmount.add(refundPrice);
                        }
                        else if ("1,2,3".contains(refundState))
                        {
                            totalRefundIngAmount = totalRefundIngAmount.add(refundPrice);
                        }
                    }
                }
                DealerClearing dealerClearing = new DealerClearing();
                dealerClearing.setDealerId(dealerOrder.getDealerId());
                dealerClearing.setBrandId(dealerOrder.getBrandId());
                Map<String, Object> dealerClearingMap = dealerClearingService.selectDealerClearing(null, dealerClearing);
                Map<String, Object> recordSumResult = MapUtils.getMap(dealerClearingMap, "recordSumResult");
                totalClearingAmount = (BigDecimal) recordSumResult.get("allDebtPrice");
            }
        }
        mapResult.put("totalProductAmount", totalProductAmount);
        mapResult.put("totalHasRefundAmount", totalHasRefundAmount);
        mapResult.put("totalRefundIngAmount", totalRefundIngAmount);
        mapResult.put("totalClearingAmount", totalClearingAmount);
        return mapResult;
    }
    
    private BigDecimal getPrice(Object obj)
    {
        BigDecimal price = BigDecimal.ZERO;
        if (null != obj)
        {
            price = new BigDecimal(obj.toString());
        }
        return price;
    }
    
    @Override
    public Map<String, Object> selectDealerOrderType(String dealerId)
    {
        if (null != dealerId)
        {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("cash", false);
            resultMap.put("sam", false);
            resultMap.put("credit", false);
            resultMap.put("point", false);
            List<Map<String, Object>> mapList = dealerOrderMapper.selectDealerorderType(dealerId);
            for (Map<String, Object> map : mapList)
            {
                if (Short.valueOf(map.get("dealerOrderType").toString()) == DealerConstant.DealerOrder.ORDER_TYPE_CASH)
                {
                    resultMap.put("cash", true);
                    if (null != MapUtils.getBoolean(map, "isSampleOrder") && MapUtils.getBoolean(map, "isSampleOrder"))
                    {
                        resultMap.put("sam", true);
                    }
                }
                else if (Short.valueOf(map.get("dealerOrderType").toString()) == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
                {
                    resultMap.put("credit", true);
                }
                else if (Short.valueOf(map.get("dealerOrderType").toString()) == DealerConstant.DealerOrder.ORDER_TYPE_POINT)
                {
                    resultMap.put("point", true);
                }
            }
            return resultMap;
        }
        return null;
    }
    
    @Override
    public DealerOrder getDealerOrder(String orderId, String dealerId) throws BusinessException
    {
        if (null != orderId && null != dealerId)
        {
            DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(orderId);
            if (null == dealerOrder) { throw new BusinessException(CommonConst.ORDER_NULL); }
            if (dealerOrder.getDealerId().equals(dealerId))
            {
                BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(dealerOrder.getBrandsId());
                if (null != brandesInfo) dealerOrder.setBrandsName(brandesInfo.getBrandsName());
                return dealerOrder;
            }
        }
        return null;
    }
    
    @Override
    public Map<String, Object> isValid(DealerOrder dealerOrder)
    {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("valid", false); // 订单是否有效
        resultMap.put("orderType", ""); // 订单状态：现款，授信'
        resultMap.put("disCount", BigDecimal.ONE);
        if (null != dealerOrder && null != dealerOrder.getDealerId() && null != dealerOrder.getRefrenceId() && null != dealerOrder.getDealerOrderType())
        {
            BrandesInfo brandesInfo = brandesInfoService.findBrandAndBrandesInfo(dealerOrder.getBrandsId());
            DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
            if (null == dealerJoin) // 区域授权 现款价
            {
                DealerInfo dealerInfo = dealerInfoService.getDealerInfo(dealerOrder.getDealerId());
                BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), dealerInfo.getAreaNo());
                if (null != brandesInfoRegional)
                {
                    resultMap.put("valid", true);
                    resultMap.put("orderType", DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                }
            }
            if (null != brandesInfo.getBrandState() && brandesInfo.getBrandState() == BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED && null != dealerJoin) // 品牌在平台合作中，品牌与经销商在合作中
            {
                if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CASH) // 普通订单校验
                {
                    if (dealerJoin.getJoinForm() == 0 || dealerJoin.getJoinForm() == 1) // 普通订单只要加盟就行
                    {
                        resultMap.put("valid", true);
                        resultMap.put("orderType", DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                    }
                }
                if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT) // 授信订单校验
                {
                    if (dealerJoin.getJoinForm() == 1 && dealerJoin.getIsPaid()) // 授信加盟并且交了押金
                    {
                        resultMap.put("valid", true);
                        resultMap.put("orderType", DealerConstant.DealerOrder.ORDER_TYPE_CREDIT);
                        resultMap.put("disCount", dealerJoin.getDiscount());
                    }
                    else if (dealerJoin.getJoinForm() == 0)
                    {
                        resultMap.put("valid", true);
                        resultMap.put("orderType", DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                    }
                }
                if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_POINT)
                {// 2015/11/25 返点订单校验
                    if (dealerJoin.getPoint())
                    {
                        resultMap.put("valid", true);
                        resultMap.put("orderType", DealerConstant.DealerOrder.ORDER_TYPE_POINT);
                    }
                }
            }
        }
        return resultMap;
    }
    
    @Override
    public void updateAvoidMail(String[] refrenceId, String brandId) throws BusinessException
    {
        if (ArrayUtils.isEmpty(refrenceId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        for (String string : refrenceId)
        {
            updateFare(new BigDecimal(0), brandId, string, null);
        }
    }
    
    @Override
    public PaginateResult<DealerOrderModel> getDealerOrderList4Boss(DealerOrder dealerOrder, Pagination page)
    {
        dealerOrder.setPage(page);
        List<DealerOrder> list = dealerOrderMapper.getDealerOrderList4Boss(dealerOrder);
        List<DealerOrderModel> result = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list))
        {
            for (DealerOrder d : list)
            {
                DealerOrderModel model = new DealerOrderModel();
                EntityUtils.copyProperties(model, d);
                List<DealerOrders> dos = dealerOrdersMapper.getDealerOrdersByOrderId(d.getRefrenceId());
                model.setDealerOrders(dos);
                result.add(model);
            }
        }
        PaginateResult<DealerOrderModel> orders = new PaginateResult(page, result);
        return orders;
    }
    
    @Override
    public Map<String, Object> getCountAndAmountByDealerId(String dealerId)
    {
        return dealerOrderMapper.getCountAndAmountByDealerId(dealerId);
    }
    
    @Override
    public List<Map<String, Object>> findByDealerOrder(DealerOrder dealerOrder)
    {
        return dealerOrderMapper.findByDealerOrder(dealerOrder);
    }
    
    // 财务帐经销商品牌商共用
    @Override
    public Map<String, Object> getDealerOrderReportMap(Pagination pagination, DealerOrder dealerOrder) throws BusinessException
    {
        if ((null == dealerOrder.getDealerId() && null == dealerOrder.getBrandId()) || (null != dealerOrder.getDealerId() && null != dealerOrder.getBrandId())) { return null; }
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> sumResult = Maps.newHashMap();
        BigDecimal totalInIngOrderPrice = BigDecimal.ZERO; // 所有进行中的订单货款
        BigDecimal totalFinishOrderPrice = BigDecimal.ZERO; // 所有完成的订单货款
        BigDecimal totalRefundOrderPrice = BigDecimal.ZERO; // 所有退款的货款
        BigDecimal totalCreditToPointPrice = BigDecimal.ZERO;// 所有产品类型变更货款
        BigDecimal totalProductPrice = BigDecimal.ZERO; // 所有的应收货款
        BigDecimal totalPayPrice = BigDecimal.ZERO; // 所有已付的退货款
        BigDecimal totalDebtPrice = BigDecimal.ZERO; // 所有未付的退货款
        BigDecimal sumProductPrice = BigDecimal.ZERO; // 合计 ：欠付货款
        BigDecimal sumPayPrice = BigDecimal.ZERO; // 合计 ：已付货款
        BigDecimal sumDebtPrice = BigDecimal.ZERO; // 合计 ：欠付货款
        BigDecimal sumRefundOrderPrice = BigDecimal.ZERO; // 合计 ： 应收退款
        dealerOrder.setPage(pagination);
        List<Map<String, Object>> mapList = dealerOrderMapper.selectOrderUser(dealerOrder);
        PaginateResult paginateResult = new PaginateResult(pagination, mapList);
        if (!mapList.isEmpty())
        {
            for (Map<String, Object> map : mapList)
            {
                String dealerId = MapUtils.getString(map, "dealerId");
                String brandId = MapUtils.getString(map, "brandId");
                List<Map<String, Object>> dealerOrderBrandes = dealerOrderMapper.findByDealerIdAndBrandId(dealerId, brandId);
                map.put("brandesMap", dealerOrderBrandes); // 品牌信息
                Map<String, Object> _map = Maps.newHashMap();
                dealerOrder.setDealerId(dealerId);
                dealerOrder.setBrandId(brandId);
                pagination = null;
                Map<String, Object> detailResult = this.getDealerOrderReportDetailMap(pagination, dealerOrder);
                Map<String, Object> detailSumMap = MapUtils.getMap(detailResult, "sumResult");
                totalInIngOrderPrice = getPrice(detailSumMap.get("allIngOrderPrice"));
                totalFinishOrderPrice = getPrice(detailSumMap.get("allFinishOrderPrice"));
                totalRefundOrderPrice = getPrice(detailSumMap.get("allTotalRefundAmount"));
                totalCreditToPointPrice = getPrice(detailSumMap.get("allCreditToPointPrice"));
                // 应付款=进行中货款+已完成货款-退款
                totalProductPrice = totalInIngOrderPrice.add(totalFinishOrderPrice).subtract(totalRefundOrderPrice.add(totalCreditToPointPrice));
                // 已付款=统计终端商已支付的货款金额-直接支付的退款金额（reachStatus状态是0或2）
                totalPayPrice = getPrice(detailSumMap.get("allPayment"));
                // 欠付款=应付款-已付款
                totalDebtPrice = totalProductPrice.subtract(totalPayPrice);
                map.put("totalInIngOrderPrice", totalInIngOrderPrice);
                map.put("totalFinishOrderPrice", totalFinishOrderPrice);
                map.put("totalRefundOrderPrice", totalRefundOrderPrice);
                map.put("totalCreditToPointPrice", totalCreditToPointPrice);
                map.put("totalProductPrice", totalProductPrice);
                map.put("totalPayPrice", totalPayPrice);
                map.put("totalDebtPrice", totalDebtPrice);
                DealerClearing dealerClearing = new DealerClearing();
                dealerClearing.setDealerId(dealerOrder.getDealerId());
                dealerClearing.setBrandId(dealerOrder.getBrandId());
                /**当期欠付款*/
                Map<String, Object> dealerClearingMap = dealerClearingService.selectDealerClearing(null, dealerClearing);
                Map<String, Object> recordSumResult = MapUtils.getMap(dealerClearingMap, "recordSumResult");
                map.put("allDebtPrice", recordSumResult.get("allDebtPrice"));
                // 合计
                sumProductPrice = sumProductPrice.add(totalProductPrice);
                sumPayPrice = sumPayPrice.add(totalPayPrice);
                sumDebtPrice = sumDebtPrice.add(totalDebtPrice);
                sumRefundOrderPrice = sumRefundOrderPrice.add(totalRefundOrderPrice);
            }
            sumResult.put("sumProductPrice", sumProductPrice);
            sumResult.put("sumPayPrice", sumPayPrice);
            sumResult.put("sumDebtPrice", sumDebtPrice);
            sumResult.put("sumRefundOrderPrice", sumRefundOrderPrice);
        }
        resultMap.put("recordPageResult", paginateResult);
        resultMap.put("recordSum", sumResult);
        return resultMap;
    }
    
    @Override
    public List<Map<String, Object>> findbyDealerIdAndTimeRange(DealerOrder order)
    {
        return dealerOrderMapper.findbyDealerIdAndTimeRange(order);
    }
    
    @Override
    public Map<String, Object> getDealerOrderReportDetailMap(Pagination pagination, DealerOrder dealerOrder) throws BusinessException
    {
        if (null == dealerOrder.getDealerId() || null == dealerOrder.getBrandId()) { throw new BusinessException(CommonConst.USER_NOT_LOGIN); }
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> sumResult = Maps.newHashMap();
        dealerOrder.setPage(pagination);
        dealerOrder.setStartTimeLong(CalendarUtils.getLongFromTime(dealerOrder.getStartTime()));
        if (null != dealerOrder.getEndTime())
        {
            dealerOrder.setEndTimeLong(CalendarUtils.addDay(dealerOrder.getEndTime(), 1));
        }
        List<Map<String, Object>> mapList = dealerOrderMapper.getDealerOrderReportDetailList(dealerOrder);
        /**明细统计数据*/
        BigDecimal allProductPrice = BigDecimal.ZERO; // 所有该收的货款
        BigDecimal allPayment = BigDecimal.ZERO; // 所有已收的货款
        BigDecimal allDebtPrice = BigDecimal.ZERO; // 所有未收的货款
        // 先只查出完成的退款款，及已付的退货款 allTotalRefundAmount == allPaidRefundAmount
        BigDecimal allTotalRefundAmount = BigDecimal.ZERO; // 所有的退货款
        BigDecimal allPaidRefundAmount = BigDecimal.ZERO; // 所有已付的退货款
        BigDecimal allDebtRefundAmount = BigDecimal.ZERO; // 所有未付的退货款
        BigDecimal allCollectOffsetPay = BigDecimal.ZERO; // 所有应收抵欠付
        BigDecimal allAdjustPrice = BigDecimal.ZERO; // 品牌商授信产品调价
        BigDecimal allIngOrderPrice = BigDecimal.ZERO; // 进行中的订单价格
        BigDecimal allFinishOrderPrice = BigDecimal.ZERO; // 完成的订单价格
        BigDecimal allCreditToPointPrice = BigDecimal.ZERO;// 产品变更价格
        BigDecimal allDirectRefundPrice = BigDecimal.ZERO; // 直接支付的退款
        if (!mapList.isEmpty())
        {
            for (Map<String, Object> map : mapList)
            {
                String type = (String) map.get("type");
                if (type.equals("DealerOrder")) // 下单数据处理(销售/采购)
                {
                    BigDecimal productPrice = getPrice(map.get("productPrice")); // 应收货款
                    BigDecimal freight = getPrice(map.get("adjustFreight")); // 应收运费
                    BigDecimal adjustPrice = getPrice(map.get("adjustPrice")); // 优惠的价格
                    BigDecimal payment = getPrice(map.get("payment")); // 已收货款
                    BigDecimal debtPrice = BigDecimal.ZERO; // 未收的货款
                    BigDecimal offSetAmount = getPrice(map.get("offSetAmount"));// 应收抵欠付
                    String frePayState = map.get("frePayState").toString(); // 是否运费已支付
                    productPrice = productPrice.add(offSetAmount); // 应付里加上应收抵欠付
                    if ("4,9".contains(MapUtils.getString(map, "orderStatus"))) // 关闭发货时4,交易完成9，会有部分未发货款，会打到经销商账户，这里要在已付的货款中减去
                    {
                        // 4的时候返回noSendGoodsAmount，9的时候返回noSendGoodsAmount和调价后要返回给经销商的钱（如订单价186，经销商已付但未发货，然后调价为180，要在交易完成时返回给经销商6）
                        BigDecimal noSendGoodsAmountAndRefundDealer = orderPayService.getNeedRefundCashWhenConfirmPay(MapUtils.getString(map, "refrenceId"));
                        if (noSendGoodsAmountAndRefundDealer.compareTo(BigDecimal.ZERO) == 1)
                        {
                            BigDecimal orderCreditMoney = productPrice.add(noSendGoodsAmountAndRefundDealer).subtract(payment); // 4的时候，此时productPrice中未发货金额已经减掉，所以这里要加上
                            if (noSendGoodsAmountAndRefundDealer.compareTo(orderCreditMoney) == 1) // 授信关闭发货，现款关闭发货需要认真去思考
                            {
                                payment = payment.subtract(noSendGoodsAmountAndRefundDealer.subtract(orderCreditMoney));
                            }
                        }
                    }
                    if (freight.compareTo(BigDecimal.ZERO) > 0)
                    {
                        productPrice = productPrice.add(freight);
                        if (Short.valueOf(frePayState) == DealerConstant.DealerOrder.FREIGHT_PAY_YES)
                        {
                            payment = payment.add(freight); // 包含运费已支付的已支付款
                        }
                    }
                    if (adjustPrice.compareTo(BigDecimal.ZERO) != 0)
                    {
                        productPrice = productPrice.add(adjustPrice);
                    }
                    if (productPrice.subtract(payment).compareTo(BigDecimal.ZERO) > 0)
                    {
                        debtPrice = productPrice.subtract(payment);
                    }
                    map.put("productPrice", productPrice);
                    map.put("payment", payment);
                    map.put("debtPrice", debtPrice);
                    allProductPrice = allProductPrice.add(productPrice);
                    allPayment = allPayment.add(payment);
                    allDebtPrice = allDebtPrice.add(debtPrice);
                    /* ========================================= 用于财务帐总计页面，与财务帐明细无关 [@author易永耀] begin================================================ */
                    if ("2,3,4".contains(MapUtils.getString(map, "orderStatus"))) // 进行中的订单
                    {
                        allIngOrderPrice = allIngOrderPrice.add(getPrice(map.get("productPrice"))); // 运费包含在内
                    }
                    if ("9".contains(MapUtils.getString(map, "orderStatus"))) // 完成的订单
                    {
                        allFinishOrderPrice = allFinishOrderPrice.add(getPrice(map.get("productPrice")));
                    }
                    /* ========================================= 用于财务帐总计页面，与财务帐明细无关 end ================================================ */
                }
                if (type.equals("DealerRefund")) // 退款数据处理(销售退货)
                {
                    String refundState = map.get("refundState").toString(); // 退货款状态
                    BigDecimal refundAmount = getPrice(map.get("refundAmount")); // 退货款
                    BigDecimal debtRefundAmount = BigDecimal.ZERO;
                    if ("9".equals(refundState) || "10".equals(refundState))
                    {
                        map.put("totalRefundAmount", refundAmount);
                        allTotalRefundAmount = allTotalRefundAmount.add(refundAmount);
                        allProductPrice = allProductPrice.subtract(refundAmount);
                    }
                    if ("9".equals(refundState) || "10".equals(refundState))
                    {
                        String reachStatus = MapUtils.getString(map, "reachStatus", "0");
                        if (reachStatus.equals("0")) // 直接支付
                        {
                            map.put("paidRefundAmount", refundAmount);
                            allPayment = allPayment.subtract(refundAmount);
                        }
                        else if (reachStatus.equals("1")) // 应收抵欠付
                        {
                            BigDecimal collectOffsetPay = getPrice(map.get("reachAmount"));
                            allDebtPrice = allDebtPrice.subtract(collectOffsetPay);
                        }
                        else if (reachStatus.equals("2")) // 应收抵部分欠付
                        {
                            BigDecimal collectOffsetPay = getPrice(map.get("reachAmount"));
                            map.put("paidRefundAmount", refundAmount.subtract(collectOffsetPay));
                            allPayment = allPayment.subtract(refundAmount.subtract(collectOffsetPay));
                            allDebtPrice = allDebtPrice.subtract(collectOffsetPay);
                        }
                    }
                    if (getPrice(map.get("totalRefundAmount")).compareTo(getPrice(map.get("hasRefundAmount"))) > 0)
                    {
                        debtRefundAmount = getPrice(map.get("totalRefundAmount")).subtract(getPrice(map.get("hasRefundAmount")));
                    }
                    map.put("debtRefundAmount", debtRefundAmount);
                    allDebtRefundAmount = allDebtRefundAmount.add(debtRefundAmount);
                }
                if (type.equals("Adjustment")) // 授信产品调价
                {
                    allAdjustPrice = getPrice(map.get("adjustAllPrice"));
                    allProductPrice = allProductPrice.add(allAdjustPrice);
                    allDebtPrice = allDebtPrice.add(allAdjustPrice);
                    // allIngOrderPrice = allIngOrderPrice.add(allAdjustPrice); //进行中的订单也在调价中
                    allFinishOrderPrice = allFinishOrderPrice.add(allAdjustPrice); // 修改为完成的订单
                }
                if (type.equals("DealerClearing")) // 当期已付欠付款
                {
                    BigDecimal dealerClearing = getPrice(map.get("clearingAmount"));
                    allPayment = allPayment.add(dealerClearing);
                    // 数据库已经查出直接在页面显示 getPrice(map.get("clearingAmount"));
                }
                if (type.equals("CreditToPoint")) // 授信转返点
                {
                    BigDecimal costPrice = getPrice(map.get("costPrice"));
                    allCreditToPointPrice = allCreditToPointPrice.add(costPrice);
                    allProductPrice = allProductPrice.subtract(costPrice);
                }
            }
        }
        PaginateResult paginateResult = new PaginateResult(pagination, mapList);
        sumResult.put("allProductPrice", allProductPrice);
        sumResult.put("allPayment", allPayment);
        sumResult.put("allDebtPrice", allDebtPrice);
        sumResult.put("allTotalRefundAmount", allTotalRefundAmount);
        sumResult.put("allPaidRefundAmount", allPaidRefundAmount);
        sumResult.put("allDebtRefundAmount", allDebtRefundAmount);
        sumResult.put("allCollectOffsetPay", allCollectOffsetPay);
        sumResult.put("allAdjustPrice", allAdjustPrice);
        /**财务帐总页面调用，与财务帐详情页无关*/
        sumResult.put("allIngOrderPrice", allIngOrderPrice);
        sumResult.put("allFinishOrderPrice", allFinishOrderPrice);
        sumResult.put("allDirectRefundPrice", allDirectRefundPrice);
        sumResult.put("allCreditToPointPrice", allCreditToPointPrice);
        resultMap.put("result", paginateResult);
        resultMap.put("sumResult", sumResult);
        return resultMap;
    }
    
    @Override
    public Map<String, Object> selectTradeTypeList(String dealerId, String brandId)
    {
        if (null != dealerId && null != brandId)
        {
            Map<String, Object> resultMap = Maps.newHashMap();
            DealerOrder dealerOrder = new DealerOrder();
            dealerOrder.setDealerId(dealerId);
            dealerOrder.setBrandId(brandId);
            List<Map<String, Object>> mapList = dealerOrderMapper.getDealerOrderReportDetailList(dealerOrder);
            if (!mapList.isEmpty())
            {
                for (Map<String, Object> map : mapList)
                {
                    if (null == MapUtils.getString(map, "createTime"))
                    {
                        continue;
                    }
                    String type = MapUtils.getString(map, "type");
                    if (type.equals("DealerRefund"))
                    {
                        resultMap.put("DealerRefund", "退货/退款");
                    }
                    else if (type.equals("DealerOrder"))
                    {
                        resultMap.put("DealerOrder", "采购");
                    }
                    else if (type.equals("Adjustment"))
                    {
                        resultMap.put("Adjustment", "产品库存调价");
                    }
                    else if (type.equals("DealerClearing"))
                    {
                        resultMap.put("DealerClearing", "当期欠付款");
                    }
                    else if (type.equals("CreditToPoint"))
                    {
                        resultMap.put("CreditToPoint", "授信转返点");
                    }
                }
            }
            return resultMap;
        }
        return null;
    }
    
    @Override
    public Long countByOrderStatus(Short orderStatus, Long outActTime)
    {
        return dealerOrderMapper.countByOrderStatus(orderStatus, outActTime);
    }
    
    @Override
    public PaginateResult<DealerOrder> getListByOrderStatus(Short orderStatus, Long outActTime, Pagination page)
    {
        List list = dealerOrderMapper.getListByOrderStatus(orderStatus, outActTime, page);
        PaginateResult<DealerOrder> result = new PaginateResult(page, list);
        return result;
    }
    
    @Override
    public void confirmReceive(String refrenceId, String dealerId, String payword) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException(CommonConst.ORDER_REFRENCE_ID_NULL); }
        DealerOrderModel dealerOrder = this.dealerOrderMapper.getDealerOrder(refrenceId, dealerId);
        if (null == dealerOrder) { throw new BusinessException(CommonConst.ORDER_NULL); }
        if (dealerOrder.getPayState() != DealerConstant.DealerOrder.PAY_STATE_ALL) { throw new BusinessException(CommonConst.ORDER_REFUND_NOT_PAY); }
        if (DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE != dealerOrder.getOrderStatus()) { throw new BusinessException(CommonConst.ORDER_STATUS_NOT_TO_RECEIVE); }
        List<Integer> countTypeList = Lists.newArrayList();
        // 订单状态改为已完成
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        // 如果退款状态 存在时,收货后 把状态改为 取消退款k
        if (dealerOrder.getRefundStatus() != null && dealerOrder.getRefundStatus() != DealerConstant.DealerRefund.CANCEL_REFUND // 取消退款
                && dealerOrder.getRefundStatus() != DealerConstant.DealerRefund.CLOSS_REFUND)
        {
            dealerOrder.setRefundStatus(DealerConstant.DealerRefund.CANCEL_REFUND);
            DealerRefund dealerRefund = dealerRefundMapper.getEntityByOrderNumber(dealerOrder.getOrderId());
            if (dealerRefund != null)
            {
                this.dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.CANCEL_REFUND);
            }
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
            brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        }
        else
        {
            // 正常状态下 经销商待收货订单数量减1
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
        }
        // 品牌商已发货减1
        // this.brandCountCache.updateBrandCount(BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT,
        // dealerOrder.getBrandId(), -1);
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT);
        brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // 操作记录
        String dealerName = dealerInfoMapper.selectByPrimaryKey(dealerId).getDealerName();
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CONFRIMGOODS);
        dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CONFRIMGOODS_NAME);
        dealerOrderAction.setActContent("【" + dealerName + "】确认收货");
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(dealerOrder.getRefrenceId());
        dealerOrderAction.setUserId(dealerId);
        dealerOrderAction.setUserName(dealerName);
        dealerOrderActionMapper.insert(dealerOrderAction);
        UserOperationLog userOperationLog = new UserOperationLog();
        userOperationLog.setObjectId(dealerOrderAction.getOrderId());
        userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
        userOperationLog.setEvent(dealerOrderAction.getActContent());
        userOperationLogService.addUserOperationLog(request, userOperationLog);
        // 更新品牌合作表中 订单数 订单金额总量
        DealerJoin dealerJoin = dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(dealerId, dealerOrder.getBrandsId());
        if (dealerJoin != null)
        {
            BigDecimal hasMoney = dealerJoin.getOrderMoney() == null ? new BigDecimal(0) : dealerJoin.getOrderMoney();
            dealerJoin.setOrderMoney(hasMoney.add(new BigDecimal(dealerOrder.getPayment().toString())));
            Integer hasNumber = dealerJoin.getOrderNum() == null ? 0 : dealerJoin.getOrderNum();
            dealerJoin.setOrderNum(hasNumber + dealerOrder.getProductCount());
            dealerJoin.setOrderTime(dealerJoin.getOrderTime() == null ? 1 : dealerJoin.getOrderTime() + 1);
            dealerJoin.setLastOrder(CalendarUtils.getCurrentLong());
            this.dealerJoinService.updateByPrimaryKey(dealerJoin);
        }
        // 更新品牌中产品的销量(依据拍下量 做运算)
        List<DealerOrders> dealerOrdersItems = dealerOrdersMapper.selectDealerOrders(dealerOrder.getRefrenceId(), dealerId);
        // 收货量依据 发货量为判断
        int productTotalSendCount = 0;
        Map<String, Integer> productTotalCountMap = Maps.newHashMap();// Map<产品id,购买总量>
        for (DealerOrders dealerOrdersItem : dealerOrdersItems)
        {
            String productId = dealerOrdersItem.getProductId();
            Integer productTotalCount = productTotalCountMap.get(productId);
            if (null == productTotalCount)
            {
                productTotalCount = dealerOrdersItem.getQuantity() == null ? Integer.valueOf(0) : dealerOrdersItem.getQuantity();// 购买数量
            }
            else
            {
                productTotalCount += dealerOrdersItem.getQuantity();
            }
            productTotalCountMap.put(productId, productTotalCount);
            // 统计发货总量
            int productSendCount = (dealerOrdersItem.getShipCount() == null ? 0 : dealerOrdersItem.getShipCount());// 已发货数量
            productTotalSendCount += productSendCount;
        }
        updateProductCount(productTotalCountMap);
        // bug #5084}
        dealerOrder.setReceiveCount(productTotalSendCount);
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        this.dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        // 确认付款(支付平台)
        orderPayService.executeConfirmPay(dealerOrder.getRefrenceId(), payword);
    }
    
    @Override
    @Deprecated
    public void confirmReceive2(String refrenceId, String dealerId) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException(CommonConst.ORDER_REFRENCE_ID_NULL); }
        DealerOrderModel dealerOrder = this.dealerOrderMapper.getDealerOrder(refrenceId, dealerId);
        if (null == dealerOrder) { throw new BusinessException(CommonConst.ORDER_NULL); }
        if (dealerOrder.getPayState() != DealerConstant.DealerOrder.PAY_STATE_ALL) { throw new BusinessException(CommonConst.ORDER_REFUND_NOT_PAY); }
        if (DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE != dealerOrder.getOrderStatus()) { throw new BusinessException(CommonConst.ORDER_STATUS_NOT_TO_RECEIVE); }
        List<Integer> countTypeList = Lists.newArrayList();
        // 订单状态改为已完成
        dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        // 如果退款状态 存在时,收货后 把状态改为 取消退款k
        if (dealerOrder.getRefundStatus() != null && dealerOrder.getRefundStatus() != DealerConstant.DealerRefund.CANCEL_REFUND // 取消退款
                && dealerOrder.getRefundStatus() != DealerConstant.DealerRefund.CLOSS_REFUND)
        {
            dealerOrder.setRefundStatus(DealerConstant.DealerRefund.CANCEL_REFUND);
            DealerRefund dealerRefund = this.dealerRefundMapper.getEntityByOrderNumber(dealerOrder.getOrderId());
            if (dealerRefund != null)
            {
                dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.CANCEL_REFUND);
            }
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
            brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        }
        else
        {
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
        }
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT);
        brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // 操作记录
        String dealerName = dealerInfoMapper.selectByPrimaryKey(dealerId).getDealerName();
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CONFRIMGOODS);
        dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CONFRIMGOODS_NAME);
        dealerOrderAction.setActContent("【" + dealerName + "】确认收货");
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(dealerOrder.getRefrenceId());
        dealerOrderAction.setUserId(dealerId);
        dealerOrderAction.setUserName(dealerName);
        dealerOrderActionMapper.insert(dealerOrderAction);
        // todo UserOperationLog
        /*
         * UserOperationLog userOperationLog = new UserOperationLog();
         * userOperationLog.setObjectId(dealerOrderAction.getOrderId());
         * userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
         * userOperationLog.setEvent(dealerOrderAction.getActContent());
         * userOperationLogService.addUserOperationLog(request, userOperationLog);
         */
        // 更新品牌合作表中 订单数 订单金额总量
        DealerJoin dealerJoin = this.dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(dealerId, dealerOrder.getBrandsId());
        if (dealerJoin != null)
        {
            BigDecimal hasMoney = dealerJoin.getOrderMoney() == null ? new BigDecimal(0) : dealerJoin.getOrderMoney();
            dealerJoin.setOrderMoney(hasMoney.add(new BigDecimal(dealerOrder.getPayment().toString())));
            Integer hasNumber = dealerJoin.getOrderNum() == null ? 0 : dealerJoin.getOrderNum();
            dealerJoin.setOrderNum(hasNumber + dealerOrder.getProductCount());
            dealerJoin.setOrderTime(dealerJoin.getOrderTime() == null ? 1 : dealerJoin.getOrderTime() + 1);
            dealerJoin.setLastOrder(CalendarUtils.getCurrentLong());
            this.dealerJoinService.updateByPrimaryKey(dealerJoin);
        }
        List<DealerOrders> dealerOrdersItems = dealerOrdersMapper.selectDealerOrders(dealerOrder.getRefrenceId(), dealerId);
        // 收货量依据 发货量为判断
        int productTotalSendCount = 0;
        for (DealerOrders dealerOrdersItem : dealerOrdersItems)
        {
            String productId = dealerOrdersItem.getProductId();
            ProductCount productCount = productCountMapper.selectByPrimaryKey(productId);
            if (productCount == null) { throw new BusinessException(CommonConst.ORDER_RECEIVE_FAILURE); }
            // productCountMapper.updateByPrimaryKey(productCount);
        }
        dealerOrder.setReceiveCount(productTotalSendCount);
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        // todo crm免登陆经销商后台操作-确认收货(工厂店)
        /*
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * DealerCrmLog dealerCrmLog = new DealerCrmLog();
         * dealerCrmLog.setOperatorId(crm_userId);
         * dealerCrmLog.setBrandesId(dealerOrder.getBrandsId());
         * dealerCrmLog.setBeOperationName(CrmConstant.crmToDealerOptionModel.FACTORY_ORDER);
         * dealerCrmLog.setOperation(CrmConstant.CrmDealerOptionName.CONFIRM); // 确认收货
         * dealerCrmLog.setOperationDetails("确认收货:'授信订单:" + dealerOrder.getOrderId() + "';");
         * dealerCrmLogService.addCRMLog(request, dealerCrmLog);
         * }
         */
    }
    
    /**
     * @param productTotalCountMap
     * @throws BusinessException
     * @author 章旭楠
     */
    private void updateProductCount(Map<String, Integer> productTotalCountMap) throws BusinessException
    {
        for (String productId : productTotalCountMap.keySet())
        {
            Integer productTotalCount = productTotalCountMap.get(productId);
            ProductCount productCount = productCountMapper.selectByPrimaryKey(productId);
            if (productCount == null) { throw new BusinessException(CommonConst.ORDER_RECEIVE_FAILURE); }
            Integer hasSaleNum = productCount.getSaleNum() == null ? 0 : productCount.getSaleNum();
            productCount.setSaleNum(hasSaleNum + productTotalCount);
            productCount.setUpdateTime(CalendarUtils.getCurrentLong());
            productCountMapper.updateByPrimaryKey(productCount);
        }
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getTaskDealerOrderList(Pagination page, DealerOrder dealerOrder)
    {
        return new PaginateResult(page, dealerOrderMapper.getTaskDealerOrderList(dealerOrder));
    }
    
    @Override
    public void confirmRefund(String refrenceId) throws BusinessException
    {
        DealerOrder order = this.dealerOrderMapper.selectByPrimaryKey(refrenceId);
        DealerOrderModel model = EntityUtils.buildModelByEntity(DealerOrderModel.class, order);
        // 退款操作（到支付平台）
        orderPayService.executeRefund(order.getRefrenceId(), model.getAlreadyPayAmount(), BigDecimal.ZERO);
        // 修改统计数量
        // dealerCountCache.updateDealerCount(4, order.getDealerId(), -1);
        order.setUpdateTime(CalendarUtils.getCurrentLong());
        order.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
        // 更改缓存中订单信息
        dealerOrderMapper.updateByPrimaryKey(order);
        // todo 删除拿样日志
        /* productSampleLogDao.updateDelState(order.getRefrenceId(), BrandConstant.DEL_STATE_DELETED); */
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT);
        brandCountService.modifyBrandCount(order.getBrandId(), countTypeList.toArray(new Integer[]{}));
        String brandName = brandInfoMapper.selectByPrimaryKey(order.getBrandId()).getComName();
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CLOSE);
        dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CLOSE_TXT);
        dealerOrderAction.setActContent("" + brandName + " 发货超时，自动退款");
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(order.getRefrenceId());
        dealerOrderAction.setUserId(order.getDealerId());
        dealerOrderAction.setUserName(brandName);
        // 保存操作记录
        dealerOrderActionMapper.insert(dealerOrderAction);
        UserOperationLog userOperationLog = new UserOperationLog();
        userOperationLog.setObjectId(dealerOrderAction.getOrderId());
        userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
        userOperationLog.setEvent(dealerOrderAction.getActContent());
        userOperationLogService.addUserOperationLog(request, userOperationLog);
    }
    
    @Override
    public void updateComplaintState(String orderId, short state)
    {
        DealerOrder dealerOrder = this.dealerOrderMapper.selectByPrimaryKey(orderId);
        if (dealerOrder != null)
        {
            dealerOrder.setComplaintState(state);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
    }
    
    @Override
    public PaginateResult<DealerOrderModel> getDealerOrders(DealerOrderModel dealerOrder, Pagination page, int type) throws BusinessException
    {
        if (null == dealerOrder) { throw new BusinessException("请求参数为空"); }
        List<DealerOrder> list = dealerOrderMapper.getDealerOrders(dealerOrder, page, type);
        List<DealerOrderModel> mlist = Lists.newArrayList();
        for (DealerOrder d : list)
        {
            DealerOrderModel model = EntityUtils.buildModelByEntity(DealerOrderModel.class, d);
            UserInfo b = userInfoMapper.selectByPrimaryKey(d.getDealerId());
            model.setUserMobile(b == null ? null : b.getUserMobile());
            mlist.add(model);
        }
        return new PaginateResult<>(page, mlist);
    }
    
    @Override
    public Map<String, Object> findOrderCount(DealerOrderModel dealerOrder) throws BusinessException
    {
        List<DealerOrder> dealerOrders = dealerOrderMapper.listDealerOrders(dealerOrder);
        Map<String, Object> count = Maps.newHashMap();
        int totalNum = 0;// 订单总数
        int waitPay = 0;// 待付款
        BigDecimal waitPayMoney = new BigDecimal(0);// 待付款金额
        int sendOut = 0;// 已发货(包括部分发货和全部发货)
        BigDecimal sendOutMoney = new BigDecimal(0);// 已发货总金额
        int refund = 0;// 退款(包括退款中的和退款成功的)
        BigDecimal refundMoney = new BigDecimal(0);// 退款总金额
        int close = 0;// 交易关闭
        BigDecimal closeMoney = new BigDecimal(0);// 关闭总金额
        int succeed = 0;// 交易成功
        BigDecimal succeedMoney = new BigDecimal(0);// 交易成功总金额
        for (int i = 0; i < dealerOrders.size(); i++)
        {
            DealerOrder order = dealerOrders.get(i);
            totalNum = totalNum + 1;
            if (order.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY)
            {
                waitPay = waitPay + 1;
                waitPayMoney = waitPayMoney.add(order.getProductPrice());
            }
            if (order.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED
                    || order.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE)
            {
                sendOut = sendOut + 1;
                sendOutMoney = sendOutMoney.add(order.getProductPrice());
            }
            // 大于等于1少于等于5的，都是退款中的，大于等于9少于等于10的是退款成功的
            if (null != order.getRefundStatus() && order.getRefundStatus() != DealerConstant.DealerRefund.CLOSS_REFUND
                    && order.getRefundStatus() != DealerConstant.DealerRefund.CANCEL_REFUND)
            {
                refund = refund + 1;
                refundMoney = refundMoney.add(order.getProductPrice());
            }
            if (order.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_CLOSED)
            {
                close = close + 1;
                closeMoney = closeMoney.add(order.getProductPrice());
            }
            if (order.getOrderStatus() == DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED)
            {
                succeed = succeed + 1;
                succeedMoney = succeedMoney.add(order.getProductPrice());
            }
        }
        count.put("totalNum", totalNum);
        count.put("waitPay", waitPay + "(" + waitPayMoney + ")");
        count.put("sendOut", sendOut + "(" + sendOutMoney + ")");
        count.put("refund", refund + "(" + refundMoney + ")");
        count.put("close", close + "(" + closeMoney + ")");
        count.put("succeed", succeed + "(" + succeedMoney + ")");
        return count;
    }
    
    @Override
    public void updateOrderCost(DealerOrder dealerOrder) throws BusinessException
    {
        String refrenceId = dealerOrder.getRefrenceId();
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("主键为空"); }
        // 优惠价格介于0.01~100000000
        if ((Math.abs(dealerOrder.getAdjustPrice().doubleValue()) < DealerConstant.DealerOrder.min && dealerOrder.getAdjustPrice().doubleValue() != BigDecimal.ZERO
                .doubleValue()) || Math.abs(dealerOrder.getAdjustPrice().doubleValue()) > DealerConstant.DealerOrder.max) { throw new BusinessException("优惠价格超过临界值"); }
        if (dealerOrder.getAdjustFreight().doubleValue() < BigDecimal.ZERO.doubleValue() || dealerOrder.getAdjustFreight().doubleValue() > DealerConstant.DealerOrder.max) { throw new BusinessException(
                "运费价格超过临界值"); }
        // 修改价格后将关闭支付平台的支付订单
        orderPayService.executeClosePay(dealerOrder.getRefrenceId());
        DealerOrder order = dealerOrderMapper.selectByPrimaryKey(dealerOrder.getRefrenceId());
        if (null == order) { throw new BusinessException("不存在该订单信息"); }
        if (order.getAdjustFreight() != dealerOrder.getAdjustFreight() && order.getFrePayType() == 60)
        {
            order.setFrePayType((short) 2); // 包邮产品修改物流费后修改数据库状态 为 物流费
        }
        if (null != dealerOrder.getAdjustFreight())
        {
            if (null == order.getAdjustFreight() || order.getAdjustFreight().compareTo(dealerOrder.getAdjustFreight()) != 0)
            {
                // 缓存机制变更
                /*
                 * UserOperationLog userOperationLog = new UserOperationLog();
                 * userOperationLog.setObjectId(order.getRefrenceId());
                 * userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                 * userOperationLog.setEvent("【修改订单运费：修改前：" + (order.getAdjustFreight() == null ? "" : order.getAdjustFreight()) + " 修改后："
                 * + new java.text.DecimalFormat("0.00").format(dealerOrder.getAdjustFreight()) + "，订单号：" + order.getOrderId() + "】");
                 * userOperationLogService.addUserOperationLog(request, userOperationLog);
                 */
            }
            order.setAdjustFreight(dealerOrder.getAdjustFreight());
        }
        if (null != dealerOrder.getAdjustPrice())
        {
            if (null == order.getAdjustPrice() || order.getAdjustPrice().compareTo(dealerOrder.getAdjustPrice()) != 0)
            {
                // 缓存机制变更
                /*
                 * UserOperationLog userOperationLog = new UserOperationLog();
                 * userOperationLog.setObjectId(order.getRefrenceId());
                 * userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                 * userOperationLog.setEvent("【修改订单优惠价格：修改前：" + (order.getAdjustPrice() == null ? "" : order.getAdjustPrice()) + " 修改后："
                 * + new java.text.DecimalFormat("0.00").format(dealerOrder.getAdjustPrice()) + "，订单号：" + order.getOrderId() + "】");
                 * userOperationLogService.addUserOperationLog(request, userOperationLog);
                 */
            }
            order.setAdjustPrice(dealerOrder.getAdjustPrice());
        }
        order.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerOrderMapper.updateByPrimaryKey(order);
    }
    
    @Override
    public List<DealerOrder> listWithException(String userId, String ... orderIdArr) throws BusinessException
    {
        List<DealerOrder> dealerOrderList = Lists.newArrayList();
        for (int i = 0; i < orderIdArr.length; i++)
        {
            DealerOrder dealerOrder = dealerOrderMapper.getDealerOrder(orderIdArr[i], userId);
            if (null == dealerOrder) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS.getCode(), "订单不存在"); }
            dealerOrderList.add(dealerOrder);
        }
        return dealerOrderList;
    }
    
    @Override
    public boolean isRefundStatus(Long orderId, String dealerId)
    {
        return dealerOrderMapper.isRefundStatus(orderId, dealerId);
    }
    
    @Override
    public DealerOrderModel getByOrderIdAndDealerId(Long orderId, String dealerId)
    {
        return dealerOrderMapper.getByOrderIdAndDealerId(orderId, dealerId);
    }
    
    @Override
    public PaginateResult<DealerOrderModel> getOrdList(String dealerId, String brandId, String brandsId, Pagination page)
    {
        PaginateResult<DealerOrderModel> paginateResult = new PaginateResult<DealerOrderModel>();
        List<DealerOrderModel> dealerOrderList = this.dealerOrderMapper.getOrdList(dealerId, brandId, brandsId, page);
        paginateResult.setPage(page);
        paginateResult.setList(dealerOrderList);
        this.countPrice(paginateResult.getList());
        return paginateResult;
    }
    
    @Override
    public void countPrice(List<DealerOrderModel> list)
    {
        for (DealerOrderModel dealerOrder : list)
        {
            this.countPrice(dealerOrder);
        }
    }
    
    @Override
    public void countPrice(DealerOrderModel ord)
    {
        BigDecimal productPrice = ord.getProductPrice() == null ? BigDecimal.ZERO : ord.getProductPrice();
        BigDecimal adjustPrice = ord.getAdjustPrice() == null ? BigDecimal.ZERO : ord.getAdjustPrice();
        BigDecimal adjustFreight = ord.getAdjustFreight() == null ? BigDecimal.ZERO : ord.getAdjustFreight();
        BigDecimal payment = ord.getPayment() == null ? BigDecimal.ZERO : ord.getPayment();
        // 采购金额==productPrice+isnull(adjustFreight,0)
        ord.setPurcMoney(productPrice.add(adjustFreight));
        // 最终金额=productPrice+isnull(adjustPrice,0)+ isnull(adjustFreight,0)
        ord.setSumMoney(productPrice.add(adjustPrice).add(adjustFreight));
        // 欠款 = 最终金额- payment
        List<Map<String, Object>> refundList = dealerRefundMapper.getCompleteDealerRefundList(ord.getRefrenceId());
        ord.setDebtMoney(ord.getSumMoney().subtract(payment));
        if (CollectionUtils.isEmpty(refundList))
        {
            ord.setDebtMoney(ord.getSumMoney().subtract(payment));
            if (ord.getFrePayState().shortValue() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED)
            {
                ord.setDebtMoney(ord.getDebtMoney().subtract(adjustFreight));
            }
        }
        else
        {
            ord.setDebtMoney(new BigDecimal("0"));
            BigDecimal refundAmount = new BigDecimal("0");
            for (Map<String, Object> item : refundList)
            {
                refundAmount = refundAmount.add(new BigDecimal(item.get("refundAmount").toString()));
            }
            ord.setSumMoney(ord.getSumMoney().subtract(refundAmount));
        }
        if (DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED == ord.getOrderStatus().shortValue()
                || DealerConstant.DealerOrder.ORDER_STATUS_CLOSED == ord.getOrderStatus().shortValue())
        {
            ord.setDebtMoney(new BigDecimal("0"));
        }
    }
    
    @Override
    public void updateCostPriceByProductIdAndSkuList(List<ProductSku> productSkuList) throws BusinessException
    {
        if (null == productSkuList || productSkuList.size() == 0) return;
        Map<String, DealerOrder> dealerOrderMap = Maps.newHashMap();
        Map<String, BigDecimal> dealerOrderOldPriceMap = Maps.newHashMap();
        ProductBaseInfo baseInfo = getProductBaseInfo(productSkuList.get(0).getProductId());
        for (ProductSku sku : productSkuList)
        {
            if (sku.getRefrenceId() == null) throw new BusinessException("Sku[" + sku.getProductSkuName() + "] refrenceId为空");
            List<DealerOrders> dealerOrdersList = dealerOrdersMapper.getNoSendGoodsOrders(sku.getRefrenceId());
            for (DealerOrders dealerOrders : dealerOrdersList)
            {
                DealerOrder dealerOrder = dealerOrderMap.get(dealerOrders.getOrderId());
                if (dealerOrder == null)
                {
                    dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerOrders.getOrderId());
                    dealerOrderMap.put(dealerOrders.getOrderId(), dealerOrder);
                }
                cacheOldOrderPrice(dealerOrderOldPriceMap, dealerOrder);
                BigDecimal oldTotalAmount = dealerOrders.getTotalAmount();
                BigDecimal newPrice = getPrice(dealerOrders, dealerOrder, sku);
                BigDecimal oldRealPrice = getSkuRealPrice(dealerOrders);
                if (newPrice.compareTo(oldRealPrice) >= 0) continue; // 如果调整价比现有实际单价高则不调整
                if (null == dealerOrders.getOldPrice())
                { // 第一次调价时存下订单生成时的sku单价
                  // dealerOrders.setOldPrice(oldRealPrice);
                    dealerOrders.setOldPrice(dealerOrders.getPrice());
                }
                dealerOrders.setPrice(newPrice);
                dealerOrders.setTotalAmount(newPrice.multiply(new BigDecimal(dealerOrders.getQuantity())));
                setDealerOrderProductPrice(dealerOrder, oldTotalAmount, newPrice.multiply(new BigDecimal(dealerOrders.getQuantity())));
                dealerOrdersMapper.updateByPrimaryKey(dealerOrders);
            }
        }
        for (DealerOrder dealerOrder : dealerOrderMap.values())
        {
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
            insertDealerOrderAction(dealerOrder, baseInfo.getProductNo(), dealerOrderOldPriceMap);
        }
    }
    
    private void setDealerOrderProductPrice(DealerOrder dealerOrder, BigDecimal oldAmount, BigDecimal newAmount)
    {
        BigDecimal productPrice = dealerOrder.getProductPrice();
        productPrice = productPrice.subtract(oldAmount).add(newAmount);
        dealerOrder.setProductPrice(productPrice);
    }
    
    // 得到sku实际单价
    private BigDecimal getSkuRealPrice(DealerOrders dealerOrders)
    {
        BigDecimal realPrice = dealerOrders.getPrice();
        if (null == dealerOrders.getOldPrice())
        {// 未调价过
            if (null != dealerOrders.getDiscount() && dealerOrders.getDiscount().compareTo(BigDecimal.ZERO) > 0)
            {
                realPrice = realPrice.multiply(dealerOrders.getDiscount());
            }
        }
        return realPrice;
    }
    
    // 得到订单项对应sku的价格
    private BigDecimal getPrice(DealerOrders dealerOrders, DealerOrder dealerOrder, ProductSku sku) throws BusinessException
    {
        BigDecimal price = dealerOrders.getPrice();
        ProductSkuPrice skuPrice = null;
        skuPrice = productSkuInfoDubboConsumer.getPriceBySkuId(sku.getRefrenceId());
        if (null != dealerOrder && null != skuPrice)
        {
            if (dealerOrder.getDealerOrderType().equals(DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)) price = skuPrice.getCreditPrice();
            else
            {
                if (null != dealerOrder.getIsSampleOrder() && dealerOrder.getIsSampleOrder()) price = skuPrice.getSamplePrice();
                else price = skuPrice.getDirectPrice();
            }
        }
        return price;
    }
    
    // 获取产品基本信息
    private ProductBaseInfo getProductBaseInfo(String productId) throws BusinessException
    {
        return productInfoDubboConsumer.getProductById(productId);
    }
    
    // 增加订单操作日志
    private void insertDealerOrderAction(DealerOrder dealerOrder, String productNo, Map<String, BigDecimal> dealerOrderOldPriceMap)
    {
        BigDecimal oldPrice = dealerOrderOldPriceMap.get(dealerOrder.getRefrenceId());
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode("ProductAdjustPrice");
        dealerOrderAction.setActName("产品调价");
        dealerOrderAction.setActContent("产品" + productNo + "调价,订单金额由" + oldPrice + "调整为" + dealerOrder.getProductPrice());
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(dealerOrder.getRefrenceId());
        dealerOrderAction.setUserId(OnLineUserUtils.getPrincipal().getRefrenceId());
        dealerOrderAction.setUserName(OnLineUserUtils.getPrincipal().getRefrenceId());
        dealerOrderActionMapper.insert(dealerOrderAction);
    }
    
    // 存订单旧价格
    private void cacheOldOrderPrice(Map<String, BigDecimal> dealerOrderOldPriceMap, DealerOrder dealerOrder)
    {
        if (null == dealerOrderOldPriceMap.get(dealerOrder.getRefrenceId()))
        {
            dealerOrderOldPriceMap.put(dealerOrder.getRefrenceId(), dealerOrder.getProductPrice());
        }
    }
    
    @Override
    public DealerOrder findModelById(String orderId)
    {
        return dealerOrderMapper.findModelById(orderId);
    }
}
