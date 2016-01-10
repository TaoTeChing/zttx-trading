/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商购买的服务记录 实体对象
 * <p>File：DealerBuySerLog.java</p>
 * <p>Title: DealerBuySerLog</p>
 * <p>Description:DealerBuySerLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerBuySerLog extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**经销商编号*/
    @NotBlank(message = "经销商编号不能为空")
    private String               dealerId;
    
    /**经销商名称*/
    private String               dealerName;
    
    /**支付流水号*/
    private Long                 serialNumber;
    
    /**服务编号*/
    @NotBlank(message = "服务编号不能为空")
    private String               serviceId;
    
    private String               serviceName;
    
    /**服务类别*/
    @NotNull(message = "服务类别不能为空")
    private Short                servicerCate;
    
    /**购买时间*/
    private Long                 buyTime;
    
    /**购买数量*/
    @NotNull(message = "购买数量不能为空")
    @Digits(integer = 11, fraction = 0, message = "购买数量不能超过11位")
    private Integer              buyNum;
    
    /**购买的金额*/
    @NotNull(message = "购买的金额不能为空")
    @Digits(integer = 11, fraction = 2, message = "购买的金额整数位不能超过11位，小数位不能超过2位")
    private java.math.BigDecimal buyMoney;
    
    /**开始时间*/
    @NotNull(message = "服务开始时间不能为空")
    private Long                 beginTime;
    
    /**endTime*/
    private Long                 endTime;
    
    /**支付状态
            0：生成
            1：提交支付
            2：支付成功
            3：支付失败*/
    private Short                buyState;
    
    /**1：续期
       2：增加数量*/
    @NotNull(message = "购买类型不能为空")
    @Max(value = 2, message = "购买类型不存在")
    @Min(value = 1, message = "购买类型不存在")
    private Short                chargType;
    
    /**addressId*/
    private String               addressId;
    
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
    
    public Long getSerialNumber()
    {
        return this.serialNumber;
    }
    
    public void setSerialNumber(Long serialNumber)
    {
        this.serialNumber = serialNumber;
    }
    
    public String getServiceId()
    {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    
    public DealerBuySerLog setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
        return this;
    }
    
    public Short getServicerCate()
    {
        return this.servicerCate;
    }
    
    public void setServicerCate(Short servicerCate)
    {
        this.servicerCate = servicerCate;
    }
    
    public Long getBuyTime()
    {
        return this.buyTime;
    }
    
    public void setBuyTime(Long buyTime)
    {
        this.buyTime = buyTime;
    }
    
    public Integer getBuyNum()
    {
        return this.buyNum;
    }
    
    public void setBuyNum(Integer buyNum)
    {
        this.buyNum = buyNum;
    }
    
    public java.math.BigDecimal getBuyMoney()
    {
        return this.buyMoney;
    }
    
    public void setBuyMoney(java.math.BigDecimal buyMoney)
    {
        this.buyMoney = buyMoney;
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
    
    public Short getBuyState()
    {
        return this.buyState;
    }
    
    public void setBuyState(Short buyState)
    {
        this.buyState = buyState;
    }
    
    public Short getChargType()
    {
        return this.chargType;
    }
    
    public void setChargType(Short chargType)
    {
        this.chargType = chargType;
    }
    
    public String getAddressId()
    {
        return this.addressId;
    }
    
    public void setAddressId(String addressId)
    {
        this.addressId = addressId;
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
    
    public DealerBuySerLog setStartSearchTime(Long startSearchTime)
    {
        this.startSearchTime = startSearchTime;
        return this;
    }
    
    public Long getEndSearchTime()
    {
        if (null == endSearchTime) { return null; }
        return CalendarUtils.addDay(endSearchTime, 1) - 1;
    }
    
    public DealerBuySerLog setEndSearchTime(Long endSearchTime)
    {
        this.endSearchTime = endSearchTime;
        return this;
    }
}
