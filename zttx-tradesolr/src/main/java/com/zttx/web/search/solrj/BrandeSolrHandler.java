package com.zttx.web.search.solrj;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;

import com.google.common.collect.Lists;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.module.brand.entity.*;

/**
 * <p>File：BrandeSolrHandler.java </p>
 * <p>Title: 品牌索引服务 </p>
 * <p>Description: BrandeSolrHandler </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class BrandeSolrHandler
{
    private Logger logger = Logger.getLogger(BrandeSolrHandler.class.getSimpleName());
    
    /**
     * 新增品牌数据到solr索引里
     *
     * @param brandesInfoEntity
     * @return {@link Boolean}
     */
    public Boolean addBrandsInfo(BrandesInfo brandesInfoEntity)
    {
        Boolean bl = Boolean.FALSE;
        try
        {
            SolrInputDocument doc = convertBrandesInfoEntityToDoc(brandesInfoEntity);
            if (doc != null) bl = SolrjHandler.addDoc(doc, SolrjHandler.CORE_NAME_BRANDES);
        }
        catch (Exception e)
        {
            logger.error("addBrandsInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 批量添加品牌信息到收索引擎建立索引
     *
     * @param biList
     * @return {@link Boolean}
     */
    public Boolean addBrandsInfoList(List<BrandesInfo> biList)
    {
        Boolean bl = false;
        if (null != biList && biList.size() > 0) try
        {
            List<SolrInputDocument> docList = Lists.newArrayList();
            for (BrandesInfo bi : biList)
            {
                SolrInputDocument doc = convertBrandesInfoEntityToDoc(bi);
                docList.add(doc);
            }
            if(null != docList && docList.size()>0){
            	bl = SolrjHandler.addDocList(docList, SolrjHandler.CORE_NAME_BRANDES);
            }
        }
        catch (Exception e)
        {
            logger.error("addBrandsInfoList error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 从搜索引擎中删除
     */
    public Boolean removeBrandesInfo(BrandesInfo brandesInfo)
    {
        Boolean bl;
        try
        {
            bl = SolrjHandler.delDoc(brandesInfo.getRefrenceId(), SolrjHandler.CORE_NAME_BRANDES);
        }
        catch (Exception e)
        {
            logger.error("remove brandesInfo error:", e);
            bl = false;
        }
        return bl;
    }
    
    /**
     * 将 BrandesInfo entity 和附加属性转换为solr 的索引文档
     *
     * @param bi
     * @return {@link SolrInputDocument}
     */
    public SolrInputDocument convertBrandesInfoEntityToDoc(BrandesInfo bi)
    {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("refrenceId", bi.getRefrenceId());
        // 加入品牌商信息
        BrandInfo brandInfo = bi.getBrandInfo();
        if (null != brandInfo)
        {
            doc.addField("brandId", brandInfo.getRefrenceId());
            doc.addField("comName", brandInfo.getComName());
            doc.addField("dealType", brandInfo.getDealType());
            doc.addField("legalName", brandInfo.getLegalName());
            doc.addField("provinceName", brandInfo.getProvinceName());
            doc.addField("cityName", brandInfo.getCityName());
            doc.addField("areaName", brandInfo.getAreaName());
            doc.addField("comAddress", brandInfo.getComAddress());
            doc.addField("regMoney", brandInfo.getRegMoney());
        }
        // 加入品牌商经营类目信息
        List<BrandCatelog> catelogs = bi.getCatelogList();
        if (null != catelogs && catelogs.size() > 0)
        {
            for (BrandCatelog brandCatelog : bi.getCatelogList())
            {
                doc.addField("mainId", brandCatelog.getDealNo());
                doc.addField("mainName", brandCatelog.getDealName());
            }
        }
        // 加入品牌主营类目信息
        List<BrandDeal> deals = bi.getDealList();
        if (null != deals && deals.size() > 0)
        {
            for (BrandDeal deal : deals)
            {
                doc.addField("dealId", deal.getDealNo());
                doc.addField("dealName", deal.getDealName());
            }
        }
        // 加入品牌统计信息
        BrandsCount brandsCount = bi.getBrandsCount();
        if (null != brandsCount)
        {
            doc.addField("applyNum", brandsCount.getApplyCount());
            doc.addField("saleNum", brandsCount.getApplyCount());
            doc.addField("favNum", brandsCount.getFavNum());
            doc.addField("inviteNum", brandsCount.getInviteCount());
            doc.addField("joinNum", brandsCount.getJoinCount());
            doc.addField("viewNum", brandsCount.getViewNum());
            doc.addField("productNum", brandsCount.getProductCount());
        }
        doc.addField("brandName", bi.getBrandsName());
        doc.addField("brandType", bi.getBrandType());
        doc.addField("brandLogo", bi.getBrandLogo());
        doc.addField("proLogo", bi.getProLogo());
        doc.addField("holdName", bi.getHoldName());
        doc.addField("brandMark", bi.getBrandMark());
        doc.addField("recommendImage", bi.getRecommendImage());
        doc.addField("createTime", CalendarUtils.getTimeFromLong(bi.getCreateTime() != null ? bi.getCreateTime() : 0l));
        doc.addField("updateTime", CalendarUtils.getTimeFromLong(bi.getUpdateTime() != null ? bi.getUpdateTime() : 0l));
        doc.addField("brandsWeight", bi.getBrandsWeight());
        return doc;
    }
}
