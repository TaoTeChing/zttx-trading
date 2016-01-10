/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.sdk.utils.ValidateUtils;

/**
 * 加盟入驻信息 实体对象
 * <p>File：JoinInfo.java</p>
 * <p>Title: JoinInfo</p>
 * <p>Description:JoinInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class JoinInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**联系人员*/
    @NotBlank(message = "联系人不能为空")
    private String            userName;
    
    /**联系人性别*/
    @NotBlank(message = "联系人性别不能为空")
    private String            userGender;
    
    /**邮箱地址*/
    private String            userMail;
    
    /**手机号码*/
    @Pattern(regexp = ValidateUtils.REGULAR_MOBILE, message = "手机号码格式不正确")
    @NotBlank(message = "手机号码不能为空")
    private String            userMobile;
    
    /**联系电话*/
    private String            userTelphone;
    
    /**传真号码*/
    private String            userFax;
    
    /**所在区域*/
    @NotNull(message = "地址不能为空")
    private Integer           areaNo;
    
    /**留言内容*/
    private String            joinMark;
    
    /**加盟类型
            0：服务商
            1：工厂店*/
    @NotNull(message = "加盟类型不能为空")
    private Short             joinType;
    
    /**建档时间*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    /**建档者IP*/
    private Integer           createIp;
    
    public String getUserName()
    {
        return this.userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getUserGender()
    {
        return this.userGender;
    }
    
    public void setUserGender(String userGender)
    {
        this.userGender = userGender;
    }
    
    public String getUserMail()
    {
        return this.userMail;
    }
    
    public void setUserMail(String userMail)
    {
        this.userMail = userMail;
    }
    
    public String getUserMobile()
    {
        return this.userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public String getUserTelphone()
    {
        return this.userTelphone;
    }
    
    public void setUserTelphone(String userTelphone)
    {
        this.userTelphone = userTelphone;
    }
    
    public String getUserFax()
    {
        return this.userFax;
    }
    
    public void setUserFax(String userFax)
    {
        this.userFax = userFax;
    }
    
    public Integer getAreaNo()
    {
        return this.areaNo;
    }
    
    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public String getJoinMark()
    {
        return this.joinMark;
    }
    
    public void setJoinMark(String joinMark)
    {
        this.joinMark = joinMark;
    }
    
    public Short getJoinType()
    {
        return this.joinType;
    }
    
    public void setJoinType(Short joinType)
    {
        this.joinType = joinType;
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
}
