/*
 * @(#)ProductInfoExcelView 2014/4/9 11:04
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.zttx.goods.module.dto.Attribute;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ProductConsts;
import com.zttx.web.module.brand.model.PriceModel;
import com.zttx.web.utils.CalendarUtils;

/**
 * <p>File：ProductInfoExcelView</p>
 * <p>Title: </p>
 * <p>Description: 产品管理-产品列表-excel导出</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/9 11:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public class ProductInfoExcelView extends AbstractExcelView
{
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("list");
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        int rowCount = 0;
        Sheet sheet = workbook.createSheet("产品列表");
        Row titleRow = sheet.createRow(rowCount++);
        titleRow.createCell(0).setCellValue("产品名称");
        titleRow.createCell(1).setCellValue("吊牌价");
        titleRow.createCell(2).setCellValue("属性/价格/库存");
        titleRow.createCell(3).setCellValue("总销量");
        titleRow.createCell(4).setCellValue("发布时间");
        for (Map<String, Object> map : list)
        {
            Row row = sheet.createRow(rowCount++);
            StringBuffer name = new StringBuffer("货号：");
            StringBuffer priceAndStore = new StringBuffer();
            name.append(map.get("productNo").toString()).append("\r\n").append(map.get("productTitle").toString());
            if (ProductConsts.CATE_ORDER.getKey().toString().equals(map.get("productCate").toString()))
            {
                name.append("\r\n预定产品");
            }
            List<PriceModel> priceModelList = (List<PriceModel>) map.get("priceModels");
            if (priceModelList != null && !priceModelList.isEmpty())
            {
                for (PriceModel priceModel : priceModelList)
                {
                    for (Attribute attr : priceModel.getZ())
                    {
                        priceAndStore.append(attr.getV()).append(" ");
                    }
                    priceAndStore.append(priceModel.getP()).append("   ").append(priceModel.getS()).append("\r\n");
                }
            }
            Cell c0 = row.createCell(0);
            c0.setCellStyle(cs);
            c0.setCellValue(name.toString());
            row.createCell(1).setCellValue(map.get("productPrice").toString());
            Cell c2 = row.createCell(2);
            c2.setCellStyle(cs);
            c2.setCellValue(priceAndStore.toString());
            Object saleNum = map.get("saleNum");
            row.createCell(3).setCellValue(saleNum == null ? "" : saleNum.toString());
            row.createCell(4).setCellValue(CalendarUtils.getTimeFromLong((Long) map.get("createTime"), ApplicationConst.DATE_FORMAT_YMD));
        }
        response.setHeader("content-disposition", "attachment;filename=productExcel.xls");
    }
}
