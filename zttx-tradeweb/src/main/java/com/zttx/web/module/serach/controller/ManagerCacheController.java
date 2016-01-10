package com.zttx.web.module.serach.controller;

import com.zttx.web.security.shiro.cache.RedisCacheManager;
import com.zttx.web.security.shiro.session.RedisSessionDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.cache.RedisCacheUtils;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.web.consts.CommonConst;

/**
 * <p>File：ManagerCacheController.java </p>
 * <p>Title: 管理缓存服务的控制器 </p>
 * <p>Description: ManagerCacheController </p>
 * <p>Copyright: Copyright (c) 2014 08/13/2015 18:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/cache")
public class ManagerCacheController extends GenericController
{
    /**
     * 根据指定的KEY清理缓存
     * @param key
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/clean")
    public JsonMessage clean(String key) throws BusinessException
    {
        JedisUtils.delObject(key);
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 清理持久层缓存
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cleanDao")
    public JsonMessage cleanDao() throws BusinessException
    {
        RedisCacheUtils.getInstance().clean();
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 清理会话缓存
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cleanSession")
    public JsonMessage cleanSession() throws BusinessException
    {
        RedisSessionDAO.clean();
        RedisCacheManager.clear();
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 清理所有缓存
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cleanAll")
    public JsonMessage cleanAll() throws BusinessException
    {
        RedisSessionDAO.clean();
        RedisCacheManager.clear();
        RedisCacheUtils.getInstance().clean();
        return getJsonMessage(CommonConst.SUCCESS);
    }
}
