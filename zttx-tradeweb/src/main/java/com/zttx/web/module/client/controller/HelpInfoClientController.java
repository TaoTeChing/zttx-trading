package com.zttx.web.module.client.controller;

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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.fronts.entity.HelpInfo;
import com.zttx.web.module.fronts.service.HelpCateService;
import com.zttx.web.module.fronts.service.HelpInfoService;
import com.zttx.web.search.solrj.HelpSolrHandler;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：HelpInfoClientController.java </p>
 * <p>Title: HelpInfoClientController </p>
 * <p>Description: 帮助内容管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/helpInfo")
public class HelpInfoClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(HelpInfoClientController.class);
    
    @Autowired(required = true)
    private HelpInfoService     helpInfoService;
    
    @Autowired
    private HelpSolrHandler     helpSolrHandler;
    
    @Autowired
    private HelpCateService     helpCateService;
    
    /**
     * 分页查询
     * @param param 参数
     * @return JsonMessage
     * @throws BusinessException
     * @author 章旭楠
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        HelpInfo searchBean = new HelpInfo();
        searchBean.setIsRecommand(null);
        searchBean.setIsHot(null);
        searchBean.setIsFaq(null);// 由于默认false，不作为查询条件设null
        BeanUtils.populate(searchBean, map);
        PaginateResult<HelpInfo> helpInfoList = helpInfoService.selectByClient(page, searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, helpInfoList);
    }
    
    /**
     * 查看
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     * @author 章旭楠
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        HelpInfo helpInfo = helpInfoService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == helpInfo) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, helpInfo);
    }
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     * 
     * @author 周光暖
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        HelpInfo helpInfo = new HelpInfo();
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        try
        {
            BeanUtils.populate(helpInfo, map);
            helpInfo.setHtmlText(request.getParameter(ClientConst.HTML));
            helpInfo.setCreateIp(IPUtil.getIpAddr(request));
            if (beanValidator(jsonMessage, helpInfo))
            {
                helpInfo = helpInfoService.selectByPrimaryKey(helpInfoService.saveByClient(helpInfo).getRefrenceId());
                helpInfo.setHelpCates(helpCateService.getParentHelpCates(helpInfo.getHelpCateId()));
                helpSolrHandler.addHelpInfo(helpInfo);
            }
        }
        catch (BusinessException e)
        {
            logger.error("帮助内容管理接口，调用保存失败: " + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return jsonMessage;
    }
    
    /**
     * 逻辑删除
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        HelpInfo helpInfo = helpInfoService.selectByPrimaryKey(refrenceId);
        if (null != helpInfo)
        {
            helpInfoService.delete(MapUtils.getString(map, "refrenceId"));
            helpSolrHandler.removeHelpInfo(helpInfo);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
