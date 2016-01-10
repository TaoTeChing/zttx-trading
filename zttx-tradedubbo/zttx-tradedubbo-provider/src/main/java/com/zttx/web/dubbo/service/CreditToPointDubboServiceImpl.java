package com.zttx.web.dubbo.service;

import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.common.entity.CreditToPoint;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import com.zttx.web.module.common.mapper.CreditToPointMapper;
import com.zttx.web.module.common.service.StockAdjustDetailService;
import com.zttx.web.utils.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>File:CreditToPointDubboServiceImpl</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/18 18:46</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class CreditToPointDubboServiceImpl implements CreditToPointDubboService
{
    Logger                      logger = LoggerFactory.getLogger(CreditToPointDubboServiceImpl.class);
    
    @Autowired(required = false)
    private ProductSkuService   productSkuService;
    
    @Autowired
    private BrandesInfoMapper   brandesInfoMapper;
    
    @Autowired
    private CreditToPointMapper creditToPointMapper;
    @Autowired
    private StockAdjustDetailService stockAdjustDetailService;
    
    /**
     * erp调用插入铺货转返点后的剩余铺货信息
     * 记录品牌商修改产品交易类型为返点类型，原本的铺货类型产品变更为返点类型后未销售的剩余产品信息，并在财务账应付款中减掉相应的款数
     * @author 易永耀
     * @param mapList
     *      dealerId        经销商id
     *      productSkuId    产品skuid
     *      costPirce       sku成本价
     *      baseStock       erp基础库存量
     *      pointPrice      返点价
     *      pointPercent    返点比例
     *      erpTime         erp交易类型变更时间
     * @return JsonMessage
     *      code            126000成功 126009失败
     *      message         失败原因描述
     *      rows            详细失败的sku集合
     */
    @Override
    public JsonMessage saveCreditToPoint(List<Map> mapList) throws BusinessException
    {
        if (null == mapList || mapList.isEmpty()) { throw new BusinessException(CommonConst.FAIL.getCode(), "参数为null"); }
        List<CreditToPoint> creditToPointList = new ArrayList<>();
        String dealerId;
        String productSkuId;
        BigDecimal costPirce;
        Integer baseStock;
        BigDecimal pointPrice;
        BigDecimal pointPercent;
        Long erpTime;
        for (int i = 0, length = mapList.size(); i < length; i++)
        {
            Map<String, Object> map = mapList.get(i);
            dealerId = MapUtils.getString(map, "dealerId");
            productSkuId = MapUtils.getString(map, "productSkuId");
            costPirce = new BigDecimal(MapUtils.getString(map, "costPirce"));
            baseStock = MapUtils.getInteger(map, "baseStock");
            pointPrice = new BigDecimal(MapUtils.getString(map, "pointPrice"));
            pointPercent = new BigDecimal(MapUtils.getString(map, "pointPercent"));
            erpTime = MapUtils.getLong(map, "erpTime");
            if (StringUtils.isEmpty(dealerId) || StringUtils.isEmpty(productSkuId)) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：dealerId,productSkuId为空"); }
            if (null == costPirce) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：costPirce为空"); }
            if (null == baseStock) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：baseStock为空"); }
            if (null == pointPrice) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：pointPrice为空"); }
            if (null == pointPercent) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：pointPercent为空"); }
            if (null == erpTime) { throw new BusinessException(CommonConst.FAIL.getCode(),"参数异常：erpTime为空"); }
            ProductSku productSku = productSkuService.findProductSku(productSkuId);
            if (null == productSku) { throw new BusinessException(CommonConst.FAIL.getCode(),"商品中心异常：productSku不存在(skuId" + productSkuId + ")"); }
            BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(productSku.getBrandsId());
            if (null == brandesInfo) { throw new BusinessException(CommonConst.FAIL.getCode(),"商品中心异常：brandesInfo不存在(" + productSku.getBrandsId() + ")"); }
            CreditToPoint creditToPoint = new CreditToPoint();
            creditToPoint.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            creditToPoint.setDealerId(dealerId);
            creditToPoint.setBrandId(brandesInfo.getBrandId());
            creditToPoint.setBrandsId(brandesInfo.getRefrenceId());
            creditToPoint.setProductId(productSku.getProductId());
            creditToPoint.setProductSkuId(productSkuId);
            creditToPoint.setPointPrice(pointPrice);
            creditToPoint.setPointPercent(pointPercent);
            creditToPoint.setCostPirce(costPirce);
            creditToPoint.setBaseStock(baseStock);
            creditToPoint.setSumCost(costPirce.multiply(new BigDecimal(baseStock)).setScale(2, BigDecimal.ROUND_HALF_UP));
            creditToPoint.setErpTime(erpTime);
            creditToPoint.setCreateTime(CalendarUtils.getCurrentTime());
            creditToPointList.add(creditToPoint);
        }
        if (ListUtils.isNotEmpty(creditToPointList))
        {
            creditToPointMapper.insertBatch(creditToPointList);
            try {
            stockAdjustDetailService.addStockAdjustDetail(StockAdjustDetail.TYPE_CREDITTOPOINT,creditToPointList);

            }catch (Exception e){

            }
        }
        return new JsonMessage(CommonConst.SUCCESS);
    }
}
