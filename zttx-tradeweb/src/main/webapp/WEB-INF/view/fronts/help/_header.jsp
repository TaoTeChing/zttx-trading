<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<jsp:include page="${ctx}/common/top" />

<div class="header">
    <div id="search" class="clearfix">
        <div id="logo" class="fl">
            <div class="logo fl">
                <a href="${ctx}/" class="logo">
                    <img src="${res}/images/common/logo-old.png" alt="8637品牌超级代理"/>
                </a>
            </div>
            <div class="rules-center fl">
                <span>帮助中心</span>
                <%--<ul class="items">
                    <li><a href="${ctx }/apply/">加盟入驻</a></li>
                    <li><a href="${ctx }/rules/">规则中心</a></li>
                </ul>--%>
            </div>
        </div>
    </div>

    <div id="navigation" class="clearfix">
        <ul class="nav-items">
            <li class="active">
                <a class="_blank" href="${ctx }/help">帮助中心</a>
            </li>
            <li>
                <a class="_blank" href="${ctx }/rules">规则中心</a>
            </li>
        </ul>
        <div class="crumbs">
            <div class="crumbs-content">
                <span>服务中心</span> &gt; <a href="${ctx }/help">帮助中心</a>
            </div>
        </div>
    </div>
</div>
