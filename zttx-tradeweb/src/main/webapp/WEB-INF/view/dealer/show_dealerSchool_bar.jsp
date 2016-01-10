<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<!--提示框-->
<div class="main-grid mb10">
	<div class="alert c-w warning ie6">
		<b class="icon i-close"></b><i class="icon i-warning"></i><a href="#">预警消息:您有一笔订单超过15天未付款! 2013-10-16</a>
	</div>
</div>

<div class="main-grid mb10">
	<div class="panel-crumbs">
		<i class="icon i-school"></i>
		<a href="${ctx}/dealer">管理中心</a>
		<c:if test="${param.m!='1'}">
			 >> <a href="${ctx}/dealer/school/index">商学院管理</a>
		</c:if>
		<c:choose>
			<c:when test="${param.m=='1'}"> >> <span class="bb">天下商学院管理</span></c:when>
			<c:when test="${param.m=='2'}"> >> <a href="${ctx}/dealer/school/list">我的上传</a> > <span id="js-currentPage" class="bb">通过审核</span></c:when>
			<c:when test="${param.m=='3'}"> > <span id="js-currentPage" class="bb">我的学习</span></c:when>
			<c:when test="${param.m=='4'}"> > <span id="js-currentPage" class="bb">我的收藏</span></c:when>
			<c:when test="${param.m=='5'}"> > <span id="js-currentPage" class="bb">我的关注</span></c:when>
			<c:when test="${param.m=='6'}"> > <span id="js-currentPage" class="bb">我要上传</span></c:when>
		</c:choose>
		<a class="panel-crumbs-help" href="${ctx}/help/index"><i class="icon i-help"></i>帮助中心</a>
	</div>
</div>

<div class="main-grid mb10">
	<div class="panel-tabs">
		<div class="panel-head">
			<ul>
				<li<c:if test="${param.m=='1'}"> class="on"</c:if>><a href="${ctx}/dealer/school/index" class="yahei fs14">天下商学院</a></li>
				<li<c:if test="${param.m=='2'}"> class="on"</c:if>><a href="${ctx}/dealer/school/list" class="yahei fs14">我的上传</a></li>
				<li<c:if test="${param.m=='3'}"> class="on"</c:if>><a href="${ctx}/dealer/school/learn" class="yahei fs14">我的学习</a></li>
				<li<c:if test="${param.m=='4'}"> class="on"</c:if>><a href="${ctx}/dealer/school/favorite" class="yahei fs14">我的收藏</a></li>
				<li<c:if test="${param.m=='5'}"> class="on"</c:if>><a href="${ctx}/dealer/school/attent" class="yahei fs14">我的关注</a></li>
				<c:if test="${param.m=='6'}"><li class="on"><a href="${ctx}/dealer/school/execute" class="yahei fs14">我要上传</a></li></c:if>
			</ul>
		</div>
	</div>
	<c:if test="${param.m!='6'}">
	<div class="MyUploadBt">
		<a class="clearing ui-button ui-button-mred fs12" href="${ctx}/dealer/school/execute">我要上传</a>
	</div>
	</c:if>
</div>