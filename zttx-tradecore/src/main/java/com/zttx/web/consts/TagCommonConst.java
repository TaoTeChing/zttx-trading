/*
 * @(#)TagCommonConst.java 2014-3-31 上午9:41:17
 * Copyright 2014 罗盛平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.web.module.common.service.RegionalService;
import com.zttx.sdk.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>File：TagCommonConst.java</p>
 * <p>Title: 标签公共类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-31 上午9:41:17</p>
 * <p>Company: 8637.com</p>
 * @author 罗盛平
 * @version 1.0
 */
public class TagCommonConst
{
    private static RegionalService regionalService = SpringUtils.getBean(RegionalService.class);

    /**
     * 传入省市区  用分隔符分割 获取区域全名
     * @param province 省
     * @param city 市
     * @param area 区
     * @param splitTag  分隔符
     * @return 比如 浙江省/宁波市/鄞州区
     * @author 施建波
     */
    public static String getFullArea(Object province, Object city, Object area, String splitTag)
    {
        return getFullAreaChange(province, city, area, splitTag);
    }
    
    /**
     * 传入省市区  用分隔符分割 获取区域全名
     * @param province 省
     * @param city 市
     * @param area 区
     * @param splitTag  分隔符
     * @return 比如 浙江省/宁波市/鄞州区
     * @author 罗盛平
     */
    public static String getFullArea(String province, String city, String area, String splitTag)
    {
        return getFullAreaChange(province, city, area, splitTag);
    }
    
    public static String getFullAreaChange(Object province, Object city, Object area, String splitTag)
    {
        StringBuffer fullName = new StringBuffer();
        if (null != province && !StringUtils.isBlank(province.toString()))
        {
            fullName.append(province);
            if (null != city && !StringUtils.isBlank(city.toString()))
            {
                fullName.append(splitTag).append(city);
                if (null != area && !StringUtils.isBlank(area.toString()))
                {
                    fullName.append(splitTag).append(area);
                }
            }
        }
        return fullName.toString();
    }
    
    public static String getAreaNameByNo(Integer areaNo)
    {
        return regionalService.getNameByAreaNo(areaNo);
    }
}
