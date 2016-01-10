package com.zttx.web.module.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.utils.MultipartUtils;

/**
 * <p>File：FileUploadController.java </p>
 * <p>Title: FileUploadController </p>
 * <p>Description: FileUploadController </p>
 * <p>Copyright: Copyright (c) 2014 08/10/2015 13:34</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/common")
public class FileUploadController extends GenericController
{
    private static final Logger logger = Logger.getLogger(FileUploadController.class);
    
    /**
     * 文件通用上传
     * 只判断文件的后缀，请慎用
     * @param file
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/showFile", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, String fSize, String requiredFileTypes)
    {
        JsonMessage json = null;
        try
        {
            MultipartUtils.checkFileType(file.getOriginalFilename(), requiredFileTypes);
            MultipartUtils.checkFileSize(file.getSize(), fSize, MultipartUtils.COMMON);
            String urlPath = MultipartUtils.uploadFile(file, request, MultipartUtils.TEMP, false);
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), urlPath, file.getOriginalFilename());// 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    /**
     * 只用于百度编辑器文件上传
     * @param file
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/editor/upfile", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage uploadEditorFile(@RequestParam MultipartFile file, HttpServletRequest request, String fSize, String requiredFileTypes)
    {
        JsonMessage json = null;
        try
        {
            MultipartUtils.checkFileSize(file.getSize(), fSize, MultipartUtils.IMAGE);
            String urlPath = MultipartUtils.uploadImage(file, request, MultipartUtils.EDITOR_IMG_PATH, false);
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), urlPath, file.getOriginalFilename());// 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    /**
     * 图片暂时存放的方法
     * 存放在temp文件夹中
     * 图片验证请重写verifyImg方法
     * 默认不验证
     * @param photo
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/showImg", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage showImage(@RequestParam MultipartFile photo, HttpServletRequest request, String fSize)
    {
        JsonMessage json;
        try
        {
            MultipartUtils.checkFileSize(photo.getSize(), fSize, MultipartUtils.IMAGE);
            String urlPath = MultipartUtils.uploadFile(photo, request, MultipartUtils.TEMP, false);
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), urlPath, photo.getOriginalFilename()); // 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    /**
     * 视频暂时存放
     * 视频验证请重写verifyVideo方法
     * 默认不验证
     * @param video
     * @param request
     * @return
     */
    @RequestMapping(value = "/showVideo", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage showVideo(@RequestParam MultipartFile video, HttpServletRequest request, String fSize)
    {
        JsonMessage json = null;
        try
        {
            MultipartUtils.checkFileSize(video.getSize(), fSize, MultipartUtils.VIDEO);
            Map<String, String> result = MultipartUtils.uploadVideo(video, request, MultipartUtils.TEMP);
            result.put("videoName", video.getOriginalFilename());
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), result.get("video"), result); // 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    /**
     * Flash暂时存放
     * 图片验证请重写verifyFlash方法
     * 默认不验证
     * @param flash
     * @param request
     * @return
     */
    @RequestMapping(value = "/showFlash", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage showFlash(@RequestParam MultipartFile flash, HttpServletRequest request, String fSize)
    {
        JsonMessage json = null;
        try
        {
            MultipartUtils.checkFileSize(flash.getSize(), fSize, MultipartUtils.FLASH);
            Map<String, String> result = MultipartUtils.uploadFlash(flash, request, MultipartUtils.TEMP);
            result.put("videoName", flash.getOriginalFilename());
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), result.get("video"), result); // 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    @RequestMapping("/delete/file")
    @ResponseBody
    public JsonMessage deleteFile(String filePath){
        String[] paths=new String[]{filePath};
        MultipartUtils.deleteFile(paths);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * Zip 暂时存放
     * @param zip
     * @param request
     * @param fSize
     * @return
     */
    @RequestMapping(value = "/showZip", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage showZip(@RequestParam MultipartFile zip, HttpServletRequest request, String fSize)
    {
        JsonMessage json = null;
        try
        {
            MultipartUtils.checkFileSize(zip.getSize(), fSize, MultipartUtils.ZIP);
            String urlPath = MultipartUtils.uploadZip(zip, request, MultipartUtils.TEMP);
            json = this.getJsonMessage(CommonConst.SUCCESS.getCode(), urlPath, zip.getOriginalFilename()); // 成功码为:126000
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
}
