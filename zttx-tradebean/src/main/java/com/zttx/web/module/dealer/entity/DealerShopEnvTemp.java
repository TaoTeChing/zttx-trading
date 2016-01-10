/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销店铺 临时信息 实体对象
 * <p>File：DealerShopEnvTemp.java</p>
 * <p>Title: DealerShopEnvTemp</p>
 * <p>Description:DealerShopEnvTemp</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerShopEnvTemp extends GenericEntity
{
    private static final long          serialVersionUID = 1L;
    
    /**createTime*/
    private Long                       createTime;
    
    /**updateTime*/
    private Long                       updateTime;
    
    /**0:未处理 1:已处理*/
    private Integer                    status;
    
    /**detail*/
    private String                     detail;
    
    /**auditUser*/
    private String                     auditUser;
    
    /**auditTime*/
    private Long                       auditTime;
    
    /**auditIp*/
    private String                     auditIp;
    
    private List<DealerShopEnvImgTemp> dealerShopEnvImgTemps;
    
    private Object                     detailObj;
    
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
    
    public Integer getStatus()
    {
        return this.status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getDetail()
    {
        return this.detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public String getAuditUser()
    {
        return this.auditUser;
    }
    
    public void setAuditUser(String auditUser)
    {
        this.auditUser = auditUser;
    }
    
    public Long getAuditTime()
    {
        return this.auditTime;
    }
    
    public void setAuditTime(Long auditTime)
    {
        this.auditTime = auditTime;
    }
    
    public String getAuditIp()
    {
        return this.auditIp;
    }
    
    public void setAuditIp(String auditIp)
    {
        this.auditIp = auditIp;
    }
    
    public List<DealerShopEnvImgTemp> getDealerShopEnvImgTemps()
    {
        return dealerShopEnvImgTemps;
    }
    
    public DealerShopEnvTemp setDealerShopEnvImgTemps(List<DealerShopEnvImgTemp> dealerShopEnvImgTemps)
    {
        this.dealerShopEnvImgTemps = dealerShopEnvImgTemps;
        return this;
    }
    
    public Object getDetailObj()
    {
        return detailObj;
    }
    
    public DealerShopEnvTemp setDetailObj(Object detailObject)
    {
        this.detailObj = detailObject;
        return this;
    }
}
