/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.pay.remoting.model.TransferObj;
import com.zttx.pay.remoting.model.TransferReturnObj;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.common.entity.FinancialPay;
import com.zttx.web.module.common.entity.PointSaleSum;
import com.zttx.web.module.common.mapper.PointSaleSumMapper;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ListUtils;
import com.zttx.web.utils.SerialNumberUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 返点类型产品门店销售统计和表 服务实现类
 * <p>File：PointSaleSum.java </p>
 * <p>Title: PointSaleSum </p>
 * <p>Description:PointSaleSum </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class PointSaleSumServiceImpl extends GenericServiceApiImpl<PointSaleSum>implements PointSaleSumService
{
    Logger                      logger = LoggerFactory.getLogger(PointSaleSumServiceImpl.class);
    
    private PointSaleSumMapper  pointSaleSumMapper;
    
    @Autowired
    private PayApiService       payApiService;
    
    @Autowired
    private FinancialPayService financialPayService;
    
    @Autowired
    private UserInfoService     userInfoService;
    
    @Autowired(required = true)
    public PointSaleSumServiceImpl(PointSaleSumMapper pointSaleSumMapper)
    {
        super(pointSaleSumMapper);
        this.pointSaleSumMapper = pointSaleSumMapper;
    }
    
    @Override
    public PaginateResult selectPointSaleSumPage(Pagination page, PointSaleSum pointSaleSum, Map<String, Object> sumMap) throws BusinessException
    {
        if (StringUtils.isBlank(pointSaleSum.getBrandId()) || StringUtils.isBlank(pointSaleSum.getDealerId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        pointSaleSum.setStartTimeLong(CalendarUtils.getLongFromTime(pointSaleSum.getStartTime()));
        if (null != pointSaleSum.getEndTime())
        {
            pointSaleSum.setEndTimeLong(CalendarUtils.addDay(pointSaleSum.getEndTime(), 1));
        }
        List<Map<String, Object>> mapList = pointSaleSumMapper.selectPointSaleSumList(page, pointSaleSum);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(pointSaleSumMapper.countPointSaleSumListSum(pointSaleSum));
        return paginateResult;
    }
    
    @Override
    public PaginateResult countDealersPointFinancial(Pagination page, PointSaleSum pointSaleSum, Map<String, Object> sumMap) throws BusinessException
    {
        short userType = OnLineUserUtils.getPrincipal().getUserType();
        if (UserInfoConst.USER_TYPE_BRAND == userType && StringUtils.isBlank(pointSaleSum.getBrandId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (UserInfoConst.USER_TYPE_DEALER == userType && StringUtils.isBlank(pointSaleSum.getDealerId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        List<Map<String, Object>> mapList = pointSaleSumMapper.countDealersPointFinancial(page, pointSaleSum);
        PaginateResult paginateResult = new PaginateResult(page, mapList);
        sumMap.putAll(pointSaleSumMapper.countDealersPointFinancialSum(pointSaleSum));
        return paginateResult;
    }
    
    @Override
    public List<Map<String, Object>> selectNoPayPoint(List<String> brandIds, String dealerId) throws BusinessException
    {
        if (null == brandIds || brandIds.isEmpty() || StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return pointSaleSumMapper.selectNoPayPoint(brandIds, dealerId);
    }
    
    @Override
    public void executePayPoint(List<String> brandsId, String dealerId, String password, String userName) throws BusinessException
    {
        List<Map<String, Object>> mapList = this.selectNoPayPoint(brandsId, dealerId);
        if (null == mapList && mapList.isEmpty()) { throw new BusinessException(CommonConst.FAIL.getCode(), "无支付款项,无需支付！"); }
        PointSaleSum pointSaleSum = new PointSaleSum();
        TransferObj transferObj = new TransferObj();
        FinancialPay financialPay = new FinancialPay();
        List<TransferObj> transferObjList = Lists.newArrayList();
        List<FinancialPay> financialPayList = Lists.newArrayList();
        Map<String, PointSaleSum> pointSaleSumMap = Maps.newHashMap();
        List<TransferReturnObj> transferReturnObjList = null;
        for (int i = 0, length = mapList.size(); i < length; i++)
        {
            this.composeEntity(mapList.get(i), pointSaleSum, transferObj, financialPay, dealerId, userName);
            transferObjList.add(transferObj);
            financialPayList.add(financialPay);
            pointSaleSumMap.put(pointSaleSum.getPayExtId(), pointSaleSum);
        }
        if (!ListUtils.isSizeEqual(transferObjList,
                financialPayList)) { throw new BusinessException(CommonConst.FAIL.getCode(), "数据异常：financialPayList.size != transferObjList.size"); }
        try
        {
            /*
             * 普通批量转账
             * @param transferObjList 转账对象集合
             * @return TransferReturnObj 返回转账对象，成功时放回订单信息，失败时，返回错误信息
             * List<TransferReturnObj> transferWithFeeBatch(List<TransferObj> transferObjList);
             */
            transferReturnObjList = payApiService.executeTransferBatch(password, dealerId, transferObjList); // TODO 结算平台经销商给多品牌商转账
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), "支付财务帐失败！" + e.getLocalizedMessage());
        }
        financialPayService.insertBatch(financialPayList);
        if (ListUtils.isNotEmpty(transferReturnObjList)) // 结算平台返回数据 更新本地数据
        {
            financialPayService.updateFinancialPay(transferReturnObjList);
            // 付款失败的不修改pointSaleSum
            for (TransferReturnObj transferReturnObj : transferReturnObjList)
            {
                if (!transferReturnObj.isSuccess())
                {
                    pointSaleSumMap.remove(transferReturnObj.getExId());
                }
                else
                {
                    pointSaleSumMap.get(transferReturnObj.getExId()).setIsPaid(true);
                }
            }
        }
        if (!pointSaleSumMap.isEmpty())
        {
            for (Map.Entry<String, PointSaleSum> entry : pointSaleSumMap.entrySet())
            {
                pointSaleSumMapper.updatePointSaleSum(entry.getValue());
            }
        }
    }
    
    @Override
    public Map<String, Object> selectPayIng(String dealerId, String ... brandIds) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId) || ArrayUtils.isEmpty(brandIds)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return pointSaleSumMapper.selectPayIng(dealerId, brandIds);
    }
    
    // 组装参数
    private void composeEntity(Map<String, Object> map, PointSaleSum pointSaleSum, TransferObj transferObj, FinancialPay financialPay, String dealerId, String userName)
            throws BusinessException
    {
        String brandId = MapUtils.getString(map, "brandId");
        String brandName = MapUtils.getString(map, "brandName");
        BigDecimal debtPriceSum = new BigDecimal(map.get("debtPriceSum").toString());
        int count = MapUtils.getIntValue(map, "count");
        String startTime = CalendarUtils.getStringTime(MapUtils.getLong(map, "startTime"), "yyyy-MM-dd");
        String endTime = CalendarUtils.getStringTime(MapUtils.getLong(map, "endTime"), "yyyy-MM-dd");
        if (debtPriceSum.compareTo(
                BigDecimal.ZERO) == -1) { throw new BusinessException(CommonConst.FAIL.getCode(), "支付金额异常:品牌商" + brandName + "-ID " + brandId + "-欠付款" + debtPriceSum); }
        String extId = SerialNumberUtil.getSerialNumber18();
        Long sourceUser = userInfoService.executeFindPayUserId(dealerId);
        Long targetUser = userInfoService.executeFindPayUserId(brandId);
        if (null == sourceUser)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(),"经销商："+transferObj.getSourceUser()+" 未开通支付平台");
        }
        if (null == targetUser)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(),"品牌商："+transferObj.getToUserName()+" 未开通支付平台");
        }
        transferObj.setSourceUser(sourceUser);
        transferObj.setTargetUser(targetUser);
        transferObj.setFromUserName(userName);
        transferObj.setToUserName(brandName);
        transferObj.setTitle("支付(从" + startTime + "到" + endTime + " 共" + count + "笔)返点欠付款");
        transferObj.setAmount(debtPriceSum);
        transferObj.setFee(transferObj.getAmount().multiply(new BigDecimal(0.03)).setScale(2, BigDecimal.ROUND_HALF_UP)); // 收取佣金为0.03
        transferObj.setFeeRecevier(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        transferObj.setExId(Long.valueOf(extId));
        pointSaleSum.setDealerId(dealerId);
        pointSaleSum.setBrandId(brandId);
        pointSaleSum.setStartTimeLong(MapUtils.getLong(map, "startTime"));
        pointSaleSum.setEndTimeLong(MapUtils.getLong(map, "endTime"));
        pointSaleSum.setPayExtId(extId);
        financialPay.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        financialPay.setPayExtId(extId);
        financialPay.setDealerId(dealerId);
        financialPay.setBrandId(brandId);
        financialPay.setPayAmount(debtPriceSum);
        financialPay.setAmountType(CommonConstant.financial.TYPE_POINT);
        financialPay.setPayState(CommonConstant.financial.PAY_NO);
        financialPay.setBeginTime(MapUtils.getLong(map, "startTime"));
        financialPay.setEndTime(MapUtils.getLong(map, "endTime"));
        financialPay.setDelFlag(false);
    }
}
