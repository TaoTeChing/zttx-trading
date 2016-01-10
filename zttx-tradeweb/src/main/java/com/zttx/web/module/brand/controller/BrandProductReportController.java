package com.zttx.web.module.brand.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.erp.module.statement.model.SalesStateMentModel;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.dubbo.service.SellOrderServiceDubboConsumer;
import com.zttx.web.module.brand.controller.view.CommonExcelView;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandProductReportService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.model.TransferSearchModel;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>File：BrandProductReportController.java </p>
 * <p>Title: BrandProductReportController </p>
 * <p>Description: 产品报表控制器 </p>
 * <p>Copyright: Copyright (c) 十二月 07，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/product/report")
public class BrandProductReportController extends GenericBaseController {
    private final static Logger logger = LoggerFactory.getLogger(BrandProductReportController.class);

    // 导出excel的表头
    private static final String CELLNAMES = "货号,产品名称,品牌,所属终端商,库存量";

    // 对应的数据key 与表头一一对应
    private static final String CELLKEYS = "productNo,productName,brandName,dealerName,realStorage";

    // sku为单位的数据表头
    private static final String CELLNAMES_SKU = "货号,产品名称,品牌,颜色/尺码,所属终端商,库存量";

    // sku为单位的数据key
    private static final String CELLKEYS_SKU = "productNo,productName,brandName,colorSize,dealerName,realStorage";

    @Autowired
    private SellOrderServiceDubboConsumer sellOrderServiceDubboConsumer;

    @Autowired
    private BrandesInfoService brandesInfoService;

    @Autowired
    private DealerJoinMapper dealerJoinMapper;

    @Autowired
    private BrandProductReportService brandProductReportService;

    @RequiresPermissions("brand:center")
    @RequestMapping("")
    public String reportFactoryIndex(Model model) throws BusinessException {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String brandStates = BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED + "," + BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED;
        List<BrandesInfo> brandesList = brandesInfoService.listBrandStates(brandId, brandStates);// 合作中 + 过期的品牌
        model.addAttribute("brandesList", brandesList);// 下拉品牌信息
        return "brand/report_factory";
    }

    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public JsonMessage reportFactoryData(SalesStateMentModel searchModel, TransferSearchModel searchBean, Pagination pagination) {
        PaginateResult<Map<String, Object>> brandStorageData;
        try {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            searchModel.setSupplierId(brandId);
            searchBean.setBrandId(brandId);
            List<Map<String, Object>> cooperatedDealerList = dealerJoinMapper.findCooperatedDealer(searchBean);
            searchModel.setZttxDealerIdList(getDealerIds(cooperatedDealerList));
            Map<String, Object> allDealerInfoMap = list2Map(cooperatedDealerList);// 获取所有合作中的终端商信息 用于映射
            String brandStates = BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED + "," + BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED;
            Map<String, Object> allBrandesInfoMap = listBrandesInfo2Map(brandesInfoService.listBrandStates(brandId, brandStates));
            brandStorageData = sellOrderServiceDubboConsumer.getBrandStorageData(searchModel, pagination);
            brandProductReportService.mapperInfo(brandStorageData.getList(), allDealerInfoMap, allBrandesInfoMap);// 映射终端商 以及品牌信息
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, brandStorageData);
    }

    /**
     * 获取经销商id集合
     *
     * @param cooperatedDealerList
     * @return
     */
    private List<String> getDealerIds(List<Map<String, Object>> cooperatedDealerList) {
        List<String> dealerIdList = null;
        if (ListUtils.isNotEmpty(cooperatedDealerList)) {
            dealerIdList = Lists.newArrayList();
            for (Map<String, Object> stringObjectMap : cooperatedDealerList) {
                dealerIdList.add(MapUtils.getString(stringObjectMap, "refrenceId", ""));
            }
        }
        return dealerIdList;
    }

    private Map<String, Object> listBrandesInfo2Map(List<BrandesInfo> brandesInfos) {
        Map<String, Object> resultMap = Maps.newHashMap();
        if (ListUtils.isNotEmpty(brandesInfos)) {
            for (BrandesInfo brandesInfo : brandesInfos) {
                resultMap.put(brandesInfo.getRefrenceId(), brandesInfo.getBrandsName());
            }
        }
        return resultMap;
    }

    private Map<String, Object> list2Map(List<Map<String, Object>> allDealerBaseInfo) {
        Map<String, Object> resultMap = Maps.newHashMap();
        if (ListUtils.isNotEmpty(allDealerBaseInfo)) {
            for (Map<String, Object> map : allDealerBaseInfo) {
                resultMap.put(MapUtils.getString(map, "refrenceId"), MapUtils.getString(map, "dealerName"));
            }
        }
        return resultMap;
    }

    /**
     * 产品库存 Excel 导出
     *
     * @param searchModel
     * @param _isSku
     * @param searchBean
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/quantityExcel")
    public ModelAndView exportExcel(SalesStateMentModel searchModel, Short _isSku, String name,TransferSearchModel searchBean) {
        ModelAndView mav = new ModelAndView(new CommonExcelView());
        try {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            Boolean isSku = (1 == _isSku);
            searchModel.setSupplierId(brandId);
            searchModel.setIsSku(isSku);
            List<Map<String, Object>> cooperatedDealerList = dealerJoinMapper.findCooperatedDealer(searchBean);
            searchModel.setZttxDealerIdList(getDealerIds(cooperatedDealerList));
            Map<String, Object> allDealerInfoMap = list2Map(cooperatedDealerList);// 获取所有合作中的终端商信息 用于映射
            String brandStates = BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED + "," + BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED;
            List<Map<String, Object>> allBrandStorageData = Lists.newArrayList();
            Map<String, Object> allBrandesInfoMap = listBrandesInfo2Map(brandesInfoService.listBrandStates(brandId, brandStates));
            PaginateResult<Map<String, Object>> brandStorageData;
            Integer currentPage = ZttxConst.DEFAULT_CURRENT_PAGE;
            Pagination page = new Pagination(currentPage, ZttxConst.DEFAULT_PAGE_SIZE * 5);// 经erp讨论决定 每次取100条数据
            do {
                brandStorageData = sellOrderServiceDubboConsumer.getBrandStorageData(searchModel, page);
                if (null == brandStorageData) {
                    break;
                }
                if (ListUtils.isNotEmpty(brandStorageData.getList())) {
                    allBrandStorageData.addAll(brandStorageData.getList());
                }
                page.setCurrentPage(++currentPage);
            }
            while (brandStorageData.getPage().getHasNextPage());
            brandProductReportService.mapperInfo(allBrandStorageData, allDealerInfoMap, allBrandesInfoMap);// 映射终端商 以及品牌信息
            mav.addObject("_dataList", allBrandStorageData);
            mav.addObject("_cellNames", isSku ? CELLNAMES_SKU : CELLNAMES);
            mav.addObject("_cellKeys", isSku ? CELLKEYS_SKU : CELLKEYS);
            mav.addObject("_sheetTitle", isSku ? "产品sku库存数据" : "产品库存数据");
            mav.addObject("_fileName", isSku ? "产品sku库存数据" : "产品库存数据");
        } catch (BusinessException e) {
            logger.error(e.getMessage());
            mav.addObject("_cellNames", "异常: ," + e.getErrorCode().getMessage());
        }
        return mav;
    }
}
