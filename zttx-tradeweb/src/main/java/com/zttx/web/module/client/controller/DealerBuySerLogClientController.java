package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.service.DealerBuySerLogService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerBuySerLogClientController.java </p>
 * <p>Title: DealerBuySerLogClientController </p>
 * <p>Description: 经销商购买的服务记录管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerBuySerLog")
public class DealerBuySerLogClientController extends GenericController
{
    @Autowired
    private DealerBuySerLogService dealerBuySerLogService;
    
    @Autowired
    private DataDictValueService   dataDictValueService;
    
    /**
     * 分页查询
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerBuySerLog searchBean = new DealerBuySerLog();
        BeanUtils.populate(searchBean, map);
        searchBean.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        searchBean.setBuyState(MapUtils.getShort(map, "buyState"));
        searchBean.setChargType(MapUtils.getShort(map, "chargType"));
        searchBean.setBuyTime(MapUtils.getLong(map, "buyTime"));
        searchBean.setStartSearchTime(MapUtils.getLong(map, "startSearchTime"));
        searchBean.setEndSearchTime(MapUtils.getLong(map, "endSearchTime"));
        searchBean.setDealerId(MapUtils.getString(map, "dealerId"));
        searchBean.setBuyState(DealerConstant.DealerBuySerLog.BUY_STATE_SUCCESS);// 只列表支付成功的记录
        PaginateResult<DealerBuySerLog> result = dealerBuySerLogService.searchByClient(page, searchBean);
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS, result);
        Map<String, String> servicerCateMap = dataDictValueService.findMapByDictCode("servicerCate");
        jsonMessage.setObject(servicerCateMap);
        return jsonMessage;
    }
}
