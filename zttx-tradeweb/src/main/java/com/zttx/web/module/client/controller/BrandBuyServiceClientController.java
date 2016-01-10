package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.entity.BrandBuyService;
import com.zttx.web.module.brand.service.BrandBuyServiceService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandBuyServiceClientController.java</p>
 * <p>Title: 品牌商购买的服务管理接口</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-4 上午10:44:01</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandBuyService")
public class BrandBuyServiceClientController extends GenericController
{
    private final static Logger    logger = LoggerFactory.getLogger(BrandBuyServiceClientController.class);
    
    @Autowired
    private BrandBuyServiceService brandBuyServiceService;
    
    @Autowired
    private DataDictValueService   dataDictValueService;
    
    /**
     * 列表
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandBuyService searchBean = new BrandBuyService();
        searchBean.setBrandName(MapUtils.getString(map, "brandName"));
        searchBean.setServiceId(MapUtils.getString(map, "serviceId"));
        searchBean.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        searchBean.setPage(page);
        PaginateResult<BrandBuyService> result = new PaginateResult<>(page, brandBuyServiceService.findList(searchBean));
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
        brandBuyServiceService.delete(MapUtils.getString(map, "refrenceId"));
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
        BrandBuySerLog brandBuySerLog = new BrandBuySerLog();
        BeanUtils.populate(brandBuySerLog, map);
        brandBuySerLog.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        brandBuySerLog.setChargType(MapUtils.getShort(map, "chargType"));
        brandBuySerLog.setBuyNum(MapUtils.getInteger(map, "buyNum"));
        if (null != MapUtils.getDouble(map, "buyMoney"))
        {
            brandBuySerLog.setBuyMoney(new BigDecimal(MapUtils.getDouble(map, "buyMoney")));
        }
        brandBuySerLog.setBeginTime(MapUtils.getLong(map, "beginTime"));
        brandBuySerLog.setBrandName(MapUtils.getString(map, "brandName"));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, brandBuySerLog))
        {
            brandBuyServiceService.saveByClient(brandBuySerLog);
        }
        return jsonMessage;
    }
}
