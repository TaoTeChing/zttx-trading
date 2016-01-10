package com.zttx.web.module.dealer.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>File：OrderItemsModel</p>
 * <p>Title: 订单顶模型</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 15-5-26</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class OrderItemsModel implements Serializable
{
    private static final long serialVersionUID = 1689972032270391389L;
    
    private String            orderId;
    
    private String            orderItemId;
    
    private String            productSkuId;
    
    private String            productSkuCode;
    
    private String            productSkuName;
    
    private BigDecimal        price;
    
    private BigDecimal        oldPrice;
    
    private BigDecimal        adjustPrice;
    
    private BigDecimal        agio;

    private Integer           quantity;
    
    private Integer           shipCount;
    
    private BigDecimal        discount;
    
    private BigDecimal        pointPercent;

	public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }
    
    public BigDecimal getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(BigDecimal discount)
    {
        this.discount = discount;
    }
    
    public Integer getShipCount()
    {
        return shipCount;
    }
    
    public void setShipCount(Integer shipCount)
    {
        this.shipCount = shipCount;
    }
    
    public BigDecimal getPrice()
    {
        return price;
    }
    
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    public String getProductSkuCode()
    {
        return productSkuCode;
    }
    
    public void setProductSkuCode(String productSkuCode)
    {
        this.productSkuCode = productSkuCode;
    }
    
    public String getProductSkuId()
    {
        return productSkuId;
    }
    
    public void setProductSkuId(String productSkuId)
    {
        this.productSkuId = productSkuId;
    }
    
    public String getProductSkuName()
    {
        return productSkuName;
    }
    
    public void setProductSkuName(String productSkuName)
    {
        this.productSkuName = productSkuName;
    }
    
    public Integer getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
    
    public String getOrderItemId()
    {
        return orderItemId;
    }
    
    public void setOrderItemId(String orderItemId)
    {
        this.orderItemId = orderItemId;
    }
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public BigDecimal getAgio()
    {
        return agio;
    }
    
    public void setAgio(BigDecimal agio)
    {
        this.agio = agio;
    }
    
    public BigDecimal getAdjustPrice()
    {
        return adjustPrice;
    }
    
    public void setAdjustPrice(BigDecimal adjustPrice)
    {
        this.adjustPrice = adjustPrice;
    }
    
    public BigDecimal getOldPrice()
    {
        return oldPrice;
    }
    
    public void setOldPrice(BigDecimal oldPrice)
    {
        this.oldPrice = oldPrice;
    }
    
    public BigDecimal getPointPercent() {
		return pointPercent;
	}

	public void setPointPercent(BigDecimal pointPercent) {
		this.pointPercent = pointPercent;
	}
}
