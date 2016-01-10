/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandNetimg;

/**
 * 品牌商经销网络图片信息 持久层接口
 * <p>File：BrandNetimgDao.java </p>
 * <p>Title: BrandNetimgDao </p>
 * <p>Description:BrandNetimgDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandNetimgMapper extends GenericMapper<BrandNetimg>
{

    /**
     * @param networkId
     * @return 
     */
    List<Map<String, Object>> getBrandNetimgList(String networkId);

    /**
     * @param networkId
     * @return
     */
    List<BrandNetimg> findBrandNetimgList(String networkId);

}
