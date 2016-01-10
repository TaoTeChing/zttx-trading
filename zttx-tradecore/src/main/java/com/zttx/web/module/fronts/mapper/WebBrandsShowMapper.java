/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.fronts.entity.WebBrandsShow;

/**
 * 首页感兴趣品牌展示 持久层接口
 * <p>File：WebBrandsShowDao.java </p>
 * <p>Title: WebBrandsShowDao </p>
 * <p>Description:WebBrandsShowDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface WebBrandsShowMapper extends GenericMapper<WebBrandsShow>
{
    /**
     * 统计数量
     * @param refrenceId
     * @param brandsId
     * @param showType
     * @return
     */
    int countBy(@Param("refrenceId") String refrenceId, @Param("brandsId") String brandsId, @Param("showType") Short showType);
    
    /**
     * 关联查询
     * @param searchBean
     * @return
     */
    List<WebBrandsShow> searchByClient(WebBrandsShow searchBean);
}
