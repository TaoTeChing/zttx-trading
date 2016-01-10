<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zttx.web.consts.ZttxConst" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>活动统计</title>
    <link rel="stylesheet" href="/styles/brand/global.css" />
    <link rel="stylesheet" href="/styles/brand/sign.css" />
</head>
<body class="sign-body">
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/brandes">品牌管理</a>
                <span class="arrow">&gt;</span>
                <a class="link" href="${ctx}/brand/activity/mylist">报名列表</a>
                <span class="arrow">&gt;</span>
                <span class="current">活动统计</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner sign-box">
            <div class="sign-count-head clearfix">
                <p class="fl mt5">
                <span>
                    当前参加活动的产品数：<strong id="totalProductCount" class="font_arial"></strong>
                </span><%--
                <span>
                    活动扣点：<strong class="font_arial">3%</strong>
                </span>
                <span>
                    活动总收益：<strong class="font_arial">￥<fmt:formatNumber value="${orderPriceSum}" type="currency" pattern="0.00" /></strong>
                </span>
            --%></p>
                <p class="fr">
                    <a href="<%=ZttxConst.PAYAPI_WEBURL_PORTAL%>" target="_blank" class="ui_button ui_button_lblue">查看账户余额</a>
                </p>
            </div>
            <div class="tips">
                <i class="v2-icon-explain"></i>
                提示：总销售额、扣点金额、总收益均根据所下订单统计得出，具体金额以实际收益为准；
            </div>
            <table class="sign-count-table mt10" id="js-sc-table">
                <colgroup>
                    <col width="116"/>
                    <col width="80"/>
                    <col width="66"/>
                    <col width="66"/>
                    <col width="125"/>
                    <col width="76"/>
                    <col width="80"/>
                    <col width="80"/>
                    <col width="80"/>
                    <col width="80"/>
                    <col width="80"/>
                    <col width="80"/>
                </colgroup>
                <thead>
                <tr>
                    <th>产品货号</th>
                    <th>品牌</th>
                    <th>扣点</th>
                    <th>产品线</th>
                    <th>上架时间</th>
                    <th>现单价</th>
                    <th>总浏览量</th>
                    <th>总销售量</th>
                    <th>总销售额</th>
                    <th>转化率</th>
                    <th>扣点金额</th>
                    <th class="last">总收益</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div id="pagination" class="pagination mt10">
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="temp-count" class="ajax-templage" _url="${ctx}/brand/activity/statistics/data" _page="#pagination" _pid="#js-sc-table tbody" type="text/html">
    {{each rows}}
    <tr>
        <td rowspan="{{$value.lineList.length}}"><a href ="${ctx}/market/product/{{$value.productId}}" target="_blank">{{$value.productNo}}</a></td>
        <td rowspan="{{$value.lineList.length}}">{{$value.brandsName}}</td>  
        <td rowspan="{{$value.lineList.length}}">{{$formatPercent $value.pointBalance 1}}</td>      
		{{each $value.lineList}}
            {{if $index == 0}}
            <td>{{$value.lineName}}</td>
            <td>{{$formatDate $value.createTime}}</td>
            <td>{{$formatMoney $value.price}}</td>
            {{/if}}
        {{/each}}
        <td rowspan="{{$value.lineList.length}}">{{$value.viewNum}}</td>
        <td rowspan="{{$value.lineList.length}}">{{$value.quantitySum}}</td>
        <td rowspan="{{$value.lineList.length}}">{{$formatMoney $value.priceSum}}</td>
        <td rowspan="{{$value.lineList.length}}">{{$formatPercent $value.convert 1}}</td>
        <td rowspan="{{$value.lineList.length}}">{{$formatMoney $value.pointAmountSum}}</td>
        <td rowspan="{{$value.lineList.length}}" class="last">{{$formatMoney $value.revenue}}</td>
    </tr>
        {{each $value.lineList}}
            {{if $index > 0}}
            <tr>
                <td>{{$value.lineName}}</td>
                <td>{{$formatDate $value.createTime}}</td>
                <td class="last">{{$formatMoney $value.price}}</td>
            </tr>
            {{/if}}
        {{/each}}
    {{/each}}
</script>
<script>
    $(function(){
        seajs.use(["pagination", "template", "moment"],function(Pagination, Template, Moment){
            var $this = $("#temp-count");
            var tempId = $this.attr("id");

            template.helper('$formatMoney', function (num) {
                num = num == null ? 0 : num;
                return num.toFixed(2);
            });
            template.helper('$formatDate', function (millsec) {
                return Moment(millsec).format("YYYY-MM-DD HH:mm");
            });
            template.helper('$formatPercent', function (num1, num2) {
                var num = num1 / num2 * 100;
                return num.toFixed(2) + "%";
            });

            pageTemp = new Pagination({
                url: $this.attr("_url"),
                elem: $this.attr("_page"),
                method: "post",
                handleData: function (data) {
                    $("#totalProductCount").html(data.total);
                    var html = template.render(tempId, data);
                    $($this.attr("_pid")).html(html);
                }
            });
        });
    });
</script>
</body>
</html>