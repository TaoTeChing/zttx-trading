package com.zttx.web.module.remoting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.zttx.trade.remoting.api.AreaApi;
import com.zttx.trade.remoting.model.Area;
import com.zttx.trade.remoting.model.AreaFilter;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.mapper.RegionalMapper;
import com.zttx.web.module.common.service.RegionalService;

/**
 * @version 1.0
 * @since 2015 2015/09/15 9:07
 */
public class AreaApiImpl implements AreaApi
{
    private static final int CITY_RATE   = 10000;
    
    private static final int COUNTY_RATE = 100;
    
    private RegionalService  regionalService;
    
    public void setRegionalService(RegionalService regionalService)
    {
        this.regionalService = regionalService;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Area> listAllProvince()
    {
        AreaFilter filter = new AreaFilter();
        filter.setLevel(1);
        List<Regional> list = regionalService.searchByAreaFilter(filter);
        List<Area> areas = null;
        if (CollectionUtils.isEmpty(list)) { return Collections.EMPTY_LIST; }
        areas = Lists.newArrayList();
        for (Regional regional : list)
        {
            areas.add(regionalToArea(regional));
        }
        return areas;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Area> listAllCitysUnderProvince(Integer integer)
    {
        AreaFilter filter = new AreaFilter();
        int code = integer / CITY_RATE;
        filter.setStartNo(code * CITY_RATE);
        filter.setEndNo((code + 1) * CITY_RATE);
        filter.setLevel(2);
        List<Regional> list = regionalService.searchByAreaFilter(filter);
        List<Area> areas = null;
        if (CollectionUtils.isEmpty(list)) { return Collections.EMPTY_LIST; }
        areas = Lists.newArrayList();
        for (Regional regional : list)
        {
            areas.add(regionalToArea(regional));
        }
        return areas;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Area> listAllCountyUnderCity(Integer integer)
    {
        AreaFilter filter = new AreaFilter();
        int code = integer / COUNTY_RATE;
        filter.setStartNo(code * COUNTY_RATE);
        filter.setEndNo((code + 1) * COUNTY_RATE);
        filter.setLevel(3);
        List<Regional> list = regionalService.searchByAreaFilter(filter);
        List<Area> areas = null;
        if (CollectionUtils.isEmpty(list)) { return Collections.EMPTY_LIST; }
        areas = Lists.newArrayList();
        for (Regional regional : list)
        {
            areas.add(regionalToArea(regional));
        }
        return areas;
    }
    
    @Override
    public List<Area> search(AreaFilter areaFilter)
    {
        List<Area> areas = Collections.emptyList();
        List<Regional> list = null;
        if (areaFilter != null)
        {
            list = regionalService.searchByAreaFilter(areaFilter);
            if (list != null)
            {
                areas = Lists.newArrayList();
                for (Regional regional : list)
                {
                    areas.add(regionalToArea(regional));
                }
            }
        }
        return areas;
    }
    
    @Override
    public Area getAreaByAreaNo(Integer integer)
    {
        Regional regional = regionalService.load(integer);
        Area area = new Area();
        if (regional != null)
        {
            area.setAreaNo(regional.getAreaNo());
            area.setAreaName(regional.getAreaName());
            area.setLevel(Integer.parseInt(regional.getAreaLevel().toString()));
        }
        return area;
    }
    
    @Override
    public List<Area> getFullAreaByAreaNo(Integer integer)
    {
        List<Area> areas = new ArrayList<Area>(3);
        Regional regional = regionalService.loadRegionalByAreaNo(integer);
        if (regional != null)
        {
            if (regional.getAreaLevel().intValue() == 3)
            {
                Regional province = regionalService.loadRegionalByAreaNo(integer / 10000 * 10000);
                areas.add(regionalToArea(province));
                Regional city = regionalService.loadRegionalByAreaNo(integer / 100 * 100);
                areas.add(regionalToArea(city));
            }
            if (regional.getAreaLevel().intValue() == 2)
            {
                Regional province = regionalService.loadRegionalByAreaNo(integer / 10000 * 10000);
                areas.add(regionalToArea(province));
            }
            areas.add(regionalToArea(regional));
        }
        return areas;
    }
    
    private static Area regionalToArea(Regional regional)
    {
        Area area = new Area();
        area.setAreaNo(regional.getAreaNo());
        area.setLevel(Integer.parseInt(regional.getAreaLevel().toString()));
        area.setAreaName(regional.getAreaName());
        return area;
    }
}
