/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandsDomain;

/**
 * 品牌域名 持久层接口
 * <p>File：BrandsDomainDao.java </p>
 * <p>Title: BrandsDomainDao </p>
 * <p>Description:BrandsDomainDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandsDomainMapper extends GenericMapper<BrandsDomain>
{
	/**查找指定品牌商下的 所有域名对象
     * @param brandsDomain
     * @return
     */
	List<Map<String,Object>> getBrandsDomainsByBrandId(BrandsDomain brandsDomain);
    
    /**
     * 判断域名是否存在
     * @param brandsId 品牌编号
     * @param domain
     * @return
     */
    List<BrandsDomain> isExistDomains(@Param("brandsId")String brandsId,@Param("domain")String domain);

    /**
     * 根据品牌id查询域名
     * @param brandesId
     */
    BrandsDomain selectByBrandesId(@Param("brandesId")String brandesId);
}
