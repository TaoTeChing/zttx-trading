/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.WebServiceCom;

/**
 * 网站服务商 服务接口
 * <p>File：WebServiceComService.java </p>
 * <p>Title: WebServiceComService </p>
 * <p>Description:WebServiceComService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface WebServiceComService extends GenericServiceApi<WebServiceCom>
{
    /**
     * 分页查询（支撑调用）
     * @param page 分页对象
     * @param searchBean 查询条件
     * @return PaginateResult
     */
    PaginateResult<WebServiceCom> searchByClient(Pagination page, WebServiceCom searchBean);
    
    /**
     * 保存 （支撑调用）
     * @param webServiceCom 保存对象
     * @return 主键
     */
    String saveByClient(WebServiceCom webServiceCom);
}
