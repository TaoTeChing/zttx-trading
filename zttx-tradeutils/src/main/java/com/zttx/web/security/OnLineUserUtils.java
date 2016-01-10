package com.zttx.web.security;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>File：OnLineUserUtils.java </p>
 * <p>Title: 在线用户工具类 </p>
 * <p>Description: OnLineUserUtils </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:50</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class OnLineUserUtils
{
    private static Logger logger = LoggerFactory.getLogger(OnLineUserUtils.class);
    
    /**
     * 获取当前登录经销商信息
     * @return
     */
    public static UserPrincipal getCurrentDealer() throws BusinessException
    {
        UserPrincipal userPrincipal = getPrincipal();
        if (null == userPrincipal || !userPrincipal.getUserType().equals(DealerConstant.userType.DEALER_TYPE)) { throw new BusinessException(CommonConst.DEALER_INFO_NULL); }
        return userPrincipal;
    }
    
    /**
     * h获取当前登录品牌商信息
     * @return
     */
    public static UserPrincipal getCurrentBrand() throws BusinessException
    {
        UserPrincipal userPrincipal = getPrincipal();
        if (null == userPrincipal || !userPrincipal.getUserType().equals(BrandConstant.userType.BRAND_TYPE)) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        return userPrincipal;
    }
    
    /**
     * 获取当前登录者对象
     */
    public static UserPrincipal getPrincipal()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Object object = subject.getPrincipal();
            if (null != object) { return (UserPrincipal) object; }
        }
        catch (UnavailableSecurityManagerException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        catch (InvalidSessionException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * 获取授权主要对象
     */
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }
    
    public static Session getSession()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null)
            {
                session = subject.getSession();
            }
            if (session != null) { return session; }
        }
        catch (InvalidSessionException e)
        {
        }
        return null;
    }
}
