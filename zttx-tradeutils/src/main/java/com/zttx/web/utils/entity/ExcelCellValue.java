/*
 * @(#)ExcelCellValue.java 2015-2-5 上午11:43:52
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * <p>File：ExcelCellValue.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-2-5 上午11:43:52</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class ExcelCellValue
{
    private Integer type;
    
    private Object  value;
    
    public ExcelCellValue(Integer type, Object value)
    {
        this.type = type;
        this.value = value;
    }
    
    public Integer getType()
    {
        return type;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    public String getStringValue()
    {
        if (type == Cell.CELL_TYPE_NUMERIC) { return parseString((Double) value); }
        return (String) value;
    }
    
    public Integer getIntegerValue()
    {
        if (type == Cell.CELL_TYPE_NUMERIC)
        {
            return parseInteger((Double) value);
        }
        else if (type == Cell.CELL_TYPE_STRING) { return parseInteger((String) value); }
        return (Integer) value;
    }
    
    public Double getDoubleValue()
    {
        return (Double) value;
    }
    
    public Long getLongValue()
    {
        if (type == Cell.CELL_TYPE_NUMERIC)
        {
            return parseLong((Double) value);
        }
        else if (type == Cell.CELL_TYPE_STRING) { return parseLong((String) value); }
        return (Long) value;
    }
    
    public BigDecimal getBigDecimalValue()
    {
        if (type == Cell.CELL_TYPE_NUMERIC)
        {
            return parseBigDecimal((Double) value);
        }
        else if (type == Cell.CELL_TYPE_STRING) { return parseBigDecimal((String) value); }
        return (BigDecimal) value;
    }
    
    private String parseString(Double doubleValue)
    {
        if (null == doubleValue) { return null; }
        String doubleValueStr = new DecimalFormat("0.##").format(doubleValue);
        return doubleValueStr;
    }
    
    private Integer parseInteger(Double doubleValue)
    {
        String doubleValueStr = parseString(doubleValue);
        if (null == doubleValueStr) { return null; }
        Integer integerValue = Integer.parseInt(doubleValueStr);
        return integerValue;
    }
    
    private Long parseLong(Double doubleValue)
    {
        String doubleValueStr = parseString(doubleValue);
        if (null == doubleValueStr) { return null; }
        Long longValue = Long.parseLong(doubleValueStr);
        return longValue;
    }
    
    private BigDecimal parseBigDecimal(Double doubleValue)
    {
        String doubleValueStr = parseString(doubleValue);
        if (null == doubleValueStr) { return null; }
        BigDecimal bigDecimalValue = new BigDecimal(doubleValueStr);
        return bigDecimalValue;
    }
    
    private Integer parseInteger(String stringValue)
    {
        if (StringUtils.isBlank(stringValue)) { return null; }
        stringValue = StringUtils.trim(stringValue);
        Integer integerValue = Integer.parseInt(stringValue);
        return integerValue;
    }
    
    private Long parseLong(String stringValue)
    {
        if (StringUtils.isBlank(stringValue)) { return null; }
        stringValue = StringUtils.trim(stringValue);
        Long longValue = Long.parseLong(stringValue);
        return longValue;
    }
    
    private BigDecimal parseBigDecimal(String stringValue)
    {
        if (StringUtils.isBlank(stringValue)) { return null; }
        stringValue = StringUtils.trim(stringValue);
        BigDecimal bigDecimalValue = new BigDecimal(stringValue);
        return bigDecimalValue;
    }
    
    public String toString()
    {
        return getStringValue();
    }
}
