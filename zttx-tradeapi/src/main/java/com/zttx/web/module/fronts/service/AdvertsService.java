/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.fronts.entity.Adverts;

import java.util.List;

/**
 * 广告 服务接口
 * <p>File：AdvertsService.java </p>
 * <p>Title: AdvertsService </p>
 * <p>Description:AdvertsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface AdvertsService extends GenericServiceApi<Adverts>{

    /**
     * 分页查询广告信息
     * @param advertsModel
     * @param pagination
     * @return
     */
    PaginateResult<Adverts> search(Adverts advertsModel, Pagination pagination);

    /**
     * 根据广告位编号取广告
     * @param advertId
     * @return
     */
    List<Adverts> searchAdverts(String advertId);

    /**
     * 通用保存
     * @param adverts
     */
    void save(Adverts adverts);
}
