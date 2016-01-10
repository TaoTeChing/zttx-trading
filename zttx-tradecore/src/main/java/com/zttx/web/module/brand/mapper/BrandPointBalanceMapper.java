/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandPointBalance;
import com.zttx.web.module.brand.model.BrandPointBalanceModel;

/**
 * 扣点表 持久层接口
 * <p>File：BrandPointBalanceDao.java </p>
 * <p>Title: BrandPointBalanceDao </p>
 * <p>Description:BrandPointBalanceDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandPointBalanceMapper extends GenericMapper<BrandPointBalance>
{

	/**
     * 搜索扣点
     * @author 陈建平
     * @param filter
     * @return
     */
    List<BrandPointBalance> findByBrandPointBalanceModel(BrandPointBalanceModel filter);
    
    /**
     * 获取有效的品牌
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String,Object>> findVlidBrandesInfo(BrandPointBalanceModel filter);
}
