/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.WebServiceItems;

/**
 * 网站服务项目 服务接口
 * <p>File：WebServiceItemsService.java </p>
 * <p>Title: WebServiceItemsService </p>
 * <p>Description:WebServiceItemsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface WebServiceItemsService extends GenericServiceApi<WebServiceItems>
{
    /**
     * 服务查询
     * @param webServiceItemsFilter 查询条件
     * @return List<WebServiceItems>
     */
    List<WebServiceItems> search(WebServiceItems webServiceItemsFilter);
    
    /**
     * 保存服务
     * @param webServiceItems save对象
     * @return 主键
     * @throws BusinessException
     */
    String save(WebServiceItems webServiceItems) throws BusinessException;
    
    /**
     * 判断服务是否存在
     * @param serviceId
     * @param servicerCate
     * @param chargType
     * @return
     */
    boolean isExist(String serviceId, Short servicerCate, Short chargType);
    
    /**
     * 查询 （支撑接口调用）
     * @param searchBean
     * @return
     */
    PaginateResult<WebServiceItems> selectByClient(WebServiceItems searchBean);
}
