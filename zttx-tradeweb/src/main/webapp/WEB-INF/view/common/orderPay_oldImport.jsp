<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>老的支付数据迁移</title>
</head>
<body>
	<div>
		<form:form action="${ctx}/common/orderPay/oldImport">
			<input type="hidden" name="type" value="1" />
			<input type="submit" value="迁移" />
		</form:form>
	</div>
	<c:if test="${not empty result}">
		<div>结果：${result}</div>
	</c:if>
	<c:if test="${not empty msgList}">
		<div>
			<c:forEach var="msg" items="${msgList}" varStatus="status">
				<div>${status.index + 1}：${msg}</div>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>