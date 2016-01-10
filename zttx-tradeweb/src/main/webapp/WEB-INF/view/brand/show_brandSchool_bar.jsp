<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<div class="main-grid">
	<div class="panel-tabs">
		<div class="panel-head">
			<ul>
				<li<c:if test="${param.m=='1'}"> class="on"</c:if>><a href="${ctx}/brand/school/index" class="yahei fs14">天下商学院</a></li>
				<li<c:if test="${param.m=='2'}"> class="on"</c:if>><a href="${ctx}/brand/school/list" class="yahei fs14">我的上传</a></li>
				<li<c:if test="${param.m=='3'}"> class="on"</c:if>><a href="${ctx}/brand/school/learn" class="yahei fs14">我的学习</a></li>
				<li<c:if test="${param.m=='4'}"> class="on"</c:if>><a href="${ctx}/brand/school/favorite" class="yahei fs14">我的收藏</a></li>
				<li<c:if test="${param.m=='5'}"> class="on"</c:if>><a href="${ctx}/brand/school/attent" class="yahei fs14">我的关注</a></li>
				<c:if test="${param.m=='6'}"><li class="on"><a href="${ctx}/brand/school/execute" class="yahei fs14">我要上传</a></li></c:if>
			</ul>
		</div>
	</div>
	<c:if test="${param.m!='6'}">
	<div class="MyUploadBt">
		<a class="clearing ui_button ui_button_mblue fs12" href="${ctx}/brand/school/execute">我要上传</a>
	</div>
	</c:if>
</div>