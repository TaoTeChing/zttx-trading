package com.zttx.web.security;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.MenuInfo;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.MenuInfoService;
import com.zttx.web.module.common.service.RoleInfoService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.security.shiro.session.CustomSessionDAO;
import com.zttx.web.utils.UserCenterClient;

/**
 * <p>File：UserAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: UserAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class UserAuthorizingRealm extends AuthorizingRealm
{
    @Autowired(required = false)
    private UserInfoService  userInfoService;
    
    @Autowired(required = false)
    private MenuInfoService  menuInfoService;
    
    @Autowired(required = false)
    private RoleInfoService  roleInfoService;
    
    @Autowired(required = false)
    private UserCenterClient userCenterClient;
    
    /**
     * 认证回调函数, 登录时调用
     *
     * @param authcToken
     * @return {@link AuthenticationInfo}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        try
        {
            UserPrincipal userPrincipal;
            if (null != token.getCrm() && token.getCrm())
            {
                UserInfo userInfo = userInfoService.loginVerifi(token.getUsername());
                userPrincipal = new UserPrincipal(userInfo.getRefrenceId(), userInfo.getUserName(), userInfo.getUserMail(), userInfo.getUserMobile(),
                        userInfo.getUserType(), userInfo.getUserSalt(), token.getCrm(), token.getCrm_id(), token.getCrm_name());
                return new SimpleAuthenticationInfo(userPrincipal, userInfo.getUserPwd(), getName());
            }
            else if (null != token.getErp() && token.getErp())
            {
                UserInfo userInfo = userInfoService.loginVerifi(token.getUsername());
                userPrincipal = new UserPrincipal(userInfo.getRefrenceId(), userInfo.getUserName(), userInfo.getUserMail(), userInfo.getUserMobile(),
                        userInfo.getUserType(), userInfo.getUserSalt(), token.getCrm(), token.getCrm_id(), token.getCrm_name());
                return new SimpleAuthenticationInfo(userPrincipal, userInfo.getUserPwd(), getName());
            }
            else
            {
                CenterUser centerUser = userCenterClient.login(token.getUsername(), String.valueOf(token.getPassword()));
                if (null != centerUser)
                {
                    UserInfo userInfo = userInfoService.loginVerifi(centerUser.getUserName());
                    if (userInfo != null)
                    {
                        userPrincipal = new UserPrincipal(userInfo.getRefrenceId(), userInfo.getUserName(), userInfo.getUserMail(), userInfo.getUserMobile(),
                                String.valueOf(token.getPassword()), userInfo.getUserType(), userInfo.getUserSalt());
                        return new SimpleAuthenticationInfo(userPrincipal, centerUser.getPassword(), getName());
                    }
                }
            }
        }
        catch (BusinessException e)
        {
        }
        return null;
    }
    
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principals
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        UserPrincipal principal = (UserPrincipal) getAvailablePrincipal(principals);
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
                if (StringUtils.isNotBlank(info.getAuthority()))
                {
                    authorizationInfo.addStringPermission(info.getAuthority());
                }
            }
            return authorizationInfo;
        }
        return null;
    }
}
