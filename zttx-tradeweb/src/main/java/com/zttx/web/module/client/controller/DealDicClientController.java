package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：NetAddressController.java</p>
 * <p>Title: 品牌经营品类信息管理接口</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-3 下午1:42:11</p>
 * <p>Company: 8637.com</p>
 * @author 吕海斌
 * @version 1.0
 * @author 周光暖修改 2014 2014-6-20 
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealDic")
public class DealDicClientController extends GenericController
{
    @Autowired
    private DealDicService dealDicService;
    
    /**
     * 列表
     * @throws BusinessException 
     * @author 周光暖 修改
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealDic searchBean = new DealDic();
        // BeanUtils.populate(searchBean, map);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        searchBean.setDealLevel(MapUtils.getShort(map, "dealLevel"));
        searchBean.setRefrenceId(refrenceId);
        PaginateResult<DealDic> result = dealDicService.searchByClient(page, searchBean);
        DealDic dealDic = dealDicService.findParentIdByRefrenceId(refrenceId);
        if (null == dealDic) { return super.getJsonMessage(CommonConst.SUCCESS, result); }
        Map<String, Object> backId = Maps.newHashMap();
        backId.put("backId", dealDic.getParentId());
        backId.put("dealName", dealDic.getDealName());
        return super.getJsonMessage(CommonConst.SUCCESS, result, backId);
    }
    
    /**
     * 删除品牌商经营类别
     * @param dealDic
     * @return
     * @throws BusinessException 
     * @author 周光暖 修改
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        dealDicService.deleteByClient(refrenceId);
        return super.getJsonMessage(CommonConst.SUCCESS, refrenceId);
    }
    
    /**
     * 保存品牌商经营类别：refrenceId（null：新增，非null：修改）
     * @param dealDic
     * @return
     * @throws BusinessException 
     * @author 周光暖 修改
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealDic dealDic = new DealDic();
        BeanUtils.populate(dealDic, map);
        dealDic.setParentId(StringUtils.isBlank(dealDic.getParentId()) ? "-1" : dealDic.getParentId());
        if (StringUtils.isNotBlank(dealDic.getDealIcon()))
        {
        	dealDic.setDealIcon(FileClientUtil.moveImgFromTemp(ImageConst.COMMON_IMG_PATH, dealDic.getDealIcon(), null));
        }
        String action = StringUtils.isBlank(dealDic.getRefrenceId()) ? "insert" : "update";
        dealDicService.saveByClient(dealDic);
        Map<String, Object> result = Maps.newHashMap();
        result.put("dealDic", dealDic);
        result.put("action", action);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 根据ID来查询品类
     * @param request
     * @param param
     * @return
     * @author 周光暖
     */
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage load(HttpServletRequest request, ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        DealDic dealDic = dealDicService.findByRefrenceId(refrenceId);
        return super.getJsonMessage(CommonConst.SUCCESS, dealDic);
    }
    
    /**
     * 获取类目分页结果集
     * @param request
     * @param param
     * @return
     * @author 李飞欧
     * @throws Exception 
     */
    @RequestMapping(value = "/listDealDic_erp", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage listDealDic_erp(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealDic dealDic = new DealDic();
        BeanUtils.populate(dealDic, map);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination page = new Pagination();
        page.setPageSize(pageSize == null ? 10 : pageSize);
        page.setCurrentPage(currentPage == null ? 1 : currentPage);
        PaginateResult<DealDic> result = dealDicService.listDealDics(dealDic, page);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
