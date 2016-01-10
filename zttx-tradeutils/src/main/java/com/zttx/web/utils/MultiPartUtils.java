/*
 * @(#)MultiPartUtils.java 2015-8-26 上午11:31:43
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.Watermark;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.FileSizeEmun;

/**
 * <p>File：MultiPartUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-26 上午11:31:43</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class MultiPartUtils
{
    private static final Logger logger            = Logger.getLogger(MultiPartUtils.class);
    
    // 适用于页面上的反斜杠
    public final static String  HTML_TAG          = "/";
 // 文件分割符 适用于文件夹上的反斜杠
    public final static String  File_SEPARATOR    = File.separator;
 // 存放临时文件的目录
    public final static String  TEMP              = "temp";
    
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
    //EXCILE
    public static final int     EXCILE            =9;
    
    // 静态页面类型
    protected static final int  STATIC            = 1;
    
    // 文章页面类型
    protected static final int  ARTICLE           = 2;
    
    
    // 视频目录
    public final static String  VIDEO_PATH        = "video";
 // 通用文件上传路径
    public final static String  COMMON_FILE_PATH  = "upload" + File_SEPARATOR + "common" + File_SEPARATOR + "file";
    
    
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
     * 文件通用上传
     * 只匹配文件后缀
     * @param file
     * @param request
     * @param type
     * @param water
     * @return
     * @throws FileOperateException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String type) throws FileOperateException
    {
        return uploadSingle(file, request, type, COMMON);
    }
    
 // 通用上传
    private static String uploadSingle(MultipartFile file, HttpServletRequest request, String type, Integer index) throws FileOperateException
    {
        if (null == file || StringUtils.isBlank(type)) { return null; }
        try
        {
            String fileName = file.getOriginalFilename();
            String filePath = getFilePath(getPrexif(request, File_SEPARATOR), fileName, type, true);
            file.transferTo(new File(filePath));
            return getPath(filePath);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw new FileOperateException(CommonConst.UPLOAD_FAULT);
        }
       
    }
    
    /**
     * 文件后缀名校验
     * @param fileName 文件名
     * @param fileType Integer 文件类型  1 图片  2 普通文本 3
     * @return Boolean  是否符合规范 true 符合  false不符合
     * @author 鲍建明
     */
    private static Boolean verifyFileBySuffix(String fileName, int fileType)
    {
        boolean flag = false;
        if (fileType == -1) { return flag; }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileType)
        {
            case IMAGE: // 图片后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.IMAGE_SUFFIX);
                break;
            case TEXT: // 文本后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.FILE_SUFFIX);
                break;
            case VIDEO: // 视频后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.VIDEO_SUFFIX);
                break;
            case FLASH: // Flash后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.FLASH_SUFFIX);
                break;
            case COMMON: // 通用后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.COMMON_SUFFIX);
                break;
            case FLASH_IMAGE: // Flash控件图片后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.IMAGE_SUFFIX);
                break;
            case ZIP: // 压缩文件后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.ZIP_SUFFIX);
                break;
            case FLV: // FLV文件后缀名校验
                flag = verifySuffixFile(suffix, ApplicationConst.FLV);
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }
    
    /**
     * 如果类型不正确的情况下，
     * 启用：通用校验
     * @param suffix
     * @param typeRules
     * @return
     */
    private static Boolean verifySuffixFile(String suffix, Object[] typeRules)
    {
        for (Object object : typeRules)
        {
            if(object instanceof String[]){
                if (verifySuffixFile(suffix, (String[]) object)) { return true; }
            }
            if(object instanceof String){
                if(verifySuffixFileSingle(suffix,object)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private static Boolean verifySuffixFileSingle(String suffix,Object typeRule){
        if(suffix.equals(typeRule.toString())){
            return true;
        }
        return false;
    }
    
 // 通用验证
    private static int verify(Integer index, String fileType)
    {
        int flag = -1;
        switch (index)
        {
            case IMAGE: // 图片验证
                flag = verifyFile(fileType, ApplicationConst.ALLOW_IMAGE) ? IMAGE : -1;
                break;
            case TEXT: // 普通文本验证
                flag = verifyFile(fileType, ApplicationConst.ALLOW_FILE) ? TEXT : -1;
                break;
            case VIDEO: // 视频验证
                flag = verifyFile(fileType, ApplicationConst.ALLOW_VIDEO) ? VIDEO : -1;
                break;
            case FLASH: // Flash验证
                flag = verifyFile(fileType, ApplicationConst.ALLOW_FLASH) ? FLASH : -1;
                break;
            case COMMON: // 直接判断后缀
                flag = COMMON;
                break;
            case FLASH_IMAGE: // Flash控件图片批量上传
                flag = verifyFile(fileType, ApplicationConst.ALLOW_FLASH_IMAGE) ? FLASH_IMAGE : -1;
                break;
            case ZIP:
                flag = verifyFile(fileType, ApplicationConst.ALLOW_ZIP) ? ZIP : -1;
                break;
            case FLV: // FLV 只校验后缀
                flag = FLV;
                break;
            default:
                break;
        }
        return flag;
    }
    
    /**
     * 验证文件的格式
     * 
     * @param fileType
     * @return
     */
    private static boolean verifyFile(String fileType, String[] fileTypes)
    {
        if (StringUtils.isBlank(fileType)) { return false; }
        Arrays.sort(fileTypes);
        return Arrays.binarySearch(fileTypes, fileType) >= 0 ? true : false;
    }
    /**
     * 格式化返回的字符串
     * @param oldPath   D:/Program Files/tomcat/apache-tomcat-6.0.37/webapps/Zttx/upload/dealer/img/2014/20140304/32位uuid.jpg
     * @return String : upload/dealer/img/2014/20140304/32位uuid.jpg
     */
    public static String getPath(String oldPath)
    {
        if (StringUtils.isNotBlank(oldPath))
        {
            int index = 0;
            if (oldPath.contains(VIDEO_PATH)) // 视频
            {
                index = oldPath.indexOf(File_SEPARATOR + VIDEO_PATH);
            }
            else if (oldPath.contains("upload"))
            { // 普通上传文件
                index = oldPath.indexOf(File_SEPARATOR + "upload");
            }
            else if (oldPath.contains(TEMP))
            { // 临时
                index = oldPath.indexOf(File_SEPARATOR + TEMP);
            }
            return replacePath(oldPath.substring(index));
        }
        return null;
    }
    
    /**
     * jsp 路径转换
     * @param path 原路径
     * @return 新路径
     */
    public static String replacePath(String path)
    {
        String newPath = null;
        if (!StringUtils.isBlank(path))
        {
            newPath = path.replace(File_SEPARATOR, HTML_TAG);
        }
        return newPath;
    }
    
    /**
     * 创建文件路径，并返回url路径
     * @throws BusinessException 
     */
    public static String createWebUrl(HttpServletRequest request, String type, String fileName) throws BusinessException
    {
        String prefix = getPrexif(request, File_SEPARATOR);
        try
        {
            String filePath = getFilePath(prefix, null, type, false);
            filePath = File_SEPARATOR + filePath + File_SEPARATOR + fileName;
            return filePath.replace(File_SEPARATOR, HTML_TAG);
        }
        catch (FileOperateException e)
        {
            throw new BusinessException(CommonConst.FAIL.code, "创建文件失败");
        }
    }
    
    /**
     * 获取文件的前缀
     * @param request
     * @param path
     * @return
     * @throws FileNotFoundException
     * @author 施建波
     */
    public static String getPrexif(HttpServletRequest request, String path)
    {
        try
        {
            return WebUtils.getRealPath(request.getSession().getServletContext(), path);
        }
        catch (FileNotFoundException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }
    private static String getFilePath(String path, String fileName, String type, boolean isFull) throws FileOperateException
    {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        if (!ValidateUtils.isNull(path))
        {
            sb2.append(path + File.separator);
            sb.append(type + File.separator + CalendarUtils.getCurrentDate("yyyy"));
            sb.append(File.separator + CalendarUtils.getCurrentDate("yyyyMMdd"));
            if (!ValidateUtils.isNull(fileName))
            {
                sb.append(File.separator + jointName(fileName, null));
            }
            mkdir(sb2.append(sb).toString());
        }
        if (isFull)
        {
            return sb2.toString();
        }
        else
        {
            return sb.toString();
        }
    }
    private static String jointName(String oldName, String version)
    {
        StringBuffer newName = new StringBuffer();
        if (!ValidateUtils.isNull(oldName))
        {
            newName.append(SerialnoUtils.buildPrimaryKey());
            if (!ValidateUtils.isNull(version))
            {
                newName.append("_" + version);
            }
            newName.append(oldName.substring(oldName.lastIndexOf(".")));
        }
        return newName.toString();
    }
    
    private static void mkdir(String path) throws FileOperateException
    {
        File file = null;
        try
        {
            file = new File(path);
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage() + "文件夹创建失败");
            throw new FileOperateException(CommonConst.CREATE_FILE_ERR);
        }
        finally
        {
            file = null;
        }
    }
    
    /**
     * 校验文件类型的方法
     * @param fileName  文件名称
     * @param requiredFileTypes  要求文件类型，多个已逗号隔开
     * @throws BusinessException
     * @author 张昌苗
     */
    public static void checkFileType(String fileName, String requiredFileTypes) throws BusinessException
    {
        // 如果要求文件类型为空，不做检查
        if (StringUtils.isBlank(requiredFileTypes)) { return; }
        String[] requiredFileTypeArr = requiredFileTypes.split(",");
        for (String requiredFileType : requiredFileTypeArr)
        {
            if (fileName.endsWith(requiredFileType)) { return; }
        }
        throw new BusinessException(CommonConst.FORMAT_FAULT);
    }
}
