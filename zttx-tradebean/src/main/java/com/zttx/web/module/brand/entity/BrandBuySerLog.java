/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商购买的服务记录 实体对象
 * <p>File：BrandBuySerLog.java</p>
 * <p>Title: BrandBuySerLog</p>
 * <p>Description:BrandBuySerLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandBuySerLog extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**品牌商编号*/
    @NotBlank(message = "品牌商编号不能为空")
    private java.lang.String     brandId;
    
    /**品牌商名称*/
    private java.lang.String     brandName;
    
    /**支付流水号*/
    private java.lang.Long       serialNumber;
    
    /**服务编号*/
    @NotBlank(message = "服务编号不能为空")
    private java.lang.String     serviceId;
    
    /**服务类别
            */
    private java.lang.Short      servicerCate;
    
    /**购买时间*/
    private java.lang.Long       buyTime;
    
    /**购买数量*/
    @NotNull(message = "购买数量不能为空")
    @Digits(integer = 11, fraction = 0, message = "购买数量不能超过11位")
    private java.lang.Integer    buyNum;
    
    /**购买的金额*/
	@NotNull(message = "购买的金额不能为空")
	@Digits(integer = 11, fraction = 2, message = "购买的金额整数位不能超过11位，小数位不能超过2位")
    private java.math.BigDecimal buyMoney;
    
    /**开始时间*/
    private java.lang.Long       beginTime;
    
    /**结束时间*/
    private java.lang.Long       endTime;
    
    /**支付状态
            0：生成
            1：提交支付
            2：支付成功
            3：支付失败*/
    private java.lang.Short      buyState;
    
    /**1：续期
            2：增加数量*/
	@NotNull(message = "购买类型不能为空")
	@Max(value = 2, message = "购买类型不存在")
	@Min(value = 1, message = "购买类型不存在")
    private java.lang.Short      chargType;
    
    /**修改时间*/
    private java.lang.Long       updateTime;
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandName()
    {
        return this.brandName;
    }
    
    public void setBrandName(java.lang.String brandName)
    {
        this.brandName = brandName;
    }
    
    public java.lang.Long getSerialNumber()
    {
        return this.serialNumber;
    }
    
    public void setSerialNumber(java.lang.Long serialNumber)
    {
        this.serialNumber = serialNumber;
    }
    
    public java.lang.String getServiceId()
    {
        return this.serviceId;
    }
    
    public void setServiceId(java.lang.String serviceId)
    {
        this.serviceId = serviceId;
    }
    
    public java.lang.Short getServicerCate()
    {
        return this.servicerCate;
    }
    
    public void setServicerCate(java.lang.Short servicerCate)
    {
        this.servicerCate = servicerCate;
    }
    
    public java.lang.Long getBuyTime()
    {
        return this.buyTime;
    }
    
    public void setBuyTime(java.lang.Long buyTime)
    {
        this.buyTime = buyTime;
    }
    
    public java.lang.Integer getBuyNum()
    {
        return this.buyNum;
    }
    
    public void setBuyNum(java.lang.Integer buyNum)
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
    
    public java.lang.Long getBeginTime()
    {
        return this.beginTime;
    }
    
    public void setBeginTime(java.lang.Long beginTime)
    {
        this.beginTime = beginTime;
    }
    
    public java.lang.Long getEndTime()
    {
        return this.endTime;
    }
    
    public void setEndTime(java.lang.Long endTime)
    {
        this.endTime = endTime;
    }
    
    public java.lang.Short getBuyState()
    {
        return this.buyState;
    }
    
    public void setBuyState(java.lang.Short buyState)
    {
        this.buyState = buyState;
    }
    
    public java.lang.Short getChargType()
    {
        return this.chargType;
    }
    
    public void setChargType(java.lang.Short chargType)
    {
        this.chargType = chargType;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
}
