<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-品牌财务账</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/dealer/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/dealer/dealermenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">品牌财务账</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <form:form id="favorite" name="pageForm" class="ui-form" onsubmit="return false;">
                        <input type="hidden" name="productId" id="productId"/>
                        <div>
                            <label>品牌商名称：</label>
                            <input class="ui-input" type="text" name="brandName"/>
                            <input type="button" class="ui-button ui-button-mwhite" style="vertical-align: -1px" value='搜 索' />
                        </div>
                    </form:form>
		<div class="factory-count">
                <table class="factory-count-table ui-table">
                    <thead>
                    <tr>
                        <th style="width:40%;">品牌商名称</th>
                        <th style="width:20%;">应付货款（元）</th>
                        <th style="width:20%;">已付货款（元）</th>
                        <th style="width:20%;">欠付货款（元）</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <div id="pagination"></div>
                <div class="factory-count-box"></div>
		</div>
	    </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$value.comName}}</td>
        <td>{{$formatPrice $value.amountAry[0]}}</td>
        <td>{{$formatPrice $value.amountAry[1]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
    </tr>
    {{/each}}
    {{if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="4">暂无数据</td>
    </tr>
    {{/if }}
</script>
<script id="countTpl" type="text/html">
    {{if rows.length == 0 }}
    合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
    已付货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
    欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
    {{else}}
    合计 应付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[0]}}</span> &nbsp;|&nbsp;
    已付货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[1]}}</span> &nbsp;|&nbsp;
    欠付货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[2]}}</span>
    {{/if }}
</script>
<script>
    var page;
    $(function () {
        seajs.use(["pagination","template"], function (Pagination,template) {
            template.helper('$formatPrice', function (price) {
                return price.toFixed(2);
            });
            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/dealer/order/financial/data",
                    elem: "#pagination",
                    form:$("#favorite"),
                    method:"post",
                    handleData: function (json) {
                        var html1 = template.render("feedback-templage",json);
                        var html2 = template.render("countTpl",json);
                        $(".factory-count-table tbody").html(html1);
                        $(".factory-count-box").html(html2);
                    }
                });
            };
            renderPagination();
        });
        $(".ui-button").click(function(){
            page.goTo(1);
        });
    });
</script>
</body>
</html>