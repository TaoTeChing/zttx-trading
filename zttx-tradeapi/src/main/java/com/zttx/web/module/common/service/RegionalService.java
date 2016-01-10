/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.trade.remoting.model.AreaFilter;
import com.zttx.web.module.common.entity.Regional;

/**
 * 全国区域信息 服务接口
 * <p>File：RegionalService.java </p>
 * <p>Title: RegionalService </p>
 * <p>Description:RegionalService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface RegionalService extends GenericServiceApi<Regional>
{
    String REGIONAL_SPLIT_CODE = "/"; // 区域分隔符
    
    /**
     * 根据区域编码获取全名
     * @param areaNo
     * @param split_code
     * @return
     */
    String getFullNameByAreaNo(Integer areaNo, String split_code);
    
    /**
     * 根据区域码 层级获取全名
     *
     * @param areaNo     Integer 区域编码
     * @param level      Integer 层级
     * @param split_code String 分隔符
     * @return String
     * @author 罗盛平
     */
    String getFullNameByAreaNoAndLevel(Integer areaNo, Integer level, String split_code);
    
    /**
     * 根据区域code 和级别 获取父级编码
     *
     * @param areaNo
     * @param level
     * @return Integer
     * @author 罗盛平
     */
    Integer getFatherCode(Integer areaNo, Integer level);
    
    /**
     * 根据编码获取区域
     *
     * @param code
     * @return
     * @author 罗盛平
     */
    Regional getAreaCode(Integer code, List<Regional> cacheList);
    
    /**
     * 根据过滤条件获取
     * @param areaFilter
     * @return
     */
    List<Regional> searchByAreaFilter(AreaFilter areaFilter);
    
    /**
     * 根据区域编码获取区域对象
     * @param areaNo
     * @return
     */
    Regional loadRegionalByAreaNo(Integer areaNo);
    
    /**
     * 通过名字查询地区
     * @param city
     * @return
     */
    Regional selectRegionalByName(String city);
    
    Map<String, Object> getRegionalMap();
    
    /**
     * 根据名称取区域
     * @param name
     * @return
     */
    Regional getRegionalByName(String name);
    
    /**
     * 取区域对象
     * @param request
     * @return
     */
    Regional getRegionalByRequest(HttpServletRequest request);
    
    /**
     * 根据编号取名称
     * @param areaNo
     * @return
     */
    String getNameByAreaNo(Integer areaNo);
    
    /**
     * 根据areaNo查询Regional
     * @param parseInt
     * @return
     */
    Regional searchByCode(Integer parseInt);
    
    /**
     * 根据 areaNo 获取区域全称
     * @param areaNo
     * @return
     * @author 易永耀
     */
    String getFullAreaName(Integer areaNo);
    
    /**
     * 根据区域编码获取区域对象
     * @param areaNo 区域编码
     * @return Regional 区域对象
     */
    Regional load(Integer areaNo);
    
    /**
     * 取得其下区域代码
     * @param areaNo
     * @return
     */
    List<Regional> getSubRegionalByCode(int areaNo);
    
    List<Map<String, Object>> getRegionalArea(int areaNo);
    
    /**
     * 根据地区层级获取地区名称
     * @param areaLevel
     * @return
     */
    List<String> getRegionalNameByAreaLevel(int areaLevel);
    
    /**
     * 根据城市名,电话区号获取城市信息
     * @param cityInfo
     * @return
     */
    Map<String, Object> getRegionalAreaByInfo(String cityInfo);
}
