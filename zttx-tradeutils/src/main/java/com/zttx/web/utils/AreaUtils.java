package com.zttx.web.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/*
 *
 * @(#)AreaUtils 14-3-14 上午10:59
 * Copyright ${year} 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
public abstract class AreaUtils
{
    /**
     * 将区域对象转换成JSON字符串
     * @param regionalMap
     * @return
     */
    public static final String getRegionalJsonStr(Map<String, Object> regionalMap)
    {
        return JSON.toJSONString(regionalMap);
    }
}
