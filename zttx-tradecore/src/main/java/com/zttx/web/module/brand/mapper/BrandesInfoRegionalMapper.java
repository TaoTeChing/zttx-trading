/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.model.BrandesInfoRegionalModel;

/**
 * 品牌商品牌地区列表 持久层接口
 * <p>File：BrandesInfoRegionalDao.java </p>
 * <p>Title: BrandesInfoRegionalDao </p>
 * <p>Description:BrandesInfoRegionalDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandesInfoRegionalMapper extends GenericMapper<BrandesInfoRegional> {
    /**
     * 根据区域编码 获取 该区域所有授权的产品ID
     *
     * @param searchBean
     * @return
     * @author chenjp
     */
    List<String> listProductIdByAreaNo(BrandesInfoRegionalModel searchBean);

    /**
     * 删除品牌下的所有授权区域
     *
     * @param brandesId 品牌编号
     * @return
     * @author chenjp
     */
    Integer deleteBrandesInfoRegional(String brandesId);


    /**
     * 获取品牌授权区域
     *
     * @param searchBean
     * @return
     * @author chenjp
     */
    List<BrandesInfoRegional> getBrandesInfoRegionalList(BrandesInfoRegionalModel searchBean);


    /**
     * @param brandsId
     * @param areaNo
     * @return
     */
    BrandesInfoRegional findByBrandsIdAndAreaNo(@Param("brandsId") String brandsId, @Param("areaNo") Integer areaNo);
}
