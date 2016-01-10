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
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 面包导航，并不是每个页面都有 -->
            <div class="bread_nav">
                <div class="fl">
                    <i class="icon"></i>
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <span class="current">终端商财务账</span>
                </div>
                <div class="fr">
                    <%@ include file="common_quick_bar.jsp" %>
                </div>
            </div>
            <!-- 面包屑结束 -->
            <form:form class="ui-form factory-seeking-form clearfix" action="">
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        终端商名称：
                    </label>
                    <span style="line-height: 30px;">
                        123123123
                    </span>
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        联系人姓名：
                    </label>
                    <span style="line-height: 30px;">
                        123123123
                    </span>
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        联系方式：
                    </label>
                    <span style="line-height: 30px;">
                        123123123
                    </span>
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        所在地：
                    </label>
                    <span style="line-height: 30px;">
                        浙江 宁波 海曙
                    </span>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        日期：
                    </label>
                    <input type="text" class="ui-input Wdate" id="start-cal" readonly style="width: 100px;"/> 至 <input type="text" class="ui-input Wdate" id="end-cal" readonly style="width: 100px;"/>
                </div>
                <div class="ui-form-item" style="width: 300px;">
                    <label for="" class="ui-label">
                        类型：
                    </label>
                    <div class="inline-block pr">
                        <select name="aaa" id="" class="js_select" style="width: 150px;height: 30px;">
                            <option value="">请选择</option>
                            <option value="">1</option>
                            <option value="">2</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item" style="width: 100px;">
                    <div class="inline-block">
                        <input type="button" value="搜索" class="ui_button_myourself">
                    </div>
                </div>
            </form:form>
            <div class="ta-r mr10">
                金额单位：元
            </div>
            <table class="factory-count-table" id="count_detail">
                <colgroup>
                    <col width="175">
                    <col width="205">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                </colgroup>
                <thead>
                <tr>
                    <th>时间</th>
                    <th>单号</th>
                    <th>类型</th>
                    <th>应收</th>
                    <th>已收</th>
                    <th>应付</th>
                    <th>已付</th>
                    <th>欠收抵应付</th>
                    <th>欠收</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>2015-3-1 10:00:00</td>
                    <td><span style="color: #0082cc;">3757204142</span></td>
                    <td>销售</td>
                    <td>50000.00</td>
                    <td>50000.00</td>
                    <td>50000.00</td>
                    <td>50000.00</td>
                    <td>50000.00</td>
                    <td>50000.00</td>
                </tr>
                </tbody>
            </table>
            <div id="pagination"></div>
            <table class="factory-count-table" id="count_count" style="border-top:0;">
                <colgroup>
                    <col width="175">
                    <col width="205">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                </colgroup>
                <tbody>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td>5000.00</td>
                        <td>5000.00</td>
                        <td>5000.00</td>
                        <td>5000.00</td>
                        <td>5000.00</td>
                        <td>5000.00</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<%--<script id="feedback-templage" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$value.dealerName}}</td>
        <td>{{$value.provinceName}} {{$value.cityName}} {{$value.areaName}}</td>
        <td>{{$value.userMobile}}</td>
        <td>{{$value.dealerUser}}</td>
        <td>{{$formatPrice $value.amountAry[0]}}</td>
        <td>{{$formatPrice $value.amountAry[1]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
        <td>{{$formatPrice $value.amountAry[2]}}</td>
    </tr>
    {{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="7">暂无数据</td>
    </tr>
    {{ /if }}
</script>--%>
<%--<script id="countTpl" type="text/html">
    {{ if rows.length == 0 }}
    合计 应收货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
    已收货款：<em class="fn-rmb">￥</em><span class="consult">0.00</span> &nbsp;|&nbsp;
    欠收货款：<em class="fn-rmb">￥</em><span class="consult-r">0.00</span>
    {{else}}
    合计 应收货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[0]}}</span> &nbsp;|&nbsp;
    已收货款：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.amountTotalAry[1]}}</span> &nbsp;|&nbsp;
    欠收货款：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.amountTotalAry[2]}}</span>
    {{ /if }}
</script>--%>
<script>
    var page;
    $(function () {
        /*seajs.use(["pagination","template"], function (Pagination,template) {
            template.helper('$formatPrice', function (price) {
                return price.toFixed(2);
            });
            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/brand/order/financial/data",
                    elem: "#pagination",
                    form:$(".ui-form"),
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
        $(".ui_button_myourself").click(function(){
            page.goTo(1);
        });*/

        //时间选取
        if($("#start-cal").length > 0 && $("#end-cal").length > 0){
            rangeCalendar("start-cal","end-cal");
        }
    });
</script>
</body>
</html>