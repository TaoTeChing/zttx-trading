/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.common.entity.ProductViewLog;
import com.zttx.web.module.common.mapper.ProductViewLogMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.ListUtils;

/**
 * 商品浏览历史记录 服务实现类
 * <p>File：ProductViewLog.java </p>
 * <p>Title: ProductViewLog </p>
 * <p>Description:ProductViewLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductViewLogServiceImpl extends GenericServiceApiImpl<ProductViewLog> implements ProductViewLogService
{
    private ProductViewLogMapper productViewLogMapper;
    
    @Autowired
    private DealerJoinMapper     dealerJoinMapper;
    
    @Autowired
    private DealerJoinService dealerJoinService;
    
    @Autowired
    private ProductPriceService  productPriceService;
    
    @Autowired(required = true)
    public ProductViewLogServiceImpl(ProductViewLogMapper productViewLogMapper)
    {
        super(productViewLogMapper);
        this.productViewLogMapper = productViewLogMapper;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> selectViewLogProductPage(Pagination pagination, ProductFilter filter) throws BusinessException
    {
        if (null != filter && null != filter.getDealerId())
        {
            filter.setPage(pagination);
            List<Map<String, Object>> mapList = productViewLogMapper.selectViewLogProductList(filter);
            for (Map<String, Object> map : mapList)
            {
                Map<String, Object> result = dealerJoinMapper.getValidMap(filter.getDealerId(), map.get("brandsId").toString(), MapUtils.getString(map, "productId"));
                if (result != null) // 加盟关系
                {
                    map.putAll(result);
                    map.put("isPoint", dealerJoinService.isSupportPoint(filter.getDealerId(), (String) map.get("productId")));// 是否支持返点：查询当前数据库 产品支持返点+返点加盟
                    Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPrice(map);
                    if (resultMap != null)
                    {
                        map.putAll(resultMap);
                    }
                }
                else
                { // 区域关系
                    map.put("dealerId", filter.getDealerId());
                    Map<String, Object> resultMap = productPriceService.toConfirmProTypeAndPriceByAreaNo(map);
                    map.putAll(resultMap);
                }
            }
            return new PaginateResult<>(pagination, mapList);
        }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> selectViewLogCata(String dealerId)
    {
        if (null != dealerId) { return productViewLogMapper.selectViewLogCata(dealerId); }
        return null;
    }
    
    @Override
    public int batchRemoveHistoryProduct(List<String> productViewLogsId)
    {
        if (ListUtils.isNotEmpty(productViewLogsId))
        {
            int num = 0;
            for (String refrenceId : productViewLogsId)
            {
                if (0 < productViewLogMapper.delete(refrenceId))
                {
                    num++;
                }
            }
            return num;
        }
        return 0;
    }
    
    @Override
    public ProductViewLog saveProductViewLog(ProductViewLog productViewLog)
    {
        if (null != productViewLog)
        {
            ProductViewLog oldProductViewLog = this.getProductViewLog(productViewLog.getProductId(), productViewLog.getUserId());
            if (null != oldProductViewLog)
            {
                oldProductViewLog.setViewNum(oldProductViewLog.getViewNum() + 1);
                oldProductViewLog.setCreateTime(CalendarUtils.getCurrentTime());// 由于没有updateTime
                productViewLogMapper.updateByPrimaryKeySelective(oldProductViewLog);
                return oldProductViewLog;
            }
            productViewLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            productViewLog.setCreateTime(CalendarUtils.getCurrentTime());
            productViewLog.setViewNum(1);
            productViewLogMapper.insert(productViewLog);
            return productViewLog;
        }
        return null;
    }
    
    private ProductViewLog getProductViewLog(String productId, String userId)
    {
        if (null != productId && null != userId) { return productViewLogMapper.selectProductViewLog(productId, userId); }
        return null;
    }
}
