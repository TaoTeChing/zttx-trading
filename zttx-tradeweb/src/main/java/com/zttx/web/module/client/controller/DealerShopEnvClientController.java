package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerShopEnvTemp;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerShopEnvImgTempService;
import com.zttx.web.module.dealer.service.DealerShopEnvService;
import com.zttx.web.module.dealer.service.DealerShopEnvTempService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerShopEnvClientController.java </p>
 * <p>Title: DealerShopEnvClientController </p>
 * <p>Description: 店铺周边管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerShopEnv")
public class DealerShopEnvClientController extends GenericController
{
    @Autowired
    private DealerShopEnvService        dealerShopEnvService;
    
    @Autowired
    private DealerShopEnvTempService    dealerShopEnvTempService;
    
    @Autowired
    private DealerShopEnvImgTempService dealerShopEnvImgTempService;
    
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @ResponseBody
    @RequestMapping("/search")
    public JsonMessage list(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerShopEnvTemp dealerShopEnvTemp = new DealerShopEnvTemp();
        BeanUtils.populate(dealerShopEnvTemp, map);
        dealerShopEnvTemp.setPage(page);
        dealerShopEnvTemp.setStatus((MapUtils.getString(map, "status") == null || MapUtils.getString(map, "status").equals("")) ? null : Integer.parseInt(MapUtils
                .getString(map, "status")));
        PaginateResult<DealerShopEnvTemp> result = dealerShopEnvTempService.searchByClient(dealerShopEnvTemp);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查询
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping("/view")
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        DealerShopEnvTemp dealerShopEnvTemp = dealerShopEnvTempService.selectByPrimaryKey(refrenceId);
        if (null == dealerShopEnvTemp) { return super.getJsonMessage(ClientConst.DBERROR); }
        dealerShopEnvTemp.setDealerShopEnvImgTemps(dealerShopEnvImgTempService.getDealerShopEnvImgTempByShopEnvId(refrenceId));
        dealerShopEnvTemp.setDetailObj(JSONObject.parse(dealerShopEnvTemp.getDetail()));
        dealerShopEnvTemp.setDetail(null);
        return super.getJsonMessage(CommonConst.SUCCESS, dealerShopEnvTemp);
    }
    
    @ResponseBody
    @RequestMapping("/modStatus")
    public JsonMessage modDealerShopEnvStatus(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        int status = MapUtils.getIntValue(map, "status");
        String auditUser = MapUtils.getString(map, "auditUser");
        String auditIp = MapUtils.getString(map, "auditIp");
        dealerShopEnvTempService.updateDealerShopEnvTempStatus(refrenceId, status, auditUser, auditIp);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @ResponseBody
    @RequestMapping("/modShowed")
    public JsonMessage modDealerShopEnvShowed(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        int showed = MapUtils.getIntValue(map, "showed");
        dealerShopEnvService.updateDealerShopEnvShowed(refrenceId, showed == 1);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 根据手机号码和店铺名称查询经销商信息(经销商明细表为主)
     * @param param 参数
     * @return JsonMessage
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/dealerInfos", method = RequestMethod.POST)
    public JsonMessage getDealerInfos(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        String dealerName = MapUtils.getString(map, "dealerName");
        String userMobile = MapUtils.getString(map, "userMobile");
        Pagination pagination = new Pagination(currentPage == null ? 1 : currentPage, pageSize == null ? 10 : pageSize);
        // show：0表示全部显示，1：显示有图片的经销商(图片来源dealerImage)
        boolean show = MapUtils.getShort(map, "show", (short) 0) > 0 ? true : false;
        PaginateResult<Map<String, Object>> result = dealerInfoService.getDealerInfosByClient(show, userMobile, dealerName, pagination);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
