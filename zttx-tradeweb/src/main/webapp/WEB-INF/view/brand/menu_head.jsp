<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--<script src="${res}/scripts/jquery.min.js"></script>--%>
<div class="tbar">
    <p class="layout clearfix">
        <span class="fl">
            您好！${userm.userName != "" ? userm.userName : userm.userMobile}，欢迎来到8637品牌超级代理管理中心！
            <a href="javascript:document.getElementById('logout-form').submit();" class="c_red logout">退出登录</a>
        </span>
        <span class="fr">
            <a class="ml5" href="${ctx}/brand/message/list?msgStatus=2&menuId=8888C7B3E4A447D6999C37B4C031862B">消息：<span class="c_red">${empty headBrandMessageCount ? 0: headBrandMessageCount }</span> 条</a>
            <em>|</em>
            <a href="${ctx}/">首页</a>
            <em>|</em>
            <a href="${ctx}/brand/center">品牌中心</a>
            <em>|</em>
            <a href="${ctx}/rules">加盟规则</a>
            <em>|</em>
            <a href="${ctx}/help">帮助中心</a>
            <em>|</em>
            <a href="${ctx}/about">关于我们</a>
        </span>
    </p>
    <form:form id="logout-form" action="${ctx}/common/logout" method="post" cssStyle="display: none;"></form:form>
</div>
<c:choose>
	<c:when test="${1==headType}">
		<div id="header" class="header clearfix">
	    <div class="layout">
	        <div class="fl logo" style="position:relative;">
	            <img src="${res}/images/brand/sign_logo.png" alt=""/>
	             <a href="${ctx}/" style="width:130px;height:55px; position:absolute;  left:0;top:0;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
	             <a href="${ctx}/brand/activity/list" style="width:136px;height:55px;position:absolute;  left:130px;top:0;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
	            
	        </div>
	    </div>
		</div>
	</c:when>
	<c:otherwise>
	<div id="header" class="header clearfix">
    <div class="layout">
        <div class="fl logo" style="position: relative;">
            <img src="${res}/images/brand/logo.png" alt=""/>
            <a href="${ctx}/" style="width: 130px;height: 58px;position: absolute;left: 0;top:0;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
            <a href="${ctx}/brand/center" style="width: 136px;height: 58px;position: absolute;left: 130px;top:0;z-index: 99;background: #fff;_background:none;opacity: 0;filter:Alpha(opacity=0);"></a>
        </div>
        <c:if test="${!empty mainMenuList}">
            <ul class="nav inline-float">
                <c:forEach items="${mainMenuList}" var="menu">
                	<c:set var="openId" value="${openId}"/>
                	<c:if test="${empty openId}">
                	<c:set var="openId" value="${mainMenuList.get(0).refrenceId}"/>
                	</c:if>
                    <li>
                        <a <c:if test="${menu.refrenceId==openId}">class="current"</c:if> href="${menu.menuAddr}">
                                ${menu.menuName}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <%--<form:form class="search_form" action="#">
            <input name="selectType" id="selectType" type="hidden" value="1"/>
            <div class="search_box fl">
                <div class="select">
                    <span class="type">品牌</span>
                    <i class="down_arrow iconfont">&#xe605;</i>
                    <i class="up_arrow iconfont">&#xe606;</i>
                    <ul style="display: none;">
                        <li><a href="javascript:void(0);" data-type="1">品牌</a></li>
                        <li><a href="javascript:void(0);" data-type="2">产品</a></li>
                    </ul>
                </div>
                <div class="search_input fl">
                    <input placeholder="输入关键字或您需要的产品" type="text"/>
                </div>
                <button type="submit" class="iconfont">&#xe600;</button>
            </div>
        </form:form>--%>
    </div>
	</div>
	<div id="add_cloud" class="add_cloud">
	    <a class="close" href="javascript:;">关闭</a>
	</div>
	</c:otherwise>
</c:choose>
