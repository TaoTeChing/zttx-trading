<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="/styles/dealer/global.css" rel="stylesheet" />
    <link href="/styles/dealer/weshop.css" rel="stylesheet" />
</head>
<body>
<form action="${mgrurl}" id="form_mgr_weishop" method="post">
	<input name="userDes" value='${params.userDes}' type="hidden"/>
	<input name="userKey" value='${params.userKey }' type="hidden"/>
	<input name="data" value='${params.data }' type="hidden"/>
	<input name="dataLen" value='${params.dataLen }' type="hidden"/>
</form>
<jsp:include page="/WEB-INF/view/dealer/import_javascript.jsp" />

<script>
	$(function(){
		$('#form_mgr_weishop')[0].submit();
	});
</script>
</body>
</html>
