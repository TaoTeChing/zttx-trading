<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>页面跳转</title>
    <meta name="keywords" content="8637品牌超级代理-品牌市场" />
    <meta name="description" content="8637品牌超级代理-品牌市场" />
</head>
<body>
    <div>
  		页面跳转...
    </div>
    <form:form id="postForm" action="${action}" method="${empty method ? 'post' : method}">
    	<c:forEach var="obj" items="${dataMap}">
    		<input type="hidden" name="${obj.key}" value="${obj.value}">
		</c:forEach>
    </form:form>
<script src="${res}/scripts/jquery.min.js"></script>
<script>
    $(function(){
    	$('#postForm').submit();
    });
</script>
</body>
</html>
