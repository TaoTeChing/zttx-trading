/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandPayword;

/**
 * 品牌商支付密码 服务接口
 * <p>File：BrandPaywordService.java </p>
 * <p>Title: BrandPaywordService </p>
 * <p>Description:BrandPaywordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandPaywordService extends GenericServiceApi<BrandPayword>
{
    /**
     * *新建支付密码
     *
     * @param newPwd
     */
    void insertCreatePayPassword(BrandPayword newPwd) throws BusinessException;
}
