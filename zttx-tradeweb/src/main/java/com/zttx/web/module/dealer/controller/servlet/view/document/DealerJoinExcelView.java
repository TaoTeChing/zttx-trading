/*
 * @(#)DealerJoinExcelView 2014/4/7 11:04
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller.servlet.view.document;

import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.utils.CalendarUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerJoinExcelView</p>
 * <p>Title: </p>
 * <p>Description: 品牌商管理中心-经销商管理-excel导出</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/7 11:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class DealerJoinExcelView extends AbstractExcelView
{
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        BrandJoinFilter filter = (BrandJoinFilter) model.get("filter");
        List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("list");
        if (filter.getState().equals(DealerConstant.DealerJoin.TERMINATED))
        {
            this.exprotTeminatedLists(workbook, list);
        }
        else
        {
            this.exportCoperatedLists(workbook, list);
        }
    }
    
    /**
     * 导出已合作的经销商列表
     * @param workbook
     * @param list
     */
    @SuppressWarnings("unchecked")
    private void exportCoperatedLists(HSSFWorkbook workbook, List<Map<String, Object>> list)
    {
        try {
            int rowCount = 0;
            Sheet sheet = workbook.createSheet("已合作的经销商");
            Row titleRow = sheet.createRow(rowCount++);
            titleRow.createCell(0).setCellValue("经销商名称");
            titleRow.createCell(1).setCellValue("授权时间");
            titleRow.createCell(2).setCellValue("经销商授权等级");
            titleRow.createCell(3).setCellValue("品牌名称");
            titleRow.createCell(4).setCellValue("已采购金额");
            for (Map<String, Object> map : list) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue((String) map.get("dealerName"));
                row.createCell(1).setCellValue(CalendarUtils.getTimeFromLong((Long) map.get("startTime"), ApplicationConst.DATE_FORMAT_YMD));
                row.createCell(2).setCellValue((String) map.get("levelName"));
                row.createCell(3).setCellValue((String) map.get("brandsName"));
                row.createCell(4).setCellValue(StringUtils.getStringNum(String.valueOf((BigDecimal) map.get("orderMoney"))));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * 导出已终止合作的经销商列表
     * @param workbook
     * @param list
     */
    @SuppressWarnings("unchecked")
    private void exprotTeminatedLists(HSSFWorkbook workbook, List<Map<String, Object>> list)
    {
        int rowCount = 0;
        Sheet sheet = workbook.createSheet("已终止合作的经销商");
        Row titleRow = sheet.createRow(rowCount++);
        titleRow.createCell(0).setCellValue("经销商名称");
        titleRow.createCell(1).setCellValue("终止时间");
        titleRow.createCell(2).setCellValue("经销商授权等级");
        titleRow.createCell(3).setCellValue("品牌名称");
        titleRow.createCell(4).setCellValue("已采购金额");
        for (Map<String, Object> map : list)
        {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue((String) map.get("dealerName"));
            row.createCell(1).setCellValue(CalendarUtils.getTimeFromLong((Long) map.get("endTime"), ApplicationConst.DATE_FORMAT_YMD));
            row.createCell(2).setCellValue((String) map.get("levelName"));
            row.createCell(3).setCellValue((String) map.get("brandsName"));
            row.createCell(4).setCellValue(StringUtils.getStringNum(String.valueOf((Long) map.get("orderMoney"))));
        }
    }
}
