<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<div class="nav-tree">
	<div class="title">
		<h2>规则中心</h2>
	</div>
	<c:set value="${filter.cateId}" var="selectId"></c:set>
	<c:set value="${fns:getRulesCateAll(selectId) }" var="rulesCateList"></c:set>
	<ul class="tree" id="tree">
	    ${rulesCateList }
	</ul>

</div>


