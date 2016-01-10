/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.brand.service.BrandFavoriteService;
import com.zttx.web.module.dealer.entity.DealerBuyService;
import com.zttx.web.module.dealer.entity.DealerShopEnv;
import com.zttx.web.module.dealer.mapper.DealerBuyServiceMapper;
import com.zttx.web.module.dealer.mapper.DealerImageMapper;
import com.zttx.web.module.dealer.mapper.DealerShopEnvMapper;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 终端商店铺信息 服务实现类
 * <p>File：DealerShopEnv.java </p>
 * <p>Title: DealerShopEnv </p>
 * <p>Description:DealerShopEnv </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShopEnvServiceImpl extends GenericServiceApiImpl<DealerShopEnv> implements DealerShopEnvService
{
    @Autowired
    private DealerBuyServiceMapper dealerBuyServiceMapper;
    
    @Autowired
    private DealerImageMapper      dealerImageMapper;
    
    @Autowired
    private BrandFavoriteService   brandFavoriteService;
    
    private DealerShopEnvMapper dealerShopEnvMapper;
    
    @Autowired(required = true)
    public DealerShopEnvServiceImpl(DealerShopEnvMapper dealerShopEnvMapper)
    {
        super(dealerShopEnvMapper);
        this.dealerShopEnvMapper = dealerShopEnvMapper;
    }
    
    /**
     * 保存店铺周边
     *
     * @param dealerShopEnv
     */
    @Override
    public void save(DealerShopEnv dealerShopEnv)
    {
        if (StringUtils.isNotBlank(dealerShopEnv.getRefrenceId()))
        {
            dealerShopEnv.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(dealerShopEnv);
        }
        else
        {
            dealerShopEnv.setCreateTime(CalendarUtils.getCurrentLong());
            dealerShopEnv.setUpdateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(dealerShopEnv);
        }
    }
    
    /**
     * 根据dealerId 获取终端商店铺信息
     * @author 陈建平
     * @param dealerId
     * @return
     */
    @Override
    public DealerShopEnv getDealerShopEnv(String dealerId)
    {
        if (StringUtils.isBlank(dealerId)) { return null; }
        DealerShopEnv filter = new DealerShopEnv();
        filter.setDealerId(dealerId);
        List<DealerShopEnv> list = dealerShopEnvMapper.findList(filter);
        if (null != list && list.size() > 0) { return list.get(0); }
        return null;
    }
    
    @Override
    public void updateDealerShopEnvShowed(String refrenceId, boolean isShow) throws BusinessException
    {
        DealerShopEnv _dealerShopEnv = dealerShopEnvMapper.selectByPrimaryKey(refrenceId);
        if (_dealerShopEnv == null) { throw new BusinessException("店铺不存在!"); }
        _dealerShopEnv.setShowed(isShow);
        dealerShopEnvMapper.updateByPrimaryKey(_dealerShopEnv);
    }
    
    @Override
    public PaginateResult<DealerShopEnv> getDealerShopEnvsBy(DealerShopEnv dealerShopEnv, Pagination pagination)
    {
        dealerShopEnv.setPage(pagination);
        List<DealerShopEnv> resultList = dealerShopEnvMapper.selectDealerShopEnvsBy(dealerShopEnv);
        for (DealerShopEnv _dealerShopEnv : resultList)
        {
            // 查询相应的服务
            DealerBuyService dbs = dealerBuyServiceMapper.findBy(_dealerShopEnv.getDealerId(), CommonConstant.WebServiceItems.SERVICE_SYSTEM_PLATFORM);
            if (dbs != null)
            {
                _dealerShopEnv.setPayedUser(dbs.getEndTime() != null && dbs.getEndTime() > com.zttx.web.utils.CalendarUtils.getCurrentLong());
            }
            _dealerShopEnv.setDealerImages(dealerImageMapper.selectDealerImagesByDealerId(_dealerShopEnv.getDealerId()));
            if (null != OnLineUserUtils.getPrincipal())
            {
                _dealerShopEnv.setUserType(OnLineUserUtils.getPrincipal().getUserType());
                if (OnLineUserUtils.getPrincipal().getUserType().shortValue() == UserInfoConst.USER_TYPE_BRAND.shortValue())
                {
                    _dealerShopEnv.setCollectedState(brandFavoriteService.isCollected(OnLineUserUtils.getPrincipal().getRefrenceId(), _dealerShopEnv.getDealerId()));
                }
            }
        }
        return new PaginateResult<>(pagination, resultList);
    }
    
    @Override
    public List<DealerShopEnv> getTopNewestDealerShopEnvs(int topn)
    {
        List<DealerShopEnv> list = dealerShopEnvMapper.selectTopNewestDealerShopEnvs(topn);
        for (DealerShopEnv _dealerShopEnv : list)
        {
            // 查询相应的服务
            DealerBuyService dbs = dealerBuyServiceMapper.findBy(_dealerShopEnv.getDealerId(), CommonConstant.WebServiceItems.SERVICE_SYSTEM_PLATFORM);
            if (dbs != null)
            {
                _dealerShopEnv.setPayedUser(dbs.getEndTime() != null && dbs.getEndTime() > com.zttx.web.utils.CalendarUtils.getCurrentLong());
            }
            _dealerShopEnv.setDealerImages(dealerImageMapper.selectDealerImagesByDealerId(_dealerShopEnv.getDealerId()));
        }
        return list;
    }
    
    @Override
    public PaginateResult<DealerShopEnv> getExcludeDealerShopEnvsBy(int currAreaNo, Pagination pagination)
    {
        List<DealerShopEnv> result = dealerShopEnvMapper.selectExcludeDealerShopEnvsBy(currAreaNo, pagination);
        for (DealerShopEnv _dealerShopEnv : result)
        {
            // 查询相应的服务
            DealerBuyService dbs = dealerBuyServiceMapper.findBy(_dealerShopEnv.getDealerId(), CommonConstant.WebServiceItems.SERVICE_SYSTEM_PLATFORM);
            if (dbs != null)
            {
                _dealerShopEnv.setPayedUser(dbs.getEndTime() != null && dbs.getEndTime() > com.zttx.web.utils.CalendarUtils.getCurrentLong());
            }
            _dealerShopEnv.setDealerImages(dealerImageMapper.selectDealerImagesByDealerId(_dealerShopEnv.getDealerId()));
        }
        return new PaginateResult<>(pagination, result);
    }
    
    @Override
    public boolean modDealerShopEnvViewCount(String refrenceId, int incCount) throws Exception
    {
        DealerShopEnv _dealerShopEnv = dealerShopEnvMapper.selectByPrimaryKey(refrenceId);
        if (_dealerShopEnv != null)
        {
            _dealerShopEnv.setViewCount((_dealerShopEnv.getViewCount() == null ? 0 : _dealerShopEnv.getViewCount()) + incCount);
            dealerShopEnvMapper.updateByPrimaryKey(_dealerShopEnv);
        }
        return true;
    }
}
