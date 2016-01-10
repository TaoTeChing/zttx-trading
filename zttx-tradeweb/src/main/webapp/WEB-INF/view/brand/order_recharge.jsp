<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心-充值</title>
</head>
<body>
	充值正在跳转,请稍等!
	<form:form id="order_recharge" action="${payAgentClient.payAgentUrl}" method="post" >
		<input type="hidden" name="payReqType" value='${payObject.payReqType}'>
		<input type="hidden" name="brandId" value='${payObject.brandId}'>
		<input type="hidden" name="amount" value='${payObject.amount}'>
		<input type="hidden" name="date" value='${payObject.date}'>
		<input type="hidden" name="requestUrl" value='<%=basePath%>dealer/finance/income'>
		<input type="hidden" name="noticeUrl" value='<%=basePath%>dealer/finance/income'>
		<input type="hidden" name="paramet" value='root|id=9527|age=14'>
		<input type="hidden" name="cv" value='${payObject.cv}'>
		<input type="hidden" name="custSessionId" value='${payObject.custSessionId}'>
	</form:form>
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script type="text/javascript">
	$(function(){
		$("#order_recharge")[0].submit();
	});
	</script>
</body>
</html>