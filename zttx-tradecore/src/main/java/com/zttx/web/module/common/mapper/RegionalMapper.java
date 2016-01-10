/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.trade.remoting.model.AreaFilter;
import com.zttx.web.module.common.entity.Regional;

/**
 * 全国区域信息 持久层接口
 * <p>File：RegionalDao.java </p>
 * <p>Title: RegionalDao </p>
 * <p>Description:RegionalDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RegionalMapper extends GenericMapper<Regional>
{
    /**
     *根据区域码获取
     * @param areaNo
     * @return
     */
    String getNameByAreaNo(@Param("areaNo") Integer areaNo);
    
    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    List<Regional> selectRegionalByName(@Param("name") String name);
    
    Regional selectRegionalByCode(Integer code);
    
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
    Regional loadRegionalByAreaNo(@Param("areaNo") Integer areaNo);
    
    /**
     * 通过code 查询其下的区域
     * @return
     */
    List<Regional> getSubRegionalByCode(@Param("beginCode") int beginCode, @Param("endCode") int endCode);
    
    List<Map<String, Object>> selectRegionalArea(@Param("areaNo") int areaNo);
    
    /**
     * 根据城市名,电话区号获取城市信息
     * @param cityInfo
     * @return
     */
    Map<String, Object> getRegionalAreaByInfo(String cityInfo);
    
    /**
     * 根据地区层级获取地区名称
     * @param areaLevel
     * @return
     */
    List<String> getRegionalNameByAreaLevel(@Param("areaLevel") int areaLevel);
}
