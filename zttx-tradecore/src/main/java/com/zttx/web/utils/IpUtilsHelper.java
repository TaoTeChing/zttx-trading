/*
 * @(#)IpUtilsHelper.java 2015-8-18 下午2:34:59
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.service.RegionalService;


/**
 * <p>File：IpUtilsHelper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-18 下午2:34:59</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class IpUtilsHelper
{
    private static RegionalService regionalService = SpringUtils.getBean(RegionalService.class);
    /**
     * 获取用户所在地区
     * @param request
     * @param level 0:省, 1:市，2:区
     * @return
     * @author 张昌苗
     */
    public static Integer getAreaNo(HttpServletRequest request, int level)
    {
        Regional regional = regionalService.getRegionalByRequest(request);
        if (regional != null)
        {
            if (level == 0) { return regional.getAreaNo() / 10000 * 10000; }
            if (level == 1) { return regional.getAreaNo() / 100 * 100; }
        }
        return 0;
    }
}
