/*
 * @(#)EntityValidateUtils.java 2014-2-27 上午9:47:26
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>File：EntityValidateUtils.java</p>
 * <p>Title: JSR303 Validator(Hibernate Validator)工具类.</p>
 * <p>Description:ConstraintViolation中包含propertyPath, message 和invalidValue等信息.</p>
 * 提供了各种convert方法，适合不同的i18n需求:
 * 1. List<String>, String内容为message
 * 2. List<String>, String内容为propertyPath + separator + message
 * 3. Map<propertyPath, message>
 * <p>Copyright: Copyright (c) 2014 2014-2-27 上午9:47:26</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class EntityValidateUtils
{
    /**
     * 调用JSR303的validate方法, 验证失败时抛出ConstraintViolationException.
     * @param validator Validator
     * @param object Object
     * @param groups Class<?>[]
     * @throws ConstraintViolationException ConstraintViolationException
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void validateWithException(Validator validator, Object object, Class<?> ... groups) throws ConstraintViolationException
    {
        Set constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) { throw new ConstraintViolationException(constraintViolations); }
    }
    
    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>中为List<message>.
     * @param e ConstraintViolationException
     * @return List<String> List<String>
     */
    public static List<String> extractMessage(ConstraintViolationException e)
    {
        return extractMessage(e.getConstraintViolations());
    }
    
    public static List<Map<String, String>> extractMessageList(ConstraintViolationException e)
    {
        return extractMessageList(e.getConstraintViolations());
    }
    
    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<message>
     * @param constraintViolations Set<? extends ConstraintViolation>
     * @return List<String> List<String>
     */
    @SuppressWarnings("rawtypes")
    public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations)
    {
        List<String> errorMessages = Lists.newArrayList();
        for (ConstraintViolation violation : constraintViolations)
        {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }
    
    @SuppressWarnings("rawtypes")
    public static List<Map<String, String>> extractMessageList(Set<? extends ConstraintViolation> constraintViolations)
    {
        List<Map<String, String>> errorMessageList = Lists.newArrayList();
        for (ConstraintViolation violation : constraintViolations)
        {
            Map<String, String> map = Maps.newHashMap();
            map.put("errName", violation.getPropertyPath().toString());
            map.put("errMsg", violation.getMessage());
            errorMessageList.add(map);
        }
        return errorMessageList;
    }
    
    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为Map<property, message>.
     * @param e ConstraintViolationException
     * @return Map<String, String> Map<String, String>
     */
    public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e)
    {
        return extractPropertyAndMessage(e.getConstraintViolations());
    }
    
    /**
     * 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
     * @param constraintViolations Set<? extends ConstraintViolation>
     * @return Map<String, String> Map<String, String>
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations)
    {
        Map<String, String> errorMessages = Maps.newHashMap();
        for (ConstraintViolation violation : constraintViolations)
        {
            errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errorMessages;
    }
    
    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath message>.
     * @param e ConstraintViolationException
     * @return List<String> List<String>
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e)
    {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
    }
    
    /**
     * 辅助方法, 转换Set<ConstraintViolations>为List<propertyPath message>.
     * @param constraintViolations Set<? extends ConstraintViolation>
     * @return List<String> List<String>
     */
    @SuppressWarnings("rawtypes")
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations)
    {
        return extractPropertyAndMessageAsList(constraintViolations, " ");
    }
    
    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath +separator+ message>.
     * @param e ConstraintViolationException
     * @param separator 分割符号
     * @return List<String> List<String>
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator)
    {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
    }
    
    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<propertyPath +separator+ message>.
     * @param constraintViolations Set<? extends ConstraintViolation>
     * @param separator 分割符号
     * @return List<String> List<String>
     */
    @SuppressWarnings("rawtypes")
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations, String separator)
    {
        List<String> errorMessages = Lists.newArrayList();
        for (ConstraintViolation violation : constraintViolations)
        {
            errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
        }
        return errorMessages;
    }
}
