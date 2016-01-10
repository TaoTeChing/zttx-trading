/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerOrderc;
import com.zttx.web.module.dealer.model.ProductFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经销商产品进货计数 持久层接口
 * <p>File：DealerOrdercDao.java </p>
 * <p>Title: DealerOrdercDao </p>
 * <p>Description:DealerOrdercDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerOrdercMapper extends GenericMapper<DealerOrderc>
{

    /**
     * 根据 productFilter 虚拟参数获取经销商 常进款式 信息
     * @param filter
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectOrdercProductList(ProductFilter filter);
    /**
     * 经销商 常进款式 品牌类目
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectOrdercCata(String dealerId);

    /**
     * 查询 经销商常进货 信息

     * @return
     * @author 易永耀
     */
    DealerOrderc selectByIds(DealerOrderc dealerOrderc);

    /**
     * 查询进货产品id
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    List<String> countProCate(@Param("dealerId")String dealerId, @Param("brandId")String brandId,@Param("brandsId")String brandsId);

    /**
     * 统计经常进货款数量
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    List<String> countFreProCate(@Param("dealerId")String dealerId,@Param("brandId") String brandId,@Param("brandsId") String brandsId);

    /**
     * 分页查询授权产品信息
     *
     * @param dealerId
     * @param filter
     * @param filter
     * @return
     */
    List<Map<String, Object>> searchToMap(@Param("dealerId")String dealerId, @Param("page")Pagination pagination, @Param("filter")ProductFilter filter);

}
