package com.zttx.web.security.shiro.model;

import java.io.Serializable;

/**
 * <p>File：UserPrincipal.java </p>
 * <p>Title: UserPrincipal </p>
 * <p>Description: UserPrincipal </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:41</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class UserPrincipal implements Serializable {
    private static final long serialVersionUID = 191434150385861991L;

    // 免登陆标识
    private Boolean crm = Boolean.FALSE;

    // 免登陆用户ID
    private String crm_id;

    // 免登陆用户名称
    private String crm_name;

    /**
     * 主键编号
     */
    private String refrenceId;

    /**
     * 名称
     */
    private String userName;

    /**
     * 公司名称
     */
    private String comName;

    /**
     * 电子邮箱
     */
    private String userMail;

    /**
     * 手机号码
     */
    private String userMobile;

    /**
     * 帐户类型：0 品牌商，1终端商
     */
    private Short userType;

    /**
     * 密码盐值
     */
    private String userSalt;

    /**
     * 用户密码
     */
    private String userPass;

    /**
     * default constructor
     *
     * @param refrenceId
     * @param userName
     * @param userMail
     * @param userMobile
     * @param userType
     * @param userSalt
     */
    public UserPrincipal(String refrenceId, String userName, String userMail, String userMobile,String userPass, Short userType, String userSalt) {
        this.refrenceId = refrenceId;
        this.userName = userName;
        this.userMail = userMail;
        this.userMobile = userMobile;
        this.userPass = userPass;
        this.userType = userType;
        this.userSalt = userSalt;
    }

    /**
     * crm constructor
     *
     * @param refrenceId
     * @param userName
     * @param userMail
     * @param userMobile
     * @param userType
     * @param userSalt
     */
    public UserPrincipal(String refrenceId, String userName, String userMail, String userMobile, Short userType, String userSalt, Boolean crm, String crm_id,
                         String crm_name) {
        this.refrenceId = refrenceId;
        this.userName = userName;
        this.userMail = userMail;
        this.userMobile = userMobile;
        this.userType = userType;
        this.userSalt = userSalt;
        this.crm = crm;
        this.crm_id = crm_id;
        this.crm_name = crm_name;
    }

    /**
     * full constructor
     *
     * @param refrenceId
     * @param userName
     * @param comName
     * @param userMail
     * @param userMobile
     * @param userType
     * @param userSalt
     * @param crm
     * @param crm_id
     * @param crm_name
     */
    public UserPrincipal(String refrenceId, String userName, String comName, String userMail, String userMobile, Short userType, String userSalt, Boolean crm,
                         String crm_id, String crm_name) {
        this.crm = crm;
        this.crm_id = crm_id;
        this.crm_name = crm_name;
        this.refrenceId = refrenceId;
        this.userName = userName;
        this.comName = comName;
        this.userMail = userMail;
        this.userMobile = userMobile;
        this.userType = userType;
        this.userSalt = userSalt;
    }

    public UserPrincipal(String refrenceId, String userName, String userMail, String userMobile, Short userType) {
        this.refrenceId = refrenceId;
        this.userName = userName;
        this.userMail = userMail;
        this.userMobile = userMobile;
        this.userType = userType;
    }

    public Boolean getCrm() {
        return crm;
    }

    public void setCrm(Boolean crm) {
        this.crm = crm;
    }

    public String getCrm_id() {
        return crm_id;
    }

    public void setCrm_id(String crm_id) {
        this.crm_id = crm_id;
    }

    public String getCrm_name() {
        return crm_name;
    }

    public void setCrm_name(String crm_name) {
        this.crm_name = crm_name;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getRefrenceId() {
        return refrenceId;
    }

    public void setRefrenceId(String refrenceId) {
        this.refrenceId = refrenceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
