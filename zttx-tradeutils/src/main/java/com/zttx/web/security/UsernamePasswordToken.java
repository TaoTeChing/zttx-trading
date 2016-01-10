package com.zttx.web.security;

/**
 * <p>File：UsernamePasswordToken.java </p>
 * <p>Title:  用户和密码（包含验证码）令牌类 </p>
 * <p>Description: UsernamePasswordToken </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 09:55</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
    private static final long serialVersionUID = 1L;

    private String captcha;

    // 免登陆标识
    private Boolean crm;

    private Boolean erp;

    // 免登陆用户ID
    private String crm_id;

    // 免登陆用户名称
    private String crm_name;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, char[] password, Boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    /**
     * 供 erp 免登陆用户使用
     *
     * @param username
     * @param password
     * @param host
     * @param erp
     */
    public UsernamePasswordToken(String username, char[] password, String host, Boolean erp) {
        super(username, password, false, host);
        this.erp = erp;
    }

    /**
     * 供 CRM 免登陆用户使用
     *
     * @param username
     * @param password
     * @param host
     * @param crm
     * @param crm_id
     * @param crm_name
     */
    public UsernamePasswordToken(String username, char[] password, String host, Boolean crm, String crm_id, String crm_name) {
        super(username, password, false, host);
        this.crm = crm;
        this.crm_id = crm_id;
        this.crm_name = crm_name;
    }

    public Boolean getErp() {
        return erp;
    }

    public void setErp(Boolean erp) {
        this.erp = erp;
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

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}