/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.common.service.ProductPriceService;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.mapper.DealerFavoriteMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.model.ProductFilter;

/**
 * 经销商产品收藏 服务实现类
 * <p>File：DealerFavorite.java </p>
 * <p>Title: DealerFavorite </p>
 * <p>Description:DealerFavorite </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerFavoriteServiceImpl extends GenericServiceApiImpl<DealerFavorite> implements DealerFavoriteService
{
    private DealerFavoriteMapper     dealerFavoriteMapper;
    
    @Autowired
    private DealerJoinMapper         dealerJoinMapper;
    
    @Autowired
    private DealerJoinService        dealerJoinService;
    
    @Autowired
    private ProductPriceService      productPriceService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired(required = true)
    public DealerFavoriteServiceImpl(DealerFavoriteMapper dealerFavoriteMapper)
    {
        super(dealerFavoriteMapper);
        this.dealerFavoriteMapper = dealerFavoriteMapper;
    }
    
    @Override
    public Boolean isProductExists(DealerFavorite dealerFavorite)
    {
        Integer count = dealerFavoriteMapper.countDealerFavorite(dealerFavorite);
        return (count != null && count > 0);
    }
    
    @Override
    public PaginateResult<Map<String, Object>> selectProductFavoritePage(Pagination page, ProductFilter productFilter) throws BusinessException
    {
        if (null != productFilter && null != productFilter.getDealerId())
        {
            productFilter.setPage(page);
            List<Map<String, Object>> dealerFavoriteList = dealerFavoriteMapper.selectProductFavoriteList(productFilter);
            for (Map<String, Object> map : dealerFavoriteList)
            {
                Map<String, Object> result = dealerJoinMapper.getValidMap(productFilter.getDealerId(), MapUtils.getString(map, "brandsId"),
                        MapUtils.getString(map, "productId"));
                if (result != null) // 加盟 价格状态判断
                {
                    map.putAll(result);
                    map.put("isPoint", dealerJoinService.isSupportPoint(productFilter.getDealerId(), (String) map.get("productId")));// 是否支持返点：查询当前数据库 产品支持返点+返点加盟
                    Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPrice(map);
                    if (resultMap != null)
                    {
                        map.putAll(resultMap);
                    }
                }
                else
                { // 非加盟 区域授权 价格状态判断
                    Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPriceByAreaNo(map);
                    if (resultMap != null)
                    {
                        map.putAll(resultMap);
                    }
                }
            }
            return new PaginateResult<>(page, dealerFavoriteList);
        }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> selectFavoriteCata(String dealerId)
    {
        if (null != dealerId) { return dealerFavoriteMapper.selectFavoriteCata(dealerId); }
        return null;
    }
    
    @Override
    public void addDealerFavorite(DealerFavorite dealerFavorite) throws BusinessException
    {
        if (null != dealerFavorite && null != dealerFavorite.getDealerId() && null != dealerFavorite.getProductId())
        {
            if (!dealerFavoriteMapper.isExist(dealerFavorite.getDealerId(), dealerFavorite.getProductId()))
            {
                ProductBaseInfo productBaseInfo = productInfoDubboConsumer.getProductById(dealerFavorite.getProductId());
                if (productBaseInfo != null)
                {
                    dealerFavorite.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    dealerFavorite.setBrandId(productBaseInfo.getBrandId());
                    dealerFavorite.setBrandsId(productBaseInfo.getBrandsId());
                    dealerFavorite.setProductId(dealerFavorite.getProductId());
                    dealerFavorite.setCollectTime(CalendarUtils.getCurrentTime());
                    dealerFavorite.setTagId("");
                    dealerFavorite.setDelFlag(false);
                    dealerFavoriteMapper.insert(dealerFavorite);
                }
            }
        }
    }
    
    @Override
    public void removeDealerFavorite(DealerFavorite dealerFavorite)
    {
        if (null != dealerFavorite && null != dealerFavorite.getRefrenceId())
        {
            dealerFavoriteMapper.delete(dealerFavorite.getRefrenceId());
        }
    }
}
