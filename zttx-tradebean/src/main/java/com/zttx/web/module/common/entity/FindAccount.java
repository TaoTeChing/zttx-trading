/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.utils.ValidateUtils;

/**
 * 忘记登录账户 实体对象
 * <p>File：FindAccount.java</p>
 * <p>Title: FindAccount</p>
 * <p>Description:FindAccount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class FindAccount extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**账户类型
            0：品牌商
            1：终端商*/
    @NotNull(message = "请选择用户类型")
    @Range(min = UserAccountConst.BRAND, max = UserAccountConst.DEALER, message = "未知的账户类型")
    private Short             uesrType;
    
    /**真实姓名/法人代表*/
    @NotBlank(message = "真实姓名(法人代表) 不能为空")
    @Length(min = 2, max = 15, message = "真实姓名(法人代表)长度不符合")
    private String            realName;
    
    /**证件号码*/
    @NotBlank(message = "证件号码不能为空")
    private String            certNo;
    
    /**证件类型
            1：身份证
            2：营业执照
            3：其他*/
    @NotNull(message = "请选择证件类型")
    @Range(min = CommonConstant.FindAccountConstant.CART_ID, max = CommonConstant.FindAccountConstant.OTHER_ID, message = "未知的证件类型")
    private Short             certType;
    
    /**新手机号码*/
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = ValidateUtils.REGULAR_MOBILE, message = "手机号码的格式不正确")
    private String            userMobile;
    
    /**certPhoto*/
    @NotBlank(message = "证件图片不能为空")
    private String            certPhoto;
    
    /**新邮件地址*/
    private String            userEmail;
    
    /**提交时间*/
    private Long              createTime;
    
    /**审核人员编号*/
    private String            userId;
    
    /**审核时间*/
    private Long              checkTime;
    
    /**审核状态
            0：等待处理
            1：处理完成
            2：不处理/处理失败*/
    private Short             checkState;
    
    /**不通过原因*/
    private String            checkMark;
    
    public Short getUesrType()
    {
        return this.uesrType;
    }
    
    public void setUesrType(Short uesrType)
    {
        this.uesrType = uesrType;
    }
    
    public String getRealName()
    {
        return this.realName;
    }
    
    public void setRealName(String realName)
    {
        this.realName = realName;
    }
    
    public String getCertNo()
    {
        return this.certNo;
    }
    
    public void setCertNo(String certNo)
    {
        this.certNo = certNo;
    }
    
    public Short getCertType()
    {
        return this.certType;
    }
    
    public void setCertType(Short certType)
    {
        this.certType = certType;
    }
    
    public String getUserMobile()
    {
        return this.userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public String getCertPhoto()
    {
        return this.certPhoto;
    }
    
    public void setCertPhoto(String certPhoto)
    {
        this.certPhoto = certPhoto;
    }
    
    public String getUserEmail()
    {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
    
    public Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public String getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public Long getCheckTime()
    {
        return this.checkTime;
    }
    
    public void setCheckTime(Long checkTime)
    {
        this.checkTime = checkTime;
    }
    
    public Short getCheckState()
    {
        return this.checkState;
    }
    
    public void setCheckState(Short checkState)
    {
        this.checkState = checkState;
    }
    
    public String getCheckMark()
    {
        return this.checkMark;
    }
    
    public void setCheckMark(String checkMark)
    {
        this.checkMark = checkMark;
    }
}
