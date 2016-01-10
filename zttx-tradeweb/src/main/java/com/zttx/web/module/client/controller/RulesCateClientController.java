package com.zttx.web.module.client.controller;

import java.util.Map;
import java.util.TreeSet;

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
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.fronts.entity.RulesCate;
import com.zttx.web.module.fronts.service.RulesCateService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：RulesCateClientController.java </p>
 * <p>Title: RulesCateClientController </p>
 * <p>Description: 网站规则类别管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/rulesCate")
public class RulesCateClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(RulesCateClientController.class);
    
    @Autowired
    private RulesCateService    rulesCateService;
    
    /**
     * 列表
     * @return
     * @throws Exception
     * @author 章旭楠
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage search() throws Exception
    {
        // List<RulesCate> cates = rulesCateService.selectAll();
        TreeSet<RulesCate> result = rulesCateService.selectTop();
        return getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param param
     * @return
     * @throws Exception
     * @author 周光暖
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RulesCate rulesCate = rulesCateService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == rulesCate) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, rulesCate);
    }
    
    /**
     * 保存
     * @param request 请求
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RulesCate rulesCate = new RulesCate();
        BeanUtils.populate(rulesCate, map);
        String cateText = request.getParameter(ClientConst.HTML);
        rulesCate.setCateText(cateText);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, rulesCate))
        {
            rulesCateService.saveByClient(rulesCate);
        }
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
        rulesCateService.deleteByClient(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
