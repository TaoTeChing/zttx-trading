<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:if test="${dealerMessage!=null}">
<div class="main-grid alerts">
	<div class="alert c-w warning mb10">
		<b class="iconfont">&#xe602;</b> <i class="iconfont">&#xe613;</i> <a href="${ctx }/dealer/message/readMsg/${dealerMessage.refrenceId}?currentPage=0&pageSize=20">系统消息:${dealerMessage.msgTitle}  &nbsp;${fns:getStringTime(dealerMessage.createTime,"yyyy-MM-dd")}</a>
	</div>
</div>
</c:if>

