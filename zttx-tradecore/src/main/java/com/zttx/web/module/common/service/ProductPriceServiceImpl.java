package com.zttx.web.module.common.service;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandesInfoConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.service.BrandesInfoRegionalService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * <p>File:ProductPriceServiceImpl</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/27 10:03</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Service
public class ProductPriceServiceImpl implements ProductPriceService
{
    @Autowired
    private DealerInfoService          dealerInfoService;
    
    @Autowired
    private BrandesInfoRegionalService brandesInfoRegionalService;
    
    @Autowired
    private BrandesInfoService         brandesInfoService;
    
    @Autowired(required = false)
    private ProductSkuService          productSkuService;
    
    @Override
    public Map<String, Object> toConfirmProTypeAndPrice(Map<String, Object> map) throws BusinessException
    {
        if (null == map) { return null; }
        Boolean brandDelFlag = MapUtils.getBoolean(map, "brandDelFlag", Boolean.FALSE);
        Boolean productDelFlag = MapUtils.getBoolean(map, "productDelFlag", Boolean.FALSE);
        Integer brandState = MapUtils.getInteger(map, "brandState");
        Integer productState = MapUtils.getInteger(map, "productState");
        Integer joinState = MapUtils.getInteger(map, "joinState");
        Integer joinForm = MapUtils.getInteger(map, "joinForm");
        Boolean isPaid = MapUtils.getBoolean(map, "isPaid", Boolean.FALSE);
        Boolean isCredit = MapUtils.getBoolean(map, "isCredit", Boolean.FALSE);
        Boolean isPoint = MapUtils.getBoolean(map, "isPoint", Boolean.FALSE);// 是否支持返点
        BigDecimal productPrice = new BigDecimal(MapUtils.getString(map, "productPrice", "0"));
        BigDecimal lowestPrice = new BigDecimal(MapUtils.getString(map, "lowestPrice", "0"));
        BigDecimal lowestCreditPrice = new BigDecimal(MapUtils.getString(map, "lowestCreditPrice", "0"));
        BigDecimal lowestPointPrice = new BigDecimal(MapUtils.getString(map, "lowestPointPrice", "0"));
        Map<String, Object> resultMap = Maps.newHashMap();
        if (brandDelFlag || productDelFlag || brandState != 2)
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_INVALID);
            resultMap.put("message", "已失效");
            resultMap.put("price", productPrice);
            if (productPrice.compareTo(BigDecimal.ZERO) != 1)
            {
                resultMap.put("exception", "吊牌价有误");
            }
        }
        else if (isPoint)
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_POINT);
            resultMap.put("message", "返点");
            resultMap.put("price", productPrice);
            resultMap.put("point", lowestPointPrice);
        }
        else
        {
            if (productState != 1)
            {
                resultMap.put("type", DealerConstant.productState.PRODUCT_DOWN);
                resultMap.put("message", "已下架");
                resultMap.put("price", productPrice);
                if (productPrice.compareTo(BigDecimal.ZERO) != 1)
                {
                    resultMap.put("exception", "吊牌价有误");
                }
            }
            else if (joinState != 1)
            {
                resultMap.put("type", DealerConstant.productState.PRODUCT_NORIGHT);
                resultMap.put("message", "无购买权限");
                resultMap.put("price", productPrice);
                if (productPrice.compareTo(BigDecimal.ZERO) != 1)
                {
                    resultMap.put("exception", "吊牌价有误");
                }
            }
            else if (joinState == 1 && joinForm == 0)
            {
                resultMap.put("type", DealerConstant.productState.PRODUCT_CASH);
                resultMap.put("message", "现款");
                resultMap.put("price", productPrice);
                resultMap.put("cash", lowestPrice);
                if (lowestPrice.compareTo(BigDecimal.ZERO) != 1)
                {
                    resultMap.put("exception", "现款价有误");
                }
            }
            else if (joinState == 1 && joinForm == 1)
            {
                if (isPaid && isCredit)
                {
                    resultMap.put("type", DealerConstant.productState.PRODUCT_CREDIT);
                    resultMap.put("message", "授信");
                    resultMap.put("price", productPrice);
                    resultMap.put("credit", lowestCreditPrice);
                    if (lowestCreditPrice.compareTo(BigDecimal.ZERO) != 1)
                    {
                        resultMap.put("exception", "授信价有误");
                    }
                }
                else
                {
                    resultMap.put("type", DealerConstant.productState.PRODUCT_CASH);
                    resultMap.put("message", "现款");
                    resultMap.put("price", productPrice);
                    resultMap.put("cash", lowestPrice);
                    if (lowestPrice.compareTo(BigDecimal.ZERO) != 1)
                    {
                        resultMap.put("exception", "现款价有误");
                    }
                }
            }
            else
            {
                throw new BusinessException(CommonConst.FAIL.code, "抱歉:确认产品状态价格有误!");
            }
        }
        return resultMap;
    }
    
    public Map<String, Object> toConfirmProTypeAndPriceByAreaNo(Map<String, Object> map) throws BusinessException
    {
        Map<String, Object> resultMap = Maps.newHashMap();
        String dealerId = MapUtils.getString(map, "dealerId");
        if (dealerId == null)
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        }
        Boolean brandDelFlag = MapUtils.getBoolean(map, "brandDelFlag", Boolean.FALSE);
        Boolean productDelFlag = MapUtils.getBoolean(map, "productDelFlag", Boolean.FALSE);
        Integer brandState = MapUtils.getInteger(map, "brandState");
        Integer productState = MapUtils.getInteger(map, "productState");
        DealerInfo dealerInfo = dealerInfoService.getDealerInfo(dealerId);
        BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(MapUtils.getString(map, "brandsId"), dealerInfo.getAreaNo());
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(MapUtils.getString(map, "brandsId"));
        String userAuth = brandesInfo.getUserAuth();
        BigDecimal productPrice = new BigDecimal(MapUtils.getString(map, "productPrice", "0"));
        Map<String, Object> priceMap = productSkuService.findMinPriceByProductId(MapUtils.getString(map, "productId"));
        BigDecimal lowestPrice = new BigDecimal(MapUtils.getString(priceMap, "directPrice", "0"));
        if (brandDelFlag || productDelFlag || brandState != 2)
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_INVALID);
            resultMap.put("message", "已失效");
            resultMap.put("price", productPrice);
        }
        else if (productState != 1)
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_DOWN);
            resultMap.put("message", "已下架");
            resultMap.put("price", productPrice);
        }
        else if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.MEMBER_CERTIFIED)) // 品牌对区域授权且对认证用户授权才可以现款购买
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_CASH);
            resultMap.put("message", "现款");
            resultMap.put("cash", lowestPrice);
            resultMap.put("price", productPrice);
        }
        else
        {
            resultMap.put("type", DealerConstant.productState.PRODUCT_NORIGHT);
            resultMap.put("message", "无购买权限");
            resultMap.put("price", productPrice);
        }
        return resultMap;
    }
}
