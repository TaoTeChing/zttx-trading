<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-经销商财务账</title>
    <link href="${res}/styles/brand/global.css" rel="stylesheet"/>
    <link href="${res}/styles/brand/factory.css" rel="stylesheet"/>
    <style>
        .ui-form-item{display: inline-block;*display: inline;*zoom: 1;padding-left: 100px;}
        .ui-label{width: 100px;margin-left: -100px;}
    </style>
</head>
<!-- 工厂店账目"查看详情"页 -->
<body>
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100 layout">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <!--主体内容-->
            <div class="main_con">
                <!--面包屑-->
                <div class="bread_nav">
                    <div class="fl">
                        <a class="link" href="${ctx}/brand/center">管理中心</a>
                        <span class="arrow">&gt;&gt;</span>
                        <span class="current">终端商财务账</span>
                    </div>
                  <%--  <div class="fr">
                        <%@ include file="common_quick_bar.jsp" %>
                    </div>--%>
                </div>
                <div class="inner">
                        <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                            <div class="ui-form-item">
                                <label for="" class="ui-label"> 品牌： </label>
                                <input class="ui-input" type="text" name="brandesName" value="" style="width: 100px;"/>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label"> 产品名称： </label>
                                <input class="ui-input" type="text" name="productTitle" value="" style="width: 150px;"/>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label"> 产品货号： </label>
                                <input class="ui-input" type="text" name="productNo" value="" style="width: 150px;"/>
                            </div>
                            <div class="ui-form-item" style="padding-left: 0;">
                                <div class="inline-block">
                                    <input type="button" value="搜索" class="ui-button ui-button-mwhite event-query">
                                </div>
                            </div>
                        </form:form>
                    <%--详细表格--%>
                    <table class="factory-count-table ui-table mt10" id="count_detail">
                        <colgroup>
                            <col width="155">
                            <col width="90">
                            <col width="185">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="100">
                            <col width="120">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>时间</th>
                            <th>品牌</th>
                            <th>产品名称</th>
                            <th>产品货号</th>
                            <th>颜色/尺码</th>
                            <th>原价</th>
                            <th>现价</th>
                            <th>库存量</th>
                            <th>优惠/加价金额</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <!-- 分页 -->
                    <div id="pagination"></div>
                    <div class="factory-count-box clearfix" style="font-size: 12px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$getLocalTime $value.time}}</td>
        <td>{{$value.brandesName}}</td>
        <td>{{$value.productTitle}}</td>
        <td>{{$value.productNo}}</td>
        <td>{{$value.productSkuName}}</td>
        <td>{{$formatPrice $value.oldFacSkuDirPrice}}</td>
        <td>{{$formatPrice $value.nowFacSkuDirPrice}}</td>
        <td>{{$value.quantity}}</td>
        <td>{{$formatPrice $value.totalAdjustPrice}}</td>
    </tr>
    {{/each}}
    {{ if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{ /if }}
</script>

<script id="countTpl" type="text/html">
    <div class="fl" style="color: #999;">
        说明：负数表示优惠，正数表示加价。
    </div>
    <div class="fr">
        合计金额：<span class="consult" style="color: #f00;">{{if object.totalAdjustPrice!=null}}{{ $formatPrice object.totalAdjustPrice}}{{else}}0.00{{/if}}</span>&nbsp;
        共 <span class="consult"
                style="color: #f00;">{{if object.totalNum == null }}0{{else}}{{object.totalNum}}{{/if}}</span> 条
    </div>
</script>

<script>
    $(function () {
        seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
            template.helper('$formatPrice', function (price) {
                if (isNaN(price)) {
                    return price;
                } else {
                    return price.toFixed(2);
                }
            });
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });
            template.helper('$getLocalTime', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });

            window['g_pagination'] = new Pagination({
                url: "${ctx}/brand/brandFinancial/adjustPrice/data",
                elem: "#pagination",
                form: "#favorite",
                data: {isFacotryStore: true, dealerId: "${dealerId}", brandAdjustId: "${brandAdjustId}"},
                method: "post",
                pageSize: 20,
                handleData: function (json) {
                    var html1 = template.render("feedback-templage", json);
                    var html2 = template.render("countTpl", json);
                    $(".factory-count-table tbody").html(html1);
                    $(".factory-count-box").html(html2);
                }
            });
        });
        $(".event-query").click(function () {
            g_pagination.goTo(1);
        });
    });
</script>
</body>
</html>