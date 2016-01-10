/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.annotation.DataSource;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.sdk.db.DataSourceEnum;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ProductConsts;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.entity.ProductCate;
import com.zttx.web.module.common.entity.ProductEditDetail;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.mapper.ProductAttrValueMapper;
import com.zttx.web.module.common.mapper.ProductInfoMapper;
import com.zttx.web.module.fronts.model.SolrFilter;
import com.zttx.web.search.module.FacetCounts;
import com.zttx.web.search.module.FacetField;
import com.zttx.web.search.module.SolrModel;
import com.zttx.web.search.query.ProductSolrQueryService;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.search.utils.FacetUtils;
import com.zttx.web.utils.ListUtils;

/**
 * 商品信息表 服务实现类
 * <p>File：ProductInfo.java </p>
 * <p>Title: ProductInfo </p>
 * <p>Description:ProductInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService
{
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private ProductInfoMapper           productInfoMapper;
    
    @Autowired
    private DealDicService              dealDicService;
    
    @Autowired
    private ProductImageService         productImageService;
    
    @Autowired
    private ProductCateService          productCateService;
    
    @Autowired
    private ProductCatalogService       productCatalogService;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired
    private ProductSolrQueryService     productSolrQueryService;
    
    @Autowired
    private ProductSolrHandler          productSolrHandler;
    
    @Autowired
    private ProductAttrValueMapper      productAttrValueMapper;
    
    @Override
    public PaginateResult<Map<String, Object>> searchAllProduct(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult<>();
        List<NameValuePair> facets = Lists.newArrayList();
        facets.add(new BasicNameValuePair("facet", "true"));
        facets.add(new BasicNameValuePair("facet.field", "dealId"));
        facets.add(new BasicNameValuePair("facet.field", "brandsId"));
        // facets.add(new BasicNameValuePair("facet.field", "skuColor"));
        // facets.add(new BasicNameValuePair("facet.field", "skuSize"));
        // facets.add(new BasicNameValuePair("facet.field", "otherAttr"));
        facets.add(new BasicNameValuePair("facet.mincount", "1"));
        Map<String, String> sorts = null;
        if (null != filter.getSorts())
        {
            sorts = Maps.newHashMap();
            if ("price".equals(filter.getSorts()))
            {
                sorts.put("productPrice", filter.getOrderBy());
            }
            else if ("popular".equals(filter.getSorts()))
            {
                sorts.put("collectNum", filter.getOrderBy());
            }
            else if ("saleNum".equals(filter.getSorts()))
            {
                sorts.put("saleNum", filter.getOrderBy());
                sorts.put("monthSaleNum", filter.getOrderBy());
            }
            else if ("date".equals(filter.getSorts()))
            {
                sorts.put("topTime", "desc");
                sorts.put("productGroom", "desc");
            }
        }
        SolrModel data = productSolrQueryService.searchAllProductList(filter, sorts, facets, pagination);
        if (null != data)
        {
            result = data.getResult();
            FacetCounts facetCount = data.getFacet_counts();
            if (null != facetCount && null != facetCount.getFacet_fields())
            {
                Map<String, Object> facetMaps = Maps.newHashMap();
                Map<String, Object> facetFields = facetCount.getFacet_fields();
                // 处理类目
                List<FacetField> dealDicFacets = FacetUtils.getFacetFields(MapUtils.getObject(facetFields, "dealId", null).toString());
                if (null != dealDicFacets && dealDicFacets.size() > 0)
                {
                    List<Integer> dealNos = Lists.newArrayList();
                    for (FacetField facetField : dealDicFacets)
                        dealNos.add(Integer.parseInt(facetField.getName()));
                    facetMaps.put("dealDicList", dealDicService.getDealDicByDealNos(dealNos));
                }
                // 处理品牌
                List<FacetField> brandesFacets = FacetUtils.getFacetFields(MapUtils.getObject(facetFields, "brandsId", null).toString());
                if (null != brandesFacets && brandesFacets.size() > 0)
                {
                    List<String> ids = Lists.newArrayList();
                    for (FacetField facetField : brandesFacets)
                        ids.add(facetField.getName());
                    facetMaps.put("brandesList", brandesInfoService.findBrandesInfoByIds(ids, filter.getMainId()));
                }
                // SKU信息
                // facetMaps.put("skuColor", FacetUtils.getFacetFields(MapUtils.getObject(facetFields, "skuColor", null).toString()));
                // facetMaps.put("skuSize", FacetUtils.getFacetFields(MapUtils.getObject(facetFields, "skuSize", null).toString()));
                // facetMaps.put("otherAttr", FacetUtils.getFacetFields(MapUtils.getObject(facetFields, "otherAttr", null).toString()));
                result.setFacets(facetMaps);
            }
        }
        return result;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public Long findProductToSolrCount(ProductInfo productInfo) throws BusinessException
    {
        return productInfoMapper.findProductToSolrCount(productInfo);
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<ProductInfo> findProductToSolr(ProductInfo productInfo, Pagination pagination) throws BusinessException
    {
        List<ProductInfo> productInfos = productInfoMapper.findProductToSolr(productInfo, pagination);
        setMoreInfo(productInfos);
        return productInfos;
    }
    
    private void setMoreInfo(List<ProductInfo> productInfos)
    {
        if (!ListUtils.isNotEmpty(productInfos)) { return; }
        for (ProductInfo info : productInfos)
        {
            info.setDealDics(dealDicService.getParentDealDics(info.getDealNo()));
            info.setImages(productImageService.getProductImagesByProductId(info.getRefrenceId()));
            List<ProductCate> cateList = productCateService.getProductCatesByProductId(info.getRefrenceId());
            if (null != cateList && cateList.size() > 0)
            {
                List<ProductCatalog> catalogList = Lists.newArrayList();
                for (ProductCate cate : cateList)
                {
                    catalogList.addAll(productCatalogService.findParentCates(cate.getCateId()));
                }
                info.setCategorys(catalogList);
            }
            info.setAttrs(productAttrValueMapper.getAttrValues(info.getRefrenceId()));
        }
    }
    
    @Override
    public List<Map<String, Object>> findAllProductBaseInfo()
    {
        return productInfoMapper.findAllProductBaseInfo();
    }
    
    @Override
    public void executeChangeProductInfo(ProductEditDetail editDetail) throws BusinessException
    {
        ProductBaseInfo productInfo = productInfoDubboConsumer.getProductById(editDetail.getProductId());
        if (null == productInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        if (BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_NO.equals(editDetail.getChangeType()))
        {
            productInfo.setProductNo(editDetail.getNewValue());
            if (Short.valueOf(productInfo.getStateSet()) == ProductConsts.BEGIN_TYPE_FIRST.getKey().shortValue())
            {
                // 搜索产品到收索引擎
                ProductInfo filter = new ProductInfo();
                filter.setRefrenceId(productInfo.getRefrenceId());
                List<ProductInfo> productInfos = this.findProductToSolr(filter, null);
                for (ProductInfo p : productInfos)
                {
                    p.setProductNo(editDetail.getNewValue());
                    productSolrHandler.addProducInfo(p);
                    break;
                }
            }
        }
        else if (BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_COLOR.equals(editDetail.getChangeType())
                || BrandConstant.ProductEditDetail.CHANGE_TYPE_PRODUCT_SIZE.equals(editDetail.getChangeType()))
        {
            List<ProductAttrValue> productAttrValues = productSkuInfoDubboConsumer.findAttrValuebyProductIdAndCateAttributeItemId(editDetail.getProductId(),
                    editDetail.getVid(), true);
            if (null == productAttrValues || productAttrValues.size() == 0) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            for (ProductAttrValue productAttrValue : productAttrValues)
            {
                // 先修改sku属性的扩展属性字段
                productAttrValue.setExtAttributeValue(editDetail.getNewValue());
                productSkuInfoDubboConsumer.updateProductAttrValue(productAttrValue);
                // 同步修改sku的productSkuName,订单使用该字段
                if (productAttrValue.getProductSkuId() != null)
                {
                    ProductSku sku = productSkuInfoDubboConsumer.findProductSku(productAttrValue.getProductSkuId());
                    if (sku != null)
                    {
                        String itemIds = sku.getAttrItemIds();
                        String productSkuName = sku.getProductSkuName();
                        String[] itemIdArr = StringUtils.split(itemIds, ZttxConst.SEPARATOR);
                        String[] skuNameArr = StringUtils.split(productSkuName, ZttxConst.SEPARATOR);
                        boolean lenFlag = itemIdArr.length == skuNameArr.length;
                        if (lenFlag)
                        {
                            for (int i = 0; i < itemIdArr.length; i++)
                            {
                                if (productAttrValue.getAttributeItemId().equals(itemIdArr[i]))
                                {
                                    skuNameArr[i] = productAttrValue.getExtAttributeValue();
                                    break;
                                }
                            }
                        }
                        productSkuName = StringUtils.join(skuNameArr, ZttxConst.SEPARATOR);
                        sku.setProductSkuName(productSkuName);
                        productSkuInfoDubboConsumer.updateSelectiveByPrimaryKey(sku);
                    }
                }
            }
        }
        productInfoDubboConsumer.updateSelectiveByPrimaryKey(productInfo);
    }
    
    @Override
    public List<ProductInfo> findProductToSolr(List<String> listMaps) throws BusinessException
    {
        if (null != listMaps && listMaps.size() > 0)
        {
            List<ProductInfo> productInfos = productInfoMapper.findProductToSolrByIds(listMaps);
            setMoreInfo(productInfos);
            return productInfos;
        }
        return null;
    }
}
