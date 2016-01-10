/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.dealer.entity.DealerClass;

/**
 * 经销商经营品类l 服务接口
 * <p>File：DealerClassService.java </p>
 * <p>Title: DealerClassService </p>
 * <p>Description:DealerClassService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerClassService extends GenericServiceApi<DealerClass>{

    /**
     * 根据经销商Id查找
     * @param dealerId
     * @return
     */
    List<Integer> findByDealerId(String dealerId);
	/**
	 * 通过经销商ID来查询所有对应的品类编号
	 * @author 陈建平
	 * @param dealerId
	 * @return
	 */
    List<Integer> findDealNoBy(String dealerId);
    
    /**
     * 根据经销商ID删除所有对应的品类编号
     * @author 陈建平
     * @param dealerId
     */
    void deleteByDealerInfoId(String dealerId);
    
    
    /**
     * 查询经销商经营品类信息 实体对象分类信息
     * @param dealerId
     * @return
     */
    List<DealDic> findbyId(String dealerId);
}
