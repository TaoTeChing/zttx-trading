/*
 * @(#)ProductCateModel.java 2014-4-7 上午10:53:04
 * Copyright 2014 施建波, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import com.zttx.web.module.common.entity.ProductCate;

/**
 * <p>File：ProductCateModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-7 上午10:53:04</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
public class ProductCateModel extends ProductCate
{
    // 产品编号集合
    private String[] productIdAry;
    
    // 分类编号集合
    private String[] catelogIdAry;
    
    // 品牌商编号
    private String   brandId;
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public String[] getProductIdAry()
    {
        return productIdAry;
    }
    
    public void setProductIdAry(String[] productIdAry)
    {
        this.productIdAry = productIdAry;
    }
    
    public String[] getCatelogIdAry()
    {
        return catelogIdAry;
    }
    
    public void setCatelogIdAry(String[] catelogIdAry)
    {
        this.catelogIdAry = catelogIdAry;
    }
}
