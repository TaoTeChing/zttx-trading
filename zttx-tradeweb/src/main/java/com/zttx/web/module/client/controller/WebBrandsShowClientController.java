package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.fronts.entity.WebBrandsShow;
import com.zttx.web.module.fronts.service.WebBrandsShowService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：WebBrandsShowClientController.java </p>
 * <p>Title: WebBrandsShowClientController </p>
 * <p>Description: 首页感兴趣品牌展示管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/18</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/webBrandsShow")
public class WebBrandsShowClientController extends GenericController
{
    @Autowired
    private WebBrandsShowService webBrandsShowService;
    
    /**
     * 列表（分页）
     * @param param 参数
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize == null ? 10 : pageSize);
        pagination.setCurrentPage(currentPage == null ? 1 : currentPage);
        WebBrandsShow searchBean = new WebBrandsShow();
        BeanUtils.populate(searchBean, map);
        searchBean.setShowType(MapUtils.getShort(map, "showType"));
        PaginateResult<WebBrandsShow> result = webBrandsShowService.searchByClient(searchBean, pagination);
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
        WebBrandsShow webBrandsShow = webBrandsShowService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == webBrandsShow) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, webBrandsShow);
    }
    
    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        webBrandsShowService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     * 
     * @author 周光暖 修改
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        WebBrandsShow webBrandsShow = new WebBrandsShow();
        BeanUtils.populate(webBrandsShow, map);
        webBrandsShow.setShowType(MapUtils.getShort(map, "showType"));
        webBrandsShow.setOrderId(MapUtils.getInteger(map, "orderId"));
        webBrandsShow.setImage(MapUtils.getString(map, "image"));
        webBrandsShow.setBrandsId(MapUtils.getString(map, "brandsId"));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, webBrandsShow))
        {
            if (StringUtils.isNotBlank(webBrandsShow.getImage()))
            {
                String imagePath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, webBrandsShow.getImage(), UploadAttCateConst.PRODUCT_GRAPH);
                webBrandsShow.setImage(imagePath);
            }
            webBrandsShowService.saveByClient(webBrandsShow);
        }
        return jsonMessage;
    }
}
