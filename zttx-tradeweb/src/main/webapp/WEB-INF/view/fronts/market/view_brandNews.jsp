<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
<title>品牌新闻中心  ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
<meta name="keywords" content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
<meta name="description" content=""/>
    <link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet" type="text/css"/>
    <link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css"/>
    <jsp:include page="/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
</head>
<body>
<%--<!-- top -->--%>
<jsp:include page="${ctx}/common/top"/>

<%--<!-- head -->--%>
<jsp:include page="/market/header">
    <jsp:param name="brandesId" value="${brandesId}"/>
    <jsp:param name="url" value="/news"/>
</jsp:include>

<%--<!---------------------------------   主体内容      --------------------------------->--%>
<div class="do-body">
<div class="listbody clear">
    <div class="sidebar-l">
        <h3 class="title">品牌新闻</h3>

        <div class="comnews font">
            <ul class="comnewsul">
                <li ${newsType != 1 ? 'class="selected"':''}><a href="/news?newsType=0">最新发布</a>
                </li>
                <li ${newsType == 1 ? 'class="selected"':''}><a href="/news?newsType=1">热点资讯</a></li>
            </ul>
            <div class="comnewsbodyall">
                <c:forEach items="${pageResult.list}" var="item">
                    <c:set var="detailUrl" value="/newsinfo/${item.refrenceId}" />
                    <div class="comnewsbody clear">
                        <div class="timecla f-l">
                            <p class="time">${fns:getTimeFromLong(item.createTime, 'yyyy-MM-dd')}</p>
                        </div>
                        <dl class="comnewsbodydl">
                            <dd class="f-l">
                                <div class="imgbox">
                                    <a href="${detailUrl}" title="" target="_blank" title="${item.newsTitle}">
                                        <img src="${res}${item.imageUrl}" ${empty item.imageUrl ? 'alt="暂无图片"':''} width="120" height="120" /></a>
                                </div>
                            </dd>
                            <dt class="title">
                                <a href="${detailUrl}" title="${item.newsTitle}" target="_blank">${item.newsTitle}</a>
                            </dt>
                            <dd class="des fontcolor6">${fns:cleanHtmlElems(item.newsSummary)}</dd>
                            <dd class="details">
                                <a href="${detailUrl}" title="" target="_blank">查看详情 ></a>
                            </dd>
                        </dl>
                    </div>
                </c:forEach>
            </div>
            <div id="pagination" class="pages f-r clear">
            </div>
            <div class="clear"></div>

        </div>
    </div>
    <!--// 右侧功能条 -->
    <%--<%@ include file="right_sidebar.jsp" %>--%>
    <jsp:include page="${ctx}/market/brand/rightSidebar">
        <jsp:param name="brandId" value="${brandId}"/>
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>

</div>
</div>
<%--<!--------------------------------- end:主体内容  --------------------------------->--%>
<%--视频主持人--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>--%>
<%-- 结束 --%>
<%--<!-- footer -->--%>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        setTimeout(function() {
            $.post('${ctx}/market/user_rtk', {
                id: '${brandesId}',
                code: 7
            });
        }, 2000);
        function parseURL(url) {
            var a = document.createElement('a');
            a.href = url;
            return {
                source: url,
                protocol: a.protocol.replace(':', ''),
                host: a.hostname,
                port: a.port,
                query: a.search,
                params: (function () {
                    var ret = {},
                            seg = a.search.replace(/^\?/, '').split('&'),
                            len = seg.length, i = 0, s;
                    for (; i < len; i++) {
                        if (!seg[i]) {
                            continue;
                        }
                        s = seg[i].split('=');
                        ret[s[0]] = s[1];
                    }
                    return ret;
                })(),
                file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
                hash: a.hash.replace('#', ''),
                path: a.pathname.replace(/^([^\/])/, '/$1'),
                relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
                segments: a.pathname.replace(/^\//, '').split('/')
            };
        }
        seajs.use(["pagination"], function (Pagination) {

            new Pagination({
                elem: "#pagination",
                total:${pageResult.page.totalPage},
                currentPage:${pageResult.page.currentPage},
                showTotal: false,
                pageClick: function (page) {
                    var params = parseURL(window.location.href).params;
                    if (!params) {
                        params = {};
                    }
                    params.page = page;
                    window.location.href = '?' + $.param(params);
                }
            });
        });

        //收藏品牌
        $(".attention").on('click', '.js-collected-brands', function () {
            $.post("${ctx}/market/brand/attentionNum/add", {brandesId: "${brandesId}"}, function (data) {
                switch (data.code) {
                    case zttx.SUCCESS:
                        remind("success", "收藏成功", function () {
                            var _value = parseInt($("em.gz-num").text());
                            $(".attention span.weiguanzhu").addClass("yiguanzhu").removeClass("weiguanzhu").next().text("已收藏").removeClass("js-collected-brands");
                            $("em.gz-num").text(_value+1);
                        });
                        break;
                    case zttx.NOT_LOGIN:
                        var currentUrl = location.href;
                        location.href = '${ctx}/common/login?loginType=1&redirect=' + encodeURIComponent(currentUrl);
                        break;
                    default :
                        remind("error", data.message);
                }
            }, "json");
        });
    });
</script>
</body>
</html>
