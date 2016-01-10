/*
 * @(#)BrandCrmModel.java 2014-5-30 上午9:34:19
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import java.io.Serializable;

/**
 * <p>File：BrandCrmModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-30 上午9:34:19</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class BrandCrmModel implements Serializable
{
    //
    private static final long serialVersionUID = 683190309376371937L;
    
    // ID
    private String            refrenceId;
    
    // 类型
    private Short             jsonType;
    
    public String getRefrenceId()
    {
        return refrenceId;
    }
    
    public void setRefrenceId(String refrenceId)
    {
        this.refrenceId = refrenceId;
    }
    
    public Short getJsonType()
    {
        return jsonType;
    }
    
    public void setJsonType(Short jsonType)
    {
        this.jsonType = jsonType;
    }
}
