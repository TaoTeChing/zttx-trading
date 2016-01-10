<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="nav" id="nav">
    <div class="nav-main" id="navMain">
        <ul>
            ${fns:getInfoNavHtml(filter.cateId)}
        </ul>
        <div class="nav-arrow"></div>
    </div>
</div>