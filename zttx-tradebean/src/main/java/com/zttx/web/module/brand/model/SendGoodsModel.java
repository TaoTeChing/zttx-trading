/*
 * @(#)ShoperUpdateModel.java 2014-4-10 下午4:07:45
 * Copyright 2014 罗盛平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import java.util.List;

/**
 * <p>Title:品牌商发货模型 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-10 下午4:07:45</p>
 * <p>Company: 8637.com</p>
 * @author 罗盛平
 * @version 1.0
 */
public class SendGoodsModel
{
    private Long                          orderId;        // 订单ID
    
    private String                        orderRefrenceId; // 订单主键
    
    private String                        brandId;        // 品牌id
    
    private String                        brandName;      // 品牌名称
    
    private Integer                       totalSendNum;   // 发货总量
    
    private List<SendGoodsAttributeModel> sendAtts;
    
    private String                        logisticName;   // 物流公司
    
    private String                        customLogisName; // 自定义物流公司
    
    private String                        shipNumber;     // 发货单号
    
    public List<SendGoodsAttributeModel> getSendAtts()
    {
        return sendAtts;
    }
    
    public void setSendAtts(List<SendGoodsAttributeModel> sendAtts)
    {
        this.sendAtts = sendAtts;
    }
    
    public String getLogisticName()
    {
        return logisticName;
    }
    
    public void setLogisticName(String logisticName)
    {
        this.logisticName = logisticName;
    }
    
    public String getShipNumber()
    {
        return shipNumber;
    }
    
    public void setShipNumber(String shipNumber)
    {
        this.shipNumber = shipNumber;
    }
    
    public String getCustomLogisName()
    {
        return customLogisName;
    }
    
    public void setCustomLogisName(String customLogisName)
    {
        this.customLogisName = customLogisName;
    }
    
    public Long getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }
    
    public String getOrderRefrenceId()
    {
        return orderRefrenceId;
    }
    
    public void setOrderRefrenceId(String orderRefrenceId)
    {
        this.orderRefrenceId = orderRefrenceId;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public Integer getTotalSendNum()
    {
        return totalSendNum;
    }
    
    public void setTotalSendNum(Integer totalSendNum)
    {
        this.totalSendNum = totalSendNum;
    }
}
