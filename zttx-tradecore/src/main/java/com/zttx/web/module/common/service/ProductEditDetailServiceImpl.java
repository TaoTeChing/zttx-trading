/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import com.zttx.web.dubbo.service.CateAttributeItemDubboConsumer;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.CateAttributeItem;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.ProductEdit;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
import com.zttx.web.module.common.entity.ProductEditDetail;
import com.zttx.web.module.common.mapper.ProductEditAuditLogMapper;
import com.zttx.web.module.common.mapper.ProductEditDetailMapper;
import com.zttx.web.module.common.mapper.ProductEditMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 产品修改详情 服务实现类
 * <p>File：ProductEditDetail.java </p>
 * <p>Title: ProductEditDetail </p>
 * <p>Description:ProductEditDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductEditDetailServiceImpl extends GenericServiceApiImpl<ProductEditDetail> implements ProductEditDetailService
{
    private ProductEditDetailMapper        productEditDetailMapper;
    
    @Autowired
    private ProductInfoDubboConsumer       productInfoDubboConsumer;
    
    @Autowired
    private ProductEditMapper              productEditMapper;
    
    @Autowired
    private ProductSkuInfoDubboConsumer    productSkuInfoDubboConsumer;
    
    @Autowired
    private CateAttributeItemDubboConsumer cateAttributeItemDubboConsumer;
    
    @Autowired
    private ProductEditAuditLogMapper      productEditAuditLogMapper;
    
    @Autowired
    private ProductInfoService             productInfoService;
    
    @Autowired(required = true)
    public ProductEditDetailServiceImpl(ProductEditDetailMapper productEditDetailMapper)
    {
        super(productEditDetailMapper);
        this.productEditDetailMapper = productEditDetailMapper;
    }
    
    @Override
    public ProductEditDetail executeSelect(ProductEditDetail param) throws BusinessException
    {
        if (null == param || StringUtils.isBlank(param.getProductId()) || null == param.getChangeType()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductEditDetail editDetail = productEditDetailMapper.find(param.getProductId(), param.getChangeType(), param.getVid());
        if (null == editDetail) { return null; }
        return editDetail;
    }
    
    @Override
    public ProductEditDetail executeSave(ProductEditDetail param) throws BusinessException
    {
        if (null == param) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 修改产品审核状态为待审
        String productId = param.getProductId();
        ProductEdit edit = productEditMapper.selectByPrimaryKey(productId);
        if (null == edit)
        {
            edit = new ProductEdit();
            edit.setRefrenceId(productId);
            edit.setState(BrandConstant.ProductEdit.STATE_UNAUDIT);
            edit.setUpdateTime(CalendarUtils.getCurrentLong());
            edit.setCreateTime(CalendarUtils.getCurrentLong());
            productEditMapper.insert(edit);
        }
        else
        {
            edit.setState(BrandConstant.ProductEdit.STATE_UNAUDIT);
            edit.setUpdateTime(CalendarUtils.getCurrentLong());
            productEditMapper.updateByPrimaryKeySelective(edit);
        }
        // 创建产品修改详情
        ProductEditDetail editDetail = create(param);
        return editDetail;
    }
    
    private ProductEditDetail create(ProductEditDetail param) throws BusinessException
    {
        if (null == param.getChangeType()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductEditDetail oldEditDetail = productEditDetailMapper.find(param.getProductId(), param.getChangeType(), param.getVid());
        if (null != oldEditDetail) { throw new BusinessException(CommonConst.DATA_EXISTS); }
        ProductEditDetail editDetail = new ProductEditDetail();
        editDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        editDetail.setProductId(param.getProductId());
        editDetail.setChangeType(param.getChangeType());
        editDetail.setState(BrandConstant.ProductEditDetail.STATE_UNAUDIT);
        editDetail.setUpdateTime(CalendarUtils.getCurrentLong());
        editDetail.setCreateTime(CalendarUtils.getCurrentLong());
        if (BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_NO.equals(param.getChangeType()))
        {
            fillProductNoInfo(editDetail, param);
        }
        else if (BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_COLOR.equals(param.getChangeType()))
        {
            fillProductColorInfo(editDetail, param);
        }
        else if (BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_SIZE.equals(param.getChangeType()))
        {
            fillProductSizeInfo(editDetail, param);
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        productEditDetailMapper.insert(editDetail);
        return editDetail;
    }
    
    private void fillProductNoInfo(ProductEditDetail editDetail, ProductEditDetail param) throws BusinessException
    {
        if (StringUtils.isBlank(param.getNewValue())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductBaseInfo productInfo = productInfoDubboConsumer.getProductById(param.getProductId());
        if (null == productInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        editDetail.setOldValue(productInfo.getProductNo());
        editDetail.setNewValue(param.getNewValue());
    }
    
    private void fillProductColorInfo(ProductEditDetail editDetail, ProductEditDetail param) throws BusinessException
    {
        if (StringUtils.isBlank(param.getNewValue()) || StringUtils.isBlank(param.getVid())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        List<ProductAttrValue> productAttrValues = productSkuInfoDubboConsumer.findAttrValuebyProductIdAndCateAttributeItemId(param.getProductId(), param.getVid(), true);
        // List<ProductAttrValue> productAttrValues = productAttrValueDao.findByAttrItemId(param.getProductId(), param.getVid(), true);
        for (ProductAttrValue productAttrValue : productAttrValues)
        {
            editDetail.setOldValue(productAttrValue.getExtAttributeValue());
            editDetail.setNewValue(param.getNewValue());
            editDetail.setVid(param.getVid());
            editDetail.setAttributeIcon(param.getAttributeIcon());
            if (StringUtils.isNotBlank(param.getVid()))
            {
                CateAttributeItem attrItem = cateAttributeItemDubboConsumer.selectByPrimaryKey(param.getVid());
                // CateAttributeItem attrItem = (CateAttributeItem) cateAttributeItemDao.load(CateAttributeItem.class, param.getVid());
                if (null != attrItem && StringUtils.isNotBlank(attrItem.getAttributeIcon()))
                {
                    editDetail.setAttributeIcon(attrItem.getAttributeIcon());
                }
            }
            break;
        }
    }
    
    private void fillProductSizeInfo(ProductEditDetail editDetail, ProductEditDetail param) throws BusinessException
    {
        if (StringUtils.isBlank(param.getNewValue()) || StringUtils.isBlank(param.getVid())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // List<ProductAttrValue> productAttrValues = productAttrValueDao.findByAttrItemId(param.getProductId(), param.getVid(), true);
        List<ProductAttrValue> productAttrValues = productSkuInfoDubboConsumer.findAttrValuebyProductIdAndCateAttributeItemId(param.getProductId(), param.getVid(), true);
        for (ProductAttrValue productAttrValue : productAttrValues)
        {
            editDetail.setOldValue(productAttrValue.getExtAttributeValue());
            editDetail.setNewValue(param.getNewValue());
            editDetail.setVid(param.getVid());
            break;
        }
    }
    
    @Override
    public PaginateResult<Map<String, Object>> searchEditList(ProductEditDetail editDetail)
    {
        List<Map<String, Object>> list = productEditDetailMapper.searchEditList(editDetail);
        for (Map<String, Object> map : list)
        {
            Short changeType = MapUtils.getShort(map, "changeType");
            map.put("changeTypeName", BrandConstant.ProductEditDetail.CHANGE_TYPE_NAMES[changeType]);
        }
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>();
        result.setList(list);
        result.setPage(editDetail.getPage());
        return result;
    }
    
    private ProductEditDetail findByIdWithException(String editId) throws BusinessException
    {
        if (StringUtils.isBlank(editId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductEditDetail editDetail = (ProductEditDetail) productEditDetailMapper.selectByPrimaryKey(editId);
        if (null == editDetail) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        return editDetail;
    }
    
    @Override
    public void executePassAudit(String editId, String operateId, String operateName) throws BusinessException
    {
        // 更改记录状态
        ProductEditDetail editDetail = findByIdWithException(editId);
        editDetail.setState(BrandConstant.ProductEditDetail.STATE_PASSAUDIT);
        editDetail.setCheckResult(null);
        editDetail.setUpdateTime(CalendarUtils.getCurrentLong());
        productEditDetailMapper.updateByPrimaryKeySelective(editDetail);
        // 修改产品属性
        productInfoService.executeChangeProductInfo(editDetail);
        // 记录日志
        StringBuffer content = new StringBuffer();
        content.append(operateName).append("审核通过了将").append(BrandConstant.ProductEditDetail.CHANGE_TYPE_NAMES[editDetail.getChangeType()]).append("\"")
                .append(editDetail.getOldValue()).append("\"").append("修改为").append("\"").append(editDetail.getNewValue()).append("\"");
        ProductEditAuditLog log = new ProductEditAuditLog();
        log.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        log.setProductId(editDetail.getProductId());
        log.setContent(content.toString());
        log.setOperateId(operateId);
        log.setCreateTime(CalendarUtils.getCurrentLong());
        productEditAuditLogMapper.insert(log);
        // 刷新产品编辑的审核状态
        Integer finish = productEditDetailMapper.countUnFinish(editDetail.getProductId());
        if (finish > 0) { return; }
        ProductEdit edit = productEditMapper.selectByPrimaryKey(editDetail.getProductId());
        if (null == edit) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        edit.setState(BrandConstant.ProductEdit.STATE_PASSAUDIT);
        edit.setUpdateTime(CalendarUtils.getCurrentLong());
        productEditMapper.updateByPrimaryKeySelective(edit);
    }
    
    @Override
    public void executeUnPassAudit(String editId, String result, String operateId, String operateName) throws BusinessException
    {
        if (StringUtils.isBlank(result)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 更改记录状态
        ProductEditDetail editDetail = findByIdWithException(editId);
        editDetail.setState(BrandConstant.ProductEditDetail.STATE_UNPASSAUDIT);
        editDetail.setCheckResult(result);
        editDetail.setUpdateTime(CalendarUtils.getCurrentLong());
        productEditDetailMapper.updateByPrimaryKeySelective(editDetail);
        // 记录日志
        StringBuffer content = new StringBuffer();
        content.append(operateName).append("审核拒绝了将").append(BrandConstant.ProductEditDetail.CHANGE_TYPE_NAMES[editDetail.getChangeType()]).append("\"")
                .append(editDetail.getOldValue()).append("\"").append("修改为").append("\"").append(editDetail.getNewValue()).append("\"").append("，拒绝理由：").append(result);
        ProductEditAuditLog log = new ProductEditAuditLog();
        log.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        log.setProductId(editDetail.getProductId());
        log.setContent(content.toString());
        log.setOperateId(operateId);
        log.setCreateTime(CalendarUtils.getCurrentLong());
        productEditAuditLogMapper.insert(log);
        // 刷新产品编辑的审核状态
        Integer finish = productEditDetailMapper.countUnFinish(editDetail.getProductId());
        if (finish > 0) { return; }
        ProductEdit edit = productEditMapper.selectByPrimaryKey(editDetail.getProductId());
        if (null == edit) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        edit.setState(BrandConstant.ProductEdit.STATE_PASSAUDIT);
        edit.setUpdateTime(CalendarUtils.getCurrentLong());
        productEditMapper.updateByPrimaryKeySelective(edit);
    }
}
