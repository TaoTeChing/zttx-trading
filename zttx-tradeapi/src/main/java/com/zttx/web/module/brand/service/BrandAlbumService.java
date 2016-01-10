/*
 * @(#)BrandAlbumService.java 2015-8-26 下午1:45:27
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandAlbum;

/**
 * <p>File：BrandAlbumService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-26 下午1:45:27</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public interface BrandAlbumService extends GenericServiceApi<BrandAlbum>
{

    /**
     * 根据品牌商id和品牌id查询
     * @param brandId
     * @param brandesId
     * @return
     */
    List<BrandAlbum> findByBrandAndBrands(String brandId, String brandesId);
}
