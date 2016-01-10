package com.zttx.web.search.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.search.module.FacetField;

/**
 * <p>File：FacetUtils.java </p>
 * <p>Title: 统计结果辅助类 </p>
 * <p>Description: FacetUtils </p>
 * <p>Copyright: Copyright (c) 2014 08/25/2015 10:51</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class FacetUtils
{
    private FacetUtils()
    {
    }
    
    /**
     * 根据统计结果返回自定义对象
     * @param result solr facet结果
     *   <p>
     *      如：["丝中娇",99,"华诚",69,"杭州女装919",66,"紫茉莉",61]"。
     *   </p>
     * @return {@link List<FacetField>}
     */
    public static List<FacetField> getFacetFields(String result)
    {
        if (StringUtils.isBlank(result)) return null;
        List<FacetField> fieldList = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"(.*?)\"").matcher(result);
        Matcher matcher2 = Pattern.compile(",(.*?),|,(\\d*)").matcher(result);
        List<String> names = Lists.newArrayList();
        List<String> counts = Lists.newArrayList();
        while (matcher.find())
        {
            names.add(matcher.group(1));
        }
        while (matcher2.find())
        {
            if (matcher2.group(1) != null) counts.add(matcher2.group(1));
            if (matcher2.group(2) != null) counts.add(matcher2.group(2));
        }
        for (int i = 0; i < names.size(); i++)
        {
            fieldList.add(new FacetField(names.get(i), counts.get(i)));
        }
        return fieldList;
    }
}
