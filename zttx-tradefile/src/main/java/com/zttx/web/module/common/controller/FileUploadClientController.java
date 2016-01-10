package com.zttx.web.module.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.FileSizeEmun;
import com.zttx.web.module.common.entity.UploadFile;
import com.zttx.web.utils.MultipartUtils;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：FileUploadClientController.java </p>
 * <p>Title: FileUploadClientController </p>
 * <p>Description: FileUploadClientController </p>
 * <p>Copyright: Copyright (c) 2014 08/10/2015 14:31</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/client")
public class FileUploadClientController extends GenericController
{
    private static final Logger logger = Logger.getLogger(FileUploadClientController.class);
    
    /**
     * 上传的临时文件的方法
     * 默认存放在temp文件夹中
     * @param file
     * @param request
     * @param param _fileSize 检查文件大小 _savePath 自定义文件保存路径
     * @return {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonMessage upload(@RequestParam MultipartFile file, HttpServletRequest request, ClientParameter param)
    {
        JsonMessage json = null;
        try
        {
            Map<String, String> map = ParameterUtils.getMapFromParameter(param);
            // 检查文件大小 默认F3M
            MultipartUtils.checkFileSize(file.getSize(), MapUtils.getString(map, "_fileSize", FileSizeEmun.F3M.getCode().toString()), MultipartUtils.COMMON);
            // 上传 默认临时目录
            String urlPath = MultipartUtils.uploadFile(file, request, MapUtils.getString(map, "_savePath", MultipartUtils.TEMP), false);
            UploadFile uploadFile = new UploadFile(file.getOriginalFilename(), urlPath);
            json = this.getJsonMessage(CommonConst.SUCCESS, uploadFile); // 成功码为:126000
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
     * 移动图片
     * @param request
     * @param param
     * @return  {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/moveImg", method = RequestMethod.POST)
    public JsonMessage moveImg(HttpServletRequest request, ClientParameter param) throws FileOperateException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String type = MapUtils.getString(map, "type", null);
        String newURL = MapUtils.getString(map, "newURL", null);
        String cateKey = MapUtils.getString(map, "cateKey", null);
        String result = MultipartUtils.moveAndresizeFile(request, type, newURL, cateKey);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 文件拷贝
     * @param request
     * @param param
     * @return  {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/copyFile", method = RequestMethod.POST)
    public JsonMessage copyFile(HttpServletRequest request, ClientParameter param) throws FileOperateException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String path = MapUtils.getString(map, "path", null);
        String cateKey = MapUtils.getString(map, "cateKey", null);
        String result = MultipartUtils.copyFileAndResize(request, path, cateKey);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 移动文件
     * @param request
     * @param param
     * @return  {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping(value = "/moveFile", method = RequestMethod.POST)
    public JsonMessage moveFile(HttpServletRequest request, ClientParameter param) throws FileOperateException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String type = MapUtils.getString(map, "type", null);
        String newURL = MapUtils.getString(map, "newURL", null);
        String result = MultipartUtils.moveAndDeleteFile(request, type, newURL, null);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 批量上传文件
     * @param files
     * @param request
     * @param param
     * @return  {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/batchUpload", method = RequestMethod.POST)
    public JsonMessage batchUpload(@RequestParam MultipartFile[] files, HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        String[] urlPath = MultipartUtils.uploadFile(files, request, MultipartUtils.TEMP);
        urlPath = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.COMMON_FILE_PATH, urlPath, null);
        return this.getJsonMessage(CommonConst.SUCCESS, urlPath);
    }
    
    /**
     * 批量上传图片
     * @param files
     * @param request
     * @param param
     * @return  {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/batchUploadImg", method = RequestMethod.POST)
    public JsonMessage batchUploadImg(@RequestParam MultipartFile[] files, HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        String[] urlPath = MultipartUtils.uploadImage(files, request, MultipartUtils.TEMP, false);
        urlPath = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.COMMON_IMG_PATH, urlPath, null);
        return this.getJsonMessage(CommonConst.SUCCESS, urlPath);
    }
    
    /**
     * 文本编辑器图片上传（直接上传）
     * @param file
     * @param request
     * @return  {@link JsonMessage}
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/htmlUpload", method = RequestMethod.POST)
    public JsonMessage htmlUpload(@RequestParam MultipartFile file, HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        MultipartUtils.checkFileSize(file.getSize(), request.getParameter("fSize"), MultipartUtils.COMMON);
        String urlPath = MultipartUtils.uploadFile(file, request, MultipartUtils.TEMP, false);
        urlPath = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.COMMON_IMG_PATH, urlPath, null);
        UploadFile uploadFile = new UploadFile(file.getOriginalFilename(), urlPath);
        return this.getJsonMessage(CommonConst.SUCCESS, uploadFile);
    }
    
    /**
     * 品牌模特视频上传
     * @param file
     * @param request
     * @return  {@link JsonMessage}
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/videoUpload", method = RequestMethod.POST)
    public JsonMessage videoUpload(@RequestParam MultipartFile file, HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        String videPath = MultipartUtils.uploadFlv(file, request, MultipartUtils.VIDEO_PATH);
        return this.getJsonMessage(CommonConst.SUCCESS, videPath);
    }
    
    /**
     * 客服上传专用（直接上传）
     * @param file
     * @param request
     * @return  {@link JsonMessage}
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/customerUpload", method = RequestMethod.POST)
    public JsonMessage customerUpload(@RequestParam MultipartFile file, HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        MultipartUtils.checkFileSize(file.getSize(), request.getParameter("fSize"), MultipartUtils.COMMON);
        String urlPath = MultipartUtils.uploadFile(file, request, MultipartUtils.TEMP, false);
        urlPath = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.CUSTOMER_IMG_PATH, urlPath, null);
        UploadFile uploadFile = new UploadFile(file.getOriginalFilename(), urlPath);
        return this.getJsonMessage(CommonConst.SUCCESS, uploadFile);
    }
}
