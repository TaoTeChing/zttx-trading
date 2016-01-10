<!DOCTYPE html><%@ page language="java"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>门店展示  ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
<meta name="keywords"
	content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商" />
<meta name="description" content="" />
<link href="${res}/styles/fronts/market/brandviewbase.css" rel="stylesheet"
	type="text/css" />
<link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet"
	type="text/css" />
<jsp:include page="/market/header_css">
	<jsp:param name="brandesId" value="${brandesId}" />
</jsp:include>
</head>
<body>
	<%--<!-- top -->--%>
	<jsp:include page="${ctx}/common/top" />

	<%--<!-- head -->--%>
	<jsp:include page="/market/header">
		<jsp:param name="brandesId" value="${brandesId}" />
		<jsp:param name="url" value="/network" />
	</jsp:include>
	<div class="do-body">
		<div class="listbody clear">
			<%--<div class="sidebar-l">
        <!-- 经销网络 -->
        <jsp:include page="${ctx}/market/brand/brandNetwork" />
    </div>--%>

			<%--<!-- 经销网络 -->--%>
			<div class="sidebar-l">
				<h3 class="title">门店展示</h3>

				<div class="network">
					<ul class="networkul clearfix">
						<c:forEach items="${pageResult.list}" var="item">
							<li>
								<div class="imgbox">
									<c:if test="${not empty item.path}">
										<a href="${res}${item.path}" title="${item.dealerName}" target="" data-lightbox="netpicgroup">
											<img src="${res}${item.path}" class="f-l" width="270" height="175" />
										</a>
									</c:if>
									<c:if test="${empty item.path}">
										<a href="${res}/images/common/nopic-270x175.gif" title="${item.dealerName}" target="" data-lightbox="netpicgroup">
											<img src="${res}/images/common/nopic-270x175.gif" class="f-l" width="270" height="175" />
										</a>
									</c:if>
									
								</div>
								<div class="title" title="${item.dealerName}">${item.dealerName}</div>
								<div class="address" title="${item.provinceName}${item.cityName}${item.areaName}${item.address}">${item.provinceName}${item.cityName}${item.areaName}${item.address}</div>
							</li>
						</c:forEach>
					</ul>
					<div id="pagination"></div>
				</div>
			</div>
			<%--<!--// sidebar-l -->

            <!--// sidebar-l -->--%>
            <%--<!--// 右侧功能条 -->--%>
            <%--<%@ include file="right_sidebar.jsp" %>--%>
            <jsp:include page="${ctx}/market/brand/rightSidebar">
                <jsp:param name="brandId" value="${brandId}"/>
                <jsp:param name="brandesId" value="${brandesId}"/>
            </jsp:include>
			<%--<!--// sidebar-r -->--%>
		</div>
	</div>
	<%--<!--// listbody -->--%>
	<%--视频主持人--%>
	<%--<jsp:include page="${ctx}/market/brand/rightFlash">
		<jsp:param name="brandsId" value="${brandesId}" />
	</jsp:include>--%>
	<%-- 结束 --%>
	<%--<!-- footer -->--%>
	<jsp:include page="/WEB-INF/view/include/footer.jsp"/>

	<%--<!-- 结束 -->--%>
	<script src="${res}/scripts/jquery.min.js"></script>
	<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp" />
	<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
	<script src="${res}/scripts/seajs_config.js"></script>
	<script src="${res}/scripts/common.js" type="text/javascript"></script>
    <script src="${src}/fronts/market/jquery.elastislide.js" type="text/javascript"></script>
	<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
	<script>
    seajs.use(["lightbox"], function () {
    });
</script>
	<script type="text/javascript">
    $(function () {
        setTimeout(function() {
            $.post('${ctx}/market/user_rtk', {
                id: '${brandesId}',
                code: 6
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
