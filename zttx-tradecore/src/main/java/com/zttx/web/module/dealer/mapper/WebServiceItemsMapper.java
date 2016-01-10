/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.WebServiceItems;

/**
 * 网站服务项目 持久层接口
 * <p>File：WebServiceItemsDao.java </p>
 * <p>Title: WebServiceItemsDao </p>
 * <p>Description:WebServiceItemsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface WebServiceItemsMapper extends GenericMapper<WebServiceItems>
{
    /**
     * 统计服务数量
     * @param serviceId 服务id
     * @param servicerCate 服务类型
     * @param chargType 支付类型
     * @return 数量
     */
    int countBy(@Param("serviceId") String serviceId, @Param("servicerCate") Short servicerCate, @Param("chargType") Short chargType);
    
    /**
     * 分页查询
     * @param searchBean
     * @return
     */
    List<WebServiceItems> selectByClient(WebServiceItems searchBean);
}
