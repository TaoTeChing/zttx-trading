package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zttx.sdk.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.erp.module.dubbo.model.YgPriceModel;
import com.zttx.erp.module.dubbo.service.provider.DealerProductDubboService;
import com.zttx.erp.module.product.entity.ProductInfo;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;

/**
 * <p>File：DealerProductDubboConsumer.java </p>
 * <p>Title: 门店产品消费服务 </p>
 * <p>Description:DealerProductDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class DealerProductDubboConsumer
{
    public static final Logger        logger = LoggerFactory.getLogger(DealerProductDubboConsumer.class);
    
    @Autowired(required = false)
    private DealerProductDubboService dealerProductDubboService;
    
    public void updateSkuChanged(Set<Long> var1) throws BusinessException
    {
        try
        {
            dealerProductDubboService.updateSkuChanged(var1);
        }
        catch (Exception e)
        {
            logger.error("updateSkuChanged error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void updateForSynchCenter(ProductBaseInfo var1) throws BusinessException
    {
        try
        {
            dealerProductDubboService.updateForSynchCenter(var1);
        }
        catch (BusinessException e)
        {
            logger.error("updateForSynchCenter error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void updateForSynchCenter(Map<String, Object> var1) throws BusinessException
    {
        try
        {
            dealerProductDubboService.updateForSynchCenter(var1);
        }
        catch (BusinessException e)
        {
            logger.error("updateForSynchCenter error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void priceChange(List<Map<String, Object>> var1) throws BusinessException
    {
        try
        {
            dealerProductDubboService.priceChange(var1);
        }
        catch (BusinessException e)
        {
            logger.error("priceChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void savePriceChange(List<Map<String, Object>> var1) throws BusinessException
    {
        try
        {
            dealerProductDubboService.savePriceChange(var1);
        }
        catch (BusinessException e)
        {
            logger.error("savePriceChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public void saveForYgProduct(String var1, List<ProductBaseInfo> var2, List<YgPriceModel> var3) throws BusinessException
    {
        try
        {
            dealerProductDubboService.saveForYgProduct(var1, var2, var3);
        }
        catch (BusinessException e)
        {
            logger.error("updateSkuChanged error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public boolean isSkuUsing(Long var1, String var2) throws BusinessException
    {
        Boolean info = Boolean.FALSE;
        try
        {
            info = dealerProductDubboService.isSkuUsing(var1, var2);
        }
        catch (Exception e)
        {
            logger.error("isSkuUsing error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    public Map<Long, Boolean> isSkuUsing(Set<Long> var1, String var2) throws BusinessException
    {
        Map<Long, Boolean> info = null;
        try
        {
            info = dealerProductDubboService.isSkuUsing(var1, var2);
        }
        catch (Exception e)
        {
            logger.error("isSkuUsing error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    public List<YgPriceModel> listYgModel(Set<Long> var1, String var2) throws BusinessException
    {
        List<YgPriceModel> info = null;
        try
        {
            info = dealerProductDubboService.listYgModel(var1, var2);
        }
        catch (Exception e)
        {
            logger.error("listYgModel error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    public PaginateResult<Map<String, Object>> searchYgProduct(String var1, Pagination var2, ProductInfo var3) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info = null;
        try
        {
            info = dealerProductDubboService.searchYgProduct(var1, var2, var3);
        }
        catch (Exception e)
        {
            logger.error("searchYgProduct error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
}
