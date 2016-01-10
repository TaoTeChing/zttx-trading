/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.model.BrandInviteView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 品牌商邀请经销商加盟 持久层接口
 * <p>File：BrandInviteDao.java </p>
 * <p>Title: BrandInviteDao </p>
 * <p>Description:BrandInviteDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandInviteMapper extends GenericMapper<BrandInvite>
{
    /**
     * 统计数量
     * @param brandInvite
     * @return
     */
    Long getBrandInviteCount(BrandInvite brandInvite);

    /**
     * 获取 终端商 加盟管理 申请加盟管理中的 申请中加盟的数据
     * @param brandInviteModel
     * @return
     */
    List<Map<String,Object>> getInviteApplyStateList(BrandInviteModel brandInviteModel);
    /**
     * 根据经销商id和品牌id获取邀请
     * @param dealerId
     * @param brandesId
     * @return
     */
    BrandInvite getByDealerIdAndBrandsId(@Param("dealerId")String dealerId, @Param("brandsId")String brandesId);

    /**
     * 查询品牌商加盟关系列表
     *
     * @param page
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    List<Map<String, Object>>search(@Param("page") Pagination page,@Param("info") BrandInviteView brandInvite)throws BusinessException;

    /**
     * 获取品牌商与终端商所有加盟信息
     *
     * @param dealerId
     * @param brandsId
     * @param brandId
     * @return
     */
    List<BrandInvite> getBrandInviteList(@Param("dealerId")String dealerId,@Param("brandsId")String brandsId,@Param("brandId")String brandId);

    /**
     * 校验加盟关系是否存在
     *
     * @param brandId
     * @param dealerId
     * @return
     */
    Integer isExist(@Param("brandId")String brandId,@Param("dealerId") String dealerId);

    /**
     * 申请和加盟加盟,用于dubbo接口使用
     *
     * @param pagination
     * @param brandInvite
     * @return
     * @throws Exception
     */
    public List<BrandInvite> applyOrInvite(@Param("page")Pagination pagination,@Param("invite")BrandInviteModel brandInvite)throws Exception;


    /**
     * 获取指定品牌商所有加盟数据
     *
     * @param brandInvite
     * @return
     */
    List<BrandInvite> getBrandInvites(@Param("brandInvite") BrandInvite brandInvite);

    /**
     * 获取指定品牌商所有加盟数据
     *
     * @param brandId
     * @return
     */
    List<BrandInvite> getBrandInvite_erp(@Param("brandId") String brandId);


}
