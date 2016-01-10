/*
 * @(#)DealerOrderFilter 2014/4/10 11:06
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.utils.CalendarUtils;

/**
 * <p>File：DealerOrderFilter</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/10 11:06</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class DealerOrderFilter
{
    public static final int ORDER_TYPE_NORMAL = 0; // 普通订单
    
    public static final int ORDER_TYPE_PREPAY = 1; // 预支付
    
    public static final int ORDER_TYPE_FINAL  = 2; // 尾款
    
    private String          orderId;
    
    private Integer         orderType;
    
    private Short           orderStatus;
    
    private String          brandsId;
    
    private String          brandName;
    
    private String          productName;
    
    private int             orderMuiltStatus;
    
    private Integer         dealerOrderType;      // 2 工厂店订单 1 普通订单
    
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date            startTime;
    
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date            endTime;
    
    private String          sourceId;
    
    private Integer         isFactory;
    
    private String          productNo;

    public String getProductNo()
    {
        return productNo;
    }

    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }

    public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }
    
    public Integer getOrderType()
    {
        return orderType;
    }
    
    public void setOrderType(Integer orderType)
    {
        this.orderType = orderType;
    }
    
    public Short getOrderStatus()
    {
        return orderStatus;
    }
    
    public void setOrderStatus(Short orderStatus)
    {
        this.orderStatus = orderStatus;
    }
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    
    public String getStartTimeStr()
    {
        return this.startTime == null ? "" : CalendarUtils.getDate(this.startTime, ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public String getEndTimeStr()
    {
        return this.endTime == null ? "" : CalendarUtils.getDate(this.endTime, ApplicationConst.DATE_FORMAT_YMD);
    }
    
    public int getOrderMuiltStatus()
    {
        return orderMuiltStatus;
    }
    
    public void setOrderMuiltStatus(int orderMuiltStatus)
    {
        this.orderMuiltStatus = orderMuiltStatus;
    }
    
    public String getSourceId()
    {
        return sourceId;
    }
    
    public void setSourceId(String sourceId)
    {
        this.sourceId = sourceId;
    }
    
    public Integer getDealerOrderType()
    {
        return dealerOrderType;
    }
    
    public void setDealerOrderType(Integer dealerOrderType)
    {
        this.dealerOrderType = dealerOrderType;
    }
    
    public Integer getIsFactory()
    {
        return isFactory;
    }
    
    public void setIsFactory(Integer isFactory)
    {
        this.isFactory = isFactory;
    }
}
