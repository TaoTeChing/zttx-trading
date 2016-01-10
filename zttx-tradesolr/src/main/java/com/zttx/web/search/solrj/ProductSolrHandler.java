package com.zttx.web.search.solrj;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;

import com.google.common.collect.Lists;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.common.entity.*;

/**
 * <p>File：ProductSolrHandler.java </p>
 * <p>Title: 产品信息索引服务 </p>
 * <p>Description: ProductSolrHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ProductSolrHandler
{
    private Logger logger = Logger.getLogger(ProductSolrHandler.class);
    
    /**
     * 添加产品信息到收索引擎建立索引
     *
     * @param productInfo
     * @return {@link Boolean}
     */
    public Boolean addProducInfo(ProductInfo productInfo)
    {
        Boolean bl;
        try
        {
            List<SolrInputDocument> docList = Lists.newArrayList();
            docList.add(convertProducInfoEntityToDoc(productInfo));
            bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_PRODUCT);
        }
        catch (Exception e)
        {
            logger.error("addProductInfoList error:", e);
            bl = Boolean.FALSE;
        }
        return bl;
    }
    
    /**
     * 批量添加产品信息到收索引擎建立索引
     *
     * @param biList
     * @return {@link Boolean}
     */
    public Boolean addProductInfoList(List<ProductInfo> biList)
    {
        Boolean bl = Boolean.FALSE;
        if (null != biList && biList.size() > 0) try
        {
            List<SolrInputDocument> docList = Lists.newArrayList();
            for (ProductInfo bi : biList)
            {
                SolrInputDocument doc = convertProducInfoEntityToDoc(bi);
                docList.add(doc);
            }
            bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_PRODUCT);
        }
        catch (Exception e)
        {
            logger.error("addProductInfoList error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * @param productId
     * 从搜索引擎中删除产品
     */
    public Boolean removeProductInfo(String productId)
    {
        Boolean bl;
        try
        {
            bl = SolrjHandler.delDoc(productId, SolrjHandler.CORE_NAME_PRODUCT);
        }
        catch (Exception e)
        {
            logger.error("remove productInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 产品对象转换
     *
     * @param bi
     * @return {@link SolrInputDocument}
     */
    protected SolrInputDocument convertProducInfoEntityToDoc(ProductInfo bi)
    {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("refrenceId", bi.getRefrenceId());
        doc.addField("brandId", bi.getBrandId());
        doc.addField("legalName", bi.getLegalName());
        doc.addField("comName", bi.getComName());
        doc.addField("brandsId", bi.getBrandsId());
        doc.addField("brandsName", bi.getBrandsName());
        doc.addField("provinceName", bi.getProvinceName());
        doc.addField("cityName", bi.getCityName());
        doc.addField("areaName", bi.getAreaName());
        doc.addField("dealId", bi.getDealNo());
        doc.addField("dealName", bi.getDealName());
        doc.addField("productNo", bi.getProductNo());
        doc.addField("productTitle", bi.getProductTitle());
        doc.addField("productKeyword", bi.getProductKeyword());
        doc.addField("productCarry", bi.getProductCarry());
        doc.addField("productCate", bi.getProductCate());
        doc.addField("productGroom", bi.getProductGroom());
        doc.addField("productImage", bi.getProductImage());
        doc.addField("productPrice", bi.getProductPrice());
        doc.addField("directPrice", bi.getDirectPrice());
        doc.addField("similarPrice", bi.getSimilarPrice());
        doc.addField("provincePrice", bi.getProvincePrice());
        doc.addField("productStore", bi.getProductStore());
        doc.addField("isCredit", bi.getProductCredit());
        doc.addField("isSample", bi.getProductSample());
        doc.addField("viewNum", bi.getViewNum());
        doc.addField("saleNum", bi.getSaleNum());
        doc.addField("collectNum", bi.getCollectNum());
        doc.addField("monthSaleNum", bi.getMonthSaleNum());
        doc.addField("createTime", CalendarUtils.getTimeFromLong(bi.getCreateTime() != null ? bi.getCreateTime() : 0l));
        doc.addField("updateTime", CalendarUtils.getTimeFromLong(bi.getUpdateTime() != null ? bi.getUpdateTime() : 0l));
        doc.addField("topTime", CalendarUtils.getTimeFromLong(bi.getTopTime() != null ? bi.getTopTime() : 0l));
        doc.addField("productWeight", bi.getProductWeight());
        doc.addField("brandsWeight", bi.getBrandsWeight());
        productDealHandle(bi, doc);
        productCateHandle(bi, doc);
        productImageHandle(bi, doc);
        skuAtrrHandle(bi, doc);
        return doc;
    }
    
    /**
     * 处理产品类目
     * @param bi
     * @param doc
     */
    protected void productDealHandle(ProductInfo bi, SolrInputDocument doc)
    {
        List<DealDic> dealDicList = bi.getDealDics();
        if (null != dealDicList && dealDicList.size() > 0)
        {
            for (DealDic dealDic : dealDicList)
            {
                doc.addField("mainId", dealDic.getDealNo());
                doc.addField("mainName", dealDic.getDealName());
            }
        }
    }
    
    /**
     * 处理产品分类
     * @param bi
     * @param doc
     */
    protected void productCateHandle(ProductInfo bi, SolrInputDocument doc)
    {
        List<ProductCatalog> catalogList = bi.getCategorys();
        if (null != catalogList && catalogList.size() > 0)
        {
            for (ProductCatalog catalog : catalogList)
            {
                doc.addField("categoryId", catalog.getRefrenceId());
            }
        }
    }
    
    /**
     * 处理产品图片
     * @param bi
     * @param doc
     */
    protected void productImageHandle(ProductInfo bi, SolrInputDocument doc)
    {
        List<ProductImage> imageList = bi.getImages();
        if (null != imageList && imageList.size() > 0)
        {
            for (ProductImage productImage : imageList)
            {
                doc.addField("images", productImage.getImageName());
            }
        }
    }
    
    /**
     * 处理产品属性
     * @param bi
     * @param doc
     */
    protected void skuAtrrHandle(ProductInfo bi, SolrInputDocument doc)
    {
        List<ProductAttrValue> attrValueList = bi.getAttrs();
        if (null != attrValueList && attrValueList.size() > 0)
        {
            for (ProductAttrValue attrValue : attrValueList)
            {
                if (attrValue.getIsSkuAttr())
                {// SKU属性
                    if (ZttxConst.CateAttributeItem.DEFAULT_COLOR_ID.equals(attrValue.getAttributeId()))
                    {
                        doc.addField("skuColor", attrValue.getExtAttributeValue());
                    }
                    else if (ZttxConst.CateAttributeItem.DEFAULT_SIZE_ID.equals(attrValue.getAttributeId()))
                    {
                        doc.addField("skuSize", attrValue.getExtAttributeValue());
                    }
                }
                else
                { // 产品属性
                    doc.addField("otherAttr", attrValue.getExtAttributeValue());
                }
            }
        }
    }
}
