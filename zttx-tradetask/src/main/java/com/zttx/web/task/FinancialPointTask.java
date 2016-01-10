package com.zttx.web.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.erp.module.statement.model.SalesStateMentModel;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.dubbo.service.SellOrderServiceDubboConsumer;
import com.zttx.web.module.common.entity.PointSaleDetail;
import com.zttx.web.module.common.entity.PointSaleSum;
import com.zttx.web.module.common.service.PointSaleDetailService;
import com.zttx.web.module.common.service.PointSaleSumService;
import com.zttx.web.module.common.service.StockAdjustDetailService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;

/**
 * 返点财务帐调度
 * Created by 李星 on 2015/11/23.
 */
public class FinancialPointTask extends AbstractQuartzBean implements Serializable
{
    private final static Logger                  logger                 = LoggerFactory.getLogger(FinancialPointTask.class);
    
    private final static String                  SPLIT_FLAG                  = "_";
    
    private static SellOrderServiceDubboConsumer sellOrderDubboService  = null;
    
    private static PointSaleSumService           pointSaleSumService    = null;
    
    private static PointSaleDetailService        pointSaleDetailService = null;
    
    private static ProductSkuInfoDubboConsumer   productSkuInfoDubboConsumer = null;

    private static StockAdjustDetailService stockAdjustDetailService = null;

    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始FinancialPointTask调度");
        try
        {
            initialize(getApplicationContext(jobexecutioncontext));
            // 同步主表数据
            Object[] result = synchroPointSaleSum();
            // 同步子表数据
            Map<String, String> queryTerms = (Map<String, String>) result[0];
            synchroPointSaleDetail(queryTerms);
            // 返回成功编码给ERP
            List<String> brandSellDayIdList = (List<String>) result[1];
            if (null != brandSellDayIdList && !brandSellDayIdList.isEmpty())
            {
                sellOrderDubboService.setBrandSellDaySuccessForRefund(brandSellDayIdList);
            }
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "FinancialPointTask调度异常:" + e.getMessage());
        }
        LoggerUtils.logInfo(logger, "结束FinancialPointTask调度");
    }
    
    // 同步返点类型产品门店销售统计和数据
    private Object[] synchroPointSaleSum() throws BusinessException
    {
        Object[] resultArr = new Object[2];
        List<String> brandSellDayIdList = Lists.newArrayList(); // 返回给ERP设置成功的返点财务账的状态
        Map<String, String> cacheQueryTerms4Detail = Maps.newHashMap();// key : dealerId_brandId value : dayTime
        Pagination pagination = new Pagination(1, 100);
        boolean isFirst = true;
        while (isFirst || pagination.getHasNextPage())
        {
            List<PointSaleSum> listPointSaleSum = Lists.newArrayList();
            isFirst = false;
            PaginateResult<Map<String, Object>> result = sellOrderDubboService.getBrandSellDayForRefund(pagination);
            if (null != result)
            {
                for (Map<String, Object> map : result.getList())
                {
                    String sellDayId = MapUtils.getString(map, "sellDayId");
                    String dealerId = MapUtils.getString(map, "dealerId", "");
                    String brandId = MapUtils.getString(map, "supplierId", "");
                    String brandsId = MapUtils.getString(map, "brandId", "");
                    PointSaleSum pointSaleSum = new PointSaleSum();
                    pointSaleSum.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    pointSaleSum.setDealerId(dealerId);
                    pointSaleSum.setBrandId(brandId);
                    pointSaleSum.setBrandsId(brandsId); // 品牌编号
                    try
                    {
                        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId) && null != map.get("dayTime"))
                        {
                            Integer saleNum = MapUtils.getInteger(map, "saleNum"); // 销售数量
                            Integer backNum = MapUtils.getInteger(map, "backNum"); // 退货数量
                            Integer pkNum = MapUtils.getInteger(map, "pkNum"); // 盘亏数量
                            Double saleAmount = MapUtils.getDouble(map, "saleAmount"); // 销售金额
                            Double backAmount = MapUtils.getDouble(map, "backAmount"); // 退货金额
                            Double pkAmount = MapUtils.getDouble(map, "pkAmount"); // 盘亏金额
                            Double saleRefundAmount = MapUtils.getDouble(map, "saleRefundAmount"); // 销售返点金额 = 返点价*返点比例*销售数量
                            Double backRefundAmount = MapUtils.getDouble(map, "backRefundAmount"); // 退货返点金额 = 返点价*返点比例*退货数量
                            Double pkRefundAmount = MapUtils.getDouble(map, "pkRefundAmount"); // 盘亏返点金额
                            pointSaleSum.setSaleNumSum(saleNum - backNum + pkNum); // 销量和 = 销售数量 - 退货数量 + 盘亏数量
                            pointSaleSum.setSalePriceSum(new BigDecimal(saleAmount - backAmount)); // 销售额和 = 销售金额 - 退货金额
                            pointSaleSum.setPointPriceSum(new BigDecimal(saleRefundAmount - backRefundAmount)); // 返点金额和（非返点价和）= 返点价*返点比例*（销售数量-退货数量）
                            // 成本价和=销售金额-销售返点金额 +盘亏金额-盘亏返点金额-（退货金额-退货返点金额） 或者 成本价和 = 返点价*（1-返点比例）*（销售数量-退货数量）+返点价*（1-返点比例）*盘亏数量
                            Double chengbenjia = saleAmount - saleRefundAmount + pkAmount - pkRefundAmount - (backAmount - backRefundAmount);
                            pointSaleSum.setCostPriceSum(new BigDecimal(chengbenjia));
                            pointSaleSum.setPaidPriceSum(BigDecimal.ZERO); // 已付成本价和
                            pointSaleSum.setDebtPriceSum(pointSaleSum.getCostPriceSum()); // 未付成本价和
                            pointSaleSum.setIsPaid(false);
                            pointSaleSum.setErpTime(Long.parseLong(map.get("dayTime").toString()));
                            pointSaleSum.setCreateTime(CalendarUtils.getCurrentLong());
                            pointSaleSum.setDelFlag(false);
                            listPointSaleSum.add(pointSaleSum);
                            brandSellDayIdList.add(sellDayId);
                            cacheQueryTerms4Detail.put(dealerId + SPLIT_FLAG + brandId, map.get("dayTime").toString());
                        }
                    }
                    catch (Exception e)
                    {
                        LoggerUtils.logError(logger, "调度-同步返点类型产品门店销售统计和出错:sellDayId=" + sellDayId + "," + e.getLocalizedMessage());
                    }
                }
            }
            if (null != listPointSaleSum && !listPointSaleSum.isEmpty())
            {
                pointSaleSumService.insertBatch(listPointSaleSum);
            }
            pagination = result.getPage();
            pagination.setCurrentPage(pagination.getCurrentPage() + 1);
        }
        resultArr[0] = cacheQueryTerms4Detail;
        resultArr[1] = brandSellDayIdList;
        return resultArr;
    }
    
    // 同步返点财务帐销售明细数据
    private void synchroPointSaleDetail(Map<String, String> queryTerms) throws BusinessException
    {
        if (null == queryTerms || queryTerms.size() == 0) throw new BusinessException("参数异常:查询返点财务帐销售明细参数为空");
        Iterator<String> iterator = queryTerms.keySet().iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            String[] keys = key.split(SPLIT_FLAG);
            String dealerId = keys[0];
            String brandId = keys[1];
            SalesStateMentModel model = new SalesStateMentModel();
            model.setZttxDealerId(dealerId);
            model.setSupplierId(brandId);
            model.setSearchTime(Long.parseLong(queryTerms.get(key)));
            Pagination pagination = new Pagination(1, 100);
            boolean isFirst = true;
            while (isFirst || pagination.getHasNextPage())
            {
                List<PointSaleDetail> listPointSaleDetail = Lists.newArrayList();
                isFirst = false;
                PaginateResult<Map<String, Object>> result = sellOrderDubboService.getSellDetailForRefund(pagination, model);
                if (null != result)
                {
                    for (Map<String, Object> map : result.getList())
                    {
                        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId))
                        {
                            String skuId = MapUtils.getString(map, "skuId");
                            String saleNum = MapUtils.getString(map, "saleNum");
                            String refundNum = MapUtils.getString(map, "backNum");
                            String salePrice = MapUtils.getString(map, "saleTotalPrice");
                            String refundPrice = MapUtils.getString(map, "backTotalPrice");
                            String lossNum = MapUtils.getString(map, "pkNum");
                            String lossCost = MapUtils.getString(map, "pkTotalCostPrice");
                            String saleCost = MapUtils.getString(map, "saleTotalCostPrice");
                            String refundCost = MapUtils.getString(map, "backTotalCostPrice");
                            if(StringUtils.isBlank(skuId)) throw new BusinessException("参数异常:ERP返点财务帐明细数据空");
                            PointSaleDetail pointSaleDetail = new PointSaleDetail();
                            pointSaleDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                            pointSaleDetail.setDealerId(dealerId);
                            pointSaleDetail.setBrandId(brandId);
                            pointSaleDetail.setProductSkuId(skuId);
                            if (StringUtils.isNotBlank(skuId))
                            {
                                ProductSku sku = productSkuInfoDubboConsumer.findProductSku(skuId);
                                pointSaleDetail.setProductId(sku.getProductId());
                                pointSaleDetail.setBrandsId(sku.getBrandsId()); // 品牌编号
                            }
                            pointSaleDetail.setSaleNum(Integer.parseInt(saleNum));
                            pointSaleDetail.setRefundNum(Integer.parseInt(refundNum));
                            pointSaleDetail.setSalePrice(new BigDecimal(salePrice));
                            pointSaleDetail.setRefundPrice(new BigDecimal(refundPrice));
                            pointSaleDetail.setLossNum(Integer.parseInt(lossNum));
                            pointSaleDetail.setLossCost(new BigDecimal(lossCost));
                            pointSaleDetail.setSaleCost(new BigDecimal(saleCost));
                            pointSaleDetail.setRefundCost(new BigDecimal(refundCost));
                            pointSaleDetail.setCountCost(pointSaleDetail.getLossCost().add(pointSaleDetail.getSaleCost()).subtract(pointSaleDetail.getRefundCost()));
                            pointSaleDetail.setErpTime(Long.parseLong(queryTerms.get(key)));
                            pointSaleDetail.setCreateTime(CalendarUtils.getCurrentLong());
                            pointSaleDetail.setDelFlag(false);
                            listPointSaleDetail.add(pointSaleDetail);
                        }
                    }
                }
                if (null != listPointSaleDetail && !listPointSaleDetail.isEmpty())
                {
                        pointSaleDetailService.insertPointSaleDetailBatch(listPointSaleDetail);
                }
                else throw new BusinessException("调度异常:返点财务帐ERP销售明细数据为空");
                pagination = result.getPage();
                pagination.setCurrentPage(pagination.getCurrentPage() + 1);
            }
        }
    }
    
    public static void initialize(ApplicationContext applicationContext)
    {
        if (null == sellOrderDubboService) sellOrderDubboService = applicationContext.getBean(SellOrderServiceDubboConsumer.class);
        if (null == pointSaleSumService) pointSaleSumService = applicationContext.getBean(PointSaleSumService.class);
        if (null == pointSaleDetailService) pointSaleDetailService = applicationContext.getBean(PointSaleDetailService.class);
        if (null == productSkuInfoDubboConsumer) productSkuInfoDubboConsumer = applicationContext.getBean(ProductSkuInfoDubboConsumer.class);


    }
}
