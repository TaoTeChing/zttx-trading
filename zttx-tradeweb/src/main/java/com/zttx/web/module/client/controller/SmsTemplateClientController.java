package com.zttx.web.module.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zttx.sdk.utils.LoggerUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.service.SmsTemplateService;
import com.zttx.web.utils.ParameterUtils;

/**
 * 短信模板RPC控制器
 * Created by 李星 on 2015/9/8.
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT)
public class SmsTemplateClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(SmsTemplateClientController.class);
    @Autowired
    private SmsTemplateService smsTemplateService;
    
    /**
     * 根据短信唯一编码取模板内容
     *
     * @param param 短信KEY
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/sms/getTemplate", method = RequestMethod.POST)
    public JsonMessage getTemplate(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        String key = params.get("key");
        SmsTemplate smsTemplate = null;
        if (StringUtils.isNotBlank(key))
        {
            smsTemplate = smsTemplateService.getBySmsKey(key);
        }
        return this.getJsonMessage(CommonConst.SUCCESS, smsTemplate);
    }
    
    /**
     * 查询模板内容
     *
     * @param request
     * @param param
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/sms/serachTemplate", method = RequestMethod.POST)
    public JsonMessage getAllTemplate(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        SmsTemplate template = new SmsTemplate();
        Pagination pagination = new Pagination();
        BeanUtils.populate(template,params);
        BeanUtils.populate(pagination,params);
        PaginateResult<SmsTemplate> result = smsTemplateService.pageSearch(template, pagination);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 添加或修改短信模板
     * <p>
     * 如果模板的ID为空则系统认定为添加模板，反之则修改模板
     * </p>
     *
     * @param request
     * @param param   模板对象
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/sms/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        SmsTemplate template = new SmsTemplate();
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS, template);
        BeanUtils.populate(template, params);
        if (beanValidator(jsonMessage, template))
        {
            smsTemplateService.saveOrUpdate(template);
        }
        return jsonMessage;
    }
    
    /**
     * 根据主键逻辑删除模板
     *
     * @param param 主键编码
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/sms/delete", method = RequestMethod.POST)
    public JsonMessage delete(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> params = ParameterUtils.getMapFromParameter(param);
        smsTemplateService.delete(params.get("refrenceId"));
        return this.getJsonMessage(CommonConst.SUCCESS, "成功");
    }
}
