package com.zttx.web.search.solrj;

import java.util.ArrayList;
import java.util.List;

import com.zttx.web.module.fronts.entity.ArticleCate;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.utils.PinyinUtils;

/**
 * <p>File：ArticleSolrHandler.java </p>
 * <p>Title: 资讯索引服务 </p>
 * <p>Description: ArticleSolrHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ArticleSolrHandler
{
    private Logger logger = Logger.getLogger(SolrjHandler.class.getSimpleName());
    
    /**
     * 添加一个资讯到solr服务
     *
     * @param bi
     * @return {@link Boolean}
     */
    public Boolean addArticleInfo(ArticleInfo bi)
    {
        Boolean bl;
        try
        {
            SolrInputDocument doc = convertArticleInfoEntityToDoc(bi);
            bl = SolrjHandler.addDoc(doc, SolrjHandler.CORE_NAME_ARTICLE);
        }
        catch (Exception e)
        {
            logger.error("addArticleInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 批量添加资讯信息到索引服务
     *
     * @param biList
     * @return {@link Boolean}
     */
    public Boolean addArticleInfoList(List<ArticleInfo> biList)
    {
        Boolean bl;
        try
        {
            List<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
            for (ArticleInfo bi : biList)
            {
                SolrInputDocument doc = convertArticleInfoEntityToDoc(bi);
                docList.add(doc);
            }
            bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_ARTICLE);
        }
        catch (Exception e)
        {
            logger.error("addArticleInfoList error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 从搜索引擎中删除
     */
    public Boolean removeArticleInfo(ArticleInfo articleInfo)
    {
        Boolean bl;
        try
        {
            bl = SolrjHandler.delDoc(articleInfo.getRefrenceId(), SolrjHandler.CORE_NAME_ARTICLE);
        }
        catch (Exception e)
        {
            logger.error("remove articleInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 将资讯文章转换为solr索引文档
     *
     * @param bi
     * @return {@link SolrInputDocument}
     */
    private SolrInputDocument convertArticleInfoEntityToDoc(ArticleInfo bi)
    {
        SolrInputDocument doc = new SolrInputDocument();
        Whitelist wl = Whitelist.none();
        doc.addField("refrenceId", bi.getRefrenceId());
        doc.addField("cateId", bi.getCateId());
        for (ArticleCate articleCate : bi.getArticleCates())
        {
            doc.addField("parentCateId", articleCate.getRefrenceId());
        }
        doc.addField("articleTitle", Jsoup.clean(bi.getArticleTitle() != null ? bi.getArticleTitle() : "", wl));
        doc.addField("articleText", Jsoup.clean(bi.getArticleText() != null ? bi.getArticleText() : "", wl));
        doc.addField("titlePinyin", Jsoup.clean(PinyinUtils.spell(bi.getArticleTitle() != null ? bi.getArticleTitle() : ""), wl));
        doc.addField("author", Jsoup.clean(bi.getArticleAuthor(), wl));
        doc.addField("source", bi.getArticleSource());
        doc.addField("collectNum", bi.getCollectNum());
        doc.addField("commentNum", bi.getCommentNum());
        doc.addField("shareNum", bi.getShareNum());
        doc.addField("viewNum", bi.getViewNum());
        doc.addField("createTime", bi.getCreateTime() != null ? bi.getCreateTime() : 0l);
        doc.addField("updateTime", bi.getUpdateTime() != null ? bi.getUpdateTime() : 0l);
        doc.addField("image", bi.getArticleImage());
        doc.addField("isTop", bi.getIsTop());
        doc.addField("isHead", bi.getIsHead());
        doc.addField("isComment", bi.getIsComment());
        doc.addField("isHot", bi.getIsHot());
        return doc;
    }
}
