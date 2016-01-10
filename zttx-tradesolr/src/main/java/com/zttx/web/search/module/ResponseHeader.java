/*
 * @(#)ResponseHeader 2014/5/7 13:00
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.search.module;

import java.util.Map;

/**
 * <p>File：ResponseHeader.java </p>
 * <p>Title: Solr响应头文件信息封装 </p>
 * <p>Description: ResponseHeader </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ResponseHeader
{
    private Integer             status;
    
    private Integer             QTime;
    
    private Map<String, Object> params;
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Integer getQTime()
    {
        return QTime;
    }
    
    public void setQTime(Integer QTime)
    {
        this.QTime = QTime;
    }
    
    public Map<String, Object> getParams()
    {
        return params;
    }
    
    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
