<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-当期销售明细</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    <link href="${res}/styles/dealer/factory.css" rel="stylesheet" />
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
                        <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">当期销售明细</span>
                        <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                    </div>
                </div>
                <div class="main-grid mb10">
                    <form:form id="favorite" name="pageForm" class="ui-form factory-seeking-form clearfix">
                        <input type="hidden" name="supplierId" value="${param.brandId}"/>
                        <input type="hidden" name="searchDate" value="${param.date}"/>
                        
                        <div class="ui-form-item" style="width: 240px;">
                            <label for="" class="ui-label">
                                产品货号：
                            </label>
                            <input class="ui-input" type="text" name="productNo" style="width: 150px;" />
                        </div>
                        <div class="ui-form-item" style="width: 240px;">
                            <label for="" class="ui-label">
                                产品名称：
                            </label>
                            <input class="ui-input" type="text" name="productName" style="width: 150px;" />
                        </div>
                        <div class="ui-form-item" style="width: 100px;">
                            <div class="inline-block">
                                <input type="button" value="搜索" class="ui-button ui-button-mwhite event-query">
                            </div>
                        </div>
                    </form:form>
                </div>
                <%--详细表格--%>
                <table class="factory-count-table ui-table" id="count_detail">
                    <colgroup>
                        <col width="125">
                        <col width="200">
                        <col width="175">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>产品货号</th>
                            <th>产品名称</th>
                            <th>颜色/尺码</th>
                            <th>销售总量</th>
                            <th>退货数量</th>
                            <th>销售额</th>
                            <th>盘亏数量</th>
                            <th>盘亏成本</th>
                            <th>合计成本</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <%--合计表格
                <table class="factory-count-table ui-table" id="count_count" style="border-top:0;">
                    <colgroup>
                        <col width="125">
                        <col width="145">
                        <col width="125">
                        <col width="105">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                        <col width="125">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>合计</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                            <td>5000.00</td>
                        </tr>
                    </tbody>
                </table>
                --%>
                <div id="pagination"></div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
</div>
<jsp:include page="${ctx}/WEB-INF/view/dealer/import_javascript.jsp" />
<script id="feedback-templage" type="text/html">
	{{each rows}}
      <tr>
                            <td>{{$value.productNo}}</td>
                            <td>{{$value.productName}}</td>
                            <td>{{$value.colorSize}}</td>
                            <td>{{$value.saleNum}}</td>
                            <td>{{$value.backNum}}</td>
                            <td>{{formatPrice $value.saleTotalPrice}}</td>
							<td>{{$value.pkNum}}</td>
							<td>{{formatPrice $value.pkTotalCostPrice}}</td>
                            <td>{{formatPrice($value.saleTotalCostPrice - $value.backTotalCostPrice + $value.pkTotalCostPrice)}}</td>
      </tr>
	{{/each}}
    {{if !rows || rows.length == 0 }}
    <tr>
        <td class="ta-c" colspan="10">暂无数据</td>
    </tr>
    {{/if }}
</script>
<script>
    	
    	$(function () {
            seajs.use(["pagination","template","moment"], function (Pagination,template,moment) {
                template.helper('formatPrice', function (price) {
                	if (isNaN(price)) {
                        return price;
                    } else {
                        return parseFloat(price).toFixed(2);
                    }
                });
                template.helper('$formatDate', function (millsec) {
    		            return moment(millsec).format("YYYY-MM-DD");
    		    });
                window['g_Pagination'] = new Pagination({
                    url: "/dealer/dealerFinancial/saleDetail/data",
                    elem: "#pagination",
                    form:$("#favorite"),
                    method:"post",
                    handleData: function (json) {
                    	  var html1 = template.render("feedback-templage",json);
                          $(".factory-count-table tbody").empty().append(html1);
                    }
                });
            });
            $(".event-query").click(function(){
            	g_Pagination.goTo(1);
            });
        });
</script>
</body>
</html>