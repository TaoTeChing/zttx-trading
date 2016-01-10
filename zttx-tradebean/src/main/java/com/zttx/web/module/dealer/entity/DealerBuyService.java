/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.sdk.utils.CalendarUtils;


/**
 * 经销商购买的服务 实体对象
 * <p>File：DealerBuyService.java</p>
 * <p>Title: DealerBuyService</p>
 * <p>Description:DealerBuyService</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerBuyService extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**服务商名称*/
    private String               dealerId;
    
    /**dealerName*/
    private String               dealerName;
    
    /**服务编号*/
    private String               serviceId;
    
    private String               serviceName;
    
    /**服务类别*/
    private Short                servicerCate;
    
    /**1：续期
            2：增加数量*/
    private Short                chargType;
    
    /**购买时间*/
    private Long                 buyTime;
    
    /**开始时间*/
    private Long                 beginTime;
    
    /**结束时间*/
    private Long                 endTime;
    
    /**购买金额*/
    private java.math.BigDecimal buyMoney;
    
    /**创建时间*/
    private Long                 createTime;
    
    /**修改时间*/
    private Long                 updateTime;
    
    private Long                 startSearchTime;      // 开始搜索时间
    
    private Long                 endSearchTime;        // 结束搜索时间
    
    public String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public String getDealerName()
    {
        return this.dealerName;
    }
    
    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public String getServiceId()
    {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }
    
    public Short getServicerCate()
    {
        return this.servicerCate;
    }
    
    public void setServicerCate(Short servicerCate)
    {
        this.servicerCate = servicerCate;
    }
    
    public Short getChargType()
    {
        return this.chargType;
    }
    
    public void setChargType(Short chargType)
    {
        this.chargType = chargType;
    }
    
    public Long getBuyTime()
    {
        return this.buyTime;
    }
    
    public void setBuyTime(Long buyTime)
    {
        this.buyTime = buyTime;
    }
    
    public Long getBeginTime()
    {
        return this.beginTime;
    }
    
    public void setBeginTime(Long beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public Long getEndTime()
    {
        return this.endTime;
    }
    
    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }
    
    public java.math.BigDecimal getBuyMoney()
    {
        return this.buyMoney;
    }
    
    public void setBuyMoney(java.math.BigDecimal buyMoney)
    {
        this.buyMoney = buyMoney;
    }
    
    public Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Long getStartSearchTime()
    {
        return startSearchTime;
    }
    
    public DealerBuyService setStartSearchTime(Long startSearchTime)
    {
        this.startSearchTime = startSearchTime;
        return this;
    }
    
    public Long getEndSearchTime()
    {
        if (null == endSearchTime) { return null; }
        return CalendarUtils.addDay(endSearchTime, 1) - 1;
    }
    
    public DealerBuyService setEndSearchTime(Long endSearchTime)
    {
        this.endSearchTime = endSearchTime;
        return this;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    
    public DealerBuyService setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
        return this;
    }
}
