/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌计数信息 实体对象
 * <p>File：BrandsCount.java</p>
 * <p>Title: BrandsCount</p>
 * <p>Description:BrandsCount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandsCount extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商主帐号编号*/
    private java.lang.String  brandId;
    
    /**品牌编号*/
    private java.lang.String  brandsId;
    
    /**合作中的经销商*/
    private java.lang.Integer joinCount = 0;
    
    /**申请中的经销商*/
    private java.lang.Integer applyCount = 0;
    
    /**邀请中的经销商*/
    private java.lang.Integer inviteCount = 0;
    
    /**发货订单数量*/
    private java.lang.Integer orderNum = 0;
    
    /**产品数量*/
    private java.lang.Integer productCount = 0;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**当前排名*/
    private java.lang.Integer ranking;
    
    /**收藏数量*/
    private java.lang.Integer favNum = 0;
    
    /**查看数量*/
    private java.lang.Integer viewNum = 0;
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public Integer getJoinCount()
    {
        return joinCount;
    }
    
    public void setJoinCount(Integer joinCount)
    {
        this.joinCount = joinCount;
    }
    
    public Integer getApplyCount()
    {
        return applyCount;
    }
    
    public void setApplyCount(Integer applyCount)
    {
        this.applyCount = applyCount;
    }
    
    public Integer getInviteCount()
    {
        return inviteCount;
    }
    
    public void setInviteCount(Integer inviteCount)
    {
        this.inviteCount = inviteCount;
    }
    
    public Integer getOrderNum()
    {
        return orderNum;
    }
    
    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public Integer getProductCount()
    {
        return productCount;
    }
    
    public void setProductCount(Integer productCount)
    {
        this.productCount = productCount;
    }
    
    public Long getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Integer getRanking()
    {
        return ranking;
    }
    
    public void setRanking(Integer ranking)
    {
        this.ranking = ranking;
    }
    
    public Integer getFavNum()
    {
        return favNum;
    }
    
    public void setFavNum(Integer favNum)
    {
        this.favNum = favNum;
    }
    
    public Integer getViewNum()
    {
        return viewNum;
    }
    
    public void setViewNum(Integer viewNum)
    {
        this.viewNum = viewNum;
    }
}
