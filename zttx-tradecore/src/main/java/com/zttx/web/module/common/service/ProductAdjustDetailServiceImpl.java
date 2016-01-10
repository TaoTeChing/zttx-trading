/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.common.entity.ProductAdjustDetail;
import com.zttx.web.module.common.mapper.ProductAdjustDetailMapper;

/**
 * 产品调价明细表 服务实现类
 * <p>File：ProductAdjustDetail.java </p>
 * <p>Title: ProductAdjustDetail </p>
 * <p>Description:ProductAdjustDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductAdjustDetailServiceImpl extends GenericServiceApiImpl<ProductAdjustDetail> implements ProductAdjustDetailService
{

    private ProductAdjustDetailMapper productAdjustDetailMapper;
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;

    @Autowired(required = true)
    public ProductAdjustDetailServiceImpl(ProductAdjustDetailMapper productAdjustDetailMapper)
    {
        super(productAdjustDetailMapper);
        this.productAdjustDetailMapper = productAdjustDetailMapper;
    }

    @Override
    public Integer countByProductId(String brandId, String productId)
    {
        return productAdjustDetailMapper.countByProductId(brandId,productId);
    }

    @Override
    public ProductAdjustDetail findBySkuIdLastDetail(String skuId)
    {
        return productAdjustDetailMapper.findBySkuIdLastDetail(skuId);
    }

    @Override
    public PaginateResult<Map<String, Object>> search(ProductAdjustDetail detail)throws BusinessException
    {
        PaginateResult<Map<String, Object>> result=new PaginateResult<Map<String, Object>>();
        result.setList(productAdjustDetailMapper.search(detail));
        result.setPage(detail.getPage());
        for(int i=0;i<result.getList().size();i++){
            Map<String, Object> item=result.getList().get(i);
            List<ProductAttrValue> attrList=productSkuInfoDubboConsumer.getSaleAttrValueBySku((String)item.get("skuId"));
            for(int j=0;j<attrList.size();j++){
                if(attrList.get(j).getAttributeId().equals("3372")){
                    item.put("color", attrList.get(j).getExtAttributeValue());
                }else if(attrList.get(j).getAttributeId().equals("101")){
                    item.put("size", attrList.get(j).getExtAttributeValue());
                }
            }
        }
        return result;
    }
}
