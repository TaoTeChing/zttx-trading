/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品库存调整记录表（该表由调度执行生成） 持久层接口
 * <p>File：StockAdjustDetailDao.java </p>
 * <p>Title: StockAdjustDetailDao </p>
 * <p>Description:StockAdjustDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface StockAdjustDetailMapper extends GenericMapper<StockAdjustDetail>
{
    /**
     * 按日期分组分页查询 返点财务库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @return
     */
    List<Map<String,Object>> selectStockAdjustDetailGroupByDateList(@Param("page") Pagination page, @Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按日期分组分页查询 返点财务库存情况 求和
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     */
    Map<? extends String,?> selectStockAdjustDetailGroupByDateListSum(@Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按产品分组分页查询 返点财务库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @return
     */
    List<Map<String,Object>> selectStockAdjustDetailGroupByProductList(@Param("page") Pagination page, @Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按产品分组分页查询 返点财务库存情况
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     */
    Map<String,Object> selectStockAdjustDetailGroupByProductListSum(@Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按产品sku分页查询 返点库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @return
     */
    List<Map<String,Object>> selectStockAdjustDetailBySkuList(@Param("page") Pagination page, @Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按产品sku查询 返点库存情况 求和
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     */
    Map<? extends String,?> selectStockAdjustDetailBySkuListSum(@Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按日期和类别sku分页查询 返点库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @return
     */
    List<Map<String,Object>> selectStockAdjustDetailByTimeAndSourceList(@Param("page") Pagination page, @Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 按日期和类别sku查询 返点库存情况 求和
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     */
    Map<? extends String,?> selectStockAdjustDetailByTimeAndSourceListSum(@Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
    /**
     * 查询 返点库存详细 产品sku分类 产品sku详情头信息
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     *   brandsName   string
     *   productTitle   string
     *   productNo      string
     *   productSkuName string
     *   type  string
     *
     */
    List<Map<String,Object>> selectHeadData(@Param("stockAdjustDetail") StockAdjustDetail stockAdjustDetail);
}
