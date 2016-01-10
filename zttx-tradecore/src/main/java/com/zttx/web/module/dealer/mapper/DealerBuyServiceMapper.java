/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerBuyService;

/**
 * 经销商购买的服务 持久层接口
 * <p>File：DealerBuyServiceDao.java </p>
 * <p>Title: DealerBuyServiceDao </p>
 * <p>Description:DealerBuyServiceDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerBuyServiceMapper extends GenericMapper<DealerBuyService>
{
    /**
     * 根据经销商编号和服务编号查询记录
     * @param dealerId
     * @param serviceId
     * @return
     */
    DealerBuyService findBy(@Param("dealerId") String dealerId, @Param("serviceId") String serviceId);
    
    /**
     * 购买服务查询 接口调用
     * @param searchBean 查询条件
     * @return List
     */
    List<DealerBuyService> searchByClient(DealerBuyService searchBean);
}
