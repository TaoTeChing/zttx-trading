/*
 * @(#)UploadFile.java 2014-5-16 下午1:27:59
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

/**
 * <p>File：UploadFile.java</p>
 * <p>Title: 用于封装上传文件的信息</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-16 下午1:27:59</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class UploadFile
{
    String fileName;
    
    String urlPath;
    
    public UploadFile()
    {
        super();
    }
    
    public UploadFile(String fileName, String urlPath)
    {
        super();
        this.fileName = fileName;
        this.urlPath = urlPath;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getUrlPath()
    {
        return urlPath;
    }
    
    public void setUrlPath(String urlPath)
    {
        this.urlPath = urlPath;
    }
}
