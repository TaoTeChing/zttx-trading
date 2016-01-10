/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerVisited;
import com.zttx.web.module.dealer.model.DealerVisitedModel;
import com.zttx.web.module.dealer.model.DealerVisitedView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经销商浏览记录 持久层接口
 * <p>File：DealerVisitedDao.java </p>
 * <p>Title: DealerVisitedDao </p>
 * <p>Description:DealerVisitedDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerVisitedMapper extends GenericMapper<DealerVisited>
{
    /**
     * 分页查询
     * @param dealerVisitedModel
     * @return
     */
    List<Map<String,Object>> getDealerVisitedsPage(DealerVisitedModel dealerVisitedModel);


    /**
     * 查询浏览过我的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public List<Map<String, Object>> search(@Param(value = "page") Pagination pagination, @Param(value = "info") DealerVisitedView info);
}
