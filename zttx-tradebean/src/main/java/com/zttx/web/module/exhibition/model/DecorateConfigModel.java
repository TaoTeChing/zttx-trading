/*
 * @(#)DecorateConfigModel.java 14-4-24 下午1:04
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.model;

import java.util.List;

import com.zttx.sdk.core.GenericEntity;
import com.zttx.web.module.exhibition.entity.DecorateConfig;

/**
 * <p>File：DecorateConfigModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-24 下午1:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public class DecorateConfigModel extends GenericEntity
{
    private static final long    serialVersionUID = -2894499306101916239L;
    
    private String               showTextUnescape;                         // 未转义的showText
    
    private List<DecorateConfig> configList;
    
    private String[]             configIds;
    
    private List<String>         preIds;                                   // 该模块之前的模块
    
    private List<String>         nextIds;                                  // 该模块之后的模块
    
    public List<String> getPreIds()
    {
        return preIds;
    }
    
    public void setPreIds(List<String> preIds)
    {
        this.preIds = preIds;
    }
    
    public List<String> getNextIds()
    {
        return nextIds;
    }
    
    public void setNextIds(List<String> nextIds)
    {
        this.nextIds = nextIds;
    }
    
    public String getShowTextUnescape()
    {
        return showTextUnescape;
    }
    
    public void setShowTextUnescape(String showTextUnescape)
    {
        this.showTextUnescape = showTextUnescape;
    }
    
    public String[] getConfigIds()
    {
        return configIds;
    }
    
    public void setConfigIds(String[] configIds)
    {
        this.configIds = configIds;
    }
    
    public List<DecorateConfig> getConfigList()
    {
        return configList;
    }
    
    public void setConfigList(List<DecorateConfig> configList)
    {
        this.configList = configList;
    }
}
