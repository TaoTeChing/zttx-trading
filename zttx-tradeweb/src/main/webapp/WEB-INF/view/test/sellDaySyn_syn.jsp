<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>未结的应付账款数据导入至交易平台</title>
</head>
<body>
	<div>
		<form:form action="${ctx}/common/temp/sellDaySyn/syn">
			<input type="hidden" name="type" value="1" />
			<div>
				<span>日期范围：</span>
				<input class="text date str-date hasDatepicker" id="startCal" readonly="readonly" name="fromDate" />
				 - 
				<input class="text date end-date hasDatepicker" id="endCal" readonly="readonly" name="toDate" />
			</div>
			<input type="submit" value="导入" />
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
	
	<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
	<script>
		rangeCalendar("startCal","endCal");
	</script>
</body>
</html>