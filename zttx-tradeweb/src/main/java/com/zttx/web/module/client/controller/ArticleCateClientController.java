package com.zttx.web.module.client.controller;

import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.fronts.entity.ArticleCate;
import com.zttx.web.module.fronts.service.ArticleCateService;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ArticleCateClientController.java </p>
 * <p>Title: ArticleCateClientController </p>
 * <p>Description: 资讯类目对外接口控制器 </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/articleCate")
public class ArticleCateClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(ArticleCateClientController.class);
    
    @Autowired
    private ArticleCateService  articleCateService;
    
    /**
     * 查询
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search")
    public JsonMessage search() throws Exception
    {
        TreeSet<ArticleCate> result = articleCateService.listTop();
        return getJsonMessage(ClientConst.SUCCESS, result);
    }
    
    /**
     * 查询详情
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        ArticleCate articleCate = articleCateService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == articleCate) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(ClientConst.SUCCESS, articleCate);
    }
    
    /**
     * 新增/修改
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        ArticleCate articleCate = new ArticleCate();
        BeanUtils.populate(articleCate, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, articleCate))
        {
            if (StringUtils.isNotBlank(articleCate.getCateIcon())
                    && (articleCate.getCateIcon().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || articleCate.getCateIcon().startsWith(ImageConst.TEMP)))
            {
                String articleImage = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, articleCate.getCateIcon(), "");
                articleCate.setCateIcon(articleImage);
            }
            articleCateService.saveByClient(articleCate);
        }
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        articleCateService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(ClientConst.SUCCESS);
    }
}