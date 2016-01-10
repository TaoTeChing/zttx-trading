package com.zttx.web.search.solrj;

import java.util.List;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.zttx.web.module.fronts.entity.RulesCate;
import com.zttx.web.module.fronts.entity.RulesInfo;

/**
 * <p>File：RuleSolrHandler.java </p>
 * <p>Title: 规则中心索引服务 </p>
 * <p>Description: RuleSolrHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class RuleSolrHandler
{
    private Logger logger = Logger.getLogger(RuleSolrHandler.class.getSimpleName());
    
    /**
     * 添加一个RulesInfo entity 到solr服务
     *
     * @param bi
     * @return {@link Boolean}
     */
    public Boolean addRulesInfo(RulesInfo bi)
    {
        Boolean bl = Boolean.FALSE;
        try
        {
            SolrInputDocument doc = convertRulesInfoEntityToDoc(bi);
            if (doc != null) bl = SolrjHandler.addDoc(doc, SolrjHandler.CORE_NAME_RULES);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("addRulesInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 添加一个规则信息 到solr服务
     *
     * @param biList
     * @return {@link Boolean}
     */
    public Boolean addRulesInfoList(List<RulesInfo> biList)
    {
        Boolean bl;
        try
        {
            List<SolrInputDocument> docList = Lists.newArrayList();
            for (RulesInfo bi : biList)
            {
                SolrInputDocument doc = convertRulesInfoEntityToDoc(bi);
                if (doc != null) docList.add(doc);
            }
            bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_RULES);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("addRulesInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 从搜索引擎中删除
     */
    public Boolean removeRulesInfo(RulesInfo rulesInfo)
    {
        Boolean bl;
        try
        {
            bl = SolrjHandler.delDoc(rulesInfo.getRefrenceId(), SolrjHandler.CORE_NAME_RULES);
        }
        catch (Exception e)
        {
            logger.error("remove rulesInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 规则对象转换
     *
     * @param bi
     * @return {@link SolrInputDocument}
     */
    private SolrInputDocument convertRulesInfoEntityToDoc(RulesInfo bi)
    {
        SolrInputDocument doc = new SolrInputDocument();
        Whitelist wl = Whitelist.none();
        doc.addField("refrenceId", bi.getRefrenceId());
        doc.addField("infoCateId", bi.getCateId());
        for (RulesCate rulesCate : bi.getRulesCates())
        {
            doc.addField("parentCateId", rulesCate.getRefrenceId());
        }
        doc.addField("articleTitle", Jsoup.clean(bi.getArticleTitle() != null ? bi.getArticleTitle() : "", wl));
        doc.addField("articleText", Jsoup.clean(bi.getArticleText() != null ? bi.getArticleText() : "", wl));
        doc.addField("domainName", bi.getDomainName());
        doc.addField("articleImage", bi.getArticleImage());
        doc.addField("createTime", bi.getCreateTime() != null ? bi.getCreateTime() : 0l);
        doc.addField("updateTime", bi.getUpdateTime() != null ? bi.getUpdateTime() : 0l);
        doc.addField("viewNum", bi.getViewNum());
        return doc;
    }
}
