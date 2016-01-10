/*
 * @(#)FileUtils.java 2014-6-19 下午7:44:51
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import com.zttx.sdk.exception.FileOperateException;
import com.zttx.web.consts.FileSizeEmun;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zttx.web.consts.CommonConst;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：FileUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-6-19 下午7:44:51</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class FileUtils
{
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    // 图片类型
    public static final int     IMAGE             = 1;

    // 普通文本验证
    public static final int     TEXT              = 2;

    // 视频验证
    public static final int     VIDEO             = 3;

    // Flash验证
    public static final int     FLASH             = 4;

    // 通用类型
    public static final int     COMMON            = 5;

    // FLASH组件图片上传
    public static final int     FLASH_IMAGE       = 6;

    // 压缩文件验证
    public static final int     ZIP               = 7;

    // flv
    public static final int     FLV               = 8;
    
    private FileUtils()
    {
        super();
    }
    
    /**
     * 移动单个文件
     * @param srcFile 源文件
     * @param destFile 目标文件
     * @param overlay 是否覆盖
     * @return boolean 如果移动成功，则返回true，否则返回false
     */
    public static boolean moveFile(File srcFile, File destFile, boolean overlay)
    {
        // 判断原文件是否存在
        if (null == srcFile || !srcFile.exists())
        {
            LoggerUtils.logError(logger, "复制文件失败：源文件：{}不存在！", srcFile.getName());
            return false;
        }
        else if (!srcFile.isFile())
        {
            LoggerUtils.logError(logger, "复制文件失败：源文件：{}不是一个文件!", srcFile.getName());
            return false;
        }
        if (null == destFile || destFile.isDirectory())
        {
            LoggerUtils.logError(logger, "复制文件失败：目标文件：{}不是一个文件！", destFile.getName());
            return false;
        }
        // 判断目标文件是否存在
        if (destFile.exists())
        {
            // 如果目标文件存在，而且复制时允许覆盖。
            if (!overlay)
            {
                LoggerUtils.logError(logger, "复制文件失败：目标文件：{}已经存在！", destFile.getName());
                return false;
            }
            else
            {
                return overlayFile(srcFile, destFile);
            }
        }
        else
        {
            try
            {
                org.apache.commons.io.FileUtils.moveFile(srcFile, destFile);
                return true;
            }
            catch (IOException e)
            {
                LoggerUtils.logError(logger, "复制文件失败：{}", e.getMessage());
                return false;
            }
        }
    }
    
    /**
     * 移动单个文件
     * @param srcFileName 待移动的文件名
     * @param destFileName 目标文件名
     * @param overlay 如果目标文件存在，是否覆盖
     * @return boolean 如果移动成功，则返回true，否则返回false
     */
    public static boolean moveFile(String srcFileName, String destFileName, boolean overlay)
    {
        File srcFile = new File(srcFileName);
        File destFile = new File(destFileName);
        return moveFile(srcFile, destFile, overlay);
    }
    
    private static boolean overlayFile(File srcFile, File destFile)
    {
        // 准备复制文件
        int byteread = 0;// 读取的位数
        InputStream in = null;
        OutputStream out = null;
        try
        {
            // 打开原文件
            in = new FileInputStream(srcFile);
            // 打开连接到目标文件的输出流
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            // 一次读取1024个字节，当byteread为-1时表示文件已经读完
            while ((byteread = in.read(buffer)) != -1)
            {
                // 将读取的字节写入输出流
                out.write(buffer, 0, byteread);
            }
            return true;
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "复制文件失败：{}", e.getMessage());
            return false;
        }
        finally
        {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
            org.apache.commons.io.FileUtils.deleteQuietly(srcFile);
        }
    }
    
    /**
     * 获取webapp绝对路径
     * @return
     * @author 张昌苗
     */
    public static String getWebappPath() throws BusinessException
    {
        try
        {
            URI uri = new URI(FileUtils.class.getResource("/").toString());
            File classFile = new File(uri.getPath());
            return classFile.getParentFile().getParentFile().getAbsolutePath();
        }
        catch (URISyntaxException e)
        {
            LoggerUtils.logError(logger, "获取webapp路径失败", e.getMessage());
            throw new BusinessException(CommonConst.FAIL);
        }
    }
    
    /**
     * 格式化文件路径,统一分隔符,去除多余的分隔符
     * @param path String 文件路径
     * @return String 标准化的文件路径
     */
    public static String formatPath(String path)
    {
        String formatPath = StringUtils.clearNull(path);
        if (StringUtils.isNotEmpty(path))
        {
            String separatorChar = System.getProperty("file.separator");
            formatPath = StringUtils.replaceString(formatPath, "\\",
                    separatorChar);
            formatPath = StringUtils.replaceString(formatPath, "/",
                    separatorChar);
            if (separatorChar.equals("\\"))
            {
                // 远程文件标识
                String remoteFlag = "\\\\";
                // 远程文件处理
                if (formatPath.startsWith(remoteFlag))
                {
                    formatPath = remoteFlag
                            + formatPath.substring(2).replaceAll("\\\\{2,}",
                                    "\\\\");
                }
                // 本地文件处理
                else
                {
                    formatPath = formatPath.replaceAll("\\\\{2,}", "\\\\");
                }
            }
            else
            {
                formatPath = formatPath.replaceAll("/{2,}", "/");
            }
        }
        return formatURLPath(formatPath);
    }

    /**
     * 校验文件上传大小的方法
     * @param fileSize  文件大小
     * @param fileSizeEmunCode  文件枚举类中的code 值
     * @param type  文件的类型
     * @throws BusinessException
     * @author 鲍建明
     */
    public static void checkFileSize(long fileSize, String fileSizeEmunCode, int type) throws BusinessException
    {
        long _size = FileSizeEmun.getFileSize(fileSizeEmunCode);
        switch (type)
        {
            case IMAGE:
                checkSize(fileSize / 1000, _size == -1 ? FileSizeEmun.F2M.getFileSize() : _size);
                break;
            case VIDEO:
                checkSize(fileSize / 1000, _size == -1 ? FileSizeEmun.F20M.getFileSize() : _size);
                break;
            case FLASH:
                checkSize(fileSize / 1000, _size == -1 ? FileSizeEmun.F20M.getFileSize() : _size);
                break;
            case ZIP:
                checkSize(fileSize / 1000, _size == -1 ? FileSizeEmun.F50M.getFileSize() : _size);
                break;
            case COMMON:
                checkSize(fileSize / 1000, _size == -1 ? FileSizeEmun.F20M.getFileSize() : _size);
                break;
            default:
                throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
    }

    /**
     * 鲍建明
     * @param fileSize
     * @param fileSizeEmun
     * @throws FileOperateException
     */
    private static void checkSize(long fileSize, long fileSizeEmun) throws BusinessException
    {
        if (fileSize > fileSizeEmun) { throw new BusinessException(CommonConst.IMAGE_SIZE_ERROR); }
    }
    
    /**
     * 把本地路径改为网站路径
     * @param filePath
     * @return
     */
    public static String formatURLPath(String filePath)
    {
        return filePath.replaceAll("\\\\", "/");
    }
}
