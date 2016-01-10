/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandNetimg;
/**
 * 品牌商经销网络图片信息 服务接口
 * <p>File：BrandNetimgService.java </p>
 * <p>Title: BrandNetimgService </p>
 * <p>Description:BrandNetimgService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandNetimgService extends GenericServiceApi<BrandNetimg>{

    /**
     * 根据 BrandNetwork 主键查找 BrandNetimg 记录
     * @param refrenceId
     * @return
     */
    List<Map<String,Object>> getBrandNetimgList(String refrenceId);

}
