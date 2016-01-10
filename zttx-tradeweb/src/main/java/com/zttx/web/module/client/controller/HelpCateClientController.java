package com.zttx.web.module.client.controller;

import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.zttx.web.module.fronts.entity.HelpCate;
import com.zttx.web.module.fronts.service.HelpCateService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：HelpCateClientController.java </p>
 * <p>Title: HelpCateClientController </p>
 * <p>Description: 网站帮助类别对外接口控制器 </p>
 * <p>Copyright: Copyright (c) 15/9/2</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/helpCate")
public class HelpCateClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(HelpCateClientController.class);
    
    @Autowired
    private HelpCateService     helpCateService;
    
    /**
     * 准备搜索条件相关的数据
     * @return JsonMessage
     * @throws Exception
     * @author 章旭楠
     */
    @RequestMapping(value = "/prepare", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage prepare() throws Exception
    {
        TreeSet<HelpCate> result = helpCateService.listTop();
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 数据列表查询
     * @param param 参数
     * @return JsonMessage
     * @throws Exception 异常
     * @author 章旭楠
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        HelpCate searchBean = new HelpCate();
        BeanUtils.populate(searchBean, map);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isNotBlank(refrenceId))
        {
            searchBean.setParentId(refrenceId);
        }
        else
        {
            searchBean.setHelpLevel(1);// 查询所有1级
        }
        PaginateResult<HelpCate> result = helpCateService.searchByClient(page, searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        HelpCate helpCate = helpCateService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == helpCate) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, helpCate);
    }
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        HelpCate helpCate = new HelpCate();
        BeanUtils.populate(helpCate, map);
        helpCate.setDescription(request.getParameter(ClientConst.HTML));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, helpCate))
        {
            helpCateService.saveByClient(helpCate);
        }
        return jsonMessage;
    }
    
    /**
     * 删除 逻辑
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        helpCateService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
}
