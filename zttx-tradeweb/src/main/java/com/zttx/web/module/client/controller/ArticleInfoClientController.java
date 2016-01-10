package com.zttx.web.module.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.module.fronts.service.ArticleCateService;
import com.zttx.web.module.fronts.service.ArticleInfoService;
import com.zttx.web.search.solrj.ArticleSolrHandler;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ArticleInfoClientController.java </p>
 * <p>Title: ArticleInfoClientController </p>
 * <p>Description: 资讯信息对外接口控制器 </p>
 * <p>Copyright: Copyright (c) 15/9/2</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/articleInfo")
public class ArticleInfoClientController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(ArticleInfoClientController.class);
    
    @Autowired
    private ArticleInfoService  articleInfoService;
    
    @Autowired
    private ArticleSolrHandler  articleSolrHandler;
    
    @Autowired
    private ArticleCateService  articleCateService;
    
    /**
     * 列表
     * @param param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        // 查询条件
        ArticleInfo articleInfoModel = new ArticleInfo();
        articleInfoModel.setIsComment(null);
        articleInfoModel.setIsHot(null);// "isHot" -> "1" 字符串 转 boolean 需要注意
        articleInfoModel.setIsTop(null);
        articleInfoModel.setIsHead(null);// 由于默认false，不作为查询条件设null
        BeanUtils.populate(articleInfoModel, map);
        articleInfoModel.setKeywords(MapUtils.getString(map, "keywords", null));
        // 排序条件
        ArticleInfo.OrderCriteria orderCriteria = new ArticleInfo.OrderCriteria();
        BeanUtils.populate(orderCriteria, map);
        PaginateResult<ArticleInfo> result = articleInfoService.search(page, articleInfoModel, orderCriteria);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查询
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        ArticleInfo articleInfo = articleInfoService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == articleInfo) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, articleInfo);
    }
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     *
     * @author 章旭楠
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(ClientParameter param, HttpServletRequest request) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        ArticleInfo articleInfo = new ArticleInfo();
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        try
        {
            BeanUtils.populate(articleInfo, map);
            String articleText = request.getParameter(ClientConst.HTML);
            articleInfo.setArticleText(articleText);
            if (beanValidator(jsonMessage, articleInfo))
            {
                if (StringUtils.isNotBlank(articleInfo.getArticleImage())
                        && (articleInfo.getArticleImage().startsWith(ImageConst.File_SEPARATOR + ImageConst.TEMP) || articleInfo.getArticleImage().startsWith(
                                ImageConst.TEMP)))
                {
                    String articleImage = FileClientUtil.moveAndDeleteFile(ImageConst.COMMON_IMG_PATH, articleInfo.getArticleImage(), "");
                    articleInfo.setArticleImage(articleImage);
                }
                articleInfo = articleInfoService.selectByPrimaryKey(articleInfoService.saveByClient(articleInfo).getRefrenceId());
                articleInfo.setArticleCates(articleCateService.getParentArticleCates(articleInfo.getCateId()));
                articleSolrHandler.addArticleInfo(articleInfo);
            }
        }
        catch (BusinessException e)
        {
            logger.error("资讯信息对外接口,调用保存失败: " + e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param refrenceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        ArticleInfo articleInfo = articleInfoService.selectByPrimaryKey(refrenceId);
        if (null != articleInfo)
        {
            articleInfoService.delete(refrenceId);
            articleSolrHandler.removeArticleInfo(articleInfo);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
