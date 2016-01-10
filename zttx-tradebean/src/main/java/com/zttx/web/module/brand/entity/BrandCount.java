/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌商计数信息 实体对象
 * <p>File：BrandCount.java</p>
 * <p>Title: BrandCount</p>
 * <p>Description:BrandCount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandCount extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**合作中的经销商*/
    private java.lang.Integer cooperCount = 0;
    
    /**申请中的经销商*/
    private java.lang.Integer applyCount = 0;
    
    /**邀请中的经销商*/
    private java.lang.Integer inviteCount = 0;
    
    /**等待付款订单数量*/
    private java.lang.Integer waitPayCount = 0;
    
    /**预订产品订单数量*/
    private java.lang.Integer preOrderCount = 0;
    
    /**授信订单数量*/
    private java.lang.Integer creditCount = 0;
    
    /**待发货订单数量*/
    private java.lang.Integer waitSendCount = 0;
    
    /**已发货订单数量*/
    private java.lang.Integer waitConfirmCount = 0;
    
    /**退款订单数量*/
    private java.lang.Integer refundCount = 0;
    
    /**已铺货产品数量*/
    private java.lang.Integer publishedCount = 0;
    
    /**未铺货产品数量*/
    private java.lang.Integer waitPublishCount = 0;
    
    /**紧张库存产品数量*/
    private java.lang.Integer tightInventoryCount = 0;
    
    /**库存缺货产品数量*/
    private java.lang.Integer shortageCount = 0;
    
    /**预订铺货产品数量*/
    private java.lang.Integer prePublishedCount = 0;
    
    /**查看经销商联系信息数量*/
    private java.lang.Integer viewDealerCount = 0;
    
    /**品牌数量*/
    private java.lang.Integer brandsCount = 0;
    
    /**查看经销商联系信息总数量*/
    private java.lang.Integer viewDealerTotal = 0;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    public java.lang.Integer getCooperCount()
    {
        return this.cooperCount;
    }
    
    public void setCooperCount(java.lang.Integer cooperCount)
    {
        this.cooperCount = cooperCount;
    }
    
    public java.lang.Integer getApplyCount()
    {
        return this.applyCount;
    }
    
    public void setApplyCount(java.lang.Integer applyCount)
    {
        this.applyCount = applyCount;
    }
    
    public java.lang.Integer getInviteCount()
    {
        return this.inviteCount;
    }
    
    public void setInviteCount(java.lang.Integer inviteCount)
    {
        this.inviteCount = inviteCount;
    }
    
    public java.lang.Integer getWaitPayCount()
    {
        return this.waitPayCount;
    }
    
    public void setWaitPayCount(java.lang.Integer waitPayCount)
    {
        this.waitPayCount = waitPayCount;
    }
    
    public java.lang.Integer getPreOrderCount()
    {
        return this.preOrderCount;
    }
    
    public void setPreOrderCount(java.lang.Integer preOrderCount)
    {
        this.preOrderCount = preOrderCount;
    }
    
    public java.lang.Integer getCreditCount()
    {
        return this.creditCount;
    }
    
    public void setCreditCount(java.lang.Integer creditCount)
    {
        this.creditCount = creditCount;
    }
    
    public java.lang.Integer getWaitSendCount()
    {
        return this.waitSendCount;
    }
    
    public void setWaitSendCount(java.lang.Integer waitSendCount)
    {
        this.waitSendCount = waitSendCount;
    }
    
    public java.lang.Integer getWaitConfirmCount()
    {
        return this.waitConfirmCount;
    }
    
    public void setWaitConfirmCount(java.lang.Integer waitConfirmCount)
    {
        this.waitConfirmCount = waitConfirmCount;
    }
    
    public java.lang.Integer getRefundCount()
    {
        return this.refundCount;
    }
    
    public void setRefundCount(java.lang.Integer refundCount)
    {
        this.refundCount = refundCount;
    }
    
    public java.lang.Integer getPublishedCount()
    {
        return this.publishedCount;
    }
    
    public void setPublishedCount(java.lang.Integer publishedCount)
    {
        this.publishedCount = publishedCount;
    }
    
    public java.lang.Integer getWaitPublishCount()
    {
        return this.waitPublishCount;
    }
    
    public void setWaitPublishCount(java.lang.Integer waitPublishCount)
    {
        this.waitPublishCount = waitPublishCount;
    }
    
    public java.lang.Integer getTightInventoryCount()
    {
        return this.tightInventoryCount;
    }
    
    public void setTightInventoryCount(java.lang.Integer tightInventoryCount)
    {
        this.tightInventoryCount = tightInventoryCount;
    }
    
    public java.lang.Integer getShortageCount()
    {
        return this.shortageCount;
    }
    
    public void setShortageCount(java.lang.Integer shortageCount)
    {
        this.shortageCount = shortageCount;
    }
    
    public java.lang.Integer getPrePublishedCount()
    {
        return this.prePublishedCount;
    }
    
    public void setPrePublishedCount(java.lang.Integer prePublishedCount)
    {
        this.prePublishedCount = prePublishedCount;
    }
    
    public java.lang.Integer getViewDealerCount()
    {
        return this.viewDealerCount;
    }
    
    public void setViewDealerCount(java.lang.Integer viewDealerCount)
    {
        this.viewDealerCount = viewDealerCount;
    }
    
    public java.lang.Integer getBrandsCount()
    {
        return this.brandsCount;
    }
    
    public void setBrandsCount(java.lang.Integer brandsCount)
    {
        this.brandsCount = brandsCount;
    }
    
    public java.lang.Integer getViewDealerTotal()
    {
        return this.viewDealerTotal;
    }
    
    public void setViewDealerTotal(java.lang.Integer viewDealerTotal)
    {
        this.viewDealerTotal = viewDealerTotal;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
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
