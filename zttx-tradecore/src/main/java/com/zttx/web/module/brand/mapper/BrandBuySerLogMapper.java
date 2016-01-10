/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandBuySerLog;

/**
 * 品牌商购买的服务记录 持久层接口
 * <p>File：BrandBuySerLogDao.java </p>
 * <p>Title: BrandBuySerLogDao </p>
 * <p>Description:BrandBuySerLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandBuySerLogMapper extends GenericMapper<BrandBuySerLog>
{
    /**
     * 查询
     * @param brandId
     * @return
     * @author 易永耀
     */
    BrandBuySerLog findById(String brandId, String s);
}
