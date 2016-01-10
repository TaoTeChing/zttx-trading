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
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandesWeightInfo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandesWeightInfoService;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandesWeightInfoClientController.java </p>
 * <p>Title: BrandesWeightInfoClientController </p>
 * <p>Description: 品牌权重信息接口 </p>
 * <p>Copyright: Copyright (c) 十一月 03，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandesWeightInfo")
public class BrandesWeightInfoClientController extends GenericController
{
    private final static Logger      logger = LoggerFactory.getLogger(BrandesWeightInfoClientController.class);
    
    @Autowired
    private BrandesWeightInfoService brandesWeightInfoService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private BrandeSolrHandler        brandeSolrHandler;
    
    @Autowired
    private ProductSolrHandler       productSolrHandler;
    
    @Autowired
    private ProductInfoService       productInfoService;
    
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
        BrandesWeightInfo searchBean = new BrandesWeightInfo();
        BeanUtils.populate(searchBean, map);
        return super.getJsonMessage(CommonConst.SUCCESS, brandesWeightInfoService.searchByClient(searchBean, page));
    }
    
    /**
     * 批量保存品牌权重
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/batchSave", method = RequestMethod.POST)
    @Deprecated
    public JsonMessage batchSave(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String data = MapUtils.getString(map, "dataList", "");
        List<BrandesWeightInfo> brandesWeightInfos = ParameterUtils.getListObjectFromParameter(data, BrandesWeightInfo.class);
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            for (BrandesWeightInfo brandesWeightInfo : brandesWeightInfos)
            {
                if (!beanValidator(jsonMessage, brandesWeightInfo)) { return jsonMessage; }
                brandesWeightInfo.setUpdateTime(CalendarUtils.getCurrentLong());
                brandesWeightInfoService.updateByPrimaryKeySelective(brandesWeightInfo);
                BrandesInfo filter1 = new BrandesInfo();
                filter1.setRefrenceId(brandesWeightInfo.getBrandesId());
                // 修改品牌索引
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(filter1, null));
                // 修改旗下产品索引
                ProductInfo filter2 = new ProductInfo();
                filter2.setBrandsId(brandesWeightInfo.getBrandesId());
                List<ProductInfo> productInfos = productInfoService.findProductToSolr(filter2, null);
                productSolrHandler.addProductInfoList(productInfos);
            }
        }
        catch (BusinessException e)
        {
            logger.error("品牌权重接口，调用保存失败: " + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return jsonMessage;
    }
}
