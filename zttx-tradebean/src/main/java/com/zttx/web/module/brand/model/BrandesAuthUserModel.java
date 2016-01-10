package com.zttx.web.module.brand.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <p>File:BrandesAuthUserModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/14 13:16</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class BrandesAuthUserModel
{
    public static final String INCLUDE_SKU     = "包含sku";        // 产品详情页
    
    public static final String NINCLUDE_SKU    = "不包含sku";       // 产品展示页
    
    public static final short  NO_AUTH         = 0;              // 未授权
    
    public static final short  CASH_AUTH       = 1;              // 普通授权
    
    public static final short  CREDIT_AUTH     = 2;              // 授信授权
    
    public String              productId;                        // 产品编号
    
    public Boolean             isValidProduct  = true;           // 产品是否有效
    
    public Boolean             isHasTakeSample = false;          // 是否拿过样
    
    public Boolean             isSample        = false;          // 是否支持拿样
    
    public Boolean             isPoint         = false;          // 是否支持返点
    
    public Short               isAuth;                           // 产品对用户是否授权，授权了才能显示价格 0 未授权 1 普通授权 2 授信
    
    public Short               userType;                         // 用户类型
    
    public BigDecimal          productPrice;                     // 产品吊牌价
    
    public BigDecimal          productDirPrice;                  // 产品直供价
    
    public BigDecimal          productCreditPrice;               // 产品授信价
    
    public BigDecimal          productSamplePrice;               // 产品拿样价
    
    public BigDecimal          productPointPercent;              // 产品返点比例
    
    public BigDecimal          pointPrice;//产品最小返点价
    
    public Set                 productColorSet = new TreeSet();  // 产品颜色信息
    
    public Map<String, Object> productSkuMap   = new TreeMap<>(); // 产品sku信息
    
    public BrandesAuthUserModel()
    {
    }
    
    public BrandesAuthUserModel(String productId)
    {
        this.productId = productId;
    }
    
    public Boolean getIsSample()
    {
        return isSample;
    }
    
    public void setIsSample(Boolean isSample)
    {
        this.isSample = isSample;
    }
    
    public Boolean getPoint()
    {
        return isPoint;
    }
    
    public void setPoint(Boolean point)
    {
        isPoint = point;
    }
    
    public Short getUserType()
    {
        return userType;
    }
    
    public void setUserType(Short userType)
    {
        this.userType = userType;
    }
    
    public Short getIsAuth()
    {
        return isAuth;
    }
    
    public void setIsAuth(Short isAuth)
    {
        this.isAuth = isAuth;
    }
    
    public Boolean getIsValidProduct()
    {
        return isValidProduct;
    }
    
    public void setIsValidProduct(Boolean isValidProduct)
    {
        this.isValidProduct = isValidProduct;
    }
    
    public Boolean getIsHasTakeSample()
    {
        return isHasTakeSample;
    }
    
    public void setIsHasTakeSample(Boolean isHasTakeSample)
    {
        this.isHasTakeSample = isHasTakeSample;
    }
    
    public BigDecimal getProductPrice()
    {
        return productPrice;
    }
    
    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }
    
    public BigDecimal getProductDirPrice()
    {
        return productDirPrice;
    }
    
    public void setProductDirPrice(BigDecimal productDirPrice)
    {
        this.productDirPrice = productDirPrice;
    }
    
    public BigDecimal getProductCreditPrice()
    {
        return productCreditPrice;
    }
    
    public void setProductCreditPrice(BigDecimal productCreditPrice)
    {
        this.productCreditPrice = productCreditPrice;
    }
    
    public Set getProductColorSet()
    {
        return productColorSet;
    }
    
    public void setProductColorSet(Set productColorSet)
    {
        this.productColorSet = productColorSet;
    }
    
    public BigDecimal getProductSamplePrice()
    {
        return productSamplePrice;
    }
    
    public void setProductSamplePrice(BigDecimal productSamplePrice)
    {
        this.productSamplePrice = productSamplePrice;
    }
    
    public BigDecimal getProductPointPercent()
    {
        return productPointPercent;
    }
    
    public void setProductPointPercent(BigDecimal productPointPercent)
    {
        this.productPointPercent = productPointPercent;
    }
    
    public Map<String, Object> getProductSkuMap()
    {
        return productSkuMap;
    }
    
    public void setProductSkuMap(Map<String, Object> productSkuMap)
    {
        this.productSkuMap = productSkuMap;
    }
    
    public String getProductId()
    {
        return productId;
    }
    
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public Boolean getIsPoint()
    {
        return isPoint;
    }

    public void setIsPoint(Boolean isPoint)
    {
        this.isPoint = isPoint;
    }

    public BigDecimal getPointPrice()
    {
        return pointPrice;
    }

    public void setPointPrice(BigDecimal pointPrice)
    {
        this.pointPrice = pointPrice;
    }
    
    
}
