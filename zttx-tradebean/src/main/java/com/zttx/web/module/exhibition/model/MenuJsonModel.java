/*
 * @(#)MenuJsonModel.java 14-4-23 上午9:16
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.model;

import java.io.Serializable;

/**
 * <p>File：MenuJsonModel.java</p>
 * <p>Title: DecorateMenu menuValue的json对应model</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-23 上午9:16</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public class MenuJsonModel implements Serializable
{
    private static final long serialVersionUID = -2976923601107524267L;
    
    private String            id;                                       // 编号
    
    private String            n;                                        // 名称
    
    private String            url;                                      // 地址
    
    private String            s;                                        // 是否显示 0：隐藏，1：显示
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getN()
    {
        return n;
    }
    
    public void setN(String n)
    {
        this.n = n;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getS()
    {
        return s;
    }
    
    public void setS(String s)
    {
        this.s = s;
    }
}
