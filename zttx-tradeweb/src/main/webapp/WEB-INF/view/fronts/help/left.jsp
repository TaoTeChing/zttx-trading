<%--
  Created by IntelliJ IDEA.
  User: yefeng
  Date: 2014/4/23
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="nav-tree">
	<div class="title">
		<h2>帮助中心</h2>
	</div>
	<c:set value="${filter.cateId}" var="selectId"></c:set>
	<c:set value="${fns:getHelpCateAll(selectId) }" var="helpCateList"></c:set>
	<ul class="tree" id="tree">
		${ helpCateList}
	</ul>
</div>
