/*
 * @(#)BrandFreightQueryModel.java 2014-12-22 上午9:25:10
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>File：BrandFreightQueryModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-22 上午9:25:10</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class BrandFreightQueryModel
{
    private List<ProductParam> productParamList = new ArrayList<ProductParam>();
    
    private Integer            areaNo;
    
    private Boolean            isClean;
    
    /**
     * 增加需计算的产品
     * @author 张昌苗
     */
    public void addProductParam(String productId, Integer productNum)
    {
        if (CollectionUtils.isEmpty(productParamList)) productParamList = Lists.newArrayList();
        ProductParam productParam = new ProductParam();
        productParam.productId = productId;
        productParam.productNum = productNum;
        productParamList.add(productParam);
    }
    
    /**
     * 根据品牌区分计算数据
     * @return
     * @author 张昌苗
     */
    public List<BrandFreightQueryModel> getBrandsQueryModelList()
    {
        List<BrandFreightQueryModel> brandsQueryModelList = Lists.newArrayList();
        List<String> brandsIdList = getBrandsIdList();
        for (String brandsId : brandsIdList)
        {
            BrandFreightQueryModel brandsQueryModel = getQueryModel(brandsId);
            brandsQueryModelList.add(brandsQueryModel);
        }
        return brandsQueryModelList;
    }
    
    /**
     * 如果是相同模板，则返货改模板ID
     * @return
     * @author 张昌苗
     */
    public String sameTemplateId()
    {
        String templateId = null;
        for (ProductParam productParam : productParamList)
        {
            if (StringUtils.isBlank(productParam.templateId)) continue;
            if (null == templateId) templateId = productParam.templateId;
            else if (!templateId.equals(productParam.templateId)) return null;
        }
        return templateId;
    }
    
    public List<String> getTemplateIdList()
    {
        Set<String> set = Sets.newHashSet();
        for (ProductParam productParam : productParamList)
        {
            if (StringUtils.isBlank(productParam.templateId)) continue;
            set.add(productParam.templateId);
        }
        return Lists.newArrayList(set);
    }
    
    /**
     * 是否所有产品都支持包邮
     * @return
     * @author 张昌苗
     */
    public Boolean isAllFreeFreight()
    {
        for (ProductParam productParam : productParamList)
            if (!productParam.isFreeFreight) return false;
        return true;
    }
    
    /**
     * 是否所有产品都支持快递到付
     * @return
     * @author 张昌苗
     */
    public Boolean isAllExpressCollect()
    {
        for (ProductParam productParam : productParamList)
            if (!productParam.isExpressCollectUsed) return false;
        return true;
    }
    
    /**
     * 是否所有产品都支持物流到付
     * @return
     * @author 张昌苗
     */
    public Boolean isAllLogisticsCollect()
    {
        for (ProductParam productParam : productParamList)
            if (!productParam.isLogisticsCollectUsed) return false;
        return true;
    }
    
    /**
     * 获取产品总重
     * @return
     * @author 张昌苗
     */
    public BigDecimal getSumWeight()
    {
        BigDecimal sumWeight = BigDecimal.ZERO;
        for (ProductParam productParam : productParamList)
        {
            BigDecimal productWeight = productParam.getProductWeight();
            BigDecimal productNum = new BigDecimal(productParam.getProductNum());
            sumWeight = sumWeight.add(productWeight.multiply(productNum));
        }
        return sumWeight;
    }
    
    private List<String> getBrandsIdList()
    {
        Set<String> brandsIdSet = Sets.newHashSet();
        for (ProductParam productParam : productParamList)
            brandsIdSet.add(productParam.getBrandsId());
        return Lists.newArrayList(brandsIdSet);
    }
    
    private BrandFreightQueryModel getQueryModel(String brandsId)
    {
        BrandFreightQueryModel queryModel = new BrandFreightQueryModel();
        queryModel.setAreaNo(areaNo);
        List<ProductParam> productParamList = getProductParamList(brandsId);
        queryModel.setProductParamList(productParamList);
        return queryModel;
    }
    
    private List<ProductParam> getProductParamList(String brandsId)
    {
        List<ProductParam> list = Lists.newArrayList();
        for (ProductParam productParam : productParamList)
            if (brandsId.equals(productParam.getBrandsId())) list.add(productParam);
        return list;
    }
    
    public List<ProductParam> getProductParamList()
    {
        return productParamList;
    }
    
    public void setProductParamList(List<ProductParam> productParamList)
    {
        this.productParamList = productParamList;
    }
    
    public Integer getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public Boolean getIsClean()
    {
        return isClean;
    }
    
    public void setIsClean(Boolean isClean)
    {
        this.isClean = isClean;
    }
    
    public class ProductParam
    {
        private String     productId;
        
        private Integer    productNum;
        
        private String     brandId;
        
        private String     brandsId;
        
        private String     templateId;
        
        private BigDecimal productWeight;
        
        private Boolean    isExpressUsed          = false;
        
        private Boolean    isLogisticsUsed        = false;
        
        private Boolean    isExpressCollectUsed   = false;
        
        private Boolean    isLogisticsCollectUsed = false;
        
        private Boolean    isFreeFreight;
        
        public String getProductId()
        {
            return productId;
        }
        
        public void setProductId(String productId)
        {
            this.productId = productId;
        }
        
        public Integer getProductNum()
        {
            return productNum;
        }
        
        public void setProductNum(Integer productNum)
        {
            this.productNum = productNum;
        }
        
        public String getBrandsId()
        {
            return brandsId;
        }
        
        public void setBrandsId(String brandsId)
        {
            this.brandsId = brandsId;
        }
        
        public String getTemplateId()
        {
            return templateId;
        }
        
        public void setTemplateId(String templateId)
        {
            this.templateId = templateId;
        }
        
        public String getBrandId()
        {
            return brandId;
        }
        
        public void setBrandId(String brandId)
        {
            this.brandId = brandId;
        }
        
        public BigDecimal getProductWeight()
        {
            return productWeight;
        }
        
        public void setProductWeight(BigDecimal productWeight)
        {
            this.productWeight = productWeight;
        }
        
        public Boolean getIsExpressUsed()
        {
            return isExpressUsed;
        }
        
        public void setIsExpressUsed(Boolean isExpressUsed)
        {
            this.isExpressUsed = isExpressUsed;
        }
        
        public Boolean getIsLogisticsUsed()
        {
            return isLogisticsUsed;
        }
        
        public void setIsLogisticsUsed(Boolean isLogisticsUsed)
        {
            this.isLogisticsUsed = isLogisticsUsed;
        }
        
        public Boolean getIsExpressCollectUsed()
        {
            return isExpressCollectUsed;
        }
        
        public void setIsExpressCollectUsed(Boolean isExpressCollectUsed)
        {
            this.isExpressCollectUsed = isExpressCollectUsed;
        }
        
        public Boolean getIsLogisticsCollectUsed()
        {
            return isLogisticsCollectUsed;
        }
        
        public void setIsLogisticsCollectUsed(Boolean isLogisticsCollectUsed)
        {
            this.isLogisticsCollectUsed = isLogisticsCollectUsed;
        }
        
        public Boolean getIsFreeFreight()
        {
            return isFreeFreight;
        }
        
        public void setIsFreeFreight(Boolean isFreeFreight)
        {
            this.isFreeFreight = isFreeFreight;
        }
    }
}
