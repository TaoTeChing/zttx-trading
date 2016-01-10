<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>保留域名接口测试页面</title>
</head>
<body>
		<h3>添加</h3>
		<form:form action="${ctx }/client/reservedomain/listAll?userKey=FE3141781F1441B2B912BF47934BF334&userDes=4D&dataLen=0&data=" method="post">
			<input type="submit" value="提交">
		</form:form>
</body>
</html>