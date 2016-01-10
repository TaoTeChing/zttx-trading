/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.trade.remoting.model.AreaFilter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.google.common.collect.Maps;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.mapper.RegionalMapper;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.PinyinUtils;

/**
 * 全国区域信息 服务实现类
 * <p>File：Regional.java </p>
 * <p>Title: Regional </p>
 * <p>Description:Regional </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class RegionalServiceImpl extends GenericServiceApiImpl<Regional> implements RegionalService
{
    public static final String REDIS_REGIONAL = "redis-trade-regional";
    
    private RegionalMapper     regionalMapper;
    
    @Autowired(required = true)
    public RegionalServiceImpl(RegionalMapper regionalMapper)
    {
        super(regionalMapper);
        this.regionalMapper = regionalMapper;
    }
    
    @Override
    public String getFullNameByAreaNo(Integer areaNo, String split_code)
    {
        List<Regional> cacheList = regionalMapper.selectAll();
        Regional currentRegion = getAreaCode(areaNo, cacheList);
        StringBuilder fullName = new StringBuilder();
        if (null != currentRegion)
        {
            if (currentRegion.getAreaLevel().intValue() == 1)
            {
                fullName.append(currentRegion.getAreaName());
            }
            if (currentRegion.getAreaLevel().intValue() == 2)
            {
                Integer code = getFatherCode(currentRegion.getAreaNo(), 2);
                String name1 = getAreaCode(code, cacheList).getAreaName();
                fullName.append(name1).append(split_code).append(currentRegion.getAreaName());
            }
            if (currentRegion.getAreaLevel().intValue() == 3)
            {
                Integer code = getFatherCode(currentRegion.getAreaNo(), 3);
                Regional obj = getAreaCode(code, cacheList);// 市级
                String name2 = obj.getAreaName();
                Integer code1 = getFatherCode(obj.getAreaNo(), 2);
                Regional obj1 = getAreaCode(code1, cacheList);// 省级
                String name1 = obj1.getAreaName();
                fullName.append(name1).append(split_code).append(name2).append(split_code).append(currentRegion.getAreaName());
            }
        }
        return fullName.toString();
    }
    
    @Override
    public String getFullNameByAreaNoAndLevel(Integer areaNo, Integer level, String split_code)
    {
        String splitCode = REGIONAL_SPLIT_CODE;
        List<Regional> cacheList = regionalMapper.selectAll();
        Regional currentRegion = getAreaCode(areaNo, cacheList);
        StringBuilder fullName = new StringBuilder();
        if (null != currentRegion && null != level)
        {
            if (split_code != null)
            {
                splitCode = split_code;
            }
            if (currentRegion.getAreaLevel().intValue() == 1)
            {
                fullName.append(currentRegion.getAreaName());
            }
            if (currentRegion.getAreaLevel() == 2)
            {
                Integer code = getFatherCode(currentRegion.getAreaNo(), 2);
                String name1 = getAreaCode(code, cacheList).getAreaName();
                fullName.append(name1).append(splitCode).append(currentRegion.getAreaName());
                if (level == 1)
                {
                    String tempStr = fullName.toString();
                    String[] arr = tempStr.split(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(arr[0]);
                }
            }
            if (currentRegion.getAreaLevel().intValue() == 3)
            {
                Integer code = getFatherCode(currentRegion.getAreaNo(), 3);
                Regional obj = getAreaCode(code, cacheList);// 市级
                String name2 = obj.getAreaName();
                Integer code1 = getFatherCode(obj.getAreaNo(), 2);
                Regional obj1 = getAreaCode(code1, cacheList);// 省级
                String name1 = obj1.getAreaName();
                fullName.append(name1).append(splitCode).append(name2).append(splitCode).append(currentRegion.getAreaName());
                if (level == 2)
                {
                    String tempStr = fullName.toString();
                    int pos = tempStr.lastIndexOf(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(tempStr.substring(0, pos));
                }
                if (level == 1)
                {
                    String tempStr = fullName.toString();
                    String[] arr = tempStr.split(splitCode);
                    fullName = new StringBuilder();
                    fullName.append(arr[0]);
                }
            }
        }
        return fullName.toString();
    }
    
    /**
     * 根据区域code 和级别 获取父级编码
     *
     * @param areaNo
     * @param level
     * @return Integer
     * @author 罗盛平
     */
    @Override
    public Integer getFatherCode(Integer areaNo, Integer level)
    {
        if (null != areaNo)
        {
            StringBuilder sb = new StringBuilder();
            String code = areaNo.toString();
            int len = code.length() - 4;
            String suffix = "0000";
            if (level == 3)
            {
                suffix = "00";
                len = code.length() - 2;
            }
            Integer codeNum = Integer.parseInt(code.substring(0, len) + suffix);
            sb.append(codeNum);
            return Integer.parseInt(sb.toString());
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 根据编码获取区域
     *
     * @param code
     * @return
     * @author 罗盛平
     */
    @Override
    public Regional getAreaCode(Integer code, List<Regional> cacheList)
    {
        Regional regional = null;
        if (null != cacheList && !cacheList.isEmpty())
        {
            regional = new Regional();
            for (Regional regi : cacheList)
            {
                if (regi.getAreaNo().equals(code))
                {
                    regional = regi;
                    break;
                }
            }
        }
        return regional;
    }
    
    @Override
    public List<Regional> searchByAreaFilter(AreaFilter areaFilter)
    {
        return regionalMapper.searchByAreaFilter(areaFilter);
    }
    
    @Override
    public Regional loadRegionalByAreaNo(Integer areaNo)
    {
        return regionalMapper.loadRegionalByAreaNo(areaNo);
    }
    
    /**
     * 通过名字查询地区
     *
     * @param city
     * @return
     */
    @Override
    public Regional selectRegionalByName(String city)
    {
        Regional regional = new Regional();
        List<Regional> list = regionalMapper.findList(regional);
        regional.setAreaName(city);
        return list.isEmpty() ? null : list.get(0);
    }
    
    @Override
    public Map<String, Object> getRegionalMap()
    {
        List<Regional> regionalList = getRegionals();
        Map<String, Object> regionalMap = Maps.newLinkedHashMap();
        if (null != regionalList && !regionalList.isEmpty())
        {
            Map<String, Object> provinceMap = null;
            String cityKey = "";
            StringBuilder citySb = new StringBuilder();
            StringBuilder keySb = new StringBuilder();
            for (Regional item : regionalList)
            {
                keySb.setLength(0);
                keySb.append(item.getAreaName()).append(" ").append(item.getAreaNo());
                if (1 == item.getAreaLevel())
                {
                    provinceMap = Maps.newHashMap();
                    regionalMap.put(keySb.toString(), provinceMap);
                }
                else if (2 == item.getAreaLevel())
                {
                    cityKey = keySb.toString();
                    provinceMap.put(cityKey, "");
                }
                else
                {
                    citySb.setLength(0);
                    citySb.append(provinceMap.get(cityKey));
                    if (!StringUtils.isBlank(citySb))
                    {
                        citySb.append(",");
                    }
                    citySb.append(keySb);
                    provinceMap.put(cityKey, citySb.toString());
                }
            }
        }
        return regionalMap;
    }
    
    private List<Regional> getRegionals()
    {
        // todo 缓存
        return regionalMapper.selectAll();
    }
    
    @Override
    public Regional getRegionalByName(String name)
    {
        List<Regional> list = regionalMapper.selectRegionalByName(name);
        return list.isEmpty() ? null : list.get(0);
    }
    
    @Override
    public String getNameByAreaNo(Integer areaNo)
    {
        return regionalMapper.getNameByAreaNo(areaNo);
    }
    
    @Override
    public Regional getRegionalByRequest(HttpServletRequest request)
    {
        String ip = IPUtil.getOriginalIpAddr(request);
        Regional regional = null;
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            byte[] hkey = JedisUtils.getBytesKey(REDIS_REGIONAL);
            byte[] key = JedisUtils.getBytesKey(ip);
            Object object = JedisUtils.toObject(jedis.hget(hkey, key));
            if (object != null) { return (Regional) object; }
            String city = new IPUtil().search4city(ip);
            if (StringUtils.isNotBlank(city))
            {
                List<Regional> list = regionalMapper.selectRegionalByName(city);
                regional = list.isEmpty() ? null : list.get(0);
                jedis.hset(hkey, key, JedisUtils.toBytes(regional));
            }
        }
        catch (Exception e)
        {
            // e.printStackTrace();无需打印日志
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return regional;
    }
    
    @Override
    public Regional searchByCode(Integer code)
    {
        Regional regional = regionalMapper.selectRegionalByCode(code);
        return regional;
    }
    
    @Override
    public String getFullAreaName(Integer areaNo)
    {
        String province = "";
        String city = "";
        String area = "";
        if (areaNo % 100 != 0)
        {
            area = regionalMapper.getNameByAreaNo(areaNo);
        }
        if (areaNo % 10000 != 0)
        {
            city = regionalMapper.getNameByAreaNo(areaNo / 100 * 100);
        }
        if (areaNo % 1000000 != 0)
        {
            province = regionalMapper.getNameByAreaNo(areaNo / 10000 * 10000);
        }
        return province.concat(city).concat(area);
    }
    
    @Override
    public Regional load(Integer areaNo)
    {
        Regional regional = null;
        List<Regional> list = this.selectAll();
        if (null != list && !list.isEmpty())
        {
            for (Iterator<Regional> it = list.iterator(); it.hasNext();)
            {
                Regional lRegional = it.next();
                if (areaNo == lRegional.getAreaNo())
                {
                    regional = lRegional;
                    break;
                }
            }
        }
        return regional;
    }
    
    @Override
    public List<Regional> getSubRegionalByCode(int areaNo)
    {
        int beginCode = areaNo;
        int endCode = areaNo;
        if (areaNo % 100 == 0) endCode += 100;
        if (areaNo % 10000 == 0) endCode += 10000;
        return regionalMapper.getSubRegionalByCode(beginCode, endCode);
    }
    
    @Override
    public List<Map<String, Object>> getRegionalArea(int areaNo)
    {
        return regionalMapper.selectRegionalArea(areaNo);
    }
    
    /**
     * 根据地区层级获取地区名称
     * @param areaLevel
     * @return
     */
    @Override
    public List<String> getRegionalNameByAreaLevel(int areaLevel)
    {
        return regionalMapper.getRegionalNameByAreaLevel(areaLevel);
    }
    
    @Override
    public Map<String, Object> getRegionalAreaByInfo(String cityInfo)
    {
        Map<String, Object> result = regionalMapper.getRegionalAreaByInfo(cityInfo);
        if (MapUtils.isNotEmpty(result)) return result;
        else
        // 拼音查询
        {
            cityInfo = getRegionalNameByPinyin(cityInfo);
            if (null == cityInfo) return null;
            else return regionalMapper.getRegionalAreaByInfo(cityInfo);
        }
    }
    
    private String getRegionalNameByPinyin(String cityInfo)
    {
        String key = PinyinUtils.REGIONAL_PINYIN_KEY + cityInfo;
        return JedisUtils.get(key);
    }
}
