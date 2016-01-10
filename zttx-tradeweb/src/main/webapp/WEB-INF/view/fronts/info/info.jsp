<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<jsp:include page="header.jsp"/>
<body>
<div class="container">
    <%-- 顶部 --%>
    <jsp:include page="${ctx}/common/top"/>
    <%-- 搜索 --%>
    <jsp:include page="search.jsp"/>
    <%-- 导航 --%>
    <jsp:include page="nav.jsp"/>
    <div class="container">
        <div class="g-main">
            <div class="g-info-coll">
                <div class="m-bread">
                    <a href="${ctx }/info/">新闻资讯</a>
                    <span>&gt;</span>
                    <a href="${ctx }/info/list?cateId=${articleCate.refrenceId}">${articleCate.cateName }</a>
                    <span>&gt;</span>
                    <span>正文</span>
                </div>
                <div class="news-content">
                    <h1>${articleInfo.articleTitle }</h1>

                    <div class="detail">
                        <span>${fns:getTimeFromLong(articleInfo.createTime, 'yyyy年MM月dd日') }</span>
                        <span>来源：<em>${articleInfo.articleSource }</em></span>
                    </div>
                    <div class="content">
                        ${ articleInfo.articleText }
                    </div>
                    <div class="share">
                        <span class="fl mt5">本文版权所属8637品牌超级代理,转载请注明出处</span>
                        <span class="fr bdfx" style="width: 187px;">
                            <!--百度分享开始-->
                            <div class="bdsharebuttonbox">
                                <span style="float: left;margin-top: 5px;">分享到：</span>
                                <a class="bds_more" href="#" data-cmd="more"></a>
                                <a class="bds_qzone" title="分享到QQ空间" href="#" data-cmd="qzone"></a>
                                <a class="bds_tsina" title="分享到新浪微博" href="#" data-cmd="tsina"></a>
                                <a class="bds_tqq" title="分享到腾讯微博" href="#" data-cmd="tqq"></a>
                                <a class="bds_renren" title="分享到人人网" href="#" data-cmd="renren"></a>
                                <a class="bds_weixin" title="分享到微信" href="#" data-cmd="weixin"></a>
                            </div>
                            <script>
                                window._bd_share_config = {
                                    "common": {
                                        "bdSnsKey": {},
                                        "bdText": "",
                                        "bdMini": "2",
                                        "bdPic": "",
                                        "bdStyle": "0",
                                        "bdSize": "16"
                                    }, "share": {}
                                };
                                with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                            </script>
                            <!--百度分享结束-->
                        </span>
                    </div>
                </div>
                <div class="m-title">
                    <h4>相关资讯</h4>
                </div>
                <div class="m-related">
                    <div class="list">
                        <ul>
                            <c:if test="${articleInfos.list != null }">
                                <c:forEach items="${articleInfos.list }" var="articleInfo" begin="0" end="7">
                                    <li>
                                        <i>·</i>
                                        <a href="${ctx }/info/detail/${articleInfo.refrenceId }" title=" ${fns:trimLongStringWithEllipsis(articleInfo.articleTitle, 65 ,'...') }">
                                            ${fns:trimLongStringWithEllipsis(articleInfo.articleTitle, 65 ,'...') }
                                        </a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <%-- //g-coll end --%>
            <div class="g-colr">
                <div class="brand-top">
                    <div class="m-title">
                        <h4>总排行榜</h4>
                    </div>
                    <div class="list">
                        <ul>
                            <c:set value="${fns:findHotBy(0, 10) }" var="week"/>
                            <c:if test="${week.list != null }">
                                <c:forEach items="${week.list}" var="data" varStatus="status">
                                    <li>
                                        <i class="num_${status.index > 2 ? 4 : status.index + 1 }">${status.index+1} </i>
                                        <a href="${ctx }/info/detail/${data.refrenceId}" target="_blank"
                                           title="${data.articleTitle}">${data.articleTitle}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>

                <div class="brand-top">
                    <div class="m-title">
                        <h4>电商动态</h4>
                    </div>
                    <div class="list">
                        <ul>
                            <c:set value="${fns:findSimpleByInfo(shoper_trends, 10) }" var="noticeInfo"/>
                            <c:if test="${noticeInfo.list != null }">
                                <c:forEach items="${noticeInfo.list}" var="data" varStatus="status">
                                    <li>
                                        <i>·</i>
                                        <a href="${ctx }/info/detail/${data.refrenceId}" target="_blank"
                                           title="${data.articleTitle}">${data.articleTitle}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>

            <%-- //g-colr end --%>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${src}/common/base-init.js"></script>
<script>
    seajs.use(["${jsrel}/fronts/info/index"], function (News) {
        News.navHover();
    });
</script>
</body>
</html>