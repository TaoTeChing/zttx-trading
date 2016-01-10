/*
 * @(#)CustomerServiceClientController.java 2014-7-4 下午1:58:58
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.io.IOException;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.service.BrandServiceService;
import com.zttx.web.module.common.model.Customer;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：CustomerServiceClientController.java</p>
 * <p>Title: 替换客服信息的接口 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-4 下午1:58:58</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/customerService")
public class CustomerClientController extends GenericController
{
    @Autowired
    private BrandServiceService brandServiceService;
    
    /**
     * 客服信息保存/修改 
     * @param request
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param, MultipartFile mainPhoto) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Customer customer = new Customer();
        BeanUtils.populate(customer, map);
        customer.setSex(MapUtils.getShort(map, "sex"));
        String filePath = "";
        JsonMessage json = null;
        Map<String, Object> params = Maps.newHashMap();
        if(null != mainPhoto){
        	try
            {
                json = FileClientUtil.getJsonMessage(params, "/client/customerUpload", mainPhoto.getBytes(), "file", mainPhoto.getOriginalFilename(), mainPhoto.getContentType());
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(json.getObject()));
                filePath = jsonObject.get("urlPath").toString();
            }
            catch (BusinessException e)
            {
                return this.getJsonMessage(e.getErrorCode(),e.getMessage());
            }
            catch (IOException e)
            {
            	return super.getJsonMessage(CommonConst.IMG_SAVE_FAULT);
            }
        }
        customer.setMainPhotoPath(filePath);
        brandServiceService.save(customer);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
