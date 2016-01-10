package com.zttx.web.module.common.service;

import com.zttx.sdk.exception.BusinessException;

import java.util.Map;

/**
 * <p>File:ProductPriceService</p>
 * <p>Title: </p>
 * <p>Description: 产品价格获取接口</p>
 * <p>Copyright: Copyright (c)2015/8/27 10:02</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public interface ProductPriceService {

    /**
     * 加盟用户判断
     * 判断产品的状态和其该显示的价格   ---失败啊，写了这个这么弄死人的办法，参数map,返回还是map,坑人啊---
     * @param map
     * @return
     * @author 易永耀
     */
    Map<String,Object> toConfirmProTypeAndPrice(Map<String, Object> map) throws BusinessException;
    /**
     * 非加盟用户判断,区域授权判断
     * 判断产品的状态和其该显示的价格   ---失败啊，写了这个这么弄死人的办法，参数map,返回还是map,坑人啊---
     * @param map
     * @return
     * @author 易永耀
     */
    Map<String, Object> toConfirmProTypeAndPriceByAreaNo (Map<String, Object> map)throws BusinessException;
}
