/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;

/**
 * 经销商购买的服务记录 持久层接口
 * <p>File：DealerBuySerLogDao.java </p>
 * <p>Title: DealerBuySerLogDao </p>
 * <p>Description:DealerBuySerLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerBuySerLogMapper extends GenericMapper<DealerBuySerLog>
{
    /**
     * 查询经销商购买日志
     * @param searchBean 条件
     * @return List
     */
    List<DealerBuySerLog> searchByClient(DealerBuySerLog searchBean);
    /**
     * 查询
     * @param dealerId
     * @param refrenceId
     * @return
     * @author 张昌苗
     */
    DealerBuySerLog findById(String userId, String s);
}
