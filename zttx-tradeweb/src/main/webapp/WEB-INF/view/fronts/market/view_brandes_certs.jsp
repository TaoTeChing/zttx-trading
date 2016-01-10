<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"  %>

<html>
  <head>
    <title>查看品牌证书</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	当前品牌证书:${fn:length(certs)}条<br/>
    <c:forEach items="${certs}" var="cert">
    	<img class="l-100x100" style="width:200px;height: 200px;" src="${cert.domainName }${fns:getFormateURL(cert.imageName)} "/><br />
    </c:forEach>
  </body>
  
</html>
