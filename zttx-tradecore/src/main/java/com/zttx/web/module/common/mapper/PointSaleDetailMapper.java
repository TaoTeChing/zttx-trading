/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.PointSaleDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 返点财务帐销售明细表 持久层接口
 * <p>File：PointSaleDetailDao.java </p>
 * <p>Title: PointSaleDetailDao </p>
 * <p>Description:PointSaleDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface PointSaleDetailMapper extends GenericMapper<PointSaleDetail>
{
    /**
     * 分页查询 erp 返点销售明细数据
     * @author 易永耀
     * @param pointSaleDetail 明细数据 filter
     * @return
     */
    List<Map<String,Object>> selectPointSaleDetailList(@Param("page") Pagination page, @Param("pointSaleDetail") PointSaleDetail pointSaleDetail);
    /**
     * 分页查询 erp 返点销售明细数据 求和
     * @author 易永耀
     * @param pointSaleDetail 明细数据 filter
     * @return
     */
    Map<String,Object> countPointSaleDetailListSum(@Param("pointSaleDetail") PointSaleDetail pointSaleDetail);
}
