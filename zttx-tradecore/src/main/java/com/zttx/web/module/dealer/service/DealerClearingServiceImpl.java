/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerClearing;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.mapper.DealerClearingMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工厂店品牌信息结算表 服务实现类
 * <p>File：DealerClearing.java </p>
 * <p>Title: DealerClearing </p>
 * <p>Description:DealerClearing </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerClearingServiceImpl extends GenericServiceApiImpl<DealerClearing>implements DealerClearingService
{
    private final static Logger  logger = LoggerFactory.getLogger(DealerClearingServiceImpl.class);
    
    @Autowired
    private DealerJoinService    dealerJoinService;
    
    @Autowired
    private DealerOrderMapper    dealerOrderMapper;
    
    private DealerClearingMapper dealerClearingMapper;
    
    @Autowired(required = true)
    public DealerClearingServiceImpl(DealerClearingMapper dealerClearingMapper)
    {
        super(dealerClearingMapper);
        this.dealerClearingMapper = dealerClearingMapper;
    }
    
    @Override
    public Map<String, Object> selectDealerClearing(Pagination pagination, DealerClearing dealerClearing) throws BusinessException
    {
        if (null == dealerClearing.getDealerId() || null == dealerClearing.getBrandId()) { throw new BusinessException(CommonConst.PARAM_NULL); }
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> resultSumMap = Maps.newHashMap();
        dealerClearing.setPage(pagination);
        List<DealerClearing> dealerClearingBrandList = dealerClearingMapper.selectDealerClearingListGroupByClearingTime(dealerClearing);
        BigDecimal allOwedPrice = BigDecimal.ZERO;
        BigDecimal allPayPrice = BigDecimal.ZERO;
        BigDecimal allDebtPrice = BigDecimal.ZERO;
        Map<String, Object> tempDealerJoin = Maps.newHashMap(); // 记录授信金额的可用额度
        if (null != dealerClearingBrandList && !dealerClearingBrandList.isEmpty())
        {
            for (int i = dealerClearingBrandList.size() - 1; i >= 0; i--) // 反栈循环,实现先进后出
            {
                DealerClearing clearing = dealerClearingBrandList.get(i);
                Map<String, Object> sumMap = this.selectDealerClearing(tempDealerJoin, clearing);
                clearing.setClearingAmount((BigDecimal) sumMap.get("totalOwedPrice"));
                clearing.setHasClearingAmount((BigDecimal) sumMap.get("totalPayPrice"));
                allOwedPrice = allOwedPrice.add((BigDecimal) sumMap.get("totalOwedPrice"));
                allPayPrice = allPayPrice.add((BigDecimal) sumMap.get("totalPayPrice"));
                allDebtPrice = allDebtPrice.add((BigDecimal) sumMap.get("totalDebtPrice"));
            }
        }
        PaginateResult<DealerClearing> paginateResult = new PaginateResult<>(pagination, dealerClearingBrandList);
        resultSumMap.put("allOwedPrice", allOwedPrice);
        resultSumMap.put("allPayPrice", allPayPrice);
        resultSumMap.put("allDebtPrice", allDebtPrice);
        resultMap.put("recordResult", paginateResult);
        resultMap.put("recordSumResult", resultSumMap);
        return resultMap;
    }
    
    // 先弄明白业务，不然就是天书
    protected Map<String, Object> selectDealerClearing(Map<String, Object> tempDealerJoin, DealerClearing dealerClearing) throws BusinessException
    {
        if (null == dealerClearing.getDealerId() || null == dealerClearing.getBrandsId()) { throw new BusinessException(CommonConst.PARAM_NULL); }
        Map<String, Object> sumMap = Maps.newHashMap();
        List<DealerClearing> dealerClearingList = dealerClearingMapper.selectDealerClearingList(dealerClearing);
        if (dealerClearingList.isEmpty()) { return null; }
        BigDecimal totalOwedPrice = BigDecimal.ZERO;
        BigDecimal totalPayPrice = BigDecimal.ZERO;
        BigDecimal totalDebtPrice = BigDecimal.ZERO;
        for (DealerClearing item : dealerClearingList)
        {
            if (null == item.getClearingStatus())
            {
                item.setClearingStatus(false);
            }
            if (!item.getClearingStatus())
            {
                DealerJoin dealerJoin = (DealerJoin) tempDealerJoin.get(item.getDealerId().concat(item.getBrandsId()));
                if (null == dealerJoin)
                {
                    dealerJoin = dealerJoinService.getByDealerIdAndBrandsId(item.getDealerId(), item.getBrandsId());
                    if (null == dealerJoin)
                    {
                        continue;
                    } // 品牌加盟关系不存在，财务帐明细不存在
                    tempDealerJoin.put(item.getDealerId().concat(item.getBrandsId()), dealerJoin);
                }
                BigDecimal tempClearingAmount = item.getClearingAmount(); // 可以为负数
                BigDecimal creditCurrent = BigDecimal.ZERO;
                if (item.getIsClearingAmount())
                {
                    creditCurrent = dealerJoin.getCreditCurrent();
                }
                else
                {
                    creditCurrent = dealerJoin.getCreditCurrent().subtract(dealerJoin.getClearingAmount()); // 已用的授信额度-当期已经摊的授信额度
                }
                if (creditCurrent.compareTo(tempClearingAmount) != 1)
                {
                    item.setHasClearingAmount(tempClearingAmount.subtract(dealerJoin.getCreditCurrent())); // 已付金额
                    if (creditCurrent.compareTo(BigDecimal.ZERO) == 0) // 当已用的授信额度变为0，则说明授信额度已经摊平
                    {
                        item.setClearingStatus(true);
                        item.setUpdateTime(CalendarUtils.getCurrentLong());
                        dealerClearingMapper.updateByPrimaryKeySelective(item);
                    }
                }
                else
                {
                    item.setHasClearingAmount(BigDecimal.ZERO);
                }
                if (!item.getIsClearingAmount()) // 已经摊过的就不在刷新页面往里摊了
                {
                    tempClearingAmount = tempClearingAmount.subtract(item.getHasClearingAmount());
                    dealerJoin.setClearingAmount(dealerJoin.getClearingAmount().add(tempClearingAmount)); // 变更已经摊到授信额度中的金额
                    dealerJoinService.updateByPrimaryKey(dealerJoin);
                    item.setUpdateTime(CalendarUtils.getCurrentLong());
                    item.setIsClearingAmount(true); // 摊到授信订单中
                    dealerClearingMapper.updateByPrimaryKeySelective(item);
                }
                totalPayPrice = totalPayPrice.add(item.getHasClearingAmount());
            }
            else
            {
                totalPayPrice = totalPayPrice.add(item.getClearingAmount());
            }
            totalOwedPrice = totalOwedPrice.add(item.getClearingAmount());
        }
        totalDebtPrice = totalOwedPrice.subtract(totalPayPrice);
        sumMap.put("totalOwedPrice", totalOwedPrice);
        sumMap.put("totalPayPrice", totalPayPrice);
        sumMap.put("totalDebtPrice", totalDebtPrice);
        return sumMap;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, Object> fixDealerClearingNoPayedBy(String dealerId, String brandId)
    {
        Map<String, Object> resMap = findDealerClearingNoPayedBy(dealerId, brandId);
        DealerClearing dealerClearing = new DealerClearing();
        dealerClearing.setBrandId(brandId);
        dealerClearing.setDealerId(dealerId);
        BigDecimal hadPayedAmount = (BigDecimal) MapUtils.getObject(resMap, "hadPayedAmount", 0);
        BigDecimal needPayAmount = (BigDecimal) MapUtils.getObject(resMap, "needPayAmount", 0);
        /**成本与订单支付相等时完成结算*/
        if (hadPayedAmount.compareTo(needPayAmount) == 0)
        {
            // 结算表状态修改成已经结算
            dealerClearingMapper.updateDealerClearingBy(dealerClearing);
            // 订单成本结算
            List<String> orderIds = (List<String>) MapUtils.getObject(resMap, "orderIds", 0);
            List<BigDecimal> orderPayeds = (List<BigDecimal>) MapUtils.getObject(resMap, "orderPayeds", 0);
            for (int i = 0; i < orderIds.size(); i++)
            {
                DealerOrder dealerOrder = dealerOrderMapper.getDealerOrder(orderIds.get(i), dealerId);
                dealerOrder.setClearingAmount(dealerOrder.getClearingAmount() == null ? orderPayeds.get(i) : dealerOrder.getClearingAmount().add(orderPayeds.get(i)));
                if (dealerOrder.getPayState().equals(DealerConstant.DealerOrder.PAY_STATE_ALL))
                {
                    dealerOrder.setClearingStatus(true);
                }
                dealerOrderMapper.updateByPrimaryKey(dealerOrder);
            }
        }
        return resMap;
    }
    
    @Override
    public DealerClearing selectDealerClearingSelective(DealerClearing dealerClearing)
    {
        return dealerClearingMapper.selectDealerClearingSelective(dealerClearing);
    }
    
    @Override
    public Map<String, Object> findDealerClearingNoPayedBy(String dealerId, String brandId)
    {
        Map<String, Object> resMap = Maps.newHashMap();
        Map<String, Object> result = calcTotalDealerClearAmount(dealerId, brandId);
        // 当期成本
        // double currentCost = MapUtils.getDoubleValue(result, "total", 0);
        BigDecimal currentCost = new BigDecimal(MapUtils.getString(result, "total", "0"));
        // 未完成成本结算的订单
        List<DealerOrder> dealerOrderList = dealerOrderMapper.selectNoneCostSettlementOrderBy(dealerId, brandId);
        BigDecimal hadPayedAmount = BigDecimal.ZERO;
        List<String> orderIds = Lists.newArrayList();
        List<BigDecimal> orderPayeds = Lists.newArrayList();
        List<BigDecimal> orderNdpays = Lists.newArrayList();
        List<BigDecimal> orderTotals = Lists.newArrayList();
        for (DealerOrder dealerOrder : dealerOrderList)
        {
            // 成本小于等于0 结束
            if (currentCost.compareTo(BigDecimal.ZERO) < 1) break;
            BigDecimal totalAmount = BigDecimal.ZERO; // 订单总额
            BigDecimal payedAmount = BigDecimal.ZERO; // 支付金额
            BigDecimal productPrice = getPrice(dealerOrder.getProductPrice()); // 货物原价
            if (productPrice.compareTo(BigDecimal.ZERO) > 0)
            {
                totalAmount = totalAmount.add(productPrice);
            }
            BigDecimal adjustPrice = getPrice(dealerOrder.getAdjustPrice()); // 优惠金额
            if (adjustPrice.compareTo(BigDecimal.ZERO) != 0)
            {
                totalAmount = totalAmount.add(adjustPrice);
            }
            BigDecimal adjustFreight = getPrice(dealerOrder.getAdjustFreight());// 运费金额
            if (adjustFreight.compareTo(BigDecimal.ZERO) > 0)
            {
                totalAmount = totalAmount.add(adjustFreight);
            }
            BigDecimal payment = getPrice(dealerOrder.getPayment()); // 已付货款
            if (payment.compareTo(BigDecimal.ZERO) > 0)
            {
                payedAmount = payedAmount.add(payment);
            }
            if ("1".equals(dealerOrder.getFrePayState().toString()))
            { // 已付运费
                payedAmount = payedAmount.add(adjustFreight);
            }
            // 已有结算过部分成本时
            if (getPrice(dealerOrder.getClearingAmount()).compareTo(BigDecimal.ZERO) > 0)
            {
                totalAmount = totalAmount.subtract(dealerOrder.getClearingAmount());
                payedAmount = payedAmount.subtract(dealerOrder.getClearingAmount());
            }
            // 成本仍高于当前订单货总值的话
            if (currentCost.compareTo(totalAmount) >= 0)
            {
                hadPayedAmount = hadPayedAmount.add(payedAmount);
                currentCost = currentCost.subtract(totalAmount);
            }
            else
            {
                totalAmount = currentCost;
                if (payedAmount.compareTo(currentCost) == 1)
                {
                    payedAmount = currentCost;
                }
                hadPayedAmount = hadPayedAmount.add(payedAmount);
                currentCost = BigDecimal.ZERO;
            }
            orderPayeds.add(payedAmount);
            orderNdpays.add(totalAmount.subtract(payedAmount));
            orderTotals.add(totalAmount);
            orderIds.add(dealerOrder.getRefrenceId());
        }
        resMap.put("hadPayedAmount", hadPayedAmount);
        resMap.put("needPayAmount", new BigDecimal(MapUtils.getString(result, "total", "0")));
        resMap.put("nextPayAmount", new BigDecimal(MapUtils.getString(result, "total", "0")).subtract(hadPayedAmount));
        resMap.put("orderIds", orderIds);
        resMap.put("orderPayeds", orderPayeds);
        resMap.put("orderNdpays", orderNdpays);
        resMap.put("orderTotals", orderTotals);
        return resMap;
    }

    @Override
    public Map<String, Object> selectDealerClearingPayTime(DealerClearing dealerClearing) throws BusinessException {
        if(StringUtils.isBlank(dealerClearing.getDealerId()) || StringUtils.isBlank(dealerClearing.getBrandId())){throw new BusinessException(CommonConst.PARAM_NULL);}
        return dealerClearingMapper.selectDealerClearingPayTime(dealerClearing);
    }

    private Map<String, Object> calcTotalDealerClearAmount(String dealerId, String brandId)
    {
        DealerClearing dealerClearing = new DealerClearing();
        dealerClearing.setBrandId(brandId);
        dealerClearing.setDealerId(dealerId);
        List<DealerClearing> dealerClearings = dealerClearingMapper.selectDealerClearingNoPayedByDealerIdAndBrandId(dealerClearing);
        BigDecimal total = BigDecimal.ZERO;
        List<String> dealerClearingIds = Lists.newArrayList();
        for (DealerClearing _dealerClearing : dealerClearings)
        {
            total = total.add(getPrice(_dealerClearing.getClearingAmount()));
            dealerClearingIds.add(_dealerClearing.getRefrenceId());
        }
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("total", total);
        return resMap;
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
    
    /* 同步erp每日财务帐 */
    @Override
    public DealerClearing create(String dealerId, String brandId, String brandsId, Long searchTime, Map<String, Object> map) throws BusinessException
    {
        BigDecimal saleTotalPrice = map.get("saleTotalPrice") == null ? null : new BigDecimal(map.get("saleTotalPrice").toString());
        BigDecimal saleTotalCostPrice = map.get("saleTotalCostPrice") == null ? null : new BigDecimal(map.get("saleTotalCostPrice").toString());
        BigDecimal backTotalCostPrice = map.get("backTotalCostPrice") == null ? null : new BigDecimal(map.get("backTotalCostPrice").toString());
        BigDecimal pkTotalCostPrice = map.get("pkTotalCostPrice") == null ? null : new BigDecimal(map.get("pkTotalCostPrice").toString());
        Long saleNum = map.get("saleNum") == null ? null : new Long(map.get("saleNum").toString());
        if (null == saleTotalPrice)
        {
            LoggerUtils.logError(logger,
                    "销售金额为空：dealerId=" + dealerId + " brandId=" + brandId + " 时间：" + CalendarUtils.getStringTime(searchTime, ApplicationConst.DATE_FORMAT_YMDHMS));
            return null;
        }
        if (null == saleTotalCostPrice)
        {
            LoggerUtils.logError(logger,
                    "销售成本为空：dealerId=" + dealerId + " brandId=" + brandId + " 时间：" + CalendarUtils.getStringTime(searchTime, ApplicationConst.DATE_FORMAT_YMDHMS));
            return null;
        }
        if (null == saleNum)
        {
            LoggerUtils.logError(logger,
                    "销售数量为空：dealerId=" + dealerId + " brandId=" + brandId + " 时间：" + CalendarUtils.getStringTime(searchTime, ApplicationConst.DATE_FORMAT_YMDHMS));
            return null;
        }
        DealerClearing obj = new DealerClearing();
        obj.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        obj.setBrandId(brandId);
        obj.setDealerId(dealerId);
        obj.setBrandsId(brandsId);
        obj.setClearingStatus(false);
        obj.setSalesAmount(saleTotalPrice);
        obj.setClearingTime(searchTime);
        obj.setClearingAmount(saleTotalCostPrice.subtract(backTotalCostPrice).add(pkTotalCostPrice));
        obj.setIsClearingAmount(false);
        obj.setClearingVolume(saleNum);
        obj.setCreateTime(CalendarUtils.getCurrentLong());
        return obj;
    }
    
    @Override
    public Map<String, Object> selectDealerClearing(String dealerId, String brandId)
    {
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId))
        {
            DealerClearing dealerClearing = new DealerClearing();
            dealerClearing.setDealerId(dealerId);
            dealerClearing.setBrandId(brandId);
            return dealerClearingMapper.selectDealerClearingNoPayed(dealerClearing);
        }
        return null;
    }
}
