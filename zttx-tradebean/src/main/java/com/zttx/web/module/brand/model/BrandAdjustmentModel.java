package com.zttx.web.module.brand.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 调价对象
 * <p>
 *     用于与门店数据传递
 * </p>
 *
 * Created by playguy on 15/7/16.
 */
public class BrandAdjustmentModel implements Serializable
{
    private static final long serialVersionUID = -4616349731498760670L;
    
    private String            dealerId;
    
    private String            productSkuId;
    
    private BigDecimal        oldCostPrice;
    
    private BigDecimal        newCostPrice;
    
    private Long              quantity;
    
    public String getDealerId()
    {
        return dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public Long getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }
    
    public BigDecimal getNewCostPrice()
    {
        return newCostPrice;
    }
    
    public void setNewCostPrice(BigDecimal newCostPrice)
    {
        this.newCostPrice = newCostPrice;
    }
    
    public BigDecimal getOldCostPrice()
    {
        return oldCostPrice;
    }
    
    public void setOldCostPrice(BigDecimal oldCostPrice)
    {
        this.oldCostPrice = oldCostPrice;
    }
    
    public String getProductSkuId()
    {
        return productSkuId;
    }
    
    public void setProductSkuId(String productSkuId)
    {
        this.productSkuId = productSkuId;
    }
}
