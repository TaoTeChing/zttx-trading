/*
 * @(#)BrandPointBalanceModel.java 2014-11-25 下午2:07:13
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandPointBalance;


/**
 * <p>File：BrandPointBalanceModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-25 下午2:07:13</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class BrandPointBalanceModel extends BrandPointBalance
{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String brandName;
    
    private String brandsName;
    
    public String getBrandName()
    {
        return brandName;
    }
    
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
}
