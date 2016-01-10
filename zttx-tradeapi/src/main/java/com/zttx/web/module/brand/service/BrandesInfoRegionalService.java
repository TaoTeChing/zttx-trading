/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.model.BrandesInfoRegionalModel;

/**
 * 品牌商品牌地区列表 服务接口
 * <p>File：BrandesInfoRegionalService.java </p>
 * <p>Title: BrandesInfoRegionalService </p>
 * <p>Description:BrandesInfoRegionalService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandesInfoRegionalService extends GenericServiceApi<BrandesInfoRegional>{

	/**
     * 根据区域编码 获取 该区域所有授权的产品ID
     * @author chenjp
     * @param searchBean
     * @return
     */
    List<String> listProductIdByAreaNo(BrandesInfoRegionalModel searchBean);
    
    /**
     * 删除品牌下的所有授权区域
     * @author chenjp
     * @param brandesId 品牌编号
     * @return
     */
    Integer deleteBrandesInfoRegional(String brandesId);
    
    /**
     * 批量添加品牌授权区域
     * @author chenjp
     * @param brandesInfoRegionalModel
     */
    void insertBrandesInfoRegional(BrandesInfoRegionalModel brandesInfoRegionalModel);
    
    /**
     * 获取品牌授权区域
     * @author chenjp
     * @param searchBean
     * @return
     */
    List<BrandesInfoRegional> getBrandesInfoRegionalList(BrandesInfoRegionalModel searchBean);

    /**
     * brandsId 查出该品牌商是否授权给该区域areaNo 用户
     * @param
     * @param brandsId
     * @return
     * @author易永耀
     */
    BrandesInfoRegional findByBrandsIdAndAreaNo(String brandsId,Integer areaNo);
}
