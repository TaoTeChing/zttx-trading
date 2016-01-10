<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
<title>资料下载中心  ${brandesInfo.brandsName}-${brandInfo.comName } 8637品牌超级代理</title>
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
    <jsp:param name="url" value="/document"/>
</jsp:include>
<div class="do-body">
<div class="listbody clear">

    <div class="sidebar-l">
        <%--<!-- 资料下载 -->--%>
        <jsp:include page="${ctx}/market/brand/brandDocument">
            <jsp:param name="brandId" value="${brandId}"/>
            <jsp:param name="brandesId" value="${brandesId}"/>
        </jsp:include>
    </div>

    <!--// 右侧功能条 -->
    <%--<%@ include file="right_sidebar.jsp" %>--%>
    <jsp:include page="${ctx}/market/brand/rightSidebar">
        <jsp:param name="brandId" value="${brandId}"/>
        <jsp:param name="brandesId" value="${brandesId}"/>
    </jsp:include>

    </div>
</div>
<%--视频主持人--%>
<%--<jsp:include page="${ctx}/market/brand/rightFlash">
    <jsp:param name="brandsId" value="${brandesId}"/>
</jsp:include>--%>
<%-- 结束 --%>
<%--<!-- footer -->--%>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>

<script src="${res}/scripts/jquery.min.js"></script>
<jsp:include page="/WEB-INF/view/common/setup_ajax.jsp"/>
<script src="${src}/fronts/market/jquery.elastislide.js" type="text/javascript"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js" type="text/javascript"></script>
<script src="${src}/fronts/market/last.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        var pageList = {};
        seajs.use(["pagination",'template', 'moment'], function (Pagination, template, moment) {

            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD");
            });

            $(".ajax-templage").each(function (index) {
                var $this = $(this);
                var tempId = $this.attr("id");
                if (tempId == null || tempId == "") {
                    tempId = "templage" + index;
                    $this.attr("id", tempId);
                }
                pageList[tempId] = new Pagination({
                    url: $this.attr("_url"),
                    elem: $this.attr("_page"),
                    method: "post",
                    handleData: function (data) {
                        var html = template.render(tempId, data);
                        $($this.attr("_pid")).html(html);
                    }
                });
            });

            //资料下载js
            $(".datadownload .datadownloadul a").bind("click", function () {
                //点击下载分类触发的事件
                var $this = $(this);
                $this.parent().addClass("selected").siblings().removeClass("selected");
                var page = pageList["ajax-templage-datadownload"];
                page.data = {cateId: $this.attr("_cateId")};
                page.goTo(1);
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

        $(document).on('click', 'a.download', function(event) {
            var $this = $(this);
            $.post('${ctx}/market/brand/downloadcount', {docId: $this.data('id')});
        });
    });
</script>
</body>
</html>