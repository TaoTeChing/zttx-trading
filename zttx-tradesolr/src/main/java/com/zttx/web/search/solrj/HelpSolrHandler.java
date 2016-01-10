package com.zttx.web.search.solrj;

import java.util.List;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.zttx.web.module.fronts.entity.HelpCate;
import com.zttx.web.module.fronts.entity.HelpInfo;

/**
 * <p>File：HelpSolrHandler.java </p>
 * <p>Title: 帮助中心索引服务 </p>
 * <p>Description: HelpSolrHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class HelpSolrHandler
{
    private Logger logger = Logger.getLogger(HelpSolrHandler.class.getSimpleName());
    
    /**
     * 添加帮助信息
     *
     * @param bi
     * @return {@link Boolean}
     */
    public Boolean addHelpInfo(HelpInfo bi)
    {
        Boolean bl = Boolean.FALSE;
        try
        {
            SolrInputDocument doc = convertHelpInfoEntityToDoc(bi);
            if (doc != null) bl = SolrjHandler.addDoc(doc, SolrjHandler.CORE_NAME_HELP);
        }
        catch (Exception e)
        {
            logger.error("addRulesInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 批量添加帮助索引
     *
     * @param biList
     * @return {@link Boolean}
     */
    public Boolean addHelpInfoList(List<HelpInfo> biList)
    {
        Boolean bl;
        try
        {
            List<SolrInputDocument> docList = Lists.newArrayList();
            for (HelpInfo bi : biList)
            {
                SolrInputDocument doc = convertHelpInfoEntityToDoc(bi);
                if (doc != null) docList.add(doc);
            }
            bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_HELP);
        }
        catch (Exception e)
        {
            logger.error("addHelpInfoList error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 从搜索引擎中删除
     */
    public Boolean removeHelpInfo(HelpInfo helpInfo)
    {
        Boolean bl;
        try
        {
            bl = SolrjHandler.delDoc(helpInfo.getRefrenceId(), SolrjHandler.CORE_NAME_HELP);
        }
        catch (Exception e)
        {
            logger.error("remove helpInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 将信息 存入索引库
     *
     * @param hi
     * @return {@link SolrInputDocument}
     */
    private SolrInputDocument convertHelpInfoEntityToDoc(HelpInfo hi)
    {
        SolrInputDocument doc = new SolrInputDocument();
        Whitelist wl = Whitelist.none();
        doc.addField("refrenceId", hi.getRefrenceId());
        doc.addField("helpCateId", hi.getHelpCateId() != null ? hi.getHelpCateId() : "");
        for (HelpCate helpCate : hi.getHelpCates())
        {
            doc.addField("parentCateId", helpCate.getRefrenceId());
        }
        doc.addField("helpNo", hi.getHelpNo());
        doc.addField("helpTitle", Jsoup.clean(hi.getTitle() != null ? hi.getTitle() : "", wl));
        doc.addField("helpText", Jsoup.clean(hi.getHtmlText() != null ? hi.getHtmlText() : "", wl));
        doc.addField("isRecommand", hi.getIsRecommand());
        doc.addField("isHot", hi.getIsHot());
        doc.addField("isFaq", hi.getIsFaq());
        doc.addField("createTime", hi.getCreateTime() != null ? hi.getCreateTime() : 0l);
        doc.addField("updateTime", hi.getUpdateTime() != null ? hi.getUpdateTime() : 0l);
        return doc;
    }
}
