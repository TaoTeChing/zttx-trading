/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerClass;

/**
 * 经销商经营品类l 持久层接口
 * <p>File：DealerClassDao.java </p>
 * <p>Title: DealerClassDao </p>
 * <p>Description:DealerClassDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerClassMapper extends GenericMapper<DealerClass> {

    /**
     * 根据最后更新时间查询终端商分类列表
     *
     * @param dealerClass
     * @return
     */
    List<DealerClass> getDealerClassList(@Param("dealerClass")DealerClass dealerClass,@Param("page") Pagination page);
    
    /**
     * 根据经销商Id查找
     * @param dealerId
     * @return
     */
    List<Integer> findByDealerId(@Param("dealerId") String dealerId);
    
    /**
	 * 通过经销商ID来查询所有对应的品类编号
	 * @author 陈建平
	 * @param dealerId
	 * @return
	 */
    List<Integer> findDealNoBy(@Param("dealerId")String dealerId);
    
    /**
     * 根据经销商ID删除所有对应的品类编号
     * @author 陈建平
     * @param dealerId
     */
    void deleteByDealerInfoId(@Param("dealerId")String dealerId);
}
