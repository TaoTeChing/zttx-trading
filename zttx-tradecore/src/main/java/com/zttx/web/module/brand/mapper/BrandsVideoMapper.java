/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandsVideo;

/**
 * 品牌模特视频 持久层接口
 * <p>File：BrandsVideoDao.java </p>
 * <p>Title: BrandsVideoDao </p>
 * <p>Description:BrandsVideoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandsVideoMapper extends GenericMapper<BrandsVideo>
{

	/**
	 * 搜索品牌模特视频 
	 * @author 陈建平
	 * @param filter
	 * @return
	 */
	List<Map<String,Object>> findBrandsVideoMap(BrandsVideo filter);
}
