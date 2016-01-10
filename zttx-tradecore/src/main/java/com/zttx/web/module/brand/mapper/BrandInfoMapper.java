/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 品牌商基本信息 持久层接口
 * <p>File：BrandInfoDao.java </p>
 * <p>Title: BrandInfoDao </p>
 * <p>Description:BrandInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandInfoMapper extends GenericMapper<BrandInfo>
{

    /**
     * 获取品牌商 最大条形码助记码
     * @return
     */
    String getMaxBrandInfobarCodeNum();

    /**
     * 检测是否存在相同名称的品牌商
     * @param comName
     * @param oldBrandId
     * @return
     */
    Boolean isExits(@Param("comName")String comName, @Param("oldBrandId")String oldBrandId);
    /**
     * 根据brandid获取最小库存
     * @param brandId
     * @return
     */
    Integer getBrandMInStore(String brandId);

    /**
     * 查询所有品牌商的基础信息
     * 包括ID和名称
     * @return
     */
    List<Map<String, Object>> findAllBrandBaseInfo();
}
