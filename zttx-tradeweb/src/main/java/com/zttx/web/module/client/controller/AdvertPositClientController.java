package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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
import com.zttx.web.module.fronts.entity.AdvertPosit;
import com.zttx.web.module.fronts.service.AdvertPositService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：AdvertPositClientController.java </p>
 * <p>Title: AdvertPositClientController </p>
 * <p>Description: 广告位对外接口控制器 </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/advertPosit")
public class AdvertPositClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(AdvertPositClientController.class);
    
    @Autowired
    private AdvertPositService  advertPositService;
    
    /**
     * 列表（分页）
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        AdvertPosit advertPosit = new AdvertPosit();
        BeanUtils.populate(advertPosit, map);
        advertPosit.setPage(page);
        List<AdvertPosit> result = advertPositService.findList(advertPosit);
        return super.getJsonMessage(ClientConst.SUCCESS, new PaginateResult<>(page, result));
    }
    
    /**
     * 列表（不分页）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonMessage list()
    {
        List<AdvertPosit> result = advertPositService.selectAll();
        return super.getJsonMessage(ClientConst.SUCCESS, result);
    }
    
    /**
     * 查询
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        AdvertPosit advertPosit = advertPositService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == advertPosit) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(ClientConst.SUCCESS, advertPosit);
    }
    
    /**
     * 删除广告位(逻辑删除)
     * @param param refrenceId 主键编号
     * @return {@link JsonMessage}
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        advertPositService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
    
    /**
     * 新增/修改
     * refrenceId==null：新增
     * refrenceId!=null：修改
     * @param param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        AdvertPosit advertPosit = new AdvertPosit();
        BeanUtils.populate(advertPosit, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, advertPosit))
        {
            advertPositService.save(advertPosit);
        }
        return jsonMessage;
    }
}
