package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandsVideo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandsVideoService;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * <p>File：BrandsVideoController.java</p>
 * <p>Title: 品牌模特视频管理接口</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-6-5 下午4:55:10</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandsVideo")
public class BrandsVideoClientController extends GenericController
{
    @Autowired
    private BrandsVideoService brandsVideoService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
   
    /**
     * 列表
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search")
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandsVideo searchBean = new BrandsVideo();
        BeanUtils.populate(searchBean, map);
        searchBean.setPage(page);
        PaginateResult<Map<String,Object>> result = new PaginateResult<Map<String,Object>>(page,brandsVideoService.findBrandsVideoMap(searchBean));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 新增/修改
     * refrenceId==null：新增
     * refrenceId!=null：修改
     * @param request
     * @param param
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandsVideo brandsVideo = new BrandsVideo();
        BeanUtils.populate(brandsVideo, map);
        brandsVideo.setUploadIp(IPUtil.formatStrIpToInt(MapUtils.getString(map, "uploadIp")));
        // 参数校验
        List<String> errorList = this.verifyBrandsVideo(brandsVideo);
        if (!errorList.isEmpty())
        {
            JsonMessage json = super.getJsonMessage(ClientConst.FAILURE);
            json.setMessage(errorList.toString());
            return json;
        }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandsVideo.getBrandsId());
        brandsVideo.setBrandId(brandesInfo.getBrandId());
        if (StringUtils.isBlank(brandsVideo.getRefrenceId()))
        {
        	brandsVideoService.insert(brandsVideo);
        }
        else
        {
            brandsVideoService.updateByPrimaryKey(brandsVideo);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 删除
     * @param uuid
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        brandsVideoService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    // 参数验证方法 ==============================================================================================
    private List<String> verifyBrandsVideo(BrandsVideo brandsVideo)
    {
        List<String> list = Lists.newArrayList();
        Integer width = brandsVideo.getWidth();
        if (null == width)
        {
            list.add("视频宽不能为空");
        }
        else if (width <= 0 || width > 1000)
        {
            list.add("视频宽不在使用范围");
        }
        Integer height = brandsVideo.getHeight();
        if (null == height)
        {
            list.add("视频高不能为空");
        }
        else if (height <= 0 || height > 1000)
        {
            list.add("视频高不在使用范围");
        }
        if (null == brandsVideo.getUploadIp())
        {
            list.add("上传者IP不能为空");
        }
        if (ValidateUtils.isNull(brandsVideo.getBrandsId()))
        {
            list.add("品牌编号不能为空");
        }
        if (ValidateUtils.isNull(brandsVideo.getVideoName()))
        {
            list.add("视频地址不能为空");
        }
        return list;
    }
}
