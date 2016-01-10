/*
 * @(#)MultipartUtils.java 2014-3-26 上午9:52:10
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.utils.SerialnoUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.sdk.exception.FormatException;
import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.bean.Watermark;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.FileSizeEmun;
import com.zttx.web.module.common.service.ImageService;

/**
 * <p>File：MultipartUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-26 上午9:52:10</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class MultipartUtils
{
    private static final Logger logger            = Logger.getLogger(MultipartUtils.class);
    
    private static ImageService imageService      = SpringUtils.getBean(ImageService.class);
    
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
    
    // 静态页面类型
    protected static final int  STATIC            = 1;
    
    // 文章页面类型
    protected static final int  ARTICLE           = 2;
    
    // 是否删除没有转码之前的视频文件
    protected static boolean    ISDELETE          = false;
    
    // 文件分割符 适用于文件夹上的反斜杠
    public final static String  File_SEPARATOR    = File.separator;
    
    // 经销商图片上传路径
    public final static String  DEALER_IMG_PATH   = "upload" + File_SEPARATOR + "dealer" + File_SEPARATOR + "img";
    
    // 经销商文档上传路径
    public final static String  DEALER_DOC_PATH   = "upload" + File_SEPARATOR + "dealer" + File_SEPARATOR + "doc";
    
    // 品牌商图片上传路径
    public final static String  BRAND_IMG_PATH    = "upload" + File_SEPARATOR + "brand" + File_SEPARATOR + "img";
    
    // 品牌商文档上传路径
    public final static String  BRAND_DOC_PATH    = "upload" + File_SEPARATOR + "brand" + File_SEPARATOR + "doc";
    
    // 商学院图片上传路径
    public final static String  SCHOOL_IMG_PATH   = "upload" + File_SEPARATOR + "school" + File_SEPARATOR + "img";
    
    // 商学院文件上传路径
    public final static String  SCHOOL_DOC_PATH   = "upload" + File_SEPARATOR + "school" + File_SEPARATOR + "doc";
    
    // 品牌商图库上传路径
    public final static String  BRAND_LIB         = "upload" + File_SEPARATOR + "brand" + File_SEPARATOR + "lib";
    
    // 通用图片上传路径
    public final static String  COMMON_IMG_PATH   = "upload" + File_SEPARATOR + "common" + File_SEPARATOR + "img";
    
    // 通用文件上传路径
    public final static String  COMMON_FILE_PATH  = "upload" + File_SEPARATOR + "common" + File_SEPARATOR + "file";
    
    // 存放客服图片的路径
    public final static String  CUSTOMER_IMG_PATH = "upload" + File_SEPARATOR + "customer" + File_SEPARATOR + "img";
    
    // 编辑器图片存储方式
    public final static String  EDITOR_IMG_PATH   = "upload" + File_SEPARATOR + "editor";
    
    // 静态的FreeMarket页面
    public static final String  STATIC_HTML       = "html";
    
    // 文章的FreeMarket页面
    public static final String  ARTICLE_HTML      = "WEB-INF" + File_SEPARATOR + "view" + File_SEPARATOR + "html";
    
    // 适用于页面上的反斜杠
    public final static String  HTML_TAG          = "/";
    
    // 存放临时文件的目录
    public final static String  TEMP              = "temp";
    
    // 视频目录
    public final static String  VIDEO_PATH        = "video";
    
    /**
     * 只允许FLV上传
     * @param photo
     * @param request
     * @param type
     * @return
     * @throws FileOperateException
     */
    public static String uploadFlv(MultipartFile photo, HttpServletRequest request, String type) throws FileOperateException
    {
        return uploadSingle(photo, request, type, FLV, false);
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
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String type, boolean water) throws FileOperateException
    {
        return uploadSingle(file, request, type, COMMON, water);
    }
    
    /**
     * 图片上传
     * @param photo  图片对象
     * @param request 
     * @param type 上传到哪个目录
     * * @param  water 是否水印   true :打    
     * 
     * @return
     * @throws IOException
     */
    public static String uploadImage(MultipartFile photo, HttpServletRequest request, String type, boolean water) throws FileOperateException
    {
        return uploadSingle(photo, request, type, IMAGE, water);
    }
    
    /**
     * 图片上传+生成缩略图
     * @param photo  图片对象
     * @param request 
     * @param type 上传到哪个目录
     * @param  water 是否水印   true :打    
     * 
     * @return
     * @throws IOException
     */
    public static String uploadImageAndResize(MultipartFile photo, HttpServletRequest request, String type, boolean water, String cateKey) throws FileOperateException
    {
        String path = uploadSingle(photo, request, type, IMAGE, water);
        resizeImage(path, cateKey, type, request, null);
        return path;
    }
    
    /**
     * Flash控件批量图片上传
     * @param files  图片对象
     * @param request 
     * @param type 上传到哪个目录
     *
     * @return
     * @throws IOException
     */
    public static String[] uploadFile(MultipartFile[] files, HttpServletRequest request, String type) throws FileOperateException
    {
        if (ArrayUtils.isEmpty(files)) { return null; }
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++)
        {
            result[i] = uploadSingle(files[i], request, type, COMMON, false);
        }
        return result;
    }
    
    /**
     * Flash控件批量图片上传
     * @param files  图片对象
     * @param request
     * @param type 上传到哪个目录
     * @param  water 是否水印   true :打
     *
     * @return
     * @throws IOException
     */
    public static String[] uploadImage(MultipartFile[] files, HttpServletRequest request, String type, boolean water) throws FileOperateException
    {
        if (ArrayUtils.isEmpty(files)) { return null; }
        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++)
        {
            result[i] = uploadSingle(files[i], request, type, FLASH_IMAGE, water);
        }
        return result;
    }
    
    /**
     * 普通文本文件上传
     * @param text 文本对象
     * @param request
     * @param type 上传到哪个目录
     * * @param  water 是否水印   true :打       
     * @return
     * @throws IOException
     */
    public static String uploadText(MultipartFile text, HttpServletRequest request, String type) throws FileOperateException
    {
        return uploadSingle(text, request, type, TEXT, false);
    }
    
    /**
     * 视频上传
     * @param video 视频对象
     * @param request
     * @param type 上传到哪
     * @return Map<String, String>   videoImage : 图片    video ： 视频
     * @throws IOException
     */
    public static Map<String, String> uploadVideo(MultipartFile video, HttpServletRequest request, String type) throws FileOperateException
    {
        Map<String, String> result = Maps.newHashMap();
        String videoPath = uploadSingle(video, request, type, VIDEO, false);
        String before = getPrexif(request, File_SEPARATOR);
        String oldPath = before + replaceTag(videoPath, HTML_TAG, File_SEPARATOR);
        String videoName = replaceSuffix(videoPath, ApplicationConst.VIDEO_FORMAT);
        String newVideo = before + replaceTag(videoName, HTML_TAG, File_SEPARATOR);
        VideoUtils conver = new VideoUtils(before);
        conver.convert(oldPath, newVideo);
        result.put("video", videoName);
        if (ISDELETE)
        {
            deleteFile(oldPath, request, null);
        }
        return result;
    }
    
    /**
     * 替换后缀名
     * @param path
     * @param suffixName
     * @return
     */
    public static String replaceSuffix(String path, String suffixName)
    {
        return path.substring(0, path.lastIndexOf(".")) + suffixName;
    }
    
    /**
     * Flash视频的上传  
     * 还没有转码
     * @param flash flash对象
     * @param request
     * @param type 上传到哪
     * @return
     * @throws IOException
     */
    public static Map<String, String> uploadFlash(MultipartFile flash, HttpServletRequest request, String type) throws FileOperateException
    {
        Map<String, String> result = Maps.newHashMap();
        String videoPath = uploadSingle(flash, request, type, FLASH, false);
        String before = getPrexif(request, File_SEPARATOR);
        String oldPath = before + replaceTag(videoPath, HTML_TAG, File_SEPARATOR);
        String videoName = replaceSuffix(videoPath, ApplicationConst.VIDEO_FORMAT);
        String newVideo = before + replaceTag(videoName, HTML_TAG, File_SEPARATOR);
        VideoUtils conver = new VideoUtils(before);
        conver.convert(oldPath, newVideo);
        result.put("video", videoName);
        if (ISDELETE)
        {
            deleteFile(oldPath, request, null);
        }
        return result;
    }
    
    /**
     * Zip压缩文件的上传  
     * 还没有转码
     * @param zip zip对象
     * @param request
     * @param type 上传到哪
     * @return
     * @throws IOException
     */
    public static String uploadZip(MultipartFile zip, HttpServletRequest request, String type) throws FileOperateException
    {
        return uploadSingle(zip, request, type, ZIP, false);
    }
    
    // 通用上传
    private static String uploadSingle(MultipartFile file, HttpServletRequest request, String type, Integer index, boolean water) throws FileOperateException
    {
        if (null == file || StringUtils.isBlank(type)) { return null; }
        String fileType = file.getContentType();
        if (verifyFileBySuffix(file.getOriginalFilename(), verify(index, fileType)))
        {
            try
            {
                String fileName = file.getOriginalFilename();
                String filePath = getFilePath(getPrexif(request, File_SEPARATOR), fileName, type, true);
                file.transferTo(new File(filePath));
                // 水印
                if (water)
                {
                    imageService.resizeImage(new File(filePath), null, new Watermark());
                }
                return getPath(filePath);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
                throw new FileOperateException(CommonConst.UPLOAD_FAULT);
            }
        }
        else
        {
            throw new FileOperateException(CommonConst.FORMAT_FAULT);
        }
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
            if (verifySuffixFile(suffix, (String[]) object)) { return true; }
        }
        return false;
    }
    
    /**
     * 验证文件后缀的方法
     * 
     * @param fileType
     * @return
     */
    private static boolean verifySuffixFile(String fileType, String[] fileTypes)
    {
        if (fileTypes.length == 0) { return true; // 如果没有定义将不在进行2次检验
        }
        if (StringUtils.isBlank(fileType)) { return false; }
        Arrays.sort(fileTypes);
        return Arrays.binarySearch(fileTypes, fileType) >= 0 ? true : false;
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
    
    /**
     * 获取文件上传路径的文件
     * @return 
     * @param path   path  D:/Program Files/tomcat/apache-tomcat-6.0.37/webapps、Zttx/upload
     * @param fileName 身份证.jpg
     * @param type 指定你上传的是文本还是图片
     * 
     * 
     * @return String   /upload/dealer/img/2014/20140304/32位uuid.jpg
     * @throws FileOperateException 
     * @throws FormatException 
     */
    private static String getFilePath(String path, String fileName, String type, boolean isFull) throws FileOperateException
    {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        if (!ValidateUtils.isNull(path))
        {
            sb2.append(path + File_SEPARATOR);
            sb.append(type + File_SEPARATOR + CalendarUtils.getCurrentDate("yyyy"));
            sb.append(File_SEPARATOR + CalendarUtils.getCurrentDate("yyyyMMdd"));
            if (!ValidateUtils.isNull(fileName))
            {
                sb.append(File_SEPARATOR + jointName(fileName, null));
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
     * 拼接文件名称
     * @param oldName 原来的文件名
     * @return  String 新的文件名
     */
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
    
    /**
     *  创建文件上传路径
     * @param path 文件的全路径
     * @throws FormatException 
     */
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
     * 页面及文件之间的反斜杠的替换
     * @param oldUrl   
     * @param oldTag
     * @param newTag
     * @return
     */
    private static String replaceTag(String oldUrl, String oldTag, String newTag)
    {
        if (StringUtils.isNotBlank(oldUrl) && StringUtils.isNotBlank(oldTag) && StringUtils.isNotBlank(newTag)) { return oldUrl.replace(oldTag, newTag); }
        return null;
    }
    
    /**
     * 移动文件和删除文件一起
     * 
     * 需要生成压缩图片时，请使用moveAndresizeFile 
     * @param request
     * @param type 移动到哪的说明
     * @param newURL HTML上的地址
     *  1: 1： 为空或空串时，返回null
     *  2: 如果文件找不到时，抛出异常，异常信息为(126015, "上传的文件不存在")
     * @param oldURL 传空不删除
     * @return
     * @throws IOException
     * @throws FileOperateException 
     */
    public static String moveAndDeleteFile(HttpServletRequest request, String type, String newURL, String oldURL) throws FileOperateException
    {
        if (StringUtils.isBlank(newURL)) { return null; }
        if (isLib(newURL))
        {
            newURL = newURL.substring(newURL.indexOf(ApplicationConst.UPLOAD));
            return newURL.replace(File_SEPARATOR, HTML_TAG);
        }
        String result = isEqual(newURL, oldURL);
        if ("0".equals(result)) // 相同的URL
        { return oldURL; }
        String path = moveFile(getPrexif(request, File_SEPARATOR), type, newURL);
        path = getPath(path);
        deleteFile(new String[]{result}, getPrexif(request, File_SEPARATOR));
        return path;
    }
    
    /**
     * 压缩图片
     * @param path
     * @param cateKey
     * @param type
     * @param request
     * @param WatermarkText 水印的文字内容
     * 
     * 
     * 
     */
    public static void resizeImage(String path, String cateKey, String type, HttpServletRequest request, String WatermarkText)
    {
        Watermark watermark = null;
        try
        {
            if ("-1".equals(WatermarkText)) { return; }
            if (StringUtils.isBlank(WatermarkText))
            {
                watermark = new Watermark();
            }
            else
            {
                watermark = new Watermark(WatermarkText);
            }
            String filePath = replaceTag(getPrexif(request, File_SEPARATOR) + path, HTML_TAG, File_SEPARATOR);
            imageService.resizeImage(new File(filePath), cateKey, watermark);
        }
        catch (IOException e)
        {
            logger.error("压缩图片失败");
        }
    }
    
    /**
     * 移动文件和压缩图片
     * @param request
     * @param type 移动到哪的说明
     * @param newURL HTML上的地址
     * @param cateKey 图片的类型  传空表示不需要图片压缩
     * 	cateKey :   UploadAttCateConst厘米的常量
     * 
     * @return
     * @throws IOException
     * @throws FileOperateException 
     */
    public static String moveAndresizeFile(HttpServletRequest request, String type, String newURL, String cateKey) throws FileOperateException
    {
        return moveAndresizeFile(request, type, newURL, cateKey, null);
    }
    
    /**
     * 移动文件和压缩图片
     * @param request
     * @param type 移动到哪的说明
     * @param newURL HTML上的地址
     * @param cateKey 图片的类型 传空表示不需要图片压缩
     * 			cateKey :   UploadAttCateConst厘米的常量
     * @param WatermarkText 水印的文字
     * WatermarkText: 
     * 				传空   默认的水印文字：www.8637.com
     * 				“-1” ： 不打水印
     * 				"水印文字" ： 打入输入的文字
     * @return
     * @throws IOException
     * @throws FileOperateException 
     */
    public static String moveAndresizeFile(HttpServletRequest request, String type, String newURL, String cateKey, String WatermarkText) throws FileOperateException
    {
        String path = moveAndDeleteFile(request, type, newURL, null);
        if (StringUtils.isNotBlank(cateKey))
        {
            resizeImage(path, cateKey, type, request, WatermarkText); // 生成压缩图片
        }
        return path;
    }
    
    /**
    * 文件移动
    * @param path D:/Program Files/tomcat/apache-tomcat-6.0.37/webapps、Zttx/upload
    * @param type  指定你上传的是文本还是图片
    * @param fileUrl 从前台获取到的URL
    * @return boolean 是否成功
    * @throws IOException
    * @throws FileOperateException 
    */
    public static String moveFile(String path, String type, String fileUrl) throws FileOperateException
    {
        if (StringUtils.isNotBlank(path) && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(fileUrl))
        {
            String dir = getDirPath(path, type);
            String resoure = path + File_SEPARATOR + convertHtmlURl(fileUrl);
            return moveFile(parseFilePath(resoure), parseFilePath(dir));
        }
        return null;
    }
    
    /**
     * 转换页面传过来的IMG URL 
     * @param htmlUrl
     * @return 
     */
    public static String convertHtmlURl(String htmlUrl)
    {
        if (StringUtils.isNotBlank(htmlUrl))
        {
            int index = htmlUrl.indexOf("upload");
            if (index == -1)
            {
                index = htmlUrl.indexOf(TEMP);
                if (index == -1) { return ""; }
            }
            String url = htmlUrl.substring(index);
            return replaceTag(url, HTML_TAG, File_SEPARATOR);
        }
        return null;
    }
    
    /**
     * 把路径转化成文件路径（把/替换成\）
     * @param path
     * @return String
     * @author 张昌苗
     */
    public static String parseFilePath(String path)
    {
        if (StringUtils.isBlank(path)) { return ""; }
        return path.replace(HTML_TAG, File_SEPARATOR);
    }
    
    /**
     * 获取指定的文件上传目录
     * @param path
     * @param type
     * @return
     * @throws FileOperateException 
     * @throws IOException
     */
    public static String getDirPath(String path, String type) throws FileOperateException
    {
        return getFilePath(path, null, type, true);
    }
    
    /** 
    * 移动文件 (移动文件变成文件拷贝)
    * @param srcFileName 	源文件完整路径
    * @param destDirName 	目的目录完整路径
    * @return 文件移动成功返回源文件新完整路径，否则返回null 
    */
    private static String moveFile(String srcFileName, String destDirName) throws FileOperateException
    {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists() || !srcFile.isFile()) { throw new FileOperateException(CommonConst.SOURCE_NOT_FOUND); }
        File destDir = new File(destDirName);
        if (!destDir.exists())
        {
            destDir.mkdirs();
        }
        String newFile = destDirName + File_SEPARATOR + srcFile.getName();
        if (!srcFileName.equals(newFile))
        {
            copy(srcFileName, FilenameUtils.getFullPath(newFile));
        }
        return newFile;
    }
    
    /**
    * 判断二组文件地址是否相同  
    * 相同则0字符串，不同反回需要被删除的地址
    * @param newURL
    * @param oldURL
    * @return
    */
    public static String isEqual(String newURL, String oldURL)
    {
        if (StringUtils.isBlank(newURL)) { return null; }
        if (StringUtils.isNotBlank(oldURL) && newURL.equalsIgnoreCase(oldURL)) { return "0"; }
        newURL = replaceTag(newURL, HTML_TAG, File_SEPARATOR);
        String oldURL2 = replaceTag(oldURL, HTML_TAG, File_SEPARATOR);
        if (newURL.equalsIgnoreCase(oldURL2)) { return "0"; }
        return oldURL;
    }
    
    /**
     * 删除文件
     * @param path
     * @param request
     * @param domain
     */
    public static void deleteFile(String path, HttpServletRequest request, String domain)
    {
        /*
         * path = replaceTag(path, HTML_TAG, File_SEPARATOR);
         * deleteFile(new String[]{path},getPrexif(request, File_SEPARATOR));
         */
    }
    
    /**
     * 图片是否从图库中引用的
     * @param newURL
     * @return boolean
     */
    private static boolean isLib(String newURL)
    {
        if (StringUtils.isNotBlank(newURL))
        {
            newURL = replaceTag(newURL, HTML_TAG, File_SEPARATOR);
            if (newURL.contains(BRAND_LIB)) { return true; }
            return false;
        }
        return false;
    }
    
    /**
     * 移动文件和删除文件一起(一组)
     * 不带图片压缩  替代moveAndresizeFile(HttpServletRequest request, String type,String[] newURL, String[] oldURL, String cateKey)
     * @param request
     * @param type 移动到哪的说明
     * @param newURL  HTML上的地址
     * @param oldURL 传空不删除
     * @return
     * @throws FileOperateException 
     * @throws IOException
     */
    public static String[] moveAndDeleteFile(HttpServletRequest request, String type, String[] newURL, String[] oldURL) throws FileOperateException
    {
        if (ArrayUtils.isEmpty(newURL) || StringUtils.isBlank(type)) { return null; }
        if (ArrayUtils.isEmpty(oldURL))
        {
            oldURL = new String[newURL.length];
        }
        String[] result = new String[newURL.length];
        int i;
        for (i = 0; i < newURL.length && i < oldURL.length; i++)
        {
            result[i] = moveAndDeleteFile(request, type, newURL[i], oldURL[i]);
        }
        if (newURL.length != i)
        {
            for (; i < newURL.length; i++)
            {
                result[i] = moveAndDeleteFile(request, type, newURL[i], null);
            }
        }
        return result;
    }
    
    /**
     * 对newURLs，oldURLs 排序处理后再做“移动文件和删除文件”、
     * 
     * 不带图片压缩 替代moveAndresizeFile(HttpServletRequest request, String type,List<String> newURLs, List<String> oldURLs, String cateKey)
     * @param request
     * @param type
     * @param newURLs
     * @param oldURLs
     * @return
     * @author 吴万杰
     * @throws FileOperateException
     */
    @Deprecated
    public static String[] moveAndDeleteFile(HttpServletRequest request, String type, List<String> newURLs, List<String> oldURLs) throws FileOperateException
    {
        String[] newArr = null;
        String[] oldArr = null;
        if (newURLs != null && !newURLs.isEmpty())
        {
            if (oldURLs != null && !oldURLs.isEmpty())
            {
                int len = newURLs.size();
                int oldLen = oldURLs.size();
                List<Integer> newIds = new ArrayList<Integer>();
                List<Integer> oldIds = new ArrayList<Integer>();
                // 取出未更改的url
                for (int i = 0; i < len; i++)
                {
                    String url = newURLs.get(i);
                    for (int j = 0; j < oldLen; j++)
                    {
                        String orlUrl = oldURLs.get(j);
                        if ("0".equals(isEqual(url, orlUrl)))
                        {
                            newIds.add(i);
                            oldIds.add(j);
                            break;
                        }
                    }
                }
                if (oldLen < len)
                {
                    for (int i = 0; i < len - oldLen; i++)
                    {
                        oldURLs.add(null);
                    }
                }
                for (int i = 0; i < newIds.size(); i++)
                {
                    int newIndex = newIds.get(i);
                    int oldIndex = oldIds.get(i);
                    String oldTemp = oldURLs.get(newIndex);
                    String oldTemp2 = oldURLs.get(oldIndex);
                    oldURLs.remove(newIndex);
                    oldURLs.add(newIndex, oldTemp2);
                    oldURLs.remove(oldIndex);
                    oldURLs.add(oldIndex, oldTemp);
                }
                oldArr = oldURLs.toArray(new String[oldURLs.size()]);
            }
            newArr = newURLs.toArray(new String[newURLs.size()]);
        }
        return moveAndDeleteFile(request, type, newArr, oldArr);
    }
    
    /**
     * 移动文件和删除文件一起(一组)
     * 带图片压缩
     * @param request
     * @param type 移动到哪的说明
     * @param newURL  HTML上的地址
     * @param oldURL 传空不删除
     * @param cateKey UploadAttCateConst的常量
     * @return
     * @throws FileOperateException 
     * @throws IOException
     */
    public static String[] moveAndresizeFile(HttpServletRequest request, String type, String[] newURL, String[] oldURL, String cateKey) throws FileOperateException
    {
        if (ArrayUtils.isEmpty(newURL) || StringUtils.isBlank(type)) { return null; }
        if (ArrayUtils.isEmpty(oldURL))
        {
            oldURL = new String[newURL.length];
        }
        String[] result = new String[newURL.length];
        int i;
        for (i = 0; i < newURL.length && i < oldURL.length; i++)
        {
            result[i] = moveAndresizeFile(request, type, newURL[i], cateKey, null);
        }
        if (newURL.length != i)
        {
            for (; i < newURL.length; i++)
            {
                result[i] = moveAndresizeFile(request, type, newURL[i], cateKey, null);
            }
        }
        return result;
    }
    
    /**
     * 对newURLs，oldURLs 排序处理后再做“移动文件和删除文件”、
     * 带图片压缩
     * @param request
     * @param type
     * @param newURLs
     * @param oldURLs
     * 	 * @param cateKey UploadAttCateConst的常量
     * @return
     * @author 吴万杰
     * @throws FileOperateException
     */
    public static String[] moveAndresizeFile(HttpServletRequest request, String type, List<String> newURLs, List<String> oldURLs, String cateKey)
            throws FileOperateException
    {
        String[] newArr = null;
        String[] oldArr = null;
        if (newURLs != null && !newURLs.isEmpty())
        {
            if (oldURLs != null && !oldURLs.isEmpty())
            {
                int len = newURLs.size();
                int oldLen = oldURLs.size();
                List<Integer> newIds = new ArrayList<Integer>();
                List<Integer> oldIds = new ArrayList<Integer>();
                // 取出未更改的url
                for (int i = 0; i < len; i++)
                {
                    String url = newURLs.get(i);
                    for (int j = 0; j < oldLen; j++)
                    {
                        String orlUrl = oldURLs.get(j);
                        if ("0".equals(isEqual(url, orlUrl)))
                        {
                            newIds.add(i);
                            oldIds.add(j);
                            break;
                        }
                    }
                }
                if (oldLen < len)
                {
                    for (int i = 0; i < len - oldLen; i++)
                    {
                        oldURLs.add(null);
                    }
                }
                for (int i = 0; i < newIds.size(); i++)
                {
                    int newIndex = newIds.get(i);
                    int oldIndex = oldIds.get(i);
                    String oldTemp = oldURLs.get(newIndex);
                    String oldTemp2 = oldURLs.get(oldIndex);
                    oldURLs.remove(newIndex);
                    oldURLs.add(newIndex, oldTemp2);
                    oldURLs.remove(oldIndex);
                    oldURLs.add(oldIndex, oldTemp);
                }
                oldArr = oldURLs.toArray(new String[oldURLs.size()]);
            }
            newArr = newURLs.toArray(new String[newURLs.size()]);
        }
        return moveAndresizeFile(request, type, newArr, oldArr, cateKey);
    }
    
    /**
     * 复制文件
     * @param fromFilePath 原文件路径
     * @param toDirPath 新文件目录路径
     * @author 张昌苗
     * @throws FileOperateException 
     */
    private static void copy(String fromFilePath, String toDirPath) throws FileOperateException
    {
        try
        {
            org.apache.commons.io.FileUtils.copyFileToDirectory(new File(fromFilePath), new File(toDirPath));
        }
        catch (IOException e)
        {
            throw new FileOperateException(CommonConst.COPY_FAULT);
        }
    }
    
    /**
     * 文件拷贝
     * 注意：2个文件名称是相同的，请不要再同一个目录下进行操作
     * @param request
     * @param path
     * @param type
     * @throws FileOperateException
     * @author 鲍建明
     */
    public static void copyFileToDirectory(HttpServletRequest request, String path, String type) throws FileOperateException
    {
        String before = getPrexif(request, File_SEPARATOR);
        String oldPath = before + path;
        String newPath = getFilePath(before, null, type, true);
        copy(oldPath, newPath);
    }
    
    /**
     * 文件拷贝
     * 可在同一目录下操作
     * 2个文件名是不相同的
     * @param request
     * @param path
     * @param type
     * @throws FileOperateException
     * @author 鲍建明
     */
    public static String copyFile(HttpServletRequest request, String path, String type) throws FileOperateException
    {
        return copyFiles(request, path, type, null);
    }
    
    /**
     * 文件拷贝 + 生成缩略图
     * 可在同一目录下操作
     * 2个文件名是不相同的
     * @param request
     * @param path
     * @param cateKey
     * null : 不生成缩略图
     * UploadAttCateConst的常量
     * 
     * @throws FileOperateException
     * @author 鲍建明
     */
    public static String copyFileAndResize(HttpServletRequest request, String path, String cateKey) throws FileOperateException
    {
        return copyFiles(request, path, null, cateKey);
    }
    
    // 拷贝文件
    private static String copyFiles(HttpServletRequest request, String path, String type, String cateKey) throws FileOperateException
    {
        String before = getPrexif(request, File_SEPARATOR);
        String oldPath = before + path;
        try
        {
            path = replaceTag(path, HTML_TAG, File_SEPARATOR);
            String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
            String afterName = fileName.substring(fileName.lastIndexOf("."));
            String newPathName = path.replace(fileName, SerialnoUtils.buildPrimaryKey() + afterName);
            String newPath = before + newPathName;
            org.apache.commons.io.FileUtils.copyFile(new File(oldPath), new File(newPath));
            if (StringUtils.isNotBlank(cateKey))
            {
                imageService.resizeImage(new File(newPath), cateKey, new Watermark());
            }
            return replaceTag(newPathName, File_SEPARATOR, HTML_TAG);
        }
        catch (IOException e)
        {
            throw new FileOperateException(CommonConst.COPY_FAULT);
        }
    }
    
    /**
     * 获取FreeMarket静态页面的路径
     * @param request
     * @return
     * @throws FileOperateException
     * @author 鲍建明
     */
    public static String getStaticHtmlPath(HttpServletRequest request) throws FileOperateException
    {
        return getFileName(getPrexif(request, File_SEPARATOR), STATIC);
    }
    
    /**
     * 获取文章的静态页面路径
     * @param request
     * @return
     * @throws FileOperateException
     * @author 鲍建明
     */
    public static String getArticleHtmlPath(HttpServletRequest request) throws FileOperateException
    {
        return getFileName(getPrexif(request, File_SEPARATOR), ARTICLE);
    }
    
    // 静态页面通用获取文件路径
    private static String getFileName(String before, Integer index) throws FileOperateException
    {
        String filePath = null;
        switch (index)
        {
            case STATIC:
                filePath = getFilePath(before, null, STATIC_HTML, false);
                break;
            case ARTICLE:
                filePath = getFilePath(before, null, ARTICLE_HTML, false);
                break;
            default:
                filePath = null;
                break;
        }
        return filePath;
    }
    
    public static String formatSize(long fileByteSize, String unit)
    {
        double size = (double) fileByteSize;
        long kb = 1024;
        long mb = (kb * 1024);
        long gb = (mb * 1024);
        if (size < kb)
        {
            return String.format("%.0f B", size);
        }
        else if ("KB".equals(unit) || size < mb)
        {
            return String.format("%.2f KB", size / kb);// 保留两位小数
        }
        else if ("MB".equals(unit) || size < gb)
        {
            return String.format("%.2f MB", size / mb);
        }
        else
        {
            return String.format("%.2f GB", size / gb);
        }
    }
    
    /**
     * 上传和删除
     * 先上传再删除
     * 
     * @param file 上传的文件
     * @param type 文件的类型
     * @param oldURL 原来文件的路径
     * @param request
     * @param savepath 需要保持到哪个目录下
     * @param isWater  是否打水印
     * @return 上传后的文件路径
     * @throws FileOperateException
     */
    public static String uploadAndDeleteFile(MultipartFile file, Integer type, String oldURL, HttpServletRequest request, String savepath, boolean isWater)
            throws FileOperateException
    {
        String path = uploadSingle(file, request, savepath, type, isWater);
        deleteFile(new String[]{oldURL}, getPrexif(request, File_SEPARATOR));
        return path;
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
     * 获取请求图片大小的文件服务器URL
     * @param url
     * @param width
     * @param height
     * @return  /upload/brand/img/2014/20140325/19CC5DC98FBA4874A8E1AD727E1B7BDC.png_12*12.png
     */
    public static String getImageDomainPath(String url, Integer width, Integer height)
    { //
        StringBuilder s = new StringBuilder(StringUtils.trim(url));
        if (StringUtils.isNotBlank(url))
        {
            s.append("_").append(width).append("x").append(height).append(".").append(FilenameUtils.getExtension(url)).toString();
        }
        return s.toString();
    }
    
    /**
     * 删除文件                                
     * @param paths 相对路径数组
     * @param prefix 路径前缀 比如 d:/apache tomcat7 ..
     * @author 鲍建明
     */
    public static void deleteFile(String[] paths, String prefix)
    {
        /*
         * try
         * {
         * String[] result = getDeletePath(paths, prefix);
         * if (null != result)
         * {
         * for (int i = 0; i < result.length; i++)
         * {
         * if (StringUtils.isBlank(result[i]))
         * {
         * continue;
         * }
         * File file = new File(result[i]);
         * if (file.isFile() && file.exists())
         * {
         * file.delete();
         * }
         * }
         * }
         * }
         * catch (Exception e)
         * {
         * logger.error("删除文件失败");
         * }
         */
    }
    
    /**
     * 统计文件列表大小
     * @param paths
     * @return long 大小
     * @throws FileNotFoundException 
     * @author 罗盛平
     */
    public static String getFilesByte(String[] paths, String pre) throws FileNotFoundException
    {
        String size = null;
        if (null != paths && paths.length > 0)
        {
            if (null != pre && !pre.equals(""))
            {
                File[] files = new File[paths.length];
                int i = -1;
                for (String path : paths)
                {
                    if (!path.equals(""))
                    {
                        i++;
                        path = pre + File_SEPARATOR + path;
                        File file = new File(path);
                        if (file.isFile() && file.exists() && null != file)
                        {
                            files[i] = file;
                        }
                    }
                }
                if (null != files && files.length > 0)
                {
                    size = formatSize(files);
                }
            }
        }
        return size;
    }
    
    /**
     * 格式化文件大小
     * @param files 文件数组
     * @return 大小  比如10MB
     * @author 罗盛平
     */
    public static String formatSize(File[] files)
    {
        long kb = 1024;
        long mb = (kb * 1024);
        long gb = (mb * 1024);
        Float sizes = 0f;
        if (null != files)
        {
            for (File file : files)
            {
                if (null != file)
                {
                    sizes += file.length();
                }
            }
            if (sizes > 0f)
            {
                if (sizes < kb)
                {
                    return String.format("d% B", sizes);
                }
                else if (sizes < mb)
                {
                    return String.format("%.2f KB", sizes / kb);// 保留两位小数
                }
                else if (sizes < gb)
                {
                    return String.format("%.2f MB", sizes / mb);
                }
                else
                {
                    return String.format("%.2f GB", sizes / gb);
                }
            }
        }
        return null;
    }
    
    /**
     * 获取文件类型，包含点
     * @param filePath
     * @return
     * @author 张昌苗
     */
    public static String getFileType(String filePath)
    {
        return filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
    }
    
    /**
     * 获取Web域名
     * @param url 完整url，不能为空，且为/upload目录
     * @return String http://localhost:8201/Zttx-Web/
     * @author 张昌苗
     */
    public static String getWebDomain(String url)
    {
        return url.substring(0, url.indexOf(ApplicationConst.UPLOAD) + 1);
    }
    
    /**
     * 验证图片格式
     * @param imageType
     * @return boolean true:正确  false:不正确
     */
    public static boolean verifyImg(String imageType)
    {
        boolean flag = false;
        if (!ValidateUtils.isNull(imageType))
        {
            String[] types = ApplicationConst.ALLOW_IMAGE;
            for (int i = 0; i < types.length; i++)
            {
                if (imageType.equals(types[i]))
                {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    
    /**
     * 把路径转化成URL路径（把\替换成/）
     * @param path
     * @return String
     * @author 张昌苗
     */
    public static String parseWebPath(String path)
    {
        if (StringUtils.isBlank(path)) { return ""; }
        return path.replace(File_SEPARATOR, HTML_TAG);
    }
    
    /**
     * 单个文件上传
     * @param photo  图片对象
     * @param path  WebUtils.getRealPath(request.getSession().getServletContext(), ApplicationConst.UPLOAD); 这个路径
     * @param type  文件的类型
     * @return String 图片上传到哪里文件夹的路径   没有成功则返回null
     * @throws FormatException 
     * @throws IOException 
     * @throws IllegalStateException 
     * @throws Exception 
     */
    public static String updateSingle(MultipartFile photo, String path, String type) throws Exception
    {
        if (null == photo || null == path || null == type) { return null; }
        try
        {
            String fileType = photo.getContentType();
            if (verifyImg(fileType))
            {
                String fileName = photo.getOriginalFilename();
                String filePath = getFilePath(path, fileName, type, true);
                photo.transferTo(new File(filePath));
                return getPath(filePath); // TODO 这边要设置文件的URL
                                          // 需要以后进行修改。改换成图片服务器的URL
                                          // 以后需要传入上传到哪个服务器的参数
            }
            else
            {
                throw new Exception("不符合文件格式");
            }
        }
        catch (IllegalStateException e)
        {
            logger.error(e.getMessage());
            throw new Exception("非法语句错误");
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            throw new Exception("文件上传失败");
        }
    }
    
    /**
     * 获取文件路径
     * @param path 完整path，不能为空，且为/upload目录
     * @return String upload\temp\2014\20140306\81791B5DA30D4DCAB4CFF8FEFA978AC3.jpg
     * @author 张昌苗
     */
    public static String getFilePath(String path)
    {
        String upload = ApplicationConst.UPLOAD.replace(HTML_TAG, File_SEPARATOR);
        return path.substring(path.indexOf(upload) + 1);
    }
    
    /**
     * 删除文件                                
     * @param paths 物理路径数组
     * @author 罗盛平
     */
    public static Boolean deleteFile(String[] paths)
    {
        /*
         * try
         * {
         * Boolean flag = false;
         * if (null != paths && paths.length > 0)
         * {
         * for (String path : paths)
         * {
         * if (null != path && !path.equals(""))
         * {
         * File file = new File(path);
         * if (null != file)
         * {
         * if (file.isFile() && file.exists())
         * {
         * file.delete();
         * flag = true;
         * }
         * }
         * }
         * }
         * }
         * return flag;
         * }
         * catch (Exception e)
         * {
         * logger.error("删除文件失败");
         * return false;
         * }
         */
        return true;
    }
    
    /**
     * uploadify 控件批量上传
     * @param request HttpServletRequest
     * @param photo 上传的图片
     * @param type 上传类型
     * @param water 是否水印
     * @return Map<String,String> 图片组 图片名称和路径的map
     * @throws Exception
     * @author 罗盛平    鲍建明修改 批量上传附加生成不同尺寸的缩略图
     */
    public static List<Map<String, String>> uploadifyBatch(HttpServletRequest request, MultipartFile[] photo, String type, Boolean water, String cateKey) throws Exception
    {
        if (null == photo || photo.length <= 0 || null == type) { return null; }
        List<Map<String, String>> list = Lists.newArrayList();
        for (int i = 0; i < photo.length; i++)
        {
            Map<String, String> map = Maps.newHashMap();
            String path_temp = uploadSingle(photo[i], request, type, IMAGE, water);
            resizeImage(path_temp, cateKey, type, request, null);
            map.put("sizeKb", "" + (photo[i].getSize() / 1024 + 1));
            map.put("oldName", photo[i].getOriginalFilename());
            map.put("filePath", path_temp);
            list.add(map);
        }
        return list;
    }
    
    /**
     * 计算文件占百分比
     * @param size 文件大小
     * @param total 总容量
     * @return 百分百
     * @author 罗盛平
     */
    public static String getPercent(long size, long total)
    {
        DecimalFormat df = new DecimalFormat("0.0%");
        float per = ((float) size) / total;
        return df.format(per);
    }
    
    /**
     * 清楚文件名中的非法字符
     * @return
     */
    public static String clean(String fileName)
    {
        if (StringUtils.isBlank(fileName)) { return null; }
        return fileName.replace("\\", "").replace("/", "");
    }
    
    /**
     * 文件下载
     * @param domain 域名 请从字典表中获取   HTTP://1.129.1.209：8080
     * @param downUrl 文件URL   、upload/common/img/32.jpg
     * @param fileName 文件真实名字
     * @return 返回二进制流
     * @throws FileOperateException 
     */
    public static ResponseEntity<byte[]> downLoadFile(String domain, String downUrl, String fileName) throws FileOperateException
    {
        if (StringUtils.isBlank(domain) || StringUtils.isBlank(downUrl)) { throw new FileOperateException(CommonConst.FILE_ADDRESS_NOT_EXIST); }
        try
        {
            return downLoadFile(fileName, new URL(domain + downUrl));
        }
        catch (MalformedURLException e)
        {
            logger.error("文件地址不存在：" + e.getMessage());
            throw new FileOperateException(CommonConst.FILE_ADDRESS_NOT_EXIST);
        }
    }
    
    /**
     * 文件下载
     * @param fileName 文件名
     * @param url URL  HTTP://1.129.1.209：8080/upload/common/img/32.jpg
     * @return
     * @throws FileOperateException 
     */
    public static ResponseEntity<byte[]> downLoadFile(String fileName, URL url) throws FileOperateException
    {
        if (url == null) { throw new FileOperateException(CommonConst.FILE_ADDRESS_NOT_EXIST); }
        if (StringUtils.isBlank(fileName)) { throw new FileOperateException(CommonConst.FILE_NAME_ERROR); }
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            fileName = new String(MultipartUtils.clean(fileName).getBytes(), CharsetConst.CHARSET_ISO);
            headers.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(url.openStream()), headers, HttpStatus.OK);
        }
        catch (UnsupportedEncodingException e1)
        {
            logger.error("下载的文件名有误：" + e1.getMessage());
            throw new FileOperateException(ClientConst.FAILURE.getCode(), "下载的文件名有误");
        }
        catch (IOException e)
        {
            logger.error("下载文件不存在：" + e.getMessage());
            throw new FileOperateException(CommonConst.DOWNLOAD_FILE_NOT_EXITS);
        }
        catch (Exception e2)
        {
            logger.error("文件下载失败：" + e2.getMessage());
            throw new FileOperateException(CommonConst.DOWNLOAD_ERROR);
        }
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
}
