package com.zttx.web.dubbo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.goods.module.entity.WebUnit;
import com.zttx.goods.module.service.WebUnitService;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：WebUnitServiceDubboConsumer.java </p>
 * <p>Title: 度量单位消费服务 </p>
 * <p>Description:WebUnitServiceDubboConsumer </p>
 * <p>Copyright: Copyright (c) 15/10/23 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class WebUnitServiceDubboConsumer
{
    public static final Logger logger = LoggerFactory.getLogger(WebUnitServiceDubboConsumer.class);
    
    @Autowired(required = false)
    private WebUnitService     webUnitService;
    
    /**
     * 查询所有单位
     * @return
     */
    public List<WebUnit> findAll() throws BusinessException
    {
        List<WebUnit> info = null;
        try
        {
            info = webUnitService.findAll();
        }
        catch (Exception e)
        {
            logger.error("findAll error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
    
    /**
     * 根据id获取
     * @param refrenceId
     * @return
     * @throws BusinessException
     */
    public WebUnit getByRefrenceId(String refrenceId) throws BusinessException
    {
        WebUnit info = null;
        try
        {
            info = webUnitService.getByRefrenceId(refrenceId);
        }
        catch (Exception e)
        {
            logger.error("getByRefrenceId error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
        return info;
    }
}
