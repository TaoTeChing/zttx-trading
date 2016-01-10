package com.zttx.web.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.service.MenuInfoService;
import com.zttx.web.module.common.service.RoleInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zttx.sdk.bean.PropertiesLoader;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.security.shiro.session.CustomSessionDAO;

/**
 * <p>File：CasAuthorizingRealm.java </p>
 * <p>Title: CasAuthorizingRealm </p>
 * <p>Description: CasAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 15/9/12</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class CasAuthorizingRealm extends CasRealm
{
    /**属性文件加载对象*/
    private static final PropertiesLoader props  = new PropertiesLoader("cas.properties");
    
    private static final Logger           logger = LoggerFactory.getLogger(CasAuthorizingRealm.class);
    
    private CustomSessionDAO              sessionDAO;
    
    private UserInfoService               userInfoService;
    
    private MenuInfoService               menuInfoService;
    
    private RoleInfoService               roleInfoService;
    
    public CasAuthorizingRealm()
    {
        super();
    }
    
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        CasToken casToken = (CasToken) token;
        if (token == null) return null;
        String ticket = (String) casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) return null;
        TicketValidator ticketValidator = ensureTicketValidator();
        try
        {
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String userId = casPrincipal.getName();
            Map<String, Object> attributes = casPrincipal.getAttributes();
            casToken.setUserId(userId);
            String rememberMeAttributeName = getRememberMeAttributeName();
            String rememberMeStringValue = (String) attributes.get(rememberMeAttributeName);
            if (rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue)) casToken.setRememberMe(true);
            logger.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{ticket, getCasServerUrlPrefix(), userId});
            // create simple authentication info
            UserInfo userInfo = userInfoService.loginVerifi(userId);
            UserPrincipal userPrincipal = new UserPrincipal(userInfo.getRefrenceId(), userInfo.getUserName(), userInfo.getUserMail(), userInfo.getUserMobile(),
                    userInfo.getUserType());
            return new SimpleAuthenticationInfo(userPrincipal, ticket, userInfo.getUserName());
        }
        catch (Exception e)
        {
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
    }
    
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        UserPrincipal principal = (UserPrincipal) getAvailablePrincipal(principals);
        String allow = props.getProperty("multiAccountLogin");
        // 获取当前已登录的用户
        if (ZttxConst.FALSE.equals(allow))
        {
            Collection<Session> sessions = sessionDAO.getActiveSessions(true, principal, OnLineUserUtils.getSession());
            if (sessions.size() > 0)
            { // 如果是登录进来的，则踢出已在线用户
                if (OnLineUserUtils.getSubject().isAuthenticated())
                {
                    for (Session session : sessions)
                        sessionDAO.delete(session);
                }
                else
                { // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                    OnLineUserUtils.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }
            }
        }
        UserInfo userInfo = userInfoService.loginVerifi(principal.getUserMobile());
        if (userInfo != null)
        {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            String roleId = userInfo.getRoleId();
            if (roleId == null) { return null; }
            RoleInfo role = roleInfoService.selectByPrimaryKey(roleId);
            authorizationInfo.addRole(role.getRoleCode());
            List<MenuInfo> menuList = menuInfoService.findMenuByRoleId(roleId);
            for (MenuInfo info : menuList)
            {
                if (com.zttx.sdk.utils.StringUtils.isNotBlank(info.getAuthority()))
                {
                    authorizationInfo.addStringPermission(info.getAuthority());
                }
            }
            return authorizationInfo;
        }
        return null;
    }
    
    public void setSessionDAO(CustomSessionDAO sessionDAO)
    {
        this.sessionDAO = sessionDAO;
    }
    
    public void setUserInfoService(UserInfoService userInfoService)
    {
        this.userInfoService = userInfoService;
    }
    
    public void setMenuInfoService(MenuInfoService menuInfoService)
    {
        this.menuInfoService = menuInfoService;
    }
    
    public void setRoleInfoService(RoleInfoService roleInfoService)
    {
        this.roleInfoService = roleInfoService;
    }
}
