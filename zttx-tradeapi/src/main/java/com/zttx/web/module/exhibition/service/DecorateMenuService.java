/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.exhibition.entity.DecorateHeaderLog;
import com.zttx.web.module.exhibition.entity.DecorateMenu;
import com.zttx.web.module.exhibition.model.MenuJsonModel;
/**
 * 展厅装修菜单 服务接口
 * <p>File：DecorateMenuService.java </p>
 * <p>Title: DecorateMenuService </p>
 * <p>Description:DecorateMenuService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DecorateMenuService extends GenericServiceApi<DecorateMenu>{
    DecorateMenu findByBrandIdAndBrandsId(String brandId,String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    DecorateMenu findLatestBrandMenus(String brandId, String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @param header
     * @param menus
     * @param request
     */
    void saveNavlog(String brandId, String brandsId, DecorateHeaderLog header,
            List<MenuJsonModel> menus, HttpServletRequest request) throws BusinessException;
}
