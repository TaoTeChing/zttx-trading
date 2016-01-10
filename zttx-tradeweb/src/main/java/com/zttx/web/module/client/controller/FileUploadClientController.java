package com.zttx.web.module.client.controller;

import java.io.IOException;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.utils.FileClientUtil;

/**
 * <p>File：FileUploadController.java </p>
 * <p>Title: FileUploadController </p>
 * <p>Description: 文件上传接口 </p>
 * <p>Copyright: Copyright (c) 15/9/6</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT)
public class FileUploadClientController extends GenericController
{
    private static final Logger logger = Logger.getLogger(FileUploadClientController.class);
    
    /**
     * 存放上传的临时文件的方法
     * 存放在temp文件夹中
     * @param file
     * @return JsonMessage
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonMessage upload(@RequestParam MultipartFile file)
    {
        JsonMessage json = null;
        Map<String, Object> params = Maps.newHashMap();
        try
        {
            json = FileClientUtil.getJsonMessage(params, "/client/upload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
        return json;
    }
    
    /**
     * 文本编辑器图片上传（直接上传）
     * @param file
     * @param request
     * @return
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/htmlUpload", method = RequestMethod.POST)
    public JsonMessage htmlUpload(@RequestParam MultipartFile file, ClientParameter param) throws BusinessException
    {
    	JsonMessage json = null;
        Map<String, Object> params = Maps.newHashMap();
        try
        {
            json = FileClientUtil.getJsonMessage(params, "/client/htmlUpload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
        return json;
    }
    
    /**
     * 品牌模特视频上传
     * @param file
     * @param request
     * @return
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/videoUpload", method = RequestMethod.POST)
    public JsonMessage videoUpload(@RequestParam MultipartFile file, ClientParameter param) throws BusinessException
    {
        JsonMessage json = null;
        Map<String, Object> params = Maps.newHashMap();
        try
        {
            json = FileClientUtil.getJsonMessage(params, "/client/videoUpload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
        return json;
    }
    
    /**
     * 客服上传专用（直接上传）
     * @param file
     * @param request
     * @return
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/customerUpload", method = RequestMethod.POST)
    public JsonMessage customerUpload(@RequestParam MultipartFile file, ClientParameter param) throws BusinessException
    {
    	JsonMessage json = null;
        Map<String, Object> params = Maps.newHashMap();
        try
        {
            json = FileClientUtil.getJsonMessage(params, "/client/customerUpload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
        return json;
    }
}
