<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-品牌财务账</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
    <style>
        .ui-form-item{display: inline-block;*display: inline;*zoom: 1;padding-left: 100px;}
        .ui-label{width: 100px;margin-left: -100px;}
    </style>
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
                    <div class="main-grid">
                        <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                            <input type="hidden" name="brandId" id="brandId" value="${param.brandId}"/>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                      品牌商名称：
                                </label>
                                <input class="ui-input" type="text" id="brandName"  disabled style="width: 200px;" />
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                     联系人姓名：
                                </label>
                                <input class="ui-input" type="text"  id="brandUser" value="" disabled style="width: 190px;" />
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                     联系方式：
                                </label>
                                <input class="ui-input" type="text"  id="brandMobile" value="" disabled style="width: 190px;" />
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                     所在地：
                                </label>
                                <input class="ui-input" type="text" id="address" value="" disabled style="width: 200px;" />
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                        日期：
                                </label>
                                <input type="text" name ="startTimeStr" class="ui-input date" id="start-cal" readonly style="width: 74px;"/> 至 <input type="text"  name = "endTimeStr" class="ui-input date" id="end-cal" readonly style="width: 74px;"/>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                     类型：
                                </label>
                                <select class="ui-select" name="orderType" id="" style="width:210px;height: 34px;">
                                    <option value="">请选择</option>
                                    <option value="采购">采购</option>
                                    <option value="退货退款">退货退款</option>
                                </select>
                            </div>
                            <div class="ui-form-item">
                                <div class="inline-block">
                                    <input type="button" value="搜索" class="ui-button ui-button-mwhite">
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <%--详细表格--%>
                    <table class="factory-count-table ui-table" id="count_detail">
                        <colgroup>
                            <col width="165">
                            <col width="115">
                            <col width="90">
                            <%--<col width="90">
                            <col width="90">--%>
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
                                <%--<th>支出</th>
                                <th>收入</th>--%>
                                <th>应付</th>
                                <th>已付</th>
                                <th>欠付</th>
                                <th>应收</th>
                                <th>已收</th>
                                <th>欠收</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%--<tr>
                                <td>2015-3-1 10:00:00</td>
                                <td><span style="color: #0082cc;">3757204142</span></td>
                                <td>销售</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                                <td>50000.00</td>
                            </tr>--%>
                        </tbody>
                    </table>
                    <%--合计表格--%>
                    <%--<table class="ui-table" id="count_count" style="height:30px;line-height:30px;border-top:0;">
                        <colgroup>
                            <col width="85">
                            <col width="115">
                            <col width="90">
                            <col width="90">
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
                                <td>5000.00</td>
                                <td>5000.00</td>
                            </tr>
                        </tbody>
                    </table>--%>
                    <%--分页--%>
                    <div id="pagination"></div>
                    <div class="factory-count-box" style="font-size: 12px;"></div>
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
        <td>{{$formatDate $value.createTime}}</td>
        <td>
			{{if $value.type=='DealerOrder'}}<a class="link" href="${ctx}/dealer/order/view/{{$value.refrenceId}}">{{$value.orderId}}</a>{{/if}}
			{{if $value.type=='DealerRefund'}}<a class="link" href="${ctx}/dealer/refund/details?orderNumber={{$value.orderNumber}}">{{$value.orderId}}</a>{{/if}}
		</td>
        <td>
			{{if $value.type=='DealerOrder'}}采购 {{/if}}
			{{if $value.type=='DealerRefund'}}退货退款 {{/if}}
		</td>
        <%--<td>{{$value.payment}}</td>
  		<td>{{$value.refundAmount}}</td>--%>
        <td>{{$formatPrice $value.productPrice}}</td>
 		<td>{{$formatPrice $value.payment}}</td>
		<td>{{$formatPrice $value.debtPrice}}</td>
        <td>{{$formatPrice $value.totalRefundAmount}}</td>
        <td>{{$formatPrice $value.hasRefundAmount}}</td>
        <td>{{$formatPrice $value.debtRefundAmount}}</td>
    </tr>
    {{/each}}
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{/if }}
</script>

<script id="countTpl" type="text/html">
    合计 <%--支出：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allPayment}}</span>&nbsp;|&nbsp;--%>
    <%--收入：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allHasRefundAmount}}</span>&nbsp;|&nbsp;--%>
    应付：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allProductPrice}}</span>&nbsp;|&nbsp;
    已付：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allPayment}}</span>&nbsp;|&nbsp;
    欠付：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allDebtPrice}}</span>&nbsp;|&nbsp;
    应收：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allTotalRefundAmount}}</span>&nbsp;|&nbsp;
    已收：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allHasRefundAmount}}</span>&nbsp;|&nbsp;
    欠收：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allDebtRefundAmount}}</span>
</script>

<script>
    var page;
    $(function () {
        seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
            template.helper('$formatPrice', function (price) {
                if(isNaN(price)){
                    return price;
                }else{
                    return price.toFixed(2);
                }
            });
            template.helper('$formatDate', function (millsec) {
		            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
		        });
            template.helper('$replacePrice', function (String) {
	            return ;
	        });
            var renderPagination = function(){
                page = new Pagination({
                    url: "/dealer/order/financial/details/normal/data",
                    elem: "#pagination",
                    form:$("#favorite"),
                    method:"post",
                    pageSize: 20,
                    handleData: function (json) {
                        console.log(json);
                    	$("#brandName").val(json.object.brandInfo.comName);
                        $("#brandUser").val(json.object.brand.userName);
                    	$("#brandMobile").val(json.object.brand.userMobile);
                    	$("#address").val(json.object.brand.provinceName+" "+json.object.brand.cityName+" "
                    	+json.object.brand.areaName);
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