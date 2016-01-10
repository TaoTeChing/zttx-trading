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
        .ui-label{width: 85px;margin-left: -85px;}
        .ui-form-item select {vertical-align: middle;margin: 0 0 0;}
        .unit_price{}
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
    <div class="em100">
        <div class="main clearfix pr">
            <!--侧边导航-->
            <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
            <!--主体内容-->
            <div class="main-right">
                <!--面包屑-->
                <div class="main-grid mb10">
                    <div class="panel-crumbs">
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <a href="${ctx}/dealer/dealerFinancial/financial" >品牌财务账</a> > <span class="bb">退款列表</span>
                        <a class="panel-crumbs-help" href="${ctx}/help"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="inner">
                    <div class="main-grid pt20">
                        <form:form class="ui-form factory-seeking-form clearfix" id = "refundForm" onsubmit="return false;">
                            <input type="hidden" name="brandId" id="brandId" value="${param.brandId}"/>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                     退款类型：
                                </label>
                                <select name="needRefundSel" id="needRefundSel" class="ui-select" style="width: 180px;height:34px;">
                                    <option value="">请选择</option>
                                     <c:forEach items="${refundTypeList}" var="refundState">
                                         <c:if test="${refundState.key=='needRefund'}">
                                         <c:forEach items="${refundState.value}" var="item">
                                             <option value="${item.value}">${item.key}</option>
                                         </c:forEach>
                                        </c:if>
                                     </c:forEach>
                                </select>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                        退款单号：
                                </label>
                                <input class="ui-input" type="text" name='refundId' style="width: 180px;"/>
                            </div>
                            <div class="ui-form-item">
                                <label for="" class="ui-label">
                                         状&nbsp;&nbsp;&nbsp;&nbsp;态：
                                </label>
                                <select name="refundState" id="refundState" class="ui-select" style="width: 180px;height:34px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${refundTypeList}" var="refundState">
                                        <c:if test="${refundState.key=='refundState'}">
                                            <c:forEach items="${refundState.value}" var="item">
                                                <option value="${item.value}">${item.key}</option>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="ui-form-item" style="margin-left: -100px;">
                                <div class="inline-block">
                                    <input type="button" value="搜索" class="ui-button ui-button-mwhite event-query" style="vertical-align: 7px">
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div class="ta-r mr10">
                        <span style="line-height: 30px;">金额单位：元</span>
                    </div>
                    <table class="factory-count-table ui-table" id="count_detail">
                        <colgroup>
                            <col width="190">
                            <col width="125">
                            <col width="125">
                            <col width="125">
                            <col width="180">
                            <col width="145">
                            <col width="114">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>日期</th>
                                <th>退款单号</th>
                                <th>退款类型</th>
                                <th>退款金额</th>
                                <th>欠收抵应付金额</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <div class="ta-r mt10"><a class="ui-button ui-button-mred" href="${ctx }/dealer/dealerFinancial/refund/apply?brandId=${brandId}">申请退款</a></div>
                    <div id="pagination"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />

<script id="refund-template" type="text/html">
    {{each rows}}
	<tr>
      <td>{{$formatDate $value.applyTime}}</td>
      <td>{{$value.refundId}}</td>
      <td>{{$value.needRefund==true?'退货退款':'仅退款'}}</td>
      <td>{{$formatPrice $value.refundAmount}}</td>
      <td>{{$formatPrice $value.reachAmount}}</td>
      <td>{{if $value.refundState == 1}}
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
      <td><a class="link" href="${ctx}/dealer/dealerFinancial/factory/refund/details?refrenceId={{$value.refrenceId}}">退款详情</a></td>
   </tr>

    {{/each}}
    {{if rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="7">暂无数据</td>
    </tr>
    {{/if }}
</script>

<script>
    $(function () {
        seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
            template.helper('$formatPrice', function (price) {
            	 return isNaN(price)?'0':price.toFixed(2);
              //  return price.toFixed(2);
            });
            template.helper('$formatDate', function (millsec) {
		            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
		    });
            /**初始分页控件*/
            window['g_pagination']=new Pagination({
                url: "/dealer/dealerFinancial/refund/data",
                elem: "#pagination",
                form:$("#refundForm"),
                method:"post",
                handleData: function (json) {
                    var html1 = template.render("refund-template",json);
                    $(".factory-count-table tbody").html(html1);
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