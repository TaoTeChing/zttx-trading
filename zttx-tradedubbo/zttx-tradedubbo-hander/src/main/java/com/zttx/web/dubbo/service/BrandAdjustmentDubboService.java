package com.zttx.web.dubbo.service;

import java.util.List;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandAdjustmentModel;

/**
 * Created by Administrator on 2015/7/13.
 */
public interface BrandAdjustmentDubboService
{
    
    /**
     * 接收门店推送的SKU调整价格
     * @param adjustmentModels
     * @throws BusinessException
     */
    void receiveErpPrice(List<BrandAdjustmentModel> adjustmentModels) throws BusinessException;

}
