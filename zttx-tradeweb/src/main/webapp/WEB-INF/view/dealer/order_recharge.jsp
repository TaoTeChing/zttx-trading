<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理中心  &gt; 充值</title>
</head>
<body>
	充值正在跳转,请稍等!
	<form:form id="order_recharge" action="${payAgentClient.payAgentUrl}" >
		<input type="hidden" name="payReqType" id="pay_type" value='${payObject.payReqType}'>
		<input type="hidden" name="dealerId" id="pay_dealerid" value='${payObject.dealerId}'>
		<input type="hidden" name="amount" id="pay_amount" value='${payObject.amount}'>
		<input type="hidden" name="date" id="pay_date" value='${payObject.date}'>
		<input type="hidden" name="cv" id="pay_cv" value='${payObject.cv}'>
		<input type="hidden" name="requestUrl" id="pay_requestUrl" value='<%=basePath%>dealer/finance/income'>
		<input type="hidden" name="noticeUrl" id="pay_noticeUrl" value='<%=basePath%>dealer/finance/income'>
		<input type="hidden" name="custSessionId" id="pay_custSessionId" value='${payObject.custSessionId}'>
		<input type="hidden" name="paramet" id="pay_paramet" value='root|id=9527|age=14'>
	</form:form>
	<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />
	<script type="text/javascript">
	$(function(){
		$("#order_recharge")[0].submit();
	});
	</script>
</body>
</html>