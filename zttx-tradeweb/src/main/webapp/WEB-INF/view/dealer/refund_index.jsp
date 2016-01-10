<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理中心-退款退货管理</title>
    <link href="${res}/styles/dealer/global.css" rel="stylesheet" />
    
    <link href="${res}/styles/dealer/complain_refund.css" rel="stylesheet" />
</head>
<body>
    <!--完成-->
    <div class="container">
       <jsp:include page="${ctx}/dealer/mainmenu"/>
        <div class="em100">
            <div class="main clearfix pr">
                <!--侧边导航-->
                
                    <jsp:include page="${ctx}/dealer/dealermenu" />
               
                <!--主体内容-->
                <div class="main-right">
                    <!--面包屑-->
                    <div class="main-grid mb10">
                        <div class="panel-crumbs">
                            <i class="icon i-setting"></i><a href="${ctx}/dealer/center">管理中心</a> >> <span class="bb">退款管理</span>
                            <a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
                        </div>
                    </div>
                    <div class="inner">
                        <form:form id="form_dearler_refund" class="ui-form" onsubmit="return false;">
                            <div class="ui-form-row">
                                <div class="ui-form-item">
                                    <label class="ui-label">订单编号:</label><input name="orderNumber" class="ui-input" style="width:130px;" value=""/>
                                </div>
                                <div class="ui-form-item" style="padding-left:110px;">
                                <label class="ui-label">退款申请时间:</label>
                                   <input name='applyTimeStart' id="start-cal" readonly style="width: 110px;" type="text" class="ui-input date" value=""/>
                                    -
                                    <input name='applyTimeEnd' id="end-cal" readonly  style="width: 110px;" type="text" class="ui-input date"  value=""/>
                                </div>
                                <div class="ui-form-item">
                                    <label class="ui-label">退款编号:</label><input name="refundId" class="ui-input" style="width:180px;" value=""/>
                                </div>
                            </div>

                            <div class="ui-form-row" style="padding-top: 0;">
                                <div class="ui-form-item">
                                    <label class="ui-label">退款状态:</label>
                                    <div class="pr inline-block">
                                        <select name='mixRefundState' class="ui-select js_select" style="width:150px;">
                                            <option value='0:0' <c:if test="${dealerRefund.mixRefundState=='0:0'}">selected</c:if>>全部</option>
                                            <option value='1:1' <c:if test="${dealerRefund.mixRefundState=='1:1'}">selected</c:if>>申请退款中</option>
                                            <option value='1:2' <c:if test="${dealerRefund.mixRefundState=='1:2'}">selected</c:if>>请退货</option>
                                            <option value='1:3' <c:if test="${dealerRefund.mixRefundState=='1:3'}">selected</c:if>>已发货</option>
                                            <option value='1:4' <c:if test="${dealerRefund.mixRefundState=='1:4'}">selected</c:if>>拒绝退款</option>
                                            <option value='1:5' <c:if test="${dealerRefund.mixRefundState=='1:5'}">selected</c:if>>拒绝退货</option>
                                            <option value='1:6' <c:if test="${dealerRefund.mixRefundState=='1:6'}">selected</c:if>>退款关闭</option>
                                            <option value='1:7' <c:if test="${dealerRefund.mixRefundState=='1:7'}">selected</c:if>>取消退货</option>
                                            <option value='1:9' <c:if test="${dealerRefund.mixRefundState=='1:9'}">selected</c:if>>退款成功</option>
                                            <option value='1:10' <c:if test="${dealerRefund.mixRefundState=='1:10'}">selected</c:if>>退货退款成功</option>
                                            <option value="2:1" <c:if test="${dealerRefund.mixRefundState=='2:1'}">selected</c:if>>客服介入中</option>
                                            <option value="2:2" <c:if test="${dealerRefund.mixRefundState=='2:2'}">selected</c:if>>纠纷处理中</option>
                                            <option value="2:3" <c:if test="${dealerRefund.mixRefundState=='2:3'}">selected</c:if>>处理完毕</option>
                                            <!--
                                            <option value="2:4" <c:if test="${dealerRefund.mixRefundState=='2:4'}">selected</c:if>>纠纷关闭</option>
                                            -->
                                            <option value="2:5" <c:if test="${dealerRefund.mixRefundState=='2:5'}">selected</c:if>>等待客服介入</option>
                                        </select>
                                    </div>
                                </div>
                                <!--<div class="ui-form-item">

                                    <label class="ui-label">客服介入:</label>
                                    <select  name='cusJoinState'class="ui-select" style="width:150px;">
                                        <option value='all' <c:if test="${dealerRefund.cusJoinState=='all'}">selected</c:if>>全部</option>
                                        <option value='joined' <c:if test="${dealerRefund.cusJoinState=='joined'}">selected</c:if>>客服已介入</option>
                                    </select>
                                </div>-->
                                <button class="ui-button ui-button-lwhite" id="doSearch">搜 索</button>
                            </div>
                            <div class="ui-table-container">
                                <table class="ui-table">
                                    <colgroup>
                                        <col class="tb10" />
                                        <col class="tb10" />
                                        <col class="tb10" />
                                        <col class="tb10" />
                                        <col class="tb10" />
                                        <col class="tb15" style="width: 14%;"/>
                                        <col class="tb15" style="width: 14%;"/>
                                        <col class="tb10" />
                                        <col class="tb10" />
                                    </colgroup>
                                    <!-- 可以在class中加入ui-table-inbox或ui-table-noborder分别适应不同的情况 -->
                                    <thead>
                                        <tr>
                                            <th>退款编号</th>
                                            <th>订单编号</th>
                                            <th>品牌商</th>
                                            <th>交易金额(元)</th>
                                            <th>退款金额(元)</th>
                                            <th>退款申请时间</th>
                                            <th>退款完成时间</th>
                                            <th>退款状态</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead><!-- 表头可选 -->
                                    <tbody id="datas">
                                    </tbody>
                                </table>
                                <div class="mt10" id="pagination">
                                </div>
                            </div>
                        </form:form>
                    </div>
               	 </div>
               	 	
            </div>
        </div>
        <jsp:include page="/WEB-INF/view/dealer/agency_footer.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
    
    <script id="feedback-templage" type="text/html">
	{{each rows}}
    <tr>
	<td>{{$value.refundId}}</td>
	<td>{{$value.orderNumber}}</td>
	<td>{{$value.brandName}}</td>
	<td>{{formatNumber $value.totalAmount}}</td>
		<td>{{formatNumber $value.refundAmount}}</td>
		<td>{{$formatDate $value.applyTime}}</td>
		<td>{{if $value.returnTime!=null}}  {{$formatDate $value.returnTime}} {{/if}}</td>
		<td>
			{{if $value.serProStatus!=null}}
				{{if $value.serProStatus==1}}
					<span class="c-r">客服介入中</span>
				{{/if}}
				{{if $value.serProStatus==2}}
					<span class="c-r">纠纷处理中</span>
				{{/if}}
				{{if $value.serProStatus==3}}
					<span class="c-r">处理完毕</span>
				{{/if}}
				{{if $value.serProStatus==5}}
					<span class="c-r">等待客服介入</span>
				{{/if}}
			{{/if}}
			{{if $value.serProStatus==null || $value.serProStatus==4}}
				{{if $value.refundState==1}}
					<span class="c-r">申请退款中</span>
				{{/if}}
				{{if $value.refundState==2}}
					<span class="c-r">请退货</span>
				{{/if}}
				{{if $value.refundState==3}}
					<span class="c-r">已退货</span>
				{{/if}}
				{{if $value.refundState==4}}
					<span class="c-r">拒绝退款</span>
				{{/if}}
				{{if $value.refundState==5}}
					<span class="c-r">拒绝退货</span>
				{{/if}}
				{{if $value.refundState==6}}
					<span class="c-ch">退款关闭</span>
				{{/if}}
				{{if $value.refundState==7}}
					<span class="c-ch">取消退款</span>
				{{/if}}
				{{if $value.refundState==9}}
					<span class="c-g">退款成功</span>
				{{/if}}
				{{if $value.refundState==10}}
					<span class="c-g">退货退款成功</span>
				{{/if}}
			{{/if}}
		</td>
		<td>
		 <a href="/dealer/refund/details?orderNumber={{$value.orderNumber}}" class="link" target="_blank">查看</a>
		</td>
      </tr>
	{{/each}}
    {{ if rows.length == 0 }}
    <tr>
        <td colspan="9" style="text-align:center">暂无数据</td>
    </tr>
    {{ /if }}
	</script>
    <script>

    	var dearler_refund={};
    	
    	dearler_refund.reload=function(){
			// $('#form_dearler_refund').submit();
    	};

        
        $(function () {
            //表格样式
            $(".ui-table tr").find("td:last,th:last").css("border-right", 0);
            $(".ui-table tbody tr:odd").addClass("ui-table-split");
            var checkbox = $("td .ui-checkbox");
            $('#checkAll').click(function () {
                var checked = $(this)[0].checked;
                checkbox.each(function (i, o) {
                    o.checked = checked;
                });
            });

            $('#btn_remove').click(function () {

                if (confirm('确认删除吗？')) {
                    checkbox.each(function (i, o) {
                        if (o.checked) {
                            $(o).parent().parent().remove();
                        }
                    });
                }
            });

        });
        
        
      var page;
	  $(function () {
		    var styleNum = $("#hidd").val();
		    seajs.use(["pagination", "template", "moment"], function (Pagination, template, moment) {
		        template.helper('$formatDate', function (millsec) {
		            return moment(millsec).format("YYYY-MM-DD HH:mm:ss");
		        });
		        template.helper("formatNumber",function(price){
                    if(isNaN(price)){
                        return price;
                    }else{
                        return parseFloat(price).toFixed(2);
                    }
		        });
		        var renderPagination = function () {
		                page = new Pagination({
		                    url: "${ctx}/dealer/refund.json",
		                    elem: "#pagination",
		                    form: $("#form_dearler_refund"),
		                    method: "post",
		                    handleData: function (json) {
		                        var html = template.render("feedback-templage", json);
		                        $("#datas").html(html);
		                    }
		                });

		        };
		        renderPagination();
		    });

		    $("#doSearch").click(function () {
		        page.goTo(1);
		    });

		});
    </script>
</body>
</html>
