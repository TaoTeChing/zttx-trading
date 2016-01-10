package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.erp.module.dubbo.service.provider.SellOrderDubboService;
import com.zttx.erp.module.statement.model.SalesStateMentModel;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：SellOrderServiceDubboConsumer.java </p>
 * <p>Title: 每日财务帐消费服务 </p>
 * <p>Description:SellOrderServiceDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class SellOrderServiceDubboConsumer
{
    public static final Logger    logger = LoggerFactory.getLogger(SellOrderServiceDubboConsumer.class);
    
    @Autowired(required = false)
    private SellOrderDubboService sellOrderDubboService;
    
    public void setBrandSellDaySuccess(List<String> var1) throws BusinessException
    {
        try
        {
            sellOrderDubboService.setBrandSellDaySuccess(var1);
        }
        catch (BusinessException e)
        {
            logger.error("setBrandSellDaySuccess error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    public PaginateResult<Map<String, Object>> getBrandSellDay(Pagination var1) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info = null;
        try
        {
            info = sellOrderDubboService.getBrandSellDay(var1);
        }
        catch (BusinessException e)
        {
            logger.error("getBrandSellDay error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    public PaginateResult<Map<String, Object>> getSellDetail(Pagination var1, SalesStateMentModel var2) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info = null;
        try
        {
            info = sellOrderDubboService.getSellDetail(var1, var2);
        }
        catch (BusinessException e)
        {
            logger.error("getSellDetail error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 返点财务帐综合数据
     * @param page
     * @return
     * @throws BusinessException
     */
    public PaginateResult<Map<String, Object>> getBrandSellDayForRefund(Pagination page) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info = null;
        try
        {
            info = sellOrderDubboService.getBrandSellDayForRefund(page);
        }
        catch (BusinessException e)
        {
            logger.error("getBrandSellDayForRefund error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 返点财务帐详细数据
     * @param page
     * @param searchModel
     * @return
     * @throws BusinessException
     */
    public PaginateResult<Map<String, Object>> getSellDetailForRefund(Pagination page, SalesStateMentModel searchModel) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info = null;
        try
        {
            info = sellOrderDubboService.getSellDetailForRefund(page, searchModel);
        }
        catch (BusinessException e)
        {
            logger.error("getSellDetailForRefund error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 设置成功的返点财务账的状态
     *
     * @param brandSellDayIdList 品牌财务账表的主键ID
     * @return
     * @throws BusinessException
     */
    public void setBrandSellDaySuccessForRefund(List<String> brandSellDayIdList) throws BusinessException
    {
        try
        {
            sellOrderDubboService.setBrandSellDaySuccessForRefund(brandSellDayIdList);
        }
        catch (BusinessException e)
        {
            logger.error("setBrandSellDaySuccessForRefund error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 获取产品库存
     * @param searchModel 查询条件
     *          supplierId 品牌商编号(与交易平台相同)(必填)
     *          brandId 品牌编号(与交易平台相同)(非必填)
     *          productName 产品名称（ERP数据库中的产品名称与交易平台中的可能不同），模糊查询(非必填)
     *          productNo 产品货号(与交易平台相同)，模糊查询(非必填)
     *          zttxDealerIdList 经销商编号列表(与交易平台相同)(非必填)
     *          isSku 是否按SKU查看（否则按产品级别查看）
     * @param page 分页参数
     * @return
     *          brandName 品牌名称
     *          brandsId 品牌编号
     *          productNo 产品货号
     *          productName 产品名称
     *          skuId（只有isSku为true的时候，才有）
     *          colorSize 规格（只有isSku为true的时候，才有）
     *          dealerName 经销商名称
     *          zttxDealerId 经销商编号
     *          realStorage 库存
     * @throws BusinessException
     */
    public PaginateResult<Map<String, Object>> getBrandStorageData(SalesStateMentModel searchModel, Pagination page) throws BusinessException
    {
        PaginateResult<Map<String, Object>> info;
        try
        {
            info = sellOrderDubboService.getBrandStorageData(searchModel, page);
        }
        catch (BusinessException e)
        {
            logger.error("getBrandStorageData error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
}
