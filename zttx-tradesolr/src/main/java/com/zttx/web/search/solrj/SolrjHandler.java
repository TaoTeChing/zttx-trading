package com.zttx.web.search.solrj;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.search.module.SolrResponse;
import com.zttx.web.utils.HttpUtils;

/**
 * <p>File：SolrjHandler.java </p>
 * <p>Title: SOLR服务器操作接口 </p>
 * <p>Description: SolrjHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class SolrjHandler
{
    private static Logger      logger            = Logger.getLogger(SolrjHandler.class);
    
    public static final String CORE_NAME_BRANDES = "brandes";
    
    public static final String CORE_NAME_PRODUCT = "product";
    
    public static final String CORE_NAME_ARTICLE = "article";
    
    public static final String CORE_NAME_RULES   = "rules";
    
    public static final String CORE_NAME_HELP    = "help";
    
    public static final String SOLR_QUERY_MM     = "65%";
    
    // 添加索引时候，将不被过滤的字符白名单
    public static final String whiteListTags     = "/";
    
    private static String      solrServerInvokeUrl;
    
    private SolrjHandler()
    {
    }
    
    public void setSolrServerInvokeUrl(String solrServerInvokeUrl)
    {
        SolrjHandler.solrServerInvokeUrl = solrServerInvokeUrl;
    }
    
    public static String getSolrServerInvokeUrl()
    {
        return solrServerInvokeUrl;
    }
    
    /**
     * 获取solr服务器
     * @param coreName
     * @return {@link HttpSolrClient}
     */
    private HttpSolrClient getSolrServer(String coreName)
    {
        HttpSolrClient server = new HttpSolrClient(solrServerInvokeUrl + coreName);
        server.setSoTimeout(2000); // socket read timeout
        server.setConnectionTimeout(3000);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false); // defaults to false
        server.setAllowCompression(false);
        server.setBaseURL(solrServerInvokeUrl + coreName);
        return server;
    }
    
    /**
     * 调用搜索引擎 执行搜索操作
     *
     * @param coreName
     * @param params
     * @return SolrResponse
     */
    public static SolrResponse query(String coreName, Map<String, String> params)
    {
        String url = SolrjHandler.getSolrServerInvokeUrl() + coreName + "/select";
        String jsonStr = HttpUtils.post(url, params, "UTF-8");
        return JSON.parseObject(jsonStr, SolrResponse.class);
    }
    
    /**
     * 立即强制服务提交索引
     *
     * @throws BusinessException
     */
    public static void commit(String coreName)
    {
        commit(coreName, true, true);
    }
    
    /**
     * 立即强制服务提交索引
     *
     * @param waitFlush    block until index changes are flushed to disk
     * @param waitSearcher block until a new searcher is opened and registered as the  main query searcher, making the changes visible
     * @throws BusinessException
     */
    public static boolean commit(String coreName, boolean waitFlush, boolean waitSearcher)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            if (solrServer != null) solrServer.commit(waitFlush, waitSearcher);
        }
        catch (SolrServerException e)
        {
            logger.error("commit error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("commit error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return true;
    }
    
    /**
     * 删除单个索引文档
     *
     * @param refrenceId 索引文档的唯一标识，一般是refrenceId
     * @param coreName   请参见 SolrjHandler 的 CORE_NAME_* 常量
     * @return 删除成功共与否
     */
    public static boolean delDoc(String refrenceId, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            solrServer.deleteById(refrenceId);
            solrServer.commit();
        }
        catch (SolrServerException e)
        {
            logger.error("delDoc error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("delDoc error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return true;
    }
    
    /**
     * 批量删除文档
     *
     * @param docIds 文档id集合
     * @return
     */
    public static boolean delDoc(List<String> docIds, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            solrServer.deleteById(docIds);
            solrServer.commit();
        }
        catch (SolrServerException e)
        {
            logger.error("delDoc error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("delDoc error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return true;
    }
    
    /**
     * 删除查询出来的所有
     *
     * @param query
     * @param coreName
     * @return Boolean
     */
    public static boolean delDocByQuery(String query, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            solrServer.deleteByQuery(query, -1);
            solrServer.commit();
        }
        catch (SolrServerException e)
        {
            logger.error("delDocByQuery error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("delDocByQuery error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return true;
    }
    
    /**
     * 批量添加 solrdocument 适用于数据量较大的场景，如 10万条以上
     *
     * @param solrDocList
     * @param batchSize   提交批量大小 100~10000
     * @return Boolean
     */
    public boolean batchAddDocs(List<SolrInputDocument> solrDocList, int batchSize, String coreName)
    {
        if (solrDocList != null && solrDocList.size() > 0)
        {
            HttpSolrClient solrServer = null;
            try
            {
                SolrjHandler handler = new SolrjHandler();
                solrServer = handler.getSolrServer(coreName);
                int i = 0;
                for (SolrInputDocument doc : solrDocList)
                {
                    solrServer.add(doc);
                    i++;
                    if (i % batchSize == 0) solrServer.commit();
                }
                solrServer.commit();
            }
            catch (SolrServerException e)
            {
                logger.error("batchAddDocs error:", e);
                return false;
            }
            catch (IOException e)
            {
                logger.error("batchAddDocs error:", e);
                return false;
            }
            finally
            {
                close(solrServer);
            }
        }
        return true;
    }
    
    /**
     * 添加索引对象
     * @param solrDoc
     * @param coreName
     * @return Boolean
     */
    public static boolean addDoc(SolrInputDocument solrDoc, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            UpdateResponse resp = null;
            if (solrDoc != null) resp = solrServer.add(solrDoc);
            if (solrServer != null) solrServer.commit();
            if (resp != null && resp.getStatus() == 0) return true;
        }
        catch (SolrServerException e)
        {
            logger.error("addDoc error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("addDoc error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return false;
    }
    
    /**
     * 批量添加索引
     * @param solrDocList
     * @param coreName
     * @return Boolean
     */
    public static boolean addDocList(List<SolrInputDocument> solrDocList, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            solrServer.add(solrDocList);
            UpdateResponse resp = solrServer.commit();
            if (resp.getStatus() == 0) return true;
        }
        catch (SolrServerException e)
        {
            logger.error("addDocList error:", e);
            return false;
        }
        catch (IOException e)
        {
            logger.error("addDocList error:", e);
            return false;
        }
        finally
        {
            close(solrServer);
        }
        return false;
    }
    
    /**
     * 删除索引
     * @param query
     * @param coreName
     */
    public void deleteByQuery(String query, String coreName)
    {
        HttpSolrClient solrServer = null;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            solrServer.deleteByQuery(query);
        }
        catch (SolrServerException e)
        {
            logger.error("deleteByQuery failed" + e.getMessage());
        }
        catch (IOException e)
        {
            logger.error("deleteByQuery failed" + e.getMessage());
        }
    }
    
    /**
     * 使用solrj api查询数据
     *
     * @param params
     * @param fields
     * @return List
     */
    public List<Map<String, Object>> query(SolrParams params, String[] fields, String coreName)
    {
        List<Map<String, Object>> results = Lists.newArrayList();
        HttpSolrClient solrServer;
        try
        {
            SolrjHandler handler = new SolrjHandler();
            solrServer = handler.getSolrServer(coreName);
            SolrDocumentList documents;
            try
            {
                documents = solrServer.query(params).getResults();
                Iterator<SolrDocument> iter = documents.iterator();
                while (iter.hasNext())
                {
                    SolrDocument doc = iter.next();
                    Map<String, Object> map = Maps.newHashMap();
                    for (String field : fields)
                        map.put(field, doc.getFieldValue(field));
                    results.add(map);
                }
            }
            catch (IOException e)
            {
                logger.error("query failed" + e.getMessage());
            }
        }
        catch (SolrServerException e)
        {
            logger.error("query failed" + e.getMessage());
        }
        return results;
    }
    
    /**
     * 关闭连接
     * @param client
     */
    public static void close(SolrClient client)
    {
        try
        {
            client.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
