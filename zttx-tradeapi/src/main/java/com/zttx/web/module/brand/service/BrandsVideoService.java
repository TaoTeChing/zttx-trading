/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandsVideo;

/**
 * 品牌模特视频 服务接口
 * <p>File：BrandsVideoService.java </p>
 * <p>Title: BrandsVideoService </p>
 * <p>Description:BrandsVideoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandsVideoService extends GenericServiceApi<BrandsVideo>{

	/**
	 * 搜索品牌模特视频 
	 * @author 陈建平
	 * @param filter
	 * @return
	 */
	List<Map<String,Object>> findBrandsVideoMap(BrandsVideo filter);
}
