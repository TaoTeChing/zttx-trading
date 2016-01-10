/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductEditDetail;

/**
 * 产品修改详情 持久层接口
 * <p>File：ProductEditDetailDao.java </p>
 * <p>Title: ProductEditDetailDao </p>
 * <p>Description:ProductEditDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductEditDetailMapper extends GenericMapper<ProductEditDetail>
{
    /**
     * 查询编辑细节
     * @param productId
     * @param changeType
     * @param vid
     * @return
     */
    ProductEditDetail find(@Param("productId")String productId, @Param("changeType")Short changeType, @Param("vid")String vid);
    /**
     * 根据审核状态和产品id获取审核详情
     * @param editDetail
     * @return
     */
    List<Map<String, Object>> searchEditList(ProductEditDetail editDetail);
    /**
     * 查找没有完成的
     * @param productId
     * @return
     */
    Integer countUnFinish(String productId);
    
}
