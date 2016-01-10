package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * <p>File:CreditToPointDubboService</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/18 10:00</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public interface CreditToPointDubboService {
    /**
     * erp调用插入铺货转返点后的剩余铺货信息
     * 记录品牌商修改产品交易类型为返点类型，原本的铺货类型产品变更为返点类型后未销售的剩余产品信息，并在财务账应付款中减掉相应的款数
     * @author 易永耀
     * @param mapList
     *      dealerId        经销商id
     *      productSkuId    产品skuid
     *      costPirce       sku成本价
     *      baseStock       erp基础库存量
     *      sumCost         成本合计
     *      pointPrice      返点价
     *      pointPercent    返点比例
     *      erpTime         erp交易类型变更时间
     * @throws BusinessException
     */
    public JsonMessage saveCreditToPoint(List<Map> mapList) throws BusinessException;
}
