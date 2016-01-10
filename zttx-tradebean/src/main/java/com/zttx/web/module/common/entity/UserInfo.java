/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 用户信息 实体对象
 * <p>File：UserInfo.java</p>
 * <p>Title: UserInfo</p>
 * <p>Description:UserInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class UserInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**手机号码*/
    private java.lang.String  userMobile;
    
    /**手机认证状态*/
    private java.lang.Boolean mobileVerify;
    
    /**电子邮箱*/
    private java.lang.String  userMail;
    
    /**邮箱认证状态*/
    private java.lang.Boolean mailVerify;
    
    /**密码*/
    private java.lang.String  userPwd;
    
    /**密码盐值*/
    private java.lang.String  userSalt;
    
    /**角色*/
    private java.lang.String  roleId;
    
    /**角色**/
    private RoleInfo          roleInfo;
    
    /**帐号状态
    	0:等待审核
    	1:审核通过
    	2：冻结
    	3:审核不通过
    	*/
    private java.lang.Short   userState;
    
    /**主帐号编号*/
    private java.lang.String  parentId;
    
    /**名称*/
    private java.lang.String  userName;
    
    /**所在地区*/
    private java.lang.Integer areaNo;
    
    /**注册时间*/
    private java.lang.Long    registerTime;
    
    /**注册IP*/
    private java.lang.Integer registerIp;
    
    /**帐户类型：0 品牌商，1终端商*/
    private java.lang.Short   userType;
    
    /**上一次登录IP*/
    private java.lang.String  loginIpStr;
    
    /**注册IP*/
    private java.lang.String  registIpStr;
    
    /**用户类型：0、普通用户　1、代理商用户　2、内部用户*/
    private java.lang.Short   userSort;
    
    /**支付用户ID*/
    private java.lang.Long    payUserId;
    
    /**更新时间*/
    private java.lang.Long    updateTime;
    
    private Boolean           rememberMe       = Boolean.FALSE;
    
    private String            verifyCode;
    
    private String            captcha;
    
    // 省份
    private String            province;
    
    // 城市
    private String            city;
    
    // 地区
    private String            county;
    
    // 企业名称
    private String            comName;
    
    // 持有品牌
    private String            dealBrands;

     /*========================================= 临时字段 [@author易永耀] begin================================================*/
      private String brandName;    //品牌商姓名
      private String connectUserName;   //品牌商联系人
      private String dealerName; //经销商姓名


       /*========================================= 临时字段 end ================================================*/




    public UserInfo()
    {
    }

       /*========================================= 临时字段方法get/set [@author易永耀] begin================================================*/

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getConnectUserName() {
        return connectUserName;
    }

    public void setConnectUserName(String connectUserName) {
        this.connectUserName = connectUserName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    /*========================================= 临时字段方法get/set end ================================================*/

    public UserInfo(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public java.lang.String getUserMobile()
    {
        return this.userMobile;
    }
    
    public void setUserMobile(java.lang.String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public java.lang.Boolean getMobileVerify()
    {
        return this.mobileVerify;
    }
    
    public void setMobileVerify(java.lang.Boolean mobileVerify)
    {
        this.mobileVerify = mobileVerify;
    }
    
    public java.lang.String getUserMail()
    {
        return this.userMail;
    }
    
    public void setUserMail(java.lang.String userMail)
    {
        this.userMail = userMail;
    }
    
    public java.lang.Boolean getMailVerify()
    {
        return this.mailVerify;
    }
    
    public void setMailVerify(java.lang.Boolean mailVerify)
    {
        this.mailVerify = mailVerify;
    }
    
    public java.lang.String getUserPwd()
    {
        return this.userPwd;
    }
    
    public void setUserPwd(java.lang.String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public java.lang.String getUserSalt()
    {
        return this.userSalt;
    }
    
    public void setUserSalt(java.lang.String userSalt)
    {
        this.userSalt = userSalt;
    }
    
    public java.lang.String getRoleId()
    {
        return this.roleId;
    }
    
    public void setRoleId(java.lang.String roleId)
    {
        this.roleId = roleId;
    }
    
    public java.lang.Short getUserState()
    {
        return this.userState;
    }
    
    public void setUserState(java.lang.Short userState)
    {
        this.userState = userState;
    }
    
    public java.lang.String getParentId()
    {
        return this.parentId;
    }
    
    public void setParentId(java.lang.String parentId)
    {
        this.parentId = parentId;
    }
    
    public Boolean getRememberMe()
    {
        return rememberMe;
    }
    
    public void setRememberMe(Boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }
    
    public java.lang.String getUserName()
    {
        return this.userName;
    }
    
    public void setUserName(java.lang.String userName)
    {
        this.userName = userName;
    }
    
    public java.lang.Integer getAreaNo()
    {
        return this.areaNo;
    }
    
    public void setAreaNo(java.lang.Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public java.lang.Long getRegisterTime()
    {
        return this.registerTime;
    }
    
    public void setRegisterTime(java.lang.Long registerTime)
    {
        this.registerTime = registerTime;
    }
    
    public java.lang.Integer getRegisterIp()
    {
        return this.registerIp;
    }
    
    public void setRegisterIp(java.lang.Integer registerIp)
    {
        this.registerIp = registerIp;
    }
    
    public java.lang.Short getUserType()
    {
        return this.userType;
    }
    
    public void setUserType(java.lang.Short userType)
    {
        this.userType = userType;
    }
    
    public java.lang.String getLoginIpStr()
    {
        return this.loginIpStr;
    }
    
    public void setLoginIpStr(java.lang.String loginIpStr)
    {
        this.loginIpStr = loginIpStr;
    }
    
    public java.lang.String getRegistIpStr()
    {
        return this.registIpStr;
    }
    
    public void setRegistIpStr(java.lang.String registIpStr)
    {
        this.registIpStr = registIpStr;
    }
    
    public java.lang.Short getUserSort()
    {
        return this.userSort;
    }
    
    public void setUserSort(java.lang.Short userSort)
    {
        this.userSort = userSort;
    }
    
    public java.lang.Long getPayUserId()
    {
        return this.payUserId;
    }
    
    public void setPayUserId(java.lang.Long payUserId)
    {
        this.payUserId = payUserId;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public RoleInfo getRoleInfo()
    {
        return roleInfo;
    }
    
    public void setRoleInfo(RoleInfo roleInfo)
    {
        this.roleInfo = roleInfo;
    }
    
    public String getCounty()
    {
        return county;
    }
    
    public UserInfo setCounty(String county)
    {
        this.county = county;
        return this;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public UserInfo setCity(String city)
    {
        this.city = city;
        return this;
    }
    
    public String getProvince()
    {
        return province;
    }
    
    public UserInfo setProvince(String province)
    {
        this.province = province;
        return this;
    }
    
    public String getCaptcha()
    {
        return captcha;
    }
    
    public UserInfo setCaptcha(String captcha)
    {
        this.captcha = captcha;
        return this;
    }
    
    public String getVerifyCode()
    {
        return verifyCode;
    }
    
    public UserInfo setVerifyCode(String verifyCode)
    {
        this.verifyCode = verifyCode;
        return this;
    }
    
    public String getComName()
    {
        return comName;
    }
    
    public UserInfo setComName(String comName)
    {
        this.comName = comName;
        return this;
    }
    
    public String getDealBrands()
    {
        return dealBrands;
    }
    
    public UserInfo setDealBrands(String dealBrands)
    {
        this.dealBrands = dealBrands;
        return this;
    }
}
