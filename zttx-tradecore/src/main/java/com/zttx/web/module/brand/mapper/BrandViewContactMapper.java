/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandViewContact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌商查看经销商联系信息 持久层接口
 * <p>File：BrandViewContactDao.java </p>
 * <p>Title: BrandViewContactDao </p>
 * <p>Description:BrandViewContactDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@MyBatisDao
public interface BrandViewContactMapper extends GenericMapper<BrandViewContact>
{
    /**
     * 经销商联系信息数量
     * @param brandViewContact
     * @return
     */
    Long getBrandViewContactCount(BrandViewContact brandViewContact);

    /**
     * 检测是否存在查看过联系方式的终端商
     *
     * @param brandId       品牌商编号
     * @param dealerId      终端商编号
     * @param viewType      来源类型
     * @return
     */
    Integer isExist(@Param("brandId")String brandId,@Param("dealerId") String dealerId, @Param("viewType")Short viewType);

    /**
     * 检测是否存在查看过联系方式的终端商
     *
     * @param brandViewContact
     * @return
     */
    BrandViewContact isExistForBrandViewContact(@Param("brandViewContact")BrandViewContact brandViewContact);

    /**
     * 查询品牌商查看经销商查看记录列表
     * @param brandViewContact
     * @return
     */
    List<BrandViewContact> queryBrandViewContactsList(@Param("brandViewContact")BrandViewContact brandViewContact,@Param("page") Pagination page);
}
