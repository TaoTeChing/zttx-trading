/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandsDomain;


/**
 * 品牌域名 服务接口
 * <p>File：BrandsDomainService.java </p>
 * <p>Title: BrandsDomainService </p>
 * <p>Description:BrandsDomainService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandsDomainService extends GenericServiceApi<BrandsDomain>{
    
    /**查找指定品牌商下的 所有域名对象
     * @param brandId
     * @return
     */
    List<Map<String,Object>> getBrandsDomainsByBrandId(String brandId);
    
    /**
     * 更新指定 品牌的域名值 
     * @param brandsDomain
     * @return
     */
    boolean updateBrandsDomainValue(BrandsDomain brandsDomain);
    
    /**
     * 判断域名是否存在
     * @param brandsId
     * @param domain
     * @return
     */
    boolean isExistDomains(String brandsId,String domain);

    /**
     * 根据品牌二级域名前缀 查询 BrandsDomain 记录
     * @param domain 二级域名前缀
     * @return
     */
    BrandsDomain findByDomain(String domain);

    /**
     * 根据品牌id查询域
     * @param brandesId
     * @return
     */
    BrandsDomain selectByBrandesId(String brandesId);
}
