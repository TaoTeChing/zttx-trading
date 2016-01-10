package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.EntityValidateUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandNews;
import com.zttx.web.module.brand.model.BrandNewsModel;
import com.zttx.web.module.brand.service.BrandNewsService;
import com.zttx.web.module.brand.service.BrandNewscateService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;

/**
 * 品牌商新闻资讯 控制器
 * <p>File：BrandNewsController.java </p>
 * <p>Title: BrandNewsController </p>
 * <p>Description:BrandNewsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandNews")
public class BrandNewsController extends GenericController
{
    @Autowired(required = true)
    private BrandNewsService     brandNewsService;
    
    @Autowired
    private BrandNewscateService brandNewscateService;
    
    @Autowired
    private BrandesInfoService   brandesInfoService;
    
    /**
     * 列表
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping("/news")
    public String brandNewsList(Model model)
    {
        model.addAttribute("brandNewscate", brandNewscateService.selectAll());// 咨询类型
        model.addAttribute("nowtime", CalendarUtils.getCurrentLong());
        return "brand/list_brandNews";
    }
    
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping("/ajaxList")
    public JsonMessage ajaxList(Pagination page, @RequestParam(value = "newscate", defaultValue = "") String newscate,
            @RequestParam(value = "state", defaultValue = "") String state) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandNews filter = new BrandNews();
        filter.setBrandId(brandId);
        filter.setCateId(newscate);
        filter.setState(state);
        filter.setNowtime(CalendarUtils.getCurrentLong());
        filter.setPage(page);
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>(page, brandNewsService.listBrandNews(filter));
        JsonMessage json = super.getJsonMessage(CommonConst.SUCCESS, result);
        return json;
    }
    
    /**
     * 添加/修改 页面
     * @param request
     * @return
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping("/execute")
    public String brandNewsCreate(Model model, String brandNewsId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        model.addAttribute("cates", brandNewscateService.selectAll());// 咨询类型
        model.addAttribute("brandesInfos", brandesInfoService.getCooperatedBrandes(brandId));
        model.addAttribute("fileUrl", ZttxConst.FILEAPI_WEBURL);
        if (StringUtils.isNotBlank(brandNewsId))
        {
            BrandNews result = brandNewsService.selectByPrimaryKey(brandNewsId);
            model.addAttribute("result", result);
        }
        return "brand/edit_brandNews";
    }
    
    /**
     * 加载 新闻内容
     * @param request
     * @param uuid
     * @param model
     * @return
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public JsonMessage loadContent(@RequestParam("uuid") String uuid, Model model) throws BusinessException
    {
        OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandNews result = brandNewsService.selectByPrimaryKey(uuid);
        if (result != null)
        {
            return getJsonMessage(ExceptionConst.SUCCESS, (Object) result.getNewsContent());
        }
        else
        {
            return getJsonMessage(ExceptionConst.NOEXITS);
        }
    }
    
    /**
     * 删除新闻资讯
     * @param request
     * @return
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonMessage brandNewsDelete(String brandNewsId) throws BusinessException
    {
        OnLineUserUtils.getCurrentBrand().getRefrenceId();// 品牌商
        brandNewsService.delete(brandNewsId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 保存新闻咨询
     * @param request
     * @param brandNews
     * @return
     * @throws Exception 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String brandNewsSave(BrandNewsModel brandNewsModel, String[] attachtNames, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();// 品牌商
        brandNewsModel.setBrandId(brandId);
        try
        {
            EntityValidateUtils.validateWithException(validator, brandNewsModel);
        }
        catch (ConstraintViolationException ex)
        {
            List<String> list = EntityValidateUtils.extractMessage(ex);
            addMessage(model, list.toArray(new String[]{}));
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        if (null != attachtNames && attachtNames.length > 0)
        {
            for (int i = 0; i < attachtNames.length; i++)
            {
                attachtNames[i] = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, attachtNames[i], UploadAttCateConst.NEWS);
            }
            // 设置图片属性
            brandNewsModel.setImageUrl(attachtNames[0]);
        }
        brandNewsService.insertBrandNews(brandNewsModel);
        return "redirect:/brand/brandNews/news";
    }
}
