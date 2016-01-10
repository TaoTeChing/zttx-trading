/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.ProductSaleCount;
import com.zttx.web.module.common.mapper.ProductSaleCountMapper;
import com.zttx.web.module.dealer.mapper.DealerOrdersMapper;

/**
 * ProductSaleCount 服务实现类
 * <p>File：ProductSaleCount.java </p>
 * <p>Title: ProductSaleCount </p>
 * <p>Description:ProductSaleCount </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductSaleCountServiceImpl extends GenericServiceApiImpl<ProductSaleCount> implements ProductSaleCountService {

    private ProductSaleCountMapper productSaleCountMapper;
    @Autowired
    private DealerOrdersMapper dealerOrdersMapper;

    @Autowired(required = true)
    public ProductSaleCountServiceImpl(ProductSaleCountMapper productSaleCountMapper) {
        super(productSaleCountMapper);
        this.productSaleCountMapper = productSaleCountMapper;
    }

    @Override
    public void modifyProductSaleCount(String brandId, String brandsId, String productId) throws BusinessException {
        List<Map> monthSales = dealerOrdersMapper.countMonthSaleNumByProductId(productId);
        productSaleCountMapper.deleteByProductId(productId);
        for (int i = 0; i < monthSales.size(); i++) {
            Map month = monthSales.get(i);
            ProductSaleCount count = new ProductSaleCount();
            count.setBrandId(brandId);
            count.setBrandsId(brandsId);
            count.setCreateTime(CalendarUtils.getCurrentTime());
            count.setProductId(productId);
            count.setMonthSale(((BigDecimal) month.get("quantity")).intValue());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            try {
                if (month.get("month") == null) {
                    continue;
                }
                count.setMonthTime(sdf.parse(month.get("month").toString()).getTime());
            } catch (ParseException e) {
                throw new BusinessException(CommonConst.FAIL, "日期格式错误");
            }
            count.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            productSaleCountMapper.insert(count);
        }
    }
}
