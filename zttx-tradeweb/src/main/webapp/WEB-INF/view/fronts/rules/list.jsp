<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>规则中心 - 8637品牌超级代理</title>
    <meta name="keywords" content="8637, 品牌超级代理,品牌加盟,品牌代理"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${res }/styles/fronts/help/global.css"/>
    <link rel="stylesheet" href="${res }/styles/fronts/help/index.css"/>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <div class="main">

        <div class="main-left">
            <jsp:include page="left.jsp"/>
        </div>

        <div class="main-right">
            <div class="main-content">
                <jsp:include page="_search.jsp"/>
                <div class="panel" id="infoBox"></div>
                <div class="pagination" id="pagination">
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp"/>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/seajs/seajs/2.1.1/sea.js"></script>
<script src="${res}/scripts/seajs/seajs-style/1.0.2/seajs-style.js"></script>
<script src="${res}/scripts/seajs_config.js"></script>
<script src="${res}/scripts/common.js"></script>
<script src="${src}/common/base-init.js"></script>
<script src="${src}/fronts/help/help.js"></script>
<script>
    seajs.use(["pagination", "moment", "template", "djmenu"], function (Pagination, moment, template) {

        page = new Pagination({
            url: "${ctx}/search/rules",
            elem: "#pagination",
            method: "get",
            pageSize: 10,
            form: $("#searchForm"),
            handleData: function (json) {
                var html = template.render("message_tpl", json);
                $("#infoBox").html(html);
            }

        });

        $('#searchButton').on('click', function () {
            page.goTo(1);
        });
        /***Enter方法***/
        $('#searchForm .search-input').keypress(function (e) {
            var isFocus = $("#searchForm .search-input").is(":focus");
            if (isFocus && (e.which == 13 || e.which == 10)) {
                page.goTo(1);
            }
        });

        $('#tree').djMenu({
            url: "",         //请求地址
            parameter: "",   //请求参数
            val: "id",         //请求参数的 data-val
            templateId: "nav_list"   //需要使用模版的id
        });
    });
</script>
<script type="text/html" id="message_tpl">
    <div class="panel-head">
        <div class="panel-head-title"><h3>${rulesCate.cateName }<i>{{total}}</i>条：</h3></div>
    </div>
    <div class="panel-content">
        <ul class="cont-list cont-list-block">
            {{each rows}}
            {{if '工厂店采购保证金协议' != $value.articleTitle}}
            <li>
                <a href="${ctx}/rules/info/{{$value.refrenceId}}"><em></em>{{$value.articleTitle}}</a>
            </li>
            {{/if}}
            {{/each}}
        </ul>
    </div>
</script>

</body>

</html>
