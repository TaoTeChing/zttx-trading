<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-终端商财务账</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/factory.css" />
</head>
<body>
<jsp:include page="${ctx}/brand/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/brand/brandmenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">终端商财务账</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 面包屑结束 -->
            <form:form class="ui-form factory-seeking-form clearfix ml10" action="">
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        终端商名称：
                    </label>
                   <input type="text" class="ui-input" name="dealerName"/>
                </div>
                <!--  以下搜索功能已经实现,可以直接去掉注释用
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        联系人姓名：
                    </label>
                    <input type="text" class="ui-input" name="contact"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        电话号码：
                    </label>
                    <input type="text" class="ui-input" name="userMobile"/>
                </div>
                 -->
                <div class="ui-form-item" style="width: 465px;">
                    <label for="" class="ui-label">
                 
                        地区：
                    </label>
                    <jsp:include page="${ctx}/client/Regional/searchaAll">
                        <jsp:param value="${searchModel.province}" name="regProvince" />
                        <jsp:param value="${searchModel.city}" name="regCity" />
                        <jsp:param value="${searchModel.county}" name="regCounty" />
                        <jsp:param value="test1" name="sale" />
                        <jsp:param value="ui-select js_select seeking-select-width" name="style" />
                    </jsp:include>
                </div>
                <div class="ui-form-item" style="width: 100px;">
                    <div class="inline-block">
                        <input type="button" value="搜索" class="ui_button_myourself">
                    </div>
                </div>
            </form:form>
            <div class="js_grademanage_menu mt10 pr">
                <ul class="clearfix">
                    <li><a href="${ctx}/brand/order/financial?isFacotryStore=false" style="display: block">普通财务账</a></li>
                    <li class="selected"><a href="${ctx}/brand/order/financial?isFacotryStore=true" style="display: block">工厂店财务账</a></li>
                </ul>
                <div class="unit">金额单位：元</div>
            </div>
            <table class="factory-count-table mt10">
                <colgroup>
                    <col width="100">
                    <col width="150">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="90">
                    <col width="70">
                </colgroup>
                <thead>
                <tr>
                    <th>终端商名称</th>
                    <%--
                    <th>联系人</th>
                    <th>联系方式</th>
                    --%>
                    <th>所在地</th>
                    <th>应收货款</th>
                    <th>已收货款</th>
                    <th>应付退款</th>
                    <th>应收抵欠付款</th>
                    <th>未收货款</th>
                    <th>押金</th>
                    <th>当期应收款</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div id="pagination"></div>
            <div class="factory-count-box"></div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
        <!--
        <td>{{$value.dealerUser}}</td>
        <td>{{$value.userMobile}}</td>
        -->
<script id="feedback-templage" type="text/html">
    {{each rows}}
    <tr>
        <td><a href="${ctx}/brand/dealer/info/{{$value.dealerId}}" target="_blank">{{$value.dealerName}}</a></td>
        <td>{{$value.provinceName}} {{$value.cityName}} {{$value.areaName}}</td>
        <td>{{$formatPrice $value.amountAry[0]}}</td>
        <td>{{$formatPrice $value.amountAry[1]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
        <td>{{$formatPrice $value.amountAry[6]}}</td>
        <td>{{$formatPrice $value.amountAry[4]}}</td>
        <td>{{$formatPrice $value.amountAry[8]}}</td>
        <td><a class="link" href="${ctx}/brand/order/financial/factory/currentPay?dealerId={{$value.dealerId}}&&factoryStore=true" target="_blank">{{$formatPrice ($value.needPayAmount==null?0:$value.needPayAmount)}}</a></td>
        <td><a class="link" href="${ctx}/brand/order/financialData/detail?dealerId={{$value.dealerId}}&&factoryStore=true" target="_blank">查看明细</a></td>
    </tr>
    {{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="7">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script id="countTpl" type="text/html">
    {{ if rows.length == 0 }}
        合计 应收货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
                    已收货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
                    未收货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
                    已交押金：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
    {{else}}
        合计 应收货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[0]}}</span> &nbsp;|&nbsp;
                  已收货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[1]}}</span> &nbsp;|&nbsp;
    <!-- 
     应付退款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[2]}}</span> &nbsp;|&nbsp;
     已付退款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[3]}}</span> &nbsp;|&nbsp;
    欠付退款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[5]}}</span>
  -->     

      未收货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[4]}}</span> &nbsp;|&nbsp;
                已交押金: <em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[8]}}</span> &nbsp;|&nbsp;

    {{ /if }}
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
                    url: "${ctx}/brand/order/financial/data",
                    data:{factoryStore:true},
                    elem: "#pagination",
                    form:$(".ui-form"),
                    method:"post",
                    pageSize:20,
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
        $(".ui_button_myourself").click(function(){
            page.goTo(1);
        });
    });
</script>
</body>
</html>