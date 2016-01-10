/*
 * @(#)ProductCatalogModel.java 2015-8-15 下午5:42:04
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import com.zttx.web.module.common.entity.ProductCatalog;

/**
 * <p>File：ProductCatalogModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-15 下午5:42:04</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class ProductCatalogModel extends ProductCatalog
{
    private String[] cateNameAry;
    
    private String[] refrenceIdAry;
    
    private String[] cateLevelAry;
    
    private String[] cateIconAry;
    
    public String[] getCateNameAry()
    {
        return cateNameAry;
    }
    
    public void setCateNameAry(String[] cateNameAry)
    {
        this.cateNameAry = cateNameAry;
    }
    
    public String[] getRefrenceIdAry()
    {
        return refrenceIdAry;
    }
    
    public void setRefrenceIdAry(String[] refrenceIdAry)
    {
        this.refrenceIdAry = refrenceIdAry;
    }
    
    public String[] getCateLevelAry()
    {
        return cateLevelAry;
    }
    
    public void setCateLevelAry(String[] cateLevelAry)
    {
        this.cateLevelAry = cateLevelAry;
    }
    
    public String[] getCateIconAry()
    {
        return cateIconAry;
    }
    
    public void setCateIconAry(String[] cateIconAry)
    {
        this.cateIconAry = cateIconAry;
    }
}
