package com.zttx.web.module.transfer.service;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.transfer.mapper.ProductTransferMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>File：ProductTransferServiceImpl.java</p>
 * <p>Title: 产品信息迁移服务</p>
 * <p>Description:ProductTransferServiceImpl </p>
 * <p>Copyright: Copyright (c)  15/10/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductTransferServiceImpl implements ProductTransferService
{
    @Autowired(required = true)
    private ProductTransferMapper productTransferMapper;
    
    @Override
    public Long findSampleProductInfoCount() throws BusinessException
    {
        return productTransferMapper.findSampleProductInfoCount();
    }
    
    @Override
    public List<Map<String, Object>> findSampleProductInfo(Pagination page) throws BusinessException
    {
        return productTransferMapper.findSampleProductInfo(page);
    }
    
    @Override
    public Long findFactoryActivityProductCount() throws BusinessException
    {
        return productTransferMapper.findFactoryActivityProductCount();
    }
    
    @Override
    public List<Map<String, Object>> findFactoryActivityProduct(Pagination page) throws BusinessException
    {
        return productTransferMapper.findFactoryActivityProduct(page);
    }
    
    @Override
    public BigDecimal getMaxSkuDriectPrice(String productId) throws BusinessException
    {
        return productTransferMapper.getMaxSkuDriectPrice(productId);
    }
    
    @Override
    public void modifySkuSamplePrice(String productId, BigDecimal price) throws BusinessException
    {
        productTransferMapper.modifySkuSamplePrice(productId, price);
    }
    
    @Override
    public void modifyProductCreditState(List<Map<String, Object>> products) throws BusinessException
    {
        if (null != products && products.size() > 0)
        {
            for (Map<String, Object> product : products)
            {
                String productId = MapUtils.getString(product, "refrenceId", null);
                if (null != productId) productTransferMapper.modifyProductCreditState(productId);
            }
        }
    }
    
    @Override
    public void modifySkuCreditPrice(String productId) throws BusinessException
    {
        productTransferMapper.modifySkuCreditPrice(productId);
    }
}
