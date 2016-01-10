/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;

import java.util.List;
import java.util.Map;

/**
 * 运费模板表 持久层接口
 * <p>File：BrandFreightTemplateDao.java </p>
 * <p>Title: BrandFreightTemplateDao </p>
 * <p>Description:BrandFreightTemplateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandFreightTemplateMapper extends GenericMapper<BrandFreightTemplate>
{

    /**
     * 查询模板数量
     *
     * @param brandId
     * @return
     */
    int getTemplateNumber(String brandId);

    /**
     * 查询模板列表
     *
     * @param brandFreightTemplate
     * @return
     */
    List<BrandFreightTemplate> findList(BrandFreightTemplate brandFreightTemplate);

    /**
     * 查询模板信息
     *
     * @param templateId
     * @param brandId
     * @return
     */
    BrandFreightTemplate findTemplate(String templateId,String brandId);

    /**
     * 查询品牌商下模板名称是否存在
     *
     * @param templateMap
     * @return
     */
    BrandFreightTemplate isExistTemplateName(Map templateMap);


    /**
     * 查询模板列表
     * @param brandId
     * @return
     */
    List<BrandFreightTemplate> listTemplate(String brandId);

    /**
     * 获取品牌商默认模版
     * @param brandId
     * @return
     */
    BrandFreightTemplate getDefaultTemplate(String brandId);
}
