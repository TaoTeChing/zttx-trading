/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandPointBalance;
import com.zttx.web.module.brand.mapper.BrandPointBalanceMapper;
import com.zttx.web.module.brand.model.BrandPointBalanceModel;
import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;
import com.zttx.web.module.dealer.mapper.DealerRefundMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.NumericUtils;

/**
 * 扣点表 服务实现类
 * <p>File：BrandPointBalance.java </p>
 * <p>Title: BrandPointBalance </p>
 * <p>Description:BrandPointBalance </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandPointBalanceServiceImpl extends GenericServiceApiImpl<BrandPointBalance> implements BrandPointBalanceService
{
    @Autowired
    private DealerOrdersMapper      dealerOrdersMapper;
    
    @Autowired
    private DealerRefundMapper      dealerRefundMapper;
    
    @Autowired
    private OrderPayService             orderPayService;
    
    @Autowired
    private BrandPointBalanceLogService  brandPointBalanceLogService;

    private BrandPointBalanceMapper brandPointBalanceMapper;

    @Autowired(required = true)
    public BrandPointBalanceServiceImpl(BrandPointBalanceMapper brandPointBalanceMapper)
    {
        super(brandPointBalanceMapper);
        this.brandPointBalanceMapper = brandPointBalanceMapper;
    }
    
    @Override
    public BigDecimal getPointBalance(DealerOrder order, BigDecimal payment) throws BusinessException
    {
        if (null == order) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        // 如果是授信订单，则计算每次的支付的扣点
        if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == order.getDealerOrderType().intValue())
        {
            if (null != payment)
            {
                if (null != order.getAdjustFreight() && order.getAdjustFreight().compareTo(BigDecimal.ZERO) > 0
                        && DealerConstant.DealerOrder.FRE_PAY_STATE_UNPAY == order.getFrePayState().shortValue())
                {
                    //todo 待验证:传进来的paymen加了运费?
                    payment = payment.subtract(order.getAdjustFreight());
                }
                if (payment.compareTo(BigDecimal.ZERO) > 0 && null != order.getMinPoint()) { return payment.multiply(order.getMinPoint()); }
            }
            else
            {
                if (null != order.getMinPoint())
                {
                    payment = order.getPayment();
                    /*if(null != order.getAdjustFreight() && order.getFrePayState().shortValue() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED)
                    {
                        payment = payment.add(order.getAdjustFreight());
                    }*/
                    BigDecimal debtMoney = orderPayService.countCretditAmountUsed(order); // 计算授信额度
                    if (payment.compareTo(BigDecimal.ZERO) > 0)
                    {
                        //有调价和关闭发货需退款
                        BigDecimal needRefundAmount = orderPayService.countRefundAmount(order);
                        if(needRefundAmount.compareTo(BigDecimal.ZERO) > 0)
                        {
                            if(needRefundAmount.compareTo(debtMoney) > 0)
                            {
                                payment = payment.subtract(needRefundAmount.subtract(debtMoney));
                                debtMoney = BigDecimal.ZERO;
                            }
                            else{
                                debtMoney = debtMoney.subtract(order.getNoSendGoodsAmount());
                            }
                        }
                        // 如果有退款
                        DealerRefund dealerRefund = dealerRefundMapper.findByOrderId(order.getRefrenceId());
                        if (null != dealerRefund)
                        {
                            if (DealerConstant.DealerRefund.AGREE_REFUND == order.getRefundStatus().shortValue()
                                    || DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND == order.getRefundStatus().shortValue())
                            {
                                BigDecimal cashMoney = BigDecimal.ZERO; // 需退余额现金
                                if (dealerRefund.getRefundAmount().compareTo(debtMoney) > 0)
                                {
                                    // 如果退款金额大于欠收金额
                                    cashMoney = dealerRefund.getRefundAmount().subtract(debtMoney);
                                }
                                if (cashMoney.compareTo(BigDecimal.ZERO) > 0 && payment.compareTo(cashMoney) >= 0)
                                {
                                    payment = payment.subtract(cashMoney);
                                }
                            }
                        }
                    }
                    if (payment.compareTo(BigDecimal.ZERO) > 0) return payment.multiply(order.getMinPoint());
                }
            }
        }
        else
        {
            List<Map<String, Object>> ordersList = dealerOrdersMapper.getDealerOrdersList(order.getRefrenceId());
            if (CollectionUtils.isNotEmpty(ordersList))
            {
                BigDecimal point = BigDecimal.ZERO;
                for (Map<String, Object> item : ordersList)
                {
                    Object obj = item.get("pointAmount");
                    if (null != obj)
                    {
                        point = point.add(new BigDecimal(obj.toString()).abs());
                    }
                }
                if (null != order.getMinPoint())
                {
                    BigDecimal adjustPrice = BigDecimal.ZERO;
                    // 优惠或加价部分的扣点
                    if (null != order.getAdjustPrice())
                    {
                        adjustPrice = order.getAdjustPrice().multiply(order.getMinPoint());
                    }
                    point = point.add(adjustPrice);
                    // 计算退款扣点
                    DealerRefund dealerRefund = dealerRefundMapper.findByOrderId(order.getRefrenceId());
                    if (null != dealerRefund)
                    {
                        if (DealerConstant.DealerRefund.AGREE_REFUND == order.getRefundStatus().shortValue()
                                || DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND == order.getRefundStatus().shortValue())
                        {
                            BigDecimal refundAmount = BigDecimal.ZERO;
                            if (null != dealerRefund.getRefundAmount())
                            {
                                refundAmount = dealerRefund.getRefundAmount().multiply(order.getMinPoint());
                            }
                            point = point.subtract(refundAmount);
                        }
                    }
                    // 关闭发货未发货的产品金额的扣点
                    BigDecimal noSendGoodsPart = BigDecimal.ZERO;
                    if (null != order.getNoSendGoodsAmount())
                    {
                        noSendGoodsPart = order.getNoSendGoodsAmount().multiply(order.getMinPoint());
                    }
                    point = point.subtract(noSendGoodsPart);
                }
                if (point.compareTo(BigDecimal.ZERO) > 0)
                {
                    point = NumericUtils.round(point, 2);
                    return point;
                }
            }
        }
        return BigDecimal.ZERO;
    }
    
    /**
     * 搜索扣点
     * @author 陈建平
     * @param filter
     * @return
     */
    @Override
    public List<BrandPointBalance> findByBrandPointBalanceModel(BrandPointBalanceModel filter){
    	return brandPointBalanceMapper.findByBrandPointBalanceModel(filter);
    }
    
    /**
     * 修改扣点
     * @param brandsId 品牌ID
     * @param operName 操作人
     * @author 张昌苗
     */
    @Override
    public void executeChangePoint(String brandsId, String[] joinFormArr, String[] pointArr, String operName) throws BusinessException
    {
        for (int i = 0; i < joinFormArr.length; i++)
        {
            String joinForm = "null".equals(joinFormArr[i]) ? null : joinFormArr[i];
            Double point = "null".equals(pointArr[i]) ? null : new Double(pointArr[i]);
            this.executeChangePoint(brandsId, joinForm, point, operName);
        }
    }
    
    /**
     * 修改扣点
     * @author 陈建平
     * @param brandsId
     * @param joinForm
     * @param point
     * @param operName
     * @throws BusinessException
     */
    @Override
    public void executeChangePoint(String brandsId, String joinForm, Double point, String operName) throws BusinessException
    {
    	joinForm = StringUtils.isBlank(joinForm) ? null : joinForm;
        BrandPointBalance oldObj = findPointBalance(brandsId, Short.parseShort(joinForm));
        if (null == point)
        {
            if (null != oldObj)
            {
                brandPointBalanceLogService.executeDelLog(brandsId, operName, Short.valueOf(joinForm));
                brandPointBalanceMapper.deleteByPrimaryKey(oldObj.getRefrenceId());
            }
            return;
        }
        if (null == oldObj)
        {
            BrandPointBalance newObj = new BrandPointBalance();
            newObj.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            newObj.setBrandsId(brandsId);
            //newObj.setActivityCode(joinForm);
            newObj.setJoinForm(Short.valueOf(joinForm));
            newObj.setPoint(new BigDecimal(point));
            newObj.setCreateTime(CalendarUtils.getCurrentLong());
            newObj.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            brandPointBalanceLogService.executeAddLog(brandsId, operName, Short.valueOf(joinForm), new BigDecimal(point));
            brandPointBalanceMapper.insert(newObj);
        }
        else
        {
            if (oldObj.getPoint().compareTo(new BigDecimal(point)) != 0)
            {
                brandPointBalanceLogService.executeUpdLog(brandsId, operName, Short.valueOf(joinForm), oldObj.getPoint(), new BigDecimal(point));
                oldObj.setPoint(new BigDecimal(point));
                brandPointBalanceMapper.updateByPrimaryKey(oldObj);
            }
        }
    }
    
    /**
     * 获取扣点
     * @author 陈建平
     * @param brandsId
     * @param joinForm
     * @return
     */
    @Override
    public BrandPointBalance findPointBalance(String brandsId, Short joinForm)
    {
    	if(StringUtils.isBlank(brandsId) || null == joinForm) {
    		return null;
    	}
    	BrandPointBalance filter = new BrandPointBalance();
    	filter.setBrandsId(brandsId);
    	filter.setJoinForm(joinForm);
    	List<BrandPointBalance> list = brandPointBalanceMapper.findList(filter);
    	if(null!=list && list.size()>0){
    		return list.get(0);
    	}
        return null;
    }
    
    /**
     * 扣点数据
     * @author 陈建平
     * @param brandsId
     * @return
     */
    @Override
    public Map<String, Object> findPointData(String brandsId)
    {
    	BrandPointBalanceModel filter = new BrandPointBalanceModel();
        filter.setBrandsId(brandsId);
        PaginateResult<Map<String, Object>> result = searchPointData(filter, new Pagination());
        if (CollectionUtils.isEmpty(result.getList())) { return null; }
        return result.getList().get(0);
    }
    
    /**
     * 分页获取扣点
     * @author 陈建平
     * @param filter
     * @param page
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> searchPointData(BrandPointBalanceModel filter, Pagination page)
    {
    	filter.setPage(page);
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>(page,brandPointBalanceMapper.findVlidBrandesInfo(filter));
        for (Map<String, Object> pointDataMap : result.getList())
        {
            String brandsId = (String) pointDataMap.get("brandsId");
            BrandPointBalance brandPointBalance = new BrandPointBalance();
            brandPointBalance.setBrandsId(brandsId);
            pointDataMap.put("pointList", brandPointBalanceMapper.findList(brandPointBalance));
        }
        return result;
    }
    
    /**
     * 初始化扣点值
     * @author 陈建平
     * @param joinForm
     * @param point
     */
    @Override
    public void saveInitBrandPointBalance(Short joinForm,Double point){
    	if(null != joinForm){
    		BrandPointBalance brandPointBalance = new BrandPointBalance();
        	brandPointBalance.setJoinForm(DealerConstant.DealerOrder.ORDER_TYPE_CASH);
        	List<BrandPointBalance> list = brandPointBalanceMapper.findList(brandPointBalance);
        	for(BrandPointBalance temp : list){
        		BrandPointBalance oldObj = findPointBalance(temp.getBrandsId(), joinForm);
        		if(null == oldObj){
        			BrandPointBalance newObj = new BrandPointBalance();
                    newObj.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    newObj.setBrandsId(temp.getBrandsId());
                    newObj.setJoinForm(Short.valueOf(joinForm));
                    if(null == point){
                    	point = 0.03;
                    }
                    newObj.setPoint(new BigDecimal(point));
                    newObj.setCreateTime(CalendarUtils.getCurrentLong());
                    newObj.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                    brandPointBalanceMapper.insert(newObj);
        		}
        	}
    	}
    }
}
