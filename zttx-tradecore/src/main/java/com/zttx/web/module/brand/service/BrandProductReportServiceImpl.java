package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import com.zttx.web.utils.ListUtils;

/**
 * <p>File：BrandProductReportServiceImpl.java </p>
 * <p>Title: BrandProductReportServiceImpl </p>
 * <p>Description: BrandProductReportServiceImpl </p>
 * <p>Copyright: Copyright (c) 十二月 09，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Service
public class BrandProductReportServiceImpl implements BrandProductReportService
{
    @Override
    public void mapperInfo(List<Map<String, Object>> brandStorageList, Map<String, Object> allDealerInfoMap, Map<String, Object> allBrandesInfoMap)
    {
        if (ListUtils.isNotEmpty(brandStorageList))
        {
            for (Map<String, Object> brandStorageInfo : brandStorageList)
            {
                brandStorageInfo.put("brandName",
                        MapUtils.getString(allBrandesInfoMap, MapUtils.getString(brandStorageInfo, "brandsId"), MapUtils.getString(brandStorageInfo, "brandName", "")));
                brandStorageInfo.put("dealerName",
                        MapUtils.getString(allDealerInfoMap, MapUtils.getString(brandStorageInfo, "zttxDealerId"), MapUtils.getString(brandStorageInfo, "dealerName", "")));
            }
        }
    }
}
