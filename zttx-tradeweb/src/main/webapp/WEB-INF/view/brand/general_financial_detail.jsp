<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-终端财务账</title>
    <link href="${res}/styles/brand/global.css" rel="stylesheet" />
    <link href="${res}/styles/brand/factory.css" rel="stylesheet" />
    <style>
        .ui-form-item{display: inline-block;*display: inline;*zoom: 1;padding-left: 100px;}
        .ui-label{width: 100px;margin-left: -100px;}
    </style>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu" />
<div class="main layout">
    <!--侧边导航-->
    <jsp:include page="${ctx}/common/menuInfo/sidemenu" />
    <!--主体内容-->
    <div class="main_con">
        <!--面包屑-->
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
            <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                <input type="hidden" value="${dealerInfo.refrenceId}" name ="dealerId">
                <input type="hidden" value="true" name = "factoryStore">
                <div class="ui-form-item">
                    <label for="" class="ui-label">终端商名称： </label> <input
                        class="ui-input" type="text" disabled  id="brandName"
                        style="width: 210px;"  value="${dealerInfo.dealerName}"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">联系人姓名：</label> <input
                        class="ui-input" type="text" disabled id="brandUser"
                        style="width: 210px;" value=""/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">联系方式：</label> <input
                        class="ui-input" type="text" disabled  id="brandMobile"
                        style="width: 210px;" value="${dealerInfo.userMobile}" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">所在地：</label>
                    <input class="ui-input" type="text" disabled  id="address" style="width: 210px;"  value="${fns:getFullAreaNameByNo(dealerInfo.areaNo)}" />
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label">日期：</label>
                    <input type="text"
                           class="ui-input date" id="start-cal" readonly
                           style="width: 90px;"  name="startTime"/> 至
                    <input type="text"
                           class="ui-input date" id="end-cal" readonly
                           style="width: 90px;"  name="endTimer"/>
                </div>
                <div class="ui-form-item">
                    <label for="" class="ui-label"> 类型： </label>
                    <div class="inline-block pr">
                        <select class="js_select" name="type" style="width:220px;height: 30px;">
                            <option value="">请选择</option>
                            <c:forEach var="type" items="${tradeType}">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="ta-r fr mt10">
                    <input type="button" value="搜索"
                           class="ui_button_myourself event-query" style="margin-right: 35px;">
                </div>
            </form:form>
            <%--详细表格--%>
            <table class="factory-count-table ui-table mt10" id="count_detail">
                <colgroup>
                    <col width="165">
                    <col width="215">
                    <col width="210">
                    <col width="220">
                    <col width="210">
                </colgroup>
                <thead>
                <tr>
                    <th>时间</th>
                    <th>单号</th>
                    <th>类型</th>
                    <th>应收</th>
                    <th>已收</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <!-- 分页 -->
            <div id="pagination"></div>
            <div class="factory-count-box" style="font-size: 12px;"></div>
            <%--合计表格--%>
            <%--<table class="factory-count-box ui-table" id="count_count"
                style="border-top:0;">
                <colgroup>
                    <col width="85">
                    <col width="115">
                    <col width="90">
                    &lt;%&ndash;
                    <col width="90">
                    <col width="90">
                     &ndash;%&gt;
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    <col width="90">
                    &lt;%&ndash;<col width="90"> &ndash;%&gt;
                    <col width="90">
                </colgroup>
                <tbody>
                </tbody>
            </table>--%>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{else}}
    {{each rows}}
    <tr>
        <td>{{$formatDate $value.createTime}}</td>
        <td>
            {{if $value.type=='DealerOrder'}}<a class="link" href="${ctx}/brand/order/view/{{$value.refrenceId}}">{{$value.orderId}}</a>{{/if}}
            {{if $value.type=='DealerRefund'}}<a class="link" href="${ctx}/brand/refund/factory/view/{{$value.orderId}}">{{$value.orderId}}</a>{{/if}}
        </td>
        {{if $value.type == "DealerOrder"}}
        <td> 采购</td>
        <td>{{$formatPrice $value.productPrice}}</td>
        <td>{{$formatPrice $value.payment}}</td>
        {{else if $value.type == "DealerRefund"}}
        <td>退货/退款</td>
        <td>-{{$formatPrice $value.totalRefundAmount}}</td>
        <td>-{{$formatPrice $value.paidRefundAmount}}</td>
        {{else if $value.type == "DealerJoin"}}
        <td>缴纳押金</td>
        <td>{{$formatPrice $value.paidAmount}}</td>
        <td>{{$formatPrice $value.paidAmount}}</td>
        {{else if $value.type == "Adjustment"}}
        <td>库存产品调价</td>
        <td><a class="link" href="${ctx}/brand/brandFinancial/adjustPrice?dealerId=${param.dealerId}&&adjustId={{$value.refrenceId}}">{{$formatPrice $value.adjustAllPrice}}</a></td>
        <td></td>
        {{else if $value.type == "CreditToPoint"}}
        <td>授信转返点</td>
        <td><a class="link" href="${ctx}/brand/brandFinancial/creditToPoint?dealerId=${param.dealerId}">-{{$formatPrice $value.costPrice}}</a></td>
        <td></td>
        {{else if $value.type == "DealerClearing"}}
        <td>当期欠付款</td>
        <td></td>
        <td>{{$formatPrice $value.clearingAmount}}</td>
        {{/if}}


    </tr>
    {{/each}}
    {{/if}}
</script>

<script id="countTpl" type="text/html">
    合计
    应收：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allProductPrice}}</span>&nbsp;|&nbsp;
    已收：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allPayment}}</span>&nbsp;|&nbsp;
    未收：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allDebtPrice}}</span>&nbsp;

</script>

<script>
    $(function() {
        seajs.use(["pagination", "template","moment"], function(Pagination,template,moment){

            rangeCalendar("start-cal","end-cal");

            template.helper('$formatPrice', function (price) {
                if(isNaN(price)){
                    return price;
                }else{
                    return price.toFixed(2);
                }
            });
            template.helper('$formatDate', function (millsec) {
                return moment(millsec).format("YYYY-MM-DD HH:mm:00");
            });

            window['g_pagination']  = new Pagination({
                url:"${ctx}/brand/brandFinancial/financial/detail/data",
                elem:"#pagination",
                form:"#favorite",
                method:"post",
                handleData:function(json)
                {
                    console.log(json);
                    var html1 = template.render("feedback-templage",json);
                    var html2 = template.render("countTpl",json);
                    $(".factory-count-table tbody").html(html1);
                    $(".factory-count-box").html(html2);
                }
            });
            $(".event-query").click(function(){
                g_pagination.goTo(1);
            });
        });
    });
</script>
</body>
</html>