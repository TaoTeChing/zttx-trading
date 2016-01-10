/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.CreditToPoint;

import java.util.List;
import java.util.Map;

/**
 * erp铺货变返点sku详细信息表 持久层接口
 * <p>File：CreditToPointDao.java </p>
 * <p>Title: CreditToPointDao </p>
 * <p>Description:CreditToPointDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface CreditToPointMapper extends GenericMapper<CreditToPoint>
{
    /**
     * 分页查询授信转返点数据
     * @author 易永耀
     * @param creditToPoint
     * @return
     */
    List<Map<String,Object>> selectCreditToPointList(CreditToPoint creditToPoint);
    /**
     * 查询授信转返点数据求和
     * @author 易永耀
     * @param creditToPoint
     * @return
     */
    Map<String,Object> countCreditToPointListSum(CreditToPoint creditToPoint);

    /**
     * @author 易永耀
     * 条件判断该 授信转返点 是否存在
     * @param creditToPoint
     * @return
     */
    boolean isExistCreditToPoint( CreditToPoint creditToPoint);
}
