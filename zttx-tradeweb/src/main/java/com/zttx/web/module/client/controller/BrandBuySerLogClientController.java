package com.zttx.web.module.client.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.service.BrandBuySerLogService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerBuySerLogClientController.java</p>
 * <p>Title: 品牌商购买的服务记录管理接口</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-2 上午9:19:49</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandBuySerLog")
public class BrandBuySerLogClientController extends GenericController
{
    @Autowired
    private BrandBuySerLogService brandBuySerLogService;
    
    @Autowired
    private DataDictValueService  dataDictValueService;
    
    /**
     * 列表
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandBuySerLog searchBean = new BrandBuySerLog();
        searchBean.setBrandName(MapUtils.getString(map, "brandName"));
        searchBean.setServiceId(MapUtils.getString(map, "serviceId"));
        searchBean.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        searchBean.setBuyState(MapUtils.getShort(map, "buyState"));
        searchBean.setChargType(MapUtils.getShort(map, "chargType"));
        searchBean.setBrandId(MapUtils.getString(map, "brandId"));
        searchBean.setBuyState(DealerConstant.DealerBuySerLog.BUY_STATE_SUCCESS);// 只列表支付成功的记录
        PaginateResult<BrandBuySerLog> result = new PaginateResult<BrandBuySerLog>(page,brandBuySerLogService.findList(searchBean));
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS, result);
        Map<String, String> servicerCateMap = dataDictValueService.findMapByDictCode("servicerCate");
        jsonMessage.setObject(servicerCateMap);
        return jsonMessage;
    }
}
