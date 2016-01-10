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
            <div class="g-coll">
                <div class="m-top">
                    <%-- 轮播新闻 --%>
                    <div class="slide">
                        <div id="slide_box">
                            <div class="slide-bg"></div>
                            <ul>
                                <c:set value="${fns:findArticleHead(6) }" var="articles"/>
                                <c:forEach items="${articles.list }" var="article">
                                    <li class="item">
                                        <a href="${ctx }/info/detail/${article.refrenceId}" target="_blank">
                                            <img src="${res}${fns:getImageDomainPath(article.articleImage,500 ,280)}"
                                                 width="500px" height="280px" alt="${article.articleTitle }"/>
                                        </a>
                                        <a href="${ctx }/info/detail/${article.refrenceId}" class="title"
                                           target="_blank" title="${article.articleTitle }">
                                            <span>${article.articleTitle }</span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <%-- 总排行榜 --%>
                    <div class="charts">
                        <h3>总排行榜</h3>
                        <ul>
                            <c:set value="${fns:findHotBy(0, 5) }" var="week"/>
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
                <div class="m-title mt20">
                    <h2>精选导读</h2>
                    <a href="#" class="more">&nbsp;</a>
                </div>
                <div class="choice">
                    <div class="choice-list">
                        <dl>
                            <dt><a href=""><img src="${res}/images/fronts/info/v2_c2.jpg"/></a></dt>
                            <dd class="tit">电商动态</dd>
                            <c:set value="${fns:findSimpleByInfo(shoper_trends, 3) }" var="noticeInfo"/>
                            <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                <dd>
                                    <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                        title="${info.articleTitle}">${fns:trimLongStringWithEllipsis(info.articleTitle,15, '...')}
                                    </a>
                                </dd>
                            </c:forEach>
                        </dl>
                        <dl>
                            <dt><a href=""><img src="${res}/images/fronts/info/v2_c1.jpg"/></a></dt>
                            <dd class="tit">潮流趋势</dd>
                            <c:set value="${fns:findSimpleByInfo(trend_tide, 3) }" var="noticeInfo"/>
                            <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                <dd>
                                    <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                       title="${info.articleTitle}">${fns:trimLongStringWithEllipsis(info.articleTitle, 15, '...')}
                                    </a>
                                </dd>
                            </c:forEach>
                        </dl>
                        <dl>
                            <dt><a href=""><img src="${res}/images/fronts/info/v2_c3.jpg"/></a></dt>
                            <dd class="tit">产经资讯</dd>
                            <c:set value="${fns:findSimpleByInfo(asert_news, 3) }" var="noticeInfo"/>
                            <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                <dd>
                                    <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                       title="${info.articleTitle}">${fns:trimLongStringWithEllipsis(info.articleTitle, 15, '...')}
                                    </a>
                                </dd>
                            </c:forEach>
                        </dl>
                        <dl>
                            <dt><a href=""><img src="${res}/images/fronts/info/v2_c4.jpg"/></a></dt>
                            <dd class="tit">政策参考</dd>
                            <c:set value="${fns:findSimpleByInfo(business_Report, 3) }" var="noticeInfo"/>
                            <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                <dd>
                                    <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                       title="${info.articleTitle}">${fns:trimLongStringWithEllipsis(info.articleTitle, 15, '...')}
                                    </a>
                                </dd>
                            </c:forEach>
                        </dl>
                    </div>
                </div>
                <div class="m-title mt20">
                    <h3><em>最新</em>资讯</h3>
                    <a href="#" class="more">&nbsp;</a>
                </div>
                <div class="newest">
                    <div>
                        <c:set value="${fns:loadLatestNews(15)}" var="paginateResult"/>
                        <c:forEach items="${paginateResult.list}" var="result" varStatus="status">
                            <dl>
                                <dt>
                                <h5>
                                    <a href="${ctx }/info/detail/${result.refrenceId}" class="col_c3" target="_blank" title="${result.articleTitle }">
                                            ${result.articleTitle }
                                    </a>
                                </h5>
                                <span>${fns:getTimeFromLong(result.createTime,"yyyy-MM-dd HH:mm" )}</span>
                                </dt>
                                <dd class="des">
                                        ${fns:trimLongStringWithEllipsis(result.articleText,200,".....")}
                                </dd>
                                <dd class="tag">
                                    <a href="">潮流趋势</a>
                                </dd>
                            </dl>
                        </c:forEach>
                    </div>
                    <div class="more">
                        <a href="${ctx }/info/list" target="_blank" >更多文章</a>
                    </div>
                </div>
            </div>
            <%-- //g-coll end --%>
            <div class="g-colr">
                <div class="announce">
                    <div class="tab" id="tab">
                        <ul class="tab-menu">
                            <li class="active"><a href="#">平台资讯</a></li>
                            <li><a href="#">平台公告</a></li>
                        </ul>
                        <div class="tab-con">
                            <div>
                                <ul>
                                    <c:set value="${fns:findSimpleByInfo(terrace_Inform, 10) }" var="noticeInfo"/>
                                    <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                        <li>
                                            <b>${infoStatus.index + 1}</b><a
                                                href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                                title="${fns:trimLongStringWithEllipsis(info.articleTitle, 25, '...') }">
                                                ${fns:trimLongStringWithEllipsis(info.articleTitle, 25, '...') }</a>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>
                            <div style="display: none;">
                                <ul>
                                    <c:set value="${fns:findSimpleByInfo(terrace_Notice, 10) }" var="noticeInfo"/>
                                    <c:forEach items="${noticeInfo.list }" var="info" varStatus="infoStatus">
                                        <li>
                                            <b>${infoStatus.index + 1}</b>
                                            <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                               title="${fns:trimLongStringWithEllipsis(info.articleTitle, 25, '...') }">
                                                    ${fns:trimLongStringWithEllipsis(info.articleTitle, 25, '...') }</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <%--广告 300 * 100--%>
                <%--<div class="mt10">--%>
                    <%--<a href=""><img src="" alt="" width="300" height="100"/></a>--%>
                <%--</div>--%>
                <div class="brand-news mt20">
                    <div class="m-title">
                        <h4>品牌资讯</h4>
                        <a href="${ctx }/info/list?cateId=${brand_News}" target="_blank" class="more">&nbsp;</a>
                    </div>
                    <div class="list">
                        <c:set value="${fns:getArticleCates(brand_News) }" var="cateList"/>
                        <c:forEach items="${cateList }" var="cate" varStatus="status">
                            <dl>
                                <dt class="">
                                    <a href="${ctx }/info/list?cateId=${cate.refrenceId}" target="_blank">
                                        <img src="${res}${fns:getImageDomainPath(cate.cateIcon, 90, 65)}" width="90" height="65"/>
                                    </a>
                                </dt>
                                <dd class=" tit"><a href="${ctx }/info/list?cateId=${cate.refrenceId}" target="_blank">${cate.cateName}</a></dd>
                                <c:set value="${fns:findSimpleByCate(cate.refrenceId, 2) }" var="infoList"/>
                                <c:forEach items="${infoList.list }" var="info">
                                    <dd>
                                        <a href="${ctx }/info/detail/${info.refrenceId}" target="_blank"
                                           title="${info.articleTitle}">${fns:trimLongStringWithEllipsis(info.articleTitle,15,"...")}</a>
                                    </dd>
                                </c:forEach>
                            </dl>
                        </c:forEach>
                    </div>
                </div>
               <%-- <div class="brand-famous mt20">
                    <div class="m-title">
                        <h4>知名品牌</h4>
                        <a href="#" class="more">&nbsp;</a>
                    </div>
                    <div class="list">
                        <%
                            int x2;
                            for (x2 = 0; x2 < 6; x2++) {
                        %>
                        <dl>
                            <dt><a href=""><img src="" alt="" width="80" height="40"/></a></dt>
                            <dd><a href="" title="蔓身姿">蔓身姿</a></dd>
                        </dl>
                        <%
                            }
                        %>
                    </div>
                </div>--%>
                <div class="brand-exhi mt20">
                    <div class="m-title">
                        <h4>展会报道</h4>
                        <a href="${ctx }/info/list?cateId=${meet_Report}" class="more" target="_blank">&nbsp;</a>
                    </div>
                    <c:set value="${fns:findSimpleByCate(meet_Report, 10) }" var="meetInfo"/>
                    <div class="content">
                        <ul>
                            <c:forEach items="${meetInfo.list}" var="info" varStatus="status">
                                <c:if test="${status.index <= 2}">
                                    <li class="img_${status.index + 1}">
                                        <a href="${ctx}/info/detail/${info.refrenceId}" target="_blank"
                                           title="${info.articleTitle}">
                                            <img src="${res}${fns:getImageDomainPath(info.articleImage,280 ,196)}" alt="${info.articleTitle}"/>
                                        </a>
                                        <c:if test="${status.index == 0}">
                                        <p>${info.articleTitle}</p>
                                        </c:if>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="list">
                        <ul>
                            <c:forEach items="${meetInfo.list}" var="info" varStatus="status">
                                <c:if test="${status.index > 2}">
                                    <li>
                                        <a href="${ctx}/info/detail/${info.refrenceId}" target="_blank"
                                           title="${info.articleTitle}">${info.articleTitle}
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="brand-exhi mt20">
                    <div class="m-title">
                        <h4>统计报告</h4>
                        <a href="${ctx }/info/list?cateId=${international_Report}" class="more" target="_blank">&nbsp;</a>
                    </div>
                    <c:set value="${fns:findSimpleByInfo(international_Report, 15) }" var="marketManagerList"/>
                    <div class="list">
                        <ul>
                            <c:forEach items="${marketManagerList.list }" var="marketManager">
                                <li>
                                    <a href="${ctx }/info/detail/${marketManager.refrenceId}" target="_blank"
                                       title="${marketManager.articleTitle}">${marketManager.articleTitle}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

               <%-- <div class="brand-ad mt20">
                    <div class="list">
                        <ul>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                            <li><a href=""><img src="" alt="" width="145" height="145"/></a></li>
                        </ul>
                    </div>
                </div>--%>
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
        News.init();
    });
</script>
</body>
</html>