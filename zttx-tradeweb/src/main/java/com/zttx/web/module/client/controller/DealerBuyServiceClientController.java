package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.DealerBuyService;
import com.zttx.web.module.dealer.service.DealerBuyServiceService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerBuyServiceClientController.java </p>
 * <p>Title: DealerBuyServiceClientController </p>
 * <p>Description: 经销商购买的服务管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerBuyService")
public class DealerBuyServiceClientController extends GenericController
{
    @Autowired
    private DealerBuyServiceService dealerBuyServiceService;
    
    @Autowired
    private DataDictValueService    dataDictValueService;
    
    /**
     * 列表
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerBuyService searchBean = new DealerBuyService();
        BeanUtils.populate(searchBean, map);
        searchBean.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        searchBean.setBuyTime(MapUtils.getLong(map, "buyTime"));
        searchBean.setStartSearchTime(MapUtils.getLong(map, "startSearchTime"));
        searchBean.setEndSearchTime(MapUtils.getLong(map, "endSearchTime"));
        PaginateResult<DealerBuyService> result = dealerBuyServiceService.searchByClient(page, searchBean);
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS, result);
        Map<String, String> servicerCateMap = dataDictValueService.findMapByDictCode("servicerCate");
        jsonMessage.setObject(servicerCateMap);
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        dealerBuyServiceService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 新增/修改
     * refrenceId==null：新增
     * refrenceId!=null：修改
     * @param param
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerBuySerLog dealerBuySerLog = new DealerBuySerLog();
        BeanUtils.populate(dealerBuySerLog, map);
        dealerBuySerLog.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        dealerBuySerLog.setChargType(MapUtils.getShort(map, "chargType"));
        dealerBuySerLog.setBuyNum(MapUtils.getInteger(map, "buyNum"));
        dealerBuySerLog.setBuyMoney(BigDecimal.valueOf(MapUtils.getDoubleValue(map, "buyMoney", 0.0)));
        dealerBuySerLog.setBeginTime(MapUtils.getLong(map, "beginTime"));
        dealerBuySerLog.setEndTime(MapUtils.getLong(map, "endTime"));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, dealerBuySerLog))
        {
            DealerBuyService dealerBuyService = dealerBuyServiceService.saveByClient(dealerBuySerLog);
            if (dealerBuySerLog.getChargType() == DealerConstant.DealerBuySerLog.CHARGE_TYPE_RENEW) { return super.getJsonMessage(
                    CommonConst.SUCCESS.code,
                    "服务续期成功，服务时间为：" + CalendarUtils.getStringTime(dealerBuyService.getBeginTime(), "yyyy-MM-dd") + " 到 "
                            + CalendarUtils.getStringTime(dealerBuyService.getEndTime(), "yyyy-MM-dd")); }
        }
        return jsonMessage;
    }
}
