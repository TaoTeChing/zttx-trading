package com.zttx.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.utils.entity.ExcelCellValue;

//只支持一张表的数据获取
public class ReadExcelUtils
{
    private final static Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
    
    public static List<Map<String, Object>> read(String url) throws IOException
    {
        InputStream inputStream = new FileInputStream(new File(url));
        // 初始整个Excel
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        // 获取第一个Sheet表
        List<Map<String, Object>> excelToArraylist = Lists.newArrayList();
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++)
        {
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            /*
             * System.out.println("sheet序号:" + sheetIndex + ",sheet名称:"
             * + workbook.getSheetName(sheetIndex));
             */
            // 循环该sheet中的有数据的每一行
            Map<String, String> rowTitle = Maps.newHashMap();
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++)
            {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null)
                {
                    continue;
                }
                // 循环该行的每一个单元格
                Map<String, Object> excelToMap = Maps.newHashMap();
                for (int cellnum = 0; cellnum < row.getLastCellNum(); cellnum++)
                {
                    HSSFCell cell = row.getCell(cellnum);
                    if (rowIndex == 0)
                    {
                        rowTitle.put("row" + cellnum + "Title", cell.getStringCellValue().toString());
                    }
                    else
                    {
                        // 判断该单元格内数据是否是字符型
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                        {
                            excelToMap.put(rowTitle.get("row" + cellnum + "Title"), cell.getStringCellValue());
                        }
                        // 判断该单元格内数据是否是数字
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                        {
                            excelToMap.put(rowTitle.get("row" + cellnum + "Title"), (double) cell.getNumericCellValue());
                        }
                    }
                }
                if (rowIndex != 0)
                {
                    excelToArraylist.add(excelToMap);
                }
            }
        }
        return excelToArraylist;
    }
    
    /**
     * 获取表数据
     * @author 张昌苗
     */
    public static List<Map<String, ExcelCellValue>> getDataList(HttpServletRequest request, String webUrl) throws BusinessException
    {
        Workbook workbook = getWorkbook(request, webUrl);
        return getDataList(workbook, 0, 0, 0);
    }
    
    /**
     * 获取表数据
     * @author 张昌苗
     */
    public static List<Map<String, ExcelCellValue>> getDataList(Workbook workbook, Integer sheetIndex, Integer rowIndex, Integer colIndex) throws BusinessException
    {
        Sheet sheet = getSheet(workbook, sheetIndex);
        List<String> headList = getHeadList(sheet, rowIndex, colIndex);
        List<Map<String, ExcelCellValue>> dataList = getDataList(sheet, rowIndex + 1, colIndex, headList);
        return dataList;
    }
    
    /**
     * 获取表数据
     * @author 张昌苗
     */
    public static List<Map<String, ExcelCellValue>> getDataList(Workbook workbook, String sheetName) throws BusinessException
    {
        return getDataList(workbook, sheetName, 0, 0);
    }
    
    /**
     * 获取表数据
     * @author 张昌苗
     */
    public static List<Map<String, ExcelCellValue>> getDataList(Workbook workbook, String sheetName, Integer rowIndex, Integer colIndex) throws BusinessException
    {
        Sheet sheet = getSheet(workbook, sheetName);
        List<String> headList = getHeadList(sheet, rowIndex, colIndex);
        List<Map<String, ExcelCellValue>> dataList = getDataList(sheet, rowIndex + 1, colIndex, headList);
        return dataList;
    }
    
    /**
     * 获取Workbook
     * @param request
     * @param webUrl 网络路径
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Workbook getWorkbook(HttpServletRequest request, String webUrl) throws BusinessException
    {
        if (StringUtils.isBlank(webUrl)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String filePath = getFilePath(request, webUrl);
        try
        {
            return new HSSFWorkbook(new FileInputStream(filePath));
        }
        catch (FileNotFoundException e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "文件不存在");
        }
        catch (IOException e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "文件读取失败");
        }
    }
    
    /**
     * 获取表格
     * @param workbook
     * @param sheetIndex 表序号
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Sheet getSheet(Workbook workbook, Integer sheetIndex) throws BusinessException
    {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        if (null == sheet) { throw new BusinessException(CommonConst.FAIL.code, "Sheet(0)不存在"); }
        return sheet;
    }
    
    /**
     * 获取表格
     * @param workbook
     * @param sheetName 表名称
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Sheet getSheet(Workbook workbook, String sheetName) throws BusinessException
    {
        Sheet sheet = workbook.getSheet(sheetName);
        if (null == sheet) { throw new BusinessException(CommonConst.FAIL.code, "Sheet(" + sheetName + ")不存在"); }
        return sheet;
    }
    
    /**
     * 获取表头
     * @param sheet 表头所在的表
     * @param rowIndex 表头所在的开始行序
     * @param colIndex 表头所在的开始列序
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static List<String> getHeadList(Sheet sheet, Integer rowIndex, Integer colIndex) throws BusinessException
    {
        List<String> headList = Lists.newArrayList();
        Row headRow = sheet.getRow(rowIndex);
        for (int i = colIndex; i < headRow.getLastCellNum(); i++)
        {
            Cell headCell = headRow.getCell(i);
            String head = headCell.getStringCellValue();
            if (StringUtils.isBlank(head))
            {
                break;
            }
            headList.add(head);
        }
        if (CollectionUtils.isEmpty(headList)) { throw new BusinessException(CommonConst.FAIL.code, "文件表头不正确"); }
        return headList;
    }
    
    /**
     * 获取表数据
     * @param sheet 数据所在的表
     * @param rowIndex 数据所在的开始行序
     * @param colIndex 数据所在的开始列序
     * @param headList 表头数据
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static List<Map<String, ExcelCellValue>> getDataList(Sheet sheet, Integer rowIndex, Integer colIndex, List<String> headList) throws BusinessException
    {
        List<Map<String, ExcelCellValue>> dataList = Lists.newArrayList();
        Integer lastRowNum = sheet.getLastRowNum();
        for (int i = rowIndex; i <= lastRowNum; i++)
        {
            Map<String, ExcelCellValue> data = Maps.newHashMap();
            Row row = sheet.getRow(i);
            if (isEmpty(row))
            {
                continue;
            }
            logger.debug("row{}: ", i);
            setIntegerValue(data, "_ROW_INDEX", i);
            for (int j = 0; j < headList.size(); j++)
            {
                Cell cell = row.getCell(colIndex + j);
                ExcelCellValue value = getValue(cell);
                data.put(headList.get(j), value);
                logger.debug("   col{}: {} - {}", j, headList.get(j), value);
            }
            dataList.add(data);
        }
        return dataList;
    }
    
    public static void createExcel(HttpServletRequest request, String webUrl, String sheetName, List<String> headList, List<Map<String, ExcelCellValue>> dataList)
            throws BusinessException
    {
        Workbook workbook = createWorkbook();
        Sheet sheet = createSheet(workbook, sheetName);
        createHeadList(sheet, 0, 0, headList);
        createDataList(sheet, 1, 0, headList, dataList);
        save(workbook, request, webUrl);
    }
    
    /**
     * 创建Workbook
     * @author 张昌苗
     */
    public static Workbook createWorkbook()
    {
        Workbook workbook = new HSSFWorkbook();
        return workbook;
    }
    
    /**
     * 获取Workbook
     * @author 张昌苗
     */
    public static Workbook getWorkbook(File file) throws BusinessException
    {
        try
        {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
            return workbook;
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "读取Excel模板失败");
        }
    }
    
    /**
     * 创建Sheet
     * @param workbook
     * @param sheetName 表名
     * @param headList 表头数据
     * @param dataList 表内容数据
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Sheet createWorkbook(Workbook workbook, String sheetName, List<String> headList, List<Map<String, ExcelCellValue>> dataList) throws BusinessException
    {
        Sheet sheet = createSheet(workbook, sheetName);
        createHeadList(sheet, 0, 0, headList);
        createDataList(sheet, 1, 0, headList, dataList);
        return sheet;
    }
    
    /**
     * 创建Sheet
     * @param workbook
     * @param sheetName 表名
     * @param headList  表头数据
     * @param dataList  表内容数据
     * @param  textTypeColumnHeadNames 需要设置为文本类型的表头名
     * @return Sheet
     * @throws BusinessException
     * @author 李星
     */
    public static Sheet createWorkbook(Workbook workbook, String sheetName, List<String> headList, List<Map<String, ExcelCellValue>> dataList,
            List<String> textTypeColumnHeadNames) throws BusinessException
    {
        Sheet sheet = createSheet(workbook, sheetName);
        setColumnCellTypeToText(workbook, sheet, headList, textTypeColumnHeadNames);
        createHeadList(sheet, 0, 0, headList);
        createDataList(sheet, 1, 0, headList, dataList);
        return sheet;
    }
    
    public static String save(Workbook workbook, HttpServletRequest request, String webUrl) throws BusinessException
    {
        try
        {
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            workbook.write(stream);
            Map<String, Object> params = Maps.newHashMap();
            params.put("fSize", 7);
            JsonMessage json=FileClientUtil.getJsonMessage(params, "/common/showFile",stream.toByteArray(),"file","模板.xls","text/plain");
            return json.getMessage();
        }
        catch (FileNotFoundException e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "文件不存在");
        }
        catch (IOException e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "文件读取失败");
        }
    }
    
    /**
     * 创建表格
     * @param workbook
     * @param sheetName 表格名称
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Sheet createSheet(Workbook workbook, String sheetName) throws BusinessException
    {
        Sheet sheet = workbook.getSheet(sheetName);
        if (null == sheet)
        {
            sheet = workbook.createSheet(sheetName);
        }
        if (null == sheet) { throw new BusinessException(CommonConst.FAIL.code, "创建表格失败"); }
        return sheet;
    }
    
    /**
     * 创建表头
     * @param sheet 表头所在的表
     * @param rowIndex 表头所在的开始行序
     * @param colIndex 表头所在的开始列序
     * @param headList 表头数据
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static void createHeadList(Sheet sheet, Integer rowIndex, Integer colIndex, List<String> headList) throws BusinessException
    {
        Row headRow = createRow(sheet, rowIndex);
        for (int i = 0; i < headList.size(); i++)
        {
            Cell headCell = createCell(headRow, colIndex + i);
            setValue(headCell, Cell.CELL_TYPE_STRING, headList.get(i));
        }
    }
    
    /**
     * 创建表数据
     * @param sheet 数据所在的表
     * @param rowIndex 数据所在的开始行序
     * @param colIndex 数据所在的开始列序
     * @param headList 表头数据
     * @param dataList 表内容数据
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static void createDataList(Sheet sheet, Integer rowIndex, Integer colIndex, List<String> headList, List<Map<String, ExcelCellValue>> dataList)
            throws BusinessException
    {
        if (CollectionUtils.isEmpty(dataList)) { return; }
        for (int i = 0; i < dataList.size(); i++)
        {
            Row row = createRow(sheet, rowIndex + i);
            for (int j = 0; j < headList.size(); j++)
            {
                Cell cell = createCell(row, colIndex + j);
                ExcelCellValue cellValue = dataList.get(i).get(headList.get(j));
                setValue(cell, cellValue);
            }
        }
    }
    
    public static Row createRow(Sheet sheet, Integer rowIndex)
    {
        Row row = sheet.getRow(rowIndex);
        if (null == row)
        {
            row = sheet.createRow(rowIndex);
        }
        return row;
    }
    
    public static Cell createCell(Row row, Integer colIndex)
    {
        Cell cell = row.getCell(colIndex);
        if (null == cell)
        {
            cell = row.createCell(colIndex);
        }
        return cell;
    }
    
    public static Cell createStringCell(Row row, Integer colIndex, String value)
    {
        Cell cell = createCell(row, colIndex);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(value);
        return cell;
    }
    
    public static List<Cell> createStringCell(Row row, Integer colIndex, List<String> valueList)
    {
        List<Cell> cellList = Lists.newArrayList();
        for (String value : valueList)
        {
            Cell cell = createStringCell(row, colIndex++, value);
            cellList.add(cell);
        }
        return cellList;
    }
    
    /**
     * 获取字符串形式的值
     * @param cell
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static ExcelCellValue getValue(Cell cell) throws BusinessException
    {
        if (null == cell)
        {
            return new ExcelCellValue(Cell.CELL_TYPE_BLANK, null);
        }
        else if (Cell.CELL_TYPE_STRING == cell.getCellType())
        {
            return new ExcelCellValue(Cell.CELL_TYPE_STRING, cell.getStringCellValue());
        }
        else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
        {
            return new ExcelCellValue(Cell.CELL_TYPE_NUMERIC, cell.getNumericCellValue());
        }
        else if(Cell.CELL_TYPE_FORMULA == cell.getCellType())
        {
            return new ExcelCellValue(Cell.CELL_TYPE_NUMERIC,cell.getNumericCellValue());
        }
        else if (Cell.CELL_TYPE_BLANK == cell.getCellType())
        {
            return new ExcelCellValue(Cell.CELL_TYPE_BLANK, null);
        }
        else
        {
            throw new BusinessException(CommonConst.FAIL.code, "不支持Cell[" + cell.getCellType() + "]类型");
        }
    }
    
    private static void setValue(Cell cell, ExcelCellValue cellValue) throws BusinessException
    {
        if (null == cellValue)
        {
            setValue(cell, Cell.CELL_TYPE_BLANK, null);
        }
        else
        {
            setValue(cell, cellValue.getType(), cellValue.getValue());
        }
    }
    
    private static void setValue(Cell cell, Integer type, Object value) throws BusinessException
    {
        cell.setCellType(type);
        if (type == Cell.CELL_TYPE_STRING)
        {
            cell.setCellValue((String) value);
        }
        else if (type == Cell.CELL_TYPE_NUMERIC)
        {
            cell.setCellValue((Double) value);
        }
        else if (type == Cell.CELL_TYPE_BLANK)
        {
            cell.setCellValue("");
        }
        else
        {
            throw new BusinessException(CommonConst.FAIL.code, "不支持Cell[" + cell.getCellType() + "]类型");
        }
    }
    
    /**
     * 根据url路径获取文件完整路径
     * @param request
     * @param webUrl
     * @return
     * @author 张昌苗
     */
    public static String getFilePath(HttpServletRequest request, String webUrl)
    {
        String prefix = request.getSession().getServletContext().getRealPath("/");
        if (webUrl.startsWith("/"))
        {
            webUrl = webUrl.substring(1);
        }
        webUrl = webUrl.replace("/", File.separator);
        return prefix + webUrl;
    }
    
    /**
     * 创建Excel名称
     * @param sheet
     * @param rowIndex
     * @param nameCode
     * @param valueList
     * @author 张昌苗
     */
    public static void createExcelName(Sheet sheet, Integer rowIndex, String nameCode, List<String> valueList)
    {
        if (CollectionUtils.isEmpty(valueList))
        {
            valueList = Lists.newArrayList("");
        }
        // 写入数据
        Row row = ReadExcelUtils.createRow(sheet, rowIndex);
        ReadExcelUtils.createStringCell(row, 0, nameCode);
        ReadExcelUtils.createStringCell(row, 1, valueList);
        String[] oldValueArr = CommonConstant.Excel.NAME_UNUSED;
        String[] newValueArr = CommonConstant.Excel.NAME_USED;
        // 创建名称
        Workbook workbook = sheet.getWorkbook();
        Name name = workbook.createName();
        for (int i = 0; i < oldValueArr.length; i++)
        {
            nameCode = nameCode.replace(oldValueArr[i], newValueArr[i]);
        }
        name.setNameName(nameCode);
        String formulaStr = sheet.getSheetName() + "!" + creatExcelNameList(rowIndex + 1, valueList.size(), true);
        // logger.debug("{} Formula: {}", nameCode, formulaStr);
        name.setRefersToFormula(formulaStr);
    }
    
    /**   
     * 名称数据行列计算表达式   
     * @param
     */
    private static String creatExcelNameList(int order, int size, boolean cascadeFlag)
    {
        char start = 'A';
        if (cascadeFlag)
        {
            start = 'B';
            if (size <= 25)
            {
                char end = (char) (start + size - 1);
                return "$" + start + "$" + order + ":$" + end + "$" + order;
            }
            else
            {
                char endPrefix = 'A';
                char endSuffix = 'A';
                if ((size - 25) / 26 == 0 || size == 51)
                {// 26-51之间，包括边界（仅两次字母表计算）
                    if ((size - 25) % 26 == 0)
                    {// 边界值
                        endSuffix = (char) ('A' + 25);
                    }
                    else
                    {
                        endSuffix = (char) ('A' + (size - 25) % 26 - 1);
                    }
                }
                else
                {// 51以上
                    if ((size - 25) % 26 == 0)
                    {
                        endSuffix = (char) ('A' + 25);
                        endPrefix = (char) (endPrefix + (size - 25) / 26 - 1);
                    }
                    else
                    {
                        endSuffix = (char) ('A' + (size - 25) % 26 - 1);
                        endPrefix = (char) (endPrefix + (size - 25) / 26);
                    }
                }
                return "$" + start + "$" + order + ":$" + endPrefix + endSuffix + "$" + order;
            }
        }
        else
        {
            if (size <= 26)
            {
                char end = (char) (start + size - 1);
                return "$" + start + "$" + order + ":$" + end + "$" + order;
            }
            else
            {
                char endPrefix = 'A';
                char endSuffix = 'A';
                if (size % 26 == 0)
                {
                    endSuffix = (char) ('A' + 25);
                    if (size > 52 && size / 26 > 0)
                    {
                        endPrefix = (char) (endPrefix + size / 26 - 2);
                    }
                }
                else
                {
                    endSuffix = (char) ('A' + size % 26 - 1);
                    if (size > 52 && size / 26 > 0)
                    {
                        endPrefix = (char) (endPrefix + size / 26 - 1);
                    }
                }
                return "$" + start + "$" + order + ":$" + endPrefix + endSuffix + "$" + order;
            }
        }
    }
    
    /**
     * 创建列下拉框
     * @param sheet
     * @param nameCode
     * @param startRowIndex
     * @param colIndex
     * @author 张昌苗
     */
    public static void createColSelectDataValidation(Sheet sheet, String nameCode, Integer startRowIndex, Integer colIndex)
    {
        createSelectDataValidation(sheet, nameCode, startRowIndex, -1, colIndex, colIndex);
    }
    
    /**
     * 创建列下拉框（关联父列）
     * @param sheet
     * @param nameCode
     * @param parentCol
     * @param startRowIndex
     * @param colIndex
     * @author 张昌苗
     */
    public static void createColSelectDataValidation(Sheet sheet, String nameCode, String parentCol, Integer startRowIndex, Integer colIndex)
    {
        String[] oldValueArr = CommonConstant.Excel.NAME_UNUSED;
        String[] newValueArr = CommonConstant.Excel.NAME_USED;
        String value = "INDIRECT(CONCATENATE(\"$" + parentCol + "$\",ROW()))";
        for (int i = 0; i < oldValueArr.length; i++)
        {
            value = "SUBSTITUTE(" + value + ",\"" + oldValueArr[i] + "\",\"" + newValueArr[i] + "\")";
        }
        String formulaStr = "INDIRECT(CONCATENATE(\"" + nameCode + "\"," + value + "))";
        createSelectDataValidation(sheet, formulaStr, startRowIndex, -1, colIndex, colIndex);
    }
    
    /**
     * 创建下拉框
     * @param sheet
     * @param formulaStr
     * @param startRowIndex
     * @param endRowIndex
     * @param startColIndex
     * @param endColIndex
     * @author 张昌苗
     */
    public static void createSelectDataValidation(Sheet sheet, String formulaStr, Integer startRowIndex, Integer endRowIndex, Integer startColIndex, Integer endColIndex)
    {
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaStr);
        startRowIndex = startRowIndex < 0 ? 0 : startRowIndex;
        endRowIndex = endRowIndex < 0 ? 10000 : endRowIndex;
        startColIndex = startColIndex < 0 ? 0 : startColIndex;
        endColIndex = endColIndex < 0 ? 200 : endColIndex;
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(startRowIndex, endRowIndex, startColIndex, endColIndex);
        DataValidation dataValidation = new HSSFDataValidation(cellRangeAddressList, constraint);
        // dataValidation.createPromptBox("提示","请使用下拉方式选择合适的值！");
        dataValidation.createErrorBox("错误", "你输入的值未在备选列表中，请下拉选择合适的值！");
        sheet.addValidationData(dataValidation);
    }
    
    public static String getStringValue(Map<String, ExcelCellValue> data, String headName, Boolean isRequire) throws BusinessException
    {
        String value = data.get(headName).getStringValue();
        if (StringUtils.isBlank(value))
        {
            if (isRequire) { throw new BusinessException(CommonConst.FAIL.code, headName + "不能为空"); }
            return null;
        }
        else
        {
            return value.trim();
        }
    }
    
    public static Integer getIntegerValue(Map<String, ExcelCellValue> data, String headName, Boolean isRequire) throws BusinessException
    {
        Integer value = data.get(headName).getIntegerValue();
        if (null == value)
        {
            if (isRequire) { throw new BusinessException(CommonConst.FAIL.code, headName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static Double getDoubleValue(Map<String, ExcelCellValue> data, String headName, Boolean isRequire) throws BusinessException
    {
        Double value = data.get(headName).getDoubleValue();
        if (null == value)
        {
            if (isRequire) { throw new BusinessException(CommonConst.FAIL.code, headName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static Long getLongValue(Map<String, ExcelCellValue> data, String headName, Boolean isRequire) throws BusinessException
    {
        Long value = data.get(headName).getLongValue();
        if (null == value)
        {
            if (isRequire) { throw new BusinessException(CommonConst.FAIL.code, headName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static BigDecimal getBigDecimalValue(Map<String, ExcelCellValue> data, String headName, Boolean isRequire) throws BusinessException
    {
        BigDecimal value = data.get(headName).getBigDecimalValue();
        if (null == value)
        {
            if (isRequire) { throw new BusinessException(CommonConst.FAIL.code, headName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static void setIntegerValue(Map<String, ExcelCellValue> data, String headName, Integer value)
    {
        ExcelCellValue cellValue = new ExcelCellValue(Cell.CELL_TYPE_NUMERIC, value.doubleValue());
        data.put(headName, cellValue);
    }
    
    public static void setStringValue(Map<String, ExcelCellValue> data, String headName, String value)
    {
        ExcelCellValue cellValue = new ExcelCellValue(Cell.CELL_TYPE_STRING, value);
        data.put(headName, cellValue);
    }
    
    public static Boolean isEmpty(Row row) throws BusinessException
    {
        if (null == row) { return true; }
        for (int i = 0; i <= row.getLastCellNum(); i++)
        {
            Cell cell = row.getCell(i);
            if (!isEmpty(cell)) { return false; }
        }
        return true;
    }
    
    public static Boolean isEmpty(Cell cell) throws BusinessException
    {
        if (null == cell)
        {
            return true;
        }
        else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
        {
            return true;
        }
        else if (cell.getCellType() == Cell.CELL_TYPE_STRING)
        {
            return StringUtils.isBlank(cell.getStringCellValue());
        }
        else
        {
            return false;
        }
    }
    
    public static Row getRow(Sheet sheet, Integer rowIndex) throws BusinessException
    {
        if (null == sheet) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return sheet.getRow(rowIndex);
    }
    
    public static Cell getCell(Row row, Integer colIndex) throws BusinessException
    {
        if (null == row) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return row.getCell(colIndex);
    }
    
    public static Cell getCell(Sheet sheet, Integer rowIndex, Integer colIndex) throws BusinessException
    {
        Row row = getRow(sheet, rowIndex);
        Cell cell = getCell(row, colIndex);
        return cell;
    }
    
    public static void setCellStyle(Cell cell, CellStyle cellStyle) throws BusinessException
    {
        if (null == cell) { throw new BusinessException(CommonConst.PARAM_NULL); }
        cell.setCellStyle(cellStyle);
    }
    
    public static void setCellStyle(Sheet sheet, Integer rowIndex, Integer colIndex, CellStyle cellStyle) throws BusinessException
    {
        Cell cell = getCell(sheet, rowIndex, colIndex);
        setCellStyle(cell, cellStyle);
    }
    
    /**
     * 设置必填标题的颜色
     * @param sheet
     * @param headList
     * @param requiredHeadList
     * @param cellStyle
     * @author 张昌苗
     * @throws BusinessException 
     */
    public static void setRequiredHeadColor(Sheet sheet, List<String> headList, List<String> requiredHeadList, CellStyle cellStyle) throws BusinessException
    {
        for (String requiredHead : requiredHeadList)
        {
            setCellStyle(sheet, 0, headList.indexOf(requiredHead), cellStyle);
        }
    }
    
    /**
     * 设置某列的单元格格式为文本类型
     * Sets the default column style for a given column.  POI will only apply this style to new cells added to the sheet.
     * @param workbook
     * @param sheet
     * @param headList 表头名集合
     * @param textTypeColumnHeadNames 需要设置为文本类型的列的表头名
     * @author 李星
     */
    public static void setColumnCellTypeToText(Workbook workbook, Sheet sheet, List<String> headList, List<String> textTypeColumnHeadNames)
    {
        CellStyle css = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        css.setDataFormat(format.getFormat("@"));
        for (String headName : textTypeColumnHeadNames)
        {
            sheet.setDefaultColumnStyle(headList.indexOf(headName), css);
        }
    }
    
    /**
     * 设置某列的单元格格式为文本类型
     * Sets the default column style for a given column.  POI will only apply this style to new cells added to the sheet.
     * @param workbook
     * @param sheet
     * @param colIndex 列索引
     * @author 李星
     */
    public static void setColumnCellTypeToText(Workbook workbook, Sheet sheet, int colIndex)
    {
        CellStyle css = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        css.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(colIndex, css);
    }
}
