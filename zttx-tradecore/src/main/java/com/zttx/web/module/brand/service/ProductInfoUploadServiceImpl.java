/*
 * @(#)ProductInfoUploadServiceImpl.java 2015-2-6 下午3:59:03
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.goods.module.entity.DealDic;
import com.zttx.goods.module.entity.WebUnit;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.dubbo.service.DealDicServiceDubboConsumer;
import com.zttx.web.dubbo.service.WebUnitServiceDubboConsumer;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandesInfoModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.model.ProductInfoUploadDataModel;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.service.ProductCatalogService;
import com.zttx.web.utils.FileUtils;
import com.zttx.web.utils.MultiPartUtils;
import com.zttx.web.utils.ReadExcelUtils;
import com.zttx.web.utils.entity.ExcelCellValue;

/**
 * <p>File：ProductInfoUploadServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-6 下午3:59:03</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
@Service
public class ProductInfoUploadServiceImpl implements ProductInfoUploadService
{
    private static final Logger         logger = LoggerFactory.getLogger(ProductInfoUploadServiceImpl.class);
    
    @Autowired
    private DealDicServiceDubboConsumer dealDicServiceDubboConsumer;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired
    private BrandFreightTemplateService brandFreightTemplateService;
    
    @Autowired
    private WebUnitServiceDubboConsumer webUnitServiceDubboConsumer;
    
    @Autowired
    private ProductCatalogService       productCatalogService;
    
    @Override
    public ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId) throws BusinessException
    {
        return getProductInfoUploadDataModel(brandId, null);
    }
    
    @Override
    public ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId, String brandsId) throws BusinessException
    {
        return getProductInfoUploadDataModel(brandId, brandsId, null);
    }
    
    @Override
    public ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId, String brandsId, String brandPid) throws BusinessException
    {
        ProductInfoUploadDataModel model = new ProductInfoUploadDataModel();
        // 类目
        List<DealDic> topDealDicList = dealDicServiceDubboConsumer.findLeversCascade();
        for (DealDic topDealDic : topDealDicList)
        {
            List<DealDic> childDealDicList = topDealDic.getChildren();
            model.getChildDealDicListMap().put(topDealDic.getDealNo().intValue(), childDealDicList);
        }
        model.setTopDealDicList(topDealDicList);
        if (StringUtils.isBlank(brandsId))
        {
            // 品牌
            List<BrandsInfoModel> brandesInfoList = brandesInfoService.list(brandId, BrandConst.BRAND_STATE_JOIN.code.shortValue());
            if (brandPid != null && brandesInfoList.size() == 0)
            {
                brandesInfoList = brandesInfoService.list(brandPid, BrandConst.BRAND_STATE_JOIN.code.shortValue());
            }
            for (BrandesInfo brandesInfo : brandesInfoList)
            {
                // 产品分类
                List<ProductCatalog> productCatalogList = productCatalogService.getCatalogList(brandId, brandesInfo.getRefrenceId());
                if (brandPid != null && productCatalogList.size() == 0)
                {
                    productCatalogList = productCatalogService.getCatalogList(brandPid, brandesInfo.getRefrenceId());
                }
                model.getProductCatalogListMap().put(brandesInfo.getRefrenceId(), productCatalogList);
            }
            List<BrandesInfoModel> infoModelList = new ArrayList<BrandesInfoModel>();
            for (int i = 0; i < brandesInfoList.size(); i++)
            {
                BrandesInfoModel modelInfo = new BrandesInfoModel();
                copyProperties(modelInfo, brandesInfoList.get(i));
                infoModelList.add(modelInfo);
            }
            model.setBrandesInfoList(infoModelList);
        }
        else
        {
            // 品牌
            BrandesInfo brandesInfo = brandesInfoService.findByBrandIdAndBrandsId(brandId, brandsId);
            BrandesInfoModel brandsModel = new BrandesInfoModel();
            copyProperties(brandsModel, brandesInfo);
            model.setBrandesInfoList(Lists.newArrayList(brandsModel));
            // 产品分类
            List<ProductCatalog> productCatalogList = productCatalogService.getCatalogList(brandId, brandesInfo.getRefrenceId());
            if (brandPid != null && productCatalogList.size() == 0)
            {
                productCatalogList = productCatalogService.getCatalogList(brandPid, brandesInfo.getRefrenceId());
            }
            model.getProductCatalogListMap().put(brandesInfo.getRefrenceId(), productCatalogList);
        }
        // 运费模板
        List<BrandFreightTemplate> brandFreightTemplateList = brandFreightTemplateService.listTemplate(brandId);
        if (brandPid != null && brandFreightTemplateList.size() == 0)
        {
            brandFreightTemplateList = brandFreightTemplateService.listTemplate(brandPid);
        }
        model.setBrandFreightTemplateList(brandFreightTemplateList);
        // 单位
        List<WebUnit> webUnitList = webUnitServiceDubboConsumer.findAll();
        model.setWebUnitList(webUnitList);
        return model;
    }
    
    private void copyProperties(BrandesInfoModel brandesModel, BrandesInfo brandesInfo)
    {
        brandesModel.setBarCodeNum(brandesInfo.getBarCodeNum());
        brandesModel.setBeginTime(brandesInfo.getBeginTime());
        brandesModel.setBrandHold(brandesInfo.getBrandHold());
        brandesModel.setBrandId(brandesInfo.getBrandId());
        brandesModel.setBrandLogo(brandesInfo.getBrandLogo());
        brandesModel.setBrandMark(brandesInfo.getBrandMark());
        brandesModel.setBrandName(brandesInfo.getBrandsName());
        brandesModel.setBrandsName(brandesInfo.getBrandsName());
        brandesModel.setBrandSubMark(brandesInfo.getBrandSubMark());
        brandesModel.setBrandType(brandesInfo.getBrandType());
        brandesModel.setCreateIp(brandesInfo.getCreateIp());
        brandesModel.setCreateTime(brandesInfo.getCreateTime());
        brandesModel.setDelFlag(brandesInfo.getDelFlag());
        brandesModel.setDeposit(brandesInfo.getDeposit());
        brandesModel.setEndTime(brandesInfo.getEndTime());
        brandesModel.setEnsureMoney(brandesInfo.getEnsureMoney());
        brandesModel.setFactoryStore(brandesInfo.getFactoryStore());
        brandesModel.setHoldName(brandesInfo.getHoldName());
        brandesModel.setLogoDomin(brandesInfo.getLogoDomin());
        brandesModel.setProLogo(brandesInfo.getProLogo());
        brandesModel.setRecommendImage(brandesInfo.getRecommendImage());
        brandesModel.setRefrenceId(brandesInfo.getRefrenceId());
        brandesModel.setShowed(brandesInfo.getShowed());
        brandesModel.setUpdateTime(brandesInfo.getUpdateTime());
        brandesModel.setUserAuth(brandesInfo.getUserAuth());
    }
    
    public String createExcelFile(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
            List<Map<String, ExcelCellValue>> productSaleAttrDataList, Boolean showError, String fileName) throws BusinessException
    {
        List<String> productInfoHead;
        Integer offsetCol;
        if (showError)
        {
            offsetCol = 1;
            productInfoHead = Lists.newArrayList(BrandConstant.ProductInfoUpload.HEAD_ERROR);
            productInfoHead.addAll(BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD);
        }
        else
        {
            offsetCol = 0;
            productInfoHead = BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD;
        }
        String webUrl = MultiPartUtils.createWebUrl(request, MultiPartUtils.TEMP, SerialnoUtils.buildPrimaryKey() + "/" + fileName + ".xls");
        Workbook workbook = getWorkbook();
        CellStyle requiredHeadCellStyle = getRequiredHeadCellStyle(workbook);
        Sheet productInfoSheet = ReadExcelUtils.createWorkbook(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO, productInfoHead, productInfoDataList, BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_TEXT);
        ReadExcelUtils.setRequiredHeadColor(productInfoSheet, productInfoHead, BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_REQUIRED_HEAD, requiredHeadCellStyle);
        Sheet productSaleAttrSheet = ReadExcelUtils.createWorkbook(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_SALE_ATTR,
                BrandConstant.ProductInfoUpload.LIST_PRODUCT_SALE_ATTR_HEAD, productSaleAttrDataList, BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_TEXT);
        ReadExcelUtils.setRequiredHeadColor(productSaleAttrSheet, BrandConstant.ProductInfoUpload.LIST_PRODUCT_SALE_ATTR_HEAD,
                BrandConstant.ProductInfoUpload.LIST_PRODUCT_SALE_ATTR_REQUIRED_HEAD, requiredHeadCellStyle);
        createDataSheet(workbook, dataModel, productInfoSheet, productSaleAttrSheet, offsetCol);
        workbook.setActiveSheet(workbook.getSheetIndex(productInfoSheet));
        webUrl = ReadExcelUtils.save(workbook, request, webUrl);
        return webUrl;
    }

    public void createDataSheet(Workbook workbook, ProductInfoUploadDataModel dataModel, Sheet productInfoSheet, Sheet productSaleAttrSheet, Integer offsetCol)
            throws BusinessException
    {
        Sheet sheet = ReadExcelUtils.createSheet(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_DATA);
        Integer rowIndex = 0;
        // 获取所需数据
        List<DealDic> topDealDicList = dataModel.getTopDealDicList();
        List<BrandesInfoModel> brandesInfoList = dataModel.getBrandesInfoList();
        // 一层类目
        List<String> topDealDicNameList = dataModel.getTopDealDicNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_TOP_DEAL_DIC, topDealDicNameList);
        // 二层类目
        for (DealDic topDealDic : topDealDicList)
        {
            List<String> childDealDicNameList = dataModel.getChildDealDicNameList(topDealDic);
            ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_CHILD_DEAL_DIC + topDealDic.getDealName(), childDealDicNameList);
        }
        // 品牌
        List<String> brandesInfoNameList = dataModel.getBrandesInfoNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRANDES_INFO, brandesInfoNameList);
        // 产品分类
        for (BrandesInfoModel brandesInfo : brandesInfoList)
        {
            List<String> productCatalogNameList = dataModel.getProductCatalogNameList(brandesInfo);
            ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_PRODUCT_CATALOG + brandesInfo.getBrandName(),
                    productCatalogNameList);
        }
        // 运费模板
        List<String> brandFreightTemplateNameList = dataModel.getBrandFreightTemplateNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRAND_FREIGHT_TEMPLATE, brandFreightTemplateNameList);
        // 单位
        List<String> webUnitNameList = dataModel.getWebUnitNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_WEB_UNIT, webUnitNameList);
        // 运费物流
        List<String> productCarryList = dataModel.getProductCarryList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_YFWL, productCarryList);
        /*
         * // 拿样
         * List<String> nyList = dataModel.getIsSampleList();
         * ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_NY, nyList);
         */
        // 前台可见
        List<String> qtkjList = dataModel.getIsShowList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_QTKJ, qtkjList);
        // 添加下拉框
        // 一层类目
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_TOP_DEAL_DIC, 1, offsetCol);
        // 二层类目
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_CHILD_DEAL_DIC, String.valueOf((char) (65 + offsetCol)),
                1, offsetCol + 1);
        // 品牌
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRANDES_INFO, 1, offsetCol + 2);
        // 单位
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_WEB_UNIT, 1, offsetCol + 5);
        // 产品分类
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_PRODUCT_CATALOG, String.valueOf((char) (67 + offsetCol)),
                1, offsetCol + 10);
        // 产品线
        // ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_PRODUCT_LINE, String.valueOf((char) (67 + offsetCol)),
        // 1, offsetCol + 14);
        // 运费物流
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_YFWL, 1, offsetCol + 12);
        // 运费模板
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRAND_FREIGHT_TEMPLATE, 1, offsetCol + 13);
        /*
         * // 拿样
         * ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_NY, 1, offsetCol + 18);
         */
        // 前台可见
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_QTKJ, 1, offsetCol + 15);
        workbook.setSheetHidden(workbook.getSheetIndex(sheet), true);
    }

    //生成模板3数据约束
    public void createDataSheet4TemplateThree(Workbook workbook, ProductInfoUploadDataModel dataModel, Sheet productInfoSheet, Sheet productSaleAttrSheet, Integer offsetCol)
            throws BusinessException {
        Sheet sheet = ReadExcelUtils.createSheet(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_DATA);
        Integer rowIndex = 0;
        // 获取所需数据
        List<DealDic> topDealDicList = dataModel.getTopDealDicList();
        List<BrandesInfoModel> brandesInfoList = dataModel.getBrandesInfoList();
        // 一层类目
        List<String> topDealDicNameList = dataModel.getTopDealDicNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_TOP_DEAL_DIC, topDealDicNameList);
        // 二层类目
        for (DealDic topDealDic : topDealDicList) {
            List<String> childDealDicNameList = dataModel.getChildDealDicNameList(topDealDic);
            ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_CHILD_DEAL_DIC + topDealDic.getDealName(), childDealDicNameList);
        }
        // 品牌
        List<String> brandesInfoNameList = dataModel.getBrandesInfoNameList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRANDES_INFO, brandesInfoNameList);

        // 运费物流
        List<String> productCarryList = dataModel.getProductCarryList();
        ReadExcelUtils.createExcelName(sheet, rowIndex++, BrandConstant.ProductInfoUpload.EXCEL_NAME_YFWL, productCarryList);

        // 添加下拉框
        // 一层类目
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_TOP_DEAL_DIC, 1, offsetCol);
        // 二层类目
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_CHILD_DEAL_DIC, String.valueOf((char) (65 + offsetCol)),
                1, offsetCol + 1);
        // 品牌
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_BRANDES_INFO, 1, offsetCol + 2);

        // 运费物流
        ReadExcelUtils.createColSelectDataValidation(productInfoSheet, BrandConstant.ProductInfoUpload.EXCEL_NAME_YFWL, 1, offsetCol + 4);
        workbook.setSheetHidden(workbook.getSheetIndex(sheet), true);
    }

    public List<Map<String, ExcelCellValue>> getPsaDataList(Integer index, List<Map<String, ExcelCellValue>> allDataList) throws BusinessException
    {
        List<Map<String, ExcelCellValue>> psaDataList = Lists.newArrayList();
        for (Map<String, ExcelCellValue> psaData : allDataList)
        {
            Integer cpxh = ReadExcelUtils.getIntegerValue(psaData, BrandConstant.ProductInfoUpload.HEAD_CPXH, true);
            if (cpxh.equals(index))
            {
                psaDataList.add(psaData);
            }
        }
        return psaDataList;
    }
    
    public void setErrorInfo(Map<String, ExcelCellValue> piData, List<Map<String, ExcelCellValue>> psaDataList, Integer errorIndex, String errorMessage)
    {
        ReadExcelUtils.setStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_ERROR, errorMessage);
        if (null != psaDataList)
        {
            for (Map<String, ExcelCellValue> psaData : psaDataList)
            {
                ReadExcelUtils.setIntegerValue(psaData, BrandConstant.ProductInfoUpload.HEAD_CPXH, errorIndex);
            }
        }
    }
    
    @Override
    public Map<String, ExcelCellValue> getPsaDataByYs(List<Map<String, ExcelCellValue>> psaDataList, String ys) throws BusinessException
    {
        for (Map<String, ExcelCellValue> psaData : psaDataList)
        {
            String tempYs = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_YS, true);
            if (tempYs.equals(ys)) { return psaData; }
        }
        return null;
    }
    
    @Override
    public Map<String, ExcelCellValue> getPsaDataByCm(List<Map<String, ExcelCellValue>> psaDataList, String cm) throws BusinessException
    {
        for (Map<String, ExcelCellValue> psaData : psaDataList)
        {
            String tempCm = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_CM, true);
            if (tempCm.equals(cm)) { return psaData; }
        }
        return null;
    }
    
    @Override
    public Map<String, ExcelCellValue> getPsaData(List<Map<String, ExcelCellValue>> psaDataList, String ys, String cm) throws BusinessException
    {
        for (Map<String, ExcelCellValue> psaData : psaDataList)
        {
            String tempYs = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_YS, true);
            String tempCm = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_CM, true);
            if (tempYs.equals(ys) && tempCm.equals(cm)) { return psaData; }
        }
        return null;
    }
    
    private CellStyle getRequiredHeadCellStyle(Workbook workbook)
    {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        cellStyle.setFont(font);
        return cellStyle;
    }
    
    @Override
    public String createExcelFileTwo(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
            Boolean showError, String fileName) throws BusinessException
    {
        List<String> productInfoHead;
        Integer offsetCol;
        if (showError)
        {
            offsetCol = 1;
            productInfoHead = Lists.newArrayList(BrandConstant.ProductInfoUpload.HEAD_ERROR);
            productInfoHead.addAll(BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD1);
        }
        else
        {
            offsetCol = 0;
            productInfoHead = BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD1;
        }
        String webUrl = MultiPartUtils.createWebUrl(request, MultiPartUtils.TEMP, SerialnoUtils.buildPrimaryKey() + "/" + fileName + ".xls");
        Workbook workbook = getWorkbook();
        CellStyle requiredHeadCellStyle = getRequiredHeadCellStyle(workbook);
        Sheet productInfoSheet = ReadExcelUtils.createWorkbook(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO, productInfoHead, productInfoDataList,
                BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_TEXT);
        ReadExcelUtils.setRequiredHeadColor(productInfoSheet, productInfoHead, BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_REQUIRED_HEAD1, requiredHeadCellStyle);
        createDataSheet(workbook, dataModel, productInfoSheet, null, offsetCol);
        workbook.setActiveSheet(workbook.getSheetIndex(productInfoSheet));
        // ReadExcelUtils.save(workbook, request, webUrl);
        webUrl = ReadExcelUtils.save(workbook, request, webUrl);
        return webUrl;
    }

    @Override
    public String createExcelFileThree(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
                                       Boolean showError, String fileName) throws BusinessException {
        List<String> productInfoHead;
        Integer offsetCol;
        if (showError) {
            offsetCol = 1;
            productInfoHead = Lists.newArrayList(BrandConstant.ProductInfoUpload.HEAD_ERROR);
            productInfoHead.addAll(BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_THREE);
        } else {
            offsetCol = 0;
            productInfoHead = BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_THREE;
        }
        String webUrl = MultiPartUtils.createWebUrl(request, MultiPartUtils.TEMP, SerialnoUtils.buildPrimaryKey() + "/" + fileName + ".xls");
        Workbook workbook = getWorkbook3Template3();
        CellStyle requiredHeadCellStyle = getRequiredHeadCellStyle(workbook);
        Sheet productInfoSheet = ReadExcelUtils.createWorkbook(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO_SIMPLE, productInfoHead, productInfoDataList,
                BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_HEAD_TEXT);
        ReadExcelUtils.setRequiredHeadColor(productInfoSheet, productInfoHead, BrandConstant.ProductInfoUpload.LIST_PRODUCT_INFO_REQUIRED_HEAD_THREE, requiredHeadCellStyle);
        createDataSheet4TemplateThree(workbook, dataModel, productInfoSheet, null, offsetCol);
        workbook.setActiveSheet(workbook.getSheetIndex(productInfoSheet));
        webUrl = ReadExcelUtils.save(workbook, request, webUrl);
        return webUrl;
    }

    @Override
    public Integer getType(Workbook workbook) throws BusinessException
    {
        Integer type = BrandConstant.ProductInfoUpload.IMPORT_TYPE_ONE;
        if(workbook.getSheet(BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO_SIMPLE)!=null){
            type = BrandConstant.ProductInfoUpload.IMPORT_TYPE_THREE;
        }
        else if (null == workbook.getSheet(BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_SALE_ATTR))
        {
            type = BrandConstant.ProductInfoUpload.IMPORT_TYPE_TWO;
        }
        return type;
    }
    
    /**
     * 获取Excel模板
     * @author 张昌苗
     */
    private Workbook getWorkbook() throws BusinessException
    {
        File file = new File(FileUtils.getWebappPath() + "/WEB-INF/excel/productUpload.xls");
        System.out.println(file.getAbsolutePath() + " - " + file.exists());
        Workbook workbook = ReadExcelUtils.getWorkbook(file);
        return workbook;
    }
    
    /**
     * 获取Excel模板
     *
     * @author 张昌苗
     */
    private Workbook getWorkbook3Template3() throws BusinessException
    {
        File file = new File(FileUtils.getWebappPath() + "/WEB-INF/excel/productUpload_template3.xls");
        System.out.println(file.getAbsolutePath() + " - " + file.exists());
        Workbook workbook = ReadExcelUtils.getWorkbook(file);
        return workbook;
    }
}
