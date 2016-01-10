/*
 * @(#)DealerJoinExcelView 2014/4/7 11:04
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller.servlet.view.document;

import com.zttx.sdk.utils.StringUtils;
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
 * <p>Description: 终端商管理中心-加盟管理 > 已加盟品牌库-excel导出</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/7 11:04</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
public class DealerJoinBrandExcelView extends AbstractExcelView
{
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("list");

        this.exportCoperatedLists(workbook, list);
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
            Sheet sheet = workbook.createSheet("已加盟品牌");
            Row titleRow = sheet.createRow(rowCount++);

            int i=0;
            titleRow.createCell(i++).setCellValue("品牌");
            titleRow.createCell(i++).setCellValue("公司名称");
            titleRow.createCell(i++).setCellValue("开始合作时间");
            titleRow.createCell(i++).setCellValue("合作模式");
            titleRow.createCell(i++).setCellValue("授信额度");
            titleRow.createCell(i++).setCellValue("剩余授信");
            titleRow.createCell(i++).setCellValue("押金");
            titleRow.createCell(i++).setCellValue("已缴押金");
            titleRow.createCell(i++).setCellValue("待缴/待退押金");
            titleRow.createCell(i++).setCellValue("进货累计");

            for (Map<String, Object> map : list) {
                Row row = sheet.createRow(rowCount++);
                int j=0;
                row.createCell(j++).setCellValue((String) map.get("brandsName"));
                row.createCell(j++).setCellValue((String) map.get("brandName"));
                row.createCell(j++).setCellValue((String) map.get("startTimeStr"));
                row.createCell(j++).setCellValue((String) map.get("joinFormCn"));
                row.createCell(j++).setCellValue(StringUtils.getStringNum(String.valueOf((BigDecimal) map.get("creditAmount"))));
                row.createCell(j++).setCellValue(StringUtils.getStringNum(String.valueOf(getAvailablePrestige(map))));
                row.createCell(j++).setCellValue(StringUtils.getStringNum(String.valueOf((BigDecimal) map.get("depositTotalAmount"))));
                row.createCell(j++).setCellValue(StringUtils.getStringNum(String.valueOf((BigDecimal) map.get("paidAmount"))));
                //待缴/待退 金额 = 押金金额-已缴金额
                BigDecimal want = this.getWant(map);
                row.createCell(j++).setCellValue(StringUtils.getStringNum(String.valueOf(want)));
                row.createCell(j++).setCellValue(StringUtils.getStringNum((String) map.get("orderTime")));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 计算剩余授信
     * @param map
     * @return
     */
    private BigDecimal getAvailablePrestige(Map<String, Object> map) {
        BigDecimal want = BigDecimal.ZERO;
        if((BigDecimal) map.get("creditAmount")!=null){
            if((BigDecimal) map.get("creditCurrent")!=null){
                want = (BigDecimal) ((BigDecimal) map.get("creditAmount")).subtract((BigDecimal) map.get("creditCurrent"));
            }else{
                want = (BigDecimal) map.get("creditAmount");
            }
        }else{
            if((BigDecimal) map.get("creditCurrent")!=null){
                want = BigDecimal.ZERO.subtract((BigDecimal) map.get("creditCurrent"));
            }else{
                want = BigDecimal.ZERO;
            }
        }

        return want;
    }

    /**
     * 计算代缴/待退
     * @param map
     * @return
     */
    private BigDecimal getWant(Map<String, Object> map) {
        BigDecimal want = BigDecimal.ZERO;
        if((BigDecimal) map.get("depositTotalAmount")!=null){
            if((BigDecimal) map.get("paidAmount")!=null){
                want = (BigDecimal) ((BigDecimal) map.get("depositTotalAmount")).subtract((BigDecimal) map.get("paidAmount"));
            }else{
                want = (BigDecimal) map.get("depositTotalAmount");
            }
        }else{
            if((BigDecimal) map.get("paidAmount")!=null){
                want = BigDecimal.ZERO.subtract((BigDecimal) map.get("paidAmount"));
            }else{
                want = BigDecimal.ZERO;
            }
        }

        return want;
    }

}
