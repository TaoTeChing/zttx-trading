package com.zttx.web.annotation;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by sephy Date: 14-7-4 Time: 0:34
 */
public class RequestAttributeArgumentResolver implements HandlerMethodArgumentResolver
{
    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        RequestAttribute annotation = parameter.getParameterAnnotation(RequestAttribute.class);
        if (annotation != null) return true;
        return false;
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception
    {
        RequestAttribute annotation = parameter.getParameterAnnotation(RequestAttribute.class);
        if (annotation != null)
        {
            String name = annotation.value();
            if (StringUtils.isBlank(name)) name = parameter.getParameterName();
            HttpServletRequest httprequest = (HttpServletRequest) webRequest.getNativeRequest();
            Object attr = httprequest.getAttribute(name);
            if (attr == null) attr = annotation.defaultValue();
            if (attr == null && annotation.required())
                throw new IllegalStateException("Missing parameter '" + name + "' of type [" + parameter.getParameterType().getName() + "]");
            return attr;
        }
        return null;
    }
}
