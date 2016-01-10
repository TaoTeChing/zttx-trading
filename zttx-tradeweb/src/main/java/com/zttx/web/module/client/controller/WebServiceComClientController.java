package com.zttx.web.module.client.controller;

import java.util.Map;

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
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.dealer.entity.WebServiceCom;
import com.zttx.web.module.dealer.service.WebServiceComService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：WebServiceComClientController.java </p>
 * <p>Title: WebServiceComClientController </p>
 * <p>Description: 网站服务商管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/9</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/webServiceCom")
public class WebServiceComClientController extends GenericController
{
    private final static Logger  logger = LoggerFactory.getLogger(WebServiceComClientController.class);
    
    @Autowired
    private WebServiceComService webServiceComService;
    
    /**
     * 列表  
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        WebServiceCom searchBean = new WebServiceCom();
        BeanUtils.populate(searchBean, map);
        PaginateResult<WebServiceCom> result = webServiceComService.searchByClient(page, searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
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
        WebServiceCom webServiceCom = webServiceComService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == webServiceCom) { return super.getJsonMessage(ClientConst.OBJECTEXIST); }
        return super.getJsonMessage(CommonConst.SUCCESS, webServiceCom);
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
        // webServiceComService.delWebServiceComById(MapUtils.getString(map, "refrenceId"), true);
        webServiceComService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 新增/修改
     * @param param 保存对象
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        WebServiceCom webServiceCom = new WebServiceCom();
        BeanUtils.populate(webServiceCom, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, webServiceCom))
        {
            if (StringUtils.isNotBlank(webServiceCom.getComPhoto())
                    && (webServiceCom.getComPhoto().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || webServiceCom.getComPhoto().startsWith(ImageConst.TEMP)))
            {
                String comPhoto = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, webServiceCom.getComPhoto(), "");
                webServiceCom.setComPhoto(comPhoto);
            }
            String webServiceComId = webServiceComService.saveByClient(webServiceCom);
            jsonMessage.setObject(webServiceComId);
        }
        return jsonMessage;
    }
}
