/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.brand.model.SendGoodsAttributeModel;
import com.zttx.web.module.brand.model.SendGoodsModel;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;
import com.zttx.web.module.dealer.model.DealerOrdersModel;
import com.zttx.web.module.dealer.model.OrderItemsModel;
import com.zttx.web.module.dealer.model.OrderModel;
import com.zttx.web.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 经销商订单项信息 服务实现类
 * <p>File：DealerOrders.java </p>
 * <p>Title: DealerOrders </p>
 * <p>Description:DealerOrders </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerOrdersServiceImpl extends GenericServiceApiImpl<DealerOrders> implements DealerOrdersService {

    @Autowired
    private DealerJoinService dealerJoinService;

    @Autowired
    private BrandesInfoMapper brandesInfoMapper;

    @Autowired
    private DealerOrderService dealerOrderService;

    private DealerOrdersMapper dealerOrdersMapper;

    @Autowired(required = true)
    public DealerOrdersServiceImpl(DealerOrdersMapper dealerOrdersMapper) {
        super(dealerOrdersMapper);
        this.dealerOrdersMapper = dealerOrdersMapper;
    }

    @Override
    public List<DealerOrders> findByOrderIdAndBrandId(String orderId, String brandId) {
        return dealerOrdersMapper.findByOrderIdAndBrandId(orderId, brandId);
    }

    @Override
    public List<OrderModel> convertDealerOrdersToOrderModel(List<DealerOrders> datas) {
        if (datas == null) return null;
        Map<String, OrderModel> modelHashMap = getModelMap(datas);
        List<OrderModel> modelList = Lists.newArrayList();
        for (OrderModel orderModel : modelHashMap.values()) {
            modelList.add(orderModel);
        }
        return modelList;
    }

    @Override
    public List<Map<String, Object>> getProductMap(String dealerId, String orderId) {
        if (null != dealerId && null != orderId) {
            DealerOrders dealerOrders = new DealerOrders();
            dealerOrders.setDealerId(dealerId);
            dealerOrders.setOrderId(orderId);
            dealerOrders.setPage(new Pagination(10));
            List<Map<String, Object>> mapList = dealerOrdersMapper.getProductMap(dealerOrders);
            return mapList;
        }
        return null;
    }

    @Override
    public List<DealerOrders> selectDealerOrders(String orderId, String dealerId) {
        if (null != orderId && null != dealerId) {
            List<DealerOrders> dealerOrdersList = dealerOrdersMapper.selectDealerOrders(orderId, dealerId);
            return dealerOrdersList;
        }
        return null;
    }

    /**
     * 设置折扣
     *
     * @param datas
     * @author 李星
     */
    private void processDiscountRatio(List<DealerOrders> datas) {
        for (DealerOrders dealerOrders : datas) {
            //TODO  开发时临时注释，如果忘了  谁看到记得还原
/*            if (dealerJoinService.checkHasDiscount(dealerOrders.getDealerId(), dealerOrders.getBrandId()))
            {
               dealerOrders.setAgio(dealerJoinService.getDiscountRatio(dealerOrders.getDealerId(), dealerOrders.getBrandId()));
             }
            else*/
            {
                dealerOrders.setAgio(null);
            }
        }
    }

    /**
     * 按产品ID分组对象
     *
     * @param datas
     * @return {@link java.util.Map}
     */
    protected Map<String, OrderModel> getModelMap(List<DealerOrders> datas) {
        Map<String, OrderModel> modelHashMap = Maps.newHashMap();
        for (DealerOrders item : datas) {
            String productId = item.getProductId();
            if (modelHashMap.get(productId) != null) {
                OrderModel orderModel = modelHashMap.get(productId);
                OrderItemsModel itemsModel = new OrderItemsModel();
                itemsModel.setDiscount(item.getDiscount());
                itemsModel.setPrice(item.getPrice());
                itemsModel.setOldPrice(item.getOldPrice());
                itemsModel.setAdjustPrice(item.getAdjustPrice());
                itemsModel.setAgio(item.getAgio());
                itemsModel.setQuantity(item.getQuantity());
                itemsModel.setShipCount(item.getShipCount());
                itemsModel.setOrderId(item.getOrderId());
                itemsModel.setOrderItemId(item.getRefrenceId());
                itemsModel.setProductSkuCode(item.getProductSkuCode());
                itemsModel.setProductSkuId(item.getProductSkuId());
                itemsModel.setProductSkuName(item.getProductSkuName());
                itemsModel.setPointPercent(item.getPointPercent());
                orderModel.getItemsModels().add(itemsModel);
                modelHashMap.put(productId, orderModel);
            } else {
                OrderModel orderModel = new OrderModel();
                orderModel.setProductId(item.getProductId());
                orderModel.setProductImage(item.getProductImage());
                orderModel.setProductNo(item.getProductNo());
                orderModel.setProductTitle(item.getProductTitle());
                OrderItemsModel itemsModel = new OrderItemsModel();
                itemsModel.setDiscount(item.getDiscount());
                itemsModel.setPrice(item.getPrice());
                itemsModel.setOldPrice(item.getOldPrice());
                itemsModel.setAdjustPrice(item.getAdjustPrice());
                itemsModel.setAgio(item.getAgio());
                itemsModel.setOrderId(item.getOrderId());
                itemsModel.setOrderItemId(item.getRefrenceId());
                itemsModel.setShipCount(item.getShipCount());
                itemsModel.setQuantity(item.getQuantity());
                itemsModel.setProductSkuCode(item.getProductSkuCode());
                itemsModel.setProductSkuId(item.getProductSkuId());
                itemsModel.setProductSkuName(item.getProductSkuName());
                itemsModel.setPointPercent(item.getPointPercent());
                orderModel.getItemsModels().add(itemsModel);
                modelHashMap.put(productId, orderModel);
            }
        }
        return modelHashMap;
    }


    /**
     * 品牌ERP发货询问接口
     *
     * @param map
     * @return
     * @throws BusinessException
     */
    @Override
    public DealerOrdersModel updateOrderAsk(Map<String, String> map) throws BusinessException {
        DealerOrdersModel result = new DealerOrdersModel();
        String sendProductJson = MapUtils.getString(map, "sendProductJson");// 订单信息
        String to_expressInfo = MapUtils.getString(map, "expressInfo");// 快递信息
        JSONObject expressInfo = JSONObject.parseObject(to_expressInfo);
        String logisticName = expressInfo.getString("logisticName");// 物流公司名称
        String shipNumber = expressInfo.getString("shipNumber");// 物流单号
        result.setOrder_blank(false);// 配货是否成功,初始化为false
        if (StringUtils.isBlank(sendProductJson)) {
            throw new BusinessException("请求参数为空");
        }
        // sendProductJson = sendProductJson.substring(16);
        // sendProductJson = StringUtils.replace(sendProductJson, "0X00", ":");
        // sendProductJson = StringUtils.replace(sendProductJson, "0XFF", ",");
        boolean isUpdateOrder = true;
        boolean sendAll = false;
        int sendtotal = 0; // 总的发货数量
        int sendMuntTotal = 0; // 发货数量
        int total = 0;
        JSONArray params = new JSONArray();// 用于保存订单的信息
        JSONArray jsonArray = JSONObject.parseArray(sendProductJson);
        if (jsonArray!=null && jsonArray.size() <= 0) {
            throw new BusinessException("发货订单项是空");
        }
        SendGoodsModel sendGoodsModel = new SendGoodsModel();
        List<SendGoodsAttributeModel> sendAtts = Lists.newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String orderid = obj.getString("orderid");// 订单Id
            // 订单
            DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(orderid);
            if (null == dealerOrder) {
                obj.put("result", "找不到该订单信息");
                isUpdateOrder = false;
                continue;
            }
            sendGoodsModel.setBrandId(dealerOrder.getBrandId());
            sendGoodsModel.setLogisticName(logisticName);
            sendGoodsModel.setOrderId(dealerOrder.getOrderId());

            BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(dealerOrder.getBrandsId());
            /**
             * 增加品牌名称
             */
            sendGoodsModel.setBrandName(brandesInfo.getBrandsName());
            
            sendGoodsModel.setShipNumber(shipNumber);
            sendGoodsModel.setOrderRefrenceId(orderid);
            // 4 等待确认收货 9 交易成功 10 交易关闭 订单状态
            if (DealerConstant.DealerOrder.ORDER_STATUS_CLOSED == dealerOrder.getOrderStatus()
                    || DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE == dealerOrder.getOrderStatus()
                    || DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED == dealerOrder.getOrderStatus()) {
                obj.put("result", "订单：" + dealerOrder.getOrderId() + "，不能继续发货！");
                isUpdateOrder = false;
                continue;
            }
            sendtotal = 0; // 总的发货数量
            total = dealerOrder.getProductCount();// 总的购买数量
            JSONArray products = obj.getJSONArray("products");// 多款产品
            for (int j = 0; j < products.size(); j++) {
                SendGoodsAttributeModel sendGoodsAttributeModel = new SendGoodsAttributeModel();
                JSONObject productJsonObject = products.getJSONObject(j);
//                 String productid = productJsonObject.getString("productid");// 某一款产品的编号
                JSONArray items = productJsonObject.getJSONArray("items");// 该产品的具体型号
                if (null == dealerOrder) {
                    productJsonObject.put("result", "找不到该订单信息");
                    isUpdateOrder = false;
                    continue;
                }
                for (int k = 0; k < items.size(); k++) {
                    JSONObject json = items.getJSONObject(k);
                    String vid1 = json.getString("vid1");// 颜色
                    String vid2 = json.getString("vid2");// 尺寸
                    Integer mount = json.getInteger("mount");// 发货数量
                    String productSkuId = json.getString("skuid");
                    sendtotal += mount;
                    sendMuntTotal += mount;
                    // 订单项
                    DealerOrders dealerOrders = dealerOrdersMapper.getDealerOrdersByProductSkuId(orderid, productSkuId);
                    if (null == dealerOrders) {
                        json.put("result", "找不到该订单项信息");
                        json.put("code", CommonConst.FAIL.code);
                        isUpdateOrder = false;
                        break;
                    }
                    int iRet = this.checkOrderMount(mount, dealerOrders, params);
                    if (iRet == Integer.MIN_VALUE) {
                        json.put("result", "发货失败,货物找不到");
                        json.put("code", CommonConst.FAIL.code);
                        isUpdateOrder = false;
                    } else {
                        if (iRet == 0) {
                            json.put("result", "发货成功，待发货数量" + iRet);
                            json.put("code", CommonConst.SUCCESS.code);
                        }
                        if (iRet > 0) {
                            json.put("result", "发货成功，待发货数量" + iRet);
                            json.put("code", CommonConst.SUCCESS.code);
                        }
                        if (iRet < 0) {
                            json.put("result", "发货失败，超过最大剩余发货数量" + -iRet + "件");
                            json.put("code", CommonConst.FAIL.code);
                            isUpdateOrder = false;
                        }
                    }
                    sendtotal += dealerOrders.getShipCount() == null ? 0 : dealerOrders.getShipCount();
                    sendGoodsAttributeModel.setIsSelect(true);
                    sendGoodsAttributeModel.setSendNum(mount);
                    sendGoodsAttributeModel.setOrderItemId(dealerOrders.getRefrenceId());
                    sendGoodsAttributeModel.setProductId(dealerOrders.getProductId());
                    sendAtts.add(sendGoodsAttributeModel);
                }
            }
            sendGoodsModel.setTotalSendNum(sendMuntTotal);
            sendGoodsModel.setSendAtts(sendAtts);
        }
        if (isUpdateOrder) {// 全部成功则修改订单
            // updateOrder(params, logisticName, shipNumber);
            if (total == sendtotal) {
                sendAll = true;
            }
            dealerOrderService.sendGoods(sendGoodsModel, sendAll);
            result.setOrder_blank(true);
        }
        result.setJsonArray(jsonArray);
        return result;
    }


    private int checkOrderMount(int sendCont, DealerOrders dealerOrders, JSONArray params)
    {
        Integer quantity = dealerOrders.getQuantity();// 购买数量
        Integer shipCount = dealerOrders.getShipCount()==null?0:dealerOrders.getShipCount();// 已发货数量
        int iRest = quantity - shipCount - sendCont;// 待发货数量
        if (iRest >= 0)
        {
            JSONObject js = new JSONObject();
            js.put("orderid", dealerOrders.getOrderId());
            js.put("productid", dealerOrders.getProductId());
            js.put("productSkuId", dealerOrders.getProductSkuId());
            js.put("sg", sendCont + shipCount);// sg为发货后的已发货数量
            // js.put("iRest", iRest);//待发货数量
            js.put("sendCont", sendCont);// 发货数量
            // js.put("b", iB);//发货总量
            params.add(js);
            return iRest;
        }
        return Integer.MIN_VALUE;
    }
    
    @Override
    public List<DealerOrders> getDealerOrders(DealerOrders dealerorders) throws BusinessException
    {
        if (null == dealerorders) { throw new BusinessException("参数不能为空"); }
        return dealerOrdersMapper.getDealerOrdersByOrderId(dealerorders.getOrderId());
    }
    
    @Override
    public List<Map<String, Object>> getDealerSkuMap(String dealerId, String orderId, String productId)
    {
        return dealerOrdersMapper.getDealerSkuMap(dealerId, orderId, productId);
    }
}
