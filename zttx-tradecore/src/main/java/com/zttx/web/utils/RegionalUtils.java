package com.zttx.web.utils;

import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.module.common.service.RegionalService;

/**
 * 区域工具类,提供给TAG,替换原com.zttx.web.consts.TagCommonConst
 * Created by 李星 on 2015/8/21.
 */
public class RegionalUtils
{
    private static RegionalService regionalService = SpringUtils.getBean(RegionalService.class);
    
    /**
     * 传入省市区  用分隔符分割 获取区域全名
     *
     * @param province 省
     * @param city     市
     * @param area     区
     * @param splitTag 分隔符
     * @return 比如 浙江省/宁波市/鄞州区
     * @author 施建波
     */
    public static String getFullArea(Object province, Object city, Object area, String splitTag)
    {
        return getFullAreaChange(province, city, area, splitTag);
    }
    
    /**
     * 传入省市区  用分隔符分割 获取区域全名
     *
     * @param province 省
     * @param city     市
     * @param area     区
     * @param splitTag 分隔符
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
        if (null != province && !org.apache.commons.lang3.StringUtils.isBlank(province.toString()))
        {
            fullName.append(province);
            if (null != city && !org.apache.commons.lang3.StringUtils.isBlank(city.toString()))
            {
                fullName.append(splitTag).append(city);
                if (null != area && !org.apache.commons.lang3.StringUtils.isBlank(area.toString()))
                {
                    fullName.append(splitTag).append(area);
                }
            }
        }
        return fullName.toString();
    }
    
    public static String getFullAreaNameByNo(Integer areaNo)
    {
        return regionalService.getFullAreaName(areaNo);
    }
    
    public static String getAreaNameByNo(Integer areaNo)
    {
        return regionalService.getNameByAreaNo(areaNo);
    }
}
