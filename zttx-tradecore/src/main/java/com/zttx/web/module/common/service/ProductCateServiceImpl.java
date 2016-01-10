/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.common.entity.ProductCate;
import com.zttx.web.module.common.mapper.ProductCateMapper;
import com.zttx.web.module.common.model.ProductCateModel;

/**
 * 产品所属分类 服务实现类
 * <p>File：ProductCate.java </p>
 * <p>Title: ProductCate </p>
 * <p>Description:ProductCate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductCateServiceImpl extends GenericServiceApiImpl<ProductCate> implements ProductCateService
{
    private ProductCateMapper        productCateMapper;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired(required = true)
    public ProductCateServiceImpl(ProductCateMapper productCateMapper)
    {
        super(productCateMapper);
        this.productCateMapper = productCateMapper;
    }
    
    @Override
    public String getCateIds(String productId)
    {
        String cateIdsStr = null;
        if (StringUtils.isNotBlank(productId))
        {
            StringBuffer cateIds = new StringBuffer();
            List<ProductCate> cateList = productCateMapper.getCateList(productId);
            if (cateList != null && !cateList.isEmpty())
            {
                for (ProductCate cate : cateList)
                {
                    cateIds.append(",").append(cate.getCateId());
                }
                cateIdsStr = cateIds.substring(1);
            }
        }
        return cateIdsStr;
    }
    
    @Override
    public void updateProductCate(ProductCateModel productCate) throws BusinessException
    {
        if (ArrayUtils.isNotEmpty(productCate.getProductIdAry()))
        {
            List<ProductCate> addList = Lists.newArrayList();
            List<String> delList = Lists.newArrayList();
            List<ProductBaseInfo> productList = null;
            List<ProductCate> cateList = null;
            for (String item : productCate.getProductIdAry())
            {
                Map<String, ProductCate> cateMap = Maps.newHashMap();
                if (StringUtils.isNotBlank(item))
                {
                    productList = productInfoDubboConsumer.findByBrandIdAndProductId(productCate.getBrandId(), item);// 判断是否存在 该产品
                    if (!CollectionUtils.isEmpty(productList))
                    {
                        cateList = productCateMapper.findByBrandIdAndProductId(productCate.getBrandId(), item);// 获取该产品的所属类别
                        if (null != cateList && !cateList.isEmpty())
                        {
                            for (ProductCate cateItem : cateList)
                            {
                                cateMap.put(cateItem.getCateId(), cateItem);
                            }
                        }// 转换成map 形式
                        if (ArrayUtils.isNotEmpty(productCate.getCatelogIdAry()))
                        {
                            for (String cateItem : productCate.getCatelogIdAry())
                            {
                                if (StringUtils.isNotBlank(cateItem))
                                {
                                    if (null != cateMap.get(cateItem))
                                    {
                                        cateMap.remove(cateItem);
                                    }
                                    else
                                    {
                                        ProductCate tempProductCate = new ProductCate();
                                        tempProductCate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                                        tempProductCate.setProductId(item);
                                        tempProductCate.setCateId(cateItem);
                                        tempProductCate.setCreateTime(new Date().getTime());
                                        tempProductCate.setUpdateTime(new Date().getTime());
                                        tempProductCate.setDelFlag(false);
                                        addList.add(tempProductCate);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(cateMap))
                {
                    Iterator<String> iterator = cateMap.keySet().iterator();
                    while (iterator.hasNext())
                    {
                        String key = iterator.next();
                        ProductCate pCate = cateMap.get(key);
                        String refrenceId = pCate.getRefrenceId();
                        delList.add(refrenceId);
                    }
                }
            }
            if (delList.size() > 0)
            {
                productCateMapper.deleteBatch(delList);
            }
            if (addList.size() > 0)
            {
                productCateMapper.insertBatch(addList);
            }
        }
    }
    
    @Override
    public List<ProductCate> getProductCatesByProductId(String productId)
    {
        return productCateMapper.getProductCatesByProductId(productId);
    }
}
