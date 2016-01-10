/*
 * @(#)ShoperUpdateModel.java 2014-4-10 下午4:07:45
 * Copyright 2014 罗盛平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

/**
 * <p>Title:品牌商发货属性模型 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-10 下午4:07:45</p>
 * <p>Company: 8637.com</p>
 * @author 罗盛平
 * @version 1.0
 */
public class SendGoodsAttributeModel
{
    private String  productId;       // 产品ID
    
    private String  orderItemId;     // 订单详情主键ID
    
    private Integer sendNum;         // 发货数量
    
    private String  vid;
    
    private String  vidSon;
    
    private Boolean isSelect = false; // 是否选中
    
    public Integer getSendNum()
    {
        return sendNum;
    }
    
    public void setSendNum(Integer sendNum)
    {
        this.sendNum = sendNum;
    }
    
    public String getVid()
    {
        return vid;
    }
    
    public void setVid(String vid)
    {
        this.vid = vid;
    }
    
    public String getVidSon()
    {
        return vidSon;
    }
    
    public void setVidSon(String vidSon)
    {
        this.vidSon = vidSon;
    }
    
    public String getProductId()
    {
        return productId;
    }
    
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    
    public String getOrderItemId()
    {
        return orderItemId;
    }
    
    public void setOrderItemId(String orderItemId)
    {
        this.orderItemId = orderItemId;
    }
    
    public Boolean getIsSelect()
    {
        return isSelect;
    }
    
    public void setIsSelect(Boolean isSelect)
    {
        this.isSelect = isSelect;
    }
}
