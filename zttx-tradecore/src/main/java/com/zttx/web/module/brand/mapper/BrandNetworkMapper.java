/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.dealer.entity.DealerInfo;
import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandNetwork;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌经销网络 持久层接口
 * <p>File：BrandNetworkDao.java </p>
 * <p>Title: BrandNetworkDao </p>
 * <p>Description:BrandNetworkDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandNetworkMapper extends GenericMapper<BrandNetwork>
{

    /**
     * 根据品牌id查询品牌相关的门店及图片
     * @param brandesId
     * @return
     */
    public List<BrandNetwork> selectNetworkAndImgByBrandesId(String brandesId);

    /**
     * 更新
     * @param refrenceId
     */
    public void updateShowFlag(BrandNetwork brandNetwork);

    /**
     * 更新NetworkLevel信息通过brandsId，brandId，，levelId,经营商id 一直在变化dealerId
     * @param brandId
     * @param brandsId
     * @param dealerId
     * @param levelId
     */
    public void updateNetworkLevel(@Param("brandId")String brandId,@Param("brandsId") String brandsId, @Param("dealerId") String dealerId, @Param("levelId") String levelId);

    /**
     * 查询销售网信息
     *
     * @param brandId
     * @param brandsId
     * @param dealerId
     * @param showFlag
     * @return
     */
    public BrandNetwork getBrandNetwork(@Param("brandId")String brandId,@Param("brandsId") String brandsId,@Param("dealerId") String dealerId,@Param("showFlag") Boolean showFlag);

    /**
     * 验证销售网络是否存在
     *
     * @param brandNetwork
     * @return
     */
    public Integer isExistNetworkDealer(@Param("brandNetwork")BrandNetwork brandNetwork);

    DealerInfo getDealerInfoById(String dealerId);

    BrandLevel getBrandLevelById(String s);
}
