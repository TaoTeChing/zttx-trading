/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.model.BrandFreightParamModel;
import com.zttx.web.module.brand.model.BrandFreightQueryModel;
import com.zttx.web.module.brand.model.BrandFreightResultModel;

import java.util.List;

/**
 * 运费模板表 服务接口
 * <p>File：BrandFreightTemplateService.java </p>
 * <p>Title: BrandFreightTemplateService </p>
 * <p>Description:BrandFreightTemplateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
public interface BrandFreightTemplateService extends GenericServiceApi<BrandFreightTemplate> {

    /**
     * 获取brandId 下的template所有实体
     *
     * @param brandId
     * @return
     */
    public List<BrandFreightTemplate> listTemplate(String brandId);

    /**
     * 获取品牌商运费模板编号
     *
     * @param brandId
     * @return
     */
    int getTemplateNumber(String brandId);
    /**
     * 品牌商运费模板列表
     *
     * @param brandFreightTemplate
     * @return
     */
    List<BrandFreightTemplate> findTemplateData(BrandFreightTemplate brandFreightTemplate);
    /**
     * 获取品牌商运费模板详情
     *
     * @param templateId
     * @param brandId
     * @return
     */
    BrandFreightParamModel getTempalateAndDetailData(String templateId, String brandId);
    /**
     * 保存模板信息，如果是修改模板，快递和物流数据采用全删全插方式
     *
     * @param paramModel
     * @param brandId
     * @return
     * @throws BusinessException
     */
    String insertBrandFreight(BrandFreightParamModel paramModel, String brandId) throws BusinessException;
    /**
     * 查询品牌商是否有重名模板
     * @param templateName
     * @param templateId
     * @param brandId
     * @return
     */
    Boolean isExistTemplateName(String templateName, String templateId, String brandId);

    /**
     * 删除品牌商模板
     * @param templateId
     * @param brandId
     * @throws BusinessException
     */
    void deleteTemplate(String templateId, String brandId) throws BusinessException;

    /**
     * 查询运费
     * @param brandFreightQueryModel
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    List<BrandFreightResultModel> getFreightAmount(BrandFreightQueryModel brandFreightQueryModel) throws BusinessException;

    /**
     * 查询运费
     * @param queryModel
     * @param carryType
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public BrandFreightResultModel getFreightAmount(BrandFreightQueryModel queryModel, Integer carryType) throws BusinessException;

}
