/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.common.entity.ProductCount;
import com.zttx.web.module.common.mapper.ProductCountMapper;
import com.zttx.web.module.common.mapper.ProductViewLogMapper;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.mapper.DealerFavoriteMapper;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;

/**
 * 产品计数信息 服务实现类
 * <p>File：ProductCount.java </p>
 * <p>Title: ProductCount </p>
 * <p>Description:ProductCount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductCountServiceImpl extends GenericServiceApiImpl<ProductCount> implements ProductCountService
{
    private ProductCountMapper   productCountMapper;
    
    @Autowired
    private ProductViewLogMapper productViewLogMapper;
    
    @Autowired
    private DealerFavoriteMapper dealerFavoriteMapper;
    
    @Autowired
    private DealerOrdersMapper   dealerOrdersMapper;
    
    @Autowired(required = true)
    public ProductCountServiceImpl(ProductCountMapper productCountMapper)
    {
        super(productCountMapper);
        this.productCountMapper = productCountMapper;
    }
    
    @Override
    public void modifyProductCount(String brandId, String brandsId, String productId) throws BusinessException
    {
        Integer vewNum = productViewLogMapper.countViewLogByProduct(productId);
        DealerFavorite dealerFavorite = new DealerFavorite();
        dealerFavorite.setBrandId(brandId);
        dealerFavorite.setBrandsId(brandsId);
        dealerFavorite.setProductId(productId);
        Integer favNum = dealerFavoriteMapper.countDealerFavorite(dealerFavorite);
        Integer saleNum = dealerOrdersMapper.countSaleNumByProductId(productId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String month = sdf.format(new Date());
        Date currentMonthDate;
        Integer currentMonthSale = null;
        try
        {
            currentMonthDate = sdf.parse(month);
            Long currentMonth = currentMonthDate.getTime();
            Long nextMonth = CalendarUtils.addMonth(currentMonthDate, 1);
            currentMonthSale = dealerOrdersMapper.countSaleNumByProductIdAndMonth(productId, currentMonth, nextMonth);
        }
        catch (ParseException e)
        {
        }
        ProductCount productCount = productCountMapper.selectByPrimaryKey(productId);
        if (productCount != null)
        {
            productCount.setBrandId(brandId);
            productCount.setBrandsId(brandsId);
            productCount.setRefrenceId(productId);
            productCount.setCollectNum(favNum);
            productCount.setCreateTime(CalendarUtils.getCurrentTime());
            productCount.setSaleNum(saleNum);
            productCount.setViewNum(vewNum);
            productCount.setMonthSaleNum(currentMonthSale);
            productCount.setUpdateTime(CalendarUtils.getCurrentTime());
            productCountMapper.updateByPrimaryKeySelective(productCount);
        }
        else
        {
            productCount = new ProductCount();
            productCount.setBrandId(brandId);
            productCount.setBrandsId(brandsId);
            productCount.setRefrenceId(productId);
            productCount.setCollectNum(favNum == null ? 0 : favNum);
            productCount.setCreateTime(CalendarUtils.getCurrentTime());
            productCount.setSaleNum(saleNum == null ? 0 : saleNum);
            productCount.setViewNum(vewNum == null ? 0 : vewNum);
            productCount.setMonthSaleNum(currentMonthSale == null ? 0 : currentMonthSale);
            productCountMapper.insert(productCount);
        }
    }
    
    @Override
    public List<String> getProductCountMaps() throws BusinessException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR, -1);
        Long past = calendar.getTimeInMillis();
        return productCountMapper.getProductCountMaps(past, now);
    }
}
