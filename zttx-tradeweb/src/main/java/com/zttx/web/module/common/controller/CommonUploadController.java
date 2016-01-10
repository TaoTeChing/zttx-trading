package com.zttx.web.module.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zttx.goods.consts.ApplicationEnum;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.exception.FileOperateException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.MultiPartUtils;

/**
 * 文件上传
 * <p>File：CommonUploadController.java</p>
 * <p>Title: CommonUploadController</p>
 * <p>Description:CommonUploadController</p>
 * <p>Copyright: Copyright (c) Aug 15, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping("/common")
public class CommonUploadController extends GenericController
{
	
	/**
     * 文件通用上传
     * 只判断文件的后缀，请慎用
     * @param file
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/showFile", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, String fSize) throws BusinessException
    {
        Map<String, Object> params = Maps.newHashMap();
        params.put("fSize", fSize);
        JsonMessage json = null;
		try {
			json = FileClientUtil.getJsonMessage(params, "/common/showFile",file.getBytes(),"file",file.getOriginalFilename(),file.getContentType());
			json.setObject(file.getOriginalFilename());
		} catch (BusinessException e) {
			throw e;
		} catch (IOException e) {
			throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
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
    public JsonMessage showImage(@RequestParam MultipartFile photo, HttpServletRequest request, String fSize) throws BusinessException
    {
    	Map<String, Object> params = Maps.newHashMap();
        params.put("fSize", fSize);
        JsonMessage json = null;
		try {
			json = FileClientUtil.getJsonMessage(params, "/common/showImg",photo.getBytes(),"photo",photo.getOriginalFilename(),photo.getContentType());
			json.setObject(photo.getOriginalFilename());
		} catch (BusinessException e) {
			throw e;
		} catch (IOException e) {
			throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
		}
        return json;
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
    public JsonMessage showZip(@RequestParam MultipartFile zip, HttpServletRequest request, String fSize) throws BusinessException
    {
        Map<String, Object> params = Maps.newHashMap();
        params.put("fSize", fSize);
        JsonMessage json = null;
		try {
			json = FileClientUtil.getJsonMessage(params, "/common/showZip",zip.getBytes(),"zip",zip.getOriginalFilename(),zip.getContentType());
			json.setObject(zip.getOriginalFilename());
		} catch (BusinessException e) {
			throw e;
		} catch (IOException e) {
			throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
		}
        return json;
    }
    
   /**
     * 文件通用上传
     * 只判断文件的后缀，请慎用
     * @param file
     * @param request
     * @return JsonMessage
     *
    @RequestMapping(value = "/showFile", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, String fSize, String requiredFileTypes)
    {
        Map<String, Object> params = Maps.newHashMap();
        params.put("requiredFileTypes", requiredFileTypes);
        JsonMessage json = null;
        try
        {
            json = FileClientUtil.getJsonMessage(params, "/common/showFile",file.getBytes(),"file",file.getOriginalFilename(),file.getContentType());
        }
        catch (FileOperateException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (BusinessException e)
        {
            json = this.getJsonMessage(e.getErrorCode());
        }
        catch (IOException e)
        {
            json = this.getJsonMessage(ApplicationEnum.APP_ERROR);
        }
        return json;
    }*/
    
    /**
     * 文件通用上传
     * 只判断文件的后缀，请慎用
     * @param file
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/showFileLocal", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage uploadFileLocal(@RequestParam MultipartFile file, HttpServletRequest request, String fSize, String requiredFileTypes)
    {
        
        JsonMessage json = null;
        try
        {
            MultiPartUtils.checkFileType(file.getOriginalFilename(), requiredFileTypes);
            MultiPartUtils.checkFileSize(file.getSize(), fSize, MultiPartUtils.COMMON);
            String urlPath=MultiPartUtils.uploadFile(file, request, MultiPartUtils.COMMON_FILE_PATH);
            json= getJsonMessage(CommonConst.SUCCESS.getCode(),urlPath, file.getOriginalFilename());
        }
        catch (BusinessException e)
        {
            json=new JsonMessage(ApplicationEnum.APP_ERROR);
        }

        return json;
    }
    
    /**
     * 只用于百度编辑器
     * @param photo
     * @param request
     * @return JsonMessage
     */
    @RequestMapping(value = "/editor/upfile", method = RequestMethod.POST)
    @ResponseBody
    public void editorUpload(@RequestParam MultipartFile upfile, String editorid, String type, String fSize, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
        	String urlPath = "";
        	Map<String, Object> params = Maps.newHashMap();
            params.put("fSize", fSize);
            params.put("requiredFileTypes", ImageConst.IMAGE);
            JsonMessage json = null;
    		try {
    			json = FileClientUtil.getJsonMessage(params, "/common/showFile",upfile.getBytes(),"file",upfile.getOriginalFilename(),upfile.getContentType());
    			urlPath = ZttxConst.FILEAPI_WEBURL+json.getMessage();
    		} catch (BusinessException e) {
    			throw e;
    		} catch (IOException e) {
    			throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
    		}
            if (StringUtils.isBlank(type))
            {
                getOut(response).print("<script>parent.UM.getEditor('" + editorid + "').getWidgetCallback('image')('" + urlPath + "','SUCCESS')</script>");
            }
            else
            {
                getOut(response).print(urlPath == null ? "" : urlPath);
            }
        }
        catch (BusinessException e)
        {
            response.setCharacterEncoding(CharsetConst.CHARSET_UT);
            getOut(response).print(
                    "<script>parent.UM.getEditor('" + editorid + "').getWidgetCallback('image')('" + JSON.toJSONString(super.getJsonMessage(e.getErrorCode()))
                            + "','error')</script>");
        }
    }
    
    /**
     * 获取输出流
     * @param response
     * @return
     */
    private PrintWriter getOut(HttpServletResponse response)
    {
        try
        {
            return response.getWriter();
        }
        catch (IOException e)
        {
        }
        return null;
    }
}
