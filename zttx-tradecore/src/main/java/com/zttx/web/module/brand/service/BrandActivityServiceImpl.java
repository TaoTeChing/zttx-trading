package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.utils.CrmClient;
import com.zttx.web.utils.ListUtils;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：BrandActivityServiceImpl.java </p>
 * <p>Title: BrandActivityServiceImpl </p>
 * <p>Description: BrandActivityServiceImpl </p>
 * <p>Copyright: Copyright (c) 十月 10，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Service
public class BrandActivityServiceImpl implements BrandActivityService
{
    public static final Logger logger = LoggerFactory.getLogger(BrandActivityServiceImpl.class);
    
    @Autowired(required = false)
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private BrandInfoService   brandInfoService;
    
    @Override
    public void sendAdjustPriceToBoss(String brandId, String productId, List<ProductSku> newProductSkuList) throws BusinessException
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        if (null == brandInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        ProductBaseInfo product = productInfoDubboConsumer.findByBrandIdAndProductIdWithSkuAndSkuPriceAndBarCode(brandId, productId);
        if (null != product)
        {
            // 获取需要推送给支撑接口的信息
            List<Map<String, Object>> dataToBoss = getAdjustPriceDataToBoss(brandInfo, product, newProductSkuList);
            if (ListUtils.isNotEmpty(dataToBoss))
            {
                Map<String, Object> param = Maps.newHashMap();
                param.put("adjustPrice", JSON.toJSONString(dataToBoss));
                CrmClient crmClient = new CrmClient();
                LoggerUtils.logInfo(logger, "#[调价功能]支撑系统接口调用开始");
                JsonMessage result = crmClient.getJsonMessage(param, BrandConstant.BrandActivityProduct.BOSS_ADJUST_PRICE_URL);
                if (result != null && !CommonConst.SUCCESS.getCode().equals(result.getCode()))
                {
                    LoggerUtils.logError(logger, "#[调价功能]支撑系统接口调用异常:" + result.getMessage());
                    throw new BusinessException(result.getCode(), result.getMessage());
                }
                LoggerUtils.logInfo(logger, "#[调价功能]支撑系统接口调用结果:{code:" + result.getCode() + " , message:" + result.getMessage() + "}");
            }
        }
    }
    
    /**
     * 获取需要推送给支撑接口的数据
     * @param brandInfo
     * @param product
     * @param newProductSkuList
     * @return
     */
    private List<Map<String, Object>> getAdjustPriceDataToBoss(BrandInfo brandInfo, ProductBaseInfo product, List<ProductSku> newProductSkuList)
    {
        List<Map<String, Object>> resultList = null;
        if (ListUtils.isNotEmpty(newProductSkuList) && ListUtils.isNotEmpty(product.getProductSkuList()))
        {
            resultList = Lists.newArrayList();
            Map<String, ProductSku> oldProductSkuMap = list2ProductSkuMap(product.getProductSkuList());// List 转 Map方便检索
            for (ProductSku newProductSku : newProductSkuList)
            {
                if (null == newProductSku.getAttrItemIds())
                {
                    continue;
                }
                ProductSku oldProductSku = oldProductSkuMap.get(newProductSku.getAttrItemIds());
                if (compareCreditPrice(oldProductSku, newProductSku))
                {
                    resultList.add(getAdjustPriceData(brandInfo, product, oldProductSku, newProductSku));
                }
            }
        }
        return resultList;
    }
    
    /**
     * 数据封装成map
     * @param brandInfo
     * @param product
     * @param oldProductSku
     * @param newProductSku
     * @return
     */
    private Map<String, Object> getAdjustPriceData(BrandInfo brandInfo, ProductBaseInfo product, ProductSku oldProductSku, ProductSku newProductSku)
    {
        Map<String, Object> data = Maps.newHashMap();
        data.put("employId", brandInfo.getRefrenceId());
        data.put("employName", brandInfo.getComName());
        data.put("createTime", CalendarUtils.getCurrentTime());
        data.put("useTime", CalendarUtils.getCurrentTime()); // 生效时间
        data.put("productId", product.getRefrenceId());
        data.put("productNo", product.getProductNo());
        data.put("brandId", product.getBrandsId()); // 品牌ID
        data.put("brandName", product.getBrandsName());
        data.put("content", "");
        data.put("oldPrice", oldProductSku.getProductSkuPrice().getCreditPrice());// 老的授信价
        data.put("price", newProductSku.getProductSkuPrice().getCreditPrice());// 新的授信价
        data.put("oldSalesPrice", oldProductSku.getProductSkuPrice().getFactoryStorePrice());// 建议销售价
        data.put("salesPrice", oldProductSku.getProductSkuPrice().getFactoryStorePrice());// 建议销售价保持不变
        data.put("type", 2); // 来源是交易平台
        data.put("activePrdSkuId", oldProductSku.getRefrenceId());
        data.put("activePrdSkuLabel", oldProductSku.getProductSkuName());
        return data;
    }
    
    /**
     * 比较授信价
     * @param oldProductSku
     * @param newProductSku
     * @return 不同 true ；相同 false
     */
    private boolean compareCreditPrice(ProductSku oldProductSku, ProductSku newProductSku)
    {
        if (null != oldProductSku && null != oldProductSku.getProductSkuPrice() && null != newProductSku && null != oldProductSku.getProductSkuPrice().getCreditPrice()
                && null != newProductSku.getProductSkuPrice().getCreditPrice()) { return oldProductSku.getProductSkuPrice().getCreditPrice()
                .compareTo(newProductSku.getProductSkuPrice().getCreditPrice()) != 0; }
        return false;
    }
    
    private Map<String, ProductSku> list2ProductSkuMap(List<ProductSku> productSkuList)
    {
        Map<String, ProductSku> productSkuMap = Maps.newHashMap();
        for (ProductSku productSku : productSkuList)
        {
            productSkuMap.put(productSku.getAttrItemIds(), productSku);
        }
        return productSkuMap;
    }
}
