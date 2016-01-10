package com.zttx.web.dubbo.service;

import com.google.common.collect.Lists;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.common.entity.PointRefund;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import com.zttx.web.module.common.mapper.PointRefundMapper;
import com.zttx.web.module.common.service.StockAdjustDetailService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>File:PointRefundDubboServiceImpl</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/18 10:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Service
public class PointRefundDubboServiceImpl implements PointRefundDubboService
{
    Logger  logger = LoggerFactory.getLogger(PointRefundDubboServiceImpl.class);
    
    @Autowired
    private PointRefundMapper pointRefundMapper;
    
    @Autowired
    private BrandesInfoMapper brandesInfoMapper;
    
    @Autowired(required = false)
    private ProductSkuService productSkuService;
    @Autowired
    private StockAdjustDetailService  stockAdjustDetailService;
    
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
     * @return JsonMessage
     *      code            126000成功 126009失败
     *      message         失败原因描述
     *      rows            详细失败的sku集合
     *
     */
    @Override
    public JsonMessage savePointRefund(List<Map> mapList) throws BusinessException
    {
        if (null == mapList || mapList.isEmpty()) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数为null"); }
        List<PointRefund> pointRefundList = Lists.newArrayList();
        String dealerId;
        String productSkuId;
        Integer refundNum;
        BigDecimal pointPrice;
        BigDecimal pointPercent;
        Long erpTime;
        for (int i = 0, length = mapList.size(); i < length; i++)
        {
            Map<String, Object> map = mapList.get(i);
            dealerId = MapUtils.getString(map, "dealerId");
            productSkuId = MapUtils.getString(map, "productSkuId");
            refundNum = MapUtils.getInteger(map, "refundNum");
            pointPrice = new BigDecimal(MapUtils.getString(map, "pointPrice"));
            pointPercent = new BigDecimal(MapUtils.getString(map, "pointPercent"));
            erpTime = MapUtils.getLong(map, "erpTime");
            if (StringUtils.isEmpty(dealerId)
                    || StringUtils.isEmpty(productSkuId)) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常：dealerId、productSkuId为空！"); }
            if (null == refundNum) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常:refundNum为空！"); }
            if (null == pointPrice) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常:pointPrice为空！"); }
            if (null == pointPercent) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常:pointPercent为空！"); }
            if (null == erpTime) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数异常:erpTime为空！"); }
            ProductSku productSku = productSkuService.findProductSku(productSkuId);
            if (null == productSku) { throw new BusinessException(CommonConst.FAIL.getCode(), "商品中心异常：productSku不存在(skuId" + productSkuId + ")"); }
            BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(productSku.getBrandsId());
            if (null == brandesInfo) { throw new BusinessException(CommonConst.FAIL.getCode(), "商品中心异常：brandesInfo不存在(" + productSku.getBrandsId() + ")"); }
            PointRefund pointRefund = new PointRefund();
            pointRefund.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            pointRefund.setDealerId(dealerId);
            pointRefund.setBrandId(brandesInfo.getBrandId());
            pointRefund.setBrandsId(brandesInfo.getRefrenceId());
            pointRefund.setProductId(productSku.getProductId());
            pointRefund.setProductSkuId(productSkuId);
            pointRefund.setRefundNum(refundNum);
            pointRefund.setPointPrice(pointPrice);
            pointRefund.setPointPercent(pointPercent);
            pointRefund.setErpTime(erpTime);
            pointRefund.setCreateTime(CalendarUtils.getCurrentTime());
            pointRefundList.add(pointRefund);
        }
        if (!pointRefundList.isEmpty())
        {
            pointRefundMapper.insertBatch(pointRefundList);
            try {
                stockAdjustDetailService.addStockAdjustDetail(StockAdjustDetail.TYPE_REFUND,pointRefundList);
            }catch (Exception e){
                throw new BusinessException(CommonConst.FAIL.getCode(),"返点财务帐统计退货数据异常："+e.getLocalizedMessage());
            }
        }
        return new JsonMessage(CommonConst.SUCCESS);
    }
}
