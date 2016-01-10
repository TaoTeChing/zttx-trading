/*
 * @(#)WeshopAPIConfig.java 2014-7-16 下午1:36:39
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * <p>File：WeshopAPIConfig.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-16 下午1:36:39</p>
 * <p>Company: 8637.com</p>
 * @author 吕海斌
 * @version 1.0
 */
public class WeshopAPIConfig
{
    private static String MESSAGE_FLAG = "0XFF";
    
    private static String MESSAGE_ELAG = "0X00";
    
    private static String MESSAGE_SPEC = ",";
    
    private static String MESSAGE_LINK = ":";
    
    public static String  USER_KEY     = "250A11CF7E85422AAE7EA40D00668945";
    
    public static String  ERP_KEY      = "D7B7BA3F24A84D738D8B7A15CC30201A";
    
    protected static String transMapToString(Map<String, Object> map)
    {
        String result = null;
        if (null != map && !map.isEmpty())
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> entry : map.entrySet())
            {
                String key = entry.getKey();
                Object object = entry.getValue();
                String value = "";
                if (object instanceof String) value = (String) object;
                else if (object != null) value = object.toString();
                value = StringUtils.replace(value, MESSAGE_SPEC, MESSAGE_FLAG);
                value = StringUtils.replace(value, MESSAGE_LINK, MESSAGE_ELAG);
                stringBuilder.append(key).append(MESSAGE_LINK);
                stringBuilder.append(value).append(MESSAGE_SPEC);
            }
            String tempString = stringBuilder.toString();
            result = StringUtils.substring(tempString, 0, tempString.length() - 1);
        }
        return result;
    }
    
    public static Map<String, String> transMapToEncryMap(Map<String, Object> map, String userKey)
    {
        Map<String, String> result = new LinkedHashMap<String, String>();
        String data = "";
        if (null != map && !map.isEmpty() && StringUtils.isNotBlank(userKey)) data = transMapToString(map);
        int dataLen = ValidateUtils.length(data);
        String userDes = ParameterUtils.getUserDes(userKey, dataLen);
        result.put("userDes", userDes);
        result.put("userKey", userKey);
        result.put("dataLen", String.valueOf(dataLen));
        result.put("data", data);
        return result;
    }
}
