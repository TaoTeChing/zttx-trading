/*
 * @(#)ProductInfoUploadService.java 2015-2-6 下午3:58:38
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.ProductInfoUploadDataModel;
import com.zttx.web.utils.entity.ExcelCellValue;

/**
 * <p>File：ProductInfoUploadService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-6 下午3:58:38</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public interface ProductInfoUploadService
{
    /**
     * 获取导入产品时所需的信息
     * @author 张昌苗
     */
    public ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId) throws BusinessException;
    
    /**
     * 获取导入产品时所需的信息
     * @author 张昌苗
     */
    public ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId, String brandsId) throws BusinessException;
    
    /**
     * 获取导入产品时所需的信息
     * @param brandId
     * @param brandsId
     * @param brandPid  父brandId
     * @return
     * @throws BusinessException
     * @author 李星
     */
    ProductInfoUploadDataModel getProductInfoUploadDataModel(String brandId, String brandsId, String brandPid) throws BusinessException;
    
    /**
     * 创建Excel文件，产品信息和颜色尺码信息分开显示
     * @author 张昌苗
     */
    public String createExcelFile(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
            List<Map<String, ExcelCellValue>> productSaleAttrDataList, Boolean showError, String fileName) throws BusinessException;
    
    /**
     * 创建Excel文件，产品信息和颜色尺码信息显示在同一sheet
     * @author 张昌苗
     */
    public String createExcelFileTwo(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
            Boolean showError, String fileName) throws BusinessException;
            
    /**
     * 创建Excel文件,产品信息和颜色尺码信息显示在同一sheet,
     * 模板3是模板2的简化版
     * @author 李星
     */
    String createExcelFileThree(HttpServletRequest request, ProductInfoUploadDataModel dataModel, List<Map<String, ExcelCellValue>> productInfoDataList,
            Boolean showError, String fileName) throws BusinessException;
    
    /**
     * 创建数据表（用于下拉框）
     * @author 张昌苗
     */
    public void createDataSheet(Workbook workbook, ProductInfoUploadDataModel dataModel, Sheet productInfoSheet, Sheet productSaleAttrSheet, Integer offsetCol)
            throws BusinessException;
    
    /**
     * 获取产品对应的颜色尺码信息
     * @author 张昌苗
     */
    public List<Map<String, ExcelCellValue>> getPsaDataList(Integer index, List<Map<String, ExcelCellValue>> allDataList) throws BusinessException;
    
    /**
     * 修改颜色尺码信息中的产品序号和添加错误信息
     * @author 张昌苗
     */
    public void setErrorInfo(Map<String, ExcelCellValue> piData, List<Map<String, ExcelCellValue>> psaDataList, Integer errorIndex, String errorMessage);
    
    /**
     * 获取颜色尺码信息
     * @author 张昌苗
     */
    public Map<String, ExcelCellValue> getPsaDataByYs(List<Map<String, ExcelCellValue>> psaDataList, String ys) throws BusinessException;
    
    /**
     * 获取颜色尺码信息
     * @author 张昌苗
     */
    public Map<String, ExcelCellValue> getPsaDataByCm(List<Map<String, ExcelCellValue>> psaDataList, String cm) throws BusinessException;
    
    /**
     * 获取颜色尺码信息
     * @author 张昌苗
     */
    public Map<String, ExcelCellValue> getPsaData(List<Map<String, ExcelCellValue>> psaDataList, String ys, String cm) throws BusinessException;
    
    /**
     * 获取上传文件的模板类型
     * @author 张昌苗
     */
    public Integer getType(Workbook workbook) throws BusinessException;
}
