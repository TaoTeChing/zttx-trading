package com.zttx.web.module.client.controller;

import java.util.Map;

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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.fronts.entity.RulesInfo;
import com.zttx.web.module.fronts.service.RulesCateService;
import com.zttx.web.module.fronts.service.RulesInfoService;
import com.zttx.web.search.solrj.RuleSolrHandler;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：RulesInfoClientController.java </p>
 * <p>Title: RulesInfoClientController </p>
 * <p>Description: 网站规则内容信息管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/rulesInfo")
public class RulesInfoClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(RulesInfoClientController.class);
    
    @Autowired
    private RulesInfoService    rulesInfoService;
    
    @Autowired
    private RuleSolrHandler     ruleSolrHandler;
    
    @Autowired
    private RulesCateService    rulesCateService;
    
    /**
     * 分页查询
     * @param param 参数
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        RulesInfo searchBean = new RulesInfo();
        BeanUtils.populate(searchBean, map);
        searchBean.setOrderByCreateTime(MapUtils.getString(map, "createTime", ""));
        searchBean.setOrderByViewNum(MapUtils.getString(map, "viewNum", ""));
        PaginateResult<RulesInfo> rulesInfoList = rulesInfoService.selectByClient(page, searchBean);
        return this.getJsonMessage(ClientConst.SUCCESS, rulesInfoList);
    }
    
    /**
     * 查询
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RulesInfo rulesInfo = rulesInfoService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == rulesInfo) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, rulesInfo);
    }
    
    /**
     * 保存
     * @param request 请求
     * @param param
     *      refrenceId=null：新增
     *      refrenceId!=null：修改
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        RulesInfo rulesInfo = new RulesInfo();
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        try
        {
            BeanUtils.populate(rulesInfo, map);
            String articleText = request.getParameter(ClientConst.HTML);
            rulesInfo.setArticleText(articleText);
            if (beanValidator(jsonMessage, rulesInfo))
            {
                if (StringUtils.isNotBlank(rulesInfo.getArticleImage())
                        && (rulesInfo.getArticleImage().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || rulesInfo.getArticleImage().startsWith(ImageConst.TEMP)))
                {
                    String articleImage = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, rulesInfo.getArticleImage(), "");
                    rulesInfo.setArticleImage(articleImage);
                }
                rulesInfo = rulesInfoService.selectByPrimaryKey(rulesInfoService.saveByClient(rulesInfo).getRefrenceId());
                rulesInfo.setRulesCates(rulesCateService.getParentRulesCates(rulesInfo.getCateId()));
                ruleSolrHandler.addRulesInfo(rulesInfo);
            }
        }
        catch (BusinessException e)
        {
            logger.error("网站规则内容信息管理接口，调用保存失败: " + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param 参数
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        RulesInfo rulesInfo = rulesInfoService.selectByPrimaryKey(refrenceId);
        if (null != rulesInfo)
        {
            rulesInfoService.delete(MapUtils.getString(map, "refrenceId"));
            ruleSolrHandler.removeRulesInfo(rulesInfo);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
