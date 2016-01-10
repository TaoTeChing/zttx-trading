package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * <p>File:PointRefundDubboService</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/18 10:00</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public interface PointRefundDubboService {
    /**
     * erp调用插入返点退货数据记录
     * @author 易永耀
     * @param mapList
     *      dealerId        经销商id
     *      productSkuId    产品skuId
     *      refundNum       退货数量
     *      pointPrice      返点价
     *      pointPercent    返点比例
     *      erpTime         erp退货记录生成时间
     * @throws BusinessException
     */
    public JsonMessage savePointRefund(List<Map> mapList) throws BusinessException;
}
