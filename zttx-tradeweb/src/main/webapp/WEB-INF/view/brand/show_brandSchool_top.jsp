<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!-- 面包导航，并不是每个页面都有 -->
<div class="bread_nav">
	<div class="fl">
		<i class="icon"></i>
		<a class="link" href="${ctx}/brand/center">管理中心</a>
		<c:if test="${param.m!='1'}">
			<span class="arrow">&gt;&gt;</span><a class="link" href="${ctx}/brand/school/index">商学院管理</a>
		</c:if>
		<c:choose>
			<c:when test="${param.m=='1'}"><span class="arrow">&gt;&gt;</span><span>天下商学院</span></c:when>
			<c:when test="${param.m=='2'}"><span class="arrow">&gt;&gt;</span><a class="link" href="${ctx}/brand/school/list">我的上传</a><span class="arrow">&gt;</span><span id="js-currentPage" class="bb">通过审核</span></c:when>
			<c:when test="${param.m=='3'}"><span class="arrow">&gt;</span><span id="js-currentPage" class="bb">我的学习</span></c:when>
			<c:when test="${param.m=='4'}"><span class="arrow">&gt;</span><span id="js-currentPage" class="bb">我的收藏</span></c:when>
			<c:when test="${param.m=='5'}"><span class="arrow">&gt;</span><span id="js-currentPage" class="bb">我的关注</span></c:when>
			<c:when test="${param.m=='6'}"><span class="arrow">&gt;</span><span id="js-currentPage" class="bb">我要上传</span></c:when>
		</c:choose>
	</div>
	<div class="fr">
		<jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" />
	</div>
</div>