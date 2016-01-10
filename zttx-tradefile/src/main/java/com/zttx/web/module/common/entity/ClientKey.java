/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * API接入码 实体对象
 * <p>File：ClientKey.java</p>
 * <p>Title: ClientKey</p>
 * <p>Description:ClientKey</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class ClientKey extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**
     * 接入类型
     * 001 交易平台
     * 002 文件服务器
     */
    private String            accessType;
    
    /**认证钥匙*/
    private String            userKey;
    
    /**白名单限制*/
    private Boolean           userLimit;
    
    /**申请时间*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    /**申请者IP*/
    private Integer           createIp;
    
    /**审核状态（0：未审核，1：审核通过，2：审核不通过)*/
    private Short             checkState;
    
    public String getAccessType()
    {
        return this.accessType;
    }
    
    public void setAccessType(String accessType)
    {
        this.accessType = accessType;
    }
    
    public String getUserKey()
    {
        return this.userKey;
    }
    
    public void setUserKey(String userKey)
    {
        this.userKey = userKey;
    }
    
    public Boolean getUserLimit()
    {
        return this.userLimit;
    }
    
    public void setUserLimit(Boolean userLimit)
    {
        this.userLimit = userLimit;
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
    
    public Integer getCreateIp()
    {
        return this.createIp;
    }
    
    public void setCreateIp(Integer createIp)
    {
        this.createIp = createIp;
    }
    
    public Short getCheckState()
    {
        return this.checkState;
    }
    
    public void setCheckState(Short checkState)
    {
        this.checkState = checkState;
    }
}
