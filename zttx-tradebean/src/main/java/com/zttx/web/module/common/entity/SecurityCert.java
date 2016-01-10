/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.consts.CommonConstant;

/**
 * 经销商/品牌商申请更改手机认证 实体对象
 * <p>File：SecurityCert.java</p>
 * <p>Title: SecurityCert</p>
 * <p>Description:SecurityCert</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class SecurityCert extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**经销商/品牌商主帐号编号*/
	@NotBlank(message = "主帐号编号不能为空")
	private String userIdId;
	/**经销商/品牌商名称*/
	private String userName;
	/**账户类型
            0：品牌商，1：经销商*/
	@NotNull(message = "账户类型不能为空")
	private Short userCate;
	/**原手机号码*/
	private String oldMobile;
	/**修改的手机号码*/
	private String userMobile;
	/**身份证证件正面照片*/
	private String userPhoto;
	/**营业执照图片*/
	private String certPhoto;
	/**申请类型：1:更换手机号码*/
	@NotNull(message = "申请类型不能为空")
	private Short applyType;
	/**申请时间*/
	private Long createTime;
	/**创建IP*/
	private Integer createIp;
	/**操作结果*/
	@NotNull(message = "操作结果不能为空")
	@Range(min = CommonConstant.SecurityCert.NONE_AUDIT, max = CommonConstant.SecurityCert.NO_PASS_AUDIT, message = "操作结果类型不合法")
	private Short actState;
	/**操作人员(CRM系统员工编号）*/
	@NotBlank(message = "操作人员不能为空")
	private String userId;
	/**操作时间*/
	private Long actionDate;
	/**操作人IP*/
	@NotNull(message = "操作人IP不能为空")
	private Integer actionIp;

	public String getUserIdId()
	{
		return this.userIdId;
	}

	public void setUserIdId(String userIdId)
	{
		this.userIdId = userIdId;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Short getUserCate()
	{
		return this.userCate;
	}

	public void setUserCate(Short userCate)
	{
		this.userCate = userCate;
	}

	public String getOldMobile()
	{
		return this.oldMobile;
	}

	public void setOldMobile(String oldMobile)
	{
		this.oldMobile = oldMobile;
	}

	public String getUserMobile()
	{
		return this.userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public String getUserPhoto()
	{
		return this.userPhoto;
	}

	public void setUserPhoto(String userPhoto)
	{
		this.userPhoto = userPhoto;
	}

	public String getCertPhoto()
	{
		return this.certPhoto;
	}

	public void setCertPhoto(String certPhoto)
	{
		this.certPhoto = certPhoto;
	}

	public Short getApplyType()
	{
		return this.applyType;
	}

	public void setApplyType(Short applyType)
	{
		this.applyType = applyType;
	}

	public Long getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	public Integer getCreateIp()
	{
		return this.createIp;
	}

	public void setCreateIp(Integer createIp)
	{
		this.createIp = createIp;
	}

	public Short getActState()
	{
		return this.actState;
	}

	public void setActState(Short actState)
	{
		this.actState = actState;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public Long getActionDate()
	{
		return this.actionDate;
	}

	public void setActionDate(Long actionDate)
	{
		this.actionDate = actionDate;
	}

	public Integer getActionIp()
	{
		return this.actionIp;
	}

	public void setActionIp(Integer actionIp)
	{
		this.actionIp = actionIp;
	}
	
}

