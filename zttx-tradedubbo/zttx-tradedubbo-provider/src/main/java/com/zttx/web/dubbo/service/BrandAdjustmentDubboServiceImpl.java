package com.zttx.web.dubbo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.model.BrandAdjustmentModel;
import com.zttx.web.module.common.entity.Adjustment;
import com.zttx.web.module.common.entity.Adjustments;
import com.zttx.web.module.common.mapper.AdjustmentMapper;
import com.zttx.web.module.common.mapper.AdjustmentsMapper;

/**
 * Created by Administrator on 2015/7/13.
 */
@Service
public class BrandAdjustmentDubboServiceImpl implements BrandAdjustmentDubboService
{
    @Autowired
    private AdjustmentMapper            adjustmentMapper;
    
    @Autowired
    private AdjustmentsMapper           adjustmentsMapper;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Override
    public void receiveErpPrice(List<BrandAdjustmentModel> adjustmentModelList) throws BusinessException
    {
        if (CollectionUtils.isEmpty(adjustmentModelList)) { throw new BusinessException(BrandConst.NULLERROR); }
        Map<String, Adjustment> result = this.setAdjustmentModel(adjustmentModelList);
        if (result.size() == 0) { throw new BusinessException(BrandConst.FAILURE); }
        adjustmentMapper.insertBatch(new ArrayList<Adjustment>(result.values()));
        for (Adjustment Adjustment : result.values())
        {
            adjustmentsMapper.insertBatch(Adjustment.getAdjustmentsList());
        }
    }
    
    private Map<String, Adjustment> setAdjustmentModel(List<BrandAdjustmentModel> adjustmentModelList) throws BusinessException
    {
        Map<String, Adjustment> result = Maps.newHashMap();
        for (BrandAdjustmentModel model : adjustmentModelList)
        {
            String dealerId = model.getDealerId();
            if (null == model.getNewCostPrice()) throw new BusinessException(CommonConst.FAIL, "newCostPrice为null");
            if (null == model.getOldCostPrice()) throw new BusinessException(CommonConst.FAIL, "oldCostPrice为null");
            if (null == model.getQuantity()) throw new BusinessException(CommonConst.FAIL, "quantity为null");
            ProductSku sku = productSkuInfoDubboConsumer.findProductSku(model.getProductSkuId());
            ProductBaseInfo product = productInfoDubboConsumer.getProductById(sku.getProductId());
            if (dealerId != null)
            {
                String keyId = dealerId + product.getBrandId();
                if (result.get(keyId) == null)
                {
                    Adjustment brandAdjustment = new Adjustment();
                    brandAdjustment.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandAdjustment.setCreateTime(CalendarUtils.getCurrentTime());
                    brandAdjustment.setUpdateTime(brandAdjustment.getCreateTime());
                    brandAdjustment.setDealerId(dealerId);
                    brandAdjustment.setBrandId(product.getBrandId());
                    Adjustments brandAdjustments = new Adjustments();
                    brandAdjustments.setBrandAdjustId(brandAdjustment.getRefrenceId());
                    brandAdjustments.setProductSkuId(model.getProductSkuId());
                    brandAdjustments.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandAdjustments.setProductId(product.getRefrenceId());
                    brandAdjustments.setCreateTime(brandAdjustment.getCreateTime());
                    brandAdjustments.setUpdateTime(brandAdjustments.getCreateTime());
                    brandAdjustments.setOldFacSkuDirPrice(model.getOldCostPrice());
                    brandAdjustments.setNowFacSkuDirPrice(model.getNewCostPrice());
                    brandAdjustments.setQuantity(model.getQuantity().intValue());
                    brandAdjustments.setTotalAdjustPrice(model.getNewCostPrice().subtract(model.getOldCostPrice()).multiply(new BigDecimal(model.getQuantity())));
                    brandAdjustment.setAdjustAllPrice(brandAdjustments.getTotalAdjustPrice());
                    if (brandAdjustment.getAdjustmentsList() == null)
                    {
                        brandAdjustment.setAdjustmentsList(new ArrayList<Adjustments>());
                    }
                    brandAdjustment.getAdjustmentsList().add(brandAdjustments);
                    result.put(keyId, brandAdjustment);
                }
                else
                {
                    Adjustment brandAdjustment = result.get(keyId);
                    Adjustments brandAdjustments = new Adjustments();
                    brandAdjustments.setBrandAdjustId(brandAdjustment.getRefrenceId());
                    brandAdjustments.setProductSkuId(model.getProductSkuId());
                    brandAdjustments.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandAdjustments.setProductId(product.getRefrenceId());
                    brandAdjustments.setCreateTime(brandAdjustment.getCreateTime());
                    brandAdjustments.setUpdateTime(brandAdjustments.getCreateTime());
                    brandAdjustments.setOldFacSkuDirPrice(model.getOldCostPrice());
                    brandAdjustments.setNowFacSkuDirPrice(model.getNewCostPrice());
                    brandAdjustments.setQuantity(model.getQuantity().intValue());
                    brandAdjustments.setTotalAdjustPrice(model.getNewCostPrice().subtract(model.getOldCostPrice()).multiply(new BigDecimal(model.getQuantity())));
                    brandAdjustment.setAdjustAllPrice(brandAdjustment.getAdjustAllPrice().add(brandAdjustments.getTotalAdjustPrice()));
                    if (brandAdjustment.getAdjustmentsList() == null)
                    {
                        brandAdjustment.setAdjustmentsList(new ArrayList<Adjustments>());
                    }
                    brandAdjustment.getAdjustmentsList().add(brandAdjustments);
                }
            }
        }
        return result;
    }
}
