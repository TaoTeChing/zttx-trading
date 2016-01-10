<%--
  Created by IntelliJ IDEA.
  User: txsb
  Date: 2014/4/24
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include  file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div id="navigation" class="clearfix">
    <ul class="nav-items">
        <li>
            <a class="_blank" href="${ctx }/rules">首页</a>
        </li>
       <%--   ${helpCate.refrenceId == help.refrenceId || helpCate.parentId == help.refrenceId  ? 'active' : '' } --%>
        <c:set value="${fns:getRulesCates(1, 7) }" var="cateList"></c:set>
        <c:forEach items="${cateList }" var="cate" >
        	 <li class="${cate.refrenceId == param.id || cate.refrenceId == param.parentId  ? 'active' : '' }">
	            <a href="${ctx }/common/rules/list?cateId=${cate.refrenceId}&cateType=${cate.cateType }">${cate.cateName }</a>
	        </li>
        </c:forEach>
    </ul>
</div>
