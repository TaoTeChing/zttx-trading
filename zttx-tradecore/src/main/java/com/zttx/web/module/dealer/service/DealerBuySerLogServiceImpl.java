/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.service.ComplainNumberService;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.mapper.DealerBuySerLogMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商购买的服务记录 服务实现类
 * <p>File：DealerBuySerLog.java </p>
 * <p>Title: DealerBuySerLog </p>
 * <p>Description:DealerBuySerLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerBuySerLogServiceImpl extends GenericServiceApiImpl<DealerBuySerLog> implements DealerBuySerLogService
{
    private DealerBuySerLogMapper  dealerBuySerLogMapper;
    
    @Autowired
    private DealerInfoService      dealerInfoService;
    
    @Autowired
    private ComplainNumberService  complainNumberService;
    
    @Autowired
    private WebServiceItemsService webServiceItemsService;
    
    @Autowired(required = true)
    public DealerBuySerLogServiceImpl(DealerBuySerLogMapper dealerBuySerLogMapper)
    {
        super(dealerBuySerLogMapper);
        this.dealerBuySerLogMapper = dealerBuySerLogMapper;
    }
    
    /**
     * 分页查询
     *
     * @param page       分页条件
     * @param searchBean ：过滤条件
     *                   buyTime			购买时间（搜索所有指定时间当天购买的数据）
     *                   startSearchTime	开始搜索时间（搜索所有指定时间后购买的数据）
     *                   endSearchTime	结束搜索时间（搜索所有指定时间前购买的数据）
     *                   refrenceId		资料编号
     *                   dealerId		经销商编号
     *                   serviceId		服务编号
     *                   servicerCate	服务类别
     *                   buyState		支付状态（DealerConstant.DealerBuySerLog.BUY_STATE_CREATE）
     *                   chargType		购买类型（1：续期、2：增加数量）
     * @return
     */
    @Override
    public PaginateResult<DealerBuySerLog> searchByClient(Pagination page, DealerBuySerLog searchBean)
    {
        searchBean.setPage(page);
        return new PaginateResult<>(searchBean.getPage(), dealerBuySerLogMapper.searchByClient(searchBean));
    }
    
    /**
     * 新增经销商购买的服务记录
     *
     * @param dealerBuySerLog 服务记录
     */
    @Override
    public void createDealerBuySerLog(DealerBuySerLog dealerBuySerLog) throws BusinessException
    {
        dealerBuySerLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerBuySerLog.setSerialNumber(com.zttx.web.utils.SerialnoUtils.buildServiceSN(complainNumberService.getComplainId()));
        dealerBuySerLog.setBuyState(DealerConstant.DealerBuySerLog.BUY_STATE_SUCCESS);
        dealerBuySerLog.setCreateTime(CalendarUtils.getCurrentLong());
        // 如果 是平台服务费
        if (dealerBuySerLog.getServiceId().equals(CommonConstant.WebServiceItems.SERVICE_SYSTEM_PLATFORM))
        {
            DealerInfo dealerInfo = dealerInfoService.getDealerInfo(dealerBuySerLog.getDealerId());
            if (null == dealerInfo) { throw new BusinessException(CommonConst.DEALER_INFO_NULL); }
            dealerInfo.setBeginTime(dealerBuySerLog.getBeginTime());
            dealerInfo.setEndTime(dealerBuySerLog.getEndTime());
            dealerInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerInfoService.updateByPrimaryKeySelective(dealerInfo);
        }
        // 服务日志BUG
        dealerBuySerLogMapper.insertSelective(dealerBuySerLog);
    }
    
    @Override
    public void addDealerBuySerLog(DealerBuySerLog dealerBuySerLog)
    {
        WebServiceItems _webServiceItems = webServiceItemsService.selectByPrimaryKey(dealerBuySerLog.getServiceId());
        dealerBuySerLog.setBuyMoney(_webServiceItems.getPrice().multiply(BigDecimal.valueOf(dealerBuySerLog.getBuyNum())));
        dealerBuySerLog.setBuyState(DealerConstant.DealerBuySerLog.BUY_STATE_CREATE);
        dealerBuySerLog.setChargType(_webServiceItems.getChargType());
        dealerBuySerLog.setBuyTime(CalendarUtils.getCurrentLong());
        dealerBuySerLog.setSerialNumber(com.zttx.web.utils.SerialnoUtils.buildServiceSN(complainNumberService.getComplainId()));
        dealerBuySerLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerBuySerLog.setServicerCate(_webServiceItems.getServicerCate());
        DealerInfo dealerInfo = dealerInfoService.getDealerInfo(dealerBuySerLog.getDealerId());
        dealerBuySerLog.setDealerName(dealerInfo.getDealerName());
        dealerBuySerLog.setCreateTime(CalendarUtils.getCurrentLong());
        dealerBuySerLogMapper.insertSelective(dealerBuySerLog);
    }
    
    @Override
    public List<DealerBuySerLog> listWithException(String userId, String[] idArr) throws BusinessException
    {
        List<DealerBuySerLog> list = Lists.newArrayList();
        for (int i = 0; i < idArr.length; i++)
        {
            DealerBuySerLog obj = dealerBuySerLogMapper.findById(userId, idArr[i]);
            if (null == obj) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            list.add(obj);
        }
        return list;
    }
}
