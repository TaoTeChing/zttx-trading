package com.zttx.web.dubbo.service;

import java.util.List;

import com.zttx.sdk.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.goods.module.entity.CateAttribute;
import com.zttx.goods.module.entity.CateAttributeItemRel;
import com.zttx.goods.module.entity.CateAttributeRel;
import com.zttx.goods.module.entity.DealDic;
import com.zttx.goods.module.service.DealDicService;

/**
 * <p>File：DealDicServiceDubboConsumer.java </p>
 * <p>Title: 类目字典消息服务 </p>
 * <p>Description:DealDicServiceDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class DealDicServiceDubboConsumer
{
    public static final Logger logger = LoggerFactory.getLogger(DealerProductDubboConsumer.class);
    
    @Autowired(required = false)
    private DealDicService     dealDicService;
    
    /**
     * 根据 参数 查询 商品类目信息,DealDic 对象属性作为条件 使用and 关系
     *
     * @param dealDic
     * @return
     */
    public List<DealDic> findByDealDic(DealDic dealDic) throws BusinessException
    {
        List<DealDic> infos = null;
        try
        {
            infos = dealDicService.findByDealDic(dealDic);
        }
        catch (BusinessException e)
        {
            logger.error("findByDealDic error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据DealNo查找DealDic
     *
     * @param dealNo
     * @return
     */
    public DealDic findByDealNo(Long dealNo) throws BusinessException
    {
        DealDic info = null;
        try
        {
            info = dealDicService.findByDealNo(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("findByDealNo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 获取全名
     *
     * @param dealNo
     * @return
     */
    public String getFullDealDicNamesBy(Long dealNo) throws BusinessException
    {
        String info = null;
        try
        {
            info = dealDicService.getFullDealDicNamesBy(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("getFullDealDicNamesBy error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 查询一级类目
     *
     * @return
     */
    public List<DealDic> findFirstLever() throws BusinessException
    {
        List<DealDic> infos = null;
        try
        {
            infos = dealDicService.findFirstLever();
        }
        catch (BusinessException e)
        {
            logger.error("findFirstLever error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 获取一级类目及其级联类目
     *
     * @return
     */
    public List<DealDic> findLeversCascade() throws BusinessException
    {
        List<DealDic> infos = null;
        try
        {
            infos = dealDicService.findLeversCascade();
        }
        catch (BusinessException e)
        {
            logger.error("findLeversCascade error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 新建 类目
     * 类目的 dealNo，dealName，不允许重复,否则抛出异常
     *
     * @param dealDic
     * @return 返回新创建的对象
     */
    public DealDic createDealdic(DealDic dealDic) throws BusinessException
    {
        DealDic info = null;
        try
        {
            info = dealDicService.createDealdic(dealDic);
        }
        catch (BusinessException e)
        {
            logger.error("createDealdic error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 更新 类目
     * 类目的 dealNo，dealName，不允许重复,否则抛出异常
     *
     * @param dealDic
     * @return 返回新创建的对象
     */
    public int updateDealdic(DealDic dealDic) throws BusinessException
    {
        int info = 0;
        try
        {
            info = dealDicService.updateDealdic(dealDic);
        }
        catch (BusinessException e)
        {
            logger.error("updateDealdic error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 为类目批量添加 关联属性关系
     *
     * @param cateAttributeRelList
     * @return 返回已添加的属性行数
     */
    public int addCateAttribute(List<CateAttributeRel> cateAttributeRelList) throws BusinessException
    {
        int info = 0;
        try
        {
            info = dealDicService.addCateAttribute(cateAttributeRelList);
        }
        catch (BusinessException e)
        {
            logger.error("addCateAttribute error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 删除类目
     *
     * @param primaryKey
     * @return
     */
    public int deleteDealdic(String primaryKey) throws BusinessException
    {
        int info = 0;
        try
        {
            info = dealDicService.deleteDealdic(primaryKey);
        }
        catch (BusinessException e)
        {
            logger.error("deleteDealdic error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 返回属性和属性项
     *
     * @param dealDicId
     * @return
     * @throws Exception
     */
    public List<CateAttribute> findCasCadeByDealDicId(String dealDicId) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = dealDicService.findCasCadeByDealDicId(dealDicId);
        }
        catch (BusinessException e)
        {
            logger.error("findCasCadeByDealDicId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 更具货号获取属性和属性项
     *
     * @param dealNo
     * @return
     */
    public List<CateAttribute> findCasCadeByDealNo(Long dealNo) throws BusinessException
    {
        List<CateAttribute> infos = null;
        try
        {
            infos = dealDicService.findCasCadeByDealNo(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("BusinessException error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return infos;
    }
    
    /**
     * 根据子dealNo查询父DealDic
     *
     * @param child
     * @return
     */
    public DealDic findParentByChild(String child) throws BusinessException
    {
        DealDic info = null;
        try
        {
            info = dealDicService.findParentByChild(child);
        }
        catch (BusinessException e)
        {
            logger.error("findParentByChild error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 获取父子名称组合
     *
     * @param dealNo
     * @return
     */
    public String[] getDealNamesWithParentByDealNo(String dealNo) throws BusinessException
    {
        String[] info = null;
        try
        {
            info = dealDicService.getDealNamesWithParentByDealNo(dealNo);
        }
        catch (BusinessException e)
        {
            logger.error("getDealNamesWithParentByDealNo error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 批量新增属性和属性项与类目关系
     *
     * @param dealNo
     * @param cateAttributeRelList
     * @param itemRelList
     * @return
     */
    public int saveAndUpdateCateAttributeAndItemToDealDic(String dealNo, List<CateAttributeRel> cateAttributeRelList, List<CateAttributeItemRel> itemRelList)
            throws BusinessException
    {
        int info = 0;
        try
        {
            info = dealDicService.saveAndUpdateCateAttributeAndItemToDealDic(dealNo, cateAttributeRelList, itemRelList);
        }
        catch (BusinessException e)
        {
            logger.error("saveAndUpdateCateAttributeAndItemToDealDic error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
}
