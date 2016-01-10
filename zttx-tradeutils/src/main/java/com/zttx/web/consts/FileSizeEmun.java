/*
 * @(#)FileEmun.java 2014-4-30 下午4:45:52
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：FileEmun.java</p>
 * <p>Title: 文件大小的枚举类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-30 下午4:45:52</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public enum FileSizeEmun
{
    F500KB(1, 500L),
    F2M(2, 2048L),
    F20M(3, 20480L),
    F5M(5, 5120L),
    F3M(6, 3072L),
    F1M(7, 1024L),
    F50M(8, 51200L);

    private Integer code;
    
    private Long    fileSize;
    
    private FileSizeEmun(Integer code, Long fileSize)
    {
        this.code = code;
        this.fileSize = fileSize;
    }
    
    public Integer getCode()
    {
        return code;
    }
    
    public Long getFileSize()
    {
        return fileSize;
    }
    
    /**
     * 通过key来或获取文件大小	
     * 没有返回-1
     * @return
     */
    public static Long getFileSize(String key)
    {
        Integer _key = null;
        try
        {
            _key = Integer.parseInt(key);
        }
        catch (Exception e)
        {
        }
        long _size = -1;
        if (_key == null) { return _size; }
        FileSizeEmun[] fileSizeEmuns = values();
        for (FileSizeEmun fileSizeEmun : fileSizeEmuns)
        {
            if (fileSizeEmun.getCode().equals(_key))
            {
                _size = fileSizeEmun.getFileSize();
                break;
            }
        }
        return _size;
    }
}
