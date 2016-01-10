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
                <a class="link" href="${ctx}/brand/order/financial">终端商财务账</a>
                <span class="arrow">&gt;&gt;</span>
                <span class="current">工厂店财务账明细</span>
            </div>
            <div class="fr">
                <%@ include file="common_quick_bar.jsp" %>
            </div>
        </div>
        <!-- 面包屑结束 -->
        <div class="inner">
            <!-- 内容都放这个地方  -->
            <form:form class="ui-form factory-seeking-form clearfix" action="">
                <input type="hidden" name="dealerId" value="${param.dealerId}">
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        终端商名称：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealer.dealerName}" style="width: 155px;" />
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        联系人姓名：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealer.dealerUser}" style="width: 155px;" />
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        联系方式：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealer.dealerMobile}" style="width: 155px;" />
                </div>
                <div class="ui-form-item" style="width: 240px;">
                    <label for="" class="ui-label">
                        所在地：
                    </label>
                    <input class="ui-input" type="text" disabled value="${dealer.provinceName} ${dealer.cityName} ${dealer.areaName}" style="width: 155px;" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">
                        日期：
                    </label>
                    <input type="text" name="startTimeStr" class="ui-input Wdate" id="start-cal" readonly style="width: 100px;"/> 至 <input type="text" name="endTimeStr" class="ui-input Wdate" id="end-cal" readonly style="width: 100px;"/>
                </div>
                <div class="ui-form-item" style="width: 300px;">
                    <label for="" class="ui-label">
                        类型：
                    </label>
                    <div class="inline-block pr">
                        <select name="orderType" id="" class="js_select" style="width: 150px;height: 30px;">
                            <option value="">请选择</option>
                            <option  value="销售">销售</option>
                            <option  value="销售退款">退货退款</option>
                            <option  value="押金">缴纳押金</option>
                            <option value="库存产品调价">库存产品调价</option>
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
                    <col width="100">
                    <col width="100">
                    <col width="110">
                    <col width="110">
                    <col width="110">
                    <col width="110">
                </colgroup>
                <thead>
                <tr>
                    <th>时间</th>
                    <th>单号</th>
                    <th>类型</th>
                    <th>应收</th>
                    <th>已收</th>
                    <th>未收</th>
                    <th>应付</th>
                    <th>应收抵欠付</th>
                </tr>
                </thead>
                <tbody>
                <%--<c:forEach var="dealerOrder" items="${result.getList()}">
                    <c:if test="${dealerOrder.type eq 'DealerOrder'}">
                        <tr>
                            <td>${fns:getTimeFromLong(dealerOrder.createTime,'yyyy-MM-dd HH:mm:00') }</td>
                            <td><span style="color: #0082cc;">${dealerOrder.orderId}</span></td>
                            <td>销售</td>
                            <td>${dealerOrder.productPrice }</td> <!-- 含有运费 -->
                            <td>${dealerOrder.payment}</td>
                            <td>${dealerOrder.debtPrice }</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:if>
                    <c:if test="${dealerOrder.type eq 'DealerRefund'}">
                        <tr>
                            <td>${fns:getTimeFromLong(dealerOrder.createTime,'yyyy-MM-dd HH:mm:00')}</td>
                            <td><span style="color: #0082cc;">${dealerOrder.orderId}</span></td>
                            <td>销售退款</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${dealerOrder.totalRefundAmount}</td><!-- 状态为2,9,10 -->
                            <td>${dealerOrder.collectOffsetPay}</td>  <!-- 状态为9,10 -->
                        <tr>
                    </c:if>
                     <c:if test="${dealerOrder.type eq 'DealerJoin'}">
                        <tr>
                            <td>${fns:getTimeFromLong(dealerOrder.createTime,'yyyy-MM-dd HH:mm:00')}</td>
                            <td><span style="color: #0082cc;">${dealerOrder.orderId}</span></td>
                            <td>押金</td>
                            <td>${dealerOrder.paidAmount}</td>
                            <td>${dealerOrder.paidAmount}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        <tr>
                    </c:if>
                </c:forEach>--%>
                </tbody>
            </table>
            <div id="pagination"></div>
            <div class="factory-count-box"></div>
            <%--<div class="factory-count-box">
                合计 应收:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allProductPrice }</span> &nbsp;|&nbsp;
                已收:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allPayment }</span> &nbsp;|&nbsp;
                未收:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allDebtPrice }</span> &nbsp;|&nbsp;
                退款:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allTotalRefundAmount }</span> &nbsp;|&nbsp;
                已付:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allHasRefundAmount }</span> &nbsp;|&nbsp;
                欠付:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allDebtRefundAmount }</span> &nbsp;|&nbsp;
                押金:<em class="fn-rmb">￥</em><span class="consult">${sumResult.allPaidAmount}</span>
            </div>--%>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
    {{each rows}}
    {{if $value.type == "DealerOrder"}}
    <tr>
        <td>{{$getLocalTime $value.createTime}}</td>
        <td><a href="${ctx}/brand/order/detail/{{$value.orderId}}" target="_blank"><span style="color: #0082cc;">{{$value.orderId}}</span></a></td>
        <td>销售</td>
        <td>{{$formatPrice $value.productPrice}}</td>
        <td>{{$formatPrice $value.payment}}</td>
        <td>{{$formatPrice $value.debtPrice}}</td>
        <td></td>
        <td></td>
    </tr>
    {{else if $value.type == "DealerRefund"}}
    <tr>
        <td>{{$getLocalTime $value.createTime}}</td>
        <td><a href="${ctx}/brand/refund/factory/view/{{$value.orderId}}" target="_blank"><span style="color: #0082cc;">{{$value.orderId}}</span></a></td>
        <td>退货退款</td>
        <td></td>
        <td></td>
        <td></td>
        <td>{{$formatPrice $value.totalRefundAmount}}</td>
        <td>{{$formatPrice $value.collectOffsetPay}}</td>
    </tr>
    {{else if $value.type == "DealerJoin"}}
    <tr>
        <td>{{$getLocalTime $value.createTime}}</td>
        <td><span style="color: #0082cc;">{{$value.orderId}}</span></td>
        <td>缴纳押金</td>
        <td>{{$formatPrice $value.paidAmount}}</td>
        <td>{{$formatPrice $value.paidAmount}}</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    {{else if $value.type == "BrandAdjustments"}}
    <tr>
        <td>{{$getLocalTime $value.createTime}}</td>
        <td></td>
        <td>库存产品调价</td>
        <td><a class="link" href='${ctx}/brand/order/financial/factory/adjustPrice?adjustId={{$value.refrenceId}}&&dealerId=${dealerId}'>{{$formatPrice $value.adjustAllPrice}} </a></td>
        <td></td>
        <td>{{$formatPrice $value.adjustAllPrice}}</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    {{/if}}
    {{/each}}
    {{ if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="9">暂无数据</td>
    </tr>
    {{ /if }}
</script>
<script id="countTpl" type="text/html">
    合计 应收:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allProductPrice}}</span> &nbsp;|&nbsp;
    已收:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allPayment}}</span> &nbsp;|&nbsp;
    未收:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allDebtPrice}}</span> &nbsp;|&nbsp;
    退款:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allTotalRefundAmount}}</span> &nbsp;|&nbsp;
    已付:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allHasRefundAmount}}</span> &nbsp;|&nbsp;
    欠付:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allDebtRefundAmount}}</span> &nbsp;|&nbsp;
    缴纳押金:<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.allPaidAmount}}</span>
</script>
<script>

    $(function () {
        seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
            var page;
            template.helper('$getLocalTime', function(millsec){
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });
            template.helper('$formatPrice', function (price) {
                if(isNaN(price)){
                    return price;
                }else{
                    return price.toFixed(2);
                }
            });
            var renderPagination = function(){
                page = new Pagination({
                    url: "${ctx}/brand/order/financialData/detail/data",
                    elem: "#pagination",
                    data:{factoryStore:true},
                    form:$(".ui-form"),
                    method:"post",
                    pageSize: 20,
                    handleData: function (json) {
                        //console.log(json);
                        var html1 = template.render("feedback-templage",json);
                        var html2 = template.render("countTpl",json);
                        $(".factory-count-table tbody").html(html1);
                        $(".factory-count-box").html(html2);
                    }
                });
            };
            renderPagination();
            $(".ui_button_myourself").click(function(){
                page.goTo(1);
            });

            //时间选取
            if($("#start-cal").length > 0 && $("#end-cal").length > 0){
                rangeCalendar("start-cal","end-cal");
            }
        });
    });
</script>
</body>
</html>