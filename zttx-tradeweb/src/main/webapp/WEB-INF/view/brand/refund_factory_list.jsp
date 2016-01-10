<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-工厂店退款处理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/factory.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
<div class="main layout">
    <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
    <div class="main_con">
        <!-- 面包导航，并不是每个页面都有 -->
        <div class="bread_nav">
            <div class="fl">
                <a class="link" href="${ctx}/brand/center">管理中心</a>
                <span class="arrow">&gt;&gt;</span>
                <a class="link" href="${ctx}/brand/order/purorder">交易管理</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">总账退款处理</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <!-- 面包屑结束 -->
            <form:form class="ui-form factory-seeking-form clearfix" action="${ctx}/brand/refund/factory">
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        退款类型：
                    </label>

                    <div class="inline-block pr">
                        <select name="refundType" id="refundType" class="js_select" style="width: 150px;height: 30px;">
                            <option value="">请选择</option>
                            <option value="1">退款退货</option>
                            <option value="0">退款不退货</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        退款单号：
                    </label>
                    <input class="ui-input" id="refundId" name="refundId" type="text" style="width: 155px;"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        状态：
                    </label>

                    <div class="inline-block pr">
                        <select name="refundState" id="refundState" class="js_select" style="width: 150px;height: 30px;">
                            <option value="">请选择</option>
                            <option value="1">等待处理</option>
                            <option value="2">处理中</option>
                            <option value="3">拒绝退款</option>
                            <option value="4">拒绝退货</option>
                            <option value="5">退款关闭</option>
                            <option value="6">已撤销</option>
                            <option value="7">处理完成</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item" style="float: right;margin-right: 24px;">
                    <label class="ui-label">&nbsp;</label>
                    <div class="inline-block">
                        <input type="button" value="搜索" class="ui_button_myourself"><span style="margin-left:10px;line-height: 30px;">金额单位：元</span>
                    </div>
                </div>
            </form:form>
            <table class="factory-count-table mt10" id="count_detail">
                <colgroup>
                    <col width="175">
                    <col width="185">
                    <col width="120">
                    <col width="90">
                    <col width="120">
                    <col width="115">
                    <col width="115">
                    <col width="90">
                </colgroup>
                <thead>
                <tr>
                    <th>日期</th>
                    <th>终端商名称</th>
                    <th>单号</th>
                    <th>退款类型</th>
                    <th>退款金额</th>
                    <th>欠收抵应付金额</th>
                    <%--<th>直接支付金额</th>  --%>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div id="pagination"></div>
        </div>
    </div>
</div>
<script id="feedback-templage" type="text/html">
    {{each rows}}
    <tr>
        <td>{{$getLocalTime $value.updateTime}}</td>
        <td><a class="link" href="${ctx}/brand/dealer/info/{{$value.dealerId}}">{{$value.dealerName}}</a></td>
        <td>{{$value.refundId}}</td>
        <td>
            {{if $value.needRefund == true}}
                退款退货
            {{else}}
                退款不退货
            {{/if}}
        </td>
        <td>{{$formatPrice $value.refundAmount}}</td>
        <td>
            {{if $value.reachAmount != null}}
                {{$formatPrice $value.reachAmount}}
            {{else}}
                0.00
            {{/if}}
        </td>
        <%--<td>{{$formatPrice $value.comPayment}}</td>--%>
        <td>
            {{if $value.refundState == 1}}
                等待处理
            {{else if $value.refundState  == 2 || $value.refundState == 3}}
                处理中
            {{else if $value.refundState == 4}}
                拒绝退款
            {{else if $value.refundState == 5}}
                拒绝退货
            {{else if $value.refundState == 6}}
                退款关闭
            {{else if $value.refundState == 7}}
                已撤销
            {{else if $value.refundState == 9 || $value.refundState == 10}}
                处理完成
            {{/if}}
        </td>
        <td><a class="link" href="${ctx}/brand/refund/factory/view/{{$value.refundId}}">退款详情</a></td>
    </tr>
    {{/each}}
</script>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp"/>
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp"/>
<script>
    $(function () {
        seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
            var page;
            template.helper('$formatPrice', function (price) {
                if(isNaN(price)){
                    return price;
                }else{
                    return price.toFixed(2);
                }
            });
            template.helper('$getLocalTime', function(millsec){
                return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
            });

            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/brand/refund/factory/data",
                    elem: "#pagination",
                    form: $(".ui-form"),
                    method: "post",
                    pageSize: 20,
                    handleData: function (json) {
                        var html1 = template.render("feedback-templage",json);
                        $(".factory-count-table tbody").html(html1);
                    }
                });
            };
            renderPagination();
            $(".ui_button_myourself").click(function(){
                page.goTo(1);
            });
        });

    });
</script>
</body>
</html>