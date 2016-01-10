package com.zttx.web.dubbo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.service.ProductService;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductInfoDubboConsumer.java </p>
 * <p>Title: 产品信息消费服务 </p>
 * <p>Description:ProductInfoDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class ProductInfoDubboConsumer
{
    public static final Logger logger = LoggerFactory.getLogger(ProductInfoDubboConsumer.class);
    
    @Autowired(required = false)
    private ProductService     productService;
    
    /**
     * 创建商品信息
     *
     * @param productBaseInfo 商品基本信息
     * @return
     */
    public ProductBaseInfo createProduct(ProductBaseInfo productBaseInfo) throws BusinessException
    {
        ProductBaseInfo baseInfo;
        try
        {
            baseInfo = productService.createProduct(productBaseInfo);
        }
        catch (BusinessException e)
        {
            logger.error("createProduct error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return baseInfo;
    }
    
    /**
     * 批量新增商品
     *
     * @param productInfoList
     * @return
     */
    public List<ProductBaseInfo> createProductBatch(List<ProductBaseInfo> productInfoList) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.createProductBatch(productInfoList);
        }
        catch (BusinessException e)
        {
            logger.error("createProductBatch error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 更新商品信息
     *
     * @param productBaseInfo
     * @return productBaseInfo
     */
    public ProductBaseInfo updateProduct(ProductBaseInfo productBaseInfo, Boolean delFlag) throws BusinessException
    {
        ProductBaseInfo baseInfo;
        try
        {
            baseInfo = productService.updateProduct(productBaseInfo, delFlag);
        }
        catch (BusinessException e)
        {
            logger.error("updateProduct error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return baseInfo;
    }
    
    /**
     * 批量删除产品
     *
     * @param productIds
     * @return
     */
    public int deleteBatch(List<String> productIds) throws BusinessException
    {
        Integer tmp;
        try
        {
            tmp = productService.deleteBatch(productIds);
        }
        catch (BusinessException e)
        {
            logger.error("deleteBatch error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return tmp;
    }
    
    /**
     * 删除某品牌商下的产品
     *
     * @param brandId
     * @param productIds
     */
    public List<ProductBaseInfo> deleteProductInfos(String brandId, String[] productIds) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.deleteProductInfos(brandId, productIds);
        }
        catch (BusinessException e)
        {
            logger.error("deleteProductInfos error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 永久删除产品
     *
     * @param brandId
     * @param productIds
     * @throws BusinessException
     */
    public List<ProductBaseInfo> deleteProductInfosReal(String brandId, String[] productIds) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.deleteProductInfosReal(brandId, productIds);
        }
        catch (BusinessException e)
        {
            logger.error("deleteProductInfos error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 还原产品状态，从删除到不删除
     *
     * @param brandId
     * @param productIds
     */
    public List<ProductBaseInfo> returnProductInfos(String brandId, String[] productIds) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.returnProductInfos(brandId, productIds);
        }
        catch (BusinessException e)
        {
            logger.error("returnProductInfos error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * brandId:交易平台品牌商主键id
     * productNo:产品货号(可选)
     * productTitle:产品名称(可选)
     * stateSet:状态(可选)终端erp上下架，1 上架0下架
     * dealDic.refrenceId:类目id
     * page.startIndex:分页开始index
     * page.pageSize:分页每页大小
     * 根据组合条件查询商品基本信息
     *
     * @param params
     * @return
     */
    public PaginateResult<ProductBaseInfo> findByParams(ProductBaseInfo params) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findByParams(params);
        }
        catch (BusinessException e)
        {
            logger.error("findByParams error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * productTitle:产品名称
     * productNo:产品货号
     *
     * @param params
     * @return
     */
    public PaginateResult<ProductBaseInfo> findByTitleOrNo(ProductBaseInfo params, List<String> productIdList) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findByTitleOrNo(params, productIdList);
        }
        catch (BusinessException e)
        {
            logger.error("findByTitleOrNo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据组合条件查询商品及sku信息
     *
     * @param params
     * @return
     */
    public PaginateResult<ProductBaseInfo> findProductWithSkuByParams(ProductBaseInfo params) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findProductWithSkuByParams(params);
        }
        catch (BusinessException e)
        {
            logger.error("findByTitleOrNo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据组合条件查询商品及图片信息
     *
     * @param params
     * @return
     */
    public PaginateResult<ProductBaseInfo> findProductWithImgByParams(ProductBaseInfo params) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findProductWithImgByParams(params);
        }
        catch (BusinessException e)
        {
            logger.error("findProductWithImgByParams error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据商品sku ID集合查询商品信息
     *
     * @param page
     * @param productSkuIdList
     * @return
     */
    public PaginateResult<ProductBaseInfo> findByProductSkuIds(Pagination page, List<String> productSkuIdList) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findByProductSkuIds(page, productSkuIdList);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductSkuIds error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据商品ID 集合查询商品信息
     *
     * @param productIdList：产品id列表
     * @param withImages:true带有图片，false 没有图片
     * @return
     */
    public List<ProductBaseInfo> findByProductIds(List<String> productIdList, Boolean withImages) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByProductIds(productIdList, withImages);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductIds error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据单个产品id获取产品信息
     *
     * @param productId
     * @return
     */
    public ProductBaseInfo getProductById(String productId) throws BusinessException
    {
        ProductBaseInfo baseInfo;
        try
        {
            baseInfo = productService.getProductById(productId);
        }
        catch (BusinessException e)
        {
            logger.error("getProductById error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return baseInfo;
    }
    
    /**
     * 根据商品ID 集合查询商品信息
     *
     * @param productIdList：产品id列表
     * @return
     */
    public List<ProductBaseInfo> findByProductIds(List<String> productIdList) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByProductIds(productIdList);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductIds error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据产品id和产品其他条件与关系查询产品
     *
     * @param ids
     * @param baseInfo
     * @return
     */
    public PaginateResult<ProductBaseInfo> findByProductIdsAndProduct(List<String> ids, ProductBaseInfo baseInfo) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result;
        try
        {
            result = productService.findByProductIdsAndProduct(ids, baseInfo);
        }
        catch (BusinessException e)
        {
            logger.error("findByProductIdsAndProduct error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 新增商品 带sku 带属性
     *
     * @param productBaseInfo
     * @return
     */
    public ProductBaseInfo insert(ProductBaseInfo productBaseInfo) throws BusinessException
    {
        ProductBaseInfo baseInfo;
        try
        {
            baseInfo = productService.insert(productBaseInfo);
        }
        catch (BusinessException e)
        {
            logger.error("insert error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return baseInfo;
    }
    
    /**
     * 根据商品的品牌和创建时间段查询商品
     *
     * @param brandsId  品牌id
     * @param startDate 创建时间的 开始时间
     * @param endDate   创建时间的 结束时间
     * @return
     */
    public List<ProductBaseInfo> findByBrandsAndCreateTimeRange(String brandsId, Long startDate, Long endDate, Boolean withImages) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByBrandsAndCreateTimeRange(brandsId, startDate, endDate, withImages);
        }
        catch (BusinessException e)
        {
            logger.error("findByBrandsAndCreateTimeRange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据产品id获取产品属性
     *
     * @param productId
     * @return
     */
    public List<ProductAttrValue> findProductAttrValueByProductId(String productId) throws BusinessException
    {
        List<ProductAttrValue> infos;
        try
        {
            infos = productService.findProductAttrValueByProductId(productId);
        }
        catch (BusinessException e)
        {
            logger.error("findProductAttrValueByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据SkuId获取商品属性
     *
     * @param productSkuId
     * @return
     */
    public List<ProductAttrValue> findProductAttrValueByProducSkuId(String productSkuId) throws BusinessException
    {
        List<ProductAttrValue> infos;
        try
        {
            infos = productService.findProductAttrValueByProducSkuId(productSkuId);
        }
        catch (BusinessException e)
        {
            logger.error("findProductAttrValueByProducSkuId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 产品上下架
     *
     * @param productIds
     * @param stateSet
     * @return
     */
    public Integer upOrDownProduct(List<String> productIds, List<String> stateSet) throws BusinessException
    {
        int tmp;
        try
        {
            tmp = productService.upOrDownProduct(productIds, stateSet);
        }
        catch (BusinessException e)
        {
            logger.error("upOrDownProduct error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return tmp;
    }
    
    /**
     * 根据时间查询比这个时间大创建的产品数量
     *
     * @param createTime
     * @return
     */
    public Integer countProductByCreateTimeGigger(Long createTime) throws BusinessException
    {
        int tmp;
        try
        {
            tmp = productService.countProductByCreateTimeGigger(createTime);
        }
        catch (BusinessException e)
        {
            logger.error("countProductByCreateTimeGigger error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return tmp;
    }
    
    /**
     * 根据店铺创建时间查找商品
     *
     * @return
     */
    public List<ProductBaseInfo> findByDealerAndCreateTimeRangeDesc(String shopId, Long tiemStart, Long timeEnd, int start, int pageSize) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByDealerAndCreateTimeRangeDesc(shopId, tiemStart, timeEnd, start, pageSize);
        }
        catch (BusinessException e)
        {
            logger.error("findByDealerAndCreateTimeRangeDesc error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 统计推荐数量
     *
     * @param brandId
     * @return
     */
    public Long countProductGroom(String brandId) throws BusinessException
    {
        Long num;
        try
        {
            num = productService.countProductGroom(brandId);
        }
        catch (Exception e)
        {
            logger.error("countProductGroom error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return num;
    }
    
    /**
     * 判断产品货号是否存在
     *
     * @param brandId
     * @param brandsId
     * @param productNo
     * @param productId
     * @return
     * @throws BusinessException
     */
    public Boolean isSameProductNo(String brandId, String brandsId, String productNo, String productId) throws BusinessException
    {
        Boolean flag;
        try
        {
            flag = productService.isSameProductNo(brandId, brandsId, productNo, productId);
        }
        catch (Exception e)
        {
            logger.error("isSameProductNo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 根据条件查询产品信息
     *
     * @param model
     * @return
     */
    public PaginateResult<ProductBaseInfoModel> findByBaseInfoModel(ProductBaseInfoModel model) throws BusinessException
    {
        PaginateResult<ProductBaseInfoModel> result;
        try
        {
            result = productService.findByBaseInfoModel(model);
        }
        catch (Exception e)
        {
            logger.error("findByBaseInfoModel error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据品牌商和产品id获取产品信息
     *
     * @param brandId
     * @param productId
     * @return
     */
    public List<ProductBaseInfo> findByBrandIdAndProductId(String brandId, String productId) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByBrandIdAndProductId(brandId, productId);
        }
        catch (BusinessException e)
        {
            logger.error("findByBrandIdAndProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 交易平台更新或新增产品
     *
     * @param info
     */
    public ProductBaseInfo saveOrUpdateForTrade(ProductBaseInfoModel info) throws BusinessException
    {
        ProductBaseInfo infos;
        try
        {
            infos = productService.saveOrUpdateForTrade(info);
        }
        catch (BusinessException e)
        {
            logger.error("saveOrUpdateForTrade error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 交易平台产品列表页查询
     *
     * @param model
     * @return
     */
    public PaginateResult<Map<String, Object>> searchByBaseInfoModelForList(ProductBaseInfoModel model) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result;
        try
        {
            result = productService.searchByBaseInfoModelForList(model);
        }
        catch (BusinessException e)
        {
            logger.error("searchByBaseInfoModelForList error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 推荐和取消推荐产品
     *
     * @param brandId
     * @param productIds
     * @param isGroom
     */
    public void updateProductGroom(String brandId, String[] productIds, Boolean isGroom) throws BusinessException
    {
        try
        {
            productService.updateProductGroom(brandId, productIds, isGroom);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductGroom error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 获取产品统计数据
     *
     * @param baseInfo
     * @return
     */
    public List<Map<String, Object>> getProductBaseInfoCountList(ProductBaseInfo baseInfo) throws BusinessException
    {
        List<Map<String, Object>> infos;
        try
        {
            infos = productService.getProductBaseInfoCountList(baseInfo);
        }
        catch (BusinessException e)
        {
            logger.error("getProductBaseInfoCountList error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据品牌商获取产品预警值
     *
     * @param brandId
     * @return
     */
    public Integer getStoreWarnNum(String brandId) throws BusinessException
    {
        Integer infos;
        try
        {
            infos = productService.getStoreWarnNum(brandId);
        }
        catch (BusinessException e)
        {
            logger.error("getStoreWarnNum error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 紧张库存产品数量
     *
     * @param baseInfo
     * @return
     */
    public Long getProductBaseInfoCount(ProductBaseInfo baseInfo)
    {
        Long infos = null;
        try
        {
            infos = productService.getProductBaseInfoCount(baseInfo);
        }
        catch (BusinessException e)
        {
            logger.error("getProductBaseInfoCount error！", e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 设置产品上下架
     *
     * @param brandId
     * @param productIds
     * @param beginType
     */
    public List<ProductBaseInfo> updateProductBeginType(String brandId, String[] productIds, Short beginType) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.updateProductBeginType(brandId, productIds, beginType);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductBeginType error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 设置预警值
     *
     * @param brandId
     * @param storeWarnNum
     */
    public void saveStoreWarnNum(String brandId, Integer storeWarnNum) throws BusinessException
    {
        try
        {
            productService.saveStoreWarnNum(brandId, storeWarnNum);
        }
        catch (BusinessException e)
        {
            logger.error("saveStoreWarnNum error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 设置拿样
     *
     * @param brandId
     * @param productIds
     * @param isSample
     */
    public void updateProductSample(String brandId, String[] productIds, Boolean isSample) throws BusinessException
    {
        try
        {
            productService.updateProductSample(brandId, productIds, isSample);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductSample error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 修改产品拿样价格
     *
     * @param brandId
     * @param productIds
     * @param samplePrice
     */
    public void updateProductSamplePrice(String brandId, String[] productIds, BigDecimal samplePrice) throws BusinessException
    {
        try
        {
            productService.updateProductSamplePrice(brandId, productIds, samplePrice);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductSamplePrice error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 修改产品拿样价格
     *
     * @param brandId
     * @param productIds
     * @param samplePrice
     */
    public void updateProductSamplePriceBatch(String brandId, String[] productIds, BigDecimal[] samplePrice) throws BusinessException
    {
        try
        {
            productService.updateProductSamplePriceBatch(brandId, productIds, samplePrice);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductSamplePriceBatch error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 根据品牌商id和产品id查找产品
     *
     * @param brandId
     * @param productId
     * @return
     */
    public ProductBaseInfo findByBrandIdAndProductIdWithSkuAndSkuPriceAndBarCode(String brandId, String productId) throws BusinessException
    {
        ProductBaseInfo infos;
        try
        {
            infos = productService.findByBrandIdAndProductIdWithSkuAndSkuPriceAndBarCode(brandId, productId);
        }
        catch (BusinessException e)
        {
            logger.error("findByBrandIdAndProductIdWithSkuAndSkuPriceAndBarCode error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * @param brandId
     * @param infoModel
     * @return
     */
    public List<Map<String, Object>> findProductInfoListByBrandIdAndBaseModelWithPrice(String brandId, ProductBaseInfoModel infoModel) throws BusinessException
    {
        List<Map<String, Object>> infos;
        try
        {
            infos = productService.findProductInfoListByBrandIdAndBaseModelWithPrice(brandId, infoModel);
        }
        catch (Exception e)
        {
            logger.error("findProductInfoListByBrandIdAndBaseModelWithPrice error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 判断
     *
     * @param templateId
     * @return
     */
    public Boolean isTemplateUsed(String templateId) throws BusinessException
    {
        Boolean flag;
        try
        {
            flag = productService.isTemplateUsed(templateId);
        }
        catch (BusinessException e)
        {
            logger.error("isTemplateUsed error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return flag;
    }
    
    /**
     * 根据品牌id和上下架状态查询展示产品
     *
     * @param brandsId
     * @param stateSet
     * @return
     */
    public List<ProductBaseInfo> getShowProductByBrandsIdAndSateSet(String brandsId, String stateSet)
    {
        List<ProductBaseInfo> infos = null;
        try
        {
            infos = productService.getShowProductByBrandsIdAndSateSet(brandsId, stateSet);
        }
        catch (BusinessException e)
        {
            logger.error("getShowProductByBrandsIdAndSateSet error！", e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 保存sku列表
     */
    public void saveProductSkuList(String brandId, List<ProductSku> productSkuList, Boolean credit, Boolean sample, boolean discount) throws BusinessException
    {
        try
        {
            productService.saveProductSkuList(brandId, productSkuList, credit, sample, discount);
        }
        catch (Exception e)
        {
            logger.error("saveProductSkuList error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    
    
    /**
     * 取消置顶产品
     *
     * @param productId
     * @param brandId
     */
    public void executeCancelTop(String productId, String brandId) throws BusinessException
    {
        try
        {
            productService.executeCancelTop(productId, brandId);
        }
        catch (BusinessException e)
        {
            logger.error("executeCancelTop error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 置顶产品
     *
     * @param productId
     * @param brandId
     */
    public void executeSetTop(String productId, String brandId) throws BusinessException
    {
        try
        {
            productService.executeSetTop(productId, brandId);
        }
        catch (BusinessException e)
        {
            logger.error("executeSetTop error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 更新产品运费模板
     */
    public void updateProductFreTemplate(String brandId, String productIds, String templateId) throws BusinessException
    {
        try
        {
            productService.updateProductFreTemplate(brandId, productIds, templateId);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductFreTemplate error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 根据品牌，开始时间，结束时间分页查询产品列表
     *
     * @param params
     * @return
     */
    public PaginateResult<ProductBaseInfo> findByBrandsId(Map<String, Object> params) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result = null;
        try
        {
            result = productService.findByBrandsId(params);
        }
        catch (Exception e)
        {
            logger.error("findByBrandsId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    /**
     * 根据品牌id查询所有产品
     *
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    public List<ProductBaseInfo> findByBrandsIdAll(String brandsId) throws BusinessException
    {
        List<ProductBaseInfo> infos;
        try
        {
            infos = productService.findByBrandsIdAll(brandsId);
        }
        catch (BusinessException e)
        {
            logger.error("findByBrandsIdAll error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据品牌id更新产品终止状态
     *
     * @param brandsId
     * @param stopState
     * @throws BusinessException
     */
    public void updateStopStateByBrandsId(String brandsId, Integer stopState) throws BusinessException
    {
        try
        {
            productService.updateStopStateByBrandsId(brandsId, stopState);
        }
        catch (BusinessException e)
        {
            logger.error("brandsId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 根据品牌和经销商查询产品信息
     *
     * @param brandsId
     * @param dealerId
     * @return
     */
    public List<String> findProductByBrandesIdAndDealerId(String brandsId, String dealerId)
    {
        List<String> infos = null;
        try
        {
            infos = productService.findProductByBrandesIdAndDealerId(brandsId, dealerId);
        }
        catch (Exception e)
        {
            logger.error("findProductByBrandesIdAndDealerId error！", e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据产品id查询产品基本信息和扩展信息
     *
     * @param productId
     * @return
     */
    public ProductBaseInfo findSimpleProductInfo(String productId) throws BusinessException
    {
        ProductBaseInfo infos;
        try
        {
            infos = productService.findSimpleProductInfo(productId);
        }
        catch (BusinessException e)
        {
            logger.error("findSimpleProductInfo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 跟新产品扩展信息
     *
     * @param info
     * @throws BusinessException
     */
    public void updateProductExtInfoSimple(ProductExtInfo info) throws BusinessException
    {
        try
        {
            productService.updateProductExtInfoSimple(info);
        }
        catch (BusinessException e)
        {
            logger.error("updateProductExtInfoSimple error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 根据产品id获取拿样价
     *
     * @param productId
     * @return
     */
    public BigDecimal getSamplePriceByProductId(String productId) throws BusinessException
    {
        BigDecimal bigDecimal;
        try
        {
            bigDecimal = productService.getSamplePriceByProductId(productId);
        }
        catch (Exception e)
        {
            logger.error("getSamplePriceByProductId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return bigDecimal;
    }
    
    /**
     * 根据主键更新产品
     *
     * @param info
     */
    public void updateSelectiveByPrimaryKey(ProductBaseInfo info) throws BusinessException
    {
        try
        {
            productService.updateSelectiveByPrimaryKey(info);
        }
        catch (BusinessException e)
        {
            logger.error("updateSelectiveByPrimaryKey error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 查找授信产品，根据是否设置推荐价格删选
     *
     * @param product
     * @param recommend
     * @return
     */
    public PaginateResult<ProductBaseInfo> findCreditProductAndFilterByRecommendPrice(ProductBaseInfo product, Boolean recommend) throws BusinessException
    {
        PaginateResult<ProductBaseInfo> result = null;
        try
        {
            result = productService.findCreditProductAndFilterByRecommendPrice(product, recommend);
        }
        catch (Exception e)
        {
            logger.error("findCreditProductAndFilterByRecommendPrice error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return result;
    }
    
    public void executeSetShow(String[] productIds, boolean show, String brandId) throws BusinessException
    {
        try
        {
            productService.executeSetShow(productIds, show, brandId);
        }
        catch (Exception e)
        {
            logger.error("executeSetShow error :", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void updateProductTitle(String productId, String title, String brandId) throws BusinessException
    {
        try
        {
            productService.updateProductTitle(productId, title, brandId);
        }
        catch (Exception e)
        {
            logger.error("updateProductTitle error :", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public List<ProductBaseInfo> findBybrandsIdAndPointed(String brandsId)throws BusinessException{
        try{
            return productService.findBybrandsIdAndPointed(brandsId);
        }catch(Exception e){
            logger.error("findBybrandsIdAndPointed error :", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
}
