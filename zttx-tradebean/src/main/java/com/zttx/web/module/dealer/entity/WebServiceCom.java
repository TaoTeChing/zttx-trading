/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.sdk.utils.ValidateUtils;

/**
 * 网站服务商 实体对象
 * <p>File：WebServiceCom.java</p>
 * <p>Title: WebServiceCom</p>
 * <p>Description:WebServiceCom</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class WebServiceCom extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**服务商名称*/
    @Length(max = 64, message = "服务商名称长度不能超过64位")
    @NotBlank(message = "服务商名称不能为空")
    private String            comName;
    
    /**服务商图片*/
    private String            comPhoto;
    
    /**资质认证*/
    @Length(max = 64, message = "资质认证长度不能超过64位")
    @NotBlank(message = "资质认证不能为空")
    private String            comCert;
    
    /**联系电话*/
    @Pattern(regexp = ValidateUtils.REGULAR_PHONE, message = "联系电话格式不正确")
    private String            comTel;
    
    /**联系邮箱*/
    @Email(message = "联系邮箱格式不正确")
    @NotBlank(message = "联系邮箱不能为空")
    private String            comEmail;
    
    /**联系手机*/
    @Pattern(regexp = ValidateUtils.REGULAR_MOBILE, message = "联系手机格式不正确")
    @NotBlank(message = "联系手机不能为空")
    private String            comMobile;
    
    /**服务商简介*/
    private String            subMark;
    
    /**createTime*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    public String getComName()
    {
        return this.comName;
    }
    
    public void setComName(String comName)
    {
        this.comName = comName;
    }
    
    public String getComPhoto()
    {
        return this.comPhoto;
    }
    
    public void setComPhoto(String comPhoto)
    {
        this.comPhoto = comPhoto;
    }
    
    public String getComCert()
    {
        return this.comCert;
    }
    
    public void setComCert(String comCert)
    {
        this.comCert = comCert;
    }
    
    public String getComTel()
    {
        return this.comTel;
    }
    
    public void setComTel(String comTel)
    {
        this.comTel = comTel;
    }
    
    public String getComEmail()
    {
        return this.comEmail;
    }
    
    public void setComEmail(String comEmail)
    {
        this.comEmail = comEmail;
    }
    
    public String getComMobile()
    {
        return this.comMobile;
    }
    
    public void setComMobile(String comMobile)
    {
        this.comMobile = comMobile;
    }
    
    public String getSubMark()
    {
        return this.subMark;
    }
    
    public void setSubMark(String subMark)
    {
        this.subMark = subMark;
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
}
