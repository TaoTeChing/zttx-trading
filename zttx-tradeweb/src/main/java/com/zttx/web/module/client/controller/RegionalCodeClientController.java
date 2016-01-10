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
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.RegionalCode;
import com.zttx.web.module.common.service.RegionalCodeService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：RegionalCodeClientController.java </p>
 * <p>Title: RegionalCodeClientController </p>
 * <p>Description: 地区码管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/17</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/regional")
public class RegionalCodeClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(RegionalCodeClientController.class);
    
    @Autowired
    private RegionalCodeService regionalCodeService;
    
    /**
     * 分页查询列表
     * @param param RegionalCode page
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        RegionalCode searchBean = new RegionalCode();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        List<RegionalCode> result = regionalCodeService.findList(searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, new PaginateResult<>(page, result));
    }
    
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public JsonMessage getRegionalCode(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        RegionalCode regionalCode = regionalCodeService.selectByPrimaryKey(refrenceId);
        return super.getJsonMessage(CommonConst.SUCCESS, regionalCode);
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonMessage modRegionalCode(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RegionalCode regionalCode = new RegionalCode();
        BeanUtils.populate(regionalCode, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, regionalCode))
        {
            regionalCodeService.saveByClient(regionalCode);
        }
        return jsonMessage;
    }
    
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public JsonMessage delRegionalCode(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        regionalCodeService.delete(refrenceId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
