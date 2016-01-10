<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<%--<script src="${res}/scripts/jquery.min.js"></script>--%>
<div class="tbar">
    <p class="em100 clearfix">
        <span class="fl">您好!【${userm.userName != "" ? userm.userName : userm.userMobile}】,欢迎来到终端商管理中心!
        <a href="javascript:document.getElementById('logout-form').submit();" class="c-r logout">退出登录</a>
	</span>
	<span class="fr">		
        <a href="${ctx}/dealer/dealerShoper/cart">我的进货单（${fns:countShoperNum()}）</a>
        <em>|</em>
        <a href="${ctx}/dealer/message/system?menuId=FCBE0A4638F64E8C946E08E9DB669A2D">消息：${empty headDealerMessageCount ? 0: headDealerMessageCount }条</a>
        <em>|</em>
        <a href="${ctx}/">首页</a>
        <em>|</em>
        <a href="${ctx}/search?st=b">品牌市场</a>
        <em>|</em>
        <a href="${ctx}/about">关于我们</a>
        <em>|</em>
        <a href="${ctx}/rules">规则中心</a>
        <em>|</em>
        <a href="${ctx}/help">帮助中心</a>
	</span>
    </p>
    <form:form id="logout-form" action="${ctx}/common/logout" method="post" cssStyle="display: none;">
    	<input type="hidden" name="menuForwardUrl" id="menuForwardUrl" value="" />
    </form:form>
</div>
<div class="header bgc-r">
    <div style="height: 58px;position:relative;" class="em100">
        <div style="float: left;margin-top: 8px;z-index: 0;" class="logo">
            <img src="${res}/images/dealer/logo.png" alt="logo"/>
            <a href="${ctx }/" style="width: 130px;height: 58px;position:absolute;top:0;left: 0;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
            <a href="${ctx }/dealer/center" style="width: 136px;height: 58px;position:absolute;top:0;left: 130px;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
        </div>
        <%--<c:if test="${param.isShow != 0}">
        <div class="add-quick-menu">
            <a href="javascript:dealerDefMenu.addDefMenu();"><i class="icon i-add-r"></i>添加到快捷通道</a>
        </div>
        </c:if>--%>
	<ul class="nav inline-float">
        <c:forEach items="${mainMenuList}" var="dealerMenu">
            <c:set var="openId" value="${openId}"/>
            <c:if test="${empty openId}">
                <c:set var="openId" value="${mainMenuList.get(0).refrenceId}"/>
            </c:if>
            <li>
                <a <c:if test="${dealerMenu.refrenceId==openId}">class="current"</c:if> href="${dealerMenu.menuAddr}">
                        ${dealerMenu.menuName}
                </a>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>