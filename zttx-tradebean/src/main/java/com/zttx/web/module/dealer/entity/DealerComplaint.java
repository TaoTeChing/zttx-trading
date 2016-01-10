/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.NotNull;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商投诉信息 实体对象
 * <p>File：DealerComplaint.java</p>
 * <p>Title: DealerComplaint</p>
 * <p>Description:DealerComplaint</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerComplaint extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**订单编号*/
    private java.lang.String  orderId;
    
    /**投诉单号*/
    private java.lang.Long    complaintNumberId;
    
    /**友好订单编号*/
    private java.lang.Long    orderNumber;
    
    /**经销商主帐号编号*/
    private java.lang.String  dealerId;
    
    private java.lang.String  dealerName;
    
    /**品牌商主帐号编号*/
    private java.lang.String  brandId;
    
    private java.lang.String  brandName;
    
    /**品牌编号*/
    private java.lang.String  brandsId;
    
    private java.lang.String  brandsName;           // 品牌名称
    
    /**投诉原因*/
    private java.lang.Short   complaintCause;
    
    /**投诉原因名*/
    private java.lang.String  complaintName;
    
    /**描述*/
    private java.lang.String  description;
    
    /**投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）*/
    @NotNull(message = "投诉状态不能为空")
    private java.lang.Short   comState;
    
    /**客服介入的时间*/
    private java.lang.Long    interposeTime;
    
    /**品牌商陈述*/
    private java.lang.String  brandDesc;
    
    /**客服处理结果*/
    private java.lang.String  complaintResult;
    
    /**建档时间*/
    private java.lang.Long    createtime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    private String            keyOrderNumber;       // 订单编号
    
    private String            keyComplaintNumberId; // 投诉编号
    
    private String            startTime;
    
    private String            endTime;
    
    public String getKeyComplaintNumberId()
    {
        return keyComplaintNumberId;
    }
    
    public void setKeyComplaintNumberId(String keyComplaintNumberId)
    {
        this.keyComplaintNumberId = keyComplaintNumberId;
    }
    
    public String getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
    public String getKeyOrderNumber()
    {
        return keyOrderNumber;
    }
    
    public void setKeyOrderNumber(String keyOrderNumber)
    {
        this.keyOrderNumber = keyOrderNumber;
    }
    
    public java.lang.String getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId(java.lang.String orderId)
    {
        this.orderId = orderId;
    }
    
    public java.lang.Long getComplaintNumberId()
    {
        return this.complaintNumberId;
    }
    
    public void setComplaintNumberId(java.lang.Long complaintNumberId)
    {
        this.complaintNumberId = complaintNumberId;
    }
    
    public java.lang.Long getOrderNumber()
    {
        return this.orderNumber;
    }
    
    public void setOrderNumber(java.lang.Long orderNumber)
    {
        this.orderNumber = orderNumber;
    }
    
    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandsId()
    {
        return this.brandsId;
    }
    
    public void setBrandsId(java.lang.String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public java.lang.Short getComplaintCause()
    {
        return this.complaintCause;
    }
    
    public void setComplaintCause(java.lang.Short complaintCause)
    {
        this.complaintCause = complaintCause;
    }
    
    public java.lang.String getComplaintName()
    {
        return this.complaintName;
    }
    
    public void setComplaintName(java.lang.String complaintName)
    {
        this.complaintName = complaintName;
    }
    
    public java.lang.String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(java.lang.String description)
    {
        this.description = description;
    }
    
    public java.lang.Short getComState()
    {
        return this.comState;
    }
    
    public void setComState(java.lang.Short comState)
    {
        this.comState = comState;
    }
    
    public java.lang.Long getInterposeTime()
    {
        return this.interposeTime;
    }
    
    public void setInterposeTime(java.lang.Long interposeTime)
    {
        this.interposeTime = interposeTime;
    }
    
    public java.lang.String getBrandDesc()
    {
        return this.brandDesc;
    }
    
    public void setBrandDesc(java.lang.String brandDesc)
    {
        this.brandDesc = brandDesc;
    }
    
    public java.lang.String getComplaintResult()
    {
        return this.complaintResult;
    }
    
    public void setComplaintResult(java.lang.String complaintResult)
    {
        this.complaintResult = complaintResult;
    }
    
    public java.lang.Long getCreatetime()
    {
        return this.createtime;
    }
    
    public void setCreatetime(java.lang.Long createtime)
    {
        this.createtime = createtime;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public String getDealerName()
    {
        return dealerName;
    }
    
    public DealerComplaint setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
        return this;
    }
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public DealerComplaint setBrandName(String brandName)
    {
        this.brandName = brandName;
        return this;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public DealerComplaint setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
        return this;
    }
}
