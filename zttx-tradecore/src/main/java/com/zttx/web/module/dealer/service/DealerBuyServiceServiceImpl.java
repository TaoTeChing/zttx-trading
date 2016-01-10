/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.DealerBuyService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.mapper.DealerBuyServiceMapper;
import com.zttx.web.utils.AppParameterUtils;

/**
 * 经销商购买的服务 服务实现类
 * <p>File：DealerBuyService.java </p>
 * <p>Title: DealerBuyService </p>
 * <p>Description:DealerBuyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerBuyServiceServiceImpl extends GenericServiceApiImpl<DealerBuyService> implements DealerBuyServiceService
{
    private DealerBuyServiceMapper dealerBuyServiceMapper;
    
    @Autowired
    private WebServiceItemsService webServiceItemsService;
    
    @Autowired
    private DealerBuySerLogService dealerBuySerLogService;
    
    @Autowired
    private DealerInfoService      dealerInfoService;
    
    @Autowired
    private UserInfoService        userInfoService;
    
    @Autowired(required = true)
    public DealerBuyServiceServiceImpl(DealerBuyServiceMapper dealerBuyServiceMapper)
    {
        super(dealerBuyServiceMapper);
        this.dealerBuyServiceMapper = dealerBuyServiceMapper;
    }
    
    /**
     * 列表（分页）
     *
     * @param page
     * @param searchBean ：过滤条件
     *                   buyTime			购买时间（搜索所有指定时间当天购买的数据）
     *                   startSearchTime	开始搜索时间（搜索所有指定时间后购买的数据）
     *                   endSearchTime	结束搜索时间（搜索所有指定时间前购买的数据）
     *                   refrenceId		资料编号
     *                   dealerName		经销商名称（模糊）
     *                   serviceId		服务编号
     *                   servicerCate	服务类别
     * @return
     * @author 周光暖
     */
    @Override
    public PaginateResult<DealerBuyService> searchByClient(Pagination page, DealerBuyService searchBean)
    {
        searchBean.setPage(page);
        return new PaginateResult<>(searchBean.getPage(), dealerBuyServiceMapper.searchByClient(searchBean));
    }
    
    /**
     * 新增/修改（第三方调用，会同时维护经销商购买的服务表）
     *
     * @param dealerBuySerLog refrenceId			经销商购买的服务表的主键（由第三方调用者提供）
     *                        dealerBuyServiceId	经销商购买的服务的主键（由第三方调用者提供，若记录不存在：新增、记录已存在：修改）
     *                        dealerId			经销商编号（必填）
     *                        dealerName			经销商名称
     *                        serviceId			服务编号（必填）
     *                        servicerCate		服务类别（读字典：字典编码 servicerCate）（必填）
     *                        buyNum 				购买数量（必填）
     *                        buyMoney			购买的金额（必填）
     *                        chargType			购买类型（1：续期、2：增加数量）（必填）
     *                        beginTime			服务开始时间
     *                        endTime				服务结束时间
     * @author 周光暖
     */
    @Override
    public DealerBuyService saveByClient(DealerBuySerLog dealerBuySerLog) throws BusinessException
    {
        DealerBuyService dealerBuyService = builtDealerBuyService(dealerBuySerLog);
        // 判断服务是否存在
        if (!webServiceItemsService.isExist(dealerBuySerLog.getServiceId(), dealerBuySerLog.getServicerCate(), dealerBuySerLog.getChargType())) { throw new BusinessException(
                ClientConst.PARAMERROR.code, "网站服务不存在"); }
        DealerBuyService dbDealerBuyService = this.findBy(dealerBuySerLog.getDealerId(), dealerBuySerLog.getServiceId());
        if (null == dbDealerBuyService)
        {
            dealerBuyService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerBuyService.setCreateTime(CalendarUtils.getCurrentLong());
            this.handChargType(dealerBuyService, null);
            this.insertSelective(dealerBuyService);
        }
        else
        {
            dealerBuyService.setRefrenceId(dbDealerBuyService.getRefrenceId());
            this.handChargType(dealerBuyService, dbDealerBuyService);
            dealerBuyService.setBuyMoney(dealerBuyService.getBuyMoney().add(dbDealerBuyService.getBuyMoney()));
            this.updateByPrimaryKeySelective(dealerBuyService);
        }
        dealerBuySerLog.setBuyTime(dealerBuyService.getBuyTime());
        dealerBuySerLog.setEndTime(dealerBuyService.getEndTime());
        dealerBuySerLogService.createDealerBuySerLog(dealerBuySerLog);
        return dealerBuyService;
    }
    
    /**
     * 处理不同的支付类型
     * @param dealerBuyService
     * @param oldDealerBuyService
     * @author 周光暖
     */
    private void handChargType(DealerBuyService dealerBuyService, DealerBuyService oldDealerBuyService)
    {
        short chargType = dealerBuyService.getChargType();
        if (DealerConstant.DealerBuySerLog.CHARGE_TYPE_RENEW == chargType)// 续期
        {
            WebServiceItems webServiceItems = webServiceItemsService.selectByPrimaryKey(dealerBuyService.getServiceId());
            if (null == webServiceItems) { throw new IllegalArgumentException("网站服务不存在"); }
            Long beginTime = dealerBuyService.getBeginTime();
            Long endTime = 0L;
            Double buyNum = 1D;
            if (webServiceItems.getRefrenceId().equals("S005"))
            {
                buyNum = 30D * 2D;
                endTime = new DateTime(beginTime).plusDays(buyNum.intValue()).toDate().getTime();
            }
            else
            {
                endTime = new DateTime(beginTime).plusYears(buyNum.intValue()).toDate().getTime();
            }
            if (webServiceItems.getPrice() != BigDecimal.ZERO)
            {
                buyNum = dealerBuyService.getBuyMoney().divide(webServiceItems.getPrice(),2).doubleValue();
                if (buyNum.intValue() < 1) { throw new IllegalArgumentException("购买数量不能小于1"); }
                endTime = new DateTime(beginTime).plusYears(buyNum.intValue()).toDate().getTime();
            }
            if (null == beginTime) { throw new IllegalArgumentException("服务开始时间不能为空"); }
            if (null != oldDealerBuyService)
            {
                Long oldEndTime = null == oldDealerBuyService.getEndTime() ? 0l : oldDealerBuyService.getEndTime();
                if (com.zttx.web.utils.CalendarUtils.getCurrentLong() < oldEndTime)// 服务未过期
                {
                    endTime = new DateTime(oldEndTime).plusYears(buyNum.intValue()).toDate().getTime();
                    beginTime = oldDealerBuyService.getBeginTime();
                }
                else
                {
                    endTime = new DateTime(com.zttx.web.utils.CalendarUtils.getCurrentLong()).plusYears(buyNum.intValue()).toDate().getTime();
                    beginTime = oldDealerBuyService.getBeginTime();
                }
            }
            dealerBuyService.setEndTime(endTime);
            dealerBuyService.setBeginTime(beginTime);
        }
        else if (DealerConstant.DealerBuySerLog.CHARGE_TYPE_RAISE == chargType)// 增加数量
        {
            dealerBuyService.setBeginTime(null);
            dealerBuyService.setEndTime(null);
        }
    }
    
    /**
     * 根据经销商编号和服务编号查询记录
     *
     * @param dealerId
     * @param serviceId
     * @return
     */
    @Override
    public DealerBuyService findBy(String dealerId, String serviceId)
    {
        return this.dealerBuyServiceMapper.findBy(dealerId, serviceId);
    }
    
    /**
     * @param dealerId
     * @throws BusinessException
     */
    @Override
    public void addTrialErpDealerBuyService(String dealerId) throws BusinessException
    {
        Map<String, Object> params = Maps.newHashMap();
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        params.put("dealerId", dealerId);
        params.put("dealerName", dealerInfo.getDealerName());
        params.put("dealerMobile", dealerUserm.getUserMobile());
        params.put("dealerUser", dealerInfo.getDealerUser());
        params.put("dealerMail", StringUtils.isEmpty(dealerUserm.getUserMail()) ? "user1@8637.com" : dealerUserm.getUserMail());
        params.put("dealerSex", dealerInfo.getDealerGender() == null ? 1 : (dealerInfo.getDealerGender() == 2 ? 1 : 0));
        params.put("dealerKey", dealerId);
        params.put("outOfTime", com.zttx.web.utils.CalendarUtils.addDay(com.zttx.web.utils.CalendarUtils.getCurrentLong(), 60));
        params.put("userName", dealerUserm.getUserMobile());
        JSONObject _resJson = AppParameterUtils.getErpTrailJSONObject(params);
        if (_resJson == null || !_resJson.containsKey("code") || _resJson.getIntValue("code") != 121000) { throw new BusinessException("ERP 接口调用失败"); }
        DealerBuyService _dealerBuyService = this.findBy(dealerId, CommonConstant.WebServiceItems.SERVICE_SYSTEM_STIALERP);
        if (_dealerBuyService != null) { throw new BusinessException("ERP试用服务已经开通,请刷页面!"); }
        _dealerBuyService = new DealerBuyService();
        _dealerBuyService.setBeginTime(CalendarUtils.getCurrentLong());
        _dealerBuyService.setEndTime(CalendarUtils.addDay(com.zttx.web.utils.CalendarUtils.getCurrentLong(), 60));
        _dealerBuyService.setBuyTime(CalendarUtils.getCurrentLong());
        _dealerBuyService.setServiceId(CommonConstant.WebServiceItems.SERVICE_SYSTEM_STIALERP);
        _dealerBuyService.setServicerCate(Short.valueOf("1"));
        _dealerBuyService.setChargType(Short.valueOf("1"));
        _dealerBuyService.setBuyMoney(BigDecimal.ZERO);
        _dealerBuyService.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        _dealerBuyService.setDealerId(dealerId);
        this.insertSelective(_dealerBuyService);
        // DealerInfo的服务起起时间
        dealerInfo.setBeginTime(CalendarUtils.getCurrentLong());
        dealerInfo.setEndTime(CalendarUtils.addDay(com.zttx.web.utils.CalendarUtils.getCurrentLong(), 60));
        dealerInfoService.updateByPrimaryKeySelective(dealerInfo);
    }
    
    private DealerBuyService builtDealerBuyService(DealerBuySerLog dealerBuySerLog)
    {
        DealerBuyService dealerBuyService = new DealerBuyService();
        dealerBuyService.setDealerId(dealerBuySerLog.getDealerId());
        dealerBuyService.setDealerName(dealerBuySerLog.getDealerName());
        dealerBuyService.setServiceId(dealerBuySerLog.getServiceId());
        dealerBuyService.setServicerCate(dealerBuySerLog.getServicerCate());
        dealerBuyService.setChargType(dealerBuySerLog.getChargType());
        dealerBuyService.setBuyTime(CalendarUtils.getCurrentLong());
        dealerBuyService.setBeginTime(dealerBuySerLog.getBeginTime());
        dealerBuyService.setBuyMoney(dealerBuySerLog.getBuyMoney());
        return dealerBuyService;
    }
}
