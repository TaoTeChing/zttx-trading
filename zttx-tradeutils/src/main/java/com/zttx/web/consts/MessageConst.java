/*
 * @(#)MessageConst.java 2014-3-19 上午10:07:21
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import java.util.Map;
import java.util.Set;

/**
 * <p>File：MessageConst.java</p>
 * <p>Title: 消息常量</p>
 * <p>Description:品牌商和经销商消息的常量</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-19 上午10:07:21</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public enum MessageConst
{
    UPDATE_PRICE("调整价格", "订单（%orderId）的价格已修改。");
    private MessageConst(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
    
    private String title;
    
    private String content;
    
    public String getTitle()
    {
        return title;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public String[] execute(Map<String, String> paramMap)
    {
        String[] temp = new String[]{title, content};
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet)
        {
            String value = paramMap.get(key);
            if (null == value)
            {
                value = "";
            }
            temp[0] = temp[0].replace("%" + key, value);
            temp[1] = temp[1].replace("%" + key, value);
        }
        return temp;
    }
}
