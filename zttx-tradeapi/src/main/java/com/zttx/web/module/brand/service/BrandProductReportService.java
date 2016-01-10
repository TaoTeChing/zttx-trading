package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

/**
 * <p>File：BrandProductReportService.java </p>
 * <p>Title: BrandProductReportService </p>
 * <p>Description: BrandProductReportService </p>
 * <p>Copyright: Copyright (c) 十二月 09，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
public interface BrandProductReportService
{
    /**
     * 映射终端商 以及品牌信息
     * @param brandStorageList
     * @param allDealerInfoMap
     * @param allBrandesInfoMap
     */
    void mapperInfo(List<Map<String, Object>> brandStorageList, Map<String, Object> allDealerInfoMap, Map<String, Object> allBrandesInfoMap);
}
