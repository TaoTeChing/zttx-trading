/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandVisited;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 品牌商浏览记录 持久层接口
 * <p>File：BrandVisitedDao.java </p>
 * <p>Title: BrandVisitedDao </p>
 * <p>Description:BrandVisitedDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandVisitedMapper extends GenericMapper<BrandVisited>
{
    /**
     * 分页获取 品牌商浏览的经销商信息
     * @param brandVisited
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> getBrandVisitedPage(BrandVisited brandVisited);


    /**
     * 我浏览过的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public List<Map<String, Object>> search(@Param("page")Pagination pagination, @Param("info")BrandVisited info);


    /**
     * 更新查看次数
     */
    void updateBrandView(@Param("brandId")String brandId,@Param("dealerId")String dealerId);

    /**
     * 查询申请列表
     *
     * @param dealerId
     * @param brandId
     * @return
     */
    List<Map<String, Object>> getBrandApplyList(@Param("dealerId")String dealerId,@Param("brandId")String brandId);
}
