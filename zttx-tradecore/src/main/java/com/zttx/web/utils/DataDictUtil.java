/*
 * @(#)DataDictUtil 2014/5/22 11:19
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.module.common.service.DataDictValueService;


/**
 * <p>File：DataDictUtil</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/22 11:19</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public final class DataDictUtil
{
    private DataDictUtil()
    {
    }
    
    private static DataDictValueService dataDictValueService = SpringUtils.getBean(DataDictValueService.class);
    
    /**
     * 翻译字典值
     * @param dictCode
     * @param dictValue
     * @return
     */
    public static String translate(String dictCode, Object dictValue)
    {
        if (dictValue == null) { return ""; }
        String value = null;
        if (dictValue instanceof String)
        {
            value = (String) dictValue;
        }
        else
        {
            value = dictValue.toString();
        }
        String name = dataDictValueService.findDictValueName(dictCode, value);
        return name == null ? "" : name;
    }
}
