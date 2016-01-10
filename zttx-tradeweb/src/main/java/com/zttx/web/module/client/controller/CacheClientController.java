package com.zttx.web.module.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.web.security.shiro.cache.RedisCacheManager;
import com.zttx.web.security.shiro.session.RedisSessionDAO;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.cache.RedisCacheUtils;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：CacheClientController.java </p>
 * <p>Title: 缓存管理接口控制器 </p>
 * <p>Description: CacheClientController </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/client")
public class CacheClientController extends GenericController
{
    /**
     * 根据指定的KEY清理缓存
     * @param request
     * @param param
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cache/cleanCache")
    public JsonMessage cleanCache(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String key = MapUtils.getString(map, "keyName");
        JedisUtils.delObject(key);
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 清除所有查询缓存
     * @param request
     * @param param
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cache/cleanDaoCache")
    public JsonMessage cleanDaoCache(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        RedisCacheUtils.getInstance().clean();
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
    * 清除所有会话缓存
    * @param request
    * @param param
    * @return {@link JsonMessage}
    * @throws BusinessException
    */
    @ResponseBody
    @RequestMapping(value = "/cache/cleanSession")
    public JsonMessage cleanSession(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        RedisSessionDAO.clean();
        RedisCacheManager.clear();
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
    * 清除所有缓存
    * @param request
    * @param param
    * @return {@link JsonMessage}
    * @throws BusinessException
    */
    @ResponseBody
    @RequestMapping(value = "/cache/cleanAll")
    public JsonMessage cleanAll(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        RedisSessionDAO.clean();
        RedisCacheManager.clear();
        RedisCacheUtils.getInstance().clean();
        return getJsonMessage(CommonConst.SUCCESS);
    }
}
