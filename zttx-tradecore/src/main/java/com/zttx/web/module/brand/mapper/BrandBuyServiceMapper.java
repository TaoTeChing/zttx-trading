/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandBuyService;

/**
 * 品牌商购买的服务 持久层接口
 * <p>File：BrandBuyServiceDao.java </p>
 * <p>Title: BrandBuyServiceDao </p>
 * <p>Description:BrandBuyServiceDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandBuyServiceMapper extends GenericMapper<BrandBuyService>
{
	/**
	 * 根据品牌商编号和服务编号查询记录
	 * @author 陈建平
	 * @param brandId
	 * @param serviceId
	 * @return
	 */
	List<BrandBuyService> findByBrandIdAndServiceId(@Param("brandId")String brandId, @Param("serviceId")String serviceId);
}
