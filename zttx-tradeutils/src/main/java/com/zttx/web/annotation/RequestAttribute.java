package com.zttx.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数绑定注解，用于获取HttpServletRequest中的attribute属性
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAttribute
{
    /**
     * 要获取的参数名, 默认会根据参数名获取, 例如
     * public String test(@ResuestAttribute String ipAddr)
     * 相当与 String ipAddr = request.getAttribute("ipAddr")
     */
    String value() default "";
    
    /**
     * Whether the parameter is required.
     * Default is true, leading to an exception thrown in case
     * of the parameter missing in the request. Switch this to
     * false if you prefer a null in case of the parameter missing.
     * Alternatively, provide a {@link #defaultValue() defaultValue},
     * which implicitly sets this flag to false.
     */
    boolean required() default true;
    
    /**
     * The default value to use as a fallback. Supplying a default value
     * implicitly sets {@link #required()} to false.
     */
    String defaultValue() default "";
}
