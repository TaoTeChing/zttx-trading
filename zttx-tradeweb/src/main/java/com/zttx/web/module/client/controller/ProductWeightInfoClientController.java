package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.entity.ProductWeightInfo;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.common.service.ProductWeightInfoService;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ProductWeightInfoClientController.java </p>
 * <p>Title: ProductWeightInfoClientController </p>
 * <p>Description: 产品权重信息接口 </p>
 * <p>Copyright: Copyright (c) 十一月 03，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/productWeightInfo")
public class ProductWeightInfoClientController extends GenericController
{
    private final static Logger      logger = LoggerFactory.getLogger(ProductWeightInfoClientController.class);
    
    @Autowired
    private ProductWeightInfoService productWeightInfoService;
    
    @Autowired
    private ProductInfoService       productInfoService;
    
    @Autowired
    private ProductSolrHandler       productSolrHandler;
    
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
        ProductWeightInfo searchBean = new ProductWeightInfo();
        BeanUtils.populate(searchBean, map);
        return super.getJsonMessage(CommonConst.SUCCESS, productWeightInfoService.searchByClient(searchBean, page));
    }
    
    /**
     * 批量保存产品权重
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/batchSave", method = RequestMethod.POST)
    public JsonMessage batchSave(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String data = MapUtils.getString(map, "dataList", "");
        List<ProductWeightInfo> productWeightInfos = ParameterUtils.getListObjectFromParameter(data, ProductWeightInfo.class);
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            for (ProductWeightInfo productWeightInfo : productWeightInfos)
            {
                if (!beanValidator(jsonMessage, productWeightInfo)) { return jsonMessage; }
                productWeightInfo.setUpdateTime(CalendarUtils.getCurrentLong());
                productWeightInfoService.updateByPrimaryKeySelective(productWeightInfo);
                ProductInfo filter = new ProductInfo();
                filter.setRefrenceId(productWeightInfo.getRefrenceId());
                List<ProductInfo> productInfos = productInfoService.findProductToSolr(filter, null);
                productSolrHandler.addProductInfoList(productInfos);
            }
        }
        catch (BusinessException e)
        {
            logger.error("产品权重接口，调用保存失败: " + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return jsonMessage;
    }
}
