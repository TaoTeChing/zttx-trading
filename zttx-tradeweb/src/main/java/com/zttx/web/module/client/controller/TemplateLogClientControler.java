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
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.WebDefTmpLog;
import com.zttx.web.module.common.service.WebDefTmpLogService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：TemplateLogClientControler.java </p>
 * <p>Title: TemplateLogClientControler </p>
 * <p>Description: HTML模板信息日志 </p>
 * <p>Copyright: Copyright (c) 15/9/7</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/webDefTmpLog")
public class TemplateLogClientControler extends GenericController
{
    @Autowired
    private WebDefTmpLogService webDefTmpLogService;
    
    /**
     * 列表历史记录
     * @param param 请求参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        WebDefTmpLog webDefTmpLog = new WebDefTmpLog();
        BeanUtils.populate(webDefTmpLog, map);
        webDefTmpLog.setPage(page);
        List<WebDefTmpLog> result = webDefTmpLogService.findList(webDefTmpLog);
        return super.getJsonMessage(CommonConst.SUCCESS, new PaginateResult(webDefTmpLog.getPage(), result));
    }
    
    /**
     * 查询历史记录
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        WebDefTmpLog webDefTmpLog = webDefTmpLogService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == webDefTmpLog) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, webDefTmpLog);
    }
}
