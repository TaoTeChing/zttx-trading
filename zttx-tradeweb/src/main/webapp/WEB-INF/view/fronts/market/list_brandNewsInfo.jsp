<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!-- 面包屑 -->
<%--<div class="crumbs-nav font">
    <span class="crumbs-nav-icon"></span>
    <span class="crumbs-nav-now">当前所在页</span>
    <span class="crumbs-nav-arrow">&gt;</span>
    <a href="${ctx}/market/viewBrandNews/${brandesId}/${brandId}" class="crumbs-nav-link">品牌新闻</a>
    <span class="crumbs-nav-arrow">&gt;</span>
    <span class="crumbs-nav-nowpage">${news.newsTitle}</span>
</div>--%>
<!-- 面包屑结束 -->
<div class="comnewsinfo font mt10">
    <div class="comnewsinfo-nowtitle">
        <span class="comnewsinfo-nowtitle-name">品牌新闻</span>
    </div>
    <div class="comnewsinfo-content">
        <h1 class="comnewsinfo-title">${news.newsTitle}</h1>
        <div class="comnewsinfo-some">
            <span>发布时间：${fns:getTimeFromLong(news.createTime,"yyyy年MM月dd日")}</span>
            <span class="ml15">发布人：${news.brand.userName}</span>
        </div>
        <div>
            <p class="comnewsinfo-pstyle">${news.newsContent}</p>
        </div>
    </div>
    <div class="comnewsinfo-bottom">
        <span>本文版权所属8637品牌超级代理，转载请注明：<a href="#" class="fontcolor9">http://www.8637.com/info?newsId=${newsId}</a></span>
    </div>
</div>
<div class="comnewsinfo-relate font mt10">
    <div class="comnewsinfo-relatetit">
        <span class="comnewsinfo-relatetit-name">相关资讯</span>
    </div>
    <ul class="comnewsinfo-relate-ulitem mt10 f-l">
    	<c:forEach var="obj" items="${relNewsList}">
    		<li class="comnewsinfo-relate-litem"><a href="${ctx}/market/viewBrandNewsInfo/${obj.brandsId}/${obj.brandId}/${obj.refrenceId}">${obj.newsTitle}</a></li>
    	</c:forEach>
    </ul>
</div>