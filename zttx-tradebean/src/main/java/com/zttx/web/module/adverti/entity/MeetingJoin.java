/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.adverti.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 交易会活动报名 实体对象
 * <p>File：MeetingJoin.java</p>
 * <p>Title: MeetingJoin</p>
 * <p>Description:MeetingJoin</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class MeetingJoin extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**分期编号*/
    private String            stageCode;
    
    /**真实姓名*/
    private String            realName;
    
    /**手机号码*/
    private String            mobile;
    
    /** 经营类目*/
    private String            businessCategory;
    
    /**用户类型：0 品牌商报名 ；1 终端商报名*/
    private Short             userType;
    
    /**创建时间*/
    private Long              createTime;
    
    public String getStageCode()
    {
        return this.stageCode;
    }
    
    public void setStageCode(String stageCode)
    {
        this.stageCode = stageCode;
    }
    
    public String getRealName()
    {
        return this.realName;
    }
    
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    
    public String getMobile()
    {
        return this.mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public Short getUserType()
    {
        return this.userType;
    }
    
    public void setUserType(Short userType)
    {
        this.userType = userType;
    }
    
    public Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public String getBusinessCategory()
    {
        return businessCategory;
    }
    
    public void setBusinessCategory(String businessCategory)
    {
        this.businessCategory = businessCategory;
    }
}
