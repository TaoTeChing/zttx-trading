/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerImage;

/**
 * 经销商店铺招牌 持久层接口
 * <p>File：DealerImageDao.java </p>
 * <p>Title: DealerImageDao </p>
 * <p>Description:DealerImageDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@MyBatisDao
public interface DealerImageMapper extends GenericMapper<DealerImage>
{

    /**
     * 根据终端商id获取终端商图片数据列表
     * @param dealerId
     * @return
     */
    List<DealerImage> selectDealerImagesByDealerId(@Param("dealerId")String dealerId);

    /**
     * 查询终端商图片列表
     */
    List<DealerImage> selectDealerImages(@Param("dealerIdList")Map<String ,List<String>> dealerIdList,@Param("updateTime")Long updateTime,@Param("page") Pagination page);
            
    /**
     * 根据经销商ID物理删除
     * @param dealerId
     * @return
     */
    Integer deleteDealerImagesByDealerId(@Param("dealerId")String dealerId);
}
