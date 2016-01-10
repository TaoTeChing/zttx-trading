package com.zttx.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 夏铭
 * @version 1.0
 * @since 2014 2014/11/13 9:45
 */
public abstract class UrlUtils
{
    public static String getFullRequestUrl(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort())
                .append(request.getContextPath()).append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(queryString))
        {
            sb.append("?").append(queryString);
        }
        return sb.toString();
    }
}
