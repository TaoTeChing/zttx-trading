/*
 * @(#)ClientValidator.java 2014-6-21 下午5:32:26
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.spi.ValidationProvider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.HibernateValidator;

import com.google.common.collect.Lists;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.utils.EntityValidateUtils;

/**
 * <p>File：ClientValidator.java</p>
 * <p>Title: 第三方接口参数校验器</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-6-21 下午5:32:26</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class ClientValidator
{
    /**
     * Logger for this class
     */
    private static final Logger logger    = Logger.getLogger(ClientValidator.class);
    
    // 使用 HibernateValidator
    private static Validator    validator = getValidator(HibernateValidator.class);
    
    /**
     * 第三方接口参数校验
     * @param bean 需要校验参数的实体
     * @return JsonMessage：验证通过时为Null
     *      数据格式：{"code":121016,"message":"品类图标不能为空,品类名称不能为空,..."}
     * @author 周光暖
     */
    public static void validateByClient(Object bean) throws BusinessException
    {
        if (null == bean) throw new BusinessException(CommonConst.PARAM_NULL);
        List<Map<String, String>> errorList = entityValidatorNoPropertyList(bean);
        if (CollectionUtils.isNotEmpty(errorList))
        {
            StringBuilder errorStr = new StringBuilder();
            for (Map<String, String> errorMap : errorList)
                errorStr.append(errorMap.get("errMsg") + ",");
            logger.error(errorStr.substring(0, errorStr.length() - 1));
            throw new BusinessException(ClientConst.PARAMERROR.getCode(), errorStr.substring(0, errorStr.length() - 1));
        }
    }
    
    /**
     * 第三方接口参数校验
     * @param errorList 错误提示信息集合
     * @return JsonMessage：验证通过时为Null
     *      数据格式：{"code":121016,"message":"品类图标不能为空,品类名称不能为空,..."}
     * @author 周光暖
     */
    public static void validateByClient(List<String> errorList) throws BusinessException
    {
        if (CollectionUtils.isNotEmpty(errorList)) throw new BusinessException(ClientConst.PARAMERROR.code, errorList.toString());
    }
    
    private static List<Map<String, String>> entityValidatorNoPropertyList(Object object, Class<?> ... groups)
    {
        try
        {
            EntityValidateUtils.validateWithException(validator, object, groups);
        }
        catch (ConstraintViolationException ex)
        {
            return EntityValidateUtils.extractMessageList(ex);
        }
        return Lists.newArrayList();
    }
    
    /**
     * 配置validator的实现类
     * @param validatorProvider
     * @return
     * @author 周光暖
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Validator getValidator(Class<? extends ValidationProvider> validatorProvider)
    {
        return Validation.byProvider(validatorProvider).configure().buildValidatorFactory().getValidator();
    }
}
