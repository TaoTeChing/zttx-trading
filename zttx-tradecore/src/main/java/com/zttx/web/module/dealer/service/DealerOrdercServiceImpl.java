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

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.service.ProductPriceService;
import com.zttx.web.module.dealer.entity.DealerOrderc;
import com.zttx.web.module.dealer.mapper.DealerOrdercMapper;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商产品进货计数 服务实现类
 * <p>File：DealerOrderc.java </p>
 * <p>Title: DealerOrderc </p>
 * <p>Description:DealerOrderc </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerOrdercServiceImpl extends GenericServiceApiImpl<DealerOrderc> implements DealerOrdercService
{
    private DealerOrdercMapper  dealerOrdercMapper;
    
    @Autowired
    private DealerJoinService   dealerJoinService;
    
    @Autowired
    private ProductPriceService productPriceService;
    
    @Autowired(required = true)
    public DealerOrdercServiceImpl(DealerOrdercMapper dealerOrdercMapper)
    {
        super(dealerOrdercMapper);
        this.dealerOrdercMapper = dealerOrdercMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> selectOrdercProductPage(Pagination pagination, ProductFilter filter) throws BusinessException
    {
        if (null != filter && null != filter.getDealerId())
        {
            filter.setPage(pagination);
            List<Map<String, Object>> mapList = dealerOrdercMapper.selectOrdercProductList(filter);
            for (Map<String, Object> map : mapList)
            {
                map.put("isPoint", dealerJoinService.isSupportPoint(filter.getDealerId(), (String) map.get("productId")));// 是否支持返点：查询当前数据库 产品支持返点+返点加盟
                if (null == MapUtils.getShort(map, "joinState"))
                {
                    map.putAll(productPriceService.toConfirmProTypeAndPriceByAreaNo(map));
                }
                else
                {
                    map.putAll(productPriceService.toConfirmProTypeAndPrice(map));
                }
            }
            return new PaginateResult<>(pagination, mapList);
        }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> selectOrdercCata(String dealerId)
    {
        if (null != dealerId) { return dealerOrdercMapper.selectOrdercCata(dealerId); }
        return null;
    }
    
    @Override
    public int batchRemoveOrdercProduct(List<String> dealerOrdercsId)
    {
        if (ListUtils.isNotEmpty(dealerOrdercsId))
        {
            int num = 0;
            for (String refrenceId : dealerOrdercsId)
            {
                if (0 < dealerOrdercMapper.delete(refrenceId))
                {
                    num++;
                }
            }
            return num;
        }
        return 0;
    }
    
    /**
     * 统计进货款数量
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    @Override
    public Long countProCate(String dealerId, String brandId, String brandsId)
    {
        List<String> productIdList = dealerOrdercMapper.countProCate(dealerId, brandId, brandsId);
        if (productIdList != null)
        {
            return (long) (productIdList.size());
        }
        else
        {
            return 0l;
        }
    }
    
    /**
     * 统计经常进货款数量
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    @Override
    public Long countFreProCate(String dealerId, String brandId, String brandsId)
    {
        List<String> productIdList = dealerOrdercMapper.countFreProCate(dealerId, brandId, brandsId);
        if (productIdList != null)
        {
            return (long) (productIdList.size());
        }
        else
        {
            return 0l;
        }
    }
    
    /**
     * 统计进货款产品id
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    @Override
    public List<String> getProCateList(String dealerId, String brandId, String brandsId)
    {
        return this.dealerOrdercMapper.countProCate(dealerId, brandId, brandsId);
    }
    
    /**
     * 统计经常进货款产品id
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    @Override
    public List<String> getFreProCateList(String dealerId, String brandId, String brandsId)
    {
        return this.dealerOrdercMapper.countFreProCate(dealerId, brandId, brandsId);
    }
    
    /**
     * 授权产品库
     * @param dealerId
     * @param pagination
     * @param filter
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> findOrdercProductsByDealerId(String dealerId, Pagination pagination, ProductFilter filter)
    {
        PaginateResult<Map<String, Object>> pageResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> result = dealerOrdercMapper.searchToMap(dealerId, pagination, filter);
        pageResult.setList(result);
        pageResult.setPage(pagination);
        // TODO 和产品有关系，又没看懂什么意思，暂时注释
        // DealerInfo dealerInfo = dealerInfoService.getDealerInfo(dealerId);
        // for (Map<String, Object> map : pageResult.getList()) {
        // DealerProductState dealerProductState = productInfoService.getProductState(map.get("id").toString(), dealerId, null);
        // Map<String, Object> productMap = productBaseInfoService.getProductSalePrice(map.get("id").toString(), dealerInfo.getAreaNo(), dealerId, null);
        // map.put("illegal", productMap.get("message") == null);
        // map.put("productState", dealerProductState);
        // if (productMap != null) {
        // BigDecimal bigDecimal = productMap.get("price") == null ? BigDecimal.ZERO : new BigDecimal(productMap.get("price").toString());
        // bigDecimal.setScale(2, BigDecimal.ROUND_UP);
        // map.put("price", bigDecimal);
        // }
        // }
        return pageResult;
    }
}
