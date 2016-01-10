/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌商证书信息 持久层接口
 * <p>File：BrandCardDao.java </p>
 * <p>Title: BrandCardDao </p>
 * <p>Description:BrandCardDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandCardMapper extends GenericMapper<BrandCard>
{
    /**
     * 分页查询品牌商证书
     * @param page
     * @param brandId
     * @return
     */
    List<BrandCard> pageSearch(Pagination page, @Param("brandId")String brandId);

    /**
     * 获取品牌商证书
     * @param refrenceId
     * @param brandId
     * @return
     */
    BrandCard getEntity(@Param("refrenceId")String refrenceId, @Param("brandId")String brandId);
}
