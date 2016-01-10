package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.goods.module.dto.ProductSkuDto;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuBarcode;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductSkuInfoDubboConsumer.java </p>
 * <p>Title: 产品SKU消费服务 </p>
 * <p>Description:ProductSkuInfoDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class ProductSkuInfoDubboConsumer
{
    public static final Logger logger = LoggerFactory.getLogger(ProductSkuInfoDubboConsumer.class);
    
    @Autowired(required = false)
    private ProductSkuService  productSkuService;
    
    /**
     * 跟新sku基本信息
     * @param sku
     * @throws BusinessException
     */
    public void updateSimple(ProductSku sku) throws BusinessException
    {
        try
        {
            productSkuService.updateSimple(sku);
        }
        catch (Exception e)
        {
            logger.error("updateSimple error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 根据主键更新商品sku
     * @param sku
     */
    public void updateSelectiveByPrimaryKey(ProductSku sku) throws BusinessException
    {
        try
        {
            productSkuService.updateSelectiveByPrimaryKey(sku);
        }
        catch (BusinessException e)
        {
            logger.error("updateSelectiveByPrimaryKey error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 更新商品属性
     * @param attrValue
     */
    public void updateProductAttrValue(ProductAttrValue attrValue) throws BusinessException
    {
        try
        {
            productSkuService.updateProductAttrValue(attrValue);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductAttrValue error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 更新建议销售价
     * @param priceList
     */
    public void updateRecommendPrice(List<ProductSkuPrice> priceList) throws BusinessException
    {
        try
        {
            productSkuService.updateRecommendPrice(priceList);
        }
        catch (BusinessException e)
        {
            logger.error("updateRecommendPrice error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 新增sku列表,仅用于
     * @param productSkuList
     * @return
     */
    public List<ProductSku> createProductSku(List<ProductSku> productSkuList) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.createProductSku(productSkuList);
        }
        catch (Exception e)
        {
            logger.error("createProductSku error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 更新指定商品sku的属性 
     * 根据  ProductSkuDto对象中的  operate 取值(新增 ,修改，删除)  
     * 对于同一个商品下的属性组合，还需要做已存在的判断
     * @param productSkuDtoList
     * @return
     */
    public List<ProductSkuDto> updateProductSku(List<ProductSkuDto> productSkuDtoList) throws BusinessException
    {
        List<ProductSkuDto> info = null;
        try
        {
            info = productSkuService.updateProductSku(productSkuDtoList);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductSku error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据商品Sku id查询商品相关的sku集合及其sku的下属属性
     * @param productSkuIds
     * @return
     */
    public List<ProductSku> findByProductSkuIds(List<String> productSkuIds) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.findByProductSkuIds(productSkuIds);
        }
        catch (Exception e)
        {
            logger.error("findByProductSkuIds error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 获取sku信息，包含属性值，不包含条码
     * @param skuId
     * @return
     */
    public ProductSku findProductSku(String skuId) throws BusinessException
    {
        ProductSku info = null;
        try
        {
            info = productSkuService.findProductSku(skuId);
        }
        catch (Exception e)
        {
            logger.error("findProductSku error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 获取sku信息，包含属性值、条码
     * @param skuIds
     * @return
     */
    public List<ProductSku> findProductSkuWithBarCode(List<String> skuIds) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.findProductSkuWithBarCode(skuIds);
        }
        catch (Exception e)
        {
            logger.error("ProductSkuInfoDubboConsumer error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据barcode获取ProductSku
     * @param barCodeList
     * @return
     */
    public List<ProductSku> findByBarCode(List<String> barCodeList) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.findByBarCode(barCodeList);
        }
        catch (Exception e)
        {
            logger.error("findByBarCode error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据productId获取sku和相关ProductAttValue
     * @param productId
     * @return
     */
    public List<ProductSku> findByProductId(String productId, Boolean withBarCode) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.findByProductId(productId, withBarCode);
        }
        catch (Exception e)
        {
            logger.error("findByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据多个产品id获取sku列表
     * @param productIdList
     * @return
     */
    public List<ProductSku> findByProductIds(List<String> productIdList, Boolean withBarCode) throws BusinessException
    {
        List<ProductSku> info = null;
        try
        {
            info = productSkuService.findByProductIds(productIdList, withBarCode);
        }
        catch (Exception e)
        {
            logger.error("findByProductIds error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据skuId查找sku价格对象
     * @param skuId
     * @return
     */
    public ProductSkuPrice getPriceBySkuId(String skuId) throws BusinessException
    {
        ProductSkuPrice info = null;
        try
        {
            info = productSkuService.getPriceBySkuId(skuId);
        }
        catch (Exception e)
        {
            logger.error("getPriceBySkuId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据产品id获取sku价格列表
     * @param productId
     * @return
     */
    public List<ProductSkuPrice> findSkuPriceByProductId(String productId) throws BusinessException
    {
        List<ProductSkuPrice> info = null;
        try
        {
            info = productSkuService.findSkuPriceByProductId(productId);
        }
        catch (Exception e)
        {
            logger.error("findSkuPriceByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据skuid查找sku条码
     * @param skuId
     * @return
     */
    public ProductSkuBarcode findCompBarCodeBySkuId(String skuId) throws BusinessException
    {
        ProductSkuBarcode info = null;
        try
        {
            info = productSkuService.findCompBarCodeBySkuId(skuId);
        }
        catch (Exception e)
        {
            logger.error("findCompBarCodeBySkuId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据产品id获取最小授信价
     * @param productId
     * @return
     */
    public Map<String, Object> findMinPriceByProductId(String productId) throws BusinessException
    {
        Map<String, Object> info = null;
        try
        {
            info = productSkuService.findMinPriceByProductId(productId);
        }
        catch (Exception e)
        {
            logger.error("findMinPriceByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据产品id和属性项id获取销售属性或产品属性
     * @return
     */
    public List<ProductAttrValue> findAttrValuebyProductIdAndCateAttributeItemId(String productId, String cateAttributeItemId, Boolean isSkuAttr) throws BusinessException
    {
        List<ProductAttrValue> info = null;
        try
        {
            info = productSkuService.findAttrValuebyProductIdAndCateAttributeItemId(productId, cateAttributeItemId, isSkuAttr);
        }
        catch (Exception e)
        {
            logger.error("findAttrValuebyProductIdAndCateAttributeItemId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据 颜色和尺码 属性项id 查找 转换后的 sku 属性
     * @param colorAttributeValueId BrandProAttrValue.refrenceId
     * @param sizeAttributeValueId BrandProAttrValue.refrenceId
     * @return
     */
    public ProductSku findByProductIdAndPairAttributeId(String srouce, String productId, String colorAttributeValueId, String colorAttributeValue,
            String sizeAttributeValueId, String sizeAttributeValue) throws BusinessException
    {
        ProductSku info = null;
        try
        {
            info = productSkuService.findByProductIdAndPairAttributeId(srouce, productId, colorAttributeValueId, colorAttributeValue, sizeAttributeValueId,
                    sizeAttributeValue);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductIdAndPairAttributeId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
  //修改sku返点价格
    public void saveProductSkuPointPriceList(String brandId, List<ProductSku> productSkuList) throws BusinessException
    {
        try
        {
            productSkuService.saveProductSkuPointPriceList(brandId, productSkuList);
        }
        catch (Exception e)
        {
            logger.error("saveProductSkuList error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    //根据skuid查找销售属性
    public List<ProductAttrValue> getSaleAttrValueBySku(String skuId)throws BusinessException{
        try
        {
            return productSkuService.getSaleAttrValueBySku(skuId);
        }
        catch (Exception e)
        {
            logger.error("productSkuInfoDubboConsumer error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
}
