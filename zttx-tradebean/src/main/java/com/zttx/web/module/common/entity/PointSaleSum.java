/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.ApplicationConst;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返点类型产品门店销售统计和表 实体对象
 * <p>File：PointSaleSum.java</p>
 * <p>Title: PointSaleSum</p>
 * <p>Description:PointSaleSum</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class PointSaleSum extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**经销商id*/
    private String            dealerId;
    
    /**品牌商id*/
    private String            brandId;
    
    /**品牌id*/
    private String            brandsId;
    
    /**销量和*/
    private Integer           saleNumSum;
    
    /**销售额和*/
    private BigDecimal        salePriceSum;
    
    /**返点金额和（非返点价和）*/
    private BigDecimal        pointPriceSum;
    
    /**成本价和*/
    private BigDecimal        costPriceSum;
    
    /**已付成本价和*/
    private BigDecimal        paidPriceSum;
    
    /**未付成本价和*/
    private BigDecimal        debtPriceSum;
    
    /**是否支付：0为支付 1支付*/
    private Boolean           isPaid;
    
    private String            payExtId;
    
    /**erp记录生成时间*/
    private Long              erpTime;
    
    /**交易平台创建时间*/
    private Long              createTime;
    
    /**查询 参数**/
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date              startTime;
    
    @DateTimeFormat(pattern = ApplicationConst.DATE_FORMAT_YMD)
    private Date              endTime;
    
    private Long              startTimeLong;
    
    private Long              endTimeLong;
    
    private String            dealerName;
    
    private String            brandName;
    
    /**查询 参数**/
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
    
    public Long getStartTimeLong()
    {
        return startTimeLong;
    }
    
    public void setStartTimeLong(Long startTimeLong)
    {
        this.startTimeLong = startTimeLong;
    }
    
    public Long getEndTimeLong()
    {
        return endTimeLong;
    }
    
    public void setEndTimeLong(Long endTimeLong)
    {
        this.endTimeLong = endTimeLong;
    }
    
    public String getDealerName()
    {
        return dealerName;
    }
    
    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public Integer getSaleNumSum()
    {
        return this.saleNumSum;
    }
    
    public void setSaleNumSum(Integer saleNumSum)
    {
        this.saleNumSum = saleNumSum;
    }
    
    public BigDecimal getSalePriceSum()
    {
        return salePriceSum;
    }
    
    public void setSalePriceSum(BigDecimal salePriceSum)
    {
        this.salePriceSum = salePriceSum;
    }
    
    public BigDecimal getPointPriceSum()
    {
        return pointPriceSum;
    }
    
    public void setPointPriceSum(BigDecimal pointPriceSum)
    {
        this.pointPriceSum = pointPriceSum;
    }
    
    public BigDecimal getCostPriceSum()
    {
        return costPriceSum;
    }
    
    public void setCostPriceSum(BigDecimal costPriceSum)
    {
        this.costPriceSum = costPriceSum;
    }
    
    public BigDecimal getPaidPriceSum()
    {
        return paidPriceSum;
    }
    
    public void setPaidPriceSum(BigDecimal paidPriceSum)
    {
        this.paidPriceSum = paidPriceSum;
    }
    
    public BigDecimal getDebtPriceSum()
    {
        return debtPriceSum;
    }
    
    public void setDebtPriceSum(BigDecimal debtPriceSum)
    {
        this.debtPriceSum = debtPriceSum;
    }
    
    public Boolean getIsPaid()
    {
        return this.isPaid;
    }
    
    public void setIsPaid(Boolean isPaid)
    {
        this.isPaid = isPaid;
    }
    
    public Long getErpTime()
    {
        return erpTime;
    }
    
    public void setErpTime(Long erpTime)
    {
        this.erpTime = erpTime;
    }
    
    public Long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public String getPayExtId()
    {
        return payExtId;
    }
    
    public void setPayExtId(String payExtId)
    {
        this.payExtId = payExtId;
    }
}
