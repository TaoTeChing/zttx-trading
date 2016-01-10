package com.zttx.web.module.brand.controller.view;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zttx.web.security.utils.Servlets;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.google.common.collect.Lists;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.StringUtils;

/**
 * <p>File：CommonExcelView.java </p>
 * <p>Title: CommonExcelView </p>
 * <p>Description: 通用的excel视图 </p>
 * <p>Copyright: Copyright (c) 十二月 07，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
public class CommonExcelView extends AbstractExcelView
{
    /**
     * Subclasses must implement this method to create an Excel HSSFWorkbook document,
     * given the model.
     *
     * @param model    the model Map
     *                 _fileName:文件名称  默认当前日期
     *                 _sheetTitle:表名    默认表1
     *                 _cellNames:表头（逗号切割）
     *                 _cellKeys:数据的key 与表头对应
     *                 _dataList:数据列表
     * @param workbook the Excel workbook to complete
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        int rowCount = 0;
        String _sheetTitle = MapUtils.getString(model, "_sheetTitle", "表1");
        Sheet sheet = workbook.createSheet(_sheetTitle);
        Row titleRow = sheet.createRow(rowCount);
        String _cellNames = MapUtils.getString(model, "_cellNames", "");
        setRow(titleRow, StringUtils.split(_cellNames, ","));
        String _fileName = MapUtils.getString(model, "_fileName", "") + CalendarUtils.getCurrentDate("yyyyMMddHHmmss");
        String _cellKeys = MapUtils.getString(model, "_cellKeys", "");
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) MapUtils.getObject(model, "_dataList", Lists.newArrayList());
        for (Map<String, Object> dataMap : dataList)
        {
            setRow(sheet.createRow(++rowCount), StringUtils.split(_cellKeys, ","), dataMap);
        }
        Servlets.setFileDownloadHeader(response,_fileName);
        //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(_fileName, "UTF-8") + ".xls");
    }
    
    private void setRow(Row titleRow, String[] cells)
    {
        for (int i = 0; i < cells.length; i++)
        {
            titleRow.createCell(i).setCellValue(cells[i]);
        }
    }
    
    private void setRow(Row titleRow, String[] keys, Map<String, Object> cellsMap)
    {
        for (int i = 0; i < keys.length; i++)
        {
            titleRow.createCell(i).setCellValue(MapUtils.getString(cellsMap, keys[i], ""));
        }
    }
}
