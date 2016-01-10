package com.zttx.web.module.dealer.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.common.collect.Lists;

/**
 * <p>File：OrderModel</p>
 * <p>Title: 订单模型</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 15-5-26</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class OrderModel implements Serializable
{
    private static final long          serialVersionUID = 1331411398609279140L;
    
    private ArrayList<OrderItemsModel> itemsModels      = Lists.newArrayList();
    
    private String                     productId;
    
    private String                     productNo;
    
    private String                     productTitle;
    
    private String                     productImage;
    
    public ArrayList<OrderItemsModel> getItemsModels()
    {
        return itemsModels;
    }
    
    public String getProductId()
    {
        return productId;
    }
    
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    
    public String getProductImage()
    {
        return productImage;
    }
    
    public void setProductImage(String productImage)
    {
        this.productImage = productImage;
    }
    
    public String getProductNo()
    {
        return productNo;
    }
    
    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }
    
    public String getProductTitle()
    {
        return productTitle;
    }
    
    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
}
