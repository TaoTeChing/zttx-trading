/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.entity.DealerShopers;
import com.zttx.web.module.dealer.mapper.DealerShoperMapper;
import com.zttx.web.module.dealer.mapper.DealerShopersMapper;
import com.zttx.web.utils.ListUtils;

/**
 * 购物详细信息表 服务实现类
 * <p>File：DealerShopers.java </p>
 * <p>Title: DealerShopers </p>
 * <p>Description:DealerShopers </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShopersServiceImpl extends GenericServiceApiImpl<DealerShopers> implements DealerShopersService
{
    private DealerShopersMapper dealerShopersMapper;
    
    @Autowired
    private DealerShoperMapper  dealerShoperMapper;
    
    @Autowired(required = true)
    public DealerShopersServiceImpl(DealerShopersMapper dealerShopersMapper)
    {
        super(dealerShopersMapper);
        this.dealerShopersMapper = dealerShopersMapper;
    }
    
    @Override
    public List<DealerShopers> selectDealerShopersBy(String shoperId, short productType)
    {
        if (null != shoperId) { return dealerShopersMapper.selectDealerShopersBy(shoperId, productType); }
        return null;
    }
    
    @Override
    public int batchUpdateShopers(List<JSONObject> jsonObjectList) throws BusinessException
    {
        if (ListUtils.isNotEmpty(jsonObjectList))
        {
            Map<String, Object> map = new HashMap<>();
            for (JSONObject jsonObject : jsonObjectList)
            {
                Integer purchaseNum = (Integer) jsonObject.get("purchaseNum");
                String shopId = jsonObject.get("shopId").toString();
                String productSkuId = jsonObject.get("productSkuId").toString();
                BigDecimal productSkuPrice = new BigDecimal((jsonObject.get("productSkuPrice").toString()));
                if (StringUtils.isBlank(shopId)) { throw new BusinessException("参数异常:shopId不存在!"); }
                if (StringUtils.isBlank(productSkuId)) { throw new BusinessException("参数异常:productSkuId不存在"); }
                DealerShoper oldDealerShoper = dealerShoperMapper.selectByPrimaryKey(shopId);
                if (null == oldDealerShoper) { throw new BusinessException("查询异常:购物车" + shopId + "不存在！"); }
                DealerShopers oldDealerShopers = dealerShopersMapper.getByproductSkuIdAndDealerShopId(productSkuId, shopId);
                if (null == oldDealerShopers)
                {
                    if (purchaseNum > 0)
                    {
                        DealerShopers dealerShopers = new DealerShopers();
                        dealerShopers.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        dealerShopers.setShoperId(shopId);
                        dealerShopers.setProductId(oldDealerShoper.getProductId());
                        dealerShopers.setProductSkuId(productSkuId);
                        dealerShopers.setPurchaseNum(purchaseNum);
                        dealerShopers.setProductType(oldDealerShoper.getProductType());
                        dealerShopers.setProductSkuPrice(productSkuPrice);
                        dealerShopers.setCreateTime(CalendarUtils.getCurrentTime());
                        dealerShopers.setUpdateTime(dealerShopers.getCreateTime());
                        dealerShopers.setDelFlag(false);
                        dealerShopersMapper.insert(dealerShopers);
                    }
                }
                else
                {
                    if (purchaseNum == 0)
                    {
                        dealerShopersMapper.deleteByPrimaryKey(oldDealerShopers.getRefrenceId());
                    }
                    else
                    {
                        oldDealerShopers.setPurchaseNum(purchaseNum);
                        dealerShopersMapper.updateByPrimaryKeySelective(oldDealerShopers);
                    }
                }
                // 产品折扣值计算
                if (null == MapUtils.getString(map, shopId))
                {
                    String discount = jsonObject.get("discount").toString();
                    if (oldDealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_SAM)
                    {
                        discount = "1"; // 拿样订单不计算折扣
                    }
                    map.put(shopId, discount);
                }
            }
            // 购物车累计和
            if (!map.isEmpty())
            {
                for (Map.Entry<String, Object> entry : map.entrySet())
                {
                    Map<String, Object> shopersSumMap = dealerShopersMapper.selectDealerShopersSumBy(entry.getKey());
                    if (null == shopersSumMap)
                    {
                        shopersSumMap = Maps.newHashMap();
                        shopersSumMap.put("purchaseNumSum", 0);
                    }
                    if (shopersSumMap.get("purchaseNumSum") instanceof BigDecimal)
                    {
                        DealerShoper dealerShoper = new DealerShoper();
                        dealerShoper.setRefrenceId(entry.getKey());
                        dealerShoper.setProductNum(((BigDecimal) shopersSumMap.get("purchaseNumSum")).intValue());
                        dealerShoperMapper.updateByPrimaryKeySelective(dealerShoper);
                    }
                }
            }
        }
        return 0;
    }
    
    @Override
    public DealerShopers selectDealerShopersBy(String shoperId, String productSkuId)
    {
        if (null != shoperId && null != productSkuId) { return dealerShopersMapper.getByproductSkuIdAndDealerShopId(productSkuId, shoperId); }
        return null;
    }
}
