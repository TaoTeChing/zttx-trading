/*
 * @(#)CustomerService.java 2014-7-4 下午1:49:38
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * <p>File：CustomerService.java</p>
 * <p>Title: 客服(用于接收第三方参数的对象)</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-4 下午1:49:38</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class Customer implements Serializable
{
    //
    private static final long serialVersionUID = 5644231390194016310L;
    
    // 品牌商或者经销商ID
    @NotBlank(message = "客户ID不能为空")
    @Length(max = 32, message = "客户ID长度过长")
    private String            refrenceId;
    
    // 客服ID
    @NotBlank(message = "客服ID不能为空")
    @Length(max = 32, min = 32, message = "客服ID长度过长")
    private String            employeId;
    
    // 工号
    @Length(max = 4, message = "工号的长度过长")
    private String            jobNum;
    
    // 客服姓名
    @NotBlank(message = "客服姓名不能为空")
    @Length(max = 100, message = "客服姓名长度过长")
    private String            name;
    
    // 0：品牌商 1：经销商
    private Short             type;
    
    // 0:男 1：女
    @Range(min = 0, max = 1, message = "未知性别")
    private Short             sex;
    
    // 分机号
    // @Pattern(regexp=ValidateUtils.REGULAR_PHONE)
    private String            telPhoneSystemNumber;
    
    // 手机号码
    // @Pattern(regexp=ValidateUtils.REGULAR_MOBILE)
    private String            mobile;
    
    // 头像图片
    @Length(max = 128, message = "头像图片地址过长")
    private String            mainPhotoPath;
    
    // 创建时间
    private String            createTime;
    
    private Short             serviceCate;
    
    public String getRefrenceId()
    {
        return refrenceId;
    }
    
    public void setRefrenceId(String refrenceId)
    {
        this.refrenceId = refrenceId;
    }
    
    public String getEmployeId()
    {
        return employeId;
    }
    
    public void setEmployeId(String employeId)
    {
        this.employeId = employeId;
    }
    
    public String getJobNum()
    {
        return jobNum;
    }
    
    public void setJobNum(String jobNum)
    {
        this.jobNum = jobNum;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Short getType()
    {
        return type;
    }
    
    public void setType(Short type)
    {
        this.type = type;
    }
    
    public Short getSex()
    {
        return sex;
    }
    
    public void setSex(Short sex)
    {
        this.sex = sex;
    }
    
    public String getTelPhoneSystemNumber()
    {
        return telPhoneSystemNumber;
    }
    
    public void setTelPhoneSystemNumber(String telPhoneSystemNumber)
    {
        this.telPhoneSystemNumber = telPhoneSystemNumber;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getMainPhotoPath()
    {
        return mainPhotoPath;
    }
    
    public void setMainPhotoPath(String mainPhotoPath)
    {
        this.mainPhotoPath = mainPhotoPath;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    public Short getServiceCate()
    {
        return serviceCate;
    }
    
    public void setServiceCate(Short serviceCate)
    {
        this.serviceCate = serviceCate;
    }
}
