/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.module.common.entity.DealDic;

/**
 * 品牌主营品类 实体对象
 * <p>File：BrandDeal.java</p>
 * <p>Title: BrandDeal</p>
 * <p>Description:BrandDeal</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandDeal extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**品牌商编号*/
    private java.lang.String  brandId;
    
    /**品牌编号*/
    private java.lang.String  brandesId;
    
    /**品类编码*/
    private java.lang.Integer dealNo;
    
    /**品类编码*/
    private java.lang.String  dealName;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**修改时间*/
    private java.lang.Long    updateTime;
    
    /**建档者IP*/
    private java.lang.Integer createIp;
    
    private DealDic           dealDic;
    
    protected String          dealMain;             // 产品类目
    
    public java.lang.String getBrandId()
    {
        return this.brandId;
    }
    
    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }
    
    public java.lang.String getBrandesId()
    {
        return this.brandesId;
    }
    
    public void setBrandesId(java.lang.String brandesId)
    {
        this.brandesId = brandesId;
    }
    
    public java.lang.Integer getDealNo()
    {
        return this.dealNo;
    }
    
    public void setDealNo(java.lang.Integer dealNo)
    {
        this.dealNo = dealNo;
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
    
    public java.lang.Integer getCreateIp()
    {
        return this.createIp;
    }
    
    public void setCreateIp(java.lang.Integer createIp)
    {
        this.createIp = createIp;
    }
    
    public DealDic getDealDic()
    {
        return dealDic;
    }
    
    public void setDealDic(DealDic dealDic)
    {
        this.dealDic = dealDic;
    }
    
    public String getDealMain()
    {
        return dealMain;
    }
    
    public void setDealMain(String dealMain)
    {
        this.dealMain = dealMain;
    }
    
    public String getDealName()
    {
        return dealName;
    }
    
    public void setDealName(String dealName)
    {
        this.dealName = dealName;
    }
}
