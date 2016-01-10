package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.dealer.entity.WebServiceItems;
import com.zttx.web.module.dealer.service.WebServiceItemsService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：WebServiceItemsClientController.java </p>
 * <p>Title: WebServiceItemsClientController </p>
 * <p>Description: 网站服务项目管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/4</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/webServiceItems")
public class WebServiceItemsClientController extends GenericController
{
    private final static Logger    logger = LoggerFactory.getLogger(WebServiceItemsClientController.class);
    
    @Autowired
    private WebServiceItemsService webServiceItemsService;
    
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
        WebServiceItems searchBean = new WebServiceItems();
        BeanUtils.populate(searchBean, map);
        searchBean.setOrderParams(MapUtils.getString(map, "orderParams"));
        searchBean.setOrderType(MapUtils.getString(map, "orderType", ""));
        searchBean.setServiceType(MapUtils.getShort(map, "serviceType"));
        searchBean.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        searchBean.setPage(page);
        PaginateResult<WebServiceItems> result = webServiceItemsService.selectByClient(searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查询
     * @param param 参数
     * @return JsonMessage
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        WebServiceItems webServiceItems = webServiceItemsService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == webServiceItems) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, webServiceItems);
    }
    
    /**
     * 逻辑删除
     * @param param 参数
     * @return JsonMessage
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        webServiceItemsService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
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
        WebServiceItems webServiceItems = new WebServiceItems();
        BeanUtils.populate(webServiceItems, map);
        webServiceItems.setServicerCate(MapUtils.getShort(map, "servicerCate"));
        webServiceItems.setCommentNum(MapUtils.getInteger(map, "commentNum"));
        webServiceItems.setPrice(BigDecimal.valueOf(MapUtils.getDoubleValue(map, "price", 0.0)));
        webServiceItems.setServicePrice(BigDecimal.valueOf(MapUtils.getDoubleValue(map, "servicePrice", 0.0)));
        webServiceItems.setMinBuyNum(MapUtils.getInteger(map, "minBuyNum", 0));
        webServiceItems.setServiceMark(request.getParameter(ClientConst.HTML));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, webServiceItems))
        {
            if (StringUtils.isNotBlank(webServiceItems.getServicePhoto())
                    && (webServiceItems.getServicePhoto().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || webServiceItems.getServicePhoto().startsWith(
                            ImageConst.TEMP)))
            {
                String servicePhoto = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, webServiceItems.getServicePhoto(), "");
                webServiceItems.setServicePhoto(servicePhoto);
            }
            jsonMessage.setObject(webServiceItemsService.save(webServiceItems));
        }
        return jsonMessage;
    }
}
