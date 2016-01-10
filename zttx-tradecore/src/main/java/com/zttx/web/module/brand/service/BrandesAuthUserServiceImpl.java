package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IpUtilsHelper;

/**
 * <p>File:BrandesAuthUserServiceImpl</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/14 13:12</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Service
public class BrandesAuthUserServiceImpl implements BrandesAuthUserService
{
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private BrandInfoService            brandInfoService;
    
    @Autowired
    private DealerOrderService          dealerOrderService;
    
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private BrandesInfoRegionalService  brandesInfoRegionalService;
    
    @Autowired
    private HttpServletRequest          request;
    
    @Override
    public BrandesAuthUserModel getAuthPrice(String userId, String productId, String state) throws BusinessException
    {
        if (null == productId) { throw new BusinessException(CommonConst.PARAM_NULL); }
        BrandesAuthUserModel brandesAuthUserModel = new BrandesAuthUserModel(productId);
        // 1.产品的有效性校验
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        if (null == productBaseInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        brandesAuthUserModel.setIsValidProduct(!productBaseInfo.getDelFlag());
        BrandesInfo brandesInfo = null;
        if (null != productBaseInfo.getBrandsId())
        {
            brandesInfo = brandesInfoService.selectByPrimaryKey(productBaseInfo.getBrandsId());
        }
        if (null == brandesInfo || brandesInfo.getBrandState() != BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED)
        {
            brandesAuthUserModel.setIsValidProduct(false);
            return brandesAuthUserModel;
        }
        brandesAuthUserModel.setIsSample(null != productBaseInfo.getProductExtInfo() ? productBaseInfo.getProductExtInfo().getSample() : false); // 产品支持拿样
        String userAuth = brandesInfo.getUserAuth();
        // 2.用户类别判断 ： 游客，品牌商，经销商
        if (null == userId)
        { // 游客不用判断是否拿过样，因为游客不能加车
            brandesAuthUserModel.setUserType(BrandUsermConstant.userType.TOURIST);
            Integer areaNo = IpUtilsHelper.getAreaNo(request, 1);
            BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), areaNo);
            if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.TOURIST)) // 品牌对游客授权（直供价）
            {
                brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH); // 普通授权
                brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
            }
            else
            {
                brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH); // 未授权
                brandesAuthUserModel.setProductPrice(productBaseInfo.getProductPrice()); // 游客 未授权看到产品的吊牌价
            }
        }
        else
        {
            UserPrincipal userm = OnLineUserUtils.getPrincipal();
            if (null != userm && UserInfoConst.USER_TYPE_BRAND.equals(userm.getUserType())) // 品牌商登录 --显示区域授权内的活动价，如活动价已经失效显示直供价
            {
                brandesAuthUserModel.setUserType(BrandUsermConstant.userType.BRAND);
                BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(userm.getRefrenceId());
                BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), brandInfo.getAreaNo());
                // 区域授权且该品牌对认证用户授权 或者是品牌商自己的产品，品牌商才可以看到直供价价格
                if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.MEMBER_CERTIFIED) || productBaseInfo.getBrandId().equals(userId))
                {
                    if (productBaseInfo.getBrandId().equals(userId) && productBaseInfo.getProductExtInfo().getCredit() != null
                            && productBaseInfo.getProductExtInfo().getCredit())
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CREDIT_AUTH); // 品牌商查看自己的授信产品看到授信价
                    }
                    else
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH);
                    }
                    brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
                }
                else
                {
                    brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH);
                    brandesAuthUserModel.setProductPrice(productBaseInfo.getProductPrice()); // 区域未授权只能看到产品的吊牌价
                }
            }
            if (null != userm && UserInfoConst.USER_TYPE_DEALER.equals(userm.getUserType())) // 经销商登录 --1.加盟用户 2.区域授权用户 （如果是加盟用户，直接给直供价，非加盟用户判断是否在产品授权区域：是给直供价，不然吊牌价）
            {
                brandesAuthUserModel.setUserType(BrandUsermConstant.userType.DEALER);
                DealerInfo dealerInfo = dealerInfoService.getDealerInfo(userm.getRefrenceId());
                BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), dealerInfo.getAreaNo());
                DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(userId, productBaseInfo.getBrandsId());
                if (dealerJoinService.isSupportPoint(dealerJoin, productBaseInfo))
                {// 是否支持返点判断
                    brandesAuthUserModel.setPoint(Boolean.TRUE);
                    brandesAuthUserModel.setProductPointPercent(productBaseInfo.getProductExtInfo().getPointPercent());
                }
                // 区域授权且品牌未对游客，注册，认证用户授权，或者是加盟关系，则该经销获取直供价
                if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.MEMBER_CERTIFIED) || null != dealerJoin)
                {
                    if (productBaseInfo.getProductExtInfo().getSample())
                    {
                        brandesAuthUserModel.setIsHasTakeSample(dealerOrderService.isHasTakeSample(userId, productId));
                    }
                    if (null != dealerJoin && DealerConstant.DealerJoin.JOINFORM_CREDIT == dealerJoin.getJoinForm() && dealerJoin.getIsPaid()
                            && productBaseInfo.getProductExtInfo().getCredit()) // 授信，押金已付
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CREDIT_AUTH);
                    }
                    else
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH);
                    }
                    brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
                }
                else
                {
                    brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH);
                    brandesAuthUserModel.setProductPrice(productBaseInfo.getProductPrice());
                }
            }
        }
        return brandesAuthUserModel;
    }
    
    @Override
    public BrandesAuthUserModel getAuthPrice(UserPrincipal principal, Map<String, Object> productMap, String state) throws BusinessException
    {
        if (null == productMap) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String productId = MapUtils.getString(productMap, "refrenceId");
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        BrandesAuthUserModel brandesAuthUserModel = new BrandesAuthUserModel(productId);
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(MapUtils.getString(productMap, "brandsId"));
        if (null == brandesInfo || brandesInfo.getBrandState() != BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED)
        {
            brandesAuthUserModel.setIsValidProduct(false);
            return brandesAuthUserModel;
        }
        Boolean isSample = MapUtils.getBoolean(productMap, "isSample", false);
        Boolean isCredit = MapUtils.getBoolean(productMap, "isCredit", false);
        Double productPrice = MapUtils.getDouble(productMap, "productPrice", 0.00);
        if (isSample) brandesAuthUserModel.setIsSample(true); // 产品支持拿样
        String userAuth = brandesInfo.getUserAuth();
        if (null == userAuth)
        { // userAuth不可能为null,如果null说明数据问题，userAuth 默认为01，02，03，04
            userAuth = "01,02,03,04";
        }
        // 2.用户类别判断 ： 游客，品牌商，经销商
        if (null == principal)
        { // 游客不用判断是否拿过样，因为游客不能加车
            Integer areaNo = IpUtilsHelper.getAreaNo(request, 1);
            BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), areaNo);
            if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.TOURIST)) // 品牌对游客授权（直供价）
            {
                brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH); // 普通授权
                brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
                brandesAuthUserModel.setUserType(BrandUsermConstant.userType.TOURIST);
            }
            else
            {
                brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH); // 未授权
                brandesAuthUserModel.setUserType(BrandUsermConstant.userType.TOURIST);
                brandesAuthUserModel.setProductPrice(new BigDecimal(productPrice)); // 游客 未授权看到产品的吊牌价
            }
        }
        else
        {
            String userId = principal.getRefrenceId();
            if (principal.getUserType().equals(UserInfoConst.USER_TYPE_BRAND))
            {// 品牌商登录 --显示区域授权内的活动价，如活动价已经失效显示直供价
                BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(userId);
                BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), brandInfo.getAreaNo());
                // 区域授权且该品牌对认证用户授权 或者是品牌商自己的产品，品牌商才可以看到直供价价格
                if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.MEMBER_CERTIFIED) || brandesInfo.getBrandId().equals(userId))
                {
                    if (brandesInfo.getBrandId().equals(userId) && isCredit)
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CREDIT_AUTH); // 品牌商查看自己的授信产品看到授信价
                    }
                    else
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH);
                    }
                    brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
                    brandesAuthUserModel.setUserType(BrandUsermConstant.userType.BRAND);
                }
                else
                {
                    brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH);
                    brandesAuthUserModel.setUserType(BrandUsermConstant.userType.BRAND);
                    brandesAuthUserModel.setProductPrice(new BigDecimal(productPrice)); // 区域未授权只能看到产品的吊牌价
                }
            }
            if (principal.getUserType().equals(UserInfoConst.USER_TYPE_DEALER))
            {// 经销商登录 --1.加盟用户 2.区域授权用户 （如果是加盟用户，直接给直供价，非加盟用户判断是否在产品授权区域：是给直供价，不然吊牌价）
                DealerInfo dealerInfo = dealerInfoService.getDealerInfo(userId);
                BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(brandesInfo.getRefrenceId(), dealerInfo.getAreaNo());
                DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(userId, brandesInfo.getRefrenceId());
                if (dealerJoinService.isSupportPoint(dealerJoin, productBaseInfo))//由于产品索引的isPoint属性不准确 需要判断
                {// 是否支持返点判断
                    brandesAuthUserModel.setPoint(Boolean.TRUE);
                    brandesAuthUserModel.setProductPointPercent(productBaseInfo.getProductExtInfo().getPointPercent());
                }
                // 区域授权且品牌未对游客，注册，认证用户授权，或者是加盟关系，则该经销获取直供价
                if (null != brandesInfoRegional && userAuth.contains(BrandesInfoConst.UserAuth.MEMBER_CERTIFIED) || null != dealerJoin)
                {
                    if (isSample)
                    {
                        if (dealerOrderService.isHasTakeSample(userId, productId))
                        {
                            brandesAuthUserModel.setIsHasTakeSample(true);
                        }
                    }
                    if (null != dealerJoin && dealerJoin.getJoinForm().equals(DealerConstant.DealerJoin.JOINFORM_CREDIT) && dealerJoin.getIsPaid() && isCredit) // 授信，押金已付
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CREDIT_AUTH);
                    }
                    else
                    {
                        brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.CASH_AUTH);
                    }
                    brandesAuthUserModel = this.getAuthPrice(productId, brandesAuthUserModel, state);
                    brandesAuthUserModel.setUserType(BrandUsermConstant.userType.DEALER);
                }
                else
                {
                    brandesAuthUserModel.setIsAuth(BrandesAuthUserModel.NO_AUTH);
                    brandesAuthUserModel.setUserType(BrandUsermConstant.userType.DEALER);
                    brandesAuthUserModel.setProductPrice(new BigDecimal(productPrice));
                }
            }
        }
        return brandesAuthUserModel;
    }
    
    protected BrandesAuthUserModel getAuthPrice(String productId, BrandesAuthUserModel brandesAuthUserModel, String state) throws BusinessException
    {
        Map<String, Object> productMap;
        if (brandesAuthUserModel.INCLUDE_SKU.equals(state))
        {
            List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(productId, false);
            if (productSkuList.isEmpty()) { throw new BusinessException("产品sku不存在"); }
            for (ProductSku productSku : productSkuList)
            {
                if (null == productSku) { throw new BusinessException("产品sku为空"); }
                List<ProductAttrValue> productAttrValueList = productSku.getAttrValueList();
                if (productAttrValueList.isEmpty()) { throw new BusinessException("产品sku的productAttrValue为null"); }
                Map<String, Object> skuMap = Maps.newHashMap();
                for (ProductAttrValue productAttrValue : productAttrValueList) // 一个sku含有两条 productAttrValue 颜色：3372 尺码:101
                {
                    if (com.zttx.sdk.consts.ZttxConst.CateAttributeItem.DEFAULT_COLOR_ID.equals(productAttrValue.getAttributeId())) // 3372为sku的颜色属性代码
                    {
                        skuMap.put("colorName", productAttrValue.getExtAttributeValue());
                        if (null != brandesAuthUserModel.getProductColorSet()
                                && !brandesAuthUserModel.getProductColorSet().contains(productAttrValue.getExtAttributeValue()))
                        {
                            brandesAuthUserModel.getProductColorSet().add(productAttrValue.getExtAttributeValue());
                            brandesAuthUserModel.getProductSkuMap().put(productAttrValue.getExtAttributeValue(), new ArrayList<>());
                        }
                        skuMap.put("productSkuName", productSku.getProductSkuName());
                        skuMap.put("productSkuId", productSku.getRefrenceId());
                        // 2015/11/17 sku返点价
                        if (brandesAuthUserModel.getPoint())
                        {
                            skuMap.put("productSkuPointPrice", productSku.getProductSkuPrice().getPointPrice());// 返点销售价
                        }
                        else
                        {
                            if (brandesAuthUserModel.getIsAuth().equals(BrandesAuthUserModel.CASH_AUTH)) // 直供
                            {
                                skuMap.put("productSkuDirPrice", productSku.getProductSkuPrice().getDirectPrice());
                            }
                            else if (brandesAuthUserModel.getIsAuth().equals(BrandesAuthUserModel.CREDIT_AUTH)) // 授信
                            {
                                skuMap.put("productSkuDirPrice", productSku.getProductSkuPrice().getDirectPrice());
                                skuMap.put("productSkuCreditPrice", productSku.getProductSkuPrice().getCreditPrice());
                            }
                        }
                        skuMap.put("productSkuPrice", productSku.getProductSkuPrice().getSkuPrice()); // 吊牌价
                        skuMap.put("quantity", productSku.getQuantity()); // 库存
                        List<Map<String, Object>> _skuMapList = (List<Map<String, Object>>) MapUtils.getObject(brandesAuthUserModel.getProductSkuMap(),
                                productAttrValue.getExtAttributeValue());
                        _skuMapList.add(skuMap);
                    }
                    else if (productAttrValue.getAttributeId().equals(com.zttx.sdk.consts.ZttxConst.CateAttributeItem.DEFAULT_SIZE_ID))
                    {
                        skuMap.put("sizeName", productAttrValue.getExtAttributeValue());
                    }
                }
            }
        }
        // 获得该产品 的最小吊牌价，最小直供价，最小授信价，最小拿样价，如果map 为null 则 价格为0，前台页面会显示为价格有误
        productMap = productSkuInfoDubboConsumer.findMinPriceByProductId(productId);
        if(brandesAuthUserModel.getPoint()){
            brandesAuthUserModel.setPointPrice(new BigDecimal(MapUtils.getString(productMap, "pointPrice", "0")));
        }
        if (brandesAuthUserModel.getIsAuth().equals(BrandesAuthUserModel.CASH_AUTH))
        {
            brandesAuthUserModel.setProductDirPrice(new BigDecimal(MapUtils.getString(productMap, "directPrice", "0"))); // 最低直供价
            brandesAuthUserModel.setProductPrice(new BigDecimal(MapUtils.getString(productMap, "skuPrice", "0")));
        }
        else if (brandesAuthUserModel.getIsAuth().equals(BrandesAuthUserModel.CREDIT_AUTH))
        {
            brandesAuthUserModel.setProductDirPrice(new BigDecimal(MapUtils.getString(productMap, "directPrice", "0"))); // 最低直供价
            brandesAuthUserModel.setProductCreditPrice(new BigDecimal(MapUtils.getString(productMap, "creditPrice", "0"))); // 最低授信价
            brandesAuthUserModel.setProductPrice(new BigDecimal(MapUtils.getString(productMap, "skuPrice", "0")));
        }
        if (brandesAuthUserModel.getIsSample()) // 支持拿样的产品要有拿样价
        {
            brandesAuthUserModel.setProductSamplePrice(new BigDecimal(MapUtils.getString(productMap, "samplePrice", "0")));
        }
        return brandesAuthUserModel;
    }
}
