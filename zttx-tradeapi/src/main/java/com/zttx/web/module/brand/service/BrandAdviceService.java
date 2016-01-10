/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandAdvice;

/**
 * 品牌商优化建议 服务接口
 * <p>File：BrandAdviceService.java </p>
 * <p>Title: BrandAdviceService </p>
 * <p>Description:BrandAdviceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface BrandAdviceService extends GenericServiceApi<BrandAdvice> {

    /**
     * 保存建议
     *
     * @param brandAdvice
     */
    void save(BrandAdvice brandAdvice);
}
