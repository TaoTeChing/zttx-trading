/*
 * @(#)BrandFreightParamModel.java 2014-12-22 上午8:49:10
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;

import java.util.List;

/**
 * <p>File：BrandFreightParamModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-22 上午8:49:10</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class BrandFreightParamModel
{
    private BrandFreightTemplate     template;              // 模板信息
    
    private Boolean                  isExpressUsed;         // 快递
    
    private BrandFreightDetail       defaultExpress;        // 默认快递运费设置
    
    private List<BrandFreightDetail> expressList;           // 其他快递运费设置列
    
    private Boolean                  isLogisticsUsed;       // 物流
    
    private BrandFreightDetail       defaultLogistics;
    
    private List<BrandFreightDetail> logisticsList;
    
    private Boolean                  isExpressCollectUsed;  // 快递到付
    
    private Boolean                  isLogisticsCollectUsed; // 物流到付
    
    public BrandFreightTemplate getTemplate()
    {
        return template;
    }
    
    public void setTemplate(BrandFreightTemplate template)
    {
        this.template = template;
    }
    
    public List<BrandFreightDetail> getExpressList()
    {
        return expressList;
    }
    
    public void setExpressList(List<BrandFreightDetail> expressList)
    {
        this.expressList = expressList;
    }
    
    public List<BrandFreightDetail> getLogisticsList()
    {
        return logisticsList;
    }
    
    public void setLogisticsList(List<BrandFreightDetail> logisticsList)
    {
        this.logisticsList = logisticsList;
    }
    
    public Boolean getIsExpressUsed()
    {
        return isExpressUsed;
    }
    
    public void setIsExpressUsed(Boolean isExpressUsed)
    {
        this.isExpressUsed = isExpressUsed;
    }
    
    public Boolean getIsLogisticsUsed()
    {
        return isLogisticsUsed;
    }
    
    public void setIsLogisticsUsed(Boolean isLogisticsUsed)
    {
        this.isLogisticsUsed = isLogisticsUsed;
    }
    
    public Boolean getIsExpressCollectUsed()
    {
        return isExpressCollectUsed;
    }
    
    public void setIsExpressCollectUsed(Boolean isExpressCollectUsed)
    {
        this.isExpressCollectUsed = isExpressCollectUsed;
    }
    
    public Boolean getIsLogisticsCollectUsed()
    {
        return isLogisticsCollectUsed;
    }
    
    public void setIsLogisticsCollectUsed(Boolean isLogisticsCollectUsed)
    {
        this.isLogisticsCollectUsed = isLogisticsCollectUsed;
    }
    
    public BrandFreightDetail getDefaultExpress()
    {
        return defaultExpress;
    }
    
    public void setDefaultExpress(BrandFreightDetail defaultExpress)
    {
        this.defaultExpress = defaultExpress;
    }
    
    public BrandFreightDetail getDefaultLogistics()
    {
        return defaultLogistics;
    }
    
    public void setDefaultLogistics(BrandFreightDetail defaultLogistics)
    {
        this.defaultLogistics = defaultLogistics;
    }
}
