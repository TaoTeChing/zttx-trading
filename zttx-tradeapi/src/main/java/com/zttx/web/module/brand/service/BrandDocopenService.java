/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandDocopen;
/**
 * 品牌商资料公开 服务接口
 * <p>File：BrandDocopenService.java </p>
 * <p>Title: BrandDocopenService </p>
 * <p>Description:BrandDocopenService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandDocopenService extends GenericServiceApi<BrandDocopen>{

	/**
     * 删除品牌商资料和经销商关联数据（物理删除）
     * @param brandId       品牌部编号
     * @param docId         资料编号
     * @return
     */
    void deleteDocopen(String brandId, String docId);
    
    /**
     * 批量删除
     * @author 陈建平
     * @param list
     */
    void deleteBatch(List<String> list);
    
    /**
     * 根据品牌商编号，品牌编号和资料编号 获取允许查看的资料的终端商ID
     * @author 陈建平
     * @param brandId 品牌商ID
     * @param brandesId 品牌ID
     * @param docId 资料ID
     * @return
     */
    List<String> getDocDealerIdList(String brandId,String brandesId,String docId);
}
