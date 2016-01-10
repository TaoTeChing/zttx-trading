/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.mapper.ProductCatalogMapper;
import com.zttx.web.module.common.mapper.ProductCateMapper;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.module.common.model.ProductCatalogModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 产品分类信息 服务实现类
 * <p>File：ProductCatalog.java </p>
 * <p>Title: ProductCatalog </p>
 * <p>Description:ProductCatalog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductCatalogServiceImpl extends GenericServiceApiImpl<ProductCatalog> implements ProductCatalogService
{
    private ProductCatalogMapper productCatalogMapper;
    
    @Autowired
    private ProductCateMapper    productCateMapper;
    
    @Autowired(required = true)
    public ProductCatalogServiceImpl(ProductCatalogMapper productCatalogMapper)
    {
        super(productCatalogMapper);
        this.productCatalogMapper = productCatalogMapper;
    }
    
    @Override
    public List<MenuTree> getCatalogTreeList(String brandId, String brandsId)
    {
        List<ProductCatalog> catalogList = getCatalogList(brandId, brandsId);
        return MenuTree.getCatalogTree(catalogList);
    }
    
    @Override
    public List<ProductCatalog> getCatalogList(String brandId, String brandsId)
    {
        return getCatalogList(brandId, brandsId, null);
    }
    
    @Override
    public List<ProductCatalog> getCatalogList(String brandId, String brandsId, Short cateLevel)
    {
        List<ProductCatalog> catalogList = productCatalogMapper.getCatalogList(brandId, brandsId, cateLevel);
        return catalogList;
    }
    
    @Override
    public void insertCatalog(ProductCatalogModel productCatalog, HttpServletRequest request) throws BusinessException
    {
        List<ProductCatalog> catalogList = getCatalogList(productCatalog.getBrandId(), productCatalog.getBrandsId());
        Map<String, ProductCatalog> catalogMap = Maps.newHashMap();
        Map<String, Map<String, String>> catalogNameMap = Maps.newHashMap();
        Map<String, String> firstCataNameMap = Maps.newHashMap();
        if (null != catalogList && !catalogList.isEmpty())
        {
            for (ProductCatalog item : catalogList)
            {
                String refrenceId = item.getRefrenceId();
                catalogMap.put(refrenceId, item);
                if (item.getCateLevel() > 1)
                {
                    refrenceId = item.getParentId();
                }
                else
                {
                    firstCataNameMap.put(item.getCateName(), "");
                }
                Map<String, String> map = Maps.newHashMap();
                if (catalogNameMap.containsKey(refrenceId))
                {
                    map = catalogNameMap.get(refrenceId);
                }
                else
                {
                    catalogNameMap.put(refrenceId, map);
                }
                map.put(item.getCateName(), "");
            }
        }
        List<ProductCatalog> addList = Lists.newArrayList();
        List<ProductCatalog> updateList = Lists.newArrayList();
        String cateName = "";
        String refrenceId = "";
        Integer cateLevel = 1;
        String cateLevelStr = "";
        String parentId = "";
        String cateIcon = "";
        if (ArrayUtils.isNotEmpty(productCatalog.getCateNameAry()))
        {
            for (int i = 0; i < productCatalog.getCateNameAry().length; i++)
            {
                cateName = productCatalog.getCateNameAry()[i];
                if (!StringUtils.isBlank(cateName))
                {
                    refrenceId = getAryStr(productCatalog.getRefrenceIdAry(), i);
                    cateLevelStr = getAryStr(productCatalog.getCateLevelAry(), i);
                    cateLevel = ("2".equals(cateLevelStr)) ? 2 : 1;
                    cateIcon = getAryStr(productCatalog.getCateIconAry(), i);
                    if (StringUtils.isBlank(refrenceId))
                    {
                        ProductCatalog newProductCatalog = new ProductCatalog();
                        newProductCatalog.setBrandId(productCatalog.getBrandId());
                        newProductCatalog.setBrandsId(productCatalog.getBrandsId());
                        newProductCatalog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        newProductCatalog.setCateName(cateName);
                        newProductCatalog.setCateOrder(i);
                        newProductCatalog.setDomainName(NetworkUtils.getDoMainName());
                        if (1 == cateLevel)
                        {
                            if (firstCataNameMap.containsKey(cateName))
                            {
                                throw new BusinessException(CommonConst.DATA_EXISTS);
                            }
                            else
                            {
                                firstCataNameMap.put(cateName, "");
                            }
                            parentId = newProductCatalog.getRefrenceId();
                        }
                        else
                        {
                            newProductCatalog.setParentId(parentId);
                        }
                        newProductCatalog.setCateLevel(cateLevel.shortValue());
                        isUpCateIcon(newProductCatalog, cateIcon, request);
                        newProductCatalog.setProductNum(0);
                        newProductCatalog.setDelFlag(false);
                        newProductCatalog.setCreateTime(CalendarUtils.getCurrentLong());
                        addList.add(newProductCatalog);
                        valiCataName(catalogNameMap, parentId, cateName, null);
                    }
                    else
                    {
                        if (null == catalogMap.get(refrenceId)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
                        ProductCatalog newProductCatalog = catalogMap.get(refrenceId);
                        if (1 == newProductCatalog.getCateLevel())
                        {
                            parentId = newProductCatalog.getRefrenceId();
                        }
                        Boolean isUpdate = false;
                        if (!newProductCatalog.getCateName().equals(cateName))
                        {
                            if (1 == newProductCatalog.getCateLevel())
                            {
                                if (firstCataNameMap.containsKey(cateName))
                                {
                                    throw new BusinessException(CommonConst.DATA_EXISTS);
                                }
                                else
                                {
                                    firstCataNameMap.put(cateName, "");
                                }
                            }
                            newProductCatalog.setCateName(cateName);
                            isUpdate = true;
                            valiCataName(catalogNameMap, parentId, cateName, newProductCatalog.getCateName());
                        }
                        if (i != newProductCatalog.getCateOrder())
                        {
                            newProductCatalog.setCateOrder(i);
                            isUpdate = true;
                        }
                        if (isUpCateIcon(newProductCatalog, cateIcon, request))
                        {
                            isUpdate = true;
                        }
                        if (isUpdate)
                        {
                            newProductCatalog.setUpdateTime(new Date().getTime());
                            updateList.add(newProductCatalog);
                        }
                        catalogMap.remove(newProductCatalog.getRefrenceId());
                    }
                }
            }
            if (addList.size() > 0)
            {
                productCatalogMapper.insertBatch(addList);
            }
            if (updateList.size() > 0)
            {
                for (int i = 0; i < updateList.size(); i++)
                {
                    productCatalogMapper.updateByPrimaryKey(updateList.get(i));
                }
            }
        }
        this.updateCatalogDelState(catalogMap);
    }
    
    private void updateCatalogDelState(Map<String, ProductCatalog> catalogMap) throws BusinessException
    {
        if (null != catalogMap && !catalogMap.isEmpty())
        {
            Integer len = catalogMap.size();
            String[] idAry = new String[len];
            Iterator<String> iterator = catalogMap.keySet().iterator();
            int i = 0;
            while (iterator.hasNext())
            {
                idAry[i] = iterator.next().toString();
                Integer count = productCateMapper.countByCatalogId(idAry[i]);
                if (count > 0) { throw new BusinessException(CommonConst.FAIL.getCode(), "分类" + catalogMap.get(idAry[i]).getCateName() + "已经被使用，请先移除产品分类", ""); }
                i++;
            }
            productCatalogMapper.updateCatalogDelState(idAry, true);
            productCateMapper.deleteCateBatch(idAry);
        }
    }
    
    private void valiCataName(Map<String, Map<String, String>> catalogNameMap, String refrenceId, String newCateName, String oldCateName) throws BusinessException
    {
        Map<String, String> map = Maps.newHashMap();
        if (catalogNameMap.containsKey(refrenceId))
        {
            map = catalogNameMap.get(refrenceId);
            if (map.containsKey(newCateName))
            {
                throw new BusinessException(CommonConst.DATA_EXISTS);
            }
            else
            {
                map.put(newCateName, "");
                if (StringUtils.isNotBlank(oldCateName))
                {
                    map.remove(oldCateName);
                }
            }
        }
        else
        {
            map.put(newCateName, "");
            catalogNameMap.put(refrenceId, map);
        }
    }
    
    private String getAryStr(String[] strAry, Integer size)
    {
        if (null != strAry)
        {
            Integer len = strAry.length;
            if (size.intValue() <= len.intValue() - 1) { return strAry[size].trim(); }
        }
        return null;
    }
    
    private Boolean isUpCateIcon(ProductCatalog productCatalog, String cateIcon, HttpServletRequest request) throws BusinessException
    {
        Boolean isUpCateIcon = false;
        String tmpCateIcon = productCatalog.getCateIcon();
        try
        {
            if (StringUtils.isBlank(cateIcon))
            {
                if (!StringUtils.isBlank(tmpCateIcon))
                {
                    // 删除图片
                    FileClientUtil.deleteFile(tmpCateIcon);
                    productCatalog.setCateIcon(null);
                    isUpCateIcon = true;
                }
            }
            else
            {
                if (!StringUtils.isBlank(tmpCateIcon))
                {
                    if (!cateIcon.equals(tmpCateIcon))
                    {
                        FileClientUtil.deleteFile(tmpCateIcon);
                    }
                    else
                    {
                        return false;
                    }
                }
                // 移动图片
                String imgPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, cateIcon, UploadAttCateConst.BRANDS_LOGO);
                productCatalog.setCateIcon(imgPath);
                isUpCateIcon = true;
            }
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.IMG_SAVE_FAULT);
        }
        return isUpCateIcon;
    }
    
    @Override
    public List<ProductCatalog> findSubCates(String cataId)
    {
        if (StringUtils.isBlank(cataId)) return null;
        return productCatalogMapper.findSubCates(cataId);
    }
    
    @Override
    public List<ProductCatalog> findParentCates(String cataId)
    {
        ProductCatalog catalog = productCatalogMapper.selectByPrimaryKey(cataId);
        List<ProductCatalog> catalogs = Lists.newArrayList(catalog);
        return findParentCates(catalogs, catalog);
    }
    
    /**
     * 递归处理类目
     * @param catalogs
     * @param catalog
     * @return
     */
    List<ProductCatalog> findParentCates(List<ProductCatalog> catalogs, ProductCatalog catalog)
    {
        if (StringUtils.isBlank(catalog.getParentId())) return catalogs;
        ProductCatalog productCatalog = productCatalogMapper.selectByPrimaryKey(catalog.getParentId());
        if (null != productCatalog) catalogs.add(productCatalog);
        return findParentCates(catalogs, productCatalog);
    }
}
