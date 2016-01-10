/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericEntity;

import java.util.Map;

/**
 * 品牌经营品类信息 实体对象
 * <p>File：DealDic.java</p>
 * <p>Title: DealDic</p>
 * <p>Description:DealDic</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealDic extends GenericEntity
{
    private static final long                   serialVersionUID = 1L;
    
    /**品类编号*/
    private java.lang.Integer                   dealNo;
    
    /**品类名称*/
    private java.lang.String                    dealName;
    
    /**品类图标*/
    private java.lang.String                    dealIcon;
    
    /**上层编号*/
    private java.lang.String                    parentId;
    
    /**品类级别*/
    private java.lang.Short                     dealLevel;
    
    /**排序编号*/
    private java.lang.Integer                   dealOrder;
    
    /**该品类下的产品数量*/
    private java.lang.Integer                   productNum;
    
    /**默认产品重量*/
    private java.math.BigDecimal                productWeight;
    
    /**建档时间*/
    private java.lang.Long                      createTime;
    
    /**修改时间*/
    private java.lang.Long                      updateTime;
    
    /** 统计数量 */
    private String                              count            = "0";
    
    private PaginateResult<Map<String, Object>> subList;
    
    public java.lang.Integer getDealNo()
    {
        return this.dealNo;
    }
    
    public void setDealNo(java.lang.Integer dealNo)
    {
        this.dealNo = dealNo;
    }
    
    public java.lang.String getDealName()
    {
        return this.dealName;
    }
    
    public void setDealName(java.lang.String dealName)
    {
        this.dealName = dealName;
    }
    
    public java.lang.String getDealIcon()
    {
        return this.dealIcon;
    }
    
    public void setDealIcon(java.lang.String dealIcon)
    {
        this.dealIcon = dealIcon;
    }
    
    public java.lang.String getParentId()
    {
        return this.parentId;
    }
    
    public void setParentId(java.lang.String parentId)
    {
        this.parentId = parentId;
    }
    
    public java.lang.Short getDealLevel()
    {
        return this.dealLevel;
    }
    
    public void setDealLevel(java.lang.Short dealLevel)
    {
        this.dealLevel = dealLevel;
    }
    
    public java.lang.Integer getDealOrder()
    {
        return this.dealOrder;
    }
    
    public void setDealOrder(java.lang.Integer dealOrder)
    {
        this.dealOrder = dealOrder;
    }
    
    public java.lang.Integer getProductNum()
    {
        return this.productNum;
    }
    
    public void setProductNum(java.lang.Integer productNum)
    {
        this.productNum = productNum;
    }
    
    public java.math.BigDecimal getProductWeight()
    {
        return this.productWeight;
    }
    
    public void setProductWeight(java.math.BigDecimal productWeight)
    {
        this.productWeight = productWeight;
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
    
    public PaginateResult<Map<String, Object>> getSubList()
    {
        return subList;
    }
    
    public void setSubList(PaginateResult<Map<String, Object>> subList)
    {
        this.subList = subList;
    }
    
    public String getCount()
    {
        return count;
    }
    
    public void setCount(String count)
    {
        this.count = count;
    }
}
