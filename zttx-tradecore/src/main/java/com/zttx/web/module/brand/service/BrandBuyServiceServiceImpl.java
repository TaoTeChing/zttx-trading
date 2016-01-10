/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.entity.BrandBuyService;
import com.zttx.web.module.brand.mapper.BrandBuyServiceMapper;
import com.zttx.web.module.common.service.ComplainNumberService;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.service.WebServiceItemsService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商购买的服务 服务实现类
 * <p>File：BrandBuyService.java </p>
 * <p>Title: BrandBuyService </p>
 * <p>Description:BrandBuyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandBuyServiceServiceImpl extends GenericServiceApiImpl<BrandBuyService> implements BrandBuyServiceService
{
    @Autowired
    private BrandBuySerLogService  brandBuySerLogService;
    
    @Autowired
    private WebServiceItemsService webServiceItemsService;
    
    @Autowired
    private ComplainNumberService  complainNumberService;
    
    private BrandBuyServiceMapper  brandBuyServiceMapper;
    
    @Autowired(required = true)
    public BrandBuyServiceServiceImpl(BrandBuyServiceMapper brandBuyServiceMapper)
    {
        super(brandBuyServiceMapper);
        this.brandBuyServiceMapper = brandBuyServiceMapper;
    }
    
    /**
     * 根据品牌商编号和服务编号查询记录
     * @author 陈建平
     * @param brandId
     * @param serviceId
     * @return
     */
    @Override
    public BrandBuyService findByBrandIdAndServiceId(String brandId, String serviceId)
    {
        if (StringUtils.isBlank(brandId) || StringUtils.isBlank(serviceId)) { return null; }
        List<BrandBuyService> list = brandBuyServiceMapper.findByBrandIdAndServiceId(brandId, serviceId);
        if (null != list && list.size() > 0) { return list.get(0); }
        return null;
    }
    
    /**
     * 保存购买日志
     * @author 陈建平
     * @param brandBuySerLog
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandBuyService saveByClient(BrandBuySerLog brandBuySerLog) throws BusinessException
    {
        // 参数校验
        // ClientValidator.validateByClient(brandBuySerLog);
        BrandBuyService brandBuyService = new BrandBuyService();
        brandBuyService.setBrandId(brandBuySerLog.getBrandId());
        brandBuyService.setServiceId(brandBuySerLog.getServiceId());
        brandBuyService.setBrandName(brandBuySerLog.getBrandName());
        brandBuyService.setServicerCate(brandBuySerLog.getServicerCate());
        brandBuyService.setChargType(brandBuySerLog.getChargType());
        brandBuyService.setBuyTime(CalendarUtils.getCurrentLong());
        brandBuyService.setBeginTime(brandBuySerLog.getBeginTime());
        brandBuyService.setBuyMoney(brandBuySerLog.getBuyMoney());
        // 判断服务是否存在
        if (!webServiceItemsService.isExist(brandBuySerLog.getServiceId(), brandBuySerLog.getServicerCate(), brandBuySerLog.getChargType())) { throw new BusinessException(
                ClientConst.PARAMERROR.code, "网站服务不存在"); }
        BrandBuyService oldBrandBuyService = findByBrandIdAndServiceId(brandBuySerLog.getBrandId(), brandBuySerLog.getServiceId());
        try
        {
            if (null == oldBrandBuyService)// 新增 经销商购买的服务 记录
            {
                brandBuyServiceMapper.insert(brandBuyService);
            }
            else
            // 修改 经销商购买的服务 记录
            {
                this.handChargType(brandBuyService, oldBrandBuyService);
                brandBuyService.setBuyMoney(brandBuyService.getBuyMoney().add(oldBrandBuyService.getBuyMoney()));
                brandBuyService.setRefrenceId(oldBrandBuyService.getRefrenceId());
                updateByPrimaryKey(brandBuyService);
            }
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException(ClientConst.PARAMERROR.code, e.getMessage());
        }
        // 新增经销商购买的服务记录
        brandBuySerLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandBuySerLog.setSerialNumber(com.zttx.web.utils.SerialnoUtils.buildServiceSN(complainNumberService.getComplainId()));
        brandBuySerLog.setBuyState(DealerConstant.DealerBuySerLog.BUY_STATE_SUCCESS);
        brandBuySerLog.setBuyTime(CalendarUtils.getCurrentLong());
        brandBuySerLog.setEndTime(brandBuyService.getEndTime());
        brandBuySerLogService.insert(brandBuySerLog);
        return brandBuyService;
    }
    
    /**
     * 处理不同的支付类型
     * @param brandBuyService
     * @param oldBrandBuyService
     */
    private void handChargType(BrandBuyService brandBuyService, BrandBuyService oldBrandBuyService)
    {
        short chargType = brandBuyService.getChargType();
        if (DealerConstant.DealerBuySerLog.CHARGE_TYPE_RENEW == chargType)// 续期
        {
            WebServiceItems webServiceItems = webServiceItemsService.selectByPrimaryKey(brandBuyService.getServiceId());
            if (null == webServiceItems) { throw new IllegalArgumentException("网站服务不存在"); }
            BigDecimal buyNum = brandBuyService.getBuyMoney().divide(webServiceItems.getPrice(),2);
            if (buyNum.intValue() < 1) { throw new IllegalArgumentException("购买数量不能小于1"); }
            Long beginTime = brandBuyService.getBeginTime();
            if (null == beginTime) { throw new IllegalArgumentException("服务开始时间不能为空"); }
            Long endTime = new DateTime(beginTime).plusYears(buyNum.intValue()).toDate().getTime();
            if (null != oldBrandBuyService)
            {
                Long oldEndTime = oldBrandBuyService.getEndTime();
                if (null != oldEndTime && CalendarUtils.getCurrentLong() < oldEndTime)// 服务未过期
                {
                    endTime = new DateTime(oldEndTime).plusYears(buyNum.intValue()).toDate().getTime();
                    beginTime = oldBrandBuyService.getBeginTime();
                }
            }
            brandBuyService.setEndTime(endTime);
            brandBuyService.setBeginTime(beginTime);
        }
        else if (DealerConstant.DealerBuySerLog.CHARGE_TYPE_RAISE == chargType)// 增加数量
        {
            brandBuyService.setBeginTime(null);
            brandBuyService.setEndTime(null);
        }
    }
}
