/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.DealerService;

/**
 * 经销商商客服信息 持久层接口
 * <p>File：DealerServiceDao.java </p>
 * <p>Title: DealerServiceDao </p>
 * <p>Description:DealerServiceDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerServiceMapper extends GenericMapper<DealerService>
{
    /**
     * 根据客服ID修改所有该客服信息
     * @author 陈建平
     * @param brandService
     */
    void updateDealerServiceByUserId(DealerService dealerService);
}
