<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
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
    <%-- //nav end --%>
    <div class="container">
        <div class="g-main">
            <div class="g-coll">
                <div class="m-title">
                    <h3>${articleCate.cateName != null ? articleCate.cateName : "最新资讯"}</h3>
                    <a href="${ctx }/info/list?cateId=${articleCate.refrenceId}" class="more">&nbsp;</a>
                </div>
                <%--<!--全部资讯-->--%>
                <form id="searchForm">
                    <input name="cateId" type="hidden" value="${filter.cateId }"/>
                    <input name="q" type="hidden" value="${filter.q}"/>
                </form>
                <div class="newest" id="infoBox">

                </div>
                <div id="pagination" style="margin-right: 20px; padding: 10px 0;">

                </div>
            </div>
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
        </div>
        <jsp:include page="/WEB-INF/view/include/footer.jsp"/>
    </div>
    <script type="text/html" id="message_tpl">
        {{each rows}}
        <dl>
            <dt>
            <h5><a href="${ctx }/info/detail/{{$value.refrenceId}}" title="{{$value.articleTitle}}">{{$value.articleTitle}}</a></h5>
            <span>{{$formatDate $value.createTime}}</span>
            </dt>
            <dd class="des">
                {{$trimLongString $value.articleText}}
            </dd>
        </dl>
        {{/each}}
    </script>
    <script src="${res}/scripts/jquery.min.js"></script>
    <script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
    <script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
    <script src="${res}/scripts/seajs_config.js"></script>
    <script src="${res}/scripts/common.js"></script>
    <script src="${src}/common/base-init.js"></script>
    <jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
    <script>
        seajs.use(["pagination", "moment", "template", "${jsrel}/fronts/info/index"], function (Pagination, moment, template, News) {

            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY年MM月DD日");
            });

            template.helper('$trimLongString', function(origText){
                return trimLongString(origText, 200);
            });

            new Pagination({
                url: "${ctx}/search/info",
                elem: "#pagination",
                method: "get",
                pageSize: 22,
                form: $("#searchForm"),
                handleData: function (json) {
                    var html = template.render("message_tpl", json);
                    $("#infoBox").html(html);
                }
            });

            News.navHover();
        });
    </script>
</body>
</html>