/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.ProductParity;
import com.zttx.web.module.common.model.ParityModel;
/**
 * 产品比价表 服务接口
 * <p>File：ProductParityService.java </p>
 * <p>Title: ProductParityService </p>
 * <p>Description:ProductParityService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductParityService extends GenericServiceApi<ProductParity>{

    /**
     * 添加或修改比价
     * @param model
     * @param productId
     */
    public void addOrUpdate(ParityModel model,String productId);
    /**
     * 查找比价信息
     * @return
     */
    List<Map<String,Object>> findParityModel(String productId);
}
