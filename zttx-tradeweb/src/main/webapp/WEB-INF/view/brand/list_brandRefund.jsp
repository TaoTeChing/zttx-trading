<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<%@ taglib prefix="page" tagdir="/WEB-INF/tags"%>
    <title>管理中心-退款管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/refund_detail.css"/>
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css"/>
    <style>
        .refund_list .list_table td{font-size: 12px;}
    </style>
</head>
<body>
	<jsp:include page="${ctx}/brand/mainmenu"/>
	<div class="main layout">
		 <jsp:include page="${ctx}/brand/brandmenu"/>
		<div class="main_con">
            <div class="bread_nav">
                <div class="fl">
                    <a class="link" href="${ctx}/brand/center">管理中心</a>
                    <span class="arrow">&gt;&gt;</span>
                    <span class="current">退款管理</span>
                </div>
                <div class="fr">
                    <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
                </div>
            </div>
			<div class="inner">
        <!-- 内容都放这个地方  -->

        <div class="refund_search clearfix">
            <form:form action="/brand/refund/list" id="form_dearler_refund"  class="ui-form" >
                <div class="ui-form-item">
                    <label>
                        订单编号：
                    </label>
                    <input name="orderNumber"  style="width: 224px;" type="text" class="ui-input" value="${dealerRefund.orderNumber }">
                </div>
                <div class="ui-form-item">
                    <label>
                        退款申请时间：
                    </label>
                    <input name='applyTimeStart'  id="startDate" readonly style="width: 106px;" type="text" class="ui-input Wdate" value="${dealerRefund.applyTimeStart }"/>
                    -
                    <input name='applyTimeEnd'   id="endDate" readonly  style="width: 106px;" type="text" class="ui-input Wdate"  value="${dealerRefund.applyTimeEnd }"/>
                </div>
                <div class="ui-form-item">
                    <label>
                        退款编号：
                    </label>
                    <input name="refundId"  style="width: 250px;" type="text" class="ui-input" value="${dealerRefund.refundId }"/>
                </div>
                <div class="ui-form-item">
                    <label>
                        退款状态：
                    </label>
                    <div class="inline-block">
                        <select class="ui-select js-select" name='refundState' >
                        	<option value='0'>全部</option>
                        	<c:if test="${orderRefund != null }">
                        		<c:forEach items="${orderRefund }" var="refund">
                        			<option value="${refund.dictValue }" ${dealerRefund.refundState == refund.dictValue ? 'selected="selected"' : '' }>${refund.dictValueName }</option>
                        		</c:forEach>
                        	</c:if>
                        	<c:if test="${customerStatus != null }">
                        		<c:forEach items="${customerStatus}" var="customer">
                        			<option value="${100 + customer.dictValue}" ${dealerRefund.serProStatus == customer.dictValue ? 'selected="selected"' : '' }>${customer.dictValueName }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item" style="display:none;">
                    <label for="">
                        客服介入：
                    </label>
                    <div class="inline-block">
                        <select class="ui-select js-select"  name='serProStatus'>
                        	<option value='0' >全部</option>
                        	<c:if test="${customerStatus != null }">
                        		<c:forEach items="${customerStatus}" var="customer">
                        			<option value="${customer.dictValue }" ${dealerRefund.serProStatus == customer.dictValue ? 'selected="selected"' : '' }>${customer.dictValueName }</option>
                        		</c:forEach>
                        	</c:if>
                        </select>
                    </div>
                </div>
                <div class="ui-form-item">
                    <button class="simple_button" type="button" onclick="dearler_refund.reload()">搜&nbsp;索</button>
                </div>
        </div>
        <div  class="refund_list">
            <table class="list_table">
                <colgroup>
                    <col width="90">
                    <col width="90">
                    <col width="150">
                    <col width="110">
                    <col width="100">
                    <col width="140">
                    <col width="140">
                    <col width="105">
                    <col width="80">
                </colgroup>
                <thead>
                    <tr>
                        <th>退款编号</th>
                        <th>订单号</th>
                        <th>终端商</th>
                        <th>交易金额（元）</th>
                        <th>退款金额（元）</th>
                        <th>退款申请时间</th>
                        <th>退款完成时间</th>
                        <th>退款状态</th>
                        <th class="last">操作</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                <c:when test="${!empty result.list}">
                   <c:forEach items="${result.list}" var="dealerRefund">
	                                    <tr>
	                                        <td>${dealerRefund.refundId}</td>
	                                        <td>${dealerRefund.orderNumber}</td>
	                                        <td class="ta-c">${dealerRefund.dealerName}</td>
	                                        <td>${dealerRefund.totalAmount}</td>
	                                        <td>${dealerRefund.refundAmount}</td>
	                                        <td>${fns:getStringTime(dealerRefund.applyTime,'yyyy-MM-dd HH:mm:ss')}</td>
	                                        <td>${fns:getStringTime(dealerRefund.returnTime,'yyyy-MM-dd HH:mm:ss')}</td>
	                                        <td class="ta-c">
	                                        	<c:choose>
	                                        		<c:when test="${dealerRefund.serProStatus !=null && dealerRefund.serProStatus !=4}">
	                                        			<c:choose>
															<c:when test="${dealerRefund.serProStatus==1}"><span class="status_1">客服介入中</span></c:when>
															<c:when test="${dealerRefund.serProStatus==2}"><span class="status_1">纠纷处理中</span></c:when>
															<c:when test="${dealerRefund.serProStatus==3}"><span class="status_2">处理完毕</span></c:when>
															<c:when test="${dealerRefund.serProStatus==4}"><span class="status_3">纠纷关闭</span></c:when>
															<c:when test="${dealerRefund.serProStatus==5}"><span class="status_1">等待客服介入中</span></c:when>
														 </c:choose>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        			<c:choose>
															<c:when test="${dealerRefund.refundState==1}"><span class="status_1">申请退款中</span></c:when>
															<c:when test="${dealerRefund.refundState==2}"><span class="status_1">请退货</span></c:when>
															<c:when test="${dealerRefund.refundState==3}"><span class="status_1">已退货</span></c:when>
															<c:when test="${dealerRefund.refundState==4}"><span class="status_1">拒绝退款</span></c:when>
															<c:when test="${dealerRefund.refundState==5}"><span class="status_1">拒绝退货</span></c:when>
															<c:when test="${dealerRefund.refundState==6}"><span class="status_3">退款关闭</span></c:when>
															<c:when test="${dealerRefund.refundState==7}"><span class="status_2">取消退款</span></c:when>
															<c:when test="${dealerRefund.refundState==9}"><span class="status_2">退款成功</span></c:when>
															<c:when test="${dealerRefund.refundState==10}"><span class="status_2">退货退款成功</span></c:when>
															<c:otherwise>未指定</c:otherwise>
														</c:choose>
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        </td>
	                                        <td class="ta-c">
	                                        <c:if test="${dealerRefund.serProStatus !=null}">
	                                        		 <c:choose>
														<c:when test="${dealerRefund.serProStatus==1}"><a href="${ctx}/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a></c:when>
														<c:when test="${dealerRefund.serProStatus==2}"><a href="${ctx}/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a></c:when>
														<c:when test="${dealerRefund.serProStatus==3}"><a href="${ctx}/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a></c:when>
														<c:when test="${dealerRefund.serProStatus==4}"><a href="${ctx}/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a></c:when>
														<c:when test="${dealerRefund.serProStatus==5}"><a href="${ctx}/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a></c:when>
													 </c:choose>
	                                        	</c:if>
	                                        	<c:if test="${dealerRefund.serProStatus ==null}">
	                                        	<a href="/brand/refund/view/${dealerRefund.orderNumber }" class="link" target="_blank">查看</a>
			                                       
	                                        	</c:if>
	                                        </td>
	                                    </tr>
	                                    </c:forEach>
	            	</c:when>
	            	<c:otherwise>
	            	<tr><td colspan="9" style="text-align:center;">暂无数据</td></tr>
	            	</c:otherwise>
	            	</c:choose>
                </tbody>
            </table>
            <div class="pagination">
                <page:page form="$('#form_dearler_refund')" page="${result.page}"></page:page>
            </div>
        </div>
    </div>
    </form:form>
</div>
</div>
            <jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
    <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
    <script>

    	 var dearler_refund={};
    	
    	dearler_refund.reload=function(){
			 $('#form_dearler_refund').submit();
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
        var refund_management = {
        init: function(){
            this.initCalendar();
        },
        initCalendar: function(){
            seajs.use(["my97DatePicker"],function(){

                $("#startDate").on("focus",function(){
                    WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'});
                })

                $("#endDate").on("focus",function(){
                    WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'});
                })

            })
        }
    } 
    refund_management.init();
    </script>

    <!--[if IE 6]>
    <script src="${res}/scripts/DD_belatedPNG.js"></script>
    <script>
        DD_belatedPNG.fix(".logo a,.icon");
    </script>
    <![endif]-->
</body>
</html>
