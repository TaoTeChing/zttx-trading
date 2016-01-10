/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 投诉流水号 实体对象
 * <p>File：ComplainNumber.java</p>
 * <p>Title: ComplainNumber</p>
 * <p>Description:ComplainNumber</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ComplainNumber extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**投诉流水号*/
    private java.lang.Long    complainId;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getComplainId()
    {
        return complainId;
    }
    
    public ComplainNumber setComplainId(Long complainId)
    {
        this.complainId = complainId;
        return this;
    }
}
