<!doctype html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
    <title>管理中心-我的消息-消息管理</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/myMessage.css"/>
</head>
<body>
<jsp:include page="${ctx}/common/menuInfo/mainmenu"/>
	<div class="main layout">
        <jsp:include page="${ctx}/common/menuInfo/sidemenu"/>
<div class="main_con">
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="${ctx}/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/message/list">我的消息</a>
            <span class="arrow">&gt;</span>
            <span class="current">消息管理</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp" >
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <div class="mymessage">
            <div class="message-info-title">
                <h3>${msgTitle}</h3>
            </div>
            <div class="message-info-some clearfix">
                <a class="ui_button inline-block ui_button_mblue" href="javascript:void(0);" onclick="returnList();">返回列表</a>
                <div>
                	<c:choose>
                		<c:when test="${msgCate eq 1}"><span>类型:系统消息</span></c:when>
                		<c:when test="${msgCate eq 2}"><span>类型:站内消息</span></c:when>
                		<c:when test="${msgCate eq 3}"><span>类型:订单消息</span></c:when>
                		<c:when test="${msgCate eq 4}"><span>类型:在线留言</span></c:when>
                		<c:otherwise><span>类型:未定义${msgCate}</c:otherwise>
                	</c:choose>
                    <span class="ml15">时间: ${fns:getTimeFromLong(msgCreateTime,"yyyy-MM-dd HH:mm")}</span>
                    <span class="ml15">发件人:${msgSenderName}</span>
                </div>
            </div>
            <div class="message-info-content">${msgContent}</div>
            <%--
            留言消息回复
            <div class="message-info-reply">
                <form class="ui-form messageReply" data-widget="validator">
                    <div class="ui-form-item">
                        回复留言：
                    </div>
                    <div class="ui-form-item">
                        <textarea class="ui-textarea" required data-display="留言信息"></textarea>
                    </div>
                    <div class="ui-form-item">
                        <input class="simple_button fr" type="submit" value="回复"/>
                    </div>
                </form>
            </div>--%>
        </div>
    </div>
</div>
</div>
<form:form id="js-msgList" action="${ctx}/brand/message/list" method="post">
	<input type="hidden" name="pageSize" value="${empty param.pageSize ? 0 : param.pageSize}" />
	<input type="hidden" name="currentPage" value="${empty param.currentPage ? 0 : param.currentPage}" />
	<input type="hidden" name="listType" value="${param.listType}" />
	<input type="hidden" name="searchType" value="${param.searchType}" />
	<input type="hidden" name="searchWord" value="${param.searchWord}" />
	<input type="hidden" name="msgStatus" value="${param.msgStatus}" />
	<input type="hidden" name="sendBeginTime" value="${param.sendBeginTime}" />
	<input type="hidden" name="sendEndTime" value="${param.sendEndTime}" />
    <input type="hidden" name="menuId" value="<%=request.getParameter("menuId")%>"/><%--todo 锁定菜单 临时添加--%>
</form:form>
<jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
<jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />
<script src="${src}/brand/myMessage.js"></script>
<script>
    myMessagemsgman.init();

    function returnList()
    {
    	$("#js-msgList").submit();
    }
</script>
</body>
</html>