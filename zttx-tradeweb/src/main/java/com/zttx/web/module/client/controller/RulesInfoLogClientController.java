package com.zttx.web.module.client.controller;

import java.util.List;
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
import com.zttx.web.module.fronts.entity.RulesInfoLog;
import com.zttx.web.module.fronts.service.RulesInfoLogService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：RulesInfoLogClientController.java </p>
 * <p>Title: RulesInfoLogClientController </p>
 * <p>Description: 网站规则内容信息日志管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/8</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/rulesInfoLog")
public class RulesInfoLogClientController extends GenericController
{
    @Autowired
    private RulesInfoLogService rulesInfoLogService;
    
    /**
     * 列表
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
        RulesInfoLog searchBean = new RulesInfoLog();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        List<RulesInfoLog> result = rulesInfoLogService.findList(searchBean);
        return this.getJsonMessage(ClientConst.SUCCESS, new PaginateResult(searchBean.getPage(), result));
    }
    
    /**
     * 查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RulesInfoLog rulesInfoLog = rulesInfoLogService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == rulesInfoLog) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(ClientConst.SUCCESS, rulesInfoLog);
    }
}
