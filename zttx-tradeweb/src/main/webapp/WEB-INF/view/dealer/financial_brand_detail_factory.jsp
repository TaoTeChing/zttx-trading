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
		<jsp:include page="${ctx}/common/menuInfo/mainmenu" />
		<div class="em100">
			<div class="main clearfix pr">
				<!--侧边导航-->
				<jsp:include page="${ctx}/common/menuInfo/sidemenu" />
				<!--主体内容-->
				<div class="main-right">
					<!--面包屑-->
					<div class="main-grid mb10">
						<div class="panel-crumbs">
							<i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a>
                            >> <a href="${ctx}/dealer/dealerFinancial/financial" >品牌商财务账</a> > <span class="bb">品牌商财务账详情</span> <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
						</div>
					</div>
                    <div class="inner">
                        <div class="main-grid">
                            <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                             <input type="hidden" value="${param.brandId}" name ="brandId">
                             <input type="hidden" value="true" name = "factoryStore">
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 品牌商名称： </label> <input
                                        class="ui-input" type="text" disabled  id="brandName"
                                        style="width: 200px;"  value="${brandInfo.brandName}"/>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 联系人姓名： </label> <input
                                        class="ui-input" type="text" disabled  id="brandUser"
                                        style="width: 190px;" value="${brandInfo.connectUserName}"/>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 联系方式： </label> <input
                                        class="ui-input" type="text" disabled  id="brandMobile"
                                        style="width: 190px;" value="${brandInfo.userMobile}" />
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 所在地：</label>
                                    <input class="ui-input" type="text" disabled  id="address" style="width: 200px;"  value="${fns:getFullAreaNameByNo(brandInfo.areaNo)}" />
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 日期： </label> <input type="text"
                                        class="ui-input date" id="start-cal" readonly
                                        style="width: 74px;"  name="startTime" /> 至 <input type="text"
                                        class="ui-input date" id="end-cal" readonly
                                        style="width: 74px;"  name="endTime"/>
                                </div>
                                <div class="ui-form-item">
                                    <label for="" class="ui-label"> 类型： </label>

                                    <select class="ui-select" name="type" style="width:210px;height: 34px;">
                                        <option value="">请选择</option>
                                        <c:forEach var="type" items="${tradeType}">
                                        <option value="${type.key}">${type.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="ui-form-item">
                                    <div class="inline-block">
                                        <input type="button" value="搜索"
                                            class="ui-button ui-button-mwhite event-query">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <%--详细表格--%>
                        <table class="factory-count-table ui-table" id="count_detail">
                            <colgroup>
                                <col width="200">
                                <col width="135">
                                <col width="120">
                                <%--
                                <col width="90">
                                <col width="90">
                                 --%>
                                <col width="120">
                                <col width="120">
                                <%--<col width="90">--%>
                                <%--<col width="90">--%>
                                <%--<col width="90"> --%>
                                <%--<col width="90">--%>
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>时间</th>
                                    <th>单号</th>
                                    <th>类型</th>
                                    <th>应付</th>
                                    <th>已付</th>

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
		</div>
		<jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp" />
	</div>
	<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
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
			{{if $value.type=='DealerOrder'}}<a class="link" href="${ctx}/dealer/dealerOrder/orderDetail/{{$value.refrenceId}}">{{$value.orderId}}</a>{{/if}}
			{{if $value.type=='DealerRefund'}}<a class="link" href="${ctx}/dealer/refund/details?refrenceId={{$value._refrenceId}}">{{$value.orderId}}</a>{{/if}}
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
         <td><a class="link" href="${ctx}/dealer/dealerFinancial/factory/adjustPrice?brandId=${param.brandId}&&adjustId={{$value.refrenceId}}">{{$formatPrice $value.adjustAllPrice}}</a></td>
         <td></td>
         {{else if $value.type == "CreditToPoint"}}
         <td>授信转返点</td>
         <td><a class="link" href="${ctx}/dealer/dealerFinancial/creditToPoint?brandId=${param.brandId}">-{{$formatPrice $value.costPrice}}</a></td>
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
    当前页合计
    应付：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allProductPrice}}</span>&nbsp;|&nbsp;
    已付：<em class="fn-rmb">￥</em><span class="consult">{{$formatPrice object.sumResult.allPayment}}</span>&nbsp;|&nbsp;
    欠付：<em class="fn-rmb">￥</em><span class="consult-r">{{$formatPrice object.sumResult.allDebtPrice}}</span>&nbsp;
</script>
	
	<script>
		$(function() {
			seajs.use(["pagination", "template","moment"], function(Pagination,template,moment){
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
						url:"${ctx}/dealer/dealerFinancial/financial/detail/data",
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
			});
		   $(".event-query").click(function(){
			   g_pagination.goTo(1);
		   });
		});
	</script>
</body>
</html>