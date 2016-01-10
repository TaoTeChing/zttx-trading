/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.DealDic;

/**
 * 品牌经营品类信息 持久层接口
 * <p>File：DealDicDao.java </p>
 * <p>Title: DealDicDao </p>
 * <p>Description:DealDicDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealDicMapper extends GenericMapper<DealDic>
{
    /**
     * 取产品顶级类别
     *
     * @return
     */
    List<DealDic> getTopProductDealDics();
    
    /**
     * 根据字典编码取下级与产品有关的字典
     *
     * @param dealNo
     * @return
     */
    List<DealDic> getProductDealDics(Integer dealNo);
    
    /**
     * 根据父ID,查询
     *
     * @param parentId
     * @return
     */
    List<DealDic> getDealDicsBy(String parentId);
    
    /**
     * 通过 dealNo
     *
     * @param dealNo
     * @return
     */
    List<DealDic> selectDealDicByDealNo(Integer dealNo);
    
    /**
     * 根据一组类目编号获取类目
     *
     * @param dealNos
     * @return
     */
    List<DealDic> getDealDicByDealNos(@Param("dealNos") List<Integer> dealNos);
    
    /**
     * 根据目录品类级别查询记录
     *
     * @param level
     * @return
     */
    List<DealDic> getDealDicByLevel(Short level);
    
    List<DealDic> getDealDicOrderByField(@Param("page") Pagination page);
    
    /**
     * 通过品类编号来获取品牌信息
     * 首页导航
     *
     * @param dealDicNo
     * @param page
     * @return
     */
    List<Map<String, Object>> findIndexNav(@Param("dealNo") Integer dealDicNo, @Param("page") Pagination page);
    
    /**
     *
     * @param parentId
     * @param dealLevel
     * @return
     */
    Integer getMaxDealNo(@Param("parentId") String parentId, @Param("dealLevel") Short dealLevel);
    
    DealDic getDealDicByDealNo(Integer dealNo);
}
