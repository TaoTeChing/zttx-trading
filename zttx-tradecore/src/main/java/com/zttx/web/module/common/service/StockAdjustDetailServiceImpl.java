/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.CreditToPoint;
import com.zttx.web.module.common.entity.PointRefund;
import com.zttx.web.module.common.entity.PointSaleDetail;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import com.zttx.web.module.common.mapper.StockAdjustDetailMapper;
import com.zttx.web.module.dealer.entity.DealerOrders;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品库存调整记录表（该表由调度执行生成） 服务实现类
 * <p>File：StockAdjustDetail.java </p>
 * <p>Title: StockAdjustDetail </p>
 * <p>Description:StockAdjustDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class StockAdjustDetailServiceImpl extends GenericServiceApiImpl<StockAdjustDetail>implements StockAdjustDetailService
{
    private StockAdjustDetailMapper stockAdjustDetailMapper;
    
    @Autowired(required = true)
    public StockAdjustDetailServiceImpl(StockAdjustDetailMapper stockAdjustDetailMapper)
    {
        super(stockAdjustDetailMapper);
        this.stockAdjustDetailMapper = stockAdjustDetailMapper;
    }
    
    @Override
    public PaginateResult selectStockAdjustDetailGroupByDatePage(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException
    {
        if (StringUtils.isBlank(stockAdjustDetail.getDealerId())
                || StringUtils.isBlank(stockAdjustDetail.getBrandId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (null != stockAdjustDetail.getStartTime())
        {
            stockAdjustDetail.setStartTimeLong(CalendarUtils.getLongFromTime(stockAdjustDetail.getStartTime()));
        }
        if (null != stockAdjustDetail.getEndTime())
        {
            stockAdjustDetail.setEndTimeLong(com.zttx.web.utils.CalendarUtils.addDay(stockAdjustDetail.getEndTime(), 1));
        }
        List<Map<String, Object>> mapList = stockAdjustDetailMapper.selectStockAdjustDetailGroupByDateList(page, stockAdjustDetail);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(stockAdjustDetailMapper.selectStockAdjustDetailGroupByDateListSum(stockAdjustDetail));
        return paginateResult;
    }
    
    @Override
    public PaginateResult selectStockAdjustDetailGroupByProductPage(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap)
            throws BusinessException
    {
        if (StringUtils.isBlank(stockAdjustDetail.getDealerId())
                || StringUtils.isBlank(stockAdjustDetail.getBrandId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        List<Map<String, Object>> mapList = stockAdjustDetailMapper.selectStockAdjustDetailGroupByProductList(page, stockAdjustDetail);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(stockAdjustDetailMapper.selectStockAdjustDetailGroupByProductListSum(stockAdjustDetail));
        return paginateResult;
    }
    
    @Override
    public PaginateResult selectStockAdjustDetailBySku(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException
    {
        if (StringUtils.isBlank(stockAdjustDetail.getBrandId()) || StringUtils.isBlank(stockAdjustDetail.getDealerId())
                || StringUtils.isBlank(stockAdjustDetail.getProductSkuId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        List<Map<String, Object>> mapList = stockAdjustDetailMapper.selectStockAdjustDetailBySkuList(page, stockAdjustDetail);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(stockAdjustDetailMapper.selectStockAdjustDetailBySkuListSum(stockAdjustDetail));
        return paginateResult;
    }
    
    @Override
    public PaginateResult selectStockAdjustDetailByTimeAndSource(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException
    {
        if (StringUtils.isBlank(stockAdjustDetail.getBrandId()) || StringUtils.isBlank(stockAdjustDetail.getDealerId()) || null == stockAdjustDetail.getSource()
                || null == stockAdjustDetail.getCreateTime()) { throw new BusinessException(CommonConst.PARAM_NULL); }
        List<Map<String, Object>> mapList = stockAdjustDetailMapper.selectStockAdjustDetailByTimeAndSourceList(page, stockAdjustDetail);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(stockAdjustDetailMapper.selectStockAdjustDetailByTimeAndSourceListSum(stockAdjustDetail));
        return paginateResult;
    }
    
    @Override
    public Map<String, Object> selectHeadData(StockAdjustDetail stockAdjustDetail) throws BusinessException
    {
        if (StringUtils.isBlank(stockAdjustDetail.getDealerId()) || StringUtils.isBlank(stockAdjustDetail.getBrandId())
                || StringUtils.isBlank(stockAdjustDetail.getProductSkuId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        Map<String, Object> resultMap = Maps.newHashMap();
        List<Map<String, Object>> mapList = stockAdjustDetailMapper.selectHeadData(stockAdjustDetail);
        if (mapList == null || mapList.isEmpty()) { throw new BusinessException(CommonConst.FAIL.getCode(), "sku对应信息不存在，查询失败！"); }
        resultMap.put("brandsName", mapList.get(0).get("brandsName"));
        resultMap.put("productTitle", mapList.get(0).get("productTitle"));
        resultMap.put("productNo", mapList.get(0).get("productNo"));
        resultMap.put("productSkuName", mapList.get(0).get("productSkuName"));
        Map<String, Object> map = Maps.newHashMap();
        resultMap.put("type", map);
        for (int i = 0, length = mapList.size(); i < length; i++)
        {
            String typeValue = mapList.get(i).get("type").toString();
            switch (Integer.parseInt(typeValue))
            {
                case 0:
                    map.put("采购", typeValue);
                    continue;
                case 1:
                    map.put("销售", typeValue);
                    continue;
                case 2:
                    map.put("退货", typeValue);
                    continue;
                case 3:
                    map.put("铺货变返点", typeValue);
                    continue;
            }
        }
        return resultMap;
    }
    
    @Override
    public <T> void  addStockAdjustDetail(Integer type, List<T> objectList) throws BusinessException
    {
        if (null==type || null == objectList || objectList.isEmpty()) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数为null"); }
        List<StockAdjustDetail> stockAdjustDetailList = Lists.newArrayList();
        switch (type)
        {

            case StockAdjustDetail.TYPE_SEND:
            {
                if (objectList.get(0) instanceof DealerOrders)
                {
                    this.transDealerOrdersToStockAdjustDetail((List<DealerOrders>)objectList, stockAdjustDetailList);
                }
                break;
            }
            case StockAdjustDetail.TYPE_SALE:
            {
                if (objectList.get(0) instanceof PointSaleDetail)
                {
                    this.transPointSaleDetailToStockAdjustDetail((List<PointSaleDetail>)objectList, stockAdjustDetailList);
                }
                break;
            }
            case StockAdjustDetail.TYPE_REFUND:
            {
                if (objectList.get(0) instanceof PointRefund)
                {
                    this.transPointRefundToStockAdjustDetail((List<PointRefund>)objectList, stockAdjustDetailList);
                }
                break;
            }
            case StockAdjustDetail.TYPE_CREDITTOPOINT:
            {
                if (objectList.get(0) instanceof CreditToPoint)
                {
                    this.transCreditToPointToStockAdjustDetail((List<CreditToPoint>)objectList, stockAdjustDetailList);
                }
                break;
            }
            default:
                throw new BusinessException(CommonConst.FAIL.getCode(), "参数type不在范围中！");
        }
        stockAdjustDetailMapper.insertBatch(stockAdjustDetailList);
    }

    private void transCreditToPointToStockAdjustDetail(List<CreditToPoint> objectList, List<StockAdjustDetail> stockAdjustDetailList) {
        for(int i=0,length=objectList.size();i<length;i++)
        {
            CreditToPoint creditToPoint = objectList.get(i);
            StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
            stockAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            stockAdjustDetail.setDealerId(creditToPoint.getDealerId());
            stockAdjustDetail.setBrandId(creditToPoint.getBrandId());
            stockAdjustDetail.setBrandsId(creditToPoint.getBrandsId());
            stockAdjustDetail.setProductId(creditToPoint.getProductId());
            stockAdjustDetail.setProductSkuId(creditToPoint.getProductSkuId());
            stockAdjustDetail.setBaseStock(creditToPoint.getBaseStock());
            stockAdjustDetail.setSendNum(0);
            stockAdjustDetail.setSaleNum(0);
            stockAdjustDetail.setRefundNum(0);
            stockAdjustDetail.setCreateTime(creditToPoint.getErpTime());
            stockAdjustDetail.setSource((short) StockAdjustDetail.TYPE_CREDITTOPOINT);
            stockAdjustDetail.setDelFlag(false);
            stockAdjustDetailList.add(stockAdjustDetail);
        }
    }

    private void transPointRefundToStockAdjustDetail(List<PointRefund> objectList, List<StockAdjustDetail> stockAdjustDetailList) {
        for(int i=0,length=objectList.size();i<length;i++)
        {
            PointRefund pointRefund =objectList.get(i);
            StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
            stockAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            stockAdjustDetail.setDealerId(pointRefund.getDealerId());
            stockAdjustDetail.setBrandId(pointRefund.getBrandId());
            stockAdjustDetail.setBrandsId(pointRefund.getBrandsId());
            stockAdjustDetail.setProductId(pointRefund.getProductId());
            stockAdjustDetail.setProductSkuId(pointRefund.getProductSkuId());
            stockAdjustDetail.setBaseStock(0);
            stockAdjustDetail.setSendNum(0);
            stockAdjustDetail.setSaleNum(0);
            stockAdjustDetail.setRefundNum(pointRefund.getRefundNum());
            stockAdjustDetail.setCreateTime(pointRefund.getErpTime());
            stockAdjustDetail.setSource((short) StockAdjustDetail.TYPE_REFUND);
            stockAdjustDetail.setDelFlag(false);
            stockAdjustDetailList.add(stockAdjustDetail);
        }
    }


    private void transPointSaleDetailToStockAdjustDetail(List<PointSaleDetail> objectList, List<StockAdjustDetail> stockAdjustDetailList) {

        for(int i=0,length=objectList.size();i<length;i++)
        {
            PointSaleDetail pointSaleDetail = objectList.get(i);
            StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
            stockAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            stockAdjustDetail.setDealerId(pointSaleDetail.getDealerId());
            stockAdjustDetail.setBrandId(pointSaleDetail.getBrandId());
            stockAdjustDetail.setBrandsId(pointSaleDetail.getBrandsId());
            stockAdjustDetail.setProductId(pointSaleDetail.getProductId());
            stockAdjustDetail.setProductSkuId(pointSaleDetail.getProductSkuId());
            stockAdjustDetail.setBaseStock(0);
            stockAdjustDetail.setSendNum(0);
            stockAdjustDetail.setSaleNum(pointSaleDetail.getLossNum() + pointSaleDetail.getSaleNum() - pointSaleDetail.getRefundNum());  //盘亏+销售-退货
            stockAdjustDetail.setRefundNum(0);
            stockAdjustDetail.setCreateTime(pointSaleDetail.getErpTime());
            stockAdjustDetail.setSource((short) StockAdjustDetail.TYPE_SALE);
            stockAdjustDetail.setDelFlag(false);
            stockAdjustDetailList.add(stockAdjustDetail);
        }

    }


    private  void transDealerOrdersToStockAdjustDetail(List<DealerOrders> objectList, List<StockAdjustDetail> stockAdjustDetailList)
    {
        for(int i=0,length=objectList.size();i<length;i++)
        {
            DealerOrders dealerOrders =objectList.get(i);
            StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
            stockAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            stockAdjustDetail.setDealerId(dealerOrders.getDealerId());
            stockAdjustDetail.setBrandId(dealerOrders.getBrandId());
            stockAdjustDetail.setBrandsId(dealerOrders.getBrandsId());
            stockAdjustDetail.setProductId(dealerOrders.getProductId());
            stockAdjustDetail.setProductSkuId(dealerOrders.getProductSkuId());
            stockAdjustDetail.setOrderNo(dealerOrders.getOrderNo());
            stockAdjustDetail.setBaseStock(0);
            stockAdjustDetail.setSendNum(dealerOrders.getSendNumEvery());
            stockAdjustDetail.setSaleNum(0);
            stockAdjustDetail.setRefundNum(0);
            stockAdjustDetail.setCreateTime(dealerOrders.getUpdateTime());
            stockAdjustDetail.setSource((short) StockAdjustDetail.TYPE_SEND);
            stockAdjustDetail.setDelFlag(false);
            stockAdjustDetailList.add(stockAdjustDetail);
        }
    }
}
