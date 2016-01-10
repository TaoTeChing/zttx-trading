/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandFreightDetail;
import com.zttx.web.module.brand.entity.BrandFreightRegion;
import com.zttx.web.module.brand.entity.BrandFreightSettings;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.mapper.BrandFreightDetailMapper;
import com.zttx.web.module.brand.mapper.BrandFreightRegionMapper;
import com.zttx.web.module.brand.mapper.BrandFreightSettingsMapper;
import com.zttx.web.module.brand.mapper.BrandFreightTemplateMapper;
import com.zttx.web.module.brand.model.BrandFreightParamModel;
import com.zttx.web.module.brand.model.BrandFreightQueryModel;
import com.zttx.web.module.brand.model.BrandFreightResultModel;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.utils.ValidateUtils;

/**
 * 运费模板表 服务实现类
 * <p/>
 * <p>File：BrandFreightTemplate.java </p>
 * <p>Title: BrandFreightTemplate </p>
 * <p>Description:BrandFreightTemplate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Service
public class BrandFreightTemplateServiceImpl extends GenericServiceApiImpl<BrandFreightTemplate> implements BrandFreightTemplateService
{
    private BrandFreightTemplateMapper  brandFreightTemplateMapper;
    
    @Autowired
    private BrandFreightDetailMapper    brandFreightDetailMapper;
    
    @Autowired
    private BrandFreightSettingsMapper  brandFreightSettingsMapper;
    
    @Autowired
    private BrandFreightRegionMapper    brandFreightRegionMapper;
    
    @Autowired
    private RegionalService             regionalService;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private BrandFreightSettingsService brandFreightSettingsService;
    
    @Autowired
    private BrandFreightDetailService   brandFreightDetailService;
    
    @Autowired(required = true)
    public BrandFreightTemplateServiceImpl(BrandFreightTemplateMapper brandFreightTemplateMapper)
    {
        super(brandFreightTemplateMapper);
        this.brandFreightTemplateMapper = brandFreightTemplateMapper;
    }
    
    /**
     * 获取品牌商运费模板编号
     *
     * @param brandId
     * @return
     */
    @Override
    public int getTemplateNumber(String brandId)
    {
        return brandFreightTemplateMapper.getTemplateNumber(brandId);
    }
    
    /**
     * 品牌商运费模板列表
     *
     * @param brandFreightTemplate
     * @return
     */
    @Override
    public List<BrandFreightTemplate> findTemplateData(BrandFreightTemplate brandFreightTemplate)
    {
        List<BrandFreightTemplate> templateList = brandFreightTemplateMapper.findList(brandFreightTemplate);
        if (null == templateList) { return null; }
        for (BrandFreightTemplate templateObj : templateList)
        {
            String templateId = templateObj.getRefrenceId();
            // 根据编号获取区域全称
            String fullAreaName = regionalService.getFullNameByAreaNo(templateObj.getAreaNo(), " ");
            // 查询是否支持到付
            List<BrandFreightSettings> settingsList = brandFreightSettingsMapper.getCarryType(templateId);
            for (BrandFreightSettings setting : settingsList)
            {
                if (setting.getCarryType() == 3)
                {
                    templateObj.setExpressCollect(BrandConstant.BrandFreight.CARRY_NAME_EXPRESS_COLLECT);// 快递到付
                }
                if (setting.getCarryType() == 4)
                {
                    templateObj.setLogisticsCollect(BrandConstant.BrandFreight.CARRY_NAME_LOGISTICS_COLLECT);// 物流到付
                }
            }
            templateObj.setFullAreaName(fullAreaName);
            List<Map<String, Object>> listMapDetail = this.getTempalateDetailData(templateId);
            templateObj.setListMapDetail(listMapDetail);
        }
        return templateList;
    }
    
    /**
     * 查询特定template 下的详细信息
     *
     * @param templateId
     * @return
     */
    public List<Map<String, Object>> getTempalateDetailData(String templateId)
    {
        List<Map<String, Object>> listMapDetail = new ArrayList<Map<String, Object>>();
        List<BrandFreightSettings> listSettings = brandFreightSettingsMapper.listByTemplateId(templateId);
        for (BrandFreightSettings settings : listSettings)
        {
            String settingId = settings.getRefrenceId();
            List<BrandFreightDetail> listDetail = brandFreightDetailMapper.listBySettingsId(settingId);
            if (null != listDetail)
            {
                for (BrandFreightDetail detail : listDetail)
                {
                    String detailId = detail.getRefrenceId();
                    List<BrandFreightRegion> listRegion = brandFreightRegionMapper.listByDetailId(detailId);
                    String areaNos = null;
                    String arriveAddress = null;
                    String carryTypeName = null;
                    for (BrandFreightRegion region : listRegion)
                    {
                        if (null == region.getAreaNo())
                        {
                            continue;
                        }
                        areaNos = region.getAreaNo().toString() + "," + areaNos;
                        String areaName = region.getAreaName();
                        if (org.apache.commons.lang3.StringUtils.isBlank(areaName))
                        {
                            continue;
                        }
                        arriveAddress = areaName + "," + arriveAddress;
                    }
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(areaNos))
                    {
                        detail.setAreaNos(areaNos.substring(0, areaNos.lastIndexOf(",")));
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(arriveAddress))
                        {
                            detail.setArriveAddress(arriveAddress.substring(0, arriveAddress.lastIndexOf(",")));
                        }
                    }
                    if (detail.getCarryType() == BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS)
                    {
                        carryTypeName = BrandConstant.BrandFreight.CARRY_NAME_EXPRESS;
                    }
                    else if (detail.getCarryType() == BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS)
                    {
                        carryTypeName = BrandConstant.BrandFreight.CARRY_NAME_LOGISTICS;
                    }
                    Map<String, Object> mapDetail = Maps.newHashMap();
                    mapDetail.put("carryType", detail.getCarryType());
                    mapDetail.put("carryTypeName", carryTypeName);
                    mapDetail.put("arriveAreaNos", detail.getAreaNos());
                    mapDetail.put("arriveAddress", detail.getArriveAddress());
                    mapDetail.put("firstWeight", detail.getFirstWeight());
                    mapDetail.put("firstPrice", detail.getFirstPrice());
                    mapDetail.put("extendWeight", detail.getExtendWeight());
                    mapDetail.put("extendPrice", detail.getExtendPrice());
                    mapDetail.put("isDefault", detail.getIsDefault());
                    listMapDetail.add(mapDetail);
                }
            }
        }
        return listMapDetail;
    }
    
    /**
     * 获取品牌商运费模板详情
     *
     * @param templateId
     * @param brandId
     * @return
     */
    @Override
    public BrandFreightParamModel getTempalateAndDetailData(String templateId, String brandId)
    {
        // brandFreightTemplateMapper.findTemplate(templateId, brandId);
        BrandFreightParamModel paramModel = new BrandFreightParamModel();
        BrandFreightTemplate template = brandFreightTemplateMapper.findTemplate(templateId, brandId);
        if (null != template)
        {
            if (null != template.getAreaNo())
            {
                template.setFullAreaName(regionalService.getFullNameByAreaNo(template.getAreaNo(), " "));
            }
        }
        boolean isExpressCollectUsed = false;
        boolean isLogisticsCollectUsed = false;
        // 获取是否含有快递到付,物流到付,现在只有修改时,才会查询该数据
        List<BrandFreightSettings> listSettings = brandFreightSettingsMapper.listByTemplateId(templateId);
        for (BrandFreightSettings settings : listSettings)
        {
            if (null != settings.getCarryType() && settings.getCarryType() == BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT)
            {
                isExpressCollectUsed = true;
            }
            if (null != settings.getCarryType() && settings.getCarryType() == BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT)
            {
                isLogisticsCollectUsed = true;
            }
        }
        List<Map<String, Object>> maplistDetail = this.getTempalateDetailData(templateId);
        List<BrandFreightDetail> listDetail = new ArrayList<BrandFreightDetail>();
        for (Map<String, Object> mapDetail : maplistDetail)
        {
            BrandFreightDetail bfDetail = new BrandFreightDetail();
            bfDetail.setCarryType((Integer) mapDetail.get("carryType"));
            bfDetail.setAreaNos((String) mapDetail.get("arriveAreaNos"));
            bfDetail.setArriveAddress((String) mapDetail.get("arriveAddress"));
            bfDetail.setFirstWeight((BigDecimal) mapDetail.get("firstWeight"));
            bfDetail.setFirstPrice((BigDecimal) mapDetail.get("firstPrice"));
            bfDetail.setExtendWeight((BigDecimal) mapDetail.get("extendWeight"));
            bfDetail.setExtendPrice((BigDecimal) mapDetail.get("extendPrice"));
            bfDetail.setIsDefault((Short) mapDetail.get("isDefault"));
            listDetail.add(bfDetail);
        }
        boolean isExpressUsed = false;
        boolean isLogisticsUsed = false;
        BrandFreightDetail defaultExpress = null;
        BrandFreightDetail defaultLogistics = null;
        List<BrandFreightDetail> expressList = new ArrayList<BrandFreightDetail>();
        List<BrandFreightDetail> logisticsList = new ArrayList<BrandFreightDetail>();
        for (BrandFreightDetail detail : listDetail)
        {
            if (detail.getCarryType() == 1) // 1是快递
            {
                if (isExpressUsed == false) // 确认快递
                {
                    isExpressUsed = true;
                }
                if (detail.getIsDefault() == 1) // 确认快递默认模板
                {
                    defaultExpress = detail;
                }
                else
                { // 除模板以外的区域运费模板 0
                    expressList.add(detail);
                }
            }
            if (detail.getCarryType() == 2) // 2是物流
            {
                if (isLogisticsUsed == false)
                {
                    isLogisticsUsed = true;
                }
                if (detail.getIsDefault() == 1) // 确认物流默认模板
                {
                    defaultLogistics = detail;
                }
                else
                {
                    logisticsList.add(detail);
                }
            }
        }
        paramModel.setTemplate(template);
        paramModel.setIsExpressUsed(isExpressUsed);
        paramModel.setDefaultExpress(defaultExpress);
        paramModel.setExpressList(expressList);
        paramModel.setIsLogisticsUsed(isLogisticsUsed);
        paramModel.setDefaultLogistics(defaultLogistics);
        paramModel.setLogisticsList(logisticsList);
        paramModel.setIsExpressCollectUsed(isExpressCollectUsed);
        paramModel.setIsLogisticsCollectUsed(isLogisticsCollectUsed);
        return paramModel;
    }
    
    /**
     * 保存模板信息，如果是修改模板，快递和物流数据采用全删全插方式
     *
     * @param paramModel
     * @param brandId
     * @return
     * @throws BusinessException
     */
    @Override
    public String insertBrandFreight(BrandFreightParamModel paramModel, String brandId) throws BusinessException
    {
        if (null == paramModel || null == paramModel.getTemplate()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 检查运费模板的区域是否重复
        if ((null != paramModel.getExpressList() && this.isExistAreaNo(paramModel.getExpressList()))
                || (null != paramModel.getLogisticsList() && this.isExistAreaNo(paramModel.getLogisticsList()))) { throw new BusinessException(
                CommonConst.FREIGHT_TEMPLATE_AREANO_EXIST); }
        // 保存模板
        BrandFreightTemplate template;
        if (StringUtils.isBlank(paramModel.getTemplate().getRefrenceId()))
        {
            template = this.insertBrandFreight(paramModel.getTemplate(), brandId);
        }
        else
        {
            brandFreightRegionMapper.removeByTemplateId(paramModel.getTemplate().getRefrenceId());
            brandFreightDetailMapper.removeByTemplateId(paramModel.getTemplate().getRefrenceId());
            brandFreightSettingsMapper.removeByTemplateId(paramModel.getTemplate().getRefrenceId());
            template = this.updateBrandFreight(paramModel.getTemplate(), brandId);
        }
        // 保存快递
        if (null != paramModel.getIsExpressUsed() && paramModel.getIsExpressUsed())
        {
            BrandFreightSettings settings = this.insertSettingForTempleat(template, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS);
            // 保存默认快递
            this.insertBrandFreightDefaultDetail(template, settings, paramModel.getDefaultExpress(), BrandConstant.BrandFreightDetail.IS_DEFAULT_YES);
            // 保存非默认快递
            this.insertBrandFreightNotDefaultDetail(template, settings, paramModel.getExpressList(), BrandConstant.BrandFreightDetail.IS_DEFAULT_NO);
        }
        // 保存物流
        if (null != paramModel.getIsLogisticsUsed() && paramModel.getIsLogisticsUsed())
        {
            BrandFreightSettings settings = this.insertSettingForTempleat(template, BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS);
            this.insertBrandFreightDefaultDetail(template, settings, paramModel.getDefaultLogistics(), BrandConstant.BrandFreightDetail.IS_DEFAULT_YES);
            this.insertBrandFreightNotDefaultDetail(template, settings, paramModel.getLogisticsList(), BrandConstant.BrandFreightDetail.IS_DEFAULT_NO);
        }
        // 保存快递到付
        if (null != paramModel.getIsExpressCollectUsed() && paramModel.getIsExpressCollectUsed())
        {
            this.insertSettingForTempleat(template, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT);
        }
        // 保存物流到付
        if (null != paramModel.getIsLogisticsCollectUsed() && paramModel.getIsLogisticsCollectUsed())
        {
            this.insertSettingForTempleat(template, BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT);
        }
        return template.getRefrenceId();
    }
    
    /**
     * 验证区域编号是否合法
     *
     * @param detailList
     * @return
     */
    private Boolean isExistAreaNo(List<BrandFreightDetail> detailList)
    {
        List<String> areaNoList = Lists.newArrayList();
        this.isExistAreaNoAdd(areaNoList, detailList);
        Set<String> areaNoSet = Sets.newHashSet(areaNoList);
        return areaNoSet.size() != areaNoList.size();
    }
    
    /**
     * 获取区域编码字符串
     *
     * @param list   这是方法返回值！！！！！
     * @param detail
     */
    private void isExistAreaNoAdd(List<String> list, BrandFreightDetail detail)
    {
        if (StringUtils.isBlank(detail.getAreaNos())) { return; }
        String[] areaNoArr = detail.getAreaNos().split(",");
        for (String areaNo : areaNoArr)
        {
            list.add(areaNo);
        }
    }
    
    /**
     * 获取区域编码字符串
     *
     * @param list       这是方法返回值！！！！！
     * @param detailList
     */
    private void isExistAreaNoAdd(List<String> list, List<BrandFreightDetail> detailList)
    {
        if (CollectionUtils.isEmpty(detailList)) { return; }
        for (BrandFreightDetail detail : detailList)
        {
            isExistAreaNoAdd(list, detail);
        }
    }
    
    /**
     * 保存模板数据
     *
     * @param param
     * @param brandId
     * @return
     * @throws BusinessException
     */
    private BrandFreightTemplate insertBrandFreight(BrandFreightTemplate param, String brandId) throws BusinessException
    {
        if (StringUtils.isBlank(param.getName()) || null == param.getAreaNo()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 校验品牌商下的模板名称是否存在
        if (this.isExistTemplateName(param.getName(), null, brandId)) { throw new BusinessException(CommonConst.FREIGHT_TEMPLATE_NAME_EXIST); }
        BrandFreightTemplate template = new BrandFreightTemplate();
        template.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        template.setBrandId(brandId);
        template.setName(param.getName());
        template.setAreaNo(param.getAreaNo());
        template.setIsDefault(BrandConstant.BrandFreightTemplate.IS_DEFAULT_YES);
        // 如果有品牌商id，是普通模板，如果没有id是默认模板
        if (StringUtils.isBlank(brandId))
        {
            template.setIsRecommend(BrandConstant.BrandFreightTemplate.IS_RECOMMEND_YES);
        }
        else
        {
            template.setIsRecommend(BrandConstant.BrandFreightTemplate.IS_RECOMMEND_NO);
        }
        template.setCreateTime(CalendarUtils.getCurrentLong());
        template.setUpdateTime(CalendarUtils.getCurrentLong());
        // 保存模板数据
        brandFreightTemplateMapper.insert(template);
        return template;
    }
    
    /**
     * 更新模板信息，用于编辑页面使用
     *
     * @param param
     * @param brandId
     * @return
     * @throws BusinessException
     */
    private BrandFreightTemplate updateBrandFreight(BrandFreightTemplate param, String brandId) throws BusinessException
    {
        if (StringUtils.isBlank(param.getName()) || null == param.getAreaNo()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 检查运费模板的名称是否重复
        if (isExistTemplateName(param.getName(), param.getRefrenceId(), brandId)) { throw new BusinessException(CommonConst.FREIGHT_TEMPLATE_NAME_EXIST); }
        BrandFreightTemplate template = brandFreightTemplateMapper.selectByPrimaryKey(param.getRefrenceId());
        template.setName(param.getName());
        template.setAreaNo(param.getAreaNo());
        template.setUpdateTime(CalendarUtils.getCurrentLong());
        brandFreightTemplateMapper.updateByPrimaryKey(template);
        return template;
    }
    
    /**
     * 查询品牌商是否有重名模板
     *
     * @param templateName
     * @param templateId
     * @param brandId
     * @return
     */
    @Override
    public Boolean isExistTemplateName(String templateName, String templateId, String brandId)
    {
        Map<String, String> templMap = new HashMap<String, String>();
        templMap.put("templateName", templateName);
        templMap.put("templateId", templateId);
        templMap.put("brandId", brandId);
        BrandFreightTemplate template = brandFreightTemplateMapper.isExistTemplateName(templMap);
        if (template != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 删除品牌商模板
     *
     * @param templateId
     * @param brandId
     * @throws BusinessException
     */
    @Override
    public void deleteTemplate(String templateId, String brandId) throws BusinessException
    {
        BrandFreightTemplate template = brandFreightTemplateMapper.findTemplate(templateId, brandId);
        if (null == template) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 商品中心接口，判断模板是否被商品使用，如果没使用可以删除
        if (productInfoDubboConsumer.isTemplateUsed(template.getRefrenceId())) { throw new BusinessException(CommonConst.FREIGHT_TEMPLATE_DELETE_APPLY); }
        brandFreightRegionMapper.removeByTemplateId(template.getRefrenceId());
        brandFreightDetailMapper.removeByTemplateId(template.getRefrenceId());
        brandFreightSettingsMapper.removeByTemplateId(template.getRefrenceId());
        brandFreightTemplateMapper.delete(template.getRefrenceId());
    }
    
    /* ========================================= 经销商运费模版获取 [@author易永耀] begin================================================ */
    @Override
    public List<BrandFreightResultModel> getFreightAmount(BrandFreightQueryModel queryModel) throws BusinessException
    {
        if (null == queryModel || null == queryModel.getAreaNo() || CollectionUtils.isEmpty(queryModel.getProductParamList())) { throw new BusinessException(
                CommonConst.PARAMS_VALID_ERR); }
        // 获取计算所需要的信息
        fillInfo(queryModel);
        // 是否所有产品都包邮
        if (queryModel.isAllFreeFreight())
        {
            List<BrandFreightResultModel> resultModelList = Lists.newArrayList();
            resultModelList.add(BrandFreightResultModel.getFreeFreight());
            return resultModelList;
        }
        List<BrandFreightQueryModel> brandsQueryModelList = queryModel.getBrandsQueryModelList();
        if (brandsQueryModelList.size() > 1) { throw new BusinessException(CommonConst.FAIL.getCode(), "不能存在多个品牌"); }
        return getBrandsResultModelList(brandsQueryModelList.get(0));
    }
    
    /**
     * 获取品牌商ID，品牌ID, 产品重量和模板ID
     * @author 张昌苗
     */
    private void fillInfo(BrandFreightQueryModel queryModel) throws BusinessException
    {
        for (BrandFreightQueryModel.ProductParam productParam : queryModel.getProductParamList())
        {
            ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productParam.getProductId());
            ProductExtInfo productExtInfo = productBaseInfo.getProductExtInfo();
            if (null == productBaseInfo || null == productExtInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            productParam.setBrandId(productBaseInfo.getBrandId());
            productParam.setBrandsId(productBaseInfo.getBrandsId());
            if (null == productExtInfo.getProductWeight()) { throw new BusinessException(CommonConst.FAIL.getCode(), "产品重量不能为空," + productParam.getBrandsId()); }
            productParam.setProductWeight(new BigDecimal(productExtInfo.getProductWeight().toString()));
            productParam.setTemplateId(productExtInfo.getFreTemplateId());
            Boolean isFreeFreight = null == productBaseInfo.getProductCarry() ? false : "2".equals(productBaseInfo.getProductCarry().toString());
            productParam.setIsFreeFreight(isFreeFreight);
            if (!isFreeFreight || StringUtils.isNotBlank(productParam.getTemplateId()))
            {
                if (StringUtils.isBlank(productParam.getTemplateId()))
                {
                    BrandFreightTemplate template = brandFreightTemplateMapper.getDefaultTemplate(productParam.getBrandId());
                    if (null == template)
                    {
                        if (queryModel.getIsClean())
                        {
                            throw new BusinessException(CommonConst.FAIL.getCode(), "产品模板为空");
                        }
                        else
                        {
                            throw new BusinessException(CommonConst.FAIL.getCode(), "产品模板为空," + productParam.getBrandsId());
                        }
                    }
                    productParam.setTemplateId(template.getRefrenceId());
                }
                List<BrandFreightSettings> settingsList = brandFreightSettingsService.listByTemplateId(productParam.getTemplateId());
                for (BrandFreightSettings settings : settingsList)
                {
                    if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS == settings.getCarryType())
                    {
                        productParam.setIsExpressUsed(true);
                    }
                    if (BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS == settings.getCarryType())
                    {
                        productParam.setIsLogisticsUsed(true);
                    }
                    if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT == settings.getCarryType())
                    {
                        productParam.setIsExpressCollectUsed(true);
                    }
                    if (BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT == settings.getCarryType())
                    {
                        productParam.setIsLogisticsCollectUsed(true);
                    }
                }
            }
        }
    }
    
    private Boolean hasCollect(List<BrandFreightResultModel> resultModelList)
    {
        for (BrandFreightResultModel resultModel : resultModelList)
        {
            if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT == resultModel.getCarryType()
                    || BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT == resultModel.getCarryType()) { return true; }
        }
        return false;
    }
    
    private Boolean hasFreight(List<BrandFreightResultModel> resultModelList)
    {
        for (BrandFreightResultModel resultModel : resultModelList)
        {
            if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS == resultModel.getCarryType()
                    || BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS == resultModel.getCarryType()
                    || BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT == resultModel.getCarryType()) { return true; }
        }
        return false;
    }
    
    @Override
    public BrandFreightResultModel getFreightAmount(BrandFreightQueryModel queryModel, Integer carryType) throws BusinessException
    {
        if (null == carryType) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        BrandFreightResultModel tempResultModel = null;
        List<BrandFreightResultModel> resultModelList = getFreightAmount(queryModel);
        if (Lists.newArrayList(BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS,
                BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT,
                BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT).contains(carryType))
        {
            tempResultModel = BrandFreightResultModel.getResultModel(resultModelList, carryType);
        }
        else if (BrandConstant.BrandFreight.CARRY_TYPE_CLEAN_FREIGHT.equals(carryType))
        {
            tempResultModel = getCheapFreightResultModel(resultModelList);
        }
        else if (BrandConstant.BrandFreight.CARRY_TYPE_CLEAN_COLLECT.equals(carryType))
        {
            tempResultModel = getCheapCollectResultModel(resultModelList);
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        if (null == tempResultModel) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        return tempResultModel;
    }
    
    /**
     * 优先包邮，快递物流到付
     * @param resultModelList
     * @return
     * @author 张昌苗
     */
    private BrandFreightResultModel getCheapCollectResultModel(List<BrandFreightResultModel> resultModelList)
    {
        BrandFreightResultModel freeFreightResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT);
        BrandFreightResultModel expressCollectResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT);
        BrandFreightResultModel logisticsCollectResultModel = BrandFreightResultModel.getResultModel(resultModelList,
                BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT);
        if (null != freeFreightResultModel)
        {
            return freeFreightResultModel;
        }
        else if (null != expressCollectResultModel)
        {
            return expressCollectResultModel;
        }
        else
        {
            return logisticsCollectResultModel;
        }
    }
    
    /**
     * 优先包邮，快递物流运费
     * @param resultModelList
     * @return
     * @author 张昌苗
     */
    private BrandFreightResultModel getCheapFreightResultModel(List<BrandFreightResultModel> resultModelList)
    {
        BrandFreightResultModel freeFreightResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT);
        BrandFreightResultModel expressResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS);
        BrandFreightResultModel logisticsResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS);
        BrandFreightResultModel expressCollectResultModel = BrandFreightResultModel.getResultModel(resultModelList, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT);
        BrandFreightResultModel logisticsCollectResultModel = BrandFreightResultModel.getResultModel(resultModelList,
                BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT);
        if (null != freeFreightResultModel)
        {
            return freeFreightResultModel;
        }
        else if (null == expressResultModel && null == logisticsResultModel)
        {
            if (null != expressCollectResultModel)
            {
                return expressCollectResultModel;
            }
            else
            {
                return logisticsCollectResultModel;
            }
        }
        else if (null == logisticsResultModel)
        {
            return expressResultModel;
        }
        else if (null == expressResultModel)
        {
            return logisticsResultModel;
        }
        else if (expressResultModel.getFreightAmount().compareTo(logisticsResultModel.getFreightAmount()) < 0)
        {
            return expressResultModel;
        }
        else
        {
            return logisticsResultModel;
        }
    }
    
    private List<BrandFreightResultModel> getBrandsResultModelList(BrandFreightQueryModel queryModel) throws BusinessException
    {
        List<BrandFreightResultModel> resultModelList = Lists.newArrayList();
        if (queryModel.isAllFreeFreight())
        {
            resultModelList.add(BrandFreightResultModel.getFreeFreight());
            return resultModelList;
        }
        BrandFreightTemplate template = getFreightTemplate(queryModel);
        if (null != template)
        {
            List<BrandFreightSettings> settingsList = brandFreightSettingsService.listByTemplateId(template.getRefrenceId());
            for (BrandFreightSettings settings : settingsList)
            {
                BrandFreightResultModel resultModel = getBrandsResultModel(queryModel, template, settings);
                resultModelList.add(resultModel);
            }
        }
        else
        {
            BrandFreightResultModel expressResultModel = getBrandsResultModel(queryModel, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS);
            if (null != expressResultModel)
            {
                resultModelList.add(expressResultModel);
            }
            BrandFreightResultModel logisticsResultModel = getBrandsResultModel(queryModel, BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS);
            if (null != logisticsResultModel)
            {
                resultModelList.add(logisticsResultModel);
            }
            BrandFreightResultModel expressCollectResultModel = getBrandsResultModel(queryModel, BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT);
            if (null != expressCollectResultModel)
            {
                resultModelList.add(expressCollectResultModel);
            }
            BrandFreightResultModel logisticsCollectResultModel = getBrandsResultModel(queryModel, BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT);
            if (null != logisticsCollectResultModel)
            {
                resultModelList.add(logisticsCollectResultModel);
            }
        }
        return resultModelList;
    }
    
    private BrandFreightResultModel getBrandsResultModel(BrandFreightQueryModel queryModel, Integer carryType) throws BusinessException
    {
        BigDecimal freightAmount = BigDecimal.ZERO;
        if (BrandConstant.BrandFreight.CARRY_TYPE_EXPRESS_COLLECT == carryType.intValue())
        {
            if (!queryModel.isAllExpressCollect()) { return null; }
        }
        else if (BrandConstant.BrandFreight.CARRY_TYPE_LOGISTICS_COLLECT == carryType.intValue())
        {
            if (!queryModel.isAllLogisticsCollect()) { return null; }
        }
        else
        {
            String brandId = queryModel.getProductParamList().get(0).getBrandId();
            BigDecimal sumWeight = queryModel.getSumWeight();
            Integer areaNo = queryModel.getAreaNo();
            List<String> templateIdList = queryModel.getTemplateIdList();
            freightAmount = brandFreightDetailService.calculateCheapFreightAmount(brandId, templateIdList, areaNo, carryType, sumWeight);
            if (null == freightAmount) { return null; }
        }
        BrandFreightResultModel resultModel = new BrandFreightResultModel();
        resultModel.setCarryType(carryType);
        resultModel.setCarryName(BrandConstant.BrandFreight.CARRY_TYPE_NAME[carryType]);
        resultModel.setFreightAmount(freightAmount);
        return resultModel;
    }
    
    private BrandFreightResultModel getBrandsResultModel(BrandFreightQueryModel queryModel, BrandFreightTemplate template, BrandFreightSettings settings)
            throws BusinessException
    {
        BrandFreightDetail detail = brandFreightDetailService.getDetail(template.getRefrenceId(), settings.getCarryType(), queryModel.getAreaNo());
        BigDecimal freightAmount = brandFreightDetailService.calculateFreightAmount(detail, settings.getCarryType(), queryModel.getSumWeight());
        BrandFreightResultModel resultModel = new BrandFreightResultModel();
        resultModel.setCarryType(settings.getCarryType());
        resultModel.setCarryName(settings.getCarryTypeName());
        resultModel.setFreightAmount(freightAmount);
        return resultModel;
    }
    
    private BrandFreightTemplate getFreightTemplate(BrandFreightQueryModel queryModel) throws BusinessException
    {
        BrandFreightTemplate freightTemplate = null;
        // 如果是相同模板，取这个模板
        String sameTemplateId = queryModel.sameTemplateId();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(sameTemplateId))
        {
            freightTemplate = getTemplateByIdWithException(sameTemplateId);
        }
        // 获取默认模板
        if (null == freightTemplate)
        {
            String brandId = queryModel.getProductParamList().get(0).getBrandId();
            freightTemplate = brandFreightTemplateMapper.getDefaultTemplate(brandId);
        }
        return freightTemplate;
    }
    
    public BrandFreightTemplate getTemplateByIdWithException(String templateId) throws BusinessException
    {
        BrandFreightTemplate template = brandFreightTemplateMapper.selectByPrimaryKey(templateId);
        if (null == template) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        return template;
    }
    
    /* ========================================= 经销商运费模版获取 end ================================================ */
    /**
     * 保存模板设置信息
     *
     * @param template
     * @param carryType
     * @return
     */
    public BrandFreightSettings insertSettingForTempleat(BrandFreightTemplate template, Integer carryType)
    {
        BrandFreightSettings settings = new BrandFreightSettings();
        settings.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        settings.setTemplateId(template.getRefrenceId());
        settings.setCarryType(carryType);
        settings.setCarryTypeName(BrandConstant.BrandFreight.CARRY_TYPE_NAME[carryType]);
        settings.setCreateTime(com.zttx.web.utils.CalendarUtils.getCurrentLong());
        settings.setUpdateTime(com.zttx.web.utils.CalendarUtils.getCurrentLong());
        settings.setDelFlag(false);
        brandFreightSettingsMapper.insert(settings);
        return settings;
    }
    
    /**
     * 保存模板详情
     *
     * @param template
     * @param settings
     * @param brandFreightDetail
     * @param isDefault
     * @throws BusinessException
     */
    private void insertBrandFreightDefaultDetail(BrandFreightTemplate template, BrandFreightSettings settings, BrandFreightDetail brandFreightDetail, Short isDefault)
            throws BusinessException
    {
        if (null == brandFreightDetail.getFirstWeight() || brandFreightDetail.getFirstWeight().compareTo(BigDecimal.ZERO) < 0 || null == brandFreightDetail.getFirstPrice()
                || brandFreightDetail.getFirstPrice().compareTo(BigDecimal.ZERO) < 0 || null == brandFreightDetail.getExtendWeight()
                || brandFreightDetail.getExtendWeight().compareTo(BigDecimal.ZERO) < 0 || null == brandFreightDetail.getExtendPrice()
                || brandFreightDetail.getExtendPrice().compareTo(BigDecimal.ZERO) < 0)
        {
            // return; //老代码这样写！！
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        // 运费价格重量正则校验 修改重量或价格最大值时,要注意数据变成科学计数法,而无法匹配正则
        if (!ValidateUtils.isWight(brandFreightDetail.getFirstWeight().doubleValue(), 0.00, 10000000.00)
                || !ValidateUtils.isWight(brandFreightDetail.getExtendWeight().doubleValue(), 0.00, 10000000.00)) { throw new BusinessException(
                CommonConst.FREIGHT_WEIGHT_ERROR); }
        if (!ValidateUtils.isMoney(brandFreightDetail.getFirstPrice(), BigDecimal.valueOf(0.00), BigDecimal.valueOf(10000000.00))
                || !ValidateUtils.isMoney(brandFreightDetail.getExtendPrice(), BigDecimal.valueOf(0.00), BigDecimal.valueOf(10000000.00))) { throw new BusinessException(
                CommonConst.FREIGHT_MONEY_ERROR); }
        BrandFreightDetail detail = new BrandFreightDetail();
        detail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        detail.setTemplateId(template.getRefrenceId());
        detail.setSettingsId(settings.getRefrenceId());
        detail.setCarryType(settings.getCarryType());
        detail.setFirstWeight(brandFreightDetail.getFirstWeight());
        detail.setFirstPrice(brandFreightDetail.getFirstPrice());
        detail.setExtendWeight(brandFreightDetail.getExtendWeight());
        detail.setExtendPrice(brandFreightDetail.getExtendPrice());
        detail.setIsDefault(isDefault);
        detail.setCreateTime(com.zttx.web.utils.CalendarUtils.getCurrentLong());
        detail.setAreaNos(brandFreightDetail.getAreaNos());
        detail.setUpdateTime(CalendarUtils.getCurrentLong());
        // 保存模板详情数据
        brandFreightDetailMapper.insert(detail);
        // 如果不是推荐，批量插入详情数据，否则单独保存详情数据
        if (BrandConstant.BrandFreightDetail.IS_DEFAULT_NO == isDefault.intValue())
        {
            if (org.apache.commons.lang3.StringUtils.isBlank(brandFreightDetail.getAreaNos())) { throw new BusinessException(CommonConst.FREIGHT_REGION_AREANAME_EXIT_NO); }
            String[] areaNoArr = brandFreightDetail.getAreaNos().split(",");
            for (String areaNo : areaNoArr)
            {
                this.insertRegionInfo(template, detail, Integer.parseInt(areaNo));
            }
        }
        else
        {
            this.insertRegionInfo(template, detail, null);
        }
    }
    
    /**
     * 保存非默认模板详情
     *
     * @param template
     * @param settings
     * @param brandFreightDetailList
     * @param isDefault
     * @throws BusinessException
     */
    private void insertBrandFreightNotDefaultDetail(BrandFreightTemplate template, BrandFreightSettings settings, List<BrandFreightDetail> brandFreightDetailList,
            Short isDefault) throws BusinessException
    {
        if (CollectionUtils.isEmpty(brandFreightDetailList)) { return; }
        for (BrandFreightDetail detail : brandFreightDetailList)
        {
            this.insertBrandFreightDefaultDetail(template, settings, detail, isDefault);
        }
    }
    
    /**
     * 保存区域数据
     *
     * @param template
     * @param brandFreightDetail
     * @param areaNo
     */
    private void insertRegionInfo(BrandFreightTemplate template, BrandFreightDetail brandFreightDetail, Integer areaNo) throws BusinessException
    {
        BrandFreightRegion region = new BrandFreightRegion();
        if (areaNo != null)
        {
            String areaName;
            areaName = TagCommonConst.getAreaNameByNo(areaNo);
            if (org.apache.commons.lang3.StringUtils.isBlank(areaName)) { throw new BusinessException(CommonConst.FREIGHT_REGION_AREANAME_EXIT_NO); }
            region.setAreaName(areaName);
        }
        else
        {
            region.setAreaName(null);
        }
        region.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        region.setTemplateId(template.getRefrenceId());
        region.setDetailId(brandFreightDetail.getRefrenceId());
        region.setAreaNo(areaNo);
        region.setCreateTime(com.zttx.web.utils.CalendarUtils.getCurrentLong());
        region.setUpdateTime(CalendarUtils.getCurrentLong());
        brandFreightRegionMapper.insert(region);
    }
    
    /**
     * 获取brandId 下的template所有实体
     *
     * @param brandId
     * @return
     */
    public List<BrandFreightTemplate> listTemplate(String brandId)
    {
        if (org.apache.commons.lang3.StringUtils.isBlank(brandId)) { return null; }
        List<BrandFreightTemplate> listTemplate = brandFreightTemplateMapper.listTemplate(brandId);
        return listTemplate;
    }
}
