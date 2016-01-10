<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
	<title>${news.newsTitle} - ${brandesInfo.brandsName} - ${brandInfo.comName } 8637品牌超级代理</title>
	<meta name="keywords" content="${brandesInfo.brandsName},${brandesInfo.brandsName}品牌代理,${brandesInfo.brandsName}品牌招商"/>
	<meta name="description" content=""/>
    <link href="${res}/styles/fronts//market/brandviewbase.css" rel="stylesheet" type="text/css"/>
    <link href="${res}/styles/fronts/market/decoration.css" rel="stylesheet" type="text/css"/>
    <jsp:include page="/market/header_css">
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>
</head>
<body>
<!-- top -->
<jsp:include page="${ctx}/common/top"/>

<!-- head -->
<jsp:include page="/market/header">
    <jsp:param name="brandesId" value="${brandesId}"/>
    <jsp:param name="url" value="/news"/>
</jsp:include>

<!---------------------------------   主体内容      --------------------------------->
<div class="do-body">
<div class="listbody clear">
    <div class="sidebar-l">
        <!-- 品牌新闻-查看 -->
        <div class="comnewsinfo font">
            <div class="comnewsinfo-nowtitle">
                <span class="comnewsinfo-nowtitle-name">品牌新闻</span>
            </div>
            <div class="comnewsinfo-content">
                <h1 class="comnewsinfo-title">${news.newsTitle}</h1>

                <div class="comnewsinfo-some">
                    <span>发布时间：${fns:getTimeFromLong(news.createTime,"yyyy年MM月dd日")}</span>
                    <span class="ml15">发布人：${brandInfo.comName}</span>
                </div>
                <div>
                    <p class="comnewsinfo-pstyle">${news.newsContent}</p>
                </div>
            </div>
            <div class="comnewsinfo-bottom">
                <span>本文版权所属8637品牌超级代理，转载请注明：<a href="#" class="fontcolor9">http://www.8637.com/info?newsId=${newsId}</a></span>
            </div>
        </div>
        <%--<div class="comnewsinfo-relate font mt10">
            <div class="comnewsinfo-relatetit">
                <span class="comnewsinfo-relatetit-name">相关资讯</span>
            </div>
            <ul class="comnewsinfo-relate-ulitem mt10 f-l">
                <c:forEach var="obj" items="${relNewsList}">
                    <li class="comnewsinfo-relate-litem">
                        <a href="/viewBrandNewsInfo/${obj.brandsId}/${obj.brandId}/${obj.refrenceId}">${obj.newsTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>--%>
    </div>

    <div class="sidebar-r">
        <!-- 关注度信息 -->
        <jsp:include page="${ctx}/market/brand/attentionNum">
            <jsp:param name="brandesId" value="${brandesId}"/>
        </jsp:include>

        <!-- 品牌信息 -->
        <jsp:include page="${ctx}/market/brand/brandesInfo">
            <jsp:param name="brandesId" value="${brandesId}"/>
        </jsp:include>

        <!-- 品牌商基本信息 -->
        <jsp:include page="${ctx}/market/brand/brandInfo">
            <jsp:param name="brandId" value="${brandId}"/>
            <jsp:param name="brandesId" value="${brandesId}"/>
        </jsp:include>

        <!-- 品牌商旗下所有品牌信息 -->
        <jsp:include page="${ctx}/market/brand/brandesList">
            <jsp:param name="brandId" value="${brandId}"/>
        </jsp:include>

        <%-- <!-- 在线联系信息 -->
        <jsp:include page="${ctx}/market/brand/contactOnline" >
            <jsp:param name="brandesId"  value="${brandesId}" />
        </jsp:include> --%>
    </div>
</div>
</div>
<!--------------------------------- end:主体内容  --------------------------------->
<!-- footer -->
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>

<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${res}/scripts/market/last.js" type="text/javascript"></script>
<script src="${res}/scripts/plugin/template-simple.js" type="text/javascript"></script>
<script src="${res}/scripts/plugin/jquery-dateFormat.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        var pageList = {};
        seajs.use(["pagination"], function (Pagination) {

            template.helper('$formatDate', function (millsec) {
                return $.format.date(new Date(millsec), 'yyyy-MM-dd');
            });

            $(".ajax-templage").each(function (index) {
                var $this = $(this);
                var tempId = "templage" + index;
                $this.attr("id", tempId);
                pageList[tempId] = new Pagination({
                    url: $this.attr("_url"),
                    elem: $this.attr("_page"),
                    handleData: function (data) {
                        var html = template.render(tempId, data);
                        $($this.attr("_pid")).html(html);
                    }
                });
            });
            $(".attention").on('click', '.js-collected-brands', function () {
                $.post("${ctx}/market/brand/attentionNum/add", {brandesId: "${brandesId}"}, function (data) {
                    switch (data.code) {
                        case zttx.SUCCESS:
                            remind("success", "收藏成功", 1000, function () {
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
    });
</script>
</body>
</html>
