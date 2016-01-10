package com.zttx.web.dubbo.service;

import com.zttx.goods.module.entity.CateAttribute;
import com.zttx.goods.module.service.CateAttributeService;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>File：CateAttributeDubboConsumer.java </p>
 * <p>Title: 商品属性消费服务 </p>
 * <p>Description:CateAttributeDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class CateAttributeDubboConsumer
{
    public static final Logger   logger = LoggerFactory.getLogger(CateAttributeDubboConsumer.class);
    
    @Autowired(required = false)
    private CateAttributeService cateAttributeService;
    
    /**
     * 根据 属性对象属性作为条件 使用and 关系，分页 
     * @param cateAttribute
     * @return
     */
    public PaginateResult<CateAttribute> findByCateAttributeByPage(CateAttribute cateAttribute) throws BusinessException
    {
        PaginateResult<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findByCateAttributeByPage(cateAttribute);
        }
        catch (BusinessException e)
        {
            logger.error("findByCateAttributeByPage error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据 属性对象属性作为条件 使用and 关系 
     * @param cateAttribute
     * @return
     */
    public List<CateAttribute> findByCateAttribute(CateAttribute cateAttribute) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findByCateAttribute(cateAttribute);
        }
        catch (BusinessException e)
        {
            logger.error("findByCateAttribute error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据属性条件获取属性和属性项
     * @param cateAttribute
     * @return
     * @throws BusinessException
     */
    public List<CateAttribute> findByCateAttributeWithAttrValueItems(CateAttribute cateAttribute) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findByCateAttributeWithAttrValueItems(cateAttribute);
        }
        catch (BusinessException e)
        {
            logger.error("findByCateAttributeWithAttrValueItems error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 查询指定商品类目关联的属性 和属性项
     * @param dealNo
     * @return
     */
    public List<CateAttribute> findProductAttrByDealNo(String dealNo) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findProductAttrByDealNo(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("findProductAttrByDealNo error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 查询指定商品类目关联的属性 和属性项 支撑专用
     * @param dealNo
     * @return
     */
    public List<CateAttribute> findSkuAttributeByDealNo(String dealNo) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findSkuAttributeByDealNo(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("findSkuAttributeByDealNo error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 查询指定商品类目关联的属性 和属性项
     * @param dealNo
     * @return
     */
    public List<CateAttribute> findSkuAttributeByDealNoSimple(String dealNo) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findSkuAttributeByDealNoSimple(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("findSkuAttributeByDealNoSimple error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 新增属性，属性的   `attributeNo` `attributeName` 不允许重复,否则抛出异常
     *
     * @param cateAttribute
     * @return
     */
    public int insert(CateAttribute cateAttribute) throws BusinessException
    {
        int infos = 0;
        try
        {
            infos = cateAttributeService.insert(cateAttribute);
        }
        catch (BusinessException e)
        {
            logger.error("insert error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 保存属性和属性与类目关系
     * @param dealNoRefrenceId
     * @param cateAttrList
     * @return
     * @throws BusinessException
     */
    public int saveCateAttributeAndAttrRel(String dealNoRefrenceId, List<CateAttribute> cateAttrList) throws BusinessException
    {
        int infos = 0;
        try
        {
            infos = cateAttributeService.saveCateAttributeAndAttrRel(dealNoRefrenceId, cateAttrList);
        }
        catch (BusinessException e)
        {
            logger.error("saveCateAttributeAndAttrRel error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 更新 类目
     * 类目的 dealNo，dealName，不允许重复,否则抛出异常 
     * @param cateAttribute
     * @return 返回新创建的对象
     */
    public int updateCateAttribute(CateAttribute cateAttribute) throws BusinessException
    {
        int infos = 0;
        try
        {
            infos = cateAttributeService.updateCateAttribute(cateAttribute);
        }
        catch (BusinessException e)
        {
            logger.error("updateCateAttribute error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据主键逻辑删除
     * @param primaryKey
     * @return
     * @throws BusinessException
     */
    public int deleteCateAttribute(String primaryKey) throws BusinessException
    {
        int infos = 0;
        try
        {
            infos = cateAttributeService.deleteCateAttribute(primaryKey);
        }
        catch (BusinessException e)
        {
            logger.error("deleteCateAttribute error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据分类和产品id查找属性和属性项列表，属性项值自定义
     * @param dealNo
     * @param productId
     * @return
     * @throws BusinessException
     */
    public List<CateAttribute> findSkuAttributeByDealNoAndProductId(String dealNo, String productId) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findSkuAttributeByDealNoAndProductId(dealNo, productId);
        }
        catch (BusinessException e)
        {
            logger.error("findSkuAttributeByDealNoAndProductId error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据dealNo获取分类信息
     * @param dealNo
     * @param skuAttr
     * @return
     * @throws BusinessException
     */
    public List<CateAttribute> findCateAttributesByDealNo(Long dealNo, Boolean skuAttr) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.findCateAttributesByDealNo(dealNo, skuAttr);
        }
        catch (BusinessException e)
        {
            logger.error("findCateAttributesByDealNo error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 处理销售属性
     * @param saleAttributes
     * @param productId
     * @return
     * @throws BusinessException
     */
    public List<CateAttribute> getSaleAttributes(List<CateAttribute> saleAttributes, String productId) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.getSaleAttributes(saleAttributes, productId);
        }
        catch (BusinessException e)
        {
            logger.error("getSaleAttributes error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 处理产品属性
     * @param proAttributes
     * @param productId
     * @return
     * @throws BusinessException
     */
    public List<CateAttribute> getProAttributes(List<CateAttribute> proAttributes, String productId) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = cateAttributeService.getProAttributes(proAttributes, productId);
        }
        catch (BusinessException e)
        {
            logger.error("getProAttributes error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据id查找
     * @param refrenceId
     * @return
     * @throws BusinessException
     */
    public CateAttribute selectByRefrenceId(String refrenceId) throws BusinessException
    {
        CateAttribute infos = null;
        try
        {
            infos = cateAttributeService.selectByRefrenceId(refrenceId);
        }
        catch (BusinessException e)
        {
            logger.error("selectByRefrenceId error！", e.getLocalizedMessage());
            throw new  BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
}
