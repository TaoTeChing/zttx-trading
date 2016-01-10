package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import com.zttx.web.dubbo.service.DealerProductDubboConsumer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ProductSkuPriceClientController.java </p>
 * <p>Title: ProductSkuPriceClientController </p>
 * <p>Description: 商品sku建议销售价管理接口 </p>
 * <p>Copyright: Copyright (c) 十月 08，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
// @Controller 该接口已经不用
@RequestMapping(value = ApplicationConst.CLIENT + "/productSkuPrice")
public class ProductSkuPriceClientController extends GenericController
{
    private final static Logger         logger = LoggerFactory.getLogger(ProductSkuPriceClientController.class);
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private DealerProductDubboConsumer  dealerProductDubboConsumer;
    
    /**
     * 分页列表
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        ProductBaseInfo searchBean = new ProductBaseInfo();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        PaginateResult<ProductBaseInfo> productBaseInfoList = productInfoDubboConsumer.findCreditProductAndFilterByRecommendPrice(searchBean,
                MapUtils.getBooleanValue(map, "tabId", false));
        return super.getJsonMessage(CommonConst.SUCCESS, productBaseInfoList);
    }
    
    /**
     * 查询sku信息
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(MapUtils.getString(map, "refrenceId"), false);
        return super.getJsonMessage(CommonConst.SUCCESS, productSkuList);
    }
    
    /**
     * 保存建议销售价
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String data = MapUtils.getString(map, "priceList", "");
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS);
        productSkuInfoDubboConsumer.updateRecommendPrice(getProductSkuPriceListFromParameter(data));
        dealerProductDubboConsumer.savePriceChange(getProductSkuPriceMapListFromParameter(data));// 调用erp接口
        return jsonMessage;
    }
    
    /**
     * 解析参数，符合erp接口参数格式
     * @param data
     * @return
     */
    private List<Map<String, Object>> getProductSkuPriceMapListFromParameter(String data)
    {
        JSONArray jsonArray = JSONArray.parseArray(data);
        List<Map<String, Object>> mapList = Lists.newArrayList();
        try
        {
            for (int i = 0; i < jsonArray.size(); i++)
            {
                if (null != jsonArray.get(i))
                {
                    Map<String, Object> eachMap = Maps.newHashMap();
                    Map<String, Object> productMap = new ObjectMapper().readValue(jsonArray.get(i).toString(), Map.class);// 转成map
                    eachMap.put("productId", MapUtils.getObject(productMap, "productId"));
                    eachMap.put("salePrice", MapUtils.getObject(productMap, "factoryStorePrice"));
                    eachMap.put("startTime", MapUtils.getObject(productMap, "startTime"));
                    eachMap.put("skuIds", MapUtils.getObject(productMap, "productSkuId"));
                    mapList.add(eachMap);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return mapList;
    }
    
    /**
     * 解析参数，符合商品中心接口参数格式
     * @param data
     * @return
     */
    private List<ProductSkuPrice> getProductSkuPriceListFromParameter(String data)
    {
        JSONArray jsonArray = JSONArray.parseArray(data);
        List<ProductSkuPrice> tList = Lists.newArrayList();
        try
        {
            for (int i = 0; i < jsonArray.size(); i++)
            {
                if (null != jsonArray.get(i))
                {
                    ProductSkuPrice retObj = new ProductSkuPrice();
                    Map<String, Object> productMap = new ObjectMapper().readValue(jsonArray.get(i).toString(), Map.class);// 转成map
                    BeanUtils.populate(retObj, productMap);
                    tList.add(retObj);
                }
            }
        }
        catch (Exception e)
        {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return tList;
    }
}
