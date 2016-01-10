<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/zttx.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="layout" uri="/WEB-INF/tlds/layout.tld" %>
<c:set var="weshop" value="http://192.168.1.111:8080"/>
<c:set var="noticeUrl" value="/dealer/myorder"/>
<c:set var="serviceUrl" value="/dealer/service"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="res" value="http://172.16.1.8:81"/>
<c:set var="jsrel" value="src" />
<c:set var="src" value="${res}/scripts/${jsrel}" />
<c:set var="zttx" value=".dev8.com"/>