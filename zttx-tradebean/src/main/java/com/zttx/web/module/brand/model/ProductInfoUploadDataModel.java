/*
 * @(#)ProductInfoUploadDataModel.java 2015-2-6 下午3:27:16
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.DealDic;
import com.zttx.goods.module.entity.WebUnit;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.common.entity.ProductCatalog;

import java.util.List;
import java.util.Map;

/**
 * <p>File：ProductInfoUploadDataModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-6 下午3:27:16</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class ProductInfoUploadDataModel
{
    // 一级类目
    private List<DealDic>                     topDealDicList;
    
    // 二级类目
    private Map<Integer, List<DealDic>>       childDealDicListMap   = Maps.newHashMap();
    
    // 品牌
    private List<BrandesInfoModel>                 brandesInfoList;
    
    // 产品分类
    private Map<String, List<ProductCatalog>> productCatalogListMap = Maps.newHashMap();
    
    
    // 运费模板
    private List<BrandFreightTemplate>        brandFreightTemplateList;
    
    // 单位
    private List<WebUnit>                     webUnitList;
    
    // 运费物流
    private List<String>                      productCarryList      = Lists.newArrayList("终端商承担物流", "品牌商承担物流");
    
    private List<Short>                       productCarryValueList = Lists.newArrayList((short) 1, (short) 2);
    
    // 拿样
    private List<String>                      isSampleList          = Lists.newArrayList("支持", "不支持");
    
    private List<Boolean>                     isSampleValueList     = Lists.newArrayList(true, false);
    
    // 前台可见
    private List<String>                      isShowList            = Lists.newArrayList("可见", "不可见");
    
    private List<Boolean>                     isShowValueList       = Lists.newArrayList(true, false);

    public List<String> getTopDealDicNameList()
    {
        List<String> list = Lists.newArrayList();
        for (DealDic obj : topDealDicList)
            list.add(obj.getDealName());
        return list;
    }
    
    public List<String> getChildDealDicNameList(DealDic topDealDic)
    {
        List<String> list = Lists.newArrayList();
        List<DealDic> childDealDicList = childDealDicListMap.get(new Integer(topDealDic.getDealNo().intValue()));
        for (DealDic obj : childDealDicList)
            list.add(obj.getDealName());
        return list;
    }
    
    public List<String> getBrandesInfoNameList()
    {
        List<String> list = Lists.newArrayList();
        for (BrandesInfoModel obj : brandesInfoList)
            list.add(obj.getBrandName());
        return list;
    }
    
    public List<String> getProductCatalogNameList(BrandesInfoModel brandesInfo)
    {
        List<String> list = Lists.newArrayList();
        List<ProductCatalog> productCatalogList = productCatalogListMap.get(brandesInfo.getRefrenceId());
        for (ProductCatalog obj : productCatalogList)
            list.add(obj.getCateName());
        return list;
    }
    
    public List<String> getBrandFreightTemplateNameList()
    {
        List<String> list = Lists.newArrayList();
        for (BrandFreightTemplate obj : brandFreightTemplateList)
            list.add(obj.getName());
        return list;
    }
    
    public List<String> getWebUnitNameList()
    {
        List<String> list = Lists.newArrayList();
        for (WebUnit obj : webUnitList)
            list.add(obj.getUnitName());
        return list;
    }
    
    public DealDic getTopDealDic(String name) throws BusinessException
    {
        for (DealDic topDealDic : topDealDicList)
            if (topDealDic.getDealName().equals(name)) return topDealDic;
        throw new BusinessException(CommonConst.FAIL.code, "找不到一级类目");
    }
    
    public DealDic getChildDealDic(String topDealDicName, String name) throws BusinessException
    {
        DealDic topDealDic = getTopDealDic(topDealDicName);
        return getChildDealDic(topDealDic, name);
    }
    
    public DealDic getChildDealDic(DealDic topDealDic, String name) throws BusinessException
    {
        List<DealDic> childDealDicList = childDealDicListMap.get(topDealDic.getDealNo().intValue());
        for (DealDic obj : childDealDicList)
            if (obj.getDealName().equals(name)) return obj;
        throw new BusinessException(CommonConst.FAIL.code, "找不到二级类目");
    }
    
    public BrandesInfoModel getBrandesInfo(String name) throws BusinessException
    {
        for (BrandesInfoModel obj : brandesInfoList)
            if (obj.getBrandName().equals(name)) return obj;
        throw new BusinessException(CommonConst.FAIL.code, "找不到品牌");
    }
    
    public ProductCatalog getProductCatalog(BrandesInfo brandesInfo, String name) throws BusinessException
    {
        List<ProductCatalog> productCatalogList = productCatalogListMap.get(brandesInfo.getRefrenceId());
        for (ProductCatalog obj : productCatalogList)
            if (obj.getCateName().equals(name)) return obj;
        throw new BusinessException(CommonConst.FAIL.code, "找不到产品分类");
    }
    
    public BrandFreightTemplate getBrandFreightTemplate(String name) throws BusinessException
    {
        for (BrandFreightTemplate obj : brandFreightTemplateList)
            if (obj.getName().equals(name)) return obj;
        throw new BusinessException(CommonConst.FAIL.code, "找不到运费模板");
    }
    
    public WebUnit getWebUnit(String name) throws BusinessException
    {
        for (WebUnit obj : webUnitList)
            if (obj.getUnitName().equals(name)) return obj;
        throw new BusinessException(CommonConst.FAIL.code, "找不到单位");
    }
    
    public Short getProductCarry(String name)
    {
        Integer index = productCarryList.indexOf(name);
        return productCarryValueList.get(index);
    }
    
    public Boolean getIsSample(String name)
    {
        Integer index = isSampleList.indexOf(name);
        return isSampleValueList.get(index);
    }
    
    public Boolean getIsShow(String name)
    {
        Integer index = isShowList.indexOf(name);
        return isShowValueList.get(index);
    }
    

    public List<DealDic> getTopDealDicList()
    {
        return topDealDicList;
    }

    public void setTopDealDicList(List<DealDic> topDealDicList)
    {
        this.topDealDicList = topDealDicList;
    }

    public Map<Integer, List<DealDic>> getChildDealDicListMap()
    {
        return childDealDicListMap;
    }

    public void setChildDealDicListMap(Map<Integer, List<DealDic>> childDealDicListMap)
    {
        this.childDealDicListMap = childDealDicListMap;
    }


    public List<BrandesInfoModel> getBrandesInfoList()
    {
        return brandesInfoList;
    }

    public void setBrandesInfoList(List<BrandesInfoModel> brandesInfoList)
    {
        this.brandesInfoList = brandesInfoList;
    }

    public Map<String, List<ProductCatalog>> getProductCatalogListMap()
    {
        return productCatalogListMap;
    }

    public void setProductCatalogListMap(Map<String, List<ProductCatalog>> productCatalogListMap)
    {
        this.productCatalogListMap = productCatalogListMap;
    }

    public List<BrandFreightTemplate> getBrandFreightTemplateList()
    {
        return brandFreightTemplateList;
    }

    public void setBrandFreightTemplateList(List<BrandFreightTemplate> brandFreightTemplateList)
    {
        this.brandFreightTemplateList = brandFreightTemplateList;
    }

    public List<WebUnit> getWebUnitList()
    {
        return webUnitList;
    }

    public void setWebUnitList(List<WebUnit> webUnitList)
    {
        this.webUnitList = webUnitList;
    }

    public List<String> getProductCarryList()
    {
        return productCarryList;
    }

    public void setProductCarryList(List<String> productCarryList)
    {
        this.productCarryList = productCarryList;
    }

    public List<Short> getProductCarryValueList()
    {
        return productCarryValueList;
    }

    public void setProductCarryValueList(List<Short> productCarryValueList)
    {
        this.productCarryValueList = productCarryValueList;
    }

    public List<String> getIsSampleList()
    {
        return isSampleList;
    }

    public void setIsSampleList(List<String> isSampleList)
    {
        this.isSampleList = isSampleList;
    }

    public List<Boolean> getIsSampleValueList()
    {
        return isSampleValueList;
    }

    public void setIsSampleValueList(List<Boolean> isSampleValueList)
    {
        this.isSampleValueList = isSampleValueList;
    }

    public List<String> getIsShowList()
    {
        return isShowList;
    }

    public void setIsShowList(List<String> isShowList)
    {
        this.isShowList = isShowList;
    }

    public List<Boolean> getIsShowValueList()
    {
        return isShowValueList;
    }

    public void setIsShowValueList(List<Boolean> isShowValueList)
    {
        this.isShowValueList = isShowValueList;
    }
    
    
}
