package com.zttx.web.dubbo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.goods.module.entity.ProductImage;
import com.zttx.goods.module.service.ProductImageService;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductImageDubboConsumer.java </p>
 * <p>Title: 产品图片消费服务 </p>
 * <p>Description:ProductImageDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class ProductImageDubboConsumer
{
    public static final Logger  logger = LoggerFactory.getLogger(ProductImageDubboConsumer.class);
    
    @Autowired(required = false)
    private ProductImageService productImageService;
    
    /**
     * 根据产品编号取产品图片
     * @param productId
     * @return {@link List<ProductImage>}
     */
    public List<ProductImage> findByProductId(String productId) throws BusinessException {
        List<ProductImage> imageList = null;
        try
        {
            imageList = productImageService.findByProductId(productId);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return imageList;
    }
}
