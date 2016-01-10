package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.search.solrj.ProductSolrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>File：ProductSolrDubboServiceImpl.java </p>
 * <p>Title: 提供操作产品SOLR搜索引擎的DUBBO服务 </p>
 * <p>Description: ProductSolrDubboServiceImpl </p>
 * <p>Copyright: Copyright (c) 15/9/7</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductSolrDubboServiceImpl implements ProductSolrDubboService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductSolrHandler productSolrHandler;

    @Override
    public void delProductInfo(String productId) throws BusinessException {
        if (StringUtils.isBlank(productId)) {
            throw new BusinessException("产品编号不可为空！");
        }
        productSolrHandler.removeProductInfo(productId);
    }

    @Override
    public void addProductInfo(String productId) throws BusinessException {
        if (StringUtils.isBlank(productId)) {
            throw new BusinessException("产品编号不可为空！");
        }
        ProductInfo filter = new ProductInfo();
        filter.setRefrenceId(productId);
        List<ProductInfo> productInfos = productInfoService.findProductToSolr(filter, null);
        for (ProductInfo productInfo : productInfos) {
            productSolrHandler.addProducInfo(productInfo);
            break;
        }
    }
}
