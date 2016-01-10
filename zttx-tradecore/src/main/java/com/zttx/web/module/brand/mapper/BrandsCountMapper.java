/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandsCount;

/**
 * 品牌计数信息 持久层接口
 * <p>File：BrandsCountDao.java </p>
 * <p>Title: BrandsCountDao </p>
 * <p>Description:BrandsCountDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandsCountMapper extends GenericMapper<BrandsCount>
{

    /**
     * 根据品牌商编号和品牌编号查询
     * @param brandId
     * @param brandesId
     * @return
     */
    BrandsCount findByBrandIdAndBrandesId(@Param("brandId") String brandId, @Param("brandesId") String brandesId);

    /**
     * 根据品牌商id 品牌id 更新 BrandsCount表指定列 的 数值
     * @param colnumnName
     * @param brandId
     * @param brandesId
     * @param count 增量
     * @return
     */
    boolean updateBrandsCount(@Param("colnumnName")String colnumnName, @Param("brandId")String brandId,
            @Param("brandesId")String brandesId, @Param("count")int count ,@Param("now") long now);

    /**
     * 取在某个时间段修改过的品牌统计记录
     * @param time1
     * @param time2
     * @return id 集合
     */
    List<String> getBrandsCountUpdatedBetween(@Param("time1") long time1, @Param("time2") long time2);
}
