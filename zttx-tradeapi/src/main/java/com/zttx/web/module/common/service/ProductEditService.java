/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Map;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.ProductEdit;
/**
 * 产品修改 服务接口
 * <p>File：ProductEditService.java </p>
 * <p>Title: ProductEditService </p>
 * <p>Description:ProductEditService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductEditService extends GenericServiceApi<ProductEdit>{
    /**
     * 查找产品审核信息
     * @param state
     * @param info
     * @return
     */
    PaginateResult<Map<String, Object>> searchProList(Integer state,ProductBaseInfo info);
}
