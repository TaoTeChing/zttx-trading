package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zttx.web.module.common.entity.WebDefTemplate;
import com.zttx.web.module.common.service.WebDefTemplateService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：TemplateClientController.java </p>
 * <p>Title: TemplateClientController </p>
 * <p>Description: 页面模版接口 </p>
 * <p>Copyright: Copyright (c) 15/9/6</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/webDefTemplate")
public class TemplateClientController extends GenericController
{
    private final static Logger   logger = LoggerFactory.getLogger(TemplateClientController.class);
    
    @Autowired
    private WebDefTemplateService webDefTemplateService;
    
    /**
     * 列表
     * @param param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        WebDefTemplate webDefTemplate = new WebDefTemplate();
        BeanUtils.populate(webDefTemplate, map);
        webDefTemplate.setPage(page);
        List<WebDefTemplate> result = webDefTemplateService.findList(webDefTemplate);
        return super.getJsonMessage(ClientConst.SUCCESS, new PaginateResult(webDefTemplate.getPage(), result));
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
        WebDefTemplate webDefTemplate = webDefTemplateService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == webDefTemplate) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(ClientConst.SUCCESS, webDefTemplate);
    }
    
    /**
     * 新增/修改
     * refrenceId==null：新增
     * refrenceId!=null：修改
     * @param request
     * @param param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        WebDefTemplate webDefTemplate = new WebDefTemplate();
        BeanUtils.populate(webDefTemplate, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        webDefTemplate.setHtmlText(request.getParameter(ClientConst.HTML));
        if (beanValidator(jsonMessage, webDefTemplate))
        {
            webDefTemplateService.save(webDefTemplate);
        }
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        webDefTemplateService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
