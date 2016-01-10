/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandDocopen;

/**
 * 品牌商资料公开 持久层接口
 * <p>File：BrandDocopenDao.java </p>
 * <p>Title: BrandDocopenDao </p>
 * <p>Description:BrandDocopenDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandDocopenMapper extends GenericMapper<BrandDocopen>
{
	/**
     * 删除品牌商资料和经销商关联数据（物理删除）
     * @param brandId       品牌部编号
     * @param docId         资料编号
     * @return
     */
    void deleteDocopen(@Param("brandId")String brandId, @Param("docId")String docId);
    
    /**
     * 批量删除
     * @author 陈建平
     * @param list
     */
    void deleteBatch(List<String> list);
    
    /**
     * 根据品牌商编号，品牌编号和资料编号 获取允许查看的资料的终端商ID
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param docId
     * @return
     */
    List<String> getDocDealerIdList(@Param("brandId")String brandId,@Param("brandsId")String brandsId,@Param("docId")String docId);
}
