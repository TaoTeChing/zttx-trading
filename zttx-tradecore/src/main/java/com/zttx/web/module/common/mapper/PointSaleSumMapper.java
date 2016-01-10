/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.PointSaleSum;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 返点类型产品门店销售统计和表 持久层接口
 * <p>File：PointSaleSumDao.java </p>
 * <p>Title: PointSaleSumDao </p>
 * <p>Description:PointSaleSumDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface PointSaleSumMapper extends GenericMapper<PointSaleSum>
{

    /**
     * 分页求和 erp 返点价数据日结总和
     * @param page
     * @param pointSaleSum
     * @return
     */
    List<Map<String,Object>> selectPointSaleSumList(@Param("page") Pagination page, @Param("pointSaleSum") PointSaleSum pointSaleSum);
    /**
     * 分页求和 erp 返点价数据日结总和 求和
     * @param pointSaleSum
     * @return
     */
    Map<String,Object> countPointSaleSumListSum(@Param("pointSaleSum") PointSaleSum pointSaleSum);
    /**
     * 更具品牌商id统计 该品牌商与所有和他有返点财务帐关系的经销商的返点财务帐总帐
     * @author易永耀
     * @param page
     * @param pointSaleSum  filter
     * @return
     */
    List<Map<String,Object>> countDealersPointFinancial(@Param("page") Pagination page, @Param("pointSaleSum") PointSaleSum pointSaleSum);
    /**
     * 更具品牌商id统计 该品牌商与所有和他有返点财务帐关系的经销商的返点财务帐总帐 和
     * @author易永耀
     * @param pointSaleSum  filter
     * @return
     */
    Map<String,Object> countDealersPointFinancialSum(@Param("pointSaleSum") PointSaleSum pointSaleSum);

    /**
     * 批量查找该经销商给品牌商们的付款 资金与个人信息
     * @param brandIds
     * @param dealerId
     * @return
     *
     */
    List<Map<String,Object>> selectNoPayPoint(@Param("brandIds") List<String> brandIds, @Param("dealerId") String dealerId);

    /**
     * 更新
     * @param pointSaleSum
     */
    void updatePointSaleSum(PointSaleSum pointSaleSum);
    /**
     * 获取经销商给品牌商正在支付中的货款
     * @author 易永耀
     * @param dealerId
     * @param brandIds
     * @return
     */
    Map<String,Object> selectPayIng(@Param("dealerId") String dealerId, @Param("brandIds") String[] brandIds);
}
