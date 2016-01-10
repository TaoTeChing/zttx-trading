package com.zttx.web.dubbo.service;

import com.zttx.goods.module.entity.CateAttributeItem;
import com.zttx.goods.module.service.CateAttributeItemService;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>File：CateAttributeItemDubboConsumer.java </p>
 * <p>Title: 商品属性项消息服务 </p>
 * <p>Description:CateAttributeItemDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class CateAttributeItemDubboConsumer
{
    public static final Logger       logger = LoggerFactory.getLogger(CateAttributeItemDubboConsumer.class);
    
    @Autowired(required = false)
    private CateAttributeItemService cateAttributeItemService;
    
    public CateAttributeItem selectByPrimaryKey(String refrenceId) throws BusinessException
    {
        CateAttributeItem info = null;
        try
        {
            info = cateAttributeItemService.selectByPrimaryKey(refrenceId);
        }
        catch (Exception e)
        {
            logger.error("selectByPrimaryKey error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据属性id  查询属性相关的项值
     * @param attributeId
     * @return
     */
    public List<CateAttributeItem> findByCateAttribute(String attributeId) throws BusinessException
    {
        List<CateAttributeItem> info = null;
        try
        {
            info = cateAttributeItemService.findByCateAttribute(attributeId);
        }
        catch (Exception e)
        {
            logger.error("findByCateAttribute error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 分页查询
     * 据属性id  查询属性相关的项值
     * @param cateAttributeItem
     * @return
     * @throws BusinessException
     */
    public PaginateResult<CateAttributeItem> findByCateAttributeByPage(CateAttributeItem cateAttributeItem) throws BusinessException
    {
        PaginateResult<CateAttributeItem> info = null;
        try
        {
            info = cateAttributeItemService.findByCateAttributeByPage(cateAttributeItem);
        }
        catch (Exception e)
        {
            logger.error("findByCateAttributeByPage error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 新增属性项
     * 在同一个attributeId 下    attributeItem 不允许重复
     * @param cateAttributeItemList
     * @return CateAttributeItem
     */
    public int insertCateAttributeItem(CateAttributeItem cateAttributeItemList) throws BusinessException
    {
        int info = 0;
        try
        {
            info = cateAttributeItemService.insertCateAttributeItem(cateAttributeItemList);
        }
        catch (Exception e)
        {
            logger.error("insertCateAttributeItem error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 更新 类目
     * 类目的 dealNo，dealName，不允许重复,否则抛出异常 
     * @param cateAttributeItem
     * @return 返回新创建的对象
     */
    public CateAttributeItem updateCateAttributeItem(CateAttributeItem cateAttributeItem) throws BusinessException
    {
        CateAttributeItem info = null;
        try
        {
            info = cateAttributeItemService.updateCateAttributeItem(cateAttributeItem);
        }
        catch (Exception e)
        {
            logger.error("updateCateAttributeItem error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 删除属性项
     * @param refrenceId
     * @return
     */
    public int deleteCateAttributeItem(String refrenceId) throws BusinessException
    {
        int info = 0;
        try
        {
            info = cateAttributeItemService.deleteCateAttributeItem(refrenceId);
        }
        catch (Exception e)
        {
            logger.error("deleteCateAttributeItem error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
}
