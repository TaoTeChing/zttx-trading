/*
 * @(#)BrandsCountDubboService.java 2015-8-22 下午6:13:06
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandsCount;

/**
 * <p>File：BrandsCountDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-22 下午6:13:06</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface BrandsCountDubboService
{
    void modifyBrandsCount(String brandsId,String[]countType)throws BusinessException;


    /**
     * 查询品牌商计数器数据
     *
     * @param refrenceId
     * @return
     */
    public BrandsCount queryBrandCountList(String refrenceId);
}
