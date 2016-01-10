/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductEdit;

/**
 * 产品修改 持久层接口
 * <p>File：ProductEditDao.java </p>
 * <p>Title: ProductEditDao </p>
 * <p>Description:ProductEditDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductEditMapper extends GenericMapper<ProductEdit>
{
    /**
     * 查找产品审核
     * @param state
     * @param info
     * @param page
     * @return
     */
    List<Map<String, Object>> searchProList(@Param("state")Integer state,@Param("info")ProductBaseInfo info,@Param("page")Pagination page);
}
