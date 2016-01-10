package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
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
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.fronts.entity.Adverts;
import com.zttx.web.module.fronts.service.AdvertsService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：AdvertsClientController.java </p>
 * <p>Title: AdvertsClientController </p>
 * <p>Description: 广告信息对外接口控制器 </p>
 * <p>Copyright: Copyright (c) 15/9/2</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/adverts")
public class AdvertsClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(AdvertsClientController.class);
    
    @Autowired
    private AdvertsService      advertsService;
    
    /**
     * 广告分页列表
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        Adverts adverts = new Adverts();
        adverts.setUsedState(MapUtils.getShort(map, "usedState"));
        BeanUtils.populate(adverts, map);
        PaginateResult<Adverts> result = advertsService.search(adverts, page);
        return super.getJsonMessage(ClientConst.SUCCESS, result);
    }
    
    /**
     * 查询
     * @param param refrenceId 主键编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Adverts adverts = advertsService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == adverts) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(ClientConst.SUCCESS, adverts);
    }
    
    /**
     * 删除广告(逻辑删除)
     * @param param refrenceId 主键编号
     * @return {@link JsonMessage}
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        advertsService.delete(MapUtils.getString(map, "refrenceId"));
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
        Adverts adverts = new Adverts();
        BeanUtils.populate(adverts, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, adverts))
        {
            if (StringUtils.isNotBlank(adverts.getAdLogo())
                    && (adverts.getAdLogo().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || adverts.getAdLogo().startsWith(ImageConst.TEMP)))
            {
                String adLogo = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, adverts.getAdLogo(), "");
                adverts.setAdLogo(adLogo);
            }
            adverts.setUrlAddress(adverts.getUrlAddress().replace("^", "="));
            advertsService.save(adverts);
        }
        return jsonMessage;
    }
}
